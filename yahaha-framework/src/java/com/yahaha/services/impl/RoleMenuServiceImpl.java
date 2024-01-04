package com.yahaha.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.domain.entity.Menu;
import com.yahaha.domain.entity.RoleMenu;
import com.yahaha.mapper.MenuMapper;
import com.yahaha.mapper.RoleMenuMapper;
import com.yahaha.services.MenuService;
import com.yahaha.services.RoleMenuService;
import org.springframework.stereotype.Service;


@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
