package com.virtusa.eg.service;

import java.sql.SQLException;

public interface SupplierService {
public void addSupplier();
public boolean supplierLogin();
public void viewSupplier();
public void updateSupplier() throws SQLException;
public void viewOrders();
public void deleteSupplier(int id);
public void upadteOrderStatus();
}
