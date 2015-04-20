package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class PartyIdentitySVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String partyIdentityId;

    private String certTypeId;

    private String localNetId;

    private String partyId;

    private String certCode;

    private String certAddr;

    private Date certExpDate;

    private String detailInfo;

    private String checkInfo;

    private String sts;

    private Date stsDate;

    private Date createDate;

    public void setPartyIdentityId(String partyIdentityId) {
        this.partyIdentityId = partyIdentityId;
    }
    
    public String getPartyIdentityId() {
        return partyIdentityId;
    }

    public void setCertTypeId(String certTypeId) {
        this.certTypeId = certTypeId;
    }
    
    public String getCertTypeId() {
        return certTypeId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }
    
    public String getLocalNetId() {
        return localNetId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
    
    public String getPartyId() {
        return partyId;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }
    
    public String getCertCode() {
        return certCode;
    }

    public void setCertAddr(String certAddr) {
        this.certAddr = certAddr;
    }
    
    public String getCertAddr() {
        return certAddr;
    }

    public void setCertExpDate(Date certExpDate) {
        this.certExpDate = certExpDate;
    }
    
    public Date getCertExpDate() {
        return certExpDate;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }
    
    public String getDetailInfo() {
        return detailInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }
    
    public String getCheckInfo() {
        return checkInfo;
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
        if (obj != null && obj instanceof PartyIdentitySVO) {
            PartyIdentitySVO another = (PartyIdentitySVO) obj;
            equals = new EqualsBuilder()
                    .append(partyIdentityId, another.getPartyIdentityId())
                    .isEquals();
        }
        return equals;
    }
    
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(partyIdentityId)
                .toHashCode();
    }
    
    public String toString() {
        return new ToStringBuilder(this)
                .append("partyIdentityId", getPartyIdentityId())
                .toString();
    }
}