package com.osw.customer.service;

import java.util.List;

import com.osw.customer.model.Cart;
import com.osw.customer.model.CartLine;

public interface ICartLineService {
	public void insertCart(CartLine c);
	public void updateCart(CartLine c);
	public void deleteCart(int cartlineId);
	public List<CartLine> printAllCarts();
	public CartLine printCartLine(int cartlineId);
	public List<CartLine> allCartLinesOfCart(int CartId);
	public Cart increaseCartQuant(List<CartLine> cartlinelist);
	public Cart decreaseCartQuant(List<CartLine> cartlinelist);
}
