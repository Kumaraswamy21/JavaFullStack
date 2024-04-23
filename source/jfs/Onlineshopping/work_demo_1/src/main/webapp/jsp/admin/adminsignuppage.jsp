<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Admin SignUp Page</h1><br><br>
    <form action="adminsignup">   
            UserName:<input type="text" name="adminUserName" required><br><br>
            ContactNo:<input type="text" name="contactNo" required><br><br>
            Email:<input type="text" name="adminEmail" required><br><br>
            Password:<input type="text" name="adminPassword" required><br><br>
            confirm Password:<input type="text" name="adminconfirmPassword" required><br><br>
        <button type="submit">SignUp</button>
       </form>
</body>
</html>