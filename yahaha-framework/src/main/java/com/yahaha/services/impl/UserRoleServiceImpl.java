package com.yahaha.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.domain.entity.LoginUser;
import com.yahaha.domain.entity.Menu;
import com.yahaha.domain.entity.UserRole;
import com.yahaha.mapper.MenuMapper;
import com.yahaha.mapper.UserRoleMapper;
import com.yahaha.services.MenuService;
import com.yahaha.services.UserRoleService;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    
}
