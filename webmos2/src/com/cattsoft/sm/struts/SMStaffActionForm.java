package com.cattsoft.sm.struts;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-1 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SMStaffActionForm extends ActionForm {
    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

    }

    public SMStaffActionForm() {

    }

    private String[] choses;

    /**
     * 属性staffId 标识 属性
     */
    private String staffId;

    /**
     * 属性position
     */
    private String position;

    /**
     * 属性standard
     */
    private String standardCode;

    private String srcUrl;

    /**
     * 属性sts
     */
    private String sts;

    /**
     * 属性partyId
     */
    private String partyId;

    /**
     * 属性name
     */
    private String name;

    /**
     * 属性nameBrief
     */
    private String nameBrief;

    /**
     * 属性nameOther
     */
    private String nameOther;

    /**
     * 属性partyType
     */
    private String partyType;

    /**
     * 属性certCode
     */
    private String certCode;

    /**
     * 属性contactAddr
     */
    private String contactAddr;

    /**
     * 属性postalCode
     */
    private String postalCode;

    /**
     * 属性gender
     */
    private String gander;

    /**
     * 属性gradSchool
     */
    private String graduateSchool;

    /**
     * 属性nativePlace
     */
    private String nativePlace;

    /**
     * 属性educLevel
     */
    private String educLevel;

    /**
     * 属性maritalStatus
     */
    private String maritalStatus;

    /**
     * 属性companyName
     */
    private String companyName;

    /**
     * 属性workAddr
     */
    private String workAddr;

    /**
     * 属性funcDesc
     */
    private String funcDesc;

    /**
     * 属性salary
     */
    private String salary;

    /**
     * 属性charType
     */
    private String charType;

    /**
     * 属性favorite
     */
    private String favorite;

    /**
     * 属性workExper
     */
    private String workExper;

    /**
     * 属性jurPerson
     */
    private String jurPerson;

    /**
     * 属性netType
     */
    private String netType;

    /**
     * 属性taxNbr
     */
    private String taxNbr;

    /**
     * 属性companySize
     */
    private String companySize;

    /**
     * 属性remarks
     */
    private String remarks;

    private Date certExpDate;

    private Timestamp createDate;

    private Timestamp lastModDate;

    private Date maritalDate;

    private Timestamp stsDate;

    private String areaId;

    private String certTypeId;

    private String deptId;

    private String deptName;

    private String localNetId;

    private String nation;

    private Date birthday;

    private String politicalStatus;

    private String birthday1;

    private String maritalDate1;

    private String certExpDate1;

    private java.util.List localNetList = new java.util.ArrayList();

    private java.util.List areaList = new java.util.ArrayList();

    private java.util.List certTypeList = new java.util.ArrayList();

    private java.util.List deptList = new java.util.ArrayList();

    /**
     * 属性politics 持久化 属性
     */
    private String politics;

    /**
     * 属性station 持久化 属性
     */
    private String station;

    private List localNetSel;

    private List areaSel;

    private Integer staffLevel;
    
    private String oldSysUserName;//旧登录名
    
    private String sysUserName;//新登录名
    
    private String sysUserName1;//确认新登录名
    
	public String getOldSysUserName() {
		return oldSysUserName;
	}

	public void setOldSysUserName(String oldSysUserName) {
		this.oldSysUserName = oldSysUserName;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getSysUserName1() {
		return sysUserName1;
	}

	public void setSysUserName1(String sysUserName1) {
		this.sysUserName1 = sysUserName1;
	}

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public java.util.List getAreaList() {
        return areaList;
    }

    public void setAreaList(java.util.List areaList) {
        this.areaList = areaList;
    }

    public List getAreaSel() {
        return areaSel;
    }

    public void setAreaSel(List areaSel) {
        this.areaSel = areaSel;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public java.util.List getCertTypeList() {
        return certTypeList;
    }

    public void setCertTypeList(java.util.List certTypeList) {
        this.certTypeList = certTypeList;
    }

    public String getCharType() {
        return charType;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

    public String[] getChoses() {
        return choses;
    }

    public void setChoses(String[] choses) {
        this.choses = choses;
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

    public java.util.List getDeptList() {
        return deptList;
    }

    public void setDeptList(java.util.List deptList) {
        this.deptList = deptList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEducLevel() {
        return educLevel;
    }

    public void setEducLevel(String educLevel) {
        this.educLevel = educLevel;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
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

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
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

    public java.util.List getLocalNetList() {
        return localNetList;
    }

    public void setLocalNetList(java.util.List localNetList) {
        this.localNetList = localNetList;
    }

    public List getLocalNetSel() {
        return localNetSel;
    }

    public void setLocalNetSel(List localNetSel) {
        this.localNetSel = localNetSel;
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
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

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCertTypeId() {
        return certTypeId;
    }

    public void setCertTypeId(String certTypeId) {
        this.certTypeId = certTypeId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Integer getStaffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(Integer staffLevel) {
        this.staffLevel = staffLevel;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCertExpDate() {
        return certExpDate;
    }

    public void setCertExpDate(Date certExpDate) {
        this.certExpDate = certExpDate;
    }

    
    public Date getMaritalDate() {
        return maritalDate;
    }

    public void setMaritalDate(Date maritalDate) {
        this.maritalDate = maritalDate;
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

    public String getTaxNbr() {
        return taxNbr;
    }

    public void setTaxNbr(String taxNbr) {
        this.taxNbr = taxNbr;
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Date ConvertStringToDate(String strDate) {
        if (strDate == null || strDate.equals("")) {
            return null;
        }
        Date date = java.sql.Date.valueOf(strDate);
        return date;
    }

    public String getCertExpDate1() {
        if (this.certExpDate == null) {
            return null;
        }
        return this.certExpDate.toString();
    }

    public void setCertExpDate1(String certExpDate1) {
        this.certExpDate = this.ConvertStringToDate(certExpDate1);
    }

    public String getMaritalDate1() {
        if (this.maritalDate == null) {
            return null;
        }
        return this.maritalDate.toString();
    }

    public void setMaritalDate1(String maritalDate1) {
        this.maritalDate = this.ConvertStringToDate(maritalDate1);
    }

    public String getBirthday1() {
        if (this.birthday == null) {
            return null;
        }
        return this.birthday.toString();
    }

    public void setBirthday1(String birthday1) {
        this.birthday = this.ConvertStringToDate(birthday1);
    }

}
