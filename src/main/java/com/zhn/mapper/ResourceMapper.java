package com.zhn.mapper;

import java.util.List;

import com.zhn.model.Resource;
import com.zhn.util.MyMapper;

public interface ResourceMapper extends MyMapper<Resource> {
    
    List<Resource> getUserResourcesByUserId(Long userId);
	
	List<Resource> getUserResourcesByEmail(String email);
	
	List<Resource> getRoleResourcesByRoleId(Long roleId);
}