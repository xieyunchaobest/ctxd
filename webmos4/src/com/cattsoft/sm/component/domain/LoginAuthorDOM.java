package com.cattsoft.sm.component.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.sm.component.dao.IFuncNodeMDAO;
import com.cattsoft.sm.component.dao.IFuncNodeTreeMDAO;
import com.cattsoft.sm.component.dao.ISysUserSDAO;
import com.cattsoft.sm.vo.FuncNodeSVO;
import com.cattsoft.sm.vo.FuncNodeTreeAllMVO;
import com.cattsoft.sm.vo.SysUserSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-27 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class LoginAuthorDOM {
	private static Logger log = Logger.getLogger(LoginAuthorDOM.class);

    private IFuncNodeTreeMDAO funcNodeTreeMDao = (IFuncNodeTreeMDAO) DAOFactory.getInstance()
            .getDAO(IFuncNodeTreeMDAO.class);

    private IFuncNodeMDAO funcNodeMDao = (IFuncNodeMDAO) DAOFactory.getInstance().getDAO(
            IFuncNodeMDAO.class);

    private ISysUserSDAO sysUserSDao = (ISysUserSDAO) DAOFactory.getInstance().getDAO(
            ISysUserSDAO.class);

    /**
     * 询用户在指定子系统下的所有菜单功能节点
     * 
     * @param sysUserId
     *            Long
     * @param subSystem
     *            String
     * @throws SysException
     * @throws AppException
     * @return List
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
     * 根据用户id查询用户可访问的功能点集合和功能树
     * 
     * @param sysUserId
     * @return
     * @throws SysException
     * @throws AppException
     */
    public FuncNodeTreeAllMVO getFunNodeTreeAllMVO(String sysUserId) throws SysException,
            AppException {
        FuncNodeTreeAllMVO retVO = new FuncNodeTreeAllMVO();
        SysUserSVO vo = new SysUserSVO();
        vo.setSysUserId(sysUserId);
        vo = (SysUserSVO) sysUserSDao.findByPK(vo);
        List funcNodeVOs = funcNodeMDao.findFuncNodeForLogin(sysUserId, vo.getLocalNetId());
        retVO.setFuncNodeVOs(funcNodeVOs);
        if (log.isDebugEnabled())
            log.debug("[功能点树-查询][功能点集合 funcNodeVOs=" + funcNodeVOs + "]");
        List fntvs = authUserSubSystemFuncNodeTree(funcNodeVOs);
        if (log.isDebugEnabled())
            log.debug("[功能点树-查询][功能点目录集合 fntvs=" + fntvs + "]");
        retVO.setFuncNodeTreeVOs(fntvs);
        return retVO;

    }
}