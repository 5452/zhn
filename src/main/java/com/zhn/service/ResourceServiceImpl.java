package com.zhn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhn.mapper.ResourceMapper;
import com.zhn.mapper.RoleResourceMapper;
import com.zhn.model.Resource;
import com.zhn.model.ResourceExample;
import com.zhn.model.RoleResource;
import com.zhn.model.RoleResourceExample;

/**
 * 
 * @author 5452
 *	2016年5月13日
 */
@Service("ResourceService")
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
	@Override
	public List<Resource> query(Resource resource) {
		// TODO
		return null;
	}

	@Override
	public void add(Resource resource) {
		resourceMapper.insert(resource);
	}

	@Override
	public void delete(Long id) {
		resourceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateResource(Resource resource) {
		resourceMapper.updateByPrimaryKey(resource);
	}

	@Override
	public Resource getById(Long id) {
		return resourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Resource> findAll() {
		return resourceMapper.selectByExample(new ResourceExample());
	}

	@Override
	public List<Resource> getUserResourcesByUserId(Long userId) {
		return resourceMapper.getUserResourcesByUserId(userId);
	}

	@Override
	public List<Resource> getRoleResourcesByRoleId(Long roleId) {
		return resourceMapper.getRoleResourcesByRoleId(roleId);
	}

	@Override
	public List<Resource> getResourcesByEmail(String email) {
		return resourceMapper.getUserResourcesByEmail(email);
	}

	@Override
	@Transactional
	public void saveRoleResources(Long roleId, List<Long> list) {
		
		RoleResourceExample example = new RoleResourceExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		roleResourceMapper.deleteByExample(example);
		List<RoleResource> roleResources = new ArrayList<>();
		for (Long resourceId : list) {
			if(resourceId != null && resourceId > 0){
				RoleResource roleResource = new RoleResource(roleId, resourceId);
				roleResources.add(roleResource);
			}
		}
		roleResourceMapper.insertList(roleResources);
	}
}
