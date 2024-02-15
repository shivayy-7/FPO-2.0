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
					<h2 class="text-blue pb-2 fw-bold">Manage Village</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
				<a href="${contextPath}/home" class="btn btn-sm btn-border btn-blue btn-round mr-2">
				<i class="fa fa-home"></i></a> 
				<a href="#"	class="btn btn-sm btn-border btn-blue btn-round mr-2">/Add Village</a>
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
							<h4 class="card-title">Add Village</h4>
					</div>
					<div class="card-body">
							<form action="${contextPath}/core/village/addNupdate" id="addManageId"	method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input type="hidden" name="villageId" value="${villageData.villageId}" id="villageId"/>
									<div class="col-md-12">
									<div class="col-md-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required1 required" for="state">State :</label>
					                           <div class="col-md-12">
					                              <select name="state.stateId" id="stateId" class="form-control form-control-sm" required="required" onchange="findDistrictListByStateId(this.value,'')">
					                                 <option value="0" >Select</option>
					                                    <c:forEach items="${stateList}" var="state">
															<option value="${state.stateId}" ${state.stateId eq villageData.gpId.block.district.state.stateId?'selected':''}>${state.stateNameEN}</option>
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
<%-- 					                                      <c:if test="${not empty villageData}">
					                                    	<option value="${villageData.gpId.block.district.districtId}" selected>${villageData.gpId.block.district.districtName}</option>
					                                    </c:if> --%>
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
					                     <div class="col-md-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="state">Block :</label>
					                           <div class="col-md-12">
					                              <select name="block.blockId" id="blockId" class="form-control form-control-sm" required="required" onchange="findGpListByBlockId(this.value,'');">
					                                 <option value="0" >Select</option>
<%-- 					                                      <c:if test="${not empty villageData}">
					                                    	<option value="${villageData.gpId.block.blockId}" selected>${villageData.gpId.block.blockNameEN}</option>
					                                    </c:if> --%>
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
					                     <div class="col-md-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="state">Grampanchayat :</label>
					                           <div class="col-md-12">
					                              <select name="gpId.gpId" id="gpId" class="form-control form-control-sm" required="required" >
					                                 <option value="0" >Select</option>
