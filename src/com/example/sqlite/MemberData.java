package com.example.sqlite;

public class MemberData {
	private long id;
	private String name;
	private String tel;
	private String email;
	
	void setId(long id){
		this.id = id;
	}
	
	void setName(String name){
		this.name=name;
	}
	void setTel(String tel){
		this.tel = tel;
	}
	void setEmail(String email){
		this.email=email;
	}
	
	long getId(){
		return id;
	}
	String getName(){
		return name;
	}
	String getTel(){
		return tel;
	}
	String getEmail(){
		return email;
	}
	
	
	
	
	
	
}
