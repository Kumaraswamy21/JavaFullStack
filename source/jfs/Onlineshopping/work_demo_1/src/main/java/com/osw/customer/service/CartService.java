package com.osw.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.customer.model.Cart;
import com.osw.customer.repository.CartRepo;
@Service
public class CartService implements ICartService{
	@Autowired
	private CartRepo cartrepo;
	@Override
	public void insertCart(Cart c) {
		// TODO Auto-generated method stub
		cartrepo.save(c);
		
	}

	@Override
	public void updateCart(Cart c) {
		// TODO Auto-generated method stub
		cartrepo.save(c);
	}

	@Override
	public void deleteCart(int cartId) {
		// TODO Auto-generated method stub
		cartrepo.deleteById(cartId);
	}

	@Override
	public List<Cart> printAllCarts() {
		// TODO Auto-generated method stub
		
		return cartrepo.findAll();
	}

	@Override
	public Cart printCart(int cartId) {
		// TODO Auto-generated method stub
		return cartrepo.findById(cartId).get();
	}

}
