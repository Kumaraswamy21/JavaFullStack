package com.virtusa.eg.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import java.util.Scanner;

import com.virtusa.eg.dao.CustomerDAO;
import com.virtusa.eg.dao.OrderDAO;
import com.virtusa.eg.dto.Customer;
import com.virtusa.eg.dto.Order;
import com.virtusa.eg.dto.Product;
import com.virtusa.eg.exceptions.InvalidCredentialsException;
import com.virtusa.eg.exceptions.InvalidQuantityException;
import com.virtusa.eg.exceptions.ProductNotFoundException;

public class CustomerServiceImpl implements CustomerService {
	private static CustomerServiceImpl customerServiceImpl = null;
	
	private CustomerServiceImpl() {
		
	}
	public static CustomerServiceImpl getInstance() {
		if(customerServiceImpl == null) {
			customerServiceImpl = new CustomerServiceImpl();
		}
		return customerServiceImpl;
	}
	
	
	Customer customer = new Customer();
	ProductServiceImpl prdImpl = ProductServiceImpl.getInstance();
	static Logger log = Logger.getLogger(CustomerServiceImpl.class);
	 String customerEmail;
	Scanner sc = new Scanner(System.in);
	public void registerCustomer() throws SQLException {
		log.info("Enter Details to register :");
		inputDetails();
		CustomerDAO.getInstance().registerCustomer(customer);
		
		
		
		
		
	}
	
	private void inputDetails() {
		log.info("Enter Your Name :");
		customer.setCustName(sc.next());
		log.info("Enter Your E-Mail :");
		customer.seteMail(sc.next());
		log.info("Enter Your Phone :");
		customer.setCustPhone(sc.next());
		log.info("Enter Your Location :");
		customer.setCustLocation(sc.next());
		log.info("Enter Your Password :");
		customer.setPassword(sc.next());
		
	}
	
	public boolean customerLogin() throws InvalidCredentialsException {
			log.info("Enter Your E-Mail:");
			String email = sc.next();
			log.info("Enter Your Password:");
			String password = sc.next();
			String status = CustomerDAO.getInstance().customerLogin(email, password);
			if(status!=null) {
				customerEmail=email;
				return true;
			}
			return false;
		}
	@Override
	public void updateCustomer() throws SQLException {
		log.info("Enter Details to Update :");
		inputDetails();
		CustomerDAO.getInstance().updateCustomer(customer);
		
	}
	@Override
	public void viewCustomer() {
		CustomerDAO.getInstance().viewCustomer(customerEmail);
		
	}
	@Override
	public void deleteCustomer() {
		log.info("Enter the customer Id of the customer to be deleted:");
		int custId = sc.nextInt();
		CustomerDAO.getInstance().deleteCustomer(custId);
		
	}
	@Override
	public void placeOrder() {
		Order order = new Order();
		log.info("Enetr Your customer Id :");
		order.setCustId(sc.next());
		log.info("Enter the product Id you want to order : ");
		int prodId = sc.nextInt();
		
		Product product = prdImpl.searchProduct(prodId);
		try
		{
			
			if(product!=null)
			{
			order.setProdId(prodId);
			log.info("Enter the produt name you want to order : ");
			order.setProdName(sc.next());
			log.info("Enter the product quantity");
			int quantity = sc.nextInt();
			if(quantity>product.getProdQuantity())
			{
				throw new InvalidQuantityException("Invalid Product Quantity");
			}
			order.setQuantity(quantity);
			order.setProdPrice(product.getProdPrice());
			order.setTotalPrice(quantity*product.getProdPrice());
			order.setOrderStatus("Pending");
			
			
			}
			else
			{
				throw new ProductNotFoundException("product not found");
				
			}
		}
		catch(Exception e)
		{
			log.info(e);
		}
		OrderDAO.getInstance().placeOrder(order);
		
		
	}
	@Override
	public void getInvoice() {
		OrderDAO.getInstance().getApprovedOrders()
								.stream()
								.filter(e->e.getOrderStatus().equalsIgnoreCase("Approved"))
								.forEach(e->log.info(e));
		log.info("Please enter orderId which you want to get Invoice:");
		int orderId = sc.nextInt();
		Order order = OrderDAO.getInstance().searchOrder(orderId);
		if(order!=null) {
			log.info("Invoice:\n"+order);
		}else {
			log.info("Invalid orderId, try again!");
			getInvoice();
		}
	}
	

}
