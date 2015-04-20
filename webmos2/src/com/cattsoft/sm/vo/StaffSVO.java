package com.cattsoft.sm.vo;

import java.io.*;
import com.cattsoft.pub.vo.GenericVO;
import java.util.*;
import java.sql.Clob;
import java.sql.Blob;

/**
 * StaffSVO
 * @author £º°×Ð¡ÁÁ¡£
 * @version 1.1  2007-9-23
 * <p>Company: ´óÌÆÈí¼þ</p>
 */
public class StaffSVO extends GenericVO {
	private String companyCode = null;
	private Date createDate = null;
	private String deptId = null;
	private String deptType = null;
	private String localNetId = null;
	private String partyId = null;
	private String position = null;
	private String simPassword = null;
	private String simSysUserName = null;
	private String staffId = null;
	private String standardCode = null;
	private String sts = null;
	private Date stsDate = null;
	private String telNbr = null;
	private String terminalFlag = null;
	private int flagCompanyCode = 0;
	private int flagCreateDate = 0;
	private int flagDeptId = 0;
	private int flagDeptType = 0;
	private int flagLocalNetId = 0;
	private int flagPartyId = 0;
	private int flagPosition = 0;
	private int flagSimPassword = 0;
	private int flagSimSysUserName = 0;
	private int flagStaffId = 0;
	private int flagStandardCode = 0;
	private int flagSts = 0;
	private int flagStsDate = 0;
	private int flagTelNbr = 0;
	private int flagTerminalFlag = 0;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String newValue) {
		this.companyCode = newValue;
		flagCompanyCode = 1;
	}

	public int getFlagCompanyCode() {
		return flagCompanyCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date newValue) {
		this.createDate = newValue;
		flagCreateDate = 1;
	}

	public int getFlagCreateDate() {
		return flagCreateDate;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String newValue) {
		this.deptId = newValue;
		flagDeptId = 1;
	}

	public int getFlagDeptId() {
		return flagDeptId;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String newValue) {
		this.deptType = newValue;
		flagDeptType = 1;
	}

	public int getFlagDeptType() {
		return flagDeptType;
	}

	public String getLocalNetId() {
		return localNetId;
	}

	public void setLocalNetId(String newValue) {
		this.localNetId = newValue;
		flagLocalNetId = 1;
	}

	public int getFlagLocalNetId() {
		return flagLocalNetId;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String newValue) {
		this.partyId = newValue;
		flagPartyId = 1;
	}

	public int getFlagPartyId() {
		return flagPartyId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String newValue) {
		this.position = newValue;
		flagPosition = 1;
	}

	public int getFlagPosition() {
		return flagPosition;
	}

	public String getSimPassword() {
		return simPassword;
	}

	public void setSimPassword(String newValue) {
		this.simPassword = newValue;
		flagSimPassword = 1;
	}

	public int getFlagSimPassword() {
		return flagSimPassword;
	}

	public String getSimSysUserName() {
		return simSysUserName;
	}

	public void setSimSysUserName(String newValue) {
		this.simSysUserName = newValue;
		flagSimSysUserName = 1;
	}

	public int getFlagSimSysUserName() {
		return flagSimSysUserName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String newValue) {
		this.staffId = newValue;
		flagStaffId = 1;
	}

	public int getFlagStaffId() {
		return flagStaffId;
	}

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String newValue) {
		this.standardCode = newValue;
		flagStandardCode = 1;
	}

	public int getFlagStandardCode() {
		return flagStandardCode;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String newValue) {
		this.sts = newValue;
		flagSts = 1;
	}

	public int getFlagSts() {
		return flagSts;
	}

	public Date getStsDate() {
		return stsDate;
	}

	public void setStsDate(Date newValue) {
		this.stsDate = newValue;
		flagStsDate = 1;
	}

	public int getFlagStsDate() {
		return flagStsDate;
	}

	public String getTelNbr() {
		return telNbr;
	}

	public void setTelNbr(String newValue) {
		this.telNbr = newValue;
		flagTelNbr = 1;
	}

	public int getFlagTelNbr() {
		return flagTelNbr;
	}

	public String getTerminalFlag() {
		return terminalFlag;
	}

	public void setTerminalFlag(String newValue) {
		this.terminalFlag = newValue;
		flagTerminalFlag = 1;
	}

	public int getFlagTerminalFlag() {
		return flagTerminalFlag;
	}

	public void clearFlagCompanyCode() {
		flagCompanyCode = 0;
	}

	public void clearFlagCreateDate() {
		flagCreateDate = 0;
	}

	public void clearFlagDeptId() {
		flagDeptId = 0;
	}

	public void clearFlagDeptType() {
		flagDeptType = 0;
	}

	public void clearFlagLocalNetId() {
		flagLocalNetId = 0;
	}

	public void clearFlagPartyId() {
		flagPartyId = 0;
	}

	public void clearFlagPosition() {
		flagPosition = 0;
	}

	public void clearFlagSimPassword() {
		flagSimPassword = 0;
	}

	public void clearFlagSimSysUserName() {
		flagSimSysUserName = 0;
	}

	public void clearFlagStaffId() {
		flagStaffId = 0;
	}

	public void clearFlagStandardCode() {
		flagStandardCode = 0;
	}

	public void clearFlagSts() {
		flagSts = 0;
	}

	public void clearFlagStsDate() {
		flagStsDate = 0;
	}

	public void clearFlagTelNbr() {
		flagTelNbr = 0;
	}

	public void clearFlagTerminalFlag() {
		flagTerminalFlag = 0;
	}

	public void clearAll() {
		flagCompanyCode = 0;
		flagCreateDate = 0;
		flagDeptId = 0;
		flagDeptType = 0;
		flagLocalNetId = 0;
		flagPartyId = 0;
		flagPosition = 0;
		flagSimPassword = 0;
		flagSimSysUserName = 0;
		flagStaffId = 0;
		flagStandardCode = 0;
		flagSts = 0;
		flagStsDate = 0;
		flagTelNbr = 0;
		flagTerminalFlag = 0;

	}
}
