package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class UserDataRangeForm extends ActionForm {
	
    private String []userIds = null;
    
    private List localNets=null;
    
    private List areas=null;
    private List workAreas=null;
    private List staffs=null;
    private String chbLocalNetId="1";
    private String localNetId=null;
    private String chbAreaId;
    private String areaId=null;
    private String chbWorkAreaId;
    private String workAreaId=null;
    private String staffId=null;
    
    //****lilin[20080815] 加几个属性
    //对应rangeTypeId的radio
    private String rangeTypeId=null;
    
    //许可标志allocFlag
    private String allocFlag=null;
    
    //许可标志的checkbox
    private String chbAllocFlag=null;
    //****end lilin[20080815]
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getLocalNetId() {
		return localNetId;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}

	public String getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(String workAreaId) {
		this.workAreaId = workAreaId;
	}

	public List getAreas() {
		return areas;
	}

	public void setAreas(List areas) {
		this.areas = areas;
	}

	public List getLocalNets() {
		return localNets;
	}

	public void setLocalNets(List localNets) {
		this.localNets = localNets;
	}

	public List getWorkAreas() {
		return workAreas;
	}

	public void setWorkAreas(List workAreas) {
		this.workAreas = workAreas;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public List getStaffs() {
		return staffs;
	}

	public void setStaffs(List staffs) {
		this.staffs = staffs;
	}

	public String getChbAreaId() {
		return chbAreaId;
	}

	public void setChbAreaId(String chbAreaId) {
		this.chbAreaId = chbAreaId;
	}

	public String getChbLocalNetId() {
		return chbLocalNetId;
	}

	public void setChbLocalNetId(String chbLocalNetId) {
		this.chbLocalNetId = chbLocalNetId;
	}

	public String getChbWorkAreaId() {
		return chbWorkAreaId;
	}

	public void setChbWorkAreaId(String chbWorkAreaId) {
		this.chbWorkAreaId = chbWorkAreaId;
	}

	public String getAllocFlag() {
		return allocFlag;
	}

	public void setAllocFlag(String allocFlag) {
		this.allocFlag = allocFlag;
	}

	public String getChbAllocFlag() {
		return chbAllocFlag;
	}

	public void setChbAllocFlag(String chbAllocFlag) {
		this.chbAllocFlag = chbAllocFlag;
	}

	public String getRangeTypeId() {
		return rangeTypeId;
	}

	public void setRangeTypeId(String rangeTypeId) {
		this.rangeTypeId = rangeTypeId;
	}
	
}
