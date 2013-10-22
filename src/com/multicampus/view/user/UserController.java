package com.multicampus.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multicampus.biz.user.UserService;
import com.multicampus.biz.user.vo.UserVO;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/loginView.do")
	public String loginView(){
		return "login.jsp";
	}

	@RequestMapping("/login.do")
	public String login(UserVO vo, 
			            HttpSession session) {
		UserVO user = userService.getUser(vo);

		if(user != null){
			session.setAttribute("EMPLOYEE_NUMBER", user.getEMPLOYEE_NUMBER());
			session.setAttribute("EMPLOYEE_NAME", user.getEMPLOYEE_NAME());
			//return "redirect:/getBoardList.do";
			return "redirect:/goMainView.do";
		}else{
			return "login.jsp";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index.jsp";
	}
}
