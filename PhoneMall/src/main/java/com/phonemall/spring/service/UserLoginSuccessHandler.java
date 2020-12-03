package com.phonemall.spring.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

/* 역할 : 로그인 페이지 접근 전 하던 곳으로 로그인 후 돌아가도록 해줌. */
//1. 로그인 안한 채 허용되지 않은 영역 접근 시 login page로 intercept 된 경우
//2. 로그인 화면 버튼을 눌러 들어온 경우 - intercept x

@Service
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//intercept 된 경우
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		String uri = "/";
		
		if(savedRequest != null) {
			uri = savedRequest.getRedirectUrl();
			
			requestCache.removeRequest(request, response);
			System.out.println(uri);
		}
		else {
			String prevPage = (String) request.getSession().getAttribute("prevPage");
			if(prevPage != null && !prevPage.equals("")) {
				uri = prevPage;
			}
		}
		
		response.sendRedirect(uri);
	}
	
}
