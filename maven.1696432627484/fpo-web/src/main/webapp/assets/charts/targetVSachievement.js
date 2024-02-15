am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("targetVSachievement");
  root._logo.dispose();

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);


// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(am5xy.XYChart.new(root, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  layout: root.verticalLayout
}));


// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
var legend = chart.children.push(
  am5.Legend.new(root, {
    centerX: am5.p50,
    x: am5.p50
  })
);

var data = [{
  "Agency": "Agency 1",
  "target": 7.5,
  "achievement": 5.5
}, {
  "Agency": "Agency 2",
  "target": 2.5,
  "achievement": 2.5
}, {
  "Agency": "Agency 3",
  "target": 9.5,
  "achievement": 3.5
},{
  "Agency": "Agency 4",
  "target": 1.5,
  "achievement": 9.5
},{
  "Agency": "Agency 5",
  "target": 6.5,
  "achievement": 4.5
},{
  "Agency": "Agency 6",
  "target": 3.5,
  "achievement": 9.5
},{
  "Agency": "Agency 7",
  "target": 3.6,
  "achievement": 2.7
}, {
  "Agency": "Agency 8",
  "target": 2.8,
  "achievement": 2.9
}, {
  "Agency": "Agency 9",
  "target": 2.8,
  "achievement": 2.9
}, {
  "Agency": "Agency 10",
  "target": 2.8,
  "achievement": 2.9
}, {
  "Agency": "Agency 11",
  "target": 2.8,
  "achievement": 2.9
}, {
  "Agency": "Agency 12",
  "target": 2.8,
  "achievement": 2.9
}]


// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
  categoryField: "Agency",
  renderer: am5xy.AxisRendererX.new(root, {
    cellStartLocation: 0.1,
    cellEndLocation: 0.9
  }),
  tooltip: am5.Tooltip.new(root, {})
}));

xAxis.data.setAll(data);

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
  renderer: am5xy.AxisRendererY.new(root, {})
}));


// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
function makeSeries(name, fieldName) {
  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
    name: name,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: fieldName,
    categoryXField: "Agency"
  }));

  series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}: {valueY}",
    width: am5.percent(90),
    tooltipY: 0
  });

  series.data.setAll(data);

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear();

  series.bullets.push(function () {
    return am5.Bullet.new(root, {
      locationY: 0,
      sprite: am5.Label.new(root, {
        text: "{valueY}",
        fill: root.interfaceColors.get("alternativeText"),
        centerY: 0,
        centerX: am5.p50,
        populateText: true
      })
    });
  });

  legend.data.push(series);
}

makeSeries("Target", "target");
makeSeries("Achievement", "achievement");


// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(1000, 100);

}); // end am5.ready()
