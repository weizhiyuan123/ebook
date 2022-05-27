package com.demo.ebook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class toolUtil {
	public static boolean isEmpty(String str){
		if(str != null && !"".equals(str.trim())){
			return false;
		}
		return true;
	}

	public static String getDateByTime(Long time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string = format.format(new Date(time));
		return string;
	}

	public static Long getTime(){
		long time = System.currentTimeMillis();
		return time;
	}
}
