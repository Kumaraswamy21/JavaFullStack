package com.virtusa.eg.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.virtusa.eg.dto.Product;

public class ProductDAO {
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

		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();

			session.persist(product);
			session.getTransaction().commit();
			log.info("Product inserted");

		} catch (Exception e) {
			log.info(e);
		}

	}

	public List<Product> getproducts() {

		List<Product> products = new ArrayList<>();

		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query<Product> query = session.createQuery("from Product", Product.class);
			products = query.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			log.info(e);
		}

		return products;

	}

	public boolean deleteProduct(int prodId) {
		boolean deleted = false;

		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query<Product> query = session.createQuery("delete from Product where prodId=:id ", Product.class);
			query.setParameter("id", prodId);
			int resultSet = query.executeUpdate();
			if (resultSet > 0) {
				deleted = true;
				log.info("Product deleted successfully");
				session.getTransaction().commit();
			} else {
				log.info("Product Id is Incorrect!");
			}
		} catch (Exception e) {

			log.info(e);
		}
		return deleted;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void updateProduct(Product product) {

		String hql = "update Product set prodName=:prodName,prodType=:prodType,prodBrand=:prodBrand,prodPrice=:prodPrice,prodQuantity=:prodQuantity,prodCode=:prodCode,expiryDate=:expiryDate where prodId=:ProdId";
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery(hql);
			query.setParameter("prodName", product.getProdName());
			query.setParameter("prodType", product.getProdType());
			query.setParameter("prodBrand", product.getProdBrand());
			query.setParameter("prodPrice", product.getProdPrice());
			query.setParameter("prodQuantity", product.getProdQuantity());
			query.setParameter("prodCode", product.getProdCode());
			query.setParameter("expiryDate", product.getExpiryDate());
			query.setParameter("ProdId", product.getProdId());

			if (query.executeUpdate() > 0) {
				log.info("Product data updated");
				session.getTransaction().commit();
			} else {
				log.info("Something went wrong try again!");

			}
		} catch (Exception e) {

			log.info(e);
		}
	}

	public Product searchProduct(int productId) {

		Product product = new Product();

		String hql = "from Product where prodId =:prodId";
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query<Product> query = session.createQuery(hql, Product.class);
			query.setParameter("prodId", productId);
			product = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			log.info(e);
		}
		return product;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public int reduceQunantity(Product p1, int prodQuantity) {
		int rs = 0;

		String hquery = "update Product set prodQuantity=:prodQuantity where prodId =:prodId";
		try (Session session = EGDatabaseConnection.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery(hquery);
			if (prodQuantity >= p1.getProdId()) {
				query.setParameter("prodQuantity", prodQuantity - p1.getProdId());
				query.setParameter("prodId", p1.getProdId());
				rs = query.executeUpdate();
				session.getTransaction().commit();
			}

		} catch (Exception e) {
			log.info(e);
		}
		return rs;
	}

}
