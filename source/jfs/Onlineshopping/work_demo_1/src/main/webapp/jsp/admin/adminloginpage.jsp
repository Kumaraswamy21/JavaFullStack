<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <!-- Font Awesome -->
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">  
        <!-- Stylish Login Page CSS -->
        <link rel="stylesheet" href="css/login-page.css">
        <style>
    </style>
    </head>
    <body>     
        <form class="codehim-form" action="adminLogin">
            <div class="form-title">
               <div class="user-icon gr-bg">
                  <i class="fa fa-user"></i>
               </div>
               <h2> Admin Login Form</h2>
            </div>
            <label for="username"> Username</label>
            <input type="text" id="adminUserName" name="adminUserName" class="cm-input" placeholder="Enter your Username" required>
            <label for="pass"><i class="fa fa-lock"></i> Password:</label>
            <input id="pass" type="password" name="adminPassword" class="cm-input" placeholder="Enter your password" required>
            <p style="color:red">${ExceptionMessage}</p>
            <button type="submit" class="btn-login  gr-bg">Login</button><br>
            <button  onclick="window.location.href = 'validateadminid';" class="btn-login  gr-bg">           
                ForgotPassword?
             </button>
         </form>     
</body>
</html>