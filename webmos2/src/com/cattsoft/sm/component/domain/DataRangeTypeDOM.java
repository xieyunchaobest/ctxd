package com.cattsoft.sm.component.domain;

import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IDataRangeItemMDAO;
import com.cattsoft.sm.component.dao.IDataRangeItemSDAO;
import com.cattsoft.sm.component.dao.IDataRangeTypeMDAO;
import com.cattsoft.sm.component.dao.IDataRangeTypeSDAO;
import com.cattsoft.sm.vo.DataRangeItemSVO;
import com.cattsoft.sm.vo.DataRangeTypeSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-20 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */

public class DataRangeTypeDOM {
    private static Logger log = Logger.getLogger(DataRangeTypeDOM.class);

    IDataRangeTypeSDAO dataRangeTypeDao = (IDataRangeTypeSDAO) DAOFactory.getInstance().getDAO(
            IDataRangeTypeSDAO.class);

    IDataRangeTypeMDAO dataRangeTypeMDao = (IDataRangeTypeMDAO) DAOFactory.getInstance().getDAO(
            IDataRangeTypeMDAO.class);

    IDataRangeItemMDAO dataRangeItemMDao = (IDataRangeItemMDAO) DAOFactory.getInstance().getDAO(
            IDataRangeItemMDAO.class);

    IDataRangeItemSDAO dataRangeItemSDao = (IDataRangeItemSDAO) DAOFactory.getInstance().getDAO(
            IDataRangeItemSDAO.class);

    /**
     * 查询区域模式表
     * 
     * @param vo
     *            DataRangeSVO
     * @throws SysException
     * @throws AppException
     * @return List
     */

    public List findDataRangeTypes(DataRangeTypeSVO vo) throws SysException, AppException {
        List list = dataRangeTypeDao.findByVO(vo);
        return list;
    }

    /**
     * 增加区域模式表
     * 
     * @param vo
     *            DataRangeSVO
     * @return
     * @throws SysException
     * @throws AppException
     * @return List
     */

    public String addDataRangeType(DataRangeTypeSVO vo) throws SysException, AppException {
        vo.setDataRangeTypeId(MaxId.getSequenceNextVal(Constant.SEQ_DATA_RANGE_TYPE_ID));
        dataRangeTypeDao.add(vo);
        return vo.getDataRangeTypeId();

    }

    /**
     * 查询区域模式表
     * 
     * @param vo
     *            DataRangeSVO
     * @return List
     * @throws Exception
     */

    public List findDataRangeTypesBySet(HashSet set) throws Exception {
        List list = dataRangeTypeMDao.findDataRangeTypesBySet(set);
        return list;
    }

    /**
     * 删除区域模式
     * 
     * @param vo
     *            DataRangeSVO
     * @throws SysException
     * @throws AppException
     */

    public void delDataRangeType(List vos) throws SysException, AppException {
        DataRangeTypeSVO drv = null;
        for (int i = 0; i < vos.size(); i++) {
            drv = (DataRangeTypeSVO) vos.get(i);
            log.debug("DataRangeSVO:" + drv);
            DataRangeItemSVO driv = new DataRangeItemSVO();
            driv.setDataRangeTypeId(drv.getDataRangeTypeId());
            log.debug("dataRangeItemMDao.findDataRangeItem(driv.getRangeId()):" + driv);
            if (dataRangeItemMDao.findDataRangeItem(driv.getRangeId()) != null)// 查询当前快捷组下有无快捷。
                throw new AppException("3300024", "当前模式下有对应条例不允许删除");
            dataRangeTypeDao.delete(drv);
        }
    }

    /**
     * 修改区域模式
     * 
     * @param vo
     *            DataRangeSVO
     * @throws SysException
     * @throws AppException
     */

    public void modDataRangeType(DataRangeTypeSVO vo) throws SysException, AppException {
        dataRangeTypeDao.update(vo);
    }

    /**
     * 主键查询区域模式表
     * 
     * @param vo
     *            DataRangeSVO
     * @throws SysException
     * @throws AppException
     * @return List
     */

    public DataRangeTypeSVO findDataRangeTypeByPK(DataRangeTypeSVO svo) throws SysException,
            AppException {
        DataRangeTypeSVO vo = (DataRangeTypeSVO) dataRangeTypeDao.findByPK(svo);
        log.debug("dataRangeTypeDao.DataRangeTypeSVO():" + vo);
        return vo;
    }

}
