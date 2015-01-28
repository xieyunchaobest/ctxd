package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class SysUserSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

    private String sysUserId;

    private String partyRoleTypeId;

    private String partyRoleId;

    private String sysUserName;

    private String password;

    private Timestamp setPwdTime;

    private Timestamp updatePwdTime;

    private String lastPwd;

    private Timestamp createDate;

    private String sts;

    private Timestamp stsDate;

    private String localNetId;
    
    //added by yangkai 增加日志记录 2009-6-9   
    private ActionLogSVO actionLog=null;
    
    private String erpno;
    
    
    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setPartyRoleTypeId(String partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public String getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleId(String partyRoleId) {
        this.partyRoleId = partyRoleId;
    }

    public String getPartyRoleId() {
        return partyRoleId;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setLastPwd(String lastPwd) {
        this.lastPwd = lastPwd;
    }

    public String getLastPwd() {
        return lastPwd;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSts() {
        return sts;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getSetPwdTime() {
        return setPwdTime;
    }

    public void setSetPwdTime(Timestamp setPwdTime) {
        this.setPwdTime = setPwdTime;
    }

    public Timestamp getStsDate() {
        return stsDate;
    }

    public void setStsDate(Timestamp stsDate) {
        this.stsDate = stsDate;
    }

    public Timestamp getUpdatePwdTime() {
        return updatePwdTime;
    }

    public void setUpdatePwdTime(Timestamp updatePwdTime) {
        this.updatePwdTime = updatePwdTime;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof SysUserSVO) {
            SysUserSVO another = (SysUserSVO) obj;
            equals = new EqualsBuilder().append(sysUserId, another.getSysUserId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(sysUserId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("sysUserId", getSysUserId()).toString();
    }

	/**
	 * @return the actionLog
	 */
	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	/**
	 * @param actionLog the actionLog to set
	 */
	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public String getErpno() {
		return erpno;
	}

	public void setErpno(String erpno) {
		this.erpno = erpno;
	}
	
	
}