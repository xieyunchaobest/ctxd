package com.cattsoft.tm.component.domain;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.PasswordUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.component.dao.ISysUserSDAO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.dao.ICtxdMDAO;
import com.cattsoft.tm.component.dao.IDColumnDescSDAO;
import com.cattsoft.tm.component.dao.IDQueryConditionSDAO;
import com.cattsoft.tm.component.dao.IDTableDescSDAO;
import com.cattsoft.tm.component.dao.ILoginLogSDAO;
import com.cattsoft.tm.vo.DColumnDescSVO;
import com.cattsoft.tm.vo.DQueryConditionSVO;
import com.cattsoft.tm.vo.DTableDescSVO;
import com.cattsoft.tm.vo.FuncNodeSVO;
import com.cattsoft.tm.vo.FuncNodeTreeSVO;
import com.cattsoft.tm.vo.LoginLogSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;
import com.cattsoft.pub.util.PagInfo;
public class CtxdDOM {
	public PagView queryResult(String instanceId, List conditionListFromPage,PagInfo pg)
			throws AppException, SysException {
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		PagView res = ctxddao.queryResult(instanceId, conditionListFromPage,pg);
		return res;
	}

	public List getQueryConditionList(String instanceId, List conditionListFromPage)
			throws AppException, SysException {
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List conditionList = ctxddao.getQueryCondition(instanceId,
				conditionListFromPage);

		return conditionList;
	}

