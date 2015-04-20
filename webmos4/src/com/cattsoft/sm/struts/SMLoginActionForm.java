package com.cattsoft.sm.struts;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-24 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SMLoginActionForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    /**
     * 密码
     */
    private String password;
//    private String expiredDays;//还剩几天过期

    /**
     * 用户名
     */
    private String userName;
    private String remUsername="0";
	
	private String localNetName;


	private List areas = new ArrayList();

	/**
	 * 供选择的工区
	 */
    /**
     * 子系统名称，如：SM、CM
     */
    private String subSystemName;

    /**
     * 子系统下的节点分区
     */
    private String subNode;
    
    /**
     * 供选择的工区
     */
    private List workAreas = new ArrayList();

    /**
     * 选择的工区id
     */
    private String workAreaId;

    private String localNetId;

    private List local = new ArrayList();

    private List localNets = new ArrayList();

    private String funcNodeId;

    private String funcNodeName;

    private String oldPassword;

    private String areaId;

    private String password1;

    private String tg;

    /**
     * 标志为 1表示从选择工作区页面进入,2表示点击切换工区进入
     */
    private String mark;

    private String href;

    private String wavSize;

    public String getWavSize() {
        return wavSize;
    }

    public void setWavSize(String wavSize) {
        this.wavSize = wavSize;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getFuncNodeId() {
        return funcNodeId;
    }

    public void setFuncNodeId(String funcNodeId) {
        this.funcNodeId = funcNodeId;
    }

    public List getLocal() {
        return local;
    }

    public void setLocal(List local) {
        this.local = local;
    }

    public List getLocalNets() {
        return localNets;
    }

    public void setLocalNets(List localNets) {
        this.localNets = localNets;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getSubSystemName() {
        return subSystemName;
    }

    public void setSubSystemName(String subSystemName) {
        this.subSystemName = subSystemName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(String workAreaId) {
        this.workAreaId = workAreaId;
    }

    public List getWorkAreas() {
        return workAreas;
    }

    public void setWorkAreas(List workAreas) {
        this.workAreas = workAreas;
    }

    public String getFuncNodeName() {
        return funcNodeName;
    }

    public void setFuncNodeName(String funcNodeName) {
        this.funcNodeName = funcNodeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getSubNode() {
		return subNode;
	}

	public void setSubNode(String subNode) {
		this.subNode = subNode;
	}

	public String getLocalNetName() {
		return localNetName;
	}

	public void setLocalNetName(String localNetName) {
		this.localNetName = localNetName;
	}

	public List getAreas() {
		return areas;
	}

	public void setAreas(List areas) {
		this.areas = areas;
	}

	public String getRemUsername() {
		return remUsername;
	}

	public void setRemUsername(String remUsername) {
		this.remUsername = remUsername;
	}

//	public String getExpiredDays() {
//		return expiredDays;
//	}
//
//	public void setExpiredDays(String expiredDays) {
//		this.expiredDays = expiredDays;
//	}

}
