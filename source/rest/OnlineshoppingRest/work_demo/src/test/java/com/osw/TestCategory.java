package com.osw;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.osw.product.model.Category;
import com.osw.product.repository.CategoryRepo;
import com.osw.product.service.CategoryService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestCategory {
    @Autowired
    private CategoryRepo catrepo;
    
    @Autowired
    private CategoryService catservice;
    
    
    @Test
    @Order(1)
    public void testAddCategory() {
        Category category= new Category();
        category.setCategoryName("Electronics");
        category.setImageUrl("../img/electronics.png");
        catservice.insertCategory(category);
        assertNotNull(catrepo.findById("CAT_004").get());
    }
    
    @Test
    @Order(2)
    public void testShowCategoryByName() {
        Category category=catservice.showCategoryByName("Electronics");
        assertEquals("Electronics",category.getCategoryName());
    }
    
    @Test
    @Order(3)
    public void testUpdateCategory() {
        Category category=catservice.showCategoryById("CAT_004");
        category.setCategoryName("Electronics");
        category.setImageUrl("../img/electronics1.jpg");
        catrepo.save(category);
        assertEquals("../img/electronics1.jpg",category.getImageUrl());        
    }
    
    @Test
    @Order(4)
    public void testshowAllCategories() {
        List<Category> categorylist=catservice.showAllCategories();
        assertThat(categorylist).size().isGreaterThan(0);
    }
    
    
    @Test
    @Order(5)
    public void testDeleteCategory() {
        catrepo.deleteById("CAT_004");
        assertThat(catrepo.existsById("CAT_004")).isFalse();
    }
    
    
}