package com.yahaha.controller;

import com.yahaha.domain.ResponseResult;
import com.yahaha.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        // 查询热门文章, 封装成 ResponseResult 返回
        return articleService.hotArticleList();
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }
}
