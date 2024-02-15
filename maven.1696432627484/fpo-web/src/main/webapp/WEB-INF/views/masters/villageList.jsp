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
					<h2 class="text-blue pb-2 fw-bold">Manage Village List</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
				<a href="${contextPath}/home" class="btn btn-sm btn-border btn-blue btn-round mr-2">
					<i class="fa fa-home"></i></a> 
				<a href="#"	class="btn btn-sm btn-border btn-blue btn-round mr-2">/ Village List</a>
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
						<h4 class="card-title">Village List</h4>
					</div>
					<div class="card-body">
						<div class="col-md-2">
	                        <div class="form-group">
	                           <label class="col-md-12 required" for="state">District :</label>
	                           <div class="col-md-12">
	                              <select name="districtId" id="districtId" class="form-control form-control-sm" required="required" onchange="findBlockListByDistrictId(this.value,'')">
	                                 <option value="0" >Select</option>
	                                 <c:forEach items="${districtList}" var="dist">
										<option value="${dist.districtId}">${dist.districtNameEN}</option>
									</c:forEach> 
	                              </select>
	                           </div>
	                        </div>
	                     </div>  
	                     <div class="col-md-2">
	                        <div class="form-group">
	                           <label class="col-md-12 required" for="state">Block :</label>
	                           <div class="col-md-12">
	                              <select name="blockId" id="blockId" class="form-control form-control-sm" onchange="findGpListByBlockId(this.value,'');">
	                                 <option value="0" >Select</option>
	                              </select>
	                           </div>
	                        </div>
	                     </div>  
						<div class="col-md-2">
							<div class="form-group">
								<label class="col-md-12 required" for="blockNameEN">GP Name :</label>
								<select name="gpId" id="gpId" class="form-control form-control-sm" onchange="getVillageByGpId(this.value);">
	                                 <option value="0" >Select</option>
	                              </select>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label class="col-md-12 required" for="blockNameEN">Village Name :</label>
								<select name="villageId" id="villageId" class="form-control form-control-sm" required="required">
	                                 <option value="0" >Select</option>
	                              </select>
							</div>
						</div>
                         <div class="col-lg-2" style="margin-top: 20px;">
                            <button type="button" class="btn btn-primary btn-sm" onclick="filter()">Filter</button>
                        </div>
						<div class="table-responsive">
							<table class="display table table-bordered table-hover DataTableBtn" id="datatable-svr">										
								<thead>
									<tr>
									    <th>Sl.No</th>
									    <th>District Name</th>
									    <th>Block Name</th>
									    <th>Gp Name</th>
										<th>Village Name</th>
										<th>Village Code</th>
										<th>Village LGD Code</th>
										<th>Village Census Code</th>
										<th>Village Tribal</th>
										<th>Status</th>
										<th>Action</th> 													
									</tr>
								</thead>
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
<script src="${contextPath}/assets/js/plugin/datatables/datatables.min.js"></script>
<script src="../assets/js/atlantis.min.js"></script>
<script >	
	$().ready(function(){
			getAllVillage(); 
			
			$('.DataTable').DataTable({
				aLengthMenu: [
			[10,25, 50, 100, 200, -1],
			[10,25, 50, 100, 200, "All"]
			],
			iDisplayLength: -1
			});

	});

		
	function editVillageById(id){
	    $("#formId").attr('action','${contextPath}/core/village/edit/'+ id +'');
	    $("#formId").submit();
   }	
	function getAllVillage(){
		
		 var dateSearch  = $('#dateSearch').val();	  		 			   
		   $('#datatable-svr').DataTable().clear().destroy();			   
		   window.dtTableGPAdmin = $('#datatable-svr')
			.DataTable(
					{
						"processing" : true,
						"serverSide" : true,
					     "bSort": false,
						"ajax" : {
							"url" : './getAllVillage.json',
							"data" : {							
								"${_csrf.parameterName}" : '${_csrf.token}'
							},
							"type": "POST"
						},
						 
						"columns" : [ {
							"data" : "slNum"
						},
						{
							"data" : "DistrictName"
						},
						{
							"data" : "BlockName"
						},
						{
							"data" : "GpName"
						},
						{
							"data" : "VillageName"
						},
						{
							"data" : "VillageCode"
						},
						{
							"data" : "VillageLgdCode"
						},
						{
							"data" : "VillageCensusCode"
						},
						{
							"data" : "VillageTribal"
						},
						{
							"data" : "Status"
						},
						{
							"data" : "Edit"
						}
						],
						"columnDefs": [ 
							{
				                 "targets": 10,
				                 render: function (data, type, row, meta) {
				                	 var html = '';
					           			html = html + '<a href="#"  class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="top" title="Edit" onclick="editVillageById('+ row.id+');"><i class="fa fa-edit" aria-hidden="true"></i></a> <a href="${contextPath}/core/activeInActiveDmgy/VLG/'+ row.id+'/'+ row.isActive +'"  class='+(row.isActive == true ? "btn btn-danger btn-xs" : "btn btn-success btn-xs")+' data-toggle="tooltip" title='+(row.isActive == true ? "InActive" : "Active")+' ><i class="fa fa-lock" data-placement="top" aria-hidden="true"></i></a>';
					           			return html;
				                 }
				            },
				]
						
						 
				});
		
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
	
	function getVillageByGpId(gpId){
		if(gpId != "0"){
			$('#loader').removeClass('hidden');
			$.ajax({
				type : "GET",
				url : '${contextPath}/core/findVillageListByGpId',
				dataType : "json",
				data : {
					"gpId" : gpId,
				},
				success : function(response) {
					$('#loader').addClass('hidden');
					var html = "<option value='0'>-Select-</option>";
					var data = response;
					if (data != "" || data != null) {
						$.each(data, function(index, value) {
							html = html + "<option value="+value.villageId+">"
									+ value.villageName + "</option>";
						});
					}
					$('#villageId').empty().append(html);
				},
				error : function(error) {
					$('#loader').addClass('hidden');
					bootbox.alert("No Village Found");
				}
			});
		}else{
			$('#villageId').empty().append("<option value='0'>-Select-</option>");
		}

	}
	
	function filter(){
		var dist = $('#districtId').val();	
		var block = $('#blockId').val();
		var gp = $('#gpId').val();
		var vlg = $('#villageId').val();
		if(dist != "0"){
			var dateSearch  = $('#dateSearch').val();	  		 			   
			   $('#datatable-svr').DataTable().clear().destroy();			   
			   window.dtTableGPAdmin = $('#datatable-svr')
				.DataTable(
						{
							"processing" : true,
							"serverSide" : true,
						     "bSort": false,
							"ajax" : {
								"url" : '${contextPath}/core/village/filter/getFilterAllVillage.json',
								"data" : {							
									"districtId" : dist,
									"blockId" : block,
									"gpId" : gp,
									"villageId" : vlg,
									"${_csrf.parameterName}" : '${_csrf.token}',
								},
								"type": "POST"
							},
							 
							"columns" : [ {
								"data" : "slNum"
							},
							{
								"data" : "DistrictName"
							},
							{
								"data" : "BlockName"
							},
							{
								"data" : "GpName"
							},
							{
								"data" : "VillageName"
							},
							{
								"data" : "VillageCode"
							},
							{
								"data" : "VillageLgdCode"
							},
							{
								"data" : "VillageCensusCode"
							},
							{
								"data" : "VillageTribal"
							},
							{
								"data" : "Status"
							},
							{
								"data" : "Edit"
							}
							],
							"columnDefs": [ 
								{
					                 "targets": 10,
					                 render: function (data, type, row, meta) {
					                	 var html = '';
						           			html = html + '<a href="#"  class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="top" title="Edit" onclick="editVillageById('+ row.id+');"><i class="fa fa-edit" aria-hidden="true"></i></a> <a href="${contextPath}/core/activeInActiveDmgy/VLG/'+ row.id+'/'+ row.isActive +'"  class='+(row.isActive == true ? "btn btn-danger btn-xs" : "btn btn-success btn-xs")+' data-toggle="tooltip" title='+(row.isActive == true ? "InActive" : "Active")+' ><i class="fa fa-lock" data-placement="top" aria-hidden="true"></i></a>';
						           			return html;
					                 }
					            },
					]
							
							 
					});
		}else{
			bootbox.alert("Please select district");
			return false;
		}
		
	}
	</script>
	
