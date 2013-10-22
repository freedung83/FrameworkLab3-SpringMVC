package com.multicampus.biz.main.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.multicampus.biz.common.EntityObject;
import com.multicampus.biz.main.vo.MainVO;

@Repository
public class MainDAOIbatis {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public MainVO getMain(MainVO vo){
		return (MainVO)sqlMapClientTemplate.queryForObject("getMain", vo);
	}
	/**
	 * 메뉴리스트를 가져옴
	 * @param vo
	 * @return
	 */
	public ArrayList<MainVO> findMyMenu(MainVO vo){
		return (ArrayList<MainVO>)sqlMapClientTemplate.queryForList("findMyMenu", vo);
	}
	
	public EntityObject getUserByUserId(MainVO vo){
		return (EntityObject)sqlMapClientTemplate.queryForObject("getUserByUserId", vo);
	}
	
}












