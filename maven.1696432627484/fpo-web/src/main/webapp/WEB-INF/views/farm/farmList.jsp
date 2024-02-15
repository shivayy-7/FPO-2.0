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
        <div class="col-md-6"> <h5 class="change-color"> Farm List </h5></div>
        <div class="col-md-6"> <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item"><a href="#"> Farm Management</a></li>
              <li class="breadcrumb-item active" aria-current="page"> View Farm</li>
            </ol>
          </nav></div>
        <div class="col-md-12 ">

          <!-- Rahul Edit -->
          <hr style="margin-top: 5px;" />
          <form action="${contextPath}/farm/getFarm" id="submitForm" method="get" > 
              <div class="col-md-12">
                <div class="header-box">
                    <div class="row align-items-end">
                        <div class="form-group col-md-2">
                            <label for="inputEmail4">Select Block </label>
                            <select class="form-select form-control form-control-sm selectpicker" id="block" name="farmVO.blockId" onchange="getGramPanchayat(this.value,'gp','')">
                            		<option value="0">-Select-</option>
                               <c:forEach var="block" items="${blockList}" varStatus="status">
								    <option value="${block.blockId}" ${farmDtls.farmVO.blockId eq block.blockId ? 'selected':''}>${block.blockNameEN}</option>
							   </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputEmail4">Select GP </label>
                            <select class="form-select form-control form-control-sm" id="gp" name="farmVO.gpId" onchange="getVillage(this.value,'village','')" >
                            <option value="0">-Select-</option>
                            
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputEmail4">Select Village </label>
                            <select class="form-select form-control form-control-sm" data-live-search="true" id="village" name="farmVO.villageId" onchange="getFrmByVlgId(this.value,'frm','')">
                            <option value="0">-Select-</option>
                            
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputEmail4">Farmer Id </label>
                            <select class="form-select" id="frm" name="farmVO.frmId">
                            <option value="0">-Select-</option>
                            
                            </select>
                        </div>
                        <div class="col-md-2 text-start mb-7"><button type="button" class="btn-submit" onclick="filterData();"><i class="bx bx-search"></i> Search</button></div>
                    </div>
                </div>
            </div>
            </form>

          <c:if test="${not empty farmData.farmList}">
<!--             <div class="col-md-12" > -->
<!--                 <div class="table-responsive"> -->
                    <table class="datatable table table-striped table-bordered exportbtn mt-3">
                        <thead>
                          <tr>
                            <th>Sl No</th>
                            <th>Name of the Farm</th>
                            <th>Area of the Farm</th>
                            <th>Farm Owner Name</th>
                            <th>Plot No</th>
                            <th>Khata Number</th>
                            <th>Crop Name</th>
                            <th>Geo-Tagged Done?</th>
                            <th>Action</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="farm" items="${farmData.farmList}" varStatus="loop">
                          <tr>
                            <td>${loop.index + 1}</td>
                            <td></td>
                            <td>${farm.landAreaOfFarm}</td>
                            <td>${farm.farmerId.name}</td>
                            <td>${farm.plotNo}</td>
                            <td>${farm.khataNo}</td>
                            <td>${farm.cropName}</td>
                            <td>No</td>
                            <td>
                              <a href="#" type="button" class="btn-warning btn btn-sm" title="Update" onclick="updateFarmdata('${farm.farmCode}')"><i class="bx bx-undo"></i></a>
<!--                               <button type="button" class="btn-info btn btn-sm text-white" title="View"><i class="bx bx-file"></i></button> -->
                            </td>
                          </tr>
                          </c:forEach>
                          
                        </tbody> 
                      </table>
                <div class="col-md-12 text-center">
                    <button type="button" class="btn btn-sm btn-dark">Back</button>
                </div>
<!--                 </div> -->

<!--             </div> -->
          </c:if>
          <c:if test="${empty farmData.farmList}">Data not found</c:if> 
<form action="${contextPath}/farm" id="updateForm" method="get" >
      <input type="hidden" name="blockId" id="updateBlockId" />
	  <input type="hidden" name="gpId" id="updateGpId" />
	  <input type="hidden" name="villageId" id="updateVillageId"  />
	  <input type="hidden" name="frmId" id="updateFrmId" />
	  <input type="hidden" name="farmCode" id="updatefrm" />
</form>         
          
<script>

function updateFarmdata(frmCode) {
	debugger;
    var blockValue = $("#block").val();
    var gpValue = $("#gp").val();
    var villageValue = $("#village").val();
    var frmValue = $("#frm").val();

    $("#updateBlockId").val(blockValue);
    $("#updateGpId").val(gpValue);
    $("#updateVillageId").val(villageValue);
    $("#updateFrmId").val(frmValue);
    $("#updatefrm").val(frmCode);
    
    $("#updateForm").submit();
}

function filterData(){
   debugger;
   $("#submitForm").submit();
}
            
            $(document).ready(function(){
            	if('${farmDtls.farmVO.blockId}' != ''){
            		getGramPanchayat('${farmDtls.farmVO.blockId}','gp', '${farmDtls.farmVO.gpId}');
            		getVillage('${farmDtls.farmVO.gpId}','village', '${farmDtls.farmVO.villageId}');
            		getFrmByVlgId('${farmDtls.farmVO.villageId}','frm', '${farmDtls.farmVO.frmId}');
            	}
            });
            
</script>