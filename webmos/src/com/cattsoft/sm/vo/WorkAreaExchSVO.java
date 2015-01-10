package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class WorkAreaExchSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String workAreaExchId;

	private String workAreaId;

	private String exchId;

	private String sts;

	private Date stsDate;

	private Date createDate;

	private int flagCreateDate = 0;

	private int flagExchId = 0;

	private int flagSts = 0;

	private int flagStsDate = 0;

	private int flagWorkAreaExchId = 0;

	private int flagWorkAreaId = 0;

	public void setWorkAreaExchId(String workAreaExchId) {
		this.workAreaExchId = workAreaExchId;
		flagWorkAreaExchId=1;
	}
	
	public String getWorkAreaExchId() {
		return workAreaExchId;
	}

	public void setWorkAreaId(String workAreaId) {
		this.workAreaId = workAreaId;
		flagWorkAreaId=1;
	}
	
	public String getWorkAreaId() {
		return workAreaId;
	}

	public void setExchId(String exchId) {
		this.exchId = exchId;
		flagExchId=1;
	}
	
	public String getExchId() {
		return exchId;
	}

	public void setSts(String sts) {
		this.sts = sts;
		flagSts=1;
	}
	
	public String getSts() {
		return sts;
	}

	public void setStsDate(Date stsDate) {
		this.stsDate = stsDate;
		flagStsDate=1;
	}
	
	public Date getStsDate() {
		return stsDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		flagCreateDate=1;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof WorkAreaExchSVO) {
			WorkAreaExchSVO another = (WorkAreaExchSVO) obj;
			equals = new EqualsBuilder()
					.append(workAreaExchId, another.getWorkAreaExchId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(workAreaExchId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("workAreaExchId", getWorkAreaExchId())
				.toString();
	}

	public int getFlagCreateDate() {
		return flagCreateDate;
	}

	public void setFlagCreateDate(int flagCreateDate) {
		this.flagCreateDate = flagCreateDate;
	}

	public int getFlagExchId() {
		return flagExchId;
	}

	public void setFlagExchId(int flagExchId) {
		this.flagExchId = flagExchId;
	}

	public int getFlagSts() {
		return flagSts;
	}

	public void setFlagSts(int flagSts) {
		this.flagSts = flagSts;
	}

	public int getFlagStsDate() {
		return flagStsDate;
	}

	public void setFlagStsDate(int flagStsDate) {
		this.flagStsDate = flagStsDate;
	}

	public int getFlagWorkAreaExchId() {
		return flagWorkAreaExchId;
	}

	public void setFlagWorkAreaExchId(int flagWorkAreaExchId) {
		this.flagWorkAreaExchId = flagWorkAreaExchId;
	}

	public int getFlagWorkAreaId() {
		return flagWorkAreaId;
	}

	public void setFlagWorkAreaId(int flagWorkAreaId) {
		this.flagWorkAreaId = flagWorkAreaId;
	}
}