package com.wl.blog.service;

import com.alibaba.fastjson.JSON;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wl.blog.server.entity.Article;
import com.wl.blog.server.entity.querydsl.QArticle;
import com.wl.blog.server.entity.querydsl.QUser;
import com.wl.blog.utils.BlogUtil;
import com.wl.blog.viewmodel.ArticleViewModel;
import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.dao.BaseDao;
import com.wl.common.entity.ResultSet;
import com.wl.common.exception.JrsfException;
import com.wl.common.utils.FileUtil;
import com.wl.common.utils.JrsfReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-05 15:44
 **/
@Service
public class ArticleService {
    @Autowired
    BaseDao baseDao;

    @Transactional
    public JrsfReturn addArticle(ArticleViewModel articleViewModel) {
        if (BlogUtil.getLoginUser()==null)
        {
            throw new JrsfException("未登录");
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleViewModel, article);

        article.setImg(upLoadimg(articleViewModel));
        article.setUserId(BlogUtil.getLoginUser().getId());
        this.baseDao.save(article);
        return JrsfReturn.okData(article);
    }


    @Transactional
    public JrsfReturn deleteArticle(String id) {

        Article article = getArticleById(id);
        checkPower(article);
        FileUtil.deleteFileOrDire(BlogUtil.fileAbsolutePath(article.getImg()));
        this.baseDao.delete(article);
        return JrsfReturn.okMsg("已删除！");
    }

    private void checkPower(Article article) {
        if (BlogUtil.getLoginUser()==null)
        {
            throw new JrsfException("未登录");
        }
        if (BlogUtil.getLoginUser().getRoleList().get(0).getRoleGrade()>0&&!BlogUtil.getLoginUser().getId().equals(article.getUserId()))
        {
            throw new JrsfException("权限不足！");
        }
    }

    @Transactional
    public JrsfReturn updateArticle(ArticleViewModel articleViewModel) {
        Article article = getArticleById(articleViewModel.getId());
        checkPower(article);
        if (StringUtils.isEmpty(articleViewModel.getBase64())) {
            articleViewModel.setImg(article.getImg());
        } else {
            FileUtil.deleteFileOrDire(BlogUtil.fileAbsolutePath(article.getImg()));
            articleViewModel.setImg(upLoadimg(articleViewModel));
        }
        articleViewModel.setUserId(article.getUserId());
        articleViewModel.setCreatedTime(article.getCreatedTime());
        BeanUtils.copyProperties(articleViewModel, article);
        this.baseDao.update(article);
        return JrsfReturn.okData(article);
    }

    @Transactional
    public JrsfReturn getArticle(String id) {
        Article article = getArticleById(id);
        article.setReadCount(article.getReadCount() + 1);
        this.baseDao.update(article);
        return JrsfReturn.okData(article);
    }

    public JrsfReturn getArticle(String condition, int pageIndex, int pageSize) {
        Map<String, Object> paramMap = JSON.parseObject(condition);
        QArticle qArticle = QArticle.article;
        QUser qUser = QUser.user;
        JPAQuery<ArticleViewModel> jpaQuery = new JPAQuery<>(this.baseDao.getEntityManager());
        jpaQuery.from(qArticle).leftJoin(qUser).on(qArticle.userId.eq(qUser.id)).select(Projections.bean(ArticleViewModel.class
                , qArticle.id
                , qArticle.createdTime
                , qArticle.modifiedTime
                , qArticle.img
                , qArticle.introduction
                , qArticle.title
                , Projections.bean(UserViewModel.class
                        , qUser.userName
                        , qUser.portrait
                ).as("userViewModel")
        )).offset((pageIndex - 1) * pageSize)
                .limit(pageSize).orderBy(qArticle.modifiedTime.desc());
        if (BlogUtil.conditionParamCheck(paramMap, "title")) {
            jpaQuery.where(qArticle.title.like("%" + paramMap.get("title").toString() + "%")
                    .or(qArticle.introduction.like("%" + paramMap.get("title").toString() + "%"))
                    .or(qArticle.content.like("%" + paramMap.get("title").toString() + "%")));
        }
        if (BlogUtil.conditionParamCheck(paramMap, "userId")) {
            jpaQuery.where(qArticle.userId.eq(paramMap.get("userId").toString()));
        }
        ResultSet<ArticleViewModel> resultSet = new ResultSet<>();
        resultSet.setTotal(jpaQuery.fetchCount());
        List<ArticleViewModel> articleViewModels = jpaQuery.fetch();
        resultSet.setRows(articleViewModels);
        return JrsfReturn.okData(resultSet);
    }

    private Article getArticleById(String id) {
        QArticle qArticle = QArticle.article;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.baseDao.getEntityManager());
        Article article = jpaQueryFactory.selectFrom(qArticle).where(qArticle.id.eq(id)).fetchFirst();
        return article;
    }

    private String upLoadimg(ArticleViewModel articleViewModel) {
        String imgName = articleViewModel.getImgName();
        String imgPath = "articleimg" + File.separator + UUID.randomUUID().toString().replace("-", "") + imgName;
        FileUtil.base64ToFile(articleViewModel.getBase64(), BlogUtil.fileAbsolutePath(imgPath));
        return imgPath;
    }

    @Transactional
    public JrsfReturn likeArticle(String id) {
        Article article = getArticleById(id);
        long likeCount = article.getLikeCount();
        article.setLikeCount(likeCount + 1);
        this.baseDao.update(article);
        return JrsfReturn.ok();
    }

    @Transactional
    public JrsfReturn dislikeArticle(String id) {
        Article article = getArticleById(id);
        long dislikeCount = article.getDislikeCount();
        article.setDislikeCount(dislikeCount + 1);
        this.baseDao.update(article);
        return JrsfReturn.ok();
    }

}
