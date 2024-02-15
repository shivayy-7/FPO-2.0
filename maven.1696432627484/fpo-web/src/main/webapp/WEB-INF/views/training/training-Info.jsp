<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script src="${contextPath}/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.js"></script>
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/ajaxJs/commonAjax.js"></script>


<style>

#upcomingbtn {
      width: 100px;
      height: 40px;
      background-color: blue;
      color: white;
      cursor: pointer;
    }
    
    #upcomingbtn.clicked {
  background-color: red;
  /* Add any other styles you want for the clicked state */
}

</style>

<div class="row mt-3">
        <div class="col-md-6"> <h5 class="change-color"> View Training Information </h5></div>
        <div class="col-md-6"> <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item"><a href="#"> Training Management</a></li>
              <li class="breadcrumb-item active" aria-current="page"> View Training Information</li>
            </ol>
          </nav></div>
        <div class="col-md-12 ">

          <!-- Rahul Edit -->
          <hr style="margin-top: 5px;" />
          <form action="${contextPath}/training/searchInfo" id="submitForm" method="GET" >
              <input type="hidden" name="status" id="statusCode" >
              <div class="col-md-12">
                <div class="row align-items-end">
                  <div class="form-group col-md-3">
                    <label for="inputEmail4">Form Date To Date <span style="color:red">*</span> </label>
                    <input type="text" class="form-control form-control-sm rangePicker" id="fromdateTodate" name="fromdateTodate" value="${fromdateTodate}">
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Training Type <span style="color:red">*</span> </label>
                    <select class="form-select" aria-label="Default select example" id="trainingType" name="trainingType">
                      <option value="0">-Select-</option>
                      <option value="Exposure Visit" ${trainingType eq 'Exposure Visit' ? 'selected':''}> Exposure Visit</option>
                      <option value="Training" ${trainingType eq 'Training' ? 'selected':''}> Training</option>
                    </select>
                  </div>
                  <div class="col-md-2 text-start mb-7"><button type="button" class="btn btn-submit" onclick="searchTrainingData()"><i class="bx bx-search"></i> Search</button></div>
                </div>
              </div>
         </form>

              <div class="col-lg-12">
                <ul class="nav nav-tabs" id="myTab" role="tablist" style="justify-content:left;">
                    <li class="nav-item" role="presentation" id="upcomingbtn">
                      <button type="button" onclick="trainingList('UPCOMING')">Upcoming</button>
                    </li>
                    <li class="nav-item" role="presentation" id="completedbtn">
                      <button type="button"onclick="trainingList('COMPLETED')">Completed</button>
                    </li>
                  </ul>
                  <div class="tab-content" id="myTabContent">
                  <c:if test="${status eq 'UPCOMING'}">
                    <div id="upcoming" >
                        <div class="table-responsive">
                            <table class="datatable table table-striped table-bordered exportbtn mt-3">
                              <thead>
                                <tr>
                                  <th>Sl No ->UPCOMING</th>
                                  <th>Training Type</th>
                                  <th>Training Name</th>
                                  <th>Date of Training</th>
                                  <th>Trainer Name</th>
                                  <th>Number of Farmers</th>
                                  <th>Action</th>
                              </thead>
                              <tbody>
                              <c:forEach var="training" items="${trainingInfo.trainingList}" varStatus="loop">
                                <tr>
                                  <td>${loop.index +1 }</td>
                                  <td>${training.trainingType}</td>
                                  <td>${training.trainingName}</td>
                                  <td><fmt:formatDate value="${training.dateOfTraining}" pattern="dd/MM/yyyy" /></td>
                                  <td>${training.trainerName}</td>
                                  <td>${training.noOfFarmers}</td>
                                  <td>
                                    <button type="button" class="btn btn-success btn-sm" title="View" style="padding: 0px 5px;"><i class='bx bx-file'></i></button>
                                    <a href="${contextPath}/training?trainingCode=${training.trainingCode}" type="button" class="btn btn-info btn-sm text-white" title="Edit" style="padding: 0px 5px;"><i class='bx bx-pencil'></i></a>
                                  </td>
                                </tr>
                                </c:forEach>
                              </tbody>
                            </table>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${status eq 'COMPLETED'}">
                    <div  id="completed" >
                        <div class="table-responsive">
                            <table class="datatable table table-striped table-bordered exportbtn mt-3">
                              <thead>
                                <tr>
                                  <th>Sl No->COMPLETED</th>
                                  <th>Training Type</th>
                                  <th>Training Name</th>
                                  <th>Date of Training</th>
                                  <th>Trainer Name</th>
                                  <th>Expected Farmers</th>
                                  <th>Farmer in Attendance</th>
                                  <th style="width: 70px;">Images</th>
                                  <th>Action</th>
                              </thead>
                              <tbody>
                              <c:forEach var="training" items="${trainingInfo.trainingList}" varStatus="loop">
                                <tr>
                                  <td>${loop.index +1 }</td>
                                  <td>${training.trainingType}</td>
                                  <td>${training.trainingName}</td>
                                  <td><fmt:formatDate value="${training.dateOfTraining}" pattern="dd/MM/yyyy" /></td>
                                  <td>${training.trainerName}</td>
                                  <td>${training.noOfFarmers}</td>
                                  <td></td>
                                  <td></td>
                                  <td>
                                    <button type="button" class="btn btn-success btn-sm" title="View" style="padding: 0px 5px;"><i class='bx bx-file'></i></button>
                                    <a href="${contextPath}/training?trainingCode=${training.trainingCode}" type="button" class="btn btn-info btn-sm text-white" title="Edit" style="padding: 0px 5px;"><i class='bx bx-pencil'></i></a>
                                  </td>
                                </tr>
                              </c:forEach>
                              </tbody>
                            </table>
                        </div>
                    </div>
                    </c:if>
                  </div>
              </div>
                <div class="col-md-12 text-center"><button type="button" class="btn btn-dark">Back</button></div>
              </div>

<script>
function searchTrainingData(){
	debugger;
	$("#submitForm").submit();
}

function trainingList(status){
	debugger;
	$("#statusCode").val(status);
	$("#submitForm").submit();
}


// $(document).ready(function() {
//     debugger;
//     var status = '${status}';
//     if (status !== '' && status !== null) {
//         // Remove "active" class from all li elements
// //         $("#upcomingbtn, #completedbtn").removeClass("active");

//         if (status === 'UPCOMING') {
//             $("#upcomingbtn").addClass("active");
//         } else if (status === 'COMPLETED') {
//             $("#completedbtn").addClass("active");
//         }
//     }
// });

$(document).ready(function() {
    // Using the on method to handle the click event
    $("#upcomingbtn").on("click", function() {
      // Toggle the background color on click
      $(this).toggleClass("clicked");
    });
  });

</script>          