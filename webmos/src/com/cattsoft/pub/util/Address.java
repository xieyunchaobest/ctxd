package com.cattsoft.pub.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Address 
{
	
	private static final Logger log = Logger.getLogger(Address.class);

	public static Properties getAddress() {
		
		Properties config = new Properties();
		InputStream is = null;
		try {
			is = Address.class.getClassLoader().getResourceAsStream("address.properties");
			config.load(is);
		} catch (IOException e) {
			throw new RuntimeException("failed to read address.properties configuration file");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		log.debug("return address config");
		return config;
	}
	
	 public static boolean isSafeThread()  {
	    	java.util.Date nowdate=new java.util.Date(); 
	    	String myString = "2014-02-30";
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
	    	Date d=null;
			try {
				d = sdf.parse(myString);
			} catch (ParseException e) {
				e.printStackTrace();
			}

	    	boolean flag = d.after(nowdate);
	    	System.out.println("flag====="+flag);
	    	return flag;
	    }
	
	public static void main(String args[]) {
		new Address().isSafeThread();
	}
}
