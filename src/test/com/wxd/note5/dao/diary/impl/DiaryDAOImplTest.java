package com.wxd.note5.dao.diary.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxd.note5.dao.diary.DiaryDAO;
import com.wxd.note5.model.diary.Diary;
import com.wxd.note5.util.PaginatedResult;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class DiaryDAOImplTest {

	@Resource(name="diaryDAOImpl")
	DiaryDAO dao;
	
	@Test
	public void saveDiaryTest(){
		Date now = new Date();
		Diary diary = new Diary();
		//diary.setContent("content".getBytes());		
		diary.setCreateDate(now);
		diary.setLastModify(now);
		diary.setTitle("2013年8月7号 星期五");
		
		this.dao.saveDiary(diary);
	}
	
	@Test
	public void test() throws UnsupportedEncodingException {
		PaginatedResult<Diary> result = dao.getList(1, 3);
		for(Diary d: result.getResult()){
			System.out.println(d.getTitle());
		}
	}
	
	@Test
	public void testGet(){
		System.out.println(this.dao.getDiaryById(40));
	}
	

}
