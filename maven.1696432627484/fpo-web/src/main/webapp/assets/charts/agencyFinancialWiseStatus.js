class agencyFinancialWiseStatus {
	
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
		url = url + "&chartCall=AGN-FIN-WISE";
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
var chart = am4core.create("agencyFinancialWiseStatus", am4charts.XYChart);

chart.data = [];
			  
// Cursor
this.data.forEach((v, i, ar) => {
	let entryArticleData = {};
	entryArticleData.AgencyName = v.statusCall;
	entryArticleData.demanded = v.dataOne;
	entryArticleData.disbursed = v.dataTwo;
	entryArticleData.utilized = v.dataThree;
	chart.data.push(entryArticleData);
});

// Create axes
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.renderer.labels.template.horizontalCenter = "right";
categoryAxis.renderer.labels.template.verticalCenter = "middle";
categoryAxis.renderer.labels.template.rotation = -45;
categoryAxis.renderer.minHeight = 110;

categoryAxis.dataFields.category = "AgencyName";
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.minGridDistance = 10;
categoryAxis.renderer.cellStartLocation = 0.1;
categoryAxis.renderer.cellEndLocation = 0.6;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.title.text = "Agency Name";
valueAxis.title.fontWeight = 600;

// Create series
var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.valueY = "demanded";
series.dataFields.categoryX = "AgencyName";
series.tooltipText = "Demanded: [bold]{valueY}[/]";

var series2 = chart.series.push(new am4charts.ColumnSeries());
series2.dataFields.valueY = "disbursed";
series2.dataFields.categoryX = "AgencyName";
series2.columns.template.width = am4core.percent(50);
series2.tooltipText = "Disbursed : [bold]{valueY}[/]";

var series3 = chart.series.push(new am4charts.ColumnSeries());
series3.dataFields.valueY = "utilized";
series3.dataFields.categoryX = "AgencyName";
series3.columns.template.width = am4core.percent(50);
series3.tooltipText = "Utilized : [bold]{valueY}[/]";

chart.cursor = new am4charts.XYCursor();
chart.cursor.lineX.disabled = true;
chart.cursor.lineY.disabled = true;

chart.colors.list = [
  am4core.color("#ffa500"),
  am4core.color("#fb5607"),
  am4core.color("#009688"),
  am4core.color("#2bb907"),
  am4core.color("#3cb44b"),
  am4core.color("#42d4f4"),
  am4core.color("#4363d8"),
  am4core.color("#911eb4"),
  am4core.color("#f032e6")
];
chart.logo.disabled = true;
}
		window.loadCounter--;
	}
}