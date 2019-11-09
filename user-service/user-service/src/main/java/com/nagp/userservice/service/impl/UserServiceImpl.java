package com.nagp.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nagp.userservice.beans.AccountBean;
import com.nagp.userservice.beans.User;
import com.nagp.userservice.criteria.UserCriteria;
import com.nagp.userservice.proxy.AccountServiceProxy;
import com.nagp.userservice.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserServiceImpl implements UserService{

	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountServiceProxy accountServiceProxy;
	
	List<User> allUsers = new ArrayList<User>();
	
	@Override
	public Integer registerNewUser(UserCriteria userCriteria) throws Exception {		
		User isAlreadyRegistered = getUserByEmailId(userCriteria.getEmailId());
		if(isAlreadyRegistered != null) {
			throw new Exception("User is already registered");
		} 
		
		if(userCriteria.getEmailId() == null || userCriteria.getPassword() == null) {
			throw new Exception("Email Id and password cant be null");
		}

		User user = new User();
		user.setEmailId(userCriteria.getEmailId());
		user.setName(userCriteria.getName());
		user.setPassword(userCriteria.getPassword());
		user.setAddress(userCriteria.getAddress());
		user.setGender(userCriteria.getGender());
		user.setmNumber(userCriteria.getmNumber());
		user.setNickName(userCriteria.getNickName());
		user.setNomineeName(userCriteria.getNomineeName());
		
		if(allUsers.size() == 0) {
			user.setUserId(2019100);
		} else {
			user.setUserId(allUsers.get(allUsers.size() - 1).getUserId() + 1);
		}
		user.setLinkedAccounts(null);
		allUsers.add(user);
		LOGGER.info("{}", user);
		return user.getUserId();
	}
	
	
	private User getUserByEmailId(String emailId) {
		User user = null;
		
		for(int i=0; i<allUsers.size(); i++) {
			if(emailId.equals(allUsers.get(i).getEmailId())) {
				user = allUsers.get(i);
				break;
			}
		}
		return user;
	}

	@Override
	public User getUserByEmailIdAndPassword(String emailId, String password) throws Exception {
	
		User user = getUserByEmailId(emailId);
		if(user == null) {
			throw new Exception("No such user exists with this email id");
		} else {
			if(password.equals(user.getPassword())){
				return user;
			} else {
				throw new Exception("Invalid credentials");
			}
		}
	}

	@Override
	public Integer updateUser(UserCriteria userCriteria) throws Exception {

		if(userCriteria.getEmailId() == null || userCriteria.getPassword() == null) {
			throw new Exception("Email Id and password cant be null");
		}
		
		User user = getUserByEmailId(userCriteria.getEmailId());
		if(user == null) {
			throw new Exception("No such user with given emailid");
		}
		if(userCriteria.getAddress() != null) {
			user.setAddress(userCriteria.getAddress());
		}
		if(userCriteria.getmNumber() != null) {
			user.setmNumber(userCriteria.getmNumber());
		}
		if(userCriteria.getGender() != null) {
			user.setGender(userCriteria.getGender());
		}
		if(userCriteria.getName()!=null) {
			user.setName(userCriteria.getName());
		}
		
		if(userCriteria.getNickName()!=null) {
			user.setNickName(userCriteria.getNickName());
		}
		if(userCriteria.getNomineeName()!=null) {
			user.setNomineeName(userCriteria.getNomineeName());
		}
		
		return user.getUserId();
	}


	@Override
	public User linkAccount(Integer accountNumber, String accPassword, String emailId, String password) throws Exception {
		// TODO Auto-generated method stub
		User user = getUserByEmailId(emailId);
		if(user == null) {
			throw new Exception("No such user exists with this email id");
		} else {
			if(password.equals(user.getPassword())){
				List<AccountBean> accounts = user.getLinkedAccounts();
				if(accounts == null) {
					accounts = new ArrayList<AccountBean>();
				} else {
					for(int i=0; i<accounts.size(); i++) {
						if(accountNumber.equals(accounts.get(i).getAccountNumber())){
							return user;
						}
					}
				}
				 	AccountBean newAccount = new AccountBean();
				 	Boolean isAccountActive= accountServiceProxy.getActiveAccountByAccNumber(accountNumber, accPassword);
					if(isAccountActive)
					{
						newAccount.setAccountNumber(accountNumber);
						newAccount.setAccountPassword(accPassword);
					}
					else {
						throw new Exception("This account cant be linked");
					}	
					accounts.add(newAccount);
					user.setLinkedAccounts(accounts);
	
			} else {
				throw new Exception("Invalid credentials");
			}
		}
		return user;
	}


	@Override
	public List<AccountBean> viewLinkedAccounts(String emailId, String password) throws Exception {
		// TODO Auto-generated method stub
		User user = getUserByEmailId(emailId);
		List<AccountBean> accountInfo = new ArrayList<AccountBean>();
		if(user == null) {
			throw new Exception("No such user exists with this email id");
		} else {
			if(password.equals(user.getPassword())){
				Gson gson = new Gson();
				for(int i=0;i<user.getLinkedAccounts().size();i++){
					String acc = accountServiceProxy.getAccountByAccNumberAndAccPassword(user.getLinkedAccounts().get(i).getAccountNumber(), user.getLinkedAccounts().get(i).getAccountPassword());	
					AccountBean accBean = gson.fromJson(acc, AccountBean.class);
					accountInfo.add(accBean);
				}
			} else {
				throw new Exception("Invalid credentials");
			}
		}
		return accountInfo;
	}
	
	


}
