package com.yahaha.job;

import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.entity.Article;
import com.yahaha.services.ArticleService;
import com.yahaha.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCount {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount() {
        Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConstants.REDIS_KEY_VIEW_COUNT);
        List<Article> collect = cacheMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        // 更新到数据库中
        articleService.updateBatchById(collect);
    }
}
