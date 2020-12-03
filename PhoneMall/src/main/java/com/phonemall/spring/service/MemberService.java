package com.phonemall.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.phonemall.spring.dao.MemberMapper;
import com.phonemall.spring.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	public boolean checkMemberContain(String id) {
		MemberVO mem = mapper.selectMem(id);
		if(mem == null) {
			return false;
		}else
			return true;
	}

	public void addMember(MemberVO member) {
		MemberVO mem = new MemberVO(member);
		
		mem.setPassword(pwEncoder.encode(mem.getPassword()));
		mapper.insertMember(member);
		mapper.inserUserAuthDefault(mem.getId());
	}
}
