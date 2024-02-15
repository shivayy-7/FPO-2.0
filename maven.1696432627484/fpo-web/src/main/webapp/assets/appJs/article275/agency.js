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
    
  function getParentAgencyCode(agencyMasterCode,demographyId,selectedVal){
    	if(agencyMasterCode=='0' && demographyId=='0' ){
    		var agencyTypeCode = $("#agencyTypeCode").val();
	    	if(agencyTypeCode==="DISTRICT-JHG6GJ"){
	    		demographyId =$("#distId").val();
	    	}if(agencyTypeCode === 'STATE-JF3JKF'){
	    		demographyId =$("#stateId").val();
	    	}
	    	agencyMasterCode=$("#agencyCode").val();
    	}
    	var accessLevelType=$("#accessLevel").val();
    	if(accessLevelType==='VIEWER' || accessLevelType==='SUB-AGENCY'){
	    	if(agencyMasterCode!='0' && demographyId!='0'){
	    	 $('#loader').removeClass('hidden');
	    		$.ajax({
	    			url : contextPath+'/articleAjax/getParentAgencyCodeForViewer',
	    			type : 'GET',
	    			data : ({
	    				agencyMasterCode : agencyMasterCode,
	    				demographyId : demographyId
	    			}),
	    			success : function(response) {
	    			 $('#loader').addClass('hidden');
	    				var html=" <option value='0' >--select--</option>";
	    				var data = response.data;
	    				var selectData = '';
	    				if (data.length > 0) {
	    					$.each(data, function(key, value) {
	    						selectData = (value.code == selectedVal) ? "selected" : "";
	    						html += "<option value='"+value.code+"' "+selectData+">"+value.name+"</option>";
	         				});
	    					$(".parentAgencyCode").removeClass("hidden");
	    					$("#parentAgencyCode").empty().append(html);
	    				}else{
	    					bootbox.alert("No Creator present...");
	    					$("#accessLevel").val('0');
	    					$(".parentAgencyCode").addClass("hidden");
	    		    		$("#parentAgencyCode").val("");
	    				}
	    				
	    			},
	    			error : function(error) {
						$('#loader').addClass('hidden');
	     				bootbox.alert(error);
	    			}
	    		});
	    	}else{
	    		$(".parentAgencyCode").addClass("hidden");
	    		$("#parentAgencyCode").val("");
	    	}
    		
    	}else{
    		$(".parentAgencyCode").addClass("hidden");
    		$("#parentAgencyCode").val("");
    	}
    }
    
    function showDistrict(){
    	var agencyTypeCode = $("#agencyTypeCode").val();
    	if(agencyTypeCode==="DISTRICT-JHG6GJ"){
    		var stateId = $("#stateId").val();
			$(".district").removeClass("hidden");
			getDistrict(stateId,0);
		}else{
			$(".district").addClass("hidden");
		}
    	
    }
    
    function saveForm(submitType){
		if($("#agencyRegistrationNameEn").val() == "" ) {
					bootbox.alert("Please Enter Agency Name."); 
					return false;
			}
			if(submitType === 'SAVE'){
				if($("#agencyTypeCode").val() == "0" ) {
					bootbox.alert("Please select Agency Type."); 
					return false;
				}
				if($("#agencyCode").val() == "0" ) {
						bootbox.alert("Please select Agency Master Name."); 
						return false;
				}
				if($("#stateId").val() == "0" ) {
						bootbox.alert("Please select State."); 
						return false;
				}
				if($("#agencyTypeCode").val() == "DISTRICT-JHG6GJ" ) {
					if($("#distId").val() == "0" ) {
						bootbox.alert("Please select District."); 
						return false;
					}
					if($("#blockId").val() == "" ) {
						bootbox.alert("Please select Block."); 
						return false;
					}
				}
				if($("#accessLevel").val() === "0" ) {
					bootbox.alert("Please select Access level."); 
						return false;
				}
				
				if($("#accessLevel").val() == "VIEWER" ) {
					if($("#parentAgencyCode").val() == "0" ) {
						bootbox.alert("Please Select Parent Agency Code."); 
						return false;
					}
				}
			}
			
			if($("#agencyRegistrationMail").val() == "" ) {
				bootbox.alert("Please Enter Agency Email."); 
					return false;
			}
			if($("#agencyRegistrationCellNumber").val() == "" ) {
				bootbox.alert("Please Enter Agency contact no."); 
				return false;
			}
/*			if($("#agencyRegistrationAccountNumber").val() == "" ) {
				bootbox.alert("Please Enter Agency Account number."); 
				return false;
			}
			if($("#newBankName").val() == "0" ) {
				bootbox.alert("Please Select Agency Bank Name."); 
				return false;
			}
			if($("#newBranchName").val() == "0" ) {
				bootbox.alert("Please Select Agency Branch Name."); 
				return false;
			}
			if($("#newIFSC").val() == "0" ) {
				bootbox.alert("Please Select IFSC Name."); 
				return false;
			}*/
			var message = submitType ==='SAVE'?	"save":"update";
			bootbox.confirm("Do you want to "+message+" agency?",
			        function(result) {
			                if (result == true) {
			                	$("#formSubmit").submit();
			                }
			        });
    
    }
    
    $('#agencyTypeCode').change(function() {
  	  $('#accessLevel option[value="SUB-AGENCY"]').prop('disabled', $(this).val() == 'STATE-JF3JKF')
	});
	

	
