package com.cattsoft.sm.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class PartyRoleSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String partyRoleTypeId;

    private String partyRoleId;

    private String partyId;

    private String localNetId;

    public void setPartyRoleTypeId(String partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public String getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleId(String partyRoleId) {
        this.partyRoleId = partyRoleId;
    }

    public String getPartyRoleId() {
        return partyRoleId;
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

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof PartyRoleSVO) {
            PartyRoleSVO another = (PartyRoleSVO) obj;
            equals = new EqualsBuilder().append(partyRoleTypeId, another.getPartyRoleTypeId())
                    .append(partyRoleId, another.getPartyRoleId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(partyRoleTypeId).append(partyRoleId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("partyRoleTypeId", getPartyRoleTypeId()).append(
                "partyRoleId", getPartyRoleId()).toString();
    }
}