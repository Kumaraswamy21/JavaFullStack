<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.osw.customer.model.Customer"%>
<%@page import="com.osw.product.model.Category"%>
<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="description" content="">
<title>Home</title>

<meta name="theme-color" content="#478ac9">
<meta property="og:title" content="Home">
<meta property="og:description" content="">
<meta property="og:type" content="website">
<style>
     .exception{
        margin-top: 150px;
    }
    a{
       
        margin-bottom: 20px;
        padding: 20px;
        background-color: white;
        color: black;
    }
</style>
</head>
<body class="u-body u-xl-mode" data-lang="en" style="background-color: #c4baca;">
    <div class="exception">
   <center>
    <h3>${ExceptionMessage}</h3><br>
    <a href="customerLoginController">Login</a><br><br><br><br>
    <a href="customerSignup">SignUp</a><br><br><br><br>
    <a href="backToCustomerHomepage">Back to Home</a>
</center>
</div>
 
  </body></html>