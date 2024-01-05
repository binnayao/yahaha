package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.domain.VO.PageVo;
import com.yahaha.domain.entity.Tag;
import com.yahaha.mapper.TagMapper;
import com.yahaha.services.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public PageVo list(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        Page<Tag> tagPage = new Page<>(pageNum, pageSize);
        page(tagPage, queryWrapper);
        return new PageVo(tagPage.getRecords(), tagPage.getTotal());
    }
}
