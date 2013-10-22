package com.multicampus.view.board;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.multicampus.biz.board.vo.BoardVO;

public class BoardValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return BoardVO.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		BoardVO board = (BoardVO)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "board.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "board.writer");
		if(board.getContent().length() <= 10){
			errors.rejectValue("content", "board.content");
		}
	}	
}








