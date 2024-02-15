am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

var chart = am4core.create("distWiseCMC", am4charts.XYChart);
chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect

// Add data
chart.data = [{
  "District": "Angul",
  "Victim Count": 25
}, {
  "District": "Balangir",
  "Victim Count": 15
}, {
  "District": "Balasore",
  "Victim Count": 89
},
{
  "District": "Bargarh",
  "Victim Count": 56
},
{
  "District": "Bhadrak",
  "Victim Count": 25
}, {
  "District": "Boudh",
  "Victim Count": 15
}, {
  "District": "Cuttack",
  "Victim Count": 89
},
{
  "District": "Deogarh",
  "Victim Count": 89
},
{
  "District": "Dhenkanal",
  "Victim Count": 25
}, {
  "District": "Gajapati",
  "Victim Count": 15
}, {
  "District": "Ganjam",
  "Victim Count": 76
},
{
  "District": "Jagatsinghapur",
  "Victim Count": 66
},
{
  "District": "Jajpur",
  "Victim Count": 25
}, {
  "District": "Jharsuguda",
  "Victim Count": 15
}, {
  "District": "Kalahandi",
  "Victim Count": 89
},
{
  "District": "Kandhamal",
  "Victim Count": 58
},
{
  "District": "Kendrapara",
  "Victim Count": 25
}, {
  "District": "Kendujhar",
  "Victim Count": 15
}, {
  "District": "Khordha",
  "Victim Count": 75
},
{
  "District": "Koraput",
  "Victim Count": 89
},
{
  "District": "Malkangiri",
  "Victim Count": 25
}, {
  "District": "Mayurbhanj",
  "Victim Count": 15
}, {
  "District": "Nabarangpur",
  "Victim Count": 89
},
{
  "District": "Nayagarh",
  "Victim Count": 89
},
{
  "District": "Nuapada",
  "Victim Count": 25
}, {
  "District": "Puri",
  "Victim Count": 15
}, {
  "District": "Rayagada",
  "Victim Count": 89
},
{
  "District": "Sambalpur",
  "Victim Count": 89
},
{
  "District": "Sonepur",
  "Victim Count": 25
}, {
  "District": "Sundargarh",
  "Victim Count": 15
}];


var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.dataFields.category = "District";
categoryAxis.renderer.minGridDistance = 40;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

var series = chart.series.push(new am4charts.CurvedColumnSeries());
series.dataFields.categoryX = "District";
series.dataFields.valueY = "Victim Count";
series.tooltipText = "{valueY.value}"
series.columns.template.strokeOpacity = 0;
series.columns.template.tension = 1;

series.columns.template.fillOpacity = 0.75;

var hoverState = series.columns.template.states.create("hover");
hoverState.properties.fillOpacity = 1;
hoverState.properties.tension = 0.8;


    series.columns.template.events.on("hit", function(ev) {
      console.log("clicked on ", ev.target.dataItem);
      chart.closeAllPopups();
      chart.openPopup("No Toilets Facility AWC List <br><strong> " + ev.target.dataItem.dataContext.country + "</strong>");
    
     }, this);

chart.cursor = new am4charts.XYCursor();

// Add distinctive colors for each column using adapter
series.columns.template.adapter.add("fill", function(fill, target) {
  return chart.colors.getIndex(target.dataItem.index);
});

chart.scrollbarX = new am4core.Scrollbar();
chart.scrollbarY = new am4core.Scrollbar();

}); // end am4core.ready()