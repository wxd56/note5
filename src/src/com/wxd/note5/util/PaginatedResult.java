package com.wxd.note5.util;

import java.util.List;

/**
 *  查询分页结果集
 * @author 王旭东  
 */
public class PaginatedResult<T> {
	
	private List<T> result;
	
	private int pageNo;
	private int pageSize;
	private long totalCount;
	
	public PaginatedResult(){}
	
	public PaginatedResult(int pageNo,int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getTotalPage() {
		if(pageSize > totalCount){
			return 1;
		}else{
			long mod = totalCount % pageSize;
			if(mod == 0){
				return totalCount/pageSize;
			}else{
				return totalCount/pageSize + 1;
			}
		}
	}

	@Override
	public String toString() {
		return "PaginatedResult [result=" + result + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", totalCount=" + totalCount + "]";
	}
	
	
}