<%-- 					                                     <c:if test="${not empty villageData}">
					                                    	<option value="${villageData.gpId.gpId}" selected>${villageData.gpId.gpNameEN}</option>
					                                    </c:if> --%>
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="blockNameEN">Village Name :</label>
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="villageNameEn" name="villageNameEn" value="${villageData.villageNameEn}" required="required" maxlength="150">
												</div>
											</div>
										</div>
										<%-- <div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="blockNameHI">Village Name Hindi</label>
												<div class="col-md-12">
													<input type="text" class="form-control" id="villageNameHi" name="villageNameHi" value="${villageData.villageNameHi}"  maxlength="100" required="required">
												</div>
											</div>
										</div> --%>
										
										<c:choose>
										<c:when test="${not empty villageData.villageId}">
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="schemeCode">Village Code :</label>
												<div id="hideCodeId">
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="villageCode" value="${villageData.villageCode}"  maxlength="150"
														name="villageCode" required="required">
												</div>
												</div>
											</div>
										</div>
										</c:when>
										<c:otherwise>
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-12 required" for="schemeCode">Village Code :</label>
												<div id="hideCodeId">
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="villageCode" value="${villageData.villageCode}"  maxlength="150"
														name="villageCode" required="required">
												</div>
												</div>
											</div>
										</div>
										</c:otherwise>
										</c:choose>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="col-md-12 required">Village LGD Code:</label>
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="villageLgdCode" name="villageLgdCode" value="${villageData.villageLgdCode}" required="required" maxlength="150">
												</div>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="col-md-12 required">Village Census Code:</label>
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="villageCensusCode" name="villageCensusCode" value="${villageData.villageCensusCode}" required="required" maxlength="150">
												</div>
											</div>
										</div>
										<div class="col-lg-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required">Village Tribal:</label>
					                           <div class="col-md-12">
					                           <div class="d-flex align-items-center redio-box">
													<input  type="radio" value="true" id="villageTribal" name="villageTribal" ${villageData.villageTribal eq true ?'checked':''} required="required"> Yes &nbsp;&nbsp;
													<input  type="radio" value="false" id="villageTribal" name="villageTribal" ${villageData.villageTribal eq false ?'checked':''} required="required"> No 
					                           </div>
					                           </div>
					                        </div>
					                     </div>
										<div class="col-md-3">
										<div class="form-group">
											<label class="col-md-12 required" for="inputDisabled">Status :</label>
											<div class="col-md-12">
											<div class="d-flex align-items-center redio-box">
												<input style="margin-right: 5px;" type="radio" value="true" name="isActive" id="isActive1" ${villageData.isActive eq true ?'checked':''} required="required"> Active &nbsp;&nbsp; 
												<input style="margin-right: 5px;" type="radio" value="false" name="isActive" id="isActive2" ${villageData.isActive eq false ?'checked':''} required="required"> Inactive 
											</div> 
											</div>
										</div>
									</div> 	
								</div>	
								<div class="col-md-12">
									<div class="text-center" style="margin-top: 20px;">
										<input type="button" name="add&ManageApplicationScheme" value="${villageData eq null ? 'SAVE' : 'UPDATE'}" id="add&ManageApplicationSchemes"
											 class="btn btn-primary btn-sm" onclick="submitFormData('${villageData eq null ? 'SAVE' : 'UPDATE'}')">
										<button type="reset" class="btn btn-danger btn-sm">Reset</button>
										<c:if test="${villageData eq null}">
											<a href="${contextPath}/home" type="button" class="btn btn-dark btn-sm">Back</a>	
										</c:if>
										<c:if test="${villageData ne null}">
											<a href="${contextPath}/core/village/add" type="button" class="btn btn-dark btn-sm">Back</a>
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
					if(status =='VLG'){
						$('#districtId').val('${villageData.gpId.block.district.districtId}');
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
			$('#gpId').empty().append(html);
		}
		
	}
	
	function findBlockListByDistrictId(districtId,status) {
		if(districtId != "0"){
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
					if(status =='VLG'){
						$('#blockId').val('${villageData.gpId.block.blockId}');
					}
				},
				error : function(error) {
					$('#loader').addClass('hidden');
					bootbox.alert("<spring:message code="site.common.msg.block"></spring:message>");
				}
			});
		}else{
			var html = "<option value='0' selected>-Select-</option>";
			$('#blockId').empty().append(html);
			$('#gpId').empty().append(html);
		}
		
	}
	
	function findGpListByBlockId(blockId,status) {
		if(blockId !="0"){
			$('#loader').removeClass('hidden');
			$.ajax({
				type : "GET",
				url : '${contextPath}/core/findGpListByBlockId',
				dataType : "json",
				data : {
					"blockId" : blockId,
				},
				success : function(response) {
					$('#loader').addClass('hidden');
					var html = "<option value='0' selected>-Select-</option>";
					var data = response;
					if (data != "" && data != null && data.length > 0) {
						$.each(data, function(index, value) {
							html = html + "<option value="+value.gpId+">"
									+ value.gpName + "</option>";
						});
					}else{
						bootbox.alert("<spring:message code="site.common.msg.grampanchayat"></spring:message>");
					}
					$('#gpId').empty().append(html);
					if(status =='VLG'){
						$('#gpId').val('${villageData.gpId.gpId}');
					}
				},
				error : function(error) {
					$('#loader').addClass('hidden');
					bootbox.alert("<spring:message code="site.common.msg.grampanchayat"></spring:message>");
				}
			});
		}else{
			$('#gpId').empty().append("<option value='0' selected>-Select-</option>");
		}

	}
	
	function submitFormData(status){
		var villageCode = $("#villageCode").val();  
		var villageId = $("#villageId").val();
		var villageName = $("#villageNameEn").val();
		
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
		if($("#gpId").val()==""){
			bootbox.alert("Select Grampanchayat");
			return false;
		}
		if($("#villageNameEn").val().trim()==""){
			bootbox.alert("Please Enter Village Name ");
			return false;
		}
		if($("#villageCode").val().trim()==""){
			bootbox.alert("Please Enter Village Code");
			return false;
		}
		if($("#villageLgdCode").val().trim()==""){
			bootbox.alert("Please Enter Village LGD Code");
			return false;
		}
		if($("#villageCensusCode").val().trim()==""){
			bootbox.alert("Please Enter Village Census Code");
			return false;
		}
		if($('input[name="villageTribal"]:checked').val()==undefined){
			bootbox.alert("Select Village Tribal");
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
				url : "${contextPath}/core/village/validateVillageName",
				data : {
					"villageName" : villageName,
					"villageId" : villageId,
					"type" :"Village"
				}, 
				success : function(response) {
					$('#loader').addClass('hidden');
					if (response.isDuplicate == 'true') {
						bootbox.alert("Duplicate Village Name Not Allowed"); 
					}else{
						$('#loader').removeClass('hidden');
						$.ajax({
							type : "GET",
							url : "${contextPath}/core/village/validateVillageCode",
							data : {
								"villageCode" : villageCode,
								"villageId" : villageId,
								"type" :"Village"
							}, 
							success : function(response) {
								$('#loader').addClass('hidden');
								if (response.isDuplicate == 'true') {
									bootbox.alert("Duplicate Village Code Not Allowed"); 
								}else{
									var msg = status === 'SAVE' ? 'add' : 'update';
									bootbox.confirm("Do you want to "+msg+" village ?",
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
		if('${villageData}' != ''){
			var state = $("#stateId").val();
			var dist = '${villageData.gpId.block.district.districtId}';
			var block = '${villageData.gpId.block.blockId}';
			var sts = 'VLG';
			findDistrictListByStateId(state,sts);
			findBlockListByDistrictId(dist,sts);
			findGpListByBlockId(block,sts);
			
		}

	});
	</script>
	
