package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.sm.component.dao.IStaffWorkAreaMDAO;
import com.cattsoft.sm.vo.StaffWorkAreaMVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-10 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class StaffWorkAreaMDAOImpl implements IStaffWorkAreaMDAO {
	

    //private Logger log = Logger.getLogger(StaffWorkAreaMDAOImpl.class);

    public List findStaffWorkAreaByVo(StaffWorkAreaMVO mvo, String localNetId) throws SysException,
            AppException {

        List results = null;
        StaffWorkAreaMVO staffWorkArea = (StaffWorkAreaMVO) mvo;
        Sql sql = new Sql();
        sql.append("select a.staff_work_area_id,a.staff_id,a.work_area_id,a.grantor,a.admin_flag,a.sts,a.sts_date,c.name staff_name,d.name work_area_name ");
        sql.append(" from STAFF_WORK_AREA a, staff b, party c, work_area d where 1=1 ");
        sql.append("and a.staff_id=b.staff_id and b.party_id=c.party_id and a.work_area_id=d.work_area_id and d.sts='A' ");

        if (staffWorkArea.getStaffWorkAreaId() != null) {
            sql.append(" and a.STAFF_WORK_AREA_ID=:staffWorkAreaId ");
            sql.setString("staffWorkAreaId", staffWorkArea.getStaffWorkAreaId());
        }
        if (staffWorkArea.getStaffId() != null) {
            sql.append(" and a.STAFF_ID=:staffId ");
            sql.setString("staffId", staffWorkArea.getStaffId());
        }
        if (staffWorkArea.getWorkAreaId() != null) {
            sql.append(" and a.WORK_AREA_ID=:workAreaId ");
            sql.setString("workAreaId", staffWorkArea.getWorkAreaId());
        }
        if (staffWorkArea.getGrantor() != null) {
            sql.append(" and a.GRANTOR=:grantor ");
            sql.setString("grantor","'"+staffWorkArea.getGrantor()+"'");
        }
        if (staffWorkArea.getAdminFlag() != null) {
            sql.append(" and a.ADMIN_FLAG=:adminFlag ");
            sql.setString("adminFlag", staffWorkArea.getAdminFlag());
        }
        if (staffWorkArea.getSts() != null) {
            sql.append(" and a.STS=:sts");
            sql.setString("sts", staffWorkArea.getSts());
        }
        if (staffWorkArea.getStsDate() != null) {
            sql.append(" and a.STS_DATE=:stsDate");
            sql.setTimestamp("stsDate", staffWorkArea.getStsDate());
        }
        if (staffWorkArea.getCreateDate() != null) {
            sql.append(" and a.CREATE_DATE=:createDate ");
            sql.setTimestamp("createDate", staffWorkArea.getCreateDate());
        }
        //modi by liyaquan start
        if (staffWorkArea.getAreaId() != null) {
            sql.append(" and d.AREA_ID=:areaId ");
            sql.setString("areaId", staffWorkArea.getAreaId());
        }
       sql.append(" order by d.name ");
       //modi by liyaquan end
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            connection = ConnectionFactory.getConnection();
//            ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(),
//                    localNetId));
//            log.debug(PartitionUtil.getPartitionSQL(sql.toString(), localNetId));
//            int index = 1;
//            if (staffWorkArea.getStaffWorkAreaId() != null) {
//                ps.setString(index++, staffWorkArea.getStaffWorkAreaId());
//            }
//            if (staffWorkArea.getStaffId() != null) {
//                ps.setString(index++, staffWorkArea.getStaffId());
//            }
//            if (staffWorkArea.getWorkAreaId() != null) {
//                ps.setString(index++, staffWorkArea.getWorkAreaId());
//            }
//            if (staffWorkArea.getGrantor() != null) {
//                ps.setString(index++, staffWorkArea.getGrantor());
//            }
//            if (staffWorkArea.getAdminFlag() != null) {
//                ps.setString(index++, staffWorkArea.getAdminFlag());
//            }
//            if (staffWorkArea.getSts() != null) {
//                ps.setString(index++, staffWorkArea.getSts());
//            }
//            if (staffWorkArea.getStsDate() != null) {
//                ps.setDate(index++, staffWorkArea.getStsDate());
//            }
//            if (staffWorkArea.getCreateDate() != null) {
//                ps.setDate(index++, staffWorkArea.getCreateDate());
//            }
        	connection = ConnectionFactory.getConnection();
        	ps = connection.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, StaffWorkAreaMVO.class);
        } catch (SQLException e) {
            throw new SysException("222", "findStaffWorkArea", e);
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
    /**
	 *<p>Description:根据工区Id得到该工区下所有员工Id和name</p>
	 * @author 黄永欣 2008-4-7
	 * @param workAreaId
	 * @return
	 */
	public List findLabeBeanByStaffWorkArea(StaffWorkAreaMVO mvo) throws SysException, AppException {
		 List results = null;
	        StaffWorkAreaMVO staffWorkArea = (StaffWorkAreaMVO) mvo;
	        Sql sql = new Sql();
	        sql.append("select distinct b.staff_id, concat(c.name,concat('(',concat(sss.sys_user_name, ')'))) staffname ");
	        sql.append(" from STAFF_WORK_AREA a, staff b, party c, work_area d, sys_user sss where 1=1 ");
	        sql.append("and a.staff_id=b.staff_id and b.party_id=c.party_id and a.work_area_id=d.work_area_id and sss.sys_user_id = b.staff_id and d.sts='A' ");
	        sql.append("and a.sts='A' and b.sts='A' and c.sts='A'");
	   
	        if (staffWorkArea.getWorkAreaId() != null) {
	            sql.append(" and a.WORK_AREA_ID=:workAreaId ");
	            sql.setString("workAreaId", staffWorkArea.getWorkAreaId());
	        }
	        if (staffWorkArea.getStaffId() != null) {
	            sql.append(" and a.STAFF_ID=:staffId ");
	            sql.setString("staffId", staffWorkArea.getStaffId());
	        }
	        
	        sql.append(" order by staffname");
	       
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        LabelValueBean lvBean = null;
	        results=(List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
	        try {

	        	connection = ConnectionFactory.getConnection();
	        	ps = connection.prepareStatement(sql.getSql());
				sql.fillParams(ps);
				sql.log(this.getClass());
	            rs = ps.executeQuery();
	            while (rs.next()) {
					lvBean = new LabelValueBean();
					lvBean.setLabel(rs.getString("staffname"));
					lvBean.setValue(rs.getString("staff_id"));
					results.add(lvBean);
				}
	        } catch (SQLException e) {
	            throw new SysException("222", "findStaffWorkArea", e);
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
