package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PartitionUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyAddrSDAO;
import com.cattsoft.sm.vo.PartyAddrSVO;


public class PartyAddrSDAOImpl implements IPartyAddrSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
        PartyAddrSVO partyAddr = (PartyAddrSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" PARTY_ADDR(PARTY_ADDR_ID,PARTY_ID,LOCAL_NET_ID,ADDR_TYPE,ADDR_STS,STS_DATE,CREATE_DATE,SITUATED) values(?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            if (partyAddr.getPartyAddrId() == null || partyAddr.getPartyAddrId().length() == 0) {
                ps.setString(1, MaxId.getSequenceNextVal(Constant.SEQ_PARTY_ADDR_ID));
            } else {
                ps.setString(1, partyAddr.getPartyAddrId());
            }
            ps.setString(2, partyAddr.getPartyId());
            ps.setString(3, partyAddr.getLocalNetId());
            ps.setString(4, partyAddr.getAddrType());
            ps.setString(5, partyAddr.getAddrSts());
            ps.setDate(6, partyAddr.getStsDate());
            ps.setDate(7, partyAddr.getCreateDate());
            ps.setString(8, partyAddr.getSituated());
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
        PartyAddrSVO partyAddr = (PartyAddrSVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY_ADDR set");
        if (partyAddr.getPartyId() != null) {
            sql.append(" PARTY_ID=?,");
        }
        if (partyAddr.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (partyAddr.getAddrType() != null) {
            sql.append(" ADDR_TYPE=?,");
        }
        if (partyAddr.getAddrSts() != null) {
            sql.append(" ADDR_STS=?,");
        }
        if (partyAddr.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (partyAddr.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        if (partyAddr.getSituated() != null) {
            sql.append(" SITUATED=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_ADDR_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyAddr.getPartyId() != null) {
                ps.setString(index++, partyAddr.getPartyId());
            }
            if (partyAddr.getLocalNetId() != null) {
                ps.setString(index++, partyAddr.getLocalNetId());
            }
            if (partyAddr.getAddrType() != null) {
                ps.setString(index++, partyAddr.getAddrType());
            }
            if (partyAddr.getAddrSts() != null) {
                ps.setString(index++, partyAddr.getAddrSts());
            }
            if (partyAddr.getStsDate() != null) {
                ps.setDate(index++, partyAddr.getStsDate());
            }
            if (partyAddr.getCreateDate() != null) {
                ps.setDate(index++, partyAddr.getCreateDate());
            }
            if (partyAddr.getSituated() != null) {
                ps.setString(index++, partyAddr.getSituated());
            }
            ps.setString(index++, partyAddr.getPartyAddrId());
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
        PartyAddrSVO partyAddr = (PartyAddrSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from PARTY_ADDR where 1=1");
        sql.append(" and PARTY_ADDR_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyAddr.getPartyAddrId());
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
        PartyAddrSVO result = null;
        PartyAddrSVO partyAddr = (PartyAddrSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.PARTY_ADDR_ID,a.PARTY_ID,a.LOCAL_NET_ID,a.ADDR_TYPE,a.ADDR_STS,a.STS_DATE,a.CREATE_DATE,a.SITUATED");
        sql.append(" from PARTY_ADDR a where 1=1");
        sql.append(" and PARTY_ADDR_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyAddr.getPartyAddrId());
            rs = ps.executeQuery();
            result = (PartyAddrSVO) ResultSetUtil.convertToVo(rs, PartyAddrSVO.class);
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
        PartyAddrSVO partyAddr = (PartyAddrSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.PARTY_ADDR_ID,a.PARTY_ID,a.LOCAL_NET_ID,a.ADDR_TYPE,a.ADDR_STS,a.STS_DATE,a.CREATE_DATE,a.SITUATED");
        sql.append(" from PARTY_ADDR a where 1=1");
        if (partyAddr.getPartyAddrId() != null) {
            sql.append(" and PARTY_ADDR_ID=?");
        }
        if (partyAddr.getPartyId() != null) {
            sql.append(" and PARTY_ID=?");
        }
        if (partyAddr.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (partyAddr.getAddrType() != null) {
            sql.append(" and ADDR_TYPE=?");
        }
        if (partyAddr.getAddrSts() != null) {
            sql.append(" and ADDR_STS=?");
        }
        if (partyAddr.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (partyAddr.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        if (partyAddr.getSituated() != null) {
            sql.append(" and SITUATED=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(),
                    partyAddr.getLocalNetId()));
            int index = 1;
            if (partyAddr.getPartyAddrId() != null) {
                ps.setString(index++, partyAddr.getPartyAddrId());
            }
            if (partyAddr.getPartyId() != null) {
                ps.setString(index++, partyAddr.getPartyId());
            }
            if (partyAddr.getLocalNetId() != null) {
                ps.setString(index++, partyAddr.getLocalNetId());
            }
            if (partyAddr.getAddrType() != null) {
                ps.setString(index++, partyAddr.getAddrType());
            }
            if (partyAddr.getAddrSts() != null) {
                ps.setString(index++, partyAddr.getAddrSts());
            }
            if (partyAddr.getStsDate() != null) {
                ps.setDate(index++, partyAddr.getStsDate());
            }
            if (partyAddr.getCreateDate() != null) {
                ps.setDate(index++, partyAddr.getCreateDate());
            }
            if (partyAddr.getSituated() != null) {
                ps.setString(index++, partyAddr.getSituated());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartyAddrSVO.class);
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
