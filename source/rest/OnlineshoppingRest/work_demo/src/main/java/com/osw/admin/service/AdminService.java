package com.osw.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.admin.model.Admin;
import com.osw.admin.repository.AdminRepo;

@Service
public class AdminService implements IAdminService{
    
    @Autowired
    private AdminRepo adminrepo;

    
    @Override
    public Admin updateAdmin(Admin admin) {
        // TODO Auto-generated method stub
        Admin admin1= adminrepo.save(admin); 
        return admin1;
    }

    @Override
    public Admin validateAdmin(String username, String password) {
        // TODO Auto-generated method stub
        Admin admin = adminrepo.findByAdminUserName(username);
        if(admin!=null && admin.getAdminPassword().equals(password)) {
            return admin;            
        }else
        return null;
    }

    @Override
    public Admin validateAdminId(String adminid) {
        // TODO Auto-generated method stub 
    	Optional<Admin> alist=adminrepo.findById(adminid);
        if(!alist.isEmpty()) {
            return alist.get();
        }else
        return null;
    }

	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminrepo.save(admin);
	}


}