package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-28 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class WorkAreaExchMVO extends GenericVO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 223588300619171788L;

	private WorkAreaSVO workAreaSVO = new WorkAreaSVO();

    private ExchSVO exchSVO = new ExchSVO();

    private String workAreaExchId;

    private String workAreaId;

    private String exchId;

    private String sts;

    private Date stsDate;

    private Date createDate;
    
    private String workTypeId;//added by yangkai 2009-9-1
    
    private String workTypeName;//added by yangkai 2009-9-1

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExchId() {
        return exchId;
    }

    public void setExchId(String exchId) {
        this.exchId = exchId;
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

    public String getWorkAreaExchId() {
        return workAreaExchId;
    }

    public void setWorkAreaExchId(String workAreaExchId) {
        this.workAreaExchId = workAreaExchId;
    }

    public String getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(String workAreaId) {
        this.workAreaId = workAreaId;
    }

    public ExchSVO getExchSVO() {
        return exchSVO;
    }

    public void setExchSVO(ExchSVO exchSVO) {
        this.exchSVO = exchSVO;
    }

    public WorkAreaSVO getWorkAreaSVO() {
        return workAreaSVO;
    }

    public void setWorkAreaSVO(WorkAreaSVO workAreaSVO) {
        this.workAreaSVO = workAreaSVO;
    }

	public String getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

}
