package com.osw.product.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.product.model.Product;
import com.osw.product.repository.ProductRepo;

@Service
public class ProductService implements IProductService{
    
    @Autowired
    private ProductRepo prodrepo;
    
    

    @Override
    public Product addProduct(Product product) {
        // TODO Auto-generated method stub
        
        return prodrepo.save(product);
    }

    @Override
    public Product UpdateProductDetails(Product product) {
        // TODO Auto-generated method stub
    	Product p=prodrepo.save(product);
        return p;
    }

    @Override
    public void deleteProduct(String prodId) {
        // TODO Auto-generated method stub
        Product product=prodrepo.findById(prodId).get();  ///
        prodrepo.deleteById(product.getProdId());
    
    
    }

    @Override
    public List<Product> showProductDetails(String prodName) {
        // TODO Auto-generated method stub
    
        List<Product> productlist =prodrepo.findByProdName(prodName);
        
        return productlist;
    }

    @Override
    public List<Product> showAllProducts() {
        // TODO Auto-generated method stub
        
        return prodrepo.findAll();
    }

    @Override
    public List<Product> findByProdPrice(double prodPrice) {
        // TODO Auto-generated method stub
        return prodrepo.findByProdPrice(prodPrice);
    }

    @Override
    public List<Product> findByProdBrand(String prodBrand) {
List<Product> productlist =prodrepo.findByProdBrand(prodBrand);
        
        return productlist;
    }

    @Override
    public List<Product> findByProdType(String prodType) {
        // TODO Auto-generated method stub
        return prodrepo.findByProdType(prodType);
    }

    @Override
    public boolean findByProdStatus(boolean Status) {
        // TODO Auto-generated method stub
        return prodrepo.findByProdStatus(Status);
    }

    @Override
    public Product findByProdId(String prodId) {
    	System.out.println("In product service"+prodrepo.findById(prodId));
        if(!prodrepo.findById(prodId).isEmpty())
        	return prodrepo.findById(prodId).get();
        return null;
    }

	@Override
	public List<Product> findHighlyRated() {
		// TODO Auto-generated method stub
List<Product> productlist =prodrepo.findByProdRatingGreaterThan(3);
        
        return productlist;
	}

	@Override
	public List<Product> findNewlyArrived(Date date) {
		// TODO Auto-generated method stub
		List<Product> productlist =prodrepo.findAllWithProdDateAfter(date);
		return productlist;
	}

	@Override
	public List<Product> findBelow999() {
		List<Product> productlist =prodrepo.findByProdPriceLessThan(1000);
		return productlist;
	}

    
}