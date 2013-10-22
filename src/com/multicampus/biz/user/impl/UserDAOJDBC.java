package com.multicampus.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.multicampus.biz.common.JDBCUtil;
import com.multicampus.biz.user.vo.UserVO;

@Repository
public class UserDAOJDBC {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from users where id=? and password=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getEMPLOYEE_NUMBER());
			stmt.setString(2, vo.getEMPLOYEE_NAME());
			rs = stmt.executeQuery();
			if(rs.next()){
				user = new UserVO();
				user.setEMPLOYEE_NUMBER(rs.getString("ID"));
				user.setEMPLOYEE_NAME(rs.getString("PASSWORD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
}
