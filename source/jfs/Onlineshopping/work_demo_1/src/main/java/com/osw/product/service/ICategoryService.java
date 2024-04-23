package com.osw.product.service;

import java.util.List;

import com.osw.product.model.Category;
import com.osw.product.model.Product;

public interface ICategoryService {
    
public Category insertCategory(Category category);
public Category changeCategory(Category category);
public void deleteCategory(String catId);
public List<Category> showAllCategories();
public Category showCategoryById(String catId);
public Category showCategoryByName(String catName);
public List<Product> productlistinCategory(String catId);
}