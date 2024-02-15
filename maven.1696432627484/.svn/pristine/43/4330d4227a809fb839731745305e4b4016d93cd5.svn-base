
function createOrFetchRow(status){
	$("#formSubmit").addClass("hidden");
	$("#tableId1").find("tr:gt(0)").remove();

	$("#letterRcvDate").val("");
	var letterOrderNoCenter = $("#letterOrderNoCenter").val();
	var newEntry = $("#newOrder").val();
	var statusFlag = "";
	if(letterOrderNoCenter != "0"){
		if (letterOrderNoCenter != 'NEW' || newEntry.trim() != '') {
			if (newEntry.trim() == '') {
				$("#center").addClass("hidden");
				$("#newOrder").val('');
				$("#newOrder").removeAttr("name");
				$("letterOrderNoCenter").attr("name", "letterReceived.letterOrderNoCenter");
				statusFlag = "EXIST";
			} else {
				
				if(status == "NEW_LETTER"){
					letterOrderNoCenter = newEntry.trim();
					statusFlag = "NEWDATA";
				}
				if(status == "LETTER"){
					letterOrderNoCenter = letterOrderNoCenter;
					$("#center").addClass("hidden");
					$("#newOrder").val('');
					$("#newOrder").removeAttr("name");
				}
				
			}
			if (statusFlag == "NEWDATA") {
				bootbox.confirm({
					message: 'Kindly reverify the letter order no as once created then can not be changed latter. Do you want to confirm ?',
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
							letterRcvDataAjax(letterOrderNoCenter);
							$("#newOrder").prop("readonly", true);
							$("#aapId").addClass("frezz");
							$("#finId").addClass("frezz");
              				$("#subAapId").addClass("frezz");
							$("#letterOrderNoCenter").addClass("frezz");
						} else {
							$("#center").addClass("hidden");
							$("#newOrder").val('');
							$("#newOrder").removeAttr("name");
//							$("#letterOrderNoCenter").removeAttr("name");
							$("#letterOrderNoCenter").empty().append("<option value='0' >--select--</option>");
              $("#subAapId").empty().append("<option value='0' >--select--</option>");
							$("#aapId").val("0");
							$("#finId").val("0");
						}
					}
				});
			} else{
				letterRcvDataAjax(letterOrderNoCenter);
				$("#docId").addClass("hidden");
			}

		}
		else {
			$("#center").removeClass("hidden");
			$("#sanctionOrderNo").removeAttr("name");
			$("newOrder").attr("name", "fundReceived.sanctionOrderNo");
			$("#newOrder").prop("readonly", false);
			$("#docId").addClass("hidden");
//			if(newEntry.trim() == ''){
//				bootbox.alert("Letter order no can't be blank'");
//				return false;
//			}
		}
	
	}else{
		$("#center").addClass("hidden");
		$("#newOrder").val('');
		$("#newOrder").removeAttr("name");
		$("#docId").addClass("hidden");
	}

}
function validApproveAmount(index){
var amtDmd = parseFloat($("#amountDemanded_"+index).val());
var appAmt = parseFloat($("#sanctionedAmount_"+index).val());
//	parseFloat(amtDmd);
//	parseFloat(appAmt);
if(appAmt > amtDmd){
  bootbox.alert("Approved amount shouldn't be greater than demanded amount");
  $("#sanctionedAmount_"+index).val("0");
  return false;
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

function letterRcvDataAjax(letterOrderNoCenter){

  var format = new Intl.NumberFormat('en-US', {
		currency: 'INR',
		minimumFractionDigits: 2,
	});

		$("#tableId1").find("tr:gt(0)").remove();

	$("#letterRcvDate").val("");
  
	var fin = $("#finId").val();
	var aapCode = $("#aapId").val();
	var letterOrderNoCenter = letterOrderNoCenter;

  var addAap = $("#subAapId").val();
//	var newEntry = $("#newOrder").val();
	
	if(fin !="0"){
           $('#loader').removeClass('hidden');   
          $.get( contextPath+"/state/createLetterRow", { finYear: fin , aapCode : aapCode ,letterOrderNoCenter: letterOrderNoCenter,addAap : addAap} )
              .done(function( data ) {
				$('#loader').addClass('hidden');
              console.log(data);
              if(data.outcome){
                  $("#formSubmit").removeClass("hidden");

                  //set fund received part
                  $("#letterReceivedId").val(data.data.letterReceived.id);
                  $("#letterRcvCodeId").val(data.data.letterReceived.letterRcvCode);
                  $("#letterRcvDate").val(data.data.letterReceived.dateOfLetterReceived);
                  if(data.data.letterReceived.supportingDocument != null){
                     $("#docId").removeClass("hidden");
                     $("#docId").html("<i class='fa fa-download'></i>");
                  }

                

                  data.data.letterFundList.forEach((elm,index) => {

                  var totalRows=$('#tableId1 tr').length-1;
                  var table = document.getElementById("tableId1");
                  var row = table.insertRow(totalRows + 1);

                  // var cell1 = row.insertCell(0);
                  var cell2 = row.insertCell(0);
                  var cell3 = row.insertCell(1);
                  var cell4 = row.insertCell(2);
                  var cell5 = row.insertCell(3);
//                    var cell6 = row.insertCell(4);

                  var dynoId=totalRows-2;


                  var cellVal1 = '<input type="hidden" class="form-control form-control-sm"  name="letterFundList['+index+'].id" value="'+elm.id+'" ><input type="hidden" class="form-control form-control-sm"  name="letterFundList['+index+'].actLetterCode" value="'+elm.actLetterCode+'" ><input type="text" width=100% class="form-control form-control-sm " style="width:100%;" id="cmpNameEn_'+index+'" name="letterFundList['+index+'].componentCode.cmpNameEn" value="'+elm.componentCode.cmpNameEn+'" readonly>';
                    var cellVal2 =  '<input type="hidden" width=100% class="form-control form-control-sm " style="width:100%;" id="actNameEn_'+index+'" name="letterFundList['+index+'].actCode.actCode" value="'+elm.actCode.actCode+'" ><input type="text" width=100% class="form-control form-control-sm " style="width:100%;" id="actNameEn_'+index+'" name="letterFundList['+index+'].actCode.actNameEn" value="'+elm.actCode.actNameEn+'" readonly>';
                  var cellVal3 = '<input type="text"  class="form-control form-control-sm NumbersOnly" style="width:100%;" name="letterFundList['+index+'].demandedAmount" readonly id="amountDemanded_'+index+'" value="'+format.format(elm.demandedAmount)+'" >';
                  // var cellVal4 = '<input type="text"  class="form-control form-control-sm "   onchange="validApproveAmount('+index+')"style="width:100%;" name="letterFundList['+index+'].sanctionedAmount"  id="sanctionedAmount_'+index+'" value="'+( elm.sanctionedAmount == null ? 0.0 : elm.sanctionedAmount ) +'" >';
                    var cellVal4 = '<input type="text" class="form-control form-control-sm" style="width:100%;"  name="letterFundList['+index+'].sanctionedAmount" id="sanctionedAmount_'+index+'" maxlength="10" value="'+format.format( elm.sanctionedAmount == null ? 0.0 : elm.sanctionedAmount ) +'" autocomplete="off">';
//                  var cellVal5 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="letterFundList['+index+'].receivedAmount" id="receiveAmount_'+index+'" value="'+( elm.receivedAmount == null ? 0.0 : elm.receivedAmount ) +'" >';

//					
        
                  // cell1.innerHTML = ""+(index+1)+"";

                  cell2.innerHTML = ""+cellVal1+"";

                  cell3.innerHTML = ""+cellVal2+"";

                  cell4.innerHTML = ""+cellVal3+"";
                  
                  cell5.innerHTML = ""+cellVal4+"";

//                    cell6.innerHTML = ""+cellVal5+"";
                 $("#sanctionedAmount_"+index).attr("onkeyup","DoublePercent('sanctionedAmount_"+index+"')");
                // $("#sanctionedAmount_"+index).attr("onchange","currencyConverter(this.value,'sanctionedAmount_"+index+"')");
                $("#sanctionedAmount_"+index).attr("onchange","currencyConverter(this.value,'sanctionedAmount_"+index+"');getAmountReceived("+index+","+( elm.sanctionedAmount == null ? 0.0 : elm.sanctionedAmount ) +")");
                $.getScript(contextPath+"/assets/appJs/validation/common-utils.js");

                      });
                  }else{
                    bootbox.alert(data.message+" or else please choose all mandotory fields");
                    $("#aapId").val("0");
                    $("#finId").val("0");
                    $("#letterOrderNoCenter").empty().append("<option value='0' >--select--</option>");
                    $("#center").addClass("hidden");
		  			$("#newOrder").val('');
		  			$("#aapId").removeClass("frezz");
					$("#finId").removeClass("frezz");
					$("#letterOrderNoCenter").removeClass("frezz");
          			$("#subAapId").removeClass("frezz");
          			$("#subAapId").empty().append("<option value='0' >--select--</option>");

                  }

              })
              .fail(function (exception) {
                  $("#formSubmit").addClass("hidden");
                  // Our error logic here
                  $('#loader').addClass('hidden');
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


