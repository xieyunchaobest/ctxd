package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class PartyAddrSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String partyAddrId;

	private String partyId;

	private String localNetId;

	private String addrType;

	private String addrSts;

	private Date stsDate;

	private Date createDate;

	private String situated;

	public void setPartyAddrId(String partyAddrId) {
		this.partyAddrId = partyAddrId;
	}
	
	public String getPartyAddrId() {
		return partyAddrId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	
	public String getPartyId() {
		return partyId;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}
	
	public String getLocalNetId() {
		return localNetId;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}
	
	public String getAddrType() {
		return addrType;
	}

	public void setAddrSts(String addrSts) {
		this.addrSts = addrSts;
	}
	
	public String getAddrSts() {
		return addrSts;
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

	public void setSituated(String situated) {
		this.situated = situated;
	}
	
	public String getSituated() {
		return situated;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof PartyAddrSVO) {
			PartyAddrSVO another = (PartyAddrSVO) obj;
			equals = new EqualsBuilder()
					.append(partyAddrId, another.getPartyAddrId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(partyAddrId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("partyAddrId", getPartyAddrId())
				.toString();
	}
}