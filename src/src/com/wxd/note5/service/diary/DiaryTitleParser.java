package com.wxd.note5.service.diary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryTitleParser {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
 /**
  * ���ݱ�����������������
  * @param title ��־�ı��⣬��2012��10��27�� ������
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
	 * ���ݱ�����������������
	 * @param title ��־�ı��⣬��2012��10��27�� ������
	 * @return  �����������ַ�������2012-10-27����ʶ���򷵻�null
	 */
	public  static String getTitleDateString(String title){
		String year  = null;
		String month = null;
		String date = null;
		if(title != null){		
			StringBuffer tempBuf = new StringBuffer("");
			String [] tokens = title.split("");
			for(String token : tokens){
				//����
				if(token.length() == 0) continue; 
				
				//������
				if(year == null){
					if(Character.isDigit(token.charAt(0))){
						tempBuf.append(token);
						continue;
					}
					if(token.equals("��")){
						year = tempBuf.toString();
						tempBuf = tempBuf.delete(0,tempBuf.length());
					}
				} 
				
				if(month == null){
					if(Character.isDigit(token.charAt(0))){
						tempBuf.append(token);
						continue;
					}
					if(token.equals("��")){
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
					if(token.equals("��") || token.equals("��")){
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
		String str = getTitleDateString("2013��4��2��  ");
		System.out.println(str);
		System.out.println(getTitleDate("2013��4��2��"));
	}
} 