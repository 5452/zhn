package com.zhn.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zhn.mapper.ResourceMapper;
import com.zhn.mapper.UserMapper;
import com.zhn.model.Resource;
import com.zhn.model.User;
import com.zhn.model.UserExample;

/**
 * Custom Spring Security  权限验证类
 * 
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 *
 */
/**
 * Custom Spring Security 权限验证类 获得对象的方式： WebUserDetails webUserDetails =
 * (WebUserDetails
 * )SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 或在JSP中： <sec:authentication property="principal.username"/> 
 * 
 * 2014年3月21日
 * 
 * @author WANGJUN
 * 
 */
@Service
public class SecurityService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ResourceMapper resourceMapper;

	// 登录验证
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try {
			UserExample example = new UserExample();
			example.createCriteria().andEmailEqualTo(email);
			//取得用户的权限
			List<User> userList = userMapper.selectByExample(example);
			user = userList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(email + " not exist!");
		}
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		// 封装成spring security的user
		org.springframework.security.core.userdetails.User userdetail = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), true, true, true, true,
				grantedAuths // 用户的权限
		);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		List<Resource> resources = resourceMapper.getUserResourcesByUserId(user.getId());
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resource resource : resources) {
			authSet.add(new SimpleGrantedAuthority(resource.getSignature()));
		}
		return authSet;
	}
}