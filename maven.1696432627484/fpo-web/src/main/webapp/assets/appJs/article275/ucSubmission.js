function createOrFetchRow(status){


   $("#tableId1").find("tr:gt(0)").remove();

   $("#fndRcvDate").val("");
   
    var statusFlag = "";
    var fin = $("#finId").val();
    var aapCode = $("#aapTypeId").val();
    var ucSubNo = $("#ucSubNo").val();
    var newEntry = $("#newOrder").val();

     if(ucSubNo != "0"){
          if (ucSubNo != 'NEW' || newEntry.trim() != '') {
              if (newEntry.trim() == '') {
                  $("#ucSubNew").addClass("hidden");
                  $("#newOrder").val('');
                  $("#newOrder").removeAttr("name");
                  $("ucSubNo").attr("name", "ucReceived.ucRcvCode");
                  statusFlag = "EXIST";
              } else {
                  if (status == "NEW_UC") {
                      ucSubNo = newEntry;
                      statusFlag = "NEWDATA";
                      $("#ucSubmit").attr("disabled", false);
                        $("#ucSubmit").addClass("hidden");
                  }
                  if (status == "UC") {
                      ucSubNo = ucSubNo;
                      $("#ucSubNew").addClass("hidden");
                      $("#newOrder").val('');
                      $("#newOrder").removeAttr("name");
                      $("#ucSubmit").attr("disabled", false);
                      $("#ucSubmit").addClass("hidden");
                  }
              }
              if (statusFlag == "NEWDATA") {
                  bootbox.confirm({
                      message: 'Kindly reverify the UC submission no as once created it can not be changed later. Do you want to confirm ?',
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
                      callback: function(result) {
                          if (result == true) {
                              ucSubmissionDataAjax(ucSubNo);
                              $("#newOrder").prop("readonly", true);
                              $("#aapId").addClass("frezz");
                              $("#finId").addClass("frezz");
                              $("#ucSubNo").addClass("frezz");
                          }else{
                              var html = "<option value='0'>--Select--<option>";
                              $("#ucSubNew").addClass("hidden");
                              $("#newOrder").val('');
                              $("#newOrder").removeAttr("name");
                              $("#finId").val("0");
                              $("#aapId").val("0");
                              $("#ucSubNo").empty().append("<option value='0' >--select--</option>");
                          }
                      }
                  });
              } else {
                ucSubmissionDataAjax(ucSubNo);
              }

          } else {
              $("#ucSubNew").removeClass("hidden");
              $("#newOrder").val('');
              $("#ucSubNo").removeAttr("name");
              $("#newOrder").attr("name", "ucReceived.ucRcvCode");
              $("#newOrder").prop("readonly", false);
              $("#ucSubmit").attr("disabled", false);
              $("#ucSubmit").addClass("hidden");
          }

     }else{
          $("#ucSubNew").addClass("hidden");
          $("#newOrder").val('');
          $("#newOrder").removeAttr("name");
          $("ucSubNo").attr("name", "ucReceived.ucRcvCode");
          $("#ucSubmit").attr("disabled", false);
          $("#ucSubmit").addClass("hidden");
     }

}


