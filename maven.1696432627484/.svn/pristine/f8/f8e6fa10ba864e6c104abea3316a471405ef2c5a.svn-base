<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script src="${contextPath}/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.js"></script>
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/ajaxJs/commonAjax.js"></script>


<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="row mt-3">
        <div class="col-md-6"> <h5 class="change-color"> Add Training Information </h5></div>
        <%@ include file="/WEB-INF/views/message.jsp"%>
        <div class="col-md-6"> <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item"><a href="#"> Training Management</a></li>
              <li class="breadcrumb-item active" aria-current="page"> Add Training Information</li>
            </ol>
          </nav></div>
        <div class="col-md-12 ">

          <!-- Rahul Edit -->
          <hr style="margin-top: 5px;" />
          <form action="${contextPath}/training/manageTraining" id="submitForm" method="POST"> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="trainingVO.trainingId" id="pcProfileId" value="${trainingDtls.trainingVO.trainingId}" />
						<input type="hidden" name="trainingVO.trainingCode" value="${trainingDtls.trainingVO.trainingCode}"/>
						<input type="hidden" name="trainingVO.status" id="statusCode" value="${trainingDtls.trainingVO.status}"/>
						
            <div class="row">
              <div class="col-md-12">
                <div class="row align-items-end">

                  <div class="form-group col-md-2">
                    <label for="trainingType" class="required">Training Type <!-- <span style="color:red">*</span> --> </label>
                    <select class="form-select" id="trainingType" name="trainingVO.trainingType">
                      <option value="0">-Select-</option>
                      <option value="Exposure Visit" ${trainingDtls.trainingVO.trainingType eq 'Exposure Visit' ? 'selected':''}> Exposure Visit</option>
                      <option value="Training" ${trainingDtls.trainingVO.trainingType eq 'Training' ? 'selected':''}> Training</option>
                    </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="trainingName" class="required"> Training Name <!-- <span style="color:red">*</span>  --></label>
                    <input class="form-control form-control-sm AlphabetsOnly" type="text" id="trainingName" name="trainingVO.trainingName"  value="${trainingDtls.trainingVO.trainingName}" >
                  </div>
                  <div class="form-group col-md-2">
                    <label for="dateOfTraining" class="required">Date of Training<!-- <span style="color:red">*</span>  --></label>
                    <div class="datepicker-box">
                      <input class="datepicker_con form-control form-control-sm" type="text" id="dateOfTraining" name="trainingVO.dateOfTraining" value="${trainingDtls.trainingVO.dateOfTraining}">
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="trainerName" class="required">Trainer Name<!-- <span style="color:red">*</span>  --></label>
                    <div class="datepicker-box">
                      <input class="form-control form-control-sm AlphabetsOnly" type="text" id="trainerName" name="trainingVO.trainerName" value="${trainingDtls.trainingVO.trainerName}">
                    </div>
                  </div>
                  
                  <div class="form-group col-md-2">
                    <label for="noOfDay" class="required">Number of Days<!-- <span style="color:red">*</span>  --></label>
                    <input class="form-control form-control-sm NumbersOnly" type="text" id="noOfDay" name="trainingVO.noOfDay" value="${trainingDtls.trainingVO.noOfDay}">
                  </div>
                  <div class="form-group col-md-2">
                    <label for="fpo" class="required">FIG group<!-- <span style="color:red">*</span>  --></label>
                    <select class="form-select form-control form-control-sm selectpicker" multiple data-live-search="true" id="fpo" name="fpoId" onchange="updateFarmersCount()">
                      <option value="0">-Select-</option>
                      <c:forEach items="${fpoList}" var="fpo">   
                               <option value="${fpo.fpoId}"  <%-- ${trainingDtls.trainingFpoMapVOList.fpoId.fpoId eq fpo.fpoId ? 'selected' : ''} --%> > ${fpo.fpoName}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="noOfFarmers" class="required">Number of Farmers<!-- <span style="color:red">*</span>  --></label>
                    <div class="calanderholder">
                      <input type="text" class="form-control form-control-sm NumbersOnly" id="noOfFarmers" name="trainingVO.noOfFarmers" value="${trainingDtls.trainingVO.noOfFarmers}" readonly="readonly" />
                    </div>
                  </div> 

                  <div class="col-md-2 text-start mb-10"><button type="button" class="btn-submit" onclick="submitForm('UPCOMING')" >Submit</button></div>
                  
                  
                </div>
              </div>
            </div>
          </form>
          </div>
          </div>
          
<script>
function updateFarmersCount() {
	  var selectedFpos = document.getElementById("fpo").selectedOptions;
	  
	  document.getElementById("noOfFarmers").value = selectedFpos.length;
	}
	
function submitForm(status){
	debugger;
	var labels = document.querySelectorAll('label[class="required"]');
	var falseFields=0;
	labels.forEach(function(label) {
	    var labelId = label.getAttribute('for');
	    var inputElement = document.getElementById(labelId);

	    if (inputElement) {
	        var inputValue = inputElement.value;

	        if (inputValue === '' || inputValue === undefined || inputValue === '0') {
	            alert("Fill the " + labelId);
	            falseFields ++;
	        } 
	    } else {
	        console.error("Input element not found for label with ID: " + labelId);
	    }
	});
	
	if (falseFields > 0) {
	    console.log("There are empty or undefined fields.");
	} else {
		$("#statusCode").val(status);
		$("#submitForm").submit();
	}
	
}
</script>

<script>
$(document).ready(function() {
	var labels = document.querySelectorAll('label[for="required"]');
	labels.forEach(function(label) {
	    var span = document.createElement('span');
	    span.style.color = 'red';
	    span.innerHTML = ' *';
	    label.appendChild(span);
	});
  });
</script>