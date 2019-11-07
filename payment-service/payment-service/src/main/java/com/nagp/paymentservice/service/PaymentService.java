package com.nagp.paymentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagp.paymentservice.beans.Payment;
import com.nagp.paymentservice.beans.PaymentCriteria;

@Service
public interface PaymentService {
	
	public Payment deposit(PaymentCriteria paymentCriteria) throws Exception;
	public Payment withdraw(PaymentCriteria paymentCriteria) throws Exception;
	public Payment transfer(PaymentCriteria paymentCriteria) throws Exception;
	public Payment getPaymentById(Integer id) throws Exception;
	public List<Payment> getAllPaymentsLinkedWithAccount(PaymentCriteria paymentCriteria) throws Exception;
}
