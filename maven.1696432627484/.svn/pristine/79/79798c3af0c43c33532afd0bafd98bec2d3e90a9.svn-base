
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("componentWiseBeneficiary");
  root._logo.dispose();

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);



// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(am5xy.XYChart.new(root, {
  panX: true,
  panY: true,
  wheelX: "panX",
  wheelY: "zoomX",
  layout: root.verticalLayout,
  pinchZoomX:true
}));

// Add cursor
// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
  behavior: "none"
}));
cursor.lineY.set("visible", false);

var colorSet = am5.ColorSet.new(root, {});

// The data
var data = [
  {
    component: "Component 1",
    beneficiary: 23.5,
    strokeSettings: {
      stroke: colorSet.getIndex(0)
    },
    fillSettings: {
      fill: colorSet.getIndex(0),
    },
    bulletSettings: {
      fill: colorSet.getIndex(0)
    }
  },
  {
    component: "Component 2",
    beneficiary: 26,
    bulletSettings: {
      fill: colorSet.getIndex(0)
    }
  },
  {
    component: "Component 3",
    beneficiary: 30,
    bulletSettings: {
      fill: colorSet.getIndex(0)
    }
  },
  {
    component: "Component 4",
    beneficiary: 20,
    bulletSettings: {
      fill: colorSet.getIndex(0)
    }
  },
  {
    component: "Component 5",
    beneficiary: 30,
    strokeSettings: {
      stroke: colorSet.getIndex(3)
    },
    fillSettings: {
      fill: colorSet.getIndex(3),
    },
    bulletSettings: {
      fill: colorSet.getIndex(3)
    }
  },
  {
    component: "Component 6",
    beneficiary: 30,
    bulletSettings: {
      fill: colorSet.getIndex(3)
    }
  },
  {
    component: "Component 7",
    beneficiary: 31,
    bulletSettings: {
      fill: colorSet.getIndex(3)
    }
  },
  {
    component: "Component 8",
    beneficiary: 34,
    strokeSettings: {
      stroke: colorSet.getIndex(6)
    },
    fillSettings: {
      fill: colorSet.getIndex(6),
    },
    bulletSettings: {
      fill: colorSet.getIndex(6)
    }
  },
  {
    component: "Component 9",
    beneficiary: 33,
    bulletSettings: {
      fill: colorSet.getIndex(6)
    }
  },
  {
    component: "Component 10",
    beneficiary: 34,
    bulletSettings: {
      fill: colorSet.getIndex(6)
    }
  },
  {
    component: "Component 11",
    beneficiary: 36,
    bulletSettings: {
      fill: colorSet.getIndex(6)
    }
  }
];

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xRenderer = am5xy.AxisRendererX.new(root, {});
xRenderer.grid.template.set("location", 0.5);
xRenderer.labels.template.setAll({
  location: 0.5,
  multiLocation: 0.5
});

var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
  categoryField: "component",
  renderer: xRenderer,
  tooltip: am5.Tooltip.new(root, {})
}));

xAxis.data.setAll(data);

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
  maxPrecision: 0,
  renderer: am5xy.AxisRendererY.new(root, {})
}));

var series = chart.series.push(am5xy.LineSeries.new(root, {
  xAxis: xAxis,
  yAxis: yAxis,
  valueYField: "beneficiary",
  categoryXField: "component",
  tooltip: am5.Tooltip.new(root, {
    labelText: "Beneficiary: {valueY}",
    dy:-5
  })
}));

series.strokes.template.setAll({
  templateField: "strokeSettings",
  strokeWidth: 2
});

series.fills.template.setAll({
  visible: true,
  fillOpacity: 0.5,
  templateField: "fillSettings"
});


series.bullets.push(function() {
  return am5.Bullet.new(root, {
    sprite: am5.Circle.new(root, {
      templateField: "bulletSettings",
      radius: 5
    })
  });
});

series.data.setAll(data);
series.appear(1000);

// Add scrollbar
// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
chart.set("scrollbarX", am5.Scrollbar.new(root, {
  /*orientation: "horizontal",*/
  marginBottom: 20
}));

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(1000, 100);

}); // end am5.ready()