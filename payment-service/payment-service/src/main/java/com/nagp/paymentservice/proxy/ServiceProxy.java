package com.nagp.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("zuul-server")
public interface ServiceProxy {
	@GetMapping("/user-service/user/{emailId}/{password}")
	public String getUserByEmailAndPassword(@PathVariable("emailId") String emailId, @PathVariable("password") String password) throws Exception;
	
	@GetMapping("/account-service/account/{accNumber}/{accPassword}")
	public String getAccountByAccNumberAndAccPassword(@PathVariable("accNumber") Integer accNumber, @PathVariable("accPassword")String accPassword) throws Exception;

	@PostMapping("/account-service/account/addbalance")
	public String addBalance(@RequestParam("accNumber") Integer accNumber, @RequestParam("accPassword") String accPassword,@RequestParam("balanceToAdd") Double balanceToAdd) throws Exception;
	
	@PostMapping("/account-service/account/subbalance")
	public String subBalance(@RequestParam("accNumber") Integer accNumber, @RequestParam("accPassword") String accPassword, @RequestParam("balanceToSub") Double balanceToSub) throws Exception;
	
	@PostMapping("account-service/account/transferbalance")
	public String transferBalance(@RequestParam("fromAccNumber") Integer fromAccNumber, @RequestParam("toAccNumber") Integer toAccNumber,@RequestParam("fromAccPassword")  String fromAccPassword,@RequestParam("balanceToTransfer")  Double balanceToTransfer) throws Exception;
}
