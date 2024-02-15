/**
 * ---------------------------------------
 * This demo was created using amCharts 4.
 * 
 * For more information visit:
 * https://www.amcharts.com/
 * 
 * Documentation is available at:
 * https://www.amcharts.com/docs/v4/
 * ---------------------------------------
 */

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("total_value_of_items_project_wise", am4charts.XYChart);

// Add data
chart.data = [{
  "country": "Project1",
  "visits": 42
}, {
  "country": "Project2",
  "visits": 25
}, {
  "country": "Project3",
  "visits": 38
}, {
  "country": "Project4",
  "visits": 10
}];


// Modify chart's colors
chart.colors.list = [
  am4core.color("#067b6a"),
  am4core.color("#99402b"),
  am4core.color("#eeb137"),
  am4core.color("#FF9671"),
  am4core.color("#FFC75F"),
  am4core.color("#F9F871"),
];
// Create axes

var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "country";
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.minGridDistance = 30;

categoryAxis.renderer.labels.template.adapter.add("dy", function(dy, target) {
  if (target.dataItem && target.dataItem.index & 2 == 2) {
    return dy + 25;
  }
  return dy;
});

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

// Create series
var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.valueY = "visits";
series.dataFields.categoryX = "country";
series.name = "Visits";
series.columns.template.tooltipText = "{categoryX}: [bold]{valueY}[/]";
series.columns.template.fillOpacity = .9;
series.columns.template.width = am4core.percent(20);

var columnTemplate = series.columns.template;
columnTemplate.strokeWidth = 2;
columnTemplate.strokeOpacity = 1;

