package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IFuncNodeSDAO;
import com.cattsoft.sm.vo.*;

public class FuncNodeSDAOImpl implements IFuncNodeSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		
		FuncNodeSVO funcNode = (FuncNodeSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" FUNC_NODE(FUNC_NODE_ID,NODE_TREE_ID,FUNC_NODE_CODE,FUNC_NODE_NAME,SUB_SYSTEM_NAME,SECURITY_LEVEL,FUNC_NODE_TYPE,HTML,FILE_NAME,VERSION,DESCRIPTION,SHORT_CUT_IMAGE,STS,STS_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcNode.getFuncNodeId());
			ps.setString(2, funcNode.getNodeTreeId());
			ps.setString(3, funcNode.getFuncNodeCode());
			ps.setString(4, funcNode.getFuncNodeName());
			ps.setString(5, funcNode.getSubSystemName());
			ps.setString(6, funcNode.getSecurityLevel());
			ps.setString(7, funcNode.getFuncNodeType());
			ps.setString(8, funcNode.getHtml());
			ps.setString(9, funcNode.getFileName());
			ps.setString(10, funcNode.getVersion());
			ps.setString(11, funcNode.getDescription());
			ps.setString(12, funcNode.getShortCutImage());
			ps.setString(13, funcNode.getSts());
			ps.setDate(14, funcNode.getStsDate());
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
		FuncNodeSVO funcNode = (FuncNodeSVO) vo;
		StringBuffer sql = new StringBuffer("update FUNC_NODE set");
		if (funcNode.getNodeTreeId() != null) {
			sql.append(" NODE_TREE_ID=?,");
		}
		if (funcNode.getFuncNodeCode() != null) {
			sql.append(" FUNC_NODE_CODE=?,");
		}
		if (funcNode.getFuncNodeName() != null) {
			sql.append(" FUNC_NODE_NAME=?,");
		}
		if (funcNode.getSubSystemName() != null) {
			sql.append(" SUB_SYSTEM_NAME=?,");
		}
		if (funcNode.getSecurityLevel() != null) {
			sql.append(" SECURITY_LEVEL=?,");
		}
		if (funcNode.getFuncNodeType() != null) {
			sql.append(" FUNC_NODE_TYPE=?,");
		}
		if (funcNode.getHtml() != null) {
			sql.append(" HTML=?,");
		}
		if (funcNode.getFileName() != null) {
			sql.append(" FILE_NAME=?,");
		}
		if (funcNode.getVersion() != null) {
			sql.append(" VERSION=?,");
		}
		if (funcNode.getDescription() != null) {
			sql.append(" DESCRIPTION=?,");
		}
		if (funcNode.getShortCutImage() != null) {
			sql.append(" SHORT_CUT_IMAGE=?,");
		}
		if (funcNode.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (funcNode.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and FUNC_NODE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (funcNode.getNodeTreeId() != null) {
				ps.setString(index++, funcNode.getNodeTreeId());
			}
			if (funcNode.getFuncNodeCode() != null) {
				ps.setString(index++, funcNode.getFuncNodeCode());
			}
			if (funcNode.getFuncNodeName() != null) {
				ps.setString(index++, funcNode.getFuncNodeName());
			}
			if (funcNode.getSubSystemName() != null) {
				ps.setString(index++, funcNode.getSubSystemName());
			}
			if (funcNode.getSecurityLevel() != null) {
				ps.setString(index++, funcNode.getSecurityLevel());
			}
			if (funcNode.getFuncNodeType() != null) {
				ps.setString(index++, funcNode.getFuncNodeType());
			}
			if (funcNode.getHtml() != null) {
				ps.setString(index++, funcNode.getHtml());
			}
			if (funcNode.getFileName() != null) {
				ps.setString(index++, funcNode.getFileName());
			}
			if (funcNode.getVersion() != null) {
				ps.setString(index++, funcNode.getVersion());
			}
			if (funcNode.getDescription() != null) {
				ps.setString(index++, funcNode.getDescription());
			}
			if (funcNode.getShortCutImage() != null) {
				ps.setString(index++, funcNode.getShortCutImage());
			}
			if (funcNode.getSts() != null) {
				ps.setString(index++, funcNode.getSts());
			}
			if (funcNode.getStsDate() != null) {
				ps.setDate(index++, funcNode.getStsDate());
			}
			ps.setString(index++, funcNode.getFuncNodeId());
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
		FuncNodeSVO funcNode = (FuncNodeSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from FUNC_NODE where 1=1");
		sql.append(" and FUNC_NODE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcNode.getFuncNodeId());
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
		FuncNodeSVO result = null;
		FuncNodeSVO funcNode = (FuncNodeSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.FUNC_NODE_ID,a.NODE_TREE_ID,a.FUNC_NODE_CODE,a.FUNC_NODE_NAME,a.SUB_SYSTEM_NAME,a.SECURITY_LEVEL,a.FUNC_NODE_TYPE,a.HTML,a.FILE_NAME,a.VERSION,a.DESCRIPTION,a.SHORT_CUT_IMAGE,a.STS,a.STS_DATE");
		sql.append(" from FUNC_NODE a where 1=1");
		sql.append(" and FUNC_NODE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcNode.getFuncNodeId());
			rs = ps.executeQuery();
			result = (FuncNodeSVO) ResultSetUtil.convertToVo(rs, FuncNodeSVO.class);
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
		FuncNodeSVO funcNode = (FuncNodeSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.FUNC_NODE_ID,a.NODE_TREE_ID,a.FUNC_NODE_CODE,a.FUNC_NODE_NAME,a.SUB_SYSTEM_NAME,a.SECURITY_LEVEL,a.FUNC_NODE_TYPE,a.HTML,a.FILE_NAME,a.VERSION,a.DESCRIPTION,a.SHORT_CUT_IMAGE,a.STS,a.STS_DATE");
		sql.append(" from FUNC_NODE a where 1=1");
		if (funcNode.getFuncNodeId() != null) {
			sql.append(" and FUNC_NODE_ID=?");
		}
		if (funcNode.getNodeTreeId() != null) {
			sql.append(" and NODE_TREE_ID=?");
		}
		if (funcNode.getFuncNodeCode() != null) {
			sql.append(" and FUNC_NODE_CODE=?");
		}
		if (funcNode.getFuncNodeName() != null) {
			sql.append(" and FUNC_NODE_NAME=?");
		}
		if (funcNode.getSubSystemName() != null) {
			sql.append(" and SUB_SYSTEM_NAME=?");
		}
		if (funcNode.getSecurityLevel() != null) {
			sql.append(" and SECURITY_LEVEL=?");
		}
		if (funcNode.getFuncNodeType() != null) {
			sql.append(" and FUNC_NODE_TYPE=?");
		}
		if (funcNode.getHtml() != null) {
			sql.append(" and HTML=?");
		}
		if (funcNode.getFileName() != null) {
			sql.append(" and FILE_NAME=?");
		}
		if (funcNode.getVersion() != null) {
			sql.append(" and VERSION=?");
		}
		if (funcNode.getDescription() != null) {
			sql.append(" and DESCRIPTION=?");
		}
		if (funcNode.getShortCutImage() != null) {
			sql.append(" and SHORT_CUT_IMAGE=?");
		}
		if (funcNode.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (funcNode.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (funcNode.getFuncNodeId() != null) {
				ps.setString(index++, funcNode.getFuncNodeId());
			}
			if (funcNode.getNodeTreeId() != null) {
				ps.setString(index++, funcNode.getNodeTreeId());
			}
			if (funcNode.getFuncNodeCode() != null) {
				ps.setString(index++, funcNode.getFuncNodeCode());
			}
			if (funcNode.getFuncNodeName() != null) {
				ps.setString(index++, funcNode.getFuncNodeName());
			}
			if (funcNode.getSubSystemName() != null) {
				ps.setString(index++, funcNode.getSubSystemName());
			}
			if (funcNode.getSecurityLevel() != null) {
				ps.setString(index++, funcNode.getSecurityLevel());
			}
			if (funcNode.getFuncNodeType() != null) {
				ps.setString(index++, funcNode.getFuncNodeType());
			}
			if (funcNode.getHtml() != null) {
				ps.setString(index++, funcNode.getHtml());
			}
			if (funcNode.getFileName() != null) {
				ps.setString(index++, funcNode.getFileName());
			}
			if (funcNode.getVersion() != null) {
				ps.setString(index++, funcNode.getVersion());
			}
			if (funcNode.getDescription() != null) {
				ps.setString(index++, funcNode.getDescription());
			}
			if (funcNode.getShortCutImage() != null) {
				ps.setString(index++, funcNode.getShortCutImage());
			}
			if (funcNode.getSts() != null) {
				ps.setString(index++, funcNode.getSts());
			}
			if (funcNode.getStsDate() != null) {
				ps.setDate(index++, funcNode.getStsDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
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
