package com.cattsoft.pub.util;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;

public class Parameter {
    private String name;

    private Object value;

    private char type;

    public static final char TIME_STAMP = 'T';

    public static final char NULL_TIME_STAMP = 't';

    public static final char INTEGER = 'I';

    public static final char NULL_INTEGER = 'i';

    public static final char LONG = 'L';

    public static final char NULL_LONG = 'l';

    public static final char STRING = 'S';

    public static final char NULL_STRING = 's';

    public static final char DATE = 'D';

    public static final char NULL_DATE = 'd';

    public static final char Blob = 'B';

    public static final char NULL_Blob = 'b';

    public static final char Clob = 'C';

    public static final char NULL_Clob = 'c';

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Parameter(String name, char type, Object value) {

        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 根据param信息设置ps的参数
     * 
     * @param ps
     * @param index
     * @param param
     * @throws AppException
     */
    public PreparedStatement fillParam(PreparedStatement ps, int index) throws SysException,
            AppException {

        try {
            switch (type) {
            case Parameter.LONG:
                Long longValue = (Long) value;
                ps.setLong(index, longValue.longValue());
                break;
            case Parameter.NULL_LONG:
                ps.setNull(index, Types.NUMERIC);
                break;
            case Parameter.STRING:
                String str = (String) value;
                ps.setString(index, str);
                break;
            case Parameter.NULL_STRING:
                ps.setNull(index, Types.VARCHAR);
                break;
            case Parameter.INTEGER:
                Integer intValue = (Integer) value;
                ps.setInt(index, intValue.intValue());
                break;
            case Parameter.NULL_INTEGER:
                ps.setNull(index, Types.INTEGER);
                break;
            case Parameter.DATE:
                Date dateValue = (Date) value;
                ps.setDate(index, dateValue);
                break;
            case Parameter.NULL_DATE:
                ps.setNull(index, Types.DATE);
                break;
            case Parameter.TIME_STAMP:
                Timestamp timeValue = (Timestamp) value;
                ps.setTimestamp(index, timeValue);
                break;
            case Parameter.NULL_TIME_STAMP:
                ps.setNull(index, Types.DATE);
                break;
            case Parameter.NULL_Blob:
                ps.setNull(index, Types.BLOB);
                break;
            case Parameter.Blob:
                Blob blobValue = (Blob) value;
                ps.setBlob(index, blobValue);
                break;
            case Parameter.NULL_Clob:
                ps.setNull(index, Types.CLOB);
                break;
            case Parameter.Clob:
                Clob clobValue = (Clob) value;
                ps.setClob(index, clobValue);
                break;

            default:
                throw new AppException("100008", "系统未定义对对应的sql参数类型：" + type);
            }
        } catch (SQLException se) {
            throw new SysException("100009", "设置sql参数错误！", se);
        }
        return ps;
    }

    /**
     * 根据type类型返回当前参数，提交到数据库应该在sql对应的字符串
     * 
     * @return
     * @throws AppException
     */
    public String getSqlStr() throws AppException {

        switch (type) {
        case Parameter.LONG:
            Long longValue = (Long) value;
            return longValue.toString();
        case Parameter.NULL_LONG:
            return "Null";
        case Parameter.STRING:
            String str = (String) value;
            return "'" + str + "'";
        case Parameter.NULL_STRING:
            return "Null";
        case Parameter.INTEGER:
            Integer intValue = (Integer) value;
            return intValue.toString();
        case Parameter.NULL_INTEGER:
            return "Null";
        case Parameter.DATE:
            Date dateValue = (Date) value;
            return "to_date('" + DateUtil.to_char(dateValue, "yyyy-MM-dd") + "','YYYY-MM-DD') ";
        case Parameter.NULL_DATE:
            return "Null";
        case Parameter.TIME_STAMP:
            Timestamp timeValue = (Timestamp) value;
            return "to_date('" + DateUtil.to_char(timeValue, "yyyy-MM-dd HH:mm:ss")
                    + "','YYYY-MM-DD HH24:MI:ss') ";
        case Parameter.NULL_TIME_STAMP:
            return "Null";
        default:
            throw new AppException("100008", "系统未定义对对应的sql参数类型：" + type);
        }
    }

}
