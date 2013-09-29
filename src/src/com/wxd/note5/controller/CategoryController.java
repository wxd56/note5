package com.wxd.note5.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

 

import com.wxd.note5.model.category.Category;
import com.wxd.note5.service.category.CategoryManager;

 

@Controller
@RequestMapping("/category")
public class CategoryController {	 
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh时mm分");
	@Autowired
	CategoryManager manager;
	
	@RequestMapping("manage")
	public ModelAndView showDocs(HttpServletRequest req) throws ParseException{
		ModelAndView mav = new ModelAndView("category/manageCategory");		
		mav.addObject("pageTitle","事务管理系统5.0");		
		return mav;
	} 
	
	/**
	 * 添加分类
	 */
	@RequestMapping("addCateogry")
	public void addCategory(	@RequestParam("parent")String parent,
													HttpServletRequest req,
													HttpServletResponse resp) throws IOException{
		
		if(parent.equals("null")){
			parent = "0";
		}
		
		String categoryName  =  new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8")).readLine();
		 
		Category c = new Category();
		c.setCreateDate(new Date());
		c.setLastModify(new Date());
		c.setName(categoryName);
		c.setParentID(parent);
		this.manager.addCategory(c);
		
		//返回添加的Id
		resp.getWriter().print(c.getId());
	}
	
	/**
	 * 列出某个父类的所有的子类
	 * @param parent		父分类ID
	 * @param resp		用于将返回值写进对象中。注意返回为Json格式的字符串
	 */
	@RequestMapping("list")
	public void listCategory(@RequestParam("parent")String parent,HttpServletResponse resp) throws IOException{
		if(parent.equals("null")){
			parent = "0";
		}
		
		List<Category> categories = this.manager.listCategories(parent);
		StringBuffer buf = new StringBuffer();
		buf.append("{\"data\":[");
		for(int i = 0; i< categories.size(); i++){
			Category c = categories.get(i);
			buf.append("{");
			buf.append("\"name\":\"");
			buf.append(c.getName());
			buf.append("\",\"id\":\"");
			buf.append(c.getId());
			buf.append("\",\"parent\":\"");
			buf.append(c.getParentID());
			buf.append("\",\"createDate\":\"");
			buf.append(sdf.format(c.getCreateDate()));
			buf.append("\",\"lastModify\":\"");
			buf.append(sdf.format(c.getLastModify()));
			buf.append("\"}");
			if(i != categories.size()-1){
				buf.append(",");
			}
			
		}
		buf.append("]}");
		 
		//返回数据
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(buf.toString());
	}
	
	/**
	 * 重命名
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("rename")
	public void rename(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException, IOException{
		String id = req.getParameter("id");
		String categoryName  =  new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8")).readLine();
		
		this.manager.reName(id, categoryName);
	}
	
	/**
	 * 移动分类
	 * @param req
	 * @throws IOException 
	 */
	@RequestMapping("move")
	public void move(HttpServletRequest req,HttpServletResponse resp) throws IOException{		
		String target = req.getParameter("target");
		String destination = req.getParameter("destination");
		this.manager.moveCategory(target,destination);
		
		resp.getWriter().print("OK");
	}
 
}