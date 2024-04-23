package com.virtusa.eg.dao;



import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;



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
		
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			
			
			session.persist(customer);
				session.getTransaction().commit();
		
		} catch (Exception e) {
			log.info(e);
		}
		
	
	
	}
	
	public String customerLogin(String email, String password) throws InvalidCredentialsException {
		String customerEmail=null;
		
		String query = "from Customer where eMail=:eMail and password=:password";
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query<Customer> login = session.createQuery(query,Customer.class);
			login.setParameter("eMail", email);
			login.setParameter("password", password);
			Customer customer = login.getSingleResult();
			
			if(customer!=null) {
				customerEmail=email;
			log.info("Login Successfull");
				
				session.getTransaction().commit();
				
			}
			else {
				throw new InvalidCredentialsException("Invalid Credentials");
			}
		} catch (Exception e) {

			log.info("There is a issue with DB!");
			log.info(e);
		}
		
		return customerEmail;
		
	}
	
	public boolean deleteCustomer(int custId) {
		boolean isDeleted = false;
		
		String query1 = "delete from Customer where custId =:custId ?";
		try(Session session = EGDatabaseConnection.getSessionFactory().openSession()){
			session.beginTransaction();
			Query<Customer> query = session.createQuery(query1,Customer.class);
			query.setParameter("custId", custId);
			int rs = query.executeUpdate();
			if(rs>0) {
				isDeleted = true;
				log.info ("Customer data deleted successfully");
				session.getTransaction().commit();
			}
			else {
				log.info ("customer Id is Incorrect!");
			}
		} catch (Exception e) {

			log.info(e);
		}
		return isDeleted;	
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public void updateCustomer(Customer customer) {
		
		String query = "update Customer set custName=:custName,eMail=:EMail,custPhone=:custPhone,password=:password,custLocation=:custLocation where custId=:custId";
		 
		try(Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			Query update = session.createQuery(query);
		
		update.setParameter("custName", customer.getCustName());
		update.setParameter("EMail", customer.geteMail());
		update.setParameter("custPhone", customer.getCustPhone());
		update.setParameter("password", customer.getPassword());
		update.setParameter("custLocation", customer.getCustLocation());
		update.setParameter("custId", customer.getCustId());
		if(update.executeUpdate()>0) {
			 log.info ("Customer database updated");
			session.getTransaction().commit();
		}
		else {
			log.info ("Something went wrong try again!");
			
		}
		} catch (Exception e) {
			log.info(e);
		}
		
	}
	public void viewCustomer(String email) {
		
		String query = "from Customer where eMail =:eMail";
		try(Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			Query<Customer> view = session.createQuery(query,Customer.class);
			view.setParameter("eMail",email);
			Customer customer = view.getSingleResult();
			log.info(customer);
				
			}
		 catch (Exception e) {

			log.info(e);
		}
	}
 

} 
