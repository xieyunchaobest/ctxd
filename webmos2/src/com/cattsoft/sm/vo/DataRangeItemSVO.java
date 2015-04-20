package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class DataRangeItemSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String dataRangeItemId;

	private String rangeId;

	private String dataRangeTypeId;

	private String fieldRangeValue;

	private String seq;

	private String leftBracket;

	private String rightBracket;

	private String operator;

	private String logical;

	private Date createDate;

	private String sts;

	private Date stsDate;

	public void setDataRangeItemId(String dataRangeItemId) {
		this.dataRangeItemId = dataRangeItemId;
	}
	
	public String getDataRangeItemId() {
		return dataRangeItemId;
	}

	public void setRangeId(String rangeId) {
		this.rangeId = rangeId;
	}
	
	public String getRangeId() {
		return rangeId;
	}

	public void setDataRangeTypeId(String dataRangeTypeId) {
		this.dataRangeTypeId = dataRangeTypeId;
	}
	
	public String getDataRangeTypeId() {
		return dataRangeTypeId;
	}

	public void setFieldRangeValue(String fieldRangeValue) {
		this.fieldRangeValue = fieldRangeValue;
	}
	
	public String getFieldRangeValue() {
		return fieldRangeValue;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public String getSeq() {
		return seq;
	}

	public void setLeftBracket(String leftBracket) {
		this.leftBracket = leftBracket;
	}
	
	public String getLeftBracket() {
		return leftBracket;
	}

	public void setRightBracket(String rightBracket) {
		this.rightBracket = rightBracket;
	}
	
	public String getRightBracket() {
		return rightBracket;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator() {
		return operator;
	}

	public void setLogical(String logical) {
		this.logical = logical;
	}
	
	public String getLogical() {
		return logical;
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
		if (obj != null && obj instanceof DataRangeItemSVO) {
			DataRangeItemSVO another = (DataRangeItemSVO) obj;
			equals = new EqualsBuilder()
					.append(dataRangeItemId, another.getDataRangeItemId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(dataRangeItemId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("dataRangeItemId", getDataRangeItemId())
				.toString();
	}
}