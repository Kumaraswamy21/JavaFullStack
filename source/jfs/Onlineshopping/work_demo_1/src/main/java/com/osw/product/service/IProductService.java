package com.osw.product.service;

import java.util.Date;
import java.util.List;

import com.osw.product.model.Product;

public interface IProductService {
    public Product addProduct(Product product);
    public Product UpdateProductDetails(Product product);
    public void deleteProduct(String prodId); ////
    public List<Product> showProductDetails(String prodName);
    public List<Product> showAllProducts();
    public Product findByProdId(String prodId); ////
  
    public List<Product> findByProdPrice(double prodPrice);
    public List<Product> findByProdBrand(String prodBrand);
    public List<Product> findByProdType(String prodType);
    public boolean findByProdStatus(boolean Status);
    public List<Product> findHighlyRated();
   public List<Product> findNewlyArrived(Date date);
   public List<Product> findBelow999();
    
    

}