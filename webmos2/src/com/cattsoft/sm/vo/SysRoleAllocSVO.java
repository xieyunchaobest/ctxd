package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class SysRoleAllocSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String sysRoleAllocId;

	private String sysRoleId;

	private String funcNodeId;

	private String allowAuth;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setSysRoleAllocId(String sysRoleAllocId) {
		this.sysRoleAllocId = sysRoleAllocId;
	}
	
	public String getSysRoleAllocId() {
		return sysRoleAllocId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	
	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setFuncNodeId(String funcNodeId) {
		this.funcNodeId = funcNodeId;
	}
	
	public String getFuncNodeId() {
		return funcNodeId;
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
		if (obj != null && obj instanceof SysRoleAllocSVO) {
			SysRoleAllocSVO another = (SysRoleAllocSVO) obj;
			equals = new EqualsBuilder()
					.append(sysRoleAllocId, another.getSysRoleAllocId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(sysRoleAllocId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("sysRoleAllocId", getSysRoleAllocId())
				.toString();
	}
}