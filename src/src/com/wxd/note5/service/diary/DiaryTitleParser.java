package com.wxd.note5.service.diary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryTitleParser {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
 /**
  * 根据标题来解析出来日期
  * @param title 日志的标题，如2012年10月27号 星期六
  */
	public  static  Date  getTitleDate(String title){
		String date = getTitleDateString(title);
		Date target = null;
		if(date != null){
			try {
				target = format.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return target;
	}
	
	/**
	 * 根据标题来解析出来日期
	 * @param title 日志的标题，如2012年10月27号 星期六
	 * @return  解析的日期字符串，如2012-10-27，不识别则返回null
	 */
	public  static String getTitleDateString(String title){
		String year  = null;
		String month = null;
		String date = null;
		if(title != null){		
			StringBuffer tempBuf = new StringBuffer("");
			String [] tokens = title.split("");
			for(String token : tokens){
				//过滤
				if(token.length() == 0) continue; 
				
				//解析年
				if(year == null){
					if(Character.isDigit(token.charAt(0))){
						tempBuf.append(token);
						continue;
					}
					if(token.equals("年")){
						year = tempBuf.toString();
						tempBuf = tempBuf.delete(0,tempBuf.length());
					}
				} 
				
				if(month == null){
					if(Character.isDigit(token.charAt(0))){
						tempBuf.append(token);
						continue;
					}
					if(token.equals("月")){
						month = tempBuf.toString();
						if(month.length() ==1){
							month = "0" + month;
						}
						tempBuf = tempBuf.delete(0,tempBuf.length());
					}
				} 

				if(date == null){
					if(Character.isDigit(token.charAt(0))){
						tempBuf.append(token);
						continue;
					}
					if(token.equals("号") || token.equals("日")){
						date = tempBuf.toString();
						if(date.length() == 1){
							date = "0" + date;
						}
						break;
					}
				}
			}
 		}else{
			return  null;
		} 
		return 	 year + "-" + month + "-" + date;
	} 
	
	public static void main(String args[]){
		String str = getTitleDateString("2013年4月2日  ");
		System.out.println(str);
		System.out.println(getTitleDate("2013年4月2日"));
	}
} 