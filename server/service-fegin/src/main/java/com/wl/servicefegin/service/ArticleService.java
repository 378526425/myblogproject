package com.wl.servicefegin.service;

import com.wl.blog.viewmodel.ArticleViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.fallback.ArticleFeignFallBack;
import com.wl.servicefegin.interceptor.FeignHystrixConcurrencyStrategy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-15 16:05
 **/
@FeignClient(value = "service-blog",fallback = ArticleFeignFallBack.class,configuration = FeignHystrixConcurrencyStrategy.class)
@Component
public interface ArticleService {
    @PostMapping("/api/Article")
    JrsfReturn addArticle(@RequestBody @Validated ArticleViewModel articleViewModel);
    @DeleteMapping("/api/Article/{id}")
     JrsfReturn deleteArticle(@PathVariable String id) ;
    @PutMapping("/api/Article")
     JrsfReturn updateArticle(@RequestBody @Validated ArticleViewModel articleViewModel);
    @GetMapping("/api/Article/{id}")
     JrsfReturn getArticle(@PathVariable String id);
    @GetMapping("/api/Article")
    JrsfReturn getPageArticle(@RequestParam(required = false, defaultValue = "") String condition, @RequestParam(defaultValue = "1", required = false) int pageIndex, @RequestParam(defaultValue = "10", required = false) int pageSize);
    @GetMapping("/api/Article/likeArticle/{id}")
    JrsfReturn likeArticle(@PathVariable String id);

    @GetMapping("/api/Article/disLikeArticle/{id}")
    JrsfReturn disLikeArticle(@PathVariable String id);
}
