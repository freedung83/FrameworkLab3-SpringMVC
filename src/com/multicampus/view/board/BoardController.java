package com.multicampus.view.board;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.multicampus.biz.board.BoardService;
import com.multicampus.biz.board.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/addBoardView.do")
	public String addBoardView(){
		return "addBoard.jsp";
	}
	
	@RequestMapping("/addBoard.do")
	public String addBoard(@ModelAttribute("board") BoardVO vo, 
			               BindingResult result) throws IOException {		
		// 유효성 체크
		new BoardValidator().validate(vo, result);
		if(result.hasErrors()){
			return "addBoard.jsp";
		}
		
		// 파일 업로드
		MultipartFile uploadFile = vo.getUploadFile();
		if(uploadFile != null && uploadFile.getOriginalFilename().length() > 0){
			String fileName = uploadFile.getOriginalFilename();
			vo.setFileName(fileName);
			byte[] fileData = uploadFile.getBytes();
			FileOutputStream output = new FileOutputStream("C:/spring/FrameworkLab3-SpringMVC/WebContent/uploadFile/"+fileName);
			output.write(fileData);
		}
		
		boardService.addBoard(vo);
		return "redirect:/getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo) {
		boardService.updateBoard(vo);
		return "redirect:/getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "redirect:/getBoardList.do";
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, 
			               Model model) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "getBoard.jsp";
	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, 
			                   HttpSession session, 
			                   Model model) {
		String userId = (String)session.getAttribute("userId");
		if(userId == null) return "login.jsp";
		// Null Check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
				
		model.addAttribute("boardList", boardService.getBoardList(vo)); 
		return "getBoardList.jsp";
	}
}
