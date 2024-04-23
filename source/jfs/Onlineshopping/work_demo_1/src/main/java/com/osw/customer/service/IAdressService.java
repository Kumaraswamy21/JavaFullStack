package com.osw.customer.service;

import java.util.List;

import com.osw.customer.model.Address;

public interface IAdressService {
	public void insertAddress(Address a);
	public void updateAddress(Address a);
	public void deleteAddress(String adressId);
	public Address printAddress(String addressId);
	public List<Address> allAddress();
}
