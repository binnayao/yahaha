package com.yahaha.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.User;

public interface UserService extends IService<User> {
    ResponseResult getUserInfo();

    ResponseResult putUserInfo(User user);
}
