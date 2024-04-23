package com.virtusa.eg.service;

import java.sql.SQLException;

import com.virtusa.eg.exceptions.InvalidCredentialsException;

public interface CustomerService {
public void registerCustomer() throws SQLException;
public boolean customerLogin() throws InvalidCredentialsException;
public void updateCustomer() throws SQLException;
public void viewCustomer();
public void deleteCustomer();


public void placeOrder();
public void getInvoice();
}
