package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ILocalNetMDAO;
import com.cattsoft.sm.vo.LocalNetMVO;

public class LocalNetMDAOImpl implements ILocalNetMDAO {

    public List findLocalNetsExceptAll(LocalNetMVO vo) throws SysException {
        
    	List vos = null;
        StringBuffer sql = new StringBuffer();
        sql
                .append("select a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.DIST_NBR,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE,a.JF_LOCAL_NET_ID from Local_Net a ");
        sql.append("where 1=1 ");
        if (vo.getLocalNetId() != null)
            sql.append("and a.local_Net_Id=? ");
        if (vo.getName() != null)
            sql.append("and a.name like '%? ");
        if (vo.getSts() != null)
            sql.append("and a.sts =? ");
        sql.append("order by a.local_Net_Id");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (vo.getLocalNetId() != null) {
                ps.setString(index++, vo.getLocalNetId());
            }
            if (vo.getName() != null) {
                ps.setString(index++, vo.getName());
            }
            if (vo.getSts() != null) {
                ps.setString(index++, vo.getSts());
            }
            rs = ps.executeQuery();
            LocalNetMVO localNet = new LocalNetMVO();
            while (rs.next()) {
                localNet = (LocalNetMVO) ResultSetUtil.convertToVo(rs, LocalNetMVO.class);
                vos.add(localNet);
            }
            LocalNetMVO localNetMVO = null;
            for (int i = 0; i < vos.size(); i++) {
                localNetMVO = (LocalNetMVO) vos.get(i);
                if (Integer.parseInt(localNetMVO.getLocalNetId()) == 0) {
                    vos.remove(localNetMVO);
                    break;
                }
            }
        } catch (Exception e) {
            throw new SysException("", "findCustAcctInUseByVO error..", e);
        }
        return vos;
    }

    /**
     * 查询所有LocalNet
     * 
     * @param vo
     *            LocalNetVO
     * @throws SysException
     */

    public List findLocalNets(LocalNetMVO vo) throws SysException {
        List pos = null;
        // get query total no;
        StringBuffer sql = new StringBuffer();
        sql
                .append("select a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.DIST_NBR,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE,a.JF_LOCAL_NET_ID from Local_Net a ");
        sql.append("where 1=1 ");
        if (vo.getLocalNetId() != null)
            sql.append("and a.local_Net_Id= ? ");
        if (vo.getName() != null)
            sql.append("and a.name like '% ? %'");
        sql.append("and a.sts='A' ");
        sql.append(" order by a.local_Net_Id");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (vo.getLocalNetId() != null) {
                ps.setString(index++, vo.getLocalNetId());
            }
            if (vo.getName() != null) {
                ps.setString(index++, vo.getName());
            }
            rs = ps.executeQuery();

            pos = (List) ResultSetUtil.convertToList(rs, LocalNetMVO.class);

        } catch (Exception e) {
            throw new SysException("", "findCustAcctInUseByVO error..", e);
        }
        return pos;

    }
    
    /**
     * 作用：获取本地网的google map中心点
     * create time: Feb 11, 2010
     * author: yangkai
     * @param vo
     * @return
     * @throws AppException
     * @throws SysException
     */
    public List findByPK(GenericVO vo) throws AppException, SysException {
return null;
    	}
}
