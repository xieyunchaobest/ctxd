/**
 * 
 */
package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;
/**
 * Title:系统管理->员工管理->工区分配<br>
 * Description:工区分配Form<br>
 * Copyright:Copyright (C) 2009<br>
 * Company:cattsoft<br>
 * 
 * @author 杨凯 2009-6-30<br>
 * @version 1.0 <br>
 * 
 */
public class WorkAreaDispatchForm extends ActionForm{
	private static final long serialVersionUID = 7710625594745163927L;
	private List localNetList;
	
	private List areaList; 
	
	private List stsList;
	
	private String sts;
	
	private String[] choses;

    /**
     * 属性staffId 标识 属性
     */
    private String staffId;
    
    private String areaId;
    
    private String localNetId;
    
    private String chbAreaId;
    
    private String chbLocalNetId;
    
    private String chbStaffName;
    
    private String staffName;

    private String chbStaffId;
    
    private String chbSts;
    
    private String sysUserName;
    
    private String chbSysUserName;
   
    
	public String getChbSysUserName() {
		return chbSysUserName;
	}

	public void setChbSysUserName(String chbSysUserName) {
		this.chbSysUserName = chbSysUserName;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getChbStaffId() {
		return chbStaffId;
	}

	public void setChbStaffId(String chbStaffId) {
		this.chbStaffId = chbStaffId;
	}

	public List getLocalNetList() {
		return localNetList;
	}

	public void setLocalNetList(List localNetList) {
		this.localNetList = localNetList;
	}

	public List getAreaList() {
		return areaList;
	}

	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}

	public List getStsList() {
		return stsList;
	}

	public void setStsList(List stsList) {
		this.stsList = stsList;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String[] getChoses() {
		return choses;
	}

	public void setChoses(String[] choses) {
		this.choses = choses;
	}

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

	public String getChbStaffName() {
		return chbStaffName;
	}

	public void setChbStaffName(String chbStaffName) {
		this.chbStaffName = chbStaffName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getChbSts() {
		return chbSts;
	}

	public void setChbSts(String chbSts) {
		this.chbSts = chbSts;
	}
    
    
}
