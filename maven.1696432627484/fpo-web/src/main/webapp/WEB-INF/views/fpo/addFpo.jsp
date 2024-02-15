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
<%-- <c:set var="tlength" value="${fn:length(managementDetails)}" /> --%>

<style>
.mandotory::before {
  content: '* '; /* Display an asterisk followed by a space before the header text */
  color: red;    /* Optionally, set the color to red or any other desired color */
}
</style>

<%-- <jsp:include page="/WEB-INF/commonSnippets/layoutWithLoginPage/baselayout.jsp"/> --%>

<script src="${contextPath}/WEB-INF/commonSnippets/layoutWithLoginPage/baselayout.jsp"></script>


<div class="row mt-3">
        <div class="col-md-6"> <h5 class="change-color">Add FPO </h5></div>
        <%@ include file="/WEB-INF/views/message.jsp"%>
        <div class="col-md-6"> <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item"><a href="#">FPO
                  Management</a></li>
              <li class="breadcrumb-item active" aria-current="page">Add FPO</li>
            </ol>
          </nav></div>
        <div class="col-md-12 ">

          <!-- Rahul Edit -->
          <hr style="margin-top: 5px;" />
          <h6 class="headingbg mb-2">General Information</h6>
          <form action="${contextPath}/fpo/addNUpdate" id="submitForm" method="POST" enctype="multipart/form-data"> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="fpoVo.fpoId" id="pcProfileId" value="${fpoDtls.fpoVo.fpoId}" />
						<input type="hidden" name="fpoVo.fpoCode" value="${fpoDtls.fpoVo.fpoCode}"/>
						<input type="hidden" name="fpoVo.status" id="statusCode" value="${fpoDtls.fpoVo.status}"/>
						<input type="hidden" name="fpoDemographyVO.demographyId" value="${fpoDtls.fpoDemographyVO.demographyId}"/>
						
            <div class="row">
              <div class="col-md-12">
                <div class="row">

                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Name of FPO <span style="color:red">*</span> </label>
                    <input type="text" name="fpoVo.fpoName" id="fpoCode" class="form-control form-control-sm AlphabetsOnly" value="${fpoDtls.fpoVo.fpoName}">
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Sector <span style="color:red">*</span> </label>
                    <select class="form-select" name="fpoVo.component" id="component" onchange="getActivityListByCmpCode(this.value,'activity')">
                                           <option value="0">--- Select ---</option>
                                           <c:forEach items="${componentList}" var="cmp">   
                                               <option value="${cmp.cmpCode}" ${cmp.cmpCode eq fpoDtls.fpoVo.component ?'selected':''}> ${cmp.cmpNameEn}</option>
                                           </c:forEach>
                    </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Activity/Product <span style="color:red">*</span> </label>
                    <select class="form-control form-control-sm" name="fpoVo.activity" id="activity"  onchange="getSubActivityListByActCode(this.value,'subActivity')" >
                                          <option value="0">--- Select ---</option>
                                          <c:forEach items="${clusterReportData.activityList}" var="activity">   
                                               <option value="${activity.actCode}" > ${activity.actNameEn}</option>
                                           </c:forEach>
                                      </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Sub-Activity <span style="color:red">*</span> </label>
                    <!-- <select class="form-select form-select form-control form-control-sm selectpicker" multiple data-live-search="true" name="fpoVo.subActivity" id="subActivity"> -->
                    <select class="form-select " name="fpoVo.subActivity" id="subActivity">
                                         <option value="0">--- Select ---</option>
                                         <c:forEach items="${clusterReportData.subActivityList}" var="subActivity">   
                                               <option value="${subActivity.subActCode}" > ${subActivity.subActNameEn}</option>
                                           </c:forEach>
                                     </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Date of Registration <span style="color:red">*</span>
                    </label>
                    <div class="datepicker-box">
                      <input type="text" class="form-control form-control-sm datepicker_con" id="dateregistration" name="fpoVo.dateOfReg" value='${fpoDtls.fpoVo.dateOfReg}'>
                    </div>
                  </div>

                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> No. Of Farmers <span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.noOfFrm"  maxlength="6" id="noOfFarmers" class="form-control form-control-sm NumbersOnly" value="${fpoDtls.fpoVo.noOfFrm}">
                    </div>
                  </div>
                </div>
              </div>
            </div>
          
          <h6 class="headingbg mb-2 mt-4">Company Details</h6>
          
            <div class="row">
              <div class="col-md-12">
                <div class="row">
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Date of Incorporation<span style="color:red">*</span>
                    </label>
                    <div class="datepicker-box ">
                      <input type="text" placeholder="" class="form-control form-control-sm datepicker_con"  id="dateOfIncorporation" name="fpoVo.dateOfIncorporation" value='${fpoDtls.fpoVo.dateOfIncorporation}'>
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">CIN<span style="color:red">*</span> </label>

                    <input type="text" name="fpoVo.cin" id="cinno" maxlength="21" class="form-control form-control-sm AlphaNumericOnly" onkeypress="return/[a-zA-Z0-9]/i.test(event.key)" value="${fpoDtls.fpoVo.cin}" >

                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Company PAN<span style="color:red">*</span> </label>
                    <input type="text" name="fpoVo.companyPan" id="panNo" maxlength="10" class="form-control form-control-sm" value="${fpoDtls.fpoVo.companyPan}">
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Company Category<span style="color:red">*</span> </label>

                    <select class="form-control form-control-sm" id="registrationfrom" name="fpoVo.companyCategory">
                                       <option value="0">--Select--</option>
                                     	  <option value="Society" ${fpoDtls.fpoVo.companyCategory eq 'Society' ?'selected':''} >Society</option>
                                     	    <option value="Trust" ${fpoDtls.fpoVo.companyCategory eq 'Trust' ?'selected':''}>Trust</option>
                                     	      <option value="Company" ${fpoDtls.fpoVo.companyCategory eq 'Company' ?'selected':''}>Company</option>
                    </select>

                  </div>
                  
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Age of Company<span style="color:red">*</span> </label>
                    <input type="text" name="fpoVo.ageOfCompany"  maxlength="2" id="ageOfCategory" class="form-control form-control-sm NumbersOnly" value="${fpoDtls.fpoVo.ageOfCompany}">
                  </div>
                  
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> No of Director<span style="color:red">*</span> </label>
                    <input type="text" name="fpoVo.noOfDirector"  maxlength="6" id="noofshareholder" class="form-control form-control-sm NumbersOnly" value="${fpoDtls.fpoVo.noOfDirector}">
                  </div>
                  
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Shared Capital<span style="color:red">*</span> </label>
                    <input type="text" class="form-control form-control-sm NumbersOnly amount" id="sharedCapitalConverted" maxlength="14" placeholder="0.0" name="fpoVo.sharedCapital" value="${fpoDtls.fpoVo.sharedCapital}" onchange="currencyConverter(this.value,'sharedCapitalConverted')">
                  </div>
                </div>
              </div>
            </div>
          
          <h6 class="headingbg mb-2 mt-4">Contact Details</h6>
          
            <div class="row">
              <div class="col-md-12">
                <div class="row">
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Registered Address<span style="color:red">*</span>
                    </label>
                    <div class="calanderholder">
                      <textarea class="form-control form-control-sm" id="regAddress" name="fpoVo.regAddress" rows="4" cols="50">${fpoDtls.fpoVo.regAddress}</textarea>
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Business Address<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <textarea class="form-control form-control-sm" id="busiAddress" name="fpoVo.buisnessAddress" rows="4" cols="50">${fpoDtls.fpoVo.buisnessAddress}</textarea>
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> e-Mail <span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="email" name="fpoVo.email" id="emailId" class="form-control form-control-sm" value="${fpoDtls.fpoVo.email}" onchange="mailVal(this.value)" >
                    </div>
                  </div>
                  <div class="form-group col-md-3">
                    <label for="inputEmail4"> Phone Number of Authorized Signatory<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.mobileOfAuthSignatory" id="contactNo" class="form-control form-control-sm NumbersOnly" maxlength="10" onchange="is_mobile_valid(this);" value="${fpoDtls.fpoVo.mobileOfAuthSignatory}">
                    </div>
                  </div>
                  <div class="form-group col-md-3">
                    <label for="inputEmail4"> Name of Authorized Signatory<span style="color:red">*</span></label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.authSignatory" id="nameOfAuthSign" class="form-control form-control-sm AlphabatesOnly" value="${fpoDtls.fpoVo.authSignatory}" >
                    </div>
                  </div>
                </div>
              </div>
            </div>
          
          <h6 class="headingbg  mb-2 mt-4">Financial Details</h6>
          
            <div class="row">
              <div class="col-md-12">
                <div class="row">
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Total Investment <span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.totalInvestment" id="totalInvestmentConverted" class="form-control form-control-sm" value="${fpoDtls.fpoVo.totalInvestment}" onchange="currencyConverter(this.value,'totalInvestmentConverted')">
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Debt<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.debt" id="debtConverted" class="form-control form-control-sm" value="${fpoDtls.fpoVo.debt}" onchange="currencyConverter(this.value,'debtConverted')">
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Promoter Holding(%)<span style="color:red">*</span></label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.promoterHolding" id="promoterHolding" class="form-control form-control-sm" value="${fpoDtls.fpoVo.promoterHolding}" >
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Total Loan Amount<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.totalLoanAmt" id="totalLoanAmountConverted" class="form-control form-control-sm" value="${fpoDtls.fpoVo.totalLoanAmt}" onchange="currencyConverter(this.value,'totalLoanAmountConverted')">
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Loan Repaid<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.loanRepaid" id="loanRepaidConverted" class="form-control form-control-sm" value="${fpoDtls.fpoVo.loanRepaid}" onchange="currencyConverter(this.value,'loanRepaidConverted')">
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">ROI(%)<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.roi" id="roi" class="form-control form-control-sm" value="${fpoDtls.fpoVo.roi}" >
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4"> Total Outstanding Amount<span style="color:red">*</span>
                    </label>
                    <div class="calanderholder">
                      <input type="text" name="fpoVo.totalOutstanAmt" id="totalOutstanAmountConverted" class="form-control form-control-sm" value="${fpoDtls.fpoVo.totalOutstanAmt}" onchange="currencyConverter(this.value,'totalOutstanAmountConverted')">
                    </div>
                  </div>
                </div>
              </div>
            </div>
          
          <h6 class="headingbg  mb-2 mt-4">Director’s Information</h6>
          
          <div class="table-responsive">
            <table
              class="datatable table table-striped table-bordered exportbtn mt-3 "
              id="apndTable">
              <thead>
                <tr>
                  <th>Sl No</th>
                  <th>Name of the Director</th>
                  <th style="width:150px">DIN</th>
                  <th>Designation</th>
                  <th>Appointment Date</th>
                  <th style="width: 100px;">Share(%)</th>
                  <th style="width: 100px;">Gender</th>
                  <th style="width:100px">Caste</th>
				  <c:if test="${fpoDtls.fpoVo.status eq 'DRAFT' || empty fpoDtls.fpoVo.fpoId}">
					<th>
					<button type="button" class="btn-primary addRow"title="Add">+</button>
					</th>
				  </c:if>
               </tr>
              </thead>
              <tbody>

                <c:if test="${empty fpoDtls.fpoMngmtVo}">
                <tr class="gradeA" id="managementRow">
                  <td>1</td>
                  <td>
					  <input type="text" class="form-control form-control-sm" onkeypress="return/[a-zA-Z]/i.test(event.key)" maxlength="40" id="name1" name="fpoMngmtVo[0].name" >
				  </td>
				  <td>
				      <input type="text" class="form-control form-control-sm" id="dinNo1" maxlength="10" name="fpoMngmtVo[0].din">
				  </td>
				  <td>
					<select class="form-control form-control-sm" id="designation1" name="fpoMngmtVo[0].designation.designationId" >
						<option value="0">--Select--</option>
					<c:forEach items="${designationList}" var="designation">
						<option value="${designation.designationId}" >${designation.designationName}</option>
					</c:forEach> 
					</select>
				 </td>
                  <td>
                  <div class="datepicker-box">
					  <input type="text" class="form-control form-control-sm datepicker_con" id="appDate1"  maxlength="10" name="fpoMngmtVo[0].appointmentDate" >
				  </div>
				  </td>
				  <td>
				     <input type="text" class="form-control form-control-sm NumbersOnly" id="share1" maxlength="10" name="fpoMngmtVo[0].share">
				  </td>
				  <td>
					<select class="form-control form-control-sm" id="gender1" name="fpoMngmtVo[0].gender.genderId" >
						<option value="0">--Select--</option>
					<c:forEach items="${genderList}" var="gl">
							<option value="${gl.genderId}" >${gl.genderNameEN}</option>
					</c:forEach> 
					</select>
				 </td>
                  <td>
					<select class="form-control form-control-sm" id="caste1" name="fpoMngmtVo[0].caste.casteId" >
						<option value="0">--Select--</option>
					<c:forEach items="${casteList}" var="cast">
							<option value="${cast.casteId}" >${cast.casteCode}</option>
					</c:forEach>
					</select>
				 </td>
                  <td>
                    <button type="button" class="btn-danger deleteRow"
                      title="Delete" style="padding: 0px 5px;"><i
                        class="bx bx-minus"></i></button>
                  </td>
                </tr>
                </c:if>
                <c:if test="${not empty fpoDtls.fpoMngmtVo}">
                <c:forEach items="${fpoDtls.fpoMngmtVo}" var="managementDetails" varStatus="vs">
                
                <tr class="gradeA" id="managementRow">
                  <td>${vs.count}</td>
                  <td>
                      <input type="hidden" class="form-control form-control-sm" maxlength="40" id="name" name="fpoMngmtVo[${vs.count-1}].directorId" value="${managementDetails.directorId}">
					  <input type="text" class="form-control form-control-sm" onkeypress="return/[a-zA-Z]/i.test(event.key)" maxlength="40" id="name" name="fpoMngmtVo[${vs.count-1}].name" value="${managementDetails.name}">
				  </td>
				  <td>
				      <input type="text" class="form-control form-control-sm" id="dinNo" maxlength="10" name="fpoMngmtVo[${vs.count-1}].din" value="${managementDetails.din}">
				  </td>
				  <td>
					<select class="form-control form-control-sm" id="designation1" name="fpoMngmtVo[${vs.count-1}].designation.designationId" >
						<option value="0">--Select--</option>
					<c:forEach items="${designationList}" var="designation">
						<option value="${designation.designationId}" ${managementDetails.designation.designationId eq designation.designationId ? 'selected' : ''}>${designation.designationName}</option>
					</c:forEach> 
					</select>
				 </td>
                  <td>
                  <div class="datepicker-box">
					  <input type="text" class="form-control form-control-sm datepicker_con" id="appDate1"  maxlength="10" name="fpoMngmtVo[${vs.count-1}].appointmentDate" value="${managementDetails.appointmentDate}">
				  </div>
				  </td>
				  <td>
				     <input type="text" class="form-control form-control-sm NumbersOnly" id="share1" maxlength="10" name="fpoMngmtVo[${vs.count-1}].share" value="${managementDetails.share}">
				  </td>
				  <td>
					<select class="form-control form-control-sm" id="gender1" name="fpoMngmtVo[${vs.count-1}].gender.genderId" >
						<option value="0">--Select--</option>
					<c:forEach items="${genderList}" var="gl">
							<option value="${gl.genderId}" ${managementDetails.gender.genderId eq gl.genderId ? 'selected' : ''}>${gl.genderNameEN}</option>
					</c:forEach> 
					</select>
				 </td>
                  <td>
					<select class="form-control form-control-sm" id="caste1" name="fpoMngmtVo[${vs.count-1}].caste.casteId" >
						<option value="0">--Select--</option>
					<c:forEach items="${casteList}" var="cast">
							<option value="${cast.casteId}" ${managementDetails.caste.casteId eq cast.casteId ? 'selected' : ''}>${cast.casteCode}</option>
					</c:forEach>
					</select>
				 </td>
				 <c:if test="${fpoDtls.fpoVo.status eq 'DRAFT' || empty fpoDtls.fpoVo.fpoId}">
                   <td>
                    <button type="button" class="btn-danger deleteRow"
                      title="Delete" style="padding: 0px 5px;"><i
                        class="bx bx-minus"></i></button>
                  </td> 
                 </c:if>
                </tr>
                
                </c:forEach>
                </c:if>
                
              </tbody>
            </table>
          </div>
          <h6 class="headingbg mb-2 mt-3">Demography Details</h6>
         
            <div class="row">
              <div class="col-md-12">
                <div class="row">

                  <div class="form-group col-md-2">
                     
                    <label for="inputEmail4">District<span style="color:red">*</span> </label>
                    <select class="form-select" name="fpoDemographyVO.distId.districtId">
                        <option value="0" >-Select-</option>
                      <c:forEach items="${distList}" var="dist">
                      <option value="${dist.districtId}" ${dist.districtId eq fpoDtls.fpoDemographyVO.distId.districtId ? 'selected' : ''}>${dist.districtNameEN}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Block<span style="color:red">*</span> </label>
                    <select class="form-select form-control form-control-sm selectpicker"  data-live-search="true" name="fpoDemographyVO.blockId.blockId" onchange="getGramPanchayat(this.value,'gp')">
                        <option value="0" >-Select-</option>
	                      <c:forEach items="${blockList}" var="block">
	                      <option value="${block.blockId}" ${block.blockId eq fpoDtls.fpoDemographyVO.blockId.blockId ? 'selected' : ''}>${block.blockNameEN}</option>
                      </c:forEach>
                    </select>
                  </div>
                 <%--  <div class="form-group col-md-2">
                    <label for="inputEmail4">GP<span style="color:red">*</span> </label>
                     <select class="form-select" name="fpoDemographyVO.gpId.gpId" id="gp" onchange="getVillage(this.value,'village')">
                         <option>-Select-</option>
                        <c:forEach items="${distList}" var="dist">
                      <option value="${dist.districtId}">${dist.districtNameEN}</option>
                      </c:forEach>
                    </select> 
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Village<span style="color:red">*</span> </label>
                     <select class="form-select" name="fpoDemographyVO.villageId.villageId" id="village">
                        <option>-Select-</option>
                     <c:forEach items="${distList}" var="dist">
                      <option value="${dist.districtId}">${dist.districtNameEN}</option>
                      </c:forEach>
                    </select> 
                  </div> --%>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Areas Covered (In Acre)<span style="color:red">*</span></label>
                    <div class="calanderholder">
                      <input type="text" class="form-control form-control-sm NumbersOnly" name="fpoDemographyVO.areasCovered" value="${fpoDtls.fpoDemographyVO.areasCovered}"/>
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">Number of Farms<span style="color:red">*</span></label>
                    <div class="calanderholder">
                      <input type="text" class="form-control form-control-sm NumbersOnly" name="fpoDemographyVO.numberOfFarms" value="${fpoDtls.fpoDemographyVO.numberOfFarms}"/>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          

          <h6 class="headingbg mb-2 mt-3">Documents Upload</h6>
          
            <div class="row">
              <div class="col-md-12">
                <div class="row" id="doc">

                  <div class="form-group col-md-2">
                    <label for="inputEmail4">
                      Incorporation Certificate<span style="color:red">*</span>
                    </label>
                    <div class="calanderholder">
                      <input type="file"
                        class="form-control form-control-sm" />
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">
                      PAN<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="file"
                        class="form-control form-control-sm" />
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">
                      UDYOG/MSME Certificate<span style="color:red">*</span>
                    </label>
                    <div class="calanderholder">
                      <input type="file"
                        class="form-control form-control-sm" />
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">
                      AOA<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="file"
                        class="form-control form-control-sm" />
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">
                      MOA<span style="color:red">*</span> </label>
                    <div class="calanderholder">
                      <input type="file"
                        class="form-control form-control-sm" />
                    </div>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputEmail4">
                      If any other document<span style="color:red">*</span>
                    </label>
                    <div class="calanderholder">
                      <button type="button"
                        class="btn-primary addinput form-control form-control-sm"
                        title="Add">Add</button>
                    </div>
                  </div>

                </div>
              </div>
            </div>
          

          <h6 class="headingbg mb-2 mt-3">Scheme Management</h6>
          <div class="table-responsive">
            <table
              class="datatable table table-striped table-bordered exportbtn"
              id="apndTable2">
              <thead>
                <tr>
                  <th>Sl No</th>
                  <th>Name of Scheme</th>
                  <th>Type of Scheme</th>
                  <th>Date of Participation</th>
                  <c:if test="${fpoDtls.fpoVo.status eq 'DRAFT' || empty fpoDtls.fpoVo.fpoId }">
                  <th><button type="button" class="btn-primary addRow2"
                      title="Add">+</button></th>
                  </c:if>
                </tr>
              </thead>
              <tbody class="tbody">

               <c:if test="${empty fpoDtls.fpoSchemeMapVO }">
                <tr class="gradeA">
                  <td>1</td>
                  <td>
                      <select class="form-select" name="fpoSchemeMapVO[0].schemee.schemeCode" id="schemeName1">
                        <option value="0">-Select-</option>
                      <c:forEach items="${schemeList}" var="scheme">
                      <option value="${scheme.schemeCode}">${scheme.schemeNameEn}</option>
                      </c:forEach>
                    </select>
                  </td>
                  <td><select class="form-select" id="schemeType1" name="fpoSchemeMapVO[0].type" >
                      <option value="0">-Select-</option>
                      <option value="CENTRE">Centre</option>
                      <option value="STATE">State Scheme </option>
                    </select>
                  </td>
                  <td><input type="text" class="datepicker_con" name="fpoSchemeMapVO[0].dateOfParticipation" id="dateOfParticipation1"/></td>
                  <td>
                    <button type="button" class="btn-danger deleteRow2"
                      title="Delete" style="padding: 0px 5px;"><i
                        class="bx bx-minus"></i></button>
                  </td>
                </tr>
                </c:if>
                <c:if test="${not empty fpoDtls.fpoSchemeMapVO }">
                <c:forEach items="${fpoDtls.fpoSchemeMapVO}" var="fpoScheme" varStatus="vs">
                  <tr class="gradeA">
                  <td>${vs.count}</td>
                  <td>
                  	  <input type="hidden" name="fpoSchemeMapVO[${vs.count-1}].schemeMngmntId" id="dateOfParticipation" value="${fpoScheme.schemeMngmntId}"/>
                      <select class="form-select" name="fpoSchemeMapVO[${vs.count-1}].schemee.schemeCode" id="schemeName" >
                        <option value="0">-Select-</option>
                      <c:forEach items="${schemeList}" var="scheme">
                      <option value="${scheme.schemeCode}" ${fpoScheme.schemee.schemeCode eq scheme.schemeCode ? 'selected' : ''}>${scheme.schemeNameEn}</option>
                      </c:forEach>
                    </select>
                  </td>
                  <td><select class="form-select" id="schemeType1" name="fpoSchemeMapVO[${vs.count-1}].type" >
                      <option value="0">-Select-</option>
                      <option value="CENTRE" ${fpoScheme.type eq 'CENTRE' ? 'selected' : ''}>Centre</option>
                      <option value="STATE" ${fpoScheme.type eq 'STATE' ? 'selected' : ''}>State Scheme </option>
                    </select>
                  </td>
                  <td>
                  <input type="text" class="datepicker_con" name="fpoSchemeMapVO[${vs.count-1}].dateOfParticipation" id="dateOfParticipation" value="${fpoScheme.dateOfParticipation}"/>
                  </td>
                  <c:if test="${fpoDtls.fpoVo.status eq 'DRAFT' || empty fpoDtls.fpoVo.fpoId}">
                   <td>
                    <button type="button" class="btn-danger deleteRow2"
                      title="Delete" style="padding: 0px 5px;"><i
                        class="bx bx-minus"></i></button>
                  </td> 
                  </c:if>
                </tr>
                </c:forEach>
                </c:if>
              </tbody>
            </table>
          </div>
          <c:if test="${empty fpoDtls.fpoVo.fpoId || fpoDtls.fpoVo.status eq 'DRAFT'}">
          <div class="col-md-12"><button type="button" class="btn-submit" onclick="submitButton('SAVE')">Submit</button></div>
          <div class="col-md-12"><button type="button" class="btn-submit" onclick="submitButton('DRAFT')">SaveAsDraft</button></div>
          </c:if>
          </form>
          </div>
          </div>
         <input type="hidden" id="rowLength2" value="1"/> 
         
         <input type="hidden" id="rowLength3" value="1"/> 

          <!-- Rahul Edit -->
