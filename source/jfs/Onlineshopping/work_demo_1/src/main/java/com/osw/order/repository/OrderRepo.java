package com.osw.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.order.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
