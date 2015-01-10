package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PartitionUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyRoleSDAO;
import com.cattsoft.sm.vo.PartyRoleSVO;

public class PartyRoleSDAOImpl implements IPartyRoleSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
    	
        PartyRoleSVO partyRole = (PartyRoleSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" PARTY_ROLE(PARTY_ROLE_TYPE_ID,PARTY_ROLE_ID,PARTY_ID,LOCAL_NET_ID) values(?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRole.getPartyRoleTypeId());
            ps.setString(2, partyRole.getPartyRoleId());
            ps.setString(3, partyRole.getPartyId());
            ps.setString(4, partyRole.getLocalNetId());
            ps.execute();
        } catch (SQLException e) {
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
        PartyRoleSVO partyRole = (PartyRoleSVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY_ROLE set");
        if (partyRole.getPartyId() != null) {
            sql.append(" PARTY_ID=?,");
        }
        if (partyRole.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_ROLE_TYPE_ID=?");
        sql.append(" and PARTY_ROLE_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyRole.getPartyId() != null) {
                ps.setString(index++, partyRole.getPartyId());
            }
            if (partyRole.getLocalNetId() != null) {
                ps.setString(index++, partyRole.getLocalNetId());
            }
            ps.setString(index++, partyRole.getPartyRoleTypeId());
            ps.setString(index++, partyRole.getPartyRoleId());
            ps.execute();
        } catch (SQLException e) {
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
        PartyRoleSVO partyRole = (PartyRoleSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from PARTY_ROLE where 1=1");
        sql.append(" and PARTY_ROLE_TYPE_ID=?");
        sql.append(" and PARTY_ROLE_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRole.getPartyRoleTypeId());
            ps.setString(2, partyRole.getPartyRoleId());
            ps.execute();
        } catch (SQLException e) {
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
        PartyRoleSVO result = null;
        PartyRoleSVO partyRole = (PartyRoleSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql.append(" a.PARTY_ROLE_TYPE_ID,a.PARTY_ROLE_ID,a.PARTY_ID,a.LOCAL_NET_ID");
        sql.append(" from PARTY_ROLE a where 1=1");
        sql.append(" and PARTY_ROLE_TYPE_ID=?");
        sql.append(" and PARTY_ROLE_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyRole.getPartyRoleTypeId());
            ps.setString(2, partyRole.getPartyRoleId());
            rs = ps.executeQuery();
            result = (PartyRoleSVO) ResultSetUtil.convertToVo(rs, PartyRoleSVO.class);
        } catch (SQLException e) {
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
        PartyRoleSVO partyRole = (PartyRoleSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.PARTY_ROLE_TYPE_ID,a.PARTY_ROLE_ID,a.PARTY_ID,a.LOCAL_NET_ID");
        sql.append(" from PARTY_ROLE a where 1=1");
        if (partyRole.getPartyRoleTypeId() != null) {
            sql.append(" and PARTY_ROLE_TYPE_ID=?");
        }
        if (partyRole.getPartyRoleId() != null) {
            sql.append(" and PARTY_ROLE_ID=?");
        }
        if (partyRole.getPartyId() != null) {
            sql.append(" and PARTY_ID=?");
        }
        if (partyRole.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(),
                    partyRole.getLocalNetId()));
            int index = 1;
            if (partyRole.getPartyRoleTypeId() != null) {
                ps.setString(index++, partyRole.getPartyRoleTypeId());
            }
            if (partyRole.getPartyRoleId() != null) {
                ps.setString(index++, partyRole.getPartyRoleId());
            }
            if (partyRole.getPartyId() != null) {
                ps.setString(index++, partyRole.getPartyId());
            }
            if (partyRole.getLocalNetId() != null) {
                ps.setString(index++, partyRole.getLocalNetId());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartyRoleSVO.class);
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
        return results;
    }

}
