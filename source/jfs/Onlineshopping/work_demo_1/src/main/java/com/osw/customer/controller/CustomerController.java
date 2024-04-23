package com.osw.customer.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
public class CustomerController {
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
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@RequestMapping("customer")
    public String customer() {
		logger.info("In customer module");
        return "customer/customerWelcome";
    }
	@RequestMapping("customerLoginController")
    public String customerLoginController(Model m) {
		logger.info("In customer Login page");
		m.addAttribute("customerAttribute", new Customer());
        return "customer/customerloginpage";
    }
	@RequestMapping("customerSignup")
    public String customerSignup(Model m) {
		m.addAttribute("customerAttribute", new Customer());
		logger.info("In customer signup page");
        return "customer/customerregisterpage";
    }
	@RequestMapping("customerHome")
    public String customerHome(HttpSession session,Model m) {
		Customer customer=(Customer)session.getAttribute("customersession");
		logger.info("In customer home");
		if(customer==null)
			logger.warn("customer not logged in");
		else
			logger.info("customer "+customer.getCustId() +"logged in ");
		List<Category> categorylist=catService.showAllCategories();
		m.addAttribute("categorylist", categorylist);
		 return "customer/customerhomepage";
    }
	@RequestMapping("customerRegister")
    public String customerRegister(@Valid @ModelAttribute("customerAttribute")Customer c,BindingResult br) {
		if(br.hasErrors()) {
			logger.warn("Validation failed");
			return "customer/customerregisterpage";
		}else {
			if(c.getCustPassword().equals(c.getCustConfirmPassword())) {
		c.setCustUsername();
		Cart cart=new Cart();
		Wishlist wishlist=new Wishlist();
		c.setCart(cart);
		c.setWishlist(wishlist);
		cartService.insertCart(cart);
		wishlistService.insertWishlist(wishlist);
		custService.insertCustomer(c);
		logger.info("Customer with "+c.getCustId()+" is registered");
		System.out.println("Hi "+c.getCustFirstName()+" your username is "+c.getCustUsername());
		logger.info("customer username for "+c.getCustId()+"is generated");
        return "customer/customerloginpage";}
			else {
				logger.warn("customer password and confirm password is not equal");
				return "customer/customerregisterpage";
			}
		}
    }
	@RequestMapping("customerLogin")
    public String customerLogin( @RequestParam("custUsername") String username, @RequestParam("custPassword") String password,HttpSession session,Model m) {
		Customer c=custService.validUser(username, password);
		if(c!=null) {
			logger.info("customer validation successful");
			session.setAttribute("customersession", c);
			List<Category> categorylist=catService.showAllCategories();
			m.addAttribute("categorylist", categorylist);
			 return "customer/customerhomepage";
		}
		else {
			logger.warn("Exception raised: Customer Not Found for Username "+username+" and password "+password);
			throw new CustomerNotFoundException("Wrong credentials");
		}
		}
	
