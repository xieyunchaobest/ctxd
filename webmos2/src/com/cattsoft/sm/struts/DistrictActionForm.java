package com.cattsoft.sm.struts;

import java.sql.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-15 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class DistrictActionForm extends ActionForm {

    private String districtId;

    private String name;

    private String abbrevName;

    private String parentDistrictId;

    private String districtLevelId;

    private String localNetId;

    private String areaId;

    private String servDeptId;

    private String sts;

    private Date stsDate;

    private Date createDate;

    private List localNetSel;

    private List areaSel;

    private List servDeptSel;

    private List levelSel;

    private String fromSrc;

    private String parentName;

    private String cityFlag;

    public String getCityFlag() {
        return cityFlag;
    }

    public void setCityFlag(String cityFlag) {
        this.cityFlag = cityFlag;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getFromSrc() {
        return fromSrc;
    }

    public void setFromSrc(String fromSrc) {
        this.fromSrc = fromSrc;
    }

    public List getLevelSel() {
        return levelSel;
    }

    public void setLevelSel(List levelSel) {
        this.levelSel = levelSel;
    }

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictLevelId() {
        return districtLevelId;
    }

    public void setDistrictLevelId(String districtLevelId) {
        this.districtLevelId = districtLevelId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentDistrictId() {
        return parentDistrictId;
    }

    public void setParentDistrictId(String parentDistrictId) {
        this.parentDistrictId = parentDistrictId;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
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

    public List getAreaSel() {
        return areaSel;
    }

    public void setAreaSel(List areaSel) {
        this.areaSel = areaSel;
    }

    public List getLocalNetSel() {
        return localNetSel;
    }

    public void setLocalNetSel(List localNetSel) {
        this.localNetSel = localNetSel;
    }

    public List getServDeptSel() {
        return servDeptSel;
    }

    public void setServDeptSel(List servDeptSel) {
        this.servDeptSel = servDeptSel;
    }

}
