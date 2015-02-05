package com.cattsoft.tm.vo;

import java.io.Serializable;
import java.util.List;
/**
 * Title: 服务开通系统 <br>
 * Description: 树的bean类<br>
 * Date: Sep 18, 2012 <br>
 * Copyright (c) 2012 CATTSoft <br>
 * 
 * @author 
 */
public class FuncMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7869980123274710488L;

	private String name="";
	
	private List children;
	
	private String open="";
	
	private String url="";

	private String parentId;
	
	private String id;
	
	private String depth;
	
	private String target="right";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	
	

}
