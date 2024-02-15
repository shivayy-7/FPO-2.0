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

<script src="${contextPath}/WEB-INF/commonSnippets/layoutWithLoginPage/baselayout.jsp"></script>


      <div class="row mt-3">
        <div class="col-md-6"> <h5 class="change-color">Add Farmer</h5></div>
        <%@ include file="/WEB-INF/views/message.jsp"%>
        <div class="col-md-6"> <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item"><a href="#">Farmer
                  Management</a></li>
              <li class="breadcrumb-item active" aria-current="page">Add Farmer</li>
            </ol>
          </nav></div>
          
          <hr style="margin-top: 5px;" />

   <div class="container">
    <div>
    <ul class="nav nav-tabs" id="myTab" role="tablist">
   
      <li role="presentation" class="nav-item">
          <a href="#step1" class="nav-link active" data-bs-toggle="tab" aria-controls="step1" role="tab" title="Step 1">  <i class='bx bx-cog'></i></a>
        
          <p>Basic Details</p>
      </li>
      <li role="presentation" class="nav-item" id="basicTab">
          <a href="#step2" class="nav-link" data-bs-toggle="tab" aria-controls="step2" role="tab" title="Step 2"><i class='bx bx-user'></i></a>
          <p>Occupation Details</p>
      </li>
      <li role="presentation" class="nav-item">
          <a href="#step3" class="nav-link" data-bs-toggle="tab" aria-controls="step3" role="tab" title="Step 3"><i class='bx bx-location-plus' ></i></a>
          <p>Address</p>
      </li>

      <li role="presentation" class="nav-item">
          <a href="#step4" class="nav-link" data-bs-toggle="tab" aria-controls="step4" role="tab" title="step 4"><i class='bx bxs-bank' ></i></a>
          <p>Bank Details</p>
      </li>
      
      <li role="presentation" class="nav-item">
        <a href="#step5" class="nav-link" data-bs-toggle="tab" aria-controls="step 5" role="tab" title="step 5">  <i class='bx bx-upload'></i></a>
        <p>Documents Upload</p>
    </li>

  </ul>
</div>
  

<!--   <form role="form"> -->
      <div class="tab-content">
          <div class="tab-pane active" role="tabpanel" id="step1">
         
            <h6 class="headingbg mb-2">Basic Details</h6>
            <form action="${contextPath}/farmer/manage-farmer" id="submitTab1" method="POST" >
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="farmerVO.farmerId" id="pcProfileId" value="${fpoDtls.fpoVo.fpoId}" />
						<input type="hidden" name="member.memberId" value="${memberDtls.member.memberId}"/>
						<input type="hidden" name="farmerVO.status" id="tab1status" />
