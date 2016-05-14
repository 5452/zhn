package com.zhn.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zhn.mapper.UserLoginMapper;
import com.zhn.mapper.UserMapper;
import com.zhn.model.User;
import com.zhn.model.UserExample;
import com.zhn.model.UserLogin;

/**
 * 用户登录验证
 * 
 * @author 5452 2016年5月12日
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String SUPPORTED_LOGIN_METHOD = "POST";

	/**
	 * 登录成功后跳转的地址
	 */
	private String successUrl = "/";
	/**
	 * 登录失败后跳转的地址
	 */
	private String errorUrl = "/user/login";

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserLoginMapper userLoginMapper;

	/**
	 * 自定义表单参数的name属性，默认是 j_username 和 j_password 定义登录成功和失败的跳转地址
	 */
	public void init() {
		// System.err.println(" --------------- MyAuthenticationFilter
		// init--------------- ");
		this.setUsernameParameter(EMAIL);
		this.setPasswordParameter(PASSWORD);
		// 验证成功，跳转的页面
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(successUrl);
		this.setAuthenticationSuccessHandler(successHandler);

		// 验证失败，跳转的页面
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl(errorUrl);
		this.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), SUPPORTED_LOGIN_METHOD)) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String email = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();
		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			throw new BadCredentialsException("用户名或密码不能为空！");
		}
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		
		List<User> list = userMapper.selectByExample(example);
		if(list == null || list.size() == 0) {
			throw new BadCredentialsException("用户不存在！");
		}
		User user = list.get(0);
		// 验证用户账号与密码是否正确
		if (!bcryptEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("用户名/密码不匹配！");
		}
		// 当验证都通过后，把用户信息放在session里
		request.getSession().setAttribute("userSession", user);
		// 记录登录信息
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip)) { 
			ip = request.getRemoteAddr(); 
		} 
		UserLogin userLogin = new UserLogin();
		userLogin.setIp(ip);
		userLogin.setUserId(user.getId());
		userLoginMapper.insertSelective(userLogin);
		
		// 实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
		setDetails(request, authRequest);
		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	/**
	 * 验证登陆信息是否有效
	 * 
	 * @param email
	 *            登录使用的邮箱
	 * @param password
	 *            登陆密码（未加密）
	 * @return
	 */
	public boolean authUser(String email, String password) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		List<User> list = userMapper.selectByExample(example);
		if (list == null || list.size() != 1)
			return false;
		User user = list.get(0);
		return bcryptEncoder.matches(password, user.getPassword());
	}
}
