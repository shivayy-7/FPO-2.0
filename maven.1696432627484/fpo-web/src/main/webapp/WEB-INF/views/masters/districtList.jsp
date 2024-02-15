<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<h4 class="card-title">District List</h4>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table id="basic-datatables" class="display table table-bordered table-hover DataTableBtn" >
								<thead>
									<tr>
									    <th>Sl.No</th>
									    <th>State</th>
										<th>District Name</th>
										<th>District Code</th>
										<th>District LGD Code</th>
										<th>District Census Code</th>
										<th>District Tribal</th>
										<th>Status</th>
										<th>Action</th> 													
									</tr>
								</thead>
								<tbody id="tbd">
							 	   <c:forEach items="${districtList}" var="district" varStatus="count"> 
										<tr>
										     <td> ${count.count} </td>
										     <td>${district.state.stateNameEN}</td>
											<td>${district.districtNameEN}</td>
											<td>${district.districtCode}</td>
											<td>${district.districtLgdCode}</td>
											<td>${district.districtCensusCode}</td>
											<td>${district.districtTribal eq true ? 'Yes' : 'No' }</td>
											<td>${district.isActive eq true ? 'Active' : 'InActive' }</td>
											<td>
											<button class="btn btn-primary btn-sm" data-toggle="tooltip" title="Edit" onclick="editDistrictById('${district.districtId}')">
												<i class="fas fa-edit" ></i>
											</button>
											 <a href="${contextPath}/core/activeInActiveDmgy/DIST/${district.districtId}/${district.isActive eq true ? 'false' : 'true'}" 
											 	class="btn ${district.isActive eq true ? 'btn-danger' : 'btn-success'} btn-xs" data-toggle="tooltip">
                                       			<i class="${district.isActive eq true ? 'fa fa-lock' : 'fa fa-unlock'}" title="${district.isActive eq true ? 'InActive' : 'Active'}"></i>
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
		

		  <!-- Modal End -->
<form  method="GET" id="formId">
</form>
	<!-- Atlantis JS -->
	<script src="../assets/js/atlantis.min.js"></script>
	<script >
		
	function editDistrictById(id){
	    $("#formId").attr('action','${contextPath}/core/district/edit/'+ id +'');
	    $("#formId").submit();
   }
	
	</script>
	
