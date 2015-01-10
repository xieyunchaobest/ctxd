package com.cattsoft.sm.component.dao;

import java.sql.Date;
import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.WorkAreaExchMVO;
import com.cattsoft.sm.vo.WorkAreaExchSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-29 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public interface IWorkAreaExchMDAO extends IDAO {
    public List findWorkAreaExchs(WorkAreaExchMVO vo) throws AppException, SysException;

    public List findExchsUnSelNotSo(WorkAreaExchMVO waevo) throws SysException, AppException;

    public List findExchsUnSelSo(WorkAreaExchMVO waevo) throws SysException, AppException;

    public List findExchByWorkAreaSVO(WorkAreaExchSVO waevo) throws SysException, AppException;

    public String findWorkAreaByExchAndStep(String exchId, String stepId) throws SysException,
            AppException;
    
    public List findWorkAreaUnSel(WorkAreaExchMVO waevo) throws SysException,AppException ;
    public void WorkAreaExchSync(String actType, String workAreaExchId,String workAreaId, String exchId, 
                                 String sts, Date stsDate, Date createDate)throws SysException, AppException;
    
    public void addWorkAreaExch(GenericVO vo)throws AppException, SysException;
    
    public void updateWorkAreaExch(GenericVO vo)throws AppException, SysException;
    
    public void deleteWorkAreaExch(GenericVO vo)throws AppException, SysException;
    
    public List findWorkId(GenericVO vo, String exchId) throws AppException,
	SysException;
	public List findWorkIdByServDept(GenericVO vo, String exchId) throws AppException,
	SysException;
	

}
