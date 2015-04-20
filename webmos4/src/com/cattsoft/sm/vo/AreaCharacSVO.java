package com.cattsoft.sm.vo;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class AreaCharacSVO extends GenericVO {
	
	
	private static final long serialVersionUID = 1L;

	private String areaCharacId;

	private String characteristicId;

	private String regionType;

	private String regionId;

	private String value;

	private String sts;

	private Timestamp stsDate;

	public void setAreaCharacId(String areaCharacId) {
		this.areaCharacId = areaCharacId;
	}
	
	public String getAreaCharacId() {
		return areaCharacId;
	}

	public void setCharacteristicId(String characteristicId) {
		this.characteristicId = characteristicId;
	}
	
	public String getCharacteristicId() {
		return characteristicId;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}
	
	public String getRegionType() {
		return regionType;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	public String getRegionId() {
		return regionId;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}
	
	public String getSts() {
		return sts;
	}

	public void setStsDate(Timestamp stsDate) {
		this.stsDate = stsDate;
	}
	
	public Timestamp getStsDate() {
		return stsDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof AreaCharacSVO) {
			AreaCharacSVO another = (AreaCharacSVO) obj;
			equals = new EqualsBuilder()
					.append(areaCharacId, another.getAreaCharacId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(areaCharacId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("areaCharacId", getAreaCharacId())
				.toString();
	}
}