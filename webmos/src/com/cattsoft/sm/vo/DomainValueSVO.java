package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class DomainValueSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String domainValueId;

	private String domainId;

	private String domainValue;

	private String valueMeaning;

	private String orderId;

	private String applScope;

	public void setDomainValueId(String domainValueId) {
		this.domainValueId = domainValueId;
	}
	
	public String getDomainValueId() {
		return domainValueId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	
	public String getDomainId() {
		return domainId;
	}

	public void setDomainValue(String domainValue) {
		this.domainValue = domainValue;
	}
	
	public String getDomainValue() {
		return domainValue;
	}

	public void setValueMeaning(String valueMeaning) {
		this.valueMeaning = valueMeaning;
	}
	
	public String getValueMeaning() {
		return valueMeaning;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setApplScope(String applScope) {
		this.applScope = applScope;
	}
	
	public String getApplScope() {
		return applScope;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof DomainValueSVO) {
			DomainValueSVO another = (DomainValueSVO) obj;
			equals = new EqualsBuilder()
					.append(domainValueId, another.getDomainValueId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(domainValueId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("domainValueId", getDomainValueId())
				.toString();
	}
}