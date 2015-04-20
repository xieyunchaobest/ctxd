package com.cattsoft.tm.struts;

public class Tools {
	
	public static boolean isDateType(String dataType) {
		boolean flag=false;
		if("DATE".equals(dataType.toUpperCase()))   flag=true;
		return flag;
	}
	
	public static boolean isVarchar(String dataType) {
		boolean flag=false;
		if("VARCHAR2".equals(dataType.toUpperCase())  || "VARCHAR".equals(dataType.toUpperCase()))   flag=true;
		return flag;
	}
	
	public static boolean isNumber(String dataType) {
		boolean flag=false;
		if("NUMBER".equals(dataType.toUpperCase()))   flag=true;
		return flag;
	}
	

}
