package com.cattsoft.tm.struts;

import org.apache.struts.action.ActionForm;

/**
 * 
 * Title: 服务开通系统-知识库子系统<br>
 * Description: 知识库区域管理ActionForm<br>
 * Date: 2012-8-27 <br>
 * Copyright (c) 2012 CATTSoft<br>
 * 
 * @author 鲁攀
 */

public class KbForumForm extends ActionForm {

	/**
	 * 自动生成序列号
	 */
	private static final long serialVersionUID = -8183726975099455537L;
	
	/**
	 * 分类ID
	 */
	private String categories_id;
	/**
	 * 分类标题
	 */
	private String title;
	/**
	 * 显示顺序
	 */
	private String display_order;
	/**
	 * 是否需要审核
	 */
	private String moderated;
	
	/**
	 * 无参构造函数
	 */
	public KbForumForm() {
		// TODO Auto-generated constructor stub
	}

	public String getCategories_id() {
		return categories_id;
	}

	public void setCategories_id(String categoriesId) {
		categories_id = categoriesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDisplay_order() {
		return display_order;
	}

	public void setDisplay_order(String displayOrder) {
		display_order = displayOrder;
	}

	public String getModerated() {
		return moderated;
	}

	public void setModerated(String moderated) {
		this.moderated = moderated;
	}

}
