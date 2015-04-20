package com.cattsoft.sm.vo;

import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class ContactMediumSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String contactId;

    private String partyId;

    private String contactAddr;

    private String contactNbrType;

    private String contactNbr;

    private String faxNbr;

    private String emailAddr;

    private String emailProtocol;

    private String postalCode;

    private String sts;

    private Date stsDate;

    private Date createDate;

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactNbrType(String contactNbrType) {
        this.contactNbrType = contactNbrType;
    }

    public String getContactNbrType() {
        return contactNbrType;
    }

    public void setContactNbr(String contactNbr) {
        this.contactNbr = contactNbr;
    }

    public String getContactNbr() {
        return contactNbr;
    }

    public void setFaxNbr(String faxNbr) {
        this.faxNbr = faxNbr;
    }

    public String getFaxNbr() {
        return faxNbr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailProtocol(String emailProtocol) {
        this.emailProtocol = emailProtocol;
    }

    public String getEmailProtocol() {
        return emailProtocol;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
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
        if (obj != null && obj instanceof ContactMediumSVO) {
            ContactMediumSVO another = (ContactMediumSVO) obj;
            equals = new EqualsBuilder().append(contactId, another.getContactId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(contactId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("contactId", getContactId()).toString();
    }
}