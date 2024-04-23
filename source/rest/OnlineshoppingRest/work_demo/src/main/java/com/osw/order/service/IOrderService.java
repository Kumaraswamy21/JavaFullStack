package com.osw.order.service;

import java.util.List;

import com.osw.order.model.Order;

public interface IOrderService {
	public void insertOrder(Order o);
	public void updateOrder(Order o);
	public void deleteOrder(int orderId);
	public Order printOrder(int orderId);
	public List<Order> printAllOrders();
}
