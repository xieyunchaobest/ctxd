package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class DomainSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String domainId;

	private String domainName;

	private String domainMeaning;

	private String minValue;

	private String maxValue;

	private String defValue;

	private Date lastModiDate;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	
	public String getDomainId() {
		return domainId;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public String getDomainName() {
		return domainName;
	}

	public void setDomainMeaning(String domainMeaning) {
		this.domainMeaning = domainMeaning;
	}
	
	public String getDomainMeaning() {
		return domainMeaning;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	
	public String getMinValue() {
		return minValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	
	public String getMaxValue() {
		return maxValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	
	public String getDefValue() {
		return defValue;
	}

	public void setLastModiDate(Date lastModiDate) {
		this.lastModiDate = lastModiDate;
	}
	
	public Date getLastModiDate() {
		return lastModiDate;
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
		if (obj != null && obj instanceof DomainSVO) {
			DomainSVO another = (DomainSVO) obj;
			equals = new EqualsBuilder()
					.append(domainId, another.getDomainId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(domainId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("domainId", getDomainId())
				.toString();
	}
}