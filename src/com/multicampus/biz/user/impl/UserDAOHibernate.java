package com.multicampus.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.multicampus.biz.user.vo.UserVO;

@Repository
public class UserDAOHibernate {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public UserVO getUser(UserVO vo) {
		List<UserVO> userList = hibernateTemplate.find("select u from UserVO u where u.EMPLOYEE_NUMBER=? ", vo.getEMPLOYEE_NUMBER());
		return userList.get(0);
	}
}









