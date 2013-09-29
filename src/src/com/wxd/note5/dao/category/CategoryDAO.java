package com.wxd.note5.dao.category;

import java.util.List;

import com.wxd.note5.model.category.Category;

 

/**
 * 分类的数据访问层
 * @author 王旭东
 */
public interface CategoryDAO {
	/**
	 * 添加分类
	 */
	public void createCategory(Category c);
	
	/**
	 * 得到分类列表
	 * @param parentID   父分类ID，如果为0，则是第一层分类的列表。如果为-1,则是叶子节点的列表
	 */
	public List<Category> listCategory(String parentID);
	
	/**
	 * 得到所有的父类的列表
	 * @param childrenId	子类的id
	 */
	public List<Category> listParentCategoies(String childrenId);
	
	/**
	 * 通过Id得到分类
	 * @param id
	 * @return  如果没有找到则返回null
	 */
	public Category findById(String id);
	
	/**
	 * 更新指定目录的父目录
	 * @param id				要被更新的目录
	 * @param parentId	更新后的父目录的值
	 */
	public void updateParent(String id,String parentId);
	
	/**
	 * 更新目录名称
	 * @param id					id
	 * @param newName	新的名称
	 */
	public void updateName(String id,String newName);
	
}
