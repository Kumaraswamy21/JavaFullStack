package com.virtusa.eg.util;

import java.util.Comparator;

import com.virtusa.eg.dto.Product;

public class ProductNameSorting implements Comparator<Product> {

	@Override
	public int compare(Product product1, Product product2) {
		return product1.getProdName().compareToIgnoreCase(product2.getProdName());
	}


}
