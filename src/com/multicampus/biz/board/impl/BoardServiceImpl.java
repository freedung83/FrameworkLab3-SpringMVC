package com.multicampus.biz.board.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multicampus.biz.board.BoardService;
import com.multicampus.biz.board.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAOIbatis boardDAO;
	
	public void addBoard(BoardVO vo) {
		boardDAO.addBoard(vo);
	}

	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}

	public ArrayList<BoardVO> getBoardList(BoardVO vo) {
		vo.setSearchKeyword("%"+vo.getSearchKeyword()+"%");
		return boardDAO.getBoardList(vo);
	}
}

