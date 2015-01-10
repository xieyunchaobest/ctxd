package com.cattsoft.sm.vo;
import java.io.*;

import com.cattsoft.pub.vo.GenericVO;

import java.util.*;
import java.sql.Clob;
import java.sql.Blob;
 /**
   * StaffMemberSVO
   * @author £º°×Ð¡ÁÁ¡£
   * @version 1.0  2007-5-14
   * <p>Company: ´óÌÆÈí¼þ</p>
  */
public class StaffMemberSVO extends GenericVO {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 427907310751313719L;
private Date createDate = null;
private String deptId = null;
private Date inCompDate = null;
private Date inDepaDate = null;
private String partyId = null;
private String staffId = null;
private String staffLevel = null;
private String standardCode = null;
private String station = null;
private String sts = null;
private Date stsDate = null;
private int flagCreateDate = 0;
private int flagDeptId = 0;
private int flagInCompDate = 0;
private int flagInDepaDate = 0;
private int flagPartyId = 0;
private int flagStaffId = 0;
private int flagStaffLevel = 0;
private int flagStandardCode = 0;
private int flagStation = 0;
private int flagSts = 0;
private int flagStsDate = 0;
public Date getCreateDate(){ 
 return createDate;
}
public void setCreateDate(Date newValue){  
 this.createDate = newValue;
  flagCreateDate = 1;
}
public int getFlagCreateDate(){ 
 return flagCreateDate;
}
public String getDeptId(){
 return deptId;
}
public void setDeptId(String newValue){ 
 this.deptId = newValue;
  flagDeptId = 1;
}
public int getFlagDeptId(){ 
 return flagDeptId;
}
public Date getInCompDate(){ 
 return inCompDate;
}
public void setInCompDate(Date newValue){  
 this.inCompDate = newValue;
  flagInCompDate = 1;
}
public int getFlagInCompDate(){ 
 return flagInCompDate;
}
public Date getInDepaDate(){ 
 return inDepaDate;
}
public void setInDepaDate(Date newValue){  
 this.inDepaDate = newValue;
  flagInDepaDate = 1;
}
public int getFlagInDepaDate(){ 
 return flagInDepaDate;
}
public String getPartyId(){
 return partyId;
}
public void setPartyId(String newValue){ 
 this.partyId = newValue;
  flagPartyId = 1;
}
public int getFlagPartyId(){ 
 return flagPartyId;
}
public String getStaffId(){
 return staffId;
}
public void setStaffId(String newValue){ 
 this.staffId = newValue;
  flagStaffId = 1;
}
public int getFlagStaffId(){ 
 return flagStaffId;
}
public String getStaffLevel(){
 return staffLevel;
}
public void setStaffLevel(String newValue){ 
 this.staffLevel = newValue;
  flagStaffLevel = 1;
}
public int getFlagStaffLevel(){ 
 return flagStaffLevel;
}
public String getStandardCode(){
 return standardCode;
}
public void setStandardCode(String newValue){ 
 this.standardCode = newValue;
  flagStandardCode = 1;
}
public int getFlagStandardCode(){ 
 return flagStandardCode;
}
public String getStation(){
 return station;
}
public void setStation(String newValue){ 
 this.station = newValue;
  flagStation = 1;
}
public int getFlagStation(){ 
 return flagStation;
}
public String getSts(){
 return sts;
}
public void setSts(String newValue){ 
 this.sts = newValue;
  flagSts = 1;
}
public int getFlagSts(){ 
 return flagSts;
}
public Date getStsDate(){ 
 return stsDate;
}
public void setStsDate(Date newValue){  
 this.stsDate = newValue;
  flagStsDate = 1;
}
public int getFlagStsDate(){ 
 return flagStsDate;
}
public void clearFlagCreateDate(){
 flagCreateDate = 0 ;
}
public void clearFlagDeptId(){
 flagDeptId = 0 ;
}
public void clearFlagInCompDate(){
 flagInCompDate = 0 ;
}
public void clearFlagInDepaDate(){
 flagInDepaDate = 0 ;
}
public void clearFlagPartyId(){
 flagPartyId = 0 ;
}
public void clearFlagStaffId(){
 flagStaffId = 0 ;
}
public void clearFlagStaffLevel(){
 flagStaffLevel = 0 ;
}
public void clearFlagStandardCode(){
 flagStandardCode = 0 ;
}
public void clearFlagStation(){
 flagStation = 0 ;
}
public void clearFlagSts(){
 flagSts = 0 ;
}
public void clearFlagStsDate(){
 flagStsDate = 0 ;
}
public void clearAll(){ flagCreateDate = 0;
 flagDeptId = 0;
 flagInCompDate = 0;
 flagInDepaDate = 0;
 flagPartyId = 0;
 flagStaffId = 0;
 flagStaffLevel = 0;
 flagStandardCode = 0;
 flagStation = 0;
 flagSts = 0;
 flagStsDate = 0;

}
}
