package com.cattsoft.tm.component.domain;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.PasswordUtil;
import com.cattsoft.sm.component.dao.ISysUserSDAO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.dao.ICtxdMDAO;
import com.cattsoft.tm.component.dao.ILoginLogSDAO;
import com.cattsoft.tm.vo.FuncNodeSVO;
import com.cattsoft.tm.vo.FuncNodeTreeSVO;
import com.cattsoft.tm.vo.LoginLogSVO;
import com.cattsoft.pub.util.PagInfo;
public class CtxdDOM {
	public PagView queryResult(String tableId, List conditionListFromPage,PagInfo pg)
			throws AppException, SysException {
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		PagView res = ctxddao.queryResult(tableId, conditionListFromPage,pg);
		return res;
	}

	public List getQueryConditionList(String tableId, List conditionListFromPage)
			throws AppException, SysException {
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List conditionList = ctxddao.getQueryCondition(tableId,
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
	public List getQueryColumnList(String tableId) throws AppException,
			SysException {
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List conditionList = ctxddao.getQueryColumnList(tableId);
		return conditionList;
	}

	public String login(SysUserSVO user) throws AppException, SysException {
		ISysUserSDAO sysUserSDAO = (ISysUserSDAO) DAOFactory
				.getDAO(ISysUserSDAO.class);
		String userName = user.getSysUserName();
		String passWord = user.getPassword();
		try {
			passWord = PasswordUtil.getMD5Str(passWord);
		} catch (NoSuchAlgorithmException e) {
			throw new AppException("", "加密过程出现错误！");
		}

		SysUserSVO sysUserSVO = new SysUserSVO();
		sysUserSVO.setSysUserName(userName);
		sysUserSVO.setPassword(passWord);

		List userList = sysUserSDAO.findByVO(sysUserSVO);
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


}
