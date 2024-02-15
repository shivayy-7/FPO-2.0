$(function() {



	$('.AlphabetsOnly').keydown(function(e) {
		var regex = new RegExp(/^[a-zA-Z\s]+$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		if(e.key != "Backspace"){
			if (regex.test(str)) {
				return true;
			} else {
				e.preventDefault();
				return false;
			}
		}
	});
	

	$('.emailsOnly').keydown(function (e) {
	    var regex = new RegExp("/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }
	    else {
	        e.preventDefault();
	        return false;
	    }
	});
	$('.NumbersOnly').on(
			'keyup',
			function(event) {
				var regex = new RegExp("/^\d+$/");
				var key = String.fromCharCode(!event.charCode ? event.which
						: event.charCode);
				if (!regex.test(key)) {
					var getkey;
					getkey = $(event.target);
					getkey.val(getkey.val().replace(/[^0-9.]+/g, ""));
					//event.preventDefault();
					return false;
				}
		});
		$('.NumbersOnlyWithoutDot').keydown(function(e) {
		var regex = new RegExp(/^[0-9]+|[\b]+$/); // space given because of issue
		var str = e.key;
		if (regex.test(str) || str=='Backspace' || str=='Tab') {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});

	$('.AlphaNumericOnly').keydown(function(e) {
		var regex = new RegExp(/^[a-zA-Z0-9._\b\s]+$/); // space given because of issue
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		console.log(str);
		if (regex.test(str)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});
	
	$('.FlatsOnly').on(
			'keypress',
			function(event) {
				var regex = new RegExp(/^[a-zA-Z0-9\,\-]+$/);
				var key = String.fromCharCode(!event.charCode ? event.which
						: event.charCode);
				if (!regex.test(key)) {
					event.preventDefault();
					return false;
				}
		});
});

function validateEmail(emailField) {
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if (reg.test(emailField.value) == false) {
		//bootbox.alert("Please Provide a Vaild Email Id");
		var result = emailField.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		emailField.value = result;
		emailField.value = '';
		emailField.focus();
		return false;
	}
	return true;
}

function validateMobileNo(element) {
	/*alert("mobile vaildate function id---------->"+element);*/
	var re = /^[0-9]+$/;

	var str = element.toString();

	var str1 = element.value;

	element = (element) ? element : window.event;
	var charCode = (element.which) ? element.which : element.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}

	if (isNaN(str1) || str1.indexOf(" ") != -1) {
		//bootbox.alert("Please Provide a Vaild Mobile Number");
		var result = element.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		element.value = result;
		element.value='';
		this.value='';
		//document.getElementById('txtMobile').focus();
		return false;
	}

	if (str1.length > 10 || str1.length < 10) {
		//bootbox.alert("Please Provide a Vaild Mobile Number");
		var result = element.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		//element.value = result;
		element.value='';
		this.value='';
		//document.getElementById('txtMobile').value = '';
		return false;
	}

	$('.checkXss').keydown(function(event) {
	    var regex = new RegExp("[^a-zA-Z1-90_\\- \\.\\@\\#\\/\\,\\==+]*");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});
	
	/*$('textarea').on('keydown', function (event) {
	    var regex = new RegExp("^[a-zA-Z0-9. ]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});*/
}

function imageCheck(that){
	var ValidFileExtension = [ 'jpg','jpeg'];
	if($(that).val().split('.').length == 2 ) {
	if ($.inArray($("#"+that.id).val().split('.').pop().toLowerCase(), ValidFileExtension) == -1) {
		$("#"+that.id).val("");
		bootbox.alert("Sorry! allowed format is jpg or jpeg only.");
		event.preventDefault();
		return false;
	}
	if ((that.files[0].size) > 5242880) {
		$(that).val("");
		bootbox.alert("File size exceeds maximun image size of 5 MB!");
		return false;
	}
  }else{
	  	   bootbox.alert("Unsupported file format,Please check your file extension");
	  	   $(that).val("");
	}
}


function fileCheck(that){
		var ValidFileExtension = ['pdf'];

	if($(that).val().split('.').length == 2 ) {
		  if ($.inArray($(that).val().split('.').pop().toLowerCase(), ValidFileExtension) == -1) {
				bootbox.alert("Sorry! allowed format is pdf only.");
				$(that).val("");
				return false;
			}
			if ((that.files[0].size) > 2097152) {
				bootbox.alert("File size exceeds maximun file size of 2 MB!");
				$(that).val("");
				return false;
			}
		}else{
	  	   bootbox.alert("Unsupported file format,Please check your file extension");
	  	   $(that).val("");
	 }
	}
	
	function SpecificFileCheck(that){
			debugger;
			var ValidFileExtension = ['pdf','word','jpg','jpeg','png'];

		if($(that).val().split('.').length == 2 ) {
			  if ($.inArray($(that).val().split('.').pop().toLowerCase(), ValidFileExtension) == -1) {
					bootbox.alert("Sorry! allowed format is PDF , WORD , JPG , PNG only.");
					$(that).val("");
					return false;
				}
				if ((that.files[0].size) > 2097152) {
					bootbox.alert("File size exceeds maximun file size of 2 MB!");
					$(that).val("");
					return false;
				}
			}else{
		  	   bootbox.alert("Unsupported file format,Please check your file extension");
		  	   $(that).val("");
		 }
		}
	
	
function nonZeroFirstDigit(that) {
if (that.value.length == 1 && that.value == 0) {
	$("#"+that.id).val('');
}
}
	
function todayDateWithoutTime(dateTime) {
    var date = new Date(dateTime.getTime());
    date.setHours(0, 0, 0, 0);
    return date;
}

$('.AlphaNumericANDscAnd').keydown(function(e) {
debugger;
		var regex = new RegExp(/^[a-zA-Z0-9_\-@/.\s]+|[\b]+$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		if (regex.test(str)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});

		$('.AllotmentSpcl').on(
			'keydown',
			function(event) {
				var regex = new RegExp("^[1-9]\d*$");
				var key = String.fromCharCode(!event.charCode ? event.which
						: event.charCode);
				if (!regex.test(key)) {
					event.preventDefault();
					return false;
				}
			});
			
	$('.AlphaNumericWithLimitedSpecialChars').keydown(function(e) {
		var regex = new RegExp(/^[a-zA-Z0-9_\-@/./,/;/:/+/=\s]+$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		
		const key = event.key; // const {key} = event; ES6+
	    if (key === "Backspace") {
	       return true;
	    }
		
		if (regex.test(key)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});
	
	
	function removeLastIndexCommasWithWhiteSpaces(that){
		var finalCheck ="";
		finalCheck =that.value.trim().replace(/,*(?=$)/,''); //.split('-').join('');
	    finalCheck = finalCheck.trim().replace(/-*(?=$)/,''); 		//that.value=that.value.replace(/,(\s+)?$/, '') //.split('-').join('');
		if(finalCheck.match(/,(\s+)?$/)){
		that.value=finalCheck.trim().replace(/,*(?=$)/,'');
		}else{
		that.value=finalCheck.trim().replace(/-*(?=$)/,'');
		}
		return false;  
	}
	
	function currencyConverter(that,fieldId){
		if(that!=""){
			var value = that.replaceAll(",","");
			if(value.includes(".")){
				var splitValues = value.split(".");
				if(splitValues.length>=2){
					if(splitValues[0]==""){
						splitValues[0]="0";
					}
					value=splitValues[0]+"."+splitValues[1];
					if(splitValues[1].length==1){
						value=splitValues[0]+"."+splitValues[1]+"0";		
					}
					if(splitValues[1].length>2 || splitValues[1]==""){
						value=splitValues[0]+".00";		
					}
				}else{
					value=splitValues[0];
				}
			}else{
				value=value+".00";
			}
			//var hiddenFieldId = fieldId.split("Conv")[0];
			$("#"+fieldId+"Converted").val(value);
			value = value.replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
			$("#"+fieldId).val(value);
		}
	}
	
		function currencyConverterForProject(fieldId,index){
		if($("#"+fieldId+""+index).val()!=""){
			var value = $("#"+fieldId+""+index).val().replaceAll(",","");
			if(value.includes(".")){
				var splitValues = value.split(".");
				if(splitValues.length>=2){
					if(splitValues[0]==""){
						splitValues[0]="0";
					}
					value=splitValues[0]+"."+splitValues[1];
					if(splitValues[1].length==1){
						value=splitValues[0]+"."+splitValues[1]+"0";		
					}
					if(splitValues[1].length>2 || splitValues[1]==""){
						value=splitValues[0]+".00";		
					}
				}else{
					value=splitValues[0];
				}
			}else{
				value=value+".00";
			}
			//var hiddenFieldId = fieldId.split("Conv")[0];
			$("#"+fieldId+"Converted"+index).val(value);
			value = value.replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
			$("#"+fieldId+""+index).val(value);
		}
	}
	
	function CodeValidation(Type,fieldId,value){	
		$.ajax({
			type : "GET",
			url : window.contextPath+'/mst/validateCode',
			data : {
				"Code" : value,
				"type" :Type
			}, 
			success : function(response) {
			var data=JSON.parse(response);
			if(data[0].isDuplicate ==true){			
						bootbox.alert("Duplicate Code Not Allowed");
						$("#"+fieldId).val("");
					}			
			}, 
			error : function(error) {
			bootbox.alert("Time out");
			
			}
		});
	} 
	
	function changeCase(txt,fieldId) {
    let str1 = "";
    for (let i = 0; i < txt.length; i++) {
        if (/[A-Z]/.test(txt[i])) str1 += txt[i].toUpperCase();
        else str1 += txt[i].toUpperCase();
    }
   
    $("#"+fieldId).val(str1);
}


function checkSpaces(that){
debugger;
var id= that.id;
var value = that.value;
var replacedValue = value.replaceAll(" ","");
if(replacedValue.length==0){
	bootbox.alert("Field value cannot contain only spaces.");
	$("#"+id).val("");
	return false;
	}else{
	return true;
	}
}

	$('.DoublesOnly').on(
			'keyup',
			function(event) {
			debugger;
				var regex = new RegExp("^\d*\.?\d+$");
				var key = String.fromCharCode(!event.charCode ? event.which
						: event.charCode);
				if (!regex.test(key)) {
					var getkey;
					getkey = $(event.target);
					getkey.val(getkey.val().replace(/[^0-9.]+/g, ""));
					//event.preventDefault();
					return false;
				}
		});


		function DoublePercent(id){
			var benPercent = $('#'+id).val();
			//alert(mystring);
			if($.isNumeric(benPercent)){
				
				mystring=benPercent.replace(/\./g,'');
				// mystring = benPercent.substring(0, 4);
	
				// mystring1 = benPercent.substring(0, 2);
				if(!(mystring.indexOf(".") == 1)){
					//mystring2 = benPercent.replace(/\./g,'').substring(2, 4);
	
					//mystring2=""+mystring1+"."+mystring2+"";
					//alert(mystring2);
					if(benPercent!="" ){
						document.getElementById(id).value=mystring;
					}
				}else{
					$('#'+id).val('');
				}
				
			}else{
				$('#'+id).val('');
			}
		}
		
$('.NumberOnly').keydown(function(e) {
	var regex = new RegExp(/^[^\.\-\+]+$/);
	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	
	const key = event.key; // const {key} = event; ES6+
	if (key === "Backspace") {
	   return true;
	}
	
	if (regex.test(key)) {
		return true;
	} else {
		e.preventDefault();
		return false;
	}
});


$('.NoSpace').keydown(function(e) {
 	debugger;
	var regex = new RegExp(/\S/);
	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	
	const key = event.key; // const {key} = event; ES6+
	if (key === "Backspace") {
	   return true;
	}
	
	if (regex.test(key)) {
		return true;
	} else {
		e.preventDefault();
		return false;
	}
});

function currencyConverter(that, fieldId) {
	if (that != "") {
		var value = that.replaceAll(",", "");
		if (value.includes(".")) {
			var splitValues = value.split(".");
			if (splitValues.length >= 2) {
				if (splitValues[0] == "") {
					splitValues[0] = "0";
				}
				value = splitValues[0] + "." + splitValues[1];
				if (splitValues[1].length == 1) {
					value = splitValues[0] + "." + splitValues[1] + "0";
				}
				if (splitValues[1].length > 2 || splitValues[1] == "") {
					value = splitValues[0] + ".00";
				}
			} else {
				value = splitValues[0];
			}
		} else {
			value = value + ".00";
		}
		//var hiddenFieldId = fieldId.split("Conv")[0];
		$("#" + fieldId + "Converted").val(value);
		value = value.replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
		$("#" + fieldId).val(value);
	}
}

function validateMobileNumber(fieldId,mobNum){
        var filter = /^\d*(?:\.\d{1,2})?$/;
          if (filter.test(mobNum)) {
            if(mobNum.length==10){
             } else {
            	 bootbox.alert('Please put 10  digit mobile number');
            	 $("#"+fieldId).val("");
                return false;
              }
            }
            else {
            	bootbox.alert('Not a valid mobile number');
              $("#"+fieldId).val("");
              
              return false;
           }
	}
	
function aadharValidation(value, fieldId){
debugger;
  var cleanAadhar = value.replace(/[^0-9]/g, '');
   if (cleanAadhar.length === 12) {
   
  }else{
       $("#"+fieldId).val(" ");  
       alert("Aadhar no should be 12 digit");
  }
}
	
	