package com.wl.blog.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.wl.blog.server.entity.ArticleComment;
import com.wl.blog.server.entity.CommentMessage;
import com.wl.blog.server.entity.querydsl.QArticleComment;
import com.wl.blog.server.entity.querydsl.QCommentMessage;
import com.wl.blog.server.entity.querydsl.QUser;
import com.wl.blog.utils.BlogUtil;
import com.wl.blog.viewmodel.ArticleCommentViewModel;
import com.wl.blog.viewmodel.CommentMessageViewModel;
import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.dao.BaseDao;
import com.wl.common.entity.ResultSet;
import com.wl.common.exception.JrsfException;
import com.wl.common.utils.JrsfReturn;
import com.wl.common.utils.JrsfUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-22 15:25
 **/
@Service
public class ArticleCommentService {
    @Autowired
    BaseDao baseDao;

    /**
     * @Description: 文章留言
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/8/22
     */
    @Transactional
    public JrsfReturn addArticleComment(ArticleCommentViewModel articleCommentViewModel) {
        ArticleComment articleComment = new ArticleComment();
        BeanUtils.copyProperties(articleCommentViewModel, articleComment);
        if (BlogUtil.getLoginUser() == null) {
            throw new JrsfException("未登录！");
        }
        articleComment.setUserId(BlogUtil.getLoginUser().getId());
        this.baseDao.save(articleComment);
        return JrsfReturn.ok();
    }

    /**
     * @Description: 根据id查询文章的评论
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/8/23
     */
    public JrsfReturn getArticleComment(String articleId, int pageIndex, int pageSize) {
        QArticleComment qArticleComment = QArticleComment.articleComment;
        QUser qUser = QUser.user;
        JPAQuery<ArticleCommentViewModel> jpaQuery = new JPAQuery<>(this.baseDao.getEntityManager());
        jpaQuery.from(qArticleComment).leftJoin(qUser).on(qArticleComment.userId.eq(qUser.id))
                .select(Projections.bean(ArticleCommentViewModel.class
                        , qArticleComment.content
                        , qArticleComment.commentCount
                        , qArticleComment.createdTime
                        , Projections.bean(UserViewModel.class
                                , qUser.userName
                                , qUser.portrait
                        ).as("userViewModel")
                ))
                .where(qArticleComment.articleId.eq(articleId))
                .orderBy(qArticleComment.createdTime.desc());
        ResultSet<ArticleCommentViewModel> resultSet = new ResultSet<>();
        JrsfUtil.getListByJpaQuery(resultSet,jpaQuery,pageIndex,pageSize);
        return JrsfReturn.okData(resultSet);
    }

    public JrsfReturn addCommentMessage(CommentMessageViewModel commentMessageViewModel) {
        if (BlogUtil.getLoginUser() == null) {
            throw new JrsfException("未登录！");
        }
        CommentMessage commentMessage = new CommentMessage();
        BeanUtils.copyProperties(commentMessageViewModel, commentMessage);
        commentMessage.setUserId(BlogUtil.getLoginUser().getId());
        this.baseDao.save(commentMessage);
        String commentId = commentMessageViewModel.getCommentId();
        QArticleComment qArticleComment = QArticleComment.articleComment;
        JPAQuery<ArticleComment> jpaQuery = new JPAQuery<>(this.baseDao.getEntityManager());
        ArticleComment articleComment = jpaQuery.select(qArticleComment).from(qArticleComment).where(qArticleComment.id.eq(commentId)).fetchFirst();
        articleComment.setCommentCount(articleComment.getCommentCount() + 1);
        this.baseDao.update(articleComment);
        return JrsfReturn.ok();
    }

    /**
     * @Description: 评论回复
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/9/26
     */
    public JrsfReturn getCommentMessage(String commentId, int pageIndex, int pageSize) {
        QCommentMessage qCommentMessage = QCommentMessage.commentMessage;
        JPAQuery<CommentMessageViewModel> jpaQuery = new JPAQuery<>(this.baseDao.getEntityManager());
        jpaQuery.from(qCommentMessage).select(
                Projections.bean(CommentMessageViewModel.class,
                        qCommentMessage.id,
                        qCommentMessage.commentId,
                        qCommentMessage.content,
                        qCommentMessage.createdTime,
                        qCommentMessage.toUserId,
                        qCommentMessage.userId)
        ).where(qCommentMessage.commentId.eq(commentId)).orderBy(qCommentMessage.createdTime.desc()).offset((pageIndex-1)*pageSize).limit(pageSize);
        ResultSet<CommentMessageViewModel> resultSet=new ResultSet<>();
        JrsfUtil.getListByJpaQuery(resultSet,jpaQuery,pageIndex,pageSize);
        return JrsfReturn.okData(resultSet);
    }
}
