package com.cattsoft.sm.component.domain;

import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.sm.component.dao.IAreaSDAO;
import com.cattsoft.sm.component.dao.ILocalNetMDAO;
import com.cattsoft.sm.component.dao.ILocalNetSDAO;
import com.cattsoft.sm.vo.AreaSVO;
import com.cattsoft.sm.vo.LocalNetSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-7 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class RegionDOM {
	
    private static Logger log = Logger.getLogger(RegionDOM.class);

    private ILocalNetSDAO localNetDao = (ILocalNetSDAO) DAOFactory.getInstance().getDAO(
            ILocalNetSDAO.class);

    private IAreaSDAO areaSDao = (IAreaSDAO) DAOFactory.getInstance().getDAO(IAreaSDAO.class);

    /**
     * 查询所有本地网
     * 
     * @param LocalNetSVO
     * @return List<LocalNetSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findLocalNetsAll(LocalNetSVO vo) throws AppException, SysException {
        List localnet = localNetDao.findByVO(vo);
        if (localnet == null) {
            return null;
        }
        return localnet;
    }

    /**
     * 查询LocalNet
     * 
     * @param LocalNetSVO
     * @return List<LocalNetSVO>
     * 
     * @throws SysException
     * @throws AppException
     */
    public List findLocalNet(LocalNetSVO vo) throws AppException, SysException {
        List localnet = localNetDao.findByVO(vo);
        if (localnet == null) {
            return null;
        }
        return localnet;
    }

    /**
     * 根据主键查询本地网
     * 
     * @param localNetId
     * @return localNetSVO
     * @throws AppException
     * @throws SysException
     */
    public LocalNetSVO findLocalNetByPK(String localNetId) throws AppException, SysException {
        LocalNetSVO lsvo = new LocalNetSVO();
        lsvo.setLocalNetId(localNetId);
        return (LocalNetSVO) localNetDao.findByPK(lsvo);
    }

    /**
     * 查询服务区信息
     * 
     * @param AreaSVO
     * @return List<AreaSVO>
     * 
     * @throws SysException
     * @throws AppException
     */
    public List findAreas(AreaSVO vo) throws AppException, SysException {

        return areaSDao.findByVO(vo);
    }

    /**
     * 根据主键查询服务区信息
     * 
     * @param areaId
     * @return AreaSVO
     * 
     * @throws SysException
     * @throws AppException
     */
    public AreaSVO findAreaByPK(String areaId) throws AppException, SysException {
        AreaSVO asvo = new AreaSVO();
        asvo.setAreaId(areaId);
        return (AreaSVO) areaSDao.findByPK(asvo);
    }
}
