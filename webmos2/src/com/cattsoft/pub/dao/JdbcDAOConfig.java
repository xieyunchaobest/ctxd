package com.cattsoft.pub.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ObjectUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.util.UtilException;

/**
 * 
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2007-6-13 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class JdbcDAOConfig {
	private static Logger log = Logger.getLogger(JdbcDAOConfig.class);

	private String daoConfigFileName = "dao_config.properties";
	
	private String dbDialet = null;

	private String interfaceCatalogPrefix = "com.cattsoft";

	private String[] interfaceCatalogAry = null;
	
	private List jarFiles = new ArrayList();
	
	private String[] jarFilesStrAry = null;

	private int daoMode = 0;

	private static final String DB_DIALET = "db.dialect";

	private static final String INTERFACE_CATALOG_PREFIX = "interface.catalog.prefix";

	private static final String INTERFACE_CATALOG_LIST = "interface.catalog.list";

	private static final String DAO_IMPL_SUFFIX = "Impl";

	private static final String DAO_MODE = "dao.mode";

	public static final int DAO_MODE_DEBUG = 0;

	public static final int DAO_MODE_RUN = 1;
	
	public static final String DAO_JAR = "dao.jar";
	
	

	/**
	 * 装载配置文件内容，根据接口文件列表装载实现类列表
	 * 
	 * @param is
	 * @return Map<String接口的全限定名,Object接口对应实现类的实例对象>
	 * @throws SysException
	 * @throws AppException
	 * 
	 */
	public Map getDAOImpls(String daoConfPath) throws AppException, SysException {
		if(StringUtil.isBlank(daoConfPath)){
			throw new AppException("", "没有配置dao_config.properties的路径！");
		}
		// 装载DAO实现的配置
		log.debug("开始装载配置文件:"+daoConfigFileName);
		InputStream is = null;		
		if(daoConfigFileName.equals(daoConfPath)){
			is = DAOFactory.class.getClassLoader().getResourceAsStream(daoConfigFileName);
		}else{
			daoConfigFileName = daoConfPath;
			File file = new File(daoConfigFileName);
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new SysException("1000005","jvm参数daoconfigURL配置的dao_config.properties路径不正确！",e);
			}			
		}
		
		if (is == null)
			throw new AppException("", "找不到dao_config.properties配置文件！");
		Map DAOImpls = new HashMap();
		this.readProperties(is);
		
		String classPath = "";
		URL url = Thread.currentThread().getContextClassLoader().getResource("/");
		if(url != null){
			classPath = url.getPath();
			log.debug("系统路径："+classPath);
		}
		String jarPath = "";
		 
		if(jarFilesStrAry!=null){
			for(int i=0;i<jarFilesStrAry.length;i++){
				if(!StringUtil.isBlank(jarFilesStrAry[i])){
					//jarPath = classPath + jarFilesStrAry[i];
					jarPath = jarFilesStrAry[i];
					log.debug("读取jar文件："+jarPath);
					try {
						JarFile jarFile = new JarFile(jarPath);
						jarFiles.add(jarFile);
						log.debug("读取jar文件："+jarFile);
					} catch (IOException e) {
						log.debug("读取jar文件失败！"+jarPath);
					}
				}
			}
		}

		// 未配置DAO接口目录
		if (null == interfaceCatalogAry)
			return null;

		// 遍历装载DAO接口 
		for (int i = 0; i < interfaceCatalogAry.length; i++) {	
			interfaceCatalogAry[i] = interfaceCatalogPrefix.trim() + interfaceCatalogAry[i].trim();
			log.debug("装载DAO包：" + interfaceCatalogAry[i]);
			String relativePath =  interfaceCatalogAry[i].replaceAll("\\.", "/");
			
			List interfaceClassNameList = new ArrayList(); 
//			遍历jar文件，过滤其中的DAO接口文件名列表
			List interfaceDAOJar = filterJarDAOInterface(relativePath);
			if(interfaceDAOJar != null) {
				interfaceClassNameList.addAll(filterJarDAOInterface(relativePath));
				log.debug("Jar文件中找到dao接口类:"+interfaceClassNameList.size()+"个。");
			}else{
				log.debug("Jar文件中未找到dao接口类！");
			}
			
			//log.debug("文件相对路径："+ relativePath);
			URL pathUrl = JdbcDAOConfig.class.getResource("/" + relativePath);
			if (null != pathUrl) {
				String path = pathUrl.getPath();
				log.debug("!!!文件绝对路径："+ path);
				File daoPackage = new File(path);
				// 不是目录
				/*if (!daoPackage.isDirectory()) {
					throw new AppException("", "请检查dao_conAfig.properties配置文件的DAO包名配置!");
				}*/
				// 获取dao接口文件
				File[] daoInterfaceFiles = daoPackage.listFiles(new DAOInterfaceFilter());
	
				if (null == daoInterfaceFiles || daoInterfaceFiles.length == 0) {
					log.debug("未找到要装载的DAO对象！");
					continue;
				}
	
				// log.debug(daoInterfaceFiles[0].getName());
				// 找当前dialect对应的DAO实现包
				String str = "/" + dbDialet + DAO_IMPL_SUFFIX;
				File dialectImplPackage = new File(daoPackage + str);
				// log.debug(daoPackage + str);
				if (!dialectImplPackage.isDirectory()) {
					throw new AppException("", "找不到dao配置" + DB_DIALET + "对应的DAO实现包!");
				}
				
				for(int k=0;k<daoInterfaceFiles.length;k++){
					interfaceClassNameList.add(daoInterfaceFiles[k].getName());
				}		
			}
			
//			 遍历DAO接口去装载DAO实现类
			Map interfaceDAOImpl = loadDAOImpl(interfaceCatalogAry[i],interfaceClassNameList);
			if(interfaceDAOImpl != null){
				DAOImpls.putAll(interfaceDAOImpl);
			}
			
		}
		
		
		
		
		
		return DAOImpls;
	}
	
	
	/**
	 * 遍历jar文件，过滤其中的DAO接口文件名列表
	 * @param relativePath
	 * @return
	 */
	public List filterJarDAOInterface(String relativePath){
		if(jarFiles == null || jarFiles.size()==0){
			return null;
		}
		
		List interfaceClassNameList = new ArrayList();
		for(int i=0;i<jarFiles.size();i++){
			JarFile jfile = (JarFile)jarFiles.get(i);
			Enumeration enu = jfile.entries();
			while(enu.hasMoreElements()){
				JarEntry entry = (JarEntry)enu.nextElement();
				String realName = entry.getName();
				String files[] = realName.split(relativePath+"/");
				//匹配不上
				if(files == null || files.length <= 1) continue;
				//是dao接口包下的内容
				if(!StringUtil.isBlank(files[1]) && files[1].indexOf("/") == -1){
					String last5Chars = files[1].substring(files[1].length()-6);
//					是class文件，是接口类(I开头)
					if(".class".equals(last5Chars.toLowerCase())){
						//if(files[1].indexOf("wm")>0){
							//log.debug("wm."+files[1] );
						//}
						if('I' == files[1].charAt(0)){
							interfaceClassNameList.add(files[1]);
							//log.debug("jarfile:"+files[1].substring(0,files[1].length()-6));
						}
					}
				}
				
			}
		}
		
		return interfaceClassNameList;
	}
	
	
	/**
	 * 遍历DAO接口文件装载对应的DAO实现类
	 * @param interfaceCatalog
	 * @param interfaceClassNameList<String>
	 * @return
	 */
	private Map loadDAOImpl(String interfaceCatalog,List interfaceClassNameList){
		//未找到接口类
		if(interfaceClassNameList == null || interfaceClassNameList.size() == 0){
			return null;
		}
		//log.debug("装载dao实现...");
		Map DAOImpls = new HashMap();
		String aDAOInterface = null;
		String DAOInterfacePrefix = interfaceCatalog + ".";
		String DAOImplPrefix = interfaceCatalog + "." + dbDialet + DAO_IMPL_SUFFIX + ".";
		String DAOInterfaceName;
		String DAOImplName = null;
		int countDao = 0;
		for (int j = 0; j < interfaceClassNameList.size(); j++) {
			aDAOInterface = (String)interfaceClassNameList.get(j);

			// 接口名和实现名
			DAOInterfaceName = DAOInterfacePrefix + aDAOInterface;
			DAOImplName = DAOImplPrefix + aDAOInterface.substring(1);// 去掉接口标识“I”

			// 去掉后面的.class
			try {
				DAOInterfaceName = StringUtil.removeSuffix(DAOInterfaceName, ".class");
				DAOImplName = StringUtil.removeSuffix(DAOImplName, ".class");

				//log.debug("DAO接口名称:"+DAOInterfaceName);
				//log.debug("DAO实现名称:"+DAOImplName);
				// 实现名称加上Impl后缀
				DAOImplName = DAOImplName + DAO_IMPL_SUFFIX;
				Object impl = ObjectUtil.createByName(DAOImplName);
				//log.debug("成功装载DAO:"+DAOImplName);
				//log.debug("map key:"+DAOInterfaceName);
				DAOImpls.put(DAOInterfaceName, impl);
				countDao++;
			} catch (UtilException e) {
				log.error( DAOImplName + "文件装载错误!"+e.getMessage());
				continue;
			}
		}
		log.debug("成功装载" + interfaceCatalog + "的DAO实现" + countDao + "个。");
		return DAOImpls;
	}

	/**
	 * 读取配置文件配置
	 * 
	 * @param is
	 * @throws AppException
	 * @throws SysException
	 */
	public void readProperties(InputStream is) throws AppException, SysException {
		if (null == is)
			throw new AppException("", "系统未获取到DAO初始化配置！");
		Properties config = new Properties();
		try {
			config.load(is);
		} catch (IOException e) {
			log.error( e.getMessage());
			throw new AppException("", "系统装载DAO配置文件失败！");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					log.error( e.getMessage());
					throw new SysException("", "系统关闭配置读取控制错误！", e);
				}
			}
		}

		this.dbDialet = (String) config.get(DB_DIALET);
		String daoModeStr = (String) config.get(DAO_MODE);
		this.daoMode = Integer.parseInt(daoModeStr.trim());
		this.interfaceCatalogPrefix = (String) config.get(INTERFACE_CATALOG_PREFIX);
		String interfaceCatalogListStr = (String) config.get(INTERFACE_CATALOG_LIST);
		interfaceCatalogAry = interfaceCatalogListStr.split(",");
		//读jar.file配置
		String jarFilesStr = config.getProperty(DAO_JAR);
		if(!StringUtil.isBlank(jarFilesStr)){
			this.jarFilesStrAry = jarFilesStr.trim().split(",");
		}
		log.debug("dbDialet:" + dbDialet);
		log.debug("interfaceCatalogPrefix:" + interfaceCatalogPrefix);
		log.debug("interfaceCatalogListStr:" + interfaceCatalogListStr);
	}

	public static void main(String[] args) throws Exception {
		// String str =
		// JdbcDAOConfig.class.getResource("/dao_config/dao_config.properties").getPath();
		// File file = new File(str);
		String temp = "com.cattsoft.tm.component.dao";

		// System.out.println("----"+JdbcDAOConfig.class.getResource("/dao_config"));

		//System.out.print(temp.replaceAll("\\.", "/"));
	}

	public int getDaoMode() {
		return daoMode;
	}

	public void setDaoMode(int daoMode) {
		this.daoMode = daoMode;
	}

}
