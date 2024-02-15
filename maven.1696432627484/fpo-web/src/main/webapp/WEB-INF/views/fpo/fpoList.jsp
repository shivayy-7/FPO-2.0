<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/ajaxJs/commonAjax.js"></script>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="tlength" value="${fn:length(managementDetails)}" />

<style>
.mandotory::before {
  content: '* '; /* Display an asterisk followed by a space before the header text */
  color: red;    /* Optionally, set the color to red or any other desired color */
}
.separator {
    margin: 0 10px; /* Adjust the spacing as needed */
    color: #333;  /* Change the color of the separator */
}

</style>
<div class="content">
   <div class="panel-header bg-primary-gradient">
      <div class="page-inner py-4">
         <div
            class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
            <div>
              <c:if test="${not empty fpoList}"><h2 class="text-blue pb-2 fw-bold">FPO LIST</h2></c:if>
              <c:if test="${not empty eventAvailable}"><h2 class="text-blue pb-2 fw-bold">FPO EVENTS</h2></c:if>
            </div>
<%--             <jsp:include page="/WEB-INF/views/message.jsp" /> --%>
            <div class="ml-md-auto mb-4 py-2 py-md-0">
               <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a> 
               <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2">/FPO List</a>
            </div>
         </div>
      </div>
   </div>
   <div class="page-inner mt--5 pb-0">
   <%@ include file="/WEB-INF/views/message.jsp"%>
   <%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>
      <div class="row mt-3">
         <div class="col-md-12">
            
				<c:if test="${not empty eventAvailable}">
				   <div class="card full-height">
						<div class="card-header">
							<div class="panel-actions">
								<a href="#" class="fa fa-caret-down"></a>
							</div>
							<h4 class="card-title">FPO EVENTS :</h4>
						</div>
						<div class="card-body" style="">
							<div class="col-md-12">

								<div align="center">
									<table border="1" cellpadding="5">
										<tr>
											<th>#Sl.No</th>
											<th>Subject</th>
											<th>Trainer Name</th>
											<th>Event Description</th>
											<th>Event Date</th>
											<th>Action</th>
										</tr>
										<c:forEach var="event" items="${eventAvailable.trainingFpoMap}" varStatus="loop">
											<tr>
												<td>${loop.index + 1}</td>
												<td><c:out value="${event.trainingId.subject}" /></td>
												<td><c:out value="${event.trainingId.trainer.cbboMngmtName}" /></td>
												<td><c:out value="${event.trainingId.eventDesc}" /></td>
												<td><c:out value="${event.trainingId.dateOfEvent}" /></td>
												<td>
													<c:choose>
														<c:when test="${event.trainingAcceptance ne null}">
															${event.trainingAcceptance ? '<i class="fa fa-check fa-lg" aria-hidden="true" style="color: green;"></i>' : '<i class="fa fa-times fa-lg" aria-hidden="true" style="color: red;"></i>'}
														</c:when>
														<c:otherwise>
															<a href="${contextPath}/fpo/event-Acceptance?eventCode=${event.trainingId.trainingCode}&status='ACCEPT'"> 
																<i class="fa fa-check fa-lg" aria-hidden="true" style="color: green;"></i> </a> 
																<span class="separator"> | </span>
																<a href="${contextPath}/fpo/event-Acceptance?eventCode=${event.trainingId.trainingCode}&status='REJECT">
																	<i class="fa fa-times fa-lg" aria-hidden="true" style="color: red;"></i></a> 
														</c:otherwise>
													</c:choose>
											</tr>
										</c:forEach>
									</table>
								</div>

							</div>
						</div>
					</div>
				   
				</c:if>
            
         </div>
      </div>
      
      <div class="table-responsive">
            <table
              class="datatable table table-striped table-bordered exportbtn mt-3 "
              id="apndTable">
              <thead>
                <tr>
                  <th>#Sl.No</th>
                  <th>Fpo Code</th>
                  <th style="width:150px">Fpo Name</th>
                  <th>Fpo Email</th>
                  <th style="width: 100px;">Date Of Incorporation</th>
                  <th style="width: 100px;">Date Of Reg</th>
                  <th style="width:100px">Status</th>
<!--                   <th style="width:100px">Acchachment</th> -->
                  <th>Action</th>
              </thead>
              <tbody>
                <c:forEach var="fpo" items="${fpoList}" varStatus="loop">
											<tr>
												<td>${loop.index + 1}</td>
												<td>${fpo.fpoCode}</td>
												<td>${fpo.fpoName}</td>
												<td>${fpo.email}</td>
												<td>
												    <fmt:formatDate value="${fpo.dateOfIncorporation}" pattern="dd-MM-yyyy" />
												</td>
												<td>
												    <fmt:formatDate value="${fpo.dateOfReg}" pattern="dd-MM-yyyy" />
												</td>
												<td>${fpo.status}</td>
												<td><button type="button" class="btn-primary" title="Update" onclick="viewFpoByFpoCode('${fpo.fpoCode}')">Update</button>
<%-- 												<a href="${contextPath}/fpo/list?fpoCode=${fpo.fpoCode}"><i --%>
<!-- 														class="fa fa-eye" aria-hidden="true"></i></a> <a></a></td> -->
											</tr>
										</c:forEach>
              </tbody>
            </table>
          </div>
      
      
   </div>
   
   <form action="${contextPath}/fpo" method="get" id="fpoCodeForm">
      <input type="hidden" name="fpoCode" id="fpoCode">
   </form>
   
   <script>
     function viewFpoByFpoCode(fpoCode){
    	 debugger;
    	 $('#fpoCode').val(fpoCode);
    	 $('#fpoCodeForm').submit();
     }
   </script>
   
   	 
