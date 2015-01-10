package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class OrgDeptSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String deptId;

	private String deptName;

	private String areaId;

	private String parentDeptId;

	private String localNetId;

	private String servDeptId;

	private String adminStaffId;

	private String deptType;

	private String rootFlag;

	private String sts;

	private Date stsDate;

	private Date createDate;

	private String deptDesc;

	private String address;

	private String postNbr;

	private String branchId;
	
	private ActionLogSVO actionLog=null;

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getDeptName() {
		return deptName;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}
	
	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}
	
	public String getLocalNetId() {
		return localNetId;
	}

	public void setServDeptId(String servDeptId) {
		this.servDeptId = servDeptId;
	}
	
	public String getServDeptId() {
		return servDeptId;
	}

	public void setAdminStaffId(String adminStaffId) {
		this.adminStaffId = adminStaffId;
	}
	
	public String getAdminStaffId() {
		return adminStaffId;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	
	public String getDeptType() {
		return deptType;
	}

	public void setRootFlag(String rootFlag) {
		this.rootFlag = rootFlag;
	}
	
	public String getRootFlag() {
		return rootFlag;
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

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	
	public String getDeptDesc() {
		return deptDesc;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setPostNbr(String postNbr) {
		this.postNbr = postNbr;
	}
	
	public String getPostNbr() {
		return postNbr;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchId() {
		return branchId;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof OrgDeptSVO) {
			OrgDeptSVO another = (OrgDeptSVO) obj;
			equals = new EqualsBuilder()
					.append(deptId, another.getDeptId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(deptId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("deptId", getDeptId())
				.toString();
	}
}