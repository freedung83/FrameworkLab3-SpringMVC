package com.multicampus.biz.board.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.multicampus.biz.board.vo.BoardVO;

@Repository
public class BoardDAOHibernate {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void addBoard(BoardVO vo){	
		hibernateTemplate.save(vo);
	}
	
	public void updateBoard(BoardVO vo){	
		hibernateTemplate.update(vo);
	}	
	
	public void deleteBoard(BoardVO vo){	
		hibernateTemplate.delete(vo);
	}
	
	public BoardVO getBoard(BoardVO vo){
		return hibernateTemplate.get(BoardVO.class, vo.getSeq());
	}
	
	public ArrayList<BoardVO> getBoardList(BoardVO vo){
		return (ArrayList<BoardVO>) hibernateTemplate.find("from BoardVO b order by b.seq desc");
	}	
}












