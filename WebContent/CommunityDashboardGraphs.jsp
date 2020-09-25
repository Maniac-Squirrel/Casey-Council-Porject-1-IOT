<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="webapp.DateFloatHolder" %>
 
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;

List<Map<Object,Object>> Totaleleclist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> Totalwaterlist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> Totalgaslist = new ArrayList<Map<Object,Object>>();

List<Map<Object,Object>> communitywater7daysLinelist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> indivwater7daysLinelist = new ArrayList<Map<Object,Object>>();
 

List<Map<Object,Object>> communityelec7daysLinelist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> indivelec7daysLinelist = new ArrayList<Map<Object,Object>>();

List<Map<Object,Object>> communitygas7daysLinelist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> indivgas7daysLinelist = new ArrayList<Map<Object,Object>>();

ArrayList<Float> totalElecCons =  
(ArrayList<Float>)request.getAttribute("totalElecCons");

ArrayList<Integer> totalWaterCons =  
(ArrayList<Integer>)request.getAttribute("totalWaterCons");

ArrayList<Float> totalGasCons =  
(ArrayList<Float>)request.getAttribute("totalGasCons");

ArrayList<DateFloatHolder> waterLast7days =  
(ArrayList<DateFloatHolder>)request.getAttribute("communityIndiv7daywater");

ArrayList<DateFloatHolder> electricityLast7days =  
(ArrayList<DateFloatHolder>)request.getAttribute("communityIndiv7dayelec");

ArrayList<DateFloatHolder> gasLast7days =  
(ArrayList<DateFloatHolder>)request.getAttribute("communityIndiv7daygas");


map = new HashMap<Object,Object>(); map.put("label", "Total individual electricity consumption"); map.put("y", totalElecCons.get(0)); Totaleleclist.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Total community electricity consumption"); map.put("y", totalElecCons.get(1)); Totaleleclist.add(map);

map = new HashMap<Object,Object>(); map.put("label", "Total individual water consumption"); map.put("y", totalWaterCons.get(0)); Totalwaterlist.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Total community water consumption"); map.put("y", totalWaterCons.get(1)); Totalwaterlist.add(map);

map = new HashMap<Object,Object>(); map.put("label", "Total individual gas consumption"); map.put("y", totalGasCons.get(0)); Totalgaslist.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Total community gas consumption"); map.put("y", totalGasCons.get(1)); Totalgaslist.add(map);


for(int i = 0; i < 7; i++){
	map = new HashMap<Object,Object>(); map.put("label", waterLast7days.get(6 + i).getColumn1()); map.put("y", waterLast7days.get(6 + i).getColumn2()); communitywater7daysLinelist.add(map);
	map = new HashMap<Object,Object>(); map.put("label", waterLast7days.get(i).getColumn1()); map.put("y", waterLast7days.get(i).getColumn2()); indivwater7daysLinelist.add(map);
	

	map = new HashMap<Object,Object>(); map.put("label", electricityLast7days.get(6 + i).getColumn1()); map.put("y", electricityLast7days.get(6 + i).getColumn2()); communityelec7daysLinelist.add(map);
	map = new HashMap<Object,Object>(); map.put("label", electricityLast7days.get(i).getColumn1()); map.put("y", electricityLast7days.get(i).getColumn2()); indivelec7daysLinelist.add(map);
	
	map = new HashMap<Object,Object>(); map.put("label", gasLast7days.get(6 + i).getColumn1()); map.put("y", gasLast7days.get(6 + i).getColumn2()); communitygas7daysLinelist.add(map);
	map = new HashMap<Object,Object>(); map.put("label", gasLast7days.get(i).getColumn1()); map.put("y", gasLast7days.get(i).getColumn2()); indivgas7daysLinelist.add(map);
}

String dataPoints = gsonObj.toJson(Totaleleclist);
String dataPoints2 = gsonObj.toJson(Totalwaterlist);
String dataPoints3 = gsonObj.toJson(Totalgaslist);

String dataPoints4 = gsonObj.toJson(communitywater7daysLinelist);
String dataPoints5 = gsonObj.toJson(indivwater7daysLinelist);

String dataPoints6 = gsonObj.toJson(communityelec7daysLinelist);
String dataPoints7 = gsonObj.toJson(indivelec7daysLinelist);

String dataPoints8 = gsonObj.toJson(communitygas7daysLinelist);
String dataPoints9 = gsonObj.toJson(indivgas7daysLinelist);


%>
 
<!DOCTYPE HTML>
<html>
<head>
	

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "Total community and individual electricity consumption"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "pie", //change type to bar, line, area, pie, etc
		//indexLabel: "{y}", //Shows y value on all Data Points
		indexLabelFontColor: "#5A5757",
		indexLabelPlacement: "outside",
		dataPoints: <%out.print(dataPoints);%>
	}]
});

var chart2 = new CanvasJS.Chart("chartContainer2", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "Total community and individual water consumption"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "pie", //change type to bar, line, area, pie, etc
		//indexLabel: "{y}", //Shows y value on all Data Points
		indexLabelFontColor: "#5A5757",
		indexLabelPlacement: "outside",
		dataPoints: <%out.print(dataPoints2);%>
	}]
});

var chart3 = new CanvasJS.Chart("chartContainer3", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "Total community and individual gas consumption"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "pie", //change type to bar, line, area, pie, etc
		//indexLabel: "{y}", //Shows y value on all Data Points
		indexLabelFontColor: "#5A5757",
		indexLabelPlacement: "outside",
		dataPoints: <%out.print(dataPoints3);%>
	}]
});

