package com.cattsoft.sm.component.dao.oracleImpl;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IWorkAreaPrptyMDAO;
import com.cattsoft.sm.vo.WorkAreaPrptyMVO;
import com.cattsoft.sm.vo.WorkAreaSVO;

public class WorkAreaPrptyMDAOImpl implements IWorkAreaPrptyMDAO {
	private static Logger log = Logger.getLogger(WorkAreaPrptyMDAOImpl.class);
	public List findByVO(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		WorkAreaPrptyMVO workAreaPrpty = (WorkAreaPrptyMVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT WAP.ACTION_ID,WAP.AREA_ID,WAP.EXCH_ID,WAP.LOCAL_NET_ID,WAP.MATCH_VALUE,WAP.PROD_ID,WAP.REMARKS,WAP.RULE_ID,WAP.STS,WAP.STS_DATE,WAP.WORK_AREA_ID,WAP.WORK_AREA_PRPTY_ID,WAP.WORK_TYPE_ID, " +
				"LN.NAME LOCAL_NET_NAME,A.NAME AREA_NAME,E.NAME EXCH_NAME,P.NAME PROD_NAME,S.STS_WORDS,BR.NAME RULE_NAME,WA.NAME WORK_AREA_NAME,WT.NAME WORK_TYPE_NAME "+
				"FROM WORK_AREA_PRPTY WAP LEFT JOIN BUSI_RULE BR ON BR.RULE_ID = WAP.RULE_ID LEFT JOIN WORK_AREA WA ON WA.WORK_AREA_ID = WAP.WORK_AREA_ID,LOCAL_NET LN,AREA A,EXCH E,PROD P,STATUS S,WORK_TYPE WT " +
				"WHERE 1=1 AND LN.LOCAL_NET_ID = WAP.LOCAL_NET_ID AND A.AREA_ID = WAP.AREA_ID AND E.EXCH_ID = WAP.EXCH_ID AND P.PROD_ID = WAP.PROD_ID " +
				"AND S.STS_ID=WAP.STS AND S.COLUMN_NAME='STS' AND S.TABLE_NAME='WORK_AREA_PRPTY' " +
				"AND WT.WORK_TYPE_ID = WAP.WORK_TYPE_ID ");
		try {
			if (workAreaPrpty.getFlagActionId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getActionId())) {
					sql.append(" and WAP.ACTION_ID is null ");
				} else {
					sql.append(" and WAP.ACTION_ID=:actionId");
					sql.setLong("actionId", workAreaPrpty.getActionId());
				}
			}

