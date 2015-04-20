package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class HandleTypeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String handleTypeId;

	private String name;

	private String systemName;

	private Date createDate;
	
	private ActionLogSVO actionLog=null;

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setHandleTypeId(String handleTypeId) {
		this.handleTypeId = handleTypeId;
	}
	
	public String getHandleTypeId() {
		return handleTypeId;
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

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof HandleTypeSVO) {
			HandleTypeSVO another = (HandleTypeSVO) obj;
			equals = new EqualsBuilder()
					.append(handleTypeId, another.getHandleTypeId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(handleTypeId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("handleTypeId", getHandleTypeId())
				.toString();
	}
}