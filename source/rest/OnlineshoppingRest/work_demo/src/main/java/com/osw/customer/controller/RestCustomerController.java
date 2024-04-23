package com.osw.customer.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.osw.admin.model.Admin;
import com.osw.customer.model.Address;
import com.osw.customer.model.Cart;
import com.osw.customer.model.CartLine;
import com.osw.customer.model.Customer;
import com.osw.customer.model.Wishlist;
import com.osw.customer.service.AddressService;
import com.osw.customer.service.CartLineService;
import com.osw.customer.service.CartService;
import com.osw.customer.service.CustomerService;
import com.osw.customer.service.WishListService;
import com.osw.exceptions.AddressNotFoundexception;
import com.osw.exceptions.CartLinesNotFoundException;
import com.osw.exceptions.CategoryNotFoundException;
import com.osw.exceptions.CustomerNotFoundException;
import com.osw.exceptions.ProductNotFoundexception;
import com.osw.exceptions.UserNotFoundException;
import com.osw.order.model.CardDb;
import com.osw.order.model.Order;
import com.osw.order.model.Payment;
import com.osw.order.service.CardDbService;
import com.osw.order.service.OrderService;
import com.osw.order.service.PaymentService;
import com.osw.product.model.Category;
import com.osw.product.model.Product;
import com.osw.product.service.CategoryService;
import com.osw.product.service.ProductService;

@RestController
public class RestCustomerController {
	@Autowired
	private CustomerService custService;

	@Autowired
	private CartService cartService;

	@Autowired
	private WishListService wishlistService;

	@Autowired
	private AddressService addressservice;

	@Autowired
	private CategoryService catService;

	@Autowired
	private ProductService prodservice;

	@Autowired
	private CartLineService cartlineService;

