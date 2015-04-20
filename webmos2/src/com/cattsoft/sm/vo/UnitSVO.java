package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class UnitSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String unitId;

	private String unitTypeId;

	private String unitName;

	private String standardConvertRate;

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	public String getUnitId() {
		return unitId;
	}

	public void setUnitTypeId(String unitTypeId) {
		this.unitTypeId = unitTypeId;
	}
	
	public String getUnitTypeId() {
		return unitTypeId;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getUnitName() {
		return unitName;
	}

	public void setStandardConvertRate(String standardConvertRate) {
		this.standardConvertRate = standardConvertRate;
	}
	
	public String getStandardConvertRate() {
		return standardConvertRate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof UnitSVO) {
			UnitSVO another = (UnitSVO) obj;
			equals = new EqualsBuilder()
					.append(unitId, another.getUnitId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(unitId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("unitId", getUnitId())
				.toString();
	}
}