package com.cattsoft.pub.util;

public class StringArrayUtil {
	
	
	/**
	 * 拼接字符串数组内容
	 * @param strAry
	 * @return
	 */
	public static String toString(String[] strAry){
		String str = "";
		if(strAry != null){
			for(int i=0 ; i<strAry.length; i++){
				str = str +"," + strAry[i];
			}
			if(strAry.length>0){
				str = str.substring(1);
			}
		}
		return str;
	}
	
	
	public static String[] fetchStr(String str,String splitStr){
		if(str == null) return null;
		String[] ary = str.split(splitStr);
		if(ary.length<1){
			if(StringUtil.isBlank(ary[0])){
				return null;
			}
		}
		return ary;
	}

}
