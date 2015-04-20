package com.cattsoft.sm.vo;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class OtherCharacterRelSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String characterRelId;

	private String characteristicId;

	private String relCharacteristicId;

	private String relType;

	private String othCharacteristicId;

	private String sts;

	private Timestamp stsDate;

	private Timestamp createDate;

	public void setCharacterRelId(String characterRelId) {
		this.characterRelId = characterRelId;
	}
	
	public String getCharacterRelId() {
		return characterRelId;
	}

	public void setCharacteristicId(String characteristicId) {
		this.characteristicId = characteristicId;
	}
	
	public String getCharacteristicId() {
		return characteristicId;
	}

	public void setRelCharacteristicId(String relCharacteristicId) {
		this.relCharacteristicId = relCharacteristicId;
	}
	
	public String getRelCharacteristicId() {
		return relCharacteristicId;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}
	
	public String getRelType() {
		return relType;
	}

	public void setOthCharacteristicId(String othCharacteristicId) {
		this.othCharacteristicId = othCharacteristicId;
	}
	
	public String getOthCharacteristicId() {
		return othCharacteristicId;
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

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof OtherCharacterRelSVO) {
			OtherCharacterRelSVO another = (OtherCharacterRelSVO) obj;
			equals = new EqualsBuilder()
					.append(characterRelId, another.getCharacterRelId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(characterRelId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("characterRelId", getCharacterRelId())
				.toString();
	}
}