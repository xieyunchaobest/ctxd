package com.cattsoft.sm.component.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.sm.component.dao.ILocalNetAreaMDAO;
import com.cattsoft.sm.vo.AreaSVO;
import com.cattsoft.sm.vo.LocalNetSVO;

public class LocalNetAreaDOM {
	
    private ILocalNetAreaMDAO localNetAreaMDao = (ILocalNetAreaMDAO) DAOFactory.getInstance()
            .getDAO(ILocalNetAreaMDAO.class);

    /**
     * 按照员工所处本地网、服务区信息查找出可以在前台显示的本地网信息
     * 
     * @param LocalNetSVO
     *            vo1, AreaSVO vo2
     * @return List<LabelValueBean>
     * @throws AppException
     * @throws SysException
     */
    public List findLocalNet(LocalNetSVO vo1, AreaSVO vo2) throws AppException, SysException {
        return localNetAreaMDao.findLocalNet(vo1, vo2);
    }

    /**
     * 按照员工所处本地网、服务区信息查找出可以在前台显示的服务区信息
     * 
     * @param LocalNetSVO
     *            vo1,AreaSVO vo2
     * @return List<LabelValueBean>
     * @throws AppException
     * @throws SysException
     */
    public List findArea(LocalNetSVO vo1, AreaSVO vo2) throws AppException, SysException {
        return localNetAreaMDao.findArea(vo1, vo2);
    }

    /**
     * 构建本地网服务区树
     * 
     * @param vo1
     * @param vo2
     * @return
     * @throws AppException
     * @throws SysException
     */
    public String initLocalNetAreaTree(LocalNetSVO vo1, AreaSVO vo2) throws AppException,
            SysException {
    return null;
    }

    /**
     * 父节点
     * 
     * @param list
     * @return
     */
    private List convertNodeBean(List list) {
    	return null;
    }

    /**
     * 叶子节点
     * 
     * @param list
     * @return
     */
    private List convertLeafNodeBean(List list) {
return null;
    }
}
