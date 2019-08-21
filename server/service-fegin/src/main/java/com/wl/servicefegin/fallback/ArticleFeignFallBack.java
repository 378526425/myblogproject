package com.wl.servicefegin.fallback;

import com.wl.blog.viewmodel.ArticleViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.service.ArticleService;
import org.springframework.stereotype.Component;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-21 15:53
 **/
@Component
public class ArticleFeignFallBack implements ArticleService {
    @Override
    public JrsfReturn addArticle(ArticleViewModel articleViewModel) {
        return null;
    }

    @Override
    public JrsfReturn deleteArticle(String id) {
        return null;
    }

    @Override
    public JrsfReturn updateArticle(ArticleViewModel articleViewModel) {
        return null;
    }

    @Override
    public JrsfReturn getArticle(String id) {
        return null;
    }

    @Override
    public JrsfReturn getPageArticle(String condition, int pageIndex, int pageSize) {
        return null;
    }
}
