package com.osw.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.customer.model.CartLine;

public interface CartLineRepo extends JpaRepository<CartLine, Integer>{
	
}
