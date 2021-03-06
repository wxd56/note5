package com.wxd.note5.service.category;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class CategoryManagerTest {

	@Autowired
	CategoryManager manager;
	
	@Test
	public void test() {
		List list = manager.listCategories("0");
		System.out.println(list);
	}

}
