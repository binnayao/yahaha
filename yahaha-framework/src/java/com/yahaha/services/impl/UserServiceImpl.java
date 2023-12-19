package com.yahaha.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.domain.entity.User;
import com.yahaha.mapper.UserMapper;
import com.yahaha.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
