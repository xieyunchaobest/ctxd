package com.cattsoft.sm.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-3 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SMStaffWorkAreaActionForm extends ActionForm {
	
	
    private String staffWorkAreaId;

    private String staffId;

    private String[] choses;

    private String workAreaId;

    private List staffList;

    private List workAreaList;

    private String grantor;

    private String sts;

    private String areaId;

    private java.util.List areaList;

    private Long[] workAreaChoses;

    private String workTypeId;

    private List workTypeIds;

    private String addWorks;

    private String localNetId;

    private String areaIscenter;
    
    private String localNetIscenter;
    
    private String areaName;
    
    private String adminFlag;

    public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getLocalNetIscenter() {
		return localNetIscenter;
	}

	public void setLocalNetIscenter(String localNetIscenter) {
		this.localNetIscenter = localNetIscenter;
	}

	public String getAreaIscenter() {
        return areaIscenter;
    }

    public void setAreaIscenter(String areaIscenter) {
        this.areaIscenter = areaIscenter;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public java.util.List getAreaList() {
        return areaList;
    }

    public void setAreaList(java.util.List areaList) {
        this.areaList = areaList;
    }

    public String[] getChoses() {
        return choses;
    }

    public void setChoses(String[] choses) {
        this.choses = choses;
    }

    public String getGrantor() {
        return grantor;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public List getStaffList() {
        return staffList;
    }

    public void setStaffList(List staffList) {
        this.staffList = staffList;
    }

    public String getStaffWorkAreaId() {
        return staffWorkAreaId;
    }

    public void setStaffWorkAreaId(String staffWorkAreaId) {
        this.staffWorkAreaId = staffWorkAreaId;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Long[] getWorkAreaChoses() {
        return workAreaChoses;
    }

    public void setWorkAreaChoses(Long[] workAreaChoses) {
        this.workAreaChoses = workAreaChoses;
    }

    public String getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(String workAreaId) {
        this.workAreaId = workAreaId;
    }

    public List getWorkAreaList() {
        return workAreaList;
    }

    public void setWorkAreaList(List workAreaList) {
        this.workAreaList = workAreaList;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public List getWorkTypeIds() {
        return workTypeIds;
    }

    public void setWorkTypeIds(List workTypeIds) {
        this.workTypeIds = workTypeIds;
    }

    public String getAddWorks() {
        return addWorks;
    }

    public void setAddWorks(String addWorks) {
        this.addWorks = addWorks;
    }

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}
}
