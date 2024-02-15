function getAPDataForExpenditure(){
    var cmpCode = $("#cmpId").val();
    var fy = $("#finId").val();
    var aapType= $("#aapId").val();
	 $('#loader').removeClass('hidden');
      $.ajax({
        url : contextPath+'/articleAjax/getAPDataForExpenditure',
        data : {
          "cmpCode" : cmpCode,
          "fy" : fy,
          "aapType" : aapType
        },
          success : function(response) {
           $('#loader').addClass('hidden');
            var data = response;
            var trHTML = '<option value="0">--Select--</option>';
            if(response != null){
              $.each(data, function(i, value) {
              
                  trHTML += '<option value="' + value.apRcvCode + '">' + value.apRcvCode + '</option>';
              });
            }else{
              bootbox.alert("Data not found")
            }
              $("#apId").empty().append(trHTML);
              
          },
  });
}

function getMPRDataForExpenditure(mprVal){
    var apRcvCode = $("#apId").val();
    var cmpCode = $("#cmpId").val();
    var fy = $("#finId").val();
    var aapType= $("#aapId").val();
	 $('#loader').removeClass('hidden');
      $.ajax({
        url : contextPath+'/articleAjax/getMprDataForExpenditure',
        data : {
          "apRcvCode" : apRcvCode,
          "cmpCode" : cmpCode,
          "fy" : fy,
          "aapType" : aapType
        },
          success : function(response) {
           $('#loader').addClass('hidden');
            var data = response;
            var trHTML = '<option value="0">--Select--</option>';
            if(response != null){
              $.each(data, function(i, value) {
              
                  trHTML += '<option value="' + value.mprCode + '">' + value.mprCode + '</option>';
              });
            }else{
              bootbox.alert("Data not found")
            }
              $("#mprId").empty().append(trHTML);
              if(mprVal!=''){
                $("#mprId").val(mprVal);
              }
          },
  });
}

function getEntity(entityCode,subActCode){
 $('#loader').removeClass('hidden');
    $.ajax({
        url : contextPath+'/articleAjax/getEntity',
        data : {
          "entityCode" : entityCode,
          "subActCode" : subActCode
        },
          success : function(response) {
           $('#loader').addClass('hidden');
            var data = response;
            var trHTML = '<option value="0">--Select--</option>';
            if(response != null){
              $.each(data, function(i, value) {
              
                  trHTML += '<option value="' + value.entityCode     + '">' + value.entityName + '(' + value.aadhar + ')' +'</option>';
              });
            }else{
              bootbox.alert("Data not found")
            }
              $("#entityId").empty().append(trHTML);
          },
  });

}
    function checkDuplicatePhase(){
    	 if($("#entityTypeId").val()==""){
    	        bootbox.alert("Please Fill Entity Type. ");
    	        return false;
    	        }else if($("#entityId").val()==""|| $("#entityId").val()==0){
    	            bootbox.alert("Please Fill Entity.");
    	        return false;
    	        }else{
    	        	 debugger;
    	        	 	$('#loader').removeClass('hidden');
    	        	    var entityTypeId = $("#entityTypeId").val();
    	        	    var entityId = $("#entityId").val();
    	        	    var paymentPhase = $("#paymentPhase").val();
    	        	    var expRcvCode = $("#expRcvCode").val();
    	        	      $.ajax({
    	        	        url : contextPath+'/articleAjax/checkDuplicatePhase',
    	        	        data : {
    	        	          "entityTypeId" : entityTypeId,
    	        	          "entityId" : entityId,
    	        	          "paymentPhase" : paymentPhase,
    	        	          "expRcvCode" : expRcvCode,
    	        	        },
    	        	          success : function(response) {
    	        	           $('#loader').addClass('hidden');
    	        	            if(response != null){
    	        	            
    	        	             if(response=='EXIST'){
    	        	              $("#paymentPhase").val('');
    	        	            	 bootbox.alert("Phase already given against the entity .");
    	        	            	
    	        	             }
    	        	          }
    	        	          },
    	        	          error:function(error){
									$('#loader').addClass('hidden');
    	        	        	  bootbox.alert("Something Went Wrong");
    	        	          }
    	        	  });
    	        }
    	 
    }
    


	
	