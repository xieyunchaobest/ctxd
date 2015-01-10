package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class ProvinceSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String provId;

	private String name;

	public void setProvId(String provId) {
		this.provId = provId;
	}
	
	public String getProvId() {
		return provId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof ProvinceSVO) {
			ProvinceSVO another = (ProvinceSVO) obj;
			equals = new EqualsBuilder()
					.append(provId, another.getProvId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(provId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("provId", getProvId())
				.toString();
	}
}