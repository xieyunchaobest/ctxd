package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class FuncNodeTreeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String nodeTreeId;

	private String nodeTreeName;

	private String parentNodeTreeId;

	private String nodeTreeCode;

	private String description;

	private String html;

	private String fileName;

	public void setNodeTreeId(String nodeTreeId) {
		this.nodeTreeId = nodeTreeId;
	}
	
	public String getNodeTreeId() {
		return nodeTreeId;
	}

	public void setNodeTreeName(String nodeTreeName) {
		this.nodeTreeName = nodeTreeName;
	}
	
	public String getNodeTreeName() {
		return nodeTreeName;
	}

	public void setParentNodeTreeId(String parentNodeTreeId) {
		this.parentNodeTreeId = parentNodeTreeId;
	}
	
	public String getParentNodeTreeId() {
		return parentNodeTreeId;
	}

	public void setNodeTreeCode(String nodeTreeCode) {
		this.nodeTreeCode = nodeTreeCode;
	}
	
	public String getNodeTreeCode() {
		return nodeTreeCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	public String getHtml() {
		return html;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof FuncNodeTreeSVO) {
			FuncNodeTreeSVO another = (FuncNodeTreeSVO) obj;
			equals = new EqualsBuilder()
					.append(nodeTreeId, another.getNodeTreeId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(nodeTreeId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("nodeTreeId", getNodeTreeId())
				.toString();
	}
}