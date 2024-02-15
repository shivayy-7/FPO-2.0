am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_material);
am4core.useTheme(am4themes_animated);
// Themes end

var chart = am4core.create("top5", am4charts.XYChart);

var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.dataFields.category = "network";
categoryAxis.renderer.minGridDistance = 1;
categoryAxis.renderer.inversed = true;
categoryAxis.renderer.grid.template.disabled = true;

var valueAxis = chart.xAxes.push(new am4charts.ValueAxis());
valueAxis.min = 0;

var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.categoryY = "network";
series.dataFields.valueX = "MAU";
series.tooltipText = "{valueX.value}"
series.columns.template.strokeOpacity = 0;
series.columns.template.column.cornerRadiusBottomRight = 5;
series.columns.template.column.cornerRadiusTopRight = 5;

var labelBullet = series.bullets.push(new am4charts.LabelBullet())
labelBullet.label.horizontalCenter = "left";
labelBullet.label.dx = 10;
labelBullet.label.text = "{values.valueX.workingValue}";
labelBullet.locationX = 1;

// as by default columns of the same series are of the same color, we add adapter which takes colors from chart.colors color set
series.columns.template.adapter.add("fill", function(fill, target){
  return chart.colors.getIndex(target.dataItem.index);
});

categoryAxis.sortBySeries = series;
 chart.logo.disabled = true;
 chart.cursor = new am4charts.XYCursor();
chart.data = [
    {
      "network": "Ganjam ",
      "MAU": 120
    },
    {
      "network": "Boudh",
      "MAU": 105
    },
    {
      "network": "Kendrapara",
      "MAU": 90
    },
    {
      "network": "Sambalpur",
      "MAU": 75
    },
    {
      "network": "Koraput",
      "MAU": 58
    }
  ]

}); // end am4core.ready()