package com.zhn.service;

import java.util.List;

import org.springframework.security.core.AuthenticationException;

import com.zhn.model.User;
import com.zhn.model.UserRole;
import com.zhn.service.base.IService;


public interface UserService extends IService<User> {
	
	/**
	 * 按用户名称查询符合条件的记录
	 * @param name
	 * @return
	 */
	public List<User> getUserByName(String name);

	/**
	 * 根据给定的email获取用户信息
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);
	
	/**
	 * 添加用户
	 * @param user
	 * @param roleIds
	 */
	public void addUser(User user, List<Long> roleIds);
	
	/**
	 * 更新用户
	 * @param user
	 */
	public void updateUser(User user, List<Long> roleIds);
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(Long id);
	
	/**
	 * 验证用户登录
	 * @param email
	 * @param password
	 * @return
	 */
	public User authUser(String email, String password) throws AuthenticationException;
	
	/**
	 * 更新用户角色，只支持用户绑定单一角色
	 * @param userRole
	 */
	public void updateUserRole(UserRole userRole);
}
