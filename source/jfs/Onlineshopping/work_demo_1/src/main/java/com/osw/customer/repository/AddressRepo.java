package com.osw.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.customer.model.Address;

public interface AddressRepo extends JpaRepository<Address, String>{

}
