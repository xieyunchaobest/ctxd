package com.cattsoft.sm.struts;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-6 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SMLocalNetActionForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1603954976724155328L;

	private String localNetId;

    private String name;

    private String[] choses;

    private String sts;

    private String iscenter;

    private String abbrevName;

    private String distNbr;

    private Date stsDate;

    private Date createDate;
    
    //added by yangkai 2009-6-24 增加下面4个属性
    
    private String chbName;
    
    private String chbSts;
    
    private String[] localNetIdAry = null;

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
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

    public String getDistNbr() {
        return distNbr;
    }

    public void setDistNbr(String distNbr) {
        this.distNbr = distNbr;
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

	public String getChbName() {
		return chbName;
	}

	public void setChbName(String chbName) {
		this.chbName = chbName;
	}

	public String getChbSts() {
		return chbSts;
	}

	public void setChbSts(String chbSts) {
		this.chbSts = chbSts;
	}
	
	public String[] getLocalNetIdAry() {
		return localNetIdAry;
	}

	public void setLocalNetIdAry(String[] localNetIdAry) {
		this.localNetIdAry = localNetIdAry;
	}

}
