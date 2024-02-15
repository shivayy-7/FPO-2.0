am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

var chart = am4core.create("genderWise", am4charts.PieChart3D);
chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
chart.logo.disabled = true;
chart.legend = new am4charts.Legend();
chart.data = [
  
  {
    gender: "Female",
    litres: 45
  },{
    gender: "Male",
    litres: 29
  }
];

var series = chart.series.push(new am4charts.PieSeries3D());
series.dataFields.value = "litres";
series.dataFields.category = "gender";

});