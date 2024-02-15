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
					<h2 class="text-blue pb-2 fw-bold">Manage Block</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
					<a href="${contextPath}/home"
						class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
							class="fa fa-home"></i></a> <a href="#"
								class="btn btn-sm btn-border btn-blue btn-round mr-2">/Add Block</a>
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
						<h4 class="card-title">Add Block</h4>
					</div>
					<div class="card-body">
							<form action="${contextPath}/core/block/addNupdate" id="addBlockFrm" class="form-row" method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input type="hidden" name="blockId" value="${blockData.blockId}" id="blockId" />
									
										<div class="col-lg-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="stateId">State :</label>
					                           <div class="col-md-12">
					                              <select name="state.stateId" id="stateId" class="form-control form-control-sm" required="required" onchange="findDistrictListByStateId(this.value,'')">
					                                 <option value="0" >Select</option>
					                                    <c:forEach items="${stateList}" var="state">
															<option value="${state.stateId}" ${state.stateId eq blockData.district.state.stateId?'selected':''}>${state.stateNameEN}</option>
														</c:forEach> 
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
									  <div class="col-lg-3">
					                        <div class="form-group">
					                           <label class="col-md-12 required" for="districtId">District :</label>
					                           <div class="col-md-12">
					                              <select name="district.districtId" id="districtId" class="form-control form-control-sm" required="required">
					                                   <option value="" >Select</option>
