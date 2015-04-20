package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class StatusSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String tableName;

	private String columnName;

	private String stsId;

	private String stsWords;

	private String orderId;
	
	private ActionLogSVO actionLog=null;

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setStsId(String stsId) {
		this.stsId = stsId;
	}
	
	public String getStsId() {
		return stsId;
	}

	public void setStsWords(String stsWords) {
		this.stsWords = stsWords;
	}
	
	public String getStsWords() {
		return stsWords;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof StatusSVO) {
			StatusSVO another = (StatusSVO) obj;
			equals = new EqualsBuilder()
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.toString();
	}
}