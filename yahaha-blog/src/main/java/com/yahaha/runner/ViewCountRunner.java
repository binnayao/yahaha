package com.yahaha.runner;

import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.entity.Article;
import com.yahaha.mapper.ArticleMapper;
import com.yahaha.services.ArticleService;
import com.yahaha.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        // 查询博客信息 id viewCount
        List<Article> list = articleService.list();
        Map<String, Integer> collect = list.stream()
                .collect(Collectors.toMap(
                        article -> article.getId().toString(),
                        article -> article.getViewCount().intValue()
                ));
        redisCache.setCacheMap(SystemConstants.REDIS_KEY_VIEW_COUNT, collect);
    }
}
