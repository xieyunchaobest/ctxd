package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyIdentityMDAO;
import com.cattsoft.sm.vo.QryBasicCustInfoMVO;
import com.cattsoft.sm.vo.QryCustInfoConditionMVO;
import com.cattsoft.sm.vo.QryPartyInfoConditionMVO;

public class PartyIdentityMDAOImpl implements IPartyIdentityMDAO {
	
	

    public QryBasicCustInfoMVO findCertInfo(QryCustInfoConditionMVO vo) throws AppException,
            SysException {
        QryBasicCustInfoMVO qryBasicCustInfoMVO = null;
        StringBuffer sql = new StringBuffer();
        sql
                .append("select a.cert_code, b.name as cert_type_name from party_identity a, cert_type b, party_role c where 1=1 ");
        sql.append("and a.party_Id = c.party_id ");
        sql.append("and a.cert_type_id = b.cert_type_id ");
        sql.append("and party_role_type_id = " + Constant.PARTY_ROLE_TYPE_CUST + " ");
        sql.append("and party_role_id = ? ");
        sql.append("and a.local_net_id = ? ");
        sql.append("and a.sts = '" + Constant.STS_IN_USE + "' ");
        sql.append("and b.sts = '" + Constant.STS_IN_USE + "' ");
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
                qryBasicCustInfoMVO.setCertType(rs.getString("cert_type_name"));
                qryBasicCustInfoMVO.setCertCode(rs.getString("cert_code"));
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "findCertInfo error", e);
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

    private Logger log = Logger.getLogger(PartyIdentityMDAOImpl.class);

    public List findPartyRoleIdByCertCode(GenericVO vo) throws AppException, SysException {
        QryPartyInfoConditionMVO qryVo = (QryPartyInfoConditionMVO) vo;
        List custIdList = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        StringBuffer sql = new StringBuffer();
        sql.append("select d.party_role_id ");
        sql
                .append("from party_identity a left join cert_type b on a.cert_type_id = b.cert_type_id ");
        sql.append("left join party c on a.party_id = c.party_id ");
        sql.append("left join party_role d on c.party_id = d.party_id where 1=1 ");
        sql.append("and a.cert_code = ? ");
        sql.append("and a.local_net_id = ? ");
        sql.append("and b.cert_type_id = ? ");
        sql.append("and (b.sts = '" + Constant.STS_IN_USE + "' or b.sts is null) ");
        sql.append("and (c.sts = '" + Constant.STS_IN_USE + "' or c.sts is null) ");
        sql.append("and (c.local_net_id = ? or c.local_net_id is null) ");
        sql.append("and d.party_role_type_id =? ");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, qryVo.getCertCode());
            ps.setString(index++, qryVo.getLocalNetId());
            ps.setString(index++, qryVo.getCertType());
            ps.setString(index++, qryVo.getLocalNetId());
            ps.setString(index++, qryVo.getPartyRoleType());
            rs = ps.executeQuery();
            if (rs.next()) {
                custIdList.add(rs.getString("party_role_id"));
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "findPartyRoleIdByCertCode error", e);
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
        return custIdList.size() == 0 ? null : custIdList;
    }

}
