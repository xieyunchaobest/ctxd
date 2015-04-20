package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class FuncRangeTypeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String funcRangeTypeId;

	private String funcNodeId;

	private String dataRangeTypeId;

	private String columnName;

	private String version;

	public void setFuncRangeTypeId(String funcRangeTypeId) {
		this.funcRangeTypeId = funcRangeTypeId;
	}
	
	public String getFuncRangeTypeId() {
		return funcRangeTypeId;
	}

	public void setFuncNodeId(String funcNodeId) {
		this.funcNodeId = funcNodeId;
	}
	
	public String getFuncNodeId() {
		return funcNodeId;
	}

	public void setDataRangeTypeId(String dataRangeTypeId) {
		this.dataRangeTypeId = dataRangeTypeId;
	}
	
	public String getDataRangeTypeId() {
		return dataRangeTypeId;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return version;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof FuncRangeTypeSVO) {
			FuncRangeTypeSVO another = (FuncRangeTypeSVO) obj;
			equals = new EqualsBuilder()
					.append(funcRangeTypeId, another.getFuncRangeTypeId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(funcRangeTypeId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("funcRangeTypeId", getFuncRangeTypeId())
				.toString();
	}
}