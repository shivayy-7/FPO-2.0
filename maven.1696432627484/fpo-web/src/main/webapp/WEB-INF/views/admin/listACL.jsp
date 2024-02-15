
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<div class="content">
	<div class="panel-header bg-primary-gradient">
		<div class="page-inner py-4">
			<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
				<div>
					<h2>List Access Level Master</h2>
					
				</div>
				<div class="ml-md-auto mb-4 py-2 py-md-0">
					<a href="${contextPath}/home" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a>
					<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/ Administration</a>
					<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/ List Access Level Master</a>
				</div>
			</div>
		</div>
	</div>
	<div class="page-inner mt-5 pb-0">
		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>
						<h4 class="card-title">Access Level Masters</h4>
					</div>
					<div class="card-body">
						<div class="row">
						
							<div class="col-md-12">
								<form class="form-horizontal"
							action="${contextPath}/"
							method="POST" id="userForm">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
							<input type="hidden" name="mstLevelId"  />
							<div class="col-md-12" style="padding-top: 15px;">
								<div class="col-sm-12">
									<div>
										<table class="DataTable table table-bordered table-sm">
											<thead>
												<tr>
													<th>Sl #</th>
													<th>Display Name</th>
													<th>Level Code</th>
													<th>Source Entity</th>
													<th>View Entity</th>
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${lstAccessLevels}" var="acl"
													varStatus="cnt">
													<tr>
														<td>${cnt.count}</td>
														<td>${acl.displayName}</td>
														<td>${acl.levelCode}</td>
														<td>${acl.levelEntityName}</td>
														<td>${acl.displayViewName}</td>
														<td></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="card-footer text-center mt-2">
							<input type="button"
									class="btn btn-success btn-sm" value="Submit"
									onclick="return validateForm()">
								<a href="${contextPath}/admin/role/list.htm" type="button"
									class="btn btn-warning btn-sm">Back</a> 
							</div>
						</form>
							</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
