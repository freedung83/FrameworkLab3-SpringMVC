package com.multicampus.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.multicampus.biz.board.vo.BoardVO;

@Repository
public class BoardDAOSpring {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL 명령어들
	private final String BOARD_ADD = "insert into board(seq, title, writer, content, filename) values((select nvl(max(seq), 0)+1 from board),?,?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_LIST_TITLE = "select * from board where title like ? order by seq desc";
	private final String BOARD_LIST_CONTENT = "select * from board where content like ? order by seq desc";
	private final String BOARD_GET = "select * from board where seq=?";
	
	public void addBoard(BoardVO vo){
		jdbcTemplate.update(BOARD_ADD, vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getFileName());
	}
	
	public void updateBoard(BoardVO vo){
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}	
	
	public void deleteBoard(BoardVO vo){
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	public BoardVO getBoard(BoardVO vo){
		Object[] args = {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	public ArrayList<BoardVO> getBoardList(BoardVO vo){
		Object[] args = {vo.getSearchKeyword()};
		return (ArrayList<BoardVO>) 
				jdbcTemplate.query(BOARD_LIST_TITLE, 
						           args, 
						           new BoardRowMapper());
	}	
}

class BoardRowMapper implements RowMapper<BoardVO>{
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		board.setFileName(rs.getString("FILENAME"));
		return board;
	}	
}












