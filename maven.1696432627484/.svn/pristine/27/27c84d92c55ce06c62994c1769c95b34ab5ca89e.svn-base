<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>

<sec:authentication var="principal" property="principal" />

<style>
    .form-group {
        height: auto;
    }
    .frezz{
    background-color:#E7E9EB;
    color:gray;
    }
</style>

<c:set var="activeCheck" value="${principal.primaryRole.roleCode == 'ROLE_FPO' ? true : false}" />
<div class="content">
    <div class="panel-header bg-primary-gradient">
        <div class="page-inner pt-2 mb-2">
            <div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
                <div class="pb-2 hid">
                    <h2 class="pt-2 fw-bold">Manage Installment List</h2>
                </div>
                <div class="ml-md-auto py-2 py-md-0 bred-crumb">
                    <a href="${contextPath}/home" class="btn btn-sm"><i class="fa fa-home"></i></a>
                    <a href="javascript:void(0)" class="btn btn-sm">Installment List</a>
                </div>
            </div>
        </div>
    </div>
    <div class="page-inner mt-2 pb-0">
        <div class="row">
            <%@ include file="/WEB-INF/views/message.jsp"%>
            <div class="col-md-12">
                <div class="card full-height">
                    <div class="card-header">
                        <div class="panel-actions">
                            <a href="#" class="fa fa-caret-down"></a>
                        </div>
                        <h4 class="card-title"> Installment List </h4>
                    </div>
                    <div class="card-body" style="">
                        <div class="col-md-12" id="tabs">
                            <ul class="nav nav-tabs mb-3 ">
                                <c:if test="${activeCheck eq true}">
                                    <li class="nav-item"><a class="nav-link" data-toggle="tab"
                                                            href="#draft" id="a">Draft</a></li>
                                </c:if>
                                <li class="nav-item "><a class="nav-link ${ activeCheck ne true ? 'active' : ''}" data-toggle="tab"
                                                         href="#pending" id="b">Pending</a></li>
                                <li class="nav-item"><a class="nav-link" data-toggle="tab"
                                                        href="#approved" id="c">Approved</a></li>
                                <li class="nav-item"><a class="nav-link" data-toggle="tab"
                                                        href="#revert" id="d">Reverted</a></li>
                            </ul>

                            <!-- Tab panes -->

                            <div class="tab-content">
                                <c:if test="${activeCheck eq true}">
                                    <div class="tab-pane" id="draft">
                                        <div class="col-md-12">
                                            <form class="form-horizontal" action="${contextPath}/fund/bulk/sendForApproval"
                                                  method="post" enctype="multipart/form-data" id="fundBulkForm">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                <input type="hidden" name="tabCode" id="tabCode1" value="${tabCode}">
                                                <input type="hidden" name="viewType" id="viewType" value="BDG_LIST">
                                                <table class="display table table-bordered table-hover DataTableBtn">
                                                    <thead class="thead-light">
                                                    <tr>
                                                        <th style="width: 55px;">Sl No.</th>
                                                        <th>Installment User</th>
                                                        <th>Installment Code</th>
                                                        <th>Installment Number</th>
                                                        <th>Installment Creation Date</th>
                                                        <th>Current Status</th>
                                                        <th>Action</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tablerow">
                                                    <c:forEach items="${allMilestoneCollector}" var="inst" varStatus="count">
                                                        <c:if test="${inst.stageForwardedRule.updatedStatus.status eq 'Draft'}">
                                                                <tr>
                                                                    <td>${count.count}</td>
                                                                    <td>${inst.createdBy.userName}</td>
                                                                    <td>${inst.mcpCode}</td>
                                                                    <td>${inst.inst.instName}</td>
                                                                    <td>${inst.createdOn}</td>
                                                                    <td>${inst.stageForwardedRule.updatedStatus.statusDescription}</td>
                                                                    <td>
                                                                        <a href="${contextPath}/installment/edit?instId=${inst.id}&msStatus=PARENT"
                                                                        class="btn btn-primary btn-sm" title="View"><i class="fas fa-eye"></i></a>
                                                                        <button type="button" class="btn btn-primary btn-sm"
                                                                        onclick="getWorkFlowHistory(${inst.id},'INSTALLMENT')">Track</button></td>
                                                                </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                    </tbody>

                                                </table>
                                            </form>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="tab-pane  ${activeCheck ne true ? 'active show' : ''}" id="pending">
                                    <div class="col-md-12">
                                        <table class="display table table-bordered table-hover DataTableBtn">
                                            <thead class="thead-light">
                                            <tr>
                                                <th style="width: 55px;">Sl No.</th>
                                                <th>Installment User </th>
                                                <th>Installment Code</th>
                                                <th>Installment Number</th>
                                                <th>Installment Creation Date</th>
                                                <th>Current Status</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody id="tablerow">
                                            <c:forEach items="${allMilestoneCollector}" var="inst" varStatus="count">
                                                <c:if test="${inst.stageForwardedRule.updatedStatus.status eq 'Pending'}">
                                                <tr>
                                                    <td>${count.count}</td>
                                                    <td>${inst.createdBy.userName}</td>
                                                    <td>${inst.mcpCode}</td>
                                                    <td>${inst.inst.instName}</td>
                                                    <td>${inst.createdOn}</td>
                                                    <td>${inst.stageForwardedRule.updatedStatus.statusDescription}</td>
                                                    <td>
                                                        <a href="${contextPath}/installment/edit?instId=${inst.id}&msStatus=PARENT"
                                                           class="btn btn-primary btn-sm" title="View"><i class="fas fa-eye"></i></a>
                                                           <button type="button" class="btn btn-primary btn-sm"
                                                           onclick="getWorkFlowHistory(${inst.id},'INSTALLMENT')"><i class="fa fa-thumb-tack" aria-hidden="true"></i></button></td>
                                                </tr>
                                                </c:if>
                                            </c:forEach>
                                            </tbody>

                                        </table>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="approved">
                                    <table class="display table table-bordered table-hover DataTableBtn">
                                        <thead class="thead-light">
                                        <tr>
                                            <th style="width: 55px;">Sl No.</th>
                                            <th>Installment User </th>
                                            <th>Installment Code</th>
                                            <th>Installment Number</th>
                                            <th>Installment Creation Date</th>
                                            <th>Current Status</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tablerow">
                                        <c:forEach items="${allMilestoneCollector}" var="inst" varStatus="count">
                                            <c:if test="${inst.stageForwardedRule.updatedStatus.status eq 'Approved'}">
                                            <tr>
                                                <td>${count.count}</td>
                                                <td>${inst.createdBy.userName}</td>
                                                <td>${inst.mcpCode}</td>
                                                <td>${inst.inst.instName}</td>
                                                <td>${inst.createdOn}</td>
                                                <td>${inst.stageForwardedRule.updatedStatus.statusDescription}</td>
                                                <td>
                                                    <a href="${contextPath}/installment/edit?instId=${inst.id}&msStatus=PARENT"
                                                       class="btn btn-primary btn-sm" title="View"><i class="fas fa-eye"></i></a>
                                                       <button type="button" class="btn btn-primary btn-sm"
                                                       onclick="getWorkFlowHistory(${inst.id},'INSTALLMENT')">Track</button></td>
                                            </tr>
                                            </c:if>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="revert">
                                    <table class="display table table-bordered table-hover DataTableBtn">
                                        <thead class="thead-light">
                                        <tr>
                                            <th style="width: 55px;">Sl No.</th>
                                            <th>Installment User </th>
                                            <th>Installment Code</th>
                                            <th>Installment Number</th>
                                            <th>Installment Creation Date</th>
                                            <th>Current Status</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tablerow">
                                        <c:forEach items="${allMilestoneCollector}" var="inst" varStatus="count">
                                            <c:if test="${inst.stageForwardedRule.updatedStatus.status eq 'Revert'}">
                                            <tr>
                                                <td>${count.count}</td>
                                                <td>${inst.createdBy.userName}</td>
                                                <td>${inst.mcpCode}</td>
                                                <td>${inst.inst.instName}</td>
                                                <td>${inst.createdOn}</td>
                                                <td>${inst.stageForwardedRule.updatedStatus.statusDescription}</td>
                                                <td>
                                                    <a href="${contextPath}/installment/edit?instId=${inst.id}&msStatus=PARENT"
                                                       class="btn btn-primary btn-sm" title="View"><i class="fas fa-eye"></i></a>
                                                       <button type="button" class="btn btn-primary btn-sm"
                                                       onclick="getWorkFlowHistory(${inst.id},'INSTALLMENT')">Track</button>
                                                    </td>
                                            </tr>
                                            </c:if>
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
    </div>
