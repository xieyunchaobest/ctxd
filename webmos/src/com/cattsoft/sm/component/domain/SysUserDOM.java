package com.cattsoft.sm.component.domain;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.PasswordUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.util.SysConfigData;
import com.cattsoft.pub.util.SysDate;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.component.dao.IAppInfoSDAO;
import com.cattsoft.sm.component.dao.IFuncNodeMDAO;
import com.cattsoft.sm.component.dao.ILoginLogSDAO;
import com.cattsoft.sm.component.dao.IMosFuncNodeSDAO;
import com.cattsoft.sm.component.dao.IPermissionAppSDAO;
import com.cattsoft.sm.component.dao.IStaffMDAO;
import com.cattsoft.sm.component.dao.ISysConfigSDAO;
import com.cattsoft.sm.component.dao.ISysUserMDAO;
import com.cattsoft.sm.component.dao.ISysUserSDAO;
import com.cattsoft.sm.component.dao.IWorkAreaMDAO;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.AppInfoSVO;
import com.cattsoft.sm.vo.ConstraintAndPriviledgeSVO;
import com.cattsoft.sm.vo.FuncNodeSVO;
import com.cattsoft.sm.vo.FuncNodeTreeAllMVO;
import com.cattsoft.sm.vo.LoginLogSVO;
import com.cattsoft.sm.vo.MosFuncNodeSVO;
import com.cattsoft.sm.vo.PermissionAppSVO;
import com.cattsoft.sm.vo.StaffExtendMVO;
import com.cattsoft.sm.vo.SysConfigSVO;
import com.cattsoft.sm.vo.SysUserAllocSVO;
import com.cattsoft.sm.vo.SysUserExtMVO;
import com.cattsoft.sm.vo.SysUserExtendedMVO;
import com.cattsoft.sm.vo.SysUserMVO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.sm.vo.WorkAreaMVO;
import com.cattsoft.sm.vo.WorkAreaSVO;
import com.cattsoft.webpub.util.SysDateUtil;
import com.linkage.component.bean.adm.Encryptor;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SysUserDOM {

    private static Logger log = Logger.getLogger(SysUserDOM.class);

    private ISysUserMDAO sysUserMDao = (ISysUserMDAO) DAOFactory.getInstance().getDAO(
            ISysUserMDAO.class);

    private IFuncNodeMDAO funcNodeMDao = (IFuncNodeMDAO) DAOFactory.getInstance().getDAO(
            IFuncNodeMDAO.class);

    private IStaffMDAO staffMDao = (IStaffMDAO) DAOFactory.getInstance().getDAO(IStaffMDAO.class);

    private ILoginLogSDAO loginLogSDao = (ILoginLogSDAO) DAOFactory.getInstance().getDAO(
            ILoginLogSDAO.class);

    private ISysUserSDAO sysUserSDao = (ISysUserSDAO) DAOFactory.getInstance().getDAO(
            ISysUserSDAO.class);
 
    private ISysConfigSDAO sysConfigDao = (ISysConfigSDAO) DAOFactory.getInstance().getDAO(
            ISysConfigSDAO.class);

    private IPermissionAppSDAO permissAppDao = (IPermissionAppSDAO) DAOFactory.getInstance().getDAO(
    		IPermissionAppSDAO.class);
    
    private IAppInfoSDAO appInfoDao = (IAppInfoSDAO) DAOFactory.getInstance().getDAO(
    		IAppInfoSDAO.class);
    
    //SSO 登陆验证
    public Map osslogin(SysUserMVO vo, String localNetId) throws SysException, AppException, SQLException{
    	 SysUserExtendedMVO retVO = new SysUserExtendedMVO();
    	 Map resultMap = new HashMap();
    	 SysUserSVO sysUserVO = new SysUserSVO();
         sysUserVO.setSysUserName(vo.getSysUserName());
         sysUserVO.setPassword(vo.getPassword());
         //List list = sysUserSDao.findByVO(sysUserVO);
         //用户名不区分大小写 add by zhaodan 20100121
         List list = sysUserMDao.findByVOIgnorecase(sysUserVO);
         if (list != null && list.size() > 0) {
             sysUserVO = (SysUserSVO) list.get(0); 
             retVO.setSysUserSVO(sysUserVO);
         } else {
             retVO.setPasswordValid(1);
             resultMap.put("sysUserExtendedMVO", retVO);// 用户名或密码错误
             return resultMap;
         }
          
         // 判断多点登陆
         int size = 0;
//         // 判断有效用户
         size = sysUserMDao.findSysUsersSts(vo.getSysUserName());
         if (size >= 1) {
             retVO.setUserValid(size);
             resultMap.put("sysUserExtendedMVO", retVO); 
             return resultMap;
         }
//         
         /*
          * 同步CRM初始化密码之后必须更新密码才能进行登陆
          * add by gongzhen
          */
         //取sys_config中的密码12ab!@
//         SysConfigSVO sysConfigSVO2 = new SysConfigSVO();
//         sysConfigSVO2.setConfigId("21002");
         String pwdSysConfig = null;
         try {
         	pwdSysConfig = SysConfigData
				.getSysConfigCurValue(SysConstants.SYS_CONFIG_FIRST_PASSWORD, null,null, null, null, null);
         	if(StringUtil.isBlank(pwdSysConfig)){
         		throw new AppException("1000000", "读取缓存的sys_config中配置的默认密码时候，数据时取不到，空指针！");
         	}
         } catch (Exception e2) {
             throw new SysException("1000000", "缓存取数据错误", e2);
         }
         try {
        	//added by yangkai 2009-8-26 加密算法选择，如果是N或没有配置则使用MD5算法，否则为联创提供的算法
        	String flag=SysConfigData.getSysConfigCurValue("110010", null,null, null, null, null);
        	if(flag==null ||flag.equals(SysConstants.PWD_FLAG)){
        		if (PasswordUtil.isEqual(pwdSysConfig,sysUserVO.getPassword())){
                    retVO.setFirstFlag(-1);
                    resultMap.put("sysUserExtendedMVO", retVO); 
                    return resultMap;// 用户的密码已经初始化，要求强制修改密码
                }        		
        	}else{
        		if(Encryptor.fnEncrypt(pwdSysConfig,"00linkage").equalsIgnoreCase(sysUserVO.getPassword())){
        			retVO.setFirstFlag(-1);
                    resultMap.put("sysUserExtendedMVO", retVO); 
                    return resultMap;// 用户的密码已经初始化，要求强制修改密码
        		}
        	}   	
             
         } catch (NoSuchAlgorithmException e3) {
             throw new SysException("1000000", "对比密码出错", e3);
         }
         
         
         if (sysUserVO.getUpdatePwdTime() == null) {
             retVO.setFirstFlag(-1);
             resultMap.put("sysUserExtendedMVO", retVO); 
             return resultMap; // 第一次登陆没有修改密码重新登陆
         } else {
             String[] strDay = new String[2];
             SysConfigSVO sysConfigSVO1 = new SysConfigSVO();
             for (int i = 0; i < 2; i++) {
                 if (i == 0)
                     sysConfigSVO1.setConfigId(Constant.SYS_CONFIG_PW_DATE);
                 if (i == 1)
                     sysConfigSVO1.setConfigId(Constant.SYS_CONFIG_PW_TX);
                 sysConfigSVO1 = sysConfigDao.findSysConfigByPK(sysConfigSVO1);
                 if (sysConfigSVO1 != null)
                     strDay[i] = sysConfigSVO1.getCurValue();
                 else
                     break;
             }
             if (strDay[0] != null) {
                 GregorianCalendar c1 = new GregorianCalendar();
                 c1.setTime(sysUserVO.getUpdatePwdTime());
                 GregorianCalendar c2 = new GregorianCalendar();
                 c2.setTimeInMillis(System.currentTimeMillis());

                 double timeLong = c2.getTimeInMillis() - c1.getTimeInMillis();
                 int dayNum = (int) (((timeLong / 1000) / 3600) / 24);
                 if (dayNum > Integer.parseInt(strDay[0])) {
                     // 密码过期
                     retVO.setPasswordValid(2);
                     resultMap.put("sysUserExtendedMVO", retVO); 
                     return resultMap;
                 } else {
                     if (strDay[1] != null
                             && Integer.parseInt(strDay[1]) > Integer.parseInt(strDay[0])
                                     - dayNum) {
                         // 密码超过提醒日期
                         retVO.setPasswordDay(String.valueOf(Integer.parseInt(strDay[0])
                                 - dayNum));
                     }
                 }
             }

         }
         LoginLogSVO ll = null;
         if (vo.getLoginLogs() != null && vo.getLoginLogs().size() > 0) {
             if (log.isDebugEnabled())
                 log.debug("登录成功后，开始记录登录日志。");
             Object[] obj = vo.getLoginLogs().toArray(); // 日志vo通过set传递
             ll = (LoginLogSVO) obj[0];
             // 登录日志编号
             ll.setLoginId(MaxId.getSequenceNextVal(Constant.SEQ_LOGIN_ID));
             ll.setSysUserId(sysUserVO.getSysUserId());
             if (size < 1) {
                 ll.setLoginTime(SysDateUtil.getCurrentTimestamp());
             }
             ll.setLocalNetId(sysUserVO.getLocalNetId());
             loginLogSDao.add(ll);
             
             if (log.isDebugEnabled())
                 log.debug("登录日志记录完毕!");
             resultMap.put("LOGINID", ll.getLoginId());
         } else {
             if (log.isDebugEnabled())
                 log.debug("通过不登录系统访问系统链接的接口进来的。");
         }
         retVO.setLoginId(ll.getLoginId());
         
         PermissionAppSVO perAppSVO = new PermissionAppSVO();
         perAppSVO.setCommonId(sysUserVO.getSysUserId());
         perAppSVO.setLocalNetId(sysUserVO.getLocalNetId());
         perAppSVO.setSts(SysConstants.USE_YES_STS);
         List permissList = permissAppDao.findByVO(perAppSVO);  
         StringBuffer permissAppStr = new StringBuffer();
         List appInfoList  = null;
         StringBuffer appInfoURL = new StringBuffer();
         String defaultAppId = null;
         String defaultAppURL = null;
         String defaultApp = null;
         if (permissList!=null){
        	 AppInfoSVO appInfoVo = new AppInfoSVO();
        	 appInfoVo.setSts(SysConstants.USE_YES_STS); 
        	 appInfoList = appInfoDao.findByVO(appInfoVo);        	 
             
	         for(int i=0;i<permissList.size();i++){
	      	   perAppSVO = (PermissionAppSVO)permissList.get(i); 
	      	   permissAppStr.append(perAppSVO.getAppId()+":"+perAppSVO.getPermission()+";"); 
	      	   if (SysConstants.SSO_DEFAULT_APP.equals(perAppSVO.getDefaultApp())){
	      		 defaultAppId = perAppSVO.getAppId();
	      	   }
	         }  
	         if (appInfoList!=null){ 
	 			 for (int i =0;i<appInfoList.size();i++){ 				
	 				appInfoVo = (AppInfoSVO)appInfoList.get(i); 
	 				appInfoURL.append(appInfoVo.getAppId()).append(",").append(appInfoVo.getUrl()+";");
	 				if (appInfoVo.getAppId().equals(defaultAppId)){
	 					defaultAppURL = appInfoVo.getUrl();
	 					defaultApp = appInfoVo.getAppId() ;
	 				} 
	 			 }
	 			if (defaultAppURL ==null){
 					appInfoVo = (AppInfoSVO)appInfoList.get(0); 
 					defaultAppURL = appInfoVo.getUrl();
 					defaultApp = appInfoVo.getAppId() ;
 				}
	         }
 			 
         } 
         retVO.setPermissAppStr(permissAppStr.toString());
         retVO.setAppInfoURLStr(appInfoURL.toString());
         retVO.setDefaultApp(defaultApp);
         resultMap.put("DEFAULTAPPURL",defaultAppURL); 
         resultMap.put("sysUserExtendedMVO", retVO);
         return resultMap;
    }
    
    public SysUserExtendedMVO login(SysUserSVO sysUserVO,SysUserExtendedMVO suve) throws SysException, AppException, SQLException {
    	 SysUserExtendedMVO retVO = suve;
        try { 
            //用户名不区分大小写 add by xingzhenfeng 2013.9.19
            SysUserSVO sysUserVO1 = new SysUserSVO();
             sysUserVO1.setSysUserName(sysUserVO.getSysUserName());
             sysUserVO1.setPassword(sysUserVO.getPassword());
            List list = sysUserMDao.findByVOIgnorecase(sysUserVO);
            if (list != null && list.size() > 0) {
                sysUserVO = (SysUserSVO) list.get(0);
            } else {
                retVO.setPasswordValid(1);
                return retVO;// 用户名或密码错误
            }
            StaffExtendMVO staffMVO = new StaffExtendMVO();

            if (sysUserVO != null) { 
                // 得到用户可登录的功能点。
                List funcNodeVOs = funcNodeMDao.findFuncNodeForLogin(sysUserVO.getSysUserId(),sysUserVO.getLocalNetId());
                retVO.setFuncNodeAll(funcNodeVOs);
                log.debug(funcNodeVOs);
                LoginAuthorDOM dom = new LoginAuthorDOM();
                List fntvs = dom.authUserSubSystemFuncNodeTree(funcNodeVOs);
                log.debug(fntvs);
                retVO.setFuncNodeTreeVOs(fntvs);
                // 得到用户对应参与这角色信息，现在仅判断是否为员工角色。7为员工的参与者角色类型。
                if (sysUserVO.getPartyRoleTypeId().equals(Constant.PARTY_ROLE_TYPE_STAFF)) {
                    staffMVO = staffMDao.findStaffExtendMVO(sysUserVO.getPartyRoleId()); 
                    log.debug(staffMVO.getLocalNetId());
                    retVO.setStaffExtendMVO(staffMVO);
                    // 得到员工可访问的工作区(先暂时做成这样,应付服务开通需求)
                    /*List workAreas = workAreaMDao.findSingleWorkAreasBySystemStaff(
                            new WorkAreaSVO(), null, staffMVO.getStaffSVO().getStaffId());
                    log.debug(list);
                    retVO.setStaffWorkAreas(workAreas);*/
                }
            }
        } catch (AppException e) {
            throw new AppException("", "sysuserDOM.login");
        } catch (SysException e) {
            throw new SysException("", "sysUserDOM.login", e);
        }
        return retVO;
    }
    
    /**
     * OSS登录
     * @param sysUserVO
     * @param suve
     * @return
     * @throws SysException
     * @throws AppException
     * @throws SQLException
     */
    public SysUserExtendedMVO osslogin(SysUserSVO sysUserVO,SysUserExtendedMVO suve) throws SysException, AppException, SQLException {
   	 SysUserExtendedMVO retVO = suve;
       try { 
           //用户名不区分大小写 add by xingzhenfeng 2013.9.19
           SysUserSVO sysUserVO1 = new SysUserSVO();
            sysUserVO1.setSysUserName(sysUserVO.getSysUserName());
            sysUserVO1.setPassword(sysUserVO.getPassword());
           List list = sysUserMDao.findByVOIgnorecase(sysUserVO);
           if (list != null && list.size() > 0) {
               sysUserVO = (SysUserSVO) list.get(0);
           } else {
               retVO.setPasswordValid(1);
               return retVO;// 用户名或密码错误
           }
           StaffExtendMVO staffMVO = new StaffExtendMVO();

           if (sysUserVO != null) { 
               // 得到用户可登录的功能点。
               List funcNodeVOs = funcNodeMDao.findFuncNodeForOssLogin(sysUserVO.getSysUserId(),sysUserVO.getLocalNetId());
               retVO.setFuncNodeAll(funcNodeVOs);
               log.debug(funcNodeVOs);
               LoginAuthorDOM dom = new LoginAuthorDOM();
               List fntvs = dom.authUserSubSystemFuncNodeTree(funcNodeVOs);
               log.debug(fntvs);
               retVO.setFuncNodeTreeVOs(fntvs);
               // 得到用户对应参与这角色信息，现在仅判断是否为员工角色。7为员工的参与者角色类型。
               if (sysUserVO.getPartyRoleTypeId().equals(Constant.PARTY_ROLE_TYPE_STAFF)) {
                   staffMVO = staffMDao.findStaffExtendMVO(sysUserVO.getPartyRoleId()); 
                   log.debug(staffMVO.getLocalNetId());
                   retVO.setStaffExtendMVO(staffMVO);
                   // 得到员工可访问的工作区(先暂时做成这样,应付服务开通需求)
                   /*List workAreas = workAreaMDao.findSingleWorkAreasBySystemStaff(
                           new WorkAreaSVO(), null, staffMVO.getStaffSVO().getStaffId());
                   log.debug(list);
                   retVO.setStaffWorkAreas(workAreas);*/
               }
           }
       } catch (AppException e) {
           throw new AppException("", "sysuserDOM.login");
       } catch (SysException e) {
           throw new SysException("", "sysUserDOM.login", e);
       }
       return retVO;
   }
    
    
    /**
     * MOS native 登录
     * @param voJson
     * @return
     * @throws SysException
     * @throws AppException
     * @throws SQLException
     */
    public String login4MOS(String voJson) throws SysException, AppException, SQLException{
    	JSONObject voJsonObject=JSONObject.fromObject(voJson);
    	JSONObject suveJsonObject=new JSONObject();    	
        try {
            SysUserSVO sysUserVO = new SysUserSVO();
            JSONObject sysUserSVOJsonObject=new JSONObject();
            sysUserVO.setSysUserName(voJsonObject.getString("sysUserName"));
            sysUserVO.setPassword(voJsonObject.getString("password"));
            //List list = sysUserSDao.findByVO(sysUserVO);
            //用户名不区分大小写 add by zhaodan
            List list = sysUserMDao.findByVOIgnorecase(sysUserVO);
            if (list != null && list.size() > 0) {
                sysUserVO = (SysUserSVO) list.get(0);
            } else {
                suveJsonObject.put("passwordValid", new Integer(1));
                suveJsonObject.put("firstFlag", new Integer(0));  
                suveJsonObject.put("userValid", new Integer(0));
                return suveJsonObject.toString();// 用户名或密码错误
            }
            StaffExtendMVO staffMVO = new StaffExtendMVO();
            JSONObject staffSVOJsonObject=new JSONObject();
            JSONObject staffMVOJsonObject=new JSONObject();
            
            if (sysUserVO != null) {
            	sysUserSVOJsonObject.put("sysUserId", sysUserVO.getSysUserId());
            	sysUserSVOJsonObject.put("partyRoleTypeId",sysUserVO.getPartyRoleTypeId());
            	sysUserSVOJsonObject.put("partyRoleId", sysUserVO.getPartyRoleId());
            	sysUserSVOJsonObject.put("sysUserName", sysUserVO.getSysUserName());
            	sysUserSVOJsonObject.put("password", sysUserVO.getPassword());
            	sysUserSVOJsonObject.put("setPwdTime", sysUserVO.getSetPwdTime());
            	sysUserSVOJsonObject.put("updatePwdTime",sysUserVO.getUpdatePwdTime());
            	sysUserSVOJsonObject.put("lastPwd", sysUserVO.getLastPwd());
            	sysUserSVOJsonObject.put("createDate", sysUserVO.getCreateDate());
            	sysUserSVOJsonObject.put("sts", sysUserVO.getSts());
            	sysUserSVOJsonObject.put("stsDate", sysUserVO.getStsDate());
            	sysUserSVOJsonObject.put("localNetId", sysUserVO.getLocalNetId());
                
            	suveJsonObject.put("sysUserSVO", sysUserSVOJsonObject);
            	
                // 得到用户对应参与这角色信息，现在仅判断是否为员工角色。7为员工的参与者角色类型。
                if (sysUserVO.getPartyRoleTypeId().equals(Constant.PARTY_ROLE_TYPE_STAFF)) {
                    staffMVO = staffMDao.findStaffExtendMVO(sysUserVO.getPartyRoleId());

                    staffSVOJsonObject.put("createDate", staffMVO.getStaffSVO().getCreateDate());
                    staffSVOJsonObject.put("deptId", staffMVO.getStaffSVO().getDeptId());
                    staffSVOJsonObject.put("partyId", staffMVO.getStaffSVO().getPartyId());
                    staffSVOJsonObject.put("position", staffMVO.getStaffSVO().getPosition());
                    staffSVOJsonObject.put("staffId", staffMVO.getStaffSVO().getStaffId());
                    staffSVOJsonObject.put("standardCode", staffMVO.getStaffSVO().getStandardCode());
                    staffSVOJsonObject.put("sts", staffMVO.getStaffSVO().getSts());
                    staffSVOJsonObject.put("stsDate", staffMVO.getStaffSVO().getStsDate());
                    staffSVOJsonObject.put("companyCode", staffMVO.getStaffSVO().getCompanyCode());
                    staffSVOJsonObject.put("deptType", staffMVO.getStaffSVO().getDeptType());
                    staffSVOJsonObject.put("telNbr", staffMVO.getStaffSVO().getTelNbr());
                    
                    staffMVOJsonObject.put("staffSVO", staffSVOJsonObject);
                    
                    staffMVOJsonObject.put("partyId", staffMVO.getPartyId());
                    staffMVOJsonObject.put("partyName", staffMVO.getPartyName());
                    staffMVOJsonObject.put("partySts", staffMVO.getPartySts());
                    staffMVOJsonObject.put("partyType", staffMVO.getPartyType());
                    staffMVOJsonObject.put("localNetId", staffMVO.getLocalNetId());
                    staffMVOJsonObject.put("localNetName", staffMVO.getLocalNetName());
                    staffMVOJsonObject.put("localNetIscenter", staffMVO.getLocalNetIscenter());
                    staffMVOJsonObject.put("areaId", staffMVO.getAreaId());
                    staffMVOJsonObject.put("areaName", staffMVO.getAreaName());
                    staffMVOJsonObject.put("areaIscenter", staffMVO.getAreaIscenter());
                    staffMVOJsonObject.put("servDeptId", staffMVO.getServDeptId());
                    staffMVOJsonObject.put("servDeptName", staffMVO.getServDeptName());
                    staffMVOJsonObject.put("branchId", staffMVO.getBranchId());
                    staffMVOJsonObject.put("branchName", staffMVO.getBranchName());
 
                    log.debug("sssss" + staffMVO);
                    log.debug(staffMVO.getLocalNetId());
              
                    suveJsonObject.put("staffExtendMVO", staffMVOJsonObject);
                    
                    // 得到员工可访问的工作区(先暂时做成这样,应付服务开通需求)
                    /*List workAreas = workAreaMDao.findSingleWorkAreasBySystemStaff(
                            new WorkAreaSVO(), null, staffMVO.getStaffSVO().getStaffId());
                    log.debug(list);
                    retVO.setStaffWorkAreas(workAreas);*/
                }
              //add by xieyunchao 20130926,获取掌上运维相关的权限，用于控制MOS首页和工单详情底部按钮
                List funcNodeVOs = funcNodeMDao.findFuncNodeForLogin4MOS(sysUserVO.getSysUserId(),sysUserVO.getLocalNetId());
                List convertedMosFuncList=this.convertIOMFunctionToMosFuncNode(funcNodeVOs);
                if(convertedMosFuncList!=null) {
                	suveJsonObject.put("mosFuncNodeList", com.alibaba.fastjson.JSONObject.toJSONString(convertedMosFuncList)); 
                }

            }
            // 判断多点登陆
            int size = 0;
            // 判断有效用户
            size = sysUserMDao.findSysUsersSts(voJsonObject.getString("sysUserName"));
            if (size >= 1) {
                //retVO.setUserValid(size);
                suveJsonObject.put("userValid", new Integer(size));
                suveJsonObject.put("passwordValid", new Integer(0));
                suveJsonObject.put("firstFlag", new Integer(0));
                return suveJsonObject.toString();
            }
            
            /*
             * 同步CRM初始化密码之后必须更新密码才能进行登陆
             * add by gongzhen
             */
            //取sys_config中的密码12ab!@
//            SysConfigSVO sysConfigSVO2 = new SysConfigSVO();
//            sysConfigSVO2.setConfigId("21002");
            String pwdSysConfig = null;
            try {
            	pwdSysConfig = SysConfigData
				.getSysConfigCurValue(SysConstants.SYS_CONFIG_FIRST_PASSWORD, null,null, null, null, null);
            	if(StringUtil.isBlank(pwdSysConfig)){
            		throw new AppException("1000000", "读取缓存的sys_config中配置的默认密码时候，数据时取不到，空指针！");
            	}
            } catch (Exception e2) {
                throw new SysException("1000000", "缓存取数据错误", e2);
            }
            try {
                if (PasswordUtil.isEqual(pwdSysConfig,sysUserVO.getPassword())){
                    suveJsonObject.put("firstFlag", new Integer(-1));
                    suveJsonObject.put("passwordValid", new Integer(0));
                    suveJsonObject.put("userValid", new Integer(0));
                    return suveJsonObject.toString(); // 用户的密码已经初始化，要求强制修改密码
                }
            } catch (NoSuchAlgorithmException e3) {
                throw new SysException("1000000", "对比密码出错", e3);
            }
            
            
            if (sysUserVO.getUpdatePwdTime() == null) {
                suveJsonObject.put("firstFlag", new Integer(-1));
                suveJsonObject.put("passwordValid", new Integer(0));  
                suveJsonObject.put("userValid", new Integer(0));
                return suveJsonObject.toString(); // 第一次登陆没有修改密码重新登陆
            } else {
                String[] strDay = new String[2];
                SysConfigSVO sysConfigSVO1 = new SysConfigSVO();
                for (int i = 0; i < 2; i++) {
                    if (i == 0)
                        sysConfigSVO1.setConfigId(Constant.SYS_CONFIG_PW_DATE);
                    if (i == 1)
                        sysConfigSVO1.setConfigId(Constant.SYS_CONFIG_PW_TX);
                    sysConfigSVO1 = sysConfigDao.findSysConfigByPK(sysConfigSVO1);
                    if (sysConfigSVO1 != null)
                        strDay[i] = sysConfigSVO1.getCurValue();
                    else
                        break;
                }
                if (strDay[0] != null) {
                    GregorianCalendar c1 = new GregorianCalendar();
                    c1.setTime(sysUserVO.getUpdatePwdTime());
                    GregorianCalendar c2 = new GregorianCalendar();
                    c2.setTimeInMillis(System.currentTimeMillis());

                    double timeLong = c2.getTimeInMillis() - c1.getTimeInMillis();
                    int dayNum = (int) (((timeLong / 1000) / 3600) / 24);
                    if (dayNum > Integer.parseInt(strDay[0])) {
                        // 密码过期
                        suveJsonObject.put("passwordValid", new Integer(2));
                        suveJsonObject.put("firstFlag", new Integer(0));  
                        suveJsonObject.put("userValid", new Integer(0));
                        return suveJsonObject.toString();
                    } else {
                        if (strDay[1] != null
                                && Integer.parseInt(strDay[1]) > Integer.parseInt(strDay[0])
                                        - dayNum) {
                            // 密码超过提醒日期
                            suveJsonObject.put("passwordDay", String.valueOf(Integer.parseInt(strDay[0])
                                    - dayNum));
                        }
                    }
                }

            }
            JSONObject loginLogSVOJsonObject=voJsonObject.getJSONObject("loginLogs");
            LoginLogSVO ll = new LoginLogSVO();
            if (loginLogSVOJsonObject != null && loginLogSVOJsonObject.size() > 0) {
                if (log.isDebugEnabled())
                    log.debug("登录成功后，开始记录登录日志。");
                ll.setSysDeviceMac(loginLogSVOJsonObject.getString("sysDeviceMac"));
                ll.setIpAddr(loginLogSVOJsonObject.getString("ipAddr"));
                // 登录日志编号
                ll.setLoginId(MaxId.getSequenceNextVal(Constant.SEQ_LOGIN_ID));
                ll.setSysUserId(sysUserVO.getSysUserId());
                if (size < 1) {
                    ll.setLoginTime(SysDateUtil.getCurrentTimestamp());
                }
                ll.setLocalNetId(staffMVO.getLocalNetId());
                loginLogSDao.add(ll);
                if (log.isDebugEnabled())
                    log.debug("登录日志记录完毕!");
            } else {
                if (log.isDebugEnabled())
                    log.debug("通过不登录系统访问系统链接的接口进来的。");
            }
            suveJsonObject.put("LoginId", ll.getLoginId());

        } catch (AppException e) {

            throw new AppException("", "sysuserDOM.login");
        } catch (SysException e) {

            throw new SysException("", "sysUserDOM.login", e);
        }

        suveJsonObject.put("passwordValid", new Integer(0));
        suveJsonObject.put("firstFlag", new Integer(0));  
        suveJsonObject.put("userValid", new Integer(0));
    	return suveJsonObject.toString();
    }
    

    public SysUserExtendedMVO login(SysUserMVO vo) throws SysException, AppException, SQLException {

        SysUserExtendedMVO retVO = new SysUserExtendedMVO();
        try {
            SysUserSVO sysUserVO = new SysUserSVO();
            sysUserVO.setSysUserName(vo.getSysUserName());
            sysUserVO.setPassword(vo.getPassword());
            //List list = sysUserSDao.findByVO(sysUserVO);
            //用户名不区分大小写 add by zhaodan
            List list = sysUserMDao.findByVOIgnorecase(sysUserVO);
            if (list != null && list.size() > 0) {
                sysUserVO = (SysUserSVO) list.get(0);
            } else {
                retVO.setPasswordValid(1);
                return retVO;// 用户名或密码错误
            }
            StaffExtendMVO staffMVO = new StaffExtendMVO();

            if (sysUserVO != null) {
                retVO.setSysUserSVO(sysUserVO);
                // 得到用户可登录的功能点。
                List funcNodeVOs = funcNodeMDao.findFuncNodeForLogin(sysUserVO.getSysUserId(),sysUserVO.getLocalNetId());
                retVO.setFuncNodeAll(funcNodeVOs);
                // log.debug(funcNodeVOs);
				LoginAuthorDOM dom = new LoginAuthorDOM();
				List fntvs = dom.authUserSubSystemFuncNodeTree(funcNodeVOs);
				// log.debug(fntvs);
                retVO.setFuncNodeTreeVOs(fntvs);
                // 得到用户对应参与这角色信息，现在仅判断是否为员工角色。7为员工的参与者角色类型。
                if (sysUserVO.getPartyRoleTypeId().equals(Constant.PARTY_ROLE_TYPE_STAFF)) {
                    staffMVO = staffMDao.findStaffExtendMVO(sysUserVO.getPartyRoleId());
                    log.debug("sssss" + staffMVO);
                    log.debug(staffMVO.getLocalNetId());
                    retVO.setStaffExtendMVO(staffMVO);
                    // 得到员工可访问的工作区(先暂时做成这样,应付服务开通需求)
                    /*List workAreas = workAreaMDao.findSingleWorkAreasBySystemStaff(
                            new WorkAreaSVO(), null, staffMVO.getStaffSVO().getStaffId());
                    log.debug(list);
                    retVO.setStaffWorkAreas(workAreas);*/
                }
                 
            }
            // 判断多点登陆
            int size = 0;
            // 判断有效用户
            size = sysUserMDao.findSysUsersSts(vo.getSysUserName());
            if (size >= 1) {
                retVO.setUserValid(size);
                return retVO;
            }
            
            /*
             * 同步CRM初始化密码之后必须更新密码才能进行登陆
             * add by gongzhen
             */
            //取sys_config中的密码12ab!@
//            SysConfigSVO sysConfigSVO2 = new SysConfigSVO();
//            sysConfigSVO2.setConfigId("21002");
            String pwdSysConfig = null;
            try {
            	pwdSysConfig = SysConfigData
				.getSysConfigCurValue(SysConstants.SYS_CONFIG_FIRST_PASSWORD, null,null, null, null, null);
            	if(StringUtil.isBlank(pwdSysConfig)){
            		throw new AppException("1000000", "读取缓存的sys_config中配置的默认密码时候，数据时取不到，空指针！");
            	}
            } catch (Exception e2) {
                throw new SysException("1000000", "缓存取数据错误", e2);
            }
            try {
                if (PasswordUtil.isEqual(pwdSysConfig,sysUserVO.getPassword())){
                    retVO.setFirstFlag(-1);
                    return retVO; // 用户的密码已经初始化，要求强制修改密码
                }
            } catch (NoSuchAlgorithmException e3) {
                throw new SysException("1000000", "对比密码出错", e3);
            }
            
            
            if (sysUserVO.getUpdatePwdTime() == null) {
                retVO.setFirstFlag(-1);
                return retVO; // 第一次登陆没有修改密码重新登陆
            } else {
                String[] strDay = new String[2];
                SysConfigSVO sysConfigSVO1 = new SysConfigSVO();
                for (int i = 0; i < 2; i++) {
                    if (i == 0)
                        sysConfigSVO1.setConfigId(Constant.SYS_CONFIG_PW_DATE);
                    if (i == 1)
                        sysConfigSVO1.setConfigId(Constant.SYS_CONFIG_PW_TX);
                    sysConfigSVO1 = sysConfigDao.findSysConfigByPK(sysConfigSVO1);
                    if (sysConfigSVO1 != null)
                        strDay[i] = sysConfigSVO1.getCurValue();
                    else
                        break;
                }
                if (strDay[0] != null) {
                    GregorianCalendar c1 = new GregorianCalendar();
                    c1.setTime(sysUserVO.getUpdatePwdTime());
                    GregorianCalendar c2 = new GregorianCalendar();
                    c2.setTimeInMillis(System.currentTimeMillis());

                    double timeLong = c2.getTimeInMillis() - c1.getTimeInMillis();
                    int dayNum = (int) (((timeLong / 1000) / 3600) / 24);
                    if (dayNum > Integer.parseInt(strDay[0])) {
                        // 密码过期
                        retVO.setPasswordValid(2);
                        return retVO;
                    } else {
                        if (strDay[1] != null
                                && Integer.parseInt(strDay[1]) > Integer.parseInt(strDay[0])
                                        - dayNum) {
                            // 密码超过提醒日期
                            retVO.setPasswordDay(String.valueOf(Integer.parseInt(strDay[0])
                                    - dayNum));
                        }
                    }
                }

            }
            LoginLogSVO ll = null;
            if (vo.getLoginLogs() != null && vo.getLoginLogs().size() > 0) {
                if (log.isDebugEnabled())
                    log.debug("登录成功后，开始记录登录日志。");
                Object[] obj = vo.getLoginLogs().toArray(); // 日志vo通过set传递
                ll = (LoginLogSVO) obj[0];
                // 登录日志编号
                ll.setLoginId(MaxId.getSequenceNextVal(Constant.SEQ_LOGIN_ID));
                ll.setSysUserId(sysUserVO.getSysUserId());
                if (size < 1) {
                    ll.setLoginTime(SysDateUtil.getCurrentTimestamp());
                }
                ll.setLocalNetId(staffMVO.getLocalNetId());
                loginLogSDao.add(ll);
                if (log.isDebugEnabled())
                    log.debug("登录日志记录完毕!");
            } else {
                if (log.isDebugEnabled())
                    log.debug("通过不登录系统访问系统链接的接口进来的。");
            }
            retVO.setLoginId(ll.getLoginId());

        } catch (AppException e) {

            throw new AppException("", "sysuserDOM.login");
        } catch (SysException e) {

            throw new SysException("", "sysUserDOM.login", e);
        }
        return retVO;
    }

    /**
     * 掌上运维 登录
     * @param paraMap
     * @return
     * @throws SysException
     * @throws AppException
     * @throws SQLException
     */
    public Map login4Mobile(Map paraMap) throws SysException, AppException, SQLException {
    	Map resultMap = new HashMap();
    	SysUserMVO vo = (SysUserMVO) paraMap.get(SysConstants.PARA_MAP_SYS_USER_MVO);
        SysUserExtendedMVO retVO = new SysUserExtendedMVO();
        try {
            SysUserSVO sysUserVO = new SysUserSVO();
            sysUserVO.setSysUserName(vo.getSysUserName());
            sysUserVO.setPassword(vo.getPassword());
            //List list = sysUserSDao.findByVO(sysUserVO);
            //用户名不区分大小写 add by zhaodan
            List list = sysUserMDao.findByVOIgnorecase(sysUserVO);
            if (list != null && list.size() > 0) {
                sysUserVO = (SysUserSVO) list.get(0);
            } else {
                retVO.setPasswordValid(1);
                resultMap.put(SysConstants.PARA_MAP_SYS_USER_EXTENDED_MVO, retVO);
                return resultMap;// 用户名或密码错误
            }
            StaffExtendMVO staffMVO = new StaffExtendMVO();

            if (sysUserVO != null) {
                retVO.setSysUserSVO(sysUserVO);
                // 得到用户可登录的功能点。
                List funcNodeVOs = funcNodeMDao.findFuncNodeForLogin(sysUserVO.getSysUserId(),sysUserVO.getLocalNetId());
                retVO.setFuncNodeAll(funcNodeVOs);
                // log.debug(funcNodeVOs);
				LoginAuthorDOM dom = new LoginAuthorDOM();
				List fntvs = dom.authUserSubSystemFuncNodeTree(funcNodeVOs);
				// log.debug(fntvs);
                retVO.setFuncNodeTreeVOs(fntvs);
                // 得到用户对应参与这角色信息，现在仅判断是否为员工角色。7为员工的参与者角色类型。
                if (sysUserVO.getPartyRoleTypeId().equals(Constant.PARTY_ROLE_TYPE_STAFF)) {
                    staffMVO = staffMDao.findStaffExtendMVO(sysUserVO.getPartyRoleId());
                    log.debug("sssss" + staffMVO);
                    log.debug(staffMVO.getLocalNetId());
                    retVO.setStaffExtendMVO(staffMVO);
                    // 得到员工可访问的工作区(先暂时做成这样,应付服务开通需求)
                    /*List workAreas = workAreaMDao.findSingleWorkAreasBySystemStaff(
                            new WorkAreaSVO(), null, staffMVO.getStaffSVO().getStaffId());
                    log.debug(list);
                    retVO.setStaffWorkAreas(workAreas);*/
                }
                 
            }
            // 判断多点登陆
            int size = 0;
            // 判断有效用户
            size = sysUserMDao.findSysUsersSts(vo.getSysUserName());
            if (size >= 1) {
                retVO.setUserValid(size);
                resultMap.put(SysConstants.PARA_MAP_SYS_USER_EXTENDED_MVO, retVO);
                return resultMap;
            }
            
            /*
             * 同步CRM初始化密码之后必须更新密码才能进行登陆
             * add by gongzhen
             */
            //取sys_config中的密码12ab!@
//            SysConfigSVO sysConfigSVO2 = new SysConfigSVO();
//            sysConfigSVO2.setConfigId("21002");
            String pwdSysConfig = null;
            try {
            	pwdSysConfig = SysConfigData
				.getSysConfigCurValue(SysConstants.SYS_CONFIG_FIRST_PASSWORD, null,null, null, null, null);
            	if(StringUtil.isBlank(pwdSysConfig)){
            		throw new AppException("1000000", "读取缓存的sys_config中配置的默认密码时候，数据时取不到，空指针！");
            	}
            } catch (Exception e2) {
                throw new SysException("1000000", "缓存取数据错误", e2);
            }
            try {
                if (PasswordUtil.isEqual(pwdSysConfig,sysUserVO.getPassword())){
                    retVO.setFirstFlag(-1);
                    resultMap.put(SysConstants.PARA_MAP_SYS_USER_EXTENDED_MVO, retVO);
                    return resultMap; // 用户的密码已经初始化，要求强制修改密码
                }
            } catch (NoSuchAlgorithmException e3) {
                throw new SysException("1000000", "对比密码出错", e3);
            }
            
            
            if (sysUserVO.getUpdatePwdTime() == null) {
                retVO.setFirstFlag(-1);
                resultMap.put(SysConstants.PARA_MAP_SYS_USER_EXTENDED_MVO, retVO);
                return resultMap; // 第一次登陆没有修改密码重新登陆
            } else {
                String[] strDay = new String[2];
                SysConfigSVO sysConfigSVO1 = new SysConfigSVO();
                for (int i = 0; i < 2; i++) {
                    if (i == 0)
                        sysConfigSVO1.setConfigId(Constant.SYS_CONFIG_PW_DATE);
                    if (i == 1)
                        sysConfigSVO1.setConfigId(Constant.SYS_CONFIG_PW_TX);
                    sysConfigSVO1 = sysConfigDao.findSysConfigByPK(sysConfigSVO1);
                    if (sysConfigSVO1 != null)
                        strDay[i] = sysConfigSVO1.getCurValue();
                    else
                        break;
                }
                if (strDay[0] != null) {
                    GregorianCalendar c1 = new GregorianCalendar();
                    c1.setTime(sysUserVO.getUpdatePwdTime());
                    GregorianCalendar c2 = new GregorianCalendar();
                    c2.setTimeInMillis(System.currentTimeMillis());

                    double timeLong = c2.getTimeInMillis() - c1.getTimeInMillis();
                    int dayNum = (int) (((timeLong / 1000) / 3600) / 24);
                    if (dayNum > Integer.parseInt(strDay[0])) {
                        // 密码过期
                        retVO.setPasswordValid(2);
                        resultMap.put(SysConstants.PARA_MAP_SYS_USER_EXTENDED_MVO, retVO);
                        return resultMap;
                    } else {
                        if (strDay[1] != null
                                && Integer.parseInt(strDay[1]) > Integer.parseInt(strDay[0])
                                        - dayNum) {
                            // 密码超过提醒日期
                            retVO.setPasswordDay(String.valueOf(Integer.parseInt(strDay[0])
                                    - dayNum));
                        }
                    }
                }

            }
            LoginLogSVO ll = null;
            if (vo.getLoginLogs() != null && vo.getLoginLogs().size() > 0) {
                if (log.isDebugEnabled())
                    log.debug("登录成功后，开始记录登录日志。");
                Object[] obj = vo.getLoginLogs().toArray(); // 日志vo通过set传递
                ll = (LoginLogSVO) obj[0];
                // 登录日志编号
                ll.setLoginId(MaxId.getSequenceNextVal(Constant.SEQ_LOGIN_ID));
                ll.setSysUserId(sysUserVO.getSysUserId());
                if (size < 1) {
                    ll.setLoginTime(SysDateUtil.getCurrentTimestamp());
                }
                ll.setLocalNetId(staffMVO.getLocalNetId());
                loginLogSDao.add(ll);
                if (log.isDebugEnabled())
                    log.debug("登录日志记录完毕!");
            } else {
                if (log.isDebugEnabled())
                    log.debug("通过不登录系统访问系统链接的接口进来的。");
            }
            retVO.setLoginId(ll.getLoginId());

        } catch (AppException e) {

            throw new AppException("", "sysuserDOM.login");
        } catch (SysException e) {

            throw new SysException("", "sysUserDOM.login", e);
        }
        
        resultMap.put(SysConstants.PARA_MAP_SYS_USER_EXTENDED_MVO, retVO);
        
        return resultMap;
    }
    
    /**
     * 根据单表SysUserSDAO查询系统用户信息
     * 
     * @param vo
     */
    public SysUserSVO getSysUser(SysUserSVO vo) throws AppException, SysException {
        SysUserSVO voRet = null;
        List list = sysUserSDao.findByVO(vo);
        if (list != null) {
            Iterator it = list.iterator();
            if (it.hasNext()) {
                voRet = (SysUserSVO) it.next();
            }

        }
        return voRet;

    }

    /**
     * 根据主键修改系统用户密码
     * 
     * @param vo
     */
    public void modSysUser(SysUserSVO vo) throws SysException, AppException {
    	 
        SysUserSVO votmp = (SysUserSVO) sysUserSDao.findByPK(vo);
        vo.setSysUserName(votmp.getSysUserName());

        if (votmp.getPassword() != null && !votmp.getPassword().equals(vo)) { // 密码变了，说明要修改密码
            vo.setUpdatePwdTime(SysDate.getCurrentTimestamp());
            vo.setLastPwd(votmp.getPassword());
            sysUserSDao.update(vo);

            // 员工密码修改,同步资源
//            StaffMVO mvo = staffMDao.findStaffMemberByStaffId(votmp.getPartyRoleId());
//            mvo.setSysUserId(vo.getSysUserId());
//            mvo.setPassWord(vo.getPassword());
//            mvo.getStaffSVO().setStsDate(SysDateUtil.getCurrentTimestamp());
//            String[] backStr = staffMDao.updateStaffMVOByRs(mvo, Constant.STAFF_ACT_TYPE_R);
//            if (backStr[0].equals("-1")) {
//                throw new AppException("3333", "同步资源出错,原因为:" + backStr[1]);
//            }
            // 同步资源
          //added by yangkai 添加修改记录
            if (vo!=null&&vo.getActionLog()!=null){
            	 ActionLogSVO actionLog=vo.getActionLog();
                 actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
                 IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
                 actionLogDao.add(actionLog);
            }
           
        }

    }

    public SysUserSVO findSysUserByPK(SysUserSVO vo) throws AppException, SysException {

        SysUserSVO voRet = (SysUserSVO) sysUserSDao.findByPK(vo);

        return voRet;

    }

    /**
     * get funcNodeTreeAllVO according system. the funcNodeTreeAllVO include funcNodeVOs whose type
     * is 'M' and include funcNodeTreeVOs.
     * 
     * @param sysUserId
     *            Long
     * @param subSystemName
     *            String
     * @param localNetId
     *            int
     * @throws AppException
     * @throws SysException
     * @return FuncNodeTreeAllVO
     */
    public FuncNodeTreeAllMVO getFuncNodeTreeAllVO(String sysUserId, String subSystemName)
            throws AppException, SysException {
        FuncNodeTreeAllMVO vo = new FuncNodeTreeAllMVO();

        try {

            LoginAuthorDOM dom = new LoginAuthorDOM();
            log.debug("subSystemName:" + subSystemName);
            List fnvs = funcNodeMDao.findFuncNodeForMenu(sysUserId, subSystemName);
            vo.setFuncNodeVOs(fnvs);
            List fntvs = dom.authUserSubSystemFuncNodeTree(fnvs);
            vo.setFuncNodeTreeVOs(fntvs);
            if (log.isDebugEnabled()) {
                log.debug("功能菜单树个数为:" + fntvs.size());
                log.debug("功能点数目为：" + fnvs.size());
            }
        } catch (AppException ae) {
        	throw new AppException("520002", "应用出错："+ae.getMessage());
        } catch (SysException se) {
        	throw new SysException("1000001","系统异常："+se.getMessage(),se); 	
        }

        return vo;
    }

    /**
     * 分页查询用户
     * 
     * @param ovo
     * 
     * @param pagInfo
     * @throws SysException
     * @throws AppException
     * @return pagview
     */
    public PagView findSysUsersBySysUserForPage(SysUserExtMVO voExt, PagInfo pagInfo)
            throws SysException, AppException {

        return sysUserMDao.findSysUsersBySysUserForPage(voExt, pagInfo);
    }

    /**
     * 查询个人授权
     * 
     * @param ovo
     * 
     * @param pagInfo
     * @throws SysException
     * @throws AppException
     * @return pagview
     */
    public List findSysUserAllocListBySysUserId(SysUserAllocSVO vo, String localNetId)
            throws SysException, AppException {

        return sysUserMDao.findSysUserAllocListBySysUserId(vo);
    }

    /**
     * 查询角色授权
     * 
     * @param ovo
     * 
     * @param pagInfo
     * @throws SysException
     * @throws AppException
     * @return pagview
     */
    public List findSysRoleAllocListBySysUserId(SysUserAllocSVO vo, String localNetId)
            throws SysException, AppException {

        return sysUserMDao.findSysRoleAllocListBySysUserId(vo);
    }

    /**
     * 分页查询用户
     * 
     * @param ovo
     * 
     * @param pagInfo
     * @throws SysException
     * @throws AppException
     * @return pagview
     */
    public List findConstraintAndPriviledgeListBySysUserId(ConstraintAndPriviledgeSVO vo)
            throws SysException, AppException {

        return sysUserMDao.findConstraintAndPriviledgeListBySysUserId(vo);
    }
    /**
     * 获得领单的数量
     * @param localNetId
     * @param areaId
     * @return
     */
    public String getFetchOrderCount(String localNetId,String areaId,String workAreaId)throws AppException,
              SysException{
    	String count="";
        ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getFetchOrderCount(localNetId, areaId, workAreaId);
        return count;
    }
    /**
     * 获得回单的数量
     * @param localNetId
     * @param areaId
     * @return
     */
    public int getReturnOrderCount(String localNetId,String areaId,String workAreaId)throws AppException,
              SysException{
    	int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getReturnOrderCount(localNetId, areaId,workAreaId);
        return count;
    }
    /**
	 * 获得追单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getZhuiDanOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException{
		int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getZhuiDanOrderCount(localNetId, areaId,workAreaId);
        return count;
	}
    /**
	 * 获得退单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getBackOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException {
		int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getBackOrderCount(localNetId, areaId,workAreaId);
        return count;
	}
	/**
	 * 获得催单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getPressOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException{
		int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getPressOrderCount(localNetId, areaId,workAreaId);
        return count;
	}
	/**
	 * 获得异常单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getExceptionOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException{
		int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getExceptionOrderCount(localNetId, areaId,workAreaId);
        return count;
	}
	
	
	
	 /**
     * 获得领单的数量
     * @param localNetId
     * @param areaId
     * @return
     */
    public String getFetchOrderCountAll(String localNetId,String areaId,String staffId)throws AppException,
              SysException{
    	String count="";
        ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getFetchOrderCountAll(localNetId, areaId, staffId);
        return count;
    }
    /**
     * 获得回单的数量
     * @param localNetId
     * @param areaId
     * @return
     */
    public int getReturnOrderCountAll(String localNetId,String areaId,String staffId)throws AppException,
              SysException{
    	int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getReturnOrderCountAll(localNetId, areaId,staffId);
        return count;
    }
    /**
	 * 获得追单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getZhuiDanOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException{
		int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getZhuiDanOrderCountAll(localNetId, areaId,staffId);
        return count;
	}
    /**
	 * 获得退单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getBackOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException {
		int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getBackOrderCountAll(localNetId, areaId,staffId);
        return count;
	}
	/**
	 * 获得催单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getPressOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException{
		int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getPressOrderCountAll(localNetId, areaId,staffId);
        return count;
	}
	/**
	 * 获得异常单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getExceptionOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException{
		int count=0;
    	ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
        count = sysUserSDao.getExceptionOrderCountAll(localNetId, areaId,staffId);
        return count;
	}
	/**
	 * 获得工单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public Map getOrderCountAll(String localNetId, String areaId,String workAreaId,String staffId) throws AppException,
			SysException{
		Map countMap = new HashMap();
		ISysUserMDAO sysUserSDao = (ISysUserMDAO) com.cattsoft.pub.dao.DAOFactory.getDAO(ISysUserMDAO.class);
		//当前工区的工单数量
//		int returnCount = sysUserSDao.getReturnOrderCount(localNetId,
//				areaId, workAreaId);// 回单
//		String fetchCount = sysUserSDao.getFetchOrderCount(localNetId,
//				areaId, workAreaId);// 领单
//		int pressCount = sysUserSDao.getPressOrderCount(localNetId, areaId,
//				workAreaId);
//		int exceptionCount = sysUserSDao.getExceptionOrderCount(localNetId,
//				areaId, workAreaId);
		// 吴刚-20080825 提供统一查询所有类型定单的统一入口DAO
		Map map=sysUserSDao.getAllOrderCount(localNetId, areaId, workAreaId,staffId);
		int returnCount=Integer.valueOf(map.get("returnCount").toString()).intValue();
		String fetchCount = map.get("appendCunt").toString() + "/"
				  + map.get("backCount").toString() + "/"
    				  + map.get("commonCount").toString();
		int pressCount=Integer.valueOf(map.get("pressCount").toString()).intValue();
		int exceptionCount=Integer.valueOf(map.get("exceptionCount").toString()).intValue();
		
		//当前有权限工区的工单数量
		int returnCountAll = Integer.valueOf(map.get("userReturnCount").toString()).intValue();// 回单
		String fetchCountAll = map.get("userAppendCount").toString() + "/"
		  + map.get("userBackCount").toString() + "/"
			  + map.get("userCommonCount").toString();// 领单
		int pressCountAll=Integer.valueOf(map.get("userPressCount").toString()).intValue();
		int exceptionCountAll=Integer.valueOf(map.get("userExceptionCount").toString()).intValue();
		String returnCountStr = returnCount+"";
		String pressCountStr = pressCount+"";
		String exceptionCountStr = exceptionCount+"";
		String returnCountAllStr = returnCountAll+"";
		String pressCountAllStr = pressCountAll+"";
		String exceptionCountAllStr = exceptionCountAll+"";
		//将结果全部放到hashmap当中
		countMap.put("returnCount", returnCountStr);
		countMap.put("fetchCount", fetchCount);
		countMap.put("pressCount", pressCountStr);
		countMap.put("exceptionCount", exceptionCountStr);
		countMap.put("returnCountAll", returnCountAllStr);
		countMap.put("fetchCountAll", fetchCountAll);
		countMap.put("pressCountAll", pressCountAllStr);
		countMap.put("exceptionCountAll", exceptionCountAllStr);
		
        return countMap;
	}
	
	/**
	 * 依据staffId或sysUserId查询用户信息
	 * @param vo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public SysUserSVO findSysUser(SysUserSVO vo) throws AppException, SysException {
		if(vo == null){
			throw new AppException("5000003","没有传入相关用户的参数！");
		}
        SysUserSVO voRet = (SysUserSVO) sysUserSDao.findByPK(vo);
        if(voRet == null){
        	//传入的是staffId
        	SysUserSVO tempVo = new SysUserSVO();        	
        	tempVo.setPartyRoleId(vo.getSysUserId());
        	voRet = getSysUser(tempVo);       	
        }
        //否则传入的是sysUserId
        return voRet;
	}
	
	/**
	 * 修改用户信息
	 * @param vo
	 * @throws SysException
	 * @throws AppException
	 */
	public void updateSysUser(SysUserSVO vo) throws SysException, AppException {
		if(vo == null){
			throw new AppException("5000003","没有传入相关用户的参数！");
		}
		sysUserSDao.update(vo);
	}
	
	/**
	 * 作用：根据员工得到员工对应工区信息(移动平台用)
	 * 
	 * create time: Oct 15, 2010
	 * author: yangkai
	 * @param vo
	 * @param systemName
	 * @param staffId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findWorkAreasBySystemStaff(WorkAreaSVO vo, String systemName, String staffId) throws AppException, SysException {
		IWorkAreaMDAO workAreaMDao = (IWorkAreaMDAO) DAOFactory.getInstance().getDAO(IWorkAreaMDAO.class);
		List workarea = workAreaMDao.findWorkAreasBySystemStaff(vo, systemName, staffId);
		if (workarea == null) {
			return null;
		}
		return workarea;
	}
	
	/**
	 * 作用：根据员工得到员工对应工区信息(移动平台用),以WorkAreaMVO为参数，只查询adminflag=Y的
	 * 
	 * create time: Oct 15, 2010
	 * author: rzg
	 * @param vo
	 * @param systemName
	 * @param staffId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findWorkAreasBySystemStaff(WorkAreaMVO vo, String systemName, String staffId) throws AppException, SysException {
		IWorkAreaMDAO workAreaMDao = (IWorkAreaMDAO) DAOFactory.getInstance().getDAO(IWorkAreaMDAO.class);
		List workarea = workAreaMDao.findWorkAreasBySystemStaffByMobile(vo, systemName, staffId);
		if (workarea == null) {
			return null;
		}
		return workarea;
	}	

	/**
	 * MOS Native 查询默认工区 根据员工得到员工对应工区信息,只查询adminflag=Y的
	 * 
	 * @param vo
	 * @param systemName
	 * @param staffId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public String findDefaultWorkAreasBySystemStaff4MOS(String parameter) throws AppException, SysException {
		WorkAreaMVO vo=new WorkAreaMVO();
		JSONObject parameterJsonObject=JSONObject.fromObject(parameter);
		String systemName=parameterJsonObject.getString("systemName");
		String staffId=parameterJsonObject.getString("staffId");
		JSONObject defaultWorkAreaJsonObject=new JSONObject();
		vo.setAdminFlag("Y");
		IWorkAreaMDAO workAreaMDao = (IWorkAreaMDAO) DAOFactory.getInstance().getDAO(IWorkAreaMDAO.class);
		List workarea = workAreaMDao.findWorkAreasBySystemStaffByMobile(vo, systemName, staffId);
		if (workarea == null||workarea.isEmpty()) {
			return null;
		}
//		else if(workarea.size()>1) {
//			throw new AppException("ERROR：", "员工设置了多个默认工区！");
//		}
		WorkAreaMVO workAreaMVO=(WorkAreaMVO) workarea.get(0);//查询结果
		defaultWorkAreaJsonObject.put("workAreaId", workAreaMVO.getWorkAreaId());
		defaultWorkAreaJsonObject.put("name", workAreaMVO.getName());
		defaultWorkAreaJsonObject.put("workTypeId", workAreaMVO.getWorkTypeId());
		defaultWorkAreaJsonObject.put("workTypeName", workAreaMVO.getWorkTypeName());
		defaultWorkAreaJsonObject.put("workMode", workAreaMVO.getWorkMode());
		defaultWorkAreaJsonObject.put("localNetId", workAreaMVO.getLocalNetId());
		defaultWorkAreaJsonObject.put("localNetName", workAreaMVO.getLocalNetName());
		defaultWorkAreaJsonObject.put("localNetIscenter", workAreaMVO.getLocalNetIscenter());
		defaultWorkAreaJsonObject.put("areaId", workAreaMVO.getAreaId());
		defaultWorkAreaJsonObject.put("areaName", workAreaMVO.getAreaName());
		defaultWorkAreaJsonObject.put("areaIscenter", workAreaMVO.getAreaIscenter());
		defaultWorkAreaJsonObject.put("servDeptId", workAreaMVO.getServDeptId());
		defaultWorkAreaJsonObject.put("servDeptName", workAreaMVO.getServDeptName());
		defaultWorkAreaJsonObject.put("branchId", workAreaMVO.getBranchId());
		defaultWorkAreaJsonObject.put("branchName", workAreaMVO.getBranchName());
		defaultWorkAreaJsonObject.put("dispatchLevel", workAreaMVO.getDispatchLevel());
		defaultWorkAreaJsonObject.put("parentWorkAreaId", workAreaMVO.getParentWorkAreaId());
		defaultWorkAreaJsonObject.put("standardCode", workAreaMVO.getStandardCode());
		defaultWorkAreaJsonObject.put("sts", workAreaMVO.getSts());
		defaultWorkAreaJsonObject.put("stsDate", new Timestamp(workAreaMVO.getStsDate().getTime()));
		defaultWorkAreaJsonObject.put("workType", workAreaMVO.getWorkType());
		defaultWorkAreaJsonObject.put("adminFlag", workAreaMVO.getAdminFlag());
		
		return defaultWorkAreaJsonObject.toString();
	}
	
	
	public List findWorkAreasBySystemStaff4Mobile(Map paraMap) throws AppException, SysException {
		WorkAreaMVO vo = (WorkAreaMVO) paraMap.get(SysConstants.PARA_MAP_WORK_AREA_MVO);
		String systemName = (String) paraMap.get(SysConstants.PARA_MAP_SYSTEM_NAME);
		String staffId = (String) paraMap.get(SysConstants.PARA_MAP_STAFF_ID);
		IWorkAreaMDAO workAreaMDao = (IWorkAreaMDAO) DAOFactory.getInstance().getDAO(IWorkAreaMDAO.class);
		List workarea = workAreaMDao.findWorkAreasBySystemStaffByMobile(vo, systemName, staffId);
		if (workarea == null) {
			return null;
		}
		return workarea;
	}
	
	/**
	 * 作用：根据员工得到员工对应工区所属本地网信息(移动平台用)
	 * 
	 * create time: Oct 15, 2010
	 * author: yangkai
	 * @param staffId
	 * @param systemName
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getStaffLocalNetVOs(String staffId, String systemName) throws AppException, SysException {
		IStaffMDAO staffMDao = (IStaffMDAO)DAOFactory.getInstance().getDAO(IStaffMDAO.class);
		List staff = staffMDao.getStaffLocalNetVOs(staffId, systemName);
		if (staff == null) {
			return null;
		}
		return staff;
	}
	

	public void updateTerminalConfig(String staffId) throws AppException, SysException {
		IStaffMDAO staffMDAO = (IStaffMDAO)DAOFactory.getInstance().getDAO(IStaffMDAO.class);
		staffMDAO.updateTerminalConfig(staffId);
	}
	
	
	/**
	 * 为减少客户端代码修改，转换IOM数据模型为MOS数据模型
	 */
	private List convertIOMFunctionToMosFuncNode(List funcList) {
		if(funcList==null)return null;
		for(int i=0;i<funcList.size();i++) {
			MosFuncNodeSVO svo=(MosFuncNodeSVO)funcList.get(i);
			String funcLevel=svo.getFunccLevel();
			String shortName=svo.getShortName();
			String nodeType=svo.getType();
			if("M".equals(nodeType)) {
				nodeType="M";
			}else {
				nodeType="D";
			}
			svo.setType(nodeType);
			
			if("T".equals(funcLevel)) {
				svo.setFunccLevel("U");
			}else {
				svo.setFunccLevel("V");
			}
			if("6000000".equals(shortName)) {//工单列表
				shortName="GDLB";
			}else if("6000001".equals(shortName)){//工单查询
				shortName="GDCX";
			}else if("6000002".equals(shortName)){//流程查询
				shortName="LCCX";
			}else if("6000003".equals(shortName)){//统计报表
				shortName="TJBB";
			}else if("6000004".equals(shortName)){//资源查询
				shortName="ZYCX";
			}else if("6000005".equals(shortName)){//资源维护
				shortName="ZYWH";
			}else if("6000006".equals(shortName)){//公告
				shortName="GG";
			}else if("6000007".equals(shortName)){//知识库
				shortName="ZSK";
			}else if("6000008".equals(shortName)){//终端领用
				shortName="ZDLY";
			}else if("6000009".equals(shortName)){//工单领取
				shortName="GDLQ";
			}else if("6000010".equals(shortName)){//天气预报
				shortName="TQYB";
			}else if("6000011".equals(shortName)){//资源核查
				shortName="ZYHC";
			}else if("6000012".equals(shortName)){//更换终端
				shortName="GHZD";
			}else if("6000013".equals(shortName)){//设置
				shortName="SZ";
			}else if("6000014".equals(shortName)){//回单
				shortName="HD";
			}else if("6000015".equals(shortName)){//退单
				shortName="TD";
			}else if("6000016".equals(shortName)){//呼叫
				shortName="HJ";
			}else if("6000017".equals(shortName)){//竣工核实
				shortName="JGHS";
			}else if("6000018".equals(shortName)){//材料回填
				shortName="CLHT";
			}else if("6000019".equals(shortName)){//转派
				shortName="ZP";
			}else if("6000020".equals(shortName)){//预约
				shortName="YY";
			}else if("6000021".equals(shortName)){//网格维护
				shortName="WGWH";
			}else if("6000022".equals(shortName)){//资源变更
				shortName="ZYBG";
			}else if("6000023".equals(shortName)){//拍照
				shortName="PZ";
			}else if("6000024".equals(shortName)){//资源核查
				shortName="ZYHC";
			}else if("6000025".equals(shortName)){//业务测试
				shortName="YWCS";
			}else if("6000026".equals(shortName)){//资源清查
				shortName="ZYQC";
			}
			svo.setShortName(shortName);
		}
		return funcList;
	}

}
