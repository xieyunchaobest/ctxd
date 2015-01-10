package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IAttachmentMDAO;
import com.cattsoft.sm.vo.AttachmentMVO;
import com.cattsoft.sm.vo.AttachmentSVO;

public class AttachmentMDAOImpl implements IAttachmentMDAO {

	private static Logger log = Logger.getLogger(AttachmentMDAOImpl.class);

	public void updateAttachment(FormFile file, String attachmentId)
			throws AppException, SysException {
		if (StringUtil.isBlank(attachmentId)) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "UPDATE ATTACHMENT SET ATTACHMENT=? WHERE ATTACHMENT_ID=?";
			ps = conn.prepareStatement(sql);
			ps.setBinaryStream(1, file.getInputStream(), file.getFileSize());
			ps.setLong(2, Long.parseLong(attachmentId));
			Sql logSql = new Sql(sql);
			logSql.log(this.getClass());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SysException("100003", "JDBC操作异常！", e);
		} catch (Exception e) {
			throw new SysException("100003", "JDBC操作异常！", e);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	public void deleteByVo(AttachmentMVO attachMVO) throws AppException, SysException {
		if (StringUtil.isBlank(attachMVO.getObjectId())
				||StringUtil.isBlank(attachMVO.getForScene())
				) {
			throw new AppException("100002", "没有提供附件归属对象或附件归属对象的主键标识！");
		}
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "DELETE FROM ATTACHMENT WHERE OBJECT_ID=? AND FOR_SCENE=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, attachMVO.getObjectId());
			ps.setString(2, attachMVO.getForScene());
			Sql logSql = new Sql(sql);
			logSql.log(this.getClass());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SysException("100003", "JDBC操作异常！", e);
		} catch (Exception e) {
			throw new SysException("100003", "JDBC操作异常！", e);
		} finally {
			JdbcUtil.close(ps);
		}
		
	}	
	
	/**
	 * 主键查询的SQL。
	 * 
	 * @return String ： 主键查询的SQL。
	 */
	public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		AttachmentSVO attach = (AttachmentSVO) vo;
		if (StringUtil.isBlank(attach.getAttachmentId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Sql sql = new Sql(
				"SELECT ATTACHMENT,FILE_NAME,LOCAL_NET_ID,REMARKS,ATTACHMENT_ID,FOR_SCENE,STS,STS_DATE,OBJECT_ID FROM ATTACHMENT WHERE 1=1  ");
		sql.append(" and ATTACHMENT_ID=:attachmentId");
		sql.setLong("attachmentId", attach.getAttachmentId());

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		attach = null;
		AttachmentMVO attachMVO = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				attachMVO = new AttachmentMVO();
				Blob b = rs.getBlob("ATTACHMENT");
				attachMVO.setBytes(b.getBytes(1, (int) b.length()));
				attachMVO.setFileName(rs.getString("FILE_NAME"));
				attachMVO.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				attachMVO.setRemarks(rs.getString("REMARKS"));
				attachMVO.setAttachmentId(rs.getString("ATTACHMENT_ID"));
				attachMVO.setForScene(rs.getString("FOR_SCENE"));
				attachMVO.setSts(rs.getString("STS"));
				attachMVO.setStsDate(rs.getTimestamp("STS_DATE"));
				attachMVO.setObjectId(rs.getString("OBJECT_ID"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return attachMVO;
	}
}
