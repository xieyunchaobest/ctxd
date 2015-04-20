package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SubSystemWorkAreaMVO extends GenericVO {

    private static final long serialVersionUID = 1L;private String subSystemName;
    private StaffWorkAreaSVO staffWorkAreaVO;
    public String getSubSystemName() {
      return subSystemName;
    }

    public void setStaffWorkAreaVO(StaffWorkAreaSVO staffWorkAreaVO) {
      this.staffWorkAreaVO = staffWorkAreaVO;
    }

    public void setSubSystemName(String subSystemName) {
      this.subSystemName = subSystemName;
    }

    public StaffWorkAreaSVO getStaffWorkAreaVO() {
      return staffWorkAreaVO;
    }
    

}
