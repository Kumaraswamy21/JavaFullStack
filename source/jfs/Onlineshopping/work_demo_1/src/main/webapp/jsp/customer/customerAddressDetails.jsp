<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.osw.customer.model.Customer"%>
<!-- <!DOCTYPE html>
<html>
<head>
</head>
<body>
    <c:forEach items="${addresslist}" var="list">
        <c:out value="${list.addressId}" /> 

    </c:forEach> 
    <a href="CustomerAdmin">Back</a>
</body>
</html> -->


<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>AddressPage</title>
    <link rel="stylesheet" href="css/customer/styleC_1.css" media="screen">
<link rel="stylesheet" href="css/customerdetailspage.css" media="screen">
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
    <meta property="og:title" content="AddressPage">
    <meta property="og:type" content="website">
  </head>
  <body class="u-body u-xl-mode" data-lang="en"><header class="u-align-right u-box-shadow u-header u-valign-bottom u-white u-header" id="sec-c7d9" data-animation-name="" data-animation-duration="0" data-animation-delay="0" data-animation-direction=""><form action="customerBrowse" method="get" class="u-border-1 u-border-grey-50 u-search u-search-right u-search-1">
   
   
    <button class="u-search-button" type="submit">
          <span class="u-border-2 u-border-black u-icon-circle u-search-icon u-spacing-10 u-text-grey-40 u-search-icon-1">
            <svg class="u-svg-link" preserveAspectRatio="xMidYMin slice" viewBox="0 0 56.966 56.966" style=""><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-9dab"></use></svg>
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="svg-9dab" x="0px" y="0px" viewBox="0 0 56.966 56.966" style="enable-background:new 0 0 56.966 56.966;" xml:space="preserve" class="u-svg-content"><path d="M55.146,51.887L41.588,37.786c3.486-4.144,5.396-9.358,5.396-14.786c0-12.682-10.318-23-23-23s-23,10.318-23,23  s10.318,23,23,23c4.761,0,9.298-1.436,13.177-4.162l13.661,14.208c0.571,0.593,1.339,0.92,2.162,0.92  c0.779,0,1.518-0.297,2.079-0.837C56.255,54.982,56.293,53.08,55.146,51.887z M23.984,6c9.374,0,17,7.626,17,17s-7.626,17-17,17  s-17-7.626-17-17S14.61,6,23.984,6z"></path><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g></svg>
          </span>
        </button>
        <input class="u-search-input" type="search" name="prodName" value="" placeholder="Search">
      </form><nav data-position="" class="u-menu u-menu-one-level u-offcanvas u-menu-1">
        <div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px; font-weight: 700;">
          <a class="u-button-style u-custom-active-border-color u-custom-border u-custom-border-color u-custom-borders u-custom-hover-border-color u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-text-active-color u-custom-text-color u-custom-text-hover-color u-custom-top-bottom-menu-spacing u-nav-link" href="#" style="padding: 2px 0px; font-size: calc(1em + 4px);">
            <svg class="u-svg-link" viewBox="0 0 24 24"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use></svg>
            <svg class="u-svg-content" version="1.1" id="menu-hamburger" viewBox="0 0 16 16" x="0px" y="0px" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg"><g><rect y="1" width="16" height="2"></rect><rect y="7" width="16" height="2"></rect><rect y="13" width="16" height="2"></rect>
</g></svg>
          </a>
        </div>
        <div class="u-custom-menu u-nav-container">
          <ul class="u-nav u-spacing-30 u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-border-2 u-border-active-grey-90 u-border-hover-grey-50 u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-grey-90 u-text-grey-90 u-text-hover-grey-90" href="customerHome" style="padding: 10px 0px;">Home</a>
