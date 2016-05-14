package com.zhn.service;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zhn.model.User;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(true)
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void getUserById() {
		User user = userService.getUserById(1l);
		assertEquals("Wang", user.getName());
	}

	@Test
	public void getUserByName() {
		List<User> users = userService.getUserByName("%Zhang%");
		assertEquals(1, users.size());
	}

	@Test
	//@Rollback(false)
	public void addUser() {
		User user = new User();
		user.setName("Wang Jun");
		user.setEmail("test1@test1.com");
		user.setPassword("123456");
		user.setUpdateDatetime(new Timestamp(new DateTime().getMillis()));
		List<User> users = userService.getUserByName(user.getName());
		int found = users.size();
		userService.addUser(user, null);
		users = userService.getUserByName(user.getName());
		assertEquals("验证新插入User", found + 1, users.size());
	}

	@Test
	public void authUser() {
		try {
			User user = userService.authUser("test1@test1.com", "123456");
			assertEquals("test1@test1.com", user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals("登陆失败，Email或密码错误, email:test1@test1.com, password:123456", e.getMessage());
		}
	}
	
}
