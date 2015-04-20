package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.pub.vo.GenericVO;

public class AreaSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String areaId;

	private String localNetId;

	private String abbrevName;

	private String name;

	private String iscenter;

	private String sts;

	private Date stsDate;

	private Date createDate;
	
	//added by yangkai 增加日志记录 2009-6-25  
    private ActionLogSVO actionLog=null;

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}
	
	public String getLocalNetId() {
		return localNetId;
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

	public void setIscenter(String iscenter) {
		this.iscenter = iscenter;
	}
	
	public String getIscenter() {
		return iscenter;
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
		if (obj != null && obj instanceof AreaSVO) {
			AreaSVO another = (AreaSVO) obj;
			equals = new EqualsBuilder()
					.append(areaId, another.getAreaId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(areaId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("areaId", getAreaId())
				.toString();
	}

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}
}