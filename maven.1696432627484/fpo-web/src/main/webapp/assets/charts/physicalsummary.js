class physicalsummary {
	
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
		url = url + "&chartCall=PHY-PRG-SUM";
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

var chart = am4core.create("physicalsummary", am4charts.XYChart);
chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect
chart.logo.disabled = true;

// Add data
chart.data = [];
			  
			// Cursor
				this.data.forEach((v, i, ar) => {
					let entryArticleData = {};
					entryArticleData.country = v.statusCall;
					entryArticleData.value = v.dataOne;
					chart.data.push(entryArticleData);
				});


var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.dataFields.category = "country";
categoryAxis.renderer.minGridDistance = 40;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

var series = chart.series.push(new am4charts.CurvedColumnSeries());
series.dataFields.categoryX = "country";
series.dataFields.valueY = "value";
series.tooltipText = "{valueY.value}"
series.columns.template.strokeOpacity = 0;

series.columns.template.fillOpacity = 0.75;

var hoverState = series.columns.template.states.create("hover");
hoverState.properties.fillOpacity = 1;
hoverState.properties.tension = 0.4;

chart.cursor = new am4charts.XYCursor();

// Add distinctive colors for each column using adapter
series.columns.template.adapter.add("fill", function(fill, target) {
  return chart.colors.getIndex(target.dataItem.index);
});
				  
				  
		}
		window.loadCounter--;
	}
}

