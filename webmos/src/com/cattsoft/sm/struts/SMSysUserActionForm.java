package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-11 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SMSysUserActionForm extends ActionForm {
    private String lastPwd;

    private Long partyRoleId;

    private Integer partyRoleTypeId;

    private List partyRoleTypeSel;

    private String password;

    private String password1;

    private String sts;

    private String sysUserId;

    private String sysUserName;

    private Long[] choses;

    private Long virtualTeamId;

    private Long staffId;

    private String staffName;

    private String sysUserIds;

    private String name;

    private String deptName;

    private String type;

    // 取消权限用到
    private Integer funcNodeId;

    private String srcUrl;

    private String loginFlag;

    private List localNetSel;

    private Integer localNetId;

    private Integer areaId;

    private List areaSel;
    
    //added by yangkai 2009-6-9 增加下面4个属性
    
    private String chbsysUserName;
    
    private String chbstaffName;
    
    private String chbAreaId;
    
    private String chbSts;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public List getAreaSel() {
        return areaSel;
    }

    public void setAreaSel(List areaSel) {
        this.areaSel = areaSel;
    }

    public Long[] getChoses() {
        return choses;
    }

    public void setChoses(Long[] choses) {
        this.choses = choses;
    }

    public Integer getFuncNodeId() {
        return funcNodeId;
    }

    public void setFuncNodeId(Integer funcNodeId) {
        this.funcNodeId = funcNodeId;
    }

    public String getLastPwd() {
        return lastPwd;
    }

    public void setLastPwd(String lastPwd) {
        this.lastPwd = lastPwd;
    }

    public Integer getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(Integer localNetId) {
        this.localNetId = localNetId;
    }

    public List getLocalNetSel() {
        return localNetSel;
    }

    public void setLocalNetSel(List localNetSel) {
        this.localNetSel = localNetSel;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Long getPartyRoleId() {
        return partyRoleId;
    }

    public void setPartyRoleId(Long partyRoleId) {
        this.partyRoleId = partyRoleId;
    }

    public Integer getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(Integer partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public List getPartyRoleTypeSel() {
        return partyRoleTypeSel;
    }

    public void setPartyRoleTypeSel(List partyRoleTypeSel) {
        this.partyRoleTypeSel = partyRoleTypeSel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getVirtualTeamId() {
        return virtualTeamId;
    }

    public void setVirtualTeamId(Long virtualTeamId) {
        this.virtualTeamId = virtualTeamId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSysUserIds() {
        return sysUserIds;
    }

    public void setSysUserIds(String sysUserIds) {
        this.sysUserIds = sysUserIds;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	/**
	 * @return the chbsysUserName
	 */
	public String getChbsysUserName() {
		return chbsysUserName;
	}

	/**
	 * @param chbsysUserName the chbsysUserName to set
	 */
	public void setChbsysUserName(String chbsysUserName) {
		this.chbsysUserName = chbsysUserName;
	}

	/**
	 * @return the chbstaffName
	 */
	public String getChbstaffName() {
		return chbstaffName;
	}

	/**
	 * @param chbstaffName the chbstaffName to set
	 */
	public void setChbstaffName(String chbstaffName) {
		this.chbstaffName = chbstaffName;
	}

	/**
	 * @return the chbAreaId
	 */
	public String getChbAreaId() {
		return chbAreaId;
	}

	/**
	 * @param chbAreaId the chbAreaId to set
	 */
	public void setChbAreaId(String chbAreaId) {
		this.chbAreaId = chbAreaId;
	}

	/**
	 * @return the chbSts
	 */
	public String getChbSts() {
		return chbSts;
	}

	/**
	 * @param chbSts the chbSts to set
	 */
	public void setChbSts(String chbSts) {
		this.chbSts = chbSts;
	}
}