	@RequestMapping("validateCustUsername")
    public String validateCustUsername() {
        return "customer/validateCustomer";
    }
	@RequestMapping("verifyCustomer")
    public String validateCustUsername(@RequestParam("custUsername") String username,Model m) {
		Customer c=custService.getByUsername(username);
		if(c!=null) {
			logger.info("In forgot password customer username is verified");
			m.addAttribute("customerId", c.getCustId());
			return "customer/setPassword";
		}
		else
			logger.warn("Entered customer username is not found");
        return "customer/validateCustomer";
    }
	@RequestMapping("forgotPasswordCustomer")
    public String forgotPassword(@RequestParam("custPassword") String password,@RequestParam("confirmPassword") String cpass,@RequestParam("custId") String custId) {
        Customer c=custService.printCustomer(custId);
		if(password.equals(cpass)) {
        	c.setCustPassword(password);
        	c.setCustConfirmPassword(cpass);
        	custService.updateCustomer(c);
        	logger.info("New password is set for customer");
        	return "customer/customerloginpage";
        }
		else {
			logger.warn("While setting new password,Password and confirm password doen't match");
		}
		
		return "customer/setPassword";
    }
	@RequestMapping("customerLogout")
	public String customerLogout(HttpSession session) {
		session.removeAttribute("customersession");
		logger.info("customer is logged out");
		return "customer/customerloginpage";
	}
	@RequestMapping("customerProfile")
	public String customerProfile(HttpSession session) throws UserNotFoundException {
		Customer c=(Customer)session.getAttribute("customersession");
		
		if(c==null) {
			logger.warn("customer is not logged in");
			return "customer/usernotfoundexceptionview";
        }else {  
        	logger.info("In customer profile of customer "+c.getCustId());
		return "customer/customerdetails";}
	}
	@RequestMapping("customerAddress")
	public String customerAddress(HttpSession session,Model m) {
		Customer c=(Customer)session.getAttribute("customersession");
		logger.info("In customer address list");
		List <Address> addresslist=custService.addressListInCustomer(c.getCustId());
		m.addAttribute("addresslist",addresslist);
		return "customer/customerAddressDetails";
	}
	@RequestMapping("addAddress")
    public String addAddress(@Valid @ModelAttribute("addressvalid") Address a,BindingResult br,HttpSession session,Model m) {
		logger.info("In customer add Address");
         if(br.hasErrors())
          { 
        	 	logger.warn("Address validation failed");
              return "customer/addAddressDetails";
          }else {        
        Customer c=(Customer)session.getAttribute("customersession");
        a.setCustomer(c);
        addressservice.insertAddress(a);
        logger.info("New Address of Id "+a.getAddressId()+" for "+c.getCustId()+" is added");
        List <Address> addresslist=custService.addressListInCustomer(c.getCustId());
        System.out.println(addresslist);
        m.addAttribute("addresslist",addresslist);
        return "customer/customerAddressDetails";
    }
    }
    @RequestMapping("addCustomerAddress")
    public String addCustomerAddress(HttpSession session,Model m) {
        Customer c=(Customer)session.getAttribute("customersession");
        m.addAttribute("addressvalid", new Address());
        return "customer/addAddressDetails";
    }	
	@RequestMapping("updateCustomerDetails")
	public String updateCustomerDetails(Model m,HttpSession session) {
		logger.info("In update customer address");
		Customer customer=(Customer)session.getAttribute("customersession");
		m.addAttribute("customerAttribute",customer);
		return "customer/updateCustomerform";
	}
	@RequestMapping("updateCustomer")
	public String updateCustomer(@Valid @ModelAttribute("customerAttribute")Customer c,BindingResult br,HttpSession session,Model m) {
		if(br.hasErrors()) {
			logger.warn("while updating customer address validation failed");
			return "customer/updateCustomerform";
		}
		else {
		Customer customer=(Customer)session.getAttribute("customersession");
		customer.setCustFirstName(c.getCustFirstName());
		customer.setCustLastName(c.getCustLastName());
		customer.setCustUsername();
		customer.setCustPhone(c.getCustPhone());
		customer.setCustEmail(c.getCustEmail());
		custService.updateCustomer(customer);
		logger.info("Customer details for customer id "+customer.getCustId()+" are updated");
		return "customer/customerdetails";
		}
		
	}
	
	@RequestMapping("deleteCustomer")
	public String deleteCustomer(HttpSession session) {
		Customer c=(Customer)session.getAttribute("customersession");
		System.out.println(c.getCustId());
		custService.deleteCustomer(c.getCustId());
		logger.info("customer account "+c+" is deleted");
		//session.removeAttribute("customersession");
		return "customer/customerloginpage";
	}
	
	@RequestMapping("backToCustomerHomepage")
	public String backToCustomerHomepage(Model m) {
		List<Category> categorylist=catService.showAllCategories();
		logger.info("In customer Home");
		m.addAttribute("categorylist", categorylist);
		return "customer/customerhomepage";
	}
	 @RequestMapping(value="singleAddresss",method=RequestMethod.GET)
	   public String singleAddress(@RequestParam("addressId") String addressId,Model m,HttpSession session) {
		   Address address=addressservice.printAddress(addressId);
		   logger.info("In address details of"+addressId);
		   session.setAttribute("addresssession", address);
		   return "customer/updateaddresspage";
	       
	   }
	 
