package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IOrgDeptSDAO;
import com.cattsoft.sm.vo.DevelopMVO;
import com.cattsoft.sm.vo.OrgDeptSVO;

public class OrgDeptSDAOImpl implements IOrgDeptSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		OrgDeptSVO orgDept = (OrgDeptSVO) vo;

		StringBuffer sql = new StringBuffer("insert into");
		sql
				.append(" ORG_DEPT(DEPT_ID,DEPT_NAME,AREA_ID,PARENT_DEPT_ID,LOCAL_NET_ID,SERV_DEPT_ID,ADMIN_STAFF_ID,DEPT_TYPE,ROOT_FLAG,STS,STS_DATE,CREATE_DATE,DEPT_DESC,ADDRESS,POST_NBR,BRANCH_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, orgDept.getDeptId());
			ps.setString(2, orgDept.getDeptName());
			ps.setString(3, orgDept.getAreaId());
			ps.setString(4, orgDept.getParentDeptId());
			ps.setString(5, orgDept.getLocalNetId());
			ps.setString(6, orgDept.getServDeptId());
			ps.setString(7, orgDept.getAdminStaffId());
			ps.setString(8, orgDept.getDeptType());
			ps.setString(9, orgDept.getRootFlag());
			ps.setString(10, orgDept.getSts());
			ps.setDate(11, orgDept.getStsDate());
			ps.setDate(12, orgDept.getCreateDate());
			ps.setString(13, orgDept.getDeptDesc());
			ps.setString(14, orgDept.getAddress());
			ps.setString(15, orgDept.getPostNbr());
			ps.setString(16, orgDept.getBranchId());
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
		OrgDeptSVO orgDept = (OrgDeptSVO) vo;
		StringBuffer sql = new StringBuffer("update ORG_DEPT set");
		if (orgDept.getDeptName() != null) {
			sql.append(" DEPT_NAME=?,");
		}
		if (orgDept.getAreaId() != null) {
			sql.append(" AREA_ID=?,");
		}
		if (orgDept.getParentDeptId() != null) {
			sql.append(" PARENT_DEPT_ID=?,");
		}
		if (orgDept.getLocalNetId() != null) {
			sql.append(" LOCAL_NET_ID=?,");
		}
		if (orgDept.getServDeptId() != null) {
			sql.append(" SERV_DEPT_ID=?,");
		}
		if (orgDept.getAdminStaffId() != null) {
			sql.append(" ADMIN_STAFF_ID=?,");
		}
		if (orgDept.getDeptType() != null) {
			sql.append(" DEPT_TYPE=?,");
		}
		if (orgDept.getRootFlag() != null) {
			sql.append(" ROOT_FLAG=?,");
		}
		if (orgDept.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (orgDept.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (orgDept.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		if (orgDept.getDeptDesc() != null) {
			sql.append(" DEPT_DESC=?,");
		}
		if (orgDept.getAddress() != null) {
			sql.append(" ADDRESS=?,");
		}
		if (orgDept.getPostNbr() != null) {
			sql.append(" POST_NBR=?,");
		}
		if (orgDept.getBranchId() != null) {
			sql.append(" BRANCH_ID=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and DEPT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (orgDept.getDeptName() != null) {
				ps.setString(index++, orgDept.getDeptName());
			}
			if (orgDept.getAreaId() != null) {
				ps.setString(index++, orgDept.getAreaId());
			}
			if (orgDept.getParentDeptId() != null) {
				ps.setString(index++, orgDept.getParentDeptId());
			}
			if (orgDept.getLocalNetId() != null) {
				ps.setString(index++, orgDept.getLocalNetId());
			}
			if (orgDept.getServDeptId() != null) {
				ps.setString(index++, orgDept.getServDeptId());
			}
			if (orgDept.getAdminStaffId() != null) {
				ps.setString(index++, orgDept.getAdminStaffId());
			}
			if (orgDept.getDeptType() != null) {
				ps.setString(index++, orgDept.getDeptType());
			}
			if (orgDept.getRootFlag() != null) {
				ps.setString(index++, orgDept.getRootFlag());
			}
			if (orgDept.getSts() != null) {
				ps.setString(index++, orgDept.getSts());
			}
			if (orgDept.getStsDate() != null) {
				ps.setDate(index++, orgDept.getStsDate());
			}
			if (orgDept.getCreateDate() != null) {
				ps.setDate(index++, orgDept.getCreateDate());
			}
			if (orgDept.getDeptDesc() != null) {
				ps.setString(index++, orgDept.getDeptDesc());
			}
			if (orgDept.getAddress() != null) {
				ps.setString(index++, orgDept.getAddress());
			}
			if (orgDept.getPostNbr() != null) {
				ps.setString(index++, orgDept.getPostNbr());
			}
			if (orgDept.getBranchId() != null) {
				ps.setString(index++, orgDept.getBranchId());
			}
			ps.setString(index++, orgDept.getDeptId());
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
		OrgDeptSVO orgDept = (OrgDeptSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from ORG_DEPT where 1=1");
		sql.append(" and DEPT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, orgDept.getDeptId());
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
		OrgDeptSVO result = null;
		OrgDeptSVO orgDept = (OrgDeptSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql
				.append(" a.DEPT_ID,a.DEPT_NAME,a.AREA_ID,a.PARENT_DEPT_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.ADMIN_STAFF_ID,a.DEPT_TYPE,a.ROOT_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE,a.DEPT_DESC,a.ADDRESS,a.POST_NBR,a.BRANCH_ID");
		sql.append(" from ORG_DEPT a where 1=1");
		sql.append(" and DEPT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, orgDept.getDeptId());
			rs = ps.executeQuery();
			result = (OrgDeptSVO) ResultSetUtil.convertToVo(rs,
					OrgDeptSVO.class);
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
		OrgDeptSVO orgDept = (OrgDeptSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql
				.append(" a.DEPT_ID,a.DEPT_NAME,a.AREA_ID,a.PARENT_DEPT_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.ADMIN_STAFF_ID,a.DEPT_TYPE,a.ROOT_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE,a.DEPT_DESC,a.ADDRESS,a.POST_NBR,a.BRANCH_ID");
		sql.append(" from ORG_DEPT a where 1=1");
		if (orgDept.getDeptId() != null) {
			sql.append(" and DEPT_ID=?");
		}
		if (orgDept.getDeptName() != null) {
			sql.append(" and DEPT_NAME=?");
		}
		if (orgDept.getAreaId() != null) {
			sql.append(" and AREA_ID=?");
		}
		if (orgDept.getParentDeptId() != null) {
			sql.append(" and PARENT_DEPT_ID=?");
		}
		if (orgDept.getLocalNetId() != null) {
			sql.append(" and LOCAL_NET_ID=?");
		}
		if (orgDept.getServDeptId() != null) {
			sql.append(" and SERV_DEPT_ID=?");
		}
		if (orgDept.getAdminStaffId() != null) {
			sql.append(" and ADMIN_STAFF_ID=?");
		}
		if (orgDept.getDeptType() != null) {
			sql.append(" and DEPT_TYPE=?");
		}
		if (orgDept.getRootFlag() != null) {
			sql.append(" and ROOT_FLAG=?");
		}
		if (orgDept.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (orgDept.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (orgDept.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (orgDept.getDeptDesc() != null) {
			sql.append(" and DEPT_DESC=?");
		}
		if (orgDept.getAddress() != null) {
			sql.append(" and ADDRESS=?");
		}
		if (orgDept.getPostNbr() != null) {
			sql.append(" and POST_NBR=?");
		}
		if (orgDept.getBranchId() != null) {
			sql.append(" and BRANCH_ID=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (orgDept.getDeptId() != null) {
				ps.setString(index++, orgDept.getDeptId());
			}
			if (orgDept.getDeptName() != null) {
				ps.setString(index++, orgDept.getDeptName());
			}
			if (orgDept.getAreaId() != null) {
				ps.setString(index++, orgDept.getAreaId());
			}
			if (orgDept.getParentDeptId() != null) {
				ps.setString(index++, orgDept.getParentDeptId());
			}
			if (orgDept.getLocalNetId() != null) {
				ps.setString(index++, orgDept.getLocalNetId());
			}
			if (orgDept.getServDeptId() != null) {
				ps.setString(index++, orgDept.getServDeptId());
			}
			if (orgDept.getAdminStaffId() != null) {
				ps.setString(index++, orgDept.getAdminStaffId());
			}
			if (orgDept.getDeptType() != null) {
				ps.setString(index++, orgDept.getDeptType());
			}
			if (orgDept.getRootFlag() != null) {
				ps.setString(index++, orgDept.getRootFlag());
			}
			if (orgDept.getSts() != null) {
				ps.setString(index++, orgDept.getSts());
			}
			if (orgDept.getStsDate() != null) {
				ps.setDate(index++, orgDept.getStsDate());
			}
			if (orgDept.getCreateDate() != null) {
				ps.setDate(index++, orgDept.getCreateDate());
			}
			if (orgDept.getDeptDesc() != null) {
				ps.setString(index++, orgDept.getDeptDesc());
			}
			if (orgDept.getAddress() != null) {
				ps.setString(index++, orgDept.getAddress());
			}
			if (orgDept.getPostNbr() != null) {
				ps.setString(index++, orgDept.getPostNbr());
			}
			if (orgDept.getBranchId() != null) {
				ps.setString(index++, orgDept.getBranchId());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, OrgDeptSVO.class);
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

	public List findOrgDeptByTree(GenericVO vo) throws AppException,
			SysException {
		List results = null;
		OrgDeptSVO orgDept = (OrgDeptSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql
				.append(" a.DEPT_ID,a.DEPT_NAME,a.AREA_ID,a.PARENT_DEPT_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.ADMIN_STAFF_ID,a.DEPT_TYPE,a.ROOT_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE,a.DEPT_DESC,a.ADDRESS,a.POST_NBR,a.BRANCH_ID");
		sql.append(" from ORG_DEPT a where 1=1");
		if (orgDept.getDeptId() != null) {
			sql.append(" and DEPT_ID=?");
		}
		if (orgDept.getDeptName() != null) {
			sql.append(" and DEPT_NAME=?");
		}
		if (orgDept.getAreaId() != null) {
			sql.append(" and ( AREA_ID=? or area_id=0) ");
			// sql.append(" and ( AREA_ID=? ) ");
		}
		if (orgDept.getParentDeptId() != null) {
			sql.append(" and PARENT_DEPT_ID=?");
		}
		if (orgDept.getLocalNetId() != null) {
			sql.append(" and (LOCAL_NET_ID=? or local_net_id=0)");
			// mod by peiyy 2009-11-24由于山西所有部门都挂在全省的根节点下面，因此要查询出本地网为0的数据
			// sql.append(" and (LOCAL_NET_ID=? )");
		}
		if (orgDept.getServDeptId() != null) {
			sql.append(" and SERV_DEPT_ID=?");
		}
		if (orgDept.getAdminStaffId() != null) {
			sql.append(" and ADMIN_STAFF_ID=?");
		}
		if (orgDept.getDeptType() != null) {
			sql.append(" and DEPT_TYPE in (?)");
		}
		if (orgDept.getRootFlag() != null) {
			sql.append(" and ROOT_FLAG=?");
		}
		if (orgDept.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (orgDept.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (orgDept.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (orgDept.getDeptDesc() != null) {
			sql.append(" and DEPT_DESC=?");
		}
		if (orgDept.getAddress() != null) {
			sql.append(" and ADDRESS=?");
		}
		if (orgDept.getPostNbr() != null) {
			sql.append(" and POST_NBR=?");
		}
		if (orgDept.getBranchId() != null) {
			sql.append(" and BRANCH_ID=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			// System.out.println(sql.toString());
			int index = 1;
			if (orgDept.getDeptId() != null) {
				ps.setString(index++, orgDept.getDeptId());
			}
			if (orgDept.getDeptName() != null) {
				ps.setString(index++, orgDept.getDeptName());
			}
			if (orgDept.getAreaId() != null) {
				ps.setString(index++, orgDept.getAreaId());
			}
			if (orgDept.getParentDeptId() != null) {
				ps.setString(index++, orgDept.getParentDeptId());
			}
			if (orgDept.getLocalNetId() != null) {
				ps.setString(index++, orgDept.getLocalNetId());
			}
			if (orgDept.getServDeptId() != null) {
				ps.setString(index++, orgDept.getServDeptId());
			}
			if (orgDept.getAdminStaffId() != null) {
				ps.setString(index++, orgDept.getAdminStaffId());
			}
			if (orgDept.getDeptType() != null) {
				ps.setString(index++, orgDept.getDeptType());
			}
			if (orgDept.getRootFlag() != null) {
				ps.setString(index++, orgDept.getRootFlag());
			}
			if (orgDept.getSts() != null) {
				ps.setString(index++, orgDept.getSts());
			}
			if (orgDept.getStsDate() != null) {
				ps.setDate(index++, orgDept.getStsDate());
			}
			if (orgDept.getCreateDate() != null) {
				ps.setDate(index++, orgDept.getCreateDate());
			}
			if (orgDept.getDeptDesc() != null) {
				ps.setString(index++, orgDept.getDeptDesc());
			}
			if (orgDept.getAddress() != null) {
				ps.setString(index++, orgDept.getAddress());
			}
			if (orgDept.getPostNbr() != null) {
				ps.setString(index++, orgDept.getPostNbr());
			}
			if (orgDept.getBranchId() != null) {
				ps.setString(index++, orgDept.getBranchId());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, OrgDeptSVO.class);
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

	public List findByDevelopMVO(GenericVO vo) throws AppException,
			SysException {
		List results = (List) CollectionFactory
				.createCollection(CollectionFactory.COLLECTION_LIST);
		DevelopMVO orgDept = (DevelopMVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql
				.append(" a.DEPT_ID,a.DEPT_NAME,a.AREA_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.BRANCH_ID");
		sql.append(" from ORG_DEPT a where 1=1");

		if (orgDept.getAreaId() != null) {
			sql.append(" and a.AREA_ID=?");
		}
		if (orgDept.getLocalNetId() != null) {
			sql.append(" and a.LOCAL_NET_ID=?");
		}
		if (orgDept.getServDeptId() != null) {
			sql.append(" and0 a.SERV_DEPT_ID=?");
		}

		if (orgDept.getBranchId() != null) {
			sql.append(" and a.BRANCH_ID=?");
		}
		sql.append("and a.sts='A' ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (orgDept.getAreaId() != null) {
				ps.setString(index++, orgDept.getAreaId());
			}
			if (orgDept.getLocalNetId() != null) {
				ps.setString(index++, orgDept.getLocalNetId());
			}
			if (orgDept.getServDeptId() != null) {
				ps.setString(index++, orgDept.getServDeptId());
			}
			if (orgDept.getBranchId() != null) {
				ps.setString(index++, orgDept.getBranchId());
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				DevelopMVO mvo = new DevelopMVO();
				mvo.setDevelopId(rs.getString("dept_id"));
				mvo.setName(rs.getString("dept_name"));
				mvo.setLocalNetId(rs.getString("local_net_id"));
				mvo.setAreaId(rs.getString("area_id"));
				mvo.setServDeptId(rs.getString("serv_dept_id"));
				mvo.setBranchId(rs.getString("branch_id"));
				mvo.setPartyRoleType("2");
				results.add(mvo);
			}
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

	public List findByLabelValueBean(GenericVO vo) throws AppException,
			SysException {
		List results = (List) CollectionFactory
				.createCollection(CollectionFactory.COLLECTION_LIST);
		DevelopMVO orgDept = (DevelopMVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DEPT_ID,a.DEPT_NAME ");
		sql.append(" from ORG_DEPT a where 1=1");

		if (orgDept.getAreaId() != null) {
			sql.append(" and a.AREA_ID=?");
		}
		if (orgDept.getLocalNetId() != null) {
			sql.append(" and a.LOCAL_NET_ID=?");
		}
		if (orgDept.getServDeptId() != null) {
			sql.append(" and0 a.SERV_DEPT_ID=?");
		}

		if (orgDept.getBranchId() != null) {
			sql.append(" and a.BRANCH_ID=?");
		}
		sql.append("and a.sts='A' ");
		sql.append(" order by a.dept_id ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (orgDept.getAreaId() != null) {
				ps.setString(index++, orgDept.getAreaId());
			}
			if (orgDept.getLocalNetId() != null) {
				ps.setString(index++, orgDept.getLocalNetId());
			}
			if (orgDept.getServDeptId() != null) {
				ps.setString(index++, orgDept.getServDeptId());
			}
			if (orgDept.getBranchId() != null) {
				ps.setString(index++, orgDept.getBranchId());
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				LabelValueBean lvo = new LabelValueBean();
				lvo.setValue(rs.getString("dept_id"));
				lvo.setLabel(rs.getString("dept_name"));
				results.add(lvo);
			}
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

	public List findIdByVO(OrgDeptSVO vo) throws AppException, SysException {
		List results = (List) CollectionFactory
				.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DEPT_ID");
		sql.append(" from ORG_DEPT a where 1=1");

		if (vo.getParentDeptId() != null) {
			sql.append(" and PARENT_DEPT_ID=?");
		}

		if (vo.getSts() != null) {
			sql.append(" and STS=?");
		}

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;

			if (vo.getParentDeptId() != null) {
				ps.setString(index++, vo.getParentDeptId());
			}

			if (vo.getSts() != null) {
				ps.setString(index++, vo.getSts());
			}

			rs = ps.executeQuery();
			OrgDeptSVO svo = null;
			while (rs.next()) {
				svo = new OrgDeptSVO();
				svo.setDeptId(rs.getString("dept_id"));
				results.add(svo);
			}
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

	public List findByMap(GenericVO vo) throws AppException, SysException {
		Map map = (Map) CollectionFactory
				.createMap(CollectionFactory.COLLECTION_MAP);
		Map hm = (Map) CollectionFactory
				.createMap(CollectionFactory.COLLECTION_MAP);
		List lst = (List) CollectionFactory
				.createCollection(CollectionFactory.COLLECTION_LIST);
		OrgDeptSVO orgDept = (OrgDeptSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql
				.append(" a.DEPT_ID,a.DEPT_NAME,a.AREA_ID,a.PARENT_DEPT_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.ADMIN_STAFF_ID,a.DEPT_TYPE,a.ROOT_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE,a.DEPT_DESC,a.ADDRESS,a.POST_NBR,a.BRANCH_ID");
		sql.append(" from ORG_DEPT a where 1=1");
		if (orgDept.getDeptId() != null) {
			sql.append(" and DEPT_ID=?");
		}
		if (orgDept.getDeptName() != null) {
			sql.append(" and DEPT_NAME=?");
		}
		if (orgDept.getAreaId() != null) {
			sql.append(" and AREA_ID=?");
		}
		if (orgDept.getParentDeptId() != null) {
			sql.append(" and PARENT_DEPT_ID=?");
		}
		if (orgDept.getLocalNetId() != null) {
			sql.append(" and LOCAL_NET_ID=?");
		}
		if (orgDept.getServDeptId() != null) {
			sql.append(" and SERV_DEPT_ID=?");
		}
		if (orgDept.getAdminStaffId() != null) {
			sql.append(" and ADMIN_STAFF_ID=?");
		}
		if (orgDept.getDeptType() != null) {
			sql.append(" and DEPT_TYPE=?");
		}
		if (orgDept.getRootFlag() != null) {
			sql.append(" and ROOT_FLAG=?");
		}
		if (orgDept.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (orgDept.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (orgDept.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (orgDept.getDeptDesc() != null) {
			sql.append(" and DEPT_DESC=?");
		}
		if (orgDept.getAddress() != null) {
			sql.append(" and ADDRESS=?");
		}
		if (orgDept.getPostNbr() != null) {
			sql.append(" and POST_NBR=?");
		}
		if (orgDept.getBranchId() != null) {
			sql.append(" and BRANCH_ID=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (orgDept.getDeptId() != null) {
				ps.setString(index++, orgDept.getDeptId());
			}
			if (orgDept.getDeptName() != null) {
				ps.setString(index++, orgDept.getDeptName());
			}
			if (orgDept.getAreaId() != null) {
				ps.setString(index++, orgDept.getAreaId());
			}
			if (orgDept.getParentDeptId() != null) {
				ps.setString(index++, orgDept.getParentDeptId());
			}
			if (orgDept.getLocalNetId() != null) {
				ps.setString(index++, orgDept.getLocalNetId());
			}
			if (orgDept.getServDeptId() != null) {
				ps.setString(index++, orgDept.getServDeptId());
			}
			if (orgDept.getAdminStaffId() != null) {
				ps.setString(index++, orgDept.getAdminStaffId());
			}
			if (orgDept.getDeptType() != null) {
				ps.setString(index++, orgDept.getDeptType());
			}
			if (orgDept.getRootFlag() != null) {
				ps.setString(index++, orgDept.getRootFlag());
			}
			if (orgDept.getSts() != null) {
				ps.setString(index++, orgDept.getSts());
			}
			if (orgDept.getStsDate() != null) {
				ps.setDate(index++, orgDept.getStsDate());
			}
			if (orgDept.getCreateDate() != null) {
				ps.setDate(index++, orgDept.getCreateDate());
			}
			if (orgDept.getDeptDesc() != null) {
				ps.setString(index++, orgDept.getDeptDesc());
			}
			if (orgDept.getAddress() != null) {
				ps.setString(index++, orgDept.getAddress());
			}
			if (orgDept.getPostNbr() != null) {
				ps.setString(index++, orgDept.getPostNbr());
			}
			if (orgDept.getBranchId() != null) {
				ps.setString(index++, orgDept.getBranchId());
			}
			rs = ps.executeQuery();
			OrgDeptSVO ovo = null;
			List list = null;
			while (rs.next()) {
				ovo = new OrgDeptSVO();
				ovo.setDeptId(rs.getString("dept_id"));
				ovo.setDeptName(rs.getString("dept_name"));
				ovo.setParentDeptId(rs.getString("parent_dept_id"));
				ovo.setDeptType(rs.getString("dept_type"));
				map.put(ovo.getDeptId(), ovo);
				list = (List) hm.get(ovo.getParentDeptId());
				if (list == null || list.size() == 0) {
					list = (List) CollectionFactory
							.createCollection(CollectionFactory.COLLECTION_LIST);
				}
				list.add(ovo.getDeptId());
				hm.put(ovo.getParentDeptId(), list);
			}
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
		lst.add(0, map);
		lst.add(1, hm);
		return lst;
	}

	public List findOrgDeptByDeptTypeTree(OrgDeptSVO vo) throws AppException,
			SysException {
		OrgDeptSVO orgDept = (OrgDeptSVO) vo;
		List results = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT a.DEPT_ID,a.DEPT_NAME,a.AREA_ID,a.PARENT_DEPT_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.ADMIN_STAFF_ID,a.DEPT_TYPE,a.ROOT_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE,a.DEPT_DESC,a.ADDRESS,a.POST_NBR,a.BRANCH_ID");
		sql.append(" from ORG_DEPT a where 1=1");

		try {
			if (orgDept.getDeptId() != null) {
				sql.append(" and DEPT_ID= :deptId");
				sql.setString("deptId", orgDept.getDeptId());
			}
			if (orgDept.getDeptName() != null) {
				sql.append(" and DEPT_NAME=:deptName");
				sql.setString("deptName", orgDept.getDeptName());
			}
			if (orgDept.getAreaId() != null) {
				sql.append(" and ( AREA_ID=:areaId or area_id=0) ");
				sql.setString("areaId", orgDept.getAreaId());
			}
			if (orgDept.getParentDeptId() != null) {
				sql.append(" and PARENT_DEPT_ID=:parentDeptId");
				sql.setString("parentDeptId", orgDept.getParentDeptId());
			}
			if (orgDept.getLocalNetId() != null) {
				sql.append(" and (LOCAL_NET_ID=:localNetId or local_net_id=0)");
				// mod by peiyy 2009-11-24由于山西所有部门都挂在全省的根节点下面，因此要查询出本地网为0的数据
				// sql.append(" and (LOCAL_NET_ID=? )");
				sql.setString("localNetId", orgDept.getLocalNetId());
			}
			if (orgDept.getServDeptId() != null) {
				sql.append(" and SERV_DEPT_ID=:servDeptId");
				sql.setString("servDeptId", orgDept.getServDeptId());
			}
			if (orgDept.getAdminStaffId() != null) {
				sql.append(" and ADMIN_STAFF_ID=:adminStaffId");
				sql.setString("adminStaffId", orgDept.getAdminStaffId());
			}
			if (orgDept.getDeptType() != null) {
				sql.append(" and DEPT_TYPE in ('" + orgDept.getDeptType() + "')");
			}
			if (orgDept.getRootFlag() != null) {
				sql.append(" and ROOT_FLAG=:rootFlag");
				sql.setString("rootFlag", orgDept.getRootFlag());
			}
			if (orgDept.getSts() != null) {
				sql.append(" and STS=:sts");
				sql.setString("sts", orgDept.getSts());
			}
			if (orgDept.getStsDate() != null) {
				sql.append(" and STS_DATE=:stsDate");
				sql.setTimestamp("stsDate", orgDept.getStsDate());
			}
			if (orgDept.getCreateDate() != null) {
				sql.append(" and CREATE_DATE=:createDate");
				sql.setTimestamp("createDate", orgDept.getCreateDate());
			}
			if (orgDept.getDeptDesc() != null) {
				sql.append(" and DEPT_DESC=:deptDesc");
				sql.setString("deptDesc", orgDept.getDeptDesc());
			}
			if (orgDept.getAddress() != null) {
				sql.append(" and ADDRESS=:address");
				sql.setString("address", orgDept.getAddress());
			}
			if (orgDept.getPostNbr() != null) {
				sql.append(" and POST_NBR=:postNbr");
				sql.setString("postNbr", orgDept.getPostNbr());
			}
			if (orgDept.getBranchId() != null) {
				sql.append(" and BRANCH_ID=:branchId");
				sql.setString("branchId", orgDept.getBranchId());
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				orgDept = new OrgDeptSVO();
				orgDept.setDeptId(rs.getString("DEPT_ID"));
				orgDept.setDeptName(rs.getString("DEPT_NAME"));
				orgDept.setAreaId(rs.getString("AREA_ID"));
				orgDept.setParentDeptId(rs.getString("PARENT_DEPT_ID"));
				orgDept.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				orgDept.setServDeptId(rs.getString("SERV_DEPT_ID"));
				orgDept.setAdminStaffId(rs.getString("ADMIN_STAFF_ID"));
				orgDept.setDeptType(rs.getString("DEPT_TYPE"));
				orgDept.setRootFlag(rs.getString("ROOT_FLAG"));
				orgDept.setSts(rs.getString("STS"));
				orgDept.setStsDate(rs.getDate("STS_DATE"));
				orgDept.setCreateDate(rs.getDate("CREATE_DATE"));
				orgDept.setDeptDesc(rs.getString("DEPT_DESC"));
				orgDept.setAddress(rs.getString("ADDRESS"));
				orgDept.setPostNbr(rs.getString("POST_NBR"));
				orgDept.setBranchId(rs.getString("BRANCH_ID"));
				results.add(orgDept);
			}
		} catch (SQLException e) {
			throw new SysException("100003", "JDBC操作异常！", e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return results;
	}

}
