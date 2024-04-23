package com.osw.order.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osw.customer.model.Address;
import com.osw.customer.model.CartLine;
import com.osw.customer.model.Customer;
import com.osw.product.model.Product;

@Entity
@Table(name="Orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
    private Customer customer;
	@OneToMany(mappedBy = "order",  orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JsonIgnore
    private List<CartLine> cartlines=new ArrayList<CartLine>();
   
    private String addressOrderId;
    private String status;
    private double orderGrandTotal;
    private int noOfOrderItems;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    @JsonIgnore
    private Payment payment;
	public int getId() {
		return id;
	}
	public void setOrderId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
public List<CartLine> getCartlines() {
		return cartlines;
	}
	public void setCartlines(List<CartLine> cartlines) {
		this.cartlines = cartlines;
	}
	//	public List<Product> getProductlist() {
//		return productlist;
//	}
//	public void setProductlist(List<Product> productlist) {
//		this.productlist = productlist;
//	}
	public double getOrderGrandTotal() {
		return orderGrandTotal;
	}
	public void setOrderGrandTotal(double orderGrandTotal) {
		this.orderGrandTotal = orderGrandTotal;
	}
	public int getNoOfOrderItems() {
		return noOfOrderItems;
	}
	public void setNoOfOrderItems(int noOfOrderItems) {
		this.noOfOrderItems = noOfOrderItems;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddressOrderId() {
		return addressOrderId;
	}
	public void setAddressOrderId(String addressOrderId) {
		this.addressOrderId = addressOrderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", cartlines=" + cartlines + ", addressOrderId="
				+ addressOrderId + ", status=" + status + ", orderGrandTotal=" + orderGrandTotal + ", noOfOrderItems="
				+ noOfOrderItems + ", payment=" + payment + "]";
	}
	
	
    
}
