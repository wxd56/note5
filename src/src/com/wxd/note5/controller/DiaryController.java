package com.wxd.note5.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxd.note5.model.diary.Diary;
import com.wxd.note5.service.diary.DiaryService;
import com.wxd.note5.util.PaginatedResult;

/**
 * 关于日志操作的控制器
 * 
 * @author 王旭东
 * @version 1.0 2013年8月5日 下午9:41:55
 *
 */
@Controller
@RequestMapping("/diary")
public class DiaryController {
	
	Logger logger = Logger. getLogger(DiaryController.class.getName());
	

	@Resource(name="diaryServiceImpl")
	DiaryService diaryService;
	
	private static final int PAGE_SIZE = 15;
	
	/**
	 * 显示日志列表
	 */
	@RequestMapping("list.do")
	public String list(HttpServletRequest req) throws Exception{		
		String pageNoStr  = req.getParameter("pageNo");
		String id = req.getParameter("id");
		
		int pageNo =1 ;
		if(pageNoStr != null) pageNo = Integer.parseInt(pageNoStr);
		
		PaginatedResult< Diary> result = this.diaryService.getList(pageNo, PAGE_SIZE);
		
		//设置页面中显示日志的标题和内容
		if(id != null){
			req.setAttribute("targetDiary", this.diaryService.getDiaryById(Integer.parseInt(id)));
		}else{
			if(result.getResult().size() > 0)  req.setAttribute("targetDiary", result.getResult().get(0));
		}
		
		req.setAttribute("pageResult", result);
		return "diary/list";
	} 
	
	/**
	 * 保存日志
	 */
	@RequestMapping("save.do")
	public void  saveDiary(HttpServletRequest req ,HttpServletResponse resp) throws NumberFormatException, Exception{
		String id =   req.getParameter("id");
		
	 
		String title  = req.getParameter("title");
		 
		
		//得到保存的内容
		StringBuffer content = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
		String line = reader.readLine();
		while(line != null){
			content.append(line);
			line = reader.readLine();
			if(line != null){
				content.append("\n");
			}
		}
		
		Diary diary = this.diaryService.getDiaryById(Integer.parseInt(id));
		diary.setPlainText(content.toString());
		diary.setLastModify(new Date());
		diary.setTitle(title);
		 
		this.diaryService.updateDiary(diary);
		
		resp.getWriter().print("OK");
	}
	
	/**
	 * 新建一篇日志，默认为今天的日志，并且返回列表
	 *
	 */
	@RequestMapping("newDiary.do")
	public String newDiary(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//新建日志
		this.diaryService.newDiary();
		
		//返回列表
		return this.list(req);
	}
	
	/**
	 * 删除日志
	 */
	@RequestMapping("deleteDiary.do")
	public String newDiary(HttpServletRequest req) throws Exception{
		String id = req.getParameter("id");
		if(id != null){
			this.diaryService.deleteDiary(Integer.parseInt(id));
		}
		
		//返回列表
		return this.list(req);
	}
}
