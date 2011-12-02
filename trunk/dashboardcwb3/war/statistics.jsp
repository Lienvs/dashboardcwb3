<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
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

	<link rel="stylesheet" type="text/css" href="/stylesheets/jquery.jqplot.css" />
  
  
  
</head>

<body style="font-size:62.5%;">



<script>
	$(function() {
		var stop = false;
		$( "#accordion h3" ).click(function( event ) {
			if ( stop ) {
				event.stopImmediatePropagation();
				event.preventDefault();
				stop = false;
			}
		});
		$( "#accordion" )
			.accordion({
				header: "> div > h3"
			})
			.sortable({
				axis: "y",
				handle: "h3",
				stop: function() {
					stop = true;
				}
			});
		var data = <%=request.getAttribute("stri")%>;
  		var plot1 = jQuery.jqplot ('chart1', [data], { 
      		seriesDefaults: {
        		// Make this a pie chart.
        		renderer: jQuery.jqplot.PieRenderer, 
        		rendererOptions: {
          			// Put data labels on the pie slices.
          			// By default, labels show the percentage of the slice.
          			showDataLabels: true
        		}
      		}, 
     		 legend: { show:true, location: 'e' }
    	});
	});
	</script>



<div class="demo">

<div id="accordion">
	<div>
		<h3><a href="#">Section 1</a></h3>
		<div>
		</div>
	</div>
	<div>
		<h3><a href="#">Section 2</a></h3>
		<div>
		</div>
	</div>
	<div>
		<h3><a href="#">Section 3</a></h3>
		<div>
		</div>
	</div>
	<div>
		<h3><a href="#">Section 4</a></h3>
		<div>
		</div>
	</div>
</div>






test
<script>
  
	$(document).ready(function(){
 
});
  </script>
<div id="chart1" style="height:300px; width:500px; " ></div>



</body>
</html>