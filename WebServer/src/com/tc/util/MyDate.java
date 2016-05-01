package com.tc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

	public static String getNowTime() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm-ss").format(new Date());
	}
	public static String getTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm-ss").format(date);
	}
	public static Date getDateByString(String yyMMddhhmmss){

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm-ss");  
	    Date date = null;
		try {
			date = sdf.parse(yyMMddhhmmss);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return date;
	}

}