<script>

$(document).ready(function(){
    debugger;
    if("${status}" != 'SAVE'){
    	
             var actList = [
		        <c:forEach items="${fpoDtls.fpoVo.activity}" var="item" varStatus="status">
		            '${item}'<c:if test="${!status.last}">,</c:if>
		        </c:forEach>
		    ];
	    if ('${fpoDtls.fpoVo.component}' != null) {
	    	getActivityListByCmpCodeByOne('${fpoDtls.fpoVo.component}' ,'activity', '${fpoDtls.fpoVo.activity}')
	    }
	    if (actList != null) {
	        debugger;
	        var selecteditems = [
		        <c:forEach items="${fpoDtls.fpoVo.subActivity}" var="item" varStatus="status">
		            '${item}'<c:if test="${!status.last}">,</c:if>
		        </c:forEach>
		    ];
	        getSubActivityListByActCode(actList,'subActivity', selecteditems)
	    }
	    
	    if('${fpoDtls.fpoDemographyVO.blockId.blockId}' != null){
	    	getGramPanchayat('${fpoDtls.fpoDemographyVO.blockId.blockId}','gp', '${fpoDtls.fpoDemographyVO.gpId.gpId}');
	    	getVillage('${fpoDtls.fpoDemographyVO.gpId.gpId}','village', '${fpoDtls.fpoDemographyVO.villageId.villageId}');
	    }
    }
});
        
        function submitButton(status){
       	 debugger;
       	 $("#statusCode").val(status);
       	 $("#submitForm").submit();
        }
        
          let count = 1;
            //Try to get tbody first with jquery children. works faster!
            var tbody = $('#apndTable').children('tbody');
            //Then if no tbody just select your table 
            var table = tbody.length ? tbody : $('#apndTable');


            $('.addRow').click(function(){
                //Add row
                debugger;
                            count++;
                            var count11 = $("#rowLength2").val() ;
                    		count11++;
                    		var countArrLength = count11-1;
                    		
                table.append('<tr class="gradeA" id="deleteRowDetailsInfra' + count11 + '">' +
                	    '<td>' + count11 + '</td>' +
                	    '<td>' +
                	    '<input type="text" class="form-control form-control-sm" onkeypress="return /[a-zA-Z]/i.test(event.key)" maxlength="40" id="name' + count11 + '" name="fpoMngmtVo[' + countArrLength + '].name">' +
                	    '</td>' +
                	    '<td>' +
                	    '<input type="text" class="form-control form-control-sm" id="dinNo' + count11 + '" maxlength="10" name="fpoMngmtVo[' + countArrLength + '].din">' +
                	    '</td>' +
                	    '<td>' +
                	    '<select class="form-control form-control-sm" id="designation' + count11 + '" name="fpoMngmtVo[' + countArrLength + '].designation.designationId">' +
                	    '<option value="">--Select--</option>' +
                	    '<c:forEach items="${designationList}" var="designation">' +
                	    '<option value="${designation.designationId}">${designation.designationName}</option>' +
                	    '</c:forEach>' +
                	    '</select>' +
                	    '</td>' +
                	    '<td>' +
                	    '<div class="datepicker-box">' +
                	    '<input type="text" class="form-control form-control-sm datepicker_con" id="appDate' + count11 + '" maxlength="10" name="fpoMngmtVo[' + countArrLength + '].appointmentDate">' +
                	    '</div>' +
                	    '</td>' +
                	    '<td>' +
                	    '<input type="text" class="form-control form-control-sm NumbersOnly" id="share' + count11 + '" maxlength="10" name="fpoMngmtVo[' + countArrLength + '].share">' +
                	    '</td>' +
                	    '<td>' +
                	    '<select class="form-control form-control-sm" id="gender' + count11 + '" name="fpoMngmtVo[' + countArrLength + '].gender.genderId">' +
                	    '<option value="">--Select--</option>' +
                	    '<c:forEach items="${genderList}" var="gl">' +
                	    '<option value="${gl.genderId}">${gl.genderNameEN}</option>' +
                	    '</c:forEach>' +
                	    '</select>' +
                	    '</td>' +
                	    '<td>' +
                	    '<select class="form-control form-control-sm" id="caste' + count11 + '" name="fpoMngmtVo[' + countArrLength + '].caste.casteId">' +
                	    '<option value="">--Select--</option>'+
					'<c:forEach items="${casteList}" var="cast">'+
							'<option value="${cast.casteId}" >${cast.casteCode}</option>'+
					'</c:forEach>' +
                	    '</select>' +
                	    '</td>' +
                	    '<td><button type="button" class="btn-danger deleteRow" title="Delete" style="padding: 0px 5px;"><i class="bx bx-minus"></i></button></td>' +
                	    '</tr>');
                
                $("#rowLength2").val(count11);
        		var currRowCount = $("#rowLength2").val()-1;

        		 $( document ).ready(function() {
                     $('.datepicker_con').datepick({ dateFormat: 'dd/mm/yyyy'});
                   });
                 $('.rangePicker').datepick({ 
                  rangeSelect: true, showTrigger: '#calImg'});
            });
             $("#apndTable").on('click', '.deleteRow', function() {
              $(this).closest("tr").remove();
              count--;          
            });

            
              $(".addinput").click(function(){
              $("#doc").append('<div class="col-lg-6 removeAllDoc"><div class="row"><div class="form-group col-md-5"><label for="inputEmail4">File Name<span style="color:red">*</span> </label><div class="calanderholder"><input type="text"class="form-control form-control-sm" /></div></div><div class="form-group col-md-7 position-relative"><label for="inputEmail4">Upload Attachment<span style="color:red">*</span> </label><div class="calanderholder"><input type="file"class="form-control form-control-sm" /></div><button class="btn btn-danger btn-sm removeDoc" type="button"><i class="bx bx-minus"></i></button></div></div></div>');
              });

              $("#doc").on('click', '.removeDoc', function() {
                $(this).closest(". ").remove();   
              });

             let sl = 1;
            //Try to get tbody first with jquery children. works faster!
            var tbody2 = $('#apndTable2').children('.tbody');
            //Then if no tbody just select your table 
            var table2 = tbody2.length ? tbody2 : $('#apndTable2');


            $('.addRow2').click(function(){
                //Add row
                             count++;
                            var count11 = $("#rowLength3").val() ;
                    		count11++;
                    		var countArrLength = count11-1;
                table2.append('<tr class="gradeA">'+
                        '<td>' + count11 + '</td>'+
                       ' <td>'+
                            '<select class="form-select" name="fpoSchemeMapVO[' + countArrLength + '].schemee.schemeCode" id="schemeName' + count11 + '">'+
                             ' <option>-Select-</option>'+
                            '<c:forEach items="${schemeList}" var="scheme">'+
                          '  <option value="${scheme.schemeCode}">${scheme.schemeNameEn}</option>'+
                           ' </c:forEach>'+
                         ' </select>'+
                       ' </td>'+
                        '<td><select class="form-select" id="schemeType' + count11 + '" name="fpoSchemeMapVO[' + countArrLength + '].type" >'+
                            '<option value="0">-Select-</option>'+
                           ' <option value="CENTRE">Centre</option>'+
                           ' <option value="STATE">State Scheme </option>'+
                         ' </select>'+
                       ' </td>'+
                       ' <td><input type="text" class="datepicker_con" name="fpoSchemeMapVO[' + countArrLength + '].dateOfParticipation" id="dateOfParticipation' + count11 + '"/></td>'+
                       ' <td>'+
                         ' <button type="button" class="btn-danger deleteRow2"'+
                           ' title="Delete" style="padding: 0px 5px;"><i'+
                            '  class="bx bx-minus"></i></button>'+
                        '</td>'+
                      '</tr>');
                
                $("#rowLength3").val(count11);
        		var currRowCount = $("#rowLength3").val()-1;
        		 $( document ).ready(function() {
                     $('.datepicker_con').datepick({ dateFormat: 'dd/mm/yyyy'});
                   });
                 $('.rangePicker').datepick({ 
                  rangeSelect: true, showTrigger: '#calImg'});
            });
             $("#apndTable2").on('click', '.deleteRow2', function() {
              $(this).closest("tr").remove();
              sl--;          
            });
           
         </script>