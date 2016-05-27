package com.zhn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.zhn.mapper.ResourceMapper;
import com.zhn.mapper.RoleResourceMapper;
import com.zhn.model.Resource;
import com.zhn.model.RoleResource;
import com.zhn.model.RoleResourceExample;
import com.zhn.service.ResourceService;
import com.zhn.service.base.BaseService;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author 5452
 *	2016年5月13日
 */
@Service("resourceService")
public class ResourceServiceImpl extends BaseService<Resource> implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
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

	@Override
	public List<Resource> getByCondition(Resource resource, int pageId, int pageSize) {
		Example example = new Example(Resource.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(resource.getName())) {
            criteria.andLike("name", "%" + resource.getName() + "%");
        }
        if (StringUtils.isNotBlank(resource.getDescription())) {
            criteria.andLike("description", "%" + resource.getDescription() + "%");
        }
        if (resource.getId() != null) {
            criteria.andEqualTo("id", resource.getId());
        }
        PageHelper.startPage(pageId, pageSize);
        
        return this.selectByExample(example);
	}
	
}