	@Autowired
	private OrderService orderservice;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private CardDbService carddbservice;

Logger logger = LoggerFactory.getLogger(RestCustomerController.class);

@PostMapping("/customerRegisterRest")
public ResponseEntity<?> customerRegister1(@Valid @RequestBody Customer c, BindingResult br) {
	if (br.hasErrors()) {
		logger.warn("Validation failed");
// List<String> errors = br.getAllErrors().stream().map(e ->
// e.getDefaultMessage()).collect(Collectors.);
		return new ResponseEntity<String>("validation failed", HttpStatus.BAD_REQUEST);
	} else {
		if (c.getCustPassword().equals(c.getCustConfirmPassword())) {
			c.setCustUsername();
			Cart cart = new Cart();
			Wishlist wishlist = new Wishlist();
			c.setCart(cart);
			c.setWishlist(wishlist);
			cartService.insertCart(cart);
			wishlistService.insertWishlist(wishlist);
			custService.insertCustomer(c);

			logger.info("Customer with " + c.getCustId() + " is registered");

			System.out.println("Hi " + c.getCustFirstName() + " your username is " + c.getCustUsername());

			logger.info("customer username for " + c.getCustId() + "is generated");

			return new ResponseEntity<Customer>(c, HttpStatus.CREATED);		
			} else {

				logger.warn("customer password and confirm password is not equal");

				return new ResponseEntity<String>("customer password and confirm password is not equal",

						HttpStatus.BAD_REQUEST);

			}

	}
}

@GetMapping("/customerLoginRest/{custUsername}/{custPassword}")
public ResponseEntity<?> customerLogin(@PathVariable("custUsername") String username,
@PathVariable("custPassword") String password, HttpSession session, Model m) {
Customer c = custService.validUser(username, password);
if (c != null) {
logger.info("customer validation successful");
session.setAttribute("customersession", c);
List<Category> categorylist = catService.showAllCategories();
m.addAttribute("categorylist", categorylist);
return new ResponseEntity<Customer>(c, HttpStatus.CREATED);
} else {
logger.warn("Exception raised: Customer Not Found for Username " + username + " and password " + password);
throw new CustomerNotFoundException("Wrong credentials");

}
}


@PutMapping("customerLogoutRest")
public ResponseEntity<Object> customerLogout(HttpSession session) {
    Customer customer = (Customer) session.getAttribute("customersession");
    if (customer == null) {
        logger.warn("Exception raised: Customer Not Found ");
        throw new CustomerNotFoundException("Customer is not found");
    } else {
    session.removeAttribute("customersession");
    logger.info("customer is logged out");
    return new ResponseEntity<Object>("Customer session of id "+customer.getCustId()+" logged out",HttpStatus.FOUND);}
}





@GetMapping("/verifyCustomerRest/{custUsername}")
public ResponseEntity<?> validateCustUsername(@PathVariable("custUsername") String username, Model m) {
Customer c = custService.getByUsername(username);
if (c != null) {
logger.info("In forgot password customer username is verified");
m.addAttribute("customerId", c.getCustId());
return new ResponseEntity<Customer>(c, HttpStatus.CREATED);
} else {
logger.warn("Entered customer username is not found");
throw new CustomerNotFoundException("Wrong credentials");

}
}

@PutMapping("/forgotPasswordCustomerRest/{custPassword}/{confirmPassword}/{custId}")
public ResponseEntity<?> forgotPassword(@PathVariable("custPassword") String password,
@PathVariable("confirmPassword") String cpass, @PathVariable("custId") String custId) {
Customer c = custService.printCustomer(custId);
if(c==null) {
logger.warn("customer not found for id "+custId);
throw new CustomerNotFoundException("customer not found for id "+custId);
}else {
if (password.equals(cpass)) {
c.setCustPassword(password);
c.setCustConfirmPassword(cpass);
custService.updateCustomer(c);
logger.info("New password is set for customer");
return new ResponseEntity<Customer>(c, HttpStatus.OK);
} else {
logger.warn("While setting new password,Password and confirm password doen't match");
}

return new ResponseEntity<String>("customer password and confirm password is not equal",
HttpStatus.BAD_REQUEST);}
}

@GetMapping("/customerProfileRest")
public ResponseEntity<?> customerProfile(HttpSession session) throws UserNotFoundException {
Customer c = (Customer) session.getAttribute("customersession");

if (c == null) {
logger.warn("customer session not found");
throw new CustomerNotFoundException("customer session not found");

} else {
logger.info("In customer profile of customer " + c.getCustId());
return new ResponseEntity<Customer>(c, HttpStatus.FOUND); ///////////////
}
}

@GetMapping("/customerAddressRest")
public ResponseEntity<List<Address>> customerAddress(HttpSession session, Model m) {
Customer c = (Customer) session.getAttribute("customersession");
if (c == null) {
logger.warn("customer session not found");
throw new CustomerNotFoundException("customer session not found");

} else {
logger.info("In customer address list");
List<Address> addresslist = custService.addressListInCustomer(c.getCustId());
m.addAttribute("addresslist", addresslist);
if(addresslist==null) {
	throw new AddressNotFoundexception("Address not found for customer"+c.getCustId());
}
return new ResponseEntity<List<Address>>(addresslist, HttpStatus.FOUND);}/////////////////
}

@PostMapping("/addAddressRest")
public ResponseEntity<?> addAddress(@Valid @RequestBody Address a, BindingResult br, HttpSession session, Model m) {
logger.info("In customer add Address");
if (br.hasErrors()) {
logger.warn("Address validation failed");
return new ResponseEntity<String>("Address validation failed", HttpStatus.BAD_REQUEST);
} else {
Customer c = (Customer) session.getAttribute("customersession");
if (c == null) {
logger.warn("customer session not found");
throw new CustomerNotFoundException("customer session not found");

} else {
a.setCustomer(c);
addressservice.insertAddress(a);
logger.info("New Address of Id " + a.getAddressId() + " for " + c.getCustId() + " is added");
List<Address> addresslist = custService.addressListInCustomer(c.getCustId());
System.out.println(addresslist);
m.addAttribute("addresslist", addresslist);
return new ResponseEntity<List<Address>>(addresslist, HttpStatus.CREATED);}
}
}

@PutMapping("/updateCustomerRest")
public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer c, BindingResult br, HttpSession session,
Model m) {
if (br.hasErrors()) {
logger.warn("while updating customer address validation failed");
return new ResponseEntity<String>("while updating customer address validation failed",
HttpStatus.BAD_REQUEST);
} else {
Customer customer = (Customer) session.getAttribute("customersession");
if (c == null) {
logger.warn("customer session not found");
throw new CustomerNotFoundException("customer session not found");

} else {
customer.setCustFirstName(c.getCustFirstName());
customer.setCustLastName(c.getCustLastName());
customer.setCustUsername();
customer.setCustPhone(c.getCustPhone());
customer.setCustEmail(c.getCustEmail());
custService.updateCustomer(customer);
logger.info("Customer details for customer id " + customer.getCustId() + " are updated");
return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);}
}

}

