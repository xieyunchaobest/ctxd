package com.cattsoft.pub.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cattsoft.pub.LogConfig;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ObjectUtil;
import com.cattsoft.pub.util.UtilException;
/**
 * Abstract class for dao factory <br>
 * Apr 18, 2007 9:17:25 PM
 * 
 * @author liaoyh
 */
public class DAOFactory {
	private static Logger log = Logger.getLogger(DAOFactory.class);
	private JdbcDAOConfig config = new JdbcDAOConfig();
	
	
	private static DAOFactory instance;
	
	//缓存DAO实现类实例
	private   Map daoImplCache = null;
	
	//public static final String daoConfigFiles = "dao_config.properties";

	private DAOFactory(String path) throws AppException,SysException{
		
		daoImplCache = new HashMap();
		daoImplCache.putAll(config.getDAOImpls(path));

		log.debug("成功装载文件!" + path);
	}

	/**
	 * 获取DAO工厂实例，没有则创建新实例
	 * @param path
	 * @return
	 * @throws SysException 
	 * @throws AppException 
	 */
	synchronized public static DAOFactory initDAOFactory(String path) throws AppException, SysException{
		if(null == instance){
			instance = new DAOFactory(path);
		}
		return instance;
	}
	
	/**
	 * 获取DAO工厂实例，没有则创建新实例
	 * @param path
	 * @return
	 * @throws SysException 
	 * @throws AppException 
	 */
	synchronized public static DAOFactory initDAOFactory() throws AppException, SysException{
		if(null == instance){
			instance = new DAOFactory(null);
		}
		return instance;
	}
	
	/**
	 * 获取DAO工厂实例
	 * @return
	 * @throws AppException 
	 */
	/*public static DAOFactory getInstance() throws AppException{
		if(null == instance){
			throw new AppException("","系统未初始化DAOFactory！");
		}
		return instance;
	}*/
	 
	/**
	 * 通过DAO接口类获取对应的DAO实现
	 * @param DAOClass
	 * @return
	 * @throws AppException 
	 * @throws SysException 
	 */
	public static IDAO getDAO(Class DAOClass) throws AppException, SysException{
		if( null == DAOClass){
			throw new AppException("","无效的参数Null！");
		}
		if(null == instance){
			//throw new AppException("","系统未初始化DAOFactory！");
			initDAOFactory();
		}
		Object daoImpl = instance.daoImplCache.get(DAOClass.getName());
		if(null == daoImpl){
			throw new AppException("","找不到接口对应的实现类！["+DAOClass.getName()+"]");
		}
		
//		如果daoMode是调试模式，每次访问时创建新的实例，
		if(instance.config.getDaoMode() == JdbcDAOConfig.DAO_MODE_DEBUG){
			Class classObj = daoImpl.getClass();
			
			try {
				daoImpl = ObjectUtil.createByClass(classObj);
			} catch (UtilException e) {
				throw new SysException("","装载DAO实现类失败！",e);
			}
		}
		if(null == daoImpl){
			throw new AppException("100003","找不到接口对应的DAO实现类。"+DAOClass.getName());
		}
		return (IDAO)daoImpl;
	}
	
	
	
	
	
	 public static void main(String[] args ) throws Exception{
		 /*LogConfig.init();
		 DAOFactory.initDAOFactory("dao_config.properties");
		 ConnectionFactory.createConnection();
		 ILocalNetSDAO dao = (ILocalNetSDAO)DAOFactory.getDAO(ILocalNetSDAO.class);
		 LocalNetSVO localNet = new LocalNetSVO();
		 localNet.setLocalNetId("111");
		 localNet.setName("石家庄");
		 
		try{
			dao.delete(localNet);
		
		}finally{
			ConnectionFactory.closeConnection();
		}*/
		/*URL dao_url = Thread.currentThread().getContextClassLoader().getResource("");
		 System.out.println(dao_url);
		 String path = dao_url.getPath();
		 path = path + "../lib/wm.jar";
		 System.out.println(path);
		 JarFile jfile = new JarFile(path);
		
			Enumeration enu = jfile.entries();
			while(enu.hasMoreElements()){
				JarEntry entry = (JarEntry)enu.nextElement();
				System.out.println(entry.getName());
			}*/
		 
		/*LogConfig.init();
		 DAOFactory.initDAOFactory("dao_config.properties");
		 DAOFactory.getDAO(IStepConfigMDAO.class);*/
			
			/*String[] str = "com/cattsoft/wm/component/dao/oracleImpl/TMsActionSDAOImpl.class".split("com/cattsoft/wm/componsdent/dao/oracleImpl/");
			System.out.println(str[0]);*/
			//System.out.println(str[1]);
			//System.out.println(str[1].substring(0,str[1].length()-6));
		 
	 }
	 
}
