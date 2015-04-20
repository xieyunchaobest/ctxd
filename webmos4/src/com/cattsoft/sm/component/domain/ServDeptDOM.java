package com.cattsoft.sm.component.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.DataCache;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.component.dao.IBranchSDAO;
import com.cattsoft.sm.component.dao.IExchSDAO;
import com.cattsoft.sm.component.dao.IServDeptMDAO;
import com.cattsoft.sm.component.dao.IServDeptSDAO;
import com.cattsoft.sm.component.dao.IWorkAreaSDAO;
import com.cattsoft.sm.util.SMMaxId;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.BranchSVO;
import com.cattsoft.sm.vo.ExchSVO;
import com.cattsoft.sm.vo.ServDeptSVO;
import com.cattsoft.sm.vo.WorkAreaSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-27 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class ServDeptDOM {
	
    private IServDeptSDAO servDeptDao = (IServDeptSDAO) DAOFactory.getInstance().getDAO(
            IServDeptSDAO.class);
    private IWorkAreaSDAO workAreaDao = (IWorkAreaSDAO) DAOFactory.getInstance().getDAO(
            IWorkAreaSDAO.class);
    private IBranchSDAO branchDao = (IBranchSDAO) DAOFactory.getInstance().getDAO(
            IBranchSDAO.class);
    private IExchSDAO exchDao = (IExchSDAO) DAOFactory.getInstance().getDAO(
            IExchSDAO.class);
    //add by gaofei
    private IServDeptMDAO servDeptMDao = (IServDeptMDAO) DAOFactory.getInstance().getDAO(
    		IServDeptMDAO.class);

    private static Logger log = Logger.getLogger(ServDeptDOM.class);

    /**
     * 根据主键查询单表ServDeptSVO
     * 
     * @param vo
     * @return ServDeptSVO
     * @throws SysException
     * @throws AppException
     */
    public ServDeptSVO findByPk(ServDeptSVO vo) throws SysException, AppException {
        return (ServDeptSVO) servDeptDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表ServDeptSVO
     * 
     * @param vo
     * @return List<ServDeptSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(ServDeptSVO vo) throws SysException, AppException {
        return servDeptDao.findByVO(vo);
    }

    /**
     * 修改单表ServDeptSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     * @throws DataCacheException
     */
    public void update(ServDeptSVO vo) throws SysException, AppException {
        ServDeptSVO sdvo = new ServDeptSVO();
        sdvo.setName(vo.getName());
        sdvo.setAreaId(vo.getAreaId());
        sdvo = servDeptDao.findByName(sdvo);
        if (sdvo != null && !sdvo.getServDeptId().equals(vo.getServDeptId())) {
            throw new AppException("3210040", "名称重复");
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        servDeptDao.update(vo);
        //记录操作日志
        ActionLogSVO actionLog=vo.getActionLog();
        actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
        actionLogDao.add(actionLog);
        
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.SERV_DEPT);
          
            DataCache.initTreeHashMap(DataCache.SERV_DEPT_BRANCH);
            DataCache.initTreeHashMap(DataCache.AREA_SERV_DEPT);
           
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }

    }

    /**
     * 删除单表ServDeptSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     * @throws DataCacheException
     */
    public void delete(ServDeptSVO vo) throws SysException, AppException {
        if (vo.getSts() == null || vo.getSts().equals(Constant.STS_IN_USE)) {
            vo.setSts(Constant.STS_HISTORY);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        
        String str="";
        BranchSVO bsvo=new BranchSVO();
        ExchSVO esvo=new ExchSVO();
        WorkAreaSVO wsvo=new WorkAreaSVO();
        bsvo.setServDeptId(vo.getServDeptId());
        esvo.setServDeptId(vo.getServDeptId());
        wsvo.setServDeptId(vo.getServDeptId());
        
        List blist=branchDao.findByVO(bsvo);
        List elist=exchDao.findByVO(esvo);
        List wlist=workAreaDao.findByVO(wsvo);
        if(blist!=null&&blist.size()>0)
            str=str+"编号为:"+vo.getServDeptId()+"下存在支局! ";
        if(wlist!=null&&wlist.size()>0)
            str=str+"编号为:"+vo.getServDeptId()+"下存在工作区对应! ";
        if(elist!=null&&elist.size()>0)
            str=str+"编号为:"+vo.getServDeptId()+"下存在局向对应! ";
        if(!str.equals(""))
            throw new AppException("222",str);
        servDeptDao.update(vo);
       

    }

    /**
     * 增加单表ServDeptSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     * @throws SQLException
     * @throws DataCacheException
     */
    public void add(ServDeptSVO vo) throws SysException, AppException, SQLException{
        ServDeptSVO sdvo = new ServDeptSVO();
        if (servDeptDao.findByPK(vo) != null) {
            throw new AppException("3210004", "编号存在"); // 编号存在
        }
        sdvo.setName(vo.getName());
        sdvo.setAreaId(vo.getAreaId());
        sdvo = servDeptDao.findByName(sdvo);
        if (sdvo != null && !sdvo.getServDeptId().equals(vo.getServDeptId())) {
            throw new AppException("3210040", "名称重复");
        }
        if (StringUtil.isBlank(vo.getServDeptId())) {
            vo.setServDeptId(SMMaxId.getServDeptMaxId(vo.getLocalNetId(), vo.getAreaId()));
        }
        if (vo.getSts() == null || vo.getSts().equals(Constant.STS_HISTORY)) {
            vo.setSts(Constant.STS_IN_USE);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        vo.setCreateDate(SysDateUtil.getCurrentDate());
        servDeptDao.add(vo);
        //记录操作日志
        ActionLogSVO actionLog=vo.getActionLog();
        actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
        actionLog.setActionText(vo.getServDeptId()+":"+actionLog.getActionText());
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
        actionLogDao.add(actionLog);
        
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.SERV_DEPT);
          
            DataCache.initTreeHashMap(DataCache.SERV_DEPT_BRANCH);
            DataCache.initTreeHashMap(DataCache.AREA_SERV_DEPT);
            
      
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }
    }

    public void delServDepts(String[] servDeptIds,List logList) throws SysException, AppException {
        ServDeptSVO vo = new ServDeptSVO();
        List list=new ArrayList();
        for (int i = 0; i < servDeptIds.length; i++) {
            vo.setServDeptId(servDeptIds[i]);
            //记录操作日志
            ActionLogSVO actionLog=(ActionLogSVO) logList.get(i);
            actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
            list.add(actionLog);
            
            this.delete(vo);
        }
        
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
        actionLogDao.addBat(list);
        
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.SERV_DEPT);
          
            DataCache.initTreeHashMap(DataCache.SERV_DEPT_BRANCH);
            DataCache.initTreeHashMap(DataCache.AREA_SERV_DEPT);
           
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }
    }

    public String getServDeptId(ServDeptSVO vo) throws SysException, AppException, SQLException {
        return SMMaxId.getServDeptMaxId(vo.getLocalNetId(), vo.getAreaId());
    }

    public PagView findServDeptsByPage(ServDeptSVO vo, HashSet set, PagInfo pagInfo)
            throws Exception {
        return servDeptDao.findServDeptsByPage(vo, set, pagInfo);
    }
    
	public List queryServDept(ServDeptSVO vo) throws AppException, SysException {
		 return servDeptMDao.findServDeptsByMVO(vo);
	}
    public void unadleServDepts(String[] servDeptIds,List logList) throws SysException, AppException {
        ServDeptSVO vo = new ServDeptSVO();
        List list=new ArrayList();
        for (int i = 0; i < servDeptIds.length; i++) {
            vo.setServDeptId(servDeptIds[i]);
            //记录操作日志
            ActionLogSVO actionLog=(ActionLogSVO) logList.get(i);
            actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
            list.add(actionLog);
            
            this.delete(vo);
        }
        
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
        actionLogDao.addBat(list);
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.SERV_DEPT);
          
            DataCache.initTreeHashMap(DataCache.SERV_DEPT_BRANCH);
            DataCache.initTreeHashMap(DataCache.AREA_SERV_DEPT);
           
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }
    }
}
