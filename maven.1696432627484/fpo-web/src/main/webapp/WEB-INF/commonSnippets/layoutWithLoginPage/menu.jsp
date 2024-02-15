<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<sec:authentication var="principal" property="principal" />
		<!-- Sidebar -->
	<div class="sidebar">

      <ul class="profile-box">
        <li><img src="${contextPath}/assets/image/avtarr.png" alt="" class="img-fluid"></li>
        <li>
          <h5 class="user-name">Soumya Ranjan</h5>
          <h6 class="role">Admin</h6>
        </li>
      </ul>
      <ul class="nav-links">
        <li class="active">
          <a href="dashboard.html">
            <i class='bx bx-grid-alt'></i>
            <span class="link_name">Dashboard</span>
          </a>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-pointer'></i>
              <span class="link_name">FPO Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="Fpomanagement_addfpo.html">Add FPO</a></li>
            <li><a href="fpomanagement_membermanagement.html">Member Management</a></li>
            <li><a href="fpomanagement_marketlinkage.html">Market Linkage</a></li>
            <li><a href="fpomanagement_viewfpo.html">View FPO</a></li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-user-plus'></i>
              <span class="link_name">Farmer Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="farmerManagement_memberRegistration_register.html">Member Registration</a></li>
            <li><a href="farmerManagement_memberRegistration_viewMember.html">Member List</a></li>
            <li><a href="farmerManagement_addFarmer.html">Add Farmers</a></li>
            <li><a href="farmerManagement_viewFarmer.html">View Farmers</a></li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="#">
              <i class='bx bx-crop'></i>
              <span class="link_name">Farm Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">

            <li><a href="farmermanagement_addfarm.html">Add Farm</a></li>
            <li><a href="farmermanagement_viewfarm.html">View Farm</a></li>

          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-message-square-dots'></i>
              <span class="link_name">Human Resource</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="humanresources_addpersonnel.html">Add Personnel</a></li>
            <li><a href="humanresources_viewpersonal.html">View Personnel</a></li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-copy'></i>
              <span class="link_name">Production Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="javascript:void(0)">Pre-Production</a>
              <i class='bx bxs-chevron-right-circle' style="position: absolute; top: -10px; right: 0;"></i>
              <ul class="sub-sub-menu">
                <li><a href="productionmanagement_preproduction.html">Add Details</a></li>
                <li><a href="productionmanagement_pre_viewdetails.html">View Details</a></li>
              </ul>

            </li>
            <li><a href="javascript:void(0)">Post Production</a>
              <i class='bx bxs-chevron-right-circle' style="position: absolute; top: -10px; right: 0;"></i>
              <ul class="sub-sub-menu">
                <li><a href="productionmanagement_postproduction.html">Add Details</a></li>
                <li><a href="productionmanagement_post_viewdetails.html">View Details</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-home-smile'></i>
              <span class="link_name">Training Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="trainingmanagement_addtraining.html"> Add Training Information</a></li>
            <li><a href="trainingmanagement_viewtraining.html"> View Training Information </a></li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-repost'></i>
              <span class="link_name">Procurement</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="addprocurement.html">Add Procurement</a></li>
            <li><a href="viewprocurement.html">View Procurement</a></li>
          </ul>
        </li>

        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-coin-stack'></i>
              <span class="link_name">Inventory</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="addinventory.html">Add Inventory</a></li>
            <li><a href="viewinventory.html">View Inventory</a></li>
          </ul>
        </li>

        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-poll'></i>
              <span class="link_name">Asset Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="javascript:void(0)">Asset Configuration</a></li>
            <li><a href="assetmanagement_manageasset.html">Manage Asset</a></li>
          </ul>
        </li>

        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-rupee'></i>
              <span class="link_name">Fund Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="fundmanagement_fundreceived.html">Fund Received</a></li>
            <li><a href="fundmanagement_UCSubmission.html">UC Submission</a></li>
          </ul>
        </li>

        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-receipt'></i>
              <span class="link_name">Finance Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="financemanagement_manageSales.html">Manage Sales</a></li>
            <li><a href="financemanagement_manageExpenditure.html">Manage Expenditure</a></li>

          </ul>
        </li>

        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-message-square-dots'></i>
              <span class="link_name">Reports</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="javascript:void(0)">Reports</a></li>
            <li><a href="javascript:void(0)">Other Report</a></li>
          </ul>
        </li>
      </ul>
    </div>
		
		<%-- <div class="sidebar sidebar-style-2">
            <div class="sidebar-wrapper scrollbar scrollbar-inner">
                <div class="sidebar-content">
                    <div class="user">
                        <div class="avatar-sm float-left mr-2">
							<img id="uph" src="${contextPath}/assets/img/man.png" style="border: 2px solid #ffffff;" class="avatar-img rounded-circle"
							 aria-hidden="true" alt="User Icon"/>
						</div>
						<div class="info">
							<a data-toggle="collapse" href="#collapseExample" aria-expanded="true">
								<span>
									${principal.dbUser.firstName} ${principal.dbUser.lastName}
									<span class="user-level">${principal.dbUser.designation}</span>
									<span class="caret"></span>
								</span>
							</a>
							<div class="clearfix"></div>
							
							<div class="collapse in" id="collapseExample">
                                <ul class="nav">
                                    <li>
                                        <a href="${contextPath}/umt/user/profile">
                                            <span class="link-collapse">My Profile</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${contextPath}/umt/user/change/password">
                                            <span class="link-collapse">Change Password</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>

						</div>
					</div>
							
					<ul class="nav nav-primary">
						<sec:authorize access="isAuthenticated()">
							<c:forEach items="${sessionScope.USER_MENUS}" var="node">
								<c:if test="${node.isDisplay eq true}">
									<li class="nav-item">
										<c:choose>
											<c:when test="${node.isParent eq true}">
												<a data-toggle="collapse" href="#mnu${node.menuId}">
													<i class="${node.menuIcon}"></i>
													<p> ${node.menuText}</p>
													<span class="caret"></span>
												</a>
												<div class="collapse" id="mnu${node.menuId}">
													<ul class="nav nav-collapse">
														<c:forEach items="${node.children}" var="menu">
															<c:set var="node" value="${menu}" scope="request" />
															<jsp:include page="node.jsp" />
														</c:forEach>
													</ul>
												</div>
											</c:when>
											<c:when test="${node.isParent eq false}">
												<a href="${contextPath}${node.menuURL}">
													<i class="${node.menuIcon}"></i>
													<p>${node.menuText}</p>
												</a>
											</c:when>
										</c:choose>
									</li>
								</c:if>
							</c:forEach>
						</sec:authorize>
					</ul>
					
				</div>
			</div>
		</div> --%>