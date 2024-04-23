package com.osw.product.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
import com.osw.model.sequence.StringPrefixedSequenceIdGenerator;

@Entity
public class Category {
    @Id
@GeneratedValue(strategy =GenerationType.SEQUENCE.AUTO,generator = "category_seq")
    
    @GenericGenerator(name="category_seq", strategy =
    "com.osw.model.sequence.StringPrefixedSequenceIdGenerator", parameters = {
    
    @Parameter(name=StringPrefixedSequenceIdGenerator.INCREMENT_PARAM,value="1"),
    
    @Parameter(name=StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER,
    value="CAT_"),
    
    @Parameter(name=StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,
    value="%03d") } )
    private String categoryId;
    @NotBlank
    @Size(min=3,max=20,message="Category name length should be between 2 and 20")
    private String categoryName;
    @NotBlank
   @Pattern(regexp ="([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)",message="must be the jpg,png,gif or bmp extension")
    private String imageUrl;    
   
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @Cascade(CascadeType.SAVE_UPDATE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Product> productlist=new ArrayList<>();
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public List<Product> getProductlist() {
        return productlist;
    }
    public void setProductlist(List<Product> productlist) {
        this.productlist = productlist;
    }
    
    
    
    

}