	 @RequestMapping("updateAddressDetails")
		public String updateAddressDetails() {
			return "customer/updateaddresspage";
		}
	 @RequestMapping("updateAddress")
		public String updateAddress(Address a,HttpSession session,Model m) {
			Customer customer=(Customer)session.getAttribute("customersession");
			Address address=(Address)session.getAttribute("addresssession");
			address.setName(a.getName());
			address.setHouseNo(a.getHouseNo());
			address.setStreet(a.getStreet());
			address.setCity(a.getCity());
			address.setState(a.getState());
			address.setCountry(a.getCountry());
			address.setPostalCode(a.getPostalCode());
			addressservice.updateAddress(address);
			logger.info("Address details of "+address.getAddressId()+" is updated");
			//session.setAttribute("customersession", customer);
			List <Address> addresslist=custService.addressListInCustomer(customer.getCustId());
			System.out.println(addresslist);
			m.addAttribute("addresslist",addresslist);
			return "customer/customerAddressDetails";
		}
	 @RequestMapping("deleteAddress")
		public String deleteAddress(Address a,HttpSession session,Model m) {
			Customer customer=(Customer)session.getAttribute("customersession");
			Address address=(Address)session.getAttribute("addresssession");
			customer.getAddress().remove(address);
			addressservice.deleteAddress(address.getAddressId());
			List <Address> addresslist=custService.addressListInCustomer(customer.getCustId());
			m.addAttribute("addresslist",addresslist);
			return "customer/customerAddressDetails";
		}
	 
	 @RequestMapping("backToCustomerAddress")
		public String backToCustomerAddress(HttpSession session,Model m) {
		 Customer customer=(Customer)session.getAttribute("customersession");
			List <Address> addresslist=custService.addressListInCustomer(customer.getCustId());
			m.addAttribute("addresslist",addresslist);
			logger.info("Back to customer address of customer"+customer.getCustId());
			return "customer/customerAddressDetails";
		}

	 @RequestMapping("backtoCustomerDetails")
		public String backtoCustomerDetails(Model m) {
		 List<Category> categorylist=catService.showAllCategories();
			m.addAttribute("categorylist", categorylist);
			logger.info("Back to customer home page");
			return "customer/customerhomepage";
		}
	 
	 @RequestMapping(value="singleCategoryCustomer",method=RequestMethod.GET)
	   public String singleCategoryCustomer(@RequestParam("categoryId") String categoryId,Model m,HttpSession session) {
		 Category c=catService.showCategoryById(categoryId);
		 logger.info("In category "+c.getCategoryName());
	      session.setAttribute("categorysession",c);
	      m.addAttribute("productlist",c.getProductlist());
	      return "customer/singlecategorypageCustomer";
	       
	   }
	 @RequestMapping(value="singleProductCustomer",method=RequestMethod.GET)
	   public String singleProductCustomer(@RequestParam("prodId") String prodId,Model m,HttpSession session) throws UserNotFoundException {
		 Product product=prodservice.findByProdId(prodId);
		 logger.info("In Product "+product.getProdId());
		 if(product==null) {
			 throw new ProductNotFoundexception("601","The product with id "+prodId+" is not found");
		 }
		   session.setAttribute("productsession", product);
		   boolean wishliststatus=false; 
		   Boolean cartStatus=false;
		   boolean availablestatus=true;
			  if(product.getProdQuantity()<1) {
				  availablestatus=false;
				  product.setProdStatus(false);
				  prodservice.UpdateProductDetails(product);
			  }
			  System.out.println("singproduct customer avaialable status "+availablestatus);
			  session.setAttribute("availableStatus", availablestatus);
		   Customer customer=(Customer)session.getAttribute("customersession");
		   if(customer==null) {
			   session.setAttribute("wishlistStatus", wishliststatus);
			   session.setAttribute("cartStatus", cartStatus);
			   return "customer/singleProductpagecustomer";
	        }else {
		   Wishlist wishlist=customer.getWishlist();	   
		   System.out.println("In single product customer");
		  
		   if(wishlist.getWishlistProducts()!=null) {
		   String[] elements = wishlist.getWishlistProducts().split(",");
			 System.out.println("products in wishlist"+elements);
			 List<String> products = Arrays.asList(elements);
			 System.out.println("list of products"+products);
			
			 if(products.contains(product))
				 wishliststatus=true;
		   }
		  session.setAttribute("wishlistStatus", wishliststatus);
		  Cart cart=customer.getCart();
		  List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
		 
		   for(CartLine cl:cartlinelist) {
			   if(cl.getProduct().getProdId().equals(product.getProdId())) {
				   cartStatus=true;
				   break;
			   }
		   }
		  session.setAttribute("cartStatus", cartStatus);
		  return "customer/singleProductpagecustomer";}
	       
	   }
	 
