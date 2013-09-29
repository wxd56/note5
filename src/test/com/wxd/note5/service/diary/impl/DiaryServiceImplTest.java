package com.wxd.note5.service.diary.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxd.note5.model.diary.Diary;
import com.wxd.note5.service.diary.DiaryService;
import com.wxd.note5.util.PaginatedResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class DiaryServiceImplTest {

	@Resource(name="diaryServiceImpl")
	DiaryService service;
	
	@Test
	public void testSave() throws Exception {
		 
		
		service.newDiary();
		
		PaginatedResult<Diary> result = service.getList(1, 11);
		System.out.println(result);
	}
	
	@Test
	public void testGetById() throws Exception{
		System.out.println(this.service.getDiaryById(40));
	}
}