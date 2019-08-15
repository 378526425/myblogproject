package com.wl.servicefegin.controller;

import com.wl.blog.viewmodel.ArticleViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-15 16:12
 **/
@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @PostMapping("/Article")
    public JrsfReturn addArticle(@RequestBody @Validated ArticleViewModel articleViewModel)
    {
        return this.articleService.addArticle(articleViewModel);
    }
    @DeleteMapping("/Article/{id}")
    public JrsfReturn deleteArticle(@PathVariable String id) {
        return this.articleService.deleteArticle(id);
    }
    @PutMapping("/Article")
    public JrsfReturn updateArticle(@RequestBody @Validated ArticleViewModel articleViewModel)
    {
        return this.articleService.updateArticle(articleViewModel);
    }
    @GetMapping("/Article/{id}")
    public JrsfReturn getArticle(@PathVariable String id)
    {
        return this.articleService.getArticle(id);
    }
    @GetMapping("/Article")
    public JrsfReturn getPageArticle(@RequestParam(required = false, defaultValue = "") String condition, @RequestParam(defaultValue = "1", required = false) int pageIndex, @RequestParam(defaultValue = "10", required = false) int pageSize)
    {
        return this.articleService.getPageArticle(condition,pageIndex,pageSize);
    }
}
