
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("blockWiseAwareness", am4charts.XYChart);
chart.colors.step = 9;
// Add data
chart.data = [
{
  "category": "Angul",
  "first": 25,
  "second": 25,
}, {
  "category": "Banarpal",
  "first": 15,
  "second": 23,
}, {
  "category": "Chhendipada",
  "first": 89,
  "second": 30,
},
{
  "category": "Athamallik",
  "first": 56,
  "second": 30,
},
{
  "category": "Kishorenagar",
  "first": 25,
  "second": 25,
}, {
  "category": "Pallahara",
  "first": 15,
  "second": 23,
}, {
  "category": "Talcher",
  "first": 89,
  "second": 30,
},
{
  "category": "Kaniha",
  "first": 70,
  "second": 20,
}
];

// Create axes
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "category";
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.minGridDistance = 30;
categoryAxis.renderer.labels.template.horizontalCenter = "right";
categoryAxis.renderer.labels.template.verticalCenter = "middle";
categoryAxis.renderer.labels.template.rotation = -45;
categoryAxis.tooltip.disabled = false;
categoryAxis.renderer.minHeight = 110;
categoryAxis.renderer.cellStartLocation = 0.1;
categoryAxis.renderer.cellEndLocation = 0.9;
var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.renderer.minWidth = 50;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
//valueAxis.title.text = "KM";
valueAxis.title.fontWeight = 800;

// Create series
var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.valueY = "first";
series.dataFields.categoryX = "category";
series.clustered = false;
series.tooltipText = "For Employees: [bold]{valueY}";

var series2 = chart.series.push(new am4charts.ColumnSeries());
series2.dataFields.valueY = "second";
series2.dataFields.categoryX = "category";
series2.clustered = false;
series2.columns.template.width = am4core.percent(50);
series2.tooltipText = "For Committee Members: [bold]{valueY}";

chart.cursor = new am4charts.XYCursor();
chart.cursor.lineX.disabled = true;
chart.cursor.lineY.disabled = true;
chart.logo.disabled = true;

}); // end am4core.ready()