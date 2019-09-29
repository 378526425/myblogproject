package com.wl.servicefegin.service;

import com.wl.blog.viewmodel.ArticleCommentViewModel;
import com.wl.blog.viewmodel.CommentMessageViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.fallback.ArticleCommentFallBack;
import com.wl.servicefegin.fallback.ArticleFeignFallBack;
import com.wl.servicefegin.interceptor.FeignHystrixConcurrencyStrategy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-blog",fallback = ArticleCommentFallBack.class,configuration = FeignHystrixConcurrencyStrategy.class)
@Component
public interface ArticleCommentService {
     @PostMapping("/api/ArticleComment")
     JrsfReturn addArticleComment(@RequestBody @Validated ArticleCommentViewModel articleCommentViewModel);

    /**
     * @Description: 查询文章评论
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/9/23
     */
    @GetMapping("/api/ArticleComment")
     JrsfReturn getArticleCommentByArticleId(@RequestParam String articleId, @RequestParam(defaultValue = "1") int pageIndex, @RequestParam(defaultValue = "10") int pageSize);

    /**
     * @Description: 添加评论的评论
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/9/23
     */
    @PostMapping("/api/CommentMessage")
    JrsfReturn addCommentMessage(CommentMessageViewModel commentMessageViewModel);
    @GetMapping("/api/CommentMessage")
    JrsfReturn getCommentMessage(@RequestParam String commentId, @RequestParam(defaultValue = "1") int pageIndex, @RequestParam(defaultValue = "10") int pageSize);
}
