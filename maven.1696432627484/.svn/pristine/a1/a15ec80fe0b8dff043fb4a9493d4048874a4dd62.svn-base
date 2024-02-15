<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="content">
	<div class="panel-header bg-primary-gradient">
		<div class="page-inner py-4">
			<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
				<div>
					<h2 class="text-blue pb-2 fw-bold">Change Password</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
					<a href="${contextPath}/home" class="btn btn-sm btn-border btn-blue btn-round mr-2">
					<i class="fa fa-home"></i>
				</a>
				<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/ Change Password</a>
			</div>
		</div>
	</div>
</div>
	<div class="page-inner mt-2">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outer">
					<div class="card-header">
						<h4 class="card-title">Change Password</h4>
					</div>
					<div class="card-body">
							<div class="col-md-12">
								<%@ include file="/WEB-INF/views/message.jsp"%>
								<form class="form-horizontal" action="${contextPath}/reset/umt/user/change/password/submit" method="POST" onsubmit="return validateSubmit();">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									
										<div class="form-row align-items-end">
											<div class="col">
												<div class="form-group">
													<label class="col-md-12">User Id: </label>
													<div class="col-md-12">
														<input type="text" class="form-control" name="userId" id="userId" value="${userDetails.userName}" readonly>
													</div>
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label class="col-md-12">Current Password: </label>
													<div class="col-md-12">
														<input type="password" class="form-control" name="currPass" id="currPass" maxlength="15" required="required">
													</div>
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label class="col-md-12">New Password: </label>
													<div class="col-md-12">
														<input type="password" class="form-control" name="txtPass" id="txtPass" maxlength="15" onchange="ensureStrongPassword(this);" required="required">
													</div>
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label class="col-md-12">Confirm Password: </label>
													<div class="col-md-12">
														<input type="password" class="form-control" name="txtRePass" id="txtRePass" maxlength="15" onblur="clearData();"> 
														<span id="consms" style="font-size: 14px; font-style: italic;"></span>
													</div>
												</div>
											</div>
											<div class="col">
												<div class="text-center mt-2">
													<input type="submit" class="btn btn-success" value="Change Password" />
												</div>
											</div>
											
											<div class="col-md-12" style="margin-top: 10px;    padding: 0 10px;">
												<div class="alert alert-info" id="lblPass" style="text-align: left;">
													Password must be between 8 - 15 characters <br /> - Must
													have one lower case alphabet. <br /> - Must have one upper
													case alphabet. <br /> - Must have one number. <br /> - Must
													have one special sign (@/*/#)
												</div>
											</div>
											<div class="col-md-12">
												<div class="text-center">
													<a href="${contextPath}/" type="button" class="btn btn-dark">Back</a>
												</div>
											</div>
										</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/assets/appJs/encrypt/AesUtil.js"></script>
<script src="${pageContext.request.contextPath}/assets/appJs/encrypt/aes.js"></script>
<script src="${pageContext.request.contextPath}/assets/appJs/encrypt/pbkdf2.js"></script>
<script src="${pageContext.request.contextPath}/assets/appJs/encrypt/lbase.js"></script>
<script>
	var count = 0;
	$("#txtRePass").keyup(function() {
		var pass = $("#txtPass").val();
		var rePass = $("#txtRePass").val();
		if ((rePass == null || rePass == "")) {
			$("#consms").text(" ");
			$("#consms").css("color", "black");
		} else {
			if (pass == rePass) {
				$("#consms").text(" ");
				$("#consms").css("color", "green");
				count = 1;
			} else {
				$("#consms").text("Password not match..!!!");
				$("#consms").css("color", "red");
				count = 0;
			}
		}
	});

	$('#txtPass').keyup(function() {
		var pass = ensureStrongPassword(this);
		/* 	console.log (pass); */
		if (pass !== true) {
			$('#lblPass').removeClass('alert-info');
			$('#lblPass').addClass('alert-danger');
		} else {
			$('#lblPass').removeClass('alert-danger');
			$('#lblPass').addClass('alert-info');
		}
	});

	function clearData() {
		if (count == 0) {
			$("#txtRePass").val("");
		}
	}
	function ensureStrongPassword(element) {
		var re = /^([a-zA-Z0-9@*#]{8,15})$/;
		var sc = /[-!$%^&*()_+|~=`{}[:;<>?,.@#\]]/g;
		var letters = element.value;
		var uperCaseFlag = false;
		var lowerCaseFlag = false;
		var numberFlag = false;
		var spCharFlag = false;
		var lengthFlag = false;

		for (var i = 0; i < letters.length; i++) {
			if (letters[i] === letters[i].toUpperCase()) {
				uperCaseFlag = true;
			}
			if (letters[i] === letters[i].toLowerCase()) {
				lowerCaseFlag = true;
			}
			var matches = letters[i].match(/\d+/g);
			if (matches != null) {
				numberFlag = true;
			}
			if (letters[i].match(sc)) {
				spCharFlag = true;
			}
			if (letters.match(re)) {
				lengthFlag = true;
			}
		}
		if (uperCaseFlag == true && lowerCaseFlag == true && numberFlag == true
				&& spCharFlag == true && lengthFlag == true) {
			return true;
		} else {
			return false;
		}
	}

	function validateSubmit() {
		let txtP = $('#txtPass').val();
		var currPass= $("#currPass").val();
		if(currPass == ""){
			bootbox.alert('Please Enter your current password');
			return false;
		}	
		if (txtP != '') {
			let txtRP = $("#txtRePass").val();
			if (txtP !== txtRP) {
				bootbox.alert('Passwords do not match');
				return false;
			}
			var elem = document.getElementById('txtPass');
			var ok = ensureStrongPassword(elem);
			if (!ok) {
				bootbox.alert('Password does not match Password Criteria.');
				return false;
			}
			$('#txtPass').val(enc_password(txtP));
			$('#txtRePass').val(enc_password(txtRP));
			$("#currPass").val(enc_password(currPass));
			return ok;
		}
	}
</script>