<!-- 						<input type="hidden" name="tab" id="tab" value="BASIC"/> -->
              <div class="row">
                <div class="col-md-12">
                  <div class="row">
  
                    <div class="form-group col-md-2">
                      <label for="name" class="required">Name of Farmer </label>
                      <input type="text" class="form-control form-control-sm" id="name" name="farmerVO.name" value="${memberDtls.member.name}"/>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="aadharNo" class="required">Aadhar Card No. </label>
                      <input type="text" class="form-control form-control-sm NumbersOnly" id="aadharNo" name="farmerVO.aadharNo" onchange="aadharValidation(this.value,'aadharNo')" value="${memberDtls.member.aadharNo}"/>
                    </div>
                    <div class="form-group col-md-2">
                    <label for="gender" class="required">Gender </label>
                      <select class="form-select"  id="gender" name="farmerVO.gender.genderId">
                          <option value="0">--Select--</option>
						<c:forEach items="${genderList}" var="gl">
							<option value="${gl.genderId}" ${gl.genderId eq memberDtls.member.gender.genderId ? 'selected': ''}>${gl.genderNameEN}</option>
						</c:forEach> 
                      </select>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="caste" class="required" >Caste </label>
                      <select class="form-select" id="caste" name="farmerVO.caste.casteId">
                        	<option value="0">--Select--</option>
						<c:forEach items="${casteList}" var="cast">
							<option value="${cast.casteId}" ${cast.casteId eq memberDtls.member.caste.casteId ? 'selected': ''}>${cast.casteCode}</option>
						</c:forEach>
                      </select>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="dob" class="required">Date of Birth </label>
                      <div class="calanderholder">
                        <fmt:formatDate value="${memberDtls.member.farmer.dob}" pattern="dd/MM/yyyy" var="formattedDate" />
                        <input type="text" class="form-control form-control-sm datepicker_con" id="dob" name="farmerVO.dob" value="${formattedDate}" />
                      </div>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="familyMembers" class="required">No. of Family Members </label>
                      <div class="calanderholder">
                        <input type="number" class="form-control form-control-sm NumbersOnly" id="familyMembers" name="farmerVO.noOfFamilyMembers" value="${memberDtls.member.farmer.noOfFamilyMembers}"/>
                      </div>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="mobile" class="required">Mobile No. </label>
                      <div class="calanderholder">
                        <input type="number" class="form-control form-control-sm NumbersOnly" id="mobile" name="farmerVO.mobile" value="${memberDtls.member.farmer.mobile}"/>
                      </div>
                    </div>
                    <!-- <div class="form-group col-md-2">
                      <label for="inputEmail4">Farmer Unique ID <span style="color:red">*</span> </label>
                      <div class="calanderholder">
                        <input type="text"
                          class="form-control form-control-sm" />
                      </div>
                    </div> -->
                    <!-- <div class="form-group col-md-2">
                      <label for="inputEmail4">FIG Name <span style="color:red">*</span> </label>
                      <select class="form-select"
                        aria-label="Default select example">
                        <option>-Select-</option>
                        <option>Select-1</option>
                        <option>Select-2</option>
                        <option>Select-3</option>
                      </select>
                    </div> -->
                    <div class="form-group col-md-2">
                      <label for="frmType" class="required">Farmer Type </label>
                      <select class="form-select" id="frmType" name="farmerVO.frmType">
                        <option value="0">-Select-</option>
                        <option value="Small Farmer" ${memberDtls.member.farmer.frmType eq 'Small Farmer' ? 'selected' : ''}>Small Farmer</option>
                        <option value="Marginalised Farmer" ${memberDtls.member.farmer.frmType eq 'Marginalised Farmer' ? 'selected' : ''}>Marginalised Farmer</option>
                        <option value="Landless Farmer" ${memberDtls.member.farmer.frmType eq 'Landless Farmer' ? 'selected' : ''}>Landless Farmer</option>
                      </select>
                    </div>
                    <div class="form-group col-md-12 mt-3" style="text-align: center;">
                      <button type="button" class="btn btn-primary btn-sm next-step" id="n-btn1" >Next</button>
                      </div>
                  </div>
                </div>
              </div>
        
          </div>
          <div class="tab-pane" role="tabpanel" id="step2">
            <h6 class="headingbg mb-2 mt-4">Occupation Details</h6>
                         
                           <div class="row">
                             <div class="col-md-12">
                               <div class="row">
                                 <div class="form-group col-md-2">
				                    <label for="component" class="required" > Component </label>
				                    <select class="form-select"  id="component" onchange="getActivityListByCmpCode(this.value,'activity')">
				                                           <option value="0">--- Select ---</option>
				                                           <c:forEach items="${componentList}" var="cmp">   
				                                               <option value="${cmp.cmpCode}" ${memberDtls.component eq cmp.cmpCode ? 'selected':''}> ${cmp.cmpNameEn}</option>
				                                           </c:forEach>
				                    </select>
				                  </div>
				                  <div class="form-group col-md-2">
				                    <label for="activity" class="required" > Activity</label>
				                    <select class="form-control form-control-sm" id="activity"  onchange="getSubActivityListByActCode(this.value,'subActivity')" >
				                                          <option value="0">--- Select ---</option>
				                                          <c:forEach items="${clusterReportData.activityList}" var="activity">   
				                                               <option value="${activity.actCode}" > ${activity.actNameEn}</option>
				                                           </c:forEach>
				                                      </select>
				                  </div>
				                  <div class="form-group col-md-2">
				                    <label for="subActivity" class="required" > Sub-Activity </label>
				                    <!-- <select class="form-select form-select form-control form-control-sm selectpicker" multiple data-live-search="true" name="fpoVo.subActivity" id="subActivity"> -->
				                    <select class="form-select " name="farmerSubActMapVO.subActivity.subActCode" id="subActivity">
				                                         <option value="0">--- Select ---</option>
				                                         <c:forEach items="${clusterReportData.subActivityList}" var="subActivity">   
				                                               <option value="${subActivity.subActCode}" > ${subActivity.subActNameEn}</option>
				                                           </c:forEach>
				                                     </select>
				                  </div>
                                 
                                 <div class="form-group col-md-2">
                                   <label for="annualIncome" class="required" >Annual Income</label>
                                   <input type="number" class="form-control form-control-sm NumbersOnly" id="annualIncome" name="farmerVO.annualIncome" value="${memberDtls.member.farmer.annualIncome}"/>
                                 </div>
                                 
                                 <div class="form-group col-md-2">
                                   <label for="noOfDepd" class="required" > No. of Dependent Person </label>
                                   <input type="number" class="form-control form-control-sm NumbersOnly" id="noOfDepd" name="farmerVO.depenPerson" value="${memberDtls.member.farmer.depenPerson}"/>
                                 </div>
                                 
                                 <div class="form-group col-md-12 mt-3" style="text-align: center;">
                                  <button type="button" class="btn btn-dark prev-step btn-sm" id="p-btn1">Previous</button>
                                  <button type="button" class="btn btn-primary btn-sm next-step" id="n-btn2" >Next</button>
                                  </div>
                                  
                               </div>
                             </div> 
                           </div>
           
          </div>
          <div class="tab-pane" role="tabpanel" id="step3">
            <h6 class="headingbg mb-2 mt-4">Address</h6>
            
              <div class="row">
                <div class="col-md-12">
                  <div class="row">
                    <div class="form-group col-md-4">
                      <label for="address" class="required" > Address Line 1</label>
                      <div class="calanderholder">
                         <input type="text" class="form-control form-control-sm" id="address" name="farmerVO.address" value="${memberDtls.member.farmer.address}"/>
                      </div>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="district" class="required" >  District </label>
                      <div class="calanderholder">
	                      <select class="form-control form-control-sm" id="district" disabled>
					                           <option value="0">--- Select ---</option>
					                      <c:forEach items="${distList}" var="dist">   
					                            <option value="${dist.districtId}" ${dist.districtId eq district.districtId ? 'selected':''}> ${dist.districtNameEN}</option>
					                      </c:forEach>
					           </select>
                      </div>
                    </div>  
                    <div class="form-group col-md-2">
                      <label for="block" class="required" > Block </label>
                      <div class="calanderholder">
                            <select class="form-control form-control-sm" id="block" disabled>
					                           <option value="0">--- Select ---</option>
					                      <c:forEach items="${blockList}" var="block">   
					                            <option value="${block.blockId}" ${block.blockId eq blk ? 'selected':''}> ${block.blockNameEN}</option>
					                      </c:forEach>
					           </select>
                      </div>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="gp" class="required" > GP </label>
                      <div class="calanderholder">