@DeleteMapping("/deleteCustomerRest")
public ResponseEntity<?> deleteCustomer(HttpSession session) {
Customer c = (Customer) session.getAttribute("customersession");
if (c == null) {
logger.warn("customer session not found");
throw new CustomerNotFoundException("customer session not found");

} else {
System.out.println(c.getCustId());

custService.deleteCustomer(c.getCustId());
logger.info("customer account " + c.getCustId() + " is deleted");
return new ResponseEntity<String>("customer account "+c.getCustId()+" is deleted", HttpStatus.OK);}
}

@GetMapping("/singleAddresssRest/{addressId}")
public ResponseEntity<?> singleAddress(@PathVariable("addressId") String addressId, Model m, HttpSession session) {
Address address = addressservice.printAddress(addressId);
if(address==null) {
logger.warn("address not found for id "+addressId);
throw new AddressNotFoundexception("address not found for id "+addressId);
}else {
logger.info("In address details of" + addressId);
session.setAttribute("addresssession", address);
return new ResponseEntity<Address>(address, HttpStatus.OK);}

}
@PutMapping("/updateAddressRest")
public ResponseEntity<?> updateAddress(@RequestBody Address a, HttpSession session, Model m) {//////////////////
Customer customer = (Customer) session.getAttribute("customersession");
Address address = (Address) session.getAttribute("addresssession");
if (customer == null) {
logger.warn("customer session not found");
throw new CustomerNotFoundException("customer session not found");

} else if(address==null) {
logger.warn("address session not found");
throw new AddressNotFoundexception("address session not found");
}else {
address.setName(a.getName());
address.setHouseNo(a.getHouseNo());
address.setStreet(a.getStreet());
address.setCity(a.getCity());
address.setState(a.getState());
address.setCountry(a.getCountry());
address.setPostalCode(a.getPostalCode());
addressservice.updateAddress(address);
logger.info("Address details of " + address.getAddressId() + " is updated");

List<Address> addresslist = custService.addressListInCustomer(customer.getCustId());
System.out.println(addresslist);
m.addAttribute("addresslist", addresslist);
return new ResponseEntity<List<Address>>(addresslist, HttpStatus.CREATED);}
}

@GetMapping("/singleCategoryCustomerRest/{categoryId}")//////////////////////////////
public ResponseEntity<List<Product>> singleCategoryCustomer(@PathVariable("categoryId") String categoryId, Model m,
HttpSession session) {
Category c = catService.showCategoryById(categoryId);
if(c==null) {
logger.warn("Exception occured:category not found for id "+categoryId);
 throw new CategoryNotFoundException("category not found for id "+categoryId);
}else {
logger.info("In category " + c.getCategoryName());
session.setAttribute("categorysession", c);
m.addAttribute("productlist", c.getProductlist());
return new ResponseEntity<List<Product>>(c.getProductlist(), HttpStatus.CREATED); }////////////////////////

}

@GetMapping("/singleProductCustomerRest/{prodId}")
public ResponseEntity<?> singleProductCustomer(@PathVariable("prodId") String prodId, Model m, HttpSession session)
throws UserNotFoundException {
Product product = prodservice.findByProdId(prodId);
if (product == null) {
logger.warn("Exception occured:The product with id " + prodId + " is not found");
throw new ProductNotFoundexception( "The product with id " + prodId + " is not found");
}
session.setAttribute("productsession", product);
boolean wishliststatus = false;
Boolean cartStatus = false;
boolean availablestatus = true;
if (product.getProdQuantity() < 1) {
availablestatus = false;
product.setProdStatus(false);
prodservice.UpdateProductDetails(product);
}
System.out.println("singproduct customer avaialable status " + availablestatus);
session.setAttribute("availableStatus", availablestatus);
Customer customer = (Customer) session.getAttribute("customersession");
if (customer == null) {
session.setAttribute("wishlistStatus", wishliststatus);
session.setAttribute("cartStatus", cartStatus);
} else {
Wishlist wishlist = customer.getWishlist();

System.out.println("In single product customer");

if (wishlist.getWishlistProducts() != null) {
String[] elements = wishlist.getWishlistProducts().split(",");
System.out.println("products in wishlist" + elements);
List<String> products = Arrays.asList(elements);
System.out.println("list of products" + products);

if (products.contains(product))
wishliststatus = true;
}
session.setAttribute("wishlistStatus", wishliststatus);
Cart cart = customer.getCart();
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());

for (CartLine cl : cartlinelist) {
if (cl.getProduct().getProdId().equals(product.getProdId())) {
cartStatus = true;
break;
}
}
session.setAttribute("cartStatus", cartStatus);
}
return new ResponseEntity<Product>(product, HttpStatus.CREATED);
  

}

