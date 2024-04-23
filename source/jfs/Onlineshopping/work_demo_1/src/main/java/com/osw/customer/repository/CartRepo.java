package com.osw.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.customer.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

}
