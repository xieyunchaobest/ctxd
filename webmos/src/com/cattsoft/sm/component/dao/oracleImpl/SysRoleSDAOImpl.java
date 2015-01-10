package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysRoleSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.*;

public class SysRoleSDAOImpl implements ISysRoleSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		
		SysRoleSVO sysRole = (SysRoleSVO) vo;
		StringBuffer sql = new StringBuffer("insert into SYS_ROLE(SYS_ROLE_ID,SYS_ROLE_NAME,ROLE_MEMO,LOCAL_NET_ID,AREA_ID,CREATE_DATE,STS,STS_DATE) values(?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysRole.getSysRoleId());
			ps.setString(2, sysRole.getSysRoleName());
			ps.setString(3, sysRole.getRoleMemo());
			ps.setString(4, sysRole.getLocalNetId());
			ps.setString(5, sysRole.getAreaId());
			ps.setDate(6, sysRole.getCreateDate());
			ps.setString(7, sysRole.getSts());
			ps.setDate(8, sysRole.getStsDate());
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
		SysRoleSVO sysRole = (SysRoleSVO) vo;
		StringBuffer sql = new StringBuffer("update SYS_ROLE set");
		if (sysRole.getSysRoleName() != null) {
			sql.append(" SYS_ROLE_NAME=?,");
		}
		if (sysRole.getRoleMemo() != null) {
			sql.append(" ROLE_MEMO=?,");
		}
		if (sysRole.getLocalNetId() != null) {
			sql.append(" LOCAL_NET_ID=?,");
		}
		if (sysRole.getAreaId() != null) {
			sql.append(" AREA_ID=?,");
		}
		if (sysRole.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		if (sysRole.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (sysRole.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and SYS_ROLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (sysRole.getSysRoleName() != null) {
				ps.setString(index++, sysRole.getSysRoleName());
			}
			if (sysRole.getRoleMemo() != null) {
				ps.setString(index++, sysRole.getRoleMemo());
			}
			if (sysRole.getLocalNetId() != null) {
				ps.setString(index++, sysRole.getLocalNetId());
			}
			if (sysRole.getAreaId() != null) {
				ps.setString(index++, sysRole.getAreaId());
			}
			if (sysRole.getCreateDate() != null) {
				ps.setDate(index++, sysRole.getCreateDate());
			}
			if (sysRole.getSts() != null) {
				ps.setString(index++, sysRole.getSts());
			}
			if (sysRole.getStsDate() != null) {
				ps.setDate(index++, sysRole.getStsDate());
			}
			ps.setString(index++, sysRole.getSysRoleId());
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
		SysRoleSVO sysRole = (SysRoleSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from SYS_ROLE where 1=1");
		sql.append(" and SYS_ROLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysRole.getSysRoleId());
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
		SysRoleSVO result = null;
		SysRoleSVO sysRole = (SysRoleSVO) vo;
		StringBuffer sql = new StringBuffer("select * from SYS_ROLE where 1=1");
		sql.append(" and SYS_ROLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysRole.getSysRoleId());
			rs = ps.executeQuery();
			result = (SysRoleSVO) ResultSetUtil.convertToVo(rs, SysRoleSVO.class);
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
		SysRoleSVO sysRole = (SysRoleSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from SYS_ROLE where 1=1");
		if (sysRole.getSysRoleId() != null) {
			sql.append(" and SYS_ROLE_ID=?");
		}
		if (sysRole.getSysRoleName() != null) {
			sql.append(" and SYS_ROLE_NAME like ?");
		}
		if (sysRole.getRoleMemo() != null) {
			sql.append(" and ROLE_MEMO=?");
		}
		if (sysRole.getLocalNetId() != null) {
			sql.append(" and LOCAL_NET_ID=?");
		}
		if (sysRole.getAreaId() != null) {
			sql.append(" and AREA_ID=?");
		}
		if (sysRole.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (sysRole.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (sysRole.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (sysRole.getSysRoleId() != null) {
				ps.setString(index++, sysRole.getSysRoleId());
			}
			if (sysRole.getSysRoleName() != null) {
				String name="%"+sysRole.getSysRoleName()+"%";
				ps.setString(index++,name);
			}
			if (sysRole.getRoleMemo() != null) {
				ps.setString(index++, sysRole.getRoleMemo());
			}
			if (sysRole.getLocalNetId() != null) {
				ps.setString(index++, sysRole.getLocalNetId());
			}
			if (sysRole.getAreaId() != null) {
				ps.setString(index++, sysRole.getAreaId());
			}
			if (sysRole.getCreateDate() != null) {
				ps.setDate(index++, sysRole.getCreateDate());
			}
			if (sysRole.getSts() != null) {
				ps.setString(index++, sysRole.getSts());
			}
			if (sysRole.getStsDate() != null) {
				ps.setDate(index++, sysRole.getStsDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, SysRoleSVO.class);
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
    public PagView findSysRolesByPage(SysRoleSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        SysRoleSVO sysRole = (SysRoleSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from SYS_ROLE a where 1=1");
        if (vo == null && set != null) {
            sql.append("and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "sys_role_id", "sysRoleId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
        if (sysRole.getSysRoleId() != null) {
            sql.append(" and SYS_ROLE_ID="+sysRole.getSysRoleId());
        }
        if (sysRole.getSysRoleName() != null) {
            sql.append(" and SYS_ROLE_NAME like '%"+sysRole.getSysRoleName()+"%'");
        }
       
        if (sysRole.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID="+sysRole.getLocalNetId());
        }
        if (sysRole.getAreaId() != null) {
            sql.append(" and AREA_ID="+sysRole.getAreaId());
        }
      
        if (sysRole.getSts() != null) {
            sql.append(" and STS='"+sysRole.getSts()+"'");
        }
       }
        sql.append(" order by sys_role_id");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(SysRoleSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, SysRoleSVO.class);
            pagView.setViewList(results);
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
        return pagView;
    }
}