@GetMapping("/CustomerDetailsinAdminRest")
public ResponseEntity<?> CustomerAdmin(Model m) {
List<Customer> customerlist = custService.allCustomer();
logger.info("in customer details of admin");
m.addAttribute("customerlist", customerlist);
return new ResponseEntity<List<Customer>>(customerlist, HttpStatus.OK);

}

@GetMapping("/customerviewadminRest/{custId}")
public ResponseEntity<?> customerviewadmin(@PathVariable("custId") String custId, Model m, HttpSession session) {
Customer customer = custService.printCustomer(custId);
List<Address> addresslist = customer.getAddress();
m.addAttribute("addresslist", addresslist);
return new ResponseEntity<List<Address>>(addresslist, HttpStatus.OK);

}

@PostMapping("/addToCartRest")
public ResponseEntity<?> addToCart(Model m, HttpSession session) throws UserNotFoundException {
Product product = (Product) session.getAttribute("productsession");
Customer customer = (Customer) session.getAttribute("customersession");
if (customer == null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
} else {
Cart cart = customer.getCart();
CartLine cartline = new CartLine();
cartline.setProduct(product);
cartline.setProductCount(1);
cartline.setTotal(product.getProdPrice());
cartline.setCart(cart);
cartlineService.insertCart(cartline);
logger.info("Product " + product.getProdId() + " is added to cart " + cart.getCartId());
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
m.addAttribute("cartlinelist", cartlinelist);
double grandTotal = 0;
int totalItems = 0;
for (CartLine cl : cartlinelist) {
totalItems = totalItems + cl.getProductCount();
grandTotal = grandTotal + cl.getTotal();
}
cart.setNoOfItems(totalItems);
cart.setGrandTotal(grandTotal);
cartService.updateCart(cart);
return new ResponseEntity<Cart>(cart, HttpStatus.OK);
}
}

@GetMapping("/customercartRest")
public ResponseEntity<?> customercart(Model m, HttpSession session) throws UserNotFoundException {
Customer customer = (Customer) session.getAttribute("customersession");
if (customer == null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
} else {
Cart cart = customer.getCart();
logger.info("In cart of customer" + customer.getCustId());
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
m.addAttribute("cartlinelist", cartlinelist);
double grandTotal = 0;
int totalItems = 0;
for (CartLine cl : cartlinelist) {
totalItems = totalItems + cl.getProductCount();
grandTotal = grandTotal + cl.getTotal();
}
cart.setNoOfItems(totalItems);
cart.setGrandTotal(grandTotal);
cartService.updateCart(cart);

return new ResponseEntity<Cart>(cart, HttpStatus.OK);
}

}

@GetMapping("/singleProductCustomerCartRest/{prodId}")
public ResponseEntity<?> singleProductCustomerCart(@PathVariable("prodId") String prodId, Model m,
HttpSession session) {
Product product = prodservice.findByProdId(prodId);
if (product == null) {
throw new ProductNotFoundexception( "The product with id " + prodId + " is not found");
}
session.setAttribute("productsession", product);
Customer customer = (Customer) session.getAttribute("customersession");
if (customer == null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
} else {
session.setAttribute("wishlistStatus", false);
Cart cart = customer.getCart();
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
Boolean cartStatus = false;
for (CartLine cl : cartlinelist) {
if (cl.getProduct().getProdId().equals(product.getProdId())) {
cartStatus = true;
break;
}
}
session.setAttribute("cartStatus", cartStatus);
boolean availablestatus = true;
if (product.getProdQuantity() < 1) {
availablestatus = false;
product.setProdStatus(false);
prodservice.UpdateProductDetails(product);
}
session.setAttribute("availableStatus", availablestatus);
return new ResponseEntity<Product>(product, HttpStatus.OK);}

}

