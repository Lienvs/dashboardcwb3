<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="activity.*" %>
<%@ page import="statistics.*"%>
<%@ page import="course.*"%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<html>
<head>
  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  <script language="javascript" type="text/javascript" src="/js/jquery.jqplot.min.js"></script>
  <script language="javascript" type="text/javascript" src="/plugin/jqplot.pieRenderer.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/stylesheets/jquery.jqplot.min.css" /> 
  <script >
  $(document).ready(function() {
    $("#tabs").tabs();
    $( ".check" ).button();   
    $('#onea').click(function() {
 		$('#one').hide();
 		$('#two').show();
 		$('.wat').val("Scolair"); 		
    });
    $('#oneb').click(function() {
 		$('#one').hide();
 		$('#tree').show();
 		$('.wat').val("Extrascolair");
    });
    $('#twoa').click(function() {
 		$('#two').hide();
 		$('#four').show();
 		var text=$("#welkvak").val();
 		$('.vak').val(text);
    });
    $('#foura').click(function() {
 		$('#four').hide();
 		$('#five').show();
 		$('.type').val("Les");
    });
    $('#fourb').click(function() {
 		$('#four').hide();
 		$('#five').show();
 		$('.type').val("Zelfstudie");
    });
    $('#fourc').click(function() {
 		$('#four').hide();
 		$('#five').show();
 		$('.type').val("Oefenzitting");
    });
    $('#treea').click(function() {
 		$('#tree').hide();
 		$('#five').show();
 		var text=$("#watextra").val();
 		$('.watextra').val(text);
    });	    
    var l4 = <%=request.getAttribute("myCoursesCheese")%>;
  		var plot1 = jQuery.jqplot ('chart1', [l4], { 
      		height: 300,
      		width: 500,
      		showDataLabels: true,
      		series:[{renderer:$.jqplot.PieRenderer}],
      		legend:{show:true}
    	});
    	
    	$('#tabs').bind('tabsshow', function(event, ui) {
  			if (ui.index == 1 && plot1._drawCount == 0) {
   			 plot1.replot();
 			 }
		});    	   	
    });  	
  </script> 
</head>
<body style="font-size:62.5%;"> 

<div class="tabs" id="tabs">
	<ul>
    	<h1> Learnkeeper </br></h1>
    	<h2><%=(String)request.getAttribute("username")%> </h2>
		<% String bezig=(String)request.getAttribute("bezig");
		if(bezig!=null){%>
			<p allign="right"> <%=request.getAttribute("curract")%></p>			
			<li><a href="#fragment-2"><span>Stop</span></a></li>
		<%}
		else{%>
			<li><a href="#fragment-1"><span>Start</span></a></li>
		<%}%> 
        <li><a href="#fragment-3"><span>Statistics</span></a></li>
        <li><a href="#fragment-4"><span>Goals</span></a></li>
        <li><a href="#fragment-5"><span>My courses</span></a></li>
        <li><a href="#fragment-6"><span>Options</span></a></li>
        <li><a href="#fragment-7"><span>Logout</span></a></li>
    </ul>   
    	<%if(bezig!=null){%>
			<div id="fragment-2">
      			<%@ include file="stop.jsp" %>
  			</div>
		<%}
		if(bezig==null){%>
			<div id="fragment-1">
        			<%@ include file="start.jsp" %>
				</div>
		<%}%>  
    	<div id="fragment-3">
        	<%@ include file="statistics.jsp" %>
    	</div>   	
    	<div id="fragment-4">
        	<%@ include file="goals.jsp" %>
    	</div>    	
    	<div id="fragment-5">
        	<%@ include file="courses.jsp" %>
    	</div>    	
    	<div id="fragment-6">
        	<%@ include file="options.jsp" %>
    	</div>    	
    	<div id="fragment-7">
        	<form action="/logout" method="post">
				<input type="submit" class="check" value="Logout" name="logout">
			</form>
    	</div>   	
</div>
</body>
</html>