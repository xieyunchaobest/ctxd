package com.cattsoft.pub.dao;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cattsoft.pub.util.StringUtil;

public class AbstractSql {
	
//	 占位符名称-值对应关系Map<String,Parameter>
	protected Map parameters = new HashMap();
//	 占位符的在sql语句中的先后顺序
	protected List paramIndexs = new ArrayList();

	public static final String SYS_PARAMETER_PREFIX = "SYS_PARAMETER_";

	// 占位符起始标记
	protected static final String PARAM_SIGN = ":";

	protected static final String REF_SIGN = "?";
	
	public void setNullBlob(String name){
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_Blob, null));
	}
	
	public void setBlob(String name,Blob blob){
		if( blob == null) 
			this.setNullBlob(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.Blob, blob));
		}
	}
	
	
	public void setBlob(int index,Blob blob){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setBlob(name, blob);
	}
	
	public void setNullClob(String name){
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_Clob, null));
	}
	
	public void setClob(String name,Clob clob){
		if( clob == null) 
			this.setNullClob(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.Clob, clob));
		}
	}
	
	public void setClob(int index,Clob clob){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setClob(name, clob);
	}

	public void setString(String name, String value) {
		if( StringUtil.isBlank(value)) 
			this.setNullString(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.STRING, value));
		}
	}
	
	
	public void setString(int index, String value) {
		String name = SYS_PARAMETER_PREFIX + index;
		this.setString(name, value);
	}

	public void setNullString(String name) {
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_STRING, null));
	}

	public void setDate(String name, Date value) {
		if( null == value) 
			this.setNullDate(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.DATE, value));
		}
	}
	
	public void setDate(int index,Date value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setDate(name, value);
	}
	
	public void setDate(String name, java.util.Date value) {
		if( null == value) 
			this.setNullDate(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.DATE, new Date(value.getTime())));
		}
	}
	
	public void setDate(int index,java.util.Date value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setDate(name, value);
	}
	
	public void setNullDate(String name) {
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_DATE, null));
	}
	
	public void setTimestamp(String name, java.util.Date value) {
		if( null == value) 
			this.setNullDate(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.TIME_STAMP, new Timestamp(value.getTime())));
		}
	}

	public void setTimestamp(int index,java.util.Date value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setTimestamp(name, value);
	}
	
	public void setNullTimestamp(String name) {
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_TIME_STAMP, null));
	}

	public void setInteger(String name, Integer value) {
		if( null == value) 
			this.setNullInteger(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.INTEGER, value));
		}
	}
	
	public void setInteger(int index,Integer value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setInteger(name, value);
	}

	public void setNullInteger(String name) {
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_INTEGER, null));
	}

	public void setInteger(String name, String value) {
		if( StringUtil.isBlank(value)) 
			this.setNullInteger(name);
		else{
			Integer integerValue = Integer.valueOf(value);
			setInteger(name, integerValue);
		}
	}
	
	public void setInteger(int index,String value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setInteger(name, value);
	}

	public void setLong(String name, Long value) {
		if( null == value) 
			this.setNullLong(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.LONG, value));
		}
	}

	public void setLong(int index,Long value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setLong(name, value);
	}
	
	public void setNullLong(String name) {
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_LONG, null));
	}

	public void setLong(String name, String value) {
		if( StringUtil.isBlank(value) ) 
			this.setNullLong(name);
		else{
			Long longValue = Long.valueOf(value);
			setLong(name, longValue);
		}
	}
	
	public void setLong(int index,String value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setLong(name, value);
	}
	
	
	public void setFloat(String name, Float value) {
		if( null == value) 
			this.setNullDouble(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.FLOAT, value));
		}
	}

	public void setFloat(int index,Float value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setFloat(name, value);
	}
	
	public void setNullFloat(String name) {
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_FLOAT, null));
	}

	public void setFloat(String name, String value) {
		if( StringUtil.isBlank(value) ) 
			this.setNullFloat(name);
		else{
			Float floatValue = Float.valueOf(value);
			setFloat(name, floatValue);
		}
	}
	
	public void setFloat(int index,String value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setFloat(name, value);
	}
	
	
	
	public void setDouble(String name, Double value) {
		if( null == value) 
			this.setNullDouble(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.DOUBLE, value));
		}
	}

	public void setDouble(int index,Double value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setDouble(name, value);
	}
	
	public void setNullDouble(String name) {
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_DOUBLE, null));
	}

	public void setDouble(String name, String value) {
		if( StringUtil.isBlank(value) ) 
			this.setNullDouble(name);
		else{
			Double doubleValue = Double.valueOf(value);
			setDouble(name, doubleValue);
		}
	}
	
	public void setDouble(int index,String value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setDouble(name, value);
	}
	
	
	public void setBigDecimal(String name, BigDecimal value) {
		if( null == value) 
			this.setNullBigDecimal(name);
		else{
			name = name.trim();
			parameters.put(name, new Parameter(name, Parameter.BIG_DECIMAL, value));
		}
	}

	public void setBigDecimal(int index,BigDecimal value){
		String name = SYS_PARAMETER_PREFIX + index;
		this.setBigDecimal(name, value);
	}
	
	public void setNullBigDecimal(String name) {
		name = name.trim();
		parameters.put(name, new Parameter(name, Parameter.NULL_BIG_DECIMAL, null));
	}

	

}
