package com.zhn.service;

import java.util.List;

import com.zhn.model.Resource;
import com.zhn.service.base.IService;

public interface ResourceService extends IService<Resource>{

	public List<Resource> getUserResourcesByUserId(Long userId);

	public List<Resource> getRoleResourcesByRoleId(Long roleId);

	public List<Resource> getResourcesByEmail(String email);

	public void saveRoleResources(Long roleId, List<Long> list);
}
