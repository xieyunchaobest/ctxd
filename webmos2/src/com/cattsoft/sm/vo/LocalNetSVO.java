package com.cattsoft.sm.vo;

import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.cattsoft.sm.vo.ActionLogSVO;

import com.cattsoft.pub.vo.GenericVO;

public class LocalNetSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String localNetId;

    private String abbrevName;

    private String name;

    private String distNbr;

    private String iscenter;

    private String sts;

    private java.util.Date stsDate;

    private java.util.Date createDate;

    private String jfLocalNetId;
    
    private String provId;
    
    //added by yangkai 增加日志记录 2009-6-24  
    private ActionLogSVO actionLog=null;

    public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getJfLocalNetId() {
        return jfLocalNetId;
    }

    public void setJfLocalNetId(String jfLocalNetId) {
        this.jfLocalNetId = jfLocalNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
    }

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDistNbr(String distNbr) {
        this.distNbr = distNbr;
    }

    public String getDistNbr() {
        return distNbr;
    }

    public void setIscenter(String iscenter) {
        this.iscenter = iscenter;
    }

    public String getIscenter() {
        return iscenter;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSts() {
        return sts;
    }

    public void setStsDate(java.util.Date stsDate) {
        this.stsDate = stsDate;
    }

    public java.util.Date getStsDate() {
        return stsDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof LocalNetSVO) {
            LocalNetSVO another = (LocalNetSVO) obj;
            equals = new EqualsBuilder().append(localNetId, another.getLocalNetId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(localNetId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("localNetId", getLocalNetId()).toString();
    }

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}
}