	 @RequestMapping("backToCustomerProductspage")
		public String backToCustomerProductspage(Model m,HttpSession session) {
		 Category c=(Category)session.getAttribute("categorysession");
		 m.addAttribute("productlist",c.getProductlist());
	      return "customer/singlecategorypageCustomer";
		}
	 @RequestMapping("CustomerAdmin")
	    public String CustomerAdmin(Model m) {
	      	List<Customer> customerlist=custService.allCustomer();
	      	logger.info("in customer details of admin");
	      	m.addAttribute("customerlist", customerlist);
	        return "admin/allCustomerDetailsPage";
	        
	    }
	 @RequestMapping(value="customerviewadmin",method=RequestMethod.GET)
	   public String customerviewadmin(@RequestParam("custId") String custId,Model m,HttpSession session) {
		 Customer customer=custService.printCustomer(custId);
		  List<Address> addresslist=customer.getAddress();
		  m.addAttribute("addresslist", addresslist);
		  logger.info("Admin viewing customer details of customer"+custId);
		   return "admin/singlecustomerpage";
	       
	   }
	 
	 @RequestMapping("addToCart")
	    public String addToCart(Model m,HttpSession session) throws UserNotFoundException {
	      	Product product=(Product)session.getAttribute("productsession");
	      	Customer customer=(Customer)session.getAttribute("customersession");
	      	 if(customer==null) {
	      		 logger.warn("customer not logged in");
	      		return "customer/usernotfoundexceptionview";
		        }else {  
	      	Cart cart=customer.getCart();
	      	CartLine cartline=new CartLine();
	      	cartline.setProduct(product);
	      	cartline.setProductCount(1);
	      	cartline.setTotal(product.getProdPrice());
	      	cartline.setCart(cart);
	      	cartlineService.insertCart(cartline);
	      	logger.info("Product "+product.getProdId()+" is added to cart "+cart.getCartId());
	      	List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
	      	 m.addAttribute("cartlinelist", cartlinelist);
	      	double grandTotal=0;
	      	int totalItems=0;
	      	for(CartLine cl:cartlinelist) {
	      		totalItems=totalItems+cl.getProductCount();
	      		grandTotal=grandTotal+cl.getTotal();
	      	}
	      	cart.setNoOfItems(totalItems);
	      	cart.setGrandTotal(grandTotal);
	      	cartService.updateCart(cart);
	      	return "customer/customerCartPage";
		        }
	    }
	 @RequestMapping("customercart")
	    public String customercart(Model m,HttpSession session) throws UserNotFoundException {
	      	Customer customer=(Customer)session.getAttribute("customersession");
	      	if(customer==null) {
	      		 logger.warn("customer not logged in");
	      		return "customer/usernotfoundexceptionview";
	        }else {  
	      	Cart cart=customer.getCart();
	      	logger.info("In cart of customer"+customer.getCustId());
	      	List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
	      	 m.addAttribute("cartlinelist", cartlinelist);
	      	double grandTotal=0;
	      	int totalItems=0;
	      	for(CartLine cl:cartlinelist) {
	      		totalItems=totalItems+cl.getProductCount();
	      		grandTotal=grandTotal+cl.getTotal();
	      	}
	      	cart.setNoOfItems(totalItems);
	      	cart.setGrandTotal(grandTotal);
	      	cartService.updateCart(cart);
	      	
	      	return "customer/customerCartPage";}
	        
	    }
	 @RequestMapping(value="singleProductCustomerCart",method=RequestMethod.GET)
	   public String singleProductCustomerCart(@RequestParam("prodId") String prodId,Model m,HttpSession session) {
		 Product product=prodservice.findByProdId(prodId);
		 if(product==null) {
			 throw new ProductNotFoundexception("601","The product with id "+prodId+" is not found");
		 }
		   session.setAttribute("productsession", product);
		   Customer customer=(Customer)session.getAttribute("customersession");
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
		  session.setAttribute("cartStatus", cartStatus);
		  boolean availablestatus=true;
		  if(product.getProdQuantity()<1) {
			  availablestatus=false;
			  product.setProdStatus(false);
			  prodservice.UpdateProductDetails(product);
		  }
		  session.setAttribute("availableStatus", availablestatus);
		  logger.info("In cart of customer "+cart.getCartId()+" viewing prooduct "+prodId);
		   return "customer/singleProductpageCart";
	       
	   }
	 
