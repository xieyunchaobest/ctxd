package com.cattsoft.sm.component.dao;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.BulletinMVO;


public interface IBulletinSDAO extends ISDAO {
    public String findLocalNetName(String id) throws AppException, SysException;
    
    public String findAreaName(String id) throws AppException, SysException;
	
    public List findByDate(GenericVO vo,Timestamp date) throws AppException, SysException;
    
    public PagView findBulletinByPage(BulletinMVO mvo, HashSet set, PagInfo pagInfo) throws Exception ;
    
    public List findByMVO(BulletinMVO mvo) throws AppException, SysException;
    
    public List findBulletinByNow(BulletinMVO mvo,int rownum) throws AppException, SysException;
    
    public PagView findList(GenericVO vo, PagInfo pagInfo) throws AppException, SysException ;
    
    public PagView findList4MOS(GenericVO vo, PagInfo pagInfo) throws AppException, SysException ;
	
    
}