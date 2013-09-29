package com.wxd.note5.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	/**
	 * 得到指定日期所在周的，周一到周日所的日期的集合
	 * @param specialDate   指定的的日期
	 * @return
	 */
	public static List<String>  getSpecialWeekDays(Date date){
		List<String> dates = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		 
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		
		int delt = week-1;
		if(delt == 0) delt = 7;
		 
		calendar.add(Calendar.DATE, (delt-1 )* (-1));
		dates.add(sdf.format( calendar.getTime()));
		
		for(int i = 2; i< 8; i++){
			//calendar.roll(Calendar.DATE, true);
			calendar.add(Calendar.DATE, 1);
			dates.add(sdf.format(calendar.getTime()));
		}
	
		return dates;
	}
}