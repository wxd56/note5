package com.wxd.note5.dao.target.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxd.note5.dao.DaoBase;
import com.wxd.note5.dao.target.TargetDAO;
import com.wxd.note5.model.target.Target;
  
@Repository
public class TargetDAOImpl extends DaoBase implements TargetDAO{

	@Override
	public void saveTarget(Target t) {
		 getSqlMapClient().insert("target.saveTarget",t);
	}

	@Override
	public void updateTargetState(String id, String newState) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("state", newState);
		
		this.getSqlMapClient().update("target.updateTargetState",params);
		
	}

	@Override
	public List<Target> findTarget(String type, Date beginDate,String[] stateFilter) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("beginDate", beginDate);
		params.put("type",type);
		
		List<Target> list = getSqlMapClient().queryForList("target.findTarget",params);
		 
		
		return list;
	} 
}