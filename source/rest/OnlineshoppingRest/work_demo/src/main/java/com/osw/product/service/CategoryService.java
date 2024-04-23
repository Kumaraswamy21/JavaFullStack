package com.osw.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.admin.model.Admin;
import com.osw.product.model.Category;
import com.osw.product.model.Product;
import com.osw.product.repository.CategoryRepo;

@Service
public class CategoryService implements ICategoryService{
    
    @Autowired
    private CategoryRepo catrepo;

    @Override
    public Category insertCategory(Category category) {
        // TODO Auto-generated method stub
        Category category1 = catrepo.save(category);
        return category1;
    }

    @Override
    public Category changeCategory(Category category) {
        // TODO Auto-generated method stub
        Category category1 = catrepo.save(category);
        return category1;
    }

    @Override
    public void deleteCategory(String catId) {
        // TODO Auto-generated method stub
        catrepo.deleteById(catId);
    }

    @Override
    public List<Category> showAllCategories() {
        // TODO Auto-generated method stub
        List<Category> categorylist=catrepo.findAll();
        return categorylist;
    }

    @Override
    public Category showCategoryById(String catId) {
        // TODO Auto-generated method stub
    	Optional<Category> alist=catrepo.findById(catId);
        if(!alist.isEmpty()) {
            return alist.get();
        }else
        return null;
        
    }

    @Override
    public Category showCategoryByName(String catName) {
        // TODO Auto-generated method stub
        Category category = catrepo.findByCategoryName(catName);
        return category;
    }


    @Override
    public List<Product> productlistinCategory(String catId) {
        // TODO Auto-generated method stub
        Category category =catrepo.findById(catId).get();
        return category.getProductlist();
    }
     
    
    
}