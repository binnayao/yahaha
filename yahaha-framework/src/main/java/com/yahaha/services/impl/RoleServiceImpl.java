package com.yahaha.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.domain.entity.Menu;
import com.yahaha.domain.entity.Role;
import com.yahaha.mapper.MenuMapper;
import com.yahaha.mapper.RoleMapper;
import com.yahaha.services.MenuService;
import com.yahaha.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long userId) {
        if (userId == 1L) {
            List<String> arrayList = new ArrayList<>();
            arrayList.add("admin");
            return arrayList;
        }
        return getBaseMapper().selectRoleKeyByUserId(userId);
    }
}
