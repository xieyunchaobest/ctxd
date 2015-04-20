package com.cattsoft.pub.dao;

import java.io.File;
import java.io.FileFilter;

//import org.apache.log4j.Logger;
//
//import com.cattsoft.pub.util.LogUtil;

/**
 * DAO接口类过滤器
 * @author Administrator
 *
 */
public class DAOInterfaceFilter implements FileFilter {
//	private static final Logger log = Logger.getLogger(DAOInterfaceFilter.class);
	private static final char INTERFACE_PREFIX = 'I';
	private static final String JAVA_SUFFIX = ".class";

	/**
	 * (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File file) {
		String last5Chars = file.getName().substring(file.getName().length()-6);
//	LogUtil.debug(log,file.getAbsolutePath());
		if(file.isFile() && JAVA_SUFFIX.equals(last5Chars.toLowerCase())){
			if(INTERFACE_PREFIX == file.getName().charAt(0)){
				return true;
			}
		}
		
		return false;
		
		
	}
	
	
	public static void main(String[] args){
		String str = "sdfdsf.cLass";
		//System.out.println(str.substring(str.length()-5));
		//System.out.println(str.charAt(0));
		
	}

}
