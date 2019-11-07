package com.nagp.paymentservice.beans;

public class PaymentCriteria {

	String emailId;
	String password;
	Integer fromAccNumber;
	String accPassword;
	Double balance;
	Integer toAccNumber;
	Integer accNumber;
	
	
	public Integer getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(Integer accNumber) {
		this.accNumber = accNumber;
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
	public Integer getFromAccNumber() {
		return fromAccNumber;
	}
	public void setFromAccNumber(Integer fromAccNumber) {
		this.fromAccNumber = fromAccNumber;
	}
	public String getAccPassword() {
		return accPassword;
	}
	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getToAccNumber() {
		return toAccNumber;
	}
	public void setToAccNumber(Integer toAccNumber) {
		this.toAccNumber = toAccNumber;
	}
	
	
}
