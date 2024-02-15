am4core.ready(function() {

    // Themes begin
    am4core.useTheme(am4themes_animated);
    // Themes end
    
    // Create chart instance
    var chart = am4core.create("finanancialyearwiseawc", am4charts.XYChart);
    //chart.scrollbarX = new am4core.Scrollbar();
    
    // Add data
    chart.data = [ {
      "country": "Angul",
      "visits": 25,
      }, {
      "country": "Balangir",
      "visits": 26,
      }, {
      "country": "Balasore",
      "visits": 28,
      }, {
      "country": "Bargarh",
      "visits": 22,
      }, {
      "country": "Bhadrak",
      "visits": 27,
      }, {
      "country": "Boudh",
      "visits": 32,
      }, {
      "country": "Cuttack",
      "visits": 28,
      }, {
      "country": "Deogarh",
      "visits": 20,
      }, {
      "country": "Dhenkanal",
      "visits": 23,
      }, {
      "country": "Gajapati",
      "visits": 32,
      }, {
      "country": "Ganjam",
      "visits": 26,
      }, {
      "country": "Jagatsinghapur",
      "visits": 35,
      }, {
      "country": "Jajpur",
      "visits": 28,
      }, {
      "country": "Jharsuguda",
      "visits": 26,
      }, {
      "country": "Kalahandi",
      "visits": 26,
      }, {
      "country": "Kandhamal",
      "visits": 36,
      }, {
      "country": "Kendrapara",
      "visits": 38,
      }, {
      "country": "Kendujhar",
      "visits": 36,
      }, {
      "country": "Khordha",
      "visits": 28,
      }, {
      "country": "Koraput",
      "visits": 22,
      }, {
      "country": "Malkangiri",
      "visits": 15,
      }, {
      "country": "Mayurbhanj",
      "visits": 16,
      }, {
      "country": "Nabarangpur",
      "visits": 29,
      }, {
      "country": "Nayagarh",
      "visits": 28,
      }, {
      "country": "Nuapada",
      "visits": 26,
      }, {
      "country": "Puri",
      "visits": 22,
      }, {
      "country": "Rayagada",
      "visits": 20,
      }, {
      "country": "Sambalpur",
      "visits": 35,
      }, {
      "country": "Sonepur",
      "visits": 28,
      }, {
      "country": "Sundargarh",
      "visits": 27,
      } ];
    
    // Create axes
    var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
    categoryAxis.dataFields.category = "country";
    
    categoryAxis.renderer.grid.template.location = 0;
    categoryAxis.renderer.minGridDistance = 10;
    categoryAxis.renderer.labels.template.horizontalCenter = "right";
    categoryAxis.renderer.labels.template.verticalCenter = "middle";
    categoryAxis.renderer.labels.template.rotation = 270;
    //categoryAxis.tooltip.disabled = true;
    categoryAxis.renderer.minHeight = 110;
    
    var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
    valueAxis.title.text = "No of AWC";
    
    valueAxis.renderer.minWidth = 50;
    
    // Create series
    var series = chart.series.push(new am4charts.ColumnSeries());
    series.sequencedInterpolation = true;
    series.dataFields.valueY = "visits";
    series.dataFields.categoryX = "country";
    series.tooltipText = "[{categoryX}: bold]{valueY} AWC[/]";
    series.columns.template.strokeWidth = 0;
    
    series.tooltip.pointerOrientation = "vertical";
    
    series.columns.template.column.cornerRadiusTopLeft = 10;
    series.columns.template.column.cornerRadiusTopRight = 10;
    series.columns.template.column.fillOpacity = 0.8;
    
    // on hover, make corner radiuses bigger
    var hoverState = series.columns.template.column.states.create("hover");
    hoverState.properties.cornerRadiusTopLeft = 0;
    hoverState.properties.cornerRadiusTopRight = 0;
    hoverState.properties.fillOpacity = 1;
    
    series.columns.template.adapter.add("fill", function(fill, target) {
      return chart.colors.getIndex(target.dataItem.index);
    });
    
    series.columns.template.events.on("hit", function(ev) {
      console.log("clicked on ", ev.target.dataItem);
      chart.closeAllPopups();
      chart.openPopup("No Toilets Facility AWC List <br><strong> " + ev.target.dataItem.dataContext.country + "</strong>");
    
     }, this);
    
    // Cursor
    chart.cursor = new am4charts.XYCursor();
    
    }); // end am4core.ready()

