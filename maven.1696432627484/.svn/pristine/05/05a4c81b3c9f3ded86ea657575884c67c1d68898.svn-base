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
					<h2 class="text-blue pb-2 fw-bold">Manage Municipality</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
					<a href="${contextPath}/home"
						class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
							class="fa fa-home"></i></a> <a href="#"
								class="btn btn-sm btn-border btn-blue btn-round mr-2">/Add Municipality</a>
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
						<h4 class="card-title">Add Municipality</h4>
					</div>
					<div class="card-body">
						<div class="row">
							<form action="${contextPath}/core/municipality/addNupdate" id="addMunicipalityFrm"	method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input type="hidden" name="municipalityId" value="${municipalityData.municipalityId}" id="municipalityId" />
									<div class="col-md-12">
										<div class="col-md-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="stateId">State :</label>
					                           <div class="col-md-12">
					                              <select name="state.stateId" id="stateId" class="form-control form-control-sm" required="required" onchange="findDistrictListByStateId(this.value,'')">
					                                 <option value="0" >Select</option>
					                                    <c:forEach items="${stateList}" var="state">
															<option value="${state.stateId}" ${state.stateId eq municipalityData.district.state.stateId?'selected':''}>${state.stateNameEN}</option>
														</c:forEach> 
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
										<div class="col-md-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="districtId">District :</label>
					                           <div class="col-md-12">
					                              <select name="district.districtId" id="districtId" class="form-control form-control-sm" required="required">
					                                  <option value="0" >Select</option>
<%-- 					                                    <c:if test="${not empty municipalityData}">
					                                    	<option value="${municipalityData.district.districtId}" selected>${municipalityData.district.districtName}</option>
					                                    </c:if>  --%>
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="municipalityNameEn">Municipality Name :</label>
												<div class="col-md-12">
													<input type="text" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="municipalityNameEn" name="municipalityNameEn" value="${municipalityData.municipalityNameEn}" required="required" maxlength="150">
												</div>
											</div>
										</div>
										<%-- <div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="municipalityNameHi">Municipality Name Hindi</label>
												<div class="col-md-12">
													<input type="text" class="form-control" id="municipalityNameHi" name="municipalityNameHi" value="${municipalityData.municipalityNameHi}"  maxlength="100" required="required">
												</div>
											</div>
										</div> --%>
										<c:choose>
											<c:when test="${not empty municipalityData.municipalityId}">
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="municipalityCode">Municipality Code :</label>
												<div id="hideCodeId">
												<div class="col-md-12">
													<input type="text" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="municipalityCode" value="${municipalityData.municipalityCode}"  maxlength="150"
														name="municipalityCode" required="required" onchange="municipaltyCodeValidation();">
												</div>
												</div>
											</div>
										</div>
											</c:when>
											<c:otherwise>
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="municipalityCode">Municipality Code :</label>
												<div id="hideCodeId">
												<div class="col-md-12">
													<input type="text" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="municipalityCode" value="${municipalityData.municipalityCode}"  maxlength="150"
														name="municipalityCode" required="required" onchange="municipaltyCodeValidation();">
												</div>
												</div>
											</div>
										</div>
											</c:otherwise>
										</c:choose>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="col-md-12 required" for="districtNameEN">Municipality LGD Code:</label>
												<div class="col-md-12">
													<input type="text" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="muniLgdCode" name="muniLgdCode" value="${municipalityData.muniLgdCode}" required="required" maxlength="150">
												</div>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="col-md-12 required" for="districtNameEN">Municipality Census Code:</label>
												<div class="col-md-12">
													<input type="text" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="muniCensusCode" name="muniCensusCode" value="${municipalityData.muniCensusCode}" required="required" maxlength="150">
												</div>
											</div>
										</div>
										<div class="col-lg-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="stateId">Municipality Tribal:</label>
					                           <div class="col-md-12 d-flex align-items-center redio-box">
					                              <input style="margin-right: 5px;" type="radio" value="true" id="muniTribal" name="muniTribal" ${municipalityData.muniTribal eq true ?'checked':''} required="required"> Yes &nbsp;&nbsp;
												<input style="margin-right: 5px;" type="radio" value="false" id="muniTribal" name="muniTribal" ${municipalityData.muniTribal eq false ?'checked':''} required="required"> No 
					                           </div>
					                        </div>
					                     </div> 
										<div class="col-md-3">
										<div class="form-group">
											<label class="col-md-12 required" for="isActive">Status :</label>
											<div class="col-md-12 d-flex align-items-center redio-box">
												<input type="radio" value="true" id="isActive1" name="isActive" ${municipalityData.isActive eq true ?'checked':''} required="required"> Active &nbsp;&nbsp;
												<input type="radio" value="false" id="isActive2" name="isActive" ${municipalityData.isActive eq false ?'checked':''} required="required"> Inactive 
											</div> 
										</div>
									</div>
									</div>	
									<div class="col-md-12">
										<div class="text-center" style="margin-top: 20px;">
											<input type="button" name="add&ManageApplicationScheme" value="${municipalityData eq null ? 'SAVE' : 'UPDATE'}" id="add&ManageApplicationSchemes" 
												class="btn btn-success btn-sm" onclick="submitFormData('${municipalityData eq null ? 'SAVE' : 'UPDATE'}')">&nbsp;&nbsp;
											<button type="reset" class="btn btn-danger btn-sm">Reset</button>
											<c:if test="${municipalityData eq null}">
												<a href="${contextPath}/home" type="button" class="btn btn-dark btn-sm">Back</a>	
											</c:if>
											<c:if test="${municipalityData ne null}">
												<a href="${contextPath}/core/municipality/add" type="button" class="btn btn-dark btn-sm">Back</a>
											</c:if>
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
		

		  <!-- Modal End -->
