package com.cattsoft.sm.component.domain;

import java.util.List;
import java.util.Set;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IOtherCharacteristicSDAO;
import com.cattsoft.sm.vo.OtherCharacterApplyRangeSVO;
import com.cattsoft.sm.vo.OtherCharacteristicMVO;
import com.cattsoft.sm.vo.OtherCharacteristicSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-10-19 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class OtherCharacteristicDOM {
	

    private IOtherCharacteristicSDAO characteristicDao = (IOtherCharacteristicSDAO) DAOFactory
            .getInstance().getDAO(IOtherCharacteristicSDAO.class);

    /**
     * 获取属性信息
     * 
     * @param CharacteristicSVO
     * @throws AppException
     * @throws SysException
     * @return List<CharacteristicSVO>
     * @author longtao
     */
    public List findCharacteristic(OtherCharacteristicSVO vo) throws AppException, SysException {
        return characteristicDao.findByVO(vo);
    }

    /**
     * 根据主键查询属性的详细信息
     * 
     * @param CharacteristicSVO
     * @throws AppException
     * @throws SysException
     * @return CharacteristicSVO
     * @author longtao
     */
    public OtherCharacteristicSVO getCharacteristic(OtherCharacteristicSVO vo) throws AppException,
            SysException {
        return (OtherCharacteristicSVO) characteristicDao.findByPK(vo);
    }

    /**
     * 新增属性信息
     * 
     * @param CharacteristicSVO
     * @throws AppException
     * @throws SysException
     * @author longtao
     */
    public String addCharacteristic(OtherCharacteristicSVO vo) throws AppException, SysException {
        if (vo.getCharacteristicId() == null)
            vo.setCharacteristicId(MaxId.getSequenceNextVal(Constant.SEQ_CHARACTERISTIC_ID));
        vo.setCreateDate(SysDateUtil.getCurrentTimestamp());
        vo.setStsDate(SysDateUtil.getCurrentTimestamp());
        vo.setSts(Constant.STS_IN_USE);
        characteristicDao.add(vo);
        return vo.getCharacteristicId();
    }

    /**
     * 删除属性信息
     * 
     * @param CharacteristicSVO
     * @throws AppException
     * @throws SysException
     * @author longtao
     */
    public void deleteCharacteristic(OtherCharacteristicSVO vo) throws AppException, SysException {
        vo.setStsDate(SysDateUtil.getCurrentTimestamp());
        vo.setSts(Constant.STS_HISTORY);
        characteristicDao.update(vo);
    }

    /**
     * 批量删除属性信息
     * 
     * @param String[]
     *            characteristicIds
     * @throws AppException
     * @throws SysException
     * @author longtao
     */
    public void deleteBatchCharacteristic(String[] characteristicIds) throws AppException,
            SysException {
        for (int i = 0, size = characteristicIds.length; i < size; i++) {
            OtherCharacteristicSVO vo = new OtherCharacteristicSVO();
            vo.setCharacteristicId(characteristicIds[i]);
            vo.setStsDate(SysDateUtil.getCurrentTimestamp());
            vo.setSts(Constant.STS_HISTORY);
            characteristicDao.update(vo);
        }
    }

    /**
     * 修改属性信息
     * 
     * @param CharacteristicSVO
     * @throws AppException
     * @throws SysException
     * @author longtao
     */
    public void updateCharacteristic(OtherCharacteristicSVO vo) throws AppException, SysException {
        vo.setStsDate(SysDateUtil.getCurrentTimestamp());
        characteristicDao.update(vo);
    }

    /**
     * 获取属性信息 ----配置服务属性
     * 
     * @param CharacteristicSVO
     * @param PagInfo
     * @throws AppException
     * @throws SysException
     * @return PagView
     * @author longtao
     */
    public PagView findCharacteristicPage(OtherCharacteristicMVO vo, PagInfo pagInfo, Set set)
            throws AppException, SysException {
        return characteristicDao.findCharacteristicMVO(vo, pagInfo, set);
    }

    /**
     * 检验属性是否可以删除
     * 
     * @param String[]
     *            characteristicIds
     * @throws AppException
     * @throws SysException
     * @return String "true"--可以删除；"false"--不可以删除
     * @author longtao
     */
    public String valiCharacteristicDelete(String[] characteristicIds) throws AppException,
            SysException {
        return null;

    }

    /**
     * 校验属性名称是否已被使用
     * 
     * @param CharacteristicSVO
     * @throws AppException
     * @throws SysException
     * @return String "true"--未被使用;"false"--被使用
     */
    public String valiCharacteristicNameRepeat(OtherCharacteristicSVO vo) throws AppException,
            SysException {
        List judgeList = characteristicDao.findByVO(vo);
        if (judgeList != null && judgeList.size() > 0) {
            return "false";
        } else {
            return "true";
        }
    }

    /**
     * 获取属性信息 ----根据适应范围
     * 
     * @param CharacteristicSVO
     * @param PagInfo
     * @throws AppException
     * @throws SysException
     * @return PagView
     * @author longtao
     */
    public PagView findByRange(OtherCharacteristicMVO vo, PagInfo pagInfo, Set set)
            throws AppException, SysException {
        return characteristicDao.findCharacteristicMVOByRange(vo, pagInfo, set);
    }
}
