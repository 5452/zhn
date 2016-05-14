package com.zhn.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zhn.mapper.UserMapper;
import com.zhn.mapper.UserRoleMapper;
import com.zhn.model.User;
import com.zhn.model.UserExample;
import com.zhn.model.UserRole;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public User getUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> getUserByName(String name) {
		UserExample example = new UserExample();
		example.createCriteria().andNameLike(name);
		return userMapper.selectByExample(example);
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void addUser(User user, List<Long> roleIds) throws RuntimeException {
		if(user != null) {
			if(roleIds != null && roleIds.size() > 0) {
				user.setAdmin(true);
			}
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			userMapper.insertSelective(user);
			
			List<UserRole> userRoles = new ArrayList<>();
			if(roleIds != null && roleIds.size() > 0) {
				for(long roleId : roleIds) {
					UserRole userRole = new UserRole(roleId, user.getId());
					userRoles.add(userRole);
				}
				userRoleMapper.insertList(userRoles);
			}
		}
	}
	
	@Override
	public void updateUser(User user, List<Long> roleIds) {
		if(user != null && user.getId() != null && user.getId() > 0) {
			if(StringUtils.isNotBlank(user.getPassword()))
				user.setPassword(bcryptEncoder.encode(user.getPassword()));
			userMapper.updateByPrimaryKey(user);
			/*if(roleId != null && roleId > 0) {
				roleMapper.deleteUserRoleByUserId(user.getId());
				UserRole userRole = new UserRole(roleId, user.getId());
				roleMapper.addUserRole(userRole);
			}*/
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void deleteUser(Long id) {
		if(id != null && id > 0) {
			userMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public User getUserByEmail(String email) {
		return new User();
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<User>();
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		// TODO 
		if(userRole != null && userRole.getRoleId() != null && userRole.getRoleId() > 0 && userRole.getUserId() != null && userRole.getUserId() > 0) {
			/*roleMapper.deleteUserRoleByUserId(userRole.getUserId());
			roleMapper.addUserRole(userRole);*/
		}
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public User authUser(String email, String password) {
		String msg = "登陆失败，Email或密码错误, email:" + email + ", password:" + password;
		User user = new User();
		user.setEmail(email);
		user = userMapper.selectOne(user);
		if (user != null && user.getId() > 0 && bcryptEncoder.matches(password, user.getPassword()))
			return user;
		else
			throw new AuthenticationServiceException(msg);
	}

}
