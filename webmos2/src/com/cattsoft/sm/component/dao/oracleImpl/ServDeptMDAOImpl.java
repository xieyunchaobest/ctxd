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
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IServDeptMDAO;
import com.cattsoft.sm.vo.MsAreaSVO;
import com.cattsoft.sm.vo.ServDeptMVO;
import com.cattsoft.sm.vo.ServDeptSVO;


public class ServDeptMDAOImpl  implements IServDeptMDAO {
	public List findServDeptList(GenericVO vo) throws AppException, SysException {
        List results = null;
        ServDeptSVO servDept = (ServDeptSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.SERV_DEPT_ID,a.LOCAL_NET_ID,a.AREA_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from SERV_DEPT a where 1=1");
        if (servDept.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=?");
        }
        if (servDept.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (servDept.getAreaId() != null) {
            sql.append(" and AREA_ID=?");
        }
        if (servDept.getAbbrevName() != null) {
            sql.append(" and ABBREV_NAME=?");
        }
        if (servDept.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (servDept.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (servDept.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (servDept.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        sql.append(" order by NAME ");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (servDept.getServDeptId() != null) {
                ps.setString(index++, servDept.getServDeptId());
            }
            if (servDept.getLocalNetId() != null) {
                ps.setString(index++, servDept.getLocalNetId());
            }
            if (servDept.getAreaId() != null) {
                ps.setString(index++, servDept.getAreaId());
            }
            if (servDept.getAbbrevName() != null) {
                ps.setString(index++, servDept.getAbbrevName());
            }
            if (servDept.getName() != null) {
                ps.setString(index++, servDept.getName());
            }
            if (servDept.getSts() != null) {
                ps.setString(index++, servDept.getSts());
            }
            if (servDept.getStsDate() != null) {
                ps.setDate(index++, servDept.getStsDate());
            }
            if (servDept.getCreateDate() != null) {
                ps.setDate(index++, servDept.getCreateDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, ServDeptSVO.class);
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
	public List findServDeptsByMVO(ServDeptSVO vo) throws AppException, SysException{
		List results = null;
        ServDeptSVO servDept = (ServDeptSVO) vo;
        Sql sql =new Sql();
        sql.append("select");
        sql.append(" a.SERV_DEPT_ID,a.LOCAL_NET_ID,a.AREA_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE,a.AREA_TYPE MAIN_AREA_TYPE,");
        sql.append("(select sts_words from status d where d.table_name='SERV_DEPT' and d.column_name='AREA_TYPE' and d.sts_id=a.area_type) AREA_TYPE,");
        sql.append("b.name local_net_name,b.iscenter local_Net_Iscenter,c.name area_name ,c.iscenter area_iscenter ");
        sql.append(" from local_net b,area c,SERV_DEPT a where 1=1 and a.local_net_id=b.local_net_id and a.area_id=c.area_id ");
        if (vo != null) {
            if (servDept.getServDeptId() != null && !servDept.getServDeptId().equals("")) {
                sql.append(" and a.SERV_DEPT_ID=" + servDept.getServDeptId());
            }
            if (servDept.getLocalNetId() != null) {
                sql.append(" and a.LOCAL_NET_ID=" + servDept.getLocalNetId());
            }
            if (servDept.getAreaId() != null) {
                sql.append(" and a.AREA_ID=" + servDept.getAreaId());
            }
            if (servDept.getAbbrevName() != null) {
                sql.append(" and a.ABBREV_NAME='%" + servDept.getAbbrevName() + "%'");
            }
            if (servDept.getName() != null) {
                sql.append(" and a.NAME='%" + servDept.getName() + "%'");
            }
            if (servDept.getSts() != null) {
                sql.append(" and a.STS='" + servDept.getSts() + "'");
            }
            if (servDept.getAreaType() != null) {
                sql.append(" and a.AREA_TYPE='" + servDept.getAreaType() + "'");
            }
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			rs = ps.executeQuery();
			sql.log(this.getClass());
			//results = (List) ResultSetUtil.convertToList(rs, ExchMVO.class);
			results = convertToExchMVOList(rs);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
        return results;
	}
	private List convertToExchMVOList(ResultSet rs)throws SQLException, AppException, SysException{
    	List mvos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
    	while(rs.next()){
    		ServDeptMVO mvo =new ServDeptMVO();
    		mvo.setAbbrevName(rs.getString("ABBREV_NAME"));
    		mvo.setAreaId(rs.getString("AREA_ID"));
    		mvo.setAreaIscenter(rs.getString("area_iscenter"));
    		mvo.setAreaName(rs.getString("area_name"));
    		mvo.setCreateDate(rs.getDate("CREATE_DATE"));
    		mvo.setLocalNetId(rs.getString("LOCAL_NET_ID"));
    		mvo.setLocalNetIscenter(rs.getString("local_Net_Iscenter"));
    		mvo.setLocalNetName(rs.getString("local_net_name"));
    		mvo.setName(rs.getString("NAME"));
    		mvo.setServDeptId(rs.getString("SERV_DEPT_ID"));
    		mvo.setSts(rs.getString("sts"));
    		mvo.setStsDate(rs.getDate("STS_DATE"));
    		mvo.setAreaType(rs.getString("AREA_TYPE"));//XINGZHENFENG ADD BY 2013.9.29
    		mvo.setMainAreaType(rs.getString("MAIN_AREA_TYPE"));
    		mvos.add(mvo);
    	}
    	return mvos;
	}
	/**
	 * 根据服务区查询网格(山西)
	 * @param msAreaSVO
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findServDeptList(MsAreaSVO msAreaSVO) throws AppException, SysException {
		List results = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select a.SERV_DEPT_ID,a.NAME");
		sql.append(" from SERV_DEPT a ,MS_AREA b where a.AREA_ID=b.MS_AREA_ID");
		if (msAreaSVO.getAreaId() != null) {
			sql.append(" and b.AREA_ID=?");
		}
		if (msAreaSVO.getLocalNetId() != null) {
			sql.append(" and b.LOCAL_NET_ID=?");
		}
		sql.append(" order by a.NAME ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (msAreaSVO.getAreaId() != null) {
				ps.setString(index++, msAreaSVO.getAreaId());
			}
			if (msAreaSVO.getLocalNetId() != null) {
				ps.setString(index++, msAreaSVO.getLocalNetId());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, ServDeptSVO.class);
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
