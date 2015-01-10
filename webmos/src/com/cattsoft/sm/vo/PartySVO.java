package com.cattsoft.sm.vo;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class PartySVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String partyId;

    private String branchId;

    private String areaId;

    private String localNetId;

    private String servDeptId;

    private String partyType;

    private String name;

    private String nameBrief;

    private String nameOther;

    private String jurPerson;

    private String netType;

    private String taxNbr;

    private String companySize;

    private Date startTime;

    private String companyCharacter;

    private String companyStructure;

    private String remarks;

    private String mainProduction;

    private String totalAsset;

    private String gander;

    private Date birthday;

    private String age;

    private String nativePlace;

    private String nationality;

    private String politicalStatus;

    private String educLevel;

    private String graduateSchool;

    private String maritalStatus;

    private String domesticRelation;

    private Date maritalDate;

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

    private String standardCode;

    private Timestamp createDate;

    private String communicationSpecialty;

    private String webAddress;

    private Timestamp lastModDate;

    private String sts;

    private Timestamp stsDate;

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNameBrief(String nameBrief) {
        this.nameBrief = nameBrief;
    }

    public String getNameBrief() {
        return nameBrief;
    }

    public void setNameOther(String nameOther) {
        this.nameOther = nameOther;
    }

    public String getNameOther() {
        return nameOther;
    }

    public void setJurPerson(String jurPerson) {
        this.jurPerson = jurPerson;
    }

    public String getJurPerson() {
        return jurPerson;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getNetType() {
        return netType;
    }

    public void setTaxNbr(String taxNbr) {
        this.taxNbr = taxNbr;
    }

    public String getTaxNbr() {
        return taxNbr;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setCompanyCharacter(String companyCharacter) {
        this.companyCharacter = companyCharacter;
    }

    public String getCompanyCharacter() {
        return companyCharacter;
    }

    public void setCompanyStructure(String companyStructure) {
        this.companyStructure = companyStructure;
    }

    public String getCompanyStructure() {
        return companyStructure;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setMainProduction(String mainProduction) {
        this.mainProduction = mainProduction;
    }

    public String getMainProduction() {
        return mainProduction;
    }

    public void setTotalAsset(String totalAsset) {
        this.totalAsset = totalAsset;
    }

    public String getTotalAsset() {
        return totalAsset;
    }

    public void setGander(String gander) {
        this.gander = gander;
    }

    public String getGander() {
        return gander;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setEducLevel(String educLevel) {
        this.educLevel = educLevel;
    }

    public String getEducLevel() {
        return educLevel;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setDomesticRelation(String domesticRelation) {
        this.domesticRelation = domesticRelation;
    }

    public String getDomesticRelation() {
        return domesticRelation;
    }

    public void setMaritalDate(Date maritalDate) {
        this.maritalDate = maritalDate;
    }

    public Date getMaritalDate() {
        return maritalDate;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }

    public String getWorkAddr() {
        return workAddr;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

    public String getCharType() {
        return charType;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setTelecomAttitude(String telecomAttitude) {
        this.telecomAttitude = telecomAttitude;
    }

    public String getTelecomAttitude() {
        return telecomAttitude;
    }

    public void setWorkExper(String workExper) {
        this.workExper = workExper;
    }

    public String getWorkExper() {
        return workExper;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setCommunicationSpecialty(String communicationSpecialty) {
        this.communicationSpecialty = communicationSpecialty;
    }

    public String getCommunicationSpecialty() {
        return communicationSpecialty;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSts() {
        return sts;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Timestamp lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Timestamp getStsDate() {
        return stsDate;
    }

    public void setStsDate(Timestamp stsDate) {
        this.stsDate = stsDate;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof PartySVO) {
            PartySVO another = (PartySVO) obj;
            equals = new EqualsBuilder().append(partyId, another.getPartyId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(partyId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("partyId", getPartyId()).toString();
    }
}