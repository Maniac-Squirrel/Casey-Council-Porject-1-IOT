<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="webapp.DateFloatHolder" %>
 
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;

List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> elecperdaylist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> elecpermonthlist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> gaspermonthlist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> gasperdaylist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> waterperdaylist = new ArrayList<Map<Object,Object>>();
List<Map<Object,Object>> waterpermonthlist = new ArrayList<Map<Object,Object>>();
 
ArrayList<Float> dbResult =  
(ArrayList<Float>)request.getAttribute("data");

ArrayList<DateFloatHolder> avgelecperday =  
(ArrayList<DateFloatHolder>)request.getAttribute("avgperelecday");

ArrayList<DateFloatHolder> avgelecpermonth =  
(ArrayList<DateFloatHolder>)request.getAttribute("avgperelecmonth");

ArrayList<DateFloatHolder> avggaspermonth =  
(ArrayList<DateFloatHolder>)request.getAttribute("avgpergasmonth");

ArrayList<DateFloatHolder> avggasperday =  
(ArrayList<DateFloatHolder>)request.getAttribute("avgpergasday");

ArrayList<DateFloatHolder> avgwaterperday =  
(ArrayList<DateFloatHolder>)request.getAttribute("avgperwaterday");

ArrayList<DateFloatHolder> avgwaterpermonth =  
(ArrayList<DateFloatHolder>)request.getAttribute("avgperwatermonth");




for(int i = 0; i < avgelecperday.size(); i++){
	map = new HashMap<Object,Object>(); map.put("label", avgelecperday.get(i).getColumn1()); map.put("y", avgelecperday.get(i).getColumn2()); elecperdaylist.add(map);
}

for(int i = 0; i < avgelecpermonth.size(); i++){
	map = new HashMap<Object,Object>(); map.put("label", avgelecpermonth.get(i).getColumn1()); map.put("y", avgelecpermonth.get(i).getColumn2()); elecpermonthlist.add(map);
}

for(int i = 0; i < avggaspermonth.size(); i++){
	map = new HashMap<Object,Object>(); map.put("label", avggaspermonth.get(i).getColumn1()); map.put("y", avggaspermonth.get(i).getColumn2()); gaspermonthlist.add(map);
}

for(int i = 0; i < avggasperday.size(); i++){
	map = new HashMap<Object,Object>(); map.put("label", avggasperday.get(i).getColumn1()); map.put("y", avggasperday.get(i).getColumn2()); gasperdaylist.add(map);
}

for(int i = 0; i < avgwaterperday.size(); i++){
	map = new HashMap<Object,Object>(); map.put("label", avgwaterperday.get(i).getColumn1()); map.put("y", avgwaterperday.get(i).getColumn2()); waterperdaylist.add(map);
}

for(int i = 0; i < avgwaterpermonth.size(); i++){
	map = new HashMap<Object,Object>(); map.put("label", avgwaterpermonth.get(i).getColumn1()); map.put("y", avgwaterpermonth.get(i).getColumn2()); waterpermonthlist.add(map);
}

String dataPoints = gsonObj.toJson(elecperdaylist);
String dataPoints2 = gsonObj.toJson(elecpermonthlist);
String dataPoints3 = gsonObj.toJson(gaspermonthlist);
String dataPoints4 = gsonObj.toJson(gasperdaylist);
String dataPoints5 = gsonObj.toJson(waterperdaylist);
String dataPoints6 = gsonObj.toJson(waterpermonthlist);

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
		text: "Average electricity usage per day"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "line", //change type to bar, line, area, pie, etc
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
		text: "Average electricity usage per month"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "line", //change type to bar, line, area, pie, etc
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
		text: "Average Gas usage per month"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "line", //change type to bar, line, area, pie, etc
		//indexLabel: "{y}", //Shows y value on all Data Points
		indexLabelFontColor: "#5A5757",
		indexLabelPlacement: "outside",
		dataPoints: <%out.print(dataPoints3);%>
	}]
});

var chart4 = new CanvasJS.Chart("chartContainer4", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "Average Gas usage per day"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "line", //change type to bar, line, area, pie, etc
		//indexLabel: "{y}", //Shows y value on all Data Points
		indexLabelFontColor: "#5A5757",
		indexLabelPlacement: "outside",
		dataPoints: <%out.print(dataPoints4);%>
	}]
});

var chart5 = new CanvasJS.Chart("chartContainer5", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "Average water usage per day"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "line", //change type to bar, line, area, pie, etc
		//indexLabel: "{y}", //Shows y value on all Data Points
		indexLabelFontColor: "#5A5757",
		indexLabelPlacement: "outside",
		dataPoints: <%out.print(dataPoints5);%>
	}]
});

var chart6 = new CanvasJS.Chart("chartContainer6", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "Average water usage per month"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "line", //change type to bar, line, area, pie, etc
		//indexLabel: "{y}", //Shows y value on all Data Points
		indexLabelFontColor: "#5A5757",
		indexLabelPlacement: "outside",
		dataPoints: <%out.print(dataPoints6);%>
	}]
});

chart.render();
chart2.render();
chart3.render();
chart4.render();
chart5.render();
chart6.render();
}


</script>



</head>
<body>
<div id="chartContainer" style="height: 150px; width: 50%;"></div>
<div id="chartContainer2" style="height: 150px; width: 50%;"></div>
<div id="chartContainer3" style="height: 150px; width: 50%;"></div>
<div id="chartContainer4" style="height: 150px; width: 50%;"></div>
<div id="chartContainer5" style="height: 150px; width: 50%;"></div>
<div id="chartContainer6" style="height: 150px; width: 50%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<br>

<form action="community?id=<%= request.getParameter("id") %>" method="post">
		<input type="submit" value="Community Graphs"/>
		</form>
		
<br>				
		
<form action="profile?id=<%= request.getParameter("id") %>" method="post">
		<input type="submit" value="User Preferences"/>
		</form>		

</body>
</html>          