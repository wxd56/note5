package com.wxd.note5.service.category;

import java.util.List;

import com.wxd.note5.model.category.Category;

 

public interface  CategoryManager {
	/**
	 * 添加一个分类
	 */
	public void addCategory(Category c);
	
	/**
	 * 列出某个分类的所有的子分类
	 * @param parentID		父分类ID，0表示顶层分类Id
	 */
	public List<Category> listCategories(String parentID);
	
	/**
	 * 得到叶子节点
	 * @return
	 */
	public List<Category> getLeafCategory();
	
	public List<Category> getParentCategories(String childrenId);

	/**
	 * 移动目录
	 * @param target			要被移动的目录
	 * @param destination	移动的目的目录
	 */
	public void moveCategory(String target, String destination);
	
	/**
	 * 对分类进行重命名
	 * @param id
	 * @param newName
	 */
	public void reName(String id,String newName);
}
