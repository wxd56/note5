package com.wxd.note5.model.doc;

import java.util.Date;
/**
 * 打开文档的历史记录 
 * @author wxd
 *
 */
public class OpenDocHistory {
	private String categoryID;
	private String docID;
	private String docName;
	private Date lastAccess;
	private int count;
	private int sortOrder;
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public void setDocID(String docID) {
		this.docID = docID;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	@Override
	public String toString() {
		return "OpenDocHistory [categoryID=" + categoryID + "\ndocID=" + docID
				+ "\ndocName=" + docName + "\nlastAccess=" + lastAccess
				+ "\ncount=" + count + "\nsortOrder=" + sortOrder + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryID == null) ? 0 : categoryID.hashCode());
		result = prime * result + count;
		result = prime * result + ((docID == null) ? 0 : docID.hashCode());
		result = prime * result + ((docName == null) ? 0 : docName.hashCode());
		result = prime * result
				+ ((lastAccess == null) ? 0 : lastAccess.hashCode());
		result = prime * result + sortOrder;
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
		OpenDocHistory other = (OpenDocHistory) obj;
		if (categoryID == null) {
			if (other.categoryID != null)
				return false;
		} else if (!categoryID.equals(other.categoryID))
			return false;
		if (count != other.count)
			return false;
		if (docID == null) {
			if (other.docID != null)
				return false;
		} else if (!docID.equals(other.docID))
			return false;
		if (docName == null) {
			if (other.docName != null)
				return false;
		} else if (!docName.equals(other.docName))
			return false;
		if (lastAccess == null) {
			if (other.lastAccess != null)
				return false;
		} else if (!lastAccess.equals(other.lastAccess))
			return false;
		if (sortOrder != other.sortOrder)
			return false;
		return true;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public String getDocID() {
		return docID;
	}
	public String getDocName() {
		return docName;
	}
	public Date getLastAccess() {
		return lastAccess;
	}
	public int getCount() {
		return count;
	}
	public int getSortOrder() {
		return sortOrder;
	}
}