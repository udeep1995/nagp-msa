package com.nagp.paymentservice.beans;

public class Payment {

	Integer id;
	Integer fromAccNumber;
	Integer toAccNumber;
	Double balance;
	String type;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFromAccNumber() {
		return fromAccNumber;
	}
	public void setFromAccNumber(Integer fromAccNumber) {
		this.fromAccNumber = fromAccNumber;
	}
	public Integer getToAccNumber() {
		return toAccNumber;
	}
	public void setToAccNumber(Integer toAccNumber) {
		this.toAccNumber = toAccNumber;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
}
