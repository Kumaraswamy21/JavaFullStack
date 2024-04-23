<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.osw.product.model.Product"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <h1>Update Product Details</h1>-->
   
   <!-- <form action="editProductDetails">
    Product Image:<input type="text" name="prodImage" value="${productmodel.prodImage}"><br>
    Product Name:<input type="text" name="prodName" value="${productmodel.prodName}"><br>
    Product Code:<input type="text" name="prodCode" value="${productmodel.prodCode}"><br>
    Product Price:<input type="number" name="prodPrice" value="${productmodel.prodPrice}"><br>
    Product Type:<input type="text" name="prodType" value="${productmodel.prodType}"><br>
    Product Brand:<input type="text" name="prodBrand" value="${productmodel.prodBrand}"><br>
    Product Quantity:<input type="number" name="prodQuantity" value="${productmodel.prodQuantity}"><br>
    Product description:<input type="text" name="description" value="${productmodel.description}"><br>
    <input type="submit" value="Update">

   </form> -->
   
   <!-- <form action="editProductDetails">
    Product Image:<input type="text" name="prodImage" ><br>
    Product Name:<input type="text" name="prodName" ><br>
    Product Code:<input type="text" name="prodCode" ><br>
    Product Price:<input type="number" name="prodPrice" ><br>
    Product Type:<input type="text" name="prodType" ><br>
    Product Brand:<input type="text" name="prodBrand" ><br>
    Product Quantity:<input type="number" name="prodQuantity" ><br>
    Product description:<input type="text" name="description" ><br>
    <input type="submit" value="Update">
    
   </form>   
</body>

</html> --> 

<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>UpdateProductPage</title>
    <link rel="stylesheet" href="css/style_2.css" media="screen">
<link rel="stylesheet" href="css/addproductpage.css" media="screen">
    <script class="u-script" type="text/javascript" src="js/jquery1.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="js/oswscript_1.js" defer=""></script>
    <meta name="generator" content="Nicepage 4.19.3, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    
    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "Home",
		"logo": "../img/NewLogo2.png"
}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="UpdateProductPage">
    <meta property="og:type" content="website">
    <style>
      .error{color:red}
    </style>
  </head>
  <body class="u-body u-xl-mode" data-lang="en">
    
    <header class="u-clearfix u-custom-color-2 u-header u-header" id="sec-52f5" data-animation-name="" data-animation-duration="0" data-animation-delay="0" data-animation-direction="">
        <a href="https://nicepage.com" class="u-image u-logo u-image-1" data-image-width="253" data-image-height="159">
     
    <img src="../img/NewLogo2.png" class="u-logo-image u-logo-image-1">
      </a><nav class="u-menu u-menu-one-level u-offcanvas u-menu-1">
        <div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px;">
          <a class="u-button-style u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="#">
            <svg class="u-svg-link" viewBox="0 0 24 24"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use></svg>
            <svg class="u-svg-content" version="1.1" id="menu-hamburger" viewBox="0 0 16 16" x="0px" y="0px" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg"><g><rect y="1" width="16" height="2"></rect><rect y="7" width="16" height="2"></rect><rect y="13" width="16" height="2"></rect>
</g></svg>
          </a>
        </div>
           
        <div class="u-custom-menu u-nav-container">
          <ul class="u-nav u-unstyled u-nav-1">
            
            <li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="adminhomepage" style="padding: 10px 20px;">Home</a>
            </li> <li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="updateadminpage" style="padding: 10px 20px;">UpdateDetails</a>
            </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="viewadmindetails" style="padding: 10px 20px;">Profile</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="adminlogout" style="padding: 10px 20px;">Logout</a>
</li></ul>
        </div>
        <div class="u-custom-menu u-nav-container-collapse">
          <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
            <div class="u-inner-container-layout u-sidenav-overflow">
              <div class="u-menu-close"></div>
              <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
               <li class="u-nav-item" style="color:black;"><a class="u-button-style u-nav-link" href="adminhomepage">Home</a></li>
              <li class="u-nav-item" style="color:black;"><a class="u-button-style u-nav-link" href="updateadminpage">UpdateDetails</a>
              </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="viewadmindetails">Profile</a>
              </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="adminlogout">Logout</a>
