package com.cattsoft.sm.vo;

import java.util.List;
import java.util.Map;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-24 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SysUserExtendedMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    /**
     * VO在session中的属性名称
     */
    public final static String SESSION_NAME = "sysUserVOExtended";

    /**
     * 当前用户的SysUserMVO
     */
    private SysUserSVO sysUserSVO;

    /**
     * 当前用户正访问的子系统
     */
    private String userSubSystems;

    /**
     * 当前用户对应的员工VO
     */
    private StaffExtendMVO staffExtendMVO;

    /**
     * 当前用户可访问的所有工区
     */
    private List staffWorkAreas;

    /**
     * 当前用户正访问的工区
     */
    private WorkAreaMVO currentWorkAreaVO;

    /**
     * 当前用户可访问的所有功能点
     */
    private List funcNodeAll;

    /**
     * 当前登录日志
     */
    private LoginLogSVO loginLogVO;

    /**
     * 当前登录日志模式
     */
    private int loginMode;

    /**
     * 当前用户失效
     */
    private int userValid;

    /**
     * 初始登录
     */
    private int firstFlag;

    /**
     * 密码失效
     */
    private int passwordValid;

    /**
     * 密码距离失效天数
     */
    private String passwordDay;

    /**
     * 当前登录日志ID
     */
    private String LoginId;

    /**
     * 
     * 功能树的集合（只是为了方便从后台把功能树相关数据取出供action使用，并不会把数据保存到页面上）
     */
    private List funcNodeTreeVOs;

    /**
     * 用户当前访问的功能点id
     */
    private String curFuncNodeId;

    /**
     * 用户当前访问的功能点名字
     * 
     */
    private String curFuncNodeName; 
    //用户能访问的系统权限
    private String permissAppStr;
    
    private String appInfoURLStr;
    
    private String defaultApp;
    
    //是否默认系统
    private String flag;
    
    public String getCurFuncNodeName() {
        return curFuncNodeName;
    }

    public void setCurFuncNodeName(String curFuncNodeName) {
        this.curFuncNodeName = curFuncNodeName;
    }

    public List getFuncNodeTreeVOs() {
        return funcNodeTreeVOs;
    }

    public void setFuncNodeTreeVOs(List funcNodeTreeVOs) {
        this.funcNodeTreeVOs = funcNodeTreeVOs;
    }

    public WorkAreaMVO getCurrentWorkAreaVO() {
        return currentWorkAreaVO;
    }

    public void setCurrentWorkAreaVO(WorkAreaMVO currentWorkAreaVO) {
        this.currentWorkAreaVO = currentWorkAreaVO;
    }

    public int getFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(int firstFlag) {
        this.firstFlag = firstFlag;
    }

    public List getFuncNodeAll() {
        return funcNodeAll;
    }

    public void setFuncNodeAll(List funcNodeAll) {
        this.funcNodeAll = funcNodeAll;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public LoginLogSVO getLoginLogVO() {
        return loginLogVO;
    }

    public void setLoginLogVO(LoginLogSVO loginLogVO) {
        this.loginLogVO = loginLogVO;
    }

    public int getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(int loginMode) {
        this.loginMode = loginMode;
    }

    public int getPasswordValid() {
        return passwordValid;
    }

    public void setPasswordValid(int passwordValid) {
        this.passwordValid = passwordValid;
    }

    public StaffExtendMVO getStaffExtendMVO() {
        return staffExtendMVO;
    }

    public void setStaffExtendMVO(StaffExtendMVO staffExtendMVO) {
        this.staffExtendMVO = staffExtendMVO;
    }

    public List getStaffWorkAreas() {
        return staffWorkAreas;
    }

    public void setStaffWorkAreas(List staffWorkAreas) {
        this.staffWorkAreas = staffWorkAreas;
    }

    public SysUserSVO getSysUserSVO() {
        return sysUserSVO;
    }

    public void setSysUserSVO(SysUserSVO sysUserSVO) {
        this.sysUserSVO = sysUserSVO;
    }

    public String getUserSubSystems() {
        return userSubSystems;
    }

    public void setUserSubSystems(String userSubSystems) {
        this.userSubSystems = userSubSystems;
    }

    public int getUserValid() {
        return userValid;
    }

    public void setUserValid(int userValid) {
        this.userValid = userValid;
    }

    public String getCurFuncNodeId() {
        return curFuncNodeId;
    }

    public void setCurFuncNodeId(String curFuncNodeId) {
        this.curFuncNodeId = curFuncNodeId;
    }

    public String getPasswordDay() {
        return passwordDay;
    }

    public void setPasswordDay(String passwordDay) {
        this.passwordDay = passwordDay;
    }

	public String getAppInfoURLStr() {
		return appInfoURLStr;
	}

	public void setAppInfoURLStr(String appInfoURLStr) {
		this.appInfoURLStr = appInfoURLStr;
	}

	public String getPermissAppStr() {
		return permissAppStr;
	}

	public void setPermissAppStr(String permissAppStr) {
		this.permissAppStr = permissAppStr;
	}

	public String getDefaultApp() {
		return defaultApp;
	}

	public void setDefaultApp(String defaultApp) {
		this.defaultApp = defaultApp;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	} 
	
}
