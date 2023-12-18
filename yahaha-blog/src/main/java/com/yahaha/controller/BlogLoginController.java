package com.yahaha.controller;

import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.User;
import com.yahaha.services.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return blogLoginService.login(user);
    }
}
