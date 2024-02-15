//create a ajax call for  getting activity list by head code
const contextPath = window.contextPath;
function getActivityListByHeadCode(headCode) {
    $("#activity").empty().append("<option value='0'>--- Select Activity ---</option>");
    $("#subActivity").empty().append("<option value='0'>--- Select Sub Activity ---</option>");

    let html="<option value='0'>--- Select Activity ---</option>";
     $('#loader').removeClass('hidden');
$.get( contextPath+"/articleAjax/getActivityList", { headCode: headCode } )
  .done(function( data ) {
   $('#loader').addClass('hidden');
    //make a for loop for data 
    data.forEach(element => {
        html = html + "<option value='"+element.actCode+"'>"+element.actNameEn+"</option>";
    });

    $("#activity").empty().append(html);

    
  })
  .fail(function() {
	$('#loader').addClass('hidden');
    alert( "error" );
  });
}

function getSubActivityListByActCode(actCode) {

    $("#subActivity").empty().append("<option value='0'>--- Select Sub Activity ---</option>");

    let html="<option value='0'>--- Select Sub Activity ---</option>";
     $('#loader').removeClass('hidden');
$.get( contextPath+"/articleAjax/getSubActivityList", { actCode: actCode } )
  .done(function( data ) {
   $('#loader').addClass('hidden');
    //make a for loop for data 
    data.forEach(element => {
        html = html + "<option value='"+element.subActCode+"'>"+element.subActNameEn+"</option>";
    });

    $("#subActivity").empty().append(html);

    
  })
  .fail(function() {
	$('#loader').addClass('hidden');
    alert( "error" );
  });
}