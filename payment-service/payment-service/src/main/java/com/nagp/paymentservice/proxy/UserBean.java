package com.nagp.paymentservice.proxy;

import java.util.List;

public class UserBean {

	Integer userId;
	List<AccountBean> linkedAccounts;
	String password;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<AccountBean> getLinkedAccounts() {
		return linkedAccounts;
	}
	public void setLinkedAccounts(List<AccountBean> linkedAccounts) {
		this.linkedAccounts = linkedAccounts;
	}
	
}
