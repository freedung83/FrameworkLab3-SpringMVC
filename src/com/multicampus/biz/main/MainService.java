package com.multicampus.biz.main;


import com.multicampus.biz.common.DBManagerException;
import com.multicampus.biz.common.ListObject;
import com.multicampus.biz.main.vo.MainVO;

public interface MainService {
	public ListObject goMainView(MainVO vo) throws DBManagerException;
	public MainVO getMain(MainVO vo);
}
