package com.osw;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.osw.customer.model.Address;
import com.osw.customer.model.Cart;
import com.osw.customer.model.CartLine;
import com.osw.customer.model.Customer;
import com.osw.customer.model.Wishlist;
import com.osw.customer.repository.CartLineRepo;
import com.osw.customer.repository.CartRepo;
import com.osw.customer.repository.CustomerRepo;
import com.osw.customer.repository.WishlistRepo;
import com.osw.customer.service.CartLineService;
import com.osw.customer.service.CartService;
import com.osw.customer.service.CustomerService;
import com.osw.customer.service.WishListService;
import com.osw.order.model.Payment;
import com.osw.order.service.CardDbService;
import com.osw.product.model.Product;
import com.osw.product.repository.ProductRepo;
import com.osw.product.service.ProductService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestProduct {

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
    
    
    @Test
    @Order(1)
    public void testAddProduct() throws ParseException {
        Product prod= new Product();
        prod.setProdName("shoes");
        prod.setProddate();
        prod.setProdPrice(2000);        
        prod.setDescription("comfortable to walk");
        prod.setProdBrand("puma");
        prod.setProdType("doublehop");
        prod.setProdCode();
        prod.setProdRating(4);
        prod.setProdQuantity(200);
        prod.setProdImage("../img/shoesProduct.jpg");
        prod.setProdStatus(true);
        prodservice.addProduct(prod);
        assertNotNull(prodrepo.findById("PROD_000004").get());
        
    }
    
    @Test
    @Order(2)
    public void testPrintSingleProduct() {
    Product  prod=prodservice.findByProdId("PROD_000004");
    assertEquals("doublehop",prod.getProdType());
    
    }
    
    @Test
    @Order(3)
    public void testUpdateProdut() throws ParseException {
    Product prod=prodservice.findByProdId("PROD_000004");
    prod.setProdName("shoes");
    prod.setProddate();
    prod.setProdPrice(2000);        
    prod.setDescription("comfortable to walk");
    prod.setProdBrand("puma");
    prod.setProdType("skyer");
    prod.setProdCode();
    prod.setProdRating(4);
    prod.setProdQuantity(200);
    prod.setProdImage("../img/shoesProduct.jpg");
    prod.setProdStatus(true);
    prodservice.UpdateProductDetails(prod);
    assertEquals("skyer",prod.getProdType());
    }
    
        
    
    @Test
    @Order(4)
    public void testPrintingAllProducts() {
        List<Product> prod=prodservice.showAllProducts();
        assertThat(prod).size().isGreaterThan(0);
    }
    
    @Test
    @Order(5)
    public void testDisplayByProdPrice() {
        Product prod=prodservice.findByProdId("PROD_000004");
        double price =prod.getProdPrice();
        assertEquals(2000,price);
       
    }
    
    @Test
    @Order(6)
    public void testDisplayByProdBrand() {
        Product prod=prodservice.findByProdId("PROD_000004");
        String brand=prod.getProdBrand();
        assertEquals("puma",brand);
    }
    
    @Test
    @Order(7)
    public void testDisplayByProdType() {
        Product prod=prodservice.findByProdId("PROD_000004");
        String type=prod.getProdType();
        assertEquals("skyer", type);
    }
    
    
    
    @Test
    @Order(8)
    public void testFindingHighlyRatedProd() {
        List<Product> prodlist=prodrepo.findByProdRatingGreaterThan(3);
        for(Product p:prodlist) {
            assertTrue(p.getProdRating()>3);
        break;
        }
        
    }
    
    
    
    @Test
    @Order(9)
    public void testFindByProdPriceLessThan() {
        List<Product> prodlist=prodrepo.findByProdPriceLessThan(1000);
         for(Product p:prodlist) {
             assertTrue(p.getProdPrice()<4000); 
                break;
         }    
    }
    
    @Test
    @Order(10)
    public void testDeleteProduct() {
        prodservice.deleteProduct("PROD_000004");
        assertThat(prodrepo.existsById("PROD_000004")).isFalse();
    }
    @Test
    @Order(11)
    public void testAddCartline() {
    CartLine cartline = new CartLine();
    cartline.setProductCount(2);
    Product product = prodservice.findByProdId("PROD_000003");
    cartline.setProduct(product);
    cartline.setBuyingPrice(2000);
    cartline.setTotal(4000);
    cartline.setAvailable(true);
    Cart cart = cartservice.printCart(1);
    cartline.setCart(cart);
    cartlineservice.insertCart(cartline);
    assertNotNull(cartlinerepo.findById(5).get());
    }
    
    @Test
    @Order(12)
    public void testPrintCartline() {
        Product prod = prodservice.findByProdId("PROD_000003");
        String p=prod.getProdId();
        CartLine cartline = cartlineservice.printCartLine(5);
        Product prod1=cartline.getProduct();
        assertEquals(p,prod1.getProdId());
    }
    
    @Test
    @Order(13)
    public void testPrintAllCartLines() {
        List<CartLine> cartlinelist = cartlineservice.printAllCarts();
        Product p = prodservice.findByProdId("PROD_000003");
        cartlinelist.contains(p);
    } 
    
    @Test
    @Order(14)
    public void testDeleteCartline() {
        cartlinerepo.deleteById(14);
    assertThat(cartlinerepo.existsById(5)).isFalse();
    }
    
    @Test
    @Order(15)
    public void testRemoveProductFromCart() {
        Customer cust = custrepo.findById("CUST_0001").get();
        Product prod = prodservice.findByProdId("PROD_000003");
         Cart cart=cust.getCart();
         List<CartLine> cartlinelist=cartlineservice.allCartLinesOfCart(cart.getCartId());
         cartlinelist.remove(prod);
         for(CartLine cl:cartlinelist) {
             Product prod1=cl.getProduct();
             assertNotSame(prod,prod1);
       break;
    } 
    }
    
    @Test
    @Order(16)
    public void testAddProductToCart() {
        Customer cust = custrepo.findById("CUST_0001").get();
        Product prod = prodservice.findByProdId("PROD_000003");
         Cart cart=cust.getCart();
          cart =cartservice.printCart(1);
         CartLine cartline=new CartLine();
         cartline.setProduct(prod);
         cartline.setProductCount(1);
         cartline.setCart(cart);
         cartlineservice.insertCart(cartline);
         List<CartLine> cartlinelist=cartlineservice.allCartLinesOfCart(cart.getCartId());
    for(CartLine cl:cartlinelist) {
                assertSame(cl.getProduct(),prod);                 
       break;
    }
    }
    
    
    @Test
    @Order(17)
    public void testAddProductToWishList() {
        Customer cust = custrepo.findById("CUST_0001").get();
        Wishlist  wishlist  =cust.getWishlist();
        Product prod = prodservice.findByProdId("PROD_000004");
        wishlist.setWishlistProducts(prod.getProdId());
        wishlistservice.updateWishlist(wishlist);
        assertSame(prod.getProdId(),wishlist.getWishlistProducts());    
    }
    
    @Test
    @Order(18)
    public void testRemoveProductFromWishlist() {
        Customer cust = custrepo.findById("CUST_0001").get();
        Product prod = prodservice.findByProdId("PROD_000004");
        Wishlist  wishlist  =cust.getWishlist();
         String[] elements = wishlist.getWishlistProducts().split(",");
         List<String> products = Arrays.asList(elements);
         ArrayList<String> Products = new ArrayList<String>(products);
         Products.remove(prod.getProdId());
         String string = String.join(",", Products);
         if(string.equals(""))
             wishlist.setWishlistProducts(null);
         else
             wishlist.setWishlistProducts(string);
         wishlistservice.updateWishlist(wishlist);     
         assertNotSame(wishlist.getWishlistProducts(),prod.getProdId());
    }
    @Test
    @Order(19)/////cartlinelist
     public void testAllCartLinesofCart(){
        
        List<CartLine> allList=cartlineservice.allCartLinesOfCart(1);
        CartLine cl = cartlineservice.printCartLine(8);
         for(CartLine cartline:allList) {
             int cartlineid = cartline.getId();
             assertEquals(cartlineid,cl.getId());
             break;
         }
                
    }
    
    
}