package com.zhn.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zhn.model.Resource;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(true)
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class ResourceServiceTest {
	
	@Autowired
	private ResourceService resourceService;

	@Test
	public void getResourceById() {
		Resource resource = resourceService.getById(1l);
		assertEquals("元器件管理", resource.getName());
	}
	
	@Test
	public void getResourcesByUserid() {
		List<Resource> resources = resourceService.getUserResourcesByUserId(1l);
		assertEquals(0, resources.size());
	}
	
	@Test
	public void getResourcesByRoleid() {
		List<Resource> resources = resourceService.getRoleResourcesByRoleId(1l);
		assertEquals(0, resources.size());
	}
	
	@Test
	public void getByCondition() {
		Resource resource = new Resource();
		resource.setName("管理");
		//resource.setDescription("A%'; DELETE FROM tbl_user_login; -- ");
		List<Resource> resources = resourceService.getByCondition(resource, 1, 20);
		
		assertEquals(16, resources.size());
	}
	
}
