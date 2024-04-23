package com.osw.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.customer.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, String> {
	public Customer findByCustUsername(String username);
}
