package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class BranchSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String branchId;

	private String localNetId;

	private String areaId;

	private String servDeptId;

	private String abbrevName;

	private String name;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchId() {
		return branchId;
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

	public void setServDeptId(String servDeptId) {
		this.servDeptId = servDeptId;
	}
	
	public String getServDeptId() {
		return servDeptId;
	}

	public void setAbbrevName(String abbrevName) {
		this.abbrevName = abbrevName;
	}
	
	public String getAbbrevName() {
		return abbrevName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
		if (obj != null && obj instanceof BranchSVO) {
			BranchSVO another = (BranchSVO) obj;
			equals = new EqualsBuilder()
					.append(branchId, another.getBranchId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(branchId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("branchId", getBranchId())
				.toString();
	}
}