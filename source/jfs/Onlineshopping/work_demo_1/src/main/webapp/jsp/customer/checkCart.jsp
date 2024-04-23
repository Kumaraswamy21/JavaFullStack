<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.osw.product.model.Category"%>
    <%@page import="com.osw.product.model.Product"%>
    <%@page import="com.osw.customer.model.Customer"%>
    <%@page import="com.osw.customer.model.Cart"%>
    <%@page import="com.osw.customer.model.CartLine"%>
    <%@page import="com.osw.order.model.Order"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    
    </head>
    <body>
        <%Customer customer=(Customer)session.getAttribute("customersession");%>
        <c:forEach items="${orderlist}" var="list">
            <c:forEach items="${list.product}" var="product">
                ${product.prodName}
                ${list.status}
            </c:forEach>
        </c:forEach>
    </body>
    </html>