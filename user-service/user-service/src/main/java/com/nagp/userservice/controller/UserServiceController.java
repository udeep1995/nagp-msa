package com.nagp.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nagp.userservice.beans.AccountBean;
import com.nagp.userservice.criteria.UserCriteria;
import com.nagp.userservice.service.UserService;


@RestController
public class UserServiceController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public Integer test(){
		return 100;
	}
	
	@GetMapping("/user/{emailId}/{password}")
	public String getUserByEmailAndPassword(@PathVariable String emailId, @PathVariable String password) throws Exception{
		Gson gson = new Gson();
		return gson.toJson(userService.getUserByEmailIdAndPassword(emailId, password));
	}
	
	@PostMapping("/user")
	public Integer registerUser(UserCriteria user) throws Exception {
		return userService.registerNewUser(user);
	}
		
	@PutMapping("/user")
	public Integer updateUser(UserCriteria user) throws Exception {
		return userService.updateUser(user);
	}
	
	@PostMapping("/user/account/link")
	public String linkAccount(@RequestParam Integer accNumber, @RequestParam String accPassword, @RequestParam String emailId, @RequestParam String password) throws Exception {
		Gson gson = new Gson();
		return gson.toJson(userService.linkAccount(accNumber, accPassword, emailId, password));
	}
	
	@PostMapping("/user/account/view")
	public List<AccountBean> viewAccountsLinked(@RequestParam String emailId, @RequestParam String password) throws Exception {
			List<AccountBean> linkedAccounts =  userService.viewLinkedAccounts(emailId, password);
			if(linkedAccounts == null)
				return new ArrayList<AccountBean>();
			else 
				return linkedAccounts;
	}
}
