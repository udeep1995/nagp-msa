package com.nagp.userservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@RibbonClient("account-service")
@FeignClient("zuul-server")
public interface AccountServiceProxy {

	@PostMapping("/account-service/account/active")
	public Boolean getActiveAccountByAccNumber(@RequestParam("accNumber") Integer accNumber,
			@RequestParam("accPassword")String accPassword) throws Exception;

	
	@GetMapping("/account-service/account/{accNumber}/{accPassword}")
	public String getAccountByAccNumberAndAccPassword(@PathVariable("accNumber") Integer accNumber, @PathVariable("accPassword") String accPassword) throws Exception;
}
