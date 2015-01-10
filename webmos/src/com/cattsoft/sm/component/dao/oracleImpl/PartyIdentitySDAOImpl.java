package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyIdentitySDAO;
import com.cattsoft.sm.vo.PartyIdentitySVO;

public class PartyIdentitySDAOImpl implements IPartyIdentitySDAO {

    public void add(GenericVO vo) throws AppException, SysException {
    	
        PartyIdentitySVO partyIdentity = (PartyIdentitySVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" PARTY_IDENTITY(PARTY_IDENTITY_ID,CERT_TYPE_ID,LOCAL_NET_ID,PARTY_ID,CERT_CODE,CERT_ADDR,CERT_EXP_DATE,DETAIL_INFO,CHECK_INFO,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            if (partyIdentity.getPartyIdentityId() == null) {
                ps.setString(1, MaxId.getSequenceNextVal(Constant.SEQ_PARTY_IDENTITY_ID));
            } else {
                ps.setString(1, partyIdentity.getPartyIdentityId());
            }
            ps.setString(2, partyIdentity.getCertTypeId());
            ps.setString(3, partyIdentity.getLocalNetId());
            ps.setString(4, partyIdentity.getPartyId());
            ps.setString(5, partyIdentity.getCertCode());
            ps.setString(6, partyIdentity.getCertAddr());
            ps.setDate(7, partyIdentity.getCertExpDate());
            ps.setString(8, partyIdentity.getDetailInfo());
            ps.setString(9, partyIdentity.getCheckInfo());
            ps.setString(10, partyIdentity.getSts());
            ps.setDate(11, partyIdentity.getStsDate());
            ps.setDate(12, partyIdentity.getCreateDate());
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
        PartyIdentitySVO partyIdentity = (PartyIdentitySVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY_IDENTITY set");
        if (partyIdentity.getCertTypeId() != null) {
            sql.append(" CERT_TYPE_ID=?,");
        }
        if (partyIdentity.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (partyIdentity.getPartyId() != null) {
            sql.append(" PARTY_ID=?,");
        }
        if (partyIdentity.getCertCode() != null) {
            sql.append(" CERT_CODE=?,");
        }
        if (partyIdentity.getCertAddr() != null) {
            sql.append(" CERT_ADDR=?,");
        }
        if (partyIdentity.getCertExpDate() != null) {
            sql.append(" CERT_EXP_DATE=?,");
        }
        if (partyIdentity.getDetailInfo() != null) {
            sql.append(" DETAIL_INFO=?,");
        }
        if (partyIdentity.getCheckInfo() != null) {
            sql.append(" CHECK_INFO=?,");
        }
        if (partyIdentity.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (partyIdentity.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (partyIdentity.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_IDENTITY_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyIdentity.getCertTypeId() != null) {
                ps.setString(index++, partyIdentity.getCertTypeId());
            }
            if (partyIdentity.getLocalNetId() != null) {
                ps.setString(index++, partyIdentity.getLocalNetId());
            }
            if (partyIdentity.getPartyId() != null) {
                ps.setString(index++, partyIdentity.getPartyId());
            }
            if (partyIdentity.getCertCode() != null) {
                ps.setString(index++, partyIdentity.getCertCode());
            }
            if (partyIdentity.getCertAddr() != null) {
                ps.setString(index++, partyIdentity.getCertAddr());
            }
            if (partyIdentity.getCertExpDate() != null) {
                ps.setDate(index++, partyIdentity.getCertExpDate());
            }
            if (partyIdentity.getDetailInfo() != null) {
                ps.setString(index++, partyIdentity.getDetailInfo());
            }
            if (partyIdentity.getCheckInfo() != null) {
                ps.setString(index++, partyIdentity.getCheckInfo());
            }
            if (partyIdentity.getSts() != null) {
                ps.setString(index++, partyIdentity.getSts());
            }
            if (partyIdentity.getStsDate() != null) {
                ps.setDate(index++, partyIdentity.getStsDate());
            }
            if (partyIdentity.getCreateDate() != null) {
                ps.setDate(index++, partyIdentity.getCreateDate());
            }
            ps.setString(index++, partyIdentity.getPartyIdentityId());
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
        PartyIdentitySVO partyIdentity = (PartyIdentitySVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from PARTY_IDENTITY where 1=1");
        sql.append(" and PARTY_IDENTITY_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyIdentity.getPartyIdentityId());
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
        PartyIdentitySVO result = null;
        PartyIdentitySVO partyIdentity = (PartyIdentitySVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.PARTY_IDENTITY_ID,a.CERT_TYPE_ID,a.LOCAL_NET_ID,a.PARTY_ID,a.CERT_CODE,a.CERT_ADDR,a.CERT_EXP_DATE,a.DETAIL_INFO,a.CHECK_INFO,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from PARTY_IDENTITY a where 1=1");
        sql.append(" and PARTY_IDENTITY_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyIdentity.getPartyIdentityId());
            rs = ps.executeQuery();
            result = (PartyIdentitySVO) ResultSetUtil.convertToVo(rs, PartyIdentitySVO.class);
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
        PartyIdentitySVO partyIdentity = (PartyIdentitySVO) vo;
        Sql sql = new Sql();
        sql.append(" select ");
        sql.append(" a.PARTY_IDENTITY_ID,a.CERT_TYPE_ID,a.LOCAL_NET_ID,a.PARTY_ID, ");
        sql.append(" a.CERT_CODE,a.CERT_ADDR,a.CERT_EXP_DATE,a.DETAIL_INFO, ");
        sql.append(" a.CHECK_INFO,a.STS,a.STS_DATE,a.CREATE_DATE ");
        sql.append(" from PARTY_IDENTITY a where 1=1 ");
        if (partyIdentity.getPartyIdentityId() != null) {
            sql.append(" and PARTY_IDENTITY_ID=:partyIdentityId");
            sql.setString("partyIdentityId", partyIdentity.getPartyIdentityId());
        }
        if (partyIdentity.getCertTypeId() != null) {
            sql.append(" and CERT_TYPE_ID=:certType");
            sql.setString("certType", partyIdentity.getCertTypeId());
        }
        if (partyIdentity.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=:localNetId ");
            sql.setString("localNetId",partyIdentity.getLocalNetId());
        }
        if (partyIdentity.getPartyId() != null) {
            sql.append(" and PARTY_ID=:partyId");
            sql.setString("partyId", partyIdentity.getPartyId());
        }
        if (partyIdentity.getCertCode() != null) {
            sql.append(" and CERT_CODE=:certCode");
            sql.setString("certCode", partyIdentity.getCertCode());
        }
        if (partyIdentity.getCertAddr() != null) {
            sql.append(" and CERT_ADDR=:certAddr");
            sql.setString("certAddr", partyIdentity.getCertAddr());
        }
        if (partyIdentity.getCertExpDate() != null) {
            sql.append(" and CERT_EXP_DATE=:certExpDate");
            sql.setTimestamp("certExpDate", partyIdentity.getCertExpDate());
        }
        if (partyIdentity.getDetailInfo() != null) {
            sql.append(" and DETAIL_INFO=:detailInfo");
            sql.setString("detailInfo", partyIdentity.getDetailInfo());
        }
        if (partyIdentity.getCheckInfo() != null) {
            sql.append(" and CHECK_INFO=:checkInfo");
            sql.setString("checkInfo", partyIdentity.getCheckInfo());
        }
        if (partyIdentity.getSts() != null) {
            sql.append(" and STS=:sts");
            sql.setString("sts", partyIdentity.getSts());
        }
        if (partyIdentity.getStsDate() != null) {
            sql.append(" and STS_DATE=:stsDate");
            sql.setTimestamp("stsDate", partyIdentity.getStsDate());
        }
        if (partyIdentity.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=:createDate");
            sql.setTimestamp("createDate", partyIdentity.getCreateDate());
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            connection = ConnectionFactory.getConnection();
//            String localNetId=partyIdentity.getLocalNetId();
//            ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(),localNetId));
//            int index = 1;
//            if (partyIdentity.getPartyIdentityId() != null) {
//                ps.setString(index++, partyIdentity.getPartyIdentityId());
//            }
//            if (partyIdentity.getCertTypeId() != null) {
//                ps.setString(index++, partyIdentity.getCertTypeId());
//            }
//            if (partyIdentity.getLocalNetId() != null) {
//                ps.setString(index++, partyIdentity.getLocalNetId());
//            }
//            if (partyIdentity.getPartyId() != null) {
//                ps.setString(index++, partyIdentity.getPartyId());
//            }
//            if (partyIdentity.getCertCode() != null) {
//                ps.setString(index++, partyIdentity.getCertCode());
//            }
//            if (partyIdentity.getCertAddr() != null) {
//                ps.setString(index++, partyIdentity.getCertAddr());
//            }
//            if (partyIdentity.getCertExpDate() != null) {
//                ps.setDate(index++, partyIdentity.getCertExpDate());
//            }
//            if (partyIdentity.getDetailInfo() != null) {
//                ps.setString(index++, partyIdentity.getDetailInfo());
//            }
//            if (partyIdentity.getCheckInfo() != null) {
//                ps.setString(index++, partyIdentity.getCheckInfo());
//            }
//            if (partyIdentity.getSts() != null) {
//                ps.setString(index++, partyIdentity.getSts());
//            }
//            if (partyIdentity.getStsDate() != null) {
//                ps.setDate(index++, partyIdentity.getStsDate());
//            }
//            if (partyIdentity.getCreateDate() != null) {
//                ps.setDate(index++, partyIdentity.getCreateDate());
//            }
        	connection = ConnectionFactory.getConnection();
        	ps = connection.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartyIdentitySVO.class);
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
