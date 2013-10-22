package com.multicampus.biz.board.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.multicampus.biz.board.vo.BoardVO;

@Repository
public class BoardDAOIbatis {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public void addBoard(BoardVO vo){
		sqlMapClientTemplate.insert("addBoard", vo);
	}
	
	public void updateBoard(BoardVO vo){	
		sqlMapClientTemplate.update("updateBoard", vo);
	}	
	
	public void deleteBoard(BoardVO vo){	
		sqlMapClientTemplate.delete("deleteBoard", vo);
	}
	
	public BoardVO getBoard(BoardVO vo){
		return (BoardVO)sqlMapClientTemplate.queryForObject("getBoard", vo);
	}
	
	public ArrayList<BoardVO> getBoardList(BoardVO vo){
		return (ArrayList<BoardVO>)sqlMapClientTemplate.queryForList("getBoardList", vo);
	}	
}












