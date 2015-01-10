package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.sm.component.dao.IDataRangeTypeMDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.DataRangeTypeSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-20 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */

public class DataRangeTypeMDAOImpl implements IDataRangeTypeMDAO {

	// private static Log log = LogFactory.getLog(DataRangeTypeMDAOImpl.class);

	public List findDataRangeTypesBySet(HashSet set) throws SysException, AppException, Exception {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.data_range_type_id,a.data_range_type_name,a.data_range_pattern,a.set_range_comp,a.version  ");
		sql.append("from Data_Range_Type a ");
		sql.append("where  ( ");
		sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "data_Range_Type_Id",
				"dataRangeTypeId"));
		sql.append(" ) ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, DataRangeTypeSVO.class);
		} catch (Exception e) {
			e.printStackTrace();
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
		return vos;

	}

}
