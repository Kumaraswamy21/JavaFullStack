package com.virtusa.eg.controller;

import java.util.Scanner;


import org.apache.log4j.Logger;

import com.virtusa.eg.dao.AdminDAO;
import com.virtusa.eg.dto.Admin;
import com.virtusa.eg.service.AdminServiceImpl;
import com.virtusa.eg.service.CustomerServiceImpl;
import com.virtusa.eg.service.ProductServiceImpl;
import com.virtusa.eg.service.SupplierServiceImpl;

public class AdminController {

	 Logger log = Logger.getLogger(AdminController.class);
	 Scanner sc = new Scanner(System.in);
	 Admin admin = new Admin();
	 String adminEmail;
	public void adminFunctionalities()
	{
		log.info("press 1 To View Profile");
		log.info("press 2 To Update Profile");
		log.info("press 3 To Perform Curd Operations For Supplier");
		log.info("press 4 To Perform Curd Operations For Customers");
		log.info("press 5 To View Search Product By ID");
		log.info("Press 6 To Logout");
		
		int choice = sc.nextInt();
		
		if(choice==1)
		{
			viewProfile();
			adminFunctionalities();
		}
		else if(choice==2)
		{
			updateProfile();
			adminFunctionalities();
		}
		else if(choice==3)
		{
			supplierOperations();
			adminFunctionalities();
		}
		else if(choice==4)
		{
			customerOperations();
			adminFunctionalities();
		}
	
		else if(choice==5)
		{
			searchProductById();
			adminFunctionalities();
		}
		
		else 
		{
			log.info("Invalid ...please try again");
		}
	}



	private void searchProductById() {
		log.info("Enter the product Id you want to search :");
		int id = sc.nextInt();
		ProductServiceImpl.getInstance().searchProduct(id);
		
	}

	
	private void customerOperations() {
		
		log.info("press 1 To Show All Customers");
		log.info("press 2 To Remove Customer");
		log.info("press 3 To Search Customer By Id");
		int subChoice = sc.nextInt();
		if(subChoice == 1)
		{
			showAllCustomers();
			adminFunctionalities();
		}
	    else if(subChoice == 2)
		{
			removeCustomer();
			adminFunctionalities();
		}
	    else if(subChoice == 3)
	    {
	    	searchCustomerById();
	    	adminFunctionalities();
	    }
	    else
	    {
	    	log.info("Invalid Input");
	    }
	}


	private void searchCustomerById() {
		log.info("Enter the customer Id you want to search :");
		String customerId = sc.next();
		AdminServiceImpl.getInstance().searchCustomerById(customerId);
		
	}

	private void removeCustomer() {
		CustomerServiceImpl.getInstance().deleteCustomer();
		
	}

	private void showAllCustomers() {
		AdminServiceImpl.getInstance().getAllCustomers();

		
	}

	private void supplierOperations() {
		

		log.info("press 1 To Show All Suppliers");
		log.info("press 2 To Remove Supplier");
		log.info("press 3 To Search Supplier By Id");
		int subChoice = sc.nextInt();
		if(subChoice == 1)
		{
			showAllSuppliers();
			adminFunctionalities();
		}
	    else if(subChoice == 2)
		{
			removeSupplier();
			adminFunctionalities();
		}
	    else if(subChoice == 3)
	    {
	    	searchSupplierById();
	    	adminFunctionalities();
	    }
	    else
	    {
	    	log.info("Invalid Input");
	    }
		
		
	}

	private void searchSupplierById() {
		log.info("Enter the Supplier Id you want to search :");
		int supplierId = sc.nextInt();
		AdminDAO.getInstance().searchSupplierById(supplierId);
		
	}



	private void removeSupplier() {
		log.info("Enter supplier Id you want to delete :");
		int supplierId = sc.nextInt();
		SupplierServiceImpl.getInstance().deleteSupplier(supplierId);
		
	}



	private void showAllSuppliers() {
		AdminDAO.getInstance().getAllSuppliers()
								.forEach(e->log.info(e));
		
	}



	private void updateProfile() {
		log.info("Enter the details need to be updated :");
		log.info("Enter admin Name:");
		admin.setAdminName(sc.next());
		log.info("Enter admin Email :");
		admin.setAdminEmail(sc.next());
		log.info("Enter the password :");
		admin.setPassword(sc.next());
		log.info("Enter Phone Number");
		admin.setPhoneNumber(sc.next());
		AdminDAO.getInstance().updateAdmin(admin);
		
		
	}

	private void viewProfile() {
	
		AdminDAO.getInstance().viewAdminProfile(adminEmail);
	}



	public boolean validateAdmin() {
		log.info("Enter Your Email");
		String email=sc.next();
		
		log.info("Enter Your Password");
		String password=sc.next();
		
		try {
			Admin status = AdminDAO.getInstance().adminLogin(email, password);
			if (status!=null) {
				adminEmail = email;
				adminFunctionalities();
				return true;
			}
		} catch (Exception e) {

			log.info(e);
		}
		return false;
		
	}

}
