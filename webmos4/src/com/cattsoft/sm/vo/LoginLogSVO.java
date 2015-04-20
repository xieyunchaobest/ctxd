package com.cattsoft.sm.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class LoginLogSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String loginId;

    private String sysUserId;

    private String ipAddr;

    private String sysDeviceMac;

    private java.sql.Timestamp loginTime;

    private java.sql.Timestamp logoutTime;

    private String localNetId;

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setSysDeviceMac(String sysDeviceMac) {
        this.sysDeviceMac = sysDeviceMac;
    }

    public String getSysDeviceMac() {
        return sysDeviceMac;
    }

    public java.sql.Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(java.sql.Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public java.sql.Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(java.sql.Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof LoginLogSVO) {
            LoginLogSVO another = (LoginLogSVO) obj;
            equals = new EqualsBuilder().append(loginId, another.getLoginId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(loginId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("loginId", getLoginId()).toString();
    }
}