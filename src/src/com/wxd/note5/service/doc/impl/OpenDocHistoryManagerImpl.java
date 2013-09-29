package com.wxd.note5.service.doc.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxd.note5.dao.doc.OpenDocHistoryDAO;
import com.wxd.note5.model.doc.OpenDocHistory;
import com.wxd.note5.service.doc.OpenDocHistoryManager;

 
@Service
public class OpenDocHistoryManagerImpl  implements OpenDocHistoryManager{
	private OpenDocHistoryDAO historyDAO;
	
	@Autowired
	public void setHistoryDAO(OpenDocHistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}


	@Override
	public void recordOpenDoc(OpenDocHistory history) {
		OpenDocHistory targetHistory = null;
		//根据目录查找
		targetHistory =  this.historyDAO.findByCategoryID(history.getCategoryID());
		if(targetHistory == null){
			//根据文件查找
			targetHistory = historyDAO.findByDocID(history.getDocID());
		}
		
		//如果找到了更新访问时间和标题，返回
		if(targetHistory !=null){
			targetHistory.setDocName(history.getDocName());
			targetHistory.setLastAccess(new Date());
			targetHistory.setCount(targetHistory.getCount() + 1);
			//更新
			if(history.getDocID() != null){
				historyDAO.updateHistoryByDocID(targetHistory);
			}else{
				this.historyDAO.updateHistoryByCategoryID(targetHistory);
			}			
			return;
		}
		
		//如果没有找的则直接插入
		history.setCount(1);
		history.setLastAccess(new Date());
		this.historyDAO.insertHistory(history);
	}




	@Override
	public List<OpenDocHistory> getHistoryList(int maxCount) {
		return this.historyDAO.getHistoryList(maxCount);
	}
}
