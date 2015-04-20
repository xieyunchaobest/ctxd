package com.cattsoft.sm.vo;

import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class DistrictSVO extends GenericVO {
	
	
    private static final long serialVersionUID = 1L;

    private String districtId;

    private String name;

    private String abbrevName;

    private String parentDistrictId;

    private String districtLevelId;

    private String localNetId;

    private String areaId;

    private String servDeptId;

    private String sts;

    private Date stsDate;

    private Date createDate;

    private String cityFlag;

    public String getCityFlag() {
        return cityFlag;
    }

    public void setCityFlag(String cityFlag) {
        this.cityFlag = cityFlag;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
    }

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setParentDistrictId(String parentDistrictId) {
        this.parentDistrictId = parentDistrictId;
    }

    public String getParentDistrictId() {
        return parentDistrictId;
    }

    public void setDistrictLevelId(String districtLevelId) {
        this.districtLevelId = districtLevelId;
    }

    public String getDistrictLevelId() {
        return districtLevelId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

    public String getServDeptId() {
        return servDeptId;
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
        if (obj != null && obj instanceof DistrictSVO) {
            DistrictSVO another = (DistrictSVO) obj;
            equals = new EqualsBuilder().append(districtId, another.getDistrictId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(districtId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("districtId", getDistrictId()).toString();
    }
}