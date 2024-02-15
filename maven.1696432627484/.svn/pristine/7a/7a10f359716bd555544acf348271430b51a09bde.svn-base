<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:useBean id="utils" class="com.fpo.web.utils.ApplicationStringUtils"/>
 <sec:authentication var="principal" property="principal" />
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
  <title>FPO</title>
  <meta name="keywords" content="Article-275::Department Of SCST" />
  <meta name="description" content="Article-275::Department Of SCST">
  <meta name="author" content="Aashdit Technologies">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <link rel="stylesheet" href="${contextPath}/assets/vendor/bootstrap-5.0.2/css/bootstrap.min.css">
   <link rel="stylesheet" href="${contextPath}/assets/js/jquery_datepicker/jquery.datepick.css">
   <link rel="stylesheet" href="${contextPath}/assets/css/bootstrap-select.min.css">
   <link rel="stylesheet" href="${contextPath}/assets/css/style.css">
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
  <link rel="shortcut icon" href="loginPage/images/logo.png">
    

<style>
	table .form-control {
    width: 100px;
}
.hide-cancel {
	display: none
}

.frezz{
	pointer-events: none;
}
</style>

  <script type="text/javascript" src="${contextPath}/assets/js/jquery.min.js"></script>

<%-- <script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/AesUtil.js"></script>
<script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/aes.js"></script>
<script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/pbkdf2.js"></script>
<script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/lbase.js"></script>  --%>


</head>
<body>
		
				<tiles:insertAttribute name="menu" />
				<section class="home-section">
						<tiles:insertAttribute name="header" />
						<tiles:insertAttribute name="body">
								<jsp:include page="/WEB-INF/commonSnippets/layoutWithLoginPage/message.jsp"></jsp:include>
						</tiles:insertAttribute>
				</section>
		
</body>
<!-- Page Specific Scripts -->
<%-- <tiles:insertAttribute name="pageScripts" /> --%>

<!--   Core JS Files   -->

  
    <script type="text/javascript" src="${contextPath}/assets/vendor/bootstrap-5.0.2/js/bootstrap.bundle.min.js"></script>
    <script src="${contextPath}/assets/js/jquery_datepicker/jquery.plugin.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/js/bootstrap-select.min.js"></script>
    <script src="${contextPath}/assets/js/jquery_datepicker/jquery.datepick.js" type="text/javascript"></script>
    <script type="text/javascript" src="${contextPath}/assets/js/script.js"></script>
       

    <%-- <script src="../assets/js/script.js"></script>
<script>var ctxPath = "${pageContext.request.contextPath}"</script>
<script src="${contextPath}/assets/vendor/jquery_datepicker/jquery.plugin.js"></script>
<script src="${contextPath}/assets/vendor/jquery_datepicker/jquery.datepick.js"></script>
<script src="${contextPath}/assets/js/core/popper.min.js"></script>
<script src="${contextPath}/assets/js/core/bootstrap.min.js"></script>
<script src="${contextPath}/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script> --%>
<!-- jQuery UI -->

<%-- <script  type="text/javascript"src="${contextPath}/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/vendor/datatables/datatables.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/vendor/datatables/dataTables.buttons.min.js"></script>
<script type="text/javascript"src="${contextPath}/assets/vendor/datatables/jszip.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/vendor/datatables/vfs_fonts.js"></script>
<script type="text/javascript" src="${contextPath}/assets/vendor/datatables/pdfmake.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/vendor/datatables/buttons.html5.min.js"></script>
<script src="${contextPath}/assets/vendor/select2full/select2.full.js"></script>
<script src="${contextPath}/assets/vendor/bootbox5/bootbox.js"></script>
<script src="${contextPath}/assets/vendor/bootbox5/bootbox.min.js"></script>

<script src="${contextPath}/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>


<script src="${contextPath}/assets/js/atlantis.min.js"></script>
<script src="${contextPath}/assets/appJs/framework/languageSwitcher.js"></script>
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script> --%>


<script>
document.onreadystatechange = function () {
	var state = document.readyState
	if (state == 'interactive') {
		 $('#loader').removeClass('hidden');
	} else if (state == 'complete') {
		 $('#loader').addClass('hidden');;
	}
	}
</script>
<script type="text/javascript">
	  		window.contextPath = '${contextPath}'; 
</script>
<!-- <script>
document.onreadystatechange = function () {
var state = document.readyState
}
	
	 	$("#btnn").click(function(){
		$("#printarea").table2excel({
			exclude: ".printarea",//exclude CSS class
			name: "Allocate",
			filename: "voucher" //do not include extension
		});
	});

$(function() {
   $('#startdt >input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,maxDate:new Date(),
 showTrigger: '<button type="button" class="trigger">' +
 '<i class="fa fa-calendar"></i></button>'});
 }); 
$(function() {
	   $('#enddt >input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,
		   showTrigger: '<button type="button" class="trigger">' +
	 '<i class="fa fa-calendar"></i></button>'});
	 });
