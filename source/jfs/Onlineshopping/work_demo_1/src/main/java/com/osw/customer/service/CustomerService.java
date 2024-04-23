package com.osw.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.customer.model.Address;
import com.osw.customer.model.Customer;
import com.osw.customer.repository.CustomerRepo;
@Service
public class CustomerService implements ICustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	@Override
	public void insertCustomer(Customer c) {
		// TODO Auto-generated method stub
		customerRepo.save(c);
	}

	@Override
	public void updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		customerRepo.save(c);
	}

	@Override
	public Customer deleteCustomer(String customerId) {
		// TODO Auto-generated method stub
		Customer c=customerRepo.findById(customerId).get();
		customerRepo.delete(c);
		return c;
	}

	@Override
	public Customer printCustomer(String customerId) {
		// TODO Auto-generated method stub
		return customerRepo.findById(customerId).get();
	}

	@Override
	public List<Customer> allCustomer() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public Customer validUser(String Username, String password) {
		// TODO Auto-generated method stub
		Customer c=customerRepo.findByCustUsername(Username);
		if(c!=null && c.getCustPassword().equals(password)) {
			return c;
		}
		return null;
	}

	@Override
	public Customer getByUsername(String Username) {
		// TODO Auto-generated method stub
		return customerRepo.findByCustUsername(Username);
	}

	@Override
	public List<Address> addressListInCustomer(String customerId) {
		// TODO Auto-generated method stub
		Customer c=customerRepo.findById(customerId).get();
		return c.getAddress();
	}

}
