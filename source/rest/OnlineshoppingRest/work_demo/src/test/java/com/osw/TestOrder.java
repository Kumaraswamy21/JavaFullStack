package com.osw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.osw.customer.model.Address;
import com.osw.customer.model.Cart;
import com.osw.customer.model.CartLine;
import com.osw.customer.model.Customer;
import com.osw.customer.repository.CartLineRepo;
import com.osw.customer.repository.CartRepo;
import com.osw.customer.repository.CustomerRepo;
import com.osw.customer.repository.WishlistRepo;
import com.osw.order.repository.OrderRepo;
import com.osw.order.repository.PaymentRepo;
import com.osw.customer.service.CartLineService;
import com.osw.customer.service.CartService;
import com.osw.customer.service.CustomerService;
import com.osw.customer.service.WishListService;
import com.osw.order.model.Order;
import com.osw.order.model.Payment;
import com.osw.order.service.OrderService;
import com.osw.order.service.PaymentService;
import com.osw.order.service.CardDbService;
import com.osw.product.repository.ProductRepo;
import com.osw.product.service.ProductService;

public class TestOrder {
	@Autowired
    private ProductService prodservice;
    
    
    @Autowired
    private ProductRepo prodrepo;
    
    @Autowired
    private CartRepo cartrepo;
    
    @Autowired
    private CartService cartservice;
    
    @Autowired
    private CartLineRepo cartlinerepo;
    
    @Autowired
    private CartLineService  cartlineservice;
    
    
    @Autowired
    private CustomerRepo custrepo;
    
    @Autowired
    private CustomerService custservice;
    
    @Autowired
    private WishlistRepo wishlistrepo;
    
    @Autowired
    private WishListService wishlistservice;
    
    @Autowired
    private CardDbService cardservice;
    @Autowired 
    private OrderService orderservice;
    
    @Autowired
    private OrderRepo orderrepo;
    @Autowired
    private PaymentService paymentservice;
    @Autowired
    private PaymentRepo paymentrepo;
    
	@Test
    public void testPlaceOrder() { 
        Customer cust = custservice.printCustomer("CUST_0001");
        List<Address> address = cust.getAddress();        
        Order order = new Order();
         for(Address address1:address) {
             if(address1.getAddressId().equals("ADDR_01")) {
                 order.setAddressOrderId(address1.getAddressId());
             }
         }
        order.setCustomer(cust);
        Cart cart=cust.getCart();
         List<CartLine> cartlinelist=cartlineservice.allCartLinesOfCart(cart.getCartId());
        order.setCartlines(cartlinelist);
        order.setNoOfOrderItems(cartlinelist.size());
        orderservice.insertOrder(order);
        assertNotNull(orderrepo.findById(3));
    }
    
    @Test
    public void testPayment() {        
        Payment payment = new Payment();
        Order order= orderservice.printOrder(3);
        payment.setOrder(order);
        payment.setPaymentAmount(order.getOrderGrandTotal());
        payment.setPaymentType("cod");
        paymentservice.insertPayment(payment);
      Payment payment1=paymentservice.printPayment(4);
        order.setPayment(payment1);
        orderservice.updateOrder(order);
        assertNotNull(paymentrepo.findById(4));
    }

    @Test
    public void testCardValidation() {
        boolean card = cardservice.validCard("Phoebe Buffay","123456789012","987","2027-07","credit");
       assertEquals(true,card);
    }
}