	 @RequestMapping("customerBrowse")
	    public String customerBrowse(@RequestParam("prodName") String prodName,Model m) {
	      	List<Product> productlist=prodservice.showAllProducts();
	      	prodName=prodName.toLowerCase();
	      	 String[] a=prodName.split(" ");
	         List<String> slist=Arrays.asList(a);
	         List<Product> productListResult=new ArrayList<Product>();
	         for(String s:slist) {
	        	 for(Product p:productlist) {
	        		 String c=p.getProdCode().toLowerCase();
	        		 c=c+"_"+p.getCategory().getCategoryName().toLowerCase();
	        		 String[] b=c.split("_");
	                 List<String> values=Arrays.asList(b);
	        		 if(values.contains(s)) {
	        			 if(!productListResult.contains(p)){
	        				 productListResult.add(p); 
	                      }
	        		 }
	        	 }
	         }
	      	
	      	logger.info("customer browsed for phrase "+prodName);
	      	m.addAttribute("productlist", productListResult);
	      	return "customer/searchResult";
	        
	    }
	 @RequestMapping("addQuantCartLine")
	    public String addQuantCartLine(@RequestParam("cartLineId") int cartlineId,Model m,HttpSession session) {

		 Customer customer=(Customer)session.getAttribute("customersession");
		 CartLine cartline=cartlineService.printCartLine(cartlineId);
		 cartline.setProductCount(cartline.getProductCount()+1);
		 cartline.setTotal(cartline.getTotal()+cartline.getProduct().getProdPrice());
		 
	      	Cart cart=customer.getCart();
	      	cartlineService.updateCart(cartline);
	      	List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
	      	 m.addAttribute("cartlinelist", cartlinelist);
	      	Cart c=cartlineService.increaseCartQuant(cartlinelist);
	      	cart.setGrandTotal(c.getGrandTotal());
	      	cart.setNoOfItems(c.getNoOfItems());
	      	cartService.updateCart(cart);
	      	logger.info("the quantity for product "+cartline.getProduct()+" quantity is increased by 1");
	      	
	      	return "customer/customerCartPage";
	      	
	      	
	        
	    }
	 @RequestMapping("minusQuantCartLine")
	    public String minusQuantCartLine(@RequestParam("cartLineId") int cartlineId,Model m,HttpSession session) {
		 Customer customer=(Customer)session.getAttribute("customersession");
		 CartLine cartline=cartlineService.printCartLine(cartlineId);
		 if(cartline.getProductCount()-1>0) {
		 cartline.setProductCount(cartline.getProductCount()-1);
		 cartline.setTotal(cartline.getTotal()-cartline.getProduct().getProdPrice());
		 
	      	Cart cart=customer.getCart();
	      	cartlineService.updateCart(cartline);
	      	List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
	      	 m.addAttribute("cartlinelist", cartlinelist);
	      	Cart c=cartlineService.decreaseCartQuant(cartlinelist);
	      	cartService.updateCart(cart);
	      	logger.info("the quantity for product "+cartline.getProduct()+" quantity is decreased by 1");
	      	return "customer/customerCartPage";}
		 else {
			 Cart cart=customer.getCart();
			 List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
	      	 m.addAttribute("cartlinelist", cartlinelist);
	      	 m.addAttribute("message", "Quantity must be greater than 1");
	      	return "customer/customerCartPage";
		 }
	        
	    }
	 @RequestMapping("productBrand")
	    public String productBrand(@RequestParam("prodBrand") String prodBrand,Model m) {
	      	List<Product> productlistName=prodservice.findByProdBrand(prodBrand);
	      	m.addAttribute("productlist", productlistName);
	      	logger.info("The products of brand "+prodBrand+" are displayed");
	      	return "customer/productBrandPage";
	    }
	 @RequestMapping("confirmOrder")
	    public String confirmOrder(@RequestParam("quantity") int quantity,Model m,HttpSession session) {
		 Customer customer=(Customer)session.getAttribute("customersession");
		 Product product=(Product)session.getAttribute("productsession");
		 m.addAttribute("addresslist", customer.getAddress());
		 System.out.println(customer.getAddress());
		 logger.info("Product "+product.getProdId()+" of quantity "+quantity+" is forwarded to check out page");
		 m.addAttribute("quantity",quantity).addAttribute("totalPrice", product.getProdPrice()*quantity);
		 return "customer/checkoutPage";
	    }
	 
