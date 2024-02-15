<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- <link rel="stylesheet" href="${contextPath}/assets/vendor/b4multiselect/bootstrap-select.css" /> -->
<div class="content">
<div class="panel-header bg-primary-gradient">
    <div class="page-inner py-4">
        <div
            class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
            <div>
                <h2 class="text-blue pb-2 fw-bold">${masterCall eq 'FUNCTIONAL-HEAD' ? 'Functional Head' : masterCall eq 'ACTIVITY' ? 'Activity to Component' : 'Sub-Activity to Activity'} Mapping</h2>
            </div>
            <div class="ml-md-auto mb-4 py-2 py-md-0">
                <a href="${contextPath}/home"
                    class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a> <a href="#"
                    class="btn btn-sm btn-border btn-blue btn-round mr-2">/${masterCall eq 'FUNCTIONAL-HEAD' ? 'Functional Head' : masterCall eq 'ACTIVITY' ? 'Activity' : 'Sub Activity'}</a>
            </div>
        </div>
    </div>
</div>
<div class="page-inner">
    <div class="row mt-2">
        <%@ include file="/WEB-INF/views/message.jsp"%>
        <div class="col-md-12">
            <div class="card full-height">
                <div class="card-header"><h4 class="card-title">${masterCall eq 'FUNCTIONAL-HEAD' ? 'Functional Head' : masterCall eq 'ACTIVITY' ? 'Activity' : 'Sub-Activity'}</h4></div>
                <div class="card-body">
                    <div class="col-md-12">
                        <form action="${contextPath}/master/map" class="form-row align-items-end justify-content-start" id="masterSub" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="childId" value="${masterVO.childId}"/>
                            <input type="hidden" name="masterCall" value="${masterCall}"/>
                            <input type="hidden" name="childCode" value="${masterVO.childCode}"/>
                            <c:if test="${masterCall eq 'SUB-ACTIVITY'}">
                            	 <div class="col">
                                    <div class="form-group">
                                        <label class="col-md-12 required" >Component :${masterVO.actCode}</label>
                                        <div class="col-md-12">
                                            <select class="form-control form-control-sm" id="cmpCode" name="cmpCode" required="required" onchange="getActivityListByCmpCode(this.value);">
												<option value="0">--Select--</option>
												<c:forEach items="${componentList}" var="cmp">
                                                     <option value="${cmp.cmpCode}" ${cmp.cmpCode eq masterVO.cmpCode ? 'selected' : ''}>${cmp.cmpNameEn}</option>
                                                 </c:forEach>
											</select>
                                        </div> 
                                    </div>
                                </div>
                            </c:if>
                                <div class="col">
                                    <div class="form-group">
                                        <label class="col-md-12 required" for="DistrictNameEN">${masterCall eq 'FUNCTIONAL-HEAD' ? 'Component' : masterCall eq 'ACTIVITY' ? 'Component ' : 'Activity '} :</label>
                                        <div class="col-md-12">
                                           <select class="form-control form-control-sm" id="parentId" name="parentCode" required="required">
												<option value="0">--Select--</option>
                                               	<c:forEach  items="${masterVO.parentList}" var="map">
														<c:choose>
															<c:when test="${masterCall eq 'ACTIVITY'}">
                                                                <option value="${map.cmpCode}" <c:forEach begin="0"  end="${fn:length(masterVO.parentCode)}" var="availData" >${masterVO.parentCode[availData] eq  map.cmpCode ? 'selected' : '' }</c:forEach>>${map.cmpNameEn}</option>
                                                            </c:when>
															<%-- <c:when test="${masterCall eq 'SUB-ACTIVITY' && not empty masterVO.childCode}">
																<option value="${map.actCode}"<c:forEach begin="0"  end="${fn:length(masterVO.parentCode)}" var="availData" >${masterVO.parentCode[availData] eq  map.actCode ? 'selected' : '' }</c:forEach>>${map.actNameEn}</option>
															</c:when> --%>
														</c:choose>
												</c:forEach>
											</select>
                                        </div> 
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label class="col-md-12 required" >${masterCall eq 'FUNCTIONAL-HEAD' ? 'Functional Head' : masterCall eq 'ACTIVITY' ? 'Activity' : 'Sub Activity'} :</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control form-control-sm " id="childName" name="childName" value="${masterVO.childName}" required="required">
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${masterCall eq 'SUB-ACTIVITY'}">
                                    <div class="col">
                                        <div class="form-group">
                                            <label class="col-md-12 required" >Physical Unit :</label>
                                            <div class="col-md-12">
                                           
                                                <select class="form-control form-control-sm " name="childUnit" id="childUnit">
                                                   <c:forEach  items="${masterVO.childUnitList}" var="unit">
                                                        <option value="${unit.unitCode}" ${unit.unitCode eq masterVO.childUnit ? 'selected' : '' } >${unit.unitName}</option>        
                                                    </c:forEach>
                                                </select>
                                            </div> 
                                        </div>
                                    </div>
                                </c:if >

                                <div class="col">
                                    <div class="form-group">
                                        <label class="col-md-12" >Description :</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control form-control-sm "  name="childDescription"  value="${masterVO.childDescription}" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="col" style="margin-bottom: 9px;">
                                     <div class="text-left">
                                        <button type="button" class="btn btn-primary btn-sm" onclick="formSubmitMaster('${masterCall eq 'ACTIVITY' ? 'ACTIVITY' : 'SUB-ACTIVITY'}')" >
                                        	${masterVO.childCode eq null ? 'Save' : 'Update'}
                                        </button>
                                        <a href="${contextPath}/home" type="button" class="btn btn-dark btn-sm">Back</a>
                                     </div>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
