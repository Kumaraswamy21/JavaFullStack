package com.osw.product.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.osw.product.model.Product;

public interface ProductRepo extends JpaRepository<Product, String>{
     List<Product> findByProdName(String prodName);
     List<Product> findByProdPrice(double prodPrice);
     List<Product> findByProdBrand(String prodBrand);
     List<Product> findByProdType(String prodType);
     boolean findByProdStatus(boolean Status);
    List<Product> findByProdRatingGreaterThan(int prodRating);
    @Query("select p from Product p where p.proddate >= proddate")
    List<Product> findAllWithProdDateAfter(Date proddate);
    List<Product> findByProdPriceLessThan(double prodPrice);
}