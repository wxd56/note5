package com.wxd.note5.service.doc.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxd.note5.dao.doc.DocDAO;
import com.wxd.note5.model.doc.Document;
import com.wxd.note5.model.doc.OpenDocHistory;
import com.wxd.note5.service.doc.DocManager;
import com.wxd.note5.service.doc.OpenDocHistoryManager;
import com.wxd.note5.util.EncryptDESTool;
import com.wxd.note5.util.PaginatedResult;
 
 

@Service
public class DocManagerImpl  implements DocManager {	
 
	private DocDAO docDAO;
 
	@Autowired
    EncryptDESTool tool;
	@Autowired
	OpenDocHistoryManager  historyManager;
	
	@Override
	public String  newDoc(Document doc) {
		doc.setId(UUID.randomUUID().toString());
		doc.setCreateDate(new Date());
		doc.setLastModify(new Date());
	
		docDAO.createDoc(doc);
	 	
		return doc.getId();
	}
	
	@Override
	public void deleteDoc(String docID) {
		docDAO.deleteDoc(docID);
	}

	@Override
	public void saveContent(Document doc) throws Exception {		
		 //加密
		String cipherText = this.tool.encrypt(doc.getContent());
		doc.setContent(cipherText);
		
		doc.setLastModify(new Date());
		docDAO.updateDoc(doc);
		
	 
	}

	@Override
	public Document getById(String docID) throws Exception {
		Document doc = this.docDAO.getByID(docID);
		
		if(doc == null) return null;
		
		//解密
		doc.setContent(tool.decrypt(doc.getContent()));
		
		//记录操作记录
		OpenDocHistory history = new OpenDocHistory();
		history.setCategoryID(doc.getCategory().getId());
		history.setDocID(doc.getId());
		history.setDocName(doc.getTitle());
		historyManager.recordOpenDoc(history);
	 
		return doc;
	}
	@Autowired
	public void setDocDAO(DocDAO docDAO) {
		this.docDAO = docDAO;
	}

	@Override
	public PaginatedResult<Document> getOtherDocs(int pageNo, int pageSize,	String categoryID) {
		return  this.docDAO.listDoc(pageSize, pageNo,  categoryID);
		
		/*//解密
		for(Document d :  list){
			d.setContent(tool.decrypt(doc.getContent()));
		}*/
	}
	 
}