package com.wxd.note5.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 虚拟的VO，其他实体类都应该继承这个类
 * @author 王旭东
 *
 */
public abstract class AbstractValueObject  implements Serializable {
	private Map<String,Object> objects = new HashMap<String, Object>();
	
	/**
	 * 添加一个属性到实体中
	 * @param name	属性名称
	 * @param value	属性值
	 */
	public void addValue(String name,Object value){
		this.objects.put(name, value);
	}
	
	/**
	 * 得到一个属性值
	 * @param name
	 * @return
	 */
	public Object getValue(String name){
		return this.objects.get(name);
	}
}
