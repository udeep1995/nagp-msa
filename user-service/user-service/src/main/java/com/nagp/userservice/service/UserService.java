package com.nagp.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagp.userservice.beans.AccountBean;
import com.nagp.userservice.beans.User;
import com.nagp.userservice.criteria.UserCriteria;

@Service
public interface UserService {

	public Integer registerNewUser(UserCriteria userCriteria) throws Exception;
	
	public User getUserByEmailIdAndPassword(String emailId, String password) throws Exception;
	
	public Integer updateUser(UserCriteria userCriteria) throws Exception;
	
	public User linkAccount(Integer accountNumber,String accPassword, String emailId, String password) throws Exception;

	public List<AccountBean> viewLinkedAccounts(String emailId, String password) throws Exception;
}
