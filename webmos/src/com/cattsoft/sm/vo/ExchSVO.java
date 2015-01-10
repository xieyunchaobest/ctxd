package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class ExchSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String exchId;

	private String name;

	private String localNetId;

	private String areaId;

	private String servDeptId;

	private String branchId;

	private String ruleAreaId;

	private String abbrevName;

	private String code;

	private String exchType;
	
	private String subType;

	private String address;

	private Date commDate;

	private String standardCode;

	private String sts;

	private Date stsDate;

	private Date createDate;
	
	private ActionLogSVO actionLog=null;
	
	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setExchId(String exchId) {
		this.exchId = exchId;
	}
	
	public String getExchId() {
		return exchId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchId() {
		return branchId;
	}

	public void setRuleAreaId(String ruleAreaId) {
		this.ruleAreaId = ruleAreaId;
	}
	
	public String getRuleAreaId() {
		return ruleAreaId;
	}

	public void setAbbrevName(String abbrevName) {
		this.abbrevName = abbrevName;
	}
	
	public String getAbbrevName() {
		return abbrevName;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setExchType(String exchType) {
		this.exchType = exchType;
	}
	
	public String getExchType() {
		return exchType;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}
	
	public Date getCommDate() {
		return commDate;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	
	public String getStandardCode() {
		return standardCode;
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
		if (obj != null && obj instanceof ExchSVO) {
			ExchSVO another = (ExchSVO) obj;
			equals = new EqualsBuilder()
					.append(exchId, another.getExchId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(exchId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("exchId", getExchId())
				.toString();
	}
	

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
}