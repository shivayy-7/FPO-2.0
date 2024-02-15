
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_material);
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("blockWiseCMC", am4charts.XYChart);
chart.colors.step = 9;

// Add data
chart.data = [{
  "cases": "Angul",
  "Victim Count": 25,
  "Rescued": 25,
  "Pending Cases": 45
}, {
  "cases": "Banarpal",
  "Victim Count": 15,
  "Rescued": 23,
  "Pending Cases": 67
}, {
  "cases": "Chhendipada",
  "Victim Count": 89,
  "Rescued": 30,
  "Pending Cases": 59
},
{
  "cases": "Athamallik",
  "Victim Count": 56,
  "Rescued": 30,
  "Pending Cases": 59
},
{
  "cases": "Kishorenagar",
  "Victim Count": 25,
  "Rescued": 25,
  "Pending Cases": 45
}, {
  "cases": "Pallahara",
  "Victim Count": 15,
  "Rescued": 23,
  "Pending Cases": 67
}, {
  "cases": "Talcher",
  "Victim Count": 89,
  "Rescued": 30,
  "Pending Cases": 59
},
{
  "cases": "Kaniha",
  "Victim Count": 80,
  "Rescued": 28,
  "Pending Cases": 52
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

createSeries("Victim Count", "Victim Count");
createSeries("Rescued", "Rescued");
createSeries("Pending Cases", "Pending Cases");

// Legend
chart.legend = new am4charts.Legend();

});