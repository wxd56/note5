package com.wxd.note5.dao.doc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
 






import com.wxd.note5.dao.DaoBase;
import com.wxd.note5.dao.doc.DocDAO;
import com.wxd.note5.model.doc.Document;
import com.wxd.note5.util.PaginatedResult;
 
@Repository
public class DocDAOImpl extends DaoBase implements DocDAO{

	@Override
	public void createDoc( Document doc) {
		 getSqlMapClient().insert("doc.createDoc",doc);
	}

	@Override
	public void deleteDoc(final String docID) {
		getSqlMapClient().delete("doc.deleteDoc",docID);
	}

	@Override
	public void updateDoc( Document doc) {
		getSqlMapClient().update("doc.updateDoc",doc);
	}
 

	@Override
	public PaginatedResult<Document> listDoc( final int pageSize, final int pageNo,final String category) {
		 
		
		PaginatedResult<Document> result = new PaginatedResult<Document>();
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		
		@SuppressWarnings("unchecked")
		List<Document> list = getSqlMapClient().queryForList("doc.listDoc", category, (pageNo-1)*pageSize,pageSize);
		result.setResult(list);
		
		
		int count = (Integer) getSqlMapClient().queryForObject("doc.listDocCount", category);
		result.setTotalCount(count);		
		return result;
	}
	
 
 
	@Override
	public Document getByID(String id) {
		return (Document) getSqlMapClient().queryForObject("doc.getByID",id);
	}

	@Override
	public void updateTitle(String id, String newTitle) {
		Map< String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("title", newTitle);
		
		this.getSqlMapClient().update("doc.updateTitle",map);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Document> searchDocs(String title) {
		 return getSqlMapClient().queryForList("doc.searchDocs","%"+title+"%");
	}
}