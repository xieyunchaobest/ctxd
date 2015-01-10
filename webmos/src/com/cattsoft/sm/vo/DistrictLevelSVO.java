package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class DistrictLevelSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String districtLevelId;

	private String name;

	private String parentDistrictLevelId;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setDistrictLevelId(String districtLevelId) {
		this.districtLevelId = districtLevelId;
	}
	
	public String getDistrictLevelId() {
		return districtLevelId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setParentDistrictLevelId(String parentDistrictLevelId) {
		this.parentDistrictLevelId = parentDistrictLevelId;
	}
	
	public String getParentDistrictLevelId() {
		return parentDistrictLevelId;
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

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof DistrictLevelSVO) {
			DistrictLevelSVO another = (DistrictLevelSVO) obj;
			equals = new EqualsBuilder()
					.append(districtLevelId, another.getDistrictLevelId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(districtLevelId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("districtLevelId", getDistrictLevelId())
				.toString();
	}
}