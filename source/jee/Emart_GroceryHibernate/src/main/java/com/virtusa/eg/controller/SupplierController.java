package com.virtusa.eg.controller;


import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

import com.virtusa.eg.exceptions.InvalidCredentialsException;
import com.virtusa.eg.service.SupplierServiceImpl;

public class SupplierController {
	Scanner sc = new Scanner(System.in);
	ProductController productcontroller = new ProductController();
	 Logger log = Logger.getLogger(SupplierController.class);
	SupplierServiceImpl supplierservice = SupplierServiceImpl.getInstance();
	public void addSupplier() {
		supplierservice.addSupplier();
	}
	public void supplierLogin() throws InvalidCredentialsException {
		if(supplierservice.supplierLogin()) supplierFunctions();
		else {
			log.info("Login failed try again");
			supplierLogin();
		}
	}
	public void viewSupplier() {
		supplierservice.viewSupplier();
	}
	public void supplier() {
		
		log.info("Enter Your Choice \n 1.Register \n 2.Login");
		switch(sc.nextInt()) {
		case 1: addSupplier();
		break;
		case 2: if(supplierservice.supplierLogin()) {
			 supplierFunctions();
		}
		
			break;
			default:
				break;
			
		}
	}
	
	public void supplierFunctions()   {
		boolean flag = true;
		while(flag) {
		log.info("------WelCome ------\n 1.View Details \n 2.Update Details \n 3.Add Products \n 4.View Products \n 5.Delete Products \n 6.Update Products \n 7.View Orders 8.Update status of Order 9.exit");
		switch(sc.nextInt()) {
		case 1: viewSupplier();
		break;
		case 2:try {
				updateSuppier();
			} catch (Exception e) {
				log.info(e);
				
			}
			break;
		case 3:productcontroller.addProduct();
		break;
		case 4:productcontroller.getProducts();
			break;
		case 5: productcontroller.deleteProduct();
			break;
		case 6: productcontroller.updateProduct();
			break;
		case 7: supplierservice.viewOrders();
		break;
		case 8:supplierservice.upadteOrderStatus();
		break;
		case 9: flag = false;
		break;
		default:
			break;
		}
		}
		
	}
	private void updateSuppier() throws SQLException  {
		supplierservice.updateSupplier();
		
	}
}
