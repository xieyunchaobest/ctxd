package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IBulletinSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.AttachmentMVO;
import com.cattsoft.sm.vo.BulletinMVO;
import com.cattsoft.sm.vo.BulletinSVO;

public class BulletinSDAOImpl implements IBulletinSDAO {
	
	private static Logger log = Logger.getLogger(BulletinSDAOImpl.class);

	public void add(GenericVO vo) throws AppException, SysException {
		BulletinSVO bulletin = (BulletinSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" BULLETIN(BULLETIN_ID,TITLE,LOCAL_NET_ID,AREA_ID,SERV_DEPT_ID,BRANCH_ID,CONTENT,SYSTEM_NAME,TYPE,CREATER,CREATE_DATE,RELEASE_DATE,END_DATE,STS) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, bulletin.getBulletinId());
			ps.setString(2, bulletin.getTitle());
			ps.setString(3, bulletin.getLocalNetId());
			ps.setString(4, bulletin.getAreaId());
			ps.setString(5, bulletin.getServDeptId());
			ps.setString(6, bulletin.getBranchId());
			ps.setString(7, bulletin.getContent());
			ps.setString(8, bulletin.getSystemName());
			ps.setString(9, bulletin.getType());
			ps.setString(10, bulletin.getCreater());
			ps.setTimestamp(11, bulletin.getCreateDate());
			ps.setTimestamp(12, bulletin.getReleaseDate());
			ps.setTimestamp(13, bulletin.getEndDate());
			ps.setString(14, bulletin.getSts());
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
	public String findLocalNetName(String id) throws AppException, SysException{
		
		Sql sql = new Sql(
		"SELECT NAME FROM LOCAL_NET WHERE 1=1  ");
        sql.append(" and LOCAL_NET_ID=:localNetId");
        sql.setLong("localNetId", id);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name=null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			
			while(rs.next()){
			 name =rs.getString(1);
			}
			
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return name;
		
	}
	
public String findAreaName(String id) throws AppException, SysException{
		
	Sql sql = new Sql(
	"SELECT NAME FROM AREA WHERE 1=1  ");
    sql.append(" and AREA_ID=:areaId");
    sql.setLong("areaId", id);

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String name=null;
	try {
		conn = ConnectionFactory.getConnection();
		ps = conn.prepareStatement(sql.getSql());
		sql.fillParams(ps);
		sql.log(this.getClass());
		rs = ps.executeQuery();

		while(rs.next()){
		 name =rs.getString("NAME");
		}
		
	} catch (SQLException se) {
		throw new SysException("100003", "JDBC操作异常！", se);

	} finally {
		JdbcUtil.close(rs, ps);
	}
	return name;
	}
	public void update(GenericVO vo) throws AppException, SysException {
		BulletinSVO bulletin = (BulletinSVO) vo;
		StringBuffer sql = new StringBuffer("update BULLETIN set");
		if (bulletin.getTitle() != null) {
			sql.append(" TITLE=?,");
		}
		if (bulletin.getLocalNetId() != null) {
			sql.append(" LOCAL_NET_ID=?,");
		}
		if (bulletin.getAreaId() != null) {
			sql.append(" AREA_ID=?,");
		}
		if (bulletin.getServDeptId() != null) {
			sql.append(" SERV_DEPT_ID=?,");
		}
		if (bulletin.getBranchId() != null) {
			sql.append(" BRANCH_ID=?,");
		}
		if (bulletin.getContent() != null) {
			sql.append(" CONTENT=?,");
		}
		if (bulletin.getSystemName() != null) {
			sql.append(" SYSTEM_NAME=?,");
		}
		if (bulletin.getType() != null) {
			sql.append(" TYPE=?,");
		}
		if (bulletin.getCreater() != null) {
			sql.append(" CREATER=?,");
		}
		if (bulletin.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		if (bulletin.getReleaseDate() != null) {
			sql.append(" RELEASE_DATE=?,");
		}
		if (bulletin.getEndDate() != null) {
			sql.append(" END_DATE=?,");
		}
		if (bulletin.getSts() != null) {
			sql.append(" STS=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and BULLETIN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (bulletin.getTitle() != null) {
				ps.setString(index++, bulletin.getTitle());
			}
			if (bulletin.getLocalNetId() != null) {
				ps.setString(index++, bulletin.getLocalNetId());
			}
			if (bulletin.getAreaId() != null) {
				ps.setString(index++, bulletin.getAreaId());
			}
			if (bulletin.getServDeptId() != null) {
				ps.setString(index++, bulletin.getServDeptId());
			}
			if (bulletin.getBranchId() != null) {
				ps.setString(index++, bulletin.getBranchId());
			}
			if (bulletin.getContent() != null) {
				ps.setString(index++, bulletin.getContent());
			}
			if (bulletin.getSystemName() != null) {
				ps.setString(index++, bulletin.getSystemName());
			}
			if (bulletin.getType() != null) {
				ps.setString(index++, bulletin.getType());
			}
			if (bulletin.getCreater() != null) {
				ps.setString(index++, bulletin.getCreater());
			}
			if (bulletin.getCreateDate() != null) {
				ps.setTimestamp(index++, bulletin.getCreateDate());
			}
			if (bulletin.getReleaseDate() != null) {
				ps.setTimestamp(index++, bulletin.getReleaseDate());
			}
			if (bulletin.getEndDate() != null) {
				ps.setTimestamp(index++, bulletin.getEndDate());
			}
			if (bulletin.getSts() != null) {
				ps.setString(index++, bulletin.getSts());
			}
			ps.setString(index++, bulletin.getBulletinId());
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
		BulletinSVO bulletin = (BulletinSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from BULLETIN where 1=1");
		sql.append(" and BULLETIN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, bulletin.getBulletinId());
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
		BulletinMVO result = new BulletinMVO();
		
		BulletinMVO bulletin =(BulletinMVO)vo;
		
		StringBuffer sql = new StringBuffer("select");
		
        sql.append(" a.BULLETIN_ID,d.STS_WORDS STS_WORDS,b.name LOCAL_NET_NAME,c.name AREA_NAME,a.TITLE,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.CONTENT,a.SYSTEM_NAME,a.TYPE,a.CREATER,a.CREATE_DATE,a.RELEASE_DATE,a.END_DATE,a.STS");
        sql.append(" from BULLETIN a ");
        sql.append(" join LOCAL_NET b on  a.local_net_id=b.local_net_id  ");
        sql.append(" join AREA c on  a.area_id=c.area_id  ");
        sql.append(" join STATUS d on a.sts=d.sts_id and table_name='BULLETIN' ");
        
        
        sql.append(" where 1=1");
		sql.append(" and BULLETIN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			log.debug(sql.toString());
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, bulletin.getBulletinId());
			rs = ps.executeQuery();
			if(rs.next()){
				result.setAreaId(rs.getString("AREA_ID"));
				result.setAreaName(rs.getString("AREA_NAME"));
				result.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
				result.setCreater(rs.getString("CREATER"));
				result.setTitle(rs.getString("TITLE"));
				result.setContent(rs.getString("CONTENT"));
				result.setReleaseDate(rs.getTimestamp("RELEASE_DATE"));
				result.setBulletinId(rs.getString("BULLETIN_ID"));
				result.setBranchId(rs.getString("BRANCH_ID"));
				result.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				result.setEndDate(rs.getTimestamp("END_DATE"));
				result.setServDeptId(rs.getString("SERV_DEPT_ID"));
				result.setSts(rs.getString("STS"));
				result.setType(rs.getString("TYPE"));
				result.setSystemName(rs.getString("SYSTEM_NAME"));
				result.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				
			}
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
		BulletinSVO bulletin = (BulletinSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.BULLETIN_ID,a.TITLE,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.CONTENT,a.SYSTEM_NAME,a.TYPE,a.CREATER,a.CREATE_DATE,a.RELEASE_DATE,a.END_DATE,a.STS");
		sql.append(" from BULLETIN a where 1=1");
		if (bulletin.getBulletinId() != null) {
			sql.append(" and BULLETIN_ID=?");
		}
		if (bulletin.getTitle() != null) {
			sql.append(" and TITLE=?");
		}
		if (bulletin.getLocalNetId() != null) {
			sql.append(" and LOCAL_NET_ID=?");
		}
		if (bulletin.getAreaId() != null) {
			sql.append(" and AREA_ID=?");
		}
		if (bulletin.getServDeptId() != null) {
			sql.append(" and SERV_DEPT_ID=?");
		}
		if (bulletin.getBranchId() != null) {
			sql.append(" and BRANCH_ID=?");
		}
		if (bulletin.getContent() != null) {
			sql.append(" and CONTENT=?");
		}
		if (bulletin.getSystemName() != null) {
			sql.append(" and SYSTEM_NAME=?");
		}
		if (bulletin.getType() != null) {
			sql.append(" and TYPE=?");
		}
		if (bulletin.getCreater() != null) {
			sql.append(" and CREATER=?");
		}
		if (bulletin.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (bulletin.getReleaseDate() != null) {
			sql.append(" and RELEASE_DATE=?");
		}
		if (bulletin.getEndDate() != null) {
			sql.append(" and END_DATE=?");
		}
		if (bulletin.getSts() != null) {
			sql.append(" and STS=?");
		}
       
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (bulletin.getBulletinId() != null) {
				ps.setString(index++, bulletin.getBulletinId());
			}
			if (bulletin.getTitle() != null) {
				ps.setString(index++, bulletin.getTitle());
			}
			if (bulletin.getLocalNetId() != null) {
				ps.setString(index++, bulletin.getLocalNetId());
			}
			if (bulletin.getAreaId() != null) {
				ps.setString(index++, bulletin.getAreaId());
			}
			if (bulletin.getServDeptId() != null) {
				ps.setString(index++, bulletin.getServDeptId());
			}
			if (bulletin.getBranchId() != null) {
				ps.setString(index++, bulletin.getBranchId());
			}
			if (bulletin.getContent() != null) {
				ps.setString(index++, bulletin.getContent());
			}
			if (bulletin.getSystemName() != null) {
				ps.setString(index++, bulletin.getSystemName());
			}
			if (bulletin.getType() != null) {
				ps.setString(index++, bulletin.getType());
			}
			if (bulletin.getCreater() != null) {
				ps.setString(index++, bulletin.getCreater());
			}
			if (bulletin.getCreateDate() != null) {
				ps.setTimestamp(index++, bulletin.getCreateDate());
			}
			if (bulletin.getReleaseDate() != null) {
				ps.setTimestamp(index++, bulletin.getReleaseDate());
			}
			if (bulletin.getEndDate() != null) {
				ps.setTimestamp(index++, bulletin.getEndDate());
			}
			if (bulletin.getSts() != null) {
				ps.setString(index++, bulletin.getSts());
			}
          
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, BulletinSVO.class);
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
    public List findByDate(GenericVO vo,Timestamp date) throws AppException, SysException {
        List results = null;
        BulletinSVO bulletin = (BulletinSVO) vo;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.BULLETIN_ID,a.TITLE,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.CONTENT,a.SYSTEM_NAME,a.TYPE,a.CREATER,a.CREATE_DATE,a.RELEASE_DATE,a.END_DATE,a.STS");
        sql.append(" from BULLETIN a where 1=1");
        if (bulletin.getBulletinId() != null) {
            sql.append(" and BULLETIN_ID=?");
        }
        if (bulletin.getTitle() != null) {
            sql.append(" and TITLE=?");
        }
        if (bulletin.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (bulletin.getAreaId() != null) {
            sql.append(" and AREA_ID=?");
        }
        if (bulletin.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=?");
        }
        if (bulletin.getBranchId() != null) {
            sql.append(" and BRANCH_ID=?");
        }
        if (bulletin.getContent() != null) {
            sql.append(" and CONTENT=?");
        }
        if (bulletin.getSystemName() != null) {
            sql.append(" and SYSTEM_NAME=?");
        }
        if (bulletin.getType() != null) {
            sql.append(" and TYPE=?");
        }
        if (bulletin.getCreater() != null) {
            sql.append(" and CREATER=?");
        }
        if (bulletin.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        if (bulletin.getReleaseDate() != null) {
            sql.append(" and RELEASE_DATE=?");
        }
        if (bulletin.getEndDate() != null) {
            sql.append(" and END_DATE=?");
        }
        if (bulletin.getSts() != null) {
            sql.append(" and STS=?");
        }
        if(date!=null){
            sql.append(" and to_char(RELEASE_DATE,'yyyy-mm-dd hh24:mi:ss')<=? and to_char(end_date,'yyyy-mm-dd hh24:mi:ss')>=?");
        }
        sql.append(" order by release_date desc");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (bulletin.getBulletinId() != null) {
                ps.setString(index++, bulletin.getBulletinId());
            }
            if (bulletin.getTitle() != null) {
                ps.setString(index++, bulletin.getTitle());
            }
            if (bulletin.getLocalNetId() != null) {
                ps.setString(index++, bulletin.getLocalNetId());
            }
            if (bulletin.getAreaId() != null) {
                ps.setString(index++, bulletin.getAreaId());
            }
            if (bulletin.getServDeptId() != null) {
                ps.setString(index++, bulletin.getServDeptId());
            }
            if (bulletin.getBranchId() != null) {
                ps.setString(index++, bulletin.getBranchId());
            }
            if (bulletin.getContent() != null) {
                ps.setString(index++, bulletin.getContent());
            }
            if (bulletin.getSystemName() != null) {
                ps.setString(index++, bulletin.getSystemName());
            }
            if (bulletin.getType() != null) {
                ps.setString(index++, bulletin.getType());
            }
            if (bulletin.getCreater() != null) {
                ps.setString(index++, bulletin.getCreater());
            }
            if (bulletin.getCreateDate() != null) {
                ps.setTimestamp(index++, bulletin.getCreateDate());
            }
            if (bulletin.getReleaseDate() != null) {
                ps.setTimestamp(index++, bulletin.getReleaseDate());
            }
            if (bulletin.getEndDate() != null) {
                ps.setTimestamp(index++,bulletin.getEndDate());
            }
            if (bulletin.getSts() != null) {
                ps.setString(index++, bulletin.getSts());
            }
            if(date!=null){
                ps.setString(index++, df.format(date));
                ps.setString(index++, df.format(date));
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, BulletinSVO.class);
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
    
    public PagView findBulletinByPage(BulletinMVO mvo, HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.BULLETIN_ID,d.STS_WORDS STS_WORDS,b.name LOCAL_NET_NAME,c.name AREA_NAME,a.TITLE,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.CONTENT,a.SYSTEM_NAME,a.TYPE,a.CREATER,a.CREATE_DATE,a.RELEASE_DATE,a.END_DATE,a.STS");
        sql.append(" from BULLETIN a ");
        sql.append(" join LOCAL_NET b on  a.local_net_id=b.local_net_id  ");
        sql.append(" join AREA c on  a.area_id=c.area_id  ");
        sql.append(" join STATUS d on a.sts=d.sts_id and table_name='BULLETIN' ");
        
        
        sql.append(" where 1=1");
        
        
        if (mvo == null && set != null) {
            sql.append("and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "bulletin_id", "bulletinId"));
            sql.append(" ) ");
        }
        if (mvo != null && set == null) {
        if (!StringUtil.isBlank(mvo.getBvo().getBulletinId()) ) {
            sql.append(" and BULLETIN_ID="+mvo.getBvo().getBulletinId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getTitle())) {
            sql.append(" and TITLE like '%"+mvo.getBvo().getTitle()+"%'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getLocalNetId())) {
            sql.append(" and LOCAL_NET_ID="+mvo.getBvo().getLocalNetId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getAreaId())) {
            sql.append(" and AREA_ID="+mvo.getBvo().getAreaId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getServDeptId())) {
            sql.append(" and SERV_DEPT_ID="+mvo.getBvo().getServDeptId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getBranchId())) {
            sql.append(" and BRANCH_ID="+mvo.getBvo().getBranchId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getContent())) {
            sql.append(" and CONTENT like '%"+mvo.getBvo().getContent()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getSystemName())) {
            sql.append(" and SYSTEM_NAME='"+mvo.getBvo().getSystemName()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getType())) {
            sql.append(" and TYPE='"+mvo.getBvo().getType() +"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getCreater())) {
            sql.append(" and CREATER='"+mvo.getBvo().getCreater()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getSts())) {
            sql.append(" and STS='"+mvo.getBvo().getSts()+"'");
        }
        }
        sql.append(" order by bulletin_id");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            log.debug(sql.toString());
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(BulletinSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, BulletinSVO.class);
           
            pagView.setViewList(results);
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
        return pagView;
    }
    
    public PagView findList(GenericVO vo, PagInfo pagInfo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		BulletinMVO mvo = (BulletinMVO) vo;
		PagView pagView = null;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		ResultSet rs = null;

		Sql sql = new Sql();

		sql.append("select");
        sql.append(" a.BULLETIN_ID,d.STS_WORDS STS_WORDS,e.STS_WORDS TYPE_NAME,b.name LOCAL_NET_NAME,c.name AREA_NAME,a.TITLE,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.CONTENT,a.SYSTEM_NAME,a.TYPE,a.CREATER,a.CREATE_DATE,a.RELEASE_DATE,a.END_DATE,a.STS ");
        sql.append(" from BULLETIN a ");
        sql.append(" join LOCAL_NET b on  a.local_net_id=b.local_net_id  ");
        sql.append(" join AREA c on  a.area_id=c.area_id  ");
        sql.append(" join STATUS d on a.sts=d.sts_id and d.table_name='BULLETIN' and d.column_name='STS' ");
        sql.append(" join STATUS e on a.type=e.sts_id and e.table_name='BULLETIN' and e.column_name='TYPE' ");
        
        sql.append(" where 1=1");
        
        if (mvo != null ) {
        if (!StringUtil.isBlank(mvo.getBvo().getBulletinId()) ) {
            sql.append(" and A.BULLETIN_ID="+mvo.getBvo().getBulletinId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getTitle())) {
            sql.append(" and A.TITLE like '%"+mvo.getBvo().getTitle()+"%'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getLocalNetId())) {
            sql.append(" and A.LOCAL_NET_ID="+mvo.getBvo().getLocalNetId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getAreaId())) {
            sql.append(" and A.AREA_ID="+mvo.getBvo().getAreaId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getServDeptId())) {
            sql.append(" and A.SERV_DEPT_ID="+mvo.getBvo().getServDeptId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getBranchId())) {
            sql.append(" and A.BRANCH_ID="+mvo.getBvo().getBranchId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getContent())) {
            sql.append(" and A.CONTENT like '%"+mvo.getBvo().getContent()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getSystemName())) {
            sql.append(" and A.SYSTEM_NAME='"+mvo.getBvo().getSystemName()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getType())) {
            sql.append(" and A.TYPE='"+mvo.getBvo().getType() +"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getCreater())) {
            sql.append(" and A.CREATER='"+mvo.getBvo().getCreater()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getSts())) {
            sql.append(" and A.STS='"+mvo.getBvo().getSts()+"'");
        }
        }
        sql.append(" order by bulletin_id");

		try {
			//System.out.println("sql------------->"+sql);
			conn = ConnectionFactory.getConnection();
			BulletinMVO wo = null;
			pagView = PagUtil.InitPagViewJDBC(conn, sql, pagInfo);
			rs = PagUtil.queryOracle(conn, sql, pagInfo);
			sql.log(this.getClass());
			while (rs.next()) {

				wo = new BulletinMVO();
				wo.setBulletinId(rs.getString("BULLETIN_ID"));
				wo.setAreaName(rs.getString("AREA_NAME"));
				wo.setContent(rs.getString("CONTENT"));
				wo.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				wo.setEndDate(rs.getTimestamp("END_DATE"));
				wo.setTitle(rs.getString("TITLE"))
;				wo.setCreater(rs.getString("CREATER"));
				wo.setAreaId(rs.getString("AREA_ID"));
				wo.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				wo.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
				wo.setStsWords(rs.getString("STS_WORDS"));
				wo.setSts(rs.getString("STS"));
				wo.setTypeName(rs.getString("TYPE_NAME"));
				res.add(wo);

			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs);
		}

		if (0 == res.size())
			res = null;
		pagView.setViewList(res);

		return pagView;
	}
    
    
    public PagView findList4MOS(GenericVO vo, PagInfo pagInfo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		BulletinMVO mvo = (BulletinMVO) vo;
		PagView pagView = null;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		ResultSet rs = null;

		Sql sql = new Sql();

		sql.append("select");
        sql.append(" a.BULLETIN_ID,d.STS_WORDS STS_WORDS,e.STS_WORDS TYPE_NAME,b.name LOCAL_NET_NAME,c.name AREA_NAME,a.TITLE,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.CONTENT,a.SYSTEM_NAME,a.TYPE,a.CREATER,a.CREATE_DATE,a.RELEASE_DATE,a.END_DATE,a.STS ");
        sql.append(" from BULLETIN a ");
        sql.append(" join LOCAL_NET b on  a.local_net_id=b.local_net_id  ");
        sql.append(" join AREA c on  a.area_id=c.area_id  ");
        sql.append(" join STATUS d on a.sts=d.sts_id and d.table_name='BULLETIN' and d.column_name='STS' ");
        sql.append(" join STATUS e on a.type=e.sts_id and e.table_name='BULLETIN' and e.column_name='TYPE' ");
        
        sql.append(" where 1=1");
        
        if (mvo != null ) {
        if (!StringUtil.isBlank(mvo.getBvo().getBulletinId()) ) {
            sql.append(" and A.BULLETIN_ID="+mvo.getBvo().getBulletinId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getTitle())) {
            sql.append(" and A.TITLE like '%"+mvo.getBvo().getTitle()+"%'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getLocalNetId())) {
            sql.append(" and (A.LOCAL_NET_ID="+mvo.getBvo().getLocalNetId() + " or A.LOCAL_NET_ID=0)");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getAreaId())) {
            sql.append(" and (A.AREA_ID="+mvo.getBvo().getAreaId() + " OR A.AREA_ID=0)");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getServDeptId())) {
            sql.append(" and A.SERV_DEPT_ID="+mvo.getBvo().getServDeptId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getBranchId())) {
            sql.append(" and A.BRANCH_ID="+mvo.getBvo().getBranchId());
        }
        if (!StringUtil.isBlank(mvo.getBvo().getContent())) {
            sql.append(" and A.CONTENT like '%"+mvo.getBvo().getContent()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getSystemName())) {
            sql.append(" and A.SYSTEM_NAME='"+mvo.getBvo().getSystemName()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getType())) {
            sql.append(" and A.TYPE='"+mvo.getBvo().getType() +"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getCreater())) {
            sql.append(" and A.CREATER='"+mvo.getBvo().getCreater()+"'");
        }
        if (!StringUtil.isBlank(mvo.getBvo().getSts())) {
            sql.append(" and A.STS='"+mvo.getBvo().getSts()+"'");
        }
        }
        sql.append(" and sysdate between A.release_date and a.end_date");
        sql.append(" order by bulletin_id");

		try {
			//System.out.println("sql------------->"+sql);
			conn = ConnectionFactory.getConnection();
			BulletinMVO wo = null;
			pagView = PagUtil.InitPagViewJDBC(conn, sql, pagInfo);
			rs = PagUtil.queryOracle(conn, sql, pagInfo);
			sql.log(this.getClass());
			while (rs.next()) {

				wo = new BulletinMVO();
				wo.setBulletinId(rs.getString("BULLETIN_ID"));
				wo.setAreaName(rs.getString("AREA_NAME"));
				wo.setContent(rs.getString("CONTENT"));
				wo.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				wo.setEndDate(rs.getTimestamp("END_DATE"));
				wo.setReleaseDate(rs.getTimestamp("RELEASE_DATE"));
				wo.setTitle(rs.getString("TITLE"))
;				wo.setCreater(rs.getString("CREATER"));
				wo.setAreaId(rs.getString("AREA_ID"));
				wo.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				wo.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
				wo.setStsWords(rs.getString("STS_WORDS"));
				wo.setSts(rs.getString("STS"));
				wo.setTypeName(rs.getString("TYPE_NAME"));
				res.add(wo);

			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs);
		}

		if (0 == res.size())
			res = null;
		pagView.setViewList(res);

		return pagView;
	}

    public List findByMVO(BulletinMVO mvo) throws AppException, SysException {
        List results = null;
        StringBuffer sql = new StringBuffer();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
       sql.append("select");
       sql.append(" a.BULLETIN_ID,a.TITLE,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.CONTENT,a.SYSTEM_NAME,a.TYPE,a.CREATER,a.CREATE_DATE,a.RELEASE_DATE,a.END_DATE,a.STS");
        sql.append(" from BULLETIN a where 1=1 ");   
        if (mvo.getBulletinId() != null) {
            sql.append(" and BULLETIN_ID="+mvo.getBulletinId());
        }
        if (mvo.getTitle() != null) {
            sql.append(" and TITLE like '%"+mvo.getTitle()+"%'");
        }
        if (mvo.getLocalNetId() != null) {
        	String localNetID=mvo.getLocalNetId();
        	int num=localNetID.indexOf(",");
        	if(num==-1){
        		sql.append(" and LOCAL_NET_ID="+mvo.getLocalNetId());
        	}else{
        	    sql.append("and LOCAL_NET_ID IN ("+mvo.getLocalNetId()+")");
        	    }
        }
        if (mvo.getAreaId() != null) {
            sql.append(" and AREA_ID="+mvo.getAreaId());
        }
        if (mvo.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID="+mvo.getServDeptId());
        }
        if (mvo.getBranchId() != null) {
            sql.append(" and BRANCH_ID="+mvo.getBranchId());
        }
        if (mvo.getContent() != null) {
            sql.append(" and CONTENT like '%"+mvo.getContent()+"'");
        }
        if (mvo.getSystemName() != null) {
            sql.append(" and SYSTEM_NAME='"+mvo.getSystemName()+"'");
        }
        if (mvo.getType() != null) {
            sql.append(" and TYPE='"+mvo.getType() +"'");
        }
        if (mvo.getCreater() != null) {
            sql.append(" and CREATER='"+mvo.getCreater()+"'");
        }
        if (mvo.getReleaseDate() != null) {
            
            sql.append(" and to_char(RELEASE_DATE,'yyyy-mm-dd hh24:mi:ss')>='" +df.format(mvo.getReleaseDate()) +
            "'");
        }
        if(mvo.getReleaseDate2()!=null){
            sql.append(" and to_char(release_date,'yyyy-mm-dd hh24:mi:ss')<='" + df.format(mvo.getReleaseDate2()) +
            "'");
        }
        if (mvo.getEndDate() != null) {
            sql.append(" and to_char(end_date,'yyyy-mm-dd hh24:mi:ss')>='"+mvo.getEndDate()+"'");
        }
        if(mvo.getEndDate2()!=null){
            sql.append(" and to_char(end_date,'yyyy-mm-dd hh24:mi:ss')<='"+mvo.getEndDate2()+"'");
        }
        if (mvo.getSts() != null) {
            sql.append(" and STS='"+mvo.getSts()+"'");
        }
        sql.append(" order by release_date desc");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            log.debug(sql.toString());
            
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, BulletinSVO.class);
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
    
    public List findBulletinByNow(BulletinMVO mvo,int rownum) throws AppException, SysException {
        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        StringBuffer sql = new StringBuffer();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       sql.append("select * from ( ");
       sql.append("select");
       sql.append(" a.BULLETIN_ID,a.TITLE,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.CONTENT,a.SYSTEM_NAME,a.TYPE,a.CREATER,a.CREATE_DATE,a.RELEASE_DATE,a.END_DATE,a.STS");

       sql.append(" ,(select FILE_NAME from ATTACHMENT b where a.BULLETIN_ID=b.OBJECT_ID and b.FOR_SCENE='B') FILE_NAME");
       sql.append(" ,(select ATTACHMENT_ID from ATTACHMENT b where a.BULLETIN_ID=b.OBJECT_ID and b.FOR_SCENE='B') ATTACHMENT_ID");
        sql.append(" from BULLETIN a where 1=1 ");
        if (mvo.getBulletinId() != null) {
            sql.append(" and BULLETIN_ID="+mvo.getBulletinId());
        }
        if (mvo.getTitle() != null) {
            sql.append(" and TITLE like '%"+mvo.getTitle()+"%'");
        }
        if (mvo.getLocalNetId() != null) {
        	String localNetID=mvo.getLocalNetId();
        	int num=localNetID.indexOf(",");
        	if(num==-1){
        		sql.append(" and LOCAL_NET_ID="+mvo.getLocalNetId());
        	}else{
        	    sql.append("and LOCAL_NET_ID IN ("+mvo.getLocalNetId()+")");
        	    }
        }
        if (mvo.getAreaId() != null) {
            sql.append(" and AREA_ID="+mvo.getAreaId());
        }
        if (mvo.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID="+mvo.getServDeptId());
        }
        if (mvo.getBranchId() != null) {
            sql.append(" and BRANCH_ID="+mvo.getBranchId());
        }
        if (mvo.getContent() != null) {
            sql.append(" and CONTENT like '%"+mvo.getContent()+"'");
        }
        if (mvo.getSystemName() != null) {
            sql.append(" and SYSTEM_NAME='"+mvo.getSystemName()+"'");
        }
        if (mvo.getType() != null) {
            sql.append(" and TYPE='"+mvo.getType() +"'");
        }
        if (mvo.getCreater() != null) {
            sql.append(" and CREATER='"+mvo.getCreater()+"'");
        }
        if (mvo.getReleaseDate() != null) {
            
            sql.append(" and to_char(RELEASE_DATE,'yyyy-mm-dd hh24:mi:ss')>='" +df.format(mvo.getReleaseDate()) +
            "'");
        }
        if(mvo.getReleaseDate2()!=null){
            sql.append(" and to_char(release_date,'yyyy-mm-dd hh24:mi:ss')<='" + df.format(mvo.getReleaseDate2()) +
            "'");
        }
        if (mvo.getEndDate() != null) {
            sql.append(" and to_char(end_date,'yyyy-mm-dd hh24:mi:ss')>='"+mvo.getEndDate()+"'");
        }
        if(mvo.getEndDate2()!=null){
            sql.append(" and to_char(end_date,'yyyy-mm-dd hh24:mi:ss')<='"+mvo.getEndDate2()+"'");
        }
        if (mvo.getSts() != null) {
            sql.append(" and STS='"+mvo.getSts()+"'");
        }
        sql.append(" order by release_date desc");
        sql.append(" ) where rownum<= ").append(rownum).append(" ");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            log.debug(sql.toString());
            
            rs = ps.executeQuery();
			while (rs.next()) {
				BulletinMVO wo = new BulletinMVO();
				wo.setBulletinId(rs.getString("BULLETIN_ID"));
				wo.setContent(rs.getString("CONTENT"));
				wo.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				wo.setReleaseDate(rs.getTimestamp("RELEASE_DATE"));
				wo.setEndDate(rs.getTimestamp("END_DATE"));
				wo.setTitle(rs.getString("TITLE"));				
				wo.setCreater(rs.getString("CREATER"));
				wo.setAreaId(rs.getString("AREA_ID"));
				wo.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				wo.setSts(rs.getString("STS"));
				String attachmentId = rs.getString("ATTACHMENT_ID");
				String fileName = rs.getString("FILE_NAME");
				if(!StringUtil.isBlank(attachmentId)&&!StringUtil.isBlank(attachmentId)){
					AttachmentMVO attachMVO = new AttachmentMVO();
					attachMVO.setAttachmentId(attachmentId);
					attachMVO.setFileName(fileName);
					wo.setAttachMVO(attachMVO);
				}
				results.add(wo);

			}
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
