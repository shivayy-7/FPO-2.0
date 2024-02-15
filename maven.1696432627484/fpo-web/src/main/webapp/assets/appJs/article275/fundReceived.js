
function createOrFetchRow(status){

	$("#formSubmit").addClass("hidden");
	// reset table
	//$("#tableId1 tr").remove();
	$("#tableId1").find("tr:gt(0)").remove();

	$("#fndRcvDate").val("");

	var sanctionOrderNo = $("#sanctionOrderNo").val();
	var newEntry = $("#newOrder").val();
  	var statusFlag = "NEWDATA";
  	
  	if(sanctionOrderNo != "0"){
			if (sanctionOrderNo != 'NEW' || newEntry.trim() != '') {
				if (newEntry.trim() == '') {
					$("#center").addClass("hidden");
					$("#newOrder").val('');
					$("#newOrder").removeAttr("name");
					$("sanctionOrderNo").attr("name", "fundReceived.sanctionOrderNo");
					statusFlag = "EXIST";
				} else {
					if (status == "NEW_CENTER") {
						sanctionOrderNo = newEntry.trim();
						statusFlag = "NEWDATA";
					}
					if (status == "CENTER") {
						sanctionOrderNo = sanctionOrderNo;
						$("#center").addClass("hidden");
						$("#newOrder").val('');
						$("#newOrder").removeAttr("name");
					}
//					sanctionOrderNo = newEntry;
				}
				if (statusFlag == "NEWDATA") {
					bootbox.confirm({
						message: 'Kindly reverify the sanction order no as once created it can not be changed later. Do you want to confirm ?',
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
								fundRcvDataAjax(sanctionOrderNo);
								$("#newOrder").prop("readonly", true);
								$("#aapId").addClass("frezz");
								$("#finId").addClass("frezz");
								$("#headId").addClass("frezz");
								$("#sanctionOrderNo").addClass("frezz");
								$("#subAapId").addClass("frezz");
								
							} else {
								$("#center").addClass("hidden");
								$("#newOrder").val('');
								$("#newOrder").removeAttr("name");
								$("#sanctionOrderNo").empty().append("<option value='0' >--select--</option>");
								$("#aapId").val("0");
								$("#finId").val("0");
								$("#headId").val("0");
							}
						}
					});
				} else {
					fundRcvDataAjax(sanctionOrderNo);
					$("#docId").addClass("hidden");
				}
			} else {
				$("#center").removeClass("hidden");
				$("#sanctionOrderNo").removeAttr("name");
				$("newOrder").attr("name", "fundReceived.sanctionOrderNo");
				$("#newOrder").prop("readonly", false);
				$("#docId").addClass("hidden");
			}
  	}else{
			$("#center").addClass("hidden");
			$("#docId").addClass("hidden");
			$("#newOrder").val('');
			$("#newOrder").removeAttr("name");
//			getSanctionOrderNo('MAIN');
  	}

}


function getActivityListByHeadCode(headCode,dynoId) {
    $("#activity_"+dynoId).empty().append("<option value='NA'>--- Select Activity ---</option>");
    $("#subActivity_"+dynoId).empty().append("<option value='NA'>--- Select Sub Activity ---</option>");

    let html="<option value='NA'>--- Select Activity ---</option>";
$('#loader').removeClass('hidden');
$.get( contextPath+"/articleAjax/getActivityList", { headCode: headCode } )
  .done(function( data ) {
	$('#loader').addClass('hidden');
    //make a for loop for data
    data.forEach(element => {
        html = html + "<option value='"+element.actCode+"'>"+element.actNameEn+"</option>";
    });

    $("#activity_"+dynoId).empty().append(html);


  })
  .fail(function() {
	$('#loader').addClass('hidden');
    alert( "error" );
  });
}

