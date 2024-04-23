<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <form action="addCategoryDetails">
    Category Name:<input type="text" name="categoryName" required><br>
    Category Image:<input type="text" name="imageUrl" ><br>
    <input type="submit" value="Add">
   </form>     
</body>
</html> -->



<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Add ca​tegory">
    <meta name="description" content="">
    <title>addcategorypage</title>
    <link rel="stylesheet" href="css/style_2.css" media="screen">
<link rel="stylesheet" href="css/addcategorypage.css" media="screen">
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
    <meta property="og:title" content="addcategorypage">
    <meta property="og:type" content="website">
    <style>
      .errors{
        color:red;
      }
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
    <section class="u-clearfix u-image u-section-1" id="carousel_4c93" data-image-width="6652" data-image-height="4435">
      <div class="u-clearfix u-expanded-width u-gutter-0 u-layout-wrap u-layout-wrap-1">
        <div class="u-gutter-0 u-layout">
          <div class="u-layout-row">
            <div class="u-size-60">
              <div class="u-layout-row">
                <div class="u-container-style u-layout-cell u-right-cell u-size-60 u-layout-cell-1">
                  <div class="u-container-layout u-container-layout-1">
                    <h2 class="u-text u-text-default u-text-1">Add category<span style="font-size: 1.875rem;"></span>
                    </h2>
                    <img class="u-image u-image-default u-image-1" src="../img/pexels-ron-lach-8306367.jpg" alt="" data-image-width="3979" data-image-height="5968">
                    <div class="u-form u-form-1">
                     
                        <!-- <form action="addCategoryDetails" class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form"  name="form" style="padding: 10px;">
                      
                        <div class="u-form-group u-form-group-1">
                          <label for="categoryName" class="u-label">CategoryName</label>
                          <input type="text" placeholder="Category Name" id="categoryName"  name="categoryName" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required>
                        </div>
                       
                        <div class="u-form-group u-form-group-2">
                          <label for="imageurl" class="u-label">CategoryImage</label>
                          <input type="text" placeholder="Category Image" id="imageurl" name="imageUrl" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                       
                        <div class="u-align-center-lg u-align-center-md u-align-center-sm u-align-center-xs u-align-left-xl u-form-group u-form-submit">
                          <a href="categorypage" class="u-btn u-btn-submit u-button-style">ADD<br>
                          </a>
                          <input type="submit" value="submit" class="u-form-control-hidden">
                        </div>
                      
                        
                      </form> -->
                      <form:form action="addCategoryDetails" modelAttribute="categoryAttribute">
                        <div class="u-form-group u-form-group-1">
                            <form:label for="categoryName" path="categoryName" class="u-label">CategoryName</form:label>
                            <form:input type="text" placeholder="Category Name" id="categoryName"  name="categoryName" path="categoryName" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required="true"></form:input>
                            <form:errors path="categoryName"></form:errors>
                          </div>
                          <div class="u-form-group u-form-group-2">
                            <form:label for="imageurl" path="imageUrl" class="u-label">CategoryImage</form:label>
                            <form:input type="text" placeholder="Category Image" id="imageurl" name="imageUrl" path="imageUrl" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required="true"></form:input>
                            <form:errors path="imageUrl" ></form:errors>
                          </div>
                         
                          <!-- <div class="u-btn u-btn-submit u-button-style">
                        <input type="submit" value="Add">
                    </div> -->
                    <input type="submit" class="button-5" value="Add">
                   
                </form:form>   
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
    <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-b081"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Sample text.</p>
      </div></footer>
  
  
</body></html>