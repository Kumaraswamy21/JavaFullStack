package com.virtusa.eg.service;

import java.util.List;

import com.virtusa.eg.dto.Admin;
import com.virtusa.eg.dto.Customer;
import com.virtusa.eg.dto.Supplier;
import com.virtusa.eg.exceptions.InvalidCredentialsException;

public interface AdminService {

	public void searchCustomerById(String custId);
	
	public void validateAdmin(String email, String password) throws InvalidCredentialsException;
	public void viewAdminProfile(String email);
	public void updateAdmin(Admin admin);
	public List<Supplier> getAllSuppliers();
	public List<Customer> getAllCustomers();
	public void searchSupplierById(int id);
	
}
