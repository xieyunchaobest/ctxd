package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class VirtualTeamSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String virtualTeamId;

	private String partyId;

	private String deptId;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setVirtualTeamId(String virtualTeamId) {
		this.virtualTeamId = virtualTeamId;
	}
	
	public String getVirtualTeamId() {
		return virtualTeamId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	
	public String getPartyId() {
		return partyId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	public String getDeptId() {
		return deptId;
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
		if (obj != null && obj instanceof VirtualTeamSVO) {
			VirtualTeamSVO another = (VirtualTeamSVO) obj;
			equals = new EqualsBuilder()
					.append(virtualTeamId, another.getVirtualTeamId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(virtualTeamId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("virtualTeamId", getVirtualTeamId())
				.toString();
	}
}