package com.wxd.note5.service.target;

import java.util.Date;
import java.util.List;

import com.wxd.note5.model.target.Target;

/**
 * 目标管理类
 * @author 王旭东
 *
 */
public interface TargetManager {
	/**
	 * 添加一个目标，程序根据目标的类型来自动的修改目标的开始日期。
	 * 具体每种类型所对应的开始日期的说明请查看方法findTargets()的注释。
	 * 
	 * @return 添加成功后返回目标Id
	 * @throws Exception 
	 */
	  public  String  addTarget(Target target) throws Exception;
	  
	  /**
	   * 改变目标状态
	   * 
	   * @param id		目标Id
	   * @param newState  新的状态
	   */
	  public void changeState(String id, String newState);
	  
	  /**
	   * 查找目标
	   * 
	   * @param type  目标类型 {@link com.wxd.note2.domain.v3.TargetType}
	   * @param beginDate  目标开始日期。<br>
	   * 								  每日目标的开始日期就等于beginDate;<br>
	   * 								  周目标的开始日期是当周的周一所在的日期；<br>
	   * 								  月目标的开始日期	是当月的1号；<br>
	   * 								  如果参数不符合上述的规范，则程序会自动调整beginDate为合适的数值，得到里指定日期最近的周目标和月目标。
	   * 
	   * @return  集合，如果没有找到，则size=0。
	   * 				不会包含已经被删除的目标。
	   */
	  public List<Target>  findTargets(String type,Date beginDate) throws Exception;
}