<!--                         <input type="text" class="form-control form-control-sm" value="Mobarakpur" readonly="" /> -->
                           <select class="form-control form-control-sm" id="gp" onchange="getVillage(this.value, 'village')">
				                           <option value="0">--- Select ---</option>
				                      <c:forEach items="${gpList}" var="gp">   
				                            <option value="${gp.gpId}" > ${gp.gpNameEN}</option>
				                      </c:forEach>
				           </select>
                      </div>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="village" class="required" >Village </label>
                      <select class="form-select" id="village" name="farmerVO.villageId.villageId" >
                      <option value="0">--- Select ---</option>
                      </select>
                    </div>
                    
                    <div class="form-group col-md-12 mt-3" style="text-align: center;">
                      <button type="button" class="btn btn-dark prev-step btn-sm" id="p-btn2">Previous</button>
                      <button type="button" class="btn btn-primary btn-sm next-step" id="n-btn3">Next</button>
                      </div>
               
                  </div>
                </div>
              </div>
    
          </div>
          <div class="tab-pane" role="tabpanel" id="step4">
            <h6 class="headingbg  mb-2 mt-4">Bank Details</h6>
              <div class="row">
                <div class="col-md-12">
                  <div class="row">
                    <div class="form-group col-md-2">
                      <label for="bankName" class="required" > Bank Name </label>
                      <select class="form-select" id="bankName" name="farmerVO.bankBranchId.bankName" onchange="getBranchNameByBankName(this.value,'branchName','')">
                        <option value="0">--Select--</option>
						<c:forEach items="${bankList}" var="bank">
							<option value="${bank}" ${memberDtls.farmerVO.bankBranchId.bankName eq bank ? 'selected' : ''}>${bank}</option>
						</c:forEach> 
                      </select>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="branchName" class="required" > Branch Name </label>
                      <div class="calanderholder" >
                        <select class="form-select" id="branchName" name="farmerVO.bankBranchId.bankBranchId" onchange="getIFSCByBranchId(this.value,'ifsc','')">
	                        <option value="0">--Select--</option>
