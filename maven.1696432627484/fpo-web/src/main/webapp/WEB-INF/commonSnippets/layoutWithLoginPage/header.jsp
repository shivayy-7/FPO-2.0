
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<sec:authentication var="principal" property="principal" />

<div id="loader" class="lds-dual-ring hidden overlay"></div>
   <div class="home-content">
     <i class='bx bx-menu'><img src="${contextPath}/assets/img/menuicon.png"></i>
     <img src="${contextPath}/assets/image/favicon.png" style="width: 58px;">
     <span class="text">Farmer Producer Organization</span>
     <a href="${contextPath}/logout" class="btn btn-sm btn-danger logout-btn"><i class="bx bx-power-off"></i></a>
   </div>
  <div class="divider">
    <img src="${contextPath}/assets/image/divider.png">
  </div>
      
      
      
<%-- <div class="main-header">
    <div class="logo-header" data-background-color="blue">        
        <button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse" data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon">
                <i class="fas fa-angle-double-left"></i>
            </span>
        </button>
        <button class="topbar-toggler more"><i class="icon-options-vertical"></i></button>
        <div class="nav-toggle">
            <button class="btn btn-toggle toggle-sidebar">
                <i class="fas fa-angle-double-left"></i>
            </button>
        </div>
    </div>

            <nav class="navbar navbar-header navbar-expand-lg" data-background-color="blue2">

                <div class="container-fluid">

                    <a href="${contextPath}/" class="logo">
                        <img src="${contextPath}/assets/img/logo.png" alt="navbar brand" class="navbar-brand">
                    </a>
                    <div class="nav-item color-change">
                        <h1><b>FPO</b> 
                            <span>FARMER PRODUCER ORGANIZATION MANAGEMENT</span>
                        </h1>
                        <h2>
                            Aashdit Technologies
                        </h2>
                    </div>

                    <ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
                        <li class="nav-item dropdown hidden-caret">
                            <a class="nav-link dropdown-toggle" href="#" id="notifDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img id="uph1" src="${contextPath}/assets/img/man.png" style="border: 1px solid #ffffff; width: 35px !important;" class="avatar-img rounded-circle" aria-hidden="true" alt="User Icon">
                                <!-- <span class="notification">3</span> -->
                                <ul class="prof-li">
                                    <li>${principal.dbUser.firstName} ${principal.dbUser.lastName}</li>
                                	<li>${principal.dbUser.designation}</li>
                                </ul>
                            </a>
                            <ul class="dropdown-menu notif-box animated fadeIn" aria-labelledby="notifDropdown">
                                <li>
                                    <div class="dropdown-title">Manage User</div>
                                </li>
                                <li>
                                    <div class="notif-scroll scrollbar-outer">
                                        <div class="notif-center">
                                            <a href="${contextPath}/umt/user/profile">
                                                <div class="notif-icon notif-primary"> <i class="fa fa-user"></i></div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        Profile
                                                    </span>
                                                </div>
                                            </a>
                                            <a href="${contextPath}/umt/user/change/password">
                                                <div class="notif-icon notif-success"> <i class="fa fa-key"></i>
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        Change Password
                                                    </span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </li>
                                <!-- <li>
                                    <a class="see-all" href="javascript:void(0);">See all notifications<i
                                            class="fa fa-angle-right"></i> </a>
                                </li> -->
                            </ul>
                        </li>

                        <li class="nav-item dropdown hidden-caret">
                            <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"
                                aria-expanded="false">
                                <div class="avatar-sm">
                                    <button type="button" class="dropdown-item logout btn btn-danger" href="#" id="btnLogout" style="padding: 4px 5px; border-radius: 20%;">
													<i class="fas fa-power-off" style="font-size: 16px;"></i>
											</button>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- End Navbar -->
        </div> --%>
        
        
 <%-- <div class="main-header">
            <!-- Logo Header -->
            <div class="logo-header" data-background-color="blue">
                <a href="${contextPath}/" class="logo">
                    <span class="navigation">NAVIGATION</span>
                </a>
                <button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse"
                    data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon">
                        <i class="icon-menu"></i>
                    </span>
                </button>
                <button class="topbar-toggler more"><i class="icon-options-vertical"></i></button>
                <div class="nav-toggle">
                    <button class="btn btn-toggle toggle-sidebar">
                        <i class="icon-menu"></i>
                    </button>
                </div>
            </div>
            <!-- End Logo Header -->

            <!-- Navbar Header -->
            <nav class="navbar navbar-header navbar-expand-lg" data-background-color="blue2">

                <div class="container-fluid">

                    <a href="${contextPath}/" class="logo">
                        <img src="${contextPath}/assets/img/logo.png" alt="navbar brand" class="navbar-brand">
                    </a>
                    <div class="nav-item color-change">
                        <h1>Article-275</h1>
                        <!-- <b>Collectorate,Puri</b> -->
                    </div>

                    <ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
                        <li class="nav-item dropdown hidden-caret">
                            <a class="nav-link dropdown-toggle" href="#" id="notifDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-bell"></i>
                                <span class="notification">3</span>
                            </a>
                            <ul class="dropdown-menu notif-box animated fadeIn" aria-labelledby="notifDropdown">
                                <li>
                                    <div class="dropdown-title">You have 3 new notifications</div>
                                </li>
                                <li>
                                    <div class="notif-scroll scrollbar-outer">
                                        <div class="notif-center">
                                            <a href="#">
                                                <div class="notif-icon notif-primary"> <i class="fa fa-user-plus"></i>
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        New user registered
                                                    </span>
                                                    <span class="time">5 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-icon notif-success"> <i class="fa fa-comment"></i>
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        Akash
                                                    </span>
                                                    <span class="time">12 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-img">
                                                    <img src="${contextPath}/assets/img/profile2.jpg" alt="Img Profile">
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        Reza send messages to you
                                                    </span>
                                                    <span class="time">12 minutes ago</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <a class="see-all" href="javascript:void(0);">See all notifications<i
                                            class="fa fa-angle-right"></i> </a>
                                </li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown hidden-caret">
                            <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"
                                aria-expanded="false">
                                <div class="avatar-sm">
                                    <button type="button" class="dropdown-item logout" href="#" id="btnLogout" style="padding: 4px 5px; border-radius: 20%;">
													<i class="fas fa-power-off" style="font-size: 16px;"></i>
											</button>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- End Navbar -->
        </div> --%>
        
        <form method="post" action="${contextPath}/umt/logout" id="frmLogout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" style="display: none" />
		</form>

<script>
	$(function() {

		$('#siteLangSelector').change(function() {
			const lang = $(this).val();
			switchLanguage(lang);
		});

		$('#roleSwitcher').change(function() {
			var roleId = $('#roleSwitcher').val();
			$('#hdnRoleId').val(roleId);
			$('#frmSwitchRole').submit();

		});
	});

	$('.logout').on('click', function() {
		$("#frmLogout").submit();
	});
</script>