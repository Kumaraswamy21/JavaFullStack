package com.virtusa.eg.controller;



import java.util.Scanner;

import org.apache.log4j.Logger;

import com.virtusa.eg.exceptions.InvalidCredentialsException;

public class MainController {
Scanner sc = new Scanner(System.in);
SupplierController suppliercontroller = new SupplierController();
ProductController productcontroller = new ProductController();
CustomerController customerController = new CustomerController();
AdminController adminController  = new AdminController();
static Logger log = Logger.getLogger(MainController.class);
	public void menu() throws InvalidCredentialsException{
		boolean flag = true;
		while(flag) {
			log.info("Enter Your Choice \n 1.Admin \n 2.Supplier \n 3.Customer \n 4.exit");
			switch(sc.nextInt()) {
			case 1: adminController.validateAdmin();
			break;
			case 2: suppliercontroller.supplier();
			break;
			case 3: customerController.customer();
			break;
			case 4: flag = false;
			break;
			default:
				break;
			}
		}
	}
	
	
	

}
