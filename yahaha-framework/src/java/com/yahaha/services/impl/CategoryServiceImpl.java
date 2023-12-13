package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.CategoryVo;
import com.yahaha.domain.entity.Article;
import com.yahaha.domain.entity.Category;
import com.yahaha.mapper.CategoryMapper;
import com.yahaha.services.CategoryService;
import com.yahaha.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yahaha.services.ArticleService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {

        // 只需要展示,有发布正式文章的分类
        // 严禁3张表以上的连表查询, 最好是2张表

        // 查询文章表状态为己发布的文章
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleQueryWrapper);
        // 获取文章的分类id，并且去重
        Set<Long> longSet = articleList.stream().map(Article::getCategoryId).collect(Collectors.toSet());

        // 查询分类表
        List<Category> categoryList = listByIds(longSet);
        categoryList = categoryList.stream().filter(category -> category.getStatus().equals(SystemConstants.CATEGORY_STATUS_NORMAL)).toList();

        // 封装vo
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class));
    }
}
