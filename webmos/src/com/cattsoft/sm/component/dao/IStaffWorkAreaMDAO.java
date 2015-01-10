package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.StaffWorkAreaMVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-10 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public interface IStaffWorkAreaMDAO extends IDAO {
     public List findStaffWorkAreaByVo(StaffWorkAreaMVO mvo,String localNetId) throws SysException,AppException;
     public List findLabeBeanByStaffWorkArea(StaffWorkAreaMVO mvo) throws SysException,AppException;
} 