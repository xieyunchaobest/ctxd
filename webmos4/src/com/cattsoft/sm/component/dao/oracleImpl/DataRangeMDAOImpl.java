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
import com.cattsoft.sm.component.dao.IDataRangeMDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.DataRangeMVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */

public class DataRangeMDAOImpl implements IDataRangeMDAO {
	// private static Log log = LogFactory.getLog(DataRangeMDAOImpl.class);

	public List findDataRangesBySet(HashSet set) throws SysException, AppException, Exception {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.data_range_id,a.range_name,a.create_flag,a.local_net_id,a.area_id,a.create_date,a.sts,a.sts_date,ar.abbrev_name,ar.name,ar.iscenter  ");
		sql.append("from Data_Range a,Area ar ");
		sql.append("where a.area_id=ar.area_id  and ( ");
		sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "data_range_id", "dataRangeId"));
		sql.append(" ) ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, DataRangeMVO.class);
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
