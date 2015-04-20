package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class StaffWorkAreaSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String staffWorkAreaId;

	private String staffId;

	private String workAreaId;

	private String grantor;

	private String adminFlag;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setStaffWorkAreaId(String staffWorkAreaId) {
		this.staffWorkAreaId = staffWorkAreaId;
	}
	
	public String getStaffWorkAreaId() {
		return staffWorkAreaId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public String getStaffId() {
		return staffId;
	}

	public void setWorkAreaId(String workAreaId) {
		this.workAreaId = workAreaId;
	}
	
	public String getWorkAreaId() {
		return workAreaId;
	}

	public void setGrantor(String grantor) {
		this.grantor = grantor;
	}
	
	public String getGrantor() {
		return grantor;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}
	
	public String getAdminFlag() {
		return adminFlag;
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
		if (obj != null && obj instanceof StaffWorkAreaSVO) {
			StaffWorkAreaSVO another = (StaffWorkAreaSVO) obj;
			equals = new EqualsBuilder()
					.append(staffWorkAreaId, another.getStaffWorkAreaId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(staffWorkAreaId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("staffWorkAreaId", getStaffWorkAreaId())
				.toString();
	}
}