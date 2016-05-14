/**
 * 
 */
package com.zhn.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhn.mapper.RoleMapper;
import com.zhn.mapper.UserRoleMapper;
import com.zhn.model.Role;
import com.zhn.model.UserRole;
import com.zhn.model.UserRoleExample;

/**
 * 
 * @author 5452
 *	2016年5月13日
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public void add(Role role) {
		if(role != null && StringUtils.isNotBlank(role.getName()) && StringUtils.isNotBlank(role.getSignature()))
			roleMapper.insert(role);
	}

	@Override
	public void delete(Long id) throws Exception {
		
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andRoleIdEqualTo(id);
		List<UserRole> userRoles = userRoleMapper.selectByExample(example);
		
		if(userRoles == null || userRoles.size() == 0) {
			roleMapper.deleteByPrimaryKey(id);
		} else 
			throw new Exception("角色下存在用户，不能删除");
	}

	@Override
	public void update(Role role) {
		if(role != null && role.getId() != null && role.getId() > 0)
			roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public Role getById(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> findAll() {
		return new ArrayList<Role>();
	}

}
