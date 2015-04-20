package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class SysUserRoleSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String sysUserRoleId;

	private String sysUserId;

	private String sysRoleId;

	private String allowAuth;

	private String sts;

	private Date stsDate;

	private Date createDate;
	
	 //added by yangkai 增加日志记录 2009-6-9   
    private ActionLogSVO actionLog=null;


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

	public void setSysUserRoleId(String sysUserRoleId) {
		this.sysUserRoleId = sysUserRoleId;
	}
	
	public String getSysUserRoleId() {
		return sysUserRoleId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	
	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setAllowAuth(String allowAuth) {
		this.allowAuth = allowAuth;
	}
	
	public String getAllowAuth() {
		return allowAuth;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}
	
	public String getSts() {
		return sts;
	}

	public void setStsDate(Date stsDate) {
		this.stsDate = stsDate;
	}
	
	public Date getStsDate() {
		return stsDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof SysUserRoleSVO) {
			SysUserRoleSVO another = (SysUserRoleSVO) obj;
			equals = new EqualsBuilder()
					.append(sysUserRoleId, another.getSysUserRoleId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(sysUserRoleId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("sysUserRoleId", getSysUserRoleId())
				.toString();
	}
}