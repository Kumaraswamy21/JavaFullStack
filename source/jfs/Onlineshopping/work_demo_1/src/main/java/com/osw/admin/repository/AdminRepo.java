package com.osw.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.admin.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, String> {
    
    public Admin findByAdminUserName(String username);

}