package com.virtusa.eg.controller;

import java.sql.SQLException;

import java.util.Scanner;

import com.virtusa.eg.exceptions.InvalidCredentialsException;
import com.virtusa.eg.service.CustomerServiceImpl;
import org.apache.log4j.Logger;


public class CustomerController {
	CustomerServiceImpl customerService = CustomerServiceImpl.getInstance();
	ProductController productcontroller = new ProductController();
	 Logger log = Logger.getLogger(CustomerController.class);
	Scanner sc = new Scanner(System.in);
	public void registerCustomer() throws SQLException {
		customerService.registerCustomer();
	}

	public void customer() throws InvalidCredentialsException {
		log.info("Enter Your Choice \n 1.Register \n 2.Login");
		switch(sc.nextInt()) {
		case 1: try {
				registerCustomer();
			} catch (Exception e) {

				log.info(e);
			}
		break;
		case 2: if(customerService.customerLogin()) {
			 try {
				customerFunctions();
			} catch (Exception e) {
				log.info(e);
			}
		}
		break;
	   default:
		break;
	}
	}

	private void customerFunctions() throws SQLException {
		boolean flag = true;
		while(flag) {
		log.info("------WelCome ------\n 1.Profile \n 2.Update Profile \n 3.View Products \n 4.Place Order \n 5.Get Invoice\n6.Delete Customer 7.exit");
		switch(sc.nextInt()) {
		case 1: customerService.viewCustomer();
		break;
		case 2:customerService.updateCustomer(); break;
		case 3:productcontroller.getProducts();
		break;
		case 4:customerService.placeOrder();
			break;
		case 5: customerService.getInvoice();
		break;
		case 6: customerService.deleteCustomer();
			break;
		
		case 7: flag = false;
		break;
		default:
			break;
		}
		}
		
		
	}

	

}
