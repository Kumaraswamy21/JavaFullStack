package com.osw.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.customer.model.Address;
import com.osw.customer.repository.AddressRepo;
@Service
public class AddressService implements IAdressService {
	@Autowired
	private AddressRepo addressRepo;
	@Override
	public void insertAddress(Address a) {
		// TODO Auto-generated method stub
		addressRepo.save(a);
		
	}

	@Override
	public void updateAddress(Address a) {
		// TODO Auto-generated method stub
		addressRepo.save(a);
	}

	@Override
	public void deleteAddress(String adressId) {
		// TODO Auto-generated method stub
		System.out.println("In service"+adressId);
		addressRepo.deleteById(adressId);
	}

	@Override
	public Address printAddress(String addressId) {
		// TODO Auto-generated method stub
		return addressRepo.findById(addressId).get();
	}

	@Override
	public List<Address> allAddress() {
		// TODO Auto-generated method stub
		
		return addressRepo.findAll();
	}
	
	
}
