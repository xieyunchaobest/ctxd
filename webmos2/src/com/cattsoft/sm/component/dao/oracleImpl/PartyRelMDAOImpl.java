package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.component.dao.IPartyRelMDAO;

public class PartyRelMDAOImpl implements IPartyRelMDAO {
	

    public LabelValueBean findParentCust(String partyId2) throws SysException, AppException {
        LabelValueBean lv = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select b.name, d.cust_id from party_rel a,party b,party_role c,cust d");
        sql.append(" where 1=1");
        sql.append(" and a.party_id1 = b.party_id and a.party_id1 = c.party_id ");
        sql.append(" and c.party_role_id = d.cust_id and c.party_role_type_id = 8");
        sql.append(" and a.rel_type = 9");
        if (partyId2 != null) {
            sql.append(" and a.party_id2 =?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyId2 != null) {
                ps.setString(index, partyId2);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                lv = new LabelValueBean();
                lv.setLabel(rs.getString("name"));
                lv.setValue(rs.getString("cust_id"));
                break;
            }
        } catch (SQLException e) {
            throw new SysException("", "findParentCust error..", e);
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
        return lv;
    }

}
