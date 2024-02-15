
function createRow(actionTaken){

    // reset table 
    if(actionTaken == "FETCH-CREATION"){
        $(".dataCollector").remove();
    }
    
   var fin=$("#finId").val();
   var aapType=$("#aapTypeId").val();
   var financeCount='${financeList.size()}';



   if(fin !="0"){
        // bootbox.confirm({"message":"Are you sure you want to generate new AAP(Aanual Action Plan)?","callback":function(result){
        //     if(result){
        		 $('#loader').removeClass('hidden');
                    $.get( contextPath+"/aap/createRow", { fnYear: fin , aapType : aapType , methodCall : actionTaken , configCode :  $("#configCode").val() } )
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
                                var dynoCells = [];
                                var dynoCellValue = [];

                            var totalRows=$('#tableId1 tr').length-1;
                            var table = document.getElementById("tableId1");
                            var row = table.insertRow(totalRows + 1);
                            
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
                                }else{
                                    dynoCells[i] = row.insertCell(initialIndexCount);
                                    dynoCells[i].className = "AddRemoveRowIconCell" ;  
                                    row.className="dataCollector";
                                    row.id="successColor_"+dynoId;  
                                } 
                            }

                            //make for each loop  for componentList

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


                            
                         
                            
                            var cellVal1 = '<input type="hidden" class="form-control form-control-sm " name="agencyVOList['+dynoId+'].aapCode" value="'+elm.aapCode+'"><select class="form-control form-control-sm dupCheck2 validCheck" id="cmp_'+dynoId+'" name="agencyVOList['+dynoId+'].cmpCode.cmpCode"  onchange="getActivityListByCmpCode(this.value,'+dynoId+')" >'+componentList+'</select>';
                            var cellVal2 = '<select class="form-control form-control-sm dupCheck2 validCheck" id="activity_'+dynoId+'" name="agencyVOList['+dynoId+'].actCode.actCode" onchange="getSubActivityListByActCode(this.value,'+dynoId+')" >'+ActivityList+'</select>';
                            var cellVal3 = '<select class="form-control form-control-sm dupCheck2 validCheck" id="subActivity_'+dynoId+'" name="agencyVOList['+dynoId+'].subActCode.subActCode" onchange="getPhysicalUnitBySubActCode(this.value,'+dynoId+')" >'+subAcdtivityList+'</select>';
                            var cellVal4 = '<input type="text" class="form-control form-control-sm " id="physicalUnit_'+dynoId+'" name="agencyVOList['+dynoId+'].physicalUnit"  value="'+(elm.physicalUnit == null ? '' : elm.physicalUnit) +'" readonly autocomplete="off">';
                            // var cellVal7 = '<select class="form-control form-control-sm select2-multiple" multiple="multiple" id="cluster_'+dynoId+'" > <option value="FFFF">Select</option><option value="fdfdf">Select</option> </select>';

                            var cellVal5 = '<input type="text" class="form-control form-control-sm " id="unitNo_'+dynoId+'" name="agencyVOList['+dynoId+'].noPerUnits" value="'+(elm.noPerUnits == null ? '0' : elm.noPerUnits) +'" autocomplete="off">';
                           

                            data.data.fnList.forEach((element,ind) => { 
                                let fValue = elm.planList[ind].fnValue == null ? '0' : elm.planList[ind].fnValue;
                                let readonlyCheck=(elm.planList[ind].fnCode.fnCode == 'TOTAL' ? 'readonly' : '');
                                dynoCellValue[ind] = '<input type="hidden"  name="agencyVOList['+dynoId+'].planList['+ind+'].planCode" value="'+elm.planList[ind].planCode+'"><input type="text" class="form-control form-control-sm "   '+readonlyCheck+' id="'+element.fnCode+'_'+dynoId+'" name="agencyVOList['+dynoId+'].planList['+ind+'].fnValue" value="'+fValue+'" autocomplete="off">';  
                            });


                            cell1.innerHTML = ""+(index+1)+"";
                            
                            cell2.innerHTML = ""+cellVal1+"";
                            
                            cell3.innerHTML = ""+cellVal2+"";
                            
                            cell4.innerHTML = ""+cellVal3+"";
                            
                            cell5.innerHTML = ""+cellVal4+"";
                            
                            cell6.innerHTML = ""+cellVal5+"";


                            //make for loop with index for dynoCells

                             
                            for(var i=0;i<dynoCells.length;i++){
                                if(i < dynoCells.length-1){
                                    dynoCells[i].innerHTML = ""+dynoCellValue[i]+"";
                                }else{
                                    dynoCells[i].innerHTML = "<button type='button' class='btn btn-danger btn-sm' id='deleteMap_"+dynoId+"'  title='Remove Current Row'><i class='fa fa-minus' aria-hidden='true'></i></button>";
                                    $("#deleteMap_"+dynoId).attr("onclick","deleteRow('"+elm.aapCode+"','DELETE-ROW-DATA')");
                                }
                            }
                            // $.getScript("${contextPath}/assets/appJs/validation/common-utils.js");
                     
                                 });
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
      //make a for loop for data 
       $('#loader').addClass('hidden');
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


// $.post(contextPath+"/aap/districtAgency", function(data, status){
//     //make a for loop for data 
//     data.forEach(element => {
//         html = html + "<option value='"+element.agencyCode+"'>"+element.agencyNameEn+"</option>";
//     });

//     $("#agency").empty().append(html);
// });

function updateFormData(){
	var post_url = $("#aapData").attr("action"); //get form action url
	var form_data = $("#aapData").serialize(); //Encode form elements for submission
	console.log(form_data);
	$.post( post_url, form_data) 
        .done(function( data ) {
        //make a for loop for data 
        console.log(data);
        bootbox.alert(data.message);
        setTimeout(function() {
            createRow('FETCH-CREATION');
        }, 2000);
      })
      .fail(function() {
        alert( "error" );
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
            $.get( contextPath+"/aap/deleteRow", { helperData: helperData , aapType : aapType , methodCall : actionTaken , configCode :  $("#configCode").val() } )
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
				$('#loader').addClass('hidden');
                bootbox.alert( "error" );
            });

        }
    }});
}

  