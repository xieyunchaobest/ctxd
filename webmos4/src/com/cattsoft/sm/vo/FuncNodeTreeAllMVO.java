package com.cattsoft.sm.vo;

import java.util.List;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class FuncNodeTreeAllMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    public static String SESSION_NAME = "FuncNodeTreeAllVO";

    private List funcNodeTreeVOs;

    private List funcNodeVOs;

    public List getFuncNodeVOs() {
        return funcNodeVOs;
    }

    public List getFuncNodeTreeVOs() {
        return funcNodeTreeVOs;
    }

    public void setFuncNodeVOs(List funcNodeVOs) {
        this.funcNodeVOs = funcNodeVOs;
    }

    public void setFuncNodeTreeVOs(List funcNodeTreeVOs) {
        this.funcNodeTreeVOs = funcNodeTreeVOs;
    }

    public FuncNodeTreeAllMVO() {
    }

}
