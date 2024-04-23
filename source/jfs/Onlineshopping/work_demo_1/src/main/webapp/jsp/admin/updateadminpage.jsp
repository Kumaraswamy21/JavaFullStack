
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jstl/core_rt"  %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@page import="com.osw.admin.model.Admin"%>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Admin Details</title>
<link rel='stylesheet' href="css/updateadminpage.css">
</head>
<body>

<center>
    <div>
        <h2>Update Admin Details Page</h2><br>
        <form action="updateadmin">
            <label for="adminUserName">UserName:</label>
            <input type="text"  name="adminUserName" id="adminUserName" value=""><br><br>
            <label for="contactNo">ContactNo:</label>
            <input type="text"  name="contactNo" id="contactNo" value=""><br><br>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="adminEmail">Email:</label>
            <input type="text"  name="adminEmail" id="adminEmail" value=""><br><br>
            <input type="submit" value="Update">
        </form>
    </div>
</center>
</body>
</html> -->


<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Update Your Details">
    <meta name="description" content="">
    <title>UpdateAdminDetails</title>
    <link rel="stylesheet" href="css/style_2.css" media="screen">
<link rel="stylesheet" href="css/updateadmindetails.css" media="screen">
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
    <meta property="og:title" content="UpdateAdminDetails">
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
      
      <%Admin admin=(Admin)session.getAttribute("admin");%>

    <section class="u-align-center u-clearfix u-custom-color-7 u-section-1" id="sec-3c6a">
      <div class="u-clearfix u-sheet u-sheet-1">
        <h1 class="u-text u-text-default u-text-1">Update Details</h1>
        <div class="u-form u-form-1">
         
          <form:form action="updateadmin" modelAttribute="update">
          
            <div class="u-form-group u-form-name u-label-top">           
                <form:label for="adminUserName" path="adminUserName" class="u-label">AdminUserName</form:label>
          <form:input type="text" placeholder="Enter Your UserName" id="adminUserName" name="adminUserName" path="adminUserName" value="<%=admin.getAdminUserName()%>"  class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"/>
          <form:errors path="adminUserName" cssClass="error"/><br><br>
        </div>
        
        <div class="u-form-email u-form-group u-label-top"> 
            <form:label for="contactNo" path="contactNo" class="u-label">ContactNo</form:label>
          <form:input type="text" placeholder="Enter ContactNo" id="contactNo" name="contactNo" path="contactNo" value="<%=admin.getContactNo()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"/>
          <form:errors path="contactNo" cssClass="error"/><br><br>
        </div>
        <div class="u-form-group u-label-top u-form-group-3">
          <form:label for="adminEmail" path="adminEmail" class="u-label">Email</form:label>
          <form:input type="text" placeholder="Enter Your valid Email" id="adminEmail" name="adminEmail" path="adminEmail" value="<%=admin.getAdminEmail()%>" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"/>
          <form:errors path="adminEmail" cssClass="error"/><br><br>
        </div>  
        
          
          <form:input type="password" style="visibility: collapse;" id="adminPassword" name="adminPassword" path="adminPassword" value="<%=admin.getAdminPassword()%>" />
        
          
          <form:input type="password" style="visibility: collapse;" id="adminconfirmPassword" name="adminconfirmPassword" path="adminconfirmPassword" value="<%=admin.getAdminconfirmPassword()%>" />
         
       
        <input type="submit" class="button-5" value="Update">          
        </form:form>
         
        </div>
      </div>
    </section>
   
   
    <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-b081"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Sample text.</p>
      </div></footer>
   
</body></html>