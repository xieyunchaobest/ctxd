package com.cattsoft.sm.vo;

import java.io.*;
import com.cattsoft.pub.vo.GenericVO;
import java.util.*;
import java.sql.Clob;
import java.sql.Blob;

/**
 * MosFeedBackSVO
 * @author £º°×Ð¡ÁÁ¡£
 * @version 1.1  2007-9-23
 * <p>Company: ´óÌÆÈí¼þ</p>
 */
public class MosFeedBackSVO extends GenericVO {
	private Date createTime = null;
	private String email = null;
	private String feedDesc = null;
	private String mosFeedBackId = null;
	private String remarks = null;
	private String userId = null;
	private int flagCreateTime = 0;
	private int flagEmail = 0;
	private int flagFeedDesc = 0;
	private int flagMosFeedBackId = 0;
	private int flagRemarks = 0;
	private int flagUserId = 0;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date newValue) {
		this.createTime = newValue;
		flagCreateTime = 1;
	}

	public int getFlagCreateTime() {
		return flagCreateTime;
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

	public String getFeedDesc() {
		return feedDesc;
	}

	public void setFeedDesc(String newValue) {
		this.feedDesc = newValue;
		flagFeedDesc = 1;
	}

	public int getFlagFeedDesc() {
		return flagFeedDesc;
	}

	public String getMosFeedBackId() {
		return mosFeedBackId;
	}

	public void setMosFeedBackId(String newValue) {
		this.mosFeedBackId = newValue;
		flagMosFeedBackId = 1;
	}

	public int getFlagMosFeedBackId() {
		return flagMosFeedBackId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String newValue) {
		this.remarks = newValue;
		flagRemarks = 1;
	}

	public int getFlagRemarks() {
		return flagRemarks;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String newValue) {
		this.userId = newValue;
		flagUserId = 1;
	}

	public int getFlagUserId() {
		return flagUserId;
	}

	public void clearFlagCreateTime() {
		flagCreateTime = 0;
	}

	public void clearFlagEmail() {
		flagEmail = 0;
	}

	public void clearFlagFeedDesc() {
		flagFeedDesc = 0;
	}

	public void clearFlagMosFeedBackId() {
		flagMosFeedBackId = 0;
	}

	public void clearFlagRemarks() {
		flagRemarks = 0;
	}

	public void clearFlagUserId() {
		flagUserId = 0;
	}

	public void clearAll() {
		flagCreateTime = 0;
		flagEmail = 0;
		flagFeedDesc = 0;
		flagMosFeedBackId = 0;
		flagRemarks = 0;
		flagUserId = 0;

	}
}
