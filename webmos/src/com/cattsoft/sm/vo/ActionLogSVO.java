package com.cattsoft.sm.vo;



import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class ActionLogSVO extends GenericVO {

	private static final long serialVersionUID = 1L;

	private String actionDomain = null;
	private String actionId = null;
	private String actionModule = null;
	private String actionText = null;
	private Date actionTime = null;
	private String actionType = null;
	private String loginId = null;
	private int flagActionDomain = 0;
	private int flagActionId = 0;
	private int flagActionModule = 0;
	private int flagActionText = 0;
	private int flagActionTime = 0;
	private int flagActionType = 0;
	private int flagLoginId = 0;
	public String getActionDomain(){
		return actionDomain;
	}
	public void setActionDomain(String newValue){ 
		this.actionDomain = newValue;
		flagActionDomain = 1;
	}
	public int getFlagActionDomain(){ 
		return flagActionDomain;
	}
	public String getActionId(){
		return actionId;
	}
	public void setActionId(String newValue){ 
		this.actionId = newValue;
		flagActionId = 1;
	}
	public int getFlagActionId(){ 
		return flagActionId;
	}
	public String getActionModule(){
		return actionModule;
	}
	public void setActionModule(String newValue){ 
		this.actionModule = newValue;
		flagActionModule = 1;
	}
	public int getFlagActionModule(){ 
		return flagActionModule;
	}
	public String getActionText(){
		return actionText;
	}
	public void setActionText(String newValue){ 
		this.actionText = newValue;
		flagActionText = 1;
	}
	public int getFlagActionText(){ 
		return flagActionText;
	}
	public Date getActionTime(){ 
		return actionTime;
	}
	public void setActionTime(Date newValue){  
		this.actionTime = newValue;
		flagActionTime = 1;
	}
	public int getFlagActionTime(){ 
		return flagActionTime;
	}
	public String getActionType(){
		return actionType;
	}
	public void setActionType(String newValue){ 
		this.actionType = newValue;
		flagActionType = 1;
	}
	public int getFlagActionType(){ 
		return flagActionType;
	}
	public String getLoginId(){
		return loginId;
	}
	public void setLoginId(String newValue){ 
		this.loginId = newValue;
		flagLoginId = 1;
	}
	public int getFlagLoginId(){ 
		return flagLoginId;
	}
	public void clearFlagActionDomain(){
		flagActionDomain = 0 ;
	}
	public void clearFlagActionId(){
		flagActionId = 0 ;
	}
	public void clearFlagActionModule(){
		flagActionModule = 0 ;
	}
	public void clearFlagActionText(){
		flagActionText = 0 ;
	}
	public void clearFlagActionTime(){
		flagActionTime = 0 ;
	}
	public void clearFlagActionType(){
		flagActionType = 0 ;
	}
	public void clearFlagLoginId(){
		flagLoginId = 0 ;
	}
	public void clearAll(){ flagActionDomain = 0;
	flagActionId = 0;
	flagActionModule = 0;
	flagActionText = 0;
	flagActionTime = 0;
	flagActionType = 0;
	flagLoginId = 0;

	}
}