@GetMapping("/customerBrowseRest/{prodName}")
public ResponseEntity<?> customerBrowse(@PathVariable("prodName") String prodName, Model m) {
List<Product> productlist = prodservice.showAllProducts();
prodName = prodName.toLowerCase();
String[] a = prodName.split(" ");
List<String> slist = Arrays.asList(a);
List<Product> productListResult = new ArrayList<Product>();
for (String s : slist) {
for (Product p : productlist) {
String c = p.getProdCode().toLowerCase();
c = c + "_" + p.getCategory().getCategoryName().toLowerCase();
String[] b = c.split("_");
List<String> values = Arrays.asList(b);
if (values.contains(s)) {
if (!productListResult.contains(p)) {
productListResult.add(p);
}
}
}
}

logger.info("customer browsed for phrase " + prodName);
m.addAttribute("productlist", productListResult);
return new ResponseEntity<List<Product>>(productListResult, HttpStatus.FOUND);/////////////////

}

@PutMapping("/addQuantCartLineRest/{cartLineId}")
public ResponseEntity<?> addQuantCartLine(@PathVariable("cartLineId") int cartlineId, Model m,
HttpSession session) {

Customer customer = (Customer) session.getAttribute("customersession");
if (customer == null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
} else {
CartLine cartline = cartlineService.printCartLine(cartlineId);
if(cartline==null) {
logger.warn("Exception occured:cartline not found for "+cartlineId);
throw new CustomerNotFoundException("cartline not found for "+cartlineId);
}else {
cartline.setProductCount(cartline.getProductCount() + 1);
cartline.setTotal(cartline.getTotal() + cartline.getProduct().getProdPrice());

Cart cart = customer.getCart();
cartlineService.updateCart(cartline);
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
m.addAttribute("cartlinelist", cartlinelist);
Cart c = cartlineService.increaseCartQuant(cartlinelist);
cart.setGrandTotal(c.getGrandTotal());
cart.setNoOfItems(c.getNoOfItems());
cartService.updateCart(cart);
logger.info("the quantity for product " + cartline.getProduct() + " quantity is increased by 1");

return new ResponseEntity<Cart>(cart, HttpStatus.OK);}}

}

@PutMapping("/minusQuantCartLineRest/{cartLineId}")
public ResponseEntity<?> minusQuantCartLine(@PathVariable("cartLineId") int cartlineId, Model m,
HttpSession session) {
Customer customer = (Customer) session.getAttribute("customersession");
if (customer == null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
} else {
CartLine cartline = cartlineService.printCartLine(cartlineId);
if(cartline==null) {
logger.warn("Exception occured:cartline not found for "+cartlineId);
throw new CustomerNotFoundException("cartline not found for "+cartlineId);
}else {
if (cartline.getProductCount() - 1 > 0) {
cartline.setProductCount(cartline.getProductCount() - 1);
cartline.setTotal(cartline.getTotal() - cartline.getProduct().getProdPrice());

Cart cart = customer.getCart();
cartlineService.updateCart(cartline);
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
m.addAttribute("cartlinelist", cartlinelist);
Cart c = cartlineService.decreaseCartQuant(cartlinelist);
cartService.updateCart(cart);
logger.info("the quantity for product " + cartline.getProduct() + " quantity is decreased by 1");
return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
} else {
Cart cart = customer.getCart();
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
m.addAttribute("cartlinelist", cartlinelist);
m.addAttribute("message", "Quantity must be greater than 1");
return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);}}
}

}

