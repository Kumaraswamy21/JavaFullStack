package com.virtusa.eg.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.virtusa.eg.dto.Order;
import com.virtusa.eg.dto.Product;

public class OrderDAO {
	Logger log = Logger.getLogger(OrderDAO.class);
	private static OrderDAO orderDAO = null;
	
	private OrderDAO() {
	}

	public static OrderDAO getInstance() {
		if (orderDAO == null) {
			orderDAO = new OrderDAO();
		}

		return orderDAO;
	}	
public void placeOrder(Order order) {

	 
	try(Session session = EGDatabaseConnection.getSessionFactory().openSession() ){
		
		session.beginTransaction();
		

		session.persist(order);
		
		
		
		
		
		session.getTransaction().commit();
		log.info("Order Placed");
		
		
	} catch (Exception e) {

		log.info(e);
	}
	
}
public Order searchOrder(int orderId) {
	
	Order order = null;
	String query = "from Order where orderId =:OrderId";
	try(Session session = EGDatabaseConnection.getSessionFactory().openSession()){
		session.beginTransaction();
		Query<Order> hql = session.createQuery(query,Order.class);
		hql.setParameter("OrderId", orderId);
		order = hql.getSingleResult();
	} catch (Exception e) {
		log.info(e);
	}
	return order;
}

public List<Order> listOfOrders(){
	
	List<Order> orders = new ArrayList<>();
	String query = "from Order";
	try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
		session.beginTransaction();
		Query<Order> query1 = session.createQuery(query,Order.class);
		orders = query1.getResultList();
		session.getTransaction().commit();
	} catch (Exception e) {
		log.info(e);
	}
	return orders;
}

@SuppressWarnings({ "deprecation", "rawtypes" })
public Product updateOrderStatus(int orderId, String status) {
	int rs;
	Product p = new Product();
	
	
	try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
		session.beginTransaction();
		Query query = session.createQuery("update Order set orderStatus=:orderStatus where orderId=:orderId");
		query.setParameter("orderStatus", status);
		query.setParameter("orderId", orderId);
		rs = query.executeUpdate();
		if(rs>0)
		{
			session.getTransaction().isActive();
			try{
				Query<Order> query1 = session.createQuery("from Order where orderId=:orderId",Order.class);
			query1.setParameter("orderId", orderId);
			Order rs1 = query1.getSingleResult();
			while(rs1!=null)
			{
				p.setProdId(rs1.getProdId());
				p.setProdName(rs1.getProdName());
				p.setProdQuantity(rs1.getQuantity());
				session.getTransaction().commit();
			}
		
			}
catch (Exception e) {
	log.info(e);
}
			}
		return p;
	}
	
}

public List<Order> getApprovedOrders() {
	
	List<Order> listOfOrders = new ArrayList<>();
	try (Session session = EGDatabaseConnection.getSessionFactory().openSession()){
		Query<Order> query = session.createQuery("from Order where orderStatus='Approved'",Order.class);
		listOfOrders=query.getResultList();	
	} catch (Exception e) {
		log.info(e);
	}
	return listOfOrders;
}

}
