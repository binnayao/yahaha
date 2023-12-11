package com.yahaha.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult<Article> hotArticleList();
}
