package com.nagp.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nagp.paymentservice.beans.PaymentCriteria;
import com.nagp.paymentservice.service.PaymentService;

@RestController
public class PaymentController {

	Gson gson = new Gson();
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/payment/deposit")
	public String deposit(PaymentCriteria paymentCriteria) throws Exception {
		
		return gson.toJson(paymentService.deposit(paymentCriteria));
	}
	
	@PostMapping("/payment/withdraw")
	public String withdraw(PaymentCriteria paymentCriteria) throws Exception {
		return gson.toJson(paymentService.withdraw(paymentCriteria));
	}
	
	@PostMapping("/payment/transfer")
	public String transfer(PaymentCriteria paymentCriteria) throws Exception {
		return gson.toJson(paymentService.transfer(paymentCriteria));
	}
	
	@GetMapping("/payment/view/{id}")
	public String viewTransactionById(@PathVariable Integer id) throws Exception {
		return gson.toJson(paymentService.getPaymentById(id));
	}
	@GetMapping("/payment/account/view")
	public String getAllPaymentsLinkedWithAccount(PaymentCriteria paymentCriteria) throws Exception {
		return gson.toJson(paymentService.getAllPaymentsLinkedWithAccount(paymentCriteria));
	}
	
}
