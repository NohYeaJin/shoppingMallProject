package com.phonemall.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phonemall.spring.service.MemberService;
import com.phonemall.spring.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memService;
	
	//로그인 화면 요청 
//	@RequestMapping("/login")
//	public String toLoginPage(HttpServletRequest request) {
//		
//		String uri = request.getHeader("Referer");
//		if(!uri.contains("/loginView")) {
//			request.getSession().setAttribute("prevPage",
//								request.getHeader("Referer"));
//		}
//		return "/mypage/login";
//	}
	
	@RequestMapping("/login")
	public String loginInput(String error,String logout, Model model) {
			
		log.info("error: " + error);
		log.info("logout: " + logout);
			
		if(error!=null) {
			model.addAttribute("error","Login Error Check Your Account");
				
		}
		
		if(logout!=null) {
			model.addAttribute("logout","Logout!!");
		}
		return "/mypage/login";
	}
	
	
	//String타입의 파라미터 들어옴
	@RequestMapping("/IdDupCheck")
	@ResponseBody
	public String checkIdDuplication(@RequestParam String id) {
		boolean contain = memService.checkMemberContain(id);
		if(!contain) {
			return "notContain";
		}else
			return "contains";
	}
	
	@RequestMapping("/registerAsk")
	public String askAddMember(MemberVO member) {
		boolean contain = memService.checkMemberContain(member.getId());
		if(!contain) {
			memService.addMember(member);
		}else {
			return "/mypage/login";
		}
		return "/mallView/mainPage";
	}
	
}
