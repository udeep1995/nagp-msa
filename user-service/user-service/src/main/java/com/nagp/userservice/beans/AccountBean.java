package com.nagp.userservice.beans;

public class AccountBean {

	Integer accountNumber;
	String accountType;
	Double balance;
	Boolean isClosed;
	String checkBookNumber;
	String branch;
	Boolean isCheckBookBlocked;
	String accountPassword;

	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public Boolean getIsCheckBookBlocked() {
		return isCheckBookBlocked;
	}
	public void setIsCheckBookBlocked(Boolean isCheckBookBlocked) {
		this.isCheckBookBlocked = isCheckBookBlocked;
	}
	public String getCheckBookNumber() {
		return checkBookNumber;
	}
	public void setCheckBookNumber(String checkBookNumber) {
		this.checkBookNumber = checkBookNumber;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Boolean getIsClosed() {
		return isClosed;
	}
	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}
	
	public AccountBean(){
		super();
	}
		
}
