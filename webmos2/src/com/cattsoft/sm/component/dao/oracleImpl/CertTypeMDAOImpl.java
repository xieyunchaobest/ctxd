/**
 * 
 */
package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ICertTypeMDAO;
import com.cattsoft.sm.vo.CertTypeMVO;
import com.cattsoft.sm.vo.CertTypeSVO;

/**
 * Title:系统管理-员工管理-证件类型管理<br>
 * Description:证件类型管理MDAO接口实现<br>
 * Copyright:Copyright (C) 2009<br>
 * Company:cattsoft<br>
 * 
 * @author 杨凯 2009-4-28<br>
 * @version 1.0 <br>
 * 
 */

public class CertTypeMDAOImpl implements ICertTypeMDAO {

	/**
	 * 批量注销证件类型
	 * 
	 * @param voList
	 * @throws AppException
	 * @throws SysException
	 */
	public void unableBat(List voList) throws AppException, SysException {
		if (voList == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("update CERT_TYPE set sts=:sts ");
		sql.append(" WHERE 1=1  ");
		sql.append(" AND CERT_TYPE_ID=:certTypeId");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			for (int i = 0; i < voList.size(); i++) {
				CertTypeMVO certTypeMVO = (CertTypeMVO) (voList.get(i));
				if (StringUtil.isBlank(certTypeMVO.getCertTypeId())) {
					throw new AppException("100002", "缺少对象的主键！");
				}
				sql.setString("certTypeId", certTypeMVO.getCertTypeId());
				sql.setString("sts", Constant.STS_HISTORY);
				ps = sql.fillParams(ps);
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

	public List findByVO(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		CertTypeSVO certType = (CertTypeSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT C.CERT_TYPE_ID,C.NAME,C.CERT_CODE_LEN,C.EXP_DATE,C.STS,C.STS_DATE,");
		sql.append("(SELECT STS_WORDS FROM STATUS STS  WHERE STS.TABLE_NAME = 'CERT_TYPE' AND");
		sql.append(" STS.COLUMN_NAME = 'STS' AND STS.STS_ID = C.STS) STS_WORDS");
		sql.append(" FROM CERT_TYPE C WHERE 1 = 1");
		try {

			if (certType.getFlagName() == 1) {
				if (StringUtil.isBlank(certType.getName())) {
					sql.append(" and NAME is null ");
				} else {
					sql.append(" and NAME like :name");
					sql.setString("name", "%" + certType.getName() + "%");
				}
			}

			if (certType.getFlagSts() == 1) {
				if (StringUtil.isBlank(certType.getSts())) {
					sql.append(" and STS is null ");
				} else {
					sql.append(" and STS=:sts");
					sql.setString("sts", certType.getSts());
				}
			}

			sql.append(" ORDER BY STS_DATE DESC ");

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				CertTypeMVO mvo = new CertTypeMVO();
				mvo.setCertTypeId(rs.getString("CERT_TYPE_ID"));
				mvo.setCertCodeLen(rs.getString("CERT_CODE_LEN"));
				mvo.setExpDate(rs.getTimestamp("EXP_DATE"));
				mvo.setStsDate(rs.getTimestamp("STS_DATE"));
				mvo.setName(rs.getString("NAME"));
				mvo.setSts(rs.getString("STS"));
				mvo.setStsName(rs.getString("STS_WORDS"));
				res.add(mvo);

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
	 * 主键查询的SQL。
	 * 
	 * @return String ： 主键查询的SQL。
	 */
	public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		CertTypeMVO certType = (CertTypeMVO) vo;
		if (StringUtil.isBlank(certType.getCertTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Sql sql = new Sql(
				"SELECT CERT_CODE_LEN,CERT_TYPE_ID,CREATE_DATE,EXP_DATE,NAME,STS,STS_DATE FROM CERT_TYPE WHERE 1=1  ");
		sql.append(" and CERT_TYPE_ID=:certTypeId");
		sql.setString("certTypeId", certType.getCertTypeId());

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		certType = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				certType = new CertTypeMVO();
				certType.setCertCodeLen(rs.getString("CERT_CODE_LEN"));
				certType.setCertTypeId(rs.getString("CERT_TYPE_ID"));
				certType.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				certType.setExpDate(rs.getTimestamp("EXP_DATE"));
				certType.setName(rs.getString("NAME"));
				certType.setSts(rs.getString("STS"));
				certType.setStsDate(rs.getTimestamp("STS_DATE"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return certType;
	}

}