	/**
	 * 获取需要查询的列
	 * 
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getQueryColumnList(String instanceId) throws AppException,
			SysException {
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List conditionList = ctxddao.getColumnList(instanceId);
		return conditionList;
	}

	public String login(SysUserSVO user,HttpServletRequest request) throws AppException, SysException {
		ISysUserSDAO sysUserSDAO = (ISysUserSDAO) DAOFactory
				.getDAO(ISysUserSDAO.class);
		String userName = user.getSysUserName();
		String passWord = user.getPassword();
		String erpno=user.getErpno();
		
		SysUserSVO sysUserSVO = new SysUserSVO();
		
		if(StringUtil.isBlank(userName) && StringUtil.isBlank(passWord)) {//用户名和密码都是空的情况下，说明是从oa系统进来的
			sysUserSVO.setErpno(erpno);
		}else {
			try {
				passWord = PasswordUtil.getMD5Str(passWord);
			} catch (NoSuchAlgorithmException e) {
				throw new AppException("", "加密过程出现错误！");
			}
			sysUserSVO.setSysUserName(userName);
			sysUserSVO.setPassword(passWord);
		}
		
		

		List userList = sysUserSDAO  .findByVO(sysUserSVO);
		if (userList == null || userList.size() == 0) {
			return "E";
		} else {
			SysUserSVO sysuserSVO = (SysUserSVO) userList.get(0);
			Map resMap = new HashMap();
			resMap.put("resultCode", "1");
			resMap.put("resultInfo", "登录成功！");

			// 记录登录日志
			ILoginLogSDAO loginLogSDAO = (ILoginLogSDAO) DAOFactory
					.getDAO(ILoginLogSDAO.class);
			LoginLogSVO svo = new LoginLogSVO();
			svo.setLoginLogId(MaxId
					.getSequenceNextVal(SysConstants.LOGIN_LOG_ID));
			svo.setLoginType("I");
			svo.setLoginTime(DateUtil.getDBDate());
			svo.setUserId(sysuserSVO.getSysUserId());
			loginLogSDAO.add(svo);

			request.getSession().setAttribute("user", sysuserSVO);
//			List funcList = this.getFuncNodeListByUser(sysuserSVO);
//
//			Map sysUserSVOMap = new HashMap();
//			sysUserSVOMap.put("sysUserSVO", sysuserSVO);
//			//sysUserSVOMap.put("sysUserFuncTree", funcList);
//			resMap.put("suveJsonObject", sysUserSVOMap);

			return "S";
		}
	}
	
	public List getFuncNodeListByUser(SysUserSVO user) throws AppException,SysException {
		com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO treeSDAO = (com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO) DAOFactory.getDAO(com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO.class);
		FuncNodeTreeSVO atree=new FuncNodeTreeSVO();
		atree.setSts("A");
		List treeList=treeSDAO.findByVO(atree);
		
		ICtxdMDAO zsjfmdao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List userAllocList=zsjfmdao.getFuncNodeListByUser(user);
		
		if(treeList!=null && treeList.size()>0) {
			for(int i=0;i<treeList.size();i++) {
				List funcNodeList=new ArrayList();
				
				FuncNodeTreeSVO tree=(FuncNodeTreeSVO)treeList.get(i);
				tree.setUserFuncNodeList(funcNodeList);
				String treeId=tree.getNodeTreeId();
				System.err.println("treeId===="+treeId);
				if(userAllocList!=null && userAllocList.size()>0) {
					for(int j=0;j<userAllocList.size();j++) {
						FuncNodeSVO alloc=(FuncNodeSVO)userAllocList.get(j);
						String atreeId=alloc.getNodeTreeId();
						//System.out.println("atreeId="+atreeId);
						if(atreeId.equals(treeId)) {
							funcNodeList.add(alloc);
						}
					}
				}
				/**if(funcNodeList.size()==0) {
					treeList.remove(i);
				}**/
				
			}
		}else {
			throw new AppException("","该用户没有任何权限");
		}
		return treeList;
	}

	/**
	 * 获取当前数据库用户的表
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getDBTables()throws AppException, SysException{
		ICtxdMDAO mdao= (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		return mdao.getDBTables();
	}

	
	public DTableDescSVO getConfigTableInfo(String tableId)throws AppException, SysException{
		ICtxdMDAO mdao= (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		return mdao.getConfigTableInfo(tableId);
	}
	
	/**
	 * 获取列的说明信息，如果没有，则取数据字典的说明
	 * @param svo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getColumnDescList(String tableName)throws AppException, SysException{
		ICtxdMDAO mdao= (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		return mdao.getColumnDescList(tableName);
	}
	
	public void saveTableConfig(DTableDescSVO table,List columns,List queryConditionList) throws AppException, SysException{
		IDTableDescSDAO tableDAO= (IDTableDescSDAO) DAOFactory.getDAO(IDTableDescSDAO.class);
		IDColumnDescSDAO columnDAO= (IDColumnDescSDAO) DAOFactory.getDAO(IDColumnDescSDAO.class);
		
		//IDQueryConditionSDAO conditionDAO= (IDQueryConditionSDAO) DAOFactory.getDAO(IDQueryConditionSDAO.class);
		//先删除，再添加
		tableDAO.delete(table);
		DColumnDescSVO cvo=new DColumnDescSVO();
		cvo.setTableName(table.getTableName());
		columnDAO.delete(cvo);
		DQueryConditionSVO condition=new DQueryConditionSVO();
		condition.setTableName(table.getTableName());
		//conditionDAO.delete(condition);
		
		table.setTableId(MaxId.getSequenceNextVal(Constant.D_TABLE_DESC));
		tableDAO.add(table);
		columnDAO.addBat(columns);
		//conditionDAO.addBat(queryConditionList);
		
	}
	
	public DTableDescSVO getTableVO(String tableName) throws AppException, SysException {
		IDTableDescSDAO tableDAO= (IDTableDescSDAO) DAOFactory.getDAO(IDTableDescSDAO.class);
		DTableDescSVO vo=new DTableDescSVO();
		vo.setTableName(tableName);
		return (DTableDescSVO)tableDAO.findByVO(vo).get(0);
	}
	
	public List getConfigTabList() throws AppException, SysException {
		IDTableDescSDAO tableDAO= (IDTableDescSDAO) DAOFactory.getDAO(IDTableDescSDAO.class);
		DTableDescSVO svo=new DTableDescSVO();
		List tableList=tableDAO.findByVO(svo);
		return tableList;
	}
	
	public List getColumnCommentsByTable(String tableName)throws AppException, SysException{
		ICtxdMDAO mdao= (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		return mdao.getColumnCommentsByTable(tableName);
	}
	
	
	public PagView getQueryInstanceList(QueryInstanceSVO i,PagInfo pagInfo)throws AppException, SysException{
		ICtxdMDAO mdao= (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		return mdao.getQueryInstanceList(i,pagInfo);
	}
	
	
	
	public List getColumnList(String instacneId) throws AppException, SysException{
		ICtxdMDAO mdao= (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		return mdao.getColumnList(instacneId);
	}
	
	public List getQueryConditionList(String instanceId) throws AppException,SysException{
		ICtxdMDAO mdao= (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		return mdao.getQueryConditionList(instanceId);
	}
	
	public QueryInstanceSVO getQueryInstanceSVO(QueryInstanceSVO svo)throws AppException, SysException {
		ICtxdMDAO mdao= (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		return (QueryInstanceSVO)mdao.findByVO(svo).get(0);
	}
}
