package com.wl.blog.controller;

import com.wl.blog.service.ArticleCommentService;
import com.wl.blog.viewmodel.ArticleCommentViewModel;
import com.wl.blog.viewmodel.CommentMessageViewModel;
import com.wl.common.utils.JrsfReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-22 15:24
 **/
@RestController
@RequestMapping("/api")
public class ArticleCommentController {
    @Autowired
    ArticleCommentService articleCommentService;

    /**
     * @Description: 添加文章评论
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/9/23
     */
    @PostMapping("/ArticleComment")
    public JrsfReturn addArticleComment(@RequestBody @Validated ArticleCommentViewModel articleCommentViewModel) {
        return this.articleCommentService.addArticleComment(articleCommentViewModel);
    }

    /**
     * @Description: 查询文章评论
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/9/23
     */
    @GetMapping("/ArticleComment")
    public JrsfReturn getArticleCommentByArticleId(@RequestParam String articleId, @RequestParam(defaultValue = "1") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        return this.articleCommentService.getArticleComment(articleId, pageIndex, pageSize);
    }

    /**
     * @Description: 添加评论的评论
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/9/23
     */
    @PostMapping("/CommentMessage")
    public JrsfReturn addCommentMessage(CommentMessageViewModel commentMessageViewModel) {
        return this.articleCommentService.addCommentMessage(commentMessageViewModel);
    }
    @GetMapping("/CommentMessage")
    public JrsfReturn getCommentMessage(@RequestParam String commentId, @RequestParam(defaultValue = "1") int pageIndex, @RequestParam(defaultValue = "10") int pageSize)
    {
       return this.articleCommentService.getCommentMessage(commentId, pageIndex, pageSize);
    }
}
