package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.cattsoft.sm.util.StrutsUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-14 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class LoginLogActionForm extends ActionForm {

    private String loginId;

    private String sysDeviceId;
    
    private String sysDeviceIp;

    private String sysDeviceMac;

    private String sysUserId;
    
    private String chbSysUserId;

    private String partyName;
    
    private String chbPartyName;

    private String sysUserName;

    private String chbSysUserName;
    
    private String loginTimeStr;
   
    private String loginTimeStr2;

    private String localNet;

    private String logoutTimeStr;

   
    private String logoutTimeStr2;



    private String liChk;

    private String loChk;
    
    private String src;
    private String staffId;
  
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getLiChk() {
        return liChk;
    }

    public void setLiChk(String liChk) {
        this.liChk = liChk;
    }

    public String getLoChk() {
        return loChk;
    }

    public void setLoChk(String loChk) {
        this.loChk = loChk;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginTimeStr() {
        return loginTimeStr;
    }

    public void setLoginTimeStr(String loginTimeStr) {
        this.loginTimeStr = loginTimeStr;
    }

    public String getLoginTimeStr2() {
        return loginTimeStr2;
    }

    public void setLoginTimeStr2(String loginTimeStr2) {
        this.loginTimeStr2 = loginTimeStr2;
    }

    public String getLogoutTimeStr() {
        return logoutTimeStr;
    }

    public void setLogoutTimeStr(String logoutTimeStr) {
        this.logoutTimeStr = logoutTimeStr;
    }

    public String getLogoutTimeStr2() {
        return logoutTimeStr2;
    }

    public void setLogoutTimeStr2(String logoutTimeStr2) {
        this.logoutTimeStr2 = logoutTimeStr2;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getSysDeviceId() {
        return sysDeviceId;
    }

    public void setSysDeviceId(String sysDeviceId) {
        this.sysDeviceId = sysDeviceId;
    }

    public String getSysDeviceMac() {
        return sysDeviceMac;
    }

    public void setSysDeviceMac(String sysDeviceMac) {
        this.sysDeviceMac = sysDeviceMac;
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

    public String getChbSysUserId() {
        return chbSysUserId;
    }

    public void setChbSysUserId(String chbSysUserId) {
        this.chbSysUserId = chbSysUserId;
    }

    public String getChbSysUserName() {
        return chbSysUserName;
    }

    public void setChbSysUserName(String chbSysUserName) {
        this.chbSysUserName = chbSysUserName;
    }

    public String getChbPartyName() {
        return chbPartyName;
    }

    public void setChbPartyName(String chbPartyName) {
        this.chbPartyName = chbPartyName;
    }

    public String getLocalNet() {
        return localNet;
    }

    public void setLocalNet(String localNet) {
        this.localNet = localNet;
    }

    public String getSysDeviceIp() {
        return sysDeviceIp;
    }

    public void setSysDeviceIp(String sysDeviceIp) {
        this.sysDeviceIp = sysDeviceIp;
    }



   

   
}
