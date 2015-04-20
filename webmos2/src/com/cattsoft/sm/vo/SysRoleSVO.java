package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class SysRoleSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String sysRoleId;

	private String sysRoleName;

	private String roleMemo;

	private String localNetId;

	private String areaId;

	private Date createDate;

	private String sts;
	
	private String sysUserId;

	private Date stsDate;
	
	private ActionLogSVO actionLog=null;

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	
	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}
	
	public String getSysRoleName() {
		return sysRoleName;
	}

	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}
	
	public String getRoleMemo() {
		return roleMemo;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}
	
	public String getLocalNetId() {
		return localNetId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return createDate;
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

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof SysRoleSVO) {
			SysRoleSVO another = (SysRoleSVO) obj;
			equals = new EqualsBuilder()
					.append(sysRoleId, another.getSysRoleId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(sysRoleId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("sysRoleId", getSysRoleId())
				.toString();
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
}