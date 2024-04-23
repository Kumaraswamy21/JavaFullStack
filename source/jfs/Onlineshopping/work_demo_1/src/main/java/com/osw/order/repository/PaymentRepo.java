package com.osw.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.order.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
