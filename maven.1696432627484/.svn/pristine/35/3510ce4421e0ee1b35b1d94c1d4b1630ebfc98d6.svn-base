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


 <link rel="stylesheet" href="${contextPath}/assets/css/style.css">

<div class="row mt-3">
        <div class="col-md-6"> <h5 class="change-color">Add Farmer</h5></div>
        <div class="col-md-6"> <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item"><a href="#">Farmer
                  Management</a></li>
              <li class="breadcrumb-item active" aria-current="page">Add Farmer</li>
            </ol>
          </nav>
          </div>
          
          <hr style="margin-top: 5px;" />
          <div>
              <input type="text" name="aadharInput" id="aadharInput" style="width: 200px; margin-bottom: 10px;" class="NumbersOnly" placeholder="Enter Aadhar" onkeyup="getDetailsByAadhar(this.value, 'memberDetailsContainer')">
              </div>
              <div id="memberDetailsContainer">
       <c:forEach var="member" items="${memberList}">
      <div class="container" id="memberDetailsContainer">
        <div class="row">
          <div class="col-md-12" style="background: #65666612;
          padding: 14px 14px;
          border-radius: 10px;">
            <div class="row">
            <div class="col-md-4 " style="display:flex;">
              <div class="img-design">
              <img src="${contextPath}assets/image/avtar.png">
              </div>
              
              <div class="text-div">
                <h4>${member.name }</h4>
                <p>Aadhaar : <span>${member.aadharNo}</span></p>
                <c:if test="${not empty member.farmer.farmerId}">
                <p>Mobile : <span> ${member.farmer.mobile}</span></p>
                </c:if>
<%--                 <c:if test="${empty member.farmer.farmerId}"> --%>
<%--                 <p>Gender : <span> ${member.gender.genderNameEN}</span></p> --%>
<%--                 </c:if> --%>
                </div>
              </div>
                <div class="col-md-8">
                <div class="row">
                <div class="col-md-3">
                <div class="icon-style">
                  <i class='bx bx-cog'></i>
                </div>
                <div class="paragraph-div">
                  <p>Basic Details</p>
                </div>
                </div>
                <div class="col-md-3">
                  <div class="icon-style">
                    <i class='bx bx-user'></i>
                  </div>
                  <div class="paragraph-div">
                    <p>Occupation Details</p>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="icon-style">
                    <i class='bx bxs-bank' ></i>
                  </div>
                  <div class="paragraph-div">
                    <p>Bank Details</p>
                  </div>
                  </div>
                  <div class="col-md-3">
                    <div class="icon-style">
                      <i class='bx bx-upload'></i>
                    </div>
                    <div class="paragraph-div">
                      <p>Documents Upload</p>
                    </div>
                    </div>
                    <div class="col-md-3 button-design" style="text-align: center;">
                      <button type="button"><a href="${contextPath}/farmer?memberCode=${member.memberCode}">View Or Edit</a></button>
                    </div>
                </div>
              </div>
              <div class="col-md-4 button-design" style="margin-top: -33px;">
                <button type="button" onclick="updateMemberDtls('${member.memberCode}')"><i class='bx bx-upload'></i>Update Profile</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>
      <hr>
      </c:forEach>
      </div>
      
      <form action="${contextPath}/member" id="formSubmit">
            <input type="hidden" name="memberCode" id="memberCode" />
      </form>
      
 <script>

 function updateMemberDtls(memberCode){
	 debugger;
	 $("#memberCode").val(memberCode);
	 $("#formSubmit").submit();
 }
 
 function getDetailsByAadhar(value, fieldId) {
	    debugger;
	    $.get(contextPath + "/core/getData", { id: value, identity: 'MEMBER' })
	        .done(function (data) {
	            var memberDetails = data;
	            if (memberDetails !== null) {
	                // Create the HTML dynamically based on memberDetails
	                var html = `
	                    <div class="container">
	                        <div class="row">
	                            <div class="col-md-12" style="background: #65666612; padding: 14px 14px; border-radius: 10px;">
	                                <div class="row">
	                                    <div class="col-md-4 " style="display:flex;">
	                                        <div class="img-design">
	                                            <img src="${contextPath}assets/image/avtar.png">
	                                        </div>
	                                        <div class="text-div">
	                                            <h4>${memberDetails.name}</h4>
	                                            <p>Aadhaar : <span>${memberDetails.aadharNo}</span></p>
	                                            <p>Gender : <span>${memberDetails.gender.genderNameEN}</span></p>
	                                        </div>
	                                    </div>
	                                    <div class="col-md-8">
	                                        <div class="row">
	                                            <div class="col-md-3">
	                                                <div class="icon-style">
	                                                    <i class='bx bx-cog'></i>
	                                                </div>
	                                                <div class="paragraph-div">
	                                                    <p>Basic Details</p>
	                                                </div>
	                                            </div>
	                                            <div class="col-md-3">
	                                                <div class="icon-style">
	                                                    <i class='bx bx-user'></i>
	                                                </div>
	                                                <div class="paragraph-div">
	                                                    <p>Occupation Details</p>
	                                                </div>
	                                            </div>
	                                            <div class="col-md-3">
	                                                <div class="icon-style">
	                                                    <i class='bx bxs-bank'></i>
	                                                </div>
	                                                <div class="paragraph-div">
	                                                    <p>Bank Details</p>
	                                                </div>
	                                            </div>
	                                            <div class="col-md-3">
	                                                <div class="icon-style">
	                                                    <i class='bx bx-upload'></i>
	                                                </div>
	                                                <div class="paragraph-div">
	                                                    <p>Documents Upload</p>
	                                                </div>
	                                            </div>
	                                            <div class="col-md-3 button-design" style="text-align: center;">
	                                                <button type="button"><a href="farmerManagement_vieworedit.html">View Or Edit</a></button>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div class="col-md-4 button-design" style="margin-top: -33px;">
	                                        <button type="button"><i class='bx bx-upload'></i>Update Profile</button>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                `;

	                // Append the dynamically generated HTML to an element with a specific ID (or replace it)
	                $("#" + fieldId).empty().append(html);
	            }
	        })
	        .fail(function () {
	            $('#loader').addClass('hidden');
	            alert("error");
	        });
	}
 
 </script>