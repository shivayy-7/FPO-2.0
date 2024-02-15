
am4core.ready(function() {
// Create chart instance
var chart = am4core.create("shcompStatus", am4charts.PieChart);

// Add data
chart.data = [{
  "country": "Disposed Off",
  "litres": 34
}, {
  "country": "Pending ",
  "litres": 51
}];

// Add and configure Series
var pieSeries = chart.series.push(new am4charts.PieSeries());
pieSeries.dataFields.value = "litres";
pieSeries.dataFields.category = "country";

pieSeries.ticks.template.disabled = true;
pieSeries.alignLabels = false;
pieSeries.labels.template.text = "{value.percent.formatNumber('#.0')}%";
pieSeries.labels.template.radius = am4core.percent(100);
pieSeries.labels.template.fill = am4core.color("white");
chart.logo.disabled = true;
chart.legend = new am4charts.Legend();
});