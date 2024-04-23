package com.virtusa.eg.service;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.virtusa.eg.dao.ProductDAO;

import com.virtusa.eg.dto.Product;
import com.virtusa.eg.util.ProductNameSorting;

import org.apache.log4j.Logger;
public class ProductServiceImpl implements ProductService {
	
	private static ProductServiceImpl productServiceImpl = null;
	private ProductServiceImpl() {
		
	}
	public static ProductServiceImpl getInstance() {
		if(productServiceImpl==null) {
			productServiceImpl = new ProductServiceImpl();
		}
		return productServiceImpl;
	}
	
	Product product = new Product();
	static Logger log = Logger.getLogger(ProductServiceImpl.class);
	Scanner sc = new Scanner(System.in);

	public void addProduct() {
		
		prodInput();
		
		ProductDAO.getInstance().addProduct(product);
	}

	public void getProducts() {

		List<Product> list = ProductDAO.getInstance().getproducts();
		Collections.sort(list, new ProductNameSorting());
		list.forEach(p->log.info(p));
	}

	@Override
	public void updateProduct(){

		prodInput();
			
		 
		ProductDAO.getInstance().updateProduct(product);
	
	}

	@Override
	public void deleteProduct() {

		log.info("Enter the product Id of the Product to be deleted:");
		int prodId = sc.nextInt();
		ProductDAO.getInstance().deleteProduct(prodId);
	}

	@Override
	public Product searchProduct(int pId) {
		return ProductDAO.getInstance().searchProduct(pId);
	}
	public int reduceQunantity(Product p1, int prodQuantity) {
	return ProductDAO.getInstance().reduceQunantity( p1,  prodQuantity);
		
	}
	private void prodInput() {
		log.info("Enter Product Id:");
		 product.setProdId(sc.nextInt());
		 
		 log.info("Enter Product Name:");
			product.setProdName(sc.next());

			log.info("Enter Product Type:");
			product.setProdType(sc.next());

			log.info("Enter Product Brand:");
			product.setProdBrand(sc.next());

			log.info("Enter Product Code:");
			product.setProdCode(sc.next());

			log.info("Enter Product Quantity:");
			product.setProdQuantity(sc.nextInt());

			log.info("Enter Product Price:");
			product.setProdPrice(sc.nextDouble());

			
			log.info("Enter Product Expiry Date:");
			String expDate = sc.next();
			Date expDate1 = Date.valueOf(expDate);
			
			product.setExpiryDate(expDate1);
	}
}
