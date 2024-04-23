package com.virtusa.eg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.virtusa.eg.dto.Admin;
import com.virtusa.eg.dto.Customer;
import com.virtusa.eg.dto.Supplier;
import com.virtusa.eg.exceptions.InvalidCredentialsException;

public class AdminDAO {
	Logger log = Logger.getLogger(AdminDAO.class);
	private static   final  String EMAIL = "eMail";
private static AdminDAO adminDAO = null;
	
	private AdminDAO() {
		
	}
	public static AdminDAO getInstance() {
		if(adminDAO == null) {
			adminDAO = new AdminDAO();
		}
		return adminDAO;
	}
	
	public String adminLogin(String email, String password) throws InvalidCredentialsException {
		String adminEmail=null;
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "select adminEmail,password from admin where adminEmail=? and password=?";
		try (PreparedStatement stmt = connection.prepareStatement(query);){
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				adminEmail=email;
			log.info("Login Successfull");
				
				
				
			}
			else {
				throw new InvalidCredentialsException("Invalid Credentials");
			}
		} catch (SQLException e) {

			log.info("There is a issue with DB!");
			log.info(e);
		}
		
		return adminEmail;
		
	}
	
	public void viewAdminProfile(String email) {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "select * from admin where eMail = ?";
		try(PreparedStatement stmt = connection.prepareStatement(query);) {
			
			stmt.setString(1,email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int adminId = rs.getInt("adminId");
				String adminName = rs.getString("adminName");
				String adminEmail = rs.getString("adminEmail");
				String phoneNumer = rs.getString("phoneNumber");

				log.info ("Admin Id :"+adminId+ "\nAdmin Name:"+adminName+ "\n E-mail:"+adminEmail+
						"\nPhone Number :" +phoneNumer);
			}
		} catch (SQLException e) {

			log.info(e);
		}
	}
	
	public void updateAdmin(Admin admin) {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "update admin set adminName=?,adminEmail=?,phoneNumber=?,password=? where adminId=?";
		 
		try(PreparedStatement stmt = connection.prepareStatement(query);) {
			
		
		
		stmt.setString(1, admin.getAdminName());
		stmt.setString(2, admin.getAdminEmail());
		stmt.setString(3, admin.getPhoneNumber());
		stmt.setString(4, admin.getPassword());
		stmt.setInt(5, admin.getAdminId());
		if(stmt.executeUpdate()>0) {
			 log.info ("Admin details updated");
			
		}
		else {
			log.info ("Something went wrong try again!");
			
		}
		} catch (SQLException e) {
			log.info(e);
		}
		
	}
	
	
	public List<Supplier> getAllSuppliers(){
		EGDatabaseConnection.getInstance();
		Connection connection=EGDatabaseConnection.getConnection();
		String query = "select * from supplier";
		List<Supplier> supplierList = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getInt("supplierId"));
				supplier.setSupplierName(rs.getString("supplierName")); 
				supplier.seteMail(rs.getString(EMAIL));
				supplier.setPhone(rs.getString("phone"));
				supplier.setLocation(rs.getString("location"));
				supplierList.add(supplier);
				
			}
		} catch (SQLException e) {

			log.info(e);
		}
		return supplierList;
		
	}
	
	
	public List<Customer> getAllCustomers(){
		EGDatabaseConnection.getInstance();
		Connection connection=EGDatabaseConnection.getConnection();
		String query = "select * from suppliers";
		List<Customer> customerList = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Customer customer = new Customer();
				customer.setCustId( rs.getString("custId"));
				customer.setCustName(rs.getString("custName")); 
				customer.seteMail(rs.getString(EMAIL)); 
				customer.setCustPhone(rs.getString("custPhone"));
				customer.setCustLocation(rs.getString("custLocation"));
				customerList.add(customer);
				
			}
		} catch (SQLException e) {

			log.info(e);
		}
		return customerList;
		
	}
	
	public void searchCustomerById(String id) {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "select * from customers where custId = ?";
		try(PreparedStatement stmt = connection.prepareStatement(query);) {
			
			stmt.setString(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				String custId = rs.getString("custId");
				String custName = rs.getString("custName");
				String eMail = rs.getString(EMAIL);
				String custPhone = rs.getString("custPhone");
				String custLocation = rs.getString("custLocation");
				log.info ("Customer Id :"+custId+ "\nCustomer Name:"+custName+ "\nCustomer E-mail:"+eMail+
						"\n Customer phone :" +custPhone+ "\n Customer Location :"+custLocation);
			}
		} catch (SQLException e) {

			log.info(e);
		}
	}
 
	public void searchSupplierById(int id) {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "select * from supplier where supplierId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int supplierId = rs.getInt("supplierId");
				String supplierName = rs.getString("supplierName");
				String eMail = rs.getString(EMAIL);
				String phone = rs.getString("phone");
				String location = rs.getString("location");
				log.info ("supplier Id :"+supplierId+ "\nSupplier Name:"+supplierName+ "\nSupplier E-mail:"+eMail+
						"\n Supplier phone :" +phone+ "\n Supplier Location :"+location);
			}
		} catch (SQLException e) {

			log.info(e);
		}
	}
	
}
