package com.nagp.accountservice.service;

import org.springframework.stereotype.Service;

import com.nagp.accountservice.beans.Account;
import com.nagp.accountservice.beans.AccountCriteria;

@Service
public interface AccountService {
	
	public Account getAccountInfoByAccNumber(Integer accNumber) throws Exception;
	public Account getAccountByAccountNumberAndPassword(Integer accNumber, String accPassword) throws Exception;
	public Account getActiveAccountByAccNumber(Integer accNumber, String accPassword) throws Exception;
	public Integer registerAccount(AccountCriteria accountCriteria) throws Exception;
	public Account changeBranch(Integer accountNumber, String accPassword, String newBranch) throws Exception;
	public Account blockCheckBook(Integer accNumber, String accPassword) throws Exception;
	public Account issueCheckBook(Integer accNumber, String accPassword) throws Exception;
	public Account closeAccount(Integer accNumber, String accPassword) throws Exception;
	public Account addAccountBalance(Integer accNumber, String accPassword, Double balanceToAdd) throws Exception;
	public Account subAccountBalance(Integer accNumber, String accPassword, Double balanceToSub) throws Exception;
	public Account transferBalance(Integer fromAccNumber, Integer toAccNumber, String fromAccPassword, Double balanceToTransfer) throws Exception;
}
