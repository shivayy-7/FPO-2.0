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
					<h2 class="text-blue pb-2 fw-bold">Manage Grampanchayat</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
					<a href="${contextPath}/home"
						class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
						class="fa fa-home"></i></a> <a href="#"
						class="btn btn-sm btn-border btn-blue btn-round mr-2">/Add Grampanchayat</a>
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
						<h4 class="card-title">Add Grampanchayat</h4>
					</div>
					<div class="card-body">
							<form action="${contextPath}/core/grampanchayat/addNupdate" id="addManageId"	method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input type="hidden" name="gpId" value="${grampanchayatData.gpId}" id="gpId"/>
									
									<div class="col-md-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="state">State :</label>
					                           <div class="col-md-12">
					                              <select name="state.stateId" id="stateId" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" required="required" onchange="findDistrictListByStateId(this.value,'')">
					                                 <option value="0" >Select</option>
					                                    <c:forEach items="${stateList}" var="state">
															<option value="${state.stateId}" ${state.stateId eq grampanchayatData.block.district.state.stateId?'selected':''}>${state.stateNameEN}</option>
														</c:forEach> 
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
									   <div class="col-md-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="state">District :</label>
					                           <div class="col-md-12">
					                              <select name="districtId" id="districtId" class="form-control form-control-sm" required="required" onchange="findBlockListByDistrictId(this.value,'')">
					                                 <option value="0" >Select</option>
	<%-- 				                                      <c:if test="${not empty grampanchayatData}">
					                                    	<option value="${grampanchayatData.block.district.districtId}" selected>${grampanchayatData.block.district.districtName}</option>
					                                    </c:if> --%>
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
					                     <div class="col-md-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="state">Block :</label>
					                           <div class="col-md-12">
					                              <select name="block.blockId" id="blockId" class="form-control form-control-sm" required="required">
					                                 <option value="0" >Select</option>
<%-- 					                                      <c:if test="${not empty grampanchayatData}">
					                                    	<option value="${grampanchayatData.block.blockId}" selected>${grampanchayatData.block.blockNameEN}</option>
					                                    </c:if> --%>
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="blockNameEN">GP Name :</label>
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="gpNameEN" name="gpNameEN" value="${grampanchayatData.gpNameEN}" required="required" maxlength="150">
												</div>
											</div>
										</div>
										<c:choose>
											<c:when test="${not empty grampanchayatData.gpId}">
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="schemeCode">GP Code :</label>
												<div id="hideCodeId">
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="gpCode" value="${grampanchayatData.gpCode}"  maxlength="150"
														name="gpCode" required="required">
												</div>
											</div>
										</div>
										</div>
											</c:when>
											<c:otherwise>
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="schemeCode">GP Code :</label>
												<div id="hideCodeId">
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="gpCode" value="${grampanchayatData.gpCode}"  maxlength="150"
														name="gpCode" required="required" >
												</div>
											</div>
										</div>
										</div>
											</c:otherwise>
										</c:choose>
										
											<div class="col-lg-3">
												<div class="form-group">
													<label class="col-md-12 required">GP LGD Code:</label>
													<div class="col-md-12">
														<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="gpLgdCode" name="gpLgdCode" value="${grampanchayatData.gpLgdCode}" required="required" maxlength="150">
													</div>
												</div>
											</div>
											<div class="col-lg-3">
												<div class="form-group">
													<label class="col-md-12 required">GP Census Code:</label>
													<div class="col-md-12">
														<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="gpCensusCode" name="gpCensusCode" value="${grampanchayatData.gpCensusCode}" required="required" maxlength="150">
													</div>
												</div>
											</div>
											<div class="col-lg-3">
						                        <div class="form-group">
						                           <label class="col-md-12 required">GP Tribal:</label>
						                            <div class="col-md-12 d-flex align-items-center redio-box">
													<input  type="radio" value="true" id="gpTribal" name="gpTribal" ${grampanchayatData.gpTribal eq true ?'checked':''} required="required"> Yes &nbsp;&nbsp;
													<input  type="radio" value="false" id="gpTribal" name="gpTribal" ${grampanchayatData.gpTribal eq false ?'checked':''} required="required"> No 
												</div> 
						                        </div>
						                     </div>
									   <div class="col-md-3">
										<div class="form-group">
											<label class="col-md-12 required" for="isActive">Status :</label>
											<div class="col-md-12 d-flex align-items-center redio-box">
												<input type="radio" value="true" id="isActive1" name="isActive" ${grampanchayatData.isActive eq true ?'checked':''} required="required" style="margin-right: 5px;"> Active &nbsp;&nbsp;
												<input type="radio" value="false" id="isActive2" name="isActive" ${grampanchayatData.isActive eq false ?'checked':''} required="required" style="margin-right: 5px;"> Inactive 
											</div> 
										</div>
									</div> 	
									<div class="col-md-12">
										<div class="text-center" style="margin-top: 20px;">
											<input type="button" name="add&ManageApplicationScheme" value="${grampanchayatData eq null ? 'SAVE' : 'UPDATE'}" 
													id="add&ManageApplicationSchemes" class="btn btn-primary btn-sm"  onclick="submitFormData('${grampanchayatData eq null ? 'SAVE' : 'UPDATE'}')">
											<button type="reset" class="btn btn-danger btn-sm">Reset</button>
											<c:if test="${grampanchayatData eq null}">
												<a href="${contextPath}/home" type="button" class="btn btn-dark btn-sm">Back</a>	
											</c:if>
											<c:if test="${grampanchayatData ne null}">
												<a href="${contextPath}/core/grampanchayat/add" type="button" class="btn btn-dark btn-sm">Back</a>
											</c:if>
										</div>
								     </div>
								</form>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/views/masters/grampanchayatList.jsp"%>
	</div>
