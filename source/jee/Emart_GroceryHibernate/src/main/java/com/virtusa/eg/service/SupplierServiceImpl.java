package com.virtusa.eg.service;
import org.apache.log4j.Logger;

import java.sql.SQLException;

import java.util.Scanner;


import com.virtusa.eg.dao.OrderDAO;
import com.virtusa.eg.dao.SupplierDAO;
import com.virtusa.eg.dto.Product;
import com.virtusa.eg.dto.Supplier;


public class SupplierServiceImpl implements SupplierService {
	private static SupplierServiceImpl supplierServiceImpl=null;
	private SupplierServiceImpl() {
		
	}
	public static SupplierServiceImpl getInstance() {
		if(supplierServiceImpl==null) {
			supplierServiceImpl =  new SupplierServiceImpl();
		}
		return supplierServiceImpl;
	}
	Scanner sc = new Scanner(System.in);
	String supplierEmail; 
	static Logger log = Logger.getLogger(SupplierServiceImpl.class);
	Supplier supplier = new Supplier();
	
	OrderDAO orderDAO = OrderDAO.getInstance();
	
	public void addSupplier() {

		
		inputSupplier();
		SupplierDAO.getInstance().addSupplier(supplier);
		
	}
	
	public boolean supplierLogin() {

		log.info("Enter  E-Mail:");
		String email = sc.next();
		log.info("Enter  Password:");
		String password = sc.next();
		String status = SupplierDAO.getInstance().supplierLogin(email, password);
		if(status!=null) {
			supplierEmail=email;
			return true;
		}
		return false;
	}

	public void viewSupplier() {
		
		SupplierDAO.getInstance().viewSupplier(supplierEmail);
	}

	@Override
	public void updateSupplier() throws SQLException {

		inputSupplier();
		
		SupplierDAO.getInstance().updateSupplier(supplier);
	}

	@Override
	public void viewOrders() {
		orderDAO.listOfOrders().forEach(e->log.info(e));
		
	}

	@Override
	public void upadteOrderStatus() {
		log.info("Enter the order Id yoy want to update the status : ");
		int id = sc.nextInt();
		log.info("1.Approve\n2.Reject");
		int choice = sc.nextInt();
		if(choice==1) {
			Product p = orderDAO.updateOrderStatus(id,"Approved");
			Product p1 =ProductServiceImpl.getInstance().searchProduct(p.getProdId());
			if(p1!=null)
			{
				ProductServiceImpl.getInstance().reduceQunantity(p1,p.getProdQuantity());
			}
			
			
		}
		else if(choice==2) {
			orderDAO.updateOrderStatus(id,"Rejected");
		}
		
	}
	@Override
	public void deleteSupplier(int id) {
		SupplierDAO.getInstance().deleteSupplier(id);
		
	}
	private void inputSupplier() {
		log.info("Enter Your Id:");
		supplier.setSupplierId(sc.nextInt());
		log.info("Enter Your Name:");
		supplier.setSupplierName(sc.next());
		log.info("Enter the Mail:");
		supplier.seteMail(sc.next());
		log.info("Enter Your Phone:");
		supplier.setPhone(sc.next());
		log.info("Enter the Password:");
		supplier.setPassword(sc.next());
		 log.info("Enter Your Location:");
		supplier.setLocation(sc.next());
	}

}
