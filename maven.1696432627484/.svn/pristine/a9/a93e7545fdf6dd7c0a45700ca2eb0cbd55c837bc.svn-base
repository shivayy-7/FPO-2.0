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
    .hidden {
        display: none;
    }
</style>

<div class="row mt-3">

        <div class="col-md-6"> <h5 class="change-color"> Add Farm </h5></div>
        <%@ include file="/WEB-INF/views/message.jsp"%>
        <div class="col-md-6"> <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item"><a href="#"> Farm Management</a></li>
              <li class="breadcrumb-item active" aria-current="page"> Add Farm</li>
            </ol>
          </nav></div>
        <div class="col-md- 12 ">

          <!-- Rahul Edit -->
          <hr style="margin-top: 5px;" />
          <form action="${contextPath}/farm/manageFarm" id="submitForm" method="POST" > 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="farmVO.farmId" id="pcProfileId" value="${farmCodeDtls.farmVO.farmId}" />
						<input type="hidden" name="farmVO.farmCode" value="${farmCodeDtls.farmVO.farmCode}"/>
						
              <div class="col-md-12">
                <div class="header-box">
                    <div class="row align-items-end">
                        <div class="form-group col-md-2">
                            <label for="inputEmail4">Select Block <span style="color:red">*</span></label>
                            <select class="form-select form-control form-control-sm selectpicker" id="block" onchange="getGramPanchayat(this.value,'gp','')">
                                    <option value="0">-Select-</option>
                               <c:forEach var="block" items="${blockList}" varStatus="status">
								    <option value="${block.blockId}" ${farmCodeDtls.farmVO.blockId eq block.blockId ? 'selected':''}>${block.blockNameEN}</option>
							   </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputEmail4">Select GP <span style="color:red">*</span></label>
                            <select class="form-select form-control form-control-sm" id="gp" onchange="getVillage(this.value,'village','')" >
                            <option value="0">-Select-</option>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputEmail4">Select Village <span style="color:red">*</span></label>
                            <select class="form-select form-control form-control-sm" data-live-search="true" id="village" onchange="getFrmByVlgId(this.value,'frm','')">
                            <option value="0">-Select-</option>
                            
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputEmail4">Farmer Id <span style="color:red">*</span></label>
                            <select class="form-select form-control form-control-sm" id="frm" name="farmVO.frmId">
                            <option value="0">-Select-</option>
                            
                            </select>
                        </div>
                        <div class="col-md-2 text-start mb-7"><button type="button" class="btn-submit" onclick="showDiv()"><i class="bx bx-search"></i> Search</button></div>
                    </div>
                </div>
            </div>

            <c:if test="${not empty farmCodeDtls.farmVO}"></c:if>
            <div class="col-md-12"  id="welcomeDiv" style="display:none;">
                <div class="row align-items-end">
                    <div class="col-md-12">
                        <h6 class="headingbg mb-2 mt-2">Land Details</h6>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="landAreaOfFarm" class="required"> Area of the Farm </label>
                        <input class="form-control form-control-sm NumbersOnly" type="text" placeholder="In acre" id="landAreaOfFarm" name="farmVO.landAreaOfFarm" value="${farmCodeDtls.farmVO.landAreaOfFarm}" >
                    </div>
                    <div class="form-group col-md-2">
                        <label for="plotNo" class="required" > Plot Number  </label>
                        <input class="form-control form-control-sm NumbersOnly" type="text" placeholder="" id="plotNo" name="farmVO.plotNo" value="${farmCodeDtls.farmVO.plotNo}" >
                    </div>
                    <div class="form-group col-md-2">
                        <label for="khataNo" class="required" > Khata Number</label>
                        <input class="form-control form-control-sm NumbersOnly" type="text" placeholder="" id="khataNo" name="farmVO.khataNo" value="${farmCodeDtls.farmVO.khataNo}" >
                    </div>

                    <div class="col-md-12">
                        <h6 class="headingbg mb-2 mt-2">Crop Details</h6>
                    </div>

                    <div class="form-group col-md-2">
                        <label for="landAreaOfFarm" class="required" > Crop Name </label>
                        <select class="form-select" id="landAreaOfFarm" name="farmVO.cropName" >
                            <option value="0">-Select-</option>
                            <option value="Rice" ${farmCodeDtls.farmVO.cropName eq 'Rice'?'selected':''} > Rice</option>
                            <option value="Arhar Dal" ${farmCodeDtls.farmVO.cropName eq 'Arhar Dal'?'selected':''} > Arhar Dal</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="cropPattern" class="required" > Crop Pattern </label>
                        <select class="form-select" id="cropPattern" name="farmVO.cropPattern" >
                            <option value="0">-Select-</option>
                            <option value="Yearly" ${farmCodeDtls.farmVO.cropPattern eq 'Yearly' ? 'selected':''} > Yearly</option>
                            <option value="Half-Yearly" ${farmCodeDtls.farmVO.cropPattern eq 'Half-Yearly' ? 'selected':''}> Half-Yearly</option>
                            <option value="Quarterly" ${farmCodeDtls.farmVO.cropPattern eq 'Quarterly' ? 'selected':''}> Quarterly</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="prodQuantity" class="required" > Production Quantity (Last Year)</label>
                        <input class="form-control form-control-sm NumbersOnly" type="text" placeholder="In Ton" id="prodQuantity" name="farmVO.prodQuantity" value="${farmCodeDtls.farmVO.prodQuantity}" >
                    </div>
                    <div class="form-group col-md-2">
                        <label for="pesticide" class="required" > Pesticide(Per Acre) </label>
                        <input class="form-control form-control-sm NumbersOnly" type="text" placeholder="In KG" id="pesticide" name="farmVO.pesticide" value="${farmCodeDtls.farmVO.pesticide}" >
                    </div>
                    <div class="form-group col-md-2">
                        <label for="isSoilAnalysis" class="required" > Soil Analysis Done? </label>
                        <select class="form-select" id="isSoilAnalysis" name="farmVO.isSoilAnalysis">
                            <option value="">-Select-</option>
                            <option value="true" ${farmCodeDtls.farmVO.isSoilAnalysis ? 'selected' : ''} > Yes</option>
                            <option value="false" ${!farmCodeDtls.farmVO.isSoilAnalysis ? 'selected' : ''}> No</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="totalInvestment" class="required" > Total Investment in the Farm  </label>
                        <input class="form-control form-control-sm NumbersOnly" type="text" placeholder="Last Financial Year(INR)" id="totalInvestment" name="farmVO.totalInvestment" onchange="currencyConverter(this.value,'totalInvestment')" value="${farmCodeDtls.farmVO.totalInvestment}" >
                    </div>
                    <div class="form-group col-md-2">
                        <label for="totalReturn" class="required" > Total Return from the Farm </label>
                        <input class="form-control form-control-sm NumbersOnly" type="text" placeholder="Last Financial Year(INR)" id="totalReturn" name="farmVO.totalReturn" onchange="currencyConverter(this.value,'totalReturn')" value="${farmCodeDtls.farmVO.totalReturn}" >
                    </div>
                    <div class="form-group col-md-2">
                        <label for="irrigationFacility" class="required" > Irrigation Facility </label>
                        <select class="form-select" id="irrigationFacility" name="farmVO.irrigationFacility">
                            <option value="0">-Select-</option>
                            <option value="Rain Water" ${farmCodeDtls.farmVO.irrigationFacility eq 'Rain Water' ? 'selected':''} >Rain Water</option>
                            <option value="Lift Irrigation" ${farmCodeDtls.farmVO.irrigationFacility eq 'Lift Irrigation' ? 'selected':''}>Lift Irrigation</option>
                            <option value="Canal irrigation" ${farmCodeDtls.farmVO.irrigationFacility eq 'Canal irrigation' ? 'selected':''}>Canal irrigation</option>
                            <option value="Drift Irrigation" ${farmCodeDtls.farmVO.irrigationFacility eq 'Drift Irrigation' ? 'selected':''}>Drift Irrigation</option>
                            <option value="Others" ${farmCodeDtls.farmVO.irrigationFacility eq 'Rain Water' ? 'Others':''}>Others</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="plantationMonth" class="required" > Plantation Month </label>
                        <select class="form-select" id="plantationMonth" name="farmVO.plantationMonth">
                            <option value="0" >-Select Month-</option>
                            <option value="Jan" ${farmCodeDtls.farmVO.plantationMonth eq 'Jan' ? 'selected' : ''} >January</option>
                            <option value="Feb" ${farmCodeDtls.farmVO.plantationMonth eq 'Feb' ? 'selected' : ''}>February</option>
                            <option value="Mar" ${farmCodeDtls.farmVO.plantationMonth eq 'Mar' ? 'selected' : ''}>March</option>
                            <option value="Apr" ${farmCodeDtls.farmVO.plantationMonth eq 'Apr' ? 'selected' : ''}>April</option>
                            <option value="May" ${farmCodeDtls.farmVO.plantationMonth eq 'May' ? 'selected' : ''}>May</option>
                            <option value="Jun" ${farmCodeDtls.farmVO.plantationMonth eq 'Jun' ? 'selected' : ''}>June</option>
                            <option value="Jul" ${farmCodeDtls.farmVO.plantationMonth eq 'Jul' ? 'selected' : ''}>July</option>
                            <option value="Aug" ${farmCodeDtls.farmVO.plantationMonth eq 'Aug' ? 'selected' : ''}>August</option>
                            <option value="Sep" ${farmCodeDtls.farmVO.plantationMonth eq 'Sep' ? 'selected' : ''}>September</option>
                            <option value="Oct" ${farmCodeDtls.farmVO.plantationMonth eq 'Oct' ? 'selected' : ''}>October</option>
                            <option value="Nov" ${farmCodeDtls.farmVO.plantationMonth eq 'Nov' ? 'selected' : ''}>November</option>
                            <option value="Dec" ${farmCodeDtls.farmVO.plantationMonth eq 'Dec' ? 'selected' : ''}>December</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="harvestingMonth" class="required" > Harvesting Month </label>
                        <select class="form-select" id="harvestingMonth" name="farmVO.harvestingMonth">
                            <option value="0" >-Select Month-</option>
                            <option value="Jan" ${farmCodeDtls.farmVO.harvestingMonth eq 'Jan' ? 'selected' : ''} >January</option>
                            <option value="Feb" ${farmCodeDtls.farmVO.harvestingMonth eq 'Feb' ? 'selected' : ''}>February</option>
                            <option value="Mar" ${farmCodeDtls.farmVO.harvestingMonth eq 'Mar' ? 'selected' : ''}>March</option>
                            <option value="Apr" ${farmCodeDtls.farmVO.harvestingMonth eq 'Apr' ? 'selected' : ''}>April</option>
                            <option value="May" ${farmCodeDtls.farmVO.harvestingMonth eq 'May' ? 'selected' : ''}>May</option>
                            <option value="Jun" ${farmCodeDtls.farmVO.harvestingMonth eq 'Jun' ? 'selected' : ''}>June</option>
                            <option value="Jul" ${farmCodeDtls.farmVO.harvestingMonth eq 'Jul' ? 'selected' : ''}>July</option>
                            <option value="Aug" ${farmCodeDtls.farmVO.harvestingMonth eq 'Aug' ? 'selected' : ''}>August</option>
                            <option value="Sep" ${farmCodeDtls.farmVO.harvestingMonth eq 'Sep' ? 'selected' : ''}>September</option>
                            <option value="Oct" ${farmCodeDtls.farmVO.harvestingMonth eq 'Oct' ? 'selected' : ''}>October</option>
                            <option value="Nov" ${farmCodeDtls.farmVO.harvestingMonth eq 'Nov' ? 'selected' : ''}>November</option>
                            <option value="Dec" ${farmCodeDtls.farmVO.harvestingMonth eq 'Dec' ? 'selected' : ''}>December</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="energyUsage" class="required" > Energy Usage Type </label>
                        <select class="form-select" id="energyUsage" name="farmVO.energyUsage">
                            <option value="0" >-Select-</option>
                            <option value="Renewable" ${farmCodeDtls.farmVO.energyUsage eq 'Renewable' ? 'selected' : ''} >Renewable</option>
                            <option value="Non-Renewable" ${farmCodeDtls.farmVO.energyUsage eq 'Non-Renewable' ? 'selected' : ''} >Non-Renewable</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="cropRotation" class="required" > Crop Rotation Done? </label>
                        <select class="form-select" id="cropRotation" name="farmVO.isCropRotation" onchange="showHideField(this.value, 'hiddenRotationCropName')"  >
                            <option value="">-Select-</option>
                            <option value="true" ${farmCodeDtls.farmVO.isCropRotation ? 'selected' : ''}>Yes</option>
                            <option value="false" ${!farmCodeDtls.farmVO.isCropRotation ? 'selected' : ''}>No</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2 hidden" id="hiddenRotationCropName">
                        <label for="rotationCropName" class="required" > Crop Name</label>
                        <input class="form-control form-control-sm" type="text" placeholder="" id="rotationCropName" name="farmVO.rotationCropName" value="${farmCodeDtls.farmVO.cropName}" >
                    </div>

                    <div class="col-md-12 text-center">
                        <button type="button" class="btn btn-sm btn-info text-white">Save & Add New Farm</button>
                        <button type="button" class="btn btn-sm btn-primary" onclick="submitFarm()">Submit</button>
                    </div>

                </div>
              </div>
          </form>
    </div>      
          
          
          
