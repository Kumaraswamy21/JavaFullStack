package com.osw.customer.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @OneToOne(mappedBy = "cart",  orphanRemoval = true)
    @JsonIgnore
    private Customer customer;
    @OneToMany(mappedBy = "cart")
	@LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JsonIgnore
    private List<CartLine> cartlines=new ArrayList<CartLine>();
    
    private double grandTotal;
    private int noOfItems;
	public int getCartId() {
		return cartId;
	}
	public void setId(int id) {
		this.cartId = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}
	
	
	
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public List<CartLine> getCartlines() {
		return cartlines;
	}
	public void setCartlines(List<CartLine> cartlines) {
		this.cartlines = cartlines;
	}
	
	
    
   


}