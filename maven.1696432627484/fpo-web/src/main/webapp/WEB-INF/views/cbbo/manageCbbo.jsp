<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/article275/commonMaster.js"></script>
<script src="${contextPath}/assets/appJs/ajaxJs/commonAjax.js"></script>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="tlength" value="${fn:length(farmerCbboMngmtVO)}" />

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
                    <h2 class="text-blue pb-2 fw-bold">CBBO Management</h2>
                </div>
                <div class="ml-md-auto mb-4 py-2 py-md-0">
                    <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a>
                    <a href="${contextPath}" class="btn btn-sm btn-border btn-blue btn-round mr-2">/CBBO Management</a>
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
                        <h4 class="card-title">CBBO Management:</h4>
                    </div>
                    <div class="card-body" style="">
                        <div class="col-md-12">
                            <form action="${contextPath}/cbbo/manage" id="submitForm" method="POST" > <!-- enctype="multipart/form-data" -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="farmerCbboVO.id" id="id" value="${cbbo.farmerCbboVO.id}" />
                                <input type="hidden" name="farmerCbboVO.cbboCode" id="cbboCode" value="${cbbo.farmerCbboVO.cbboCode}" />
                                <input type="hidden" name="farmerCbboVO.status" id="statusCode"/>
                                <div class="col-md-12">
                                    <h4 class="card-title">Basic Details:</h4>
                                </div>
                                <div class="col-md-12">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="col-md-12 smallInput required">Name of the CBBO :</label>
                                            <div class="col-md-12">
                                                <input type="text" name="farmerCbboVO.cbboName" id="cbboName" class="form-control form-control-sm AlphabetsOnly" value="${cbbo.farmerCbboVO.cbboName}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="col-md-12 smallInput required" >CBBO Category :</label>
                                            <div class="col-md-12">
                                                <select class="form-control form-control-sm" id="cbboCategory" name="farmerCbboVO.cbboCategory">
                                                    <option value="0">--Select--</option>
                                                    <option value="REGISTER" ${cbbo.farmerCbboVO.cbboCategory eq 'REGISTER'?'selected':''}>Register User</option>
                                                    <option value="BUSINESS" ${cbbo.farmerCbboVO.cbboCategory eq 'BUSINESS'?'selected':''}>Business User</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="col-md-12 smallInput required" for="emailId">PIN :</label>
                                            <div class="col-md-12">
                                                <input type="text" name="farmerCbboVO.pin" id="pin" class="form-control form-control-sm" value="${cbbo.farmerCbboVO.pin}" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="col-md-12 smallInput required" >Address :</label>
                                            <div class="col-md-12">
                                                <textarea name="farmerCbboVO.address" id ="address">${cbbo.farmerCbboVO.address}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="col-md-12 smallInput required" for="schemeName">Landmark :</label>
                                            <div class="col-md-12">
                                                <textarea name="farmerCbboVO.landmark" id="landmark">${cbbo.farmerCbboVO.landmark}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="col-md-12 smallInput required" for="emailId">Scheme :</label>
                                                <div class="col-md-12">
                                                    <select class="form-control form-control-sm" id="scheme" name="farmerCbboVO.scheme.schemeId">
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${schemeList}" var="sch">
                                                            <option value="${sch.schemeId}" ${cbbo.farmerCbboVO.scheme.schemeId eq sch.schemeId ?'selected':''}>${sch.schemeNameEn}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>   
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="" class="col-md-12 required">State:</label>
                                            <div class="col-md-12">
                                                <select class="form-control form-control-sm"  id="stateId" onchange="getDistrict(this.value,'distId')" >
                                                    <option value="0">--Select--</option>
                                                    <c:forEach items="${stateList}" var="state">
                                                        <option value="${state.stateId}" ${state.stateId eq cbbo.stateId ? 'selected':''}> ${state.stateNameEN}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
    
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="" class="col-md-12 required">District :</label>
                                            <div class="col-md-12">
                                                <select class="form-control form-control-sm"  id="distId" onchange="getBlock(this.value,'blockId')" >
                                                    <option value="0">--Select--</option>
                                                    <option value="${cbbo.distId}" ${empty cbbo.distId  ? 'selected':''}> ${benVO.district.districtNameEN}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
    
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="" class="col-md-12 required">Block :</label>
                                            <div class="col-md-12">
                                                <select class="form-control form-control-sm"  id="blockId" name="blockId" multiple="multiple">
                                                    <option value="0">--Select--</option>
                                                    <c:forEach items="${blockMapList}" var="blk">
                                                    <option value="${blk.blockId}" ${blk.blockId eq cbbo.farmerCbboBlkMapVO.block.blockId ? 'selected' : '' }> ${blk.blockNameEN}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
    
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="" class="col-md-12 required">Bank :</label>
                                                <div class="col-md-12">
                                                    <select class="form-control form-control-sm"  id="newBankName" onchange="getBankBranchByBankId(this.value,'newBranchName')">
                                                        <option value="0">--Select--</option>
                                                        <c:forEach items="${bankList}" var="bank">
                                                            <option value="${bank}" ${bank eq cbbo.bankName? 'selected' : '' }> ${bank}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                         <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="" class="col-md-12 required">Branch :</label>
                                                <div class="col-md-12">
                                                    <select class="form-control form-control-sm"  id="newBranchName" onchange="getIFSCByBranchId(this.value,'newIFSC')">
                                                        <option value="0">--Select--</option>
                                                       <c:forEach items="${benVO.branchList}" var="branchL">
                                                            <option value="${branchL}" ${branchL eq cbbo.branchName ? 'selected' : '' }> ${branchL}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
    
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="" class="col-md-12 required">IFSC</label>
                                                <div class="col-md-12">
                                                    <select class="form-control form-control-sm" id="newIFSC" name="farmerCbboVO.bankBranch.bankBranchId" >
                                                        <option value="0">--Select--</option>
                                                       <c:forEach items="${benVO.ifscList}" var="ifscCode">
                                                            <option value="${benVO.benBank.bankBranchId}" ${benVO.benBank.bankBranchId eq cbbo.ifsc ? 'selected' : '' }> ${ifscCode}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div> 
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="" class="col-md-12 required">Bank Account Number :</label>
                                                <div class="col-md-12">
                                                    <input type="text" class="form-control form-control-sm NumbersOnly" autocomplete="off" id ="bankAccId" value="${cbbo.farmerCbboVO.accountNo}"
                                                        name="farmerCbboVO.accountNo" onchange = "getValidCheckData(this.value,'BNF_ACC')" maxlength="16" >
                                                </div>
                                            </div>
                                        </div>

                                </div>
                                

                                 <div class="col-md-12">
                                    <h4 class="card-title">Management Details:</h4>
                                </div>
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
<!--                                             <th class="mandotory">Is Trainer</th> -->
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody id="managementRow">
                                        <c:if test="${empty cbbo.farmerCbboMngmtVO }">
                                            <tr>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm" onkeypress="return/[a-zA-Z]/i.test(event.key)" maxlength="40" id="name1" name="farmerCbboMngmtVO[0].cbboMngmtName" >
                                                </td>
                                                <td>
                                                    <select class="form-control form-control-sm" id="gender1" name="farmerCbboMngmtVO[0].gender.genderId" >
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${genderList}" var="gl">
                                                            <option value="${gl.genderId}" >${gl.genderNameEN}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm NumbersOnly" maxlength="3" id="age1" name="farmerCbboMngmtVO[0].age" >
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm"  maxlength="80" id="education1" name="farmerCbboMngmtVO[0].education" >
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm NumbersOnly" id="aadharNo1"  maxlength="12" name="farmerCbboMngmtVO[0].aadharNo" >
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm" id="dinNo1"  maxlength="10" name="farmerCbboMngmtVO[0].dinNo" >
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm AlphaNumericOnly" id="pinNo1"  maxlength="10" name="farmerCbboMngmtVO[0].pinNo" >
                                                </td>
                                                <td>
                                                    <select class="form-control form-control-sm" id="designation1" name="farmerCbboMngmtVO[0].designation.designationId" >
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${designationList}" var="designation">
                                                            <option value="${designation.designationId}">${designation.designationName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
<!--                                                 <td> -->
<!--                                                  <div id="isTrainer1"><label for="yes_no_radio"></label><p><input type="radio" name="farmerCbboMngmtVO[0].isTrainer" value="true">Yes</p><p><input type="radio" name="farmerCbboMngmtVO[0].isTrainer" value="false" checked >No</p></div>  -->
<!--                                                 </td> -->

                                                <td class="text-center">
                                                    <button type="button" class="btn btn-xs btn-primary" onclick="addInfrastructureDetails()"><i class="fa fa-plus"></i></button>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty cbbo.farmerCbboMngmtVO}">
                                            <c:forEach items="${cbbo.farmerCbboMngmtVO}" var="farmerCbboMngmtVO" varStatus="vs">
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
<!--                                                     <td> -->
<%-- 	                                                 <div id="isTrainer${vs.count}"> --%>
<!-- 														    <label for="yes_no_radio"></label> -->
<!-- 														    <p> -->
<%-- 														        <input type="radio" id="isTrainer${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].isTrainer" value="true" ${farmerCbboMngmtVO.isTrainer eq true ? 'checked' : ''}>Yes --%>
<!-- 														    </p> -->
<!-- 														    <p> -->
<%-- 														        <input type="radio" id="isTrainer${vs.count}" name="farmerCbboMngmtVO[${vs.count-1}].isTrainer" value="false" ${farmerCbboMngmtVO.isTrainer eq false ? 'checked' : ''}>No --%>
<!-- 														    </p> -->
<!-- 														</div> -->
<!-- 	                                                </td> -->

                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${vs.count eq 1}">
                                                                <button type="button" class="btn btn-xs btn-primary" onclick="addInfrastructureDetails()"><i class="fa fa-plus"></i></button>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:choose>
                                                                    <c:when test="${vs.count eq  tlength}">
                                                                        <button type="button" id="btnMinusInfrat${vs.count}" class="btn btn-danger btn-xs" onclick="deleteInfraDetails(${farmerCbboMngmtVO.id})"><i class="fa fa-minus"></i></button>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <button disabled="disabled" type="button" id="btnMinusInfra${vs.count}" class="btn btn-danger btn-xs" onclick="deletethisbtnMinusInfra(${vs.count})"><i class="fa fa-minus"></i></button>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <!-- <button type="button" class="btn btn-sm btn-primary" onclick="addInfrastructureDetails()"><i class="fa fa-plus"></i></button> -->
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        </tbody>
                                    </table>
                                </div> 
                                
                                
                                <div class="col-md-12 text-center mt-3">
                                    <c:if test="${cbbo.farmerCbboVO.status ne 'REGISTERED' }">
                                        <input type="button" class="btn btn-primary btn-sm" onclick="submitButton('DRAFT');" value="Save As Draft"/>
                                        <input type="button" class="btn btn-success btn-sm" onclick="submitButton('REGISTERED');" value="Submit"/>
                                    </c:if>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="rowLength2" value="1"/>
   
    
