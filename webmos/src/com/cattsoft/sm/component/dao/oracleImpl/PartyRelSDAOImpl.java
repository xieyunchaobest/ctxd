package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyRelSDAO;
import com.cattsoft.sm.vo.PartyRelSVO;

/**
 * 联系人信息
 * 
 * @author wangdongxun
 * @param mapping
 * @param actionForm
 * @param request
 * 
 * @param response
 * @return
 * @throws Exception
 */
public class PartyRelSDAOImpl implements IPartyRelSDAO {
	
    private Logger log = Logger.getLogger(PartyRelSDAOImpl.class);

    public void add(GenericVO vo) throws AppException, SysException {
        PartyRelSVO partyRel = (PartyRelSVO) vo;
        StringBuffer sql = new StringBuffer(
                "insert into PARTY_REL(PARTY_REL_ID,PARTY_ID1,PARTY_ID2,REL_TYPE,CREATE_DATE,REMARKS,STS,STS_DATE) values(?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRel.getPartyRelId());
            ps.setString(2, partyRel.getPartyId1());
            ps.setString(3, partyRel.getPartyId2());
            ps.setString(4, partyRel.getRelType());
            ps.setDate(5, partyRel.getCreateDate());
            ps.setString(6, partyRel.getRemarks());
            ps.setString(7, partyRel.getSts());
            ps.setDate(8, partyRel.getStsDate());
            ps.execute();
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "add error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void update(GenericVO vo) throws AppException, SysException {
        PartyRelSVO partyRel = (PartyRelSVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY_REL set");
        if (partyRel.getPartyId1() != null) {
            sql.append(" PARTY_ID1=?,");
        }
        if (partyRel.getPartyId2() != null) {
            sql.append(" PARTY_ID2=?,");
        }
        if (partyRel.getRelType() != null) {
            sql.append(" REL_TYPE=?,");
        }
        if (partyRel.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (partyRel.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (partyRel.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        if (partyRel.getRemarks() != null) {
            sql.append(" REMARKS=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_REL_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyRel.getPartyId1() != null) {
                ps.setString(index++, partyRel.getPartyId1());
            }
            if (partyRel.getPartyId2() != null) {
                ps.setString(index++, partyRel.getPartyId2());
            }
            if (partyRel.getRelType() != null) {
                ps.setString(index++, partyRel.getRelType());
            }
            if (partyRel.getSts() != null) {
                ps.setString(index++, partyRel.getSts());
            }
            if (partyRel.getStsDate() != null) {
                ps.setDate(index++, partyRel.getStsDate());
            }
            if (partyRel.getCreateDate() != null) {
                ps.setDate(index++, partyRel.getCreateDate());
            }
            if (partyRel.getRemarks() != null) {
                ps.setString(index++, partyRel.getRemarks());
            }
            ps.setString(index++, partyRel.getPartyRelId());
            ps.execute();
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "update error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void updateByParPartyId(GenericVO vo) throws AppException, SysException {
        PartyRelSVO partyRel = (PartyRelSVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY_REL set");
        if (partyRel.getPartyId1() != null) {
            sql.append(" PARTY_ID1=?,");
        }
        if (partyRel.getRelType() != null) {
            sql.append(" REL_TYPE=?,");
        }
        if (partyRel.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (partyRel.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (partyRel.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        if (partyRel.getRemarks() != null) {
            sql.append(" REMARKS=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_ID2=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyRel.getPartyId1() != null) {
                ps.setString(index++, partyRel.getPartyId1());
            }
            if (partyRel.getRelType() != null) {
                ps.setString(index++, partyRel.getRelType());
            }
            if (partyRel.getSts() != null) {
                ps.setString(index++, partyRel.getSts());
            }
            if (partyRel.getStsDate() != null) {
                ps.setDate(index++, partyRel.getStsDate());
            }
            if (partyRel.getCreateDate() != null) {
                ps.setDate(index++, partyRel.getCreateDate());
            }
            if (partyRel.getRemarks() != null) {
                ps.setString(index++, partyRel.getRemarks());
            }
            ps.setString(index++, partyRel.getPartyId2());
            ps.execute();
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "update error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void delete(GenericVO vo) throws AppException, SysException {
        PartyRelSVO partyRel = (PartyRelSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from PARTY_REL where 1=1");
        sql.append(" and PARTY_REL_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRel.getPartyRelId());
            ps.execute();
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "delete error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        PartyRelSVO result = null;
        PartyRelSVO partyRel = (PartyRelSVO) vo;
        StringBuffer sql = new StringBuffer("select * from PARTY_REL where 1=1");
        sql.append(" and PARTY_REL_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRel.getPartyRelId());
            rs = ps.executeQuery();
            result = (PartyRelSVO) ResultSetUtil.convertToVo(rs, PartyRelSVO.class);
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "findByPK error..", e);
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
        return result;
    }

    public List findByVO(GenericVO vo) throws AppException, SysException {
        List results = null;
        PartyRelSVO partyRel = (PartyRelSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from PARTY_REL where 1=1");
        if (partyRel.getPartyRelId() != null) {
            sql.append(" and PARTY_REL_ID=?");
        }
        if (partyRel.getPartyId1() != null) {
            sql.append(" and PARTY_ID1=?");
        }
        if (partyRel.getPartyId2() != null) {
            sql.append(" and PARTY_ID2=?");
        }
        if (partyRel.getRelType() != null) {
            sql.append(" and REL_TYPE=?");
        }
        if (partyRel.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (partyRel.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (partyRel.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        if (partyRel.getRemarks() != null) {
            sql.append(" and REMARKS=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyRel.getPartyRelId() != null) {
                ps.setString(index++, partyRel.getPartyRelId());
            }
            if (partyRel.getPartyId1() != null) {
                ps.setString(index++, partyRel.getPartyId1());
            }
            if (partyRel.getPartyId2() != null) {
                ps.setString(index++, partyRel.getPartyId2());
            }
            if (partyRel.getRelType() != null) {
                ps.setString(index++, partyRel.getRelType());
            }
            if (partyRel.getSts() != null) {
                ps.setString(index++, partyRel.getSts());
            }
            if (partyRel.getStsDate() != null) {
                ps.setDate(index++, partyRel.getStsDate());
            }
            if (partyRel.getCreateDate() != null) {
                ps.setDate(index++, partyRel.getCreateDate());
            }
            if (partyRel.getRemarks() != null) {
                ps.setString(index++, partyRel.getRemarks());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartyRelSVO.class);
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
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
        return results;
    }

    /**
     * 企业组织递归取得目录树
     * 
     * @author wangdongxun
     * @param PartyId
     * @return
     */
    public List findByPartyId(String partyId1) throws AppException, SysException {
        List results = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from PARTY_REL where STS=" + "'" + Constant.STS_IN_USE + "'");
        if (partyId1 != null) {
            sql.append(" and PARTY_ID1=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyId1 != null) {
                ps.setString(index++, partyId1);
            }

            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartyRelSVO.class);
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
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
        return results;
    }

}