</li><li class="u-nav-item"><a class="u-border-2 u-border-active-grey-90 u-border-hover-grey-50 u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-grey-90 u-text-grey-90 u-text-hover-grey-90" href="customerProfile" style="padding: 10px 0px;">Profile</a>
</li><li class="u-nav-item"><a class="u-border-2 u-border-active-grey-90 u-border-hover-grey-50 u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-grey-90 u-text-grey-90 u-text-hover-grey-90" href="customerLogout" style="padding: 10px 0px;">Logout</a>
</li></ul>
        </div>
        <div class="u-custom-menu u-nav-container-collapse">
          <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
            <div class="u-inner-container-layout u-sidenav-overflow">
              <div class="u-menu-close"></div>
              <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="customerHome">Home</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="customerProfile">Profile</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="customerLogout">Logout</a>
</li></ul>
            </div>
          </div>
          <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
        </div>
      </nav><span class="u-file-icon u-hover-feature u-icon u-icon-1" data-animation-name="" data-animation-duration="0" data-animation-direction="" data-href="customercart"><img src="img/3144486.png" alt=""></span><span class="u-file-icon u-hover-feature u-icon u-icon-2" data-href="customerwishlist"><img src="img/56986.png" alt=""></span><span class="u-file-icon u-icon u-icon-3" data-href="myOrders"><img src="img/4430029.png" alt=""></span><img class="u-image u-image-default u-image-1" src="img/NewLogo3.png" alt="" data-image-width="239" data-image-height="165"></header>
      <section class="u-clearfix u-custom-color-9 u-section-1" style="background-color: #D3E5E5;" id="sec-f858">
        <div class="u-clearfix u-sheet u-sheet-1">
          <h3 class="u-text u-text-default u-text-1">Address details</h3>
          <div class="u-expanded-width u-table u-table-responsive u-table-1">
            <table class="u-table-entity">
              <colgroup>
                <col width="20%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
              </colgroup>
              <thead class="u-black u-table-header u-table-header-1">
                <tr style="height: 51px;">
                  <th class="u-border-1 u-border-black u-table-cell">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Name</th>
                  <th class="u-border-1 u-border-black u-table-cell">&nbsp; &nbsp; &nbsp; House No</th>
                  <th class="u-border-1 u-border-black u-table-cell">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Street</th>
                  <th class="u-border-1 u-border-black u-table-cell">&nbsp; &nbsp; &nbsp; &nbsp;City</th>
                  <th class="u-border-1 u-border-black u-table-cell">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;State</th>
                  <th class="u-border-1 u-border-black u-table-cell">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Country</th>
                  <th class="u-border-1 u-border-black u-table-cell">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Postal code</th>
                  <th class="u-border-1 u-border-black u-table-cell">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Update</th>
                </tr>
              </thead>
  
              <c:forEach items="${addresslist}" var="list">
              <tbody class="u-table-body">
                <tr style="height: 89px;">
                  <td class="u-border-1 u-border-grey-30 u-table-cell"><c:out value="${list.name}"/></td>
                  <td class="u-border-1 u-border-grey-30 u-table-cell"><c:out value="${list.houseNo}"/></td>
                  <td class="u-border-1 u-border-grey-30 u-table-cell"><c:out value="${list.street}"/></td>
                  <td class="u-border-1 u-border-grey-30 u-table-cell"><c:out value="${list.city}"/></td>
                  <td class="u-border-1 u-border-grey-30 u-table-cell"><c:out value="${list.state}"/></td>
                  <td class="u-border-1 u-border-grey-30 u-table-cell"><c:out value="${list.country}"/></td>
                  <td class="u-border-1 u-border-grey-30 u-table-cell"><c:out value="${list.postalCode}"/></td>
                  <td class="u-border-1 u-border-grey-30 u-table-cell">
                    <center>
                  </div>
                    <a href="singleAddresss?addressId=${list.addressId}" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-1">Update</a>
                  </div></center>
                </td>
                </tr>
              </tbody>
            </c:forEach>
            </table>
          <br>
          <br>
          <center>
          <a href="addCustomerAddress" style="background-color:black; color:white;">Add Address</a>
        </center>
      </section>
    
      <footer class="u-align-center u-clearfix u-footer u-white u-footer" id="sec-0750"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Sample text.</p>
      </div></footer>
    
  
</body></html>