<%-- 					                                    <c:if test="${not empty blockData}">
					                                    	<option value="${blockData.district.districtId}" selected>${blockData.district.districtName}</option>
					                                    </c:if> --%>
					                              </select>
					                           </div>
					                        </div>
					                     </div>  
										<div class="col-lg-3">
											<div class="form-group">
												<label class="col-md-12 required" for="blockNameEN">Block Name :</label>
												<div class="col-md-12">
													<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="blockNameEN" name="blockNameEN" value="${blockData.blockNameEN}" required="required" maxlength="150">
												</div>
											</div>
										</div>
										<c:choose>
										<c:when test="${not empty blockData}">
										<div class="col-lg-3">
											<div class="form-group">
												<label class="col-md-10 required" for="schemeCode">Block Code :</label>
												<div id="hideCodeId">
												<div class="col-md-10">
													<input type="text" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="blockCode" value="${blockData.blockCode}"  maxlength="150"
														name="blockCode" autocomplete="off" required="required" onchange="blockCodeValidation();" >
												</div>
												</div>
											</div>
										</div>
										</c:when>
										<c:otherwise>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="col-md-12 required" for="schemeCode">Block Code :</label>
												<div id="hideCodeId">
												<div class="col-md-12">
													<input type="text" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="blockCode" value="${blockData.blockCode}"  maxlength="50"
														name="blockCode" autocomplete="off" required="required" onchange="blockCodeValidation();">
												</div>
												</div>
											</div>
										</div>
										</c:otherwise>
										</c:choose>
										
										<div class="col-lg-3">
												<div class="form-group">
													<label class="col-md-12 required">Block LGD Code:</label>
													<div class="col-md-12">
														<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="blockLgdCode" name="blockLgdCode" value="${blockData.blockLgdCode}" required="required" maxlength="150">
													</div>
												</div>
											</div>
											<div class="col-lg-3">
												<div class="form-group">
													<label class="col-md-12 required">Block Census Code:</label>
													<div class="col-md-12">
														<input type="text" autocomplete="off" class="form-control form-control-sm AlphaNumericWithLimitedSpecialChars" id="blockCensusCode" name="blockCensusCode" value="${blockData.blockCensusCode}" required="required" maxlength="150">
													</div>
												</div>
											</div>
											<div class="col-lg-3">
						                        <div class="form-group">
						                           <label class="col-md-12 required">Block Tribal:</label>
						                           <div class="col-md-12">
						                             <div class="col-md-12 d-flex align-items-center redio-box">
													<input style="margin-right: 5px;" type="radio" value="true" id="blockTribal" name="blockTribal" ${blockData.blockTribal eq true ?'checked':''} required="required"> Yes &nbsp;&nbsp;
													<input style="margin-right: 5px;" type="radio" value="false" id="blockTribal" name="blockTribal" ${blockData.blockTribal eq false ?'checked':''} required="required"> No 
												</div> 
						                           </div>
						                        </div>
						                     </div>
	
										<div class="col-lg-3">
										<div class="form-group">
											<label class="col-md-12 required" for="isActive">Status :</label>
											<div class="col-md-12 d-flex align-items-center redio-box">
												<input type="radio" value="true" id="isActive1" name="isActive" ${blockData.isActive eq true ?'checked':''} required="required"> Active &nbsp;&nbsp;
												<input type="radio" value="false" id="isActive2"  name="isActive" ${blockData.isActive eq false ?'checked':''} required="required"> Inactive 
											</div> 
										</div>
									</div> 	
									<div class="col-lg-12">
										<div class="text-center" style="margin-top: 20px;">
											<input type="button" name="add&ManageApplicationScheme" value="${blockData eq null ? 'SAVE' : 'UPDATE'}" id="add&ManageApplicationSchemes" 
												class="btn btn-primary btn-sm" onclick="submitFormData('${blockData eq null ? 'SAVE' : 'UPDATE'}')" >
											<button type="reset" class="btn btn-danger btn-sm">Reset</button>
											<c:if test="${blockData eq null}">
												<a href="${contextPath}/home" type="button" class="btn btn-dark btn-sm">Back</a>	
											</c:if>
											<c:if test="${blockData ne null}">
												<a href="${contextPath}/core/block/add" type="button" class="btn btn-dark btn-sm">Back</a>
											</c:if>
										</div>
								     </div>
								</form>
					</div>
				</div>
			</div>
			
		</div>
		<%@ include file="/WEB-INF/views/masters/blockList.jsp"%>
	</div>
	</div>	

		  <!-- Modal End -->

	<script>
	function findDistrictListByStateId(stateId,status) {
		if(stateId !='0'){
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
					if(status == 'BLK'){
						$('#districtId').val('${blockData.district.districtId}');
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
	function blockCodeValidation(){
		$('#loader').removeClass('hidden');
		var blockCode = $("#blockCode").val();  
		$.ajax({
			type : "GET",
			url : "${contextPath}/core/block/validate_block_code",
			data : {
				"blockCode" : blockCode,
			}, 
			success : function(response) {
				$('#loader').addClass('hidden');
				var val = JSON.parse(response); 
				if (val.isDuplicate == true) {
					bootbox.alert("Duplicate Block Code not allowed"); 
					var html ='<div class="col-md-10"><input type="text" class="form-control" id="blockCode" value="${blockData.blockCode}"  maxlength="50" name="blockCode" required="required" onchange="blockCodeValidation();"></div>'
					$("#hideCodeId").empty().append(html);
				}  
			}, 
			error : function(error) {
				$('#loader').addClass('hidden');
			}
		});
	} 
	
	function submitFormData(status){
		var blockCode = $("#blockCode").val();  
		var blockId = $("#blockId").val();
		var blockName = $("#blockNameEN").val();
		
		if($("#stateId").val()==""){
			bootbox.alert("Select State");
			return false;
		}
		if($("#districtId").val()==""){
			bootbox.alert("Select District");
			return false;
		}
		if($("#blockNameEN").val().trim() == ""){
			bootbox.alert("Please Enter Block Name");
			return false;
		}
		
		if($("#blockCode").val().trim()==""){
			bootbox.alert("Please Enter Block Code");
			return false;
		}
		if($("#blockLgdCode").val().trim()==""){
			bootbox.alert("Please Enter Block LGD Code");
			return false;
		}
		if($("#blockCensusCode").val().trim()==""){
			bootbox.alert("Please Enter Block Census Code");
			return false;
		}
		if($('input[name="blockTribal"]:checked').val()==undefined){
			bootbox.alert("Select Block Tribal");
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
				url : "${contextPath}/core/block/validateBlockName",
				data : {
					"blockName" : blockName,
					"blockId" : blockId,
					"type" :"Block"
				}, 
				success : function(response) {
					$('#loader').addClass('hidden');
					if (response.isDuplicate == 'true') {
						bootbox.alert("Duplicate Block Name Not Allowed"); 
					}else{
						$.ajax({
							type : "GET",
							url : "${contextPath}/core/block/validateBlockCode",
							data : {
								"blockCode" : blockCode,
								"blockId" : blockId,
								"type" :"Block"
							}, 
							success : function(response) {
								if (response.isDuplicate == 'true') {
									bootbox.alert("Duplicate Block Code Not Allowed");
								}else{
									var msg = status === 'SAVE' ? 'add' : 'update';
									bootbox.confirm("Do you want to "+ msg+" block ?",
										function(result){
											if(result == true){
												$("#addBlockFrm").submit();
											}
									});
// 									$("#addBlockFrm").submit();
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
		if('${blockData}' != ''){
			var state = $("#stateId").val();
			var sts = 'BLK';
			findDistrictListByStateId(state,sts);
		}

	});
	</script>
