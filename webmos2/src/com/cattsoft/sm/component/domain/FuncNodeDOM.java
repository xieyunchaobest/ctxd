package com.cattsoft.sm.component.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.SysConfigData;
import com.cattsoft.pub.util.SysDate;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.component.dao.IFuncNodeMDAO;
import com.cattsoft.sm.component.dao.IFuncNodeSDAO;
import com.cattsoft.sm.component.dao.IFuncNodeTreeMDAO;
import com.cattsoft.sm.component.dao.IFuncNodeTreeSDAO;
import com.cattsoft.sm.component.dao.IStaffSDAO;
import com.cattsoft.sm.component.dao.ISysRoleAllocSDAO;
import com.cattsoft.sm.component.dao.ISysRoleSDAO;
import com.cattsoft.sm.component.dao.ISysUserAllocMDAO;
import com.cattsoft.sm.component.dao.ISysUserAllocSDAO;
import com.cattsoft.sm.component.dao.ISysUserRoleSDAO;
import com.cattsoft.sm.component.dao.ISysUserSDAO;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.FuncNodeSVO;
import com.cattsoft.sm.vo.FuncNodeTreeSVO;
import com.cattsoft.sm.vo.StaffSVO;
import com.cattsoft.sm.vo.SysRoleAllocSVO;
import com.cattsoft.sm.vo.SysUserAllocSVO;
import com.cattsoft.sm.vo.SysUserRoleSVO;
import com.cattsoft.sm.vo.SysUserSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-23 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */

public class FuncNodeDOM {

	private static Logger log = Logger.getLogger(FuncNodeDOM.class);

    IFuncNodeMDAO funcNodeMDao = (IFuncNodeMDAO) DAOFactory.getInstance().getDAO(
            IFuncNodeMDAO.class);

    IFuncNodeSDAO funcNodeSDao = (IFuncNodeSDAO) DAOFactory.getInstance().getDAO(
            IFuncNodeSDAO.class);

    IFuncNodeTreeMDAO funcNodeTreeMDao = (IFuncNodeTreeMDAO) DAOFactory.getInstance().getDAO(
            IFuncNodeTreeMDAO.class);

    IFuncNodeTreeSDAO funcNodeTreeSDao = (IFuncNodeTreeSDAO) DAOFactory.getInstance().getDAO(
            IFuncNodeTreeSDAO.class);

    IStaffSDAO staffSDao = (IStaffSDAO) DAOFactory.getInstance().getDAO(IStaffSDAO.class);

    ISysUserSDAO sysUserSDao = (ISysUserSDAO) DAOFactory.getInstance().getDAO(ISysUserSDAO.class);

    ISysRoleAllocSDAO sysRoleAllocSDao = (ISysRoleAllocSDAO) DAOFactory.getInstance().getDAO(
            ISysRoleAllocSDAO.class);

    ISysUserAllocSDAO sysUserAllocSDao = (ISysUserAllocSDAO) DAOFactory.getInstance().getDAO(
            ISysUserAllocSDAO.class);

    ISysUserAllocMDAO sysUserAllocMDao = (ISysUserAllocMDAO) DAOFactory.getInstance().getDAO(
            ISysUserAllocMDAO.class);

    ISysRoleSDAO sysRoleSDao = (ISysRoleSDAO) DAOFactory.getInstance().getDAO(ISysRoleSDAO.class);

    ISysUserRoleSDAO sysUserRoleSDao = (ISysUserRoleSDAO) DAOFactory.getInstance().getDAO(
            ISysUserRoleSDAO.class);

    /**
     * 查询角色的权限表
     * 
     * @param sysRoleId
     * 
     * @throws SysException
     * @throws AppException
     * @return List
     */

    public List findFuncAllocBySysRoleNotInSet(String id) throws SysException, AppException {
        List list = funcNodeMDao.findFuncAllocBySysRoleNotInSet(id);
        return list;
    }

    /**
     * 查询在指定子系统下的所有菜单功能节点
     * 
     */
    public List authUserSubSystemFuncNodeTree(List fnvs) throws SysException, AppException {
        if (fnvs == null || fnvs.size() == 0)
            return new ArrayList();
        FuncNodeSVO fnv = null;
        List tmpLst = new ArrayList();
        for (int i = 0; i < fnvs.size(); i++) { // filter the fnvs,just get nodetreeid is not null.
            fnv = (FuncNodeSVO) fnvs.get(i);
            if (fnv.getNodeTreeId() != null) {
                tmpLst.add(fnv);
            }
        }
        return funcNodeTreeMDao.findFuncNodeTreeVOs(tmpLst);

    }

