package com.cattsoft.sm.vo;

import java.util.Date;

import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.ActionLogSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class ActionLogMVO extends GenericVO {
    private static final long serialVersionUID = 1L;

    private ActionLogSVO vo;

    private String actionTime;

    private String actionTime2;

    private Integer partyRoleTypeId;

    private String partyName;

    private Integer localNetId;

    private Integer areaId;
    

    private String chbSysuserName;
    private String sysuserName;//登陆用户名称
    
    private String chbSubSystemName;
    private String subSystemName;//操作子系统名称
    
    private String actionModuleName;//操作子模块名称
    
    private String chbActionTypeName;
    private String actionTypeName;//操作类型名称
    
    private String chbActionTime;//操作时间checkbox
    private Date fromActionTime;
    private Date toActionTime;

    private String isExportExcel =""; //是否限制导出EXCEL记录条数

    public ActionLogMVO() {
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }   

	public ActionLogSVO getVo() {
		return vo;
	}

	public void setVo(ActionLogSVO vo) {
		this.vo = vo;
	}

	public String getActionTime2() {
		return actionTime2;
	}

	public void setActionTime2(String actionTime2) {
		this.actionTime2 = actionTime2;
	}

	public Integer getPartyRoleTypeId() {
		return partyRoleTypeId;
	}

	public void setPartyRoleTypeId(Integer partyRoleTypeId) {
		this.partyRoleTypeId = partyRoleTypeId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Integer getLocalNetId() {
		return localNetId;
	}

	public void setLocalNetId(Integer localNetId) {
		this.localNetId = localNetId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getActionTypeName() {
		return actionTypeName;
	}

	public void setActionTypeName(String actionTypeName) {
		this.actionTypeName = actionTypeName;
	}

	public String getSubSystemName() {
		return subSystemName;
	}

	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}

	public String getSysuserName() {
		return sysuserName;
	}

	public void setSysuserName(String sysuserName) {
		this.sysuserName = sysuserName;
	}

	public String getChbActionTime() {
		return chbActionTime;
	}

	public void setChbActionTime(String chbActionTime) {
		this.chbActionTime = chbActionTime;
	}

	public String getChbActionTypeName() {
		return chbActionTypeName;
	}

	public void setChbActionTypeName(String chbActionTypeName) {
		this.chbActionTypeName = chbActionTypeName;
	}

	public String getChbSubSystemName() {
		return chbSubSystemName;
	}

	public void setChbSubSystemName(String chbSubSystemName) {
		this.chbSubSystemName = chbSubSystemName;
	}

	public String getChbSysuserName() {
		return chbSysuserName;
	}

	public void setChbSysuserName(String chbSysuserName) {
		this.chbSysuserName = chbSysuserName;
	}

	public Date getFromActionTime() {
		return fromActionTime;
	}

	public String getFromActionTimeStr(){
		String str ="";
		if(null!=fromActionTime){
			str = DateUtil.to_char(fromActionTime, "yyyy-MM-dd HH:mm:ss");
		}
		return str;
	}
	public void setFromActionTimeStr(String fromActionTimeStr) throws SysException{
		if(!StringUtil.isBlank(fromActionTimeStr)){
			this.fromActionTime = DateUtil.to_date(fromActionTimeStr, "yyyy-MM-dd HH:mm:ss");
			return ;
		}
		this.fromActionTime= null;
	}
	
	public void setFromActionTime(Date fromActionTime) {
		this.fromActionTime = fromActionTime;
	}
	
	public String getToActionTimeStr(){
		String str ="";
		if(null!=toActionTime){
			str = DateUtil.to_char(toActionTime, "yyyy-MM-dd HH:mm:ss");
		}
		return str;
	}
	
	public void setToActionTimeStr(String toActionTimeStr) throws SysException{
		if(!StringUtil.isBlank(toActionTimeStr)){
			this.toActionTime = DateUtil.to_date(toActionTimeStr, "yyyy-MM-dd HH:mm:ss");
			return ;
		}
		this.toActionTime= null;
	}
	public Date getToActionTime() {
		return toActionTime;
	}

	public void setToActionTime(Date toActionTime) {
		this.toActionTime = toActionTime;
	}

	public String getActionModuleName() {
		return actionModuleName;
	}

	public void setActionModuleName(String actionModuleName) {
		this.actionModuleName = actionModuleName;
	}

	public String getIsExportExcel() {
		return isExportExcel;
	}

	public void setIsExportExcel(String isExportExcel) {
		this.isExportExcel = isExportExcel;
	}

}
