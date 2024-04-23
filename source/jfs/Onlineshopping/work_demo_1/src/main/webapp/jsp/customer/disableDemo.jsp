<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.osw.product.model.Category"%>
    <%@page import="com.osw.product.model.Product"%>
    <%@page import="com.osw.order.model.Order"%>
    <%@page import="com.osw.customer.model.Customer"%>
    <%@page import="com.osw.customer.model.Cart"%>
    <%@page import="com.osw.customer.model.CartLine"%>
    <!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
  </head>
  <body>
    <% if(wishlistStatus){%>
      <a href="removeFromWishlist"  class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-1" id="btn1" onclick="clickAndDisable(this);" >Remove</a>
    <%} else{%>
      <a href="addToWishlist"  class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-1" id="btn1" onclick="clickAndDisable(this);" >wishlist</a>
    <% } %>
    <% if(cartStatus && availableStatus){%>
      <a href="removeFromCart" style="margin-left: 30px; margin-top: 30px;" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Remove From Cart</a>
          <%} else if(availableStatus && !(cartStatus)){%>
      <a href="addToCart" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Add to Cart</a>
          <% } %>
          <%} else if(!(availableStatus)){%>
            <a href="" style="opacity: 0.5;" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Add to Cart</a>
            <% } %>

    </c:forEach>
  </body>
  </html>

  <% if(cartStatus && availableStatus){%>
    <a href="removeFromCart" style="margin-left: 30px; margin-top: 30px;" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Remove From Cart</a>
        <%} else if(availableStatus && !(cartStatus)){%>
    <a href="addToCart" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Add to Cart</a>
        <% } %>
        <%} else if(!(availableStatus)){%>
          <a href="" style="opacity: 0.5;" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Add to Cart</a>
          <% } %>   
          <%} else{%>
            <a href="addToCart" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Add to Cart</a>

          <% } %>  



          <!-- <%} else{%>
                                <a href="addToCart" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Add to Cart</a>

                              <% } %>   -->



                              <% if(cartStatus && availableStatus){%>
                                <a href="removeFromCart" style="margin-left: 30px; margin-top: 30px;" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Remove From Cart</a>
                                    <%} else if(availableStatus && !(cartStatus)){%>
                                <a href="addToCart" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Add to Cart</a>
                                    <%} else if(!(availableStatus)){%>
                                      <a href="" style="opacity: 0.5;" class="u-border-2 u-border-grey-75 u-btn u-button-style u-custom-color-20 u-hover-black u-btn-2">Add to Cart</a>
                                      <% } %>   
                                      

                                      onclick="clickAndDisable(this);"
      

                                      insert into card_db(cardcvv,card_holder_name,card_number,card_type,expiry_month) values("987","Phoebe Buffay","123456789012","credit","2027-07");

                

                                      <form action="https://forms.nicepagesrv.com/Form/Process" class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form" source="email" name="form" style="padding: 10px;">
                                        <div class="u-form-group u-form-radiobutton u-form-group-1">
                                          <div class="u-form-radio-button-wrapper">
                                            <input type="radio" name="radiobutton" value="Home">
                                            <label class="u-label u-label-1" for="radiobutton">Home</label>
                                            <br>
                                          </div>
                                        </div>
                                       
                                      </form>
                                      <div class="u-form-group u-form-textarea u-form-group-2">
                                        <label for="textarea-5662" class="u-label u-label-2">Home Address</label>
                                        <textarea rows="4" cols="50" id="textarea-5662" name="textarea" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required=""></textarea>
                                      </div>
                                    </div>
                                    <h3 class="u-text u-text-8">Please Select Payment Method</h3>
                                    <div class="u-form u-form-2">
                                      <form action="https://forms.nicepagesrv.com/Form/Process" class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form" source="email" name="form-1" style="padding: 10px;">
                                        <div class="u-form-group u-form-radiobutton u-form-group-3">
                                          <div class="u-form-radio-button-wrapper">
                                            <input type="radio" name="radiobutton" value="Online Payment">
                                            <label class="u-label u-label-3" for="radiobutton">Online Payment</label>
                                            <br>
                                            <input type="radio" name="radiobutton" value="Cash On Delivery">
                                            <label class="u-label u-label-4" for="radiobutton">Cash On Delivery</label>
                                            <br>
                                          </div>
                                        </div>
                                        <div class="u-align-left u-form-group u-form-submit">
                                          <a href="PaymentsPage.html" class="u-btn u-btn-submit u-button-style">Proceed</a>
                                          <input type="submit" value="submit" class="u-form-control-hidden">
                                        </div>
                                        
                                      </form>