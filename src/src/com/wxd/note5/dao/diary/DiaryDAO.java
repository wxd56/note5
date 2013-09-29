package com.wxd.note5.dao.diary;

import com.wxd.note5.model.diary.Diary;
import com.wxd.note5.util.PaginatedResult;

/**
 * 日志的数据访问类
 *
 *
 * @author 王旭东
 * @version 1.0 2013年8月5日 下午11:00:56
 *
 */
public interface DiaryDAO {
	/**
	 * 得到日志的分页列表
	 * @return
	 */
	public PaginatedResult<Diary> getList(int pageNo,int pageSize);
	
	public void saveDiary(Diary diary);
	
	public Diary getDiaryById(int id);

	public void updateDiary(Diary diary);

	/**
	 * 删除日志
	 */
	public void deleteDiary(int parseInt);
}
