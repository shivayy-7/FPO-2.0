
am4core.ready(function() {

// Themes begin
//am4core.useTheme(am4themes_material);
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("monthWiseDIR", am4charts.XYChart);
chart.colors.step = 7;

// Add data
chart.data = [{
  "cases": "January",
  "Lodged With PO": 20,
  "Lodged With SP": 25,
  "Sent to Court": 45,
  "Forwarded to Magistrate": 25,
}, {
  "cases": "February",
  "Lodged With PO": 15,
  "Lodged With SP": 23,
  "Sent to Court": 67,
  "Forwarded to Magistrate": 29,
}, {
  "cases": "March",
  "Lodged With PO": 89,
  "Lodged With SP": 30,
  "Sent to Court": 59,
  "Forwarded to Magistrate": 34,
},
{
  "cases": "April",
  "Lodged With PO": 56,
  "Lodged With SP": 30,
  "Sent to Court": 44,
  "Forwarded to Magistrate": 18,
},
{
  "cases": "May",
  "Lodged With PO": 32,
  "Lodged With SP": 25,
  "Sent to Court": 45,
  "Forwarded to Magistrate": 21,
}, {
  "cases": "June",
  "Lodged With PO": 15,
  "Lodged With SP": 23,
  "Sent to Court": 67,
  "Forwarded to Magistrate": 12,
}, {
  "cases": "July",
  "Lodged With PO": 89,
  "Lodged With SP": 30,
  "Sent to Court": 45,
  "Forwarded to Magistrate": 25,
},
{
  "cases": "August",
  "Lodged With PO": 65,
  "Lodged With SP": 30,
  "Sent to Court": 50,
  "Forwarded to Magistrate": 22,
},
{
  "cases": "September",
  "Lodged With PO": 25,
  "Lodged With SP": 36,
  "Sent to Court": 45,
  "Forwarded to Magistrate": 32,
}, {
  "cases": "October",
  "Lodged With PO": 15,
  "Lodged With SP": 23,
  "Sent to Court": 67,
  "Forwarded to Magistrate": 25,
}, {
  "cases": "November",
  "Lodged With PO": 76,
  "Lodged With SP": 30,
  "Sent to Court": 50,
  "Forwarded to Magistrate": 26,
},
{
  "cases": "December",
  "Lodged With PO": 66,
  "Lodged With SP": 30,
  "Sent to Court": 52,
  "Forwarded to Magistrate": 71,
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

createSeries("Lodged With PO", "Lodged With PO");
createSeries("Lodged With SP", "Lodged With SP");
createSeries("Sent to Court", "Sent to Court");
createSeries("Forwarded to Magistrate", "Forwarded to Magistrate");

// Legend
chart.legend = new am4charts.Legend();

});