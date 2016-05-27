/**
 * 
 */
package com.zhn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhn.mapper.RoleMapper;
import com.zhn.mapper.UserRoleMapper;
import com.zhn.model.Role;
import com.zhn.model.UserRole;
import com.zhn.model.UserRoleExample;
import com.zhn.service.RoleService;
import com.zhn.service.base.BaseService;

/**
 * 
 * @author 5452
 *	2016年5月13日
 */
@Service("roleService")
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;

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
	public List<Role> getByCondition(Role object, int pageId, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
