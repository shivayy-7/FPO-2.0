<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
table.fancytree-ext-table tbody tr.fancytree-selected {
    background-color: #FFFFFF;
}

</style>
<div class="content">
		<div class="panel-header bg-primary-gradient">
		<div class="page-inner py-4">
			<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
				<div>
					<h2 class="text-white mt-2 fw-bold"><spring:message code="site.admin.acl.configure" /></h2>
					
				</div>
				<div class="ml-md-auto mb-4 py-2 py-md-0">
					<a href="${contextPath}/home" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a>
					<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/<spring:message code="site.administration"/></a>
					<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/<spring:message code="site.admin.module.configure"/></a>
				</div>
			</div>
		</div>
	</div>
	<div class="page-inner mt-5 pb-0">
		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>
						<h4 class="card-title">Menu Permissions</h4>
					</div>
					<div class="card-body">
			 	 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			 	 	<div class="col-md-12">
		 	 	 	 	<div class="col-md-4">
                           <div class="form-group">
                              <label class="col-md-12 required1" for="roleId">Role :</label>
                              <div class="col-md-12">
                                 <select class="form-control" name="roleId" id="roleId"  >
								  	<option value="">--select--</option>
								  	<c:forEach items="${roleList}" var="role">
										<option value="${role.roleCode}" data-id="${role.roleId}">${role.displayName}</option>
									</c:forEach>
								 </select>
                              </div>
                       		</div>
                       	</div>
                       	<!-- Menu Table -->
                       	<div class="col-md-12" style="margin-top: 20px;">
	                        <div class="form-group"> 
	                          <div class="col-sm-12">
	                            <!-- Add a <table> element where the tree should appear: -->
								  <table id="treegrid" class="table table-collapsed">
								    <colgroup>
									    <col width="300px"></col>
									    <col width="300px"></col>
									    <col width="*"></col>
								    </colgroup>
								    <thead>
								      <tr> 
								      	<th>Menu Name</th> 
								      	<th>Menu URL</th>  
								      	<th>Privileges</th>
								      </tr>
								    </thead>
								    <!-- Optionally define a row that serves as template, when new nodes are created: -->
								    <tbody>
								      <tr>
								        <td></td>
								        <td></td>
								        <td></td>
								      </tr>
								    </tbody>
								  </table>
	                          </div>
	                        </div>
                      	</div>
                      	
                      	<div class="col-md-12 text-center">
                      		<a href="${contextPath}/" class="btn btn-warning">Cancel</a>
                      	</div>
                    </div>
			 	 </div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/template" id="selPriv">
	<select class="form-control chosen" multiple="multiple">
		<c:forEach items="${privs}" var="priv">
			<option value="${priv.id}">${priv.desc}</option>
		</c:forEach>
	</select>
</script>
<link href="${contextPath}/assets/js/plugin/jquery-fancytree/skin-xp/ui.fancytree.css" rel="stylesheet" />

<!-- Fancy Tree needs this version of jquery-ui -->
<script src="${contextPath}/assets/js/plugin/jquery-fancytree/jquery-ui.min.js"></script>
<script src="${contextPath}/assets/js/plugin/jquery-fancytree/jquery.fancytree-all.min.js"></script>

<!-- Chosen for Multiselect Tag Support -->
<script src="${contextPath}/assets/js/plugin/jquery.chosen/chosen.jquery.min.js"></script>
<link href="${contextPath}/assets/js/plugin/jquery.chosen/chosen.min.css" rel="stylesheet" />

<!-- end: page -->
<script type="text/javascript">
$().ready(function(){
	/* http://wwwendt.de/tech/fancytree/demo/index.html */
	$("#treegrid").fancytree({
		  checkbox: false,
		  extensions: ["wide", "table"],
		  selectMode: 1,
		  source : [] ,
		  table: {
		        nodeColumnIdx: 0,     // render the node title into the 2nd column
	      },
	      renderNode: function(event, data)
	      {
	    	  $.each(data.node.children, (idx, elem) => {
	    		  elem.selected = false;
	    	  });
	      },
	      renderColumns: function(event, data) {
	          var node = data.node;
              var $tdList = $(node.tr).find(">td");
              

	          // (index #0 is rendered by fancytree by adding the checkbox)
	          $tdList.eq(1).text(node.data.url);
	          
	          if (node.data.isParent == false)
        	  {
	        	  var $template = $($('#selPriv').html());
	        	  $template.attr("data-id",node.data.id);
	        	  
				   $.each(node.data.privs, (idx, elem) => {
					   var opt = $template.find('[value="' + elem.id + '"]')[0];
					   $(opt).prop('selected', true);
				   });
				   $tdList.eq(2).html($template);
				   $('.chosen').chosen({width: "95%",placeholder_text_multiple : "No privileges assinged"});
        	  }
	         
        },
	 });
	
	$('#roleId').change(function(){
		
		var roleCode = $('#roleId option:selected').val();
		
		$.ajax({
			url : "${contextPath}/admin/menu/assinged/" + roleCode,
			type: "GET",
	        dataType: "json",
	        success: function(data) {
	        	let tree = $("#treegrid").fancytree('getTree');
	        	tree.reload(data);
	        },
	        cache: false
		});

	});
	
	 $(document).on('change','.chosen', function(evt, params) {
		 var menuId =  $(evt.currentTarget).attr('data-id');
		 if (params.hasOwnProperty('selected'))
		 {
		 	addPrivilegeToMenu(menuId, params['selected']);
		 }
		 
		 if (params.hasOwnProperty('deselected'))
		 {
			 removePrivilegeFromMenu(menuId, params['deselected']);
		 }
	    
	 });
	 
	 function addPrivilegeToMenu(menuId, prvId)
	 {
		 menuId = parseInt(menuId);
		 prvId =  parseInt(prvId);
		 var roleId = $('#roleId option:selected').attr('data-id');
		 roleId = parseInt(roleId);
		 
		 $.ajax({
			url : "${contextPath}/admin/privilege/assign",
			type: "POST",
			data : {
				"_csrf" : '${_csrf.token}',
				"menuId" : menuId,
				"privId" : prvId,
				"roleId" : roleId
			},
	        dataType: "json",
	        success: function(data) {
	        	bootbox.alert("Privilege assigned succesfully.");
	        },
	        cache: false
		 });
	 }
	 
	 function removePrivilegeFromMenu(menuId, prvId)
	 {
		 menuId = parseInt(menuId);
		 prvId =  parseInt(prvId);
		 var roleId = $('#roleId option:selected').attr('data-id');
		 roleId = parseInt(roleId);
		 
		 $.ajax({
			url : "${contextPath}/admin/privilege/revoke",
			type: "POST",
			data : {
				"_csrf" : '${_csrf.token}',
				"menuId" : menuId,
				"privId" : prvId,
				"roleId" : roleId
			},
	        dataType: "json",
	        success: function(data) {
	        	bootbox.alert("Privilege removed succesfully.");
	        },
	        cache: false
		 });
	 }
});
</script>