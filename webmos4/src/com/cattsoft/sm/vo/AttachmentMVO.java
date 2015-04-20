package com.cattsoft.sm.vo;

import org.apache.struts.upload.FormFile;

public class AttachmentMVO extends AttachmentSVO {
	
	private static final long serialVersionUID = 964086699907075923L;

	private FormFile file;
	
	private byte[] bytes;

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
}