	 @RequestMapping("setAddressAndPayment")
	    public String setAddressAndPayment(@RequestParam("address") String addressId,@RequestParam("payment") String paymentname,Model m,HttpSession session) throws UserNotFoundException {
		System.out.println(addressId);
		Address address=addressservice.printAddress(addressId);
		Customer customer=(Customer)session.getAttribute("customersession");
		 if(customer==null) {
			 logger.warn("Exception raised: Customer Not Found ");
				throw new CustomerNotFoundException("Customer is not found");
	        }else { 
		Cart cart=customer.getCart();
		System.out.println("In setiong order");
		List<CartLine> cartlinelist=cartService.printCart(cart.getCartId()).getCartlines();
		System.out.println("customer cart lines "+cartlinelist);
		Order order=new Order();
		order.setAddressOrderId(addressId);
		order.setCartlines(cartlinelist);
		cart.setCartlines(null);
		cartService.updateCart(cart);
		System.out.println("after removing mapping"+cart);
		order.setCustomer(customer);
		order.setNoOfOrderItems(cart.getNoOfItems());
		order.setOrderGrandTotal(cart.getGrandTotal());
		System.out.println("before setting payment "+ order);
		Payment payment=new Payment();
		payment.setPaymentAmount(cart.getGrandTotal());
		if(paymentname.equals("cod")) {
			payment.setPaymentType("cod");
			System.out.println("in cod");
			paymentService.insertPayment(payment);
			order.setPayment(payment);
			order.setStatus("order placed");
			orderservice.insertOrder(order);
			logger.info("Order "+order.getId()+" for address "+address+" of payment type cod is confirmed");
			for(CartLine cl:cartlinelist) {
				cl.setOrder(order);
				cl.setCart(null);
				Product p=cl.getProduct();
				p.setProdQuantity(p.getProdQuantity()-1);
				prodservice.UpdateProductDetails(p);
				cartlineService.updateCart(cl);
			}
			List<Order> myOrders=custService.printCustomer(customer.getCustId()).getOrder();
			myOrders.add(order);
			customer.setOrder(myOrders);
			return "customer/successful";
		}
		else if(paymentname.equals("online")) {
			session.setAttribute("ordersession",order);
			session.setAttribute("paymentsession", payment);
			logger.info("Order "+order.getId()+" for address "+address+" of payment type Online is forwarded");
			m.addAttribute("checkValid", new CardDb());
			return "customer/cardFormPage";
		}
	        }
		return null;
	    }
	 @RequestMapping("myOrders")
	 public String myOrders(HttpSession session,Model m) throws UserNotFoundException {
		 Customer customer=(Customer)session.getAttribute("customersession");
		 if(customer==null) {
			 logger.warn("customer is not logged in");
			 return "customer/usernotfoundexceptionview";
	        }else {  
		 List<Order> myOrders=custService.printCustomer(customer.getCustId()).getOrder();
		 System.out.println("in myorders"+myOrders);
			m.addAttribute("orderlist", myOrders);
			logger.info("In orders page of customer "+customer.getCustId());
			return "customer/myOrdersPage";}
	 }
	 
