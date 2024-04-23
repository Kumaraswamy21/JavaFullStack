package com.virtusa.eg.service;

import com.virtusa.eg.dto.Product;

public interface ProductService {
		public void addProduct();
		public void getProducts();
		public void updateProduct();
		public void deleteProduct();
		public Product searchProduct(int pId);
		
		
}
