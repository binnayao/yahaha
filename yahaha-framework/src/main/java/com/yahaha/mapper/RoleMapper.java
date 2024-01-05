package com.yahaha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yahaha.domain.entity.Role;
import com.yahaha.domain.entity.User;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRoleKeyByUserId(Long userId);
}
