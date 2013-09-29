package com.wxd.note5.model.doc;

import java.util.Date;

import com.wxd.note5.model.category.Category;

 

/**
 * 表示文档
 * @author 王旭东
 * @since  2012年2月9日12:47:21
 */
public class Document {
	private String id;
	 
	/** 所属分类*/
	private Category category;
	/** 文章内容*/
	private String content;
	/** 文章标题*/
	private String title;
	/** 创建时间*/
	private  Date createDate;
	/**　上次修改时间 */
	private  Date lastModify;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
 
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		return "Document [title=" + title + ", createDate=" + createDate + "]";
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
		Document other = (Document) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
