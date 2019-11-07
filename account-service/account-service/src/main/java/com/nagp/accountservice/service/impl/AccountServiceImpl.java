package com.nagp.accountservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nagp.accountservice.beans.Account;
import com.nagp.accountservice.beans.AccountCriteria;
import com.nagp.accountservice.proxy.UserBean;
import com.nagp.accountservice.proxy.UserServiceProxy;
import com.nagp.accountservice.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private UserServiceProxy userServiceProxy;
	
	List<Account> allAccounts = new ArrayList<Account>();
	
	@Override
	public Integer registerAccount(AccountCriteria accountCriteria) throws Exception {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String userStr = userServiceProxy.getUserByEmailIdAndPassword(accountCriteria.getEmailId(),accountCriteria.getPassword());
		UserBean user = gson.fromJson(userStr, UserBean.class);
		if(user == null) {
			throw new Exception("Invalid user");
		}
		
		Account newAccount = new Account();
		newAccount.setAccountType(accountCriteria.getAccountType());
		newAccount.setBalance(accountCriteria.getBalance());
		newAccount.setBranch(accountCriteria.getBranch());
		if(allAccounts.size() == 0) {
			newAccount.setAccountNumber(100112345);
		} else {
			newAccount.setAccountNumber(allAccounts.get(allAccounts.size() - 1).getAccountNumber() + 1);
		}
		newAccount.setAccountPassword(accountCriteria.getAccountPassword());
		newAccount.setIsClosed(false);
		newAccount.setIsCheckBookBlocked(false);
		allAccounts.add(newAccount);
		userServiceProxy.linkAccount(newAccount.getAccountNumber(), newAccount.getAccountPassword(), accountCriteria.getEmailId(), accountCriteria.getPassword());
		
		return newAccount.getAccountNumber();
	}

	@Override
	public Account getAccountByAccountNumberAndPassword(Integer accNumber, String password) {
		for(int i=0; i<allAccounts.size(); i++) {
			if(accNumber.equals(allAccounts.get(i).getAccountNumber()) && password.equals(allAccounts.get(i).getAccountPassword()) ){
				return allAccounts.get(i);
			}
		}
		return null;
	}
	
	@Override
	public Account changeBranch(Integer accountNumber, String accPassword, String newBranch) throws Exception {	
		Account acc = getAccountByAccountNumberAndPassword(accountNumber, accPassword);
		if(acc == null ){
			throw new Exception("Invalid account number or account password");
		}
		if(acc.getIsClosed()!=null && acc.getIsClosed() == true) {
			throw new Exception("This account is closed.");
		}
		acc.setBranch(newBranch);
		return acc;
	}


	@Override
	public Account blockCheckBook(Integer accNumber, String accPassword) throws Exception {
		Account acc = getAccountByAccountNumberAndPassword(accNumber, accPassword);
		if(acc == null ){
			throw new Exception("Invalid account number or account password");
		}
		if(acc.getIsClosed()!=null && acc.getIsClosed() == true) {
			throw new Exception("This account is closed.");
		}
		acc.setIsCheckBookBlocked(true);
		return acc;
	}


	@Override
	public Account issueCheckBook(Integer accNumber, String accPassword) throws Exception {
		Account acc = getAccountByAccountNumberAndPassword(accNumber, accPassword);
		if(acc == null ){
			throw new Exception("Invalid account number or account password");
		}
		if(acc.getIsClosed()!=null && acc.getIsClosed() == true) {
			throw new Exception("This account is closed and thus checkbook cant be issued");
		}
		acc.setCheckBookNumber(acc.getAccountNumber() - (Math.random()*100)%10 
				+ acc.getBranch().charAt((int) ((Math.random() * 100)% acc.getBranch().length()))
				+ "BANK");
		acc.setIsCheckBookBlocked(false);
		return acc;
	}

	@Override
	public Account closeAccount(Integer accNumber, String accPassword) throws Exception {
		// TODO Auto-generated method stub
		Account acc = getAccountByAccountNumberAndPassword(accNumber, accPassword);
		if(acc == null ){
			throw new Exception("Invalid account number or account password");
		}
		acc.setIsClosed(true);
		return acc;
	}
	
	private Account getAccountByAccountNumber(Integer accNumber) {
		for(int i=0; i<allAccounts.size(); i++) {
			if(accNumber.equals(allAccounts.get(i).getAccountNumber())){
				return allAccounts.get(i);
			}
		}
		return null;
	}

	@Override
	public Account getActiveAccountByAccNumber(Integer accNumber, String accPassword) throws Exception {
		// TODO Auto-generated method stub
		Account acc = getAccountByAccountNumber(accNumber);
		if(accPassword.equals(acc.getAccountPassword())) {
			if(acc.getIsClosed() == null || acc.getIsClosed() == true) {
				return null;
			}
			return acc;
		} else {
			return null;
		}
		
	}

	@Override
	public Account getAccountInfoByAccNumber(Integer accNumber) throws Exception {
		// TODO Auto-generated method stub
		Account acc = getAccountByAccountNumber(accNumber);
		return acc;
	}

	@Override
	public Account addAccountBalance(Integer accNumber, String accPassword, Double balanceToAdd) throws Exception {
		// TODO Auto-generated method stub
		Account acc = getAccountByAccountNumberAndPassword(accNumber,accPassword);
		if(acc == null) {
			throw new Exception("Invalid Account Credentials");
		}
		if(acc.getIsClosed()) {
			throw new Exception("This account is closed");
		}
		Double currBalance = acc.getBalance();
		acc.setBalance(currBalance + balanceToAdd);
		return acc;
	}
	
	@Override
	public Account subAccountBalance(Integer accNumber, String accPassword, Double balanceToSub) throws Exception {
		// TODO Auto-generated method stub
		Account acc = getAccountByAccountNumberAndPassword(accNumber,accPassword);
		if(acc == null) {
			throw new Exception("Invalid Account Credentials");
		}
		if(acc.getIsClosed()) {
			throw new Exception("This account is closed");
		}
		Double currBalance = acc.getBalance();
		if(currBalance<balanceToSub) {
			throw new Exception("Balance is low to deduct");
		}
		acc.setBalance(currBalance - balanceToSub);
		return acc;
	}

	@Override
	public Account transferBalance(Integer fromAccNumber, Integer toAccNumber, String fromAccPassword,
			Double balanceToTransfer) throws Exception {
		Account fromAcc = getAccountByAccountNumberAndPassword(fromAccNumber,fromAccPassword);
		Account toAcc = getAccountByAccountNumber(toAccNumber);
		if(fromAcc == null) {
			throw new Exception("Invalid Account Credentials");
		}
		if(toAcc == null) {
			throw new Exception("Invalid account number to transfer balance");
		}
		if(fromAcc.getIsClosed() || toAcc.getIsClosed()) {
			throw new Exception("Account is closed for transaction");
		}
		
		Double currBalance = fromAcc.getBalance();
		if(currBalance < balanceToTransfer) {
			throw new Exception("Account has less balance to complete this transaction");
		}
		fromAcc.setBalance(currBalance-balanceToTransfer);
		toAcc.setBalance(toAcc.getBalance() + balanceToTransfer);
		return fromAcc;
	}

	
	
}
