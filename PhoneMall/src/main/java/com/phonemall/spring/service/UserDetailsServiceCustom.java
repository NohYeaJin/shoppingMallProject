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
		
		// �ش� ���̵��� username���� MemberVO select �ؿ�
		MemberVO memInfo = mapper.selectMem(username);
		
		if(memInfo == null)
			return null;
		
		// ������ MemberVO ��ü ������  UserDetailsVO ��ü ����
		else {
			userDetails.setUsername(memInfo.getId());
			userDetails.setPassword(memInfo.getPassword());
			userDetails.setName(memInfo.getName());
			
			userDetails.setAuthorities(mapper.selectUserAuthOne(username));
		}
		
		return userDetails;
	}
}
