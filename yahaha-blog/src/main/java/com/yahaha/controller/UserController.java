package com.yahaha.controller;

import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.User;
import com.yahaha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult putUserInfo(@RequestBody User user) {
        return userService.putUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
