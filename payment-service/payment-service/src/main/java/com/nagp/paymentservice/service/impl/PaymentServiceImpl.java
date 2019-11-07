package com.nagp.paymentservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nagp.paymentservice.beans.Payment;
import com.nagp.paymentservice.beans.PaymentCriteria;
import com.nagp.paymentservice.proxy.AccountBean;
import com.nagp.paymentservice.proxy.ServiceProxy;
import com.nagp.paymentservice.proxy.UserBean;
import com.nagp.paymentservice.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	List<Payment> allPayments = new ArrayList<Payment>();
	Gson gson = new Gson();

	@Autowired
	private ServiceProxy serviceProxy;
	
	
	@Override
	public Payment deposit(PaymentCriteria paymentCriteria) throws Exception {
		// TODO Auto-generated method stub
		
		String userStr = serviceProxy.getUserByEmailAndPassword(paymentCriteria.getEmailId(), paymentCriteria.getPassword());
		
		UserBean user = gson.fromJson(userStr, UserBean.class);
		if(user.getLinkedAccounts().size() == 0) {
			throw new Exception("No account is linked with this user");
		}
		for(int i=0;i<user.getLinkedAccounts().size();i++) {
			if(paymentCriteria.getAccNumber().equals(user.getLinkedAccounts().get(i).getAccountNumber())){
				serviceProxy.getAccountByAccNumberAndAccPassword(paymentCriteria.getAccNumber(), paymentCriteria.getAccPassword());				
					Payment payment = new Payment();
					payment.setBalance(paymentCriteria.getBalance());
					payment.setToAccNumber(paymentCriteria.getAccNumber());
					payment.setType("Deposit");
					if(allPayments.size() == 0)
						payment.setId(9001);
					else 
						payment.setId(allPayments.get(allPayments.size() - 1).getId() + 1);
					serviceProxy.addBalance(paymentCriteria.getAccNumber(), paymentCriteria.getAccPassword(), paymentCriteria.getBalance());
					
					allPayments.add(payment);	
					return payment;
				 
			}
		}
		throw new Exception("User is not linked with account");
	}
	
	@Override
	public Payment withdraw(PaymentCriteria paymentCriteria) throws Exception {
		String userStr = serviceProxy.getUserByEmailAndPassword(paymentCriteria.getEmailId(), paymentCriteria.getPassword());
		UserBean user = gson.fromJson(userStr, UserBean.class);
		if(user.getLinkedAccounts().size() == 0) {
			throw new Exception("No account is linked with this user");
		}
		for(int i=0;i<user.getLinkedAccounts().size();i++) {
			if(paymentCriteria.getAccNumber().equals(user.getLinkedAccounts().get(i).getAccountNumber())){
				serviceProxy.getAccountByAccNumberAndAccPassword(paymentCriteria.getAccNumber(), paymentCriteria.getAccPassword());			
					Payment payment = new Payment();
					payment.setBalance(paymentCriteria.getBalance());
					payment.setFromAccNumber(paymentCriteria.getAccNumber());
					payment.setType("Withdraw");
					if(allPayments.size() == 0)
						payment.setId(9001);
					else 
						payment.setId(allPayments.get(allPayments.size() - 1).getId() + 1);
				serviceProxy.subBalance(paymentCriteria.getAccNumber(), paymentCriteria.getAccPassword(), paymentCriteria.getBalance());
					allPayments.add(payment);	
					return payment;
				
			}
		}
		throw new Exception("User is not linked with account");

	}

	@Override
	public Payment transfer(PaymentCriteria paymentCriteria) throws Exception {
		String userStr = serviceProxy.getUserByEmailAndPassword(paymentCriteria.getEmailId(), paymentCriteria.getPassword());		
		UserBean user = gson.fromJson(userStr, UserBean.class);
		if(user.getLinkedAccounts().size() == 0) {
			throw new Exception("No account is linked with this user");
		}
		for(int i=0;i<user.getLinkedAccounts().size();i++) {
			if(paymentCriteria.getFromAccNumber().equals(user.getLinkedAccounts().get(i).getAccountNumber())){
				serviceProxy.getAccountByAccNumberAndAccPassword(paymentCriteria.getFromAccNumber(), paymentCriteria.getAccPassword());
					Payment payment = new Payment();
					payment.setBalance(paymentCriteria.getBalance());
					payment.setFromAccNumber(paymentCriteria.getFromAccNumber());
					payment.setToAccNumber(paymentCriteria.getToAccNumber());
					payment.setType("Transfer");
					if(allPayments.size() == 0)
						payment.setId(9001);
					else 
						payment.setId(allPayments.get(allPayments.size() - 1).getId() + 1);
					serviceProxy.transferBalance(paymentCriteria.getFromAccNumber(),
							paymentCriteria.getToAccNumber(),
							paymentCriteria.getAccPassword(), paymentCriteria.getBalance());
					allPayments.add(payment);	
					return payment;
				} 
			}
		throw new Exception("User is not linked with account");
	}

	@Override
	public Payment getPaymentById(Integer id) throws Exception {
		for(int i=0;i<allPayments.size(); i++) {
			if(id.equals(allPayments.get(i))){
				return allPayments.get(i);
			}
		}
		throw new Exception("Invalid transaction id");
	}

	@Override
	public List<Payment> getAllPaymentsLinkedWithAccount(PaymentCriteria paymentCriteria) throws Exception {
	
		String userStr = serviceProxy.getUserByEmailAndPassword(paymentCriteria.getEmailId(), paymentCriteria.getPassword());		
		UserBean user = gson.fromJson(userStr, UserBean.class);
		if(user.getLinkedAccounts().size() == 0) {
			throw new Exception("No account is linked with this user");
		}
		for(int i=0;i<user.getLinkedAccounts().size();i++) {
			if(paymentCriteria.getAccNumber().equals(user.getLinkedAccounts().get(i).getAccountNumber())){
				String accStr = serviceProxy.getAccountByAccNumberAndAccPassword(paymentCriteria.getAccNumber(), paymentCriteria.getAccPassword());
				AccountBean acc = gson.fromJson(accStr, AccountBean.class);
				
					List<Payment> linkedPayments = new ArrayList<Payment>();
					for(int j=0;j<allPayments.size(); j++) {
						Payment pay = allPayments.get(j);
						if(acc.getAccountNumber().equals(pay.getFromAccNumber()) || acc.getAccountNumber().equals(pay.getToAccNumber())) {
							linkedPayments.add(pay);
						}
					}
					return linkedPayments;
				
			}
		}
		throw new Exception("User is not linked with account");
	}
}
