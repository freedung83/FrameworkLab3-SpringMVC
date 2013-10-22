package com.multicampus.biz.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.multicampus.biz.user.vo.UserVO;

@Repository
public class UserDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserVO getUser(UserVO vo) {		
		String sql = "select * from users where id=? and password=?";
		Object[] args = {vo.getEMPLOYEE_NUMBER(), vo.getEMPLOYEE_NAME()};
		return jdbcTemplate.queryForObject(sql, args, new UserRowMapper());
	}
}

class UserRowMapper implements RowMapper<UserVO>{
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserVO user = new UserVO();
		user.setEMPLOYEE_NUMBER(rs.getString("ID"));
		user.setEMPLOYEE_NAME(rs.getString("PASSWORD"));
		return user;
	}
		
}
