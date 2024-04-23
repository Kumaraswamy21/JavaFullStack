package com.virtusa.eg.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.virtusa.eg.dto.Supplier;
import com.virtusa.eg.exceptions.InvalidCredentialsException;

import org.apache.log4j.Logger;
public class SupplierDAO {
	
	 Logger log = Logger.getLogger(SupplierDAO.class);
	private static SupplierDAO supplierDAO = null;

	private SupplierDAO() {
	}

	public static SupplierDAO getInstance() {
		if (supplierDAO == null) {
			supplierDAO = new SupplierDAO();
		}

		return supplierDAO;
	}
	
	public void addSupplier(Supplier supplier)  {
		EGDatabaseConnection.getInstance();
		Connection connection=EGDatabaseConnection.getConnection();
		String query = "insert into supplier(supplierName,eMail,phone,password,location) values (?, ?,?,?,?)";
		
		try (PreparedStatement stmt = connection.prepareStatement(query)){
		
		
		stmt.setString(1, supplier.getSupplierName());
		stmt.setString(2, supplier.geteMail());
		stmt.setString(3, supplier.getPhone());
		stmt.setString(4, supplier.getPassword());
		stmt.setString(5, supplier.getLocation());
		if(stmt.executeUpdate()>0) {
			log.info ("Supplier added to database");
			
		}
		else {
			log.info ("Something went wrong try again!");
			
		}
		} catch (SQLException e) {

			log.info(e);
		}
	}
	
	public String supplierLogin(String email, String password) {
		String supplierEmail=null;
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "select eMail,password from supplier where eMail=? and password=?";
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				supplierEmail=email;
				log.info ("Login Successfull");
				
				
				
			}
			else {
				throw new InvalidCredentialsException ("Invalid Credentials");
			}
		} catch (SQLException | InvalidCredentialsException e) {

			log.info ("There is a issue with DB!");
			log.info(e);
		}
		
		return supplierEmail;
	}
	
	public boolean deleteSupplier(int supplierId) {
		boolean isDeleted = false;
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "delete from supplier where supplierId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			
			stmt.setInt(1, supplierId);
			int rs = stmt.executeUpdate();
			if(rs>0) {
				isDeleted = true;
				log.info ("Supplier data deleted successfully");
			}
			else {
				log.info ("SupplierId is Incorrect!");
			}
		} catch (SQLException e) {

			log.info(e);
		}
		return isDeleted;	
	}
	
	public void updateSupplier(Supplier supplier)  {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "update supplier set supplierName=?,eMail=?,phone=?,password=?,location=? where supplierId=?";
		try(PreparedStatement stmt = connection.prepareStatement(query)){
		
		stmt.setString(1, supplier.getSupplierName());
		stmt.setString(2, supplier.geteMail());
		stmt.setString(3, supplier.getPhone());
		stmt.setString(4, supplier.getPassword());
		stmt.setString(5, supplier.getLocation());
		stmt.setInt(6, supplier.getSupplierId());
		if(stmt.executeUpdate()>0) {
			log.info ("Supplier database updated");
			
		}
		else {
			log.info ("Something went wrong try again!");
			
		}
		} catch (SQLException e) {

			log.info(e);
		}
		
	}
	public void viewSupplier(String email) {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "select * from supplier where eMail = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			
			stmt.setString(1,email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int supplierId = rs.getInt("supplierId");
				String supplierName = rs.getString("supplierName");
				String eMail = rs.getString("eMail");
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