</div>
		

		  <!-- Modal End -->

	<script>
	function findDistrictListByStateId(stateId,status) {
		if(stateId != '0'){
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
//	 					bootbox.alert("<spring:message code="site.common.msg.district"></spring:message>");
					}
					$('#districtId').empty().append(html);
					if(status == 'GP'){
						$('#districtId').val('${grampanchayatData.block.district.districtId}');
					}
				},
				error : function(error) {
					$('#loader').addClass('hidden');
					bootbox.alert("<spring:message code="site.common.msg.district"></spring:message>");
				}
			});
		}else{
			var html = "<option value='0' selected>-Select-</option>";
			$('#districtId').empty().append(html);
			$('#blockId').empty().append(html);
		}

	}
	
	function findBlockListByDistrictId(districtId,status) {
		if(districtId !="0"){
			$('#loader').removeClass('hidden');
			$.ajax({
				type : "GET",
				url : '${contextPath}/core/findBlockListByDistrictId',
				dataType : "json",
				data : {
					"districtId" : districtId,
				},
				success : function(response) {
					$('#loader').addClass('hidden');
					var html = "<option value='0' selected>-Select-</option>";
					var data = response;
					if (data != "" && data != null && data.length > 0) {
						$.each(data, function(index, value) {
							html = html + "<option value="+value.blockId+">"
									+ value.blockName + "</option>";
						});
					}else{
						bootbox.alert("<spring:message code="site.common.msg.block"></spring:message>");
					}
					$('#blockId').empty().append(html);
					if(status == 'GP'){
						$('#blockId').val('${grampanchayatData.block.blockId}');
					}
				},
				error : function(error) {
					$('#loader').addClass('hidden');
					bootbox.alert("<spring:message code="site.common.msg.block"></spring:message>");
				}
			});
		}else{
			$('#blockId').empty().append("<option value='0' selected>-Select-</option>");
		}

	}
	
	function submitFormData(status){
		var gpCode = $("#gpCode").val();  
		var gpId = $("#gpId").val();
		var gpName = $("#gpNameEN").val();
		
		if($("#stateId").val()==""){
			bootbox.alert("Select State");
			return false;
		}
		if($("#districtId").val()==""){
			bootbox.alert("Select District");
			return false;
		}
		if($("#blockId").val()==""){
			bootbox.alert("Select Block");
			return false;
		}
		if($("#gpNameEN").val().trim()==""){
			bootbox.alert("Please Enter Grampanchayat Name");
			return false;
		}
		if($("#gpCode").val().trim()==""){
			bootbox.alert("Please Enter Grampanchayat Code");
			return false;
		}
		if($("#gpLgdCode").val().trim()==""){
			bootbox.alert("Please Enter Grampanchayat LGD Code");
			return false;
		}
		if($("#gpCensusCode").val().trim()==""){
			bootbox.alert("Please Enter Grampanchayat Census Code");
			return false;
		}
		if($('input[name="gpTribal"]:checked').val()==undefined){
			bootbox.alert("Select Grampanchayat Tribal");
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
				url : "${contextPath}/core/grampanchayat/validateGrampanchayatName",
				data : {
					"grampanchayatName" : gpName,
					"grampanchayatId" :gpId,
					"type" :"Grampanchayat"
				}, 
				success : function(response) {
					$('#loader').addClass('hidden');
					if (response.isDuplicate == 'true') {
						bootbox.alert("Duplicate Gp Name Not Allowed"); 
					}else{
						$.ajax({
							type : "GET",
							url : "${contextPath}/core/grampanchayat/validateGrampanchayatCode",
							data : {
								"grampanchayatCode" : gpCode,
								"grampanchayatId" :gpId,
								"type" :"Grampanchayat"
							}, 
							success : function(response) {
								if (response.isDuplicate == 'true') {
									bootbox.alert("Duplicate Gp Code Not Allowed"); 
								}else{
									var msg = status === 'SAVE' ? 'add' : 'update';
									bootbox.confirm("Do you want to "+msg+" GP ?",
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
	
$(document).ready(function(){
	if('${grampanchayatData}' != ''){
		var state = $("#stateId").val();
		var dist = '${grampanchayatData.block.district.districtId}';
		var sts = 'GP';
		findDistrictListByStateId(state,sts);
		findBlockListByDistrictId(dist,sts);
	}
	
});
</script>
