package com.osw.product.controller;

import java.text.ParseException;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.osw.admin.controller.AdminController;
import com.osw.customer.model.Cart;
import com.osw.customer.model.CartLine;
import com.osw.customer.model.Wishlist;
import com.osw.customer.service.CartLineService;
import com.osw.customer.service.WishListService;
import com.osw.product.model.Category;
import com.osw.product.model.Product;
import com.osw.product.service.CategoryService;
import com.osw.product.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private CategoryService catservice;
    
    @Autowired
    private ProductService prodservice;
    @Autowired
    private WishListService wishlistservice;
    @Autowired 
    private CartLineService cartlineservice;
    Logger logger = LoggerFactory.getLogger(ProductController.class);
   @RequestMapping("Category")
   public String Category(Model m) {
	   m.addAttribute("categorylist",catservice.showAllCategories());
	   return "admin/categorypage";
   }
   @RequestMapping(value="singleCategory",method=RequestMethod.GET)
   public String singleCategory(@RequestParam("categoryId") String categoryId,Model m,HttpSession session) {
       Category c=catservice.showCategoryById(categoryId);
      session.setAttribute("categorysession",c);
      logger.info("single category"+categoryId+" page ");
      m.addAttribute("productlist",c.getProductlist());
      return "admin/singlecategorypage";
       
   }
   @RequestMapping("addCategory")
   public String addCategory(Model m) {
	   m.addAttribute("categoryAttribute", new Category());
	   return "admin/addcategorypage";
   }
   @RequestMapping("addCategoryDetails")
   public String addCategoryDetails(@Valid @ModelAttribute("categoryAttribute") Category category,BindingResult br,Model m) {
	   if(br.hasErrors()) {
		   return "admin/addcategorypage";
	   }
	   else {
	   catservice.insertCategory(category);
	   m.addAttribute("categorylist",catservice.showAllCategories());
	   logger.info("category"+category.getCategoryId()+ "added");
	   return "admin/categorypage";}
   }
   @RequestMapping("backToCategorypage")
   public String backToCategorypage(HttpSession session,Model m) {
       session.removeAttribute("categorysession");
       logger.info("Back to category page");
       m.addAttribute("categorylist",catservice.showAllCategories());
       return "admin/categorypage";
   }
   @RequestMapping("addProduct")
   public String addProduct(Model m) {
	   m.addAttribute("productAttribute", new Product());
	   return "admin/addproductpage";
   }
   @RequestMapping("addProductDetails")
   public String addProductDetails(@Valid @ModelAttribute("productAttribute")Product product,BindingResult br,HttpSession session,Model m) throws ParseException {
	   System.out.println("In product");
	   if(br.hasErrors()) {
		   System.out.println("In product errors");
		   return "admin/addproductpage";
	   }
	   else {
	   Category c=(Category)session.getAttribute("categorysession");
	   product.setCategory(c);
	   product.setProdCode();
	   product.setProddate();
	   System.out.println("In add product"+product);
	   prodservice.addProduct(product);
	   session.setAttribute("productsession", product);
	   logger.info("product"+product.getProdId()+ "added");
	   return "admin/singleproductpage";
	   }
   }
   @RequestMapping(value="singleProduct",method=RequestMethod.GET)
   public String singleProduct(@RequestParam("prodId") String prodId,Model m,HttpSession session) {
       Product product=prodservice.findByProdId(prodId);
       session.setAttribute("productsession", product);
       logger.info("single product "+prodId+" page ");
       return "admin/singleproductpage";
       
   }
   @RequestMapping("singleProduct1")
   public String singleProduct1(HttpSession session,Model m) {
	   Category c=(Category)session.getAttribute("categorysession");
	   Product p=(Product)session.getAttribute("productsession");
	   return "admin/singleproductpage";
   }
   @RequestMapping("backToProductspage")
   public String backToProductspage(HttpSession session,Model m) {
       Category c=(Category)session.getAttribute("categorysession");
       m.addAttribute("productlist",catservice.productlistinCategory(c.getCategoryId()));
       logger.info("back to product page");
    
       return "admin/singlecategorypage";
   }
   @RequestMapping("deleteCategory")
   public String deleteCategory(HttpSession session,Model m) {
	   Category c=(Category)session.getAttribute("categorysession");
	   catservice.deleteCategory(c.getCategoryId());
	   session.removeAttribute("categorysession");
	   m.addAttribute("categorylist",catservice.showAllCategories());
	   logger.info("category" +c.getCategoryId()+"deleted");
	   return "admin/categorypage";
   }
   @RequestMapping(value="viewProduct")
   public String viewProduct(HttpSession session) {
      return "admin/viewproductpage";
       
   }
   @RequestMapping(value="editProduct")
   public String editProduct(Model m,HttpSession session) {
	   Product p=(Product)session.getAttribute("productsession");
	   m.addAttribute("productAttribute", p);
      return "admin/editproductdetails";
    }
   @RequestMapping("editProductDetails")
   public String editProductDetails(@Valid @ModelAttribute("productAttribute")Product p1,BindingResult br,Model m,HttpSession session) throws ParseException {
	   if(br.hasErrors()) {
		   return "admin/editproductdetails";
	   }
	   else {
	   Category c=(Category)session.getAttribute("categorysession");
	   Product p=(Product)session.getAttribute("productsession");
	   p.setProdName(p1.getProdName());
	   p.setProdPrice(p1.getProdPrice());
	   p.setProdType(p1.getProdType());
	   p.setProdBrand(p1.getProdBrand());
	   p.setProdQuantity(p1.getProdQuantity());
	   p.setProdRating(p1.getProdRating());
	   p.setDescription(p1.getDescription());
	   p.setProdCode();
	   p.setProddate();
	   p.setCategory(c);
	   p=prodservice.UpdateProductDetails(p);
	   if(p!=null) {
		   logger.info("product"+p.getProdId()+"details updated");
		   return "admin/singleproductpage";
	   }
	   else {
		   logger.warn("product" +p.getProdId() + "details not updated");
		   return "admin/editproductdetails";
	   }
	   }
   }
   @RequestMapping(value="deleteProduct")
   public String deleteProduct(Model m,HttpSession session) {
	 
	  Category c=(Category)session.getAttribute("categorysession");
	  Product p=(Product)session.getAttribute("productsession");
	  prodservice.deleteProduct(p.getProdId());
	  List<Product> productlist=catservice.productlistinCategory(c.getCategoryId());
	  logger.info("product"+p.getProdId() +"deleted");
	  m.addAttribute("productlist",productlist);
	   return "admin/singlecategorypage";
    }
   
   @RequestMapping(value="backToAdmin")
   public String backToAdmin(Model m,HttpSession session) {
      return "admin/adminhomepage";
    }
   
}