package com.yahaha.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.domain.VO.PageVo;
import com.yahaha.domain.entity.Tag;

public interface TagService extends IService<Tag> {
    PageVo list(Integer pageNum, Integer pageSize);
}
