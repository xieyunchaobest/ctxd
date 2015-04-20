package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-16 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author Administrator
 */
public class DataRangeMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String dataRangeId;

    private String rangeName;

    private String createFlag;

    private String localNetId;

    private String areaId;

    private Date createDate;

    private String sts;

    private Date stsDate;

    // 关联服务区的简拼
    private String abbrev_name;

    // 关联服务区的名称
    private String name;

    // 关联服务区的中心标志
    private String iscenter;

    public String getAbbrev_name() {
        return abbrev_name;
    }

    public void setAbbrev_name(String abbrev_name) {
        this.abbrev_name = abbrev_name;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateFlag() {
        return createFlag;
    }

    public void setCreateFlag(String createFlag) {
        this.createFlag = createFlag;
    }

    public String getDataRangeId() {
        return dataRangeId;
    }

    public void setDataRangeId(String dataRangeId) {
        this.dataRangeId = dataRangeId;
    }

    public String getIscenter() {
        return iscenter;
    }

    public void setIscenter(String iscenter) {
        this.iscenter = iscenter;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Date getStsDate() {
        return stsDate;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }
}