<script>
	function findDistrictListByStateId(stateId,status) {
		if(stateId !="0"){
			$('#loader').removeClass('hidden');
			$.ajax({
				type : "GET",
				url : '${contextPath}/core/findDistrictListByStateId',
				dataType : "json",
				data : {
					"stateId" : stateId,
				},
				success : function(response) {
					$('#loader').addClass('hidden');
					var html = "<option value='0' selected>-Select-</option>";
					var data = response;
					if (data != "" && data != null && data.length > 0) {
						$.each(data, function(index, value) {
							html = html + "<option value="+value.districtId+">"
									+ value.districtName + "</option>";
						});
					}else{
						bootbox.alert("<spring:message code="site.common.msg.district"></spring:message>");
					}
					$('#districtId').empty().append(html);
					$('#districtId').empty().append(html);
					if(status == 'MUNI'){
						$('#districtId').val('${municipalityData.district.districtId}');
					}
				},
				error : function(error) {
					$('#loader').addClass('hidden');
					bootbox.alert("<spring:message code="site.common.msg.district"></spring:message>");
				}
			});
		}else{
			$('#districtId').empty().append("<option value='0' selected>-Select-</option>");
		}
		
	}
	
	function submitFormData(){
		var municipalityCode = $("#municipalityCode").val();  
		var municipalityId = $("#municipalityId").val();
		var municipalityName = $("#municipalityNameEn").val();
		
		if($("#stateId").val()==""){
			bootbox.alert("Select State");
			return false;
		}
		if($("#districtId").val()==""){
			bootbox.alert("Select District");
			return false;
		}
		if($("#municipalityNameEn").val().trim()==""){
			bootbox.alert("Please Enter Municipality Name");
			return false;
		}
		if($("#municipalityCode").val().trim()==""){
			bootbox.alert("Please Enter Municipality Code");
			return false;
		}
		if($('input[name="isActive"]:checked').val()==undefined){
			bootbox.alert("Select Active Or InActive");
			return false;
		}
		else{
			$('#loader').removeClass('hidden');
			$.ajax({
				type : "GET",
				url : "${contextPath}/core/municipality/validateMunicipalityName",
				data : {
					"municipalityName" : municipalityName,
					"municipalityId" : municipalityId,
					"type" :"Municipality"
				}, 
				success : function(response) {
					$('#loader').addClass('hidden');
					if (response.isDuplicate == 'true') {
						bootbox.alert("Duplicate Municipality Name Not Allowed"); 
					}else{
						$('#loader').removeClass('hidden');
						$.ajax({
							type : "GET",
							url : "${contextPath}/core/municipality/validateMunicipalityCode",
							data : {
								"municipalityCode" : municipalityCode,
								"municipalityId" : municipalityId,
								"type" :"Municipality"
							}, 
							success : function(response) {
								$('#loader').addClass('hidden');
								if (response.isDuplicate == 'true') {
									bootbox.alert("Duplicate Municipality Code Not Allowed"); 
								}else{
									var msg = status === 'SAVE' ? 'add' : 'update';
									bootbox.confirm("Do you want to "+msg+" municipality ?",
											function(result){
												if(result == true){
													$("#addMunicipalityFrm").submit();
												}
										});
// 									$("#addMunicipalityFrm").submit();
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
	
	$(document).ready(function(){
		if('${municipalityData}' != ''){
			var state = $("#stateId").val();
			var sts = 'MUNI';
			findDistrictListByStateId(state,sts);
		}

	});
	</script>
	
