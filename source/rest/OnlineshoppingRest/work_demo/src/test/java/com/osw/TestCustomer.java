package com.osw;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.osw.customer.model.Address;
import com.osw.customer.model.Cart;
import com.osw.customer.model.Customer;
import com.osw.customer.model.Wishlist;
import com.osw.customer.repository.AddressRepo;
import com.osw.customer.repository.CustomerRepo;
import com.osw.customer.service.AddressService;
import com.osw.customer.service.CustomerService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestCustomer {
    
    @Autowired
    private CustomerRepo custrepo;
    
    @Autowired
    private CustomerService custservice;
    
    @Autowired
    private AddressService addressservice;
    
    @Autowired
    private AddressRepo addressrepo;
    

    
    @Test
    @Order(1)
    public void testCustomerRegistration() {
        Customer cust = new Customer();
        cust.setCustFirstName("Madhu");
        cust.setCustLastName("Madhu");
        cust.setCustEmail("madhu@gmail.com");
        cust.setCustPhone("8798798797");
        cust.setCustUsername();
        cust.setCustPassword("MadhuMadhu1$");
        cust.setCustConfirmPassword("MadhuMadhu1$");
        Cart cart = new Cart();
        cust.setCart(cart);
        Wishlist wishlist = new Wishlist();
        cust.setWishlist(wishlist);
        custservice.insertCustomer(cust);
        assertNotNull(custrepo.findById("CUST_0007").get());
    }
    
    
    @Test
    @Order(2)
    public void testCustomerLogin() {
        Customer cust=custservice.validUser("Madhu_Madhu","MadhuMadhu1$");        
    }
    
    @Test
    @Order(3)
    public void testPrintCustomer() {
        Customer cust = custrepo.findByCustUsername("Madhu_Madhu");
        assertEquals("8798798797",cust.getCustPhone());
    }
    
    @Test
    @Order(4)
    public void testUpdateCustomer() {
        Customer cust = custservice.printCustomer("CUST_0007");
        cust.setCustFirstName("Rachel");
        cust.setCustLastName("Green");
        cust.setCustEmail("rachel@gmail.com");
        cust.setCustPhone("9999999999");
        cust.setCustUsername();
        cust.setCustPassword("RachelGreen1$");
        cust.setCustConfirmPassword("RachelGreen1$");
        custservice.insertCustomer(cust);
        assertEquals("rachel@gmail.com",cust.getCustEmail());
        
    }
    
    @Test
    @Order(5)
    public void testPrintAllCustomers() {
        List<Customer> cust = custservice.allCustomer();
        assertThat(cust).size().isGreaterThan(0);
    }
    
    @Test
    @Order(6)
    public void testDeleteCustomer() {
        custservice.deleteCustomer("Cust_0007");
        assertThat(custrepo.existsById("CUST_0007")).isFalse();
    }
    
    @Test
    @Order(7)
    public void testAddAddress() {
        Address address = new Address();
        address.setName("Home");
        address.setHouseNo("1-1a");
        address.setStreet("skyer smt");
        address.setCity("skyer");
        address.setState("skyer");
        address.setCountry("sky");
        address.setPostalCode("567567");
        addressservice.insertAddress(address);
        assertNotNull(addressrepo.findById("ADDR_03").get());
    }
    
    @Test
    @Order(8)
    public void testPrintAddress() {
        Address address = addressrepo.findById("ADDR_03").get();
        assertEquals("sky",address.getCountry());
    }
        
    
    @Test
    @Order(9)
    public void testUpdateAddress() {
    Address address = addressrepo.findById("ADDR_03").get();
    address.setName("working");
    address.setHouseNo("1-1a");
    address.setStreet("skyer smt");
    address.setCity("skyer");
    address.setState("steen");
    address.setCountry("sky");
    address.setPostalCode("567567");
    addressservice.updateAddress(address);
    assertEquals("steen",address.getState());
    }
    
    
    
    @Test
    @Order(10)
    public void testPrintingAllAddress() {
        List<Address> addresslist = addressservice.allAddress();
        assertThat(addresslist).size().isGreaterThan(0);
    }
    
    @Test
    @Order(11)
    public void testDeleteAddress() {
        addressservice.deleteAddress("ADDR_03");
        assertThat(addressrepo.existsById("ADDR_03")).isFalse();
    }

}