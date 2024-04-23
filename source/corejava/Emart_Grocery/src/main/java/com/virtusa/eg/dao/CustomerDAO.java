package com.virtusa.eg.dao;

import java.sql.Connection;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.virtusa.eg.dto.Customer;
import com.virtusa.eg.exceptions.InvalidCredentialsException;



public class CustomerDAO {
	 Logger log = Logger.getLogger(CustomerDAO.class);
	private static CustomerDAO customerDAO = null;

	private CustomerDAO() {
	}

	public static CustomerDAO getInstance() {
		if (customerDAO == null) {
			customerDAO = new CustomerDAO();
		}

		return customerDAO;
	}
	public void registerCustomer(Customer customer)  {
		EGDatabaseConnection.getInstance();
		Connection connection=EGDatabaseConnection.getConnection();
		String insert = "insert into customers(custName,eMail,custPhone,password,custLocation) values (?, ?,?,?,?)";
		String getId = "select primaryKey from customers order by primaryKey desc limit 1";
		String update = "update customers set custId=? where primaryKey=?";
		
		try (
			PreparedStatement insertstmt = connection.prepareStatement(insert);
			PreparedStatement getIdstmt = connection.prepareStatement(getId);
			PreparedStatement updatestmt = connection.prepareStatement(update)){
			
			insertstmt.setString(1, customer.getCustName());
			insertstmt.setString(2, customer.geteMail());
			insertstmt.setString(3, customer.getCustPhone());
			insertstmt.setString(4, customer.getPassword());
			insertstmt.setString(5, customer.getCustLocation());
			insertstmt.executeUpdate();
			ResultSet resultSet = getIdstmt.executeQuery();
			if(resultSet.next()) {
				updatestmt.setString(1, "CUST"+resultSet.getInt("primaryKey"));
				updatestmt.setInt(2, resultSet.getInt("primaryKey"));
				updatestmt.executeUpdate();
			}
		} catch (SQLException e) {
			log.info(e);
		}
		
	
	
	}
	
	public String customerLogin(String email, String password) throws InvalidCredentialsException {
		String customerEmail=null;
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "select eMail,password from customers where eMail=? and password=?";
		try (PreparedStatement stmt = connection.prepareStatement(query);){
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				customerEmail=email;
			log.info("Login Successfull");
				
				
				
			}
			else {
				throw new InvalidCredentialsException("Invalid Credentials");
			}
		} catch (SQLException e) {

			log.info("There is a issue with DB!");
			log.info(e);
		}
		
		return customerEmail;
		
	}
	
	public boolean deleteCustomer(int custId) {
		boolean isDeleted = false;
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "delete from customers where custId = ?";
		try(PreparedStatement stmt = connection.prepareStatement(query);){
			
			stmt.setInt(1, custId);
			int rs = stmt.executeUpdate();
			if(rs>0) {
				isDeleted = true;
				log.info ("Customer data deleted successfully");
			}
			else {
				log.info ("customer Id is Incorrect!");
			}
		} catch (SQLException e) {

			log.info(e);
		}
		return isDeleted;	
	}
	
	public void updateCustomer(Customer customer) {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "update customers set custName=?,eMail=?,custPhone=?,password=?,custLocation=? where custId=?";
		 
		try(PreparedStatement stmt = connection.prepareStatement(query);) {
			
		
		
		stmt.setString(1, customer.getCustName());
		stmt.setString(2, customer.geteMail());
		stmt.setString(3, customer.getCustPhone());
		stmt.setString(4, customer.getPassword());
		stmt.setString(5, customer.getCustLocation());
		stmt.setString(6, customer.getCustId());
		if(stmt.executeUpdate()>0) {
			 log.info ("Customer database updated");
			
		}
		else {
			log.info ("Something went wrong try again!");
			
		}
		} catch (SQLException e) {
			log.info(e);
		}
		
	}
	public void viewCustomer(String email) {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "select * from customers where eMail = ?";
		try(PreparedStatement stmt = connection.prepareStatement(query);) {
			
			stmt.setString(1,email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				String custId = rs.getString("custId");
				String custName = rs.getString("custName");
				String eMail = rs.getString("eMail");
				String custPhone = rs.getString("custPhone");
				String custLocation = rs.getString("custLocation");
				log.info ("Customer Id :"+custId+ "\nCustomer Name:"+custName+ "\nCustomer E-mail:"+eMail+
						"\n Customer phone :" +custPhone+ "\n Customer Location :"+custLocation);
			}
		} catch (SQLException e) {

			log.info(e);
		}
	}
 

} 
