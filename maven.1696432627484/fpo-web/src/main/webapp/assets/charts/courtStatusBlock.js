

// Themes begin
am4core.useTheme(am4themes_material);
am4core.useTheme(am4themes_animated);
// Themes end



var chart = am4core.create('courtStatusBlock', am4charts.XYChart)
chart.colors.step = 9;

chart.legend = new am4charts.Legend()
chart.legend.position = 'bottom'
chart.legend.paddingBottom = 10
chart.legend.labels.template.maxWidth = 200

var xAxis = chart.xAxes.push(new am4charts.CategoryAxis())
xAxis.dataFields.category = 'category'
xAxis.renderer.cellStartLocation = 0.1
xAxis.renderer.cellEndLocation = 0.9
xAxis.renderer.grid.template.location = 0;

var yAxis = chart.yAxes.push(new am4charts.ValueAxis());
yAxis.min = 0;

function createSeries(value, name) {
    var series = chart.series.push(new am4charts.ColumnSeries())
    series.dataFields.valueY = value
    series.dataFields.categoryX = 'category'
    series.name = name

    series.events.on("hidden", arngColm);
    series.events.on("shown", arngColm);

    /*var bullet = series.bullets.push(new am4charts.LabelBullet())
    bullet.interactionsEnabled = false
    bullet.dy = 30;
    bullet.label.text = '{valueY}'
    bullet.label.fill = am4core.color('#ffffff')*/

    return series;
}

chart.data = [
    {
  "category": "Angul",
  "submitted to court": 25,
  "punishment order": 25,
  "maintenance order": 45
}, {
  "category": "Banarpal",
  "submitted to court": 15,
  "punishment order": 23,
  "maintenance order": 67
}, {
  "category": "Chhendipada",
  "submitted to court": 89,
  "punishment order": 30,
  "maintenance order": 59
},
{
  "category": "Athamallik",
  "submitted to court": 56,
  "punishment order": 30,
  "maintenance order": 59
},
{
  "category": "Kishorenagar",
  "submitted to court": 25,
  "punishment order": 25,
  "maintenance order": 45
}, {
  "category": "Pallahara",
  "submitted to court": 15,
  "punishment order": 23,
  "maintenance order": 67
}, {
  "category": "Talcher",
  "submitted to court": 89,
  "punishment order": 30,
  "maintenance order": 59
},
{
  "category": "Kaniha",
  "submitted to court": 89,
  "punishment order": 30,
  "maintenance order": 59
}
]


createSeries('submitted to court', 'Submitted to Court');
createSeries('punishment order', 'Punishment Order');
createSeries('maintenance order', 'Maintenance Order');
 chart.logo.disabled = true;
function arngColm() {

    var series = chart.series.getIndex(0);
 
    var w = 1 - xAxis.renderer.cellStartLocation - (1 - xAxis.renderer.cellEndLocation);
    if (series.dataItems.length > 1) {
        var x0 = xAxis.getX(series.dataItems.getIndex(0), "categoryX");
        var x1 = xAxis.getX(series.dataItems.getIndex(1), "categoryX");
        series.columns.template.tooltipText = "[bold]{name}[/]\n[font-size:14px]{categoryX}: {valueY}";
        var delta = ((x1 - x0) / chart.series.length) * w;
        if (am4core.isNumber(delta)) {
            var middle = chart.series.length / 2;
series.columns.template.tooltipText = "[bold]{name}[/]\n[font-size:14px]{categoryX}: {valueY}";
            var newIndex = 0;
            chart.series.each(function(series) {
                if (!series.isHidden && !series.isHiding) {
                    series.dummyData = newIndex;
                    newIndex++;
                }
                else {
                    series.dummyData = chart.series.indexOf(series);
                }
            })
            var visibleCount = newIndex;
            var newMiddle = visibleCount / 2;

            chart.series.each(function(series) {
                var trueIndex = chart.series.indexOf(series);
                var newIndex = series.dummyData;
series.columns.template.tooltipText = "[bold]{name}[/]\n[font-size:14px]{categoryX}: {valueY}";
                var dx = (newIndex - trueIndex + middle - newMiddle) * delta

                series.animate({ property: "dx", to: dx }, series.interpolationDuration, series.interpolationEasing);
                series.bulletsContainer.animate({ property: "dx", to: dx }, series.interpolationDuration, series.interpolationEasing);
            })
        }
    }
}