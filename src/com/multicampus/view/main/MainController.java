package com.multicampus.view.main;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multicampus.biz.common.DBManagerException;
import com.multicampus.biz.common.ListObject;
import com.multicampus.biz.common.ListObjectException;
import com.multicampus.biz.main.MainService;
import com.multicampus.biz.main.vo.MainVO;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/goMainView.do")
	public String goMainView(MainVO vo, 
							 HttpSession session, 
							 Model model) {
		ListObject setattrLo = null;
		try{
			String userId = (String)session.getAttribute("EMPLOYEE_NUMBER");
			if(userId == null) return "login.jsp";
			// Null Check
			if(vo.getEMPLOYEE_NUMBER() == null) vo.setEMPLOYEE_NUMBER(userId);
			
			setattrLo = mainService.goMainView(vo);
			
			
			model.addAttribute("mainList1", setattrLo.get(0));
			model.addAttribute("mainList2", setattrLo.get(1));
			
			model.addAttribute("topFrame", "/main/top.jsp");
			model.addAttribute("bodyFrame", "/main/basic_body.jsp");
			
		}catch(DBManagerException de){
		}catch(ListObjectException le){
		}
		return "/main/main.jsp";
	}
	
	@RequestMapping("/getMain.do")
	public String getMain(MainVO vo, 
			               Model model) {
		model.addAttribute("main", mainService.getMain(vo));
		return "/main/getMain.jsp";
	}
}
