class monthWiseMPR_status {
	
	constructor(apiPath, contextPath)
	{
		this.apiPath =  apiPath;
		this.worker = null;
		this.contextPath = contextPath;
		this.data = null;
		this.context = "STATE";
		
	}
	
	doFetch() {
		
		if (this.worker == null )
		{
			this.worker = new Worker(this.contextPath + '/assets/appJs/CommonWorker.js');
			this.worker.addEventListener('message', (e) => this.processData(e));
		}
		var	fnyear=$("#finId").val();
		var	cmpcode=$("#cmpId").val();
		var	actcode=$("#activity").val();
		let url = this.contextPath + this.apiPath + "dashCollector" ;
		url = url +  "?finYear="+fnyear;
		url = url + "&cmpCode="+cmpcode;
		url = url + "&actCode="+(actcode !='0' ? actcode : 'NA');
		url = url + "&subActCode=NA";
		url = url + "&chartCall=MNT-WISE-MPR";
		window.loadCounter++;
		this.worker.postMessage({
			"method" : "GET" ,
			"url" : url ,
			"payload" : null
		});
	}
	
	
	processData(e){
		if (e.data.outcome == true)
		{
			this.data = e.data.data;
			
			// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("monthWiseMPR_status", am4charts.XYChart3D);

chart.logo.disabled = true;

chart.data = [];
			  
// Cursor
this.data.forEach((v, i, ar) => {
	let entryArticleData = {};
	entryArticleData.month = v.statusCall;
	entryArticleData.value = v.dataOne;
	chart.data.push(entryArticleData);
});

// Create axes
let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "month";
categoryAxis.renderer.labels.template.rotation = -45;
categoryAxis.renderer.labels.template.hideOversized = false;
categoryAxis.renderer.minGridDistance = 10;
categoryAxis.renderer.labels.template.horizontalCenter = "right";
categoryAxis.renderer.labels.template.verticalCenter = "top";
categoryAxis.tooltip.label.rotation = -45;
categoryAxis.tooltip.label.horizontalCenter = "right";
categoryAxis.tooltip.label.verticalCenter = "top";

let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.title.text = "Months";
valueAxis.title.fontWeight = "bold";

// Create series
var series = chart.series.push(new am4charts.ColumnSeries3D());
series.dataFields.valueY = "value";
series.dataFields.categoryX = "month";
series.name = "value";
series.tooltipText = "{categoryX}: [bold]{valueY}[/]";
series.columns.template.fillOpacity = .8;

var columnTemplate = series.columns.template;
columnTemplate.strokeWidth = 2;
columnTemplate.strokeOpacity = 1;
columnTemplate.stroke = am4core.color("#FFFFFF");

columnTemplate.adapter.add("fill", function(fill, target) {
  return chart.colors.getIndex(target.dataItem.index);
})

columnTemplate.adapter.add("stroke", function(stroke, target) {
  return chart.colors.getIndex(target.dataItem.index);
})

chart.cursor = new am4charts.XYCursor();
chart.cursor.lineX.strokeOpacity = 0;
chart.cursor.lineY.strokeOpacity = 0;
				  
				  
		}
		window.loadCounter--;
	}
}