<%-- 							<c:forEach items="${bankList}" var="bank"> --%>
<%-- 								<option value="${bank.bankBranchId}" >${bank.bankName}</option> --%>
<%-- 							</c:forEach>  --%>
                      </select>
                      </div>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="accountNo" class="required" > Account Number</label>
                      <div class="calanderholder">
                        <input type="number" class="form-control form-control-sm" id="accountNo" name="farmerVO.accountNo" value="${memberDtls.member.farmer.accountNo}"/>
                      </div>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="ifsc" class="required" > IFSC Code</label>
                      <div class="calanderholder">
<!--                         <input type="text" class="form-control form-control-sm" id="ifsc" /> -->
                        <select class="form-select" id="ifsc" >
	                        <option value="0">--Select--</option>
                      </select>
                      </div>
                    </div>
                    <div class="form-group col-md-2">
                      <label for="accType" class="required" > Account Type </label>
                      <select class="form-select" id="accType" name="farmerVO.accType">
                        <option value="0">-Select-</option>
                        <option value="Savings Account" ${memberDtls.member.farmer.accType eq 'Savings Account'? 'selected':''}>Savings Account</option>
                        <option value="Current Account" ${memberDtls.member.farmer.accType eq 'Current Account'? 'selected':''}>Current Account</option>
                        <option value="Salary Account" ${memberDtls.member.farmer.accType eq 'Salary Account'? 'selected':''}>Salary Account</option>
                      </select>
                    </div>
                    
                  </div>
                </div>
              </div>
            <div class="form-group col-md-12 mt-3" style="text-align: center;">
              <button type="button" class="btn btn-dark prev-step btn-sm" id="p-btn3">Previous</button>
              <button type="button" class="btn btn-primary btn-sm next-step" id="n-btn4">Next</button>
              </div>
          </div>
          <div class="tab-pane" role="tabpanel" id="step5">
            <h6 class="headingbg mb-2 mt-3">Documents Upload</h6>
                         
                           <div class="row">
                             <!-- <div class="col-md-12">
                               <div class="row" id="doc">
                                 <div class="form-group col-md-2">
                                   <label for="inputEmail4"> PAN<span style="color:red">*</span> </label>
                                   <div class="calanderholder">
                                     <input type="file" class="form-control form-control-sm" />
                                   </div>
                                 </div>
                                 <div class="form-group col-md-2">
                                   <label for="inputEmail4"> Aadhar/Voter ID Card<span style="color:red">*</span> </label>
                                   <div class="calanderholder">
                                     <input type="file" class="form-control form-control-sm" />
                                   </div>
                                 </div>
                                 <div class="form-group col-md-2">
                                   <label for="inputEmail4"> Bank Passbook<span style="color:red">*</span> </label>
                                   <div class="calanderholder">
                                     <input type="file" class="form-control form-control-sm" />
                                   </div>
                                 </div> -->
                                 <div class="col-md-2">
                                   <div class="form-group">
                                     <label for="isHealthInsurance" class="required" >Health Insurance</label>
                                     <select class="form-select" id="isHealthInsurance" name="farmerVO.isHealthInsurance">
                                       <option value="0">Select</option>
                                       <option value="true" >Yes</option>
                                       <option value="false" >No</option>
                                     </select>
                                   </div>
                                 </div>
