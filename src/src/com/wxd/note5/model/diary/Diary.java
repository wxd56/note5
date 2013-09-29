package com.wxd.note5.model.diary;

import java.util.Arrays;
import java.util.Date;

import com.wxd.note5.model.AbstractValueObject;

/**
 * 
 * 日志
 *
 * @author 王旭东
 * @version 1.0 2013年8月7日 下午3:25:29
 *
 */
public class Diary extends AbstractValueObject{

	/**  */
	private static final long serialVersionUID = 1611735348106516086L;
	
	/**  日志标识  */
	private int id;
	/** 标题 */
	private String title;
	/** 加密后的二进制内容 */
	private byte[] content;
	/**  创建时间 */
	private Date createDate;
	/** 最后修改时间 */
	private Date lastModify;
	/** 日记内容记录时间*/
	private Date diaryOn;
	/** 明文内容 */
	private String plainText;
	
	 
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diary other = (Diary) obj;
		if (id != other.id)
			return false;
		return true;
	}
	 
	
	
 
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Diary [id=" + id + ", title=" + title + ", content="
				+ Arrays.toString(content) + ", createDate=" + createDate
				+ ", lastModify=" + lastModify + ", diaryOn=" + diaryOn
				+ ", plainText=" + plainText + "]";
	}

	/**
	 * Get the 日志标识.
	 * 
	 * @return the 日志标识
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the 日志标识.
	 * 
	 * @param id
	 *            the new 日志标识
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the 标题.
	 * 
	 * @return the 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the 标题.
	 * 
	 * @param title
	 *            the new 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the 加密后的二进制内容.
	 * 
	 * @return the 加密后的二进制内容
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * Set the 加密后的二进制内容.
	 * 
	 * @param content
	 *            the new 加密后的二进制内容
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * Get the 创建时间.
	 * 
	 * @return the 创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Set the 创建时间.
	 * 
	 * @param createDate
	 *            the new 创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Get the 最后修改时间.
	 * 
	 * @return the 最后修改时间
	 */
	public Date getLastModify() {
		return lastModify;
	}

	/**
	 * Set the 最后修改时间.
	 * 
	 * @param lastModify
	 *            the new 最后修改时间
	 */
	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	/**
	 * Get the 日记内容记录时间.
	 * 
	 * @return the 日记内容记录时间
	 */
	public Date getDiaryOn() {
		return diaryOn;
	}

	 
	/**
	 * Set the 日记内容记录时间.
	 * 
	 * @param diaryOn
	 *            the new 日记内容记录时间
	 */
	public void setDiaryOn(Date diaryOn) {
		this.diaryOn = diaryOn;
	}

	 
	/**
	 * Get the 明文内容.
	 * 
	 * @return the 明文内容
	 */
	public String getPlainText() {
		return plainText;
	}
 	
	/**
	 * Set the 明文内容.
	 * 
	 * @param plainText
	 *            the new 明文内容
	 */
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
}