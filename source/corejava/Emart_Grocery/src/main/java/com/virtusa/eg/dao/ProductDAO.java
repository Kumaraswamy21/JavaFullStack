package com.virtusa.eg.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.virtusa.eg.dto.Product;


public class ProductDAO{
	 Logger log = Logger.getLogger(ProductDAO.class);
	private static ProductDAO productDAO = null;

	private ProductDAO() {
	}

	public static ProductDAO getInstance() {
		if (productDAO == null) {
			productDAO = new ProductDAO();
		}

		return productDAO;
	}

	public void addProduct(Product product) {
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "insert into product(prodName,prodType,prodBrand,prodPrice,prodQuantity,prodCode,expiryDate)values(?,?,?,?,?,?,?)";
		try(PreparedStatement stmt = connection.prepareStatement(query)){
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdType());
		stmt.setString(3, product.getProdBrand());
		stmt.setDouble(4, product.getProdPrice());
		stmt.setInt(5, product.getProdQuantity());
		stmt.setString(6, product.getProdCode());
		stmt.setDate(7, product.getExpiryDate());
		if(stmt.executeUpdate()>0) {
			log.info ("Product inserted");
			
		}
		else {
			log.info ("Something went wrong try again!");
			
		}
		} catch (SQLException e) {
			log.info(e);
		}
		
	}
	
	public List<Product>getproducts(){
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		List<Product> products = new ArrayList<>();
		String query = "select * from product";
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			
		
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setProdId(rs.getInt("prodId"));
				product.setProdName(rs.getString("prodName"));
				product.setProdType(rs.getString("prodType"));
				product.setProdBrand(rs.getString("prodBrand"));
				product.setProdCode(rs.getString("prodCode"));
				product.setProdQuantity(rs.getInt("prodQuantity"));
				product.setProdPrice(rs.getDouble("prodPrice"));
				product.setExpiryDate(rs.getDate("ExpiryDate"));
				products.add(product);
				
			}
		} catch (SQLException e) {
			log.info(e);
		}
		
		return products;
		
	}
	
	public boolean deleteProduct(int prodId) {
		boolean isDeleted = false;
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "delete from product where prodId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			
			stmt.setInt(1, prodId);
			int rs = stmt.executeUpdate();
			if(rs>0) {
				isDeleted = true;
			log.info ("Product deleted successfully");
			}
			else {
				log.info("Product Id is Incorrect!");
			}
		} catch (SQLException e) {
			
			log.info(e);
		}
		return isDeleted;	
	}
	
	public void updateProduct(Product product) {
		
		EGDatabaseConnection.getInstance();
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "update product set prodName=?,prodType=?,prodBrand=?,prodPrice=?,prodQuantity=?,prodCode=?,expiryDate=? where prodId=?";
		try(PreparedStatement stmt = connection.prepareStatement(query);){
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdType());
		stmt.setString(3, product.getProdBrand());
		stmt.setDouble(4, product.getProdPrice());
		stmt.setInt(5, product.getProdQuantity());
		stmt.setString(6, product.getProdCode());
		stmt.setDate(7, product.getExpiryDate());
		stmt.setInt(8, product.getProdId());
		
		if(stmt.executeUpdate()>0) {
			log.info ("Product data updated");
			
		}
		else {
			log.info ("Something went wrong try again!");
			
		}
		} catch (SQLException e) {
			
			log.info(e);
		}
	}
	public Product searchProduct(int productId) {
			EGDatabaseConnection.getInstance();
			Product product = new Product();
			Connection connection = EGDatabaseConnection.getConnection();
			String query = "select * from product where prodId = ?";
			try(PreparedStatement statement = connection.prepareStatement(query)){
				statement.setInt(1, productId);
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					
					product.setProdId(rs.getInt("prodId"));
					product.setProdName(rs.getString("prodName"));
					product.setProdType(rs.getString("prodType"));
					product.setProdBrand(rs.getString("prodBrand"));
					product.setProdCode(rs.getString("prodCode"));
					product.setProdQuantity(rs.getInt("prodQuantity"));
					product.setProdPrice(rs.getDouble("prodPrice"));
					product.setExpiryDate(rs.getDate("ExpiryDate"));
					
					

					
				}
			} catch (SQLException e) {
				log.info(e);
			}
			return product;
	}

	public int reduceQunantity(Product p1, int prodQuantity) {
		int rs=0;
		EGDatabaseConnection.getInstance();
		
		Connection connection = EGDatabaseConnection.getConnection();
		String query = "update product set prodQuantity=? where prodId = ?";
		try(PreparedStatement statement = connection.prepareStatement(query)){
			
			
			if(prodQuantity>=p1.getProdId())
			{
				statement.setInt(1, prodQuantity-p1.getProdId());
				statement.setInt(2, p1.getProdId());
				rs = statement.executeUpdate();
			}
			
		} catch (SQLException e) {
			log.info(e);
		}
		return rs;
	}
	

}