@GetMapping("/productBrandRest/{prodBrand}")
public ResponseEntity<?> productBrand(@PathVariable("prodBrand") String prodBrand, Model m) {
List<Product> productlistName = prodservice.findByProdBrand(prodBrand);
m.addAttribute("productlist", productlistName);
logger.info("The products of brand " + prodBrand + " are displayed");
return new ResponseEntity<List<Product>>(productlistName, HttpStatus.OK);
}
@GetMapping("/setAddressAndPaymentRest/{address}/{payment}")
public ResponseEntity<?> setAddressAndPayment(@PathVariable("address") String addressId,
@PathVariable("payment") String paymentname, Model m, HttpSession session) throws UserNotFoundException {
System.out.println(addressId);
Address address = addressservice.printAddress(addressId);
if(address==null) {
logger.warn("Exception raised: Address Not Found for "+addressId);
throw new CustomerNotFoundException(" Address Not Found for "+addressId);
}
Customer customer = (Customer) session.getAttribute("customersession");
if (customer == null) {
logger.warn("Exception raised: Customer Not Found ");
throw new CustomerNotFoundException("Customer is not found");
} else {
Cart cart = customer.getCart();
System.out.println("In setiong order");
List<CartLine> cartlinelist = cartService.printCart(cart.getCartId()).getCartlines();
System.out.println("customer cart lines " + cartlinelist);
Order order = new Order();
order.setAddressOrderId(addressId);
order.setCartlines(cartlinelist);
cart.setCartlines(null);
cartService.updateCart(cart);
System.out.println("after removing mapping" + cart);
order.setCustomer(customer);
order.setNoOfOrderItems(cart.getNoOfItems());
order.setOrderGrandTotal(cart.getGrandTotal());
System.out.println("before setting payment " + order);
Payment payment = new Payment();
payment.setPaymentAmount(cart.getGrandTotal());
if (paymentname.equals("cod")) {
payment.setPaymentType("cod");
System.out.println("in cod");
paymentService.insertPayment(payment);
order.setPayment(payment);
order.setStatus("order placed");
orderservice.insertOrder(order);
logger.info("Order " + order.getId() + " for address " + address.getName() + " of payment type cod is confirmed");
for (CartLine cl : cartlinelist) {
cl.setOrder(order);
cl.setCart(null);
Product p = cl.getProduct();
p.setProdQuantity(p.getProdQuantity() - 1);
prodservice.UpdateProductDetails(p);
cartlineService.updateCart(cl);
}

List<Order> myOrders = custService.printCustomer(customer.getCustId()).getOrder();
myOrders.add(order);
customer.setOrder(myOrders);

return new ResponseEntity<String>(
"Order "+order.getId()+" for address "+address.getName()+" of payment type cod is confirmed ",
HttpStatus.OK);
} else if (paymentname.equals("online")) {
session.setAttribute("ordersession", order);
session.setAttribute("paymentsession", payment);
logger.info(
"Order " + order.getId() + " for address " + address + " of payment type Online is forwarded");
m.addAttribute("checkValid", new CardDb());
return new ResponseEntity<String>(
"Order "+order.getId()+" for address "+address+" of payment type Online is forwarded",
HttpStatus.OK);
}
}

return new ResponseEntity<String>("Order cannot be placed!", HttpStatus.OK);
}

@GetMapping("/myOrdersRest")
public ResponseEntity<?> myOrders(HttpSession session, Model m) throws UserNotFoundException {
Customer customer = (Customer) session.getAttribute("customersession");
if (customer == null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
} else {
List<Order> myOrders = custService.printCustomer(customer.getCustId()).getOrder();
System.out.println("in myorders" + myOrders);
m.addAttribute("orderlist", myOrders);
logger.info("In orders page of customer " + customer.getCustId());
return new ResponseEntity<List<Order>>(myOrders, HttpStatus.OK);
}
}

@PutMapping("/removeFromCartRest/{cartLineId}")
public ResponseEntity<?> removeFromCart(@PathVariable("cartLineId") int cartlineId, HttpSession session, Model m) {
Customer customer = (Customer) session.getAttribute("customersession");
if(customer==null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
}
CartLine cartline = cartlineService.printCartLine(cartlineId);
if (cartline == null) {
logger.warn("cartline " + cartline.getId() + " is not found");
throw new CartLinesNotFoundException("cartline is not found to remove");
}
cartlineService.deleteCart(cartlineId);
Cart cart = customer.getCart();
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
m.addAttribute("cartlinelist", cartlinelist);

logger.info("cartline " + cartline.getId() + " is removed from cart " + cart.getCartId());
return new ResponseEntity<String>("cartline " + cartline.getId() + " is removed from cart " + cart.getCartId(),
HttpStatus.OK);
}

@GetMapping("/buyCartLinesRest")
public ResponseEntity<?> buyCartLines(HttpSession session, Model m) {
System.out.println("in buycartlines");
Customer customer = (Customer) session.getAttribute("customersession");
if(customer==null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
}
Cart cart = customer.getCart();
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
if (cartlinelist.size() == 0) {
logger.warn("No product is found in cartline to order");
throw new CartLinesNotFoundException("You have not added any products:Add products");
}
System.out.println("in buy cartlines" + cartlinelist);
m.addAttribute("cartlinelist", cartlinelist);
List<Address> addresslist = customer.getAddress();
if (addresslist.size() == 0) {
logger.warn("No address is found to set order");
throw new AddressNotFoundexception("You have not added address: Add Address");
} else {
m.addAttribute("addresslist", addresslist);
System.out.println(customer.getAddress());
return new ResponseEntity<List<CartLine>>(cartlinelist, HttpStatus.OK);
}
}