function getSubActivityListByActCode(actCode,dynoId) {

    $("#subActivity_"+dynoId).empty().append("<option value='NA'>--- Select Sub Activity ---</option>");

    let html="<option value='NA'>--- Select Sub Activity ---</option>";
	$('#loader').removeClass('hidden');
$.get( contextPath+"/articleAjax/getSubActivityList", { actCode: actCode } )
  .done(function( data ) {
	$('#loader').addClass('hidden');
    //make a for loop for data
    data.forEach(element => {
        html = html + "<option value='"+element.subActCode+"'>"+element.subActNameEn+"</option>";
    });

    $("#subActivity_"+dynoId).empty().append(html);
    $("#physicalUnit_"+dynoId).val();

  })
  .fail(function() {
	$('#loader').addClass('hidden');
    alert( "error" );
  });
}

function getPhysicalUnitBySubActCode(subActCode,dynoId) {
if(subActCode != 'NA'){
	$('#loader').removeClass('hidden');
    $.get( contextPath+"/articleAjax/getPhysicalUnitBySubActCode", { subActCode: subActCode } )
    .done(function( data ) {
	$('#loader').addClass('hidden');
      //make a for loop for data

      $("#physicalUnit_"+dynoId).empty().val(data);
  })
  .fail(function() {
	$('#loader').addClass('hidden');
    alert( "error" );
  });
}else{
    $("#physicalUnit_"+dynoId).empty().val("");
}


}


function updateFormData(){
	var post_url = $("#aapData").attr("action"); //get form action url
	var form_data = $("#aapData").serialize(); //Encode form elements for submission
	console.log(form_data);
	$.post( post_url, form_data)
        .done(function( data ) {
        //make a for loop for data
        console.log(data);
        bootbox.alert(data.message);
      })
      .fail(function() {
        alert( "error" );
      });

};


