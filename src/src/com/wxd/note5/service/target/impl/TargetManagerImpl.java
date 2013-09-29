package com.wxd.note5.service.target.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

import com.wxd.note5.dao.target.TargetDAO;
import com.wxd.note5.model.target.Target;
import com.wxd.note5.model.target.TargetState;
import com.wxd.note5.model.target.TargetType;
import com.wxd.note5.service.target.TargetManager;
import com.wxd.note5.util.DateUtil;
import com.wxd.note5.util.EncryptDESTool;

/**
 * 目标管理类的默认实现
 * 
 * @author 王旭东
 */
@Service
public class TargetManagerImpl implements TargetManager{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private TargetDAO targetDAO ;
	@Autowired
	EncryptDESTool tool;
 
	
	@Override
	public String  addTarget(Target target) throws Exception {
		if(target == null) return "0";
		
		//计算Id
		String id =  UUID.randomUUID().toString();
		target.setId(id);
		
		//计算开始时间		
		Date beginDate  = this.calculateTargetBeginDate(target.getType(), target.getBeginDate());
		target.setBeginDate(beginDate);
		
		//结算结束时间		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		
		if(target.getType().equals(TargetType.TYPE_DAY)){
			target.setEndDate(beginDate);
			
		}else if(target.getType().equals(TargetType.TYPE_WEEK)){
			calendar.add(Calendar.DATE, 7);
			target.setEndDate(calendar.getTime());
			
		}else if(target.getType().equals(TargetType.TYPE_MONTH)){
			calendar.roll(Calendar.MONTH, true);
			target.setEndDate(calendar.getTime());
		}
		
		//初始化状态
		target.setState(TargetState.STATE_CREATE);
		
		//对内容加密
		target.setContent(tool.encrypt(target.getContent()));
		
		//保存
		targetDAO.saveTarget(target);
		
		return id; 
	}

	@Override
	public void changeState(String id, String newState) {
		 this.targetDAO.updateTargetState(id, newState);
	}

	@Override
	public List<Target> findTargets(String type, Date beginDate) throws Exception {
		Date newBeginDate  = this.calculateTargetBeginDate(type,beginDate);		
		List<Target> list =  this.targetDAO.findTarget(type, newBeginDate,new String[]{TargetState.STATE_CREATE,TargetState.STATE_FINISHED});
		for(Target t :list){
			//解密内容
			t.setContent(tool.decrypt(t.getContent()));
		}
		
		return list;
	}

	@Autowired
	public void setTargetDAO(TargetDAO targetDAO) {
		this.targetDAO = targetDAO;
	}
	 
	
	/**
	 * 根据目标类型，将原始日期调整为合适的开始日期

	 * @return		 每日目标的开始日期就等于beginDate;<br>
	   * 					 周目标的开始日期是当周的周一所在的日期；<br>
	   * 					 月目标的开始日期	是当月的1号；<br>  
	 *  
	 */
	private Date calculateTargetBeginDate(String type,Date originalDate) throws ParseException{
		
		if(type.equals(TargetType.TYPE_DAY)) {
			return originalDate;
			
		}else if(type.equals(TargetType.TYPE_WEEK)){
			List<String> dates = DateUtil.getSpecialWeekDays(originalDate);
			return sdf.parse(dates.get(0));
			
		}else if(type.equals(TargetType.TYPE_MONTH)){
			Calendar c = Calendar.getInstance();
			c.setTime(originalDate);
			c.set(Calendar.DATE, 1);
			return c.getTime();
		}
		return null;
	}
}
