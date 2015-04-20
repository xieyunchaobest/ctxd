package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class RuleAreaSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String ruleAreaId;

	private String name;

	private String localNetId;

	private String areaId;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setRuleAreaId(String ruleAreaId) {
		this.ruleAreaId = ruleAreaId;
	}
	
	public String getRuleAreaId() {
		return ruleAreaId;
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
		if (obj != null && obj instanceof RuleAreaSVO) {
			RuleAreaSVO another = (RuleAreaSVO) obj;
			equals = new EqualsBuilder()
					.append(ruleAreaId, another.getRuleAreaId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(ruleAreaId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("ruleAreaId", getRuleAreaId())
				.toString();
	}
}