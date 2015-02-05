package com.cattsoft.tm.vo;

import java.util.List;

public class MenuItem {
	private int id;
	private List item;
	private String text;
	private String child;
	private List<Userdata> userdata;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List getItem() {
		return item;
	}
	public void setItem(List item) {
		this.item = item;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public List<Userdata> getUserdata() {
		return userdata;
	}
	public void setUserdata(List<Userdata> userdata) {
		this.userdata = userdata;
	}
	
	
}
