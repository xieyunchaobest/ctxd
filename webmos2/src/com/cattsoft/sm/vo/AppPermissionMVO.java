package com.cattsoft.sm.vo;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class AppPermissionMVO extends GenericVO {

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

	private String appName;

	private String appInfoId;

	private ActionLogSVO actionLog = null;

	private int flagAppId = 0;

	private int flagAppInfoId = 0;

	private int flagAppName = 0;

	private int flagSts = 0;

	private int flagStsDate = 0;
	
	private int flagCommonId = 0;

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
		flagCommonId = 1;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
		flagAppId = 1;
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
		flagSts = 1;
	}

	public Date getStsDate() {
		return stsDate;
	}

	public void setStsDate(Date stsDate) {
		this.stsDate = stsDate;
		flagStsDate = 1;
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
		flagAppName = 1;
	}

	public String getAppInfoId() {
		return appInfoId;
	}

	public void setAppInfoId(String appInfoId) {
		this.appInfoId = appInfoId;
		flagAppInfoId = 1;
	}

	public int getFlagAppId() {
		return flagAppId;
	}

	public int getFlagAppInfoId() {
		return flagAppInfoId;
	}

	public int getFlagAppName() {
		return flagAppName;
	}

	public int getFlagSts() {
		return flagSts;
	}

	public int getFlagStsDate() {
		return flagStsDate;
	}
	
	public int getFlagCommonId() {
		return flagCommonId;
	}

}
