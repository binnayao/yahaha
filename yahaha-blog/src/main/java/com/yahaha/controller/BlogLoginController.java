package com.yahaha.controller;

import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.User;
import com.yahaha.enums.AppHttpCodeEnum;
import com.yahaha.exception.SystemException;
import com.yahaha.services.BlogLoginService;
import com.yahaha.utils.WebUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.management.RuntimeMBeanException;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        // 退出登录
        // 删除redis中的token
        return blogLoginService.logout();
    }
}