@GetMapping("/checkCardValidityRest")
public ResponseEntity<?> checkCardValidity(@Valid @RequestBody CardDb cd, BindingResult br, HttpSession session,
Model m) {
if (br.hasErrors()) {
logger.warn("card validation is failed");
return new ResponseEntity<String>("card validation is failed", HttpStatus.BAD_REQUEST);
} else {
System.out.println("month " + cd.getExpiryMonth());
if (carddbservice.validCard(cd.getCardHolderName(), cd.getCardNumber(), cd.getCardCVV(),
cd.getExpiryMonth(), cd.getCardType())) {
System.out.println("After validation");
Order order = (Order) session.getAttribute("ordersession");
Payment payment = (Payment) session.getAttribute("paymentsession");
System.out.println("Order " + order);
System.out.println("Payment " + payment);
payment.setPaymentType("online");
System.out.println("in online");
paymentService.insertPayment(payment);
order.setPayment(payment);
order.setStatus("order placed");
orderservice.insertOrder(order);
Customer customer = (Customer) session.getAttribute("customersession");
Cart cart = customer.getCart();
List<CartLine> cartlinelist = cartService.printCart(cart.getCartId()).getCartlines();
System.out.println("In online cartlines" + cartlinelist);
for (CartLine cl : cartlinelist) {
cl.setOrder(order);
cl.setCart(null);
Product p = cl.getProduct();
p.setProdQuantity(p.getProdQuantity() - 1);
prodservice.UpdateProductDetails(p);
cartlineService.updateCart(cl);

}
List<Order> myOrders = custService.printCustomer(customer.getCustId()).getOrder();
myOrders.add(order);
customer.setOrder(myOrders);
logger.info(
"customer " + customer.getCustId() + " places order " + order.getId() + " by online payment");
return new ResponseEntity<String>(
"customer " + customer.getCustId() + " places order " + order.getId() + " by online payment",
HttpStatus.OK);
}
logger.warn("Entered card details are wrong");
return new ResponseEntity<String>(" Entered card details are wrong ", HttpStatus.OK);
}
}

