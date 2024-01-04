package com.yahaha.services;

import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult adminLogin(User user);

    ResponseResult getInfo();
}