	 @RequestMapping("removeFromCart")
	 public String removeFromCart(@RequestParam("cartLineId") int cartlineId,HttpSession session,Model m) {
		 Customer customer=(Customer)session.getAttribute("customersession");
		 CartLine cartline=cartlineService.printCartLine(cartlineId);
		 if(cartline==null) {
			 logger.warn("cartline "+cartline.getId()+" is not found");
			 throw new CartLinesNotFoundException("cartline is not found to remove");
		 }
		 cartlineService.deleteCart(cartlineId);
		 Cart cart=customer.getCart();
		 List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
      	 m.addAttribute("cartlinelist", cartlinelist);
      	 logger.info("cartline "+cartline.getId()+" is removed from cart "+cart.getCartId());
      	return "customer/customerCartPage";
	 }
	 @RequestMapping("buyCartLines")
	 public String buyCartLines(HttpSession session,Model m) {
		 System.out.println("in buycartlines");
		 Customer customer=(Customer)session.getAttribute("customersession");
		 Cart cart=customer.getCart();
		 List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
		 if(cartlinelist.size()==0) {
			 logger.warn("No product is found in cartline to order");
			 throw new CartLinesNotFoundException("You have not added any products:Add products");
		 }
		 System.out.println("in buy cartlines"+cartlinelist);
		 m.addAttribute("cartlinelist", cartlinelist);
		 List<Address> addresslist=customer.getAddress();
		 if(addresslist.size()==0) {   
			 logger.warn("No address is found to set order");
	            throw new AddressNotFoundexception("You have not added address: Add Address");
	        }else {
		 m.addAttribute("addresslist",addresslist);
		 System.out.println(customer.getAddress());
		 return "customer/checkoutPage";
	        }
	 }
	 
	 
	 @RequestMapping("checkCardValidity")
	 public String checkCardValidity(@Valid@ModelAttribute("checkValid") CardDb cd,BindingResult br,HttpSession session,Model m) {
		 if(br.hasErrors())
         { 
			 logger.warn("card validation is failed");
             return "customer/cardFormPage";
         }else {
		 System.out.println("month "+cd.getExpiryMonth());
		 if(carddbservice.validCard(cd.getCardHolderName(), cd.getCardNumber(), cd.getCardCVV(), cd.getExpiryMonth(), cd.getCardType())) {
			 System.out.println("After validation");
			 Order order=(Order)session.getAttribute("ordersession");
			 Payment payment=(Payment)session.getAttribute("paymentsession");
			 System.out.println("Order "+order);
			 System.out.println("Payment "+payment);
			 payment.setPaymentType("online");
				System.out.println("in online");
				paymentService.insertPayment(payment);
				order.setPayment(payment);
				order.setStatus("order placed");
				orderservice.insertOrder(order);
				Customer customer=(Customer)session.getAttribute("customersession");
				Cart cart=customer.getCart();
				List<CartLine> cartlinelist=cartService.printCart(cart.getCartId()).getCartlines();
				System.out.println("In online cartlines"+cartlinelist);
				for(CartLine cl:cartlinelist) {
					cl.setOrder(order);
					cl.setCart(null);
					Product p=cl.getProduct();
					p.setProdQuantity(p.getProdQuantity()-1);
					prodservice.UpdateProductDetails(p);
					cartlineService.updateCart(cl);
					
				}
				List<Order> myOrders=custService.printCustomer(customer.getCustId()).getOrder();
				myOrders.add(order);
				customer.setOrder(myOrders);
				logger.info("customer "+customer.getCustId()+" places order "+order.getId()+" by online payment");
				return "customer/successful";
	 }
		 logger.warn("Entered card details are wrong");
		 return "customer/failure";}
	 }
	 
