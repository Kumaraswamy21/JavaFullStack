package com.osw.product.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osw.customer.service.CartLineService;
import com.osw.customer.service.WishListService;
import com.osw.exceptions.CategoryNotFoundException;
import com.osw.exceptions.ProductNotFoundexception;
import com.osw.product.model.Category;
import com.osw.product.model.Product;
import com.osw.product.service.CategoryService;
import com.osw.product.service.ProductService;
@RestController
public class ProductRestController {
	 @Autowired
	    private CategoryService catservice;
	    
	    @Autowired
	    private ProductService prodservice;
	    @Autowired
	    private WishListService wishlistservice;
	    @Autowired 
	    private CartLineService cartlineservice;
	    Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	    @GetMapping("/CategoryRest")
	    public ResponseEntity<List<Category>> CategoryRest(Model m) {
	 	   List<Category> categorylist=catservice.showAllCategories();
	 	   return new ResponseEntity<List<Category>>(categorylist,HttpStatus.FOUND);
	    }
	    @RequestMapping("/singleCategoryRest/{categoryId}")
	    public ResponseEntity<List<Product>> singleCategory(@PathVariable("categoryId") String categoryId,HttpSession session) {
	 	   Category c=catservice.showCategoryById(categoryId);
	 	  if(c==null) {
	 		  logger.warn("Exception occured:category not found for id"+categoryId);
	 		  throw new CategoryNotFoundException("category not found");
	 	   }else {
	       session.setAttribute("categorysession",c);
	       List<Product> productlist=c.getProductlist();
	       return new ResponseEntity<List<Product>>(productlist,HttpStatus.FOUND);}
	        
	    }
	    @PostMapping("/addCategoryDetailsRest")
	    public ResponseEntity<Object> addCategoryDetails(@Valid @RequestBody Category category,BindingResult br,Model m) {
	 	   if(br.hasErrors()) {
	 		   logger.warn("category validation failed");
	 		  return new ResponseEntity<Object>("validation Failed for category",HttpStatus.BAD_REQUEST);
	 	   }
	 	   else {
	 		  Category c=catservice.insertCategory(category);
	 	   logger.info("category"+category.getCategoryId()+ "added");
	 	   
	 	  return new ResponseEntity<Object>(c,HttpStatus.CREATED);}
	    }
	   
	   
	    @PostMapping("/addProductDetailsRest")
	    public ResponseEntity<Object>  addProductDetails(@Valid @RequestBody Product product,BindingResult br,HttpSession session,Model m) throws ParseException {
	 	   if(br.hasErrors()) {
	 		  logger.warn("Product validation failed");
	 		  return new ResponseEntity<Object>("validation Failed for Product",HttpStatus.BAD_REQUEST);
	 	   }
	 	   else {
	 	   Category c=(Category)session.getAttribute("categorysession");
	 	  if(c==null) {
	 		 logger.warn("Exception occured:category  session not found");
	 		  throw new CategoryNotFoundException("category session not found");
	 	   }else {
	 	   product.setCategory(c);
	 	   product.setProdCode();
	 	   product.setProddate();
	 	   prodservice.addProduct(product);
	 	   session.setAttribute("productsession", product);
	 	   logger.info("product"+product.getProdId()+ "added");
	 	  return new ResponseEntity<Object>(product,HttpStatus.CREATED);}
	 	   }
	    }
	    @GetMapping("/singleProductRest/{prodId}")
	    public ResponseEntity<Object> singleProduct(@PathVariable("prodId") String prodId,Model m,HttpSession session) {
	 	   Product product=prodservice.findByProdId(prodId);
	 	  if(product==null) {
	 		  logger.warn("Exception occured:product not found for id "+prodId);
	 		  throw new ProductNotFoundexception("Product not found");
	 	   }
	 	   session.setAttribute("productsession", product);
	 	  return new ResponseEntity<Object>(product,HttpStatus.FOUND);
	        
	    }
	    @GetMapping("singleProduct1Rest")
	    public ResponseEntity<?> singleProduct1(HttpSession session,Model m) {
	 	   Category c=(Category)session.getAttribute("categorysession");
	 	   Product p=(Product)session.getAttribute("productsession");
	 	   if(c==null) {
	 		  logger.warn("Exception occured:category  session not found");
	 		  throw new CategoryNotFoundException("category session not found");
	 	   }
	 	   else if(p==null) {
	 		  logger.warn("Exception occured:product session not found");
	 		  throw new ProductNotFoundexception("Product session not found");
	 	   }
	 	   else {
	 	  return new ResponseEntity<Product>(p,HttpStatus.FOUND);
	 	   }
	    }
	   
	    @DeleteMapping("deleteCategoryRest")
	    public ResponseEntity<Object> deleteCategory(HttpSession session,Model m) {
	 	   Category c=(Category)session.getAttribute("categorysession");
	 	  if(c==null) {
	 		 logger.warn("Exception occured:category  session not found");
	 		  throw new CategoryNotFoundException("category session not found");
	 	   }else {
	 	   catservice.deleteCategory(c.getCategoryId());
	 	   session.removeAttribute("categorysession");
	 	   m.addAttribute("categorylist",catservice.showAllCategories());
	 	   logger.info("category" +c.getCategoryId()+"deleted");
	 	  return new ResponseEntity<Object>("Category "+c.getCategoryId()+" deleted",HttpStatus.OK);}
	    }
	 
	    @PutMapping("/editProductDetailsRest")
	    public ResponseEntity<Object> editProductDetails(@Valid @RequestBody Product p1,BindingResult br,Model m,HttpSession session) throws ParseException {
	 	   if(br.hasErrors()) {
	 		  logger.warn("Product validation failed");
	 		  return new ResponseEntity<Object>("validation failed for product",HttpStatus.BAD_REQUEST);
	 	   }
	 	   else {
	 	   Category c=(Category)session.getAttribute("categorysession");
	 	   Product p=(Product)session.getAttribute("productsession");
	 	  if(c==null) {
	 		 logger.warn("Exception occured:category  session not found");
	 		  throw new CategoryNotFoundException("category session not found");
	 	   }
	 	   else if(p==null) {
	 		  logger.warn("Exception occured:product session not found");
	 		  throw new ProductNotFoundexception("Product session not found");
	 	   }else {
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
	 		  return new ResponseEntity<Object>(p,HttpStatus.FOUND);
	 	   }
	 	   else {
	 		   logger.warn("product" +p.getProdId() + "details not updated");
	 		  return new ResponseEntity<Object>("product details not updated",HttpStatus.BAD_REQUEST);
	 	   }
	 	   }}
	    }
	    @DeleteMapping("deleteProductRest")
	    public ResponseEntity<Object> deleteProductRest(Model m,HttpSession session) {
	 	 
	 	  Category c=(Category)session.getAttribute("categorysession");
	 	  Product p=(Product)session.getAttribute("productsession");
	 	 
	 	  
	 	  if(c==null) {
	 		 logger.warn("Exception occured:category  session not found");
	 		  throw new CategoryNotFoundException("category session not found");
	 	   }
	 	   else if(p==null) {
	 		  logger.warn("Exception occured:product session not found");
	 		  throw new ProductNotFoundexception("Product session not found");
	 	   }else {
	 	  prodservice.deleteProduct(p.getProdId());

	 	  List<Product> productlist=catservice.productlistinCategory(c.getCategoryId());
	 	  logger.info("product"+p.getProdId() +"deleted");
	 	  m.addAttribute("productlist",productlist);
	 	 return new ResponseEntity<Object>("Product "+p.getProdId()+" deleted",HttpStatus.OK);}
	    }
	    
	    
}
