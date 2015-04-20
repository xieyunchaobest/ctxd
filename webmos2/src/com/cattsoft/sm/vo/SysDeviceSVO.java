package com.cattsoft.sm.vo;

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
public class SysDeviceSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    /**
     * 属性sysDeviceId 标识 属性
     */
    private Long sysDeviceId;

    /**
     * 属性sysDeviceName 持久化 属性
     */
    private String sysDeviceName;

    /**
     * 属性sysDeviceMac 持久化 属性
     */
    private String sysDeviceMac;

    /**
     * 属性userDevices 持久化 属性
     */
    private Set userDevices;

    /**
     * 属性loginLogs 持久化 属性
     */
    private Set loginLogs;

    /** default constructor */
    public SysDeviceSVO() {
    }

    /**
     * 取得属性sysDeviceId的方法
     * 
     * @return Long
     */
    public Long getSysDeviceId() {
        return this.sysDeviceId;
    }

    /**
     * 设置sysDeviceId的方法
     * 
     * @param Long
     */
    public void setSysDeviceId(Long sysDeviceId) {
        this.sysDeviceId = sysDeviceId;
    }

    /**
     * 取得属性sysDeviceName的方法
     * 
     * @return String
     */
    public String getSysDeviceName() {
        return this.sysDeviceName;
    }

    /**
     * 设置sysDeviceName的方法
     * 
     * @param String
     */
    public void setSysDeviceName(String sysDeviceName) {
        this.sysDeviceName = sysDeviceName;
    }

    /**
     * 取得属性sysDeviceMac的方法
     * 
     * @return String
     */
    public String getSysDeviceMac() {
        return this.sysDeviceMac;
    }

    /**
     * 设置sysDeviceMac的方法
     * 
     * @param String
     */
    public void setSysDeviceMac(String sysDeviceMac) {
        this.sysDeviceMac = sysDeviceMac;
    }

    /**
     * 取得属性userDevices的方法
     * 
     * @return Set
     */
    public Set getUserDevices() {
        return this.userDevices;
    }

    /**
     * 设置userDevices的方法
     * 
     * @param Set
     */
    public void setUserDevices(Set userDevices) {
        this.userDevices = userDevices;
    }

    /**
     * 取得属性loginLogs的方法
     * 
     * @return Set
     */
    public Set getLoginLogs() {
        return this.loginLogs;
    }

    /**
     * 设置loginLogs的方法
     * 
     * @param Set
     */
    public void setLoginLogs(Set loginLogs) {
        this.loginLogs = loginLogs;
    }

}
