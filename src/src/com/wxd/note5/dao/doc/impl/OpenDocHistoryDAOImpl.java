package com.wxd.note5.dao.doc.impl;

  
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxd.note5.dao.DaoBase;
import com.wxd.note5.dao.doc.OpenDocHistoryDAO;
import com.wxd.note5.model.doc.OpenDocHistory;

@Repository
public class OpenDocHistoryDAOImpl extends DaoBase implements OpenDocHistoryDAO {
	private int pageSize = 30;
	
	@Override
	public List<OpenDocHistory> getHistoryList( final int maxCount) {
		List<OpenDocHistory> list = getSqlMapClient().queryForList("doc.getHistoryList",null,0,pageSize);
		return list;
	}

	@Override
	public void insertHistory(final OpenDocHistory history) {
		if(history.getDocID() == null) history.setDocID("NONE");
		
		if(history.getDocName() == null) return;
		
		getSqlMapClient().insert("doc.insertHistory",history);
	}

	@Override
	public void updateHistoryByDocID(  OpenDocHistory history) {
		  getSqlMapClient().update("doc.updateHistoryByDocID",history);
	}

	@Override
	public OpenDocHistory findByCategoryID( String ID) { 
		 return (OpenDocHistory) getSqlMapClient().queryForObject("doc.findByCategoryID",ID); 
	}

	@Override
	public OpenDocHistory findByDocID( String ID) {
		 return (OpenDocHistory) getSqlMapClient().queryForObject("doc.findByDocID",ID); 
	}

	@Override
	public void updateHistoryByCategoryID(final  OpenDocHistory history) {
		 getSqlMapClient().update("doc.updateHistoryByCategoryID",history);
	
	}

}
