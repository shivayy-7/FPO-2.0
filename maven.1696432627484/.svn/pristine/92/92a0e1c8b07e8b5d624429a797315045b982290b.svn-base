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
               <h2 class="text-blue pb-2 fw-bold">CBBO MANAGEMENT LIST</h2>
            </div>
<%--             <jsp:include page="/WEB-INF/views/message.jsp" /> --%>
            <div class="ml-md-auto mb-4 py-2 py-md-0">
               <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a> 
               <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2">/CBBO MANAGEMENT LIST</a>
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
                  <h4 class="card-title">CBBO MANAGEMENT LIST :</h4>
               </div>
               <div class="card-body" style="">
                  <div class="col-md-12">
                  <form action="${contextPath}/training/manageCbboMngmntDtls" id="submitForm" method="POST" >
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  <div class="col-md-12 mt-2">
                                    <table class="table table-bordered"  id="dynamic-table">
                                        <thead>
                                        <tr>
                                            <th class="mandotory">Name</th>
                                            <th class="mandotory">Gender</th>
                                            <th class="mandotory">Age</th>
                                            <th class="mandotory">Educational Qualification</th>
                                            <th class="mandotory">Aadhar No</th>
                                            <th class="mandotory">DIN</th>
                                            <th class="mandotory">PAN</th>
                                            <th class="mandotory">Designation</th>
                                            <th class="mandotory">Is Trainer</th>
                                        </tr>
                                        </thead>
                                        <tbody id="managementRow">
                                       
                                            <c:forEach items="${cbboMngmntDtls}" var="farmerCbboMngmtVO" varStatus="vs">
                                                <tr>
                                                    <td>
                                                        <input type="hidden" id="managementId" name="farmerCbboMngmtVO[${vs.count-1}].id" value="${farmerCbboMngmtVO.id}" />
                                                        <input type="text" class="form-control form-control-sm" id="name${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].cbboMngmtName"  value="${farmerCbboMngmtVO.cbboMngmtName}" >
                                                    </td>
                                                    <td>
                                                        <select class="form-control form-control-sm" id="gender${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].gender.genderId" >
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${genderList}" var="gl">
                                                                <option value="${gl.genderId}" ${gl.genderId eq farmerCbboMngmtVO.gender.genderId ?'selected':''}>${gl.genderNameEN}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control form-control-sm NumbersOnly" maxlength="3" id="age${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].age"  value="${farmerCbboMngmtVO.age}"  >
                                                    </td>

                                                    <td>
                                                        <input type="text" class="form-control form-control-sm" id="education${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].education"  value="${farmerCbboMngmtVO.education}" >
                                                    </td>

                                                    <td>
                                                        <input type="text" class="form-control form-control-sm NumbersOnly" id="aadharNo${vs.count}" maxlength="12" name="farmerCbboMngmtVO[${vs.count-1}].aadharNo" value="${farmerCbboMngmtVO.aadharNo}">
                                                    </td>

                                                    <td>
                                                        <input type="text" class="form-control form-control-sm" id="dinNo${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].dinNo" value="${farmerCbboMngmtVO.dinNo}">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control form-control-sm AlphaNumericOnly" id="pinNo${vs.count}" maxlength="10" name="farmerCbboMngmtVO[${vs.count-1}].pinNo" value="${farmerCbboMngmtVO.pinNo}" >
                                                    </td>

                                                    <td>
                                                        <select class="form-control form-control-sm" id="designation${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].designation.designationId" >
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${designationList}" var="designation">
                                                                <option value="${designation.designationId}" ${designation.designationId eq farmerCbboMngmtVO.designation.designationId ?'selected':''}>${designation.designationName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td>
	                                                 <div id="isTrainer${vs.count}">
														    <label for="yes_no_radio"></label>
														    <p>
														        <input type="radio" id="isTrainer${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].isTrainer" value="true" ${farmerCbboMngmtVO.isTrainer eq true ? 'checked' : ''}>Yes
														    </p>
														    <p>
														        <input type="radio" id="isTrainer${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].isTrainer" value="false" ${farmerCbboMngmtVO.isTrainer eq false ? 'checked' : ''}>No
														    </p>
														</div>
	                                                </td>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div> 
                                <div class="col-md-12 text-center mt-3">
                                        <input type="button" class="btn btn-primary btn-sm" onclick="submitButton('UPDATE');" value="Update"/>
                                </div>
                                </form>
                     
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   
   <script>
   function submitButton(statusCode){
   	debugger;
       $("#submitForm").submit();
   }
   </script>
   	 
