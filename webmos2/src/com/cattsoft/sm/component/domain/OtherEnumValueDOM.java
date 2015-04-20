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
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IOtherEnumValueSDAO;
import com.cattsoft.sm.vo.OtherEnumValueSVO;

public class OtherEnumValueDOM {
	
    private IOtherEnumValueSDAO enumValueDao = (IOtherEnumValueSDAO) DAOFactory.getInstance()
            .getDAO(IOtherEnumValueSDAO.class);

    public List findEnumValue(OtherEnumValueSVO vo) throws AppException, SysException {
        return enumValueDao.findByVO(vo);
    }

    public OtherEnumValueSVO getEnumValue(OtherEnumValueSVO vo) throws AppException, SysException {
        return (OtherEnumValueSVO) enumValueDao.findByPK(vo);
    }

    public String addEnumValue(OtherEnumValueSVO vo) throws AppException, SysException {
        if(vo.getEnumValueId()==null)
        vo.setEnumValueId(MaxId.getSequenceNextVal(Constant.SEQ_OTHER_ENUM_VALUE_ID));
        enumValueDao.add(vo);
        return vo.getEnumValueId();
    }

    public void deleteEnumValue(OtherEnumValueSVO vo) throws AppException, SysException {
        enumValueDao.delete(vo);
    }

    public void updateEnumValue(OtherEnumValueSVO vo) throws AppException, SysException {
        enumValueDao.update(vo);
    }

    /**
     * 服务属性、组成员属性值配置
     * 
     * @param EnumValueSVO
     *            vo
     * @param PagInfo
     *            pagInfo
     * @throws AppException
     * @throws SysException
     * @return PagView 分页查询的结果
     * @author longtao
     */
    public PagView findEnumValuePage(GenericVO vo, PagInfo pagInfo, Set set) throws AppException,
            SysException {
        return enumValueDao.findEnumValueMVO(vo, pagInfo, set);
    }

    public void deleteBatchEnumValue(String[] enumValueIds) throws AppException, SysException {
        for (int i = 0, size = enumValueIds.length; i < size; i++) {
            OtherEnumValueSVO vo = new OtherEnumValueSVO();
            vo.setEnumValueId(enumValueIds[i]);
            enumValueDao.delete(vo);
        }
    }

    /**
     * 校验属性值是否可以被删除 *
     * 
     * @param String
     * @throws AppException
     * @throws SysException
     * @return String "true"--可以删除;"false"--不可删除
     * @author longtao
     */
    public String valiEnumValueDelete(String[] enumValueIds) throws AppException, SysException {

        return "true";
    }

    /**
     * 校验属性值名称是否被使用
     * 
     * @param EnumValueSVO
     * @throws AppException
     * @throws SysException
     * @return String "true"--未被使用;"false"--被使用
     * @author longtao
     */
    public String valiEnumValueNameRepeat(OtherEnumValueSVO vo) throws AppException, SysException {
        List judgeList = enumValueDao.findByVO(vo);
        if (judgeList != null && judgeList.size() > 0) {
            return "false";
        } else {
            return "true";
        }
    }
}
