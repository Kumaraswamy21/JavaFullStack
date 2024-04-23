package com.virtusa.eg.dao;



import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import com.virtusa.eg.dto.Admin;
import com.virtusa.eg.dto.Customer;
import com.virtusa.eg.dto.Order;
import com.virtusa.eg.dto.Product;
import com.virtusa.eg.dto.Supplier;




public class EGDatabaseConnection {
	private EGDatabaseConnection() {}
	private static final SessionFactory SESSION_FACTORY;
	 static {
		 Configuration con = new Configuration().configure().addAnnotatedClass(Admin.class)
															   .addAnnotatedClass(Customer.class)
															   .addAnnotatedClass(Supplier.class)
															   .addAnnotatedClass(Order.class)
															   .addAnnotatedClass(Product.class);
															   
		 
		 ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		 SESSION_FACTORY = con.buildSessionFactory(reg);
	 }
	 public static SessionFactory getSessionFactory() {
	    	return SESSION_FACTORY;
	    	}
}
