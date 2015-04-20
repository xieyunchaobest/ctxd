package com.cattsoft.sm.component.domain;

import net.sf.json.JSONObject;

import com.cattsoft.pub.MaxId;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.component.dao.IMosFeedBackSDAO;
import com.cattsoft.sm.vo.MosFeedBackSVO;

public class MosFeedBackDOM {
	
	/**
	 * 用户反馈 用于MOS系统
	 * @param paraMapStr
	 * @throws AppException
	 * @throws SysException
	 */
	public void feedBack4MOS(String paraMapStr)throws AppException,SysException {
		JSONObject paraMapJSONObject = JSONObject.fromObject(paraMapStr);
		
		String feedDesc = "";
		String userId = "";
		String email = "";
		String remarks = "";
		
		feedDesc = paraMapJSONObject.getString("feedDesc");
		
		userId = paraMapJSONObject.getString("userId");
		
		if(!StringUtil.isBlank(paraMapJSONObject.getString("email"))){
			email = paraMapJSONObject.getString("email");
		}
		
//		if(!StringUtil.isBlank(paraMapJSONObject.getString("remarks"))){
//			remarks = paraMapJSONObject.getString("remarks");
//		}
		
		MosFeedBackSVO feedBackSVO = new MosFeedBackSVO();
		feedBackSVO.setMosFeedBackId(MaxId.getSequenceNextVal(SysConstants.MOS_FEED_BACK_ID_SEQ));
		feedBackSVO.setFeedDesc(feedDesc);
		feedBackSVO.setUserId(userId);
		feedBackSVO.setEmail(email);
		feedBackSVO.setRemarks(remarks);
		feedBackSVO.setCreateTime(DateUtil.getDBDate());
		
		IMosFeedBackSDAO feedBackSDAO = (IMosFeedBackSDAO) DAOFactory.getDAO(IMosFeedBackSDAO.class);
		feedBackSDAO.add(feedBackSVO);
		
		
	}

}
