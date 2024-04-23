package com.osw.customer.service;

import java.util.List;

import com.osw.customer.model.Cart;

public interface ICartService {
	public void insertCart(Cart c);
	public void updateCart(Cart c);
	public void deleteCart(int cartId);
	public List<Cart> printAllCarts();
	public Cart printCart(int cartId);
}
