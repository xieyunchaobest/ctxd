package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class DataRangeTypeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String dataRangeTypeId;

	private String dataRangeTypeName;

	private String dataRangePattern;

	private String setRangeComp;

	private String version;

	public void setDataRangeTypeId(String dataRangeTypeId) {
		this.dataRangeTypeId = dataRangeTypeId;
	}
	
	public String getDataRangeTypeId() {
		return dataRangeTypeId;
	}

	public void setDataRangeTypeName(String dataRangeTypeName) {
		this.dataRangeTypeName = dataRangeTypeName;
	}
	
	public String getDataRangeTypeName() {
		return dataRangeTypeName;
	}

	public void setDataRangePattern(String dataRangePattern) {
		this.dataRangePattern = dataRangePattern;
	}
	
	public String getDataRangePattern() {
		return dataRangePattern;
	}

	public void setSetRangeComp(String setRangeComp) {
		this.setRangeComp = setRangeComp;
	}
	
	public String getSetRangeComp() {
		return setRangeComp;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return version;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof DataRangeTypeSVO) {
			DataRangeTypeSVO another = (DataRangeTypeSVO) obj;
			equals = new EqualsBuilder()
					.append(dataRangeTypeId, another.getDataRangeTypeId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(dataRangeTypeId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("dataRangeTypeId", getDataRangeTypeId())
				.toString();
	}
}