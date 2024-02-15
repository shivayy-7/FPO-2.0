<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<div class="content">
	<div class="panel-header bg-primary-gradient">
		<div class="page-inner py-4">
			<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
				<div>
					<h2 class="text-blue pb-2 fw-bold">Add District</h2>
				</div>
					<div class="ml-md-auto py-2 py-md-0">
						<a href="${contextPath}/home" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
							class="fa fa-home"></i></a> 
						<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/Add District</a>
					</div>
			  </div>
		 </div>
	</div>
		<div class="page-inner">
			<div class="row mt-2">
			 <%@ include file="/WEB-INF/views/message.jsp"%>
				<div class="col-md-12">
					<div class="card full-height">
						<div class="card-header">
							<h4 class="card-title">Add District</h4>
						</div>
						<div class="card-body">
								<form action="${contextPath}/core/district/addNupdate" id="addManageId"	method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<input type="hidden" id = "districtId" name="districtId" value="${districtData.districtId}" />
										<div class="col-md-12">
											<div class="col-md-3">
						                        <div class="form-group">
						                           <label class="col-md-12 required" for="stateId">State :</label>
						                           <div class="col-md-12">
						                              <select name="state.stateId" id="stateId" class="form-control form-control-sm" required="required">
						                                 <option value="" >Select</option>
						                                    <c:forEach items="${stateList}" var="state">
																<option value="${state.stateId}" ${state.stateId eq districtData.state.stateId ? 'selected':''}>${state.stateNameEN}</option>
															</c:forEach> 
						                              </select>
						                           </div>
						                        </div>
						                     </div>  
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="districtNameEN">District Name :</label>
													<div class="col-md-12">
														<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="districtNameEN" name="districtNameEN" value="${districtData.districtNameEN}" required="required" maxlength="150">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="districtCode">District Code :</label>
													<div id="hideCodeId">
													<div class="col-md-12">
														<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="districtCode" value="${districtData.districtCode}"  maxlength="150"
															name="districtCode" required="required" onchange="districtCodeValidation();">
													</div>
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required">District LGD Code :</label>
													<div class="col-md-12">
														<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="districtLgdCode" name="districtLgdCode" value="${districtData.districtLgdCode}" required="required" maxlength="150">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required">District Census Code :</label>
													<div class="col-md-12">
														<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="districtCensusCode" name="districtCensusCode" value="${districtData.districtCensusCode}" required="required" maxlength="150">
													</div>
												</div>
											</div>
											<div class="col-md-3">
						                        <div class="form-group">
						                           <label class="col-md-12 required">District Tribal :</label>
						                           <div class="col-md-12">
						                              <div class="col-md-12 d-flex align-items-center redio-box">
													<input style="margin-right: 5px;" type="radio" value="true" id="districtTribal" name="districtTribal" ${districtData.districtTribal eq true ?'checked':''} required="required"> Yes &nbsp;&nbsp;
													<input style="margin-right: 5px;" type="radio" value="false" id="districtTribal" name="districtTribal" ${districtData.districtTribal eq false ?'checked':''} required="required"> No 
												</div> 
						                           </div>
						                        </div>
						                     </div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="inputDisabled">Status :</label>
												<div class="col-md-12 d-flex align-items-center redio-box">
													<input style="margin-right: 5px;" type="radio" value="true" id="isActive1" name="isActive" ${districtData.isActive eq true ?'checked':''} required="required"> Active &nbsp;&nbsp;
													<input style="margin-right: 5px;" type="radio" value="false" id="isActive2" name="isActive" ${districtData.isActive eq false ?'checked':''} required="required"> Inactive 
												</div> 
											</div>
										</div> 	
									</div>	
									<div class="col-md-12">
										<div class="text-center" style="margin-top: 20px;">
											<input type="button" name="add&ManageApplicationScheme" value="${districtData eq null ? 'SAVE' : 'UPDATE'}" id="add&ManageApplicationSchemes" class="btn btn-primary btn-sm" onclick="submitFormData('${districtData eq null ? 'SAVE' : 'UPDATE'}')">
											<button type="reset" class="btn btn-danger btn-sm">Reset</button>
											<c:if test="${districtData eq null}">
												<a href="${contextPath}/home" type="button" class="btn btn-dark btn-sm">Back</a>	
											</c:if>
											<c:if test="${districtData ne null}">
												<a href="${contextPath}/core/district/add" type="button" class="btn btn-dark btn-sm">Back</a>
											</c:if>
																						
										</div>
								     </div>
								</form>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="/WEB-INF/views/masters/districtList.jsp"%>
		</div>
	</div>
		
<script>

function submitFormData(status){
		var districtCode = $("#districtCode").val();  
		var districtId = $("#districtId").val();
		var districtName = $("#districtNameEN").val();
		
		if($("#stateId").val()==""){
			bootbox.alert("Select State");
			return false;
		}
		if($("#districtNameEN").val().trim()==""){
			bootbox.alert("Please Enter District Name");
			return false;
		}
		/*if($("#districtNameHI").val()==""){
			bootbox.alert("Enter District Name In Hindi");
			return false;
		}*/
		if($("#districtCode").val().trim()==""){
			bootbox.alert("Please Enter District Code");
			return false;
		}
		if($("#districtLgdCode").val().trim()==""){
			bootbox.alert("Please Enter District LGD Code");
			return false;
		}
		if($("#districtCensusCode").val().trim()==""){
			bootbox.alert("Please Enter District Census Code");
			return false;
		}
		if($('input[name="districtTribal"]:checked').val()==undefined){
			bootbox.alert("Select District Tribal");
			return false;
		}
		if($('input[name="isActive"]:checked').val()==undefined){
			bootbox.alert("Select Status");
			return false;
		}
		else{
			$('#loader').removeClass('hidden');
			$.ajax({
				type : "GET",
				url : "${contextPath}/core/district/validateDistrictName",
				data : {
					"districtName" : districtName,
					"districtId" :districtId,
					"type" :"District"
				}, 
				success : function(response) {
					$('#loader').addClass('hidden');
					if (response.isDuplicate == 'true') {
						bootbox.alert("Duplicate District Name Not Allowed"); 
					}else{
						$.ajax({
							type : "GET",
							url : "${contextPath}/core/district/validateDistrictCode",
							data : {
								"districtCode" : districtCode,
								"districtId" :districtId,
								"type" :"District"
							}, 
							success : function(response) {
								if (response.isDuplicate == 'true') {
									bootbox.alert("Duplicate District Code Not Allowed"); 
								}else{
									var msg = status === 'SAVE' ? 'add' : 'update';
									bootbox.confirm("Do you want to "+msg+" district ?",
											function(result){
												if(result == true){
													$("#addManageId").submit();
												}
										});
// 									$("#addManageId").submit();
								} 
							}, 
							error : function(error) {
								$('#loader').addClass('hidden');
							}
						});
					} 
				}, 
				error : function(error) {
					$('#loader').addClass('hidden');
				}
			});
			
		}
		
}
</script>

	
						                                 
