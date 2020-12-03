package com.phonemall.spring.dao;

import java.util.List;

import com.phonemall.spring.configure.Mapper;
import com.phonemall.spring.vo.MemberVO;

@Mapper
public interface MemberMapper {
	//이미 존재하는지 확인 체크
	public MemberVO selectMem(String id);
	//존재하지 않는다면 insert
	public void insertMember(MemberVO member);
	//권한 조회
	public List<String> selectUserAuthOne(String username);
	//권한 입력
	public void inserUserAuthDefault(String id);
}
