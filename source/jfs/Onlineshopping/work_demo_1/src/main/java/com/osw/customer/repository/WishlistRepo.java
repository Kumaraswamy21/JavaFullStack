package com.osw.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.customer.model.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer>{

}
