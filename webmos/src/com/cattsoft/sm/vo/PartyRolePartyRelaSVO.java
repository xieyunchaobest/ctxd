package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class PartyRolePartyRelaSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String relaId;

    private String partyId;

    private String partyRoleId;

    private String relType;

    private String sts;

    private Date stsDate;

    private String partyRoleTypeId;

    public void setRelaId(String relaId) {
        this.relaId = relaId;
    }

    public String getRelaId() {
        return relaId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyRoleId(String partyRoleId) {
        this.partyRoleId = partyRoleId;
    }

    public String getPartyRoleId() {
        return partyRoleId;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public String getRelType() {
        return relType;
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

    public void setPartyRoleTypeId(String partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public String getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof PartyRolePartyRelaSVO) {
            PartyRolePartyRelaSVO another = (PartyRolePartyRelaSVO) obj;
            equals = new EqualsBuilder().append(relaId, another.getRelaId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(relaId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("relaId", getRelaId()).toString();
    }
}