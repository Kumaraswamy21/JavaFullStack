package com.osw.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.customer.model.Cart;
import com.osw.customer.model.CartLine;
import com.osw.customer.repository.CartLineRepo;
@Service
public class CartLineService implements ICartLineService{
	@Autowired
	private CartLineRepo cartLineRepo;

	@Override
	public void insertCart(CartLine c) {
		// TODO Auto-generated method stub
		cartLineRepo.save(c);
	}

	@Override
	public void updateCart(CartLine c) {
		// TODO Auto-generated method stub
		cartLineRepo.save(c);
	}

	@Override
	public void deleteCart(int cartlineId) {
		// TODO Auto-generated method stub
		cartLineRepo.deleteById(cartlineId);
	}

	@Override
	public List<CartLine> printAllCarts() {
		// TODO Auto-generated method stub
		return cartLineRepo.findAll();
	}

	@Override
	public CartLine printCartLine(int cartlineId) {
		// TODO Auto-generated method stub
		return cartLineRepo.findById(cartlineId).get();
	}

	@Override
	public List<CartLine> allCartLinesOfCart(int CartId) {
		// TODO Auto-generated method stub
		List<CartLine> allList=cartLineRepo.findAll();
		List<CartLine> cartLines=new ArrayList<CartLine>();
		for(CartLine cl:allList) {
			if(cl.getCart()!=null) {
			if(cl.getCart().getCartId()==CartId) {
				cartLines.add(cl);
			}
			}
		}
		return cartLines;
	}

	@Override
	public Cart increaseCartQuant(List<CartLine> cartlinelist) {
		// TODO Auto-generated method stub
		double grandTotal=0;
      	int totalItems=0;
      	for(CartLine cl:cartlinelist) {
      		totalItems=totalItems+cl.getProductCount();
      		grandTotal=grandTotal+cl.getTotal();
      	}
      	Cart cart=cartlinelist.get(0).getCart();
      	cart.setGrandTotal(grandTotal);
      	cart.setNoOfItems(totalItems);
		return cart;
	}

	@Override
	public Cart decreaseCartQuant(List<CartLine> cartlinelist) {
		// TODO Auto-generated method stub
		double grandTotal=0;
      	int totalItems=0;
      	for(CartLine cl:cartlinelist) {
      		totalItems=totalItems-cl.getProductCount();
      		grandTotal=grandTotal-cl.getTotal();
      	}
      	Cart cart=cartlinelist.get(0).getCart();
      	cart.setGrandTotal(grandTotal);
      	cart.setNoOfItems(totalItems);
		return cart;
	}
	
}
