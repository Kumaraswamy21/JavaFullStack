package com.osw.admin.controller;

import java.util.List;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.osw.admin.model.Admin;
import com.osw.admin.service.AdminService;
import com.osw.exceptions.UserNotFoundException;
import com.osw.order.model.Order;
import com.osw.order.model.Payment;
import com.osw.order.service.OrderService;
import com.osw.order.service.PaymentService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminservice;
	@Autowired
	private PaymentService paymentservice;
	@Autowired
	private OrderService orderservice;
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	 @RequestMapping("insertadmin")
	    public String insertadmin() {
	        return "admin/adminsignupform";
	    }
	 @RequestMapping("adminloginpage")
	    public String adminloginpage() {
	        return "admin/adminloginpage";
	    }
	 
	 @RequestMapping("adminsignup")
	    public String adminsignup(Admin admin) {
		 	adminservice.addAdmin(admin);
		 	System.out.println("Hii");
	        return "admin/adminloginpage";
	    }
	

	    @RequestMapping("adminLogin")
	    public String adminLogin(@RequestParam("adminUserName") String username,@RequestParam("adminPassword")String password,HttpSession session) throws UserNotFoundException {
	        Admin admin1=adminservice.validateAdmin(username,password);
	        if(admin1!=null) {
	            session.setAttribute("admin", admin1);
	            logger.info("admin"+admin1.getAdminId()+"validation successful");
	            
	            return "admin/adminhomepage";        

	        }else {
	        	logger.warn("Exception raised:Admin not found for username "+username+" and password "+password);
	        	throw new UserNotFoundException("Wrong credentials");
	    }
	    }
	    @RequestMapping("validateadminId")
	    public String validateadminId(@RequestParam("adminId")String adminId,Model model) {
	        Admin admin = adminservice.validateAdminId(adminId);
	        if(admin!=null) {
	            model.addAttribute("adminId", adminId);
	            logger.info("admin id"+admin.getAdminId()+ "validated");
	            return "admin/forgotpassword";
	        }
	        return "admin/validateadminid";
	        
	    }
	    @RequestMapping("validateadminid")
	    public String validateadminid() {
	        return "admin/validateadminid";
	    }
	    
	    
	    @RequestMapping("forgotPassword")
	    public String forgotPassword(@RequestParam("adminPassword")String password,@RequestParam("adminconfirmPassword")String cpassword,@RequestParam("adminId")String adminid) {
	        Admin admin = adminservice.validateAdminId(adminid);
	        if(password.equals(cpassword)) {
	            admin.setAdminPassword(password);
	            adminservice.updateAdmin(admin);
	            logger.info("admin"+admin.getAdminId()+ "password updated");
	            return "admin/adminloginpage";
	        }else
	        	logger.info("password update failed");
	        return "admin/forgotpassword";
	        
	    }
	    @RequestMapping("updateadminpage")
        public String updateadmin(HttpSession session,Model m) {
            Admin a=(Admin) session.getAttribute("admin");
            m.addAttribute("update", a);
            return "admin/updateadminpage";
        }
	    
	    @RequestMapping("updateadmin")
        public String updateAdminDetails(@Valid @ModelAttribute("update") Admin a,BindingResult br,HttpSession session) {
	    	System.out.println(a.getAdminPassword());
          if(br.hasErrors())
          { 
        	  logger.warn("In update admin validation failed");
              return "admin/updateadminpage";
          }else {
            Admin admin1=(Admin) session.getAttribute("admin");
            admin1.setAdminUserName(a.getAdminUserName());
            admin1.setContactNo(a.getContactNo());
            admin1.setAdminEmail(a.getAdminEmail());
             admin1=adminservice.updateAdmin(admin1);
            if(admin1!=null) {
                session.setAttribute("admin", admin1);
                logger.info("admin"+admin1.getAdminId()+"details updated");
                return "admin/adminhomepage";
            }else
            	logger.warn("admin update failed");
            return "admin/updateadminpage";
        }
        }
	    @RequestMapping("viewadmindetails")
	    public String viewdetails() {
	        return "admin/viewadmindetails";
	    }
	    
	    @RequestMapping("adminhomepage")
	    public String homepage() {
	        return "admin/adminhomepage";
	    }
	    
	    @RequestMapping("adminlogout")
	    public String adminlogout(HttpSession session) {
	        session.invalidate();
	        return "admin/adminloginpage";
	        
	    }
	    
	    
	    @RequestMapping("adminPayment")
	    public String adminPayment(Model m) {
	        List<Payment> paymentlist=paymentservice.printAllPayments();
	        m.addAttribute("paymentlist", paymentlist);
	        return "admin/paymenthomepage";
	    }
	    
	    @RequestMapping("addPaymentMethod")
	    public String addPaymentMethod(Model m) {
	        return "admin/addpaymentform";
	    }
	    
	    @RequestMapping("addPaymentType")
	    public String addPaymentType(Payment p,Model m) {
	    	paymentservice.insertPayment(p);
	    	List<Payment> paymentlist=paymentservice.printAllPayments();
	        m.addAttribute("paymentlist", paymentlist);
	        return "admin/paymenthomepage";
	    }
	    @RequestMapping("adminOrders")
	    public String adminOrders(Model m) {
	    	List<Order> orderList=orderservice.printAllOrders();
	    	System.out.println("In admin"+orderList);
	    	m.addAttribute("orderlist",orderList);
	    	return "admin/adminOrdersPage";
	    }
	    @RequestMapping("updateOrderStatus")
	    public String updateOrderStatus(@RequestParam("orderStatus")String orderStatus,@RequestParam("orderId")Integer orderId,Model m) {
	    	Order order=orderservice.printOrder(orderId);
	    	order.setStatus(orderStatus);
	    	if(orderStatus.equals("Order Delivered")) {
	    		Payment payment=order.getPayment();
	    		payment.setPaymentStatus(true);
	    		paymentservice.updatePayment(payment);
	    	}
	    	orderservice.updateOrder(order);
	        System.out.println("After updating order status"+orderservice.printOrder(orderId).getStatus());
	        List<Order> orderList=orderservice.printAllOrders();
	    	m.addAttribute("orderlist",orderList);
	    	return "admin/adminOrdersPage";
	        
	    }
	    
	    @RequestMapping("paymentDetails")
	    public String paymentDetails(Model m) {
	    	List<Payment> paymentList=paymentservice.printAllPayments();
	    	System.out.println("In admin"+paymentList);
	    	m.addAttribute("paymentlist",paymentList);
	    	return "admin/adminPaymentsPage";
	    }
	    
	   
	    
}