package com.wxd.note5.dao.target;

import java.util.Date;
import java.util.List;

import com.wxd.note5.model.target.Target;

 

/**
 * 目标的数据访问类
 * 
 * @author 王旭东
 * @version 1.0 2012年11月19日
 */
public interface TargetDAO {
	/**
	 * 保存目标
	 * @param t
	 */
	public void saveTarget(Target t);
	
	/**
	 * 更新目标状态
	 * @param id			   目标标识
	 * @param newState 新的状态	
	 */
	public void updateTargetState(String id,String newState);
	
	/**
	 * 找到指定类型的目标
	 * @param type			    目标类型
	 * @param beginDate	开始日期
	 * @param stateFilter		只是查询指定状态的目标，详细状态查看 TargetState
	 */
	public List<Target> findTarget(String type,Date beginDate,String [] stateFilter);
}
