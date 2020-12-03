package com.phonemall.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phonemall.spring.dao.MemberMapper;
import com.phonemall.spring.vo.MemberVO;
import com.phonemall.spring.vo.UserDetailsVO;

@Service
public class UserDetailsServiceCustom implements UserDetailsService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetailsVO userDetails = new UserDetailsVO();
		
		// 해당 아이디인 username으로 MemberVO select 해옴
		MemberVO memInfo = mapper.selectMem(username);
		
		if(memInfo == null)
			return null;
		
		// 가져온 MemberVO 객체 정보로  UserDetailsVO 객체 생성
		else {
			userDetails.setUsername(memInfo.getId());
			userDetails.setPassword(memInfo.getPassword());
			userDetails.setName(memInfo.getName());
			
			userDetails.setAuthorities(mapper.selectUserAuthOne(username));
		}
		
		return userDetails;
	}
}