<!--                                  <div class="yes formbox form-group col-md-2">
                                   <label for="inputEmail4"> Upload Document<span style="color:red">*</span> </label>
                                   <div class="calanderholder">
                                     <input type="file" class="form-control form-control-sm" />
                                   </div>
                                 </div> -->
               
                                 <div class="col-md-2">
                                   <div class="form-group">
                                     <label for="isJanshreeBimaYojana" class="required" >Janshree Bima Yojana</label>
                                     <select class="form-select" id="isJanshreeBimaYojana" name="farmerVO.isJanshreeBimaYojana" >
                                       <option value="0">Select</option>
                                       <option value="true" <%-- ${memberDtls.member.farmer.isJanshreeBimaYojana ? 'selected':''} --%>>Yes</option>
                                       <option value="false" <%-- ${memberDtls.member.farmer.isJanshreeBimaYojana  ? 'selected':''} --%>>No</option>
                                     </select>
                                   </div>
                                 </div>
<!--                                  <div class="yes2 formbox2 form-group col-md-2">
                                   <label for="inputEmail4"> Upload Document<span style="color:red">*</span>
                                   </label>
                                   <div class="calanderholder">
                                     <input type="file" class="form-control form-control-sm" />
                                   </div>
                                 </div> -->
               
                                 <div class="col-md-2">
								    <div class="form-group">
								        <label for="isPensionCoverage" class="required">Pension Coverage</label>
								        <select class="form-select" id="isPensionCoverage" name="farmerVO.isPensionCoverage">
								            <option value="0">Select</option>
								            <option value="true" <%-- ${memberDtls.member.farmer.isPensionCoverage == true ? "selected" : ""} --%> >Yes</option>
								            <option value="false" <%-- ${memberDtls.member.farmer.isPensionCoverage == false ? "selected" : ""} --%>>No</option>
								        </select>
								    </div>
								</div>

<!--                                  <div class="yes3 formbox3 form-group col-md-2">
                                   <label for="inputEmail4"> Upload Document<span style="color:red">*</span></label>
                                   <div class="calanderholder">
                                     <input type="file" class="form-control form-control-sm" />
                                   </div>
                                 </div> -->
               
                                 <div class="col-md-4">
                                   <div class="form-group">
                                     <label for="eduBenifits" class="required" >Education Benefits Availed for Children under JBCRY</label>
                                     <select class="form-select" id="eduBenifits" name="farmerVO.eduBenifits">
                                       <option value="0">Select</option>
                                       <option value="true" ${memberDtls.member.farmer.eduBenifits ? 'selected':''}>Yes</option>
                                       <option value="false" ${memberDtls.member.farmer.eduBenifits  ? 'selected':''}>No</option>
                                     </select>
                                   </div>
                                 </div>
<!--                                  <div class="yes4 formbox4 form-group col-md-2">
                                   <label for="inputEmail4"> Upload Document<span style="color:red">*</span></label>
                                   <div class="calanderholder">
                                     <input type="file" class="form-control form-control-sm" />
                                   </div>
                                 </div> -->
                                 <div class="form-group col-md-12 mt-3" style="text-align: center;">
                                
                                  <button type="button" class="btn btn-primary btn-sm next-step" id="" onclick="forSubmit('SAVE')">Submit</button>
                                  </div>
               
                               </div>
                             </div>
                           </div>
                     </div>
                         </form>
                         
          <div class="clearfix"></div>
      </div>
</div> 





<script>

