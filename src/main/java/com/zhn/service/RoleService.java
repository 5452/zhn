package com.zhn.service;

import java.util.List;

import com.zhn.model.Role;
import com.zhn.service.base.BaseService;

public interface RoleService extends BaseService {

	public void add(Role role);

	public void delete(Long id) throws Exception;

	public void update(Role role);

	public Role getById(Long id);

	public List<Role> findAll();
	
}
