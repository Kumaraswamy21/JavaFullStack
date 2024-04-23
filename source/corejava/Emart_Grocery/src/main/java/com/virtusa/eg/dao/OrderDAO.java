package com.virtusa.eg.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.virtusa.eg.dto.Order;
import com.virtusa.eg.dto.Product;

public class OrderDAO {
	Logger log = Logger.getLogger(OrderDAO.class);
	private static OrderDAO orderDAO = null;
	private static final String PRODID = "prodId";
	private static final String PRODNAME = "prodName";
	private static final String QUANTITY = "quantity";
	private OrderDAO() {
	}

	public static OrderDAO getInstance() {
		if (orderDAO == null) {
			orderDAO = new OrderDAO();
		}

		return orderDAO;
	}	
public void placeOrder(Order order) {
	EGDatabaseConnection.getInstance();
	Connection connection = EGDatabaseConnection.getConnection();
	String query = "insert into orders(prodId, prodName, quantity, custId, prodPrice, totalPrice, orderStatus) values (?,?,?,?,?,?,?)"; 
	try(PreparedStatement statement = connection.prepareStatement(query) ){
		statement.setInt(1, order.getProdId());
		statement.setString(2, order.getProdName());
		statement.setInt(3,order.getQuantity());
		statement.setString(4, order.getCustId());
		statement.setDouble(5, order.getProdPrice());
		statement.setDouble(6, order.getTotalPrice());
		statement.setString(7, order.getOrderStatus());
		
		if(statement.executeUpdate()>0) {
			log.info("Order Placed");
		}
		else {
			log.info("Something went wrong! please try again...");
		}
	} catch (SQLException e) {

		log.info(e);
	}
	
}
public Order searchOrder(int orderId) {
	EGDatabaseConnection.getInstance();
	Connection connection = EGDatabaseConnection.getConnection();
	Order order = null;
	String query = "select * from orders where orderId = ?";
	try(PreparedStatement statement = connection.prepareStatement(query)){
		statement.setInt(1, orderId);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			order=new Order();
			order.setOrderId( resultSet.getInt(1));
			order.setProdId(resultSet.getInt(2));
			order.setProdName(resultSet.getString(3)); 
			order.setQuantity(resultSet.getInt(4)); 
			order.setCustId(resultSet.getString(6)); 
			order.setProdPrice(resultSet.getDouble(5)); 
			order.setTotalPrice(resultSet.getDouble(7)); 
			order.setOrderStatus(resultSet.getString(8));
			
		}
	} catch (SQLException e) {
		log.info(e);
	}
	return order;
}

public List<Order> listOfOrders(){
	EGDatabaseConnection.getInstance();
	Connection connection = EGDatabaseConnection.getConnection();
	List<Order> orders = new ArrayList<>();
	String query = "select * from orders";
	try(PreparedStatement statement = connection.prepareStatement(query)){
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			Order order = new Order();
			order.setOrderId( resultSet.getInt("orderId"));
			order.setProdId(resultSet.getInt(PRODID));
			order.setProdName(resultSet.getString(PRODNAME)); 
			order.setQuantity(resultSet.getInt(QUANTITY)); 
			order.setCustId(resultSet.getString("custId")); 
			order.setProdPrice(resultSet.getDouble("prodPrice")); 
			order.setTotalPrice(resultSet.getDouble("totalPrice")); 
			order.setOrderStatus(resultSet.getString("orderStatus")); 
			orders.add(order);
			
			
		}
	} catch (SQLException e) {
		log.info(e);
	}
	return orders;
}

public Product updateOrderStatus(int orderId, String status) {
	int rs;
	Product p = new Product();
	Connection con = EGDatabaseConnection.getConnection();
	try (PreparedStatement stmt = con.prepareStatement("update orders set orderStatus=? where orderId=?")){
		stmt.setString(1, status);
		stmt.setInt(2, orderId);
		rs = stmt.executeUpdate();
		if(rs>0)
		{
			try(PreparedStatement stmt1 = con.prepareStatement("select * from orders where orderId=?")){
			stmt1.setInt(1, orderId);
			ResultSet rs1 = stmt1.executeQuery();
			while(rs1.next())
			{
				p.setProdId(rs1.getInt(PRODID));
				p.setProdName(rs1.getString(PRODNAME));
				p.setProdQuantity(rs1.getInt(QUANTITY));
			}
		}
	}
}catch (SQLException e) {
	log.info(e);
}
		return p;
	
			
	
	
	
}

public List<Order> getApprovedOrders() {
	Connection connection = EGDatabaseConnection.getConnection();
	List<Order> listOfOrders = new ArrayList<>();
	try (PreparedStatement stmt = connection.prepareStatement("select * from orders where orderStatus='Approved'");){
		ResultSet resultSet = stmt.executeQuery();
		while(resultSet.next()) {
			Order order = new Order();
			order.setOrderId( resultSet.getInt("orderId"));
			order.setProdId(resultSet.getInt(PRODID));
			order.setProdName(resultSet.getString(PRODNAME)); 
			order.setQuantity(resultSet.getInt(QUANTITY)); 
			order.setCustId(resultSet.getString("custId")); 
			order.setProdPrice(resultSet.getDouble("prodPrice")); 
			order.setTotalPrice(resultSet.getDouble("totalPrice")); 
			order.setOrderStatus(resultSet.getString("orderStatus")); 
			listOfOrders.add(order);
		}
	} catch (SQLException e) {
		log.info(e);
	}
	return listOfOrders;
}

}
