package com.wxd.note5.controller;

import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

 


import com.wxd.note5.model.category.Category;
import com.wxd.note5.model.doc.Document;
import com.wxd.note5.model.doc.OpenDocHistory;
import com.wxd.note5.service.category.CategoryManager;
import com.wxd.note5.service.doc.DocManager;
import com.wxd.note5.service.doc.OpenDocHistoryManager;
import com.wxd.note5.util.PaginatedResult;
 

@Controller
@RequestMapping("/doc")
public class DocController {
	private DocManager docManager;
	private CategoryManager categoryManager;
 
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private OpenDocHistoryManager historyManager;
	 
	
	@RequestMapping("show")
	public ModelAndView showDocs(HttpServletRequest req) throws Exception{
		 
		ModelAndView mav = new ModelAndView("doc/list");
		String selectedCategoryId = req.getParameter("categoryId");
		if(selectedCategoryId == null || "".equals(selectedCategoryId)){
			selectedCategoryId = "0";
		}
		
		List<Category> subList = categoryManager.listCategories(selectedCategoryId);
		mav.addObject("subList",subList);
		mav.addObject("selectedCategoryId",selectedCategoryId);
		
		 
		List<Category> parentList = categoryManager.getParentCategories(selectedCategoryId);
		mav.addObject("parentList",parentList);		
		
 
		PaginatedResult<Document> docs = docManager.getOtherDocs(-1, 10, selectedCategoryId);
		mav.addObject("docs", docs.getResult());
		
	 
		List<OpenDocHistory> historyList = historyManager.getHistoryList(25);
		mav.addObject("historyList",historyList);
		
 
		String docId = req.getParameter("docId") ;
		String attrDocId = (String) req.getAttribute("docId");
		docId = (docId == null?attrDocId:docId);
		if(docId != null){
			editDoc(docId,mav); 
		} 
		
		mav.addObject("pageTitle","文档管理系统5.0");		
		return mav;
	}
	
	/**
	 * 新建
	 * @throws ParseException 
	 */
	@RequestMapping("newDoc")
	public ModelAndView newDoc(HttpServletRequest req) throws  Exception {
		String categoryId = req.getParameter("categoryId");
 
		Document doc = new Document();
		doc.setTitle("no title " + sdf.format(new Date()));
		doc.setCategory(new Category(categoryId));
		String docId = this.docManager.newDoc(doc);
		
		req.setAttribute("docId", docId);
		req.setAttribute("selectedCategoryId", categoryId);
		return showDocs(req);
	}
	
	/**
	 * 查看
	 * @throws Exception 
	 */
	private void editDoc(String docId, ModelAndView mav) throws Exception {
		    mav.addObject("docId", docId);
			Document doc = docManager.getById(docId);
			
			mav.addObject("document", doc);

	}
	
	/**
	 * 更新文档标题
	 * 
	 */
	@RequestMapping("updateTitle")
	public void updateTitle(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String title = req.getParameter("title");
		String id = req.getParameter("id");
		
		this.docManager.updateTitle(id, title);
		resp.getWriter().print("111");
	}
	
	/**
	 * 删除
	 * @throws Exception 
	 */
	@RequestMapping("deleteDoc")
	public ModelAndView deleteDoc(HttpServletRequest req) throws Exception  {
		String id = req.getParameter("id");		
		docManager.deleteDoc(id);	 
		return showDocs(req);
	}

	
	/**
	 * 保存文档 
	 * @throws Exception 
	 */
	@RequestMapping("save")
	public void saveDoc(@RequestParam("id")String id,			
			@RequestParam("category")String category,
			@RequestParam("type")String type,
			HttpServletRequest req,
			HttpServletResponse resp) throws Exception{
		
		String title = req.getParameter("title");
		
		StringBuffer content = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
		String line = reader.readLine();
		while(line != null){
			content.append(line);
			line = reader.readLine();
			if(line != null){
				content.append("\n");
			}
		}
		
		Document doc = this.docManager.getById(id);
		if(doc == null){
			//创建
			 doc =  new Document();
			 doc.setCategory(new Category(category));
			 doc.setContent(content.toString());
			 doc.setTitle(title);
		 
			 id = this.docManager.newDoc(doc);
		}else{
			doc.setContent(content.toString());
			doc.setCategory(new Category(category));
			
			this.docManager.saveContent(doc);
		}
		
		//返回id
		resp.getWriter().print(id);
	}
	
	@RequestMapping("search")
	public String searchDocs(HttpServletRequest req){
		String keyWords = req.getParameter("keyWords");
		long start = System.currentTimeMillis();
		List<Document> docs = this.docManager.searchDocs(keyWords);
		long end = System.currentTimeMillis();
		req.setAttribute("docs", docs);
		
		req.setAttribute("count", docs.size());
		req.setAttribute("time", (end-start)/1000.0);
		
		return "doc/searchResult";
	}

	@Autowired
	public void setDocManager(DocManager docManager) {
		this.docManager = docManager;
	}
	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	} 
	 
	@Autowired
	public void setHistoryManager(OpenDocHistoryManager historyManager) {
		this.historyManager = historyManager;
	} 
}