package com.phonemall.spring.dao;

import java.util.List;

import com.phonemall.spring.configure.Mapper;
import com.phonemall.spring.vo.MemberVO;

@Mapper
public interface MemberMapper {
	//�̹� �����ϴ��� Ȯ�� üũ
	public MemberVO selectMem(String id);
	//�������� �ʴ´ٸ� insert
	public void insertMember(MemberVO member);
	//���� ��ȸ
	public List<String> selectUserAuthOne(String username);
	//���� �Է�
	public void inserUserAuthDefault(String id);
}
