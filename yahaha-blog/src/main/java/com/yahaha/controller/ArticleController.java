package com.yahaha.controller;

import com.yahaha.annotation.SystemLog;
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
    @SystemLog(BusinessName = "查询文章列表")
    public ResponseResult articleList(@RequestParam Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);

    }
}
