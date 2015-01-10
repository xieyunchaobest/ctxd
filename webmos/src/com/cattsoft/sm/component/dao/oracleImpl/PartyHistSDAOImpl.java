package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PartitionUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyHistSDAO;
import com.cattsoft.sm.vo.PartyHistSVO;

public class PartyHistSDAOImpl implements IPartyHistSDAO {
	
    private Logger log = Logger.getLogger(PartyHistSDAOImpl.class);

    public void add(GenericVO vo) throws AppException, SysException {
        PartyHistSVO partyHist = (PartyHistSVO) vo;
        StringBuffer sql = new StringBuffer(
                "insert into PARTY_HIST(PARTY_ID,SEQ,PARTY_HIST_ID,JUR_PERSON,NET_TYPE,TAX_NBR,COMPANY_SIZE,START_TIME,COMPANY_CHARACTER,COMPANY_STRUCTURE,REMARKS,GANDER,BIRTHDAY,AGE,NATIVE_PLACE,NATIONALITY,POLITICAL_STATUS,EDUC_LEVEL,GRADUATE_SCHOOL,MARITAL_STATUS,DOMESTIC_RELATION,MARITAL_DATE,COMPANY_NAME,DEPARTMENT,POSITION,WORK_ADDR,FUNC_DESC,SALARY,CHAR_TYPE,FAVORITE,TELECOM_ATTITUDE,WORK_EXPER,BRANCH_ID,AREA_ID,LOCAL_NET_ID,SERV_DEPT_ID,NAME,NAME_BRIEF,NAME_OTHER,PARTY_TYPE,STANDARD_CODE,STS,STS_DATE,CREATE_DATE,COMMUNICATION_SPECIALTY,MAIN_PRODUCTION,TOTAL_ASSET,WEB_ADDRESS,LAST_MOD_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyHist.getPartyId());
            ps.setString(2, partyHist.getSeq());
            if (partyHist.getPartyHistId() == null) {
                ps.setString(3, MaxId.getSequenceNextVal(Constant.SEQ_PARTY_HIST_ID));
            } else {
                ps.setString(3, partyHist.getPartyHistId());
            }
            ps.setString(4, partyHist.getJurPerson());
            ps.setString(5, partyHist.getNetType());
            ps.setString(6, partyHist.getTaxNbr());
            ps.setString(7, partyHist.getCompanySize());
            ps.setDate(8, partyHist.getStartTime());
            ps.setString(9, partyHist.getCompanyCharacter());
            ps.setString(10, partyHist.getCompanyStructure());
            ps.setString(11, partyHist.getRemarks());
            ps.setString(12, partyHist.getGander());
            ps.setDate(13, partyHist.getBirthday());
            ps.setString(14, partyHist.getAge());
            ps.setString(15, partyHist.getNativePlace());
            ps.setString(16, partyHist.getNationality());
            ps.setString(17, partyHist.getPoliticalStatus());
            ps.setString(18, partyHist.getEducLevel());
            ps.setString(19, partyHist.getGraduateSchool());
            ps.setString(20, partyHist.getMaritalStatus());
            ps.setString(21, partyHist.getDomesticRelation());
            ps.setDate(22, partyHist.getMaritalDate());
            ps.setString(23, partyHist.getCompanyName());
            ps.setString(24, partyHist.getDepartment());
            ps.setString(25, partyHist.getPosition());
            ps.setString(26, partyHist.getWorkAddr());
            ps.setString(27, partyHist.getFuncDesc());
            ps.setString(28, partyHist.getSalary());
            ps.setString(29, partyHist.getCharType());
            ps.setString(30, partyHist.getFavorite());
            ps.setString(31, partyHist.getTelecomAttitude());
            ps.setString(32, partyHist.getWorkExper());
            ps.setString(33, partyHist.getBranchId());
            ps.setString(34, partyHist.getAreaId());
            ps.setString(35, partyHist.getLocalNetId());
            ps.setString(36, partyHist.getServDeptId());
            ps.setString(37, partyHist.getName());
            ps.setString(38, partyHist.getNameBrief());
            ps.setString(39, partyHist.getNameOther());
            ps.setString(40, partyHist.getPartyType());
            ps.setString(41, partyHist.getStandardCode());
            ps.setString(42, partyHist.getSts());
            ps.setTimestamp(43, partyHist.getStsDate());
            ps.setTimestamp(44, partyHist.getCreateDate());
            ps.setString(45, partyHist.getCommunicationSpecialty());
            ps.setString(46, partyHist.getMainProduction());
            ps.setString(47, partyHist.getTotalAsset());
            ps.setString(48, partyHist.getWebAddress());
            ps.setTimestamp(49, partyHist.getLastModDate());
            ps.execute();
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "add error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void update(GenericVO vo) throws AppException, SysException {
        PartyHistSVO partyHist = (PartyHistSVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY_HIST set");
        if (partyHist.getPartyId() != null) {
            sql.append(" PARTY_ID=?,");
        }
        if (partyHist.getSeq() != null) {
            sql.append(" SEQ=?,");
        }
        if (partyHist.getJurPerson() != null) {
            sql.append(" JUR_PERSON=?,");
        }
        if (partyHist.getNetType() != null) {
            sql.append(" NET_TYPE=?,");
        }
        if (partyHist.getTaxNbr() != null) {
            sql.append(" TAX_NBR=?,");
        }
        if (partyHist.getCompanySize() != null) {
            sql.append(" COMPANY_SIZE=?,");
        }
        if (partyHist.getStartTime() != null) {
            sql.append(" START_TIME=?,");
        }
        if (partyHist.getCompanyCharacter() != null) {
            sql.append(" COMPANY_CHARACTER=?,");
        }
        if (partyHist.getCompanyStructure() != null) {
            sql.append(" COMPANY_STRUCTURE=?,");
        }
        if (partyHist.getRemarks() != null) {
            sql.append(" REMARKS=?,");
        }
        if (partyHist.getGander() != null) {
            sql.append(" GANDER=?,");
        }
        if (partyHist.getBirthday() != null) {
            sql.append(" BIRTHDAY=?,");
        }
        if (partyHist.getAge() != null) {
            sql.append(" AGE=?,");
        }
        if (partyHist.getNativePlace() != null) {
            sql.append(" NATIVE_PLACE=?,");
        }
        if (partyHist.getNationality() != null) {
            sql.append(" NATIONALITY=?,");
        }
        if (partyHist.getPoliticalStatus() != null) {
            sql.append(" POLITICAL_STATUS=?,");
        }
        if (partyHist.getEducLevel() != null) {
            sql.append(" EDUC_LEVEL=?,");
        }
        if (partyHist.getGraduateSchool() != null) {
            sql.append(" GRADUATE_SCHOOL=?,");
        }
        if (partyHist.getMaritalStatus() != null) {
            sql.append(" MARITAL_STATUS=?,");
        }
        if (partyHist.getDomesticRelation() != null) {
            sql.append(" DOMESTIC_RELATION=?,");
        }
        if (partyHist.getMaritalDate() != null) {
            sql.append(" MARITAL_DATE=?,");
        }
        if (partyHist.getCompanyName() != null) {
            sql.append(" COMPANY_NAME=?,");
        }
        if (partyHist.getDepartment() != null) {
            sql.append(" DEPARTMENT=?,");
        }
        if (partyHist.getPosition() != null) {
            sql.append(" POSITION=?,");
        }
        if (partyHist.getWorkAddr() != null) {
            sql.append(" WORK_ADDR=?,");
        }
        if (partyHist.getFuncDesc() != null) {
            sql.append(" FUNC_DESC=?,");
        }
        if (partyHist.getSalary() != null) {
            sql.append(" SALARY=?,");
        }
        if (partyHist.getCharType() != null) {
            sql.append(" CHAR_TYPE=?,");
        }
        if (partyHist.getFavorite() != null) {
            sql.append(" FAVORITE=?,");
        }
        if (partyHist.getTelecomAttitude() != null) {
            sql.append(" TELECOM_ATTITUDE=?,");
        }
        if (partyHist.getWorkExper() != null) {
            sql.append(" WORK_EXPER=?,");
        }
        if (partyHist.getBranchId() != null) {
            sql.append(" BRANCH_ID=?,");
        }
        if (partyHist.getAreaId() != null) {
            sql.append(" AREA_ID=?,");
        }
        if (partyHist.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (partyHist.getServDeptId() != null) {
            sql.append(" SERV_DEPT_ID=?,");
        }
        if (partyHist.getName() != null) {
            sql.append(" NAME=?,");
        }
        if (partyHist.getNameBrief() != null) {
            sql.append(" NAME_BRIEF=?,");
        }
        if (partyHist.getNameOther() != null) {
            sql.append(" NAME_OTHER=?,");
        }
        if (partyHist.getPartyType() != null) {
            sql.append(" PARTY_TYPE=?,");
        }
        if (partyHist.getStandardCode() != null) {
            sql.append(" STANDARD_CODE=?,");
        }
        if (partyHist.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (partyHist.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (partyHist.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        if (partyHist.getCommunicationSpecialty() != null) {
            sql.append(" COMMUNICATION_SPECIALTY=?,");
        }
        if (partyHist.getMainProduction() != null) {
            sql.append(" MAIN_PRODUCTION=?,");
        }
        if (partyHist.getTotalAsset() != null) {
            sql.append(" TOTAL_ASSET=?,");
        }
        if (partyHist.getWebAddress() != null) {
            sql.append(" WEB_ADDRESS=?,");
        }
        if (partyHist.getLastModDate() != null) {
            sql.append(" LAST_MOD_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_HIST_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (partyHist.getPartyId() != null) {
                ps.setString(index++, partyHist.getPartyId());
            }
            if (partyHist.getSeq() != null) {
                ps.setString(index++, partyHist.getSeq());
            }
            if (partyHist.getJurPerson() != null) {
                ps.setString(index++, partyHist.getJurPerson());
            }
            if (partyHist.getNetType() != null) {
                ps.setString(index++, partyHist.getNetType());
            }
            if (partyHist.getTaxNbr() != null) {
                ps.setString(index++, partyHist.getTaxNbr());
            }
            if (partyHist.getCompanySize() != null) {
                ps.setString(index++, partyHist.getCompanySize());
            }
            if (partyHist.getStartTime() != null) {
                ps.setDate(index++, partyHist.getStartTime());
            }
            if (partyHist.getCompanyCharacter() != null) {
                ps.setString(index++, partyHist.getCompanyCharacter());
            }
            if (partyHist.getCompanyStructure() != null) {
                ps.setString(index++, partyHist.getCompanyStructure());
            }
            if (partyHist.getRemarks() != null) {
                ps.setString(index++, partyHist.getRemarks());
            }
            if (partyHist.getGander() != null) {
                ps.setString(index++, partyHist.getGander());
            }
            if (partyHist.getBirthday() != null) {
                ps.setDate(index++, partyHist.getBirthday());
            }
            if (partyHist.getAge() != null) {
                ps.setString(index++, partyHist.getAge());
            }
            if (partyHist.getNativePlace() != null) {
                ps.setString(index++, partyHist.getNativePlace());
            }
            if (partyHist.getNationality() != null) {
                ps.setString(index++, partyHist.getNationality());
            }
            if (partyHist.getPoliticalStatus() != null) {
                ps.setString(index++, partyHist.getPoliticalStatus());
            }
            if (partyHist.getEducLevel() != null) {
                ps.setString(index++, partyHist.getEducLevel());
            }
            if (partyHist.getGraduateSchool() != null) {
                ps.setString(index++, partyHist.getGraduateSchool());
            }
            if (partyHist.getMaritalStatus() != null) {
                ps.setString(index++, partyHist.getMaritalStatus());
            }
            if (partyHist.getDomesticRelation() != null) {
                ps.setString(index++, partyHist.getDomesticRelation());
            }
            if (partyHist.getMaritalDate() != null) {
                ps.setDate(index++, partyHist.getMaritalDate());
            }
            if (partyHist.getCompanyName() != null) {
                ps.setString(index++, partyHist.getCompanyName());
            }
            if (partyHist.getDepartment() != null) {
                ps.setString(index++, partyHist.getDepartment());
            }
            if (partyHist.getPosition() != null) {
                ps.setString(index++, partyHist.getPosition());
            }
            if (partyHist.getWorkAddr() != null) {
                ps.setString(index++, partyHist.getWorkAddr());
            }
            if (partyHist.getFuncDesc() != null) {
                ps.setString(index++, partyHist.getFuncDesc());
            }
            if (partyHist.getSalary() != null) {
                ps.setString(index++, partyHist.getSalary());
            }
            if (partyHist.getCharType() != null) {
                ps.setString(index++, partyHist.getCharType());
            }
            if (partyHist.getFavorite() != null) {
                ps.setString(index++, partyHist.getFavorite());
            }
            if (partyHist.getTelecomAttitude() != null) {
                ps.setString(index++, partyHist.getTelecomAttitude());
            }
            if (partyHist.getWorkExper() != null) {
                ps.setString(index++, partyHist.getWorkExper());
            }
            if (partyHist.getBranchId() != null) {
                ps.setString(index++, partyHist.getBranchId());
            }
            if (partyHist.getAreaId() != null) {
                ps.setString(index++, partyHist.getAreaId());
            }
            if (partyHist.getLocalNetId() != null) {
                ps.setString(index++, partyHist.getLocalNetId());
            }
            if (partyHist.getServDeptId() != null) {
                ps.setString(index++, partyHist.getServDeptId());
            }
            if (partyHist.getName() != null) {
                ps.setString(index++, partyHist.getName());
            }
            if (partyHist.getNameBrief() != null) {
                ps.setString(index++, partyHist.getNameBrief());
            }
            if (partyHist.getNameOther() != null) {
                ps.setString(index++, partyHist.getNameOther());
            }
            if (partyHist.getPartyType() != null) {
                ps.setString(index++, partyHist.getPartyType());
            }
            if (partyHist.getStandardCode() != null) {
                ps.setString(index++, partyHist.getStandardCode());
            }
            if (partyHist.getSts() != null) {
                ps.setString(index++, partyHist.getSts());
            }
            if (partyHist.getStsDate() != null) {
                ps.setTimestamp(index++, partyHist.getStsDate());
            }
            if (partyHist.getCreateDate() != null) {
                ps.setTimestamp(index++, partyHist.getCreateDate());
            }
            if (partyHist.getCommunicationSpecialty() != null) {
                ps.setString(index++, partyHist.getCommunicationSpecialty());
            }
            if (partyHist.getMainProduction() != null) {
                ps.setString(index++, partyHist.getMainProduction());
            }
            if (partyHist.getTotalAsset() != null) {
                ps.setString(index++, partyHist.getTotalAsset());
            }
            if (partyHist.getWebAddress() != null) {
                ps.setString(index++, partyHist.getWebAddress());
            }
            if (partyHist.getLastModDate() != null) {
                ps.setTimestamp(index++, partyHist.getLastModDate());
            }
            ps.setString(index++, partyHist.getPartyHistId());
            ps.execute();
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "update error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void delete(GenericVO vo) throws AppException, SysException {
        PartyHistSVO partyHist = (PartyHistSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from PARTY_HIST where 1=1");
        sql.append(" and PARTY_HIST_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyHist.getPartyHistId());
            ps.execute();
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "delete error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        PartyHistSVO result = null;
        PartyHistSVO partyHist = (PartyHistSVO) vo;
        StringBuffer sql = new StringBuffer("select * from PARTY_HIST where 1=1");
        sql.append(" and PARTY_HIST_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, partyHist.getPartyHistId());
            rs = ps.executeQuery();
            result = (PartyHistSVO) ResultSetUtil.convertToVo(rs, PartyHistSVO.class);
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "findByPK error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public List findByVO(GenericVO vo) throws AppException, SysException {
        List results = null;
        PartyHistSVO partyHist = (PartyHistSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from PARTY_HIST where 1=1");
        if (partyHist.getPartyId() != null) {
            sql.append(" and PARTY_ID=?");
        }
        if (partyHist.getSeq() != null) {
            sql.append(" and SEQ=?");
        }
        if (partyHist.getPartyHistId() != null) {
            sql.append(" and PARTY_HIST_ID=?");
        }
        if (partyHist.getJurPerson() != null) {
            sql.append(" and JUR_PERSON=?");
        }
        if (partyHist.getNetType() != null) {
            sql.append(" and NET_TYPE=?");
        }
        if (partyHist.getTaxNbr() != null) {
            sql.append(" and TAX_NBR=?");
        }
        if (partyHist.getCompanySize() != null) {
            sql.append(" and COMPANY_SIZE=?");
        }
        if (partyHist.getStartTime() != null) {
            sql.append(" and START_TIME=?");
        }
        if (partyHist.getCompanyCharacter() != null) {
            sql.append(" and COMPANY_CHARACTER=?");
        }
        if (partyHist.getCompanyStructure() != null) {
            sql.append(" and COMPANY_STRUCTURE=?");
        }
        if (partyHist.getRemarks() != null) {
            sql.append(" and REMARKS=?");
        }
        if (partyHist.getGander() != null) {
            sql.append(" and GANDER=?");
        }
        if (partyHist.getBirthday() != null) {
            sql.append(" and BIRTHDAY=?");
        }
        if (partyHist.getAge() != null) {
            sql.append(" and AGE=?");
        }
        if (partyHist.getNativePlace() != null) {
            sql.append(" and NATIVE_PLACE=?");
        }
        if (partyHist.getNationality() != null) {
            sql.append(" and NATIONALITY=?");
        }
        if (partyHist.getPoliticalStatus() != null) {
            sql.append(" and POLITICAL_STATUS=?");
        }
        if (partyHist.getEducLevel() != null) {
            sql.append(" and EDUC_LEVEL=?");
        }
        if (partyHist.getGraduateSchool() != null) {
            sql.append(" and GRADUATE_SCHOOL=?");
        }
        if (partyHist.getMaritalStatus() != null) {
            sql.append(" and MARITAL_STATUS=?");
        }
        if (partyHist.getDomesticRelation() != null) {
            sql.append(" and DOMESTIC_RELATION=?");
        }
        if (partyHist.getMaritalDate() != null) {
            sql.append(" and MARITAL_DATE=?");
        }
        if (partyHist.getCompanyName() != null) {
            sql.append(" and COMPANY_NAME=?");
        }
        if (partyHist.getDepartment() != null) {
            sql.append(" and DEPARTMENT=?");
        }
        if (partyHist.getPosition() != null) {
            sql.append(" and POSITION=?");
        }
        if (partyHist.getWorkAddr() != null) {
            sql.append(" and WORK_ADDR=?");
        }
        if (partyHist.getFuncDesc() != null) {
            sql.append(" and FUNC_DESC=?");
        }
        if (partyHist.getSalary() != null) {
            sql.append(" and SALARY=?");
        }
        if (partyHist.getCharType() != null) {
            sql.append(" and CHAR_TYPE=?");
        }
        if (partyHist.getFavorite() != null) {
            sql.append(" and FAVORITE=?");
        }
        if (partyHist.getTelecomAttitude() != null) {
            sql.append(" and TELECOM_ATTITUDE=?");
        }
        if (partyHist.getWorkExper() != null) {
            sql.append(" and WORK_EXPER=?");
        }
        if (partyHist.getBranchId() != null) {
            sql.append(" and BRANCH_ID=?");
        }
        if (partyHist.getAreaId() != null) {
            sql.append(" and AREA_ID=?");
        }
        if (partyHist.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (partyHist.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=?");
        }
        if (partyHist.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (partyHist.getNameBrief() != null) {
            sql.append(" and NAME_BRIEF=?");
        }
        if (partyHist.getNameOther() != null) {
            sql.append(" and NAME_OTHER=?");
        }
        if (partyHist.getPartyType() != null) {
            sql.append(" and PARTY_TYPE=?");
        }
        if (partyHist.getStandardCode() != null) {
            sql.append(" and STANDARD_CODE=?");
        }
        if (partyHist.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (partyHist.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (partyHist.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        if (partyHist.getCommunicationSpecialty() != null) {
            sql.append(" and COMMUNICATION_SPECIALTY=?");
        }
        if (partyHist.getMainProduction() != null) {
            sql.append(" and MAIN_PRODUCTION=?");
        }
        if (partyHist.getTotalAsset() != null) {
            sql.append(" and TOTAL_ASSET=?");
        }
        if (partyHist.getWebAddress() != null) {
            sql.append(" and WEB_ADDRESS=?");
        }
        if (partyHist.getLastModDate() != null) {
            sql.append(" and LAST_MOD_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(),
                    partyHist.getLocalNetId()));
            int index = 1;
            if (partyHist.getPartyId() != null) {
                ps.setString(index++, partyHist.getPartyId());
            }
            if (partyHist.getSeq() != null) {
                ps.setString(index++, partyHist.getSeq());
            }
            if (partyHist.getPartyHistId() != null) {
                ps.setString(index++, partyHist.getPartyHistId());
            }
            if (partyHist.getJurPerson() != null) {
                ps.setString(index++, partyHist.getJurPerson());
            }
            if (partyHist.getNetType() != null) {
                ps.setString(index++, partyHist.getNetType());
            }
            if (partyHist.getTaxNbr() != null) {
                ps.setString(index++, partyHist.getTaxNbr());
            }
            if (partyHist.getCompanySize() != null) {
                ps.setString(index++, partyHist.getCompanySize());
            }
            if (partyHist.getStartTime() != null) {
                ps.setDate(index++, partyHist.getStartTime());
            }
            if (partyHist.getCompanyCharacter() != null) {
                ps.setString(index++, partyHist.getCompanyCharacter());
            }
            if (partyHist.getCompanyStructure() != null) {
                ps.setString(index++, partyHist.getCompanyStructure());
            }
            if (partyHist.getRemarks() != null) {
                ps.setString(index++, partyHist.getRemarks());
            }
            if (partyHist.getGander() != null) {
                ps.setString(index++, partyHist.getGander());
            }
            if (partyHist.getBirthday() != null) {
                ps.setDate(index++, partyHist.getBirthday());
            }
            if (partyHist.getAge() != null) {
                ps.setString(index++, partyHist.getAge());
            }
            if (partyHist.getNativePlace() != null) {
                ps.setString(index++, partyHist.getNativePlace());
            }
            if (partyHist.getNationality() != null) {
                ps.setString(index++, partyHist.getNationality());
            }
            if (partyHist.getPoliticalStatus() != null) {
                ps.setString(index++, partyHist.getPoliticalStatus());
            }
            if (partyHist.getEducLevel() != null) {
                ps.setString(index++, partyHist.getEducLevel());
            }
            if (partyHist.getGraduateSchool() != null) {
                ps.setString(index++, partyHist.getGraduateSchool());
            }
            if (partyHist.getMaritalStatus() != null) {
                ps.setString(index++, partyHist.getMaritalStatus());
            }
            if (partyHist.getDomesticRelation() != null) {
                ps.setString(index++, partyHist.getDomesticRelation());
            }
            if (partyHist.getMaritalDate() != null) {
                ps.setDate(index++, partyHist.getMaritalDate());
            }
            if (partyHist.getCompanyName() != null) {
                ps.setString(index++, partyHist.getCompanyName());
            }
            if (partyHist.getDepartment() != null) {
                ps.setString(index++, partyHist.getDepartment());
            }
            if (partyHist.getPosition() != null) {
                ps.setString(index++, partyHist.getPosition());
            }
            if (partyHist.getWorkAddr() != null) {
                ps.setString(index++, partyHist.getWorkAddr());
            }
            if (partyHist.getFuncDesc() != null) {
                ps.setString(index++, partyHist.getFuncDesc());
            }
            if (partyHist.getSalary() != null) {
                ps.setString(index++, partyHist.getSalary());
            }
            if (partyHist.getCharType() != null) {
                ps.setString(index++, partyHist.getCharType());
            }
            if (partyHist.getFavorite() != null) {
                ps.setString(index++, partyHist.getFavorite());
            }
            if (partyHist.getTelecomAttitude() != null) {
                ps.setString(index++, partyHist.getTelecomAttitude());
            }
            if (partyHist.getWorkExper() != null) {
                ps.setString(index++, partyHist.getWorkExper());
            }
            if (partyHist.getBranchId() != null) {
                ps.setString(index++, partyHist.getBranchId());
            }
            if (partyHist.getAreaId() != null) {
                ps.setString(index++, partyHist.getAreaId());
            }
            if (partyHist.getLocalNetId() != null) {
                ps.setString(index++, partyHist.getLocalNetId());
            }
            if (partyHist.getServDeptId() != null) {
                ps.setString(index++, partyHist.getServDeptId());
            }
            if (partyHist.getName() != null) {
                ps.setString(index++, partyHist.getName());
            }
            if (partyHist.getNameBrief() != null) {
                ps.setString(index++, partyHist.getNameBrief());
            }
            if (partyHist.getNameOther() != null) {
                ps.setString(index++, partyHist.getNameOther());
            }
            if (partyHist.getPartyType() != null) {
                ps.setString(index++, partyHist.getPartyType());
            }
            if (partyHist.getStandardCode() != null) {
                ps.setString(index++, partyHist.getStandardCode());
            }
            if (partyHist.getSts() != null) {
                ps.setString(index++, partyHist.getSts());
            }
            if (partyHist.getStsDate() != null) {
                ps.setTimestamp(index++, partyHist.getStsDate());
            }
            if (partyHist.getCreateDate() != null) {
                ps.setTimestamp(index++, partyHist.getCreateDate());
            }
            if (partyHist.getCommunicationSpecialty() != null) {
                ps.setString(index++, partyHist.getCommunicationSpecialty());
            }
            if (partyHist.getMainProduction() != null) {
                ps.setString(index++, partyHist.getMainProduction());
            }
            if (partyHist.getTotalAsset() != null) {
                ps.setString(index++, partyHist.getTotalAsset());
            }
            if (partyHist.getWebAddress() != null) {
                ps.setString(index++, partyHist.getWebAddress());
            }
            if (partyHist.getLastModDate() != null) {
                ps.setTimestamp(index++, partyHist.getLastModDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartyHistSVO.class);
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
            throw new SysException("", "findByVO error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return results;
    }

}
