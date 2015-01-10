package com.cattsoft.pub.dao;

import java.io.File;
import java.io.FileFilter;

/**
 * DAO实现包过滤器
 * 
 * @author Administrator
 *
 */
public class DAOImplPackageFilter implements FileFilter {
	private static final String DAO_IMPL_PACKAGE_SUFFIX = "Impl";

	public boolean accept(File file) {
		String last4Chars = file.getName().substring(file.getName().length()-4);
		
		if(file.isDirectory() && DAO_IMPL_PACKAGE_SUFFIX.equals(last4Chars.toLowerCase())){
			return true;
		}else{
			return false;
		}
	}

}
