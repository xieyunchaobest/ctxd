package com.cattsoft.sm.component.domain;

import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.MaxId;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.sm.component.dao.IAttachmentMDAO;
import com.cattsoft.sm.component.dao.IAttachmentSDAO;
import com.cattsoft.sm.vo.AttachmentMVO;
import com.cattsoft.sm.vo.AttachmentSVO;

public class AttachmentDOM {
    private static Logger log = Logger.getLogger(AttachmentDOM.class);
    
    public void add(AttachmentMVO attachMVO) throws SysException, AppException {
		IAttachmentSDAO dao = (IAttachmentSDAO) DAOFactory
				.getDAO(IAttachmentSDAO.class);
		IAttachmentMDAO mdao = (IAttachmentMDAO) DAOFactory
				.getDAO(IAttachmentMDAO.class);
		String fileName = attachMVO.getFile().getFileName();
		AttachmentSVO svo = new AttachmentSVO();
		svo.setLocalNetId(attachMVO.getLocalNetId());
		svo.setForScene(attachMVO.getForScene());
		svo.setObjectId(attachMVO.getObjectId());
		svo.setFileName(fileName);
		svo.setSts(SysConstants.USE_YES_STS);
		svo.setStsDate(DateUtil.getDBDate());
		svo.setAttachmentId(MaxId
				.getSequenceNextVal(SysConstants.ATTACHMENT_ID_SEQ));
		dao.add(svo);
		mdao.updateAttachment(attachMVO.getFile(), svo.getAttachmentId());		
	}
    
    public List findByVo(AttachmentSVO attachSVO) throws SysException, AppException {
		IAttachmentSDAO dao = (IAttachmentSDAO) DAOFactory
				.getDAO(IAttachmentSDAO.class);
		return dao.findByVO(attachSVO);
    }
    
    public AttachmentSVO findSVOByVo(AttachmentSVO attachSVO) throws SysException, AppException {
    	List list = findByVo(attachSVO);
    	if(list==null || list.size()==0){
    		return null;
    	}
    	return (AttachmentSVO)list.get(0);
    }
    
    public AttachmentMVO findMVOByVo(AttachmentSVO attachSVO) throws SysException, AppException {
    	
    	AttachmentSVO svo = findSVOByVo(attachSVO);
    	if(svo==null){
    		return null;
    	}
    	AttachmentMVO mvo = new AttachmentMVO();
    	mvo.setAttachmentId(svo.getAttachmentId());
    	mvo.setLocalNetId(svo.getLocalNetId());
    	mvo.setForScene(svo.getForScene());
    	mvo.setObjectId(svo.getObjectId());
    	mvo.setFileName(svo.getFileName());
    	mvo.setSts(svo.getSts());
    	mvo.setStsDate(svo.getStsDate());
    	mvo.setRemarks(svo.getRemarks());
    	return mvo;
    }

	public void deleteByVo(AttachmentMVO attachMVO) throws SysException, AppException {
		IAttachmentMDAO mdao = (IAttachmentMDAO) DAOFactory
			.getDAO(IAttachmentMDAO.class);
		mdao.deleteByVo(attachMVO);
	}

	public AttachmentMVO findAttachmentByPK(AttachmentSVO vo) throws AppException, SysException {
		IAttachmentMDAO dao = (IAttachmentMDAO) DAOFactory
				.getDAO(IAttachmentMDAO.class);
		return (AttachmentMVO)dao.findByPK(vo);
	}
    
    
}
