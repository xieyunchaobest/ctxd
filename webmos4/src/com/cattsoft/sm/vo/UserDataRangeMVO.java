package com.cattsoft.sm.vo;

public class UserDataRangeMVO extends UserDataRangeSVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7315207061483894920L;

	private String userName;
	
	private String sysUserName;

	private String localNetId;

	private String areaId;

	private String workAreaId;

	private String areaName;

	private String localNetName;
	
	private String workTypeId;

	private int localNetFlag = 0;

	private int areaFlag = 0;

	private int workAreaFlag = 0;

	// lilin[20080815] 新加个属性用于存放allow_flag对应的意义
	private String allowFlagName = null;
	// end lilin[20080815]

	public int getAreaFlag() {
		return areaFlag;
	}

	public int getLocalNetFlag() {
		return localNetFlag;
	}

	public int getWorkAreaFlag() {
		return workAreaFlag;
	}

	public String getLocalNetName() {
		return localNetName;
	}

	public void setLocalNetName(String localNetName) {
		this.localNetName = localNetName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getLocalNetId() {
		return localNetId;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
		this.localNetFlag = 1;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
		this.areaFlag = 1;
	}

	public String getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(String workAreaId) {
		this.workAreaId = workAreaId;
		this.workAreaFlag = 1;
	}

	public String getAllowFlagName() {
		return allowFlagName;
	}

	public void setAllowFlagName(String allowFlagName) {
		this.allowFlagName = allowFlagName;
	}

	public String getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}
	
	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

}
