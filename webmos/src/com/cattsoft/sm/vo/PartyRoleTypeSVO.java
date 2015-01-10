package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class PartyRoleTypeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String partyRoleTypeId;

	private String name;

	private String tableName;

	private String authFlag;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setPartyRoleTypeId(String partyRoleTypeId) {
		this.partyRoleTypeId = partyRoleTypeId;
	}
	
	public String getPartyRoleTypeId() {
		return partyRoleTypeId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	}
	
	public String getAuthFlag() {
		return authFlag;
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
		if (obj != null && obj instanceof PartyRoleTypeSVO) {
			PartyRoleTypeSVO another = (PartyRoleTypeSVO) obj;
			equals = new EqualsBuilder()
					.append(partyRoleTypeId, another.getPartyRoleTypeId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(partyRoleTypeId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("partyRoleTypeId", getPartyRoleTypeId())
				.toString();
	}
}