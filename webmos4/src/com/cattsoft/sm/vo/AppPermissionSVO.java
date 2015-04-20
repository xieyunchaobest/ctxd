package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

public class AppPermissionSVO extends GenericVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String permissionAppId;

	private String commonId;

	private String appId;

	private String permission;

	private String sts;

	private Date stsDate;

	private String localNetId;

	private String DefaultApp;

	private ActionLogSVO actionLog = null;

	public String getPermissionAppId() {
		return permissionAppId;
	}

	public void setPermissionAppId(String permissionAppId) {
		this.permissionAppId = permissionAppId;
	}

	public String getCommonId() {
		return commonId;
	}

	public void setCommonId(String commonId) {
		this.commonId = commonId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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

	public String getLocalNetId() {
		return localNetId;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}

	public String getDefaultApp() {
		return DefaultApp;
	}

	public void setDefaultApp(String defaultApp) {
		DefaultApp = defaultApp;
	}

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}
}
