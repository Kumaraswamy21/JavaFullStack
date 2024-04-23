package com.osw;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.osw.admin.model.Admin;
import com.osw.admin.repository.AdminRepo;
import com.osw.admin.service.AdminService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestAdmin {
    @Autowired
    private AdminService adminservice;

    @Autowired
    private AdminRepo adminrepo;
    
    
    
    @Test
    @Order(1)
    public void testAdminLogin() {
        Admin admin=adminservice.validateAdmin("Rajasri", "Rajasri9$");
        assertEquals("Rajasri9$",admin.getAdminPassword());
    }
    
    @Test
    @Order(2)
    public void adminIdvalidationTest() {
        Admin admin=adminservice.validateAdminId("ADMIN_01");
        assertEquals("ADMIN_01",admin.getAdminId());
    }
    
    @Test
    @Order(3)
    public void testUpdateAdmin() {
        Admin admin = adminrepo.findById("ADMIN_01").get();
        admin.setAdminUserName("Rajasri");
        admin.setAdminEmail("raj@gmail.com");
        admin.setAdminPassword("Rajasri9@");
        admin.setContactNo("9898545444");
        adminservice.updateAdmin(admin);
        assertEquals("9898545444", admin.getContactNo());
    }
}