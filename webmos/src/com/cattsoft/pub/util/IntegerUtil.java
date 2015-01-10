package com.cattsoft.pub.util;

public class IntegerUtil {
	
	/**
	 * 将long类型转换成int类型
	 * @param value
	 * @return
	 */
	public static int convert(long value){
		String strLong = Long.toString(value);
		return Integer.parseInt(strLong);
	}
	
	
	
	/**
	 * 将string类型转换成int类型
	 * @param str
	 * @return
	 */
	public static int parse(String str){
		if(str == null) return 0;
		if(str.trim().equals("")) return 0;
		return Integer.parseInt(str);
	}

}
