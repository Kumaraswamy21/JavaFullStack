package com.virtusa.eg.controller;




import com.virtusa.eg.service.ProductService;

import com.virtusa.eg.service.ProductServiceImpl;

public class ProductController {

	ProductService productService = ProductServiceImpl.getInstance();
	public void addProduct() {
		productService.addProduct();
	}
	public void getProducts(){
		productService.getProducts();
		
	}
	public void deleteProduct() {
		productService.deleteProduct();
	}
	public void updateProduct() {
		productService.updateProduct();
	}
}
