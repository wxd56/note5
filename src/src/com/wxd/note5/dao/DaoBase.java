package com.wxd.note5.dao;

 
import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
 
/**
 *  spring与ibatis的数据层操作工具
 *
 *
 * @author 王旭东
 * @version 1.0 2013年8月5日 下午11:03:23
 *
 */
@Repository
public class DaoBase {

	@Resource(name="jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
	
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClient;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public SqlMapClientTemplate getSqlMapClient() {
		 
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
}

