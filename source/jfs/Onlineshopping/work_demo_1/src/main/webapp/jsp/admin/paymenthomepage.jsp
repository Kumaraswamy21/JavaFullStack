<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="com.osw.order.model.Payment"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <a href="addPaymentMethod">Add Payment Method</a>
    <c:forEach items="${paymentlist}" var="list">
        <c:out value="${list.paymentType}" />  
</c:forEach>
<a href="adminhomepage">Back</a>
</body>
</html>