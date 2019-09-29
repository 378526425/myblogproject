package com.wl.servicefegin.fallback;

import com.wl.blog.viewmodel.ArticleCommentViewModel;
import com.wl.blog.viewmodel.CommentMessageViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.service.ArticleCommentService;
import org.springframework.stereotype.Component;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-09-27 15:01
 **/
@Component
public class ArticleCommentFallBack implements ArticleCommentService {
    @Override
    public JrsfReturn addArticleComment(ArticleCommentViewModel articleCommentViewModel) {
        return null;
    }

    @Override
    public JrsfReturn getArticleCommentByArticleId(String articleId, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public JrsfReturn addCommentMessage(CommentMessageViewModel commentMessageViewModel) {
        return null;
    }

    @Override
    public JrsfReturn getCommentMessage(String commentId, int pageIndex, int pageSize) {
        return null;
    }
}
