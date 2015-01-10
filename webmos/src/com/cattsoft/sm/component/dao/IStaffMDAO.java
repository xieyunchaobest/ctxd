package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.DevelopMVO;
import com.cattsoft.sm.vo.StaffExtendMVO;
import com.cattsoft.sm.vo.StaffMVO;
import com.cattsoft.sm.vo.StaffSVO;
import com.cattsoft.sm.vo.WorkAreaExchSVO;
import com.cattsoft.sm.vo.WorkAreaSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-26 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public interface IStaffMDAO extends IDAO {
	
    public List getStaffLocalNetVOs(Long staffId, String systemName) throws AppException, SysException;
    
    public List getStaffLocalNetVOs(String staffId, String systemName) throws AppException, SysException;

    public List getWorkStaffList(WorkAreaSVO svo) throws AppException, SysException;
    
    public List findByLabelValueBean(GenericVO vo) throws SysException, AppException;
    
    public StaffMVO findStaffMemberByStaffId(String staffId) throws AppException, SysException;

    public PagView findStaffMemberFast(StaffMVO vo, PagInfo pagInfo,List oList,String deptIds) throws SysException,
            AppException;

    public StaffExtendMVO findStaffExtendMVO(String staffId) throws SysException, AppException;

    public List findStaffMember(StaffMVO vo) throws SysException, AppException;

    public PagView findStaffMemberSet(HashSet set, PagInfo pagInfo) throws Exception;

    public List findDevelopMVO(DevelopMVO dmvo) throws SysException, AppException;
    
    public List findStaffMVOByExchId(WorkAreaExchSVO vo) throws SysException, AppException;
    
    //根据本地网和服务区获取员工列表（包含经纬度信息）
    public List findStaffList(GenericVO vo) throws SysException, AppException ;
    
  //根据本地网和服务区获取员工最近时间的列表（包含经纬度信息）排除没有巡检任务的人员
	public List findLatestStaffList(GenericVO vo) throws SysException, AppException;
	
	//根据本地网和服务区获取员工最近时间的列表（包含经纬度信息）与巡检任务无关
	public List findLastStaffList(GenericVO vo) throws SysException, AppException;
    
    //根据根据员工名称查找员工列表
	public PagView findStaffListByStaffName(GenericVO vo,PagInfo pagInfo) throws SysException, AppException;
    
	//根据根据员工名称查找员工列表
	public PagView findLatestStaffListByStaffName(GenericVO vo,PagInfo pagInfo) throws SysException, AppException;
    
    public List findStaffMemberByStaffIds(String staffIds) throws AppException, SysException;
    
    public List findStaffMVO(StaffMVO vo) throws SysException, AppException;
    
    public String[] updateStaffMVOByRs(GenericVO vo,String type) throws AppException, SysException;
    
    public String findNameByStaffId(String staffId) throws AppException, SysException;
    
    public GenericVO findStaffMVO (StaffSVO vo)throws AppException, SysException;
    
    public String findStaffIdByPartyId(String partyId) throws AppException, SysException;

    // 河南移动业务开通同步员工数据
	public void addUserService(Map paraMap) throws AppException,SysException ;
	
	public void deleteUserService(String code) throws AppException,SysException ;
	
	public void updateUserService(Map paraMap) throws AppException,SysException ;
	
	public List findAllUserService() throws AppException,SysException ;

	public void updateTerminalConfig(String staffId) throws AppException,SysException ;
	//绩效考核管理
	public PagView findStaffMemberPer(StaffMVO vo, PagInfo pagInfo,List oList,String deptIds) throws SysException,
    AppException;
	//选取考核评分人时，列出员工列表
	public PagView findStaffMemberForCheker(StaffMVO vo, PagInfo pagInfo, List oList,
			String deptIds) throws SysException, AppException;
}
