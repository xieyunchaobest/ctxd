package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IDataRangeItemSDAO;
import com.cattsoft.sm.vo.*;


public class DataRangeItemSDAOImpl implements IDataRangeItemSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		DataRangeItemSVO dataRangeItem = (DataRangeItemSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" DATA_RANGE_ITEM(DATA_RANGE_ITEM_ID,RANGE_ID,DATA_RANGE_TYPE_ID,FIELD_RANGE_VALUE,SEQ,LEFT_BRACKET,RIGHT_BRACKET,OPERATOR,LOGICAL,CREATE_DATE,STS,STS_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRangeItem.getDataRangeItemId());
			ps.setString(2, dataRangeItem.getRangeId());
			ps.setString(3, dataRangeItem.getDataRangeTypeId());
			ps.setString(4, dataRangeItem.getFieldRangeValue());
			ps.setString(5, dataRangeItem.getSeq());
			ps.setString(6, dataRangeItem.getLeftBracket());
			ps.setString(7, dataRangeItem.getRightBracket());
			ps.setString(8, dataRangeItem.getOperator());
			ps.setString(9, dataRangeItem.getLogical());
			ps.setDate(10, dataRangeItem.getCreateDate());
			ps.setString(11, dataRangeItem.getSts());
			ps.setDate(12, dataRangeItem.getStsDate());
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
		DataRangeItemSVO dataRangeItem = (DataRangeItemSVO) vo;
		StringBuffer sql = new StringBuffer("update DATA_RANGE_ITEM set");
		if (dataRangeItem.getRangeId() != null) {
			sql.append(" RANGE_ID=?,");
		}
		if (dataRangeItem.getDataRangeTypeId() != null) {
			sql.append(" DATA_RANGE_TYPE_ID=?,");
		}
		if (dataRangeItem.getFieldRangeValue() != null) {
			sql.append(" FIELD_RANGE_VALUE=?,");
		}
		if (dataRangeItem.getSeq() != null) {
			sql.append(" SEQ=?,");
		}
		if (dataRangeItem.getLeftBracket() != null) {
			sql.append(" LEFT_BRACKET=?,");
		}
		if (dataRangeItem.getRightBracket() != null) {
			sql.append(" RIGHT_BRACKET=?,");
		}
		if (dataRangeItem.getOperator() != null) {
			sql.append(" OPERATOR=?,");
		}
		if (dataRangeItem.getLogical() != null) {
			sql.append(" LOGICAL=?,");
		}
		if (dataRangeItem.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		if (dataRangeItem.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (dataRangeItem.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and DATA_RANGE_ITEM_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (dataRangeItem.getRangeId() != null) {
				ps.setString(index++, dataRangeItem.getRangeId());
			}
			if (dataRangeItem.getDataRangeTypeId() != null) {
				ps.setString(index++, dataRangeItem.getDataRangeTypeId());
			}
			if (dataRangeItem.getFieldRangeValue() != null) {
				ps.setString(index++, dataRangeItem.getFieldRangeValue());
			}
			if (dataRangeItem.getSeq() != null) {
				ps.setString(index++, dataRangeItem.getSeq());
			}
			if (dataRangeItem.getLeftBracket() != null) {
				ps.setString(index++, dataRangeItem.getLeftBracket());
			}
			if (dataRangeItem.getRightBracket() != null) {
				ps.setString(index++, dataRangeItem.getRightBracket());
			}
			if (dataRangeItem.getOperator() != null) {
				ps.setString(index++, dataRangeItem.getOperator());
			}
			if (dataRangeItem.getLogical() != null) {
				ps.setString(index++, dataRangeItem.getLogical());
			}
			if (dataRangeItem.getCreateDate() != null) {
				ps.setDate(index++, dataRangeItem.getCreateDate());
			}
			if (dataRangeItem.getSts() != null) {
				ps.setString(index++, dataRangeItem.getSts());
			}
			if (dataRangeItem.getStsDate() != null) {
				ps.setDate(index++, dataRangeItem.getStsDate());
			}
			ps.setString(index++, dataRangeItem.getDataRangeItemId());
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
		DataRangeItemSVO dataRangeItem = (DataRangeItemSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from DATA_RANGE_ITEM where 1=1");
		sql.append(" and DATA_RANGE_ITEM_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRangeItem.getDataRangeItemId());
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
		DataRangeItemSVO result = null;
		DataRangeItemSVO dataRangeItem = (DataRangeItemSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.DATA_RANGE_ITEM_ID,a.RANGE_ID,a.DATA_RANGE_TYPE_ID,a.FIELD_RANGE_VALUE,a.SEQ,a.LEFT_BRACKET,a.RIGHT_BRACKET,a.OPERATOR,a.LOGICAL,a.CREATE_DATE,a.STS,a.STS_DATE");
		sql.append(" from DATA_RANGE_ITEM a where 1=1");
		sql.append(" and DATA_RANGE_ITEM_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRangeItem.getDataRangeItemId());
			rs = ps.executeQuery();
			result = (DataRangeItemSVO) ResultSetUtil.convertToVo(rs, DataRangeItemSVO.class);
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
		DataRangeItemSVO dataRangeItem = (DataRangeItemSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DATA_RANGE_ITEM_ID,a.RANGE_ID,a.DATA_RANGE_TYPE_ID,a.FIELD_RANGE_VALUE,a.SEQ,a.LEFT_BRACKET,a.RIGHT_BRACKET,a.OPERATOR,a.LOGICAL,a.CREATE_DATE,a.STS,a.STS_DATE");
		sql.append(" from DATA_RANGE_ITEM a where 1=1");
		if (dataRangeItem.getDataRangeItemId() != null) {
			sql.append(" and DATA_RANGE_ITEM_ID=?");
		}
		if (dataRangeItem.getRangeId() != null) {
			sql.append(" and RANGE_ID=?");
		}
		if (dataRangeItem.getDataRangeTypeId() != null) {
			sql.append(" and DATA_RANGE_TYPE_ID=?");
		}
		if (dataRangeItem.getFieldRangeValue() != null) {
			sql.append(" and FIELD_RANGE_VALUE=?");
		}
		if (dataRangeItem.getSeq() != null) {
			sql.append(" and SEQ=?");
		}
		if (dataRangeItem.getLeftBracket() != null) {
			sql.append(" and LEFT_BRACKET=?");
		}
		if (dataRangeItem.getRightBracket() != null) {
			sql.append(" and RIGHT_BRACKET=?");
		}
		if (dataRangeItem.getOperator() != null) {
			sql.append(" and OPERATOR=?");
		}
		if (dataRangeItem.getLogical() != null) {
			sql.append(" and LOGICAL=?");
		}
		if (dataRangeItem.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (dataRangeItem.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (dataRangeItem.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (dataRangeItem.getDataRangeItemId() != null) {
				ps.setString(index++, dataRangeItem.getDataRangeItemId());
			}
			if (dataRangeItem.getRangeId() != null) {
				ps.setString(index++, dataRangeItem.getRangeId());
			}
			if (dataRangeItem.getDataRangeTypeId() != null) {
				ps.setString(index++, dataRangeItem.getDataRangeTypeId());
			}
			if (dataRangeItem.getFieldRangeValue() != null) {
				ps.setString(index++, dataRangeItem.getFieldRangeValue());
			}
			if (dataRangeItem.getSeq() != null) {
				ps.setString(index++, dataRangeItem.getSeq());
			}
			if (dataRangeItem.getLeftBracket() != null) {
				ps.setString(index++, dataRangeItem.getLeftBracket());
			}
			if (dataRangeItem.getRightBracket() != null) {
				ps.setString(index++, dataRangeItem.getRightBracket());
			}
			if (dataRangeItem.getOperator() != null) {
				ps.setString(index++, dataRangeItem.getOperator());
			}
			if (dataRangeItem.getLogical() != null) {
				ps.setString(index++, dataRangeItem.getLogical());
			}
			if (dataRangeItem.getCreateDate() != null) {
				ps.setDate(index++, dataRangeItem.getCreateDate());
			}
			if (dataRangeItem.getSts() != null) {
				ps.setString(index++, dataRangeItem.getSts());
			}
			if (dataRangeItem.getStsDate() != null) {
				ps.setDate(index++, dataRangeItem.getStsDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, DataRangeItemSVO.class);
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
