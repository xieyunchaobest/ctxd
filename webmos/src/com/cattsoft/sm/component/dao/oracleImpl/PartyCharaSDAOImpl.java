package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyCharaSDAO;
import com.cattsoft.sm.vo.PartyCharaSVO;

public class PartyCharaSDAOImpl implements IPartyCharaSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
        PartyCharaSVO partyChara = (PartyCharaSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" PARTY_CHARA(PARTY_CHARA_ID,PARTY_ID,CHARACTERISTIC_ID,CHARA_VALUE,REMARKS,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyChara.getPartyCharaId());
            ps.setString(2, partyChara.getPartyId());
            ps.setString(3, partyChara.getCharacteristicId());
            ps.setString(4, partyChara.getCharaValue());
            ps.setString(5, partyChara.getRemarks());
            ps.setString(6, partyChara.getSts());
            ps.setDate(7, partyChara.getStsDate());
            ps.setDate(8, partyChara.getCreateDate());
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
        PartyCharaSVO partyChara = (PartyCharaSVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY_CHARA set");
        if (partyChara.getPartyId() != null) {
            sql.append(" PARTY_ID=?,");
        }
        if (partyChara.getCharacteristicId() != null) {
            sql.append(" CHARACTERISTIC_ID=?,");
        }
        if (partyChara.getCharaValue() != null) {
            sql.append(" CHARA_VALUE=?,");
        }
        if (partyChara.getRemarks() != null) {
            sql.append(" REMARKS=?,");
        }
        if (partyChara.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (partyChara.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (partyChara.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_CHARA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyChara.getPartyId() != null) {
                ps.setString(index++, partyChara.getPartyId());
            }
            if (partyChara.getCharacteristicId() != null) {
                ps.setString(index++, partyChara.getCharacteristicId());
            }
            if (partyChara.getCharaValue() != null) {
                ps.setString(index++, partyChara.getCharaValue());
            }
            if (partyChara.getRemarks() != null) {
                ps.setString(index++, partyChara.getRemarks());
            }
            if (partyChara.getSts() != null) {
                ps.setString(index++, partyChara.getSts());
            }
            if (partyChara.getStsDate() != null) {
                ps.setDate(index++, partyChara.getStsDate());
            }
            if (partyChara.getCreateDate() != null) {
                ps.setDate(index++, partyChara.getCreateDate());
            }
            ps.setString(index++, partyChara.getPartyCharaId());
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
        PartyCharaSVO partyChara = (PartyCharaSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from PARTY_CHARA where 1=1");
        sql.append(" and PARTY_CHARA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyChara.getPartyCharaId());
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
        PartyCharaSVO result = null;
        PartyCharaSVO partyChara = (PartyCharaSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.PARTY_CHARA_ID,a.PARTY_ID,a.CHARACTERISTIC_ID,a.CHARA_VALUE,a.REMARKS,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from PARTY_CHARA a where 1=1");
        sql.append(" and PARTY_CHARA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyChara.getPartyCharaId());
            rs = ps.executeQuery();
            result = (PartyCharaSVO) ResultSetUtil.convertToVo(rs, PartyCharaSVO.class);
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
        PartyCharaSVO partyChara = (PartyCharaSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.PARTY_CHARA_ID,a.PARTY_ID,a.CHARACTERISTIC_ID,a.CHARA_VALUE,a.REMARKS,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from PARTY_CHARA a where 1=1");
        if (partyChara.getPartyCharaId() != null) {
            sql.append(" and PARTY_CHARA_ID=?");
        }
        if (partyChara.getPartyId() != null) {
            sql.append(" and PARTY_ID=?");
        }
        if (partyChara.getCharacteristicId() != null) {
            sql.append(" and CHARACTERISTIC_ID=?");
        }
        if (partyChara.getCharaValue() != null) {
            sql.append(" and CHARA_VALUE=?");
        }
        if (partyChara.getRemarks() != null) {
            sql.append(" and REMARKS=?");
        }
        if (partyChara.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (partyChara.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (partyChara.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyChara.getPartyCharaId() != null) {
                ps.setString(index++, partyChara.getPartyCharaId());
            }
            if (partyChara.getPartyId() != null) {
                ps.setString(index++, partyChara.getPartyId());
            }
            if (partyChara.getCharacteristicId() != null) {
                ps.setString(index++, partyChara.getCharacteristicId());
            }
            if (partyChara.getCharaValue() != null) {
                ps.setString(index++, partyChara.getCharaValue());
            }
            if (partyChara.getRemarks() != null) {
                ps.setString(index++, partyChara.getRemarks());
            }
            if (partyChara.getSts() != null) {
                ps.setString(index++, partyChara.getSts());
            }
            if (partyChara.getStsDate() != null) {
                ps.setDate(index++, partyChara.getStsDate());
            }
            if (partyChara.getCreateDate() != null) {
                ps.setDate(index++, partyChara.getCreateDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartyCharaSVO.class);
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
