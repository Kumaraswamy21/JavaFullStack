package com.virtusa.eg.dto;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;






@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodId;
	private String prodName;
	private String prodType;
	private String prodBrand;
	private double prodPrice;
	private int prodQuantity;
	private String prodCode;
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getProdBrand() {
		return prodBrand;
	}
	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}
	public double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getProdQuantity() {
		return prodQuantity;
	}
	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public Product() {
		super();

	}
	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", prodType=" + prodType + ", prodBrand="
				+ prodBrand + ", prodPrice=" + prodPrice + ", prodQuantity=" + prodQuantity + ", prodCode=" + prodCode
				+ ", expiryDate=" + expiryDate + "]";
	}
	public Product( String prodName, String prodType, String prodBrand, double prodPrice, int prodQuantity,
			String prodCode, Date expiryDate) {
		
		
		this.prodName = prodName;
		this.prodType = prodType;
		this.prodBrand = prodBrand;
		this.prodPrice = prodPrice;
		this.prodQuantity = prodQuantity;
		this.prodCode = prodCode;
		this.expiryDate = expiryDate;
	}

	

}
