package com.yahaha.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.Category;

public interface CategoryServices extends IService<Category> {
    ResponseResult getCategoryList();
}
