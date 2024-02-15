
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

var chart = am4core.create("daysnotfunction", am4charts.XYChart);
chart.padding(40, 40, 40, 40);

var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.dataFields.category = "AWC Name";
categoryAxis.renderer.minGridDistance = 1;
categoryAxis.renderer.inversed = true;
categoryAxis.renderer.grid.template.disabled = true;

var valueAxis = chart.xAxes.push(new am4charts.ValueAxis());
valueAxis.min = 0;

var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.categoryY = "AWC Name";
series.dataFields.valueX = "Days";
series.tooltipText = "{valueX.value}"
series.columns.template.strokeOpacity = 0;
series.columns.template.column.cornerRadiusBottomRight = 5;
series.columns.template.column.cornerRadiusTopRight = 5;

var labelBullet = series.bullets.push(new am4charts.LabelBullet())
labelBullet.label.horizontalCenter = "left";
labelBullet.label.dx = 10;
labelBullet.label.text = "{values.valueX.workingValue.formatNumber('#')}";
labelBullet.locationX = 1;

// as by default columns of the same series are of the same color, we add adapter which takes colors from chart.colors color set
series.columns.template.adapter.add("fill", function(fill, target){
  return chart.colors.getIndex(target.dataItem.index);
});

categoryAxis.sortBySeries = series;
chart.data = [
    {
      "AWC Name": "AWC_AA",
      "Days": 100
    },
    {
      "AWC Name": "AWC_BB",
      "Days": 39
    },
    {
      "AWC Name": "AWC_CC",
      "Days": 101
    },
    {
      "AWC Name": "AWC_DD",
      "Days": 44
    }
  ]



}); // end am4core.ready()