

function createRow(actionTaken){

    var format = new Intl.NumberFormat('en-US', {
		currency: 'INR',
		minimumFractionDigits: 2,
	});
    
    var statusCheck=false;
    // reset table 
    if(actionTaken == "FETCH-CREATION"){
        $(".dataCollector").remove();
    }else{
        
            updateFormData();
        
    }
    
   var fin=$("#finId").val();
   var cmpCode=$("#cmpId").val();
   var aapType=$("#aapTypeId").val();
   var financeCount=$("#finCount").val();



   if(fin !="0" && cmpCode !="0"){
        // bootbox.confirm({"message":"Are you sure you want to generate new AAP(Annual Action Plan)?","callback":function(result){
        //     if(result){
            $('#loader').removeClass('hidden')
                    $.get( contextPath+"/aap/createRow", { fnYear: fin , aapType : aapType , methodCall : actionTaken , configCode :  $("#configCode").val() ,cmpCode :  $("#cmpId").val()} )
                        .done(function( data ) {
                            $('#loader').addClass('hidden');
                        console.log(data);
                        if(data.outcome){
                    
                            statusCheck =data.data.aapStatus == "DRAFT" ? true : data.data.aapStatus == "REVERTED" ? true : false;
                            if(statusCheck){
                            $("#formSubmit").removeClass("hidden");
                            $("#configCode").val(data.data.configCode);
                            $("#aapStatus").val(data.data.aapStatus);
                            var aapStatus = data.data.aapStatus;
                            $("#formSubmit").empty().text(aapStatus == 'DRAFT' ? "Save As Draft" : aapStatus == 'REVERTED' ? "Update" : "Already Submitted");


                    // Dynamic Header set

                    if(actionTaken == "FETCH-CREATION"){
                        var dynoHeader = '<th rowspan="2">Activity</th><th rowspan="2">Sub Activity</th>';
                            
                        data.data.headerList.forEach((elm,index) => { 
                            // adding dynamic th to dynoHeader
                            dynoHeader = dynoHeader + '<th rowspan="2">'+elm.subCategoryName+'</th>';
                        });
                        if(aapType == "DIST-AAP" || aapType == "DIST-SUB-AAP"){
                            dynoHeader=dynoHeader + '<th style="width: 200px;" rowspan="2">Block</th><th rowspan="2" style="width: 200px;">Grampanchayat</th><th style="width: 200px;" rowspan="2">Village</th>';
                        }
    
                        dynoHeader= dynoHeader + '<th colspan="'+financeCount+'">Financial Plan </th><th rowspan="2"><div class="btn-group" aria-label="Basic mixed styles example"><button type="button" class="btn btn-primary btn-xs" id="addRow"  data-toggle="tooltip"><i class="fas fa-plus"></i></button></div></th>';
    
                        $("#dynoHeader").empty().append(dynoHeader);

                        $("#addRow").attr("onclick","createRow('CREATION')");
    
                        $("#dynoTable").removeClass('hidden');
                    }

                   

                    // Data set into Header


                            data.data.agencyVOList.forEach((elm,index) => { 
                                // blockArray.empty();
                                // gpArray.empty();
                                // vlgArray.empty();
                                var initialIndexCount= 1 ;
                                var dynoCells = [];
                                var dynoCellValue = [];
                               //  var ActivityList='';
                               // var subAcdtivityList='';
                                var ActivityList='<option value="NA">Select</option>';
                                var subAcdtivityList='<option value="NA">Select</option>'; 

                                if(aapType == "DIST-AAP" || aapType == "DIST-SUB-AAP"){
                              //  var blockList='<option value="NA">Select Block</option>';
                              //  var gpList='<option value="NA">Select Grampanchayat</option>';
                              //  var villageList='<option value="NA">Select Village</option>';
                               var blockList='';
                                var gpList='';
                                var villageList='';
                                var blockArray=[];
                                var gpArray=[];
                                var vlgArray=[];
                                var dynoDemoCells = [];
                                var dynoDemoCellValue = [];
                                }

                                // adding sub category to dynoCells

                                var dynoCellField =[];
                                var dynoCellFieldValue =[];

                            var totalRows=$('#tableId1 tr').length-1;
                            var table = document.getElementById("tableId1");
                            var row = table.insertRow(totalRows + 1);
                            
                            // var cell1 = row.insertCell(0);
                            var cell1 = row.insertCell(0);
                            var cell2 = row.insertCell(1);
                           

                            var dynoId=totalRows-1;


                            for (var i = 0; i < data.data.headerList.length; ++i) {
                                initialIndexCount++;
                                dynoCellField[i] = row.insertCell(initialIndexCount);
                            }

                            if(aapType == "DIST-AAP" || aapType == "DIST-SUB-AAP"){
                            for (var i = 0; i < 3; ++i) {
                                initialIndexCount++;
                                dynoDemoCells[i] = row.insertCell(initialIndexCount);
                            }
                            }

                            
                            for (var i = 0; i <= elm.planList.length; ++i) {
                                initialIndexCount++;
                                if(i != elm.planList.length){
                                dynoCells[i] = row.insertCell(initialIndexCount);
                                }else{
                                    dynoCells[i] = row.insertCell(initialIndexCount);
                                    dynoCells[i].className = "AddRemoveRowIconCell" ;  
                                    row.className="dataCollector";
                                    row.id="successColor_"+dynoId;  
                                } 
                            }

                            if(aapType == "DIST-AAP" || aapType == "DIST-SUB-AAP"){
                            for(var i=0; i<elm.blockList.length; ++i){
                                
                                blockArray=elm.blockIds == null ? [] : elm.blockIds;
                                //elm.blockList[i].aapCode == elm.aapCode && elm.blockList[i].isSelected
                                // alert(blockArray);
                                blockList = blockList + "<option value='"+elm.blockList[i].blockId+"' "+(blockArray.indexOf(elm.blockList[i].blockId) != -1 ? 'selected' : '' )+"  >"+elm.blockList[i].blockNameEN+"</option>";
                            }

                            for(var i=0; i<elm.gpList.length; ++i){
                                gpArray=elm.gpIds == null ? [] : elm.gpIds;
                                //elm.gpList[i].aapCode == elm.aapCode && elm.gpList[i].isSelected == true 
                                gpList = gpList + "<option value='"+elm.gpList[i].gpId+"' "+(gpArray.indexOf(elm.gpList[i].gpId) != -1 ? 'selected' : '' )+" >"+elm.gpList[i].gpNameEN+"</option>";
                            }

                            for(var i=0; i<elm.villageList.length; ++i){
                                vlgArray=elm.villageIds == null ? [] : elm.villageIds;
                                //elm.villageList[i].aapCode == elm.aapCode && elm.villageList[i].isSelected == true
                                villageList = villageList + "<option value='"+elm.villageList[i].villageId+"' "+(vlgArray.indexOf(elm.villageList[i].villageId) != -1 ? 'selected' : '' )+" >"+elm.villageList[i].villageNameEn+"</option>";
                            }

                        }

//                             //make for each loop  for componentList

//                             data.data.componentList.forEach(element =>{
//                                 if(elm.cmpCode == null){
//                                     componentList=componentList+"<option value='"+element.cmpCode+"' >"+element.cmpNameEn+"</option>";
//                                 }else{
//                                     componentList=componentList+"<option value='"+element.cmpCode+"' "+(elm.cmpCode.cmpCode == element.cmpCode ? 'selected' : '') +"  >"+element.cmpNameEn+"</option>";
//                                 }
//                             });

                            //make for each loop  for activityList
                            if( data.data.activityList !=null){
                                data.data.activityList.forEach(element => {
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


//                              //make for each loop  for Activity Season List

//                              data.data.actSeasonList.forEach(element =>{
//                                 if(elm.seasonCode == null){
//                                     activitySeasonList=activitySeasonList+"<option value='"+element.actSeasonCode+"'>"+element.actSeasonNameEn+"</option>";
//                                 }else{
//                                     activitySeasonList=activitySeasonList+"<option value='"+element.actSeasonCode+"' "+(elm.seasonCode.actSeasonCode == element.actSeasonCode ? 'selected' : '')+" >"+element.actSeasonNameEn+"</option>";
//                                 }
//                             });

//                              //make for each loop  for Project Area List

//                              data.data.projAreaList.forEach(element =>{
//                                 if(elm.areaCode == null){
//                                     pAreaList=pAreaList+"<option value='"+element.areaCode+"' >"+element.areaNameEn+"</option>";
//                                 }else{
//                                     pAreaList=pAreaList+"<option value='"+element.areaCode+"' "+(elm.areaCode.areaCode == element.areaCode ? 'selected' : '')+" >"+element.areaNameEn+"</option>";
//                                 }
//                             });



                            

                            
//                          //,getClusterDataListModal(this.value,'+dynoId+')
                            
                            var cellVal1 = '<input type="hidden" class="form-control form-control-sm " id="aapCode_'+dynoId+'" name="agencyVOList['+dynoId+'].aapCode" value="'+elm.aapCode+'"><select class="form-control form-control-sm dupCheck2 validCheck" id="activity_'+dynoId+'" name="agencyVOList['+dynoId+'].actCode.actCode" onchange="getSubActivityListByActCode(this.value,'+dynoId+')" >'+ActivityList+'</select>';
                            var cellVal2 = '<select class="form-control form-control-sm dupCheck2 validCheck" id="subActivity_'+dynoId+'" name="agencyVOList['+dynoId+'].subActCode.subActCode" onchange="getPhysicalUnitBySubActCode(this.value,'+dynoId+');getDuplicateCheck(this.value,'+dynoId+')" >'+subAcdtivityList+'</select>';



                        elm.planList.forEach((element,ind) => { 
                                let fValue = elm.planList[ind].fnValue == null ? '0' : elm.planList[ind].fnValue;
                                 let cValue = elm.planList[ind].charValue == null ? '' : elm.planList[ind].charValue;
                                let readonlyCheck=(elm.planList[ind].fnCode.fnCode == 'TOTAL' ? 'readonly' : '');
                                
                                if(element.fnCode.fnCode.indexOf("FINC-U8OKXU") != -1 ){
                                     dynoCellValue[ind] = '<input type="hidden"  name="agencyVOList['+dynoId+'].planList['+ind+'].planCode" value="'+elm.planList[ind].planCode+'"><input type="text" class="form-control form-control-sm " '+readonlyCheck+' id="plan_'+dynoId+ind+'" maxlength="7" name="agencyVOList['+dynoId+'].planList['+ind+'].charValue" value="'+cValue+'" autocomplete="off">';  
 
                                }else{
                                   dynoCellValue[ind] = '<input type="hidden"  name="agencyVOList['+dynoId+'].planList['+ind+'].planCode" value="'+elm.planList[ind].planCode+'"><input type="text" class="form-control form-control-sm NumbersOnly" '+readonlyCheck+' id="plan_'+dynoId+ind+'" maxlength="7" name="agencyVOList['+dynoId+'].planList['+ind+'].inrFnValue" value="'+format.format(fValue)+'" autocomplete="off">';  
                                
                                }
                                
                            });

                            if(aapType == "DIST-AAP" || aapType == "DIST-SUB-AAP"){
                            for(var i=0;i<dynoDemoCells.length;i++){
                                if(i==0){
                                    dynoDemoCellValue[i] ='<select type="text" id="block_'+dynoId+'" name="agencyVOList['+dynoId+'].blockIds" class="multiselect" multiple="multiple" role="multiselect" required>"'+blockList+'"</select>';
                                }else if(i==1){
                                    dynoDemoCellValue[i] = '<select type="text" id="gp_'+dynoId+'" name="agencyVOList['+dynoId+'].gpIds" class="multiselect" multiple="multiple" role="multiselect" required>"'+gpList+'"</select>';
                                }else if(i==2){
                                    dynoDemoCellValue[i] = '<select type="text" id="vlg_'+dynoId+'" name="agencyVOList['+dynoId+'].villageIds" class="multiselect" multiple="multiple" role="multiselect" required>"'+villageList+'"</select>';
                                }

                            }
                        }

                           
    

                        data.data.headerList.forEach((element,ind) => { 
                                if(element.catType =="TEXT"){

                                    // find matching element in array of elm.fieldList using stream
                                    let field = elm.fieldList.find(field => field.subCatCode == element.subCategoryCode);
                                    console.log(field);
                                    var dynoCode= field.dynoCode;
                                    var fieldCode= field.fieldCode;
                                    var fieldValue= field.fieldValue;
                                    var idGenerator=''; 
                                    var idChecker=false;


                                    if(element.subCategoryName.indexOf("Physical") != -1 ){
                                    idGenerator= 'id=physicalUnit_'+dynoId;
                                    idChecker=true;
                                    }


                                    dynoCellFieldValue[ind] = '<input type="hidden"  name="agencyVOList['+dynoId+'].fieldList['+ind+'].dynoCode" value="'+dynoCode+'"><input type="hidden"  name="agencyVOList['+dynoId+'].fieldList['+ind+'].fieldCode" value="'+fieldCode+'"><input type="text" '+idGenerator+' '+(idChecker == true ? 'readonly' : '')+' class="form-control form-control-sm '+element.catFields[0].fieldValidation+'" maxlength="'+element.catFields[0].maxLength+'"  name="agencyVOList['+dynoId+'].fieldList['+ind+'].fieldValue"  value="'+(fieldValue == null ? '0' : fieldValue)+'" autocomplete="off">';
                                }else if(element.catType =="DROP-DOWN"){

                                    let field = elm.fieldList.find(field => field.subCatCode == element.subCategoryCode);
                                    var dynoCode= "";
                                    var fieldCode= "";
                                    var fieldValue= "";

                                    if(field != undefined){
                                     console.log(field);
                                    var dynoCode= field.dynoCode;
                                    var fieldCode= field.fieldCode;
                                    var fieldValue= field.fieldValue;
                                    }
                                    



                                    var dropDownValue = '<option value="">Select</option>';
                                    element.catFields.forEach((elm,ind) => {
                                        dropDownValue += '<option value="'+elm.fieldCode+'"  '+(elm.fieldCode == fieldCode ? "selected" : "")+'>'+elm.fieldName+'</option>';
                                    });

                                    dynoCellFieldValue[ind] = '<input type="hidden"  name="agencyVOList['+dynoId+'].fieldList['+ind+'].dynoCode" value="'+dynoCode+'"><input type="hidden"  name="agencyVOList['+dynoId+'].fieldList['+ind+'].fieldCode" value="'+fieldCode+'"><select class="form-control form-control-sm "  name="agencyVOList['+dynoId+'].fieldList['+ind+'].fieldValue" >'+dropDownValue+'</select>';  
                                }
                            });


                            // cell1.innerHTML = ""+(index+1)+"";
                            
                            cell1.innerHTML = ""+cellVal1+"";
                            
                            cell2.innerHTML = ""+cellVal2+"";

                            for(var i=0;i<dynoCellField.length;i++){
                                dynoCellField[i].innerHTML = ""+dynoCellFieldValue[i]+"";
                            }

                            if(aapType == "DIST-AAP" || aapType == "DIST-SUB-AAP"){

                            for(var i=0;i<dynoDemoCells.length;i++){
                                dynoDemoCells[i].innerHTML = ""+dynoDemoCellValue[i]+"";
                            }
                            $("#block_"+dynoId).attr("onchange","getGpByBlock("+dynoId+")");
                            $("#gp_"+dynoId).attr("onchange","getVlgByGp("+dynoId+")");
                        }
                             
                            for(var i=0;i<dynoCells.length;i++){
                                if(i < dynoCells.length-1){
                                    dynoCells[i].innerHTML = ""+dynoCellValue[i]+"";
                                   
                                }else{
                                    dynoCells[i].innerHTML = "<button type='button' class='btn btn-danger btn-sm' id='deleteMap_"+dynoId+"'  title='Remove Current Row'><i class='fa fa-minus' aria-hidden='true'></i></button>";
                                    $("#deleteMap_"+dynoId).attr("onclick","deleteRow('"+elm.aapCode+"','DELETE-ROW-DATA')");
//                                    $("#cluster_"+dynoId).attr("onclick","getClusterDataListModal(this.value,'+dynoId+')");
                                }
                                // $('#plan_'+dynoId+'1').attr("onkeyup","DoublePercent('plan_"+dynoId+"1')");
                                if(i < dynoCells.length-2){
                                 $('#'+dynoCells[i].childNodes[1].id).attr("onchange","currencyConverter(this.value,'"+dynoCells[i].childNodes[1].id+"');");
                                }
                            }
                            
                     
                                 });
                                }else{
                                    if(data.data.aapStatus == "CONSOLIDATED" ){
                                        $("#finId").val("0");
                                        $("#cmpId").val("0");
                                        bootbox.alert("ALL AAP is already consolidated, you can not create it.");
                                    }if(data.data.aapStatus == "MAIN-AAP-NA" ){
                                        $("#finId").val("0");
                                        $("#cmpId").val("0");
                                        bootbox.alert("Main AAP is not consolidated, you can not create it.");
                                    }else{
                                        $("#finId").val("0");
                                        $("#cmpId").val("0");
                                        bootbox.alert("AAP is already processed, you can not edit it.");
                                    }
                                    
                                }
                            }
                            $("select[role='multiselect']").multiselect();
                           
                            $.getScript(contextPath+"/assets/appJs/validation/common-utils.js");
                             $("#deleteMap_0").attr("disabled", true);
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
       
   }else{
     bootbox.alert("Please Choose Financial Year Or Component");
     $("#cmpId").val("0");
   }
}


function getGpByBlock(count){
	debugger
	/*var  block=$("#block_"+count).val();
	if (block.includes("NA")) {
		const index = block.indexOf("NA");
		if (index > -1) {
			block.splice(index, 1);
		}
	}
	console.log(block);*/
	var itemvalues = $("#block_"+count).val().toString();
	//var itemvalues = block.toString();
   // var html = "<option value=''>--- Select ---</option>";
    var html = "";
    $('#gp_'+count).empty().append(html);
	$('#gp_'+count).multiselect('rebuild');
    $('#vlg_'+count).empty().append(html);
	$('#vlg_'+count).multiselect('rebuild');
	$('#loader').removeClass('hidden');
		$.ajax({
			type : "GET",
			url : contextPath+'/articleAjax/findGpListByBlockId',
			dataType : "json",
			data : {
				"blockId" : itemvalues,
			},
			success : function(response) {
                $('#loader').addClass('hidden');
				
				var data = response;
				if (data != "" && data != null && data.length > 0) {
					$.each(data, function(index, value) {
						html = html + "<option value="+value.gpId+">"
								+ value.gpName + "</option>";
					});
				}else{
					bootbox.alert("No GP Found");
				}
				// $('#gp_'+count).multiselect('destroy');
				$('#gp_'+count).empty().append(html);
				$('#gp_'+count).multiselect('rebuild');
			},
			error : function(error) {
				$('#loader').addClass('hidden');
				bootbox.alert("got error");
			}
		});
}
        

function getVlgByGp(count){
	var itemvalues = $("#gp_"+count).val().toString();
    var html = "";
    $('#vlg_'+count).empty().append(html);
	$('#vlg_'+count).multiselect('rebuild');
    $('#loader').removeClass('hidden');
	$.ajax({
		type : "GET",
		url : contextPath+'/articleAjax/findVillageListByGpId',
		dataType : "json",
		data : {
			"gpId" : itemvalues,
		},
		success : function(response) {
            $('#loader').addClass('hidden');
			var data = response;
			if (data != "" || data != null) {
				$.each(data, function(index, value) {
					html = html + "<option value="+value.villageId+">"
							+ value.villageName + "</option>";
				});
			}
			// $('#vlg_'+count).multiselect('destroy');
			$('#vlg_'+count).empty().append(html);
			$('#vlg_'+count).multiselect('rebuild');
		},
		error : function(error) {
			$('#loader').addClass('hidden');
			bootbox.alert("No Village Found");
		}
	});
}

function getActivityListByCmpCode(cmpCode,dynoId) {
    $("#activity_"+dynoId).empty().append("<option value='NA'>--- Select Activity ---</option>");
    $("#subActivity_"+dynoId).empty().append("<option value='NA'>--- Select Sub Activity ---</option>");

    let html="<option value='NA'>--- Select Activity ---</option>";
    $('#loader').removeClass('hidden');
$.get( contextPath+"/articleAjax/getActivityList", { cmpCode: cmpCode } )
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
    $('#loader').removeClass('hidden');
    $("#subActivity_"+dynoId).empty().append("<option value='NA'>--- Select ---</option>");

    let html="<option value='NA'>--- Select ---</option>";
    
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
 $('#loader').removeClass('hidden');
if(subActCode != 'NA'){
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
//     $('#loader').addClass('hidden');;
}


}



// $.post(contextPath+"/aap/districtAgency", function(data, status){
//     //make a for loop for data 
//     data.forEach(element => {
//         html = html + "<option value='"+element.agencyCode+"'>"+element.agencyNameEn+"</option>";
//     });

//     $("#agency").empty().append(html);
// });

function updateFormData(){
	debugger
	var post_url = $("#aapData").attr("action"); //get form action url
	var form_data = $("#aapData").serialize(); //Encode form elements for submission
	console.log(form_data);
	$.post( post_url, form_data) 
        .done(function( data ) {
        //make a for loop for data 
         $('#loader').addClass('hidden');;
        console.log(data);
        bootbox.alert(data.message);
        setTimeout(function() {
            createRow('FETCH-CREATION');
        }, 2000);
      })
      .fail(function() {
        bootbox.alert( "Something Went Wrong Kindly Check The Amount Fields" );
      });

    // $.ajax({
    //     url: post_url,
    //     method : 'POST',
    //     data : form_data,
    //     dataType :'json',
    //     contentType : false,
    //     cache : false,
    //     processData : false
    //   }).done(function(response) {
    //     console.log(response);
    //   }).fail(function (response) {
    //     console.log(response);
    //     //   colorSection(index);
    //     //   $("#alert").empty().html("Unable To Update Beneficiary's Allotment ").delay(2000).fadeOut();
    //   });


};

// function stringToJson(str){
//     console.log(str);
//     console.log(JSON.parse(str));
//     return JSON.parse(str);
// }


function deleteRow(helperData,actionTaken){
    $("#insertrow").empty();
    var fin=$("#finId").val();
    var aapType=$("#aapTypeId").val();
    var financeCount='${financeList.size()}';

    bootbox.confirm({"message":"Are you sure you want to delete this row ?",
    "callback":function(result){
        if(result){
         $('#loader').removeClass('hidden');
            $.get( contextPath+"/aap/deleteRow", { helperData: helperData , aapType : aapType , methodCall : actionTaken , configCode :  $("#configCode").val(),cmpCode :  $("#cmpId").val() } )
            .done(function( data ) {
             $('#loader').addClass('hidden');
                if(data.outcome){
                        bootbox.alert(data.message);
                        $(".dataCollector").remove();
                        createRow("FETCH-CREATION");

                }else{
                    bootbox.alert("Something went wrong");
                }
                
            })
            .fail(function() {
                bootbox.alert( "error" );
            });

        }
    }});
    

}

function getModal(id){
    let aapCode = $("#aapCode_"+id).val();
    let subActCode = $("#subActivity_"+id).val();
    if(aapCode == "" || aapCode == "NA" || subActCode == "NA" || subActCode == ""){
        bootbox.alert("Please select AAP Code and Sub Activity");
        return false;
    }
    getClusterDataListModal(subActCode,id);
	$("#cluster").modal('show');
}



function getClusterDataListModal(subActCode, dynoId) {
   $("#insertCluster").empty();
   var aapCode = $("#aapCode_"+dynoId).val();
   if(aapCode == ""){
       bootbox.alert("Please select AAP Code");
       return false;
   }
    if(subActCode == "NA" || subActCode == ""){
        bootbox.alert("Please select Sub Activity");
        return false;
    }
    showAjaxLoader();
     $('#loader').removeClass('hidden');
  $.ajax({
    url: contextPath + "/articleAjax/getClusterByAgnRegdCodeAndSubActCode",
    type: "GET",
    data: {
      "subActCode": subActCode,
      "aapCode": aapCode
    },
    success: function (response) {
     $('#loader').addClass('hidden');
      var data = response;
      var trHTML = "";
      var count = 0;
      if (response.length > 0) {
        $.each(data, function (index, value) {
          count++;
          trHTML += "<tr><td>" + count + "</td><td>" + value.clusterName + "</td><td>" + value.clusterCode + "</td><td><input type='checkbox' name='clusterCode' value='" + value.clusterCode + "' " + (value.alreadyMapped == true ? "checked" : "") + "></td></tr>";
        });
        $("#insertCluster").empty().append(trHTML);
        $("#clusterDisAppModalAppCodeId").val(aapCode);
      }
      hideAjaxLoader();
    },
    error: function (response) {
        hideAjaxLoader();
        bootbox.alert("Something went wrong");
    },
  });
}


function getCheckedClusterCode() {
    var clusterCode = [];
    $("#insertCluster input:checkbox[name=clusterCode]:checked").each(function () {
        clusterCode.push($(this).val());
    });
    return clusterCode;
}


function submitClusterModalData(){
    var clusterCode = getCheckedClusterCode();
    //convert array to string
    var clusterCodeString = clusterCode.join(",");
    var aapCode = $("#clusterDisAppModalAppCodeId").val();   
    showAjaxLoader(); 
     $('#loader').removeClass('hidden');
    $.ajax({
        url:contextPath + "/articleAjax/distAgency/saveCluster",
        type: "GET",
        data: {
            "clusterCodes": clusterCodeString,
            "aapCode": aapCode
        },
        success: function (response) {
         $('#loader').addClass('hidden');
            if(response.outcome){
                //bootbox alert auto close after 2 seconds
                bootbox.alert({
                    message: response.message,
                })
                updateFormData();
                setTimeout(function() {
                    createRow('FETCH-CREATION');
                }, 2000);
                
                $("#cluster").modal("hide");
                hideAjaxLoader();
            }else{
                hideAjaxLoader();
                bootbox.alert(response.message);
            }
        },
        error: function (response) {
            hideAjaxLoader();
            bootbox.alert("Something went wrong");
        },
    });
}


    // document.onreadystatechange = function () {
	// var state = document.readyState
	// if (state == 'interactive') {
	// 	 $('#loader').removeClass('hidden');
	// } else if (state == 'complete') {
	// 	 $('#loader').addClass('hidden');;
	// }
	// }

    