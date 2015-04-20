package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class SysConfigSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String configId;

	private String name;

	private String systemName;

	private String configType;

	private String curValue;

	private String valueDesc;

	private Date createDate;
	
	private ActionLogSVO actionLog=null;

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}
	
	public String getConfigId() {
		return configId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	public String getSystemName() {
		return systemName;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}
	
	public String getConfigType() {
		return configType;
	}

	public void setCurValue(String curValue) {
		this.curValue = curValue;
	}
	
	public String getCurValue() {
		return curValue;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}
	
	public String getValueDesc() {
		return valueDesc;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof SysConfigSVO) {
			SysConfigSVO another = (SysConfigSVO) obj;
			equals = new EqualsBuilder()
					.append(configId, another.getConfigId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(configId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("configId", getConfigId())
				.toString();
	}
}