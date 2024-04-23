package com.osw.product.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osw.customer.model.Cart;
import com.osw.customer.model.CartLine;
import com.osw.customer.model.Wishlist;
import com.osw.model.sequence.StringPrefixedSequenceIdGenerator;
import com.osw.order.model.Order;

@Entity
public class Product {
    @Id

    @GeneratedValue(strategy =GenerationType.SEQUENCE.AUTO,generator = "product_seq")
    
    @GenericGenerator(name="product_seq", strategy =
    "com.osw.model.sequence.StringPrefixedSequenceIdGenerator", parameters = {
    
    @Parameter(name=StringPrefixedSequenceIdGenerator.INCREMENT_PARAM,value="1"),
    
    @Parameter(name=StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER,
    value="PROD_"),
    
    @Parameter(name=StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,
    value="%06d") } )
    private String prodId;
    @NotBlank
    @Size(min=3,max=20,message="Product name length should be between 2 and 20")
    private String prodName;
    
     
  
    private String prodCode;
    private Date proddate;
  
    private double prodPrice;
    @NotBlank
    @Size(min=3,max=20,message="Product type length should be between 2 and 20")
    private String prodType;
    @NotBlank
    private String description;
    @NotBlank
    @Size(min=3,max=20,message="Product brand length should be between 2 and 20")
    private String prodBrand;
    
    @Min(value=1,message="Rating should be b/w 1 and 5")
    @Max(value=5,message="Rating should be b/w 1 and 5")
    private int prodRating;
  
    private int prodQuantity;   
    
    @NotBlank
    @Pattern(regexp ="([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)",message="must be the jpg,png,gif or bmp extension")
    private String  prodImage;
    private boolean prodStatus;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @JsonIgnore
    private List<CartLine> cartLine=new ArrayList<>();
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode() {
		this.prodCode = this.prodName+"_"+this.prodBrand+"_"+this.prodType;
	}
	public Date getProddate() {
		return proddate;
	}
	public void setProddate() throws ParseException {
		Date d=new Date();
		this.proddate = d;
	}
	public double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProdBrand() {
		return prodBrand;
	}
	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}
	
	public int getProdRating() {
		return prodRating;
	}
	public void setProdRating(int prodRating) {
		this.prodRating = prodRating;
	}
	public int getProdQuantity() {
		return prodQuantity;
	}
	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	public String getProdImage() {
		return prodImage;
	}
	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<CartLine> getCartLine() {
		return cartLine;
	}
	public void setCartLine(List<CartLine> cartLine) {
		this.cartLine = cartLine;
	}
	public boolean isProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(boolean prodStatus) {
		this.prodStatus = prodStatus;
	}
    
}