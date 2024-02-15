<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/ajaxJs/commonAjax.js"></script>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                    <h2 class="text-blue pb-2 fw-bold">Installment Management</h2>
                </div>
                <div class="ml-md-auto mb-4 py-2 py-md-0">
                    <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a>
                    <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2">/Installment Management</a>
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
                        <h4 class="card-title">Installment Management:</h4>
                    </div>
                    <div class="card-body" style="">
                        <div class="col-md-12">
                            <form action="${contextPath}/installment/milstoneManage" id="submitForm" method="POST"  enctype="multipart/form-data" >
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="id" value="${msData.id}" />
                                <input type="hidden" name="mcpCode" value="${msData.mcpCode}"/>
                                <input type="hidden" name="inst.id" value="${instId}"/>
                                <input type="hidden" id="actionTaken" name="actionTaken"  value=""/> 
                                <input type="hidden" name="formEntity" value="${categoryCode}" />
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="col-md-12 smallInput required" >Installments :</label>
                                        <div class="col-md-12">
                                            <select class="form-control form-control-sm" id="cbboCategory"  onchange="location = this.value;">
                                                <option value="0">--Select--</option>
                                                <c:forEach items="${installment}" var="inst">
                                                    <option value="${contextPath}/installment/milstone?instId=${inst.id}&msStatus=CHILD" ${instId eq inst.id ? 'selected' : ''}>${inst.instName}</option>
                                                </c:forEach>
                                            </select>
                                            
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12 smallInput required" >Milestone :</label>
                                        <div class="col-md-12">

                                                <c:forEach items="${msData.milstone}" var="ms" varStatus="msCount">
                                                    <div class="form-group">
                                                        <label class="col-md-12 smallInput required" for="">${ms.milstoneName} :</label>
                                                        <c:if test="${ms.milstoneType eq 'DOC'}">
                                                        <div class="col-md-12">
                                                            <input type="hidden" name="milstone[${msCount.count-1}].mccVO.id" value="${ms.mccVO.id}" />
                                                            <input type="hidden" name="milstone[${msCount.count-1}].mccVO.mccCode" value="${ms.mccVO.mccCode}"/>
                                                            <input type="hidden" name="milstone[${msCount.count-1}].milstoneId" value="${ms.milstoneId}"/>
                                                            <input type="file" class="form-control form-control-sm col-md-9" name="milstone[${msCount.count-1}].mccVO.mcpMultipartFile" ><a href="#" class="col-md-3 mt-1" ><i style="font-size:20px;" class="fa fa-download" aria-hidden="true"></i></a>
                                                        </div>
                                                        </c:if>
                                                        <c:if test="${ms.milstoneType eq 'TEXT'}">
                                                            <div class="col-md-12">
                                                                <input type="hidden" name="milstone[${msCount.count-1}].mccVO.id" value="${ms.mccVO.id}" />
                                                                <input type="hidden" name="milstone[${msCount.count-1}].mccVO.mccCode" value="${ms.mccVO.mcpCode}"/>
                                                                <input type="hidden" name="milstone[${msCount.count-1}].milstoneId" value="${ms.milstoneId}"/>
                                                                <input type="text" class="form-control form-control-sm col-md-9" name="milstone[${msCount.count-1}].mccVO.mcpValue" ><a href="#" class="col-md-3 mt-1" ><i style="font-size:20px;" class="fa fa-download" aria-hidden="true"></i></a>
                                                            </div>
                                                        </c:if>
                                                     </div>
                                                    </c:forEach>
                                           
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- <div class="col-md-12 text-center mt-3">
                                <input type="submit" class="btn btn-primary btn-sm" value="Save As Draft"/>
                                <input type="button" class="btn btn-success btn-sm" onclick="submitButton('SAVE');" value="Sent For Approval"/>
                                <a href="${contextPath}/home" class="btn btn-danger btn-sm">Back</a>
                               </div> -->
                                <div class="col-md-12 text-center mt-2">
                                    <c:forEach items="${buttons}" var="button">
								<c:if test="${msData.stageForwardedRule.updatedStatus.status ne 'Approved'}">
								<c:choose>
	                         			<c:when test="${button.updatedStatus.status eq 'Revert'}">
	                         			<button type="button" id="submit12" class="${button.actionType.color}" onclick="submitFormData('${button.fwdRuleCode}');" >${button.actionType.actionNameEn}</button>
	                         			</c:when>
	                         			<c:otherwise>
	                         				<button type="button" id="submit1" class="${button.actionType.color} btn_freeze" onclick="submitFormData('${button.fwdRuleCode}')">${button.actionType.actionNameEn}</button>
	                         			</c:otherwise>
	                         		</c:choose>
							   </c:if>
         		            </c:forEach>
                                    <c:choose>
                                        <c:when test="${activeCheck eq true}">
                                            <a type="button" href="${contextPath}/cluster/requested/list" class="btn btn-sm btn-danger btn-center" >Cancel </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a type="button" href="${contextPath}/cluster/list" class="btn btn-sm btn-danger btn-center" >Cancel </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

  
<script>

function submitFormData(actionTaken){
    bootbox.confirm("Do you  want to continue?", function(result) {
				if (result) {
					$("#sub").prop('disabled', true);
					$(".btn_freeze").prop('disabled', true);
					$('#actionTaken').val(actionTaken);
					
					$("#submitForm").submit(); 
				}
			});
  }
</script>