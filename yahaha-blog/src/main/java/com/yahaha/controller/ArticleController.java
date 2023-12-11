package com.yahaha.controller;

import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.Article;
import com.yahaha.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult<Article> hotArticleList() {
        // 查询热门文章, 封装成 ResponseResult 返回
        return articleService.hotArticleList();
    }
}
