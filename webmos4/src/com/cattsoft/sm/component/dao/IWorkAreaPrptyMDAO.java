package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

public interface IWorkAreaPrptyMDAO extends IDAO{
	public List findByVO(GenericVO vo)throws AppException,SysException;
	public List findWorkAreaByExchAndWorkType(String exchId,String workTypeId)throws AppException,SysException;
	public List findWorkAreaId(String workTypeId,String localNetId,String areaId,String prodId,String exchId)throws AppException, SysException;
	public List findProcedures( String ruleId) throws AppException, SysException ;
	public String[] callProc(String procedure,String localNetId,String areaId,String workTypeId,String exchId,String soNbr,String ActType,String AzFlag,String resPrptyId,String prptyType) throws AppException, SysException;
	public String[] callJava(String className,String methodName,String localNetId,String areaId,String workTypeId,String exchId,String soNbr,String ActType,String AzFlag,String resPrptyId,String prptyType) throws AppException,SysException;
	public List findWorkAreaList(String workTypeId,String localNetId,String areaId,String prodId,String exchId)throws AppException, SysException;
	 
}
