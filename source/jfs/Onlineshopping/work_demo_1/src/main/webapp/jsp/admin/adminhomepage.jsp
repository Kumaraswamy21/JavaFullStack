<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.osw.admin.model.Admin"%>
   
    <!-- <html>
    <head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <title>HomePage</title>
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel='stylesheet' href="css/adminhomepage.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
     -->
       
        <!-- <section id="header">
            <a href="#"><img src="img/logo.png"  alt=""></a>           
            <div>
                <ul  id="navbar" >
                    <li><a href="updateadminpage">UpdateDetails</a></li>
                    <li><a href="viewadmindetails">Profile</a></li>
                    <li><a href="adminlogout">Logout</a></li>
    
                </ul>
            </div>
        </section>
        <center>
            <br><br>
    
    </center>
    <section>
        <div class="main">
            <ul>
                <li><a href="Category">CategoriesPage</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
                <li><a href="CustomerAdmin">CustomerDetails</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
                <li><a href="Order">OrdersPage</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
                <li><a href="Payment">PaymentsPage</a></li>
            </ul>
        </div>
    </section>
    <br><br><br><br><br><br>
    -->
    
    <!DOCTYPE html>
<!-- <html style="font-size: 16px;" lang="en"><head> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>AdminHomePage</title>
    <link rel="stylesheet" href="css/style_2.css" media="screen">
<link rel="stylesheet" href="css/adminhomepage.css" media="screen">
    <script class="u-script" type="text/javascript" src="js/jquery1.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="js/oswscript_1.js" defer=""></script>
    <meta name="generator" content="Nicepage 4.19.3, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    
    
    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "Home",
		"logo": "img/NewLogo2.png"
}
</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="AdminHomePage">
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
      <%Admin admin=(Admin)session.getAttribute("admin");%>  
    <section class="u-clearfix u-image u-section-1" id="sec-9198" data-image-width="6651" data-image-height="3546">
        <center>
            <h2>Welcome <%=admin.getAdminUserName()%></h2>
        </center> 
        <div class="u-clearfix u-sheet u-sheet-1">
        <a href="Category" class="u-btn u-button-style u-custom-item u-hover-palette-1-dark-1 u-palette-1-base u-btn-1">categoriespage</a>
        <a href="CustomerAdmin" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-2">CUSTOMER <span style="font-weight: 400;">
            <span style="font-weight: 700;">
              <span style="font-weight: 400;"></span>
            </span>
          </span>DETAILS
        </a>
        <a href="adminOrders" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-3">ORDERS PAGE<span style="font-weight: 400;"></span>
        </a>
        <a href="paymentDetails" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-4">
          <span style="font-weight: 400;">PAYMENTS </span>
          <span style="font-weight: 400;">PAGE</span>
          <span style="font-weight: 400;"></span>
        </a>
      </div>
    </section>
    
    
    <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-b081"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Sample text.</p>
      </div></footer>
    
  
</body>
</html>