<div class="row mt-2">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header">
                <h4 class="card-title">${masterCall eq 'FUNCTIONAL-HEAD' ? 'Functional Head' : masterCall eq 'ACTIVITY' ? 'Activity' : 'Sub Activity'} Map List</h4>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table id="basic-datatables" class="display table table-bordered table-hover DataTableBtn" >
	                        <thead>
	                            <tr>
	                                <th>Sl.No</th>
	                                 <c:if test="${masterCall eq 'SUB-ACTIVITY'}">
	                                	<th>Component</th>
			                        </c:if>
	                                <th>${masterCall eq 'FUNCTIONAL-HEAD' ? 'Component' : masterCall eq 'ACTIVITY' ? 'Component' : 'Activity'} Name</th>
	                                <th>${masterCall eq 'FUNCTIONAL-HEAD' ? 'Functional Head' : masterCall eq 'ACTIVITY' ? 'Activity' : 'Sub Activity'} Name</th>
	                                <c:if test="${masterCall eq 'SUB-ACTIVITY'}">
	                                	<th>Physical Unit</th>
			                        </c:if>
	                                <th>Description</th>
	                                <th>Action</th>
	                            </tr>
	                        </thead>
                        		<tbody>
                          			<c:forEach items="${masterVO.childList}" var="child" varStatus="count">    
			                            <tr>
			                                <td>${count.count}</td>
			                                <c:if test="${masterCall eq 'SUB-ACTIVITY'}">
			                                	<td>${child.childCmpData}</td>
			                                </c:if>
			                                <td>${child.childMapData}</td>
			                                <td>${masterCall eq 'FUNCTIONAL-HEAD' ?  child.headNameEn : masterCall eq 'ACTIVITY' ? child.actNameEn :child.subActNameEn }</td>
			                                <c:if test="${masterCall eq 'SUB-ACTIVITY'}">
			                                	<td>${child.physicalUnit.unitName}</td>
			                                </c:if>
			                                <td>${masterCall eq 'FUNCTIONAL-HEAD' ?  child.headDescription : masterCall eq 'ACTIVITY' ? child.actDescription :child.subActDescription }</td>
			                                <td>
			                               	  <a href="${contextPath}/master/map?masterCall=${masterCall}&childCode=${masterCall eq 'FUNCTIONAL-HEAD' ?  child.headCode : masterCall eq 'ACTIVITY' ? child.actCode :child.subActCode}" 
			                               	 	class="btn btn-primary btn-xs" data-toggle="tooltip" title="Edit"><i class="fa fa-edit"></i></a>
			                               	  <a href="${contextPath}/master/actInAct?masterCall=${masterCall}&childCode=${masterCall eq 'FUNCTIONAL-HEAD' ?  child.headCode : masterCall eq 'ACTIVITY' ? child.actCode :child.subActCode}&isActive=${child.isActive eq true ? 'false' : 'true'}" 
			                               	 	class="btn ${child.isActive eq true ? 'btn-success' : 'btn-danger'} btn-xs" data-toggle="tooltip">
			                               	 	<c:if test="${child.isActive eq true}"><i class = "fa fa-lock" title="InActive" ></i></c:if>
			                               	 	<c:if test="${child.isActive eq false}"><i class = "fa fa-unlock" title="Active" ></i></c:if>
			                               	  </a>
			                                </td>
			                            </tr>
                          			</c:forEach> 
                        		</tbody>
                    		</table>
                		</div>
            		</div>
        		</div>
			</div>
		</div>
	</div>
</div>
<form method="GET" id="formId">
</form>
<!-- Modal End -->

<script>
    $(function() {
	$('.selectpicker').multiselect('rebuild');
});


 function formSubmitMaster(status){
	 if(status === 'ACTIVITY'){
		 if($("#parentId").val() == '0'){
			 bootbox.alert("Please select component");
			 return false;
		 }
		 if($("#childName").val().trim() == ""){
			 bootbox.alert("Please provide activity");
			 return false;
		 }

	 }
	 if(status === 'SUB-ACTIVITY'){
		 if($("#cmpCode").val() == '0'){
			 bootbox.alert("Please select component");
			 return false;
		 }
		 if($("#parentId").val() == '0'){
			 bootbox.alert("Please select activity");
			 return false;
		 }
		 if($("#childName").val().trim() == ""){
			 bootbox.alert("Please provide sub activity");
			 return false;
		 }
	 }
	 var message = status === 'ACTIVITY' ? 'activity' : 'sub activity';
	 bootbox.confirm("Do you want to save "+message+" ?",
			function(result){
		 		if(result == true){
		 			$("#masterSub").submit();
		 		}
	 });
	 
 }
 
 function getActivityListByCmpCode(){
	 var cmpCode = $("#cmpCode").val();
	 $("#parentId").empty().append("<option value='0'>--- Select ---</option>");

	    let html="<option value='0'>--- Select ---</option>";
	    $('#loader').removeClass('hidden');
	$.get( contextPath+"/core/getActivityList", { cmpCode: cmpCode } )
	  .done(function( data ) {
	    $('#loader').addClass('hidden');
	    //make a for loop for data 
	    data.forEach(element => {
	        html = html + "<option value='"+element.actCode+"'>"+element.actNameEn+"</option>";
	    });

	    $("#parentId").empty().append(html);
		if('${masterVO.actCode}' != ''){
			$("#parentId").val('${masterVO.actCode}');
		}
	    
	  })
	  .fail(function() {
		$('#loader').addClass('hidden');
	    bootbox.alert( "error" );
	  });
 }
</script>
<script>
$(document).ready(function(){
	if(${masterCall eq 'SUB-ACTIVITY'}){
		getActivityListByCmpCode();
	}
});
</script>
