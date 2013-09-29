package com.wxd.note5.service.diary;

import com.wxd.note5.model.diary.Diary;
import com.wxd.note5.util.PaginatedResult;

/**
 * 对日记进行操作的服务类
 *
 *
 * @author 王旭东
 * @version 1.0 2013年8月5日 下午10:55:42
 *
 */
public interface DiaryService {
	/**
	 * 得到日志的分页列表
	 * @return
	 */
	public PaginatedResult<Diary> getList(int pageNo,int pageSize) throws Exception;
	
	/**
	 * 新建日志
	 *  
	 */
	public void newDiary( ) throws Exception;
	
  

	/**
	 * 更新日志信息
	 * @param diary
	 */
	public void updateDiary(Diary diary) throws Exception;

	Diary getDiaryById(int id) throws Exception;

	/**
	 * 删除日志
	 */
	public void deleteDiary(int parseInt);
}
