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
<link rel="stylesheet" type="text/css" href="clock/styles.css" />
<link rel="stylesheet" type="text/css" href="clock/jquery.tzineClock.css" />

<script type="text/javascript"  src="/plugin/jqplot.pieRenderer.min.js"></script>
<script  type="text/javascript" src="/plugin/jqplot.barRenderer.min.js"></script>	 
<script  type="text/javascript" src="/plugin/jqplot.categoryAxisRenderer.min.js"></script>
<script  type="text/javascript" src="/plugin/jqplot.pointLabels.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/stylesheets/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" type="text/css" href="/stylesheets/jquery.jqplot.min.css" />
	<script language="javascript" type="text/javascript" src="/js/jquery.jqplot.min.js"></script>

  <script  type="text/javascript">
  $(document).ready(function() {
  	//stats aanmaken
  		
  	
   		var l1=<%=request.getAttribute("myCoursesCheese")%>;
	    var l2=<%=request.getAttribute("myPlacesCheese")%>;
	    var l3=<%=request.getAttribute("myCourseBar1")%>;
	    var l4=<%=request.getAttribute("myCourseBar2")%>;
	    var l5=<%=request.getAttribute("myCourseBar3")%>;
	    var l6=<%=request.getAttribute("myFunInTime")%>;
	    var l7=<%=request.getAttribute("myNightlifeInTime")%>;
	    var l8=<%=request.getAttribute("mySleepInTime")%>;
	    var l9=<%=request.getAttribute("mySportInTime")%>;
	    var l10=<%=request.getAttribute("myTimeInTime")%>;
	    var l11=<%=request.getAttribute("overallMeanTimeInTime")%>;


	  		var plot1 = jQuery.jqplot ('chart1', [l1], { 
	      		height: 300,
	      		width: 500,
	      		showDataLabels: true,
	      		series:[{renderer:$.jqplot.PieRenderer}],
	      		legend:{show:true}
	    	});
	    	var plot2 = jQuery.jqplot ('chart2', [l2], { 
	      		height: 300,
	      		width: 500,
	      		showDataLabels: true,
	      		series:[{renderer:$.jqplot.PieRenderer}],
	      		legend:{show:true}
	    	});

			var plot3 = $.jqplot('chart3', [l3,l4], {
       		// The "seriesDefaults" option is an options object that will
        	// be applied to all series in the chart.
        		seriesDefaults:{
            		renderer:$.jqplot.BarRenderer,
           			rendererOptions: {fillToZero: true}
        		},
        	// Custom labels for the series are specified with the "label"
        	// option on the series option.  Here a series option object
        	// is specified for each series.
       			series:[
            		{label:'My stats'},
            		{label:'Overal stats'}
        		],
        	// Show the legend and put it outside the grid, but inside the
        	// plot container, shrinking the grid to accomodate the legend.
        	// A value of "outside" would not shrink the grid and allow
        	// the legend to overflow the container.
        		legend: {
            		show: true,
            		placement: 'outsideGrid'
        		},
        		axes: {
            // Use a category axis on the x axis and use our custom ticks.
            		xaxis: {
                		renderer: $.jqplot.CategoryAxisRenderer,
                		ticks: l5
            		},
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            		yaxis: {
                		pad: 1.05
            		}
        		}
    		});
    		
    		var plot4 = $.jqplot ('chart4', [l6]);
    		var plot5 = $.jqplot ('chart5', [l7]);
    		var plot6 = $.jqplot ('chart6', [l8]);
    		var plot7 = $.jqplot ('chart7', [l9]);
    		var plot8 = $.jqplot ('chart8', [l10]);
    		var plot9 = $.jqplot ('chart9', [l11]);
    		
    		
    		

 
 
	    	
	    

	/*!
	 * jquery.tzineClock.js - Tutorialzine Colorful Clock Plugin
	 *
	 * Copyright (c) 2009 Martin Angelov
	 * http://tutorialzine.com/
	 *
	 * Licensed under MIT
	 * http://www.opensource.org/licenses/mit-license.php
	 *
	 * Launch  : December 2009
	 * Version : 1.0
	 * Released: Monday 28th December, 2009 - 00:00
	 */

	(function($){

		// A global array used by the functions of the plug-in:
		var gVars = {};

		// Extending the jQuery core:
		$.fn.tzineClock = function(opts){

			// "this" contains the elements that were selected when calling the plugin: $('elements').tzineClock();
			// If the selector returned more than one element, use the first one:

			var container = this.eq(0);

			if(!container)
			{
				try{
					console.log("Invalid selector!");
				} catch(e){}

				return false;
			}

			if(!opts) opts = {}; 

			var defaults = {
				/* Additional options will be added in future versions of the plugin. */
			};

			/* Merging the provided options with the default ones (will be used in future versions of the plugin): */
			$.each(defaults,function(k,v){
				opts[k] = opts[k] || defaults[k];
			})

			// Calling the setUp function and passing the container,
			// will be available to the setUp function as "this":
			setUp.call(container);

			return this;
		}
			function setUp()
			{
				var currentTime = new Date();
			    var startdate = <%=(Date)request.getAttribute("startDate")%>;
			if(startdate!=null){

					var someDate = startdate;
			}
			else{

				var someDate = new Date(2011,12,9,0,0,0);
			}	
				var timerTime = new Date(someDate.getTime());
				var timerStart = currentTime.getTime()-someDate.getTime();
				timerTime.setTime(timerStart);
				// The colors of the dials:
				var colors = ['orange','blue','green'];

				var tmp;

				for(var i=0;i<3;i++)
				{
					// Creating a new element and setting the color as a class name:

					tmp = $('<div>').attr('class',colors[i]+' clock').html(
						'<div class="display"></div>'+

						'<div class="front left"></div>'+

						'<div class="rotate left">'+
							'<div class="bg left"></div>'+
						'</div>'+

						'<div class="rotate right">'+
							'<div class="bg right"></div>'+
						'</div>'
					);

					// Appending to the container:
					$(this).append(tmp);

					// Assigning some of the elements as variables for speed:
					tmp.rotateLeft = tmp.find('.rotate.left');
					tmp.rotateRight = tmp.find('.rotate.right');
					tmp.display = tmp.find('.display');

					// Adding the dial as a global variable. Will be available as gVars.colorName
					gVars[colors[i]] = tmp;
				}

				// Setting up a interval, executed every 1000 milliseconds

				if(someDate.getTime()<=currentTime.getTime()){
					setInterval(function(){
					var secs = timerTime.getSeconds()+1;
					timerTime.setSeconds(secs);

					var h = timerTime.getHours()-1;
					var m = timerTime.getMinutes();
					var s = timerTime.getSeconds();

					animation(gVars.green, s, 60);
					animation(gVars.blue, m, 60);
					animation(gVars.orange, h, 24);

				},1000);
			}
			else{
				timerTime.setHours(0);
				timerTime.setMinutes(0);
				timerTime.setSeconds(0);
				setInterval(function(){
				var secs = timerTime.getSeconds()+1;
				timerTime.setSeconds(secs);


				var h = timerTime.getHours();
				var m = timerTime.getMinutes();
				var s = timerTime.getSeconds();

				animation(gVars.green, s, 60);
				animation(gVars.blue, m, 60);
				animation(gVars.orange, h, 12);

			},1000);
			}
			}

			function animation(clock, current, total)
			{
				// Calculating the current angle:
				var angle = (360/total)*(current+1);

				var element;

				if(current==0)
				{
					// Hiding the right half of the background:
					clock.rotateRight.hide();

					// Resetting the rotation of the left part:
					rotateElement(clock.rotateLeft,0);
				}

				if(angle<=180)
				{
					// The left part is rotated, and the right is currently hidden:
					element = clock.rotateLeft;
				}
				else
				{
					// The first part of the rotation has completed, so we start rotating the right part:
					clock.rotateRight.show();
					clock.rotateLeft.show();

					rotateElement(clock.rotateLeft,180);

					element = clock.rotateRight;
					angle = angle-180;
				}

				rotateElement(element,angle);

				// Setting the text inside of the display element, inserting a leading zero if needed:
				clock.display.html(current<10?'0'+current:current);
			}
		function rotateElement(element,angle)
		{
			// Rotating the element, depending on the browser:
			var rotate = 'rotate('+angle+'deg)';

			if(element.css('MozTransform')!=undefined)
				element.css('MozTransform',rotate);

			else if(element.css('WebkitTransform')!=undefined)
				element.css('WebkitTransform',rotate);

			// A version for internet explorer using filters, works but is a bit buggy (no surprise here):
			else if(element.css("filter")!=undefined)
			{
				var cos = Math.cos(Math.PI * 2 / 360 * angle);
				var sin = Math.sin(Math.PI * 2 / 360 * angle);

				element.css("filter","progid:DXImageTransform.Microsoft.Matrix(M11="+cos+",M12=-"+sin+",M21="+sin+",M22="+cos+",SizingMethod='auto expand',FilterType='nearest neighbor')");

				element.css("left",-Math.floor((element.width()-200)/2));
				element.css("top",-Math.floor((element.height()-200)/2));
			}

		}

	})(jQuery)
	$('#fancyClock').tzineClock();

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
    $('#backonea').click(function() {
 		$('#one').show();
 		$('#two').hide();	
    });
    $('#backoneb').click(function() {
 		$('#one').show();
 		$('#tree').hide(); 		
    });
    $('#twoa').click(function() {
 		$('#two').hide();
 		$('#four').show();
 		var text=$("#welkvak").val();
 		$('.vak').val(text);
    });
    $('#backtwo').click(function() {
 		$('#two').show();
 		$('#four').hide(); 		
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
 		$('.type').val("Oefenzitting");
 		var text=$("#watextra").val();
 		$('.watextra').val(text);
    });		
	
    	$('#tabs').bind('tabsshow', function(event, ui) {
  			if (ui.index == 1 && plot1._drawCount == 0) {
   			 plot1.replot();
			 plot2.replot();
   			 plot3.replot();
 			 }
		});
    	
	 
    });
  </script>
  
</head>

<body>

 
<div class="tabs" id="tabs">
	<ul>
    	<h1> <font size="10"> Learnkeeper </font> </br></h1>
    	<h2><%=(String)request.getAttribute("username")%></h2>
    	<div id="fancyClock"></div>
		<% String bezig=(String)request.getAttribute("bezig");
		if(bezig!=null){%>
			<p allign="right"> <%=request.getAttribute("curract").toString()%></p>
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