@PutMapping("/addToWishlistRest")
public ResponseEntity<?> addToWishlist(HttpSession session) throws UserNotFoundException {
Product product = (Product) session.getAttribute("productsession");
Customer customer = (Customer) session.getAttribute("customersession");
if(customer==null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
}else {
Wishlist wishlist = customer.getWishlist();
System.out.println("In add to wishlist" + wishlist);
String p;
if (wishlist.getWishlistProducts() == null)
p = product.getProdId();
else
p = wishlist.getWishlistProducts() + "," + product.getProdId();
wishlist.setWishlistProducts(p);
wishlistService.updateWishlist(wishlist);
System.out.println("After updating product string " + customer.getWishlist().getWishlistProducts());
session.setAttribute("wishlistStatus", true);
Cart cart = customer.getCart();
List<CartLine> cartlinelist = cartlineService.allCartLinesOfCart(cart.getCartId());
Boolean cartStatus = false;
for (CartLine cl : cartlinelist) {
if (cl.getProduct().getProdId().equals(product.getProdId())) {
cartStatus = true;
break;
}
}
session.setAttribute("cartStatus", cartStatus);
logger.info("Product " + product.getProdId() + " is added to wishlist of customer" + customer.getCustId());
return new ResponseEntity<String>(
" Product " + product.getProdId() + " is added to wishlist of customer" + customer.getCustId(),
HttpStatus.OK);
}
}
@PutMapping("removeFromWishlistRest")
    public ResponseEntity<Object> removeFromWishlist(HttpSession session) {
        Product product=(Product)session.getAttribute("productsession");
        Customer customer=(Customer)session.getAttribute("customersession");
        if(customer==null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
}else if(product==null){
                logger.warn("Exception occured :product session not found");
                throw new CustomerNotFoundException("product session not found");
            }else {
        Wishlist wishlist=customer.getWishlist();
        System.out.println("In remove from wishlist"+wishlist);
        String[] elements = wishlist.getWishlistProducts().split(",");
        System.out.println("products in wishlist"+elements);
        List<String> products = Arrays.asList(elements);
        ArrayList<String> Products = new ArrayList<String>(products);
        System.out.println("list of products"+Products);
        Products.remove(product.getProdId());
        System.out.println("After deleting list of products"+Products);
        String string = String.join(",", Products);
        System.out.println("After deleting String"+string);
        if(string.equals(""))
            wishlist.setWishlistProducts(null);
        else
            wishlist.setWishlistProducts(string);
        wishlistService.updateWishlist(wishlist);
        System.out.println("After deleting product string in wishlist"+wishlist.getWishlistProducts());
        session.setAttribute("wishlistStatus", false);
         Cart cart=customer.getCart();
         List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
         Boolean cartStatus=false;
          for(CartLine cl:cartlinelist) {
              if(cl.getProduct().getProdId().equals(product.getProdId())) {
                  cartStatus=true;
                  break;
              }
          }
          logger.info("Product "+product.getProdId()+" is removed from wishlist of customer"+customer.getCustId());
         session.setAttribute("cartStatus", cartStatus);
         return new ResponseEntity<Object>("Product "+product.getProdId()+" is removed from wishlist of customer"+customer.getCustId(),HttpStatus.FOUND);}
    }


  @GetMapping("customerwishlistRest")
    public ResponseEntity<List<Product>> customerwishlistRest(HttpSession session,Model m) throws UserNotFoundException {
        Customer customer=(Customer)session.getAttribute("customersession");
        if(customer==null) {
logger.warn("Exception occured:customer not logged in");
throw new CustomerNotFoundException("customer session not found");
}else {
                logger.info("In customer wishlist of customer "+customer.getCustId());
        Wishlist wishlist=customer.getWishlist();
        System.out.println("In wishlist"+wishlist);
        List<Product> productlist=new ArrayList<Product>();
        if(wishlist.getWishlistProducts()!=null) {
            String[] elements = wishlist.getWishlistProducts().split(",");
        System.out.println("products in wishlist"+elements);
        List<String> products = Arrays.asList(elements);
        System.out.println("list in wishlist"+products);

        for(String s:products) {
            if(prodservice.findByProdId(s)!=null) {
                Product p=prodservice.findByProdId(s);
                productlist.add(p);
            }
        }
        }
        System.out.println("In wishlist productlist"+productlist);
        m.addAttribute("productlist",productlist);
        return new ResponseEntity<List<Product>>(productlist,HttpStatus.FOUND);}
    }


    @GetMapping("newArrivalsRest")
    public ResponseEntity<List<Product>> newArrivals(Model m) throws ParseException {
        List<Product> productlist=prodservice.findNewlyArrived(new SimpleDateFormat("yyyy-MM-dd").parse("2022-10-01"));
        logger.info("new arrival products");
        m.addAttribute("productlist", productlist);
        return new ResponseEntity<List<Product>>(productlist,HttpStatus.FOUND);
    }


  @GetMapping("highlyRatedProductsRest")
    public ResponseEntity<List<Product>> highlyRatedProducts(Model m) {
       List<Product> productlist=prodservice.findHighlyRated();
        logger.info("Products rating greater than 3");
       m.addAttribute("productlist", productlist);
       return new ResponseEntity<List<Product>>(productlist,HttpStatus.FOUND);
    }

    @GetMapping("below999ProductsRest")
    public ResponseEntity<List<Product>> below999Products(Model m) {
       List<Product> productlist=prodservice.findBelow999();
        logger.info("Products cost less than 1000");
       m.addAttribute("productlist", productlist);
       return new ResponseEntity<List<Product>>(productlist,HttpStatus.FOUND);
    }
@PutMapping("/customerlogoutRest") 
public ResponseEntity<?> adminlogout(HttpSession session) { 
	  Customer customer=(Customer)session.getAttribute("customersession");
	  if(customer==null) {
logger.warn("customersession not found"); 
return new ResponseEntity<String>("customer session not found",HttpStatus.BAD_REQUEST);
}else { 
	session.invalidate(); 
	return new ResponseEntity<String>("customer "+customer.getCustId()+" is logged out",HttpStatus.OK); 
} 
}
}