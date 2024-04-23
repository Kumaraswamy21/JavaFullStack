<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="contact form">
    <meta name="description" content="">
    <title>Login</title>
    <link rel="stylesheet" href="css/customer/styleC_1.css" media="screen">
<link rel="stylesheet" href="css/customer/Login.css" media="screen">
    <script class="u-script" type="text/javascript" src="js/customerjquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="js/customeroswscriptC.js" defer=""></script>
    <meta name="generator" content="Nicepage 4.19.3, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    
    
    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "Site2"
}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="Login">
    <meta property="og:description" content="">
    <meta property="og:type" content="website">
  </head>
  <body class="u-body u-xl-mode" data-lang="en">
    <section class="u-clearfix u-valign-middle-xl u-white u-section-1" id="carousel_026e">
      <div class="u-expanded-height-lg u-expanded-height-md u-expanded-height-xl u-expanded-width-sm u-expanded-width-xs u-shape u-shape-rectangle u-white u-shape-1"></div>
      <div class="u-clearfix u-gutter-0 u-layout-wrap u-layout-wrap-1">
        <div class="u-gutter-0 u-layout">
          <div class="u-layout-row">
            <div class="u-size-28">
              <div class="u-layout-col">
                <div class="u-align-left u-container-style u-image u-layout-cell u-left-cell u-size-60 u-image-1" data-image-width="528" data-image-height="260">
                  <div class="u-container-layout u-container-layout-1"></div>
                </div>
              </div>
            </div>
            <div class="u-size-32">
              <div class="u-layout-row">
                <div class="u-container-style u-layout-cell u-right-cell u-size-60 u-layout-cell-2">
                  <div class="u-container-layout u-container-layout-2">
                    <h2 class="u-text u-text-default u-text-1">Login</h2>
                    <div class="u-expanded-width u-form u-form-1">
                      <form action="customerLogin" class="u-block-e138-21 u-clearfix u-form-spacing-25 u-form-vertical u-inner-form" ><!-- hidden inputs for siteId and pageId -->
                        <div class="u-form-group u-form-name u-label-none">
                          <label for="custUsername" path="custUsername" class="u-form-control-hidden u-label">Name</label>
                          <input type="text" placeholder="Enter your UserName" id="custUsername" path="custUsername" name="custUsername" class="u-border-no-bottom u-border-no-left u-border-no-right u-border-no-top u-grey-5 u-input u-input-rectangle" required="true"/>
                          <!-- <form:errors path="custUsername" cssClass="error"/> -->
                        </div><!-- always visible -->
                        <div class="u-form-group u-label-none u-form-group-2">
                          <label for="custPassword" path="custPassword" class="u-label">Input</label>
                          <input type="password" placeholder="Enter your Password" id="custPassword" path="custPassword" name="custPassword" class="u-border-no-bottom u-border-no-left u-border-no-right u-border-no-top u-grey-5 u-input u-input-rectangle" required="true"/>
                          <!-- <form:errors path="custPassword" cssClass="error"/> -->
                        </div>
                        <p style="color:red">${ExceptionMessage}</p>
                        <div class="u-form-group u-form-submit u-label-none">
                          <input type="submit" value="Login" class="u-btn u-btn-submit u-button-style u-hover-palette-1-dark-1 u-palette-1-base u-btn-1"/>
                        </div>
                       
                      </form>
                    </div>
                    <p class="u-text u-text-2">Don't have an account?&nbsp;<a href="customerSignup" class="u-active-none u-border-1 u-border-active-palette-1-base u-border-black u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-btn u-button-link u-button-style u-hover-none u-none u-text-custom-color-11 u-btn-2" >Signup</a>
                    </p>
                    <p class="u-text u-text-3">Forgot Password?&nbsp;<a href="validateCustUsername" class="u-active-none u-border-1 u-border-active-palette-1-base u-border-black u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-btn u-button-link u-button-style u-hover-none u-none u-text-custom-color-11 u-btn-3">Click</a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  
</body></html>