function fundRcvDataAjax(sanctionOrderNo){

	var format = new Intl.NumberFormat('en-US', {
		currency: 'INR',
		minimumFractionDigits: 2,
	});

	$("#tableId1").find("tr:gt(0)").remove();

	$("#fndRcvDate").val("");

	var fin = $("#finId").val();
	var aapCode = $("#aapId").val();
	var headCode = $("#headId").val();
	var sanctionOrderNo = sanctionOrderNo;
//	var newEntry = $("#newOrder").val();
	var addAap = $("#subAapId").val();
	if (fin != "0") {
		$('#loader').removeClass('hidden');
		$.get(contextPath + "/state/createRow", { finYear: fin, aapCode: aapCode, headCode: headCode, sanctionOrderNo: sanctionOrderNo,addAap : addAap })
			.done(function(data) {
				$('#loader').addClass('hidden');
				console.log(data);
				if (data.outcome) {
					$("#formSubmit").removeClass("hidden");

					//set fund received part
					$("#fundReceivedId").val(data.data.fundReceived.id);
					$("#fundRcvCodeId").val(data.data.fundReceived.fundRcvCode);
					$("#fndRcvDate").val(data.data.fundReceived.dateOfFundReceived);
					if (data.data.fundReceived.supportingDocument != null) {
						$("#docId").removeClass("hidden");
						$("#docId").html("<i class='fa fa-download'></i>");
					}
					// $("#sanctionOrderNo").val(data.data.fundReceived.sanctionOrderNoCenter);



					data.data.activityFundList.forEach((elm, index) => {

						var totalRows = $('#tableId1 tr').length - 1;
						var table = document.getElementById("tableId1");
						var row = table.insertRow(totalRows + 1);

						// var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(0);
						var cell3 = row.insertCell(1);
						var cell4 = row.insertCell(2);
						var cell5 = row.insertCell(3);
						var cell6 = row.insertCell(4);

						var dynoId = totalRows - 2;


						var cellVal1 = '<input type="hidden" class="form-control form-control-sm"  name="activityFundList[' + index + '].id" value="' + elm.id + '" ><input type="hidden" class="form-control form-control-sm"  name="activityFundList[' + index + '].actFundCode" id="actFundCode_' + index + '" value="' + elm.actFundCode + '" ><input type="text" width=100% class="form-control form-control-sm " style="width:100%;" id="cmpNameEn_' + index + '" name="activityFundList[' + index + '].componentCode.cmpNameEn" value="' + elm.componentCode.cmpNameEn + '" readonly>';
						var cellVal2 = '<input type="hidden" width=100% class="form-control form-control-sm " style="width:100%;" id="actNameEn_' + index + '" name="activityFundList[' + index + '].actCode.actCode" value="' + elm.actCode.actCode + '" ><input type="text" width=100% class="form-control form-control-sm " style="width:100%;" id="actNameEn_' + index + '" name="activityFundList[' + index + '].actCode.actNameEn" value="' + elm.actCode.actNameEn + '" readonly>';
						var cellVal3 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="activityFundList[' + index + '].demandedAmount" readonly id="amountDemanded_' + index + '" value="' + format.format(elm.demandedAmount) + '" >';
						var cellVal4 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="activityFundList[' + index + '].sanctionedAmount" id="sanctionedAmount_' + index + '" value="' + format.format(elm.sanctionedAmount == null ? 0.0 : elm.sanctionedAmount) + '" readonly>';
						var cellVal5 = '<input type="text" class="form-control form-control-sm " autocomplete="off" style="width:100%;" name="activityFundList[' + index + '].receivedAmount" id="receiveAmount_' + index + '" value="' + format.format(elm.receivedAmount == null ? 0.0 : elm.receivedAmount) + '" >';
						var cellVal5 = '<input type="text" class="form-control form-control-sm" autocomplete="off" maxlength="10" onchange = "getAmountReceived(' + index + ',' + (elm.receivedAmount == null ? 0.0 : elm.receivedAmount) + ')" style="width:100%;" name="activityFundList[' + index + '].receivedAmount" id="receiveAmount_' + index + '" value="' + format.format(elm.receivedAmount == null ? 0.0 : elm.receivedAmount) + '" >';


						// cell1.innerHTML = ""+(index+1)+"";

						cell2.innerHTML = "" + cellVal1 + "";

						cell3.innerHTML = "" + cellVal2 + "";

						cell4.innerHTML = "" + cellVal3 + "";

						cell5.innerHTML = "" + cellVal4 + "";

						cell6.innerHTML = "" + cellVal5 + "";

						$("#receiveAmount_" + index).attr("onkeyup", "DoublePercent('receiveAmount_" + index + "')");
						$("#receiveAmount_"+index).attr("onchange","currencyConverter(this.value,'receiveAmount_"+index+"');getAmountReceived("+index+","+( elm.receivedAmount == null ? 0.0 : elm.receivedAmount ) +")");
						$.getScript(contextPath + "/assets/appJs/validation/common-utils.js");



					});
				} else {
					bootbox.alert(data.message);
					$("#aapId").val("0");
					$("#finId").val("0");
					$("#headId").val("0");
					$("#newOrder").val("");
					$("#sanctionOrderNo").empty().append("<option value='0' >--select--</option>");
					$("#center").addClass("hidden");
					$("#aapId").removeClass("frezz");
					$("#finId").removeClass("frezz");
					$("#headId").removeClass("frezz");
					$("#sanctionOrderNo").removeClass("frezz");
					$("#subAapId").removeClass("frezz");
				}

			})
			.fail(function(exception) {
				$("#formSubmit").addClass("hidden");
				$('#loader').addClass('hidden');
				// Our error logic here
				console.log(exception.responseText);
				// if (jqXHR.status === 0) {
				//     msg = 'Not connect.\n Verify Network.';
				// } else if (jqXHR.status == 404) {
				//     msg = 'Requested page not found. [404]';
				// } else if (jqXHR.status == 500) {
				//     msg = 'Internal Server Error [500].';
				// } else if (exception === 'parsererror') {
				//     msg = 'Requested JSON parse failed.';
				// } else if (exception === 'timeout') {
				//     msg = 'Time out error.';
				// } else if (exception === 'abort') {
				//     msg = 'Ajax request aborted.';
				// } else {
				//     msg = 'Uncaught Error.\n' + jqXHR.responseText;
				// }
			});


		//     }else{
		//         $("#finId").val("0");
		//         $("#formSubmit").addClass("hidden");
		//     }
		// }});
	}

}


