

am4core.ready(function() {
// Themes begin

am4core.useTheme(am4themes_material);
am4core.useTheme(am4themes_animated);
// Themes end



var chart = am4core.create('eduStatus', am4charts.XYChart)
chart.colors.step = 3;

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

    series.events.on("hidden", arrangeColumns);
    series.events.on("shown", arrangeColumns);

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
  "school going": 25,
  "dropout": 25,
  "college going": 45
}, {
  "category": "Balangir",
  "school going": 15,
  "dropout": 23,
  "college going": 67
}, {
  "category": "Balasore",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Bargarh",
  "school going": 56,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Bhadrak",
  "school going": 25,
  "dropout": 25,
  "college going": 45
}, {
  "category": "Boudh",
  "school going": 15,
  "dropout": 23,
  "college going": 67
}, {
  "category": "Cuttack",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Deogarh",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Dhenkanal",
  "school going": 25,
  "dropout": 25,
  "college going": 45
}, {
  "category": "Gajapati",
  "school going": 15,
  "dropout": 23,
  "college going": 67
}, {
  "category": "Ganjam",
  "school going": 76,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Jagatsinghapur",
  "school going": 66,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Jajpur",
  "school going": 25,
  "dropout": 25,
  "college going": 45
}, {
  "category": "Jharsuguda",
  "school going": 15,
  "dropout": 23,
  "college going": 67
}, {
  "category": "Kalahandi",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Kandhamal",
  "school going": 58,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Kendrapara",
  "school going": 25,
  "dropout": 25,
  "college going": 45
}, {
  "category": "Kendujhar",
  "school going": 15,
  "dropout": 23,
  "college going": 67
}, {
  "category": "Khordha",
  "school going": 75,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Koraput",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Malkangiri",
  "school going": 25,
  "dropout": 25,
  "college going": 45
}, {
  "category": "Mayurbhanj",
  "school going": 15,
  "dropout": 23,
  "college going": 67
}, {
  "category": "Nabarangpur",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Nayagarh",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Nuapada",
  "school going": 25,
  "dropout": 25,
  "college going": 45
}, {
  "category": "Puri",
  "school going": 15,
  "dropout": 23,
  "college going": 67
}, {
  "category": "Rayagada",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Sambalpur",
  "school going": 89,
  "dropout": 30,
  "college going": 59
},
{
  "category": "Sonepur",
  "school going": 25,
  "dropout": 25,
  "college going": 45
}, {
  "category": "Sundargarh",
  "school going": 15,
  "dropout": 23,
  "college going": 67
}
]


createSeries('school going', 'School Going');
createSeries('dropout', 'Dropout');
createSeries('college going', 'College Going');
 chart.logo.disabled = true;
function arrangeColumns() {

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
});