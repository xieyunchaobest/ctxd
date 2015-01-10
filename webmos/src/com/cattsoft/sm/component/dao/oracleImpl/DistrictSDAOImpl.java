package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IDistrictSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.*;


public class DistrictSDAOImpl implements IDistrictSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		DistrictSVO district = (DistrictSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" DISTRICT(DISTRICT_ID,NAME,ABBREV_NAME,PARENT_DISTRICT_ID,DISTRICT_LEVEL_ID,LOCAL_NET_ID,AREA_ID,SERV_DEPT_ID,STS,STS_DATE,CREATE_DATE,city_flag) values(?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, district.getDistrictId());
			ps.setString(2, district.getName());
			ps.setString(3, district.getAbbrevName());
			ps.setString(4, district.getParentDistrictId());
			ps.setString(5, district.getDistrictLevelId());
			ps.setString(6, district.getLocalNetId());
			ps.setString(7, district.getAreaId());
			ps.setString(8, district.getServDeptId());
			ps.setString(9, district.getSts());
			ps.setDate(10, district.getStsDate());
			ps.setDate(11, district.getCreateDate());
            ps.setString(12, district.getCityFlag());
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
		DistrictSVO district = (DistrictSVO) vo;
		StringBuffer sql = new StringBuffer("update DISTRICT set");
		if (district.getName() != null) {
			sql.append(" NAME=?,");
		}
		if (district.getAbbrevName() != null) {
			sql.append(" ABBREV_NAME=?,");
		}
		if (district.getParentDistrictId() != null) {
			sql.append(" PARENT_DISTRICT_ID=?,");
		}
		if (district.getDistrictLevelId() != null) {
			sql.append(" DISTRICT_LEVEL_ID=?,");
		}
		if (district.getLocalNetId() != null) {
			sql.append(" LOCAL_NET_ID=?,");
		}
		if (district.getAreaId() != null) {
			sql.append(" AREA_ID=?,");
		}
		if (district.getServDeptId() != null) {
			sql.append(" SERV_DEPT_ID=?,");
		}
		if (district.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (district.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (district.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
        if(district.getCityFlag()!=null){
            sql.append(" city_flag=?,");
        }
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and DISTRICT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (district.getName() != null) {
				ps.setString(index++, district.getName());
			}
			if (district.getAbbrevName() != null) {
				ps.setString(index++, district.getAbbrevName());
			}
			if (district.getParentDistrictId() != null) {
				ps.setString(index++, district.getParentDistrictId());
			}
			if (district.getDistrictLevelId() != null) {
				ps.setString(index++, district.getDistrictLevelId());
			}
			if (district.getLocalNetId() != null) {
				ps.setString(index++, district.getLocalNetId());
			}
			if (district.getAreaId() != null) {
				ps.setString(index++, district.getAreaId());
			}
			if (district.getServDeptId() != null) {
				ps.setString(index++, district.getServDeptId());
			}
			if (district.getSts() != null) {
				ps.setString(index++, district.getSts());
			}
			if (district.getStsDate() != null) {
				ps.setDate(index++, district.getStsDate());
			}
			if (district.getCreateDate() != null) {
				ps.setDate(index++, district.getCreateDate());
			}
            if(district.getCityFlag()!=null){
                ps.setString(index++, district.getCityFlag());
            }
			ps.setString(index++, district.getDistrictId());
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
		DistrictSVO district = (DistrictSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from DISTRICT where 1=1");
		sql.append(" and DISTRICT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, district.getDistrictId());
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
		DistrictSVO result = null;
		DistrictSVO district = (DistrictSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.DISTRICT_ID,a.NAME,a.ABBREV_NAME,a.PARENT_DISTRICT_ID,a.DISTRICT_LEVEL_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.STS,a.STS_DATE,a.CREATE_DATE,a.city_flag");
		sql.append(" from DISTRICT a where 1=1");
		sql.append(" and DISTRICT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, district.getDistrictId());
			rs = ps.executeQuery();
			result = (DistrictSVO) ResultSetUtil.convertToVo(rs, DistrictSVO.class);
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
		DistrictSVO district = (DistrictSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DISTRICT_ID,a.NAME,a.ABBREV_NAME,a.PARENT_DISTRICT_ID,a.DISTRICT_LEVEL_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.STS,a.STS_DATE,a.CREATE_DATE,a.city_flag");
		sql.append(" from DISTRICT a where 1=1");
		if (district.getDistrictId() != null) {
			sql.append(" and DISTRICT_ID=?");
		}
		if (district.getName() != null) {
			sql.append(" and NAME like '%"+district.getName()+"%' ");
		}
		if (district.getAbbrevName() != null) {
			sql.append(" and ABBREV_NAME=?");
		}
		if (district.getParentDistrictId() != null) {
			sql.append(" and PARENT_DISTRICT_ID=?");
		}
		if (district.getDistrictLevelId() != null) {
			sql.append(" and DISTRICT_LEVEL_ID=?");
		}
		if (district.getLocalNetId() != null) {
			sql.append(" and LOCAL_NET_ID=?");
		}
		if (district.getAreaId() != null) {
			sql.append(" and AREA_ID=?");
		}
		if (district.getServDeptId() != null) {
			sql.append(" and SERV_DEPT_ID=?");
		}
		if (district.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (district.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (district.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
        if(district.getCityFlag()!=null){
            sql.append(" and city_flag=? ");
        }
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (district.getDistrictId() != null) {
				ps.setString(index++, district.getDistrictId());
			}
			
			if (district.getAbbrevName() != null) {
				ps.setString(index++, district.getAbbrevName());
			}
			if (district.getParentDistrictId() != null) {
				ps.setString(index++, district.getParentDistrictId());
			}
			if (district.getDistrictLevelId() != null) {
				ps.setString(index++, district.getDistrictLevelId());
			}
			if (district.getLocalNetId() != null) {
				ps.setString(index++, district.getLocalNetId());
			}
			if (district.getAreaId() != null) {
				ps.setString(index++, district.getAreaId());
			}
			if (district.getServDeptId() != null) {
				ps.setString(index++, district.getServDeptId());
			}
			if (district.getSts() != null) {
				ps.setString(index++, district.getSts());
			}
			if (district.getStsDate() != null) {
				ps.setDate(index++, district.getStsDate());
			}
			if (district.getCreateDate() != null) {
				ps.setDate(index++, district.getCreateDate());
			}
            if(district.getCityFlag()!=null){
                ps.setString(index++, district.getCityFlag());
            }
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, DistrictSVO.class);
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
     public PagView findDistrictsByPage(DistrictSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        DistrictSVO district = (DistrictSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.DISTRICT_ID,a.NAME,a.ABBREV_NAME,a.PARENT_DISTRICT_ID,a.DISTRICT_LEVEL_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.STS,a.STS_DATE,a.CREATE_DATE,a.city_flag,b.name parent_name ");
        sql.append(" from DISTRICT a,district b where 1=1 and a.parent_district_id=b.district_id(+)");
        if (vo == null && set != null) {
            sql.append("and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "district_id", "districtId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
        if (district.getDistrictId() != null) {
            sql.append(" and a.DISTRICT_ID="+district.getDistrictId());
        }
        if (district.getName() != null) {
            sql.append(" and a.NAME like '%"+district.getName()+ "%' ");
        }
        if (district.getAbbrevName() != null) {
            sql.append(" and a.ABBREV_NAME="+district.getAbbrevName());
        }
        if (district.getParentDistrictId() != null) {
            sql.append(" and a.PARENT_DISTRICT_ID="+district.getParentDistrictId());
        }
        if (district.getDistrictLevelId() != null) {
            sql.append(" and a.DISTRICT_LEVEL_ID="+district.getDistrictLevelId());
        }
        if (district.getLocalNetId() != null) {
            sql.append(" and a.LOCAL_NET_ID="+district.getLocalNetId());
        }
        if (district.getAreaId() != null) {
            sql.append(" and a.AREA_ID="+district.getAreaId());
        }
        if (district.getServDeptId() != null) {
            sql.append(" and a.SERV_DEPT_ID="+district.getServDeptId());
        }
        if (district.getSts() != null) {
            sql.append(" and a.STS='"+district.getSts()+"'");
        }
        if (district.getStsDate() != null) {
            sql.append(" and a.STS_DATE="+district.getStsDate());
        }
        if (district.getCreateDate() != null) {
            sql.append(" and a.CREATE_DATE="+district.getCreateDate());
        }
        if(district.getCityFlag()!=null){
            sql.append(" and a.city_flag='"+district.getCityFlag()+"'");
        }
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(DistrictSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, DistrictSVO.class);
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
     public GenericVO findMVOByPK(GenericVO vo) throws AppException, SysException {
        DistrictMVO result = null;
        DistrictSVO district = (DistrictSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql.append(" a.DISTRICT_ID,a.NAME,a.ABBREV_NAME,a.PARENT_DISTRICT_ID,a.DISTRICT_LEVEL_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.STS,a.STS_DATE,a.CREATE_DATE,a.city_flag,b.name parent_name ");
        sql.append(" from DISTRICT a,district b where 1=1 and a.parent_district_id=b.district_id(+) ");
        sql.append(" and a.DISTRICT_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, district.getDistrictId());
            rs = ps.executeQuery();
            result = (DistrictMVO) ResultSetUtil.convertToVo(rs, DistrictMVO.class);
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
}
