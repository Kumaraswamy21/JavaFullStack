<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Add address</h1><br><br>
    <form action="addAddress">  
        Name:<input type="text" name="name" required><br><br>
        House No:<input type="text" name="houseNo" required><br><br>
        Street:<input type="text" name="street" required><br><br>
            City:<input type="text" name="city" required><br><br>
            State:<input type="text" name="state" required><br><br>
            Country:<input type="text" name="country" required><br><br>
            Postal Code:<input type="text" name="postalCode" required><br><br>
        <button type="submit">Add</button>
       </form>

</body>
</html>