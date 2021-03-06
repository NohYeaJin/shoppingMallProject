package com.phonemall.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

import org.springframework.security.core.Authentication;

@Log4j
@Controller
public class PhoneMallController {
	
	@RequestMapping("/")
	public String toMainPage(){
		return "/mallView/mainPage";
	}
	
	@RequestMapping("/product")
	public String toProductPage() {
		return "/mallView/product";
	}
	
	@RequestMapping("/blog")
	public String toBlogPage() {
		return "/mallView/blog";
	}
	
	@RequestMapping("/about")
	public String toAboutPage() {
		return "/mallView/about";
	}
	
	@RequestMapping("/contact")
	public String toContackPage() {
		return "/mallView/contact";
	}
	
	@RequestMapping("/myPurchase")
	public String toMyAccount() {
		return "/mypage/myPurchase";
	}
	
	@RequestMapping("/wishList")
	public String toWishList() {
		return "/mypage/wishList";
	}
	
	@RequestMapping("/viewCart")
	public String toViewCart() {
		return "/mypage/viewCart";
	}
	
	@RequestMapping("/checkOut")
	public String toCheckOut() {
		return "/mypage/checkOut";
	}
	
	@RequestMapping("/myInfo")
	public String toMyInfo() {
		return "/mypage/myInfo";
	}
	
	@RequestMapping("/myInquiry")
	public String toMyInquiry() {
		return "/mypage/myInquiry";
	}
	
	@RequestMapping("/errorpage")
	public String toErrorPage() {
		return "/other/errorpage";
	}
}
