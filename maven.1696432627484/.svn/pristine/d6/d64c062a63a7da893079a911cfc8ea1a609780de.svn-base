<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${contextPath}/assets/js/plugin/jquery_autocomplete/jquery.autocomplete.css" />

<script src="${contextPath}/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${contextPath}/assets/js/plugin/jquery_autocomplete/jquery.autocomplete.min.js"></script>
<script src="${contextPath}/assets/appJs/framework/englishTokenizer.js"></script>

<script src="${contextPath}/assets/appJs/admin/aclConfig/aclConfig.js"></script>
<div class="content">
		<div class="panel-header bg-primary-gradient">
				<div class="page-inner py-4">
						<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
								<div>
										<h2 class="text-blue pb-2 fw-bold">User Access</h2>

								</div>
								<div class="ml-md-auto mb-4 py-2 py-md-0">
										<a href="${contextPath}/home" class="btn btn-sm btn-border btn-blue btn-round mr-2">
												<i class="fa fa-home"></i>
										</a>
										<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">
												/
												<spring:message code="site.administration" />
										</a>
										<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/ User Access</a>
								</div>
						</div>
				</div>
		</div>
		<div class="page-inner mt-1 pb-0">
				<div class="row mt-2">
						<div class="col-md-12">
								<div class="card full-height">
										<div class="card-header">
												<div class="panel-actions">
														<a href="#" class="fa fa-caret-down"></a>
												</div>
												<h4 class="card-title">
														<spring:message code="site.admin.acl.configure" />
												</h4>
										</div>
										<div class="card-body">
												<div class="row">

														<div class="col-md-12">
																<div class="col-md-4">
																		<div class="form-group">
																				<label class="col-md-12 required" for="userName">
																						<spring:message code="site.admin.user.id"></spring:message>
																				</label>
																				<div class="col-md-12">
																						<input type="hidden" id="userId" name="userId" />
																						<input type="text" id="userName" name="userName" class="form-control form-control-sm" autocomplete="off">
																				</div>
																		</div>
																</div>
																<div class="col-md-3">
																		<div class="form-group">
																				<label class="col-md-12 required" for="roleId">
																						<spring:message code="site.admin.acl.role"></spring:message>
																				</label>
																				<div class="col-md-12">
																						<select class="form-control form-control-sm" id="roleId" name="roleId" disabled>
																								<option value="" selected="selected">--SELECT--</option>
																						</select>
																				</div>
																		</div>
																</div>
																<div class="col-md-3">
																		<div class="form-group">
																				<label class="col-md-12 required" for="accessLevel">
																						<spring:message code="site.admin.acl.level"></spring:message>
																				</label>
																				<div class="col-md-12">
																						<select class="form-control form-control-sm" id="accessLevelId" name="accessLevelId" disabled>
																								<option value="" selected="selected">--SELECT--</option>
																						</select>
																				</div>
																		</div>
																</div>
																<div class="col-md-2">
																		<div class="form-group">
																				<label class="col-md-12" for="accessLevel">&nbsp;</label>
																				<div class="col-md-12">
																						<button class="btn btn-primary btn-sm form-control form-control-sm" id="btnSearch">Go</button>
																				</div>
																		</div>
																</div>
														</div>
														<!-- <div class="col-md-12">
							<div class="col-md-12 table-responsive" id="pnl">
                      
                    		 </div>
						</div> -->
												</div>
										</div>
								</div>
						</div>
				</div>
				<div class="row mt-2">
						<div class="col-md-12">
								<div class="card full-height">
										<div class="card-header">
												<div class="panel-actions">
														<a href="#" class="fa fa-caret-down"></a>
												</div>
												<h4 class="card-title">&nbsp;</h4>
										</div>
										<div class="card-body">
												<div class="col-md-12 table-responsive">
														<!--  BEG Custom Dt Header -->
														<div class="dt-header">
																<div class="dt-header-left">
																		Show
																		<select class="dt-control" id="pageSize">
																				<option value="10">10</option>
																				<option value="25">25</option>
																				<option value="50">50</option>
																				<option value="100">100</option>
																		</select>
																		entries
																</div>
																<div class="dt-header-right">
																		Search :
																		<input type="text" class="dt-control" id="searchTerm" name="searchTerm" value="" />
																</div>
														</div>
														<!-- END Custom Dt Header -->
														<div id="pnl">No data to display.</div>
														<!--  BEG Custom Dt Footer -->
														<div class="dt-footer">
																<div class="dt-footer-left">
																		Showing page
																		<span id="dt-counter"></span>
																</div>
																<div class="dt-footer-right">
																		<a href="#" id="dt-start" class="dt-navigate">First</a>
																		<a href="#" id="dt-previous" class="dt-navigate">Previous</a>
																		<a href="#" id="dt-next" class="dt-navigate">Next</a>
																		<a href="#" id="dt-end" class="dt-navigate">Last</a>
																</div>
														</div>
														<!-- END Custom Dt Header -->
												</div>
										</div>
								</div>
						</div>
				</div>
		</div>
</div>
<script type="text/javascript">
	$().ready(function(){
		var aclConfigHelper = new ACLConfigPageController({ 
			csrf : '${_csrf.token}'
		});
		
		aclConfigHelper.setupAutoComplete();
		
		$(document).on('change','.x-config', function(){
			aclConfigHelper.saveConfig(this);
		});
		
		$('#btnSearch').click(function(){
			aclConfigHelper.fetchConfigData();
		});

	});
</script>