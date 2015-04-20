package com.cattsoft.sm.struts;

import java.sql.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-7 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SMBranchActionForm extends ActionForm {

    private String branchId;

    private String localNetId;

    private String areaId;

    private String servDeptId;

    private String abbrevName;

    private String name;

    private String sts;

    private Date stsDate;

    private Date createDate;

    private List localNetSel;

    private List areaSel;

    private List servDeptSel;

    private String[] choses;

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

    public List getAreaSel() {
        return areaSel;
    }

    public void setAreaSel(List areaSel) {
        this.areaSel = areaSel;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public List getLocalNetSel() {
        return localNetSel;
    }

    public void setLocalNetSel(List localNetSel) {
        this.localNetSel = localNetSel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

    public List getServDeptSel() {
        return servDeptSel;
    }

    public void setServDeptSel(List servDeptSel) {
        this.servDeptSel = servDeptSel;
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

    public String[] getChoses() {
        return choses;
    }

    public void setChoses(String[] choses) {
        this.choses = choses;
    }

}
