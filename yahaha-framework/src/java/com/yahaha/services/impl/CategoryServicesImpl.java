package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.GetCategoryListVo;
import com.yahaha.domain.entity.Category;
import com.yahaha.mapper.CategoryMapper;
import com.yahaha.services.CategoryServices;
import com.yahaha.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServicesImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryServices {
    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式的
        queryWrapper.eq(Category::getStatus, SystemConstants.CATEGORY_STATUS_NORMAL);
        List<Category> categoryList = list();
        List<GetCategoryListVo> getCategoryListVoList = BeanCopyUtils.copyBeanList(categoryList, GetCategoryListVo.class);
        return ResponseResult.okResult(getCategoryListVoList);
    }
}
