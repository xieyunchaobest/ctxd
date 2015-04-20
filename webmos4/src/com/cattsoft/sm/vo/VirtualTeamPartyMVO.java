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

public class VirtualTeamPartyMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private PartySVO partyVO;

    private VirtualTeamSVO VirtualTeamVO;

    public VirtualTeamPartyMVO() {
    }

    public PartySVO getPartyVO() {
        return partyVO;
    }

    public VirtualTeamSVO getVirtualTeamVO() {
        return VirtualTeamVO;
    }

    public void setPartyVO(PartySVO partyVO) {
        this.partyVO = partyVO;
    }

    public void setVirtualTeamVO(VirtualTeamSVO VirtualTeamVO) {
        this.VirtualTeamVO = VirtualTeamVO;
    }

}