</li></ul>
            </div>
          </div>
          <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
        </div>
      </nav></header> 
    
      <%Product product=(Product)session.getAttribute("productsession");%>
    
      <section class="u-clearfix u-image u-section-1" id="sec-0675" data-image-width="6651" data-image-height="3546">
      <div class="u-clearfix u-sheet u-sheet-1">
        <h3 class="u-text u-text-default u-text-1">EDIT PRODUCT<span style="font-size: 1.25rem;">
            <span class="u-text-palette-1-base"></span>
          </span>
        </h3>
        <a href="backToProductspage" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-1">Back</a>
       
        <div class="u-form u-form-1">
          
            <form:form action="editProductDetails" modelAttribute="productAttribute">
            
                <div class="u-form-group u-form-group-1">
                    <form:label for="prodImage" path="prodImage" class="u-label">ProductImage</form:label>
                    <form:input type="text" placeholder="Product image" id="prodImage" name="prodImage" path="prodImage" value="<%=product.getProdImage()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"></form:input>
                      <form:errors path="prodImage"/>
                  </div>
    
                <div class="u-form-group u-form-group-2">
                    <form:label for="prodName" path="prodName" class="u-label">ProductName</form:label>
                    <form:input type="text" placeholder="Product Name" id="prodName" name="prodName" path="prodName" value="<%=product.getProdName()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"></form:input>
                      <form:errors path="prodName"/>
                  </div>
            
                <div class="u-form-group u-form-group-3">
                    <form:label for="prodPrice" path="prodPrice" class="u-label">ProductPrice</form:label>
                    <form:input type="text" placeholder="Product Price" id="prodPrice" name="prodPrice" path="prodPrice" value="<%=product.getProdPrice()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"></form:input>
                      <form:errors path="prodPrice"/>
                  </div>
        
                <div class="u-form-group u-form-group-4">
                    <form:label for="prodType" path="prodType" class="u-label">ProductType</form:label>
                    <form:input type="text" placeholder="Product Type" id="prodType" name="prodType" path="prodType" value="<%=product.getProdType()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"></form:input>
                      <form:errors path="prodType"/>
                  </div>
            
                <div class="u-form-group u-form-group-5">
                    <form:label for="prodBrand" path="prodBrand" class="u-label">ProductBrand</form:label>
                    <form:input type="text" placeholder="Product Brand" id="prodBrand" name="prodBrand" path="prodBrand" value="<%=product.getProdBrand()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"></form:input>
                      <form:errors path="prodBrand"/>
                  </div>
        
                <div class="u-form-group u-form-group-6">
                    <form:label for="prodRating" path="prodRating" class="u-label">ProductRating</form:label>
                    <form:input type="text" placeholder="Product rating" id="prodRating" name="prodRating"  path="prodRating" value="<%=product.getProdRating()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"></form:input>
                      <form:errors path="prodRating"/>
                  </div>
                  
                <div class="u-form-group u-form-group-7">
                    <form:label for="prodQuantity" path="prodQuantity" class="u-label">ProductQuantity</form:label>
                    <form:input type="text" placeholder="Product Quantity" id="prodQuantity" name="prodQuantity" path="prodQuantity" value="<%=product.getProdQuantity()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"></form:input>
                      <form:errors path="prodQuantity"/>
                  </div>

               
                <div class="u-form-group u-form-textarea u-form-group-8">
                    <form:label for="description" path="description" class="u-label">ProductDescription</form:label>
                    <form:input rows="4" cols="50" id="description" name="description" path="description" value="<%=product.getDescription()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"></form:input>
                      <form:errors path="description"/>
                  </div>
                  
                      <input type="submit" class="button-5" value="Update Product" >
                    
            </form:form>     
           
        
       
        </div>
      </div>
    </section>
    
    <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-b081"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Sample text.</p>
      </div></footer>
  
  
</body></html>