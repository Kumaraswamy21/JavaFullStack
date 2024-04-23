package com.osw.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.product.model.Category;

public interface CategoryRepo extends JpaRepository<Category, String>{
    
    public Category findByCategoryName(String catName);

}