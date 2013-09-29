package com.wxd.note5.model.target;

import java.io.Serializable;
import java.util.Date;

/**
 * 目标实体
 * 
 * @author 王旭东
 */
public class Target  implements Serializable {
	 
	/**  */
	private static final long serialVersionUID = -3458324520374354983L;
	
	/** 目标内容*/
	private String content;
	/** 标示 */
	private String id;
	/** 开始日期 */
	private Date   beginDate;
	/** 目标类型*/
	private String type;
	/** 目标状态 */
	private String state;
	/**  结束日期  */
	private Date endDate;
	
	/**
	 * Get the 目标内容.
	 *
	 * @return the 目标内容
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Set the 目标内容.
	 *
	 * @param content the new 目标内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	 
	/**
	 * Get the 开始日期.
	 *
	 * @return the 开始日期
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * Set the 开始日期.
	 *
	 * @param beginDate the new 开始日期
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * Get the 目标类型.
	 *
	 * @return the 目标类型
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Set the 目标类型.
	 *
	 * @param type the new 目标类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Get the 目标状态.
	 *
	 * @return the 目标状态
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Set the 目标状态.
	 *
	 * @param state the new 目标状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Get the 结束日期.
	 *
	 * @return the 结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the 结束日期.
	 *
	 * @param endDate the new 结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	@Override
	public String toString() {
		return "Target [content=" + content + "\nid=" + id + "\nbeginDate="
				+ beginDate + "\ntype=" + type + "\nstate=" + state
				+ "\nendDate=" + endDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Target other = (Target) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/**
	 * Get the 标示.
	 *
	 * @return the 标示
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set the 标示.
	 *
	 * @param id the new 标示
	 */
	public void setId(String id) {
		this.id = id;
	}
}
