package com.cattsoft.sm.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @Title: 服务开通系统
 * @Description:
 * @Date: 2008-11-14
 * @Copyright (c) 2008 CATTSoft
 * @author lijiancheng
 * 
 */
public class SysConfigInfoActionForm extends ActionForm {
	private static final long serialVersionUID = 8045972586850435976L;
	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}


}
