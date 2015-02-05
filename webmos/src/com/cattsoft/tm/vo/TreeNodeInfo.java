package com.cattsoft.tm.vo;

import java.io.Serializable;
/**
 * Title: 服务开通系统 <br>
 * Description: 树的bean类<br>
 * Date: Sep 18, 2012 <br>
 * Copyright (c) 2012 CATTSoft <br>
 * 
 * @author 
 */
public class TreeNodeInfo implements Serializable {

	private static final long serialVersionUID = -8551599735222488199L;
	/**
	 * 树id
	 */
	private String id;
	/**
	 * 树名称
	 */
	private String name;
	/**
	 * 树父亲id
	 */
	private String parentId;
	/**
	 * url
	 */
	private String url;
	/**
	 * 排序位置
	 */
	private Long sortPosition;
	/**
	 * 深度
	 */
	private Long depth;
	/**
	 *  用于标志是否显示用户数据，如<userdata name="url">../system/organization.jsp</userdata>
	 */
	private String userdataFalg;
	/**
	 *  用于标识树是的状态是否为被选中
	 */
	private String selectedFlag;
	/**
	 *  M 菜单 B 按钮 O组织 A 区域 U用户
	 */
	private String funcNodeType;
	/**
	 * 用于标示树节点的超链接
	 */
	private String html;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getSortPosition() {
		return sortPosition;
	}

	public void setSortPosition(Long sortPosition) {
		this.sortPosition = sortPosition;
	}

	public Long getDepth() {
		return depth;
	}

	public void setDepth(Long depth) {
		this.depth = depth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserdataFalg() {
		return userdataFalg;
	}

	public void setUserdataFalg(String userdataFalg) {
		this.userdataFalg = userdataFalg;
	}

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	public String getFuncNodeType() {
		return funcNodeType;
	}

	public void setFuncNodeType(String funcNodeType) {
		this.funcNodeType = funcNodeType;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
