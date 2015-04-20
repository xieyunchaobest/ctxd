package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.PartitionUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyMDAO;
import com.cattsoft.sm.vo.DevelopMVO;
import com.cattsoft.sm.vo.PartySVO;
import com.cattsoft.sm.vo.QryBasicCustInfoMVO;
import com.cattsoft.sm.vo.QryCustInfoConditionMVO;
import com.cattsoft.sm.vo.QryPartyInfoConditionMVO;
import com.cattsoft.sm.vo.SysUserSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-28 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author Administrator
 */
public class PartyMDAOImpl implements IPartyMDAO {
	
	
    public QryBasicCustInfoMVO findName(QryCustInfoConditionMVO vo) throws AppException,
            SysException {
        QryBasicCustInfoMVO qryBasicCustInfoMVO = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select a.name, a.area_id from party a, party_role b where 1=1 ");
        sql.append("and a.party_id = b.party_id ");
        sql.append("and b.party_role_id = ? ");
        sql.append("and b.party_role_type_id = " + Constant.PARTY_ROLE_TYPE_CUST + " ");
        sql.append("and a.sts = '" + Constant.STS_IN_USE + "' ");
        sql.append("and a.local_net_id = ? ");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, vo.getCustId());
            ps.setString(index++, vo.getLocalNetId());
            rs = ps.executeQuery();
            if (rs.next()) {
                qryBasicCustInfoMVO = new QryBasicCustInfoMVO();
                qryBasicCustInfoMVO.setCustName(rs.getString("name"));
                qryBasicCustInfoMVO.setCustId(rs.getString("area_id"));
            }
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "QryBasicCustInfoMVO error ..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return qryBasicCustInfoMVO;
    }

    private Logger log = Logger.getLogger(PartyMDAOImpl.class);

    public List findPartyBySysUserId(SysUserSVO suv,String localNetId) throws AppException, SysException {
        List results = null;
        StringBuffer sql = new StringBuffer();
        sql
                .append("select b.JUR_PERSON,b.NET_TYPE,b.TAX_NBR,b.COMPANY_SIZE,b.START_TIME,b.COMPANY_CHARACTER,b.COMPANY_STRUCTURE,b.REMARKS,b.GANDER,b.BIRTHDAY,b.AGE,b.NATIVE_PLACE,b.NATIONALITY,b.POLITICAL_STATUS,b.EDUC_LEVEL,b.GRADUATE_SCHOOL,b.MARITAL_STATUS,b.DOMESTIC_RELATION,b.MARITAL_DATE,b.COMPANY_NAME,b.DEPARTMENT,b.POSITION,b.WORK_ADDR,b.FUNC_DESC,b.SALARY,b.CHAR_TYPE,b.FAVORITE,b.TELECOM_ATTITUDE,b.WORK_EXPER,b.BRANCH_ID,b.AREA_ID,b.LOCAL_NET_ID,b.SERV_DEPT_ID,b.NAME,b.NAME_BRIEF,b.NAME_OTHER,b.PARTY_TYPE,b.STANDARD_CODE,b.STS P_STS,b.STS_DATE P_STS_DATE,b.CREATE_DATE P_CREATE_DATE,b.COMMUNICATION_SPECIALTY,b.MAIN_PRODUCTION,b.TOTAL_ASSET,b.WEB_ADDRESS,b.LAST_MOD_DATE   ");
        sql.append("from sys_user su,party_role pr,party b  ");
        sql.append("where su.party_role_id=pr.party_role_id  ");
        sql.append("and su.party_role_type_id=pr.party_role_type_id  ");
        sql.append("and pr.party_id=b.party_id  ");
        sql.append("and su.sys_user_id= ?  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (suv.getSysUserId() != null) {
                ps.setString(index++, suv.getSysUserId());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartySVO.class);
        } catch (SQLException e) {
            log.debug(e);
            throw new SysException("", "findPartyBySysUserId error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return results;
    }

    /**
     * 查询party_role_id，查询条件可以在QryPartyInfoConditionMVO中扩展
     * 
     * @author zhouqian
     */
    public List findPartyRoleIdByName(GenericVO vo) throws SysException, AppException {
        QryPartyInfoConditionMVO qryVo = (QryPartyInfoConditionMVO) vo;
        List list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        StringBuffer sql = new StringBuffer();
        sql.append("select b.party_role_id ");
        sql.append("from party a left join party_role b on a.party_id = b.party_id where 1=1 ");
        sql.append("and a.name like '" + qryVo.getName() + "%' ");
        sql.append("and a.sts = '" + Constant.STS_IN_USE + "' ");
        sql.append("and a.local_net_id = ? ");
        sql.append("and b.party_role_type_id = ? ");
        if (qryVo.getAreaId() != null) {
            sql.append("and a.area_id = ? ");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, qryVo.getLocalNetId());
            ps.setString(index++, qryVo.getPartyRoleType());
            if (qryVo.getAreaId() != null) {
                ps.setString(index++, qryVo.getAreaId());
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("party_role_id"));
            }
        } catch (SQLException e) {
            log.debug(e);
            throw new SysException("", "findPartyRoleIdByName error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return list.size() == 0 ? null : list;
    }

    public List findByDevelopMVO(GenericVO vo) throws AppException, SysException {
        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        DevelopMVO dmvo = (DevelopMVO) vo;
        StringBuffer sql = new StringBuffer();
        sql
                .append(
                        "select a.name,a.party_id,a.local_net_id,a.area_id,a.serv_dept_id,a.branch_id from PARTY a,party_role b where 1=1 ")
                .append("and a.party_id=b.party_id ");
        if (dmvo.getAreaId() != null) {
            sql.append("and a.area_id=? ");
        }
        if (dmvo.getLocalNetId() != null) {
            sql.append("and a.local_net_id=? ");
        }
        if (dmvo.getServDeptId() != null) {
            sql.append("and a.serv_dept_id=? ");
        }
        if (dmvo.getBranchId() != null) {
            sql.append("and a.branch_id=? ");
        }
        if (dmvo.getPartyRoleType() != null) {
            sql.append("and b.party_role_type_id=? ");
        }
        sql.append("and a.sts='A' ");
        log.debug(sql.toString());
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (dmvo.getAreaId() != null) {
                ps.setString(index++, dmvo.getAreaId());
            }
            if (dmvo.getLocalNetId() != null) {
                ps.setString(index++, dmvo.getLocalNetId());
            }
            if (dmvo.getServDeptId() != null) {
                ps.setString(index++, dmvo.getServDeptId());
            }
            if (dmvo.getBranchId() != null) {
                ps.setString(index++, dmvo.getBranchId());
            }
            if (dmvo.getPartyRoleType() != null) {
                ps.setString(index++, dmvo.getPartyRoleType());
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                DevelopMVO mvo = new DevelopMVO();
                mvo.setDevelopId(rs.getString("party_id"));
                mvo.setName(rs.getString("name"));
                mvo.setLocalNetId(rs.getString("local_net_id"));
                mvo.setAreaId(rs.getString("area_id"));
                mvo.setServDeptId(rs.getString("serv_dept_id"));
                mvo.setBranchId(rs.getString("branch_id"));
                results.add(mvo);
            }
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "findByVO error ..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return results;
    }

    // (labelValueBean)不知道可不可以放在后台
    public List findByLabelValueBean(GenericVO vo) throws AppException, SysException {
        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        DevelopMVO dmvo = (DevelopMVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select b.party_role_id,a.name from PARTY a,party_role b where 1=1 ").append(
                "and a.party_id=b.party_id ");
        if (dmvo.getAreaId() != null) {
            sql.append("and a.area_id=? ");
        }
        if (dmvo.getLocalNetId() != null) {
            sql.append("and a.local_net_id=? ");
        }
        if (dmvo.getServDeptId() != null) {
            sql.append("and a.serv_dept_id=? ");
        }
        if (dmvo.getBranchId() != null) {
            sql.append("and a.branch_id=? ");
        }
        if (dmvo.getPartyRoleType() != null) {
            sql.append("and b.party_role_type_id=? ");
        }
        sql.append("and a.sts='A' ");
        log.debug(sql.toString());
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(),dmvo.getLocalNetId()));
            int index = 1;
            if (dmvo.getAreaId() != null) {
                ps.setString(index++, dmvo.getAreaId());
            }
            if (dmvo.getLocalNetId() != null) {
                ps.setString(index++, dmvo.getLocalNetId());
            }
            if (dmvo.getServDeptId() != null) {
                ps.setString(index++, dmvo.getServDeptId());
            }
            if (dmvo.getBranchId() != null) {
                ps.setString(index++, dmvo.getBranchId());
            }
            if (dmvo.getPartyRoleType() != null) {
                ps.setString(index++, dmvo.getPartyRoleType());
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                LabelValueBean lvo = new LabelValueBean();
                lvo.setValue(rs.getString("party_role_id"));
                lvo.setLabel(rs.getString("name"));
                results.add(lvo);
            }
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "findByVO error ..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return results;
    }

    public List findByWorkAreaId(String workAreaId) throws AppException, SysException {
    	List resList = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Sql sql = new Sql(
				"select P.PARTY_ID, P.NAME FROM WORK_AREA WA, STAFF_WORK_AREA SWA, STAFF ST, PARTY P ");
		sql
				.append("WHERE WA.STS='A' AND SWA.STS='A' AND P.STS='A' AND WA.WORK_AREA_ID =:workAreaId ");
		sql
				.append("AND SWA.WORK_AREA_ID = WA.WORK_AREA_ID AND ST.STAFF_ID = SWA.STAFF_ID AND P.PARTY_ID = ST.PARTY_ID ");
		sql.setString("workAreaId", workAreaId);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean lvBean = new LabelValueBean();
				lvBean.setLabel(rs.getString("NAME"));
				lvBean.setValue(rs.getString("PARTY_ID"));
				resList.add(lvBean);
			}
        } catch (SQLException e) {
            throw new SysException("", "findByVO error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    	return resList;
    }
}
