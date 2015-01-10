package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.sm.component.dao.IOrgDeptMDAO;
import com.cattsoft.sm.vo.OrgDeptMVO;
import com.cattsoft.sm.vo.OrgDeptSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-30 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class OrgDeptMDAOImpl implements IOrgDeptMDAO {
	

	 private Logger log = Logger.getLogger(OrgDeptMDAOImpl.class);

	    public OrgDeptMVO findOrgDeptById(String deptId) throws AppException, SysException {
	        OrgDeptMVO result = null;
	        StringBuffer sql = new StringBuffer();
	        sql
	                .append(
	                        " select a.DEPT_ID,a.DEPT_NAME,a.AREA_ID,a.PARENT_DEPT_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.ADMIN_STAFF_ID,a.DEPT_TYPE,a.ROOT_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE,a.DEPT_DESC,a.ADDRESS,a.POST_NBR,a.BRANCH_ID,b.standard_Code,b.company_size,b.jur_Person,b.net_Type,b.tax_Nbr,b.remarks,c.PARTY_IDENTITY_id,")
	                .append(
	                        "c.cert_Code,c.cert_Type_Id,c.cert_exp_date,d.contact_id,d.postal_Code,d.contact_addr from org_dept a,party b,PARTY_IDENTITY c,CONTACT_MEDIUM d where 1=1");
	        sql
	                .append("and a.party_id=b.party_id and b.party_id=c.party_id(+) and b.party_id=d.party_id(+) and a.DEPT_ID=?");
	        sql.append("and a.sts='A' and b.sts='A'");
	        sql.append("and c.sts(+)='A' and d.sts(+)='A'");
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            connection = ConnectionFactory.getConnection();
	            ps = connection.prepareStatement(sql.toString());
	            ps.setString(1, deptId);
	            rs = ps.executeQuery();
	            result = (OrgDeptMVO) ResultSetUtil.convertToVo(rs, OrgDeptMVO.class);
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

	    public List findDeptTree(String deptId) throws AppException, SysException {

	        List result=(List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
	        StringBuffer sql = new StringBuffer();
	        sql
	                .append("select od.dept_id,od.dept_name from org_dept od connect by nocycle prior od.dept_id = od.parent_dept_id " );
	                if(deptId!=null)       
	                sql.append( "start with od.dept_id=? ");
	                sql.append(" order by dept_id");
	                     
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        log.debug(sql.toString());
	        try {
	            connection = ConnectionFactory.getConnection();
	            ps = connection.prepareStatement(sql.toString());
	            if(deptId!=null)
	            ps.setString(1, deptId);
	            rs = ps.executeQuery();
	            OrgDeptSVO svo=null;
	            while(rs.next()){
	                svo=new OrgDeptSVO();
	                svo.setDeptId(rs.getString("dept_id"));
	                svo.setDeptName(rs.getString("dept_name"));
	                result.add(svo);
	            }
	        } catch (SQLException e) {
	            throw new AppException("101436", "数据配置错误，存在环状数据");
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

 
		public List findDeptByLeaf(OrgDeptSVO svo) throws AppException,
				SysException {
			if (svo == null) {
				throw new AppException("100001", "缺少DAO操作对象！");
			}
			List res = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Sql sql;
			sql = new Sql("SELECT  pko.performance_check_obj_id, pko.check_object_id, ");
			sql.append(" a.DEPT_ID,a.DEPT_NAME,a.AREA_ID,a.PARENT_DEPT_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.ADMIN_STAFF_ID,a.DEPT_TYPE,a.ROOT_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE,a.DEPT_DESC,a.ADDRESS,a.POST_NBR,a.BRANCH_ID");
			sql.append("  from org_dept a,(select performance_check_obj_id, check_object_id from performance_check_obj  where performance_id=:performanceId and sts=:sts)pko");
			sql.append(" where 1=1");
			sql.append(" and a.parent_dept_id=:parentDeptId and pko.check_object_id(+) = a.dept_id");
			try {
				conn = ConnectionFactory.getConnection();
				ps = conn.prepareStatement(sql.getSql());
				ps = sql.fillParams(ps);
				sql.log(this.getClass());
				rs = ps.executeQuery();
				while (rs.next()) {
					svo = new OrgDeptSVO();
					svo.setDeptId(rs.getString("DEPT_ID"));
					svo.setDeptName(rs.getString("DEPT_NAME"));
					svo.setDeptType(rs.getString("DEPT_TYPE"));
					res.add(svo);
				}
			} catch (SQLException se) {
				throw new SysException("100003", "JDBC操作异常！", se);
			} finally {
				JdbcUtil.close(rs, ps);
			}
			if (0 == res.size())
				res = null;
			return res;
		}
}
