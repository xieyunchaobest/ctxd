package com.cattsoft.sm.vo;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-11 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class ExchMVO extends ExchSVO {

	private static final long serialVersionUID = 1L;

	private String localNetName;

	private String areaName;

	private String servDeptName;

	private String branchName;

	private String ruleAreaName;

	private String stsDesc;               // add by xcj

	private String exchTypeDesc;          // add by xcj 
	
	private String subTypeDesc; 
	
	private String sysUserId;            //add by zd

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getExchTypeDesc() {
		return exchTypeDesc;
	}

	public void setExchTypeDesc(String exchTypeDesc) {
		this.exchTypeDesc = exchTypeDesc;
	}

	public String getLocalNetName() {
		return localNetName;
	}

	public void setLocalNetName(String localNetName) {
		this.localNetName = localNetName;
	}

	public String getRuleAreaName() {
		return ruleAreaName;
	}

	public void setRuleAreaName(String ruleAreaName) {
		this.ruleAreaName = ruleAreaName;
	}

	public String getServDeptName() {
		return servDeptName;
	}

	public void setServDeptName(String servDeptName) {
		this.servDeptName = servDeptName;
	}

	public String getStsDesc() {
		return stsDesc;
	}

	public void setStsDesc(String stsDesc) {
		this.stsDesc = stsDesc;
	}

	public String getSubTypeDesc() {
		return subTypeDesc;
	}

	public void setSubTypeDesc(String subTypeDesc) {
		this.subTypeDesc = subTypeDesc;
	}
}
