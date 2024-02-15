
am4core.ready(function() {

    // Themes begin
    am4core.useTheme(am4themes_animated);
    // Themes end
    
    // Create chart instance
    var chart = am4core.create("housetype", am4charts.PieChart);
    
    // Add data
    chart.data = [ {
      "country": "AWC",
      "litres": 501.9
    }, {
      "country": "Provided By",
      "litres": 301.9
    }, {
      "country": "Building Status",
      "litres": 201.1
    }, 
    ];
    
    // Add and configure Series
    var pieSeries = chart.series.push(new am4charts.PieSeries());
    pieSeries.dataFields.value = "litres";
    pieSeries.dataFields.category = "country";
    pieSeries.slices.template.stroke = am4core.color("#fff");
    pieSeries.slices.template.strokeOpacity = 1;
    
    // This creates initial animation
    pieSeries.hiddenState.properties.opacity = 1;
    pieSeries.hiddenState.properties.endAngle = -90;
    pieSeries.hiddenState.properties.startAngle = -90;
    
    chart.hiddenState.properties.radius = am4core.percent(0);
    
    
    }); // end am4core.ready()