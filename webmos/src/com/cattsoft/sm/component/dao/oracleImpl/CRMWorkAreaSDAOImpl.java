package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ICRMWorkAreaSDAO;
import com.cattsoft.sm.vo.WorkAreaSVO;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.StringUtil;

public class CRMWorkAreaSDAOImpl implements ICRMWorkAreaSDAO {

	public List findByVO(GenericVO vo) throws AppException, SysException {
		WorkAreaSVO svo = null;
		if (vo instanceof WorkAreaSVO) {
			svo = (WorkAreaSVO) vo;
		} else {
			throw new AppException("", "»±…Ÿ≤È—ØSVO");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = null;
		List retList = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		conn = ConnectionFactory.getConnection();
		sql = new Sql("SELECT * FROM WORK_AREA_CRM WAC WHERE 1 = 1 ");
		if (!StringUtil.isBlank(svo.getLocalNetId())) {
			sql.append("AND WAC.LOCAL_NET_ID = :localNetId ");
			sql.setString("localNetId", svo.getLocalNetId());
		}
		if (!StringUtil.isBlank(svo.getLocalNetId())) {
			sql.append("AND WAC.AREA_ID = :areaId ");
			sql.setString("areaId", svo.getAreaId());
		}
		try {
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			rs = ps.executeQuery();
			WorkAreaSVO retVO = null;
			while(rs.next()){
				retVO = new WorkAreaSVO();
				retVO.setWorkAreaId(rs.getString("WORK_AREA_ID"));
				retVO.setName(rs.getString("NAME"));
				retList.add(retVO);
			}
		} catch (SQLException e) {
			throw new SysException("",e);
		}
		return retList;
	}

	public void add(GenericVO vo) throws AppException, SysException {
		// TODO Auto-generated method stub

	}

	public void delete(GenericVO vo) throws AppException, SysException {
		// TODO Auto-generated method stub

	}

	public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(GenericVO vo) throws AppException, SysException {
		// TODO Auto-generated method stub

	}

}
