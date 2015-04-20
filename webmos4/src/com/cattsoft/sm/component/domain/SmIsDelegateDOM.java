package com.cattsoft.sm.component.domain;

import java.util.Date;

import org.apache.log4j.Logger;

//import com.cattsoft.is.component.domain.CallCrmbaseProcDOM;
//import com.cattsoft.is.component.domain.CrmbaseDistrictDOM;
//import com.cattsoft.is.component.domain.WorkAreaSynDOM;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.DistrictSVO;

public class SmIsDelegateDOM {
	
	
    private static Logger log = Logger.getLogger(SmIsDelegateDOM.class);

    /**
     * 调用同步员工给计费 String workStaffSyn(String staffId, String officeId, String name, String passWord,
     * String regionId, String actType, Date createDate, Date expDate, Date effDate, String
     * operType)
     */
    public String workStaffSyn(String staffId, String officeId, String name, String passWord,
            String regionId, String actType, Date createDate, Date expDate, Date effDate,
            String operType) throws AppException, SysException {
//        try {
//            return WorkAreaSynDOM.callWorkStaffSyn(staffId, officeId, name, passWord, regionId,
//                    actType, createDate, expDate, effDate, operType);
//        } catch (AppException e) {
//            if (log.isDebugEnabled()) {
//                log.debug(e);
//            }
//            throw new AppException("001001", e.getErrMsg());
//        }
    	return null;
    }

    /**
     * 调用同步工区给计费 String workAreaSyn(String workAreaIdForJf, String name, String regionId, String
     * actType, Date createDate, Date expDate, Date effDate, String operType)
     */
    public String workAreaSyn(String workAreaIdForJf, String name, String regionId, String actType,
            Date createDate, Date expDate, Date effDate, String operType) throws AppException,
            SysException {
//        try {
////            return WorkAreaSynDOM.callWorkAreaSyn(workAreaIdForJf, name, regionId, actType,
////                    createDate, expDate, effDate, operType);
//        	return null;
//        } catch (AppException e) {
//            if (log.isDebugEnabled()) {
//                log.debug(e);
//            }
//            throw new AppException("001001", e.getErrMsg());
//        }
    	return null;
    }

    /**
     * 调用同步组织机构给计费 String workAreaSyn(String workAreaIdForJf, String name, String regionId, String
     * actType, Date createDate, Date expDate, Date effDate, String operType)
     */
    public String WorkDeptSyn(String deptId, String name, String regionId, String actType,
            Date createDate, Date expDate, Date effDate, String operType) throws AppException,
            SysException {
//        try {
//            return WorkAreaSynDOM.callWorkDeptSyn(deptId, name, regionId, actType, createDate,
//                    expDate, effDate, operType);
//        } catch (AppException e) {
//            if (log.isDebugEnabled()) {
//                log.debug(e);
//            }
//            throw new AppException("001001", e.getErrMsg());
//        }
    	return null;
    }
    /**
     * 基础数据同步：增加行政区域
     * @param vo
     * @throws AppException
     * @throws SysException
     */
    public void addCrmbaseDistrict(DistrictSVO vo) throws AppException, SysException {
//        if (isTrue()) {
//            CrmbaseDistrictDOM crmbaseDistrictDOM = new CrmbaseDistrictDOM();
//            crmbaseDistrictDOM.addCrmbaseDistrict(vo);
//            // 调用计费存储过程
//            CallCrmbaseProcDOM callCrmbaseProcDOM = new CallCrmbaseProcDOM();
//            callCrmbaseProcDOM.callCrmbaseProc();
//        }
    }
    /**
     * 基础数据同步：修改行政区域
     * @param vo
     * @throws AppException
     * @throws SysException
     */
    public void updateCrmbaseDistrict(DistrictSVO vo) throws AppException, SysException {
//        if (isTrue()) {
//            CrmbaseDistrictDOM crmbaseDistrictDOM = new CrmbaseDistrictDOM();
//            crmbaseDistrictDOM.updateCrmbaseDistrict(vo);
//            // 调用计费存储过程
//            CallCrmbaseProcDOM callCrmbaseProcDOM = new CallCrmbaseProcDOM();
//            callCrmbaseProcDOM.callCrmbaseProc();
//        }
    }
    /**
     * 基础数据同步：删除行政区域
     * @param vo
     * @throws AppException
     * @throws SysException
     */
    public void deleteCrmbaseDistrict(DistrictSVO vo) throws AppException, SysException {
//        if (isTrue()) {
//            CrmbaseDistrictDOM crmbaseDistrictDOM = new CrmbaseDistrictDOM();
//            crmbaseDistrictDOM.deleteCrmbaseDistrict(vo);
//            // 调用计费存储过程
//            CallCrmbaseProcDOM callCrmbaseProcDOM = new CallCrmbaseProcDOM();
//            callCrmbaseProcDOM.callCrmbaseProc();
//        }
    }

    public boolean isTrue() {
//        try {
//            SysConfigSVO vo = (SysConfigSVO) DataCache.getObject(DataCache.SYS_CONFIG,
//                    Constant.SYS_CONFIG_BASE);
//            if (null != vo) {
//                if (vo.getCurValue().equals("Y")) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        } catch (DataCacheException e) {
//          
//        }
        return false;

    }

}
