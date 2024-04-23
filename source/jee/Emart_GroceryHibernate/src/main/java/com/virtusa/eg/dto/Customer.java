package com.virtusa.eg.dto;




import org.hibernate.annotations.GenericGenerator;




import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;




@Entity
public class Customer {
	@Id
	@GeneratedValue(generator = "CUST_SEQ")
	@GenericGenerator(name = "CUST_SEQ", strategy = "com.virtusa.eg.sequencegenerator.StringPrefixedSequenceIdGenerator")
	private String custId;

	private String custName;
	private String eMail;
	private String custPhone;
	private String password;
	private String custLocation;

	public Customer() {
		super();
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustLocation() {
		return custLocation;
	}

	public void setCustLocation(String custLocation) {
		this.custLocation = custLocation;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", eMail=" + eMail + ", custPhone=" + custPhone
				+ ", password=" + password + ", custLocation=" + custLocation + "]";
	}

	public Customer(String custId, String custName, String eMail, String custPhone, String password,
			String custLocation) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.eMail = eMail;
		this.custPhone = custPhone;
		this.password = password;
		this.custLocation = custLocation;
	}

	public Customer(String custName, String eMail, String custPhone, String password, String custLocation) {
		super();
		this.custName = custName;
		this.eMail = eMail;
		this.custPhone = custPhone;
		this.password = password;
		this.custLocation = custLocation;
	}

	
}
