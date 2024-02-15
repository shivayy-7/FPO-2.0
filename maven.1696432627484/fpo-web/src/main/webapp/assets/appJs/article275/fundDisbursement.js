

function ff(){

   

   if(fin !="0"){
        // bootbox.confirm({"message":"Are you sure you want to generate new AAP(Aanual Action Plan)?","callback":function(result){
        //     if(result){
         $('#loader').removeClass('hidden');
                    $.get( contextPath+"/aap/createRow", { fnYear: fin , aapType : aapType , methodCall : actionTaken , configCode :  $("#agencyUser").val() } )
                        .done(function( data ) {
                         $('#loader').addClass('hidden');;
                        console.log(data);
                        if(data.outcome){
                            $("#formSubmit").removeClass("hidden");
                            $("#configCode").val(data.data.configCode);

                            data.data.agencyVOList.forEach((elm,index) => { 
                           
                                var initialIndexCount=5;
                                var componentList='<option value="NA">Select Component</option>';
                                var ActivityList='<option value="NA">Select Activity</option>';
                                var subAcdtivityList='<option value="NA">Select Sub Activity</option>'; 
                                var activitySeasonList='<option value="NA">Select Activity Season </option>';
                                var pAreaList='<option value="NA">Select Project Area</option>';
                                var dynoCells = [];
                                var dynoCellValue = [];

                            var totalRows=$('#tableId1 tr').length-1;
                            var table = document.getElementById("tableId1");
                            var row = table.insertRow(totalRows + 1);
                            row.className="dataCollector";
                            var cell1 = row.insertCell(0);
                            var cell2 = row.insertCell(1);
                            var cell3 = row.insertCell(2);
                            var cell4 = row.insertCell(3);
                            var cell5 = row.insertCell(4);
                            var cell6 = row.insertCell(5);
                            

                            var dynoId=totalRows-2;


                            //make for each loop with index for fnList
                            // data.data.fnList.forEach((element,index) => {
                            //     initialIndexCount++;
                            //     var element.fnCode = row.insertCell(initialIndexCount);
                            //     console.log(element.fnCode);
                            // });

                            
                            for (var i = 0; i <= data.data.fnList.length; ++i) {
                                initialIndexCount++;
                                if(i != data.data.fnList.length){
                                dynoCells[i] = row.insertCell(initialIndexCount);
                                dynoCells[i+1] = row.insertCell(initialIndexCount+1);
                                }
                            }

                            //make for each loop  for fnHeadList

                            data.data.componentList.forEach(element =>{
                                if(elm.cmpCode == null){
                                    componentList=componentList+"<option value='"+element.cmpCode+"' >"+element.cmpNameEn+"</option>";
                                }else{
                                    componentList=componentList+"<option value='"+element.cmpCode+"' "+(elm.cmpCode.cmpCode == element.cmpCode ? 'selected' : '') +"  >"+element.cmpNameEn+"</option>";
                                }
                            });

                            //make for each loop  for activityList
                            if(elm.actList !=null){
                                elm.actList.forEach(element => {
                                    if(elm.actCode == null){
                                    ActivityList=ActivityList+"<option value='"+element.actCode+"' >"+element.actNameEn+"</option>";
                                    }else{
                                         ActivityList=ActivityList+"<option value='"+element.actCode+"' "+(elm.actCode.actCode == element.actCode ? 'selected' : '') +"  >"+element.actNameEn+"</option>";    
                                    }
                                });
                                if(elm.subActList !=null){
                                    elm.subActList.forEach(element => {
                                        if(elm.subActCode == null){
                                        subAcdtivityList=subAcdtivityList+"<option value='"+element.subActCode+"' >"+element.subActNameEn+"</option>";
                                        }else{
                                        subAcdtivityList=subAcdtivityList+"<option value='"+element.subActCode+"' "+(elm.subActCode.subActCode == element.subActCode ? 'selected' : '') +"  >"+element.subActNameEn+"</option>";
                                       
                                    }
                                    });
                                }
                            }


                           



                            

                            
                         
                            
                            var cellVal1 = '<input type="hidden" class="form-control form-control-sm " name="agencyVOList['+dynoId+'].aapCode" value="'+elm.aapCode+'"><select class="form-control form-control-sm dupCheck2 validCheck frezz" readonly id="cmp_'+dynoId+'" name="agencyVOList['+dynoId+'].cmpCode.cmpCode"  onchange="getActivityListByCmpCode(this.value,'+dynoId+')" >'+componentList+'</select>';
                            var cellVal2 = '<select class="form-control form-control-sm dupCheck2 validCheck frezz"  readonly id="activity_'+dynoId+'" name="agencyVOList['+dynoId+'].actCode.actCode" onchange="getSubActivityListByActCode(this.value,'+dynoId+')" >'+ActivityList+'</select>';
                            var cellVal3 = '<select class="form-control form-control-sm dupCheck2 validCheck frezz" readonly id="subActivity_'+dynoId+'" name="agencyVOList['+dynoId+'].subActCode.subActCode" onchange="getPhysicalUnitBySubActCode(this.value,'+dynoId+')" >'+subAcdtivityList+'</select>';
                            var cellVal4 = '<input type="text" class="form-control form-control-sm frezz" readonly  id="physicalUnit_'+dynoId+'" name="agencyVOList['+dynoId+'].physicalUnit"  value="'+elm.physicalUnit+'" readonly autocomplete="off">';
                            // var cellVal7 = '<select class="form-control form-control-sm select2-multiple" multiple="multiple" id="cluster_'+dynoId+'" > <option value="FFFF">Select</option><option value="fdfdf">Select</option> </select>';

                            var cellVal5 = '<input type="text" class="form-control form-control-sm frezz" readonly id="unitNo_'+dynoId+'" name="agencyVOList['+dynoId+'].noPerUnits" value="'+elm.noPerUnits+'" autocomplete="off">';
                           
                            data.data.fnList.forEach((element,ind) => { 
                                let fValue = elm.planList[ind].fnValue == null ? '0' : elm.planList[ind].fnValue;
                                dynoCellValue[ind] = '<input type="hidden"  name="agencyVOList['+dynoId+'].planList['+ind+'].planCode" value="'+elm.planList[ind].planCode+'"><input type="text" class="form-control form-control-sm frezz" readonly  name="agencyVOList['+dynoId+'].planList['+ind+'].fnValue" value="'+fValue+'" autocomplete="off">';  
                            
                                let disbursedValue = elm.planList[ind].disbursedValue == null ? '0' : elm.planList[ind].disbursedValue;
                                dynoCellValue[ind+1] = '<input type="text" class="form-control form-control-sm "  id="'+element.fnCode+'_'+dynoId+'" name="agencyVOList['+dynoId+'].planList['+ind+'].disbursedValue" value="'+disbursedValue+'" autocomplete="off">'; 
                            });


                            cell1.innerHTML = ""+(index+1)+"";
                            
                            cell2.innerHTML = ""+cellVal1+"";
                            
                            cell3.innerHTML = ""+cellVal2+"";
                            
                            cell4.innerHTML = ""+cellVal3+"";
                            
                            cell5.innerHTML = ""+cellVal4+"";
                            
                            cell6.innerHTML = ""+cellVal5+"";

                           

                            //make for loop with index for dynoCells

                             
                            for(var i=0;i<dynoCells.length;i++){
                                    dynoCells[i].innerHTML = ""+dynoCellValue[i]+"";
                            }
                            // $.getScript("${contextPath}/assets/appJs/validation/common-utils.js");
                     
                                 });
                            }
                          
                        })
                        .fail(function (exception) {
                            $("#formSubmit").addClass("hidden");
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

function updateFormData(){
	var post_url = $("#aapData").attr("action"); //get form action url
	var form_data = $("#aapData").serialize(); //Encode form elements for submission
	console.log(form_data);
	$.post( post_url, form_data) 
        .done(function( data ) {
        //make a for loop for data 
        console.log(data);
        bootbox.alert(data.message);
        createRow('FETCH-DISBURSEMENT');

      })
      .fail(function() {
        alert( "error" );
      });
    }

    function removeRows(){
        var totalRows=$('#tableId1 tr').length-1;
        var table = document.getElementById("tableId1");
        var rowCount = table.rows.length;
        for(var i=rowCount-1; i>0; i--){
            if(i>2){
                table.deleteRow(i);
            }
        }
    }


    function getAgencyMaster(that,selectedVal) {
        var typeCode = that;
         $('#loader').removeClass('hidden');
        $.ajax({
            url : contextPath+'/articleAjax/getAgencyMaster',
            type : 'GET',
            data : ({
                typeCode : typeCode
            }),
            success : function(response) {
             $('#loader').addClass('hidden');
                var html=" <option value='0' >--select--</option>";
                var data = response.data;
                var selectData = '';
                $.each(data, function(key, value) {
                     selectData = (value.agencyCode == selectedVal) ? "selected" : "";
                     html += "<option value='"+value.agencyCode+"' "+selectData+">"+value.agencyNameEn+"</option>";
                 });
                $("#agencyCode").empty().append(html);
            },
            error : function(error) {
				$('#loader').addClass('hidden');
                 bootbox.alert(error);
            }
        });
        
    }
    
    function getAgencyListByMasterCode(masterCode) {
     $('#loader').removeClass('hidden');
        $.ajax({
            url : contextPath+'/articleAjax/getAgencyListByMasterCode',
            type : 'GET',
            data : ({
                agencyMasterCode : masterCode
            }),
            success : function(response) {
             $('#loader').addClass('hidden');
                var html=" <option value='0' >--select--</option>";
                var data = response.data;
                var selectData = '';
                $.each(data, function(key, value) {
                     html += "<option value='"+value.agencyRegistrationCode+"'>"+value.agencyRegistrationNameEn+"</option>";
                 });
                $("#agencyUser").empty().append(html);
            },
            error : function(error) {
				$('#loader').addClass('hidden');
                 bootbox.alert(error);
            }
        });
        
    }
    
    
    


    function createOrFetchRow(status){

        // reset table
      

		$("#formSubmit").addClass("hidden");
       $("#tableId1").find("tr:gt(0)").remove();

       $("#disburseDate").val("");
       
         
		var sanctionOrderNo = $("#sanctionOrderNoState").val();
		var newEntry = $("#newOrder").val();
     	var statFlag = "";
     	if(sanctionOrderNo != "0"){
			  if (sanctionOrderNo != 'NEW' || newEntry.trim() != '') {
				  if (newEntry.trim() == '') {
					  $("#center").addClass("hidden");
					  $("#newOrder").val('');
					  $("#newOrder").removeAttr("name");
					  $("sanctionOrderNoState").attr("name", "sanctionOrderState");
					  statFlag = "EXIST";
				  } else {
//					  sanctionOrderNo = newEntry;
//					  statFlag = "NEWDATA";
					  if (status == "NEW_STATE") {
						  sanctionOrderNo = newEntry.trim();
						  statFlag = "NEWDATA";
					  }
					  if (status == "STATE") {
						  sanctionOrderNo = sanctionOrderNo;
						  $("#center").addClass("hidden");
						  $("#newOrder").val('');
						  $("#newOrder").removeAttr("name");
					  }
				  }
				  if (statFlag == "NEWDATA") {
					  bootbox.confirm({
						  message: 'Kindly re verify the sanction order no as once created it can not be changed later. Do you want to confirm ?',
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
								  fundDisburseDataAjax(sanctionOrderNo);
								  $("#newOrder").prop("readonly", true);
								  $("#aapTypeId").addClass("frezz");
								  $("#finId").addClass("frezz");
								  $("#agencyTypeCode").addClass("frezz");
								  $("#agencyCode").addClass("frezz");
								  $("#cmpId").addClass("frezz");
								  $("#activity").addClass("frezz");
								  $("#centerId").addClass("frezz");
								   $("#sanctionOrderNoState").addClass("frezz");
							  }else{
							  	  var html = "<option value='0'>--Select--<option>";
								  $("#center").addClass("hidden");
								  $("#newOrder").val('');
								  $("#newOrder").removeAttr("name");
//								  $("#sanctionOrderNo").empty().append(html);
								  $("#finId").val("0");
								  $("#aapTypeId").val("0");
								  $("#agencyCode").val("0");
								  $("#agencyTypeCode").val("0");
								  $("#cmpId").val("0");
								  $("#activity").val("0");
								  $("#centerId").val("0");
								  $("#sanctionOrderNoState").val("0");
								  $("#actPopData").addClass("hidden");
							  }
						  }
					  });
				  } else {
					  fundDisburseDataAjax(sanctionOrderNo);
					  $("#docId").addClass("hidden");
				  }

			  } else {
				  $("#center").removeClass("hidden");
				  $("#newOrder").val('');
				  $("#sanctionOrderNoState").removeAttr("name");
				  $("#newOrder").attr("name", "sanctionOrderState");
				  $("#newOrder").prop("readonly", false);
				  $("#docId").addClass("hidden");
//				  $("#sanctionOrderNo").prop("disabled", false);
			  }
     	
     	}else{
			  $("#center").addClass("hidden");
			  $("#newOrder").val('');
			  $("#newOrder").removeAttr("name");
			  $("sanctionOrderNoState").attr("name", "sanctionOrderState");
     		  $("#docId").addClass("hidden");
     		  $("#formSubmit").addClass("hidden");
     	}

 }



    function submitForm(){
        bootbox.confirm("Do you want to submit  ?",
                function(result){
                    if(result == true){
                        $("#aapData").submit();
                    }
            });
    }

    function getActDataByCmpFromConsData(cmpCode){
		 $('#loader').removeClass('hidden');
        $.ajax({
            url : contextPath+'/articleAjax/getActDataByCmpFromConsData',
            data : {
              "aapCode"     : $("#aapTypeId").val(),
              "finYear"       : $("#finId").val(),
              "cmpCode" : cmpCode,
            },
            success : function(response) {
              var data = response;
              var trHTML = '<option value="0">--Select--</option>';
               $('#loader').addClass('hidden');
              if(response != null){
                $.each(data, function(i, value) {
                
                    trHTML += '<option value='+value.actCode+'>'+value.actNameEn+'</option>';
                  
                });
              }else{
                bootbox.alert("Data not found")
              }
              
                $('#activity').empty().append(trHTML);
             
            },
          });


    }
    

	
	
	
	function fundDisburseDataAjax(sanctionOrderNo){
		
        var format = new Intl.NumberFormat('en-US', {
            currency: 'INR',
            minimumFractionDigits: 2,
        });
        
		var fin = $("#finId").val();
		var aapCode = $("#aapTypeId").val();
		var sanctionOrderNo = $("#sanctionOrderNoState").val();
		var newEntry = $("#newOrder").val();
		var centerCode = $('#centerId').val();
		var cmpId = $('#cmpId').val();
		var agnRegCode = $('#agencyUser').val();
		var activity = $('#activity').val();
		var agencyCode = $('#agencyCode').val();
		
		if (fin != "0") {
			 $('#loader').removeClass('hidden');
			$.get(contextPath + "/state/createRowForDisbursement", { finYear: fin, aapCode: aapCode, stateCode: sanctionOrderNo != 'NEW' ? sanctionOrderNo : newEntry, cmpCode: cmpId, activity: activity, agencyCode: agencyCode, centerCode: centerCode })
				.done(function(data) {
					 $('#loader').addClass('hidden');
					console.log(data);
					if (data.outcome) {
						$("#formSubmit").removeClass("hidden");

						//set fund received part
						// $("#dsbId").val(data.data.disbursmentReceived.id);
						// $("#dsbRcvCode").val(data.data.disbursmentReceived.dsbRcvCode);
						$("#disburseDate").val(data.data.disbursmentDate);
						// if(data.data.disbursmentReceived.supportingDocument != null){
						//   $("#docId").removeClass("hidden");
						//   $("#docId").html("Download");
						// }
						// $("#letterNo").val(data.data.fundReceived.letterNo);



						data.data.disbursmentFundList.forEach((elm, index) => {

							var totalRows = $('#tableId1 tr').length - 1;
							var table = document.getElementById("tableId1");
							var row = table.insertRow(totalRows + 1);

							var cell1 = row.insertCell(0);
							var cell2 = row.insertCell(1);
							var cell3 = row.insertCell(2);
							var cell4 = row.insertCell(3);
							var cell5 = row.insertCell(4);
							var cell6 = row.insertCell(5);

							var dynoId = totalRows - 2;

							var cellVal1 = '<input type="hidden" class="form-control form-control-sm"  name="disbursmentFundList[' + index + '].dsbRcvCode.finYear" value="' + elm.dsbRcvCode.finYear + '" ><input type="hidden" class="form-control form-control-sm" id="deductAmount"  name="deductAmount" value="' + data.data.deductAmount + '" ><input type="hidden" class="form-control form-control-sm" id="aapCode_' + index + '" name="disbursmentFundList[' + index + '].dsbRcvCode.aapCode.aapCode" value="' + elm.dsbRcvCode.aapCode.aapCode + '" ><input type="hidden" class="form-control form-control-sm"  name="disbursmentFundList[' + index + '].dsbRcvCode.agnRegCode.agencyRegistrationCode" value="' + elm.dsbRcvCode.agnRegCode.agencyRegistrationCode + '" ><input type="text" width=100% class="form-control form-control-sm " style="width:100%;" value="' + elm.dsbRcvCode.agnRegCode.agencyRegistrationNameEn + '" readonly>';
							var cellVal2 = '<input type="hidden" class="form-control form-control-sm"  name="disbursmentFundList[' + index + '].id" value="' + elm.id + '" ><input type="hidden" name="disbursmentFundList[' + index + '].dsbFundCode" id="dsbFundCode_' + index + '" value="' + elm.dsbFundCode + '" /><input type="hidden" name="disbursmentFundList[' + index + '].dsbRcvCode.dsbRcvCode" value="' + elm.dsbRcvCode.dsbRcvCode + '" id="dsbCode"/><input type="hidden" class="form-control form-control-sm"  name="disbursmentFundList[' + index + '].actCode.actCode" id="actCode_' + index + '" value="' + elm.actCode.actCode + '" ><input type="text" width=100% class="form-control form-control-sm " style="width:100%;" id="actName_' + index + '" name="disbursmentFundList[' + index + '].actCode.actNameEn" value="' + elm.actCode.actNameEn + '" readonly>';
							var cellVal3 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="disbursmentFundList[' + index + '].amountDemanded" readonly id="amountDemanded_' + index + '" value="' + format.format(elm.amountDemanded) + '" >';
							var cellVal4 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="disbursmentFundList[' + index + '].amountReleased" id="amountReleased_' + index + '" value="' + format.format(elm.amountReleased) + '" readonly >';
							var cellVal5 = '<input type="text" class="form-control form-control-sm NumbersOnly" style="width:100%;" name="disbursmentFundList[' + index + '].balanceAmount" id="balanceAmount' + index + '" value="' + format.format(elm.balanceAmount) + '" readonly >';
							var cellVal6 = '<input type="text" class="form-control form-control-sm NumbersOnly" maxlength="10" autocomplete = "off" style="width:100%;" name="disbursmentFundList[' + index + '].dsbAmount" id="dsbAmount_' + index + '"  value="' + format.format(elm.dsbAmount) + '" >';



							cell1.innerHTML = "" + cellVal1 + "";

							cell2.innerHTML = "" + cellVal2 + "";

							cell3.innerHTML = "" + cellVal3 + "";

							cell4.innerHTML = "" + cellVal4 + "";

							cell5.innerHTML = "" + cellVal5 + "";

							cell6.innerHTML = "" + cellVal6 + "";

                            $("#dsbAmount_" + index).attr("onkeyup", "DoublePercent('dsbAmount_" + index + "')");
                            $("#dsbAmount_"+index).attr("onchange","currencyConverter(this.value,'dsbAmount_"+index+"');getAmountDisbursed("+index+","+( elm.amountDemanded == null ? 0.0 : elm.amountDemanded ) +")");
							$.getScript(contextPath + "/assets/appJs/validation/common-utils.js");

						});
					} else {
						bootbox.alert(data.message);
						
						$("#finId").val("0");
						$("#aapTypeId").val("0");
						$("#agencyCode").empty().append('<option value="0">--Select--</option>');
						$("#agencyTypeCode").val("0");
						$("#cmpId").val("0");
						$("#activity").empty().append('<option value="0">--Select--</option>');
						$("#centerId").empty().append('<option value="0">--Select--</option>');
						$("#sanctionOrderNoState").empty().append('<option value="0">--Select--</option>');
						$("#newOrder").prop("readonly", false);
					  $("#aapTypeId").removeClass("frezz");
					  $("#finId").removeClass("frezz");
					  $("#agencyTypeCode").removeClass("frezz");
					  $("#agencyCode").removeClass("frezz");
					  $("#cmpId").removeClass("frezz");
					  $("#activity").removeClass("frezz");
					  $("#centerId").removeClass("frezz");
					   $("#sanctionOrderNoState").removeClass("frezz");
					   $("#actPopData").addClass("hidden");
						
					}

				})
				.fail(function(exception) {
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