$(document).ready(function(){
    
    if("${memberDtls.activity}" != ''){
    	
             var actList = [
		        <c:forEach items="${memberDtls.activity}" var="item" varStatus="status">
		            '${item}'<c:if test="${!status.last}">,</c:if>
		        </c:forEach>
		    ];
	    if ('${memberDtls.component}' != null) {
	    	getActivityListByCmpCodeByOne('${memberDtls.component}' ,'activity', '${memberDtls.activity}')
	    }
	    if (actList != null) {
	        debugger;
	        var selecteditems = [
		        <c:forEach items="${memberDtls.subActivity}" var="item" varStatus="status">
		            '${item}'<c:if test="${!status.last}">,</c:if>
		        </c:forEach>
		    ];
	        getSubActivityListByActCode(actList,'subActivity', selecteditems)
	    }
	    
	    if('${blk}' != null){
	    	getGramPanchayat('${blk}','gp', '${memberDtls.gp}');
	    	getVillage('${memberDtls.gp}','village', '${memberDtls.farmerVO.villageId.villageId}');
	    }
    }
    
    if('${memberDtls.farmerVO.bankBranchId}' != ''){
    	debugger;
    getBranchNameByBankName('${memberDtls.farmerVO.bankBranchId.bankName}','branchName','${memberDtls.farmerVO.bankBranchId.branchName}')
    getIFSCByBranchId('${memberDtls.farmerVO.bankBranchId.bankBranchId}','ifsc','${memberDtls.farmerVO.bankBranchId.ifsc}')
    }
    
});


          let count = 1;
            //Try to get tbody first with jquery children. works faster!
            var tbody = $('#apndTable').children('tbody');
            //Then if no tbody just select your table 
            var table = tbody.length ? tbody : $('#apndTable');


            $('.addRow').click(function(){
                //Add row
                            count++;
                table.append(`<tr class="gradeA"><td>${count}</td> <td><input type="text" class="form-control form-control-sm" /></td> <td><input type="number"class="form-control form-control-sm" /></td><td><select class="form-select" aria-label="Default select example"><option>-Select-</option><option>Director</option><option>Managing Director</option><option>Board of Director</option><option>Member</option></select></td><td><input type="date"class="form-control form-control-sm" /></td> <td><input type="number"class="form-control form-control-sm" /></td> <td><select class="form-select"aria-label="Default select example"><option>-Select-</option><option>Male</option><option>Female</option><option>Other</option></select></td><td><select class="form-select"aria-label="Default select example"><option>-Select-</option><option>ST</option><option>SC</option><option>General</option><option>OBC</option><option>Other</option></select></td><td><button type="button" class="btn-danger deleteRow" title="Delete" style="padding: 0px 5px;"><i class="bx bx-minus"></i></button></td></tr>`);
            });
             $("#apndTable").on('click', '.deleteRow', function() {
              $(this).closest("tr").remove();
              count--;          
            });

            
              $(".addinput").click(function(){
              $("#doc").append('<div class="col-lg-6 removeAllDoc"><div class="row"><div class="form-group col-md-5"><label for="inputEmail4">File Name<span style="color:red">*</span> </label><div class="calanderholder"><input type="text"class="form-control form-control-sm" /></div></div><div class="form-group col-md-7 position-relative"><label for="inputEmail4">Upload Attachment<span style="color:red">*</span> </label><div class="calanderholder"><input type="file"class="form-control form-control-sm" /></div><button class="btn btn-danger btn-sm removeDoc" type="button"><i class="bx bx-minus"></i></button></div></div></div>');
              });

              $("#doc").on('click', '.removeDoc', function() {
                $(this).closest(".removeAllDoc").remove();   
              });

             let sl = 1;
            //Try to get tbody first with jquery children. works faster!
            var tbody2 = $('#apndTable2').children('.tbody');
            //Then if no tbody just select your table 
            var table2 = tbody2.length ? tbody2 : $('#apndTable2');


            $('.addRow2').click(function(){
                //Add row
                            sl++;
                table2.append(`<tr class="gradeA">
                      <td>${sl}</td>
                      <td><select class="form-select"
                        aria-label="Default select example">
                        <option>-Select-</option>
                        <option>Director</option>
                        <option>Managing Director</option>
                        <option>Board of Director</option>
                        <option>Member</option>
                      </select></td>
                      <td><select class="form-select"
                        aria-label="Default select example">
                        <option>-Select-</option>
                        <option>Male</option>
                        <option>Female</option>
                        <option>Other</option>
                      </select></td>
                      <td><input type="text" class="form-control form-control-sm datepicker_con" /></td>
                      <td>
                        <button type="button" class="btn-danger deleteRow2" title="Delete" style="padding: 0px 5px;"><i class="bx bx-minus"></i></button>
                      </td>
                    </tr>`);
            });
             $("#apndTable2").on('click', '.deleteRow2', function() {
              $(this).closest("tr").remove();
              sl--;          
            });
           
         </script>
         <script type="">
           $(document).ready(function(){
              $("#mrform-slect").change(function(){
                  $(this).find("option:selected").each(function(){
                      if($(this).attr("value")=="yes"){
                          $(".formbox").not(".yes").hide();
                          $(".yes").show();
                      }
                      else if($(this).attr("value")=="no"){
                          $(".formbox").not(".no").hide();
                          $(".no").show();
                      }            
                      else{
                          $(".formbox").hide();
                      }
                  });
              }).change();
          });
           $(document).ready(function(){
              $("#mrform-slect2").change(function(){
                  $(this).find("option:selected").each(function(){
                      if($(this).attr("value")=="yes2"){
                          $(".formbox2").not(".yes2").hide();
                          $(".yes2").show();
                      }
                      else if($(this).attr("value")=="no2"){
                          $(".formbox2").not(".no2").hide();
                          $(".no2").show();
                      }            
                      else{
                          $(".formbox2").hide();
                      }
                  });
              }).change();
          });
           $(document).ready(function(){
              $("#mrform-slect3").change(function(){
                  $(this).find("option:selected").each(function(){
                      if($(this).attr("value")=="yes3"){
                          $(".formbox3").not(".yes3").hide();
                          $(".yes3").show();
                      }
                      else if($(this).attr("value")=="no3"){
                          $(".formbox3").not(".no3").hide();
                          $(".no3").show();
                      }            
                      else{
                          $(".formbox3").hide();
                      }
                  });
              }).change();
          });
           $(document).ready(function(){
              $("#mrform-slect4").change(function(){
                  $(this).find("option:selected").each(function(){
                      if($(this).attr("value")=="yes4"){
                          $(".formbox4").not(".yes4").hide();
                          $(".yes4").show();
                      }
                      else if($(this).attr("value")=="no4"){
                          $(".formbox4").not(".no4").hide();
                          $(".no4").show();
                      }            
                      else{
                          $(".formbox4").hide();
                      }
                  });
              }).change();
          });
        </script>


