package com.wxd.note5.dao.doc;

import java.util.List;

import com.wxd.note5.model.doc.Document;
import com.wxd.note5.util.PaginatedResult;
 
public interface  DocDAO {
	public void createDoc(Document doc);
	
	public void deleteDoc(String docID);
	
	public void updateDoc(Document doc);
	
	/**
	 * 列出任务列表
	 * @param pageSize		分页的大小
	 * @param pageNo		页号,如果为0，则取出所有的条目
	 * @param category		文档所属分类
	 */
	public PaginatedResult<Document> listDoc(int pageSize,int pageNo,String category);
	
	public Document getByID(String id);	
	
	/**
	 * 更新文档标题
	 */
	public void updateTitle(String id,String newTitle);
	
	/**
	 * 根据标题模糊查询文档
	 */
	public  List<Document> searchDocs(String title);
}
