package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class SysUserAllocSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String allocId;

    private String sysUserId;

    private String funcNodeId;

    private String sysRoleId;

    private String grantSysUserId;

    private String entrustFlag;

    private String allocAuth;

    private String localNetId;

    private String rangeId;

    private Date createDate;

    private Date stsDate;

    private String sts;
    
    //added by yangkai 2009-6-9
    private ActionLogSVO actionLog=null;

    /**
	 * @return the actionLog
	 */
	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	/**
	 * @param actionLog the actionLog to set
	 */
	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setAllocId(String allocId) {
        this.allocId = allocId;
    }
    
    public String getAllocId() {
        return allocId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }
    
    public String getSysUserId() {
        return sysUserId;
    }

    public void setFuncNodeId(String funcNodeId) {
        this.funcNodeId = funcNodeId;
    }
    
    public String getFuncNodeId() {
        return funcNodeId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    
    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setGrantSysUserId(String grantSysUserId) {
        this.grantSysUserId = grantSysUserId;
    }
    
    public String getGrantSysUserId() {
        return grantSysUserId;
    }

    public void setEntrustFlag(String entrustFlag) {
        this.entrustFlag = entrustFlag;
    }
    
    public String getEntrustFlag() {
        return entrustFlag;
    }

    public void setAllocAuth(String allocAuth) {
        this.allocAuth = allocAuth;
    }
    
    public String getAllocAuth() {
        return allocAuth;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }
    
    public String getLocalNetId() {
        return localNetId;
    }

    public void setRangeId(String rangeId) {
        this.rangeId = rangeId;
    }
    
    public String getRangeId() {
        return rangeId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }
    
    public Date getStsDate() {
        return stsDate;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }
    
    public String getSts() {
        return sts;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof SysUserAllocSVO) {
            SysUserAllocSVO another = (SysUserAllocSVO) obj;
            equals = new EqualsBuilder()
                    .append(allocId, another.getAllocId())
                    .isEquals();
        }
        return equals;
    }
    
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(allocId)
                .toHashCode();
    }
    
    public String toString() {
        return new ToStringBuilder(this)
                .append("allocId", getAllocId())
                .toString();
    }
}