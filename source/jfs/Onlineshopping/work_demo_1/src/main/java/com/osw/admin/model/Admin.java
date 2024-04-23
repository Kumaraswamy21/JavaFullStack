package com.osw.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.osw.model.sequence.StringPrefixedSequenceIdGenerator;
import com.osw.validations.MobileNumber;
import com.osw.validations.Password;


@Entity
public class Admin {
    @Id
    private String adminId;
    @NotBlank
    @Size(min=3,max=20,message="UserName should have at least 3 characters")
    private String adminUserName;
    @NotBlank
    @Email
    private String adminEmail;
    @NotBlank  
    @MobileNumber
    private String contactNo;
   
   @Password
    private String adminPassword;
   
    @Password
    private String adminconfirmPassword;
    
    public String getAdminId() {
        return adminId;
    }
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    public String getAdminUserName() {
        return adminUserName;
    }
    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }
    public String getAdminEmail() {
        return adminEmail;
    }
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getAdminPassword() {
        return adminPassword;
    }
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
	public String getAdminconfirmPassword() {
		return adminconfirmPassword;
	}
	public void setAdminconfirmPassword(String adminconfirmPassword) {
		this.adminconfirmPassword = adminconfirmPassword;
	}
    
    
    

}