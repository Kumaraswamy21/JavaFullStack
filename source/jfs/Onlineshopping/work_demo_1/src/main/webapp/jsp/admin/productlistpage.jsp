<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.osw.product.model.Category"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <%Category category=(Category)session.getAttribute("categorysession");%><br>  
    <%=category.getCategoryName()%><br>
    <a href="addProduct">Add Product</a><br><br>
   
${productlist}<br>
   
    <c:forEach items="${productlist}" var="list">
        <c:out value="${list.prodId}" />
        <c:out value="${list.prodName}" />  
        <a href ="viewProduct?prodId=${list.prodId}">view</a>   
        <a href ="editProduct?prodId=${list.prodId}">edit</a>  
        <a href ="deleteProduct?prodId=${list.prodId}">delete</a>
        <br><br>  
</c:forEach>

        <a href="Backtocategorypage">Back to Category</a>
       
</body>
</html>