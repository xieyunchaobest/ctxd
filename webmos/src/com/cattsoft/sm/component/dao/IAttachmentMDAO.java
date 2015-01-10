package com.cattsoft.sm.component.dao;

import org.apache.struts.upload.FormFile;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.AttachmentMVO;

/**
 * 
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2008-12-24 <br>
 * Copyright (c) 2008 CATTSoft<br>
 * 
 * @author shilei
 */
public interface IAttachmentMDAO extends IDAO {
	public void updateAttachment(FormFile file, String soAttachId)
			throws AppException, SysException;

	public void deleteByVo(AttachmentMVO attachMVO)
			throws AppException, SysException;
	
	public GenericVO findByPK(GenericVO vo) throws AppException, SysException ;
}