</div>


<div class="modal fade bd-example-modal-lg"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" id="historyModal" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">WorkFlow Track</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
           <div class="col-md-12" style="height: 70vh; overflow-y: scroll;">
            <table class="table table-bordered DataTable">
                <thead>
                  <tr>
                    <th scope="col">From Stage</th>
                    <th scope="col">To Stage</th>
                    <th scope="col">Reported By </th>
                    <th scope="col">Reported On</th>
                  </tr>
                </thead>
                <tbody id="historyData">
                </tbody>
              </table>
          </div>
        </div>
        </div>
      </div>
    </div>
  </div>

<script>

    $(document).ready(function() {
        if('${tabCode}'=='TAB1'){
            $("#draft").addClass("in active");
            $("#a").addClass("active");

        }else if('${tabCode}'=='TAB2'){
            $("#pending").addClass("active show");
            $("#b").addClass("active");

        }else if('${tabCode}'=='TAB3'){
            $("#approved").addClass("active show");
            $("#c").addClass("active");

        }else if('${tabCode}'=='TAB4'){
            $("#revert").addClass("active show");
            $("#d").addClass("active");

        }else{
            $("#draft").addClass("in active");
            $("#a").addClass("active");
        }

    });

    /* 	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
              var target = $(e.target).attr("href") // activated tab
              if(target=="#pending"){
                  $("#tabCode").val("TAB2");
              }else if(target=="#approved"){
                  $("#tabCode").val("TAB3");
              }else if(target=="#reject"){
                  $("#tabCode").val("TAB4");
              }else if(target=="#draft"){
                  $("#tabCode").val("TAB1");
              }
            }); */
function getWorkFlowHistory(formId,moduleForm){
	$.ajax({
		url:window.contextPath+"/fpo_workFlow/getWorkFlowHistory",
		type : "GET",
		data : {
			"formId" :formId,
			"moduleForm" :moduleForm
		},
			success:function(response){
				var htmlSnippet = "";
				 $.each(response, function(index,value){
					htmlSnippet=htmlSnippet+"<tr><td>"+value.fromStage+"</td><td>"+value.toStage+"</td><td>"+value.reportedBy+"</td><td>"+value.reportedOn+"</td></tr>";
				 }); 
				$("#historyData").empty().append(htmlSnippet);
				$("#historyModal").modal('show');
				},
		error:function(error){
				bootbox.alert("error");
			}
		});
	}

</script>

