package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class FuncNodeSVO extends GenericVO {
	
	private static final long serialVersionUID = 1L;

	private String funcNodeId;

	private String nodeTreeId;

	private String funcNodeCode;

	private String funcNodeName;

	private String subSystemName;

	private String securityLevel;

	private String funcNodeType;

	private String html;

	private String fileName;

	private String version;

	private String description;

	private String shortCutImage;

	private String sts;

	private Date stsDate;

	public void setFuncNodeId(String funcNodeId) {
		this.funcNodeId = funcNodeId;
	}
	
	public String getFuncNodeId() {
		return funcNodeId;
	}

	public void setNodeTreeId(String nodeTreeId) {
		this.nodeTreeId = nodeTreeId;
	}
	
	public String getNodeTreeId() {
		return nodeTreeId;
	}

	public void setFuncNodeCode(String funcNodeCode) {
		this.funcNodeCode = funcNodeCode;
	}
	
	public String getFuncNodeCode() {
		return funcNodeCode;
	}

	public void setFuncNodeName(String funcNodeName) {
		this.funcNodeName = funcNodeName;
	}
	
	public String getFuncNodeName() {
		return funcNodeName;
	}

	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}
	
	public String getSubSystemName() {
		return subSystemName;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	
	public String getSecurityLevel() {
		return securityLevel;
	}

	public void setFuncNodeType(String funcNodeType) {
		this.funcNodeType = funcNodeType;
	}
	
	public String getFuncNodeType() {
		return funcNodeType;
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

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return version;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setShortCutImage(String shortCutImage) {
		this.shortCutImage = shortCutImage;
	}
	
	public String getShortCutImage() {
		return shortCutImage;
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
		if (obj != null && obj instanceof FuncNodeSVO) {
			FuncNodeSVO another = (FuncNodeSVO) obj;
			equals = new EqualsBuilder()
					.append(funcNodeId, another.getFuncNodeId())
					.isEquals();
		}
		return equals;
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(funcNodeId)
				.toHashCode();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("funcNodeId", getFuncNodeId())
				.toString();
	}
}