package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ILocalNetAreaMDAO;
import com.cattsoft.sm.vo.AreaSVO;
import com.cattsoft.sm.vo.LocalNetSVO;

public class LocalNetAreaMDAOImpl implements ILocalNetAreaMDAO {

    public List findLocalNet(GenericVO vo1, GenericVO vo2) throws AppException, SysException {
       
    	List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        LocalNetSVO localNet = (LocalNetSVO) vo1;
        AreaSVO area = (AreaSVO) vo2;
        if (localNet.getLocalNetId() == null) {
            throw new AppException("", "无法获得员工所处本地网");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("select LOCAL_NET_ID,NAME from LOCAL_NET where 1=1");
        if (Constant.IS_CENTER_LOCAL_NET_Y.equalsIgnoreCase(localNet.getIscenter())
                && Constant.IS_CENTER_AREA_Y.equalsIgnoreCase(area.getIscenter())) {
            /** TODO 所处工区是中心本地网、中心服务区，需要查出所有的服务区 */
        } else {
            /** 所处工区不是中心本地网，或者是中心本地网而非中心服务区都只能获取自己的信息 */
            sql.append(" and LOCAL_NET_ID = ?");
        }
        if (localNet.getSts() != null) {
            sql.append(" and STS = ?");
        }
        sql.append(" order by LOCAL_NET_ID desc");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (Constant.IS_CENTER_LOCAL_NET_Y.equalsIgnoreCase(localNet.getIscenter())
                    && Constant.IS_CENTER_AREA_Y.equalsIgnoreCase(area.getIscenter())) {
                /** TODO 所处工区是中心本地网、中心服务区，需要查出所有的服务区 */
            } else {
                ps.setString(index++, localNet.getLocalNetId());
            }
            if (localNet.getSts() != null) {
                ps.setString(index++, localNet.getSts());
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                LabelValueBean option = new LabelValueBean();
                option.setLabel(rs.getString("NAME"));
                option.setValue(rs.getString("LOCAL_NET_ID"));
                results.add(option);
            }
        } catch (SQLException e) {
            throw new SysException("", "findLocalNet error..", e);
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

    public List findArea(GenericVO vo1, GenericVO vo2) throws AppException, SysException {
        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        LocalNetSVO localNet = (LocalNetSVO) vo1;
        AreaSVO area = (AreaSVO) vo2;
        if (area.getLocalNetId() == null) {
            throw new AppException("", "无法获得员工所处本地网服务区");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("select AREA_ID,NAME from AREA where 1=1");
        /** 员工所属工区为中心本地网、中心服务区,此时需要在服务区列表中多查一条叫"所有服务区"的记录 */
        if (Constant.IS_CENTER_LOCAL_NET_Y.equalsIgnoreCase(localNet.getIscenter())
                && Constant.IS_CENTER_AREA_Y.equalsIgnoreCase(area.getIscenter())) {
            sql.append(" and (LOCAL_NET_ID = ? or LOCAL_NET_ID = 0 )");
        } else {
            sql.append(" and LOCAL_NET_ID = ?");
        }
        if (area.getSts() != null) {
            sql.append(" and STS = ?");
        }
        sql.append(" order by AREA_ID desc");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, area.getLocalNetId());
            if (area.getSts() != null) {
                ps.setString(index++, area.getSts());
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                LabelValueBean option = new LabelValueBean();
                option.setLabel(rs.getString("NAME"));
                option.setValue(rs.getString("AREA_ID"));
                results.add(option);
            }
        } catch (SQLException e) {
            throw new SysException("", "findArea error..", e);
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

    /** 在发布时，999记录是没有实际意义的工区，不应该出现在前台 */
    public List findLocalNetTree(GenericVO vo1, GenericVO vo2) throws AppException, SysException {
        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        LocalNetSVO localNet = (LocalNetSVO) vo1;
        AreaSVO area = (AreaSVO) vo2;
        if (localNet.getLocalNetId() == null) {
            throw new AppException("", "无法获得员工所处本地网");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("select LOCAL_NET_ID,NAME from LOCAL_NET where 1=1");
        if (localNet.getLocalNetId() != null && area.getAreaId() != null) {
            if (Constant.IS_CENTER_LOCAL_NET_Y.equalsIgnoreCase(localNet.getIscenter())
                    && Constant.IS_CENTER_AREA_Y.equalsIgnoreCase(area.getIscenter())) {
                /** TODO 所处工区是中心本地网、中心服务区，需要查出所有的服务区,但是不包含999 */
                sql.append(" and LOCAL_NET_ID <> ?");
            } else {
                /** 所处工区不是中心本地网，或者是中心本地网而非中心服务区都只能获取自己的信息 */
                sql.append(" and LOCAL_NET_ID = ?");
            }
        }
        if (localNet.getSts() != null) {
            sql.append(" and STS = ?");
        }
        sql.append(" order by LOCAL_NET_ID desc");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (Constant.IS_CENTER_LOCAL_NET_Y.equalsIgnoreCase(localNet.getIscenter())) {
                /** TODO 所处工区是中心本地网、中心服务区，需要查出所有的服务区 */
                ps.setString(index++, Constant.CENTER_LOCAL_NET_ID);
            } else {
                ps.setString(index++, localNet.getLocalNetId());
            }
            if (localNet.getSts() != null) {
                ps.setString(index++, localNet.getSts());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, LocalNetSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findLocalNet error..", e);
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

    public List findAreaTree(GenericVO vo1, GenericVO vo2) throws AppException, SysException {
        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        LocalNetSVO localNet = (LocalNetSVO) vo1;
        AreaSVO area = (AreaSVO) vo2;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from AREA where 1=1");
        if (localNet.getLocalNetId() != null && area.getAreaId() != null) {
            /** 员工所属工区为中心本地网中心服务区 */
            if (Constant.IS_CENTER_LOCAL_NET_Y.equalsIgnoreCase(localNet.getIscenter())
                    && Constant.IS_CENTER_AREA_Y.equalsIgnoreCase(area.getIscenter())) {
                sql.append(" and LOCAL_NET_ID <> ?");

            } else {
                sql.append(" and AREA_ID = ?");
                sql.append(" and LOCAL_NET_ID = ?");
            }
        }
        if (area.getSts() != null) {
            sql.append(" and STS = ?");
        }
        sql.append(" order by AREA_ID desc");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (Constant.IS_CENTER_LOCAL_NET_Y.equalsIgnoreCase(localNet.getIscenter())
                    && Constant.IS_CENTER_AREA_Y.equalsIgnoreCase(area.getIscenter())) {
                ps.setString(index++, Constant.CENTER_LOCAL_NET_ID);
            } else {
                ps.setString(index++, area.getAreaId());
                ps.setString(index++, localNet.getLocalNetId());
            }

            if (area.getSts() != null) {
                ps.setString(index++, area.getSts());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, AreaSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findArea error..", e);
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
