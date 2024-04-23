package com.osw.customer.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import com.osw.model.sequence.StringPrefixedSequenceIdGenerator;
import com.osw.order.model.Order;
import com.osw.validations.MobileNumber;
import com.osw.validations.Password;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osw.customer.model.Cart;
@Entity
public class Customer{
	 @Id
	    @GeneratedValue(strategy =GenerationType.SEQUENCE.AUTO,generator = "customer_seq")
	    @GenericGenerator(name="customer_seq", 
	            strategy = "com.osw.model.sequence.StringPrefixedSequenceIdGenerator",
	            parameters = {
	                    @Parameter(name=StringPrefixedSequenceIdGenerator.INCREMENT_PARAM,value="1"),
	                    @Parameter(name=StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER,value="CUST_"),
	                    @Parameter(name=StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,value="%04d")
	            }
	            )
	private String custId;
	private String custUsername;
	@NotBlank
	@Size(min=3,max=20,message="name should be between 3 and 20")
	private String custFirstName;
	@NotBlank
	@Size(min=3,max=20,message="name should be between 3 and 20")
	private String custLastName;
	@NotBlank
	@Email
	private String custEmail;
	@NotBlank
	@MobileNumber
	private String custPhone;
	@NotBlank
	@Password
	private String custPassword;
	@NotBlank
	@Password
	private String custConfirmPassword;
	@OneToMany(mappedBy = "customer",  orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.SAVE_UPDATE)
	@JsonIgnore
	private List<Address> address;
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private Cart cart;
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private Wishlist wishlist;
	@OneToMany(mappedBy = "customer",  orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.SAVE_UPDATE)
	@JsonIgnore
	private List<Order> order;
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustUsername() {
		return custUsername;
	}
	public void setCustUsername() {
		this.custUsername =custFirstName+"_"+custLastName ;
	}
	public String getCustFirstName() {
		return custFirstName;
	}
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}
	public String getCustLastName() {
		return custLastName;
	}
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Wishlist getWishlist() {
		return wishlist;
	}
	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public String getCustConfirmPassword() {
		return custConfirmPassword;
	}
	public void setCustConfirmPassword(String custConfirmPassword) {
		this.custConfirmPassword = custConfirmPassword;
	}
	public void setCustUsername(String custUsername) {
		this.custUsername = custUsername;
	}
	
	
	 
}