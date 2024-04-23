package com.osw.customer.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osw.customer.model.Customer;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import com.osw.model.sequence.StringPrefixedSequenceIdGenerator;
import com.osw.order.model.Order;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Address{
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE.AUTO,generator = "address_seq")
    @GenericGenerator(name="address_seq", 
            strategy = "com.osw.model.sequence.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name=StringPrefixedSequenceIdGenerator.INCREMENT_PARAM,value="1"),
                    @Parameter(name=StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER,value="ADDR_"),
                    @Parameter(name=StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,value="%02d")
            }
            )
    private String addressId;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Customer customer;
  
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp ="^[1-9]\\d*(?:[-\\s]?\\w+)?$",message="should contain '-',digits and alphabets ")
    private String houseNo;
    @NotBlank
    private String street;
   
   

	@NotBlank
    private String city;

    
    @NotBlank(message = "Please Enter City!")
    private String state;

    
    @NotBlank(message = "Please Enter Country!")
    private String country;

   
    @NotBlank(message = "Please Enter Postal Code!")
    @Pattern(regexp ="^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$",message="should not start with zero and should contain only 6 digits")  
    private String postalCode;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddressId() {
		return addressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String getHouseNo() {
		return houseNo;
	}


	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", customer=" + customer + ", houseNo=" + houseNo + ", street="
				+ street + ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode
				+ "]";
	}

    

    
}