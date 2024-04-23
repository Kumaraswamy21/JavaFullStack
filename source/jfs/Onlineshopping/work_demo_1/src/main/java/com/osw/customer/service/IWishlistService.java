package com.osw.customer.service;

import java.util.List;

import com.osw.customer.model.Wishlist;
import com.osw.product.model.Product;

public interface IWishlistService {
	public void insertWishlist(Wishlist c);
	public void updateWishlist(Wishlist c);
	public void deleteWishlist(int wishlistId);
	public List<Wishlist> printAllWishlists();
	public Wishlist printWishlist(int wishlistId);
	
}
