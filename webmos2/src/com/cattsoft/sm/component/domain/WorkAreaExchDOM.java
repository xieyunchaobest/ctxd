package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IExchSDAO;
import com.cattsoft.sm.component.dao.IStaffMDAO;
import com.cattsoft.sm.component.dao.IWorkAreaExchMDAO;
import com.cattsoft.sm.component.dao.IWorkAreaExchSDAO;
import com.cattsoft.sm.component.dao.IWorkAreaMDAO;
import com.cattsoft.sm.component.dao.IWorkAreaSDAO;
import com.cattsoft.sm.vo.WorkAreaExchMVO;
import com.cattsoft.sm.vo.WorkAreaExchSVO;
import com.cattsoft.sm.vo.WorkAreaSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-28 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class WorkAreaExchDOM {

	private Logger log = Logger.getLogger(WorkAreaExchDOM.class);

	private IWorkAreaExchMDAO workAreaExchMDao = (IWorkAreaExchMDAO) DAOFactory
			.getInstance().getDAO(IWorkAreaExchMDAO.class);

	private IWorkAreaExchSDAO workAreaExchDao = (IWorkAreaExchSDAO) DAOFactory
			.getInstance().getDAO(IWorkAreaExchSDAO.class);

	private IWorkAreaSDAO workAreaDao = (IWorkAreaSDAO) DAOFactory
			.getInstance().getDAO(IWorkAreaSDAO.class);
	
	private IExchSDAO exchDao = (IExchSDAO) DAOFactory.getInstance().getDAO(IExchSDAO.class);

	private IWorkAreaMDAO workAreaMDao = (IWorkAreaMDAO) DAOFactory
			.getInstance().getDAO(IWorkAreaMDAO.class);

	private IStaffMDAO staffDao = (IStaffMDAO) DAOFactory.getInstance().getDAO(
			IStaffMDAO.class);

	public List findWorkAreaExchs(WorkAreaExchMVO vo) throws AppException,
			SysException {

		return workAreaExchMDao.findWorkAreaExchs(vo);
	}

	public List findExchsUnSel(WorkAreaExchMVO vo) throws SysException,
			AppException {
		List vos = new ArrayList();
		if (log.isDebugEnabled())
			log.debug("开始查未选择的局向。。。工区：" + vo.getWorkAreaId());

		if (vo.getWorkAreaId() == null)
			return vos;
		// 查找工区
		WorkAreaSVO wvo = new WorkAreaSVO();
		wvo.setWorkAreaId(vo.getWorkAreaId());
		vo.setWorkAreaSVO((WorkAreaSVO) workAreaDao.findByPK(wvo));
		if (vo.getWorkAreaSVO().getWorkTypeId().equals("1")) {// 1代表营业工区
			if (log.isDebugEnabled())
				log.debug("现正查非营业工区可选的局向。。。");
			vos = workAreaExchMDao.findExchsUnSelNotSo(vo);
		} else {
			if (log.isDebugEnabled())
				log.debug("现正查营业工区可选的局向。。。");
			vos = workAreaExchMDao.findExchsUnSelSo(vo);
		}
		return vos;
	}

	public void delWorkAreaExch(String[] workExchIds) throws SysException,
			AppException {
		WorkAreaExchSVO svo = new WorkAreaExchSVO();
		WorkAreaExchSVO Waesvo = new WorkAreaExchSVO();
		WorkAreaSVO wasvo = new WorkAreaSVO();
		Date curDate = SysDateUtil.getCurrentDate();
		if(null!=workExchIds){
			for (int i = 0; i < workExchIds.length; i++) {
				wasvo = (WorkAreaSVO) workAreaMDao.findByWorkExch(workExchIds[i]);
				svo.setWorkAreaExchId(workExchIds[i]);
				Waesvo = (WorkAreaExchSVO) workAreaExchDao.findByPK(svo);
				svo.setWorkAreaId(Waesvo.getWorkAreaId());
				svo.setExchId(Waesvo.getExchId());
				svo.setCreateDate(Waesvo.getCreateDate());
				svo.setSts(Constant.STS_HISTORY);
				svo.setStsDate(curDate);
				workAreaExchMDao.updateWorkAreaExch(svo);
				
			}
		}
	}
	
	
	/**
	 * 删除工区
	 * @author yangkai 2009-9-1
	 * @param workExchIds
	 * @throws SysException
	 * @throws AppException
	 */
	public void delExchWorkArea(String[] workExchIds) throws SysException,AppException {
		WorkAreaExchSVO svo = new WorkAreaExchSVO();		
		for (int i = 0; i < workExchIds.length; i++) {			
			svo.setWorkAreaExchId(workExchIds[i]);
			svo =(WorkAreaExchSVO)workAreaExchDao.findByPK(svo);
			//workAreaExchDao.delete(svo); 
			svo.setExchId(svo.getExchId());
			svo.setWorkAreaId(svo.getWorkAreaId());
			svo.setSts(Constant.STS_IN_USE);
			svo.setCreateDate(svo.getCreateDate());
			svo.setStsDate(SysDateUtil.getCurrentDate());
			workAreaExchMDao.deleteWorkAreaExch(svo);
		}
      }

	public void addWorkAreaExch(String[] exchIds, String workAreaId)
			throws SysException, AppException {
		WorkAreaExchSVO svo = new WorkAreaExchSVO();
		WorkAreaExchSVO wvo = new WorkAreaExchSVO();
		WorkAreaSVO was = new WorkAreaSVO();
		Date curDate = SysDateUtil.getCurrentDate();
		List list = null;
		if(null!= exchIds){
			for (int i = 0; i < exchIds.length; i++) {
				svo.setWorkAreaId(workAreaId);
				svo.setExchId(exchIds[i]);
				was.setWorkAreaId(workAreaId);
				was = (WorkAreaSVO) workAreaDao.findByPK(was);
				list = workAreaExchDao.findByVO(svo);
				if (list != null && list.size() > 0) {
					wvo = (WorkAreaExchSVO) list.get(0);
					wvo.setSts(Constant.STS_IN_USE);
					wvo.setStsDate(curDate);
					workAreaExchMDao.updateWorkAreaExch(wvo);
					
				} else {
					wvo.setWorkAreaExchId(MaxId
							.getSequenceNextVal(Constant.SEQ_WORK_AREA_EXCH_ID));
					wvo.setExchId(exchIds[i]);
					wvo.setWorkAreaId(workAreaId);
					wvo.setSts(Constant.STS_IN_USE);
					wvo.setCreateDate(curDate);
					wvo.setStsDate(curDate);
					workAreaExchMDao.addWorkAreaExch(wvo);
					
				}
			}
		}

	}

	/**
	 * 根据WorkAreaId查找它所对应的所有ExchSVO
	 * 
	 * @param vo
	 * @return List<ExchSVO>
	 * @throws SysException
	 * @throws AppException
	 */
	public List findExchSVOByWorkAreaId(WorkAreaExchSVO vo)
			throws SysException, AppException {

		return workAreaExchMDao.findExchByWorkAreaSVO(vo);
	}

	/**
	 * 根据ExchId查找对应的所有员工信息
	 * 
	 * @param vo
	 * @return List<StaffMVO>
	 * @throws SysException
	 * @throws AppException
	 */
	public List findStaffMVOByExchId(WorkAreaExchSVO vo) throws SysException,
			AppException {
		return staffDao.findStaffMVOByExchId(vo);
	}

	/**
	 * 根据ExchId和StepId查找对应的工区信息
	 * 
	 * @param vo
	 * @return String
	 * @throws SysException
	 * @throws AppException
	 */
	public String findWorkAreaByExchAndStep(String exchId, String stepId)
			throws SysException, AppException {
		String workAreaId = null;
		workAreaId = workAreaExchMDao.findWorkAreaByExchAndStep(exchId, stepId);
		return workAreaId;
	}

	public List findByVO(WorkAreaExchSVO vo) throws SysException, AppException {
		return workAreaExchDao.findByVO(vo);
	}
	
	/**
	 * @author yangkai 2009-9-1
	 * @param vo
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public List findWorkAreaUnSel(WorkAreaExchMVO vo) throws SysException,
	AppException {
		List vos = new ArrayList();
		if (log.isDebugEnabled())
			log.debug("开始查未选择的工区：" + vo.getExchId());
		if (vo.getExchId() == null)
			return vos;
		log.debug("局向id:"+vo.getExchId()+";;;工区类型id："+vo.getWorkTypeId());
		vos = workAreaExchMDao.findWorkAreaUnSel(vo);		
		return vos;
		}
	
	public String addExchWorkArea(String[] workAreaIds, String exchId)
	throws SysException, AppException {
		String result=null;
		WorkAreaExchSVO wvo = new WorkAreaExchSVO();
		WorkAreaExchMVO mvo=new WorkAreaExchMVO();
		WorkAreaExchMVO lvo=new WorkAreaExchMVO();
		WorkAreaSVO wasvo=new WorkAreaSVO();
		mvo.setExchId(exchId);
		List list = new ArrayList();
		list = workAreaExchMDao.findWorkAreaExchs(mvo);//已有工区列表
		Date curDate = SysDateUtil.getCurrentDate();
		for (int i = 0; i < workAreaIds.length; i++) {
			wasvo.setWorkAreaId(workAreaIds[i]);
			wasvo = (WorkAreaSVO) workAreaDao.findByPK(wasvo);
			log.debug("工区类型为："+wasvo.getWorkTypeId());
			for(int j=0;j<list.size();j++){
				lvo=(WorkAreaExchMVO)list.get(j);				
				if((lvo.getWorkAreaId()).equals(wasvo.getWorkAreaId())){//如果已经添加过工区，则报异常
					result="不能重复分配工区！";
					return result;
				}
			        //throw new AppException("2300001","不能重复分配工区！");	//按理此异常不应该抛出
				log.debug("lvo.getWorkTypeId():"+lvo.getWorkTypeId());
				log.debug("wasvo.getWorkTypeId():"+wasvo.getWorkTypeId());
		        if((lvo.getWorkTypeId()).equals(wasvo.getWorkTypeId())){
		        	//如果要增加的工区的工区类型与已有的工区类型相同，则报异常
		        	result="给局向分配工区时，只能在每个工区类型下面选择一个工区！";
		        	return result;
			        //throw new AppException("2300002","给局向分配工区时，只能在每个工区类型下面选择一个工区！");	
		        }		       
			        	
	       }
	       //for循环结束后，如果没有抛出异常，说明不存在上述冲突，则可以执行增加	
//		WorkAreaExchSVO vo = new WorkAreaExchSVO();
//		List li=new ArrayList();
//		vo.setExchId(exchId);
//		vo.setWorkAreaId(workAreaIds[i]);
//		li=workAreaExchDao.findByVO(vo);
//		if(li!=null && li.size()>0)
//			result="已存在局向工区";
		wvo.setWorkAreaExchId(MaxId
				.getSequenceNextVal(Constant.SEQ_WORK_AREA_EXCH_ID));
		wvo.setExchId(exchId);
		wvo.setWorkAreaId(workAreaIds[i]);
		wvo.setSts(Constant.STS_IN_USE);
		wvo.setCreateDate(curDate);
		wvo.setStsDate(curDate);
		workAreaExchMDao.addWorkAreaExch(wvo);		
	}
		return result;
		
}
	}
