package com.zhn.service;

import com.zhn.model.Role;
import com.zhn.service.base.IService;

public interface RoleService extends IService<Role> {

	public void delete(Long id) throws Exception;
	
}
