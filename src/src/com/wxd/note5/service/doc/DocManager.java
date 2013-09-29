package com.wxd.note5.service.doc;

import com.wxd.note5.model.doc.Document;
import com.wxd.note5.util.PaginatedResult;

 


public interface DocManager {
	
	/**
	 * 创建一个新的文档
	 * @param doc
	 * @return 创建成功后的文档ID
	 */
	public String newDoc(Document doc);
	
	 
	
	/**
	 * 得到其他文档的分页列表
	 */
	public PaginatedResult<Document> getOtherDocs(int pageNo,int pageSize,String category);
	
	 
		
	/**
	 * 删除文档
	 */
	public void deleteDoc(String docID);
	
	/**
	 * 保存内容
	 * @throws Exception 
	 * 
	 */
	public void saveContent(Document doc) throws Exception;
	
	public Document getById(String docID) throws Exception;
}