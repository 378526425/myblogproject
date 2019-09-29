package com.wl.servicefegin.controller;

import com.wl.blog.viewmodel.ArticleCommentViewModel;
import com.wl.blog.viewmodel.CommentMessageViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-09-27 14:40
 **/
@RestController
@RequestMapping("/api")
public class ArticleCommentController {
    @Autowired
    ArticleCommentService articleCommentService;

    @PostMapping("/ArticleComment")
    JrsfReturn addArticleComment(@RequestBody @Validated ArticleCommentViewModel articleCommentViewModel) {
        return this.addArticleComment(articleCommentViewModel);
    }

    /**
     * @Description: 查询文章评论
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/9/23
     */
    @GetMapping("/ArticleComment")
    JrsfReturn getArticleCommentByArticleId(@RequestParam String articleId, @RequestParam(defaultValue = "1") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        return this.articleCommentService.getArticleCommentByArticleId(articleId, pageIndex, pageSize);
    }

    /**
     * @Description: 添加评论的评论
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/9/23
     */
    @PostMapping("/CommentMessage")
    JrsfReturn addCommentMessage(CommentMessageViewModel commentMessageViewModel) {
        return this.articleCommentService.addCommentMessage(commentMessageViewModel);
    }

    @GetMapping("/CommentMessage")
    JrsfReturn getCommentMessage(@RequestParam String commentId, @RequestParam(defaultValue = "1") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        return this.articleCommentService.getCommentMessage(commentId, pageIndex, pageSize);
    }
}
