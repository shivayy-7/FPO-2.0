am5.ready(function() {

  // Create root element
  // https://www.amcharts.com/docs/v5/getting-started/#Root_element
  var root = am5.Root.new("fundSummeryCM");
  root._logo.dispose();
    
  
  // Create chart
  // https://www.amcharts.com/docs/v5/charts/xy-chart/
  var chart = root.container.children.push(am5xy.XYChart.new(root, {
    panX: true,
    panY: true,
    wheelX: "panX",
    wheelY: "panY",
    pinchZoomX:false,
    paddingRight: 0,
    paddingTop: 0,
    paddingBottom: 20,
    paddingLeft: 0,
  }));
  
  // Add cursor
  // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
  var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
  cursor.lineY.set("visible", false);
  
  
  // Create axes
  // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
  var xRenderer = am5xy.AxisRendererX.new(root, { minGridDistance: 0 });
  xRenderer.labels.template.setAll({
    rotation: 0,
    centerY: am5.p10,
    centerX: am5.p10,
    paddingRight: 0
  });
  
  var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
    maxDeviation: 0.3,
    categoryField: "Month",
    renderer: xRenderer,
    tooltip: am5.Tooltip.new(root, {})
  }));
  
  var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
    maxDeviation: 0.3,
    renderer: am5xy.AxisRendererY.new(root, {})
  }));
  
  
  // Create series
  // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
    name: "Series 1",
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "value",
    sequencedInterpolation: false,
    categoryXField: "Month",
    tooltip: am5.Tooltip.new(root, {
      labelText:"Fund {Month} : {value}"
    })
  }));
  
  series.columns.template.setAll({ cornerRadiusTL: 5, cornerRadiusTR: 5 });
  series.columns.template.adapters.add("fill", function(fill, target) {
    return chart.get("colors").getIndex(series.columns.indexOf(target));
  });
  
  series.columns.template.adapters.add("stroke", function(stroke, target) {
    return chart.get("colors").getIndex(series.columns.indexOf(target));
  });
  
  yAxis.get("renderer").labels.template.setAll({
    visible: false
  });
  // Set data
  var data = [{
    Month: "Jan",
    value: 100
  }, {
    Month: "Feb",
    value: 80
  }, {
    Month: "Mar",
    value: 50
  }, {
    Month: "Apr",
    value: 40
  }, {
    Month: "May",
    value: 20
  }, {
    Month: "Jun",
    value: 70
  }, {
    Month: "Jul",
    value: 90
  }, {
    Month: "Aug",
    value: 60
  }, {
    Month: "Sep",
    value: 30
  }, {
    Month: "Oct",
    value: 50
  }, {
    Month: "Nov",
    value: 20
  }, {
    Month: "Dec",
    value: 20
  }];
  
  xAxis.data.setAll(data);
  series.data.setAll(data);
  
/*  chart.get("colors").set("colors", [
    am5.color(0x13ff00),
    am5.color(0xffb900),
    am5.color(0x5aaa95),
    am5.color(0x86a873),
    am5.color(0xbb9f06)
  ]);*/
  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear(1000);
  chart.appear(1000, 100);
  
  }); // end am5.ready()