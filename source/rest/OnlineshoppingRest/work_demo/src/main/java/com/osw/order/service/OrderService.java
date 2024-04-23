package com.osw.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.order.model.Order;
import com.osw.order.repository.OrderRepo;
@Service
public class OrderService implements IOrderService{
	@Autowired
	private OrderRepo orderRepo;
	@Override
	public void insertOrder(Order o) {
		// TODO Auto-generated method stub
		orderRepo.save(o);
	}

	@Override
	public void updateOrder(Order o) {
		// TODO Auto-generated method stub
		orderRepo.save(o);
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		orderRepo.deleteById(orderId);
	}

	@Override
	public Order printOrder(int orderId) {
		return orderRepo.findById(orderId).get();
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> printAllOrders() {
		// TODO Auto-generated method stub
		return orderRepo.findAll();
	}

}