	 @RequestMapping("addToWishlist")
	 public String addToWishlist(HttpSession session) throws UserNotFoundException {
		 Product product=(Product)session.getAttribute("productsession");
		 Customer customer=(Customer)session.getAttribute("customersession");
		 if(customer==null) {
			 logger.warn("customer is not logged in to access wishlist");
	            throw new UserNotFoundException("You have not Loged in: Login or create an accont");
	        }else {  
		 Wishlist wishlist=customer.getWishlist();
		 System.out.println("In add to wishlist"+wishlist);
		 String p;
		 if(wishlist.getWishlistProducts()==null)
			  p=product.getProdId();
		 else
			 p=wishlist.getWishlistProducts()+","+product.getProdId();
		 wishlist.setWishlistProducts(p);
		 wishlistService.updateWishlist(wishlist);
		 System.out.println("After updating product string "+customer.getWishlist().getWishlistProducts());
		 session.setAttribute("wishlistStatus", true);
		  Cart cart=customer.getCart();
		  List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
		  Boolean cartStatus=false;
		   for(CartLine cl:cartlinelist) {
			   if(cl.getProduct().getProdId().equals(product.getProdId())) {
				   cartStatus=true;
				   break;
			   }
		   }
		  session.setAttribute("cartStatus", cartStatus);
		  logger.info("Product "+product.getProdId()+" is added to wishlist of customer"+customer.getCustId());
		   return "customer/singleProductpagecustomer";
	        }
	 }
	 
	 @RequestMapping("removeFromWishlist")
	 public String removeFromWishlist(HttpSession session) {
		 Product product=(Product)session.getAttribute("productsession");
		 Customer customer=(Customer)session.getAttribute("customersession");
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
		   return "customer/singleProductpagecustomer";
	 }
	 
	 @RequestMapping("customerwishlist")
	 public String customerwishlist(HttpSession session,Model m) throws CustomerNotFoundException {
		 Product product=(Product)session.getAttribute("productsession");
		 Customer customer=(Customer)session.getAttribute("customersession");
		
		 if(customer==null) {
	            throw new CustomerNotFoundException("You have not Loged in: Login or create an accont");
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
		 return "customer/customerWishlistPage";}
	 }
	 @SuppressWarnings("null")
	@RequestMapping(value="singleProductCustomerWishlist",method=RequestMethod.GET)
   public String singleProductCustomerWishlist(@RequestParam("prodId") String prodId,Model m,HttpSession session)
	 {
		 Product product=prodservice.findByProdId(prodId);
		
		 if(product==null) {
			 logger.warn("product "+product.getProdId()+" not found");
			 throw new ProductNotFoundexception("601","The product with id "+product.getProdId()+" is not found");
		 }
		 logger.info("viewing product "+product.getProdId() );
		 Customer customer=(Customer)session.getAttribute("customersession");
		 session.setAttribute("wishlistStatus", true);
		  Cart cart=customer.getCart();
		  List<CartLine> cartlinelist=cartlineService.allCartLinesOfCart(cart.getCartId());
		  Boolean cartStatus=false;
		   for(CartLine cl:cartlinelist) {
			   if(cl.getProduct().getProdId().equals(product.getProdId())) {
				   cartStatus=true;
				   break;
			   }
		   }
		  session.setAttribute("cartStatus", cartStatus);
		  boolean availablestatus=true;
		  if(product.getProdQuantity()<1) {
			  availablestatus=false;
			  product.setProdStatus(false);
			  prodservice.UpdateProductDetails(product);
		  }
		  session.setAttribute("availableStatus", availablestatus);
		 return "customer/singleProductpageWishlist";
	 }
	 
	 @RequestMapping("newArrivals")
	 public String newArrivals(Model m) throws ParseException {
		 List<Product> productlist=prodservice.findNewlyArrived(new SimpleDateFormat("yyyy-MM-dd").parse("2022-10-01"));
		 logger.info("new arrival products");
		 m.addAttribute("productlist", productlist);
		 return "customer/productlistSpecial";
	 }
	 
	 @RequestMapping("highlyRatedProducts")
	 public String highlyRatedProducts(Model m) {
		List<Product> productlist=prodservice.findHighlyRated();
		 logger.info("Products rating greater than 3");
		m.addAttribute("productlist", productlist);
		 return "customer/productlistSpecial";
	 }
	 
	 @RequestMapping("below999Products")
	 public String below999Products(Model m) {
		List<Product> productlist=prodservice.findBelow999();
		 logger.info("Products cost less than 1000");
		m.addAttribute("productlist", productlist);
		 return "customer/productlistSpecial";
	 }
	
		
} 
