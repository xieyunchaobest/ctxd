package com.cattsoft.sm.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.cattsoft.pub.vo.GenericVO;

public class ContactMediumInfoMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private List userInfoList;

    // cust
    private String custId;

    // relType
    private String relType;

    private String partyRelId;

    // String
    private String[] contactIdTmp;

    private String[] contactAddrTmp;

    private String[] contactNbrTypeTmp;

    private String[] contactNbrTmp;

    private String[] faxNbrTmp;

    private String[] emailAddrTmp;

    private String[] emailProtocolTmp;

    private String[] postalCodeTmp;

    // contact_medium 表
    private String contactId;

    private String partyRoleTypeId;

    private String partyId;

    private String contactAddr;

    private String contactNbrType;

    private String contactNbr;

    private String faxNbr;

    private String emailAddr;

    private String emailProtocol;

    private String postalCode;

    private String sts;

    private Timestamp stsDate;

    private Timestamp createDate;

    // party 表
    private String partyId1;

    private String jurPerson;

    private String netType;

    private String taxNbr;

    private String companySize;

    private Date startTime;

    private String startTime1;

    private String companyCharacter;

    private String companyStructure;

    private String remarks;

    private String gander;

    private Date birthday;

    private String birthday1;

    private String age;

    private String nativePlace;

    private String nationality;

    private String politicalStatus;

    private String educLevel;

    private String graduateSchool;

    private String maritalStatus;

    private String domesticRelation;

    private Date maritalDate;

    private String maritalDate1;

    private String companyName;

    private String department;

    private String position;

    private String workAddr;

    private String funcDesc;

    private String salary;

    private String charType;

    private String favorite;

    private String telecomAttitude;

    private String workExper;

    private String branchId;

    private String areaId;

    private String localNetId;

    private String servDeptId;

    private String name;

    private String nameBrief;

    private String nameOther;

    private String partyType;

    private String standardCode;

    // private String sts;

    // private Date stsDate;

    // private Date createDate;

    private String communicationSpecialty;

    private String mainProduction;

    private String totalAsset;

    private String webAddress;

    private Timestamp lastModDate;

    private String identifyCustName;

    private String identifyCertType;

    private String identifyCertCode;
  
    private String partyIdentityId;
    // 证件类型
    private String certTypeId;

    // 证件号码
    private String certCode;

    public String getIdentifyCertCode() {
        return identifyCertCode;
    }

    public void setIdentifyCertCode(String identifyCertCode) {
        this.identifyCertCode = identifyCertCode;
    }

    public String getIdentifyCertType() {
        return identifyCertType;
    }

    public void setIdentifyCertType(String identifyCertType) {
        this.identifyCertType = identifyCertType;
    }

    public String getIdentifyCustName() {
        return identifyCustName;
    }

    public void setIdentifyCustName(String identifyCustName) {
        this.identifyCustName = identifyCustName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCharType() {
        return charType;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

    public String getCommunicationSpecialty() {
        return communicationSpecialty;
    }

    public void setCommunicationSpecialty(String communicationSpecialty) {
        this.communicationSpecialty = communicationSpecialty;
    }

    public String getCompanyCharacter() {
        return companyCharacter;
    }

    public void setCompanyCharacter(String companyCharacter) {
        this.companyCharacter = companyCharacter;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getCompanyStructure() {
        return companyStructure;
    }

    public void setCompanyStructure(String companyStructure) {
        this.companyStructure = companyStructure;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactNbr() {
        return contactNbr;
    }

    public void setContactNbr(String contactNbr) {
        this.contactNbr = contactNbr;
    }

    public String getContactNbrType() {
        return contactNbrType;
    }

    public void setContactNbrType(String contactNbrType) {
        this.contactNbrType = contactNbrType;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDomesticRelation() {
        return domesticRelation;
    }

    public void setDomesticRelation(String domesticRelation) {
        this.domesticRelation = domesticRelation;
    }

    public String getEducLevel() {
        return educLevel;
    }

    public void setEducLevel(String educLevel) {
        this.educLevel = educLevel;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getEmailProtocol() {
        return emailProtocol;
    }

    public void setEmailProtocol(String emailProtocol) {
        this.emailProtocol = emailProtocol;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getFaxNbr() {
        return faxNbr;
    }

    public void setFaxNbr(String faxNbr) {
        this.faxNbr = faxNbr;
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }

    public String getGander() {
        return gander;
    }

    public void setGander(String gander) {
        this.gander = gander;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getJurPerson() {
        return jurPerson;
    }

    public void setJurPerson(String jurPerson) {
        this.jurPerson = jurPerson;
    }

    public Timestamp getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Timestamp lastModDate) {
        this.lastModDate = lastModDate;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getMainProduction() {
        return mainProduction;
    }

    public void setMainProduction(String mainProduction) {
        this.mainProduction = mainProduction;
    }

    public Date getMaritalDate() {
        return maritalDate;
    }

    public void setMaritalDate(Date maritalDate) {
        this.maritalDate = maritalDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameBrief() {
        return nameBrief;
    }

    public void setNameBrief(String nameBrief) {
        this.nameBrief = nameBrief;
    }

    public String getNameOther() {
        return nameOther;
    }

    public void setNameOther(String nameOther) {
        this.nameOther = nameOther;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(String partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Timestamp getStsDate() {
        return stsDate;
    }

    public void setStsDate(Timestamp stsDate) {
        this.stsDate = stsDate;
    }

    public String getTaxNbr() {
        return taxNbr;
    }

    public void setTaxNbr(String taxNbr) {
        this.taxNbr = taxNbr;
    }

    public String getTelecomAttitude() {
        return telecomAttitude;
    }

    public void setTelecomAttitude(String telecomAttitude) {
        this.telecomAttitude = telecomAttitude;
    }

    public String getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(String totalAsset) {
        this.totalAsset = totalAsset;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getWorkAddr() {
        return workAddr;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }

    public String getWorkExper() {
        return workExper;
    }

    public void setWorkExper(String workExper) {
        this.workExper = workExper;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String[] getContactAddrTmp() {
        return contactAddrTmp;
    }

    public void setContactAddrTmp(String[] contactAddrTmp) {
        this.contactAddrTmp = contactAddrTmp;
    }

    public String[] getContactNbrTmp() {
        return contactNbrTmp;
    }

    public void setContactNbrTmp(String[] contactNbrTmp) {
        this.contactNbrTmp = contactNbrTmp;
    }

    public String[] getContactNbrTypeTmp() {
        return contactNbrTypeTmp;
    }

    public void setContactNbrTypeTmp(String[] contactNbrTypeTmp) {
        this.contactNbrTypeTmp = contactNbrTypeTmp;
    }

    public String[] getEmailAddrTmp() {
        return emailAddrTmp;
    }

    public void setEmailAddrTmp(String[] emailAddrTmp) {
        this.emailAddrTmp = emailAddrTmp;
    }

    public String[] getEmailProtocolTmp() {
        return emailProtocolTmp;
    }

    public void setEmailProtocolTmp(String[] emailProtocolTmp) {
        this.emailProtocolTmp = emailProtocolTmp;
    }

    public String[] getFaxNbrTmp() {
        return faxNbrTmp;
    }

    public void setFaxNbrTmp(String[] faxNbrTmp) {
        this.faxNbrTmp = faxNbrTmp;
    }

    public String[] getPostalCodeTmp() {
        return postalCodeTmp;
    }

    public void setPostalCodeTmp(String[] postalCodeTmp) {
        this.postalCodeTmp = postalCodeTmp;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public String getPartyId1() {
        return partyId1;
    }

    public void setPartyId1(String partyId1) {
        this.partyId1 = partyId1;
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

    public String getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(String birthday1) {
        this.birthday1 = birthday1;
    }

    public String getMaritalDate1() {
        return maritalDate1;
    }

    public void setMaritalDate1(String maritalDate1) {
        this.maritalDate1 = maritalDate1;
    }

    public String getStartTime1() {
        return startTime1;
    }

    public void setStartTime1(String startTime1) {
        this.startTime1 = startTime1;
    }

    public List getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List userInfoList) {
        this.userInfoList = userInfoList;
    }

    public String[] getContactIdTmp() {
        return contactIdTmp;
    }

    public void setContactIdTmp(String[] contactIdTmp) {
        this.contactIdTmp = contactIdTmp;
    }

    public String getPartyRelId() {
        return partyRelId;
    }

    public void setPartyRelId(String partyRelId) {
        this.partyRelId = partyRelId;
    }

    public String getPartyIdentityId() {
        return partyIdentityId;
    }

    public void setPartyIdentityId(String partyIdentityId) {
        this.partyIdentityId = partyIdentityId;
    }
    
}
