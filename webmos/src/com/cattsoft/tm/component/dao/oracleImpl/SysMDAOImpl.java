package com.cattsoft.tm.component.dao.oracleImpl;

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
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.sm.vo.MosFuncNodeSVO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.dao.ISysMDAO;
import com.cattsoft.tm.vo.FuncNodeSVO;

public class SysMDAOImpl implements ISysMDAO{
	
	public List getFuncTreeByNode(FuncNodeSVO node) throws AppException,SysException {
		return null;
	}
	
	
}
