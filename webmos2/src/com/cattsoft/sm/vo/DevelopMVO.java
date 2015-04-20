package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-4 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class DevelopMVO extends GenericVO {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -9153085647926599049L;

	String partyRoleType;

    String localNetId;

    String areaId;

    String servDeptId;

    String branchId;

    String name;
    /**
     * 根据发展人的类型不同,developId对应不同的id.
     */
    String developId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getDevelopId() {
        return developId;
    }

    public void setDevelopId(String developId) {
        this.developId = developId;
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

    public String getPartyRoleType() {
        return partyRoleType;
    }

    public void setPartyRoleType(String partyRoleType) {
        this.partyRoleType = partyRoleType;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

}
