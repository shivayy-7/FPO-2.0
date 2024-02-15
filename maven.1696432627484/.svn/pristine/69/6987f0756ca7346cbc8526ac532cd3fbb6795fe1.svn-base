
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("distWiseAwareness", am4charts.XYChart);
chart.colors.step = 9;
// Add data
chart.data = [
{
  "category": "Angul",
  "first": 25,
  "second": 25,
}, {
  "category": "Balangir",
  "first": 15,
  "second": 23,
}, {
  "category": "Balasore",
  "first": 89,
  "second": 30,
},
{
  "category": "Bargarh",
  "first": 56,
  "second": 30,
},
{
  "category": "Bhadrak",
  "first": 25,
  "second": 25,
}, {
  "category": "Boudh",
  "first": 15,
  "second": 23,
}, {
  "category": "Cuttack",
  "first": 89,
  "second": 30,
},
{
  "category": "Deogarh",
  "first": 89,
  "second": 30,
},
{
  "category": "Dhenkanal",
  "first": 25,
  "second": 25,
}, {
  "category": "Gajapati",
  "first": 15,
  "second": 23,
}, {
  "category": "Ganjam",
  "first": 76,
  "second": 30,
},
{
  "category": "Jagatsinghapur",
  "first": 66,
  "second": 30,
},
{
  "category": "Jajpur",
  "first": 25,
  "second": 25,
}, {
  "category": "Jharsuguda",
  "first": 15,
  "second": 23,
}, {
  "category": "Kalahandi",
  "first": 89,
  "second": 30,
},
{
  "category": "Kandhamal",
  "first": 58,
  "second": 30,
},
{
  "category": "Kendrapara",
  "first": 25,
  "second": 25,
}, {
  "category": "Kendujhar",
  "first": 15,
  "second": 23,
}, {
  "category": "Khordha",
  "first": 75,
  "second": 30,
},
{
  "category": "Koraput",
  "first": 89,
  "second": 30,
},
{
  "category": "Malkangiri",
  "first": 25,
  "second": 25,
}, {
  "category": "Mayurbhanj",
  "first": 15,
  "second": 23,
}, {
  "category": "Nabarangpur",
  "first": 89,
  "second": 30,
},
{
  "category": "Nayagarh",
  "first": 89,
  "second": 30,
},
{
  "category": "Nuapada",
  "first": 25,
  "second": 25,
}, {
  "category": "Puri",
  "first": 15,
  "second": 23,
}, {
  "category": "Rayagada",
  "first": 89,
  "second": 30,
},
{
  "category": "Sambalpur",
  "first": 89,
  "second": 30,
},
{
  "category": "Sonepur",
  "first": 25,
  "second": 25,
}, {
  "category": "Sundargarh",
  "first": 15,
  "second": 23,
}


];

// Create axes
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "category";
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.minGridDistance = 30;
categoryAxis.renderer.labels.template.horizontalCenter = "right";
categoryAxis.renderer.labels.template.verticalCenter = "middle";
categoryAxis.renderer.labels.template.rotation = 270;
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