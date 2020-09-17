<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
 
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
 
ArrayList<Float> dbResult =  
(ArrayList<Float>)request.getAttribute("data");


for(int i = 0; i < dbResult.size(); i++){
	map = new HashMap<Object,Object>(); map.put("x", i); map.put("y", dbResult.get(i)); list.add(map);
}


String dataPoints = gsonObj.toJson(list);
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
		text: "Simple Column Chart with Index Labels"
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "column", //change type to bar, line, area, pie, etc
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
		text: "Simple Column Chart with Index Labels"
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

chart.render();
chart2.render();
}


</script>



</head>
<body>
<div id="chartContainer" style="height: 100px; width: 100%;"></div>
<div id="chartContainer2" style="height: 100px; width: 25%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>          