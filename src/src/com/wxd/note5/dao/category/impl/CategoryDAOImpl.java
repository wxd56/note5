package com.wxd.note5.dao.category.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxd.note5.dao.DaoBase;
import com.wxd.note5.dao.category.CategoryDAO;
import com.wxd.note5.model.category.Category;
 

@Repository
public class CategoryDAOImpl  extends DaoBase implements CategoryDAO{

	@Override
	public void createCategory( Category c) {
		getSqlMapClient().insert("category.createCategory",c);
	}

	@Override
	public List<Category> listCategory( String parentID) {
		
		if(parentID.equals("-1")){ 
			return getLeafCategories();
		}
		
		List<Category> categories =  getSqlMapClient().queryForList("category.listCategory",parentID);
		 
		return categories;
	}

 
	private List<Category> getLeafCategories() {
		 List<Category> categories = new ArrayList<Category>();
		 List<Category>  root = listCategory("0");
 
		 getLeaf(categories,root,"");
		 
		return categories;
	}
	
	private void getLeaf(List<Category> result, List<Category>  parent,String parentCateName){
		 for(Category c : parent){
			 c.appendFullName(parentCateName);
			 List<Category> children = listCategory(c.getId());
			 if(children != null && children.size() > 0){
				 getLeaf(result,children,c.getFullName());
			 }else{
				 result.add(c);
			 }			 
		 }
	}

	@Override
	public List<Category> listParentCategoies(String childrenId) {
		 List<Category> categories = new ArrayList<Category>();
	 
		 getParentCategory(categories,childrenId);
		 
		return categories;
	}

 
	private void getParentCategory(List<Category> categories, String childrenId) {
		Category category = findById(childrenId);
		if(category != null){
			getParentCategory(categories, category.getParentID());
			categories.add(category);
		} 
	}

	@Override
	public Category findById( String id) { 
		return (Category) getSqlMapClient().queryForObject("category.findById",id);
	}

	@Override
	public void updateParent(final String id,final  String parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("parent", parentId);
		
		this.getSqlMapClient().update("category.update",params);
	}

	@Override
	public void updateName(final String id,final  String newName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("name",newName);
		
		this.getSqlMapClient().update("category.update",params);
	}

}
