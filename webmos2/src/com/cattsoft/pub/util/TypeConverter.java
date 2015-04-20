package com.cattsoft.pub.util;

public class TypeConverter {
	/**
	 * convert database type to simple java type without full qulified package
	 * 
	 * @param dbtype
	 * @return
	 */
	public static String convert(String dbtype) {
		String type = "String";
		if (isDate(dbtype)) {
			type = "Date";
		} else if (isBlob(dbtype)) {
			type = "Blob";
		} else if (isClob(dbtype)) {
			type = "Clob";
		}
		return type;
	}

	public static boolean isDate(String type) {
		if ("java.sql.Date".equals(type) || "java.sql.Timestamp".equals(type)) {
			return true;
		}
		return false;
	}

	public static boolean isBlob(String type) {
		if ("java.sql.Blob".equals(type) || "oracle.sql.BLOB".equals(type)) {
			return true;
		}
		return false;
	}

	public static boolean isClob(String type) {
		if ("java.sql.Blob".equals(type) || "oracle.sql.CLOB".equals(type)) {
			return true;
		}
		return false;
	}
}
