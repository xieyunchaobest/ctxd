package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class PartyCharaSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String partyCharaId;

	private String partyId;

	private String characteristicId;

	private String charaValue;

	private String remarks;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setPartyCharaId(String partyCharaId) {
		this.partyCharaId = partyCharaId;
	}
	
	public String getPartyCharaId() {
		return partyCharaId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	
	public String getPartyId() {
		return partyId;
	}

	public void setCharacteristicId(String characteristicId) {
		this.characteristicId = characteristicId;
	}
	
	public String getCharacteristicId() {
		return characteristicId;
	}

	public void setCharaValue(String charaValue) {
		this.charaValue = charaValue;
	}
	
	public String getCharaValue() {
		return charaValue;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}
	
	public String getSts() {
		return sts;
	}

	public void setStsDate(Date stsDate) {
		this.stsDate = stsDate;
	}
	
	public Date getStsDate() {
		return stsDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof PartyCharaSVO) {
			PartyCharaSVO another = (PartyCharaSVO) obj;
			equals = new EqualsBuilder()
					.append(partyCharaId, another.getPartyCharaId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(partyCharaId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("partyCharaId", getPartyCharaId())
				.toString();
	}
}