    /**
     * 查询所有功能点集合和功能树
     * 
     * @param FuncNodeSVO
     * @return
     * @throws SysException
     * @throws AppException
     */
    public List findFuncNodeTree(FuncNodeTreeSVO vo) throws SysException, AppException {
        List list = funcNodeTreeSDao.findByVO(vo);
        return list;
    }

    /**
     * 查询用户可授权权限
     * 
     * @param vo
     *            FuncAllocVO
     * @throws AppException
     * @throws SysException
     * @return List
     */
    public List findFuncNodeVOsBySysUserAuthNew(String sysUserId, String funcNodeId,
            String funcNodeType, String nodeTreeId, String type, String localNetId)
            throws SysException, AppException {
        // 如果是超级管理员，则可分配的功能点为全部。
        List fnvs = null;
        SysUserSVO suv = new SysUserSVO();
        suv.setSysUserId(sysUserId);
        SysUserSVO svo = (SysUserSVO) sysUserSDao.findByPK(suv);
        StaffSVO sv = new StaffSVO();
        sv.setStaffId(svo.getPartyRoleId());
        sv = (StaffSVO) staffSDao.findByPK(sv);
        if (sysUserId.equals("10000")) {
            FuncNodeSVO fnv = new FuncNodeSVO();
            fnv.setSts("A");
            fnv.setNodeTreeId(nodeTreeId);
            fnvs = funcNodeSDao.findByVO(fnv);
        } else
            fnvs = funcNodeMDao.findFuncNodeVOsBySysUserAuthNew(sysUserId, funcNodeId,
                    funcNodeType, nodeTreeId, type); // 得到用户权限。
        return fnvs;
    }

