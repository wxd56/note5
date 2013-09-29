package com.wxd.note5.dao.diary.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxd.note5.dao.DaoBase;
import com.wxd.note5.dao.diary.DiaryDAO;
import com.wxd.note5.model.diary.Diary;
import com.wxd.note5.util.PaginatedResult;

@Repository
public class DiaryDAOImpl  extends DaoBase implements DiaryDAO{
 
	
	@Override
	public PaginatedResult<Diary> getList(int pageNo, int pageSize) {
		PaginatedResult<Diary> result = new PaginatedResult<Diary>(pageNo,pageSize);
		
		List<Diary> list = (List<Diary>)getSqlMapClient().queryForList("diary.getList",null, (pageNo-1)*pageSize,pageSize);
		result.setResult(list);
		
		int totalCount = (Integer) getSqlMapClient().queryForObject("diary.getListCount",null);
		result.setTotalCount(totalCount);
		
		return result;
	}

	@Override
	public void saveDiary(Diary diary) {
		getSqlMapClient().insert("diary.insertDiary",diary);
	}

	@Override
	public Diary getDiaryById(int id) {
		return (Diary) getSqlMapClient().queryForObject("diary.getById", id);
	}

	@Override
	public void updateDiary(Diary diary) {
		this.getSqlMapClient().update("diary.updateDiary",diary);
	}

	@Override
	public void deleteDiary(int id) {
		this.getSqlMapClient().delete("diary.delete",id);
	}
}
