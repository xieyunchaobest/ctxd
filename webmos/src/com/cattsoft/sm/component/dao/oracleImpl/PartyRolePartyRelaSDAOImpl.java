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
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyRolePartyRelaSDAO;
import com.cattsoft.sm.vo.PartyRolePartyRelaSVO;

public class PartyRolePartyRelaSDAOImpl implements IPartyRolePartyRelaSDAO {
	
    private Logger log = Logger.getLogger(PartyRolePartyRelaSDAOImpl.class);

    public void add(GenericVO vo) throws AppException, SysException {
        PartyRolePartyRelaSVO partyRolePartyRela = (PartyRolePartyRelaSVO) vo;
        StringBuffer sql = new StringBuffer(
                "insert into PARTY_ROLE_PARTY_RELA(RELA_ID,PARTY_ID,PARTY_ROLE_ID,REL_TYPE,STS,STS_DATE,PARTY_ROLE_TYPE_ID) values(?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRolePartyRela.getRelaId());
            ps.setString(2, partyRolePartyRela.getPartyId());
            ps.setString(3, partyRolePartyRela.getPartyRoleId());
            ps.setString(4, partyRolePartyRela.getRelType());
            ps.setString(5, partyRolePartyRela.getSts());
            ps.setDate(6, partyRolePartyRela.getStsDate());
            ps.setString(7, partyRolePartyRela.getPartyRoleTypeId());
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
        PartyRolePartyRelaSVO partyRolePartyRela = (PartyRolePartyRelaSVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY_ROLE_PARTY_RELA set");
        if (partyRolePartyRela.getPartyId() != null) {
            sql.append(" PARTY_ID=?,");
        }
        if (partyRolePartyRela.getPartyRoleId() != null) {
            sql.append(" PARTY_ROLE_ID=?,");
        }
        if (partyRolePartyRela.getRelType() != null) {
            sql.append(" REL_TYPE=?,");
        }
        if (partyRolePartyRela.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (partyRolePartyRela.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (partyRolePartyRela.getPartyRoleTypeId() != null) {
            sql.append(" PARTY_ROLE_TYPE_ID=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and RELA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyRolePartyRela.getPartyId() != null) {
                ps.setString(index++, partyRolePartyRela.getPartyId());
            }
            if (partyRolePartyRela.getPartyRoleId() != null) {
                ps.setString(index++, partyRolePartyRela.getPartyRoleId());
            }
            if (partyRolePartyRela.getRelType() != null) {
                ps.setString(index++, partyRolePartyRela.getRelType());
            }
            if (partyRolePartyRela.getSts() != null) {
                ps.setString(index++, partyRolePartyRela.getSts());
            }
            if (partyRolePartyRela.getStsDate() != null) {
                ps.setDate(index++, partyRolePartyRela.getStsDate());
            }
            if (partyRolePartyRela.getPartyRoleTypeId() != null) {
                ps.setString(index++, partyRolePartyRela.getPartyRoleTypeId());
            }
            ps.setString(index++, partyRolePartyRela.getRelaId());
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
        PartyRolePartyRelaSVO partyRolePartyRela = (PartyRolePartyRelaSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from PARTY_ROLE_PARTY_RELA where 1=1");
        sql.append(" and RELA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRolePartyRela.getRelaId());
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
        PartyRolePartyRelaSVO result = null;
        PartyRolePartyRelaSVO partyRolePartyRela = (PartyRolePartyRelaSVO) vo;
        StringBuffer sql = new StringBuffer("select * from PARTY_ROLE_PARTY_RELA where 1=1");
        sql.append(" and RELA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRolePartyRela.getRelaId());
            rs = ps.executeQuery();
            result = (PartyRolePartyRelaSVO) ResultSetUtil.convertToVo(rs,
                    PartyRolePartyRelaSVO.class);
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
        PartyRolePartyRelaSVO partyRolePartyRela = (PartyRolePartyRelaSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from PARTY_ROLE_PARTY_RELA where 1=1");
        if (partyRolePartyRela.getRelaId() != null) {
            sql.append(" and RELA_ID=?");
        }
        if (partyRolePartyRela.getPartyId() != null) {
            sql.append(" and PARTY_ID=?");
        }
        if (partyRolePartyRela.getPartyRoleId() != null) {
            sql.append(" and PARTY_ROLE_ID=?");
        }
        if (partyRolePartyRela.getRelType() != null) {
            sql.append(" and REL_TYPE=?");
        }
        if (partyRolePartyRela.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (partyRolePartyRela.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (partyRolePartyRela.getPartyRoleTypeId() != null) {
            sql.append(" and PARTY_ROLE_TYPE_ID=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyRolePartyRela.getRelaId() != null) {
                ps.setString(index++, partyRolePartyRela.getRelaId());
            }
            if (partyRolePartyRela.getPartyId() != null) {
                ps.setString(index++, partyRolePartyRela.getPartyId());
            }
            if (partyRolePartyRela.getPartyRoleId() != null) {
                ps.setString(index++, partyRolePartyRela.getPartyRoleId());
            }
            if (partyRolePartyRela.getRelType() != null) {
                ps.setString(index++, partyRolePartyRela.getRelType());
            }
            if (partyRolePartyRela.getSts() != null) {
                ps.setString(index++, partyRolePartyRela.getSts());
            }
            if (partyRolePartyRela.getStsDate() != null) {
                ps.setDate(index++, partyRolePartyRela.getStsDate());
            }
            if (partyRolePartyRela.getPartyRoleTypeId() != null) {
                ps.setString(index++, partyRolePartyRela.getPartyRoleTypeId());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartyRolePartyRelaSVO.class);
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
