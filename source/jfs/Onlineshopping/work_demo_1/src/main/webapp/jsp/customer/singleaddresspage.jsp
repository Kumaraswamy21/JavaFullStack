<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.osw.customer.model.Address"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <%Address address=(Address)session.getAttribute("addresssession");%><br>
     <%=address.getName()%><br>
    <%=address.getHouseNo()%><br>
    <%=address.getStreet()%><br>
    <%=address.getCity()%><br>
    <%=address.getState()%><br>
    <%=address.getCountry()%><br>
    <%=address.getPostalCode()%><br>
    
    <a href="updateAddressDetails">Update</a>
    <a href="deleteAddress">Delete</a>
    <a href="backToCustomerAddress">Back</a>
</body>
</html>