<script>
  $('a[data-toggle="tab"]').on("shown.bs.tab", function (e) {
  var href = $(e.target).attr("href");
  var $curr = $(".process-model  a[href='" + href + "']").parent();

  $(".process-model li").removeClass();

  $curr.addClass("active");
  $curr.prevAll().addClass("visited");
});
</script>

<script>
  $(document).ready(function () {
	//Enable Tooltips
	var tooltipTriggerList = [].slice.call(
		document.querySelectorAll('[data-bs-toggle="tooltip"]')
	);
	var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl);
	});

	//Advance Tabs
	$(".next").click(function () {
		const nextTabLinkEl = $(".nav-tabs .active")
			.closest("li")
			.next("li")
			.find("a")[0];
		const nextTab = new bootstrap.Tab(nextTabLinkEl);
		nextTab.show();
	});

	$(".previous").click(function () {
		const prevTabLinkEl = $(".nav-tabs .active")
			.closest("li")
			.prev("li")
			.find("a")[0];
		const prevTab = new bootstrap.Tab(prevTabLinkEl);
		prevTab.show();
	});
});

</script>
<script>
  $('.btnNext').click(function(){
  $('.nav-tabs > li a.active').closest('li').next('li').find('a').trigger('click');
});

  $('.btnPrevious').click(function(){
  $('.nav-tabs > li a.active').closest('li').prev('li').find('a').trigger('click');
});
</script>


<script>
   $("#n-btn1").click(function () {
    $('#myTab li:nth-child(2) a').tab('show');
  }); 
  $("#n-btn2").click(function () {
    $('#myTab li:nth-child(3) a').tab('show');
  });
  $("#n-btn3").click(function () {
    $('#myTab li:nth-child(4) a').tab('show');
  });
  $("#n-btn4").click(function () {
    $('#myTab li:nth-child(5) a').tab('show');
  });


  $("#p-btn1").click(function () {
    $('#myTab li:nth-child(1) a').tab('show');
  });
  $("#p-btn2").click(function () {
    $('#myTab li:nth-child(2) a').tab('show');
  });
  $("#p-btn3").click(function () {
    $('#myTab li:nth-child(3) a').tab('show');
  });
  $("#p-btn4").click(function () {
    $('#myTab li:nth-child(4) a').tab('show');
  });
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
  
function forSubmit(status){
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
	            return;
	        } 
	    } else {
	        console.error("Input element not found for label with ID: " + labelId);
	    }
	});
	
	if (falseFields > 0) {
	    console.log("There are empty or undefined fields.");
	} else {
		$("#tab1status").val(status);
		$("#submitTab1").submit();
	}
	
}
</script>