function ucSubmissionDataAjax(ucSubNo){

    var format = new Intl.NumberFormat('en-US', {
		currency: 'INR',
		minimumFractionDigits: 2,
	});

    var fin = $("#finId").val();
    var aapCode = $("#aapId").val();
    var newEntry = $("#newOrder").val();

    if (fin != "0") {
         $('#loader').removeClass('hidden');
        $.get(contextPath + "/dist/createRowForUcSubmission", { finYear: fin, aapCode: aapCode, ucRcvCode: ucSubNo})
            .done(function(data) {
                 $('#loader').addClass('hidden');;
                console.log(data);
                if (data.outcome) {
                    $("#ucSubmit").removeClass("hidden");
/*                    if (data.data.ucReceived.supportingDocument != null) {
						$("#docId").removeClass("hidden");
						$("#docId").html("Download");
					}*/
                    data.data.ucSubmissionList.forEach((elm, index) => {

                        var totalRows = $('#tableId1 tr').length -1;
                        var table = document.getElementById("tableId1");
                        var row = table.insertRow(totalRows + 1);

                        var cell1 = row.insertCell(0);
                        var cell2 = row.insertCell(1);
                        var cell3 = row.insertCell(2);
                        var cell4 = row.insertCell(3);
                        var cell5 = row.insertCell(4);
                        var cell6 = row.insertCell(5);
                        var cell7 = row.insertCell(6);
                        var cell8 = row.insertCell(7);

                        var dynoId = totalRows ;

                        var cellVal1 = '<input type="hidden" class="form-control form-control-sm"  name="ucSubmissionList[' + index + '].id" value="' + elm.id + '" ><input type="hidden" class="form-control form-control-sm"  name="ucSubmissionList[' + index + '].ucSbmtCode" value="' + elm.ucSbmtCode + '" ><input type="text" width=100% class="form-control form-control-sm " style="width:100%;" readonly name = "ucSubmissionList[' + index + '].sanctionOrderNo" value="' + elm.sanctionOrderNo + '">';
                        var cellVal2 = '<input type="hidden" class="form-control form-control-sm"  name="ucSubmissionList[' + index + '].cmpCode.cmpCode" value="' + elm.cmpCode.cmpCode + '" ><input type="text" class="form-control form-control-sm"  name="ucSubmissionList[' + index + '].cmpCode.cmpNameEn" value="' + elm.cmpCode.cmpNameEn + '" readonly>';
                        var cellVal3 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="ucSubmissionList[' + index + '].ucToBeSbmt" id="ucToBeSbmt_' + index + '" value="' + format.format(elm.ucToBeSbmt) + '" readonly>';
                        var cellVal4 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="ucSubmissionList[' + index + '].expTillDate" id="expTillDate_' + index + '" value="' + format.format(elm.expTillDate) + '" readonly>';
                        var cellVal5 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="ucSubmissionList[' + index + '].ucSbmtTillDate" id="ucSbmtTillDate_' + index + '" value="' + format.format(elm.ucSbmtTillDate) + '" readonly >';
                        var cellVal6 = '<input type="text" class="form-control form-control-sm NumbersOnly" maxlength="7" autocomplete = "off" style="width:100%;" name="ucSubmissionList[' + index + '].blncUcToBeSbmt" id="blncUcToBeSbmt_' + index + '"  value="' + format.format(elm.blncUcToBeSbmt) + '" readonly>';
                        var cellVal7 = '<input type="text" class="form-control form-control-sm" style="width:100%;" name="ucSubmissionList[' + index + '].ucEntry" maxlength="10" id="ucEntry_' + index + '"  value="' + format.format(elm.ucEntry) + '" '+(elm.expTillDate == 0 ? 'readonly' : '')+'>'  ;
                        var cellVal8 = '<div class="dwn-at"><input type="file" class="form-control form-control-sm" style="width:100%;" name="ucSubmissionList[' + index + '].ucDocWeb" id="ucDoc_' + index + '"  value="" '+(elm.expTillDate == 0 ? 'disabled' : '')+'><a href="#" class="dwn-at" id="down_'+index+'"><i class="fa fa-download"></i></</a></div>';


                        cell1.innerHTML = "" + cellVal1 + "";

                        cell2.innerHTML = "" + cellVal2 + "";

                        cell3.innerHTML = "" + cellVal3 + "";

                        cell4.innerHTML = "" + cellVal4 + "";

                        cell5.innerHTML = "" + cellVal5 + "";

                        cell6.innerHTML = "" + cellVal6 + "";

                        cell7.innerHTML = "" + cellVal7 + "";

                        cell8.innerHTML = "" + cellVal8 + "";

                         $("#ucEntry_" + index).attr("onchange", "currencyConverter(this.value,'ucEntry_"+index+"');getValidUc("+ index +")");
                        $.getScript(contextPath + "/assets/appJs/validation/common-utils.js");
                        if(elm.ucDoc != 'NA'){
                            $('#down_'+index+'').attr("href",contextPath+"/articleDnd/download/"+elm.id+"/UC_SUBMISSION");
                        }

                        // cell6.innerHTML = "" + cellVal6 + "";
                        if(elm.ucRcvCode.isSubmitted == true){
                            $("#ucEntry_" + index).attr("readonly", true);
                            $("#ucDoc_" + index).attr("readonly", true);
                            $("#ucSubmit").attr("disabled", true);
                        }else{
                      
                            $("#ucSubmit").attr("disabled", false);
                        }

                    });
                } else {
                    bootbox.alert(data.message);
                    $("#finId").val("0");
                    $("#aapId").val("0");
                    
                }

            })
            .fail(function(exception) {
                $("#ucSubmit").addClass("hidden");
                
                console.log(exception.responseText);
            });

    }
}