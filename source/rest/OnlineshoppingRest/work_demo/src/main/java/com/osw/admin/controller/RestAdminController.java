package com.osw.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osw.admin.model.Admin;
import com.osw.admin.service.AdminService;
import com.osw.exceptions.UserNotFoundException;
import com.osw.order.model.Order;
import com.osw.order.model.Payment;
import com.osw.order.service.OrderService;
import com.osw.order.service.PaymentService;

@RestController
public class RestAdminController {
	@Autowired
	private AdminService adminservice;
	@Autowired
	private PaymentService paymentservice;
	@Autowired
	private OrderService orderservice;
	Logger logger = LoggerFactory.getLogger(RestAdminController.class);
	
	@GetMapping("/admin/{adminName}/{adminPassword}")
	public  ResponseEntity<?> admin(@PathVariable("adminName") String name,@PathVariable("adminPassword")String password,HttpSession session) throws UserNotFoundException {        
	    System.out.println("In rest controller");
		Admin a=adminservice.validateAdmin(name, password);
	    session.setAttribute("adminsession", a);
	    if(a!=null) {
	        Admin a1=(Admin) session.getAttribute("adminsession");
	        logger.info("admin"+a.getAdminId()+"validation successful");
	        return new ResponseEntity<Admin>(a,HttpStatus.CREATED);    
	    }else
	    {
	    	logger.warn("Exception raised:Admin not found for username "+name+" and password "+password);
        	throw new UserNotFoundException("Wrong credentials");
	    } 
	}
	
	@GetMapping("/validateadminIdRest/{adminId}")
    public ResponseEntity<String> validateadminIdRest(@PathVariable("adminId") String adminId,Model model) {
        Admin admin = adminservice.validateAdminId(adminId);
        if(admin!=null) {
            model.addAttribute("adminId", adminId);
            logger.info("admin id"+admin.getAdminId()+ "validated");
            return new ResponseEntity<String>("admin id"+admin.getAdminId()+"validated",HttpStatus.OK);  
        }else {
        return new ResponseEntity<String>("Admin Id is not validated",HttpStatus.BAD_REQUEST);
        }
        
    }
	 @PutMapping("/forgotPasswordRest/{adminPassword}/{adminconfirmPassowrd}/{adminId}")
	    public ResponseEntity<?> forgotPasswordRest(@PathVariable("adminPassword")String password,@PathVariable("adminconfirmPassword")String cpassword,@PathVariable("adminId")String adminid,HttpSession session) {
		 Admin admin1=(Admin) session.getAttribute("adminsession");
		 Admin admin = adminservice.validateAdminId(adminid);
	        if(password.equals(cpassword)) {
	            admin.setAdminPassword(password);
	            admin.setAdminconfirmPassword(cpassword);
	            adminservice.updateAdmin(admin);
	            logger.info("admin"+admin.getAdminId()+ "password updated");
	            return new ResponseEntity<String>("admin"+admin.getAdminId()+"password updated",HttpStatus.OK); 
	        }else
	        	logger.info("password and confirm password must be equal");
	        return new ResponseEntity<String>("password and confirm password must be equal",HttpStatus.BAD_REQUEST);
	        
	    }
	 @PutMapping("/updateadminRest")
     public ResponseEntity<?> updateAdminDetailsRest(@Valid @RequestBody Admin a,BindingResult br,HttpSession session) {
       if(br.hasErrors())
       { 
     	  logger.warn("In update admin validation failed");
     	 return new ResponseEntity<String>("validation failed",HttpStatus.BAD_REQUEST);
       }else {
         Admin admin1=(Admin) session.getAttribute("adminsession");
         if(a==null) {
	    		logger.warn("adminsession not found");
	    		return new ResponseEntity<String>("admin session not found",HttpStatus.BAD_REQUEST);
	    	}else {
         admin1.setAdminUserName(a.getAdminUserName());
         admin1.setContactNo(a.getContactNo());
         admin1.setAdminEmail(a.getAdminEmail());
         admin1.setAdminPassword(a.getAdminPassword());
         admin1.setAdminconfirmPassword(a.getAdminPassword());
          admin1=adminservice.updateAdmin(admin1);
         if(admin1!=null) {
             session.setAttribute("admin", admin1);
             logger.info("admin"+admin1.getAdminId()+"details updated");
             return new ResponseEntity<Admin>(admin1,HttpStatus.OK);
         }else
         	logger.warn("admin update failed");
         return new ResponseEntity<String>("Admin updation failed",HttpStatus.BAD_REQUEST);
     }}
     }
	 
	 @GetMapping(value="/adminOrdersRest")
	    public ResponseEntity<List<Order>> adminOrdersRest(Model m,HttpSession session) throws UserNotFoundException {
		 Admin admin1=(Admin) session.getAttribute("adminsession");
		 if(admin1==null) {
             logger.warn("Exception occured:adminsession not found");
             throw new UserNotFoundException("admin session not found");
         }else {
	    	List<Order> orderList=orderservice.printAllOrders();
	    	m.addAttribute("orderlist",orderList);
	    	logger.info("In admin orders list");
	    	return new ResponseEntity<List<Order>>(orderList,HttpStatus.FOUND);
	    }
	 }
	 
	    @PutMapping("/updateOrderStatusRest/{orderStatus}/{orderId}")
	    public ResponseEntity<Order> updateOrderStatusRest(@PathVariable("orderStatus")String orderStatus,@PathVariable("orderId")Integer orderId,Model m,HttpSession session) throws UserNotFoundException {
	    	 Admin admin1=(Admin) session.getAttribute("adminsession");
	    	 if(admin1==null) {
	             logger.warn("Exception occured:adminsession not found");
	             throw new UserNotFoundException("admin session not found");
	         }else {
	    	Order order=orderservice.printOrder(orderId);
	    	order.setStatus(orderStatus);
	    	if(orderStatus.equals("Order Delivered")) {
	    		Payment payment=order.getPayment();
	    		payment.setPaymentStatus(true);
	    		paymentservice.updatePayment(payment);
	    	}
	    	orderservice.updateOrder(order);
	    	logger.info("Updated order status to "+orderStatus+" for order id "+orderId);
	    	return new ResponseEntity<Order>(order,HttpStatus.OK);
	        
	         }
	    }
	    
	    @RequestMapping("/paymentDetailsRest")
	    public ResponseEntity<List<Payment>> paymentDetailsRest(Model m,HttpSession session) throws UserNotFoundException {
	    	 Admin admin1=(Admin) session.getAttribute("adminsession");
	    	 if(admin1==null) {
	             logger.warn("Exception occured:adminsession not found");
	             throw new UserNotFoundException("admin session not found");
	         }else {
	    	List<Payment> paymentList=paymentservice.printAllPayments();
	    	logger.info("In admin payments list");
	    	m.addAttribute("paymentlist",paymentList);
	    	return new ResponseEntity<List<Payment>>(paymentList,HttpStatus.FOUND);
	    }
	    }
		
		  @PutMapping("/adminlogoutRest")
		  public ResponseEntity<Object> adminlogout(HttpSession session) { 
			  Admin a=(Admin)session.getAttribute("adminsession");
			  if(a==null) {
		  logger.warn("adminsession not found"); 
		  return new ResponseEntity<Object>("admin session not found",HttpStatus.BAD_REQUEST);
		  }else { session.invalidate(); return new
		  ResponseEntity<Object>("Admin "+a.getAdminId()+" is logged out",HttpStatus.OK
		  ); } }
		 
	    
	
	
}