

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <title>Registration Form in HTML with Validation Demo</title>

  
	<link rel="stylesheet" type="text/css" href="css/customer/form.css" />
    <style>
       
    </style>
</head>
<body>
		

     
<div class="signupForm">
    <div class="wrapper"  >
    <div class="wrapleft">
        <h1>Register Form</h1>
    </div>
    <div class="wrapright">
        <form:form action="customerRegister" modelAttribute="customerAttribute">
            
            
            <div class="wrapInput">
              <form:input type="text" name="custFirstName" id="custFirstName" path="custFirstName" required="true"/>
                <form:label for="FirstName" path="custFirstName" >FirstName</form:label>
                <form:errors style="color:red; font-size:smaller;" path="custFirstName" ></form:errors>
            </div>
            <div class="wrapInput">
                <form:input type="text" name="custLastName" id="custLastName" path="custLastName" required="true"/><br><br>
                <form:label for="LastName" path="custLastName">LastName</form:label>
                <form:errors path="custLastName" style="color:red; font-size:smaller;"></form:errors>
            </div>
            <div class="wrapInput">
              <form:input type="text" name="custPhone" id="custPhone" path="custPhone" required="true"/>
                <form:label for="custPhone" path="custPhone">Contact No</form:label>
                    <form:errors path="custPhone" style="color:red; font-size:smaller;"></form:errors>
            </div>
            <div class="wrapInput">
              <form:input type="email" name="custEmail" id="custEmail" path="custEmail" required="true"/>
                <form:label for="email" path="custEmail">email</form:label>
                <form:errors path="custEmail" style="color:red; font-size:smaller;"></form:errors>
            </div>
            <div class="wrapInput">
              <form:input type="password" name="custPassword" id="custPassword" path="custPassword" required="true"/>
                <form:label for="password" path="custPassword">Password</form:label>
                <form:errors path="custPassword" style="color:red; font-size:smaller;"></form:errors>
            </div>
            <div class="wrapInput">
                <form:input type="password" name="custConfirmPassword" id="custConfirmPassword" path="custConfirmPassword" required="true"/>
                  <form:label for="custConfirmPassword" path="custConfirmPassword">Confirm Password</form:label>
                  <form:errors path="custConfirmPassword" style="color:red; font-size:smaller;"></form:errors>
              </div>
            <div class="wrapInput">
                <input type="submit" name="submit" value="Register" />
            </div>
              <p style="color:black;">Already User?</p><a href="customerLoginController">Login</a>
      
           
        </form:form>
    </div>
    </div>
</div>

    

	</body>
</html>