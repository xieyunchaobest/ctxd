package com.cattsoft.sm.vo;

import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class PartyRelSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String partyRelId;

    private String partyId1;

    private String partyId2;

    private String relType;

    private Date createDate;

    private String sts;

    private Date stsDate;

    private String remarks;

    public void setPartyRelId(String partyRelId) {
        this.partyRelId = partyRelId;
    }

    public String getPartyRelId() {
        return partyRelId;
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

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof PartyRelSVO) {
            PartyRelSVO another = (PartyRelSVO) obj;
            equals = new EqualsBuilder().append(partyRelId, another.getPartyRelId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(partyRelId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("partyRelId", getPartyRelId()).toString();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPartyId1() {
        return partyId1;
    }

    public String getPartyId2() {
        return partyId2;
    }

    public void setPartyId1(String partyId1) {
        this.partyId1 = partyId1;
    }

    public void setPartyId2(String partyId2) {
        this.partyId2 = partyId2;
    }
}