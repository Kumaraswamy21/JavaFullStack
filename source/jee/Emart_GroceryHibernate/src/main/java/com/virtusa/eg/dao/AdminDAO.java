package com.virtusa.eg.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.virtusa.eg.dto.Admin;
import com.virtusa.eg.dto.Customer;
import com.virtusa.eg.dto.Supplier;


public class AdminDAO {
	Logger log = Logger.getLogger(AdminDAO.class);
	
private static AdminDAO adminDAO = null;
	
	private AdminDAO() {
		
	}
	public static AdminDAO getInstance() {
		if(adminDAO == null) {
			adminDAO = new AdminDAO();
		}
		return adminDAO;
	}
	
	public Admin adminLogin(String email, String password)  {
		
		
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query<Admin> query = session.createQuery("from Admin where adminEmail=:email and password=:password",Admin.class);
			query.setParameter("email",email);
			query.setParameter("password", password);
			Admin admin = query.getSingleResult();
			session.getTransaction().commit();
			if(admin!=null) {
			
			log.info("Login Successfull");
				return admin;
			}
		}
			
		catch ( Exception e) {

			log.info("There is a issue with DB!");
			log.info(e);
		}
		return null;
		
		
		
	}
	
	public void viewAdminProfile(String email) {
	
		try(Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query<Admin> query = session.createQuery("from Admin where adminEmail=:email",Admin.class);
			query.setParameter("email",email);
			Admin admin = query.getSingleResult();
			session.getTransaction().commit();
		log.info(admin);
		} catch (Exception e) {

			log.info(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateAdmin(Admin admin) {
		
		 
		try(Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			session.saveOrUpdate(admin);
			session.getTransaction().commit();
		
		} catch (Exception e) {
			log.info(e);
		}
		
	}
	
	
	public List<Supplier> getAllSuppliers(){
		
		List<Supplier> supplierList = new ArrayList<>();
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query<Supplier> query = session.createQuery("from Supplier",Supplier.class);
			supplierList = query.getResultList();
			session.getTransaction().commit();
			
		} catch (Exception e) {

			log.info(e);
		}
		return supplierList;
		
	}
	
	
	public List<Customer> getAllCustomers(){
		
		List<Customer> customerList = new ArrayList<>();
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query<Customer> query = session.createQuery("from Customer",Customer.class);
			customerList = query.getResultList();
			session.getTransaction().commit();
	
		} catch (Exception e) {

			log.info(e);
		}
		return customerList;
		
	}
	
	public void searchCustomerById(String id) {

		try(Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query<Customer> query = session.createQuery("from Customer where custId=:id",Customer.class);
			query.setParameter("id",id);
			Customer customer = query.getSingleResult();
			session.getTransaction().commit();
			log.info(customer);
		} catch (Exception e) {

			log.info(e);
		}
	}
 
	public void searchSupplierById(int id) {
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query<Supplier> query = session.createQuery("from Supplier where supplierId=:id",Supplier.class);

			query.setParameter("id",id);
			Supplier supplier = query.getSingleResult();
			log.info(supplier);
		} catch (Exception e) {

			log.info(e);
		}
	}
	
}
