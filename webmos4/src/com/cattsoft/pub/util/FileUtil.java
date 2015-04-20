package com.cattsoft.pub.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
/**
 * @author liyaquan
 */
public class FileUtil {
	/**
	 * 获取文件大小 单位M
	 * @param file
	 * @return
	 * @throws UtilException
	 */
	public static double getFileSize(File file) throws UtilException{
		try {
		   double size=0;
		         if (file.exists()) {
		             FileInputStream fis = null;
					 fis = new FileInputStream(file);
		             size= fis.available();
		         } else {
		        	 return 0;
		         }
		         size = size/1024.00/1024.00;
		         size=Double.parseDouble(size+"");
		         BigDecimal b = new BigDecimal(size); 
		         double y1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		         java.text.DecimalFormat df =new java.text.DecimalFormat("#.00"); 
		         return Double.parseDouble(df.format(y1));
		   }catch (FileNotFoundException e) {
			   throw new UtilException(e);
		   } catch (IOException e) {
			   throw new UtilException(e);
		}
	}
	/**
	 * 向文件中写入内容
	 * @param file
	 * @param content
	 * @param addFlag
	 * @throws UtilException
	 */
	public static void addContentToFile(File file,String content,boolean addFlag) throws UtilException{
		 try {
			  // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");
			  // String contents = sdf.format(new Date()) + content + "\r\n";
			   RandomAccessFile raf = new RandomAccessFile(file, "rw");
			   if(addFlag){
				   raf.seek(raf.length());
			   }else{
				   raf.seek(0);
			   }
			   raf.write(content.getBytes("UTF-8"));
			   raf.close();
			  } catch (FileNotFoundException e) {
				  throw new UtilException(e);
			  } catch (IOException e) {
				  throw new UtilException(e);
			  }
	}
}
