package com.cattsoft.sm.vo;

import java.sql.Timestamp;

import com.cattsoft.pub.vo.GenericVO;

public class ChannelMVO extends GenericVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2009012698539542438L;

	// channel表
	private String channelId;

	private String channelName;

	private String channelLevelId;

	private String parentChannelId;

	private String channelTypeId;

	private String sts;

	private Timestamp stsDate;

	private Timestamp createDate;

	private String remarks;

	private String localNetId;

	private String areaId;

	private String servDeptId;
	
	private String centerLocalNetFlag;
	
	private String centerAreaFlag;

	// channel_type表

	private String channelCat;

	private String channelTypeName;

	private String ifContralCustRange;

	// party表
	private String partyId;

	private String name;

	// org_dept表
	public String getChannelCat() {
		return channelCat;
	}

	public void setChannelCat(String channelCat) {
		this.channelCat = channelCat;
	}

	public String getChannelTypeName() {
		return channelTypeName;
	}

	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
	}

	public String getIfContralCustRange() {
		return ifContralCustRange;
	}

	public void setIfContralCustRange(String ifContralCustRange) {
		this.ifContralCustRange = ifContralCustRange;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelLevelId() {
		return channelLevelId;
	}

	public void setChannelLevelId(String channelLevelId) {
		this.channelLevelId = channelLevelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(String channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public Timestamp getStsDate() {
		return stsDate;
	}

	public void setStsDate(Timestamp stsDate) {
		this.stsDate = stsDate;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getParentChannelId() {
		return parentChannelId;
	}

	public void setParentChannelId(String parentChannelId) {
		this.parentChannelId = parentChannelId;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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

	public String getServDeptId() {
		return servDeptId;
	}

	public void setServDeptId(String servDeptId) {
		this.servDeptId = servDeptId;
	}

	public String getCenterAreaFlag() {
		return centerAreaFlag;
	}

	public void setCenterAreaFlag(String centerAreaFlag) {
		this.centerAreaFlag = centerAreaFlag;
	}

	public String getCenterLocalNetFlag() {
		return centerLocalNetFlag;
	}

	public void setCenterLocalNetFlag(String centerLocalNetFlag) {
		this.centerLocalNetFlag = centerLocalNetFlag;
	}
}
