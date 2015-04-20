package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class SysAreaConfigSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String sysAreaConfigId;

	private String configId;

	private String spAreaId;

	private String curValue;

	private String valueDesc;
	
	private ActionLogSVO actionLog=null;

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setSysAreaConfigId(String sysAreaConfigId) {
		this.sysAreaConfigId = sysAreaConfigId;
	}
	
	public String getSysAreaConfigId() {
		return sysAreaConfigId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}
	
	public String getConfigId() {
		return configId;
	}

	public void setSpAreaId(String spAreaId) {
		this.spAreaId = spAreaId;
	}
	
	public String getSpAreaId() {
		return spAreaId;
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

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof SysAreaConfigSVO) {
			SysAreaConfigSVO another = (SysAreaConfigSVO) obj;
			equals = new EqualsBuilder()
					.append(sysAreaConfigId, another.getSysAreaConfigId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(sysAreaConfigId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("sysAreaConfigId", getSysAreaConfigId())
				.toString();
	}
}