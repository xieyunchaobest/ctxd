package com.cattsoft.sm.struts;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2010-2-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author caiqian
 */


public class AttachmentActionForm extends ActionForm {
    
    private String attachmentId;

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
}