<script>
 
$(document).ready(function(){
    debugger;
    if("${status}" != 'SAVE'){
    	
	    if ('${cbbo.stateId}' != null) {
	        getDistrict('${cbbo.stateId}', 'distId', '${cbbo.stateId}');
	    }
	    if ('${cbbo.distId}' != null) {
	        debugger;
	        getBlock('${cbbo.distId}', 'blockId', '${cbbo.farmerCbboBlkMapVO.block.blockId}');
	    }
	    if ('${cbbo.bankName}' != null) {
	        debugger;
	        getBankBranchByBankId('${cbbo.bankName}', 'newBranchName', '${cbbo.branchName}')
	    }
	    if ('${cbbo.ifsc}' != null) {
	        debugger;
	        getIFSCByBranchId('${cbbo.ifsc}','newIFSC', '${cbbo.ifsc}')
	    }
    }
});



    function checkFileTypeExtAndSize(that ,ftype) {

        var fileInput = $("#"+ that.id)[0];
        var file = fileInput.files[0];
        if (file) {
            // Check file size (in bytes)
            var maxSize = 200*1024; // 1MB in bytes
            if (file.size > maxSize) {
                bootbox.alert("File size exceeds 200Kb limit..");
                $("#"+that.id).val('');
                return false;
            }
        }
        // Get the file upload control file extension
        var extn = $('#' + that.id).val().split('.').pop().toLowerCase();
        if (extn != '') {
            //debugger;
            // Create array with the files extensions to upload
            var fileListToUpload;
            if (parseInt(ftype) == 1){
                fileListToUpload = new Array('pdf', 'gif', 'jpg', 'jpeg', 'png');
            }
            else if (parseInt(ftype) == 2){
                fileListToUpload = new Array('png', 'jpg', 'jpeg');
            }
            else{
                fileListToUpload = new Array('pdf');
            }
            //Check the file extension is in the array.
            var isValidFile = $.inArray(extn, fileListToUpload);
            // isValidFile gets the value -1 if the file extension is not in the list.
            if (isValidFile == -1) {
                if (parseInt(ftype) == 1){
                    bootbox.alert("Please select a valid file of type pdf, gif, jpg, jpeg, png.");
                }
                else if (parseInt(ftype) == 2){
                    bootbox.alert("Please select a valid file of type png, jpg or jpeg.");
                }
                else{
                    bootbox.alert("Please select a valid pdf file only.");
                }
                $('#' + that.id).replaceWith($('#' + that.id).val('').clone(true));
            }
            /*DO NOT UNCOMMENT THIS CODE UNTIL UNLESS NOT GIVE PERMISSION FROM RAJESH SIR
            else {
                // Restrict the file size to 500 KB.
                if ($('#' + cntr).get(0).files[0].size > (1024 * 500)) {
                    bootbox.alert("File size should not exceed 500 KB.hh");
                    $('#' + cntr).replaceWith($('#' + cntr).val('').clone(true));
                    $("#uph").attr('src','resources/admin_pannel/images/profile_pic.png');
                    $("#uphOther").attr('src','resources/admin_pannel/images/profile_pic.png');
                }
                if ($('#' + cntr).get(0).files[0].name.length > 100) {
                    bootbox.alert("File name should be maximum 100 characters.");
                    $('#' + cntr).replaceWith($('#' + cntr).val('').clone(true));
                }
                else {
                    return true;
                }
            }*/
        }
        else {
            return true;
        }
    }
    function is_mobile_valid(that){
        var filter = /^\d*(?:\.\d{1,2})?$/;
        var mobNum =  $('#' + that.id).val();
        if (filter.test(mobNum)) {
            if(mobNum.length==10){
            } else {
                bootbox.alert('Please enter 10 digit mobile number');
                $("#"+that.id).val('');
                return false;
            }
        }
        else {
            bootbox.alert('Not a valid mobile number');
            $("#"+that.id).val('');
            return false;
        }
    }

    function submitApprRejRevButton(status){
        debugger
        $("#approveStatus").val(status);

        if(status == 'REJECTED_FPO'){
            var remark=  $("#rejectRemark").val();
            $("#remark").val(remark);
            if($("#rejectRemark").val()==""){
                bootbox.alert("Please Enter Remark.");
                return false;
            }
        }
        if(status == 'REVERTED_FPO'){
            var remark=  $("#revertRemark").val();
            $("#remark").val(remark);
            if($("#revertRemark").val()==""){
                bootbox.alert("Please Enter Remark.");
                return false;
            }
        }

        showAjaxLoader();
        $("#approveForm").submit();
    }

    function viewGstDocument(pcProfileId){
        $("#pcProfileIdForGST").val(pcProfileId);
        $("#gstForm").submit();
    }
    function viewFssaiDocument(pcProfileId){
        $("#pcProfileIdForFSSAI").val(pcProfileId);
        $("#fssaiForm").submit();
    }
    function viewNutritionDocument(pcProfileId){
        $("#pcProfileIdForNUTRITION").val(pcProfileId);
        $("#nutritionForm").submit();
    }
    function viewLicenseDocument(pcProfileId){
        $("#pcProfileIdForLICENSE").val(pcProfileId);
        $("#licenseForm").submit();
    }
    function viewBarcodeDocument(pcProfileId){
        $("#pcProfileIdForBARCODE").val(pcProfileId);
        $("#barcodeForm").submit();
    }
    function viewOrganicDocument(pcProfileId){
        $("#pcProfileIdForORGANIC").val(pcProfileId);
        $("#organicForm").submit();
    }

    function show5(){
        var value1=$("#isGstNo").val();
        if (value1=="true") {
            $("#doc5").removeClass("hidden");
            $("#doc52").removeClass("hidden");
        }
        else{
            $("#doc5").addClass("hidden");
            $("#doc52").addClass("hidden");
        }
    }
    function show6(){
        var value1=$("#isFssaiCertified").val();
        if (value1=="true") {
            $("#doc6").removeClass("hidden");
            $("#doc62").removeClass("hidden");
        }
        else{
            $("#doc6").addClass("hidden");
            $("#doc62").addClass("hidden");
        }
    }

    function show8(){
        var value1=$("#isExportLicense").val();
        if (value1=="true") {
            $("#doc8").removeClass("hidden");
        }
        else{
            $("#doc8").addClass("hidden");
        }
    }

    function show10(){
        debugger;
        var value1=$("#isOrganicCertified").val();
        if (value1=="true") {
            $("#doc10").removeClass("hidden");
        }
        else{
            $("#doc10").addClass("hidden");
        }
    }

    function savePGArtisanProfile(){
        $("#submitForm").submit();
    }


    $(function(){
        var gst = '${pcProfile.isGstNo}';
        var fssai = '${pcProfile.isFssaiCertified}';
        var licsn = '${pcProfile.licenseAttachment}';
        var organic = '${pcProfile.isOrganicCertified}';
        if(gst){
            show5();
        }
        if(fssai){
            show6();
        }
// 	if(nutrition){
// 		show7();
// 	}
// 	if(barcode){
// 		
// 	}
        if(licsn){
            show8();
        }
        if(organic){
            show10();
        }
    });

    function submitButton(statusCode){
    	debugger;
    	$("#statusCode").val(statusCode);
        $("#submitForm").submit();
    }

    $('.amount').keyup(function(){
        var val = $(this).val();
        if(isNaN(val)){
            val = val.replace(/[^0-9\.]/g,'');
            if(val.split('.').length>2)
                val =val.replace(/\.+$/,"");
        }
        $(this).val(val);
    });

    function mailVal(){
        debugger;
        var email = $("#emailId").val();
        var pattern =/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if(!pattern.test(email)){
            bootbox.alert("Not valid email address");
            $("#emailId").val('');
            return false;
        }
    }


    function addInfrastructureDetails() {
        debugger;
        var count11 = $("#rowLength2").val();
        count11++;
        var countArrLength = count11 - 1;
        var infraHtml = '';

        infraHtml = '<tr id="deleteRowDetailsInfra' + count11 + '">' +
            '<td>' +
            '<input type="text" class="form-control form-control-sm" maxlength="40" id="name' + count11 + '" name="farmerCbboMngmtVO[' + countArrLength + '].cbboMngmtName" >' +
            '</td>' +
            '<td>' +
            '<select class="form-control form-control-sm" id="gender' + count11 + '" name="farmerCbboMngmtVO[' + countArrLength + '].gender.genderId" >' +
            '<option value="">--Select--</option>' +
            '<c:forEach items="${genderList}" var="gl">' +
            '<option value="${gl.genderId}" >${gl.genderNameEN}</option>' +
            '</c:forEach>' +
            '</select>' +
            '</td>' +
            '<td>' +
            '<input type="text" class="form-control form-control-sm NumbersOnly" maxlength="3" id="age' + count11 + '" name="farmerCbboMngmtVO[' + countArrLength + '].age" >' +
            '</td>' +
            '<td>' +
            '<input type="text" class="form-control form-control-sm" maxlength="80" id="education' + count11 + '" name="farmerCbboMngmtVO[' + countArrLength + '].education" >' +
            '</td>' +
            '<td>' +
            '<input type="text" class="form-control form-control-sm NumbersOnly" id="aadharNo' + count11 + '"  maxlength="12" name="farmerCbboMngmtVO[' + countArrLength + '].aadharNo" >' +
            '</td>' +
            '<td>' +
            '<input type="text" class="form-control form-control-sm" id="dinNo' + count11 + '"  maxlength="10" name="farmerCbboMngmtVO[' + countArrLength + '].dinNo" >' +
            '</td>' +
            '<td>' +
            '<input type="text" class="form-control form-control-sm AlphaNumericOnly" id="pinNo' + count11 + '" maxlength="10" name="farmerCbboMngmtVO[' + countArrLength + '].pinNo" >' +
            '</td>' +
            '<td>' +
            '<select class="form-control form-control-sm" id="designation' + count11 + '" name="farmerCbboMngmtVO[' + countArrLength + '].designation.designationId" >' +
            '<option value="">--Select--</option>' +
            '<c:forEach items="${designationList}" var="designation">' +
            '<option value="${designation.designationId}">${designation.designationName}</option>' +
            '</c:forEach>' +
            '</select>' +
            '</td>' +
//             '<td>' +
//             '<div id="isTrainer1' + count11 + '"><label for="yes_no_radio"></label><p><input type="radio" name="farmerCbboMngmtVO[' + countArrLength + '].isTrainer" value="true" >Yes</p><p><input type="radio" name="farmerCbboMngmtVO[' + countArrLength + '].isTrainer" value="false" checked>No</p></div> ' +
//             '</td>' +
            '<td class="text-center">' +
            '<button id="btnMinusInfra' + count11 + '" type="button" class="btn btn-danger btn-xs" onclick="deletethisbtnMinusInfra(' + count11 + ')"><i class="fa fa-minus"></i></button>' +
            '</td>' +
            '</tr>';

        $("#rowLength2").val(count11);
        var currRowCount = $("#rowLength2").val() - 1;

        var name = $("#name" + currRowCount).val();
        var phonenumber = $("#phonenumber" + currRowCount).val();
        var gender = $("#gender" + currRowCount).val();
        var age = $("#age" + currRowCount).val();
        var educationDetails = $("#education" + currRowCount).val();
        var aadharNo = $("#aadharNo" + currRowCount).val();
        var din = $("#dinNo" + currRowCount).val();
        var pin = $("#pinNo" + currRowCount).val();

        // Uncomment and adjust the validation as needed
        // if (name !== "" && phonenumber !== "" && gender !== "" && age !== "" && educationDetails !== "" && aadharNo !== "" && din !== "" && pin !== "") {
        $("#managementRow").append(infraHtml);
        // } else {
        //     bootbox.alert("Can't add an empty row");
        //     count11 = currRowCount;
        //     $("#rowLength2").val(currRowCount);
        // }

        $("#btnMinusInfra" + count11).removeAttr("disabled");
        $("#btnMinusInfra" + (count11 - 1)).attr('disabled', 'disabled');
    }


    function deletethisbtnMinusInfra(val) {
        $($("#deleteRowDetailsInfra" + val).closest("tr")).remove();
        $("#btnMinusInfra"+val).attr("disabled");
        $("#btnMinusInfra"+(val-1)).removeAttr('disabled', 'disabled');
        val--;
        $("#rowLength2").val(val);

    }



    function duplicateCodeCheck(code){
        $.ajax({
            type : "GET",
            url : "${contextPath}/fpo/duplicate_cin_no",
            data : {
                "nameField" : code,
            },
            success : function(response) {
                var val = JSON.parse(response);
                hideAjaxLoader();
                if (val.isDuplicate == true) {
                    if($("#cin_no").val() != val.cinno){
                        bootbox.alert("Duplicate CIN NO entered.");
                        $("#cinno").val("");
                    }
                }
            },
            error : function(error) {
                bootbox.alert('Something went wrong');
            }
        });
    }

    function deleteInfraDetails(id){
        $("#hdnmanagmentDtlId").val(id);
        bootbox.confirm({
            message: "Do you want to Deleted this row?",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success'
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result == true) {
                    $("#removeInfraForm").submit();
                }
            }
        });

    }

    $('.NumbersOnly').on(
        'keypress',
        function(event) {
            var regex = new RegExp("^[0-9.]+$");
            var key = String.fromCharCode(!event.charCode ? event.which
                : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
        });



    function isMobileValid(count, valueNm){
        var rows = $('#managementRow tr').length;
        for (i = 1; i <= rows; i++) {
            var count1=i
            var valueName = $('#phonenumber'+count1).val();
            var filter = /^\d*(?:\.\d{1,2})?$/;
            if (filter.test(valueName) && valueName.length != 10) {
                bootbox.alert("Invalid phone no.");
                $("#phonenumber"+count).val("");
                return false;
            }
            else{
                return true;
            }
        }

    }

    function ValidAadhaarNoCheck(that){
        var valueNm = $('#' + that.id).val();
        var filter = /^\d*(?:\.\d{1,2})?$/;
        if (filter.test(valueNm)) {
            if(valueNm.length==12){
            } else {
                bootbox.alert('Invalid Aadhaar No');
                $("#"+that.id).val('');
                return false;
            }
        }
        else {
            bootbox.alert('Invalid Aadhaar No');
            $("#"+that.id).val('');
            return false;
        }
        if (!ValidAadhaarNo(valueNm)) {
            bootbox.alert("Invalid Aadhaar No");
            $("#"+that.id).val('');
            return false;
        }

    }


    function  ValidAadhaarNoCheckCount(count, that){
        var rows = $('#managementRow tr').length;

        for (i = 1; i <= rows; i++) {
            var count1=i
            var valueName = $('#aadharNo'+count1).val();

            var filter = /^\d*(?:\.\d{1,2})?$/;
            if (filter.test(valueName)) {
                if(valueName.length==12){
                } else {
                    bootbox.alert('Invalid Aadhaar No');
                    $("#"+that.id).val('');
                    return false;
                }
            }
            else {
                bootbox.alert('Invalid Aadhaar No');
                $("#"+that.id).val('');
                return false;
            }
            if (!ValidAadhaarNo(valueName)) {
                bootbox.alert("Invalid Aadhaar No");
                $("#aadharNo"+count).val("");
                return false;
            }
        }
    }

    $(function() {
        $('.datepicker_con1 > input').datepick({
            onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',showOnFocus: true,
            showTrigger: '<button type="button" class="trigger">' +
                '<i class="fa fa-calendar"></i></button>',
            maxDate : -0,
            // minDate: 0,
            onSelect: function(d){
//	     			checkSlotforBanner(this.value);
//	     			checkBannerEvent(this.value);
            }
        });
    });

    function validatePAN(that){

        var valueNm = $('#' + that.id).val();

        var panPattern = /^[A-Z]{5}[0-9]{4}[A-Z]$/;

        if (panPattern.test(valueNm)) {
            return true; // PAN is valid
        } else {
            bootbox.alert("Invalid Pan No");
            $("#"+that.id).val('');
            return false; // PAN is invalid
        }


    }


    function aadharDupliCate(that){
        var aadharNO = $('#' + that.id).val();
        $.ajax({
            type : "GET",
            url : "${contextPath}/fpo/duplicate_aadharnofpo_no",
            data : {
                "aadharNO" : aadharNO,
            },
            success : function(response) {
                var val = JSON.parse(response);
                hideAjaxLoader();
                if (val.isDuplicate == true) {
                    if(aadharNO == val.aadharno){
                        bootbox.alert("Duplicate Aadhar Number entered.");
                        $("#"+that.id).val('');
                    }
                }
            },
            error : function(error) {
                bootbox.alert('Something went wrong');
            }
        });
    }


    function  pandupicate(that){
        var panNO = $('#' + that.id).val();
        $.ajax({
            type : "GET",
            url : "${contextPath}/fpo/duplicate_panmanagmentfpo_no",
            data : {
                "panNO" : panNO,
            },
            success : function(response) {
                var val = JSON.parse(response);
                hideAjaxLoader();
                if (val.isDuplicate == true) {
                    if(panNO == val.panNo){
                        bootbox.alert("Duplicate PAN Number Entered.");
                        $("#"+that.id).val('');
                    }
                }
            },
            error : function(error) {
                bootbox.alert('Something went wrong');
            }
        });
    }



    function  checkfpoPan(that){
        var panNO = $('#' + that.id).val();
        $.ajax({
            type : "GET",
            url : "${contextPath}/fpo/duplicate_checkfpoPan",
            data : {
                "panNO" : panNO,
            },
            success : function(response) {
                var val = JSON.parse(response);
                hideAjaxLoader();
                if (val.isDuplicate == true) {
                    if(panNO == val.panNo){
                        bootbox.alert("Duplicate PAN Number Entered.");
                        $("#"+that.id).val('');
                    }
                }
            },
            error : function(error) {
                bootbox.alert('Something went wrong');
            }
        });
    }


</script>