var chart4 = new CanvasJS.Chart("chartContainer4", {
	animationEnabled: true,
	zoomEnabled: true,
	theme: "light2",
	title: {
		text: "Community and individual water consumption over 7 days"
	},
	axisX: {
		title: "Day",
		valueFormatString: "#,##0 km"
	},
	axisY: {
		logarithmic: false, //change it to false
		title: "Total community water consumption over 7 days in Litres",
		titleFontColor: "#6D78AD",
		lineColor: "#6D78AD",
		gridThickness: 0,
		lineThickness: 1,
	},
	axisY2: {
		title: "Total indiv water consumption over 7 days in Litres",
		titleFontColor: "#51CDA0",
		logarithmic: false, //change it to true
		lineColor: "#51CDA0",
		gridThickness: 0,
		lineThickness: 1
	},
	toolTip: {
		shared: true
	},
	legend: {
		verticalAlign: "top",
		dockInsidePlotArea: true
	},
	data: [{
		type: "line",
		yValueFormatString: "###0 Litres",
		xValueFormatString: "",
		showInLegend: true,
		name: "Total community water consumption over 7 days",
		legendText: "{name} (in Litres)",
		dataPoints: <%out.print(dataPoints4);%>
	},
	{
		type: "line",
		yValueFormatString: "###0 Litres",
		xValueFormatString: "",
		axisYType: "secondary",
		showInLegend: true,
		name: "Total individual water consumption over 7 days",
		legendText: "{name} (in Litres)",
		dataPoints: <%out.print(dataPoints5);%>
	}]
});

var chart5 = new CanvasJS.Chart("chartContainer5", {
	animationEnabled: true,
	zoomEnabled: true,
	theme: "light2",
	title: {
		text: "Community and individual Electricity consumption over 7 days"
	},
	axisX: {
		title: "Day",
		valueFormatString: "#,##0 km"
	},
	axisY: {
		logarithmic: false, //change it to false
		title: "Total community electricity consumption over 7 days in Kwh",
		titleFontColor: "#6D78AD",
		lineColor: "#6D78AD",
		gridThickness: 0,
		lineThickness: 1,
	},
	axisY2: {
		title: "Total indiv electricity consumption over 7 days in Kwh",
		titleFontColor: "#51CDA0",
		logarithmic: false, //change it to true
		lineColor: "#51CDA0",
		gridThickness: 0,
		lineThickness: 1
	},
	toolTip: {
		shared: true
	},
	legend: {
		verticalAlign: "top",
		dockInsidePlotArea: true
	},
	data: [{
		type: "line",
		yValueFormatString: "###0 Kwh",
		xValueFormatString: "",
		showInLegend: true,
		name: "Total community electricty consumption over 7 days",
		legendText: "{name} (in Kwh)",
		dataPoints: <%out.print(dataPoints6);%>
	},
	{
		type: "line",
		yValueFormatString: "###0 Kwh",
		xValueFormatString: "",
		axisYType: "secondary",
		showInLegend: true,
		name: "Total individual electricty consumption over 7 days",
		legendText: "{name} (in Kwh)",
		dataPoints: <%out.print(dataPoints7);%>
	}]
});

var chart6 = new CanvasJS.Chart("chartContainer6", {
	animationEnabled: true,
	zoomEnabled: true,
	theme: "light2",
	title: {
		text: "Community and individual Gas consumption over 7 days"
	},
	axisX: {
		title: "Day",
		valueFormatString: "#,##0 km"
	},
	axisY: {
		logarithmic: false, //change it to false
		title: "Total community Gas consumption over 7 days in MJ/h",
		titleFontColor: "#6D78AD",
		lineColor: "#6D78AD",
		gridThickness: 0,
		lineThickness: 1,
	},
	axisY2: {
		title: "Total indiv Gas consumption over 7 days in MJ/h",
		titleFontColor: "#51CDA0",
		logarithmic: false, //change it to true
		lineColor: "#51CDA0",
		gridThickness: 0,
		lineThickness: 1
	},
	toolTip: {
		shared: true
	},
	legend: {
		verticalAlign: "top",
		dockInsidePlotArea: true
	},
	data: [{
		type: "line",
		yValueFormatString: "###0 MJ/h",
		xValueFormatString: "",
		showInLegend: true,
		name: "Total community Gas consumption over 7 days",
		legendText: "{name} (in MJ/h)",
		dataPoints: <%out.print(dataPoints8);%>
	},
	{
		type: "line",
		yValueFormatString: "###0 MJ/h",
		xValueFormatString: "",
		axisYType: "secondary",
		showInLegend: true,
		name: "Total individual Gas consumption over 7 days",
		legendText: "{name} (in MJ/h)",
		dataPoints: <%out.print(dataPoints9);%>
	}]
});
chart4.render();

chart.render();
chart2.render();
chart3.render();
chart5.render();
chart6.render();

}


</script>



</head>
<body>
<div id="chartContainer" style="height: 150px; width: 50%;"></div>
<div id="chartContainer2" style="height: 150px; width: 50%;"></div>
<div id="chartContainer3" style="height: 150px; width: 50%;"></div>
<div id="chartContainer4" style="height: 300px; width: 50%;"></div>
<div id="chartContainer5" style="height: 300px; width: 50%;"></div>
<div id="chartContainer6" style="height: 300px; width: 50%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<br>

<form action="home?id=<%= request.getParameter("id") %>" method="post">
		<input type="submit" value="Individual Graphs"/>
		</form>
		
<br>		
		
<form action="profile?id=<%= request.getParameter("id") %>" method="post">
		<input type="submit" value="User Preferences"/>
		</form>	
</body>
</html>          