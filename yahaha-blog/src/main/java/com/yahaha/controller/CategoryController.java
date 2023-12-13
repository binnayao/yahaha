package com.yahaha.controller;

import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.Category;
import com.yahaha.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList() {
        return categoryServices.getCategoryList();
    }
}
