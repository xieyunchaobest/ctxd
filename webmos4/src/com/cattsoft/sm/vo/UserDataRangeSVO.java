package com.cattsoft.sm.vo;

import java.util.Date;

import com.cattsoft.pub.vo.GenericVO;

public class UserDataRangeSVO extends GenericVO {

	private static final long serialVersionUID = 1L;

	private String userRangeId;

	private String sysUserId;

	private String rangeId;

	private Date createDate;

	private String sts;

	private Date stsDate;

	private String allowFlag = null;

	private String dataRangeId = null;

	private String rangeTypeId = null;

	private String userDataAreaId = null;

	private int flagAllowFlag = 0;

	private int flagCreateDate = 0;

	private int flagDataRangeId = 0;

	private int flagRangeTypeId = 0;

	private int flagSts = 0;

	private int flagStsDate = 0;

	private int flagSysUserId = 0;

	private int flagUserDataAreaId = 0;

	public String getAllowFlag() {
		return allowFlag;
	}

	public void setAllowFlag(String newValue) {
		this.allowFlag = newValue;
		flagAllowFlag = 1;
	}

	public int getFlagAllowFlag() {
		return flagAllowFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date newValue) {
		this.createDate = newValue;
		flagCreateDate = 1;
	}

	public int getFlagCreateDate() {
		return flagCreateDate;
	}

	public String getDataRangeId() {
		return dataRangeId;
	}

	public void setDataRangeId(String newValue) {
		this.dataRangeId = newValue;
		flagDataRangeId = 1;
	}

	public int getFlagDataRangeId() {
		return flagDataRangeId;
	}

	public String getRangeTypeId() {
		return rangeTypeId;
	}

	public void setRangeTypeId(String newValue) {
		this.rangeTypeId = newValue;
		flagRangeTypeId = 1;
	}

	public int getFlagRangeTypeId() {
		return flagRangeTypeId;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String newValue) {
		this.sts = newValue;
		flagSts = 1;
	}

	public int getFlagSts() {
		return flagSts;
	}

	public Date getStsDate() {
		return stsDate;
	}

	public void setStsDate(Date newValue) {
		this.stsDate = newValue;
		flagStsDate = 1;
	}

	public int getFlagStsDate() {
		return flagStsDate;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String newValue) {
		this.sysUserId = newValue;
		flagSysUserId = 1;
	}

	public int getFlagSysUserId() {
		return flagSysUserId;
	}

	public String getUserDataAreaId() {
		return userDataAreaId;
	}

	public void setUserDataAreaId(String newValue) {
		this.userDataAreaId = newValue;
		flagUserDataAreaId = 1;
	}

	public int getFlagUserDataAreaId() {
		return flagUserDataAreaId;
	}

	public void clearFlagAllowFlag() {
		flagAllowFlag = 0;
	}

	public void clearFlagCreateDate() {
		flagCreateDate = 0;
	}

	public void clearFlagDataRangeId() {
		flagDataRangeId = 0;
	}

	public void clearFlagRangeTypeId() {
		flagRangeTypeId = 0;
	}

	public void clearFlagSts() {
		flagSts = 0;
	}

	public void clearFlagStsDate() {
		flagStsDate = 0;
	}

	public void clearFlagSysUserId() {
		flagSysUserId = 0;
	}

	public void clearFlagUserDataAreaId() {
		flagUserDataAreaId = 0;
	}

	public void clearAll() {
		flagAllowFlag = 0;
		flagCreateDate = 0;
		flagDataRangeId = 0;
		flagRangeTypeId = 0;
		flagSts = 0;
		flagStsDate = 0;
		flagSysUserId = 0;
		flagUserDataAreaId = 0;

	}

	public String getRangeId() {
		return rangeId;
	}

	public void setRangeId(String rangeId) {
		this.rangeId = rangeId;
	}

	public String getUserRangeId() {
		return userRangeId;
	}

	public void setUserRangeId(String userRangeId) {
		this.userRangeId = userRangeId;
	}

}