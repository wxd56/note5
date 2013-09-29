package com.wxd.note5.model.category;

import java.util.Date;

/**
 * 表示分类
 * 
 * @author 王旭东
 * @since 2012年2月3日16:06:41    
 */
public class Category {
	/** 标示*/
	private String id;
	/** 分类名称*/
	private String name;
	/** 父分类ID*/
	private String parentID;
	/** 创建时间*/
	private Date createDate;
	/** 最后修改时间*/
	private Date lastModify;
	/** 全名如： c1/c2/c3/c4*/
	private StringBuffer fullName = new StringBuffer("");
	
	public Category(){}
	
	public Category(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModify() {
		return lastModify;
	}
	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + "\nname=" + name + "\nparentID="
				+ parentID + "\ncreateDate=" + createDate + "\nlastModify="
				+ lastModify + "]";
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public void appendFullName(String name){
		this.fullName.append(name);
		if(!name.equals("")){
			this.fullName.append("/");
		}		
	}

	public String getFullName() {
		return fullName.toString() + this.name;
	}
	
}
