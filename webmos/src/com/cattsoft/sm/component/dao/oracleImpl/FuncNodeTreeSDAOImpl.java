package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IFuncNodeTreeSDAO;
import com.cattsoft.sm.vo.*;

public class FuncNodeTreeSDAOImpl implements IFuncNodeTreeSDAO {
	
	public void add(GenericVO vo) throws AppException, SysException {
		FuncNodeTreeSVO funcNodeTree = (FuncNodeTreeSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" FUNC_NODE_TREE(NODE_TREE_ID,NODE_TREE_NAME,PARENT_NODE_TREE_ID,NODE_TREE_CODE,DESCRIPTION,HTML,FILE_NAME) values(?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcNodeTree.getNodeTreeId());
			ps.setString(2, funcNodeTree.getNodeTreeName());
			ps.setString(3, funcNodeTree.getParentNodeTreeId());
			ps.setString(4, funcNodeTree.getNodeTreeCode());
			ps.setString(5, funcNodeTree.getDescription());
			ps.setString(6, funcNodeTree.getHtml());
			ps.setString(7, funcNodeTree.getFileName());
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
		FuncNodeTreeSVO funcNodeTree = (FuncNodeTreeSVO) vo;
		StringBuffer sql = new StringBuffer("update FUNC_NODE_TREE set");
		if (funcNodeTree.getNodeTreeName() != null) {
			sql.append(" NODE_TREE_NAME=?,");
		}
		if (funcNodeTree.getParentNodeTreeId() != null) {
			sql.append(" PARENT_NODE_TREE_ID=?,");
		}
		if (funcNodeTree.getNodeTreeCode() != null) {
			sql.append(" NODE_TREE_CODE=?,");
		}
		if (funcNodeTree.getDescription() != null) {
			sql.append(" DESCRIPTION=?,");
		}
		if (funcNodeTree.getHtml() != null) {
			sql.append(" HTML=?,");
		}
		if (funcNodeTree.getFileName() != null) {
			sql.append(" FILE_NAME=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and NODE_TREE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (funcNodeTree.getNodeTreeName() != null) {
				ps.setString(index++, funcNodeTree.getNodeTreeName());
			}
			if (funcNodeTree.getParentNodeTreeId() != null) {
				ps.setString(index++, funcNodeTree.getParentNodeTreeId());
			}
			if (funcNodeTree.getNodeTreeCode() != null) {
				ps.setString(index++, funcNodeTree.getNodeTreeCode());
			}
			if (funcNodeTree.getDescription() != null) {
				ps.setString(index++, funcNodeTree.getDescription());
			}
			if (funcNodeTree.getHtml() != null) {
				ps.setString(index++, funcNodeTree.getHtml());
			}
			if (funcNodeTree.getFileName() != null) {
				ps.setString(index++, funcNodeTree.getFileName());
			}
			ps.setString(index++, funcNodeTree.getNodeTreeId());
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
		FuncNodeTreeSVO funcNodeTree = (FuncNodeTreeSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from FUNC_NODE_TREE where 1=1");
		sql.append(" and NODE_TREE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcNodeTree.getNodeTreeId());
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
		FuncNodeTreeSVO result = null;
		FuncNodeTreeSVO funcNodeTree = (FuncNodeTreeSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.NODE_TREE_ID,a.NODE_TREE_NAME,a.PARENT_NODE_TREE_ID,a.NODE_TREE_CODE,a.DESCRIPTION,a.HTML,a.FILE_NAME");
		sql.append(" from FUNC_NODE_TREE a where 1=1");
		sql.append(" and NODE_TREE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcNodeTree.getNodeTreeId());
			rs = ps.executeQuery();
			result = (FuncNodeTreeSVO) ResultSetUtil.convertToVo(rs, FuncNodeTreeSVO.class);
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
		FuncNodeTreeSVO funcNodeTree = (FuncNodeTreeSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.NODE_TREE_ID,a.NODE_TREE_NAME,a.PARENT_NODE_TREE_ID,a.NODE_TREE_CODE,a.DESCRIPTION,a.HTML,a.FILE_NAME");
		sql.append(" from FUNC_NODE_TREE a where 1=1");
		if (funcNodeTree.getNodeTreeId() != null) {
			sql.append(" and NODE_TREE_ID=?");
		}
		if (funcNodeTree.getNodeTreeName() != null) {
			sql.append(" and NODE_TREE_NAME=?");
		}
		if (funcNodeTree.getParentNodeTreeId() != null) {
			sql.append(" and PARENT_NODE_TREE_ID=?");
		}
		if (funcNodeTree.getNodeTreeCode() != null) {
			sql.append(" and NODE_TREE_CODE=?");
		}
		if (funcNodeTree.getDescription() != null) {
			sql.append(" and DESCRIPTION=?");
		}
		if (funcNodeTree.getHtml() != null) {
			sql.append(" and HTML=?");
		}
		if (funcNodeTree.getFileName() != null) {
			sql.append(" and FILE_NAME=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (funcNodeTree.getNodeTreeId() != null) {
				ps.setString(index++, funcNodeTree.getNodeTreeId());
			}
			if (funcNodeTree.getNodeTreeName() != null) {
				ps.setString(index++, funcNodeTree.getNodeTreeName());
			}
			if (funcNodeTree.getParentNodeTreeId() != null) {
				ps.setString(index++, funcNodeTree.getParentNodeTreeId());
			}
			if (funcNodeTree.getNodeTreeCode() != null) {
				ps.setString(index++, funcNodeTree.getNodeTreeCode());
			}
			if (funcNodeTree.getDescription() != null) {
				ps.setString(index++, funcNodeTree.getDescription());
			}
			if (funcNodeTree.getHtml() != null) {
				ps.setString(index++, funcNodeTree.getHtml());
			}
			if (funcNodeTree.getFileName() != null) {
				ps.setString(index++, funcNodeTree.getFileName());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, FuncNodeTreeSVO.class);
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

}
