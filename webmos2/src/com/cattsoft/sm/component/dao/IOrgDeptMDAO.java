package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.OrgDeptMVO;
import com.cattsoft.sm.vo.OrgDeptSVO;


/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-30 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public interface IOrgDeptMDAO extends IDAO {
    
    public OrgDeptMVO findOrgDeptById(String deptId) throws AppException,SysException;
    
    public List findDeptTree(String deptId) throws AppException, SysException;
    
    //根据机构树的叶子节点点击，获取结构信息
    public List findDeptByLeaf(OrgDeptSVO svo) throws AppException,SysException;

}
