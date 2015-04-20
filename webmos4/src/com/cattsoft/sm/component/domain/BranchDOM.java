package com.cattsoft.sm.component.domain;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.DataCache;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IBranchSDAO;
import com.cattsoft.sm.util.SMMaxId;
import com.cattsoft.sm.vo.BranchSVO;
import com.cattsoft.webpub.util.SysDateUtil;


/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-27 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class BranchDOM {
    private IBranchSDAO branchDao = (IBranchSDAO) DAOFactory.getInstance()
            .getDAO(IBranchSDAO.class);

    private static Logger log = Logger.getLogger(BranchDOM.class);

    /**
     * 根据主键查询单表BranchSVO
     * 
     * @param vo
     * @return BranchSVO
     * @throws SysException
     * @throws AppException
     */
    public BranchSVO findByPk(BranchSVO vo) throws SysException, AppException {
        return (BranchSVO) branchDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表BranchSVO
     * 
     * @param vo
     * @return List<BranchSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(BranchSVO vo) throws SysException, AppException {
        return branchDao.findByVO(vo);
    }

    /**
     * 修改单表BranchSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     * @throws DataCacheException 
     */
    public void update(BranchSVO vo) throws SysException, AppException {
        BranchSVO sdvo = new BranchSVO();
        sdvo.setName(vo.getName());
        sdvo.setServDeptId(vo.getServDeptId());
        sdvo = branchDao.findByName(sdvo);
        if (sdvo != null && !sdvo.getBranchId().equals(vo.getBranchId())) {
            throw new AppException("3210040", "名称重复");
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        branchDao.update(vo);
        //同步缓存
        try {
            DataCache.initHashMaps(DataCache.BRANCH);
            DataCache.initTreeHashMap(DataCache.SERV_DEPT_BRANCH);
        } catch (DataCacheException e) {
            throw new SysException("2222","同步缓存出错！",e);
        }
      
      
    }

    /**
     * 删除单表BranchSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(BranchSVO vo) throws SysException, AppException {
        if (vo.getSts() == null || vo.getSts().equals(Constant.STS_IN_USE)) {
            vo.setSts(Constant.STS_HISTORY);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        branchDao.update(vo);

    }

    /**
     * 增加单表BranchSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     * @throws SQLException
     * @throws DataCacheException 
     */
    public void add(BranchSVO vo) throws SysException, AppException, SQLException {
        BranchSVO sdvo = new BranchSVO();
        BranchSVO bso=(BranchSVO)branchDao.findByPK(vo);
        if (branchDao.findByPK(vo) != null) {
            throw new AppException("3210004", "编号存在"); // 编号存在
        }
        sdvo.setName(vo.getName());
        sdvo.setServDeptId(vo.getServDeptId());
        sdvo = branchDao.findByName(sdvo);
        if (sdvo != null && !sdvo.getBranchId().equals(vo.getBranchId())) {
            throw new AppException("3210040", "名称重复");
        }
        if (vo.getBranchId() == null) {
            vo.setServDeptId(SMMaxId.getBranchMaxId(vo.getLocalNetId(), vo.getAreaId(), vo
                    .getServDeptId()));
        }
        if (vo.getSts() == null || vo.getSts().equals(Constant.STS_HISTORY)) {
            vo.setSts(Constant.STS_IN_USE);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        vo.setCreateDate(SysDateUtil.getCurrentDate());
        if (vo.getBranchId() == null) {
            vo.setBranchId(MaxId.getSequenceNextVal(Constant.SEQ_BRANCH_ID));
        }
        branchDao.add(vo);
//      同步缓存
        try {
            DataCache.initHashMaps(DataCache.BRANCH);
            DataCache.initTreeHashMap(DataCache.SERV_DEPT_BRANCH);
        } catch (DataCacheException e) {
            throw new SysException("2222","同步缓存出错！",e);
        }
      
    }

    public void delBranchs(String[] branchIds) throws SysException, AppException {
        BranchSVO vo = new BranchSVO();
        for (int i = 0; i < branchIds.length; i++) {
            vo.setBranchId(branchIds[i]);
            this.delete(vo);
        }
//      同步缓存
        try {
            DataCache.initHashMaps(DataCache.BRANCH);
            DataCache.initTreeHashMap(DataCache.SERV_DEPT_BRANCH);
        } catch (DataCacheException e) {
            throw new SysException("2222","同步缓存出错！",e);
        }
      
    }

    public String getBranchId(BranchSVO vo) throws SysException, AppException, SQLException {
        return SMMaxId.getBranchMaxId(vo.getLocalNetId(), vo.getAreaId(), vo.getServDeptId());
    }

    public PagView findBranchsByPage(BranchSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
        return branchDao.findBranchsByPage(vo, set, pagInfo);
    }

}
