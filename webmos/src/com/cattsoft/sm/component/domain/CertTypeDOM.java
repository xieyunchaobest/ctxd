package com.cattsoft.sm.component.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.MaxId;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.sm.component.dao.ICertTypeMDAO;
import com.cattsoft.sm.component.dao.ICertTypeSDAO;
import com.cattsoft.sm.vo.CertTypeMVO;
import com.cattsoft.sm.vo.CertTypeSVO;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.vo.ActionLogSVO;

/**
 * Title: 系统管理-员工管理-客户类型管理<br>
 * Description: 客户类型管理DOM <br>
 * Date: 2007-7-30 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * Modify by yangkai 2009-04-28
 * @author anzhiqiang
 */
public class CertTypeDOM {	

    private static Logger log = Logger.getLogger(CertTypeDOM.class);
    public List findCertTypeByVO(CertTypeSVO vo) throws AppException, SysException {
    	ICertTypeSDAO certTypeSDao = (ICertTypeSDAO) DAOFactory.getInstance().getDAO(
                ICertTypeSDAO.class);    	
    	return certTypeSDao.findByVO(vo);
    }
    
    /**
	 * 功能：查询客户类型
	 * 
	 * @param CertTypeSVO:客户类型数据结构
	 * @throws SysException
	 * @throws AppException
	 * @return List<WorkTypeSVO>
	 * @author modified by yangkai change method name
	 */
	public List find(CertTypeSVO vo) throws AppException, SysException {
		ICertTypeMDAO dao = (ICertTypeMDAO) DAOFactory.getInstance().getDAO(ICertTypeMDAO.class);
		return dao.findByVO(vo);
	}

	/**
	 * 功能：增加客户类型
	 * 
	 * @param CertTypeMVO:客户类型数据结构
	 * @throws SysException
	 * @throws AppException
	 * 
	 */
	public void add(CertTypeMVO vo) throws AppException, SysException {
		log.debug("进入增加客户类型add方法！");
		ICertTypeSDAO dao = (ICertTypeSDAO)DAOFactory.getInstance().getDAO(ICertTypeSDAO.class);
		String certTypeId = MaxId
				.getSequenceNextVal(SysConstants.CERT_TYPE_ID_SEQ);// 新增客户类型序列
		vo.setCertTypeId(certTypeId);
		dao.add(vo);
		ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		actionLog.setActionText(certTypeId + actionLog.getActionText());
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory.getInstance().getDAO(IActionLogSDAO.class);
		sDao.add(actionLog);
	}

	/**
	 * 功能：修改客户类型初始化
	 * 
	 * @param CertTypeMVO:客户类型数据结构
	 * @throws SysException
	 * @throws AppException
	 * @return CertTypeMVO
	 */
	public CertTypeMVO updateInit(CertTypeMVO mvo) throws AppException,
			SysException {
		ICertTypeMDAO dao = (ICertTypeMDAO) DAOFactory
				.getInstance().getDAO(ICertTypeMDAO.class);
		return (CertTypeMVO) dao.findByPK(mvo);
	}

	/**
	 * 功能：修改客户类型
	 * 
	 * @param CertTypeMVO:客户类型数据结构
	 * @throws SysException
	 * @throws AppException
	 * @return void
	 */
	public void update(CertTypeMVO vo) throws AppException, SysException {
		ICertTypeSDAO dao = (ICertTypeSDAO) DAOFactory
				.getInstance().getDAO(ICertTypeSDAO.class);
		dao.update(vo);
		ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory
				.getInstance().getDAO(IActionLogSDAO.class);
		sDao.add(actionLog);
	}

	/**
	 * 功能：注销客户类型
	 * 
	 * @param CertTypeList:客户类型数据结构
	 * @throws SysException
	 * @throws AppException
	 * @return void
	 */
	public void unable(List certTypeList) throws AppException, SysException {
		if (certTypeList == null)
			throw new AppException("", "注销集合对象为null");
		ICertTypeMDAO dao = (ICertTypeMDAO) DAOFactory.getInstance().getDAO(ICertTypeMDAO.class);
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory.getInstance().getDAO(IActionLogSDAO.class);
		dao.unableBat(certTypeList);
		if (certTypeList != null && certTypeList.size() > 0) {
			List list = new ArrayList();
			for (int i = 0; i < certTypeList.size(); i++) {
				CertTypeMVO mvo = (CertTypeMVO) certTypeList.get(i);
				ActionLogSVO svo = (ActionLogSVO) mvo.getActionLog();
				svo.setActionId(MaxId
						.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
				list.add(svo);
			}
			sDao.addBat(list);
		}
	}

}
