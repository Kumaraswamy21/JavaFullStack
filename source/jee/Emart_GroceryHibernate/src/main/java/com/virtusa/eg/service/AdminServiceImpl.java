package com.virtusa.eg.service;

import java.util.List;

import com.virtusa.eg.dao.AdminDAO;
import com.virtusa.eg.dto.Admin;
import com.virtusa.eg.dto.Customer;
import com.virtusa.eg.dto.Supplier;
import com.virtusa.eg.exceptions.InvalidCredentialsException;

public class AdminServiceImpl implements AdminService {

private static AdminServiceImpl adminServiceImpl = null;
	
	private AdminServiceImpl() {
		
	}
	public static AdminServiceImpl getInstance() {
		if(adminServiceImpl == null) {
			adminServiceImpl = new AdminServiceImpl();
		}
		return adminServiceImpl;
	}
	@Override
	public void searchCustomerById(String custId) {
		AdminDAO.getInstance().searchCustomerById(custId);
		
		
	}
	@Override
	public void validateAdmin(String email, String password) throws InvalidCredentialsException {
		AdminDAO.getInstance().adminLogin(email, password);
	}
	@Override
	public void viewAdminProfile(String email) {
		AdminDAO.getInstance().viewAdminProfile(email);
		
	}
	@Override
	public void updateAdmin(Admin admin) {
		AdminDAO.getInstance().updateAdmin(admin);
		
	}
	@Override
	public List<Supplier> getAllSuppliers() {
		 return AdminDAO.getInstance().getAllSuppliers();
		 
	}
	@Override
	public List<Customer> getAllCustomers() {
		return AdminDAO.getInstance().getAllCustomers();
	}
	@Override
	public void searchSupplierById(int id) {
		AdminDAO.getInstance().searchSupplierById(id);
		
	}
	
	

}
