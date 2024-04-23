package com.osw.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.customer.model.Wishlist;
import com.osw.customer.repository.WishlistRepo;
import com.osw.product.model.Product;
@Service
public class WishListService implements IWishlistService {
	@Autowired
	private WishlistRepo wishlistRepo;
	@Override
	public void insertWishlist(Wishlist c) {
		// TODO Auto-generated method stub
		wishlistRepo.save(c);
	}

	@Override
	public void updateWishlist(Wishlist c) {
		// TODO Auto-generated method stub
		wishlistRepo.save(c);
	}

	@Override
	public void deleteWishlist(int wishlistId) {
		// TODO Auto-generated method stub
		wishlistRepo.deleteById(wishlistId);
	}

	@Override
	public List<Wishlist> printAllWishlists() {
		// TODO Auto-generated method stub
		return wishlistRepo.findAll();
	}

	
	@Override
	public Wishlist printWishlist(int wishlistId) {
		// TODO Auto-generated method stub
		return wishlistRepo.findById(wishlistId).get();
	}

	

}
