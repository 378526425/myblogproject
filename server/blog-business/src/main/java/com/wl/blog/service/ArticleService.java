package com.wl.blog.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wl.blog.server.entity.Article;
import com.wl.blog.server.entity.querydsl.QArticle;
import com.wl.blog.viewmodel.ArticleViewModel;
import com.wl.common.dao.BaseDao;
import com.wl.common.utils.JrsfReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        Article article = new Article();
        BeanUtils.copyProperties(articleViewModel, article);
        this.baseDao.save(article);
        return JrsfReturn.ok();
    }

    @Transactional
    public JrsfReturn deleteArticle(String id) {
        QArticle qArticle = QArticle.article;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.baseDao.getEntityManager());
        long result = jpaQueryFactory.delete(qArticle).where(qArticle.id.eq(id)).execute();
        return JrsfReturn.okMsg(String.valueOf(result));
    }

    @Transactional
    public JrsfReturn updateArticle(ArticleViewModel articleViewModel) {
        Article article = new Article();
        BeanUtils.copyProperties(articleViewModel, article);
        this.baseDao.update(article);
        return JrsfReturn.ok();
    }

    public JrsfReturn getArticle(String id) {
        QArticle qArticle = QArticle.article;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.baseDao.getEntityManager());
        Article article = jpaQueryFactory.selectFrom(qArticle).where(qArticle.id.eq(id)).fetchFirst();
        return JrsfReturn.okData(article);
    }
}
