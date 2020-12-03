package com.phonemall.spring.vo;

public class MemberVO {
	
	private String id;
	private String name;
	private String password;
	private String email;
	private String address;
	private String phoneNum;
	
	public MemberVO() {
		
	}
	
	public MemberVO(MemberVO mem) {
		this.id = mem.id;
		this.name = mem.name;
		this.password = mem.password;
		this.email = mem.email;
		this.address = mem.address;
		this.phoneNum = mem.phoneNum;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
