<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.osw.product.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <!-- <img src="${product.prodImage}"/> <br> -->
   <%Product product=(Product)session.getAttribute("productsession");%><br> 
   Product Id:<%=product.getProdId()%><br>
   Product Name:<%=product.getProdName()%><br>
   Product Code:<%=product.getProdCode()%><br>
   Product Price:<%=product.getProdPrice()%><br>
   Product Type:<%=product.getProdType()%><br> 
   Product Brand:<%=product.getProdBrand()%><br>
   Product Quantity:<%=product.getProdQuantity()%><br>  
   Product Status:<%=product.isProdStatus()%><br>
   Product Description:<%=product.getDescription()%><br>
   Product Review:<%=product.getProdReview()%><br>


   <a href ="singleProduct1">Back</a>   <br>
       
</body>
</html>