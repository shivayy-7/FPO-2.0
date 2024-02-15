
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_material);
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("distWisePS", am4charts.XYChart);
chart.colors.step = 3;

// Add data
chart.data = [{
  "cases": "Angul",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Balangir",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Balasore",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Bargarh",
  "By CMPO": 56,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Bhadrak",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Boudh",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Cuttack",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Deogarh",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Dhenkanal",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Gajapati",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Ganjam",
  "By CMPO": 76,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Jagatsinghapur",
  "By CMPO": 66,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Jajpur",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Jharsuguda",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Kalahandi",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Kandhamal",
  "By CMPO": 58,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Kendrapara",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Kendujhar",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Khordha",
  "By CMPO": 75,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Koraput",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Malkangiri",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Mayurbhanj",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Nabarangpur",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Nayagarh",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Nuapada",
  "By CMPO": 25,
  "By Collector": 25,
  "By Childline": 45
}, {
  "cases": "Puri",
  "By CMPO": 15,
  "By Collector": 23,
  "By Childline": 29
}, {
  "cases": "Rayagada",
  "By CMPO": 42,
  "By Collector": 30,
  "By Childline": 36
},
{
  "cases": "Sambalpur",
  "By CMPO": 32,
  "By Collector": 20,
  "By Childline": 31
},
{
  "cases": "Sonepur",
  "By CMPO": 25,
  "By Collector": 29,
  "By Childline": 42
}, {
  "cases": "Sundargarh",
  "By CMPO": 18,
  "By Collector": 23,
  "By Childline": 29
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