package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class DataRangeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String dataRangeId;

	private String rangeName;

	private String createFlag;

	private String localNetId;

	private String areaId;

	private Date createDate;

	private String sts;

	private Date stsDate;

	public void setDataRangeId(String dataRangeId) {
		this.dataRangeId = dataRangeId;
	}
	
	public String getDataRangeId() {
		return dataRangeId;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}
	
	public String getRangeName() {
		return rangeName;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}
	
	public String getCreateFlag() {
		return createFlag;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}
	
	public String getLocalNetId() {
		return localNetId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}
	
	public String getSts() {
		return sts;
	}

	public void setStsDate(Date stsDate) {
		this.stsDate = stsDate;
	}
	
	public Date getStsDate() {
		return stsDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof DataRangeSVO) {
			DataRangeSVO another = (DataRangeSVO) obj;
			equals = new EqualsBuilder()
					.append(dataRangeId, another.getDataRangeId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(dataRangeId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("dataRangeId", getDataRangeId())
				.toString();
	}
}