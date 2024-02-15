
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_material);
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("blockWisePS", am4charts.XYChart);
chart.colors.step = 9;

// Add data
chart.data = [{
  "cases": "Angul",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Banarpal",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Chhendipada",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Athamallik",
  "By CMPO": 56,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Kishorenagar",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Pallahara",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Talcher",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Kaniha",
  "By CMPO": 42,
  "By Collector": 28,
  "By Childline": 31
}];

// Create axes
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "cases";
categoryAxis.renderer.grid.template.location = 0;


var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.renderer.inside = true;
valueAxis.renderer.labels.template.disabled = true;
valueAxis.min = 0;

// Create series
function createSeries(field, name) {
  
  // Set up series
  var series = chart.series.push(new am4charts.ColumnSeries());
  series.name = name;
  series.dataFields.valueY = field;
  series.dataFields.categoryX = "cases";
  series.sequencedInterpolation = true;
  
  // Make it stacked
  series.stacked = true;
  
  // Configure columns
  series.columns.template.width = am4core.percent(60);
  series.columns.template.tooltipText = "[bold]{name}[/]\n[font-size:14px]{categoryX}: {valueY}";
  
  // Add label
  var labelBullet = series.bullets.push(new am4charts.LabelBullet());
  labelBullet.label.text = "{valueY}";
  labelBullet.locationY = 0.5;
  labelBullet.label.hideOversized = true;
  chart.logo.disabled = true;
  return series;
}

createSeries("By CMPO", "By CMPO");
createSeries("By Collector", "By Collector");
createSeries("By Childline", "By Childline");

// Legend
chart.legend = new am4charts.Legend();

});