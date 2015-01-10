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
import com.cattsoft.sm.component.dao.IContactMediumSDAO;
import com.cattsoft.sm.vo.ContactMediumInfoMVO;
import com.cattsoft.sm.vo.ContactMediumSVO;

public class ContactMediumSDAOImpl implements IContactMediumSDAO {
	
	
    private static Logger log = Logger.getLogger(ContactMediumSDAOImpl.class);

    public void add(GenericVO vo) throws AppException, SysException {
        ContactMediumSVO contactMedium = (ContactMediumSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" CONTACT_MEDIUM(CONTACT_ID,PARTY_ID,CONTACT_ADDR,CONTACT_NBR_TYPE,CONTACT_NBR,FAX_NBR,EMAIL_ADDR,EMAIL_PROTOCOL,POSTAL_CODE,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, contactMedium.getContactId());
            ps.setString(2, contactMedium.getPartyId());
            ps.setString(3, contactMedium.getContactAddr());
            ps.setString(4, contactMedium.getContactNbrType());
            ps.setString(5, contactMedium.getContactNbr());
            ps.setString(6, contactMedium.getFaxNbr());
            ps.setString(7, contactMedium.getEmailAddr());
            ps.setString(8, contactMedium.getEmailProtocol());
            ps.setString(9, contactMedium.getPostalCode());
            ps.setString(10, contactMedium.getSts());
            ps.setDate(11, contactMedium.getStsDate());
            ps.setDate(12, contactMedium.getCreateDate());
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
        ContactMediumSVO contactMedium = (ContactMediumSVO) vo;
        StringBuffer sql = new StringBuffer("update CONTACT_MEDIUM set");
        if (contactMedium.getPartyId() != null) {
            sql.append(" PARTY_ID=?,");
        }
        if (contactMedium.getContactAddr() != null) {
            sql.append(" CONTACT_ADDR=?,");
        }
        if (contactMedium.getContactNbrType() != null) {
            sql.append(" CONTACT_NBR_TYPE=?,");
        }
        if (contactMedium.getContactNbr() != null) {
            sql.append(" CONTACT_NBR=?,");
        }
        if (contactMedium.getFaxNbr() != null) {
            sql.append(" FAX_NBR=?,");
        }
        if (contactMedium.getEmailAddr() != null) {
            sql.append(" EMAIL_ADDR=?,");
        }
        if (contactMedium.getEmailProtocol() != null) {
            sql.append(" EMAIL_PROTOCOL=?,");
        }
        if (contactMedium.getPostalCode() != null) {
            sql.append(" POSTAL_CODE=?,");
        }
        if (contactMedium.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (contactMedium.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (contactMedium.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and CONTACT_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (contactMedium.getPartyId() != null) {
                ps.setString(index++, contactMedium.getPartyId());
            }
            if (contactMedium.getContactAddr() != null) {
                ps.setString(index++, contactMedium.getContactAddr());
            }
            if (contactMedium.getContactNbrType() != null) {
                ps.setString(index++, contactMedium.getContactNbrType());
            }
            if (contactMedium.getContactNbr() != null) {
                ps.setString(index++, contactMedium.getContactNbr());
            }
            if (contactMedium.getFaxNbr() != null) {
                ps.setString(index++, contactMedium.getFaxNbr());
            }
            if (contactMedium.getEmailAddr() != null) {
                ps.setString(index++, contactMedium.getEmailAddr());
            }
            if (contactMedium.getEmailProtocol() != null) {
                ps.setString(index++, contactMedium.getEmailProtocol());
            }
            if (contactMedium.getPostalCode() != null) {
                ps.setString(index++, contactMedium.getPostalCode());
            }
            if (contactMedium.getSts() != null) {
                ps.setString(index++, contactMedium.getSts());
            }
            if (contactMedium.getStsDate() != null) {
                ps.setDate(index++, contactMedium.getStsDate());
            }
            if (contactMedium.getCreateDate() != null) {
                ps.setDate(index++, contactMedium.getCreateDate());
            }
            ps.setString(index++, contactMedium.getContactId());
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
        ContactMediumSVO contactMedium = (ContactMediumSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from CONTACT_MEDIUM where 1=1");
        sql.append(" and CONTACT_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, contactMedium.getContactId());
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
        ContactMediumSVO result = null;
        ContactMediumSVO contactMedium = (ContactMediumSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.CONTACT_ID,a.PARTY_ID,a.CONTACT_ADDR,a.CONTACT_NBR_TYPE,a.CONTACT_NBR,a.FAX_NBR,a.EMAIL_ADDR,a.EMAIL_PROTOCOL,a.POSTAL_CODE,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from CONTACT_MEDIUM a where 1=1");
        sql.append(" and CONTACT_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, contactMedium.getContactId());
            rs = ps.executeQuery();
            result = (ContactMediumSVO) ResultSetUtil.convertToVo(rs, ContactMediumSVO.class);
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
        ContactMediumSVO contactMedium = (ContactMediumSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.CONTACT_ID,a.PARTY_ID,a.CONTACT_ADDR,a.CONTACT_NBR_TYPE,a.CONTACT_NBR,a.FAX_NBR,a.EMAIL_ADDR,a.EMAIL_PROTOCOL,a.POSTAL_CODE,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from CONTACT_MEDIUM a where 1=1");
        if (contactMedium.getContactId() != null) {
            sql.append(" and CONTACT_ID=?");
        }
        if (contactMedium.getPartyId() != null) {
            sql.append(" and PARTY_ID=?");
        }
        if (contactMedium.getContactAddr() != null) {
            sql.append(" and CONTACT_ADDR=?");
        }
        if (contactMedium.getContactNbrType() != null) {
            sql.append(" and CONTACT_NBR_TYPE=?");
        }
        if (contactMedium.getContactNbr() != null) {
            sql.append(" and CONTACT_NBR=?");
        }
        if (contactMedium.getFaxNbr() != null) {
            sql.append(" and FAX_NBR=?");
        }
        if (contactMedium.getEmailAddr() != null) {
            sql.append(" and EMAIL_ADDR=?");
        }
        if (contactMedium.getEmailProtocol() != null) {
            sql.append(" and EMAIL_PROTOCOL=?");
        }
        if (contactMedium.getPostalCode() != null) {
            sql.append(" and POSTAL_CODE=?");
        }
        if (contactMedium.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (contactMedium.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (contactMedium.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (contactMedium.getContactId() != null) {
                ps.setString(index++, contactMedium.getContactId());
            }
            if (contactMedium.getPartyId() != null) {
                ps.setString(index++, contactMedium.getPartyId());
            }
            if (contactMedium.getContactAddr() != null) {
                ps.setString(index++, contactMedium.getContactAddr());
            }
            if (contactMedium.getContactNbrType() != null) {
                ps.setString(index++, contactMedium.getContactNbrType());
            }
            if (contactMedium.getContactNbr() != null) {
                ps.setString(index++, contactMedium.getContactNbr());
            }
            if (contactMedium.getFaxNbr() != null) {
                ps.setString(index++, contactMedium.getFaxNbr());
            }
            if (contactMedium.getEmailAddr() != null) {
                ps.setString(index++, contactMedium.getEmailAddr());
            }
            if (contactMedium.getEmailProtocol() != null) {
                ps.setString(index++, contactMedium.getEmailProtocol());
            }
            if (contactMedium.getPostalCode() != null) {
                ps.setString(index++, contactMedium.getPostalCode());
            }
            if (contactMedium.getSts() != null) {
                ps.setString(index++, contactMedium.getSts());
            }
            if (contactMedium.getStsDate() != null) {
                ps.setDate(index++, contactMedium.getStsDate());
            }
            if (contactMedium.getCreateDate() != null) {
                ps.setDate(index++, contactMedium.getCreateDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, ContactMediumSVO.class);
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

    public void updateByPartyId(GenericVO vo) throws AppException, SysException {
        ContactMediumSVO contactMedium = (ContactMediumSVO) vo;
        StringBuffer sql = new StringBuffer("update CONTACT_MEDIUM set");
        if (contactMedium.getContactAddr() != null) {
            sql.append(" CONTACT_ADDR=?,");
        }
        if (contactMedium.getContactNbrType() != null) {
            sql.append(" CONTACT_NBR_TYPE=?,");
        }
        if (contactMedium.getContactNbr() != null) {
            sql.append(" CONTACT_NBR=?,");
        }
        if (contactMedium.getFaxNbr() != null) {
            sql.append(" FAX_NBR=?,");
        }
        if (contactMedium.getEmailAddr() != null) {
            sql.append(" EMAIL_ADDR=?,");
        }
        if (contactMedium.getEmailProtocol() != null) {
            sql.append(" EMAIL_PROTOCOL=?,");
        }
        if (contactMedium.getPostalCode() != null) {
            sql.append(" POSTAL_CODE=?,");
        }
        if (contactMedium.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (contactMedium.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (contactMedium.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (contactMedium.getContactAddr() != null) {
                ps.setString(index++, contactMedium.getContactAddr());
            }
            if (contactMedium.getContactNbrType() != null) {
                ps.setString(index++, contactMedium.getContactNbrType());
            }
            if (contactMedium.getContactNbr() != null) {
                ps.setString(index++, contactMedium.getContactNbr());
            }
            if (contactMedium.getFaxNbr() != null) {
                ps.setString(index++, contactMedium.getFaxNbr());
            }
            if (contactMedium.getEmailAddr() != null) {
                ps.setString(index++, contactMedium.getEmailAddr());
            }
            if (contactMedium.getEmailProtocol() != null) {
                ps.setString(index++, contactMedium.getEmailProtocol());
            }
            if (contactMedium.getPostalCode() != null) {
                ps.setString(index++, contactMedium.getPostalCode());
            }
            if (contactMedium.getSts() != null) {
                ps.setString(index++, contactMedium.getSts());
            }
            if (contactMedium.getStsDate() != null) {
                ps.setDate(index++, contactMedium.getStsDate());
            }
            if (contactMedium.getCreateDate() != null) {
                ps.setDate(index++, contactMedium.getCreateDate());
            }
            ps.setString(index++, contactMedium.getPartyId());
            ps.execute();
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "updateByPartyId error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public List findInfoByMVO(ContactMediumInfoMVO vo) throws AppException, SysException {
        List result = null;
        ContactMediumInfoMVO contactMedium = (ContactMediumInfoMVO) vo;
        StringBuffer sql = new StringBuffer(
                "select a.*,b.* from PARTY a,CONTACT_MEDIUM b where a.PARTY_ID=b.PARTY_ID and a.STS=b.STS");
        sql.append(" and b.STS='" + "A'");
        if (contactMedium.getPartyId() != null) {
            sql.append(" and b.PARTY_ID=?");
        }
        if (contactMedium.getContactId() != null) {
            sql.append(" and b.CONTACT_ID=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (contactMedium.getPartyId() != null) {
                ps.setString(index++, contactMedium.getPartyId());
            }
            if (contactMedium.getContactId() != null) {
                ps.setString(index++, contactMedium.getContactId());
            }
            rs = ps.executeQuery();
            result = (List) ResultSetUtil.convertToList(rs, ContactMediumInfoMVO.class);
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "findInfoByMVO error..", e);
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

}
