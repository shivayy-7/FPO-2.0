
		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<h4 class="card-title">Grampanchayat List</h4>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table id="basic-datatables" class="display table table-bordered table-hover DataTableBtn" >
								<thead>
									<tr>
									    <th>Sl.No</th>
									    <th>District</th>
									    <th>Block</th>
										<th>GP Name </th>
										<th>GP Code</th>
										<th>GP LGD Code</th>
										<th>GP Census Code</th>
										<th>GP Tribal</th>
										<th>Status</th>
										<th>Action</th> 													
									</tr>
								</thead>
								<tbody id="tbd">
							 	   <c:forEach items="${gpList}" var="grampanchayat" varStatus="count"> 
										<tr>
										    <td> ${count.count} </td>
										     <td>${grampanchayat.block.district.districtNameEN}</td>
										    <td>${grampanchayat.block.blockNameEN}</td>
											<td>${grampanchayat.gpNameEN}</td>
											<td>${grampanchayat.gpCode}</td>
											<td>${grampanchayat.gpLgdCode}</td>
											<td>${grampanchayat.gpCensusCode}</td>
											<td>${grampanchayat.gpTribal eq true ? 'Yes' : 'No' }</td>
											<td>${grampanchayat.isActive eq true ? 'Active' : 'InActive' }</td>
											<td>
											<button class="btn btn-primary btn-sm" data-toggle="tooltip" title="Edit" onclick="editGrampanchayatById('${grampanchayat.gpId}')">
												<i class="fas fa-edit" ></i>
											</button>
											<a href="${contextPath}/core/activeInActiveDmgy/GP/${grampanchayat.gpId}/${grampanchayat.isActive eq true ? 'false' : 'true'}" 
											 	class="btn ${grampanchayat.isActive eq true ? 'btn-danger' : 'btn-success'} btn-xs" data-toggle="tooltip">
                                       			<i class="${grampanchayat.isActive eq true ? 'fa fa-lock' : 'fa fa-unlock'}" title="${grampanchayat.isActive eq true ? 'InActive' : 'Active'}"></i>
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
		
	function editGrampanchayatById(id){
	    $("#formId").attr('action','${contextPath}/core/grampanchayat/edit/'+ id +'');
	    $("#formId").submit();
   }
	</script>
	
