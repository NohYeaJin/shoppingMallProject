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

/* ���� : �α��� ������ ���� �� �ϴ� ������ �α��� �� ���ư����� ����. */
//1. �α��� ���� ä ������ ���� ���� ���� �� login page�� intercept �� ���
//2. �α��� ȭ�� ��ư�� ���� ���� ��� - intercept x

@Service
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//intercept �� ���
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
