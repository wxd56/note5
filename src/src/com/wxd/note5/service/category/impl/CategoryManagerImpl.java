package com.wxd.note5.service.category.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxd.note5.dao.category.CategoryDAO;
import com.wxd.note5.model.category.Category;
import com.wxd.note5.service.category.CategoryManager;

 

@Service
public class CategoryManagerImpl  implements CategoryManager{
	
	private CategoryDAO categoryDAO;

	@Override
	public void addCategory(Category c) {
		if(c != null){
			c.setId(UUID.randomUUID().toString());
			categoryDAO.createCategory(c);;
		}
	
	}

	@Autowired
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public List<Category> listCategories(String parentID) {
		if(parentID != null){
			return categoryDAO.listCategory(parentID);
		}
		return null;
	}

	@Override
	public List<Category> getLeafCategory() {
		return this.categoryDAO.listCategory("-1");
	}

	@Override
	public List<Category> getParentCategories(String childrenId) {
		return this.categoryDAO.listParentCategoies(childrenId);
	}

	@Override
	public void moveCategory(String target, String destination) {
		this.categoryDAO.updateParent(target, destination);
	}

	@Override
	public void reName(String id, String newName) {
		this.categoryDAO.updateName(id, newName);
	} 
}