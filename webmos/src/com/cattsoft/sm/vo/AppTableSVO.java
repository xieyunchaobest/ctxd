package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class AppTableSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String appTableId;

	private String engName;

	private String chName;

	private String tableComment;

	private String dbUser;

	private Date lastModiDate;

	private String sts;

	private Date stsDate;

	private Date createDate;

	public void setAppTableId(String appTableId) {
		this.appTableId = appTableId;
	}
	
	public String getAppTableId() {
		return appTableId;
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

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
	public String getTableComment() {
		return tableComment;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	
	public String getDbUser() {
		return dbUser;
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
		if (obj != null && obj instanceof AppTableSVO) {
			AppTableSVO another = (AppTableSVO) obj;
			equals = new EqualsBuilder()
					.append(appTableId, another.getAppTableId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(appTableId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("appTableId", getAppTableId())
				.toString();
	}
}