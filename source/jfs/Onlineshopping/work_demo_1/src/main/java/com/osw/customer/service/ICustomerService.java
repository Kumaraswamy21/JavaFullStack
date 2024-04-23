package com.osw.customer.service;

import java.util.List;

import com.osw.customer.model.Address;
import com.osw.customer.model.Customer;

public interface ICustomerService {
	public void insertCustomer(Customer c);
	public void updateCustomer(Customer c);
	public Customer deleteCustomer(String customerId);
	public Customer printCustomer(String customerId);
	public List<Customer> allCustomer();
	public Customer validUser(String Username,String password);
	public Customer getByUsername(String Username);
	public List<Address> addressListInCustomer(String customerId);
}
