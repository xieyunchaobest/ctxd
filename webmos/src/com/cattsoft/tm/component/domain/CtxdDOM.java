package com.cattsoft.tm.component.domain;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Document;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.PasswordUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.component.dao.ISysUserSDAO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.dao.ICtxdMDAO;
import com.cattsoft.tm.component.dao.IDColumnDescSDAO;
import com.cattsoft.tm.component.dao.IDTableDescSDAO;
import com.cattsoft.tm.component.dao.ILoginLogSDAO;
import com.cattsoft.tm.component.dao.ISysMDAO;
import com.cattsoft.tm.util.TreeUtil;
import com.cattsoft.tm.vo.DColumnDescSVO;
import com.cattsoft.tm.vo.DQueryConditionSVO;
import com.cattsoft.tm.vo.DTableDescSVO;
import com.cattsoft.tm.vo.FuncMenu;
import com.cattsoft.tm.vo.FuncNodeSVO;
import com.cattsoft.tm.vo.FuncNodeTreeSVO;
import com.cattsoft.tm.vo.LoginLogSVO;
import com.cattsoft.tm.vo.QueryInstanceColumnSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;
import com.cattsoft.tm.vo.TreeNodeInfo;
public class CtxdDOM {
	public PagView queryResult(String instanceId, List conditionListFromPage,PagInfo pg,Map sortMap)
			throws AppException, SysException {
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		PagView res = ctxddao.queryResult(instanceId, conditionListFromPage,pg, sortMap);
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
	
	public List getFuncNodeListByUserNew(SysUserSVO user) throws AppException,SysException{
		ICtxdMDAO zsjfmdao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List userAllocList=zsjfmdao.getFuncNodeListByUserNew(user);
		return userAllocList;
		
	}
	
	
	public List getFuncMenuList(SysUserSVO user) throws AppException, SysException {
		List funcList=getFuncNodeListByUserNew(user);
		List menuList=convertToFuncMenuList(funcList);
		if(menuList!=null && menuList.size()>0) {
			for(int i=0;i<menuList.size();i++) {
				FuncMenu m=(FuncMenu)menuList.get(i);
				setSubFuncMenu(m, menuList);
			}
		}
		
		List resList=new ArrayList();
		for(int i=0;i<menuList.size();i++) {
			FuncMenu m=(FuncMenu)menuList.get(i);
			if(m.getDepth().equals("1")) {
				resList.add(m);
			}
		}
		return resList;
	}
	
	private List convertToFuncMenuList(List funcNodeList)  throws AppException, SysException {
		List menuList=new ArrayList();
		if(funcNodeList!=null) {
			for(int i=0;i<funcNodeList.size();i++) {
				TreeNodeInfo n=(TreeNodeInfo)funcNodeList.get(i);
				FuncMenu m=new FuncMenu();
				m.setId(n.getId());
				m.setName(n.getName());
				m.setParentId(n.getParentId());
				m.setUrl(n.getHtml());
				m.setDepth(n.getDepth()+"");
				menuList.add(m);
			}
		}
		return menuList;
	}
	
	
	private void setSubFuncMenu(FuncMenu node,List funcList ) throws AppException,SysException {
		List subList=new ArrayList();
		if(funcList!=null) {
			for(int i=0;i<funcList.size();i++) {
				FuncMenu menu=(FuncMenu)funcList.get(i);
				String pid=menu.getParentId();
				if(pid.equals(node.getId())){
					subList.add(menu);
				}
			}
		}
		if(subList.size()>0) {
			node.setChildren(subList);
			node.setIconOpen("../images/folderOpen.png");
			node.setIconClose("../images/folderClosed.png");
		}
	}
	
	public Document getFuncMenuDoc(SysUserSVO user) throws AppException,SysException{
		ICtxdMDAO zsjfmdao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List userAllocList=zsjfmdao.getFuncNodeListByUserNew(user);
		TreeNodeInfo root=new TreeNodeInfo();
		root.setId("0");
		Document doc = TreeUtil.buildTree(root, userAllocList, "");
		return doc;
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
	
	
	public HSSFWorkbook exportExcel(String instanceId,List conditionListFromPage,Map sortMap) throws AppException,SysException{
		List columnList=getColumnList(instanceId);
		List headerList=new ArrayList();
		for(int i=0;i<columnList.size();i++) {
			QueryInstanceColumnSVO c=(QueryInstanceColumnSVO)columnList.get(i);
			String columnDesc=c.getColumnDesc();
			headerList.add(columnDesc);
		}
		
		Object obj[]=(Object[]) headerList.toArray();
		String headArr[]=new String[obj.length];
		 for (int i = 0; i < obj.length; i++) {
	            String e = (String) obj[i];
	            headArr[i]=e;
	        }
		
		List dataList=exportResultList(instanceId,conditionListFromPage,sortMap);
		HSSFWorkbook wb = new HSSFWorkbook();  
	    HSSFSheet sheet = wb.createSheet("查询结果");  
	    HSSFRow row = sheet.createRow((int) 0);  
	        
	    HSSFCellStyle style = wb.createCellStyle();  
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	    
	    for (int i = 0; i < headArr.length; i++) {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellValue(headArr[i]);  
            cell.setCellStyle(style);  
            sheet.setColumnWidth((short) i, (short) 12000);
        }  
	    
	    
	    if(dataList!=null) {
	    	 for (int i = 0; i < dataList.size(); i++) {  
	             row = sheet.createRow(i + 1);  
	             Map m =(Map) dataList.get(i);  
	             Set<String> keys = m.keySet();
	             int cn=0;
	             for (Iterator it = keys.iterator(); it.hasNext();) {
	            	 String key = (String) it.next();
	            	 if( (!"width".equals(key))  && !("bgColor".equals(key)) ) {
	            		 row.createCell(cn++).setCellValue((String)m.get(key));  
	            	 }
	            	  
	             }

	            
	         }  
	    }
	   
        return wb;  
		
	}
	
	public List exportResultList(String instanceId, List conditionListFromPage,Map sortMap)
			throws AppException, SysException {
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List res = ctxddao.exportResult(instanceId, conditionListFromPage,sortMap);
		return res;
	}
	
	
	public List getDataDicList(String tableName,String columnName) throws AppException,SysException{
		ISysMDAO iSysMDAO = (ISysMDAO) DAOFactory.getDAO(ISysMDAO.class);
		return iSysMDAO.getDataDicList(tableName, columnName);
	}
}
