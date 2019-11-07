package com.nagp.userservice.beans;

import java.util.List;

public class User {
	
	Integer userId;
	String name;
	String emailId;
	String password;
	List<AccountBean> linkedAccounts;
	String gender;
	String address;
	String mNumber;
	String nickName;
	String nomineeName;
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getmNumber() {
		return mNumber;
	}
	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AccountBean> getLinkedAccounts() {
		return linkedAccounts;
	}
	public void setLinkedAccounts(List<AccountBean> linkedAccounts) {
		this.linkedAccounts = linkedAccounts;
	}
}
