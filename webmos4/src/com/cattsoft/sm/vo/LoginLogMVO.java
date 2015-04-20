package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class LoginLogMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private LoginLogSVO ll;

    private Integer partyRoleTypeId;

    private String partyName;
    
    private String staffId;

    private Integer localNetId;

    private Integer areaId;

    private String loginTime;

    private String loginTime2;

    private String logoutTime;

    private String logoutTime2;

    private String sysUserName;
    
    private String localNet;

    public String getLocalNet() {
        return localNet;
    }

    public void setLocalNet(String localNet) {
        this.localNet = localNet;
    }

    public LoginLogSVO getLl() {
        return ll;
    }

    public Integer getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public void setLl(LoginLogSVO ll) {
        this.ll = ll;
    }

    public void setPartyRoleTypeId(Integer partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public void setLocalNetId(Integer localNetId) {
        this.localNetId = localNetId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginTime2() {
        return loginTime2;
    }

    public void setLoginTime2(String loginTime2) {
        this.loginTime2 = loginTime2;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLogoutTime2() {
        return logoutTime2;
    }

    public void setLogoutTime2(String logoutTime2) {
        this.logoutTime2 = logoutTime2;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getPartyName() {
        return partyName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public Integer getLocalNetId() {
        return localNetId;
    }

    public LoginLogMVO() {
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

}
