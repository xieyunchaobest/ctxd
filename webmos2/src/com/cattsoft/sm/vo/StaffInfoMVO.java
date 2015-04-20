package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

public class StaffInfoMVO extends GenericVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9181715617644229979L;
	private String name=null;
	private String staffId=null;
	private String partyId = null;
	
	private int flagName = 0;
	private int flagStaffId= 0;
	private int flagPartyId = 0;
	
	public int getFlagName() {
		return flagName;
	}

	public int getFlagPartyId() {
		return flagPartyId;
	}

	public int getFlagStaffId() {
		return flagStaffId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		flagName=1;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
		flagPartyId=1;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
		flagStaffId=1;
	}
	

}
