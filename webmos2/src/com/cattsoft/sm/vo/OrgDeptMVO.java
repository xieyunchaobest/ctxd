package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-30 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class OrgDeptMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private String deptId;

    private String deptName;

    private String partyId;

    private String areaId;

    private String parentDeptId;

    private String localNetId;

    private String servDeptId;

    private String adminStaffId;

    private String deptType;

    private String rootFlag;

    private String sts;

    private Date stsDate;

    private Date createDate;

    private String deptDesc;

    private String certCode;

    private String certTypeId;

    private String contactAddr;

    private String postalCode;

    private String standardCode;

    private String jurPerson;

    private String netType;

    private String taxNbr;

    private String remarks;
    
    private Date certExpDate;
    
    private String companySize;
    
    private String contactId;
    
    private String partyIdentityId;

    private java.util.List certTypeList;

    // 2005-7-19 mjh the source url visiting the tree

    public String getAdminStaffId() {
        return adminStaffId;
    }

    public void setAdminStaffId(String adminStaffId) {
        this.adminStaffId = adminStaffId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCertTypeId() {
        return certTypeId;
    }

    public void setCertTypeId(String certTypeId) {
        this.certTypeId = certTypeId;
    }

    public java.util.List getCertTypeList() {
        return certTypeList;
    }

    public void setCertTypeList(java.util.List certTypeList) {
        this.certTypeList = certTypeList;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getJurPerson() {
        return jurPerson;
    }

    public void setJurPerson(String jurPerson) {
        this.jurPerson = jurPerson;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(String parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRootFlag() {
        return rootFlag;
    }

    public void setRootFlag(String rootFlag) {
        this.rootFlag = rootFlag;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts; 
    }

    public Date getStsDate() {
        return stsDate;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }

    public String getTaxNbr() {
        return taxNbr;
    }

    public void setTaxNbr(String taxNbr) {
        this.taxNbr = taxNbr;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public Date getCertExpDate() {
        return certExpDate;
    }

    public void setCertExpDate(Date certExpDate) {
        this.certExpDate = certExpDate;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getPartyIdentityId() {
        return partyIdentityId;
    }

    public void setPartyIdentityId(String partyIdentityId) {
        this.partyIdentityId = partyIdentityId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

}
