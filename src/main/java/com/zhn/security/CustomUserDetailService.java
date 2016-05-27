package com.zhn.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zhn.mapper.ResourceMapper;
import com.zhn.mapper.UserMapper;
import com.zhn.model.Resource;
import com.zhn.model.UserExample;

/**
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 
 * 获得对象的方式： WebUserDetails webUserDetails =
 * (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().
 * getPrincipal();
 * 
 * 或在JSP中： <sec:authentication property="principal.username"/>
 * 
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可 权限验证类
 * 
 * @author 5452 2016年5月13日
 */

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ResourceMapper resourceMapper;

	// 登录验证
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		// 取得用户的信息
		List<com.zhn.model.User> userList = userMapper.selectByExample(example);
		if (userList == null || userList.size() != 1)
			throw new UsernameNotFoundException(email + " not exist!");
		com.zhn.model.User user = userList.get(0);
		// 获取用户权限
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		// 封装成spring security的user
		User userdetail = new User(user.getEmail(), user.getPassword(), true, true, true, true, grantedAuths);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(com.zhn.model.User user) {
		List<Resource> resources = resourceMapper.getUserResourcesByUserId(user.getId());
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resource res : resources) {
			authSet.add(new SimpleGrantedAuthority(res.getSignature()));
		}
		return authSet;
	}
}