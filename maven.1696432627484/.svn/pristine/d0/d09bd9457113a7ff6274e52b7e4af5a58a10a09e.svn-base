<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="icon" type="image/png" sizes="32x32" href="${contextPath}/assets/img/favicon-32x32.png">
<sec:authentication var="principal" property="principal" />

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="UTF-8">
  <title>Dummy Project Setup</title>
  <meta name="keywords" content="Article-275::Department Of SCST" />
  <meta name="description" content="Article-275::Department Of SCST">
  <meta name="author" content="Aashdit Technologies">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/loginPage/images/logo.png">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/loginPage/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/loginPage/css/style.css">
<%--   <script src="${pageContext.request.contextPath}/loginPage/loginJs/login.js"></script> --%>
  	<script src="${pageContext.request.contextPath}/loginPage/js/jquery.min.js"></script>
  	<script src="${pageContext.request.contextPath}/loginPage/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/loginPage/js/custom.js"></script>
	<script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/AesUtil.js"></script>
    <script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/aes.js"></script>
    <script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/pbkdf2.js"></script>
    <script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/lbase.js"></script> 
</head>
<body class="login_page">
<%@ include file="/WEB-INF/views/message.jsp"%>
	<section class="main-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="logo-box">
                            <div class="logo-img">
                                <img src="${pageContext.request.contextPath}/loginPage/images/logo.png" class="img-fluid" />
                            </div>
                            <div class="logo-content">
                                <!-- <h1>L<span>I</span>MS</h1> -->
                                <h4>FARMER PRODUCE ORGANIZATION</h4>
                                <!-- <p>( A Government of Odisha Undertaking )</p> -->
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="login-box">
                            <h2><span>Login Here</span></h2>
                            <!-- <div class="login-fild">
                                <label>Login As...</label>
                                <select class="category">
                                    <option>State Admin</option>
                                    <option>Laboratory</option>
                                    <option selected>Patient</option>
                                </select>
                                <i class='bx bx-category-alt'></i>
                            </div> -->
                            <form class="form-horizontal loginbox" id="login-window" method="POST" action="${contextPath}/overwrite/umt/login" autocomplete="off" >
								<input type="hidden" id="password" name="password" value="">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				              	<div class="login-fild" id="unm">
                                    <label>Username</label>
                                    <input type="text" placeholder="userName" name="userName" id="username" class="user" required autocomplete="off">
                                    <i class='bx bx-user'></i>
                                </div>
                                 <div class="login-fild" id="pas">
                                    <label>Password</label>
                                    <input type="password" placeholder="******"  class="password" name="loginPassword" type="password" id="textPassword" required autocomplete="off">
                                    <i class='bx bxs-key'></i>
                                </div>
				              
				              <div class="login-fild" style="display: flex; padding: 0;justify-content: space-between;">
										
									<input name="captcha" type="text" placeholder="Enter Captcha"
										maxlength="5" id="captcha" class="form-control"
										autocomplete="off" style="wifth:">
									
				
									<div class="captchaImg mt-2">
										<img id="Image1" onclick="refreshCaptcha();" causesvalidation="false" src="${contextPath}/loginPage/image/refresh.png">
									</div>
									<div class=" captcha" onmousedown="return false">
										<img src="${contextPath}/captcha/5" id="captchaImage" />
									</div>
							  </div>
			
				              
                                <div class="login-fild submit-btn">
                                    <button type="button" id="loginSubmitBtn" class="button-style-custom"> Login <!-- <i class='bx bx-navigation'></i> --></button>
                                    <!-- <a href="#">New Registration</a> -->
                                </div>
				            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>


<div id="pdModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Forgot Password ?</h4>
        <span id="SecondsUntilExpire" style="display: none;"></span>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body col-md-12">
        <div class="" id="contents">
        <form class="form-horizontal" id="resetform" action="${contextPath}/reset/forgot-password" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="col-md-12">
            <label>Username<span style="color: #ff0808;">*</span> :</label>
          </div>
           <div class="col-md-12">
            <input type="text" class="form-control" required name="usernm" autocomplete="off" required maxlength="64">
          </div>
          <div class="col-md-12 text-center mt-2">
          	<button style="margin-left: 10%;" type="submit" id="passwordResetBtn" class="btn btn-sm btn-success">Submit</button>
          	<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Cancel</button>
          </div>
          <div class="col-md-12">
          	<label style="margin-top: 10px;font-style: italic;color:#ff0808;font-size: 12px;">Note: Password will be sent to the registered email.</label>
          </div>
        </form>
        </div>
      </div>
      <div class="modal-footer text-center">
      </div>
    </div>

  </div>
</div> 
<script type="text/javascript">

$(document).ready(function(){
    $('form').attr('autocomplete', 'off');
    $(this).bind("contextmenu", function(e) {
    	e.preventDefault();
    	}); 
    $(this).bind('cut copy paste', function (e) {
        e.preventDefault();
    	});
});

	document.onkeydown = function(e) {
		if(event.keyCode == 123) {
			return false;
		}
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){
			return false;
		}
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){
			return false;
		}
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)){
			return false;
		}
		if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){
			return false;
		}
	} 
	
/* 	function getAction() {
		var username = $("#userName").val();
		var password = $("#password").val();
		var captcha = $("#captcha").val();
		if (username == "") {
			alert("Please Enter UserName");
			return false;
		}
		if (password == "") {
			alert("Please Enter Password");
			return false;
		}
		if (captcha == "") {
			alert("Please Enter Captcha");
			return false;
		}
		$("#login-window").submit();
		 $('#loader').removeClass('hidden');

	}; */
	
	$("#loginSubmitBtn" ).on("click", function( event ) {
	  	var username = $("#userName").val();
	  	var password = $("#textPassword").val();
	  	var generateCaptcha=$("#captcha").val();
	    
	  	if (username == "") {
	  		alert("Username Can't Be Empty");
	  		return false;
	  	}
	  	if (password == "") {
	  		alert("Password Can't Be Empty");
	  		return false;
	  	}
	  	if (generateCaptcha == "") {
		    alert("Please enter the CAPTCHA"); 
		    return false;
		}
		if(password != "") {
	  		$('#password').val(enc_password(password)); 
	  		$("#login-window").submit();
	  		return false;
	  	}
	}); 
	
	function refreshCaptcha() {

		var image = document.getElementById("captchaImage");
		image.src = '${contextPath}/captcha/5';

	}
	
	 function encrypt(word, key) {
		 var ciphertext = CryptoJS.AES.encrypt(word, key);
		 return ciphertext.toString();
	    }
	    function decrypt(word, key){  
	       var decrypt = CryptoJS.AES.decrypt(word, key);     
	       return CryptoJS.enc.Utf8.stringify(decrypt).toString();  
	    } 


      //Random_Background_Colour_Change
      var colors = ["blue", "maroon", "green"];
			var rand = Math.floor(Math.random() * colors.length);
			$('.login_content').addClass(colors[rand]);
</script>
 	
    </body>
</html>



