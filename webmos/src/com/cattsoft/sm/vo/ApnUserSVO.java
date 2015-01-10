package com.cattsoft.sm.vo;

import java.util.Date;

import com.cattsoft.pub.vo.GenericVO;

/**
 * ApnUserSVO
 * @author £º°×Ð¡ÁÁ¡£
 * @version 1.1  2007-9-23
 * <p>Company: ´óÌÆÈí¼þ</p>
 */
public class ApnUserSVO extends GenericVO {
	private Date createdDate = null;
	private String deviceToken = null;
	private String deviceType = null;
	private String email = null;
	private String id = null;
	private String name = null;
	private String password = null;
	private Date updatedDate = null;
	private String username = null;
	private int flagCreatedDate = 0;
	private int flagDeviceToken = 0;
	private int flagDeviceType = 0;
	private int flagEmail = 0;
	private int flagId = 0;
	private int flagName = 0;
	private int flagPassword = 0;
	private int flagUpdatedDate = 0;
	private int flagUsername = 0;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date newValue) {
		this.createdDate = newValue;
		flagCreatedDate = 1;
	}

	public int getFlagCreatedDate() {
		return flagCreatedDate;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String newValue) {
		this.deviceToken = newValue;
		flagDeviceToken = 1;
	}

	public int getFlagDeviceToken() {
		return flagDeviceToken;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String newValue) {
		this.deviceType = newValue;
		flagDeviceType = 1;
	}

	public int getFlagDeviceType() {
		return flagDeviceType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String newValue) {
		this.email = newValue;
		flagEmail = 1;
	}

	public int getFlagEmail() {
		return flagEmail;
	}

	public String getId() {
		return id;
	}

	public void setId(String newValue) {
		this.id = newValue;
		flagId = 1;
	}

	public int getFlagId() {
		return flagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String newValue) {
		this.name = newValue;
		flagName = 1;
	}

	public int getFlagName() {
		return flagName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newValue) {
		this.password = newValue;
		flagPassword = 1;
	}

	public int getFlagPassword() {
		return flagPassword;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date newValue) {
		this.updatedDate = newValue;
		flagUpdatedDate = 1;
	}

	public int getFlagUpdatedDate() {
		return flagUpdatedDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String newValue) {
		this.username = newValue;
		flagUsername = 1;
	}

	public int getFlagUsername() {
		return flagUsername;
	}

	public void clearFlagCreatedDate() {
		flagCreatedDate = 0;
	}

	public void clearFlagDeviceToken() {
		flagDeviceToken = 0;
	}

	public void clearFlagDeviceType() {
		flagDeviceType = 0;
	}

	public void clearFlagEmail() {
		flagEmail = 0;
	}

	public void clearFlagId() {
		flagId = 0;
	}

	public void clearFlagName() {
		flagName = 0;
	}

	public void clearFlagPassword() {
		flagPassword = 0;
	}

	public void clearFlagUpdatedDate() {
		flagUpdatedDate = 0;
	}

	public void clearFlagUsername() {
		flagUsername = 0;
	}

	public void clearAll() {
		flagCreatedDate = 0;
		flagDeviceToken = 0;
		flagDeviceType = 0;
		flagEmail = 0;
		flagId = 0;
		flagName = 0;
		flagPassword = 0;
		flagUpdatedDate = 0;
		flagUsername = 0;

	}
}
