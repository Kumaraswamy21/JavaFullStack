package com.osw.order.service;

import java.util.List;
import com.osw.order.model.Payment;

public interface IPaymentService {
	public void insertPayment(Payment p);
	public void updatePayment(Payment p);
	public void deletePayment(int paymentId);
	public Payment printPayment(int paymentId);
	public List<Payment> printAllPayments();
}
