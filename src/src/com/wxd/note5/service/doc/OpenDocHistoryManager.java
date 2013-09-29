package com.wxd.note5.service.doc;

import java.util.List;

import com.wxd.note5.model.doc.OpenDocHistory;

 
public interface OpenDocHistoryManager {
	public void recordOpenDoc(OpenDocHistory history);
	
	public List<OpenDocHistory> getHistoryList(  int maxCount) ;
}
