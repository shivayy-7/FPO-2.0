const context = window.contextPath;
function getBankBranchByBankId(val,selectedVal){
	$('#loader').removeClass('hidden');
    var bank=val;
    $.ajax({
        url : context+'/core/getBranchNameByBankName',
        type : 'GET',
        data : ({
            bankName : bank
        }),
        cache : true,
        asynch : false,
        success : function(response) {
			$('#loader').addClass('hidden');
            var html=" <option value='0' selected='selected'>--select--</option>";
        	var selectData = '';
            var obj = response.data;
                 $.each(obj, function(key,value) {
                	 selectData = (value.branchName == selectedVal) ? "selected" : "";
                	 html += "<option value='"+value.branchName+"' "+selectData+">"+value.branchName+"</option>";
                }); 
                $("#newBranchName").empty().append(html);
                $("#newIFSC").empty().append("<option value='0' >--select--</option>");
                 var brnch=$("#txhBranch").val();
            //	$("#newBranchName").val(brnch).trigger('change');
            },
        error : function(error) {
			$('#loader').addClass('hidden');
            bootbox.alert(error);
        }
    });
}

function getIFSCByBranchId(val,selectedVal) {	
    $('#loader').removeClass('hidden');
    var bank=$("#newBankName").val();
    var branch=val;
    $.ajax({
        url : context+'/core/getIfscByBranchName',
        type : 'GET',
        data : ({
            bankName : bank,
            branchName : branch
        }),
        cache : false,
        asynch : false,
        success : function(response) {
			$('#loader').addClass('hidden');
            var html="<option value='0' selected='selected'>--select--</option>";
            var obj = response.data;
            var selectData = '';
                 $.each(obj, function(key,value) {
                  selectData = (value.bankBranchId == selectedVal) ? "selected" : "";
                   html += "<option value='"+value.bankBranchId+"' "+selectData+">"+value.ifsc+"</option>";
                }); 
                 $("#newIFSC").val("0");
                $("#newIFSC").empty().append(html);
            },
        error : function(error) {
			$('#loader').addClass('hidden');
            bootbox.alert(error);
        }
    });
}


// ################################################### DEMOGRAPHY ############################################################### //

function getState(selectedVal){
	$('#loader').removeClass('hidden');
	$.ajax({
		type : "GET",
		url : context+'/core/getStateList',
		dataType : "json",
		success : function(response) {
			$('#loader').addClass('hidden');
			var html = "<option value='0'>-Select-</option>";
			var selectData = '';
			var data = response;
			if (data != "" || data != null) {
				$.each(data, function(index, value) {
						selectData = (value.stateId == selectedVal) ? "selected" : "";
						html += "<option value='"+value.stateId+"' "+selectData+">"+value.stateName+"</option>";
					});
			}
			$('#stateId').empty().append(html);
		},
		error : function(error) {
			$('#loader').addClass('hidden');
			bootbox.alert("No State Found");
		}
	});
}

function getDistrict(stateId,selectedVal){
	// var stateId = $("#stateId").val();
	// var stateId = selectedVal;
	$('#loader').removeClass('hidden');
	$.ajax({
		type : "GET",
		url : context+'/core/findDistrictListByStateId',
		dataType : "json",
		data : {
			"stateId" : stateId,
		},
		success : function(response) {
			$('#loader').addClass('hidden');
			var html = "<option value='0'>-Select-</option>";
			var selectData = '';
			var data = response;
			if (data != "" || data != null) {
				$.each(data, function(index, value) {
						selectData = (value.districtId == selectedVal) ? "selected" : "";
						html += "<option value='"+value.districtId+"' "+selectData+">"+value.districtName+"</option>";
					});
			}
			$('#distId').empty().append(html);
			$('#blockId').empty().append("<option value='0'>-Select-</option>");
			$('#gpId').empty().append("<option value='0'>-Select-</option>");
			$('#villageId').empty().append("<option value='0'>-Select-</option>");
		},
		error : function(error) {
			$('#loader').addClass('hidden');
			bootbox.alert("No District Found");
		}
	});
}
	
function getULB(id){
	var distId = $("#distId").val();
	$('#loader').removeClass('hidden');
	$.ajax({
		type : "GET",
		url : context+'/core/findMunicipalityListByDistrictId',
		dataType : "json",
		data : {
			"districtId" : distId,
		},
		success : function(response) {
			$('#loader').addClass('hidden');
			var html = "<option value='0'>-Select-</option>";
			var data = response;
			if (data != "" || data != null) {
				$.each(data, function(index, value) {
					html = html + "<option value="+value.municipalityId+">"
							+ value.municipalityName + "</option>";
				});
			}
			$('#ulbId').empty().append(html);
			$('#wardId').empty().append("<option value='0'>-Select-</option>");
		},
		error : function(error) {
			$('#loader').addClass('hidden');
			bootbox.alert("No ULB Found");
		}
	});
	}
	
	
	
	
function getBlock(){
	var distId = $("#distId").val();
	var gp= $('#gpId').empty().append("<option value='0'>-Select-</option>");
	var village=$('#villageId').empty().append("<option value='0'>-Select-</option>");
	$('#loader').removeClass('hidden');
$.ajax({
	type : "GET",
	url : context+'/core/findBlockListByDistrictId',
	dataType : "json",
	data : {
		"districtId" : distId,
	},
	success : function(response) {
		$('#loader').addClass('hidden');
		var html = "<option value='0'>-Select-</option>";
		var data = response;
		if (data != "" || data != null) {
			$.each(data, function(index, value) {
				html = html + "<option value="+value.blockId+">"
						+ value.blockName + "</option>";
			});
		}
		$('#blockId').empty().append(html);
	},
	error : function(error) {
		$('#loader').addClass('hidden');
		bootbox.alert("No Block Found");
	}
});
}


function getGP(blockId){
	$('#loader').removeClass('hidden');
	$.ajax({
		type : "GET",
		url : context+'/core/findGpListByBlockId',
		dataType : "json",
		data : {
			"blockId" : blockId,
		},
		success : function(response) {
			$('#loader').addClass('hidden');
			var html = "<option value='0'>-Select-</option>";
			var data = response;
			if (data != "" || data != null) {
				$.each(data, function(index, value) {
					html = html + "<option value="+value.gpId+">"
							+ value.gpName + "</option>";
				});
			}
			$('#gpId').empty().append(html);
			$('#villageId').empty().append("<option value='0'>-Select-</option>");
		},
		error : function(error) {
			$('#loader').addClass('hidden');
			bootbox.alert("No Grampanchayat Found");
		}
	});
}

function getVillage(gpId){
	$('#loader').removeClass('hidden');
	$.ajax({
		type : "GET",
		url : context+'/core/findVillageListByGpId',
		dataType : "json",
		data : {
			"gpId" : gpId,
		},
		success : function(response) {
			$('#loader').addClass('hidden');
			var html = "<option value='0'>-Select-</option>";
			var data = response;
			if (data != "" || data != null) {
				$.each(data, function(index, value) {
					html = html + "<option value="+value.villageId+">"
							+ value.villageName + "</option>";
				});
			}
			$('#villageId').empty().append(html);
		},
		error : function(error) {
			$('#loader').addClass('hidden');
			bootbox.alert("No Village Found");
		}
	});
}