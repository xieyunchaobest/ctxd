package com.cattsoft.sm.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class OtherCharacterApplyRangeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String characterApplyRangeId;

	private String characteristicId;

	private String applyType;

	public void setCharacterApplyRangeId(String characterApplyRangeId) {
		this.characterApplyRangeId = characterApplyRangeId;
	}
	
	public String getCharacterApplyRangeId() {
		return characterApplyRangeId;
	}

	public void setCharacteristicId(String characteristicId) {
		this.characteristicId = characteristicId;
	}
	
	public String getCharacteristicId() {
		return characteristicId;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	public String getApplyType() {
		return applyType;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof OtherCharacterApplyRangeSVO) {
			OtherCharacterApplyRangeSVO another = (OtherCharacterApplyRangeSVO) obj;
			equals = new EqualsBuilder()
					.append(characterApplyRangeId, another.getCharacterApplyRangeId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(characterApplyRangeId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("characterApplyRangeId", getCharacterApplyRangeId())
				.toString();
	}
}