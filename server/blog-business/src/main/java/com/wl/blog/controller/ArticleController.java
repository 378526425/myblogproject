package com.wl.blog.controller;

import com.wl.blog.service.ArticleService;
import com.wl.blog.viewmodel.ArticleViewModel;
import com.wl.common.utils.JrsfReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-05 15:49
 **/
@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/Article")
    public JrsfReturn addArticle(@RequestBody @Validated ArticleViewModel articleViewModel) {
        return this.articleService.addArticle(articleViewModel);
    }

    @DeleteMapping("/Article/{id}")
    public JrsfReturn deleteArticle(@PathVariable String id) {
        return articleService.deleteArticle(id);
    }

    @PutMapping("/Article")
    public JrsfReturn updateArticle(@RequestBody @Validated ArticleViewModel articleViewModel) {
        return this.articleService.updateArticle(articleViewModel);
    }

    @GetMapping("/Article/{id}")
    public JrsfReturn getArticle(@PathVariable String id) {
        return articleService.getArticle(id);
    }
}
