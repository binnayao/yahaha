package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.HotArticleVo;
import com.yahaha.domain.entity.Article;
import com.yahaha.mapper.ArticleMapper;
import com.yahaha.services.ArticleService;
import com.yahaha.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult<Article> hotArticleList() {
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
}
