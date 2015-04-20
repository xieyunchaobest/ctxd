package com.cattsoft.sm.vo;

import java.sql.Date;
import java.util.Set;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-24 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SysUserMVO extends GenericVO {
    private static final long serialVersionUID = 1L;

    /**
     * 属性sysUserId 标识 属性
     */
    private String sysUserId;

    /**
     * 属性partyRoleId 持久化 属性
     */
    private String partyRoleId;

    /**
     * 属性partyRoleTypeId 持久化 属性
     */
    private String partyRoleTypeId;

    /**
     * 属性sysUserName 持久化 属性
     */
    private String sysUserName;

    /**
     * 属性password 持久化 属性
     */
    private String password;

    /**
     * 属性setPwdTime 持久化 属性
     */
    private Date setPwdTime;

    /**
     * 属性updatePwdTimenullable 持久化 属性
     */
    private Date updatePwdTime;

    /**
     * 属性lastPwdnullable 持久化 属性
     */
    private String lastPwd;

    /**
     * 属性createDate 持久化 属性
     */
    private Date createDate;

    /**
     * 属性sts 持久化 属性
     */
    private String sts;

    /**
     * 属性stsDate 持久化 属性
     */
    private Date stsDate;

    /** ******party表的一些信息************** */
    private String partyId;

    private String name;

    private String localNetId;

    private String areaId;

    private String branchId;

    private String servDeptId;

    private String partyType;

    private String partySts;

    private Date partyStsDate;

    /**
     * 属性loginLogs 持久化 属性
     */
    private Set loginLogs;

    private String loginFlag;

    /** default constructor */
    public SysUserMVO() {
    }

    public String getPartyRoleId() {
        return partyRoleId;
    }

    public void setPartyRoleId(String partyRoleId) {
        this.partyRoleId = partyRoleId;
    }

    public String getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(String partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * 取得属性sysUserName的方法
     * 
     * @return String
     */
    public String getSysUserName() {
        return this.sysUserName;
    }

    /**
     * 设置sysUserName的方法
     * 
     * @param String
     */
    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    /**
     * 取得属性password的方法
     * 
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置password的方法
     * 
     * @param String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 取得属性setPwdTime的方法
     * 
     * @return Date
     */
    public Date getSetPwdTime() {
        return this.setPwdTime;
    }

    /**
     * 设置setPwdTime的方法
     * 
     * @param Date
     */
    public void setSetPwdTime(Date setPwdTime) {
        this.setPwdTime = setPwdTime;
    }

    /**
     * 取得属性updatePwdTime的方法
     * 
     * @return Date
     */
    public Date getUpdatePwdTime() {
        return this.updatePwdTime;
    }

    /**
     * 设置updatePwdTime的方法
     * 
     * @param Date
     */
    public void setUpdatePwdTime(Date updatePwdTime) {
        this.updatePwdTime = updatePwdTime;
    }

    /**
     * 取得属性lastPwd的方法
     * 
     * @return String
     */
    public String getLastPwd() {
        return this.lastPwd;
    }

    /**
     * 设置lastPwd的方法
     * 
     * @param String
     */
    public void setLastPwd(String lastPwd) {
        this.lastPwd = lastPwd;
    }

    /**
     * 取得属性createDate的方法
     * 
     * @return Date
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置createDate的方法
     * 
     * @param Date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 取得属性sts的方法
     * 
     * @return String
     */
    public String getSts() {
        return this.sts;
    }

    /**
     * 设置sts的方法
     * 
     * @param String
     */
    public void setSts(String sts) {
        this.sts = sts;
    }

    /**
     * 取得属性stsDate的方法
     * 
     * @return Date
     */
    public Date getStsDate() {
        return this.stsDate;
    }

    /**
     * 设置stsDate的方法
     * 
     * @param Date
     */
    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Set getLoginLogs() {
        return loginLogs;
    }

    public void setLoginLogs(Set loginLogs) {
        this.loginLogs = loginLogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartySts() {
        return partySts;
    }

    public void setPartySts(String partySts) {
        this.partySts = partySts;
    }

    public Date getPartyStsDate() {
        return partyStsDate;
    }

    public void setPartyStsDate(Date partyStsDate) {
        this.partyStsDate = partyStsDate;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

}