$(function() {
	   $('#schemeStrt >input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,minDate:new Date(),
		   showTrigger: '<button type="button" class="trigger">' +
	 '<i class="fa fa-calendar"></i></button>'});
	 });
$(function() {
	   $('#schemeEnd >input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,minDate:new Date(),
		   showTrigger: '<button type="button" class="trigger">' +
	 '<i class="fa fa-calendar"></i></button>'});
	 });
	 


</script> -->
<!-- <script>
   $(function() {
      $('.datepicker_con>input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,
    showTrigger: '<button type="button" class="trigger">' +
    '<i class="fa fa-calendar"></i></button>'});
    }); 
   
   $('#date_of_last_election>input').datepick({onShow: $.datepick.monthOnly, minDate: '${notId.strLotteryDate}', dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,
	    showTrigger: '<button type="button" class="trigger">' +
	    '<i class="fa fa-calendar"></i></button>',onClose: function () {
	    	submitData(this);
		}});
   
   $('#dob>input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,maxDate: '-18Y',
 	  showTrigger: '<button type="button" class="trigger">' +
 	  '<i class="fa fa-calendar"></i></button>'});
  </script> -->
<script>
/* $(document).ready(function() {
	$('.DataTable').DataTable();
});
$('.DataTableBtn').DataTable({
    dom: 'Bfrtip',
    buttons: [
        'excel', 'csv'
    ]
} ); */
		  /* $(".YEAR-CONSTITUTION").datepick({
			    onSelect: function() {
			    	var date = document.getElementsByClassName('YEAR-CONSTITUTION')[0].value;
			    
			    	 var d = new Date(date);
			    	 var year = d.getFullYear();
			    	 var month = d.getMonth()+1;
			    	 var day = d.getDate();
			    	 if (day < 10) {
			    	 		day = '0' + day;
			            }
			            if (month < 10) {
			            	month = '0' + month;
			            }
		    	 	 var yearConstitute=day +  "/" + month + "/"+year;
			    	/*  var c = new Date(year + 3, month, day);
			    	 year1=c.getFullYear();
			    	  month1 = c.getMonth();
			    	  day1 = c.getDate();
			    	 var d=day1+"/"+ month1 +"/"+year1;
			    	 var d = day +  "/" + month + "/"+ parseInt(parseInt(year)+3);
			    	 $('.NEXT-ELECTION').val(d); 
 			    	 alert($('.NEXT-ELECTION').val());
			    	 $('.NEXT-ELECTION').addClass("frezz");
 			    	 $('.NEXT-ELECTION').datepick().datepick('disable');
 			    	 alert($('.NEXT-ELECTION').val());
			    	 $(".YEAR-CONSTITUTION").val(yearConstitute);
		 	    	 $(nextElection).val(c); 
			     NEXT-ELECTION
			        $(this).change();
			    }
			}); */
		  
		  
// 		  $('.Female').bind("change", function(){	
			   
// // 				var female = document.getElementsByClassName('Female');
// 				 var id=($(this).attr('id'));	
// 				 var classname=($(this).attr('class'));	
				 
// 				 var classList = document.getElementById(id).classList;
// 				 console.log(classList);
// 				 for(var i = 0; i < classList.length; i++){
// 				        console.log(classList[3]);
// 				 }
				        
// 				var male= document.getElementsByClassName('Male')[0].value;
// 				var tot=parseInt(male)+parseInt(female);
// 				 $(".Total").val(tot);
// 				$('.Total').addClass("frezz");
				
// 			});
		  
	</script>

<!-- <script type="text/javascript">
// 			$(document).ready(function() {				 
// 				$('.js-example-basic-multiple').select2();
// 			});
		
			
 	</script> -->
<script>
// 		$( ".select2-single, .select2-multiple" ).select2( {
// 			theme: "bootstrap",
// 			placeholder: "Select a State",
// 			maximumSelectionSize: 6,
// 			containerCssClass: ':all:'
// 		} );
		$('.btnNext').click(function() {
		    $('.nav-tabs .active').parent().next('li').find('a').trigger('click');
		  });
		
			

			
/* function convertFormToJSON(form) {
	  const array = form; // Encodes the set of form elements as an array of names and values.
	  
	  var obj = {};
      $.each(array, function(i, pair){
          var cObj = obj, pObj, cpName;
          $.each(pair.name.split("."), function(i, pName){
              pObj = cObj;
              cpName = pName;
              cObj = cObj[pName] ? cObj[pName] : (cObj[pName] = {});
          });
          pObj[cpName] = pair.value;
      });
      return obj; 
	}


function convertFormToJSONArray(form) {
	  const array = form; // Encodes the set of form elements as an array of names and values.
	   const json = {};
	    $.each(array, function() {
	    	var contentCheck=this.name;
	        if (json[this.name]) {
	            if (!json[this.name].push) {
	            	json[this.name] = [json[this.name]];
	            }
	            json[this.name].push(this.value || '');
	        } else if(contentCheck.includes("Array")) {
	        	json[this.name] = [this.value];
	        }else{
	        	json[this.name] = this.value || '';
	        }
	    });
	  return json; 
	} */
	</script>
	
</html>