    /**
     * 给角色分配权限
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void addSysRoleAlloc(List vos, String sysUserId) throws SysException, AppException {
        for (int j = 0; j < vos.size(); j++) {
            SysRoleAllocSVO vo = (SysRoleAllocSVO) vos.get(j);
            List list = this.findSysRoleByFuncNodeAndSysRole(vo); // 根据功能点和角色查询是否存在旧记录
            if (log.isDebugEnabled())
                log.debug("[角色分配权限-增加][list=" + list + "]");
            if (list != null) { // 假如有旧记录直接修改
                SysRoleAllocSVO wvo = (SysRoleAllocSVO) list.get(0);
                if ("P".equals(wvo.getSts())) {
                    wvo.setSts("A");
                    this.modSysRoleAlloc(wvo);
                }
            } else { // 否则增加新记录
                vo.setCreateDate(SysDate.getCurrentDate());
                vo.setStsDate(SysDate.getCurrentDate());
                vo.setSysRoleAllocId(MaxId.getSequenceNextVal(Constant.SEQ_SYS_ROLE_ALLOC_ID));
                vo.setSts(Constant.STS_IN_USE);
                vo.setAllowAuth("T");
                sysRoleAllocSDao.add(vo);
            }
            SysUserAllocSVO suavo = new SysUserAllocSVO();
            suavo.setSysRoleId(vo.getSysRoleId());
            SysUserAllocSVO suavo1 = new SysUserAllocSVO();
            SysUserRoleSVO surs = new SysUserRoleSVO();
            surs.setSysRoleId(vo.getSysRoleId());
            surs.setSts(Constant.STS_IN_USE);
            List listvo = null;
            listvo = sysUserRoleSDao.findByVO(surs); // 根据角色遍历用户角色表查询所有拥有此角色的用户
            if (log.isDebugEnabled())
                log.debug("[角色分配权限-增加][listvo=" + list + "]");
            if (listvo != null) { // 假如存在用户

                suavo1.setFuncNodeId(vo.getFuncNodeId());
                suavo1.setSysRoleId(vo.getSysRoleId());
                suavo1.setGrantSysUserId(sysUserId);
                suavo1.setAllocAuth("T");
                suavo1.setCreateDate(SysDate.getCurrentDate());
                suavo1.setStsDate(SysDate.getCurrentDate());
                suavo1.setSts(Constant.STS_IN_USE);
                suavo1.setEntrustFlag("Y");

                sysUserAllocMDao.addBySysRole(suavo1);
            }
        }
    }

    /**
     * 把角色权限删除
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void delSysRoleAlloc(List vos, String sysUserId) throws SysException, AppException {
        for (int j = 0; j < vos.size(); j++) {
            SysRoleAllocSVO vo = (SysRoleAllocSVO) vos.get(j);
            List list = this.findSysRoleByFuncNodeAndSysRole(vo);
            if (log.isDebugEnabled())
                log.debug("[角色分配权限-删除][list=" + list + "]");
            if (list != null) {
                SysRoleAllocSVO wvo = (SysRoleAllocSVO) list.get(0);
                if (Constant.STS_IN_USE.equals(wvo.getSts())) {
                    wvo.setStsDate(SysDate.getCurrentDate());
                    wvo.setSts(Constant.STS_HISTORY);
                    this.modSysRoleAlloc(wvo);
                }
            }
            SysUserAllocSVO suavo = new SysUserAllocSVO();
            suavo.setSysRoleId(vo.getSysRoleId());
            SysUserRoleSVO surs = new SysUserRoleSVO();
            surs.setSysRoleId(vo.getSysRoleId());
            List listvo = null;
            surs.setSts(Constant.STS_IN_USE);
            listvo = sysUserRoleSDao.findByVO(surs); // 根据角色遍历用户角色表查询所有拥有此角色的用户
            if (log.isDebugEnabled())
                log.debug("[角色分配权限-删除][listvo=" + list + "]");
            if (listvo != null) { // 假如存在用户
                suavo.setFuncNodeId(vo.getFuncNodeId());
                suavo.setSysRoleId(vo.getSysRoleId());
                suavo.setLocalNetId(null);
                sysUserAllocMDao.delBySysRole(suavo);
            }
        }
    }

    /**
     * 根据功能点和角色编号在角色权限表查询是否曾经存在旧记录
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public List findSysRoleByFuncNodeAndSysRole(SysRoleAllocSVO vo) throws SysException,
            AppException {
        List list = sysRoleAllocSDao.findByVO(vo);
        return list;

    }

    /**
     * 根据功能点和角色编号在用户权限表查询是否曾经存在旧记录
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public List findSysUserByFuncNodeAndSysRole(SysUserAllocSVO vo, String localNetId)
            throws SysException, AppException {

        List list = sysUserAllocSDao.findByVO(vo);
        return list;

    }

    /**
     * 修改角色权限
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void modSysRoleAlloc(SysRoleAllocSVO wvo) throws SysException, AppException {
        sysRoleAllocSDao.update(wvo);
    }

    /**
     * 修改用户权限
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void modSysUserAlloc(SysUserAllocSVO wvo) throws SysException, AppException {
        sysUserAllocSDao.update(wvo);
    }

    /**
     * 给用户分配权限
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void addSysUserAlloc(List lst) throws SysException, AppException {

        // 功能点localnetid和用户的一致
        String lnld = null;
        for (int j = 0; j < lst.size(); j++) {
            SysUserAllocSVO vo = (SysUserAllocSVO) lst.get(j);

            if (j == 0) {
                SysUserSVO suvo = new SysUserSVO();
                suvo.setSysUserId(vo.getSysUserId());
                suvo = (SysUserSVO) sysUserSDao.findByPK(suvo);
                if (suvo != null) {
                    lnld = suvo.getLocalNetId();
                }
            }
            vo.setLocalNetId(lnld);
            List list = this.findSysUserByFuncNodeAndSysUser(vo); // 根据功能点和角色查询是否存在旧记录
            if (log.isDebugEnabled())
                log.debug("[用户分配权限-增加][list=" + list + "]");
            if (list != null) { // 假如有旧记录直接修改
                SysUserAllocSVO wvo = (SysUserAllocSVO) list.get(0);
                if (Constant.STS_HISTORY.equals(wvo.getSts())) {
                    wvo.setSts(Constant.STS_IN_USE);
                    this.modSysUserAlloc(wvo);
                    
                  //added by yangkai 添加修改记录 2009-6-9 
                    ActionLogSVO actionLog=vo.getActionLog();
                    actionLog.setActionType(SysConstants.LOG_MOD);
                    actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
                    IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
                    actionLogDao.add(actionLog);
                }
            } else { // 否则增加新记录
                vo.setCreateDate(SysDate.getCurrentDate());
                vo.setStsDate(SysDate.getCurrentDate());
                vo.setAllocId(MaxId.getSequenceNextVal(Constant.SEQ_ALLOC_ID));
                vo.setSts(Constant.STS_IN_USE);
                String localVersion = SysConfigData.getSysConfigCurValue(
        				SysConstants.SYS_CONFIG_INTERFACE_LOCAL_VISION, null, null,
        				null, null, null);
                if("sc".equalsIgnoreCase(localVersion)){
                	vo.setEntrustFlag("Y");
                }
                vo.setAllocAuth("T");
                sysUserAllocSDao.add(vo);
                
              //added by yangkai 添加修改记录 2009-6-9 
                ActionLogSVO actionLog=vo.getActionLog(); 
                actionLog.setActionText(vo.getAllocId()+actionLog.getActionText());
                actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
                IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
                actionLogDao.add(actionLog);
            }
        }
    }

    /**
     * 把用户功能点删除
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void delSysUserAlloc(List lst) throws SysException, AppException {
        for (int j = 0; j < lst.size(); j++) {
            SysUserAllocSVO vo = (SysUserAllocSVO) lst.get(j);
            List list = this.findSysUserByFuncNodeAndSysUser(vo); // 根据功能点和角色查询是否存在旧记录
            if (log.isDebugEnabled())
                log.debug("[用户分配权限-删除][list=" + list + "]");
            if (list != null) { // 假如有旧记录直接修改
                SysUserAllocSVO wvo = (SysUserAllocSVO) list.get(0);
                if (Constant.STS_IN_USE.equals(wvo.getSts())) {
                    wvo.setSts(Constant.STS_HISTORY);
                    this.modSysUserAlloc(wvo);
                }
            } else
                return;
            //added by yangkai 添加修改记录 2009-6-9 
            ActionLogSVO actionLog=vo.getActionLog(); 
            actionLog.setActionText(vo.getAllocId()+actionLog.getActionText());
            actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
            IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
            actionLogDao.add(actionLog);
        }
    }

    /**
     * 根据功能点和用户编号在用户权限表查询是否曾经存在旧记录
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public List findSysUserByFuncNodeAndSysUser(SysUserAllocSVO vo) throws SysException,
            AppException {

        List list = sysUserAllocMDao.findSysUserByFuncNodeAndSysUser(vo);
        return list;

    }

    /**
     * 给用户分配角色
     * 
     * @param List
     *            list, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void addSysUserRole(List list, String sysUserId) throws SysException, AppException {
        String localNetId = null;
        for (int j = 0; j < list.size(); j++) {
            SysUserRoleSVO vo = (SysUserRoleSVO) list.get(j);
            List vos = this.findSysRoleBySysUserAndSysRole(vo); // 根据用户和角色在用户角色表查询是否存在旧记录
            if (log.isDebugEnabled())
                log.debug("[用户分配角色-增加][vos=" + vos + "]");

            if (vos != null) { // 假如有旧记录直接修改
                SysUserRoleSVO wvo = (SysUserRoleSVO) vos.get(0);
                if (Constant.STS_HISTORY.equals(wvo.getSts())) {
                    wvo.setSts(Constant.STS_IN_USE);
                    this.modSysUserRole(wvo);            		
                }
            } else { // 否则增加新记录
                vo.setCreateDate(SysDate.getCurrentDate());
                vo.setStsDate(SysDate.getCurrentDate());
                vo.setSysUserRoleId(MaxId.getSequenceNextVal(Constant.SEQ_SYS_USER_ROLE_ID));
                vo.setSts(Constant.STS_IN_USE);
                vo.setAllowAuth("T");
                sysUserRoleSDao.add(vo);
                
                //added by yangkai 添加修改记录 2009-6-9 
                ActionLogSVO actionLog=vo.getActionLog();  
                actionLog.setActionText(vo.getSysUserRoleId()+actionLog.getActionText());
                actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
                IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
                actionLogDao.add(actionLog);
            }
            SysRoleAllocSVO sras = new SysRoleAllocSVO();
            sras.setSysRoleId(vo.getSysRoleId());
            sras.setSts(Constant.STS_IN_USE);
            List listvo1 = sysRoleAllocSDao.findByVO(sras); // 根据角色查询角色拥有功能点；
            if (log.isDebugEnabled())
                log.debug("[用户分配角色-增加][listvo1=" + listvo1 + "]");
            if (listvo1 != null) {
                // 因为删除是直接删除,不是置为p状态,所以增加时直接增加

                SysUserAllocSVO suavo = new SysUserAllocSVO();
                if (j == 0) {
                    SysUserSVO suvo = new SysUserSVO();
                    suvo.setSysUserId(sysUserId);
                    suvo = (SysUserSVO) sysUserSDao.findByPK(suvo);

                    if (suvo == null)
                        throw new AppException("1000000", "不存在此用户");
                    localNetId = suvo.getLocalNetId();
                }

                suavo.setAllocAuth("T");
                suavo.setCreateDate(SysDate.getCurrentDate());
                suavo.setEntrustFlag("Y");
                suavo.setGrantSysUserId(sysUserId);
                suavo.setSts(Constant.STS_IN_USE);
                suavo.setStsDate(SysDate.getCurrentDate());
                suavo.setSysRoleId(vo.getSysRoleId());
                suavo.setSysUserId(vo.getSysUserId());
                suavo.setLocalNetId(localNetId);
                sysUserAllocMDao.addBySysRoleAndSysUser(suavo);

            }
        }
    }

    /**
     * 把用户角色删除
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void delSysUserRole(List list, String sysUserId) throws SysException, AppException {
        for (int j = 0; j < list.size(); j++) {
            SysUserRoleSVO vo = (SysUserRoleSVO) list.get(j);
            List vos = this.findSysRoleBySysUserAndSysRole(vo); // 根据用户和角色在用户角色表查询是否存在旧记录
            if (log.isDebugEnabled())
                log.debug("[用户分配角色-删除][vos=" + vos + "]");
            if (vos != null) { // 假如有旧记录直接修改
                SysUserRoleSVO wvo = (SysUserRoleSVO) vos.get(0);
                if (Constant.STS_IN_USE.equals(wvo.getSts())) {
                    wvo.setSts(Constant.STS_HISTORY);
                    this.modSysUserRole(wvo); 
                }
            }
            SysRoleAllocSVO sras = new SysRoleAllocSVO();
            sras.setSysRoleId(vo.getSysRoleId());
            sras.setSts(Constant.STS_IN_USE);
            List listvo1 = sysRoleAllocSDao.findByVO(sras); // 根据角色查询角色拥有功能点；
            if (log.isDebugEnabled())
                log.debug("[用户分配角色-删除][listvo1=" + listvo1 + "]");
            if (listvo1 != null) {

                // 直接删除,为了登陆效率,所以sysUserAlloc表中不存在角色不为空，状态为p的数据
                SysUserAllocSVO svo = new SysUserAllocSVO();
                svo.setSysUserId(vo.getSysUserId());
                svo.setSysRoleId(vo.getSysRoleId());
                if (svo.getSysRoleId() != null && svo.getSysUserId() != null){
                    sysUserAllocMDao.delBySysRoleAndSysUser(svo);
                }

            }
          //added by yangkai 添加修改记录 2009-6-9 
            ActionLogSVO actionLog=vo.getActionLog(); 
            actionLog.setActionText(vo.getSysUserRoleId()+actionLog.getActionText());
            actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
            IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
            actionLogDao.delete(actionLog);
        }
    }

    /**
     * 修改用户角色
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public void modSysUserRole(SysUserRoleSVO wvo) throws SysException, AppException {
        sysUserRoleSDao.update(wvo);
    }

    /**
     * 根据功能点和角色编号在用户权限表查询是否曾经存在旧记录
     * 
     * @param List
     *            vos, List ahVo,String sysUserId,int localNetId
     * @throws AppException
     * @throws SysException
     * @return
     */
    public List findSysRoleBySysUserAndSysRole(SysUserRoleSVO vo) throws SysException, AppException {

        List list = sysUserRoleSDao.findByVO(vo);
        return list;

    }

    public List findFuncNodeForLogin(String sysUserId) throws SysException, AppException {
        SysUserSVO vo = new SysUserSVO();
        vo.setSysUserId(sysUserId);
        vo = (SysUserSVO) sysUserSDao.findByPK(vo);
        List list = funcNodeMDao.findFuncNodeForLogin(sysUserId, vo.getLocalNetId());
        return list;

    }

    public PagView findFuncNodeForLogin(String sysUserId, PagInfo pagInfo) throws SysException,
            AppException {
        return funcNodeMDao.findFuncNodeForLogin(sysUserId, pagInfo);
    }

    public List fincFuncNodeByVo(FuncNodeSVO vo) throws AppException, SysException {
        return funcNodeSDao.findByVO(vo);
    }
}
