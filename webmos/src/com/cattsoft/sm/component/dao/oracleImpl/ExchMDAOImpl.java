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
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IExchMDAO;
import com.cattsoft.sm.vo.ExchMVO;
import com.cattsoft.sm.vo.ExchSVO;

public class ExchMDAOImpl implements IExchMDAO{ 

    public List findExchMVOByVO(ExchSVO vo) throws AppException, SysException{
        List results = null;
        ExchSVO exch = (ExchSVO) vo;
        Sql sql = new Sql();
        sql.append("select");
        sql.append("(select ss.sts_words from status ss where  ss.table_name = 'EXCH' and ss.column_name = 'STS' and ss.sts_id = a.sts) STS_DESC,");
        sql.append("(select ss.sts_words from status ss where  ss.table_name = 'EXCH' and ss.column_name='EXCH_TYPE' and ss.sts_id = a.EXCH_TYPE) EXCH_TYPE_DESC,");
        sql.append("(select ss.sts_words from status ss where  ss.table_name = 'EXCH' and ss.column_name='SUB_TYPE' and ss.sts_id = a.SUB_TYPE) SUB_TYPE_DESC,");
        sql.append("a.EXCH_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.RULE_AREA_ID,a.ABBREV_NAME,a.CODE,a.EXCH_TYPE,a.SUB_TYPE,a.ADDRESS,a.COMM_DATE,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE, ");
        sql.append("b.name local_net_name,c.name area_name,d.name serv_dept_name,e.name branch_name,f.name rule_area_name ");
        sql.append("from exch a,local_net b,area c,serv_dept d,branch e,rule_area f ");
        sql.append(" where  a.local_net_id=b.local_net_id ");
        sql.append(" and a.area_id=c.area_id and a.serv_dept_id=d.serv_dept_id ");
        sql.append(" and a.branch_id=e.branch_id(+) and a.rule_area_id=f.rule_area_id(+)  ");
        if (vo != null ) {
            if (exch.getExchId() != null) {
                sql.append(" and a.EXCH_ID=" + exch.getExchId());
            }
            if (exch.getName() != null) {
                sql.append(" and a.NAME like '%" + exch.getName()+"%'");
            }
            if (exch.getLocalNetId() != null) {
                sql.append(" and a.LOCAL_NET_ID=" + exch.getLocalNetId());
            }
            if (exch.getAreaId() != null) {
                sql.append(" and a.AREA_ID=" + exch.getAreaId());
            }
            if (exch.getServDeptId() != null) {
                sql.append(" and a.SERV_DEPT_ID=" + exch.getServDeptId());
            }
            if (exch.getBranchId() != null) {
                sql.append(" and a.BRANCH_ID=" + exch.getBranchId());
            }
            if (exch.getRuleAreaId() != null) {
                sql.append(" and a.RULE_AREA_ID=" + exch.getRuleAreaId());
            }
            if (exch.getAbbrevName() != null) {
                sql.append(" and a.ABBREV_NAME=" + exch.getAbbrevName());
            }
            if (exch.getCode() != null) {
                sql.append(" and a.CODE=" + exch.getCode());
            }
            if (exch.getExchType() != null) {
                sql.append(" and a.EXCH_TYPE='" + exch.getExchType()+"'");
            }
            if (exch.getSubType() != null) {//added by yangkai 2009-9-3 增加了此字段
                sql.append(" and a.SUB_TYPE='" + exch.getSubType()+"'");
            }
            if (exch.getAddress() != null) {
                sql.append(" and a.ADDRESS=" + exch.getAddress());
            }
            if (exch.getCommDate() != null) {
                sql.append(" and a.COMM_DATE=" + exch.getCommDate());
            }
            if (exch.getStandardCode() != null) {
                sql.append(" and a.STANDARD_CODE=" + exch.getStandardCode());
            }
            if (exch.getSts() != null) {
                sql.append(" and a.STS=" + "'" + exch.getSts() + "'");
            }
            if (exch.getStsDate() != null) {
                sql.append(" and a.STS_DATE=" + exch.getStsDate());
            }
            if (exch.getCreateDate() != null) {
                sql.append(" and a.CREATE_DATE=" + exch.getCreateDate());
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
    		ExchMVO mvo = new ExchMVO();
    		mvo.setAbbrevName(rs.getString("ABBREV_NAME"));
    		mvo.setAddress(rs.getString("ADDRESS"));
    		mvo.setAreaId(rs.getString("AREA_ID"));
    		mvo.setAreaName(rs.getString("AREA_NAME"));
    		mvo.setBranchId(rs.getString("BRANCH_ID"));
    		mvo.setBranchName(rs.getString("BRANCH_NAME"));
    		mvo.setCode(rs.getString("CODE"));
    		mvo.setCommDate(rs.getDate("COMM_DATE"));
    		mvo.setCreateDate(rs.getDate("CREATE_DATE"));
    		mvo.setExchId(rs.getString("EXCH_ID"));
    		mvo.setExchType(rs.getString("EXCH_TYPE"));
    		mvo.setSubType(rs.getString("SUB_TYPE"));//added by yangkai 2009-9-3 增加了此字段
    		mvo.setSubTypeDesc(rs.getString("SUB_TYPE_DESC"));
    		mvo.setExchTypeDesc(rs.getString("EXCH_TYPE_DESC"));
    		mvo.setLocalNetId(rs.getString("LOCAL_NET_ID"));
    		mvo.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
    		mvo.setName(rs.getString("NAME"));
    		mvo.setRuleAreaId(rs.getString("RULE_AREA_ID"));
    		mvo.setRuleAreaName(rs.getString("RULE_AREA_NAME"));
    		mvo.setServDeptId(rs.getString("SERV_DEPT_ID"));
    		mvo.setServDeptName(rs.getString("SERV_DEPT_NAME"));
    		mvo.setStandardCode(rs.getString("STANDARD_CODE"));
    		mvo.setSts(rs.getString("STS"));
    		mvo.setStsDate(rs.getDate("STS_DATE"));
    		mvo.setStsDesc(rs.getString("STS_DESC"));
    		mvos.add(mvo);
    	}
    	return mvos;
    }

	public List findByMVO(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		ExchMVO mvo = (ExchMVO)vo;
		List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        Sql sql = new Sql();
        sql.append(" SELECT * FROM EXCH A ");
        sql.append(" WHERE A.AREA_ID IN ");
        sql.append(" ( ");
        sql.append(" SELECT B.DATA_RANGE_ID FROM USER_DATA_RANGE B ");        
        sql.append(" WHERE B.RANGE_TYPE_ID = 'A' ");
        sql.append(" AND B.ALLOW_FLAG = 'Q' ");
        sql.append(" AND B.STS = 'A' ");
        if (!StringUtil.isBlank(mvo.getSysUserId())) {
            sql.append(" AND B.SYS_USER_ID = :sysUserId ");
            sql.setString("sysUserId", mvo.getSysUserId());
        }
        sql.append(" ) ");
        sql.append(" AND A.STS = 'A' ");
        if (!StringUtil.isBlank(mvo.getLocalNetId())) {
            sql.append(" AND A.LOCAL_NET_ID = :localNetId ");
            sql.setString("localNetId", mvo.getLocalNetId());
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
			while (rs.next()) {
				ExchMVO exchMvo = new ExchMVO();
				exchMvo.setExchId(rs.getString("EXCH_ID"));
				exchMvo.setName(rs.getString("NAME"));
				
				results.add(exchMvo);
			}
		} catch (SQLException se) {
			throw new SysException("100002", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs);
		}
        return results;
	}
}


