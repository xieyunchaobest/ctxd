package com.cattsoft.sm.struts;

import org.apache.struts.action.ActionForm;

public class SMAppPermissionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288930094372097486L;

	private String appInfoId;

	private String[] appIds;

	private String appId;

	private String appName;

	private String url;

	private String sts;

	private String stsDate;

	private String sysUserId;

	private String[] permissionAppIds;
	
	public String getAppInfoId() {
		return appInfoId;
	}

	public void setAppInfoId(String appInfoId) {
		this.appInfoId = appInfoId;
	}

	public String[] getAppIds() {
		return appIds;
	}

	public void setAppIds(String[] appIds) {
		this.appIds = appIds;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getStsDate() {
		return stsDate;
	}

	public void setStsDate(String stsDate) {
		this.stsDate = stsDate;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	public String[] getPermissionAppIds() {
		return permissionAppIds;
	}

	public void setPermissionAppIds(String[] permissionAppIds) {
		this.permissionAppIds = permissionAppIds;
	}
}
