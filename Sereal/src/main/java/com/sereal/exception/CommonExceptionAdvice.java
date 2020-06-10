package com.sereal.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

//이 클래스의 인스턴스가 스프링의 컨트롤러에서 발생하는 예외를 처리할 것임을 명시한다.
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	//이 메소드가 이 어노테이션의 괄호 안의 예외 타입을 처리할 것임을 지정한다.
	//여기선 Exception.class로 지정했으므로 모든 예외에 대한 처리를 시행한다.
	//예를 들어 숫자 파라미터를 보내야 하는 주소에 문자 파라미터를 보내면 error_page라는 문자열이 리턴되고 그에 해당되는 jsp로 이동한다.
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		log.error("Exception.............."+ex.getMessage());
		model.addAttribute("exception",ex);
		log.error(model);
		return "error_page";
	}
	
	
	/*
	 * @ExceptionHandler(NoHandlerFoundException.class)
	 * 
	 * @ResponseStatus(HttpStatus.NOT_FOUND) public String
	 * handler404(NoHandlerFoundException ex) { return "custom404"; }
	 */	
	
}
