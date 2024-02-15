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
<style>
  /* Add this style to remove underline from anchor tags */
  a.btn-primary {
    text-decoration: none;
  }
</style>

<div class="row mt-3">
        <div class="col-md-6"> <h5 class="change-color">View Member</h5></div>
        <div class="col-md-6"> <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item"><a href="#">Farmer
                  Management</a></li>
              <li class="breadcrumb-item active" aria-current="page">Member Registration</li>
            </ol>
          </nav></div>
        <div class="col-md-12 ">

          <!-- Rahul Edit -->
          <hr style="margin-top: 5px;" />
          <form>
            <div class="row">
              <div class="col-md-12">
                <div class="row">
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">
                     From Date<span style="color:red">*</span>
                    </label>
                    <div class="datepicker-box ">
                      <input type="text"
                        class="form-control form-control-sm datepicker_con" />
                      <i class='bx bx-calendar-week'></i>
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">
                     To Date<span style="color:red">*</span>
                    </label>
                    <div class="datepicker-box ">
                      <input type="text"
                        class="form-control form-control-sm datepicker_con" />
                      <i class='bx bx-calendar-week'></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </form>
          
          <div class="table-responsive">
            <table
              class="datatable table table-striped table-bordered exportbtn mt-3 "
              id="apndTable">
              <thead>
                <tr>
                  <th>Sl No</th>
                  <th>Name of the Farmer</th>
                  <th style="width:150px">Aadhar No.</th>
                  <th>Gender</th>
                  <th>Caste</th>
                  <th style="width: 100px;">Total Area in Acre</th>
                  <th style="width: 100px;">Payment Amount</th>
                  <th style="width:100px">Mode of Payment</th>
                  <th style="width:100px">Acchachment</th>
                  <th>Action</th>
              </thead>
              <tbody>
              <c:forEach var="member" items="${memberList}" varStatus="loop">
                <tr class="gradeA">
                  <td>${loop.index+1}</td>
                  <td>${member.name }</td>
                  <td>${member.aadharNo }</td>
                  <td>${member.gender.genderId }</td>
                  <td>${member.caste.casteId }</td>
                  <td>${member.totalArea}</td>
                  <td>${member.paymentAmnt}</td>
                  <td>${member.paymentMode}</td>
                  <td class="uploadedimg">
                      <a href="../assets/img/single.jpg"> <img class="img-fluid img-thumbnail" src="../assets/img/single.jpg" alt="Random Image"> </a>
                  </td>
                  <td>
                    <a href="${contextPath}/member?memberCode=${member.memberCode}" class="btn-primary" title="Update">Update</a>
                  </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>