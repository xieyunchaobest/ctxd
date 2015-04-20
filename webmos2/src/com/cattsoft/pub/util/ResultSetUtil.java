package com.cattsoft.pub.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
public class ResultSetUtil 
{   
	private static Logger log = Logger.getLogger(ResultSetUtil.class);
	/**
     * convert data from a result set to a vo <br>
     * the result set should have only one row of data,<br>
     * otherwise <b>only the first</b> row would be converted.
     * 
     * @param rs
     *            result set
     * @param classOfVo
     *            class of a concrete vo
     * @return an instance of classOfVo
     */
    public static GenericVO convertToVo(ResultSet rs, Class classOfVo) throws AppException,
            SysException {
        GenericVO vo = null;
        try {
            if (rs.next()) {
                vo = convert(rs, classOfVo);
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(e.getMessage());
            }
            throw new AppException("", "结果集与VO转换错误！");
        }
        return vo;
    }
	/**
     * convert data from a result set to a list of vos
     * 
     * @param rs
     *            result set
     * @param classOfVo
     *            class of a concrete vo
     * @return a list of instances of classOfVo
     */
    public static List convertToList(ResultSet rs, Class classOfVo) throws AppException,
            SysException {
        List vos = new ArrayList();
        try {
            while (rs.next()) {
                vos.add(convert(rs, classOfVo));
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(e.getMessage());
            }
            throw new AppException("", "结果集与VO集合转换错误！");
        }
        return vos.size() == 0 ? null : vos;
    }	
    /*
     * 遍历VO对象，根据VO对象中的属性从ResultSet对象中取同名字段的值
     */
    private static GenericVO convert(ResultSet rs, Class classOfVo) throws Exception {
        GenericVO vo = null;
        vo = (GenericVO) classOfVo.newInstance();
        NameConverter nameconverter = NameConverter.getInstance();
        Field[] fields = classOfVo.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String name = nameconverter.convertToDb(field.getName());
            String type = field.getType().getName();
            try {
                if ("java.sql.Timestamp".equals(type)) {
                    field.set(vo, rs.getTimestamp(name));
                } else if ("java.util.Date".equals(type)) {
                    field.set(vo, rs.getDate(name));
                } else if("java.sql.Date".equals(type)){
                	field.set(vo, rs.getDate(name));
                }else if (TypeConverter.isBlob(type)) {
                    field.set(vo, rs.getBlob(name));
                } else if (TypeConverter.isClob(type)) {
                    field.set(vo, rs.getClob(name));
                } else {
                    field.set(vo, rs.getString(name));
                }
            } catch (SQLException e) {
                // in most circumstances this exception is ORACLE "invalid column" exception,
                // just ignore it.
            }
        }
        return vo;
    }
}
