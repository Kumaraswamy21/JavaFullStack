package com.osw.customer.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.osw.product.model.Product;
@Entity
public class Wishlist {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int wishlistId;
	 private String wishlistProducts;
	
	public int getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
	}
	public String getWishlistProducts() {
		return wishlistProducts;
	}
	public void setWishlistProducts(String wishlistProducts) {
		this.wishlistProducts = wishlistProducts;
	}
	 
}
