package com.osw.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.order.model.Payment;
import com.osw.order.repository.PaymentRepo;
@Service
public class PaymentService implements IPaymentService{
	@Autowired
	private PaymentRepo paymentRepo;
	@Override
	public void insertPayment(Payment p) {
		// TODO Auto-generated method stub
		paymentRepo.save(p);
	}

	@Override
	public void updatePayment(Payment p) {
		// TODO Auto-generated method stub
		paymentRepo.save(p);
	}

	@Override
	public void deletePayment(int paymentId) {
		// TODO Auto-generated method stub
		paymentRepo.deleteById(paymentId);
	}

	@Override
	public Payment printPayment(int paymentId) {
		// TODO Auto-generated method stub
		return paymentRepo.findById(paymentId).get();
	}

	@Override
	public List<Payment> printAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepo.findAll();
	}

}
