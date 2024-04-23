<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.osw.product.model.Category"%>
    <%@page import="com.osw.product.model.Product"%>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    
  
    <a href ="viewProduct">View</a>   <br>
        <a href ="editProduct">Edit</a>   <br>
        <a href ="deleteProduct">Delete</a>   <br>

        <a href="backToProductspage">Back to Category</a>
       
</body>
</html> -->

<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>SingleProductPage</title>
    <link rel="stylesheet" href="css/style_2.css" media="screen">
<link rel="stylesheet" href="css/singleproductpage.css" media="screen">
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
    <meta property="og:title" content="SingleProductPage">
    <meta property="og:type" content="website">
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
     
      <%Category category=(Category)session.getAttribute("categorysession");%><br>     
      <%Product product=(Product)session.getAttribute("productsession");%><br>
   
      <section class="u-clearfix u-section-1" id="sec-50e3">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-clearfix u-expanded-width u-gutter-10 u-layout-wrap u-layout-wrap-1">
          <div class="u-layout">
            <div class="u-layout-row">
              <div class="u-size-30 u-size-60-md">
                <div class="u-layout-col">
                  <div class="u-container-style u-image u-layout-cell u-left-cell u-size-60 u-image-1" style="background-image:url('<%=product.getProdImage()%>');" src="" data-image-width="6652" data-image-height="4435">
                    <div class="u-container-layout u-valign-middle u-container-layout-1"></div>
                  </div>
                </div>
              </div>
              <div class="u-size-30 u-size-60-md">
                <div class="u-layout-col">
                  <div class="u-size-60">
                    <div class="u-layout-row">
                      <div class="u-align-left u-container-style u-layout-cell u-size-60 u-layout-cell-2">
                        <div class="u-container-layout u-container-layout-2">
                          <!-- <h3 class="u-text u-text-1"><%=product.getProdName()%></h3>
                          <p class="u-text u-text-1"><%=product.getProdId()%></p>
                          <p class="u-text u-text-1"><%=product.getProdCode()%></p>
                          <p class="u-text u-text-1"><%=product.getProdType()%></p>
                          <p class="u-text u-text-1"><%=product.getProdBrand()%></p>
                          <p class="u-text u-text-1"><%=product.getProdPrice()%></p>
                          <p class="u-text u-text-1"><%=product.getProdQuantity()%></p>
                          <p class="u-text u-text-1"><%=product.getProdRating()%></p>
                          <p class="u-text u-text-1"><%=product.isProdStatus()%></p>
                          <p class="u-text u-text-1"><%=product.getDescription()%></p> -->
                          <h2 class="u-custom-font u-font-montserrat u-text u-text-custom-color-16 u-text-1"><%=product.getProdName()%></h2>
                          <ul class="u-text u-text-2">
                            <li>
                              <span style="font-weight: 700;">Price</span>&nbsp;:<%=product.getProdPrice()%>
                            </li>
                            <li>
                              <span style="font-weight: 700;">Type</span>&nbsp;:<%=product.getProdType()%>
                            </li>
                            <li>
                              <span style="font-weight: 700;">Code&nbsp;<span style="font-weight: 400;">:<%=product.getProdCode()%></span>
                              </span>
                            </li>
                            <li>
                              <span class="u-text-black">
                                <span style="font-weight: 700;">Brand</span>
                                <span style="font-weight: 700;"> :</span>
                              </span><%=product.getProdBrand()%>
                            </li>
                            <li>
                              <span class="u-text-black" style="font-weight: 700;">Status:</span><%=product.isProdStatus()%>
                            </li>
                            <li>
                              <span class="u-text-black" style="font-weight: 700;">Description :<span style="font-weight: 400;"><%=product.getDescription()%></span>
                              </span>
                            </li>
                            <li>
                              <span class="u-text-black" style="font-weight: 700;">Review:<span style="font-weight: 400;"><%=product.getProdRating()%></span>
                              </span>
                            </li>
                          </ul>
                         
                          <br>
                          <a href="editProduct" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-1">PRODUCT UPDATE</a>
                          <a href="deleteProduct" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-2">Delete Product</a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <a href="backToProductspage" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-3">Back</a>
      </div>
    </section>   
    <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-b081"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Sample text.</p>
      </div></footer>
</body></html>