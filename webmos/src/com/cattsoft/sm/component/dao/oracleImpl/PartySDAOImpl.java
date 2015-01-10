package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PartitionUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartySDAO;
import com.cattsoft.sm.vo.PartySVO;

public class PartySDAOImpl implements IPartySDAO {

    public void add(GenericVO vo) throws AppException, SysException {
    	
        PartySVO party = (PartySVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" PARTY(PARTY_ID,BRANCH_ID,AREA_ID,LOCAL_NET_ID,SERV_DEPT_ID,PARTY_TYPE,NAME,NAME_BRIEF,NAME_OTHER,JUR_PERSON,NET_TYPE,TAX_NBR,COMPANY_SIZE,START_TIME,COMPANY_CHARACTER,COMPANY_STRUCTURE,REMARKS,MAIN_PRODUCTION,TOTAL_ASSET,GANDER,BIRTHDAY,AGE,NATIVE_PLACE,NATIONALITY,POLITICAL_STATUS,EDUC_LEVEL,GRADUATE_SCHOOL,MARITAL_STATUS,DOMESTIC_RELATION,MARITAL_DATE,COMPANY_NAME,DEPARTMENT,POSITION,WORK_ADDR,FUNC_DESC,SALARY,CHAR_TYPE,FAVORITE,TELECOM_ATTITUDE,WORK_EXPER,STANDARD_CODE,CREATE_DATE,COMMUNICATION_SPECIALTY,WEB_ADDRESS,LAST_MOD_DATE,STS,STS_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            if (party.getPartyId() == null) {
                ps.setString(1, MaxId.getSequenceNextVal(Constant.SEQ_PARTY_ID));
            } else {
                ps.setString(1, party.getPartyId());
            }
            ps.setString(2, party.getBranchId());
            ps.setString(3, party.getAreaId());
            ps.setString(4, party.getLocalNetId());
            ps.setString(5, party.getServDeptId());
            ps.setString(6, party.getPartyType());
            ps.setString(7, party.getName());
            ps.setString(8, party.getNameBrief());
            ps.setString(9, party.getNameOther());
            ps.setString(10, party.getJurPerson());
            ps.setString(11, party.getNetType());
            ps.setString(12, party.getTaxNbr());
            ps.setString(13, party.getCompanySize());
            ps.setDate(14, party.getStartTime());
            ps.setString(15, party.getCompanyCharacter());
            ps.setString(16, party.getCompanyStructure());
            ps.setString(17, party.getRemarks());
            ps.setString(18, party.getMainProduction());
            ps.setString(19, party.getTotalAsset());
            ps.setString(20, party.getGander());
            ps.setDate(21, party.getBirthday());
            ps.setString(22, party.getAge());
            ps.setString(23, party.getNativePlace());
            ps.setString(24, party.getNationality());
            ps.setString(25, party.getPoliticalStatus());
            ps.setString(26, party.getEducLevel());
            ps.setString(27, party.getGraduateSchool());
            ps.setString(28, party.getMaritalStatus());
            ps.setString(29, party.getDomesticRelation());
            ps.setDate(30, party.getMaritalDate());
            ps.setString(31, party.getCompanyName());
            ps.setString(32, party.getDepartment());
            ps.setString(33, party.getPosition());
            ps.setString(34, party.getWorkAddr());
            ps.setString(35, party.getFuncDesc());
            ps.setString(36, party.getSalary());
            ps.setString(37, party.getCharType());
            ps.setString(38, party.getFavorite());
            ps.setString(39, party.getTelecomAttitude());
            ps.setString(40, party.getWorkExper());
            ps.setString(41, party.getStandardCode());
            ps.setTimestamp(42, party.getCreateDate());
            ps.setString(43, party.getCommunicationSpecialty());
            ps.setString(44, party.getWebAddress());
            ps.setTimestamp(45, party.getLastModDate());
            ps.setString(46, party.getSts());
            ps.setTimestamp(47, party.getStsDate());
            ps.execute();
        } catch (SQLException e) {
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
        PartySVO party = (PartySVO) vo;
        StringBuffer sql = new StringBuffer("update PARTY set");
        if (party.getBranchId() != null) {
            sql.append(" BRANCH_ID=?,");
        }
        if (party.getAreaId() != null) {
            sql.append(" AREA_ID=?,");
        }
        if (party.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (party.getServDeptId() != null) {
            sql.append(" SERV_DEPT_ID=?,");
        }
        if (party.getPartyType() != null) {
            sql.append(" PARTY_TYPE=?,");
        }
        if (party.getName() != null) {
            sql.append(" NAME=?,");
        }
        if (party.getNameBrief() != null) {
            sql.append(" NAME_BRIEF=?,");
        }
        if (party.getNameOther() != null) {
            sql.append(" NAME_OTHER=?,");
        }
        if (party.getJurPerson() != null) {
            sql.append(" JUR_PERSON=?,");
        }
        if (party.getNetType() != null) {
            sql.append(" NET_TYPE=?,");
        }
        if (party.getTaxNbr() != null) {
            sql.append(" TAX_NBR=?,");
        }
        if (party.getCompanySize() != null) {
            sql.append(" COMPANY_SIZE=?,");
        }
        if (party.getStartTime() != null) {
            sql.append(" START_TIME=?,");
        }
        if (party.getCompanyCharacter() != null) {
            sql.append(" COMPANY_CHARACTER=?,");
        }
        if (party.getCompanyStructure() != null) {
            sql.append(" COMPANY_STRUCTURE=?,");
        }
        if (party.getRemarks() != null) {
            sql.append(" REMARKS=?,");
        }
        if (party.getMainProduction() != null) {
            sql.append(" MAIN_PRODUCTION=?,");
        }
        if (party.getTotalAsset() != null) {
            sql.append(" TOTAL_ASSET=?,");
        }
        if (party.getGander() != null) {
            sql.append(" GANDER=?,");
        }
        if (party.getBirthday() != null) {
            sql.append(" BIRTHDAY=?,");
        }
        if (party.getAge() != null) {
            sql.append(" AGE=?,");
        }
        if (party.getNativePlace() != null) {
            sql.append(" NATIVE_PLACE=?,");
        }
        if (party.getNationality() != null) {
            sql.append(" NATIONALITY=?,");
        }
        if (party.getPoliticalStatus() != null) {
            sql.append(" POLITICAL_STATUS=?,");
        }
        if (party.getEducLevel() != null) {
            sql.append(" EDUC_LEVEL=?,");
        }
        if (party.getGraduateSchool() != null) {
            sql.append(" GRADUATE_SCHOOL=?,");
        }
        if (party.getMaritalStatus() != null) {
            sql.append(" MARITAL_STATUS=?,");
        }
        if (party.getDomesticRelation() != null) {
            sql.append(" DOMESTIC_RELATION=?,");
        }
        if (party.getMaritalDate() != null) {
            sql.append(" MARITAL_DATE=?,");
        }
        if (party.getCompanyName() != null) {
            sql.append(" COMPANY_NAME=?,");
        }
        if (party.getDepartment() != null) {
            sql.append(" DEPARTMENT=?,");
        }
        if (party.getPosition() != null) {
            sql.append(" POSITION=?,");
        }
        if (party.getWorkAddr() != null) {
            sql.append(" WORK_ADDR=?,");
        }
        if (party.getFuncDesc() != null) {
            sql.append(" FUNC_DESC=?,");
        }
        if (party.getSalary() != null) {
            sql.append(" SALARY=?,");
        }
        if (party.getCharType() != null) {
            sql.append(" CHAR_TYPE=?,");
        }
        if (party.getFavorite() != null) {
            sql.append(" FAVORITE=?,");
        }
        if (party.getTelecomAttitude() != null) {
            sql.append(" TELECOM_ATTITUDE=?,");
        }
        if (party.getWorkExper() != null) {
            sql.append(" WORK_EXPER=?,");
        }
        if (party.getStandardCode() != null) {
            sql.append(" STANDARD_CODE=?,");
        }
        if (party.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        if (party.getCommunicationSpecialty() != null) {
            sql.append(" COMMUNICATION_SPECIALTY=?,");
        }
        if (party.getWebAddress() != null) {
            sql.append(" WEB_ADDRESS=?,");
        }
        if (party.getLastModDate() != null) {
            sql.append(" LAST_MOD_DATE=?,");
        }
        if (party.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (party.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and PARTY_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (party.getBranchId() != null) {
                ps.setString(index++, party.getBranchId());
            }
            if (party.getAreaId() != null) {
                ps.setString(index++, party.getAreaId());
            }
            if (party.getLocalNetId() != null) {
                ps.setString(index++, party.getLocalNetId());
            }
            if (party.getServDeptId() != null) {
                ps.setString(index++, party.getServDeptId());
            }
            if (party.getPartyType() != null) {
                ps.setString(index++, party.getPartyType());
            }
            if (party.getName() != null) {
                ps.setString(index++, party.getName());
            }
            if (party.getNameBrief() != null) {
                ps.setString(index++, party.getNameBrief());
            }
            if (party.getNameOther() != null) {
                ps.setString(index++, party.getNameOther());
            }
            if (party.getJurPerson() != null) {
                ps.setString(index++, party.getJurPerson());
            }
            if (party.getNetType() != null) {
                ps.setString(index++, party.getNetType());
            }
            if (party.getTaxNbr() != null) {
                ps.setString(index++, party.getTaxNbr());
            }
            if (party.getCompanySize() != null) {
                ps.setString(index++, party.getCompanySize());
            }
            if (party.getStartTime() != null) {
                ps.setDate(index++, party.getStartTime());
            }
            if (party.getCompanyCharacter() != null) {
                ps.setString(index++, party.getCompanyCharacter());
            }
            if (party.getCompanyStructure() != null) {
                ps.setString(index++, party.getCompanyStructure());
            }
            if (party.getRemarks() != null) {
                ps.setString(index++, party.getRemarks());
            }
            if (party.getMainProduction() != null) {
                ps.setString(index++, party.getMainProduction());
            }
            if (party.getTotalAsset() != null) {
                ps.setString(index++, party.getTotalAsset());
            }
            if (party.getGander() != null) {
                ps.setString(index++, party.getGander());
            }
            if (party.getBirthday() != null) {
                ps.setDate(index++, party.getBirthday());
            }
            if (party.getAge() != null) {
                ps.setString(index++, party.getAge());
            }
            if (party.getNativePlace() != null) {
                ps.setString(index++, party.getNativePlace());
            }
            if (party.getNationality() != null) {
                ps.setString(index++, party.getNationality());
            }
            if (party.getPoliticalStatus() != null) {
                ps.setString(index++, party.getPoliticalStatus());
            }
            if (party.getEducLevel() != null) {
                ps.setString(index++, party.getEducLevel());
            }
            if (party.getGraduateSchool() != null) {
                ps.setString(index++, party.getGraduateSchool());
            }
            if (party.getMaritalStatus() != null) {
                ps.setString(index++, party.getMaritalStatus());
            }
            if (party.getDomesticRelation() != null) {
                ps.setString(index++, party.getDomesticRelation());
            }
            if (party.getMaritalDate() != null) {
                ps.setDate(index++, party.getMaritalDate());
            }
            if (party.getCompanyName() != null) {
                ps.setString(index++, party.getCompanyName());
            }
            if (party.getDepartment() != null) {
                ps.setString(index++, party.getDepartment());
            }
            if (party.getPosition() != null) {
                ps.setString(index++, party.getPosition());
            }
            if (party.getWorkAddr() != null) {
                ps.setString(index++, party.getWorkAddr());
            }
            if (party.getFuncDesc() != null) {
                ps.setString(index++, party.getFuncDesc());
            }
            if (party.getSalary() != null) {
                ps.setString(index++, party.getSalary());
            }
            if (party.getCharType() != null) {
                ps.setString(index++, party.getCharType());
            }
            if (party.getFavorite() != null) {
                ps.setString(index++, party.getFavorite());
            }
            if (party.getTelecomAttitude() != null) {
                ps.setString(index++, party.getTelecomAttitude());
            }
            if (party.getWorkExper() != null) {
                ps.setString(index++, party.getWorkExper());
            }
            if (party.getStandardCode() != null) {
                ps.setString(index++, party.getStandardCode());
            }
            if (party.getCreateDate() != null) {
                ps.setTimestamp(index++, party.getCreateDate());
            }
            if (party.getCommunicationSpecialty() != null) {
                ps.setString(index++, party.getCommunicationSpecialty());
            }
            if (party.getWebAddress() != null) {
                ps.setString(index++, party.getWebAddress());
            }
            if (party.getLastModDate() != null) {
                ps.setTimestamp(index++, party.getLastModDate());
            }
            if (party.getSts() != null) {
                ps.setString(index++, party.getSts());
            }
            if (party.getStsDate() != null) {
                ps.setTimestamp(index++, party.getStsDate());
            }
            ps.setString(index++, party.getPartyId());
            ps.execute();
        } catch (SQLException e) {
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
        PartySVO party = (PartySVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from PARTY where 1=1");
        sql.append(" and PARTY_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, party.getPartyId());
            ps.execute();
        } catch (SQLException e) {
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
        PartySVO result = null;
        PartySVO party = (PartySVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.PARTY_ID,a.BRANCH_ID,a.AREA_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.PARTY_TYPE,a.NAME,a.NAME_BRIEF,a.NAME_OTHER,a.JUR_PERSON,a.NET_TYPE,a.TAX_NBR,a.COMPANY_SIZE,a.START_TIME,a.COMPANY_CHARACTER,a.COMPANY_STRUCTURE,a.REMARKS,a.MAIN_PRODUCTION,a.TOTAL_ASSET,a.GANDER,a.BIRTHDAY,a.AGE,a.NATIVE_PLACE,a.NATIONALITY,a.POLITICAL_STATUS,a.EDUC_LEVEL,a.GRADUATE_SCHOOL,a.MARITAL_STATUS,a.DOMESTIC_RELATION,a.MARITAL_DATE,a.COMPANY_NAME,a.DEPARTMENT,a.POSITION,a.WORK_ADDR,a.FUNC_DESC,a.SALARY,a.CHAR_TYPE,a.FAVORITE,a.TELECOM_ATTITUDE,a.WORK_EXPER,a.STANDARD_CODE,a.CREATE_DATE,a.COMMUNICATION_SPECIALTY,a.WEB_ADDRESS,a.LAST_MOD_DATE,a.STS,a.STS_DATE");
        sql.append(" from PARTY a where 1=1");
        sql.append(" and PARTY_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, party.getPartyId());
            rs = ps.executeQuery();
            result = (PartySVO) ResultSetUtil.convertToVo(rs, PartySVO.class);
        } catch (SQLException e) {
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
        PartySVO party = (PartySVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.PARTY_ID,a.BRANCH_ID,a.AREA_ID,a.LOCAL_NET_ID,a.SERV_DEPT_ID,a.PARTY_TYPE,a.NAME,a.NAME_BRIEF,a.NAME_OTHER,a.JUR_PERSON,a.NET_TYPE,a.TAX_NBR,a.COMPANY_SIZE,a.START_TIME,a.COMPANY_CHARACTER,a.COMPANY_STRUCTURE,a.REMARKS,a.MAIN_PRODUCTION,a.TOTAL_ASSET,a.GANDER,a.BIRTHDAY,a.AGE,a.NATIVE_PLACE,a.NATIONALITY,a.POLITICAL_STATUS,a.EDUC_LEVEL,a.GRADUATE_SCHOOL,a.MARITAL_STATUS,a.DOMESTIC_RELATION,a.MARITAL_DATE,a.COMPANY_NAME,a.DEPARTMENT,a.POSITION,a.WORK_ADDR,a.FUNC_DESC,a.SALARY,a.CHAR_TYPE,a.FAVORITE,a.TELECOM_ATTITUDE,a.WORK_EXPER,a.STANDARD_CODE,a.CREATE_DATE,a.COMMUNICATION_SPECIALTY,a.WEB_ADDRESS,a.LAST_MOD_DATE,a.STS,a.STS_DATE");
        sql.append(" from PARTY a where 1=1");
        if (party.getPartyId() != null) {
            sql.append(" and PARTY_ID=?");
        }
        if (party.getBranchId() != null) {
            sql.append(" and BRANCH_ID=?");
        }
        if (party.getAreaId() != null) {
            sql.append(" and AREA_ID=?");
        }
        if (party.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (party.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=?");
        }
        if (party.getPartyType() != null) {
            sql.append(" and PARTY_TYPE=?");
        }
        if (party.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (party.getNameBrief() != null) {
            sql.append(" and NAME_BRIEF=?");
        }
        if (party.getNameOther() != null) {
            sql.append(" and NAME_OTHER=?");
        }
        if (party.getJurPerson() != null) {
            sql.append(" and JUR_PERSON=?");
        }
        if (party.getNetType() != null) {
            sql.append(" and NET_TYPE=?");
        }
        if (party.getTaxNbr() != null) {
            sql.append(" and TAX_NBR=?");
        }
        if (party.getCompanySize() != null) {
            sql.append(" and COMPANY_SIZE=?");
        }
        if (party.getStartTime() != null) {
            sql.append(" and START_TIME=?");
        }
        if (party.getCompanyCharacter() != null) {
            sql.append(" and COMPANY_CHARACTER=?");
        }
        if (party.getCompanyStructure() != null) {
            sql.append(" and COMPANY_STRUCTURE=?");
        }
        if (party.getRemarks() != null) {
            sql.append(" and REMARKS=?");
        }
        if (party.getMainProduction() != null) {
            sql.append(" and MAIN_PRODUCTION=?");
        }
        if (party.getTotalAsset() != null) {
            sql.append(" and TOTAL_ASSET=?");
        }
        if (party.getGander() != null) {
            sql.append(" and GANDER=?");
        }
        if (party.getBirthday() != null) {
            sql.append(" and BIRTHDAY=?");
        }
        if (party.getAge() != null) {
            sql.append(" and AGE=?");
        }
        if (party.getNativePlace() != null) {
            sql.append(" and NATIVE_PLACE=?");
        }
        if (party.getNationality() != null) {
            sql.append(" and NATIONALITY=?");
        }
        if (party.getPoliticalStatus() != null) {
            sql.append(" and POLITICAL_STATUS=?");
        }
        if (party.getEducLevel() != null) {
            sql.append(" and EDUC_LEVEL=?");
        }
        if (party.getGraduateSchool() != null) {
            sql.append(" and GRADUATE_SCHOOL=?");
        }
        if (party.getMaritalStatus() != null) {
            sql.append(" and MARITAL_STATUS=?");
        }
        if (party.getDomesticRelation() != null) {
            sql.append(" and DOMESTIC_RELATION=?");
        }
        if (party.getMaritalDate() != null) {
            sql.append(" and MARITAL_DATE=?");
        }
        if (party.getCompanyName() != null) {
            sql.append(" and COMPANY_NAME=?");
        }
        if (party.getDepartment() != null) {
            sql.append(" and DEPARTMENT=?");
        }
        if (party.getPosition() != null) {
            sql.append(" and POSITION=?");
        }
        if (party.getWorkAddr() != null) {
            sql.append(" and WORK_ADDR=?");
        }
        if (party.getFuncDesc() != null) {
            sql.append(" and FUNC_DESC=?");
        }
        if (party.getSalary() != null) {
            sql.append(" and SALARY=?");
        }
        if (party.getCharType() != null) {
            sql.append(" and CHAR_TYPE=?");
        }
        if (party.getFavorite() != null) {
            sql.append(" and FAVORITE=?");
        }
        if (party.getTelecomAttitude() != null) {
            sql.append(" and TELECOM_ATTITUDE=?");
        }
        if (party.getWorkExper() != null) {
            sql.append(" and WORK_EXPER=?");
        }
        if (party.getStandardCode() != null) {
            sql.append(" and STANDARD_CODE=?");
        }
        if (party.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        if (party.getCommunicationSpecialty() != null) {
            sql.append(" and COMMUNICATION_SPECIALTY=?");
        }
        if (party.getWebAddress() != null) {
            sql.append(" and WEB_ADDRESS=?");
        }
        if (party.getLastModDate() != null) {
            sql.append(" and LAST_MOD_DATE=?");
        }
        if (party.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (party.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(), party
                    .getLocalNetId()));
            int index = 1;
            if (party.getPartyId() != null) {
                ps.setString(index++, party.getPartyId());
            }
            if (party.getBranchId() != null) {
                ps.setString(index++, party.getBranchId());
            }
            if (party.getAreaId() != null) {
                ps.setString(index++, party.getAreaId());
            }
            if (party.getLocalNetId() != null) {
                ps.setString(index++, party.getLocalNetId());
            }
            if (party.getServDeptId() != null) {
                ps.setString(index++, party.getServDeptId());
            }
            if (party.getPartyType() != null) {
                ps.setString(index++, party.getPartyType());
            }
            if (party.getName() != null) {
                ps.setString(index++, party.getName());
            }
            if (party.getNameBrief() != null) {
                ps.setString(index++, party.getNameBrief());
            }
            if (party.getNameOther() != null) {
                ps.setString(index++, party.getNameOther());
            }
            if (party.getJurPerson() != null) {
                ps.setString(index++, party.getJurPerson());
            }
            if (party.getNetType() != null) {
                ps.setString(index++, party.getNetType());
            }
            if (party.getTaxNbr() != null) {
                ps.setString(index++, party.getTaxNbr());
            }
            if (party.getCompanySize() != null) {
                ps.setString(index++, party.getCompanySize());
            }
            if (party.getStartTime() != null) {
                ps.setDate(index++, party.getStartTime());
            }
            if (party.getCompanyCharacter() != null) {
                ps.setString(index++, party.getCompanyCharacter());
            }
            if (party.getCompanyStructure() != null) {
                ps.setString(index++, party.getCompanyStructure());
            }
            if (party.getRemarks() != null) {
                ps.setString(index++, party.getRemarks());
            }
            if (party.getMainProduction() != null) {
                ps.setString(index++, party.getMainProduction());
            }
            if (party.getTotalAsset() != null) {
                ps.setString(index++, party.getTotalAsset());
            }
            if (party.getGander() != null) {
                ps.setString(index++, party.getGander());
            }
            if (party.getBirthday() != null) {
                ps.setDate(index++, party.getBirthday());
            }
            if (party.getAge() != null) {
                ps.setString(index++, party.getAge());
            }
            if (party.getNativePlace() != null) {
                ps.setString(index++, party.getNativePlace());
            }
            if (party.getNationality() != null) {
                ps.setString(index++, party.getNationality());
            }
            if (party.getPoliticalStatus() != null) {
                ps.setString(index++, party.getPoliticalStatus());
            }
            if (party.getEducLevel() != null) {
                ps.setString(index++, party.getEducLevel());
            }
            if (party.getGraduateSchool() != null) {
                ps.setString(index++, party.getGraduateSchool());
            }
            if (party.getMaritalStatus() != null) {
                ps.setString(index++, party.getMaritalStatus());
            }
            if (party.getDomesticRelation() != null) {
                ps.setString(index++, party.getDomesticRelation());
            }
            if (party.getMaritalDate() != null) {
                ps.setDate(index++, party.getMaritalDate());
            }
            if (party.getCompanyName() != null) {
                ps.setString(index++, party.getCompanyName());
            }
            if (party.getDepartment() != null) {
                ps.setString(index++, party.getDepartment());
            }
            if (party.getPosition() != null) {
                ps.setString(index++, party.getPosition());
            }
            if (party.getWorkAddr() != null) {
                ps.setString(index++, party.getWorkAddr());
            }
            if (party.getFuncDesc() != null) {
                ps.setString(index++, party.getFuncDesc());
            }
            if (party.getSalary() != null) {
                ps.setString(index++, party.getSalary());
            }
            if (party.getCharType() != null) {
                ps.setString(index++, party.getCharType());
            }
            if (party.getFavorite() != null) {
                ps.setString(index++, party.getFavorite());
            }
            if (party.getTelecomAttitude() != null) {
                ps.setString(index++, party.getTelecomAttitude());
            }
            if (party.getWorkExper() != null) {
                ps.setString(index++, party.getWorkExper());
            }
            if (party.getStandardCode() != null) {
                ps.setString(index++, party.getStandardCode());
            }
            if (party.getCreateDate() != null) {
                ps.setTimestamp(index++, party.getCreateDate());
            }
            if (party.getCommunicationSpecialty() != null) {
                ps.setString(index++, party.getCommunicationSpecialty());
            }
            if (party.getWebAddress() != null) {
                ps.setString(index++, party.getWebAddress());
            }
            if (party.getLastModDate() != null) {
                ps.setTimestamp(index++, party.getLastModDate());
            }
            if (party.getSts() != null) {
                ps.setString(index++, party.getSts());
            }
            if (party.getStsDate() != null) {
                ps.setTimestamp(index++, party.getStsDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, PartySVO.class);
        } catch (SQLException e) {
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
