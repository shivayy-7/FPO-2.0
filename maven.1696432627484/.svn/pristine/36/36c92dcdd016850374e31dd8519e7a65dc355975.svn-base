am4core.ready(function() {
am4core.useTheme(am4themes_animated);
// Create chart instance
var chart = am4core.create("dispStatus", am4charts.PieChart);
//chart.colors.step = 7;
// Add data
chart.data = [{
  "country": "Provided With Medical Aid",
  "litres": 34
}, {
  "country": "Provided With Shelters",
  "litres": 51
}, {
  "country": "Provided With Legal Aid",
  "litres": 25
}];

// Add and configure Series
var pieSeries = chart.series.push(new am4charts.PieSeries());
pieSeries.dataFields.value = "litres";
pieSeries.dataFields.category = "country";

pieSeries.ticks.template.disabled = true;
pieSeries.alignLabels = false;
pieSeries.labels.template.text = "{value.percent.formatNumber('#.0')}%";
pieSeries.labels.template.radius = am4core.percent(-40);
pieSeries.labels.template.fill = am4core.color("white");
chart.logo.disabled = true;
chart.legend = new am4charts.Legend();
}); 