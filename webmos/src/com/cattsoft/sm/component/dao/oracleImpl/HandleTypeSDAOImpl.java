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
import com.cattsoft.sm.component.dao.IHandleTypeSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.*;

public class HandleTypeSDAOImpl implements IHandleTypeSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		
		HandleTypeSVO handleType = (HandleTypeSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" HANDLE_TYPE(HANDLE_TYPE_ID,NAME,SYSTEM_NAME,CREATE_DATE) values(?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, handleType.getHandleTypeId());
			ps.setString(2, handleType.getName());
			ps.setString(3, handleType.getSystemName());
			ps.setDate(4, handleType.getCreateDate());
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
		HandleTypeSVO handleType = (HandleTypeSVO) vo;
		StringBuffer sql = new StringBuffer("update HANDLE_TYPE set");
		if (handleType.getName() != null) {
			sql.append(" NAME=?,");
		}
		if (handleType.getSystemName() != null) {
			sql.append(" SYSTEM_NAME=?,");
		}
		if (handleType.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and HANDLE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (handleType.getName() != null) {
				ps.setString(index++, handleType.getName());
			}
			if (handleType.getSystemName() != null) {
				ps.setString(index++, handleType.getSystemName());
			}
			if (handleType.getCreateDate() != null) {
				ps.setDate(index++, handleType.getCreateDate());
			}
			ps.setString(index++, handleType.getHandleTypeId());
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
		HandleTypeSVO handleType = (HandleTypeSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from HANDLE_TYPE where 1=1");
		sql.append(" and HANDLE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, handleType.getHandleTypeId());
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
		HandleTypeSVO result = null;
		HandleTypeSVO handleType = (HandleTypeSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.HANDLE_TYPE_ID,a.NAME,a.SYSTEM_NAME,a.CREATE_DATE");
		sql.append(" from HANDLE_TYPE a where 1=1");
		sql.append(" and HANDLE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, handleType.getHandleTypeId());
			rs = ps.executeQuery();
			result = (HandleTypeSVO) ResultSetUtil.convertToVo(rs, HandleTypeSVO.class);
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
		HandleTypeSVO handleType = (HandleTypeSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.HANDLE_TYPE_ID,a.NAME,a.SYSTEM_NAME,a.CREATE_DATE");
		sql.append(" from HANDLE_TYPE a where 1=1");
		if (handleType.getHandleTypeId() != null) {
			sql.append(" and HANDLE_TYPE_ID=?");
		}
		if (handleType.getName() != null) {
			sql.append(" and NAME like '%?%' ");
		}
		if (handleType.getSystemName() != null) {
			sql.append(" and SYSTEM_NAME=?");
		}
		if (handleType.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (handleType.getHandleTypeId() != null) {
				ps.setString(index++, handleType.getHandleTypeId());
			}
			if (handleType.getName() != null) {
				ps.setString(index++, handleType.getName());
			}
			if (handleType.getSystemName() != null) {
				ps.setString(index++, handleType.getSystemName());
			}
			if (handleType.getCreateDate() != null) {
				ps.setDate(index++, handleType.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, HandleTypeSVO.class);
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
     public PagView findHandleTypesByPage(HandleTypeSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        HandleTypeSVO handleType = (HandleTypeSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.HANDLE_TYPE_ID,a.NAME,a.SYSTEM_NAME,a.CREATE_DATE");
        sql.append(" from HANDLE_TYPE a where 1=1");
        if (vo == null && set != null) {
            sql.append("and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "handle_type_id", "handleTypeId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
        if (handleType.getHandleTypeId() != null) {
            sql.append(" and HANDLE_TYPE_ID="+vo.getHandleTypeId());
        }
        if (handleType.getName() != null) {
            sql.append(" and NAME like '%"+vo.getName()+"%'");
        }
        if (handleType.getSystemName() != null) {
            sql.append(" and SYSTEM_NAME='"+vo.getSystemName()+"'");
        }
        if (handleType.getCreateDate() != null) {
            sql.append(" and CREATE_DATE="+vo.getCreateDate());
        }
        }
        sql.append(" order by handle_type_id");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(HandleTypeSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, HandleTypeSVO.class);
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
     public HandleTypeSVO findByName(GenericVO vo) throws AppException, SysException {
        HandleTypeSVO result = null;
        HandleTypeSVO handleType = (HandleTypeSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql.append(" a.HANDLE_TYPE_ID,a.NAME,a.SYSTEM_NAME,a.CREATE_DATE");
        sql.append(" from HANDLE_TYPE a where 1=1");
        sql.append(" and name=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, handleType.getName());
            rs = ps.executeQuery();
            result = (HandleTypeSVO) ResultSetUtil.convertToVo(rs, HandleTypeSVO.class);
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


}