			if (workAreaPrpty.getFlagAreaId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getAreaId())) {
					sql.append(" and WAP.AREA_ID is null ");
				} else {
					sql.append(" and WAP.AREA_ID=:areaId");
					sql.setLong("areaId", workAreaPrpty.getAreaId());
				}
			}

			if (workAreaPrpty.getFlagExchId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getExchId())) {
					sql.append(" and WAP.EXCH_ID is null ");
				} else {
					sql.append(" and WAP.EXCH_ID=:exchId");
					sql.setLong("exchId", workAreaPrpty.getExchId());
				}
			}

			if (workAreaPrpty.getFlagLocalNetId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getLocalNetId())) {
					sql.append(" and WAP.LOCAL_NET_ID is null ");
				} else {
					sql.append(" and WAP.LOCAL_NET_ID=:localNetId");
					sql.setLong("localNetId", workAreaPrpty.getLocalNetId());
				}
			}

			if (workAreaPrpty.getFlagMatchValue() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getMatchValue())) {
					sql.append(" and WAP.MATCH_VALUE is null ");
				} else {
					sql.append(" and WAP.MATCH_VALUE=:matchValue");
					sql.setString("matchValue", workAreaPrpty.getMatchValue());
				}
			}

			if (workAreaPrpty.getFlagProdId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getProdId())) {
					sql.append(" and WAP.PROD_ID is null ");
				} else {
					sql.append(" and WAP.PROD_ID=:prodId");
					sql.setString("prodId", workAreaPrpty.getProdId());
				}
			}

			if (workAreaPrpty.getFlagRemarks() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getRemarks())) {
					sql.append(" and WAP.REMARKS is null ");
				} else {
					sql.append(" and WAP.REMARKS=:remarks");
					sql.setString("remarks", workAreaPrpty.getRemarks());
				}
			}

			if (workAreaPrpty.getFlagRuleId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getRuleId())) {
					sql.append(" and WAP.RULE_ID is null ");
				} else {
					sql.append(" and WAP.RULE_ID=:ruleId");
					sql.setString("ruleId", workAreaPrpty.getRuleId());
				}
			}

			if (workAreaPrpty.getFlagSts() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getSts())) {
					sql.append(" and WAP.STS is null ");
				} else {
					sql.append(" and WAP.STS=:sts");
					sql.setString("sts", workAreaPrpty.getSts());
				}
			}

			if (workAreaPrpty.getFlagStsDate() == 1) {
				if (workAreaPrpty.getStsDate() == null) {
					sql.append(" and WAP.STS_DATE is null ");
				} else {
					sql.append(" and WAP.STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", workAreaPrpty.getStsDate());
				}
			}

			if (workAreaPrpty.getFlagWorkAreaId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getWorkAreaId())) {
					sql.append(" and WAP.WORK_AREA_ID is null ");
				} else {
					sql.append(" and WAP.WORK_AREA_ID=:workAreaId");
					sql.setLong("workAreaId", workAreaPrpty.getWorkAreaId());
				}
			}

			if (workAreaPrpty.getFlagWorkAreaPrptyId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getWorkAreaPrptyId())) {
					sql.append(" and WAP.WORK_AREA_PRPTY_ID is null ");
				} else {
					sql.append(" and WAP.WORK_AREA_PRPTY_ID=:workAreaPrptyId");
					sql.setLong("workAreaPrptyId", workAreaPrpty
							.getWorkAreaPrptyId());
				}
			}

			if (workAreaPrpty.getFlagWorkTypeId() == 1) {
				if (StringUtil.isBlank(workAreaPrpty.getWorkTypeId())) {
					sql.append(" and WAP.WORK_TYPE_ID is null ");
				} else {
					sql.append(" and WAP.WORK_TYPE_ID=:workTypeId");
					sql.setLong("workTypeId", workAreaPrpty.getWorkTypeId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				workAreaPrpty = new WorkAreaPrptyMVO();
				workAreaPrpty.setActionId(rs.getString("ACTION_ID"));
				workAreaPrpty.setAreaId(rs.getString("AREA_ID"));
				workAreaPrpty.setAreaName(rs.getString("AREA_NAME"));
				workAreaPrpty.setExchId(rs.getString("EXCH_ID"));
				workAreaPrpty.setExchName(rs.getString("EXCH_NAME"));
				workAreaPrpty.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				workAreaPrpty.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
				workAreaPrpty.setMatchValue(rs.getString("MATCH_VALUE"));
				workAreaPrpty.setProdId(rs.getString("PROD_ID"));
				workAreaPrpty.setProdName(rs.getString("PROD_NAME"));
				workAreaPrpty.setRemarks(rs.getString("REMARKS"));
				workAreaPrpty.setRuleId(rs.getString("RULE_ID"));
				workAreaPrpty.setRuleName(rs.getString("RULE_NAME"));
				workAreaPrpty.setSts(rs.getString("STS"));
				workAreaPrpty.setStsName(rs.getString("STS_WORDS"));
				workAreaPrpty.setStsDate(rs.getTimestamp("STS_DATE"));
				workAreaPrpty.setWorkAreaId(rs.getString("WORK_AREA_ID"));
				workAreaPrpty.setWorkAreaName(rs.getString("WORK_AREA_NAME"));
				workAreaPrpty.setWorkAreaPrptyId(rs.getString("WORK_AREA_PRPTY_ID"));
				workAreaPrpty.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
				workAreaPrpty.setWorkTypeName(rs.getString("WORK_TYPE_NAME"));
				res.add(workAreaPrpty);

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
	public List findWorkAreaByExchAndWorkType(String exchId, String workTypeId)
			throws AppException, SysException {
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"select wa.work_area_id,wa.name from work_area wa where wa.work_area_id in (select wae.work_area_id from work_area_exch wae where "
		);
		try {
		if(StringUtil.isBlank(exchId)){
			sql.append(" wae.exch_id is null ");
		}else {
			sql.append(" wae.exch_id = :exchId");
			sql.setLong("exchId", exchId);
		}
		sql.append(")");
		if(StringUtil.isBlank(workTypeId)){
			sql.append(" and wa.work_type_id is null) ");
		}else {
			sql.append(" and wa.work_type_id = :workTypeId ");
			sql.setLong("workTypeId", workTypeId);
		}
		conn = ConnectionFactory.getConnection();
		ps = conn.prepareStatement(sql.getSql());
		ps = sql.fillParams(ps);
		sql.log(this.getClass());
		rs = ps.executeQuery();
		WorkAreaSVO was = null;
		while(rs.next()){
			was = new WorkAreaSVO();
			was.setWorkAreaId(rs.getString("WORK_AREA_ID"));
			was.setName(rs.getString("NAME"));
			res.add(was);
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
	
	public List findWorkAreaId( String workTypeId,String localNetId,String areaId,String prodId,String exchId)throws AppException, SysException {


		List list=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Sql sql = new Sql(
				"SELECT WORK_AREA_ID,MATCH_VALUE,RULE_ID FROM WORK_AREA_PRPTY WHERE 1 = 1 and STS = 'A'");
		
		if(StringUtil.isBlank(localNetId))
		{	
			sql.append(" and LOCAL_NET_ID is null ");
			
		}else{
			sql.append(" and LOCAL_NET_ID=:localNetId");
			sql.setLong("localNetId", localNetId);
		}
		if(StringUtil.isBlank(areaId))
		{	
			sql.append(" and AREA_ID is null ");
			
		}else{
			sql.append(" and AREA_ID=:areaId");
			sql.setLong("areaId", areaId);
		}
		if(StringUtil.isBlank(prodId))
		{
			sql.append(" and PROD_ID is null ");
		}else{
			sql.append(" and PROD_ID=:prodId");
			sql.setString("prodId", prodId);
		}
		if(StringUtil.isBlank(exchId))
		{
			sql.append(" and EXCH_ID is null ");
			
		}else{
			sql.append(" and EXCH_ID=:exchId");
			sql.setLong("exchId", exchId);
			
		}
	//	sql.append(" and WORK_AREA_ID in(select WORK_AREA_ID from work_area where WORK_TYPE_ID=:workTypeId and sts ='A')");
		if(StringUtil.isBlank(workTypeId))
		{
			sql.append(" and WORK_TYPE_ID is null ");	
		}else{	
			sql.append(" and WORK_TYPE_ID=:workTypeId");
			sql.setLong("workTypeId", workTypeId);
			
		}
		WorkAreaPrptyMVO workAreaPrpty;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
			while (rs.next()) {
				workAreaPrpty = new WorkAreaPrptyMVO();
				workAreaPrpty.setWorkAreaId(rs.getString("WORK_AREA_ID"));
				workAreaPrpty.setMatchValue(rs.getString("MATCH_VALUE"));
				workAreaPrpty.setRuleId(rs.getString("RULE_ID"));
				list.add(workAreaPrpty);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		return list;
	}
	
		public List findProcedures( String ruleId) throws AppException, SysException {
		
		List list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Sql sql = new Sql(
				"SELECT CLASS_NAME,METHOD_NAME,CALL_TYPE FROM BUSI_RULE WHERE 1 = 1 and RULE_ID =:ruleId and sts ='A' ");
		sql.setLong("ruleId", ruleId);
		String ret;
		String ret1;
		String ret2;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
			while (rs.next()) {
				ret=rs.getString("CALL_TYPE");
				ret1=rs.getString("CLASS_NAME");
				ret2=rs.getString("METHOD_NAME");
				list.add(ret);
				list.add(ret1);
				list.add(ret2);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
		return list;
		}
		
		public String[] callProc(String procedure,String localNetId,String areaId,String workTypeId,String exchId,String soNbr,String ActType,String AzFlag,String resPrptyId,String prptyType) throws AppException, SysException{
			
			CallableStatement proc = null;
			String[] returnS=new String[2];
			 try{
				 Connection conn = ConnectionFactory.getConnection();
				 String sql = "{call "+procedure+"(?,?,?,?,?,?,?,?,?,?,?) }";   //存放存储过程名称
				 proc = conn.prepareCall(sql);
				 
				 proc.setString(1, localNetId);
				 proc.setString(2, areaId);
				 proc.setString(3, workTypeId);
				 proc.setString(4, exchId);
				 proc.setString(5, soNbr);
				 proc.setString(6, ActType);
				 proc.setString(7, AzFlag);
				 proc.setString(8, resPrptyId);
				 proc.setString(9, prptyType);
				 proc.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR); 
				 proc.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR); 
				 proc.execute();
				 returnS[0] = proc.getString(10);
				 returnS[1] = proc.getString(11);	//返回oReturnS 
				 
			 }catch(SQLException e){
				 throw new SysException("100003", "JDBC操作异常！"+e.getMessage(), e);
			 }catch(Exception e){
				 throw new SysException("100003", e.getMessage(), e);
			 }
			 return returnS;
			 
		}
			public String[] callJava(String className,String methodName,String localNetId,String areaId,String workTypeId,String exchId,String soNbr,String ActType,String AzFlag,String resPrptyId,String prptyType) throws AppException,SysException{
				String[] ret = new String[2];
				try {
					Class c = Class.forName(className);
					Method m = c.getMethod(methodName, new Class[]{localNetId.getClass(),areaId.getClass(),workTypeId.getClass(),exchId.getClass(),soNbr.getClass(),ActType.getClass(),AzFlag.getClass(),resPrptyId.getClass(),prptyType.getClass()});
					Object obj =m.invoke(c.newInstance(), new Object[]{localNetId,areaId,workTypeId,exchId,soNbr,ActType,AzFlag});
					if(obj!=null){
						ret[1] = obj.toString();
					}
					ret[0]=null;
				} catch (Exception e) {
					throw new SysException("null"," 类"+className+"的方法"+methodName+"的组合元素的值！"+e.getMessage(),e);
				}
				return ret;
			}
			
			public List findWorkAreaList(String workTypeId,String localNetId,String areaId,String prodId,String exchId)throws AppException, SysException {
				List list=null; 
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;

				Sql sql = new Sql(
						"SELECT * FROM (SELECT AREA_ID,EXCH_ID,LOCAL_NET_ID,MATCH_VALUE,PRIORITY,PROD_ID,PRPTY_ID,PRPTY_TYPE,RULE_ID,WORK_AREA_ID,WORK_TYPE_ID FROM WORK_AREA_PRPTY WHERE STS = 'A' " +
						" and LOCAL_NET_ID=:localNetId");
					sql.setLong("localNetId", localNetId);  
					sql.append(" and AREA_ID=:areaId");
					sql.setLong("areaId", areaId); 
					sql.append(" and PROD_ID=:prodId");
					sql.setString("prodId", prodId);  
					sql.append(" and EXCH_ID=:exchId");
					sql.setLong("exchId", exchId);  
					sql.append(" and WORK_TYPE_ID=:workTypeId");
					sql.setLong("workTypeId", workTypeId); 
				sql.append(" UNION ALL ");
				sql.append(" SELECT AREA_ID,EXCH_ID,LOCAL_NET_ID,MATCH_VALUE,PRIORITY,PROD_ID,PRPTY_ID,PRPTY_TYPE,RULE_ID,WORK_AREA_ID,WORK_TYPE_ID FROM WORK_AREA_PRPTY WHERE STS = 'A' and LOCAL_NET_ID=:localNetId");
				sql.setLong("localNetId", localNetId);  
				sql.append(" and AREA_ID=:areaId");
				sql.setLong("areaId", areaId); 
				sql.append(" and PROD_ID='0'");
				sql.append(" and EXCH_ID=:exchId");
				sql.setLong("exchId", exchId);  
				sql.append(" and WORK_TYPE_ID=:workTypeId");
				sql.setLong("workTypeId", workTypeId); 
				sql.append(" UNION ALL ");
				sql.append(" SELECT AREA_ID,EXCH_ID,LOCAL_NET_ID,MATCH_VALUE,PRIORITY,PROD_ID,PRPTY_ID,PRPTY_TYPE,RULE_ID,WORK_AREA_ID,WORK_TYPE_ID FROM WORK_AREA_PRPTY WHERE STS = 'A' and LOCAL_NET_ID=:localNetId");
				sql.setLong("localNetId", localNetId);  
				sql.append(" and AREA_ID=:areaId");
				sql.setLong("areaId", areaId); 
				sql.append(" and PROD_ID=:prodId");
				sql.setString("prodId", prodId);  
				sql.append(" and EXCH_ID=0");  
				sql.append(" and WORK_TYPE_ID=:workTypeId");
				sql.setLong("workTypeId", workTypeId); 
				sql.append(" UNION ALL ");
				sql.append(" SELECT AREA_ID,EXCH_ID,LOCAL_NET_ID,MATCH_VALUE,PRIORITY,PROD_ID,PRPTY_ID,PRPTY_TYPE,RULE_ID,WORK_AREA_ID,WORK_TYPE_ID FROM WORK_AREA_PRPTY WHERE STS = 'A' and LOCAL_NET_ID=:localNetId");
				sql.setLong("localNetId", localNetId);  
				sql.append(" and AREA_ID=0"); 
				sql.append(" and PROD_ID=:prodId");
				sql.setString("prodId", prodId);  
				sql.append(" and EXCH_ID=0");  
				sql.append(" and WORK_TYPE_ID=:workTypeId");
				sql.setLong("workTypeId", workTypeId); 
				sql.append(" UNION ALL ");
				sql.append(" SELECT AREA_ID,EXCH_ID,LOCAL_NET_ID,MATCH_VALUE,PRIORITY,PROD_ID,PRPTY_ID,PRPTY_TYPE,RULE_ID,WORK_AREA_ID,WORK_TYPE_ID FROM WORK_AREA_PRPTY WHERE STS = 'A' and LOCAL_NET_ID=:localNetId");
				sql.setLong("localNetId", localNetId);  
				sql.append(" and AREA_ID=:areaId");
				sql.setLong("areaId", areaId); 
				sql.append(" and PROD_ID='0'");
				sql.append(" and EXCH_ID='0'"); 
				sql.append(" and WORK_TYPE_ID=:workTypeId");
				sql.setLong("workTypeId", workTypeId); 
				sql.append(" UNION ALL ");
				sql.append(" SELECT AREA_ID,EXCH_ID,LOCAL_NET_ID,MATCH_VALUE,PRIORITY,PROD_ID,PRPTY_ID,PRPTY_TYPE,RULE_ID,WORK_AREA_ID,WORK_TYPE_ID FROM WORK_AREA_PRPTY WHERE STS = 'A' and LOCAL_NET_ID=:localNetId");
				sql.setLong("localNetId", localNetId);  
				sql.append(" and AREA_ID=0"); 
				sql.append(" and PROD_ID='0'");
				sql.append(" and EXCH_ID='0'"); 
				sql.append(" and WORK_TYPE_ID=:workTypeId");
				sql.setLong("workTypeId", workTypeId); 
				sql.append(") order by priority");
				WorkAreaPrptyMVO workAreaPrpty;
				try {
					conn = ConnectionFactory.getConnection();
					ps = conn.prepareStatement(sql.getSql());
					ps = sql.fillParams(ps);
					sql.log(this.getClass());
					rs = ps.executeQuery();
					list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
					while (rs.next()) {
						workAreaPrpty = new WorkAreaPrptyMVO();
						workAreaPrpty.setMatchValue(rs.getString("MATCH_VALUE"));
						workAreaPrpty.setProdId(rs.getString("PROD_ID"));
						workAreaPrpty.setPrptyType(rs.getString("PRPTY_TYPE"));
						workAreaPrpty.setLocalNetId(rs.getString("LOCAL_NET_ID"));
						workAreaPrpty.setAreaId(rs.getString("AREA_ID"));
						workAreaPrpty.setExchId(rs.getString("EXCH_ID"));
						workAreaPrpty.setPrptyId(rs.getString("PRPTY_ID"));
						workAreaPrpty.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
						workAreaPrpty.setWorkAreaId(rs.getString("WORK_AREA_ID"));
						workAreaPrpty.setMatchValue(rs.getString("MATCH_VALUE"));
						workAreaPrpty.setRuleId(rs.getString("RULE_ID"));
						list.add(workAreaPrpty);
					}
				} catch (SQLException se) {
					throw new SysException("100003", "JDBC操作异常！", se);
				} finally {
					JdbcUtil.close(rs, ps);
				}

				return list;
			}
}
