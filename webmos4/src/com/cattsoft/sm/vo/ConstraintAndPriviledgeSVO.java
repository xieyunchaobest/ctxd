package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class ConstraintAndPriviledgeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String consPrivId;

	private String sysUserRoleId;

	private String sysUserId;

	private String funcNodeId;

	private String flag;

	private String rangeId;

	private Date createDate;

	private String sts;

	private Date stsDate;

	public void setConsPrivId(String consPrivId) {
		this.consPrivId = consPrivId;
	}
	
	public String getConsPrivId() {
		return consPrivId;
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

	public void setFuncNodeId(String funcNodeId) {
		this.funcNodeId = funcNodeId;
	}
	
	public String getFuncNodeId() {
		return funcNodeId;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setRangeId(String rangeId) {
		this.rangeId = rangeId;
	}
	
	public String getRangeId() {
		return rangeId;
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
		if (obj != null && obj instanceof ConstraintAndPriviledgeSVO) {
			ConstraintAndPriviledgeSVO another = (ConstraintAndPriviledgeSVO) obj;
			equals = new EqualsBuilder()
					.append(consPrivId, another.getConsPrivId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(consPrivId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("consPrivId", getConsPrivId())
				.toString();
	}
}