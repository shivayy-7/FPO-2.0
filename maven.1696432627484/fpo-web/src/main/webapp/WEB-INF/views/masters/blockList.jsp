 

		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<h4 class="card-title">Block List</h4>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table id="basic-datatables" class="display table table-bordered table-hover DataTableBtn" >
								<thead>
									<tr>
									    <th>Sl.No</th>
									    <th>District</th>
										<th>Block Name</th>
										<th>Block Code</th>
										<th>Block LGD Code</th>
										<th>Block Census Code</th>
										<th>Block Tribal</th>
										<th>Status</th>
										<th>Action</th> 													
									</tr>
								</thead>
								<tbody id="tbd">
							 	   <c:forEach items="${blockList}" var="block" varStatus="count"> 
										<tr>
										    <td> ${count.count} </td>
										    <td>${block.district.districtNameEN}</td>
											<td>${block.blockNameEN}</td>
											<td>${block.blockCode}</td>
											<td>${block.blockLgdCode}</td>
											<td>${block.blockCensusCode}</td>
											<td>${block.blockTribal eq true ? 'Yes' : 'No' }</td>
											<td>${block.isActive eq true ? 'Active' : 'InActive' }</td>
											<td>
											<button class="btn btn-primary btn-sm" data-toggle="tooltip" title="Edit" onclick="editBlockById('${block.blockId}')">
												<i class="fas fa-edit" ></i>
											</button>
											<a href="${contextPath}/core/activeInActiveDmgy/BLOCK/${block.blockId}/${block.isActive eq true ? 'false' : 'true'}" 
											 	class="btn ${block.isActive eq true ? 'btn-danger' : 'btn-success'} btn-xs" data-toggle="tooltip">
                                       			<i class="${block.isActive eq true ? 'fa fa-lock' : 'fa fa-unlock'}" title="${block.isActive eq true ? 'InActive' : 'Active'}"></i>
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
		
	function editBlockById(id){
	    $("#formId").attr('action','${contextPath}/core/block/edit/'+ id +'');
	    $("#formId").submit();
   }
	
	</script>
	
