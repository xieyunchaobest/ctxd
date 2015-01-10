package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class SysUserBarSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String sysUserBarId;

	private String sysUserId;

	private String funcNodeId;

	private String sts;

	private Date stsDate;

	public void setSysUserBarId(String sysUserBarId) {
		this.sysUserBarId = sysUserBarId;
	}
	
	public String getSysUserBarId() {
		return sysUserBarId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	public String getSysUserId() {
		return sysUserId;
	}

	public void setFuncNodeId(String funcNodeId) {
		this.funcNodeId = funcNodeId;
	}
	
	public String getFuncNodeId() {
		return funcNodeId;
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
		if (obj != null && obj instanceof SysUserBarSVO) {
			SysUserBarSVO another = (SysUserBarSVO) obj;
			equals = new EqualsBuilder()
					.append(sysUserBarId, another.getSysUserBarId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(sysUserBarId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("sysUserBarId", getSysUserBarId())
				.toString();
	}
}