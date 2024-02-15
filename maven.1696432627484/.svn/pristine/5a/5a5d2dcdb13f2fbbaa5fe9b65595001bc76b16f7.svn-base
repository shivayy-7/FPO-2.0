	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
		<c:set var="userDetails" value="${serviceOutcome.data}"/> 
<div class="content">
	<div class="panel-header bg-primary-gradient">
		<div class="page-inner py-4">
			<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
				<div>
					<h2 class="text-blue pb-2 fw-bold">View Role</h2>
					
				</div>
				<div class="ml-md-auto mb-4 py-2 py-md-0">
					<a href="${contextPath}/home" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a>
					<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/ View Role</a>
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
						<h4 class="card-title">Role Details</h4>
					</div>
					<div class="card-body">
						<div class="row">
						 
							<div class="col-md-12">
								<form class="form-horizontal" action="${contextPath}/core/role/addNupdate" method="POST">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input type="hidden" name="roleId" value="${userDetails.roleId}"/>
										<div class="col-md-12">
											<%-- <div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName">Role Name</label>
													<div class="col-md-12">
														<input type="text" id="roleName" name="roleName" value="${userDetails.roleName}" class="form-control form-control-sm" required="required" readonly="readonly">
													</div>
												</div>
											</div> --%>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName"><spring:message code="site.admin.mst.role.code"></spring:message></label>
													<div class="col-md-12">
														<input type="text" id="roleCode" name="roleCode" value="${userDetails.roleCode}" class="form-control form-control-sm" maxlength="3" required="required" readonly="readonly">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintAge"><spring:message code="site.admin.mst.role.display.name"></spring:message></label>
													<div class="col-md-12">
														<input type="text" id="displayName" name="displayName" value="${userDetails.displayName}" class="form-control form-control-sm" required="required" readonly="readonly">
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required" for="Email"><spring:message code="site.admin.mst.role.description"></spring:message></label>
													<div class="col-md-12">
														<input type="text" name="description" id="description" class="form-control form-control-sm"  value="${userDetails.description}" required="required" readonly="readonly">  
													</div>
												</div>
											</div> 
											<div class="col-md-2">
												<div class="form-group">
													<label class="col-md-12 required" for="Email"><spring:message code="site.admin.mst.role.maxassignments"></spring:message></label>
													<div class="col-md-12">
														<input type="number" name="description" id="description" class="form-control form-control-sm"  value="${userDetails.maxAssignments}" required="required" readonly="readonly">  
													</div>
												</div>
											</div> 
										</div>
									<div class="card-footer text-center mt-2">
									<a
										href="${contextPath}/admin/role/list"
										type="button" class="btn btn-warning btn-sm">Back</a>
											<!-- <input type="submit" value="submit"
										 class="btn btn-success"> -->
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
	<script >
		$(document).ready(function() {
			$('#basic-datatables').DataTable({
			});
		});
	</script>
	
