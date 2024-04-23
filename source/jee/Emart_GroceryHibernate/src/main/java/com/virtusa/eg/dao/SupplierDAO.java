package com.virtusa.eg.dao;



import com.virtusa.eg.dto.Supplier;
import com.virtusa.eg.exceptions.InvalidCredentialsException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
		
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
		session.beginTransaction();
		session.persist(supplier);
		session.getTransaction().commit();
		
		
		
			log.info ("Supplier added to database");
			
		} catch (Exception e) {

			log.info(e);
		}
	}
	
	public String supplierLogin(String email, String password) {
		String supplierEmail=null;
		
	
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query<Supplier> query = session.createQuery("from Supplier where eMail=:Email and password=:password",Supplier.class);
			query.setParameter("Email", email);
			query.setParameter("password", password);
			Supplier supplier = query.getSingleResult();
			
			if(supplier!=null) {
				supplierEmail=email;
				log.info ("Login Successfull");
				
				session.getTransaction().commit();
				
			}
			else {
				throw new InvalidCredentialsException ("Invalid Credentials");
			}
		} catch (Exception e) {

			log.info ("There is a issue with DB!");

		}
		
		return supplierEmail;
	}
	
	public boolean deleteSupplier(int supplierId) {
		boolean deletion = false;
		
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query<Supplier> query = session.createQuery("delete from Supplier where supplierId=:id ",Supplier.class);
			query.setParameter("id", supplierId);

			int result = query.executeUpdate();
			
			if(result>0) {
				deletion = true;
				log.info ("Supplier data deleted successfully");
				session.getTransaction().commit();
			}
			else {
				log.info ("SupplierId is Incorrect!");
			}
		} catch (Exception e) {

			log.info(e);
		}
		return deletion;	
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public void updateSupplier(Supplier supplier)  {
		
		
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query query = session.createQuery("update Supplier s set s.supplierName=:name,s.eMail=:email,s.phone=:phone,s.password=:password,s.location=:location where s.supplierId=:id ");
		
		query.setParameter("name", supplier.getSupplierName());
		query.setParameter("email", supplier.geteMail());
		query.setParameter("phone", supplier.getPhone());
		query.setParameter("password", supplier.getPassword());
		query.setParameter("location", supplier.getLocation());
		query.setParameter("id", supplier.getSupplierId());
		int rs =query.executeUpdate();
		if(rs>0) {
			log.info ("Supplier database updated");
			
		}
		else {
			log.info ("Something went wrong try again!");
			
		}
		} catch (Exception e) {

			log.info(e);
		}
		
	}
	public void viewSupplier(String email) {
		
		try(Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query<Supplier> query = session.createQuery("from Supplier where eMail=:email",Supplier.class);
			query.setParameter("email",email);
			Supplier supplier = query.getSingleResult();
			session.getTransaction().commit();
		log.info(supplier);
		} catch (Exception e) {

			log.info(e);
		}
			
	}
 
}
