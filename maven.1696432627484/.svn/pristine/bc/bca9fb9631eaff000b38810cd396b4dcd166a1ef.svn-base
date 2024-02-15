am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("mpdsc", am4charts.XYChart);
chart.colors.step = 9;
// Add data
chart.data = [{
    "country": "Jan",
    "Lodged DIR": 432,
    "Disposed Off": 355
    
}, {
    "country": "Feb",
    "Disposed Off": 301,
    "Lodged DIR": 375
    
}, {
    "country": "March ",
    "Disposed Off": 299,
    "Lodged DIR": 344
    
},
{
    "country": "April",
    "Lodged DIR": 432,
    "Disposed Off": 355
    
}, {
    "country": "May",
    "Disposed Off": 301,
    "Lodged DIR": 375
    
}, {
    "country": "June ",
    "Disposed Off": 299,
    "Lodged DIR": 344
    
},
{
    "country": "July",
    "Lodged DIR": 432,
    "Disposed Off": 355
    
}, {
    "country": "Aug",
    "Disposed Off": 301,
    "Lodged DIR": 375
    
}, {
    "country": "Sept ",
    "Disposed Off": 299,
    "Lodged DIR": 344
    
},
{
    "country": "Oct",
    "Lodged DIR": 432,
    "Disposed Off": 355
    
}, {
    "country": "Nov",
    "Disposed Off": 301,
    "Lodged DIR": 375
    
}, {
    "country": "Dec ",
    "Disposed Off": 299,
    "Lodged DIR": 344
    
}];

// Create axes
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "country";
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.minGridDistance = 30;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
//valueAxis.title.text = "KM";
valueAxis.title.fontWeight = 800;

// Create series
var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.valueY = "Disposed Off";
series.dataFields.categoryX = "country";
series.clustered = false;
series.tooltipText = "Disposed Off: [bold]{valueY} ";

var series2 = chart.series.push(new am4charts.ColumnSeries());
series2.dataFields.valueY = "Lodged DIR";
series2.dataFields.categoryX = "country";
series2.clustered = false;
series2.columns.template.width = am4core.percent(50);
series2.tooltipText = "Raised DIRs: [bold]{valueY} ";

chart.cursor = new am4charts.XYCursor();
chart.cursor.lineX.disabled = true;
chart.cursor.lineY.disabled = true;
chart.logo.disabled = true;
}); // end am4core.ready()