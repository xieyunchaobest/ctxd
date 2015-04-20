package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.sm.component.dao.IStaffSDAO;
import com.cattsoft.sm.vo.StaffSVO;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.pub.util.StringUtil;

/**
 * 方法StaffSDAOImpl
 * <p>Company: 大唐软件</p>
 * @author ：白小亮。
 * @version 1.1  2007-9-23
 */
public class StaffSDAOImpl implements IStaffSDAO {
	private static Logger log = Logger.getLogger(StaffSDAOImpl.class);
	private static final String UNABLE_STS = "P";

	/**
	 * 增加。
	 * @return String
	 */
	public void add(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		StaffSVO staff = (StaffSVO) vo;
		if (StringUtil.isBlank(staff.getStaffId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO STAFF(COMPANY_CODE,CREATE_DATE,DEPT_ID,DEPT_TYPE,LOCAL_NET_ID,PARTY_ID,POSITION,SIM_PASSWORD,SIM_SYS_USER_NAME,STAFF_ID,STANDARD_CODE,STS,STS_DATE,TEL_NBR,TERMINAL_FLAG)");
		sql
				.append(" VALUES (:companyCode,:createDate,:deptId,:deptType,:localNetId,:partyId,:position,:simPassword,:simSysUserName,:staffId,:standardCode,:sts,:stsDate,:telNbr,:terminalFlag)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			if (StringUtil.isBlank(staff.getCompanyCode())) {
				sql.setNullLong("companyCode");
			} else {
				sql.setLong("companyCode", staff.getCompanyCode());
			}

			if (staff.getCreateDate() == null) {
				sql.setNullDate("createDate");
			} else {
				sql.setTimestamp("createDate", staff.getCreateDate());
			}

			if (StringUtil.isBlank(staff.getDeptId())) {
				sql.setNullString("deptId");
			} else {
				sql.setString("deptId", staff.getDeptId());
			}

			if (StringUtil.isBlank(staff.getDeptType())) {
				sql.setNullString("deptType");
			} else {
				sql.setString("deptType", staff.getDeptType());
			}

			if (StringUtil.isBlank(staff.getLocalNetId())) {
				sql.setNullLong("localNetId");
			} else {
				sql.setLong("localNetId", staff.getLocalNetId());
			}

			if (StringUtil.isBlank(staff.getPartyId())) {
				sql.setNullLong("partyId");
			} else {
				sql.setLong("partyId", staff.getPartyId());
			}

			if (StringUtil.isBlank(staff.getPosition())) {
				sql.setNullString("position");
			} else {
				sql.setString("position", staff.getPosition());
			}

			if (StringUtil.isBlank(staff.getSimPassword())) {
				sql.setNullString("simPassword");
			} else {
				sql.setString("simPassword", staff.getSimPassword());
			}

			if (StringUtil.isBlank(staff.getSimSysUserName())) {
				sql.setNullString("simSysUserName");
			} else {
				sql.setString("simSysUserName", staff.getSimSysUserName());
			}

			if (StringUtil.isBlank(staff.getStaffId())) {
				sql.setNullString("staffId");
			} else {
				sql.setString("staffId", staff.getStaffId());
			}

			if (StringUtil.isBlank(staff.getStandardCode())) {
				sql.setNullString("standardCode");
			} else {
				sql.setString("standardCode", staff.getStandardCode());
			}

			if (StringUtil.isBlank(staff.getSts())) {
				sql.setNullString("sts");
			} else {
				sql.setString("sts", staff.getSts());
			}

			if (staff.getStsDate() == null) {
				sql.setNullDate("stsDate");
			} else {
				sql.setTimestamp("stsDate", staff.getStsDate());
			}

			if (StringUtil.isBlank(staff.getTelNbr())) {
				sql.setNullString("telNbr");
			} else {
				sql.setString("telNbr", staff.getTelNbr());
			}

			if (StringUtil.isBlank(staff.getTerminalFlag())) {
				sql.setNullString("terminalFlag");
			} else {
				sql.setString("terminalFlag", staff.getTerminalFlag());
			}

			sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * 主键查询的SQL。
	 * @return String ： 主键查询的SQL。
	 */
	public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		StaffSVO staff = (StaffSVO) vo;
		if (StringUtil.isBlank(staff.getStaffId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Sql sql = new Sql(
				"SELECT COMPANY_CODE,CREATE_DATE,DEPT_ID,DEPT_TYPE,LOCAL_NET_ID,PARTY_ID,POSITION,SIM_PASSWORD,SIM_SYS_USER_NAME,STAFF_ID,STANDARD_CODE,STS,STS_DATE,TEL_NBR,TERMINAL_FLAG FROM STAFF WHERE 1=1  ");
		sql.append(" and STAFF_ID=:staffId");
		sql.setString("staffId", staff.getStaffId());

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		staff = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				staff = new StaffSVO();
				staff.setCompanyCode(rs.getString("COMPANY_CODE"));
				staff.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				staff.setDeptId(rs.getString("DEPT_ID"));
				staff.setDeptType(rs.getString("DEPT_TYPE"));
				staff.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				staff.setPartyId(rs.getString("PARTY_ID"));
				staff.setPosition(rs.getString("POSITION"));
				staff.setSimPassword(rs.getString("SIM_PASSWORD"));
				staff.setSimSysUserName(rs.getString("SIM_SYS_USER_NAME"));
				staff.setStaffId(rs.getString("STAFF_ID"));
				staff.setStandardCode(rs.getString("STANDARD_CODE"));
				staff.setSts(rs.getString("STS"));
				staff.setStsDate(rs.getTimestamp("STS_DATE"));
				staff.setTelNbr(rs.getString("TEL_NBR"));
				staff.setTerminalFlag(rs.getString("TERMINAL_FLAG"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return staff;
	}

	/**
	 * 批量查询的SQL。
	 * @return String ： 批量查询的SQL。
	 */
	public List findByVO(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		StaffSVO staff = (StaffSVO) vo;
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT COMPANY_CODE,CREATE_DATE,DEPT_ID,DEPT_TYPE,LOCAL_NET_ID,PARTY_ID,POSITION,SIM_PASSWORD,SIM_SYS_USER_NAME,STAFF_ID,STANDARD_CODE,STS,STS_DATE,TEL_NBR,TERMINAL_FLAG FROM STAFF WHERE 1=1 ");
		try {
			if (staff.getFlagCompanyCode() == 1) {
				if (StringUtil.isBlank(staff.getCompanyCode())) {
					sql.append(" and COMPANY_CODE is null ");
				} else {
					sql.append(" and COMPANY_CODE=:companyCode");
					sql.setLong("companyCode", staff.getCompanyCode());
				}
			}

			if (staff.getFlagCreateDate() == 1) {
				if (staff.getCreateDate() == null) {
					sql.append(" and CREATE_DATE is null ");
				} else {
					sql.append(" and CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", staff.getCreateDate());
				}
			}

			if (staff.getFlagDeptId() == 1) {
				if (StringUtil.isBlank(staff.getDeptId())) {
					sql.append(" and DEPT_ID is null ");
				} else {
					sql.append(" and DEPT_ID=:deptId");
					sql.setString("deptId", staff.getDeptId());
				}
			}

			if (staff.getFlagDeptType() == 1) {
				if (StringUtil.isBlank(staff.getDeptType())) {
					sql.append(" and DEPT_TYPE is null ");
				} else {
					sql.append(" and DEPT_TYPE=:deptType");
					sql.setString("deptType", staff.getDeptType());
				}
			}

			if (staff.getFlagLocalNetId() == 1) {
				if (StringUtil.isBlank(staff.getLocalNetId())) {
					sql.append(" and LOCAL_NET_ID is null ");
				} else {
					sql.append(" and LOCAL_NET_ID=:localNetId");
					sql.setLong("localNetId", staff.getLocalNetId());
				}
			}

			if (staff.getFlagPartyId() == 1) {
				if (StringUtil.isBlank(staff.getPartyId())) {
					sql.append(" and PARTY_ID is null ");
				} else {
					sql.append(" and PARTY_ID=:partyId");
					sql.setLong("partyId", staff.getPartyId());
				}
			}

			if (staff.getFlagPosition() == 1) {
				if (StringUtil.isBlank(staff.getPosition())) {
					sql.append(" and POSITION is null ");
				} else {
					sql.append(" and POSITION=:position");
					sql.setString("position", staff.getPosition());
				}
			}

			if (staff.getFlagSimPassword() == 1) {
				if (StringUtil.isBlank(staff.getSimPassword())) {
					sql.append(" and SIM_PASSWORD is null ");
				} else {
					sql.append(" and SIM_PASSWORD=:simPassword");
					sql.setString("simPassword", staff.getSimPassword());
				}
			}

			if (staff.getFlagSimSysUserName() == 1) {
				if (StringUtil.isBlank(staff.getSimSysUserName())) {
					sql.append(" and SIM_SYS_USER_NAME is null ");
				} else {
					sql.append(" and SIM_SYS_USER_NAME=:simSysUserName");
					sql.setString("simSysUserName", staff.getSimSysUserName());
				}
			}

			if (staff.getFlagStaffId() == 1) {
				if (StringUtil.isBlank(staff.getStaffId())) {
					sql.append(" and STAFF_ID is null ");
				} else {
					sql.append(" and STAFF_ID=:staffId");
					sql.setString("staffId", staff.getStaffId());
				}
			}

			if (staff.getFlagStandardCode() == 1) {
				if (StringUtil.isBlank(staff.getStandardCode())) {
					sql.append(" and STANDARD_CODE is null ");
				} else {
					sql.append(" and STANDARD_CODE=:standardCode");
					sql.setString("standardCode", staff.getStandardCode());
				}
			}

			if (staff.getFlagSts() == 1) {
				if (StringUtil.isBlank(staff.getSts())) {
					sql.append(" and STS is null ");
				} else {
					sql.append(" and STS=:sts");
					sql.setString("sts", staff.getSts());
				}
			}

			if (staff.getFlagStsDate() == 1) {
				if (staff.getStsDate() == null) {
					sql.append(" and STS_DATE is null ");
				} else {
					sql.append(" and STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", staff.getStsDate());
				}
			}

			if (staff.getFlagTelNbr() == 1) {
				if (StringUtil.isBlank(staff.getTelNbr())) {
					sql.append(" and TEL_NBR is null ");
				} else {
					sql.append(" and TEL_NBR=:telNbr");
					sql.setString("telNbr", staff.getTelNbr());
				}
			}

			if (staff.getFlagTerminalFlag() == 1) {
				if (StringUtil.isBlank(staff.getTerminalFlag())) {
					sql.append(" and TERMINAL_FLAG is null ");
				} else {
					sql.append(" and TERMINAL_FLAG=:terminalFlag");
					sql.setString("terminalFlag", staff.getTerminalFlag());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				staff = new StaffSVO();
				staff.setCompanyCode(rs.getString("COMPANY_CODE"));
				staff.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				staff.setDeptId(rs.getString("DEPT_ID"));
				staff.setDeptType(rs.getString("DEPT_TYPE"));
				staff.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				staff.setPartyId(rs.getString("PARTY_ID"));
				staff.setPosition(rs.getString("POSITION"));
				staff.setSimPassword(rs.getString("SIM_PASSWORD"));
				staff.setSimSysUserName(rs.getString("SIM_SYS_USER_NAME"));
				staff.setStaffId(rs.getString("STAFF_ID"));
				staff.setStandardCode(rs.getString("STANDARD_CODE"));
				staff.setSts(rs.getString("STS"));
				staff.setStsDate(rs.getTimestamp("STS_DATE"));
				staff.setTelNbr(rs.getString("TEL_NBR"));
				staff.setTerminalFlag(rs.getString("TERMINAL_FLAG"));
				res.add(staff);

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

	/**
	 * 更新的SQL。
	 * @return String ： 更新的SQL。
	 */
	public void update(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		StaffSVO staff = (StaffSVO) vo;
		if (StringUtil.isBlank(staff.getStaffId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("UPDATE STAFF SET ");
		try {
			if (staff.getFlagCompanyCode() == 1) {
				sql.append("COMPANY_CODE=:companyCode,");
				sql.setLong("companyCode", staff.getCompanyCode());
			}

			if (staff.getFlagCreateDate() == 1) {
				sql.append("CREATE_DATE=:createDate,");
				sql.setTimestamp("createDate", staff.getCreateDate());
			}

			if (staff.getFlagDeptId() == 1) {
				sql.append("DEPT_ID=:deptId,");
				sql.setString("deptId", staff.getDeptId());
			}

			if (staff.getFlagDeptType() == 1) {
				sql.append("DEPT_TYPE=:deptType,");
				sql.setString("deptType", staff.getDeptType());
			}

			if (staff.getFlagLocalNetId() == 1) {
				sql.append("LOCAL_NET_ID=:localNetId,");
				sql.setLong("localNetId", staff.getLocalNetId());
			}

			if (staff.getFlagPartyId() == 1) {
				sql.append("PARTY_ID=:partyId,");
				sql.setLong("partyId", staff.getPartyId());
			}

			if (staff.getFlagPosition() == 1) {
				sql.append("POSITION=:position,");
				sql.setString("position", staff.getPosition());
			}

			if (staff.getFlagSimPassword() == 1) {
				sql.append("SIM_PASSWORD=:simPassword,");
				sql.setString("simPassword", staff.getSimPassword());
			}

			if (staff.getFlagSimSysUserName() == 1) {
				sql.append("SIM_SYS_USER_NAME=:simSysUserName,");
				sql.setString("simSysUserName", staff.getSimSysUserName());
			}

			if (staff.getFlagStandardCode() == 1) {
				sql.append("STANDARD_CODE=:standardCode,");
				sql.setString("standardCode", staff.getStandardCode());
			}

			if (staff.getFlagSts() == 1) {
				sql.append("STS=:sts,");
				sql.setString("sts", staff.getSts());
			}

			if (staff.getFlagStsDate() == 1) {
				sql.append("STS_DATE=:stsDate,");
				sql.setTimestamp("stsDate", staff.getStsDate());
			}

			if (staff.getFlagTelNbr() == 1) {
				sql.append("TEL_NBR=:telNbr,");
				sql.setString("telNbr", staff.getTelNbr());
			}

			if (staff.getFlagTerminalFlag() == 1) {
				sql.append("TERMINAL_FLAG=:terminalFlag,");
				sql.setString("terminalFlag", staff.getTerminalFlag());
			}

			sql.removeSuffix(1);
			sql.append(" WHERE 1=1 ");
			sql.append(" and STAFF_ID=:staffId");
			sql.setString("staffId", staff.getStaffId());

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}

	}

	/**
	 * 批量增加记录。
	 * @return String ： 批量增加的SQL。
	 */
	public void addBat(List list) throws AppException, SysException {
		if (list == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO STAFF(COMPANY_CODE,CREATE_DATE,DEPT_ID,DEPT_TYPE,LOCAL_NET_ID,PARTY_ID,POSITION,SIM_PASSWORD,SIM_SYS_USER_NAME,STAFF_ID,STANDARD_CODE,STS,STS_DATE,TEL_NBR,TERMINAL_FLAG)");
		sql
				.append(" VALUES (:companyCode,:createDate,:deptId,:deptType,:localNetId,:partyId,:position,:simPassword,:simSysUserName,:staffId,:standardCode,:sts,:stsDate,:telNbr,:terminalFlag)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			for (int i = 0; i < list.size(); i++) {
				StaffSVO staff = (StaffSVO) list.get(i);
				if (staff == null) {
					throw new AppException("100001", "缺少DAO操作对象！");
				}
				if (StringUtil.isBlank(staff.getStaffId())) {
					throw new AppException("100002", "缺少对象的主键！");
				}
				if (StringUtil.isBlank(staff.getCompanyCode())) {
					sql.setNullLong("companyCode");
				} else {
					sql.setLong("companyCode", staff.getCompanyCode());
				}

				if (staff.getCreateDate() == null) {
					sql.setNullDate("createDate");
				} else {
					sql.setTimestamp("createDate", staff.getCreateDate());
				}

				if (StringUtil.isBlank(staff.getDeptId())) {
					sql.setNullString("deptId");
				} else {
					sql.setString("deptId", staff.getDeptId());
				}

				if (StringUtil.isBlank(staff.getDeptType())) {
					sql.setNullString("deptType");
				} else {
					sql.setString("deptType", staff.getDeptType());
				}

				if (StringUtil.isBlank(staff.getLocalNetId())) {
					sql.setNullLong("localNetId");
				} else {
					sql.setLong("localNetId", staff.getLocalNetId());
				}

				if (StringUtil.isBlank(staff.getPartyId())) {
					sql.setNullLong("partyId");
				} else {
					sql.setLong("partyId", staff.getPartyId());
				}

				if (StringUtil.isBlank(staff.getPosition())) {
					sql.setNullString("position");
				} else {
					sql.setString("position", staff.getPosition());
				}

				if (StringUtil.isBlank(staff.getSimPassword())) {
					sql.setNullString("simPassword");
				} else {
					sql.setString("simPassword", staff.getSimPassword());
				}

				if (StringUtil.isBlank(staff.getSimSysUserName())) {
					sql.setNullString("simSysUserName");
				} else {
					sql.setString("simSysUserName", staff.getSimSysUserName());
				}

				if (StringUtil.isBlank(staff.getStaffId())) {
					sql.setNullString("staffId");
				} else {
					sql.setString("staffId", staff.getStaffId());
				}

				if (StringUtil.isBlank(staff.getStandardCode())) {
					sql.setNullString("standardCode");
				} else {
					sql.setString("standardCode", staff.getStandardCode());
				}

				if (StringUtil.isBlank(staff.getSts())) {
					sql.setNullString("sts");
				} else {
					sql.setString("sts", staff.getSts());
				}

				if (staff.getStsDate() == null) {
					sql.setNullDate("stsDate");
				} else {
					sql.setTimestamp("stsDate", staff.getStsDate());
				}

				if (StringUtil.isBlank(staff.getTelNbr())) {
					sql.setNullString("telNbr");
				} else {
					sql.setString("telNbr", staff.getTelNbr());
				}

				if (StringUtil.isBlank(staff.getTerminalFlag())) {
					sql.setNullString("terminalFlag");
				} else {
					sql.setString("terminalFlag", staff.getTerminalFlag());
				}

				sql.fillParams(ps);
				sql.log(this.getClass());
				sql.clearParameters();
				ps.addBatch();
			}

			ps.executeBatch();
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * 根据传入参数删除一条或者一批记录。
	 * @return String ： 删除的SQL。
	 */
	public void delete(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		StaffSVO staff = (StaffSVO) vo;
		if (StringUtil.isBlank(staff.getStaffId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("DELETE FROM STAFF WHERE 1=1  ");
		sql.append(" and STAFF_ID=:staffId");
		sql.setString("staffId", staff.getStaffId());

		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * 注销一条或者一批
	 * @return String ： 注销一条或者一批的SQL。
	 */
	public void unable(GenericVO vo) throws AppException, SysException {
		StaffSVO staff = (StaffSVO) vo;
	}
}