<script>

$(document).ready(function(){
	debugger;
	if('${farmCodeDtls.farmVO}' != null && '${farmCodeDtls.farmVO}' != ''){
		document.getElementById('welcomeDiv').style.display = "block";
	}
	
    if('${farmCodeDtls.farmVO.blockId}' != ''){
    	getGramPanchayat('${farmCodeDtls.farmVO.blockId}','gp', '${farmCodeDtls.farmVO.gpId}');
    	getVillage('${farmCodeDtls.farmVO.gpId}','village', '${farmCodeDtls.farmVO.villageId}');
    	getFrmByVlgId('${farmCodeDtls.farmVO.villageId}','frm', '${farmCodeDtls.farmVO.frmId}');
    }
    showHideField('${farmCodeDtls.farmVO.isCropRotation}', 'hiddenRotationCropName');
	
});

            function showDiv() {
            	debugger;
            	if($("#block").val() == '0'){
            	   alert("Fill the Block");
            	}else if($("#gp").val() == '0'){
            		alert("Fill the Gp");
            	}else if($("#village").val() == '0'){
            		alert("Fill the Village");
            	}else if($("#frm").val() == '0'){
            		alert("Fill the FarmerId");
            	}else{
            		document.getElementById('welcomeDiv').style.display = "block";
            	}
            	
            
        	}
            
            function showHideField(value, showField){
            	debugger;
            	if(value === "true"){
            		$("#"+showField).removeClass("hidden");
            	}else{
            		$("#"+showField).addClass("hidden");
            	}
            }
         
// function submitFarm(){
// 	debugger;
// 	$("#submitForm").submit();
// }
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
  
function submitFarm(){
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
		$("#submitForm").submit();
	}
	
}

/* function convertToRupees(amount, field) {
	debugger;

    var integerAmount = parseInt(amount, 10);

    if (!isNaN(integerAmount)) {
        
        var formattedAmount = integerAmount.toLocaleString('en-IN', {
            style: 'currency',
            currency: 'INR'
        });

        document.getElementById(field).value = formattedAmount;
    } else {
        document.getElementById('result').textContent = 'Please enter a valid number.';
    }
} */
  
</script>
          