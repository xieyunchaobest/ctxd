package com.cattsoft.sm.struts;

import java.sql.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.cattsoft.sm.vo.HandleTypeSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-12 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class HandleTypeActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String handleTypeId;

	private String name;

	private String systemName;

	private Date createDate;

	private String[] handleTypeIds;

	private HandleTypeSVO handleTypeSVO = new HandleTypeSVO();

	private String chbHandleTypeId;

	private String chbName;

	private String chbSystemName;

	private String handleTypeIdSelect;

	private String nameSelect;

	private String systemNameSelect;

	private List handleTypeIdList;

	private List nameList;

	private List systemNameList;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getHandleTypeId() {
		return handleTypeId;
	}

	public void setHandleTypeId(String handleTypeId) {
		this.handleTypeId = handleTypeId;
	}

	public String[] getHandleTypeIds() {
		return handleTypeIds;
	}

	public void setHandleTypeIds(String[] handleTypeIds) {
		this.handleTypeIds = handleTypeIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public HandleTypeSVO getHandleTypeSVO() {
		return handleTypeSVO;
	}

	public void setHandleTypeSVO(HandleTypeSVO handleTypeSVO) {
		this.handleTypeSVO = handleTypeSVO;
	}

	public String getChbHandleTypeId() {
		return chbHandleTypeId;
	}

	public void setChbHandleTypeId(String chbHandleTypeId) {
		this.chbHandleTypeId = chbHandleTypeId;
	}

	public String getChbName() {
		return chbName;
	}

	public void setChbName(String chbName) {
		this.chbName = chbName;
	}

	public String getChbSystemName() {
		return chbSystemName;
	}

	public void setChbSystemName(String chbSystemName) {
		this.chbSystemName = chbSystemName;
	}

	public List getHandleTypeIdList() {
		return handleTypeIdList;
	}

	public void setHandleTypeIdList(List handleTypeIdList) {
		this.handleTypeIdList = handleTypeIdList;
	}

	public String getHandleTypeIdSelect() {
		return handleTypeIdSelect;
	}

	public void setHandleTypeIdSelect(String handleTypeIdSelect) {
		this.handleTypeIdSelect = handleTypeIdSelect;
	}

	public List getNameList() {
		return nameList;
	}

	public void setNameList(List nameList) {
		this.nameList = nameList;
	}

	public String getNameSelect() {
		return nameSelect;
	}

	public void setNameSelect(String nameSelect) {
		this.nameSelect = nameSelect;
	}

	public List getSystemNameList() {
		return systemNameList;
	}

	public void setSystemNameList(List systemNameList) {
		this.systemNameList = systemNameList;
	}

	public String getSystemNameSelect() {
		return systemNameSelect;
	}

	public void setSystemNameSelect(String systemNameSelect) {
		this.systemNameSelect = systemNameSelect;
	}

}
