package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class ServDeptSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String servDeptId;

	private String localNetId;

	private String areaId;

	private String abbrevName;

	private String name;

	private String sts;
	
	private String areaType;

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	private Date stsDate;

	private Date createDate;
	
	private ActionLogSVO actionLog=null;

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setServDeptId(String servDeptId) {
		this.servDeptId = servDeptId;
	}
	
	public String getServDeptId() {
		return servDeptId;
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
		if (obj != null && obj instanceof ServDeptSVO) {
			ServDeptSVO another = (ServDeptSVO) obj;
			equals = new EqualsBuilder()
					.append(servDeptId, another.getServDeptId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(servDeptId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("servDeptId", getServDeptId())
				.toString();
	}
}