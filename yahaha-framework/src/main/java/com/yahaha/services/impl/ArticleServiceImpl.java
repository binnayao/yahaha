package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.ArticleDetailVo;
import com.yahaha.domain.VO.ArticleListVo;
import com.yahaha.domain.VO.HotArticleVo;
import com.yahaha.domain.VO.PageVo;
import com.yahaha.domain.entity.Article;
import com.yahaha.mapper.ArticleMapper;
import com.yahaha.services.ArticleService;
import com.yahaha.services.CategoryService;
import com.yahaha.utils.BeanCopyUtils;
import com.yahaha.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult hotArticleList() {
        // 查询热门文章, 封装成 ResponseResult 返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多只能返回 10 条
        Page<Article> page = new Page<>(SystemConstants.PAGE_START_NUM, SystemConstants.PAGE_SIZE);
        page(page, queryWrapper);

        List<Article> articlesList = page.getRecords();


        List<HotArticleVo> hotArticleVoList = BeanCopyUtils.copyBeanList(articlesList, HotArticleVo.class);


        return ResponseResult.okResult(hotArticleVoList);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 如果有 categoryId 就要 查询时要和传入的相同
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        // 状态是正式发布的文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 置顶的文章要显示在最前面,对isTop进行排序,降序,isTop为1的显示在上面
        queryWrapper.orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> page = new Page<>(pageNum, pageSize == null ? SystemConstants.PAGE_SIZE : pageSize);
        page(page, queryWrapper);

        // 查询 categoryName
        page.getRecords().forEach(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()));

        // 封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        Article articleDetail = getById(id);
        articleDetail.setCategoryName(categoryService.getById(articleDetail.getCategoryId()).getName());
        return ResponseResult.okResult(BeanCopyUtils.copyBean(articleDetail, ArticleDetailVo.class));
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        // 更新 redis 中对应id的流量量
        redisCache.incrementCacheMapValue(SystemConstants.REDIS_KEY_VIEW_COUNT, id.toString(), 1);
        return null;
    }
}
