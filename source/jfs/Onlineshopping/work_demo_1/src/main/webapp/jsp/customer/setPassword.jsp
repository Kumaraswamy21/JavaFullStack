<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Sample Product">
    <meta name="description" content="">
    <title>ForgotPassword</title>
    <link rel="stylesheet" href="css/customer/styleC_1.css" media="screen">
<link rel="stylesheet" href="css/customer/ForgotPassword.css" media="screen">
    <script class="u-script" type="text/javascript" src="js/customer/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="js/customer/oswscriptC.js" defer=""></script>
    <meta name="generator" content="Nicepage 4.19.3, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    <link id="u-page-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Alata:400">
    
    
    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "Site2"
}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="ForgotPassword">
    <meta property="og:description" content="">
    <meta property="og:type" content="website">
  </head>
  <body class="u-body u-xl-mode" data-lang="en">
    <section class="u-align-center u-clearfix u-custom-color-14 u-section-1" id="carousel_f3a3">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <h2 class="u-custom-font u-text u-text-body-color u-text-default u-text-1">Set Password</h2>
        <div class="u-form u-form-1">
            <center>
            
                <form action="forgotPasswordCustomer">  
                    <input type="text" name="custId" value="${customerId}" hidden>                
                          New Password:<input type="password" name="custPassword" placeholder="Enter New Password" required /><br><br> ${passwordValid}
                         Confirm Password:<input type="password"  name="confirmPassword" placeholder="Enter ConfirmPassword" required/><br><br>       
                          <button type="submit">Change Password</button>      
                        </form>
    </center>
        </div>
        
      </div>
    </section>
    
    
    
    
  
</body></html>