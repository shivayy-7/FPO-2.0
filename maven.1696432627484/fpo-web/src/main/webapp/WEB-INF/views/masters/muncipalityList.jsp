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
					<h2 class="text-blue pb-2 fw-bold">Manage Municipality List</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
					<a href="${contextPath}/home"
							class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
						class="fa fa-home"></i></a> <a href="#"
					class="btn btn-sm btn-border btn-blue btn-round mr-2">/Municipality List</a>
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
						<h4 class="card-title">Municipality List</h4>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table id="basic-datatables" class="display table table-bordered table-hover DataTableBtn" >
								<thead>
									<tr>
									    <th>Sl.No</th>
									    <th>District</th>
										<th>Municipality Name</th>
										<th>Municipality Code</th>
										<th>Municipality LGD Code</th>
										<th>Municipality Census Code</th>
										<th>Municipality Tribal</th>
										<th>Status</th>
										<th>Action</th> 													
									</tr>
								</thead>
								<tbody id="tbd">
							 	   <c:forEach items="${municipalityList}" var="municipality" varStatus="count"> 
										<tr>
										    <td> ${count.count} </td>
										    <td>${municipality.district.districtNameEN}</td>
											<td>${municipality.municipalityNameEn}</td>
											<td>${municipality.municipalityCode}</td>
											<td>${municipality.muniLgdCode}</td>
											<td>${municipality.muniCensusCode}</td>
											<td>${municipality.muniTribal}</td>
											<c:if test="${municipality.isActive eq true }">
											<td>Active</td>
											</c:if>
											<c:if test="${municipality.isActive eq false }">
											<td>InActive</td>
											</c:if>
											
											<td>
											<button class="btn btn-warning btn-sm" data-toggle="tooltip" title="Edit" onclick="editMunicipalityById('${municipality.municipalityId}')">
												<i class="fas fa-edit" ></i>
											</button>
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
		

		  <!-- Modal End -->
<form  method="GET" id="formId">
</form>
	<!-- Atlantis JS -->
	<script src="../assets/js/atlantis.min.js"></script>
	<script >
		
	function editMunicipalityById(id){
	    $("#formId").attr('action','${contextPath}/core/municipality/edit/'+ id +'');
	    $("#formId").submit();
   }
	
	</script>
	
