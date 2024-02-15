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
</style>
<div class="content">
   <div class="panel-header bg-primary-gradient">
      <div class="page-inner py-4">
         <div
            class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
            <div>
               <h2 class="text-blue pb-2 fw-bold">CBBO List</h2>
            </div>
            <jsp:include page="/WEB-INF/views/message.jsp" />
            <div class="ml-md-auto mb-4 py-2 py-md-0">
               <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a> 
               <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2">/CBBO List</a>
            </div>
         </div>
      </div>
   </div>
   <div class="page-inner mt--5 pb-0">
   <%@ include file="/WEB-INF/views/message.jsp"%>
   <%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>
      <div class="row mt-3">
         <div class="col-md-12">
            <div class="card full-height">
               <div class="card-header">
                  <div class="panel-actions">
                     <a href="#" class="fa fa-caret-down"></a>
                  </div>
                  <h4 class="card-title">CBBO List :</h4>
               </div>
               <div class="card-body" style="">
                  <div class="col-md-12">
                  
                  <div align="center">
			<table border="1" cellpadding="5">
			<tr>
							<th>#Sl.No</th>
                     <th>Cbbo Code</th>
                     <th>Cbbo Name</th>
                     <th>Cbbo Category</th>
                     <th>Scheme</th>
							<th>Pin</th>
			            <th>Status</th>
							<th>Action</th>
			</tr>
			<c:forEach var="cbbo" items="${cbboList}" varStatus="loop">
			<tr>
								<td>${loop.index + 1}</td>
                        <td><c:out value="${cbbo.cbboCode}" /></td>
                        <td><c:out value="${cbbo.cbboName}" /></td>
                        <td><c:out value="${cbbo.cbboCategory}" /></td>
                        <td><c:out value="${cbbo.scheme.schemeCode}" /></td>
								<td><c:out value="${cbbo.pin}" /></td>
                        <td><c:out value="${cbbo.status}" /></td>
								<td>
								<a href="${contextPath}/cbbo/list?cbboCode=${cbbo.cbboCode}"><i class="fa fa-eye" aria-hidden="true"></i></a>
								<a></a>
								</td>
			</tr>
			</c:forEach>
			</table>
</div>
                     
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   
   	 
