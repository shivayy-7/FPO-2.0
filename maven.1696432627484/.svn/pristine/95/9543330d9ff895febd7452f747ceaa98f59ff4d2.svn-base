<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="displayName" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.primaryRole.displayName}" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="content">
	<div class="panel-header bg-primary-gradient">
      <div class="page-inner">
         <div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
            <div>
               <h2 class="text-white mt-2 fw-bold">
              <%--   <spring:message code="site.dashboard" /> --%>
               </h2>
            </div>
            <div class="ml-md-auto py-2 py-md-0">
               <a href="${contextPath}/" class="btn btn-white btn-border btn-round mr-2"><i class="fa fa-home"></i></a>
            </div>
         </div>
      </div>
   </div>
	<div class="page-inner">
		<div class="page-category">
			<h1><i>WELCOME ${displayName}</i></h1> 
		</div>
	</div>
</div>