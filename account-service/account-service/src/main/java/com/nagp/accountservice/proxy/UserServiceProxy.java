package com.nagp.accountservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RibbonClient("user-service")
@FeignClient("zuul-server")
public interface UserServiceProxy {
	
	@GetMapping("/user-service/user/{emailId}/{password}")
	public String getUserByEmailIdAndPassword(@PathVariable("emailId") String emailId, @PathVariable("password")String password) throws Exception;
	
	@PostMapping("user-service/user/account/link")
	public String linkAccount(@RequestParam("accNumber") Integer accNumber,
			@RequestParam("accPassword") String accPassword, @RequestParam("emailId") String emailId,
			@RequestParam("password") String password) throws Exception;

}
