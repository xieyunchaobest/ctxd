package com.cattsoft.pub.util;

import java.util.HashMap;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.SysException;

/**
 * 
 * <p>
 * Title: 初始化参数工具类
 * </p>
 * <p>
 * Description: 系统会用到的初始化参数放置在本类中。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: CATTSoft
 * </p>
 * 
 * @author wangyun
 * @version 1.0
 */

public abstract class SysInit {
	public final static String WEB_CONTEXT = "webContext";

	public final static String DBMAIN = "dbmain";

	public final static String DBTYPE = "dbtype";

	public final static String IF_INS_NEW_HREF = "ifInsNewHref";

	public final static String OS_TYPE = "osType";

	public final static String USE_HREF_AUTH = "useHrefAuth";

	public final static String STARTUP_SYM = "startupSym";

	public final static String IS_EJB_LOCAL = "ejblocal";

	public final static String EJB_LOCAL = "1";

	public final static String EJB_REMOTE = "0";

	// 2005-7-10 mjh 组织机构管理模式。A:按服务区分割。L:按层级管理，顶层为当前员工的组织。L型在河北客户回访用到。
	public final static String ORG_MNG_MODE = "orgMngMode";

	private static HashMap hm;

	public static boolean isNull() {
		if (hm == null)
			return true;
		return false;
	}

	/**
	 * 得到本类的实例。单例模式。
	 */
	public static void instance() {
		if (hm != null)
			return;
		hm = new HashMap();
		hm.put(WEB_CONTEXT, "/web");
		// ejblocal参数表示系统中使用local接口调用ejb还是remote接口调用。
		// hm.put(WEB_CONTEXT, "");
		String isEjbRemote  = EJB_REMOTE;
		try {
			isEjbRemote = SysConfigData.getSysConfigCurValue(
					SysConstants.SYS_CONFIG_IS_EJB_REMOTE, null, null, null, null, null);
			if("".equals(isEjbRemote)||null==isEjbRemote){
				isEjbRemote  = EJB_REMOTE;
			}
		} catch (SysException e) {
			e.printStackTrace();
		}
		hm.put(IS_EJB_LOCAL, isEjbRemote); // 0 is flase;
		hm.put("dbmain", "ds/hbsps");
		hm.put("dbtype", "oracle");
	}

	/**
	 * 得到本类的实例。单例模式。
	 */
	public static void instance(HashMap hm1) {
		if (hm1 == null)
			return;
		hm = hm1;
	}

	/**
	 * 根据错误号取得错误信息
	 * 
	 * @param errId
	 *            String 错误号
	 * @return String 错误信息
	 */
	public static String getRef(String refId) {
		if (hm == null)
			return "系统参数整体未初始化！";
		String str = (String) hm.get(refId);
		if (str == null) {

			return "该配置项为空！";

		}
		return str;
	}
}
