<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.osw.order.model.Order"%>
    <%@page import="com.osw.Customer.model.Customer"%>
    <%@page import="com.osw.order.model.Payment"%>
    <!DOCTYPE html>
    <html style="font-size: 16px;" lang="en"><head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>UpdateOrderStatus</title>
        <link rel="stylesheet" href="css/style_2.css" media="screen">
    <link rel="stylesheet" href="css/UpdateOrderStatus.css" media="screen">
        <script class="u-script" type="text/javascript" src="js/jquery.js" defer=""></script>
        <script class="u-script" type="text/javascript" src="js/oswscript.js" defer=""></script>
        <meta name="generator" content="Nicepage 4.19.3, nicepage.com">
        <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
        
        
        <script type="application/ld+json">{
        "@context": "http://schema.org",
        "@type": "Organization",
        "name": "Home",
        "logo": "images/NewLogo2.png"
    }</script>
        <meta name="theme-color" content="#478ac9">
        <meta property="og:title" content="UpdateOrderStatus">
        <meta property="og:type" content="website">
      </head>
      <body class="u-body u-xl-mode" data-lang="en">
    
        <header class="u-clearfix u-custom-color-2 u-header u-header" id="sec-52f5" data-animation-name="" data-animation-duration="0" data-animation-delay="0" data-animation-direction="">
            <a href="" class="u-image u-logo u-image-1" data-image-width="253" data-image-height="159">
         
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
                
                <li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="adminhomepage" style="padding: 10px 20px; color: black;">Home</a>
                </li> <li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="updateadminpage" style="padding: 10px 20px; color: black;">UpdateDetails</a>
                </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="viewadmindetails" style="padding: 10px 20px; color: black;">Profile</a>
    </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="adminlogout" style="padding: 10px 20px; color: black;">Logout</a>
    </li></ul>
            </div>
            <div class="u-custom-menu u-nav-container-collapse">
              <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
                <div class="u-inner-container-layout u-sidenav-overflow">
                  <div class="u-menu-close"></div>
                  <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
                   <li class="u-nav-item" style="color: black;"><a class="u-button-style u-nav-link" href="adminhomepage">Home</a></li>
                  <li class="u-nav-item" style="color: black;"><a class="u-button-style u-nav-link" href="updateadminpage">UpdateDetails</a>
                  </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="viewadmindetails">Profile</a>
                  </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="adminlogout">Logout</a>
    </li></ul>
                </div>
              </div>
              <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
            </div>
          </nav></header> 
        <section class="u-clearfix u-section-1" id="sec-c95f">
          <div class="u-clearfix u-sheet u-sheet-1">
            <h3 class="u-text u-text-default u-text-1">Order</h3>
            <div class="u-expanded-width u-table u-table-responsive u-table-1">
              <table class="u-table-entity">
                <colgroup>
                  <col width="13.9%">
                  <col width="16.8%">
                  <col width="15.4%">
                  <col width="14.4%">
                  <col width="23.3%">
                  <col width="16.2%">
                </colgroup>
                <thead class="u-black u-table-header u-table-header-1">
                  <tr style="height: 50px;">
                    <th class="u-border-1 u-border-black u-table-cell">OrderID</th>
                    <th class="u-border-1 u-border-black u-table-cell">CustomerID</th>
                    <th class="u-border-1 u-border-black u-table-cell">CustomerName</th>
                    <th class="u-border-1 u-border-black u-table-cell">PaymentType</th>
                    <th class="u-border-1 u-border-black u-table-cell">PaymentStatus</th>
                    <th class="u-border-1 u-border-black u-table-cell">UpdateOrderStatus</th>
                    <th class="u-border-1 u-border-black u-table-cell">Update</th>
                  </tr>
                </thead>
                <c:forEach items="${orderlist}" var="list">
                <tbody class="u-table-body">
                  <tr style="height: 85px;">
                    <td class="u-border-1 u-border-grey-30 u-table-cell">${list.id}</td>
                    <td class="u-border-1 u-border-grey-30 u-table-cell">${list.customer.custId}</td>
                    <td class="u-border-1 u-border-grey-30 u-table-cell">${list.customer.custUsername}</td>
                    <td class="u-border-1 u-border-grey-30 u-table-cell">${list.payment.paymentType}</td>
                    <td class="u-border-1 u-border-grey-30 u-table-cell">${list.payment.paymentStatus}</td>
                    <form action="updateOrderStatus">
                      <input type="number" name="orderId" value="${list.id}" hidden>
                    <td class="u-border-1 u-border-grey-30 u-table-cell"><select name="orderStatus" id="orderStatus">
                      <option value="Order Accepted">Order Accepted</option>
                      <option value="Order Shipped">Order Shipped</option>
                      <option value="Order Reached">Order Reached</option>
                      <option value="Order Delivered">Order Delivered</option>
                      <option value="Order Rejected">Order Rejected</option>
                  </select></td>
                    <td class="u-border-1 u-border-grey-30 u-table-cell"><input type="submit"  class="button-5" value="Update"></td>
                    </form>
                  </tr>
                </tbody>
                </c:forEach>
              </table>
            </div>
            <!-- <a href="OrdersPage.html" class="u-btn u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-1">Update</a>
            <div class="u-form u-form-1">
              <form action="https://forms.nicepagesrv.com/Form/Process" class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form" source="email" name="form" style="padding: 10px;">
                <div class="u-form-group u-form-select u-label-none u-form-group-1">
                  <label for="select-92ef" class="u-label">Dropdown</label>
                  <div class="u-form-select-wrapper">
                    <select id="select-92ef" name="select" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                      <option value="Select Below">Select Below</option>
                      <option value="OrderAccepted">OrderAccepted</option>
                      <option value="OrderShipped">OrderShipped</option>
                      <option value="OrderReached">OrderReached</option>
                      <option value="OrderDelivered">OrderDelivered</option>
                      <option value="OrderRejected">OrderRejected</option>
                    </select>
                    <svg class="u-caret u-caret-new" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="16px" height="16px" viewBox="0 0 16 16" style="fill:currentColor;" xml:space="preserve"><polygon class="st0" points="8,12 2,4 14,4 "></polygon></svg>
                  </div>
                </div>
                <div class="u-form-send-message u-form-send-success"> Thank you! Your message has been sent. </div>
                <div class="u-form-send-error u-form-send-message"> Unable to send your message. Please fix errors then try again. </div>
                <input type="hidden" value="" name="recaptchaResponse">
                <input type="hidden" name="formServices" value="38d61f1ef11e1d611171985d1e1bee48">
              </form>
            </div> -->
          </div>
        </section>
        
        
        <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-b081"><div class="u-clearfix u-sheet u-sheet-1">
            <p class="u-small-text u-text u-text-variant u-text-1">Sample text.</p>
          </div></footer>
       
    </body></html>