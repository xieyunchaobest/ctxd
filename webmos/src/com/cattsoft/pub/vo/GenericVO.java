package com.cattsoft.pub.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class GenericVO implements Serializable{
 

	private static final long serialVersionUID = -5198390916297894518L;

	public String toString() {
	    return ReflectionToStringBuilder.reflectionToString(this);
	  }

	  public boolean equals(Object vo) {
	    return EqualsBuilder.reflectionEquals(this, vo);
	  }

	  public int hashCode() {
	    return HashCodeBuilder.reflectionHashCode(this);
	  }
	  

}
