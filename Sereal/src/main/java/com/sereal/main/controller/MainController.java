package com.sereal.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.innc.user.model.UserInfo;
import com.sereal.member.model.TMemberInfoModel;
import com.sereal.member.model.UserSessionModel;
//import com.jumanji.member.service.TMemberInfoService;
import com.sereal.member.service.TMemberInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/main")
@Log4j
@AllArgsConstructor
//MainController는 TMemberInfoService에 의존적이다(TMemberInfoService 없이는 제 역할을 못한다)
//때문에 AllArgsConstructor를 이용해 생성자를 만들고 자동으로 주입되게 한다(@Setter와 역할이 흡사함)
public class MainController {
	
	//메인페이지 이동, 로그인, 로그아웃 처리
	
	private TMemberInfoService service;
	
	//메인 페이지 보여줌
	@RequestMapping("")
	public String main(HttpServletRequest request) {
		
		log.info("main read");
		HttpSession session = request.getSession(); 		
		if(session.getAttribute("sessionModel") != null) {
			log.info("세션 있음");
		}else{
			log.info("세션 없음");
		}
		
		return "main/main";
	}
	
	//로그인 페이지 보여줌
	@RequestMapping("/login")
	public String login() {
		
		log.info("login read");		
		return "/main/login";
	}
	

	
	//사용자 로그인 처리
	@RequestMapping("/authenticate")
	public String user_authenticate(TMemberInfoModel memberModel, Model model, HttpServletRequest request) {
		
		log.info("user_authenticate read");	
		
	
		String user_id = memberModel.getUser_id();
		String user_pw = memberModel.getUser_pw();
		log.info("id" + user_id);
		log.info("pw" + user_pw);
		
		//전달 받은 아이디와 비번으로 사용자 조회
		TMemberInfoModel model2 = new TMemberInfoModel();
		model2 = service.selectMember(memberModel);
		
		//유효성 검사 후 세션 저장
		if(model2 != null) {
			
			//log.info("해당 아이디와 비번에 맞는 회원이 있음");
			
			//기존 세션에 저장된 정보가 있을 경우 제거함
			HttpSession session = request.getSession(); 			
			if(session.getAttribute("sessionModel") != null) {
				log.info("기존 로그인으로 저장 되었던 세션 정보를 제거합니다.");
				session.removeAttribute("sessionModel");
			}
			
			//셔센 모델에 아이디, 번호, 구분값 세팅 후 세션에 저장			
			UserSessionModel sessionModel = new UserSessionModel();
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(user_id);
			userInfo.setUserIdx(model2.getUser_idx());
			userInfo.setUserStCd(model2.getUser_st_cd());
			
			sessionModel.setUserInfo(userInfo);
			
			session.setAttribute("sessionModel", sessionModel);
			//return "redirect:/main";
			return "redirect:/board/generalBoardList";			
		}else{			
			//log.info("해당 아이디와 비번에 맞는 회원이 없음");
			model.addAttribute("CAUSE", "NOT_AUTH");
			return "redirect:/main/login";
		}
	
	}
	
	
	//로그아웃 처리
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {		
		log.info("logout read");		
		
		//세션 삭제
		HttpSession session = request.getSession(); 
		session.removeAttribute("sessionModel");

		return "redirect:/board/jumanjiBoardList";
	}
		
}
