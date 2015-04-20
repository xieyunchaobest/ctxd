package com.cattsoft.sm.vo;

import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class FaultReasonSVO extends GenericVO {

    private static final long serialVersionUID = 1L;
    

    private String reasonId;

    private String name;

    private String reasonCat;

    private String dutyFlag;

    private String sts;

    private Date stsDate;

    private Date createDate;

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setReasonCat(String reasonCat) {
        this.reasonCat = reasonCat;
    }

    public String getReasonCat() {
        return reasonCat;
    }

    public void setDutyFlag(String dutyFlag) {
        this.dutyFlag = dutyFlag;
    }

    public String getDutyFlag() {
        return dutyFlag;
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
        if (obj != null && obj instanceof FaultReasonSVO) {
            FaultReasonSVO another = (FaultReasonSVO) obj;
            equals = new EqualsBuilder().append(reasonId, another.getReasonId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(reasonId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("reasonId", getReasonId()).toString();
    }
}