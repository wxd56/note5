package com.wxd.note5.dao.doc;

import java.util.List;

import com.wxd.note5.model.doc.OpenDocHistory;
 
/**
 * 打开文档的历史记录
 * @author Administrator
 *
 */
public interface OpenDocHistoryDAO {

	/** 记录打开文档 */
	//void recordOpenDoc(OpenDocHistory history);
	
	public void updateHistoryByDocID(final  OpenDocHistory history) ;
	
	List<OpenDocHistory>  getHistoryList(int maxCount);
	
	void insertHistory(OpenDocHistory history);
	
	public void updateHistoryByCategoryID(final  OpenDocHistory history) ;
	
	OpenDocHistory findByCategoryID(String ID);
	
	OpenDocHistory findByDocID(String ID);
}
