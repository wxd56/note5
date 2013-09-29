package com.wxd.note5.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

 


import com.wxd.note5.model.target.Target;
import com.wxd.note5.model.target.TargetType;
import com.wxd.note5.service.target.TargetManager;
import com.wxd.note5.util.DateUtil;

/**
 * 目标管理控制器
 *
 *
 * @author 王旭东
 * @version 1.0 2013年8月10日 下午6:00:03
 *
 */
@Controller
@RequestMapping("/target")
public class TargetController {
	private Logger log = Logger.getLogger(this.getClass().getName());
	private TargetManager targetManager;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
	 
	private static String[] weekNames = {"","星期一","星期二","星期三","星期四","星期五","星期六","星期日",};
	
	@RequestMapping("show")
	public ModelAndView showTargets(HttpServletRequest req) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		
		//得到选择的日期
		String selectedDate = req.getParameter("selectedDate");
		if(selectedDate == null || "".equals(selectedDate)){
			selectedDate = sdf.format(calendar.getTime());
		}else{
			calendar.setTime(sdf.parse(selectedDate));
		}
		
		ModelAndView mav = new ModelAndView("targets/show");
				
		 //星期几
		int currWeek = calendar.get(Calendar.DAY_OF_WEEK) -1;
		if(currWeek == 0) currWeek = 7;
		mav.addObject("currWeek",currWeek);
		
		//当前日期
		mav.addObject("currDateTime",selectedDate);	
		
		//当前日期所在周的，周一到周日所的日期的集合
		List<String> dayOfWeeks = DateUtil.getSpecialWeekDays(calendar.getTime());
		mav.addObject("dayOfWeeks",dayOfWeeks);
		mav.addObject("weekNames",weekNames);
		
		//得到一周内的所有目标
		List<List<Target>>dayTargetsOfWeek = new ArrayList<List<Target>>();
		for(String day: dayOfWeeks){
			List<Target> dayTargets = new ArrayList<Target>();
			try {
				dayTargets = this.targetManager.findTargets(TargetType.TYPE_DAY, sdf.parse(day));
			} catch (Exception e) {
				log.log(Level.WARNING,"得到每日事务出现错误！");
			}
			dayTargetsOfWeek.add(dayTargets);
		}
		mav.addObject("dayTargetsOfWeek",dayTargetsOfWeek);
		
		//得到本周的周目标列表
		List<Target> weekTargets = null;
		try {
			weekTargets = this.targetManager.findTargets(TargetType.TYPE_WEEK, sdf.parse(selectedDate));
		} catch (Exception e) {
			log.log(Level.WARNING,"得到周目标错误！",e);
			weekTargets = new ArrayList<Target>();
		}
		mav.addObject("weekTargets",weekTargets);
		
		//得到本月的所有目标
		List<Target> monthTargets = null;
		try {
			monthTargets = this.targetManager.findTargets(TargetType.TYPE_MONTH, sdf.parse(selectedDate));
		} catch (Exception e) {
			log.log(Level.WARNING,"得到月目标时出现错误！",e);
			monthTargets = new ArrayList<Target>();
		}
		mav.addObject("monthTargets",monthTargets);
		
		//全年第几周
		mav.addObject("currWeekOfYear", calendar.get(Calendar.WEEK_OF_YEAR));
		mav.addObject("pageTitle","目标管理系统V5.0");
		return mav;
	}
	
	@RequestMapping("addTarget")
	public void addTarget(HttpServletRequest req,HttpServletResponse resp) throws IOException, ParseException{
		String type = req.getParameter("type");
		Target t = new Target();
		t.setType(type);
		
		//设置开始日期
		String beginDate = req.getParameter("beginDate");
		t.setBeginDate(sdf.parse(beginDate));
		
		//设置内容
		t.setContent(getReqContent(req));
		
		//添加
		String id = "0";
		try {
			id = this.targetManager.addTarget(t);
		} catch (Exception e) {
			log.log(Level.WARNING, "添加目标错误！",e);
		}
		
		resp.setContentType("text/xml;charset=utf-8");
		resp.getWriter().print(id);
	}

	//读取请求body中的内容
	private String getReqContent(HttpServletRequest req) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf8"));
		StringBuffer buf = new StringBuffer();
		String line = reader.readLine();
		
		while(line != null){
			buf.append(line);
			line = reader.readLine();
		}
		return buf.toString();
	}
	
	/**
	 * 删除目标
	 */
	@RequestMapping("updateState.do")
	public void deleteTargets(HttpServletRequest req) {
		String id = req.getParameter("id");
		String newState = req.getParameter("newState");
		
		 this.targetManager.changeState(id, newState);
	}
	

	@Autowired
	public void setTargetManager(TargetManager targetManager) {
		this.targetManager = targetManager;
	} 
}