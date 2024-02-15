function viewAppDtlsById(configCode){



                                var fin=data.data.finYear.finYear;
                                var cmpCode=data.data.cmpCode.cmpCode;
                                var aapType=data.data.aapType.aapTypeCode;
                                var financeCount=$("#finCount").val();
                                
                                    // bootbox.confirm({"message":"Are you sure you want to generate new AAP(Annual Action Plan)?","callback":function(result){
                                    //     if(result){
                                                $.get( contextPath+"/aap/createRow", { fnYear: fin , aapType : aapType , methodCall : "FETCH-CREATION" , configCode :  data.data.configCode ,cmpCode :  cmpCode  } )
                                                    .done(function( data ) {
                                                    console.log(data);
                                                    if(data.outcome){
                                                        $("#formSubmit").removeClass("hidden");
                                                        $("#configCode").val(data.data.configCode);
                            
                                                // Dynamic Header set
                            
                                              
                                                    var dynoHeader = '<th rowspan="2">Activity</th><th rowspan="2">Sub Activity</th>';
                                                        
                                                    data.data.headerList.forEach((elm,index) => { 
                                                        // adding dynamic th to dynoHeader
                                                        dynoHeader = dynoHeader + '<th rowspan="2">'+elm.subCategoryName+'</th>';
                                                    });
                                
                                                    dynoHeader= dynoHeader + '<th colspan="'+financeCount+'">Financial Plan (Rs. In Lakh)</th><th rowspan="2"><div class="btn-group" aria-label="Basic mixed styles example"><button type="button" class="btn btn-primary btn-xs" id="addRow"  data-toggle="tooltip"><i class="fas fa-plus"></i></button></div></th>';
                                
                                                    $("#dynoHeader").empty().append(dynoHeader);
                            
                                                    $("#addRow").attr("onclick","createRow('CREATION')");
                                
                                                    $("#dynoTable").removeClass('hidden');
                                           
                            
                                               
                            
                                                // Data set into Header
                            
                            
                                                        data.data.agencyVOList.forEach((elm,index) => { 
                                                       
                                                            var initialIndexCount= 1 ;
                                                            var dynoCells = [];
                                                            var dynoCellValue = [];
                                                            var ActivityList='<option value="NA">Select Activity</option>';
                                                            var subAcdtivityList='<option value="NA">Select Sub Activity</option>'; 
                            
                            
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
                            
                                                        
                                                        var cellVal1 = '<input type="hidden" class="form-control form-control-sm " id="aapCode_'+dynoId+'" name="agencyVOList['+dynoId+'].aapCode" value="'+elm.aapCode+'"><select class="form-control form-control-sm dupCheck2 " id="activity_'+dynoId+'" name="agencyVOList['+dynoId+'].actCode.actCode" onchange="getSubActivityListByActCode(this.value,'+dynoId+')" >'+ActivityList+'</select>';
                                                        var cellVal2 = '<select class="form-control form-control-sm dupCheck2 validCheck" id="subActivity_'+dynoId+'" name="agencyVOList['+dynoId+'].subActCode.subActCode" onchange="getPhysicalUnitBySubActCode(this.value,'+dynoId+')" >'+subAcdtivityList+'</select>';
                          
                            
                                                    elm.planList.forEach((element,ind) => { 
                                                            let fValue = elm.planList[ind].fnValue == null ? '0' : elm.planList[ind].fnValue;
                                                            let readonlyCheck=(elm.planList[ind].fnCode.fnCode == 'TOTAL' ? 'readonly' : '');
                                                            dynoCellValue[ind] = '<input type="hidden"  name="agencyVOList['+dynoId+'].planList['+ind+'].planCode" value="'+elm.planList[ind].planCode+'"><input type="text" class="form-control form-control-sm validCheck " '+readonlyCheck+' id="'+element.fnCode+'_'+dynoId+'" name="agencyVOList['+dynoId+'].planList['+ind+'].fnValue" value="'+fValue+'" autocomplete="off">';  
                                                        });
                            
                                                    data.data.headerList.forEach((element,ind) => { 
                                                            if(element.catType =="TEXT"){
                            
                                                                // find matching element in array of elm.fieldList using stream
                                                                let field = elm.fieldList.find(field => field.subCatCode == element.subCategoryCode);
                                                                console.log(field);
                                                                var dynoCode= field.dynoCode;
                                                                var fieldCode= field.fieldCode;
                                                                var fieldValue= field.fieldValue;
                            
                            
                                                                dynoCellFieldValue[ind] = '<input type="hidden"  name="agencyVOList['+dynoId+'].fieldList['+ind+'].dynoCode" value="'+dynoCode+'"><input type="hidden"  name="agencyVOList['+dynoId+'].fieldList['+ind+'].fieldCode" value="'+fieldCode+'"><input type="text"  class="form-control form-control-sm validCheck "  name="agencyVOList['+dynoId+'].fieldList['+ind+'].fieldValue"  value="'+(fieldValue == null ? '0' : fieldValue)+'" autocomplete="off">';  
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
                            
                                                         
                                                        for(var i=0;i<dynoCells.length;i++){
                                                            if(i < dynoCells.length-1){
                                                                dynoCells[i].innerHTML = ""+dynoCellValue[i]+"";
                                                            }else{
                                                                dynoCells[i].innerHTML = "<button type='button' class='btn btn-danger btn-sm' id='deleteMap_"+dynoId+"'  title='Remove Current Row'><i class='fa fa-minus' aria-hidden='true'></i></button>";
                                                                $("#deleteMap_"+dynoId).attr("onclick","deleteRow('"+elm.aapCode+"','DELETE-ROW-DATA')");
                            //                                    $("#cluster_"+dynoId).attr("onclick","getClusterDataListModal(this.value,'+dynoId+')");
                                                            }
                                                        }
                                                        // $.getScript("${contextPath}/assets/appJs/validation/common-utils.js");
                                                 
                                                             });
                                                        }
                                                      
                                                    })
                                                    .fail(function (exception) {
                                                        $("#formSubmit").addClass("hidden");
                                                        // Our error logic here
                                                        console.log(exception.responseText);
                                                       
                                          });
   
}