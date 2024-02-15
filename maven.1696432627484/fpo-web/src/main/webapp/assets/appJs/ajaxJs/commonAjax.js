function getActivityListByCmpCode(cmpCode, fieldId, selecteditems) {
//    debugger;

    $.get(contextPath + "/core/getData", { id: cmpCode, identity: 'Activity' })
        .done(function (data) {
            var activity = data.data;
            var html = "<option value='0'>--- Select ---</option>";

            if (activity !== null) {
                activity.forEach(element => {
                    // Iterate over the selecteditems array
                    if(selecteditems != null){
                    selecteditems.forEach(selectedItem => {
                        html += `<option value='${element.actCode.actCode}' ${selectedItem === element.actCode.actCode ? 'selected' : ''}>${element.actCode.actNameEn}</option>`;
                    });
                    }else{
                        html += `<option value='${element.actCode.actCode}' >${element.actCode.actNameEn}</option>`;
                    }
                    
                });
            }

            $("#" + fieldId).empty().append(html);
        })
        .fail(function () {
            $('#loader').addClass('hidden');
            alert("error");
        });
}

function getActivityListByCmpCodeByOne(cmpCode, fieldId, selecteditems) {
//    debugger;

    $.get(contextPath + "/core/getData", { id: cmpCode, identity: 'Activity' })
        .done(function (data) {
            var activity = data.data;
            var html = "<option value='0'>--- Select ---</option>";

            if (activity !== null) {
                activity.forEach(element => {
                    // Iterate over the selecteditems array
                    if(selecteditems != null){
                        html += `<option value='${element.actCode.actCode}' ${selecteditems === element.actCode.actCode ? 'selected' : ''}>${element.actCode.actNameEn}</option>`;
                    }else{
                        html += `<option value='${element.actCode.actCode}' >${element.actCode.actNameEn}</option>`;
                    }
                    
                });
            }

            $("#" + fieldId).empty().append(html);
        })
        .fail(function () {
            $('#loader').addClass('hidden');
            alert("error");
        });
}



function getSubActivityListByActCode(actCode,fieldId,selecteditems) {
//	debugger;
	actCode = actCode.toString();
$.get( contextPath+"/core/getData", { id: actCode, identity: 'SubActivity'} )
  .done(function( data ) {
	  var subActivity = data.data;
	  console.log(subActivity);
	  var html="<option value='0'>--- Select ---</option>";
    
    if (subActivity !== null) {
    subActivity.forEach(element => {
    
        if(selecteditems != null){
              selecteditems.forEach(selectedItem => {
                   html += `<option value='${element.subActCode.subActCode}' ${selectedItem === element.subActCode.subActCode ? 'selected' : ''}>${element.subActCode.subActNameEn}</option>`;
           });
         }else{
              html += `<option value='${element.subActCode.subActCode}'>${element.subActCode.subActNameEn}</option>`;
         }
    });
    }

    $("#"+fieldId).empty().append(html);
    
  })
  .fail(function() {
	  $('#loader').addClass('hidden');
    alert( "error" );
  });
}


function getBankBranchByBankId(bankId, fieldId, matchId) {
    debugger;
    $.get(contextPath + "/core/getData", { id: bankId, identity: 'BankBranch' })
        .done(function (data) {
            var bankBranch = data.data;
            var html = "<option value='0'>--- Select ---</option>";

            if (bankBranch !== null) {
                bankBranch.forEach(element => {
                    var isSelected = element.branchName == matchId ? 'selected' : '';
                    html += "<option value='" + element.bankBranchId + "' " + isSelected + ">" + element.branchName + "</option>";
                });
            }

            $("#" + fieldId).empty().append(html);

        })
        .fail(function () {
            $('#loader').addClass('hidden');
            alert("error");
        });
}

function getBranchNameByBankName(bankName, fieldId, matchId) {
//    debugger;
    $.get(contextPath + "/core/getData", { id: bankName, identity: 'BANKBRANCH' })
        .done(function (data) {
            var bankBranch = data.data;
            var html = "<option value='0'>--- Select ---</option>";
            var input = "";

            if (bankBranch !== null) {
                bankBranch.forEach(element => {
                    var isSelected = element.branchName == matchId ? 'selected' : '';
                    html += "<option value='" + element.bankBranchId + "' " + isSelected + ">" + element.branchName + "</option>";
                });
            }

            $("#" + fieldId).empty().append(html);

        })
        .fail(function () {
            $('#loader').addClass('hidden');
            alert("error");
        });
}



function getIFSCByBranchId(actCode,fieldId,matchId) {	
debugger;
  $.get( contextPath+"/core/getData", { id: actCode, identity: 'BANKBRANCHIFSC'} )
  .done(function( data ) {
	  var bankBranchIfsc = data.data;
	  var html="<option value='0'>--- Select ---</option>";
    
    if (bankBranchIfsc !== null) {
      bankBranchIfsc.forEach(element => {
      var isSelected = element.ifsc == matchId ? 'selected' : '';
      html += "<option value='" + element.bankBranchId + "' " + isSelected + ">" + element.ifsc + "</option>";
//       html = html +  "<option value='"+element.bankBranchId+"'>"+element.ifsc+"</option>";
    });
    }

    $("#"+fieldId).empty().append(html);
    
  })
  .fail(function() {
	  $('#loader').addClass('hidden');
    alert( "error" );
  });

}


// ################################################### DEMOGRAPHY ############################################################### //

function getDistrict(stateId, fieldId, matchId) {
//    debugger;
    $.get(contextPath + "/core/getData", { id: stateId, identity: 'District' })
        .done(function(data) {
            var dist = data.data;
            var html = "<option value='0'>--- Select ---</option>";

            if (dist !== null) {
                dist.forEach(element => {
                    var isSelected = element.districtId == matchId ? 'selected' : '';
                    html += "<option value='" + element.districtId + "'" + isSelected + ">" + element.districtName + "</option>";
                });
            }

            $("#" + fieldId).empty().append(html);

        })
        .fail(function() {
            $('#loader').addClass('hidden');
            alert("error");
        });
}


function getBlock(distId,fieldId, matchId){
//debugger;
  $.get( contextPath+"/core/getData", { id: distId, identity: 'Block'} )
  .done(function( data ) {
	  var blk = data.data;
	  var html="<option value='0'>--- Select ---</option>";
    
    if (blk !== null) {
      blk.forEach(element => {
       var isSelected = element.blockId == matchId ? 'selected' : '';
//       html = html +  "<option value="+element.blockId+">" + element.blockNameEN + "</option>";
       html += "<option value='" + element.blockId + "'" + isSelected + ">" + element.blockNameEN + "</option>";
    });
    }

    $("#"+fieldId).empty().append(html);
    
  })
  .fail(function() {
	  $('#loader').addClass('hidden');
    alert( "error" );
  });
}

function getGramPanchayat(blockId,fieldId, matchId){
debugger;
  $.get( contextPath+"/core/getData", { id: blockId, identity: 'GP'} )
  .done(function( data ) {
	  var blk = data.data;
	  var html="<option value='0'>--- Select ---</option>";
    
    if (blk !== null) {
      blk.forEach(element => {
       var isSelected = element.gpId == matchId ? 'selected' : '';
//       html = html +  "<option value="+element.gpId+">" + element.gpNameEN + "</option>";
       html += "<option value='" + element.gpId + "'" + isSelected + ">" + element.gpNameEN + "</option>";
    });
    }

    $("#"+fieldId).empty().append(html);
    
  })
  .fail(function() {
	  $('#loader').addClass('hidden');
    alert( "error" );
  });
}

function getVillage(villageId,fieldId, matchId){
debugger;
  $.get( contextPath+"/core/getData", { id: villageId, identity: 'VILLAGE'} )
  .done(function( data ) {
	  var blk = data.data;
	  var html="<option value='0'>--- Select ---</option>";
    
    if (blk !== null) {
      blk.forEach(element => {
       var isSelected = element.villageId == matchId ? 'selected' : '';
//       html = html +  "<option value="+element.villageId+">" + element.villageNameEn + "</option>";
       html += "<option value='" + element.villageId + "'" + isSelected + ">" + element.villageNameEn + "</option>";
    });
    }

    $("#"+fieldId).empty().append(html);
    
  })
  .fail(function() {
	  $('#loader').addClass('hidden');
    alert( "error" );
  });
}

function getFrmByVlgId(villageId, fieldId, matchId){// get Farmer By Village Id
   debugger;
  $.get( contextPath+"/core/getData", { id: villageId, identity: 'FRMBYVILLAGE'} )
  .done(function( data ) {
	  var blk = data.data;
	  var html="<option value='0'>--- Select ---</option>";
    
    if (blk !== null) {
      blk.forEach(element => {
       var isSelected = element.farmerId == matchId ? 'selected' : '';
       html += "<option value='" + element.farmerId + "'" + isSelected + ">" + element.name + "</option>";
    });
    }

    $("#"+fieldId).empty().append(html);
    
  })
  .fail(function() {
	  $('#loader').addClass('hidden');
    alert( "error" );
  });
}
