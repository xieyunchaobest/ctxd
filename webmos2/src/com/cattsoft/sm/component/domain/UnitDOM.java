package com.cattsoft.sm.component.domain;

import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IUnitSDAO;
import com.cattsoft.sm.vo.UnitSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-10 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class UnitDOM {
	
    private IUnitSDAO unitDao = (IUnitSDAO) DAOFactory.getInstance().getDAO(IUnitSDAO.class);

    private static Logger log = Logger.getLogger(UnitDOM.class);

    /**
     * 根据主键查询单表UnitSVO
     * 
     * @param vo
     * @return UnitSVO
     * @throws SysException
     * @throws AppException
     */
    public UnitSVO findByPk(UnitSVO vo) throws SysException, AppException {
        return (UnitSVO) unitDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表UnitSVO
     * 
     * @param vo
     * @return List<UnitSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(UnitSVO vo) throws SysException, AppException {
        return unitDao.findByVO(vo);
    }

    /**
     * 修改单表UnitSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(UnitSVO vo) throws SysException, AppException {
        unitDao.update(vo);
    }

    /**
     * 删除单表UnitSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(UnitSVO vo) throws SysException, AppException {
        unitDao.delete(vo);

    }

    /**
     * 增加单表UnitSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void add(UnitSVO vo) throws SysException, AppException {
        if(vo.getUnitId()==null){
            vo.setUnitId(MaxId.getSequenceNextVal(Constant.SEQ_UNIT_ID));
        }
        unitDao.add(vo);
    }

}
