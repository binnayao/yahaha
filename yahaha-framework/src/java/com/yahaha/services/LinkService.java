package com.yahaha.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.Link;

public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}
