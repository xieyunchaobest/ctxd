package com.cattsoft.sm.component.domain;

import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.SysDate;
import com.cattsoft.sm.component.dao.IDataRangeItemMDAO;
import com.cattsoft.sm.component.dao.IDataRangeItemSDAO;
import com.cattsoft.sm.component.dao.IDataRangeMDAO;
import com.cattsoft.sm.component.dao.IDataRangeSDAO;
import com.cattsoft.sm.vo.DataRangeItemSVO;
import com.cattsoft.sm.vo.DataRangeSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */

public class DataRangeDOM {
    private static Logger log = Logger.getLogger(DataRangeDOM.class);

    IDataRangeSDAO dataRangeDao = (IDataRangeSDAO) DAOFactory.getInstance().getDAO(
            IDataRangeSDAO.class);

    IDataRangeMDAO dataRangeMDao = (IDataRangeMDAO) DAOFactory.getInstance().getDAO(
            IDataRangeMDAO.class);

    IDataRangeItemMDAO dataRangeItemMDao = (IDataRangeItemMDAO) DAOFactory.getInstance().getDAO(
            IDataRangeItemMDAO.class);

    IDataRangeItemSDAO dataRangeItemSDao = (IDataRangeItemSDAO) DAOFactory.getInstance().getDAO(
            IDataRangeItemSDAO.class);

    /**
     * 查询数据范围表
     * 
     * @param vo
     *            DataRangeSVO
     * @throws SysException
     * @throws AppException
     * @return List
     */

    public List findDataRange(DataRangeSVO vo) throws SysException, AppException {
        log.debug(vo.getLocalNetId());
        log.debug(vo.getAreaId());
        List list = dataRangeDao.findByVO(vo);
        log.debug("list=" + list);
        return list;
    }

    /**
     * 增加数据范围表
     * 
     * @param vo
     *            DataRangeSVO
     * @return
     * @throws SysException
     * @throws AppException
     * @return List
     */

    public String addDataRange(DataRangeSVO vo) throws SysException, AppException {
        vo.setCreateDate(SysDate.getCurrentDate());
        vo.setStsDate(SysDate.getCurrentDate());
        vo.setDataRangeId(MaxId.getSequenceNextVal(Constant.SEQ_DATA_RANGE_ID));
        vo.setSts(Constant.STS_IN_USE);
        dataRangeDao.add(vo);
        return vo.getDataRangeId();

    }

    /**
     * 查询数据范围表
     * 
     * @param vo
     *            DataRangeSVO
     * @return List
     * @throws Exception
     */

    public List findDataRangesBySet(HashSet set) throws Exception {
        List list = dataRangeMDao.findDataRangesBySet(set);
        return list;
    }

    /**
     * 删除数据范围
     * 
     * @param vo
     *            DataRangeSVO
     * @throws SysException
     * @throws AppException
     */

    public void delDataRange(List vos) throws SysException, AppException {
        DataRangeSVO drv = null;
        for (int i = 0; i < vos.size(); i++) {
            drv = (DataRangeSVO) vos.get(i);
            log.debug("DataRangeSVO:" + drv);
            DataRangeItemSVO driv = new DataRangeItemSVO();
            driv.setRangeId(drv.getDataRangeId());
            log.debug("dataRangeItemMDao.findDataRangeItem(driv.getRangeId()):" + driv);
            if (dataRangeItemMDao.findDataRangeItem(driv.getRangeId()) != null)// 查询当前快捷组下有无快捷。
                throw new AppException("3300022", "当前用户下有快捷不允许删除");
            drv.setStsDate(SysDate.getCurrentDate());
            drv.setSts(Constant.STS_HISTORY);
            dataRangeDao.update(drv);
        }
    }

    /**
     * 修改数据范围
     * 
     * @param vo
     *            DataRangeSVO
     * @throws SysException
     * @throws AppException
     */

    public void modDataRange(DataRangeSVO vo) throws SysException, AppException {
        dataRangeDao.update(vo);
    }

    /**
     * 主键查询数据范围表
     * 
     * @param vo
     *            DataRangeSVO
     * @throws SysException
     * @throws AppException
     * @return List
     */

    public DataRangeSVO findDataRangeByPK(DataRangeSVO svo) throws SysException, AppException {
        DataRangeSVO vo = (DataRangeSVO) dataRangeDao.findByPK(svo);
        log.debug("DataRangeDOM.DataRangeSVO():" + vo);
        return vo;
    }
}
