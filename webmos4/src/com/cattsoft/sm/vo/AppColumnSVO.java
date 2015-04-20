package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class AppColumnSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String appColumnId;

	private String engName;

	private String chName;

	private String domainId;

	private String appTableId;

	private String columnComment;

	private String dataType;

	private String columnLength;

	private String mandatoryFlag;

	private String primaryKeyFlag;

	private String foreignKeyFlag;

	private Date lastModiDate;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setAppColumnId(String appColumnId) {
		this.appColumnId = appColumnId;
	}
	
	public String getAppColumnId() {
		return appColumnId;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}
	
	public String getEngName() {
		return engName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}
	
	public String getChName() {
		return chName;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	
	public String getDomainId() {
		return domainId;
	}

	public void setAppTableId(String appTableId) {
		this.appTableId = appTableId;
	}
	
	public String getAppTableId() {
		return appTableId;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	
	public String getColumnComment() {
		return columnComment;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public String getDataType() {
		return dataType;
	}

	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}
	
	public String getColumnLength() {
		return columnLength;
	}

	public void setMandatoryFlag(String mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}
	
	public String getMandatoryFlag() {
		return mandatoryFlag;
	}

	public void setPrimaryKeyFlag(String primaryKeyFlag) {
		this.primaryKeyFlag = primaryKeyFlag;
	}
	
	public String getPrimaryKeyFlag() {
		return primaryKeyFlag;
	}

	public void setForeignKeyFlag(String foreignKeyFlag) {
		this.foreignKeyFlag = foreignKeyFlag;
	}
	
	public String getForeignKeyFlag() {
		return foreignKeyFlag;
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
		if (obj != null && obj instanceof AppColumnSVO) {
			AppColumnSVO another = (AppColumnSVO) obj;
			equals = new EqualsBuilder()
					.append(appColumnId, another.getAppColumnId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(appColumnId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("appColumnId", getAppColumnId())
				.toString();
	}
}