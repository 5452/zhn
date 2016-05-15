package com.zhn.mapper;

import java.util.List;

import com.zhn.model.Role;
import com.zhn.util.MyMapper;

public interface RoleMapper extends MyMapper<Role> {

	List<Role> getRolesByUserId(Long userId);
}