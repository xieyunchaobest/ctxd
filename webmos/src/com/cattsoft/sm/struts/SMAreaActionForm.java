package com.cattsoft.sm.struts;

import java.sql.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-6 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SMAreaActionForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8576946241569232226L;

	private String areaId;

    private Date createDate;

    private String localNetId;

    private String name;

    private String sts;

    private Date stsDate;

    private String[] choses;

    private List localNetSel;

    private List localNets;// 员工可访问的本地网。

    private String iscenter;
    
    //added by yangkai 2009-6-25
    private String chbSts;
    
    private String chbLocalNetId;
    
    private String[] areaIds;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String[] getChoses() {
        return choses;
    }

    public void setChoses(String[] choses) {
        this.choses = choses;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public List getLocalNets() {
        return localNets;
    }

    public void setLocalNets(List localNets) {
        this.localNets = localNets;
    }

    public List getLocalNetSel() {
        return localNetSel;
    }

    public void setLocalNetSel(List localNetSel) {
        this.localNetSel = localNetSel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

	public String getChbSts() {
		return chbSts;
	}

	public void setChbSts(String chbSts) {
		this.chbSts = chbSts;
	}

	public String[] getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String[] areaIds) {
		this.areaIds = areaIds;
	}

	public String getChbLocalNetId() {
		return chbLocalNetId;
	}

	public void setChbLocalNetId(String chbLocalNetId) {
		this.chbLocalNetId = chbLocalNetId;
	}

}
