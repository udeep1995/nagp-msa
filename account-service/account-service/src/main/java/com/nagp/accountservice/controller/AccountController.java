package com.nagp.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nagp.accountservice.beans.Account;
import com.nagp.accountservice.beans.AccountCriteria;
import com.nagp.accountservice.service.AccountService;

@RestController
public class AccountController {

	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/account/active")
	public Boolean getActiveAccountByAccountNumber(@RequestParam Integer accNumber, @RequestParam String accPassword) throws Exception {
		Account acc = accountService.getActiveAccountByAccNumber(accNumber, accPassword);
		if(acc == null) {
			return false;
		}
		return true;
	}

	@GetMapping("/account/{accNumber}/{accPassword}")
	public String getAccountByAccNumberAndAccPassword(@PathVariable Integer accNumber, @PathVariable String accPassword) throws Exception{
		Gson gson = new Gson();
		Account acc = accountService.getAccountInfoByAccNumber(accNumber);
		return gson.toJson(acc);
	}
	
	@PostMapping("/account")
	public Integer registerAccount(AccountCriteria accountCriteria) throws Exception {
		return accountService.registerAccount(accountCriteria);
	}
	
	@PostMapping("/account/addbalance")
	public String addBalance(Integer accNumber, String accPassword, Double balanceToAdd) throws Exception {
		Gson gson = new Gson();
		return gson.toJson(accountService.addAccountBalance(accNumber, accPassword, balanceToAdd));
	}
	
	@PostMapping("/account/subbalance")
	public String subBalance(Integer accNumber, String accPassword, Double balanceToSub) throws Exception {
		Gson gson = new Gson();
		return gson.toJson(accountService.subAccountBalance(accNumber, accPassword, balanceToSub));
	}
	
	@PostMapping("/account/transferbalance")
	public String transferBalance(@RequestParam Integer fromAccNumber,@RequestParam  Integer toAccNumber,@RequestParam  String fromAccPassword,@RequestParam  Double balanceToTransfer) throws Exception {
		Gson gson = new Gson();
		return gson.toJson(accountService.transferBalance(fromAccNumber,toAccNumber,fromAccPassword, balanceToTransfer));
	}
	
	
	@PutMapping("/account/branch")
	public Account changeBranch(@RequestParam Integer accNumber, @RequestParam String accPassword, @RequestParam String newBranch) throws Exception {
		return accountService.changeBranch(accNumber, accPassword, newBranch);
	}
	@PostMapping("/account/checkbook")
	public Account issueCheckBook(@RequestParam Integer accNumber, @RequestParam String accPassword) throws Exception {
		return accountService.issueCheckBook(accNumber, accPassword);
	}	
	@PostMapping("/account/checkbook/block")
	public Account blockCheckBook(@RequestParam Integer accNumber, @RequestParam String accPassword) throws Exception {
		return accountService.blockCheckBook(accNumber, accPassword);
	}
	@DeleteMapping("/account/close")
	public Account closeAccount(@RequestParam Integer accNumber, @RequestParam String accPassword) throws Exception {
		return accountService.closeAccount(accNumber, accPassword);
	}
}
