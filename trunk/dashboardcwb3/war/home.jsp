<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="activity.*" %>
<%@ page import="statistics.*"%>
<%@ page import="course.*"%>
<%@ page import="user.*"%>
<%@ page import="statistics.*"%>
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
<link rel="stylesheet" type="text/css" href="clock/test/jquery.tzineClock.css" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script language="javascript" type="text/javascript" src="/js/jquery.jqplot.min.js"></script>

<script  language="javascript" type="text/javascript" src="/plugins/jqplot.pieRenderer.min.js"></script>
<script  language="javascript" "type="text/javascript" src="/plugins/jqplot.barRenderer.min.js"></script>
	 
<script  language="javascript" type="text/javascript" src="/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script  language="javascript" type="text/javascript" src="/plugins/jqplot.pointLabels.min.js"></script>
<script  language="javascript" type="text/javascript" src="/plugins/jqplot.dateAxisRenderer.min.js"></script>

<script language="javascript" type="text/javascript" src="/plugins/jqplot.logAxisRenderer.min.js"></script>
<script language="javascript" type="text/javascript" src="/plugins/jqplot.canvasTextRenderer.min.js"></script>
<script language="javascript" type="text/javascript" src="/plugins/jqplot.canvasAxisLabelRenderer.min.js"></script>
<script language="javascript" type="text/javascript" src="/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>






<link rel="stylesheet" type="text/css" href="/stylesheets/jquery-ui-1.8.16.custom.css" media=all/>
<link rel="stylesheet" type="text/css" href="/stylesheets/jquery.jqplot.min.css" media=all/>



  <script  type="text/javascript">
  $(document).ready(function() {
  
  	
  	<%StatisticController stat=new StatisticController();%>
    <%ArrayList<Course> coursesstudent=UserManager.getInstance().getCourses();%>
  	
  	

  	
   		var l1=<%=stat.myCoursesCheese()%>;//werkt
   		var l2=<%=stat.myPlacesCheese()%>;//werkt
   		
   		
   		var plot1 = jQuery.jqplot ('chart1', [l1], { 
	      		height: 300,
	      		width: 500,
	      		seriesDefaults:{
           			renderer:$.jqplot.PieRenderer, 
            		trendline:{ show:false }, 
            		rendererOptions: { padding: 8, showDataLabels: true }
        		},
	      		legend:{show:true},
	      		title: {
       				text: 'Personnal relative time per course',   // title for the plot,
        			show: true
    			}
	    	});
	    	var plot2 = jQuery.jqplot ('chart2', [l2], { 
	      		height: 300,
	      		width: 500,
	      		seriesDefaults:{
           			renderer:$.jqplot.PieRenderer, 
            		trendline:{ show:false }, 
            		rendererOptions: { padding: 8, showDataLabels: true }
        		},
	      		legend:{show:true},
	      		title: {
       				text: 'Your study locations',   // title for the plot,
        			show: true
    			}
	    	});
   		
   		
   		
   		
   		var l3=<%=stat.myCourseBar().get(0)%>;//ipv chart3
   		var l4=<%=stat.myCourseBar().get(1)%>;
   		var l5=<%=stat.myCourseBar().get(2)%>;
   		
   		var plot3 = $.jqplot('chart3', [l3,l4], {
       		// The "seriesDefaults" option is an options object that will
        	// be applied to all series in the chart.
        		seriesDefaults:{
            		renderer:$.jqplot.BarRenderer,
           			pointlabels:{show:true},
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
            		}
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            		
        		},
        		title: {
       				text: 'comparison between you and the average for each course',   // title for the plot,
        			show: true
    			}
    		});
   		
   		
   		
   		var l6=<%=stat.myFunInTime()%>;//half
   		var l7=<%=stat.myNightlifeInTime()%>;//half
   		var l8=<%=stat.mySleepInTime()%>;//half
   		var l9=<%=stat.mySportInTime()%>;//half
   		var l10=<%=stat.myTimeInTime()%>;//half
   		var l11=<%=stat.overallMeanTimeInTime()%>;//half
   		var l12=<%=stat.overallMeanFunInTime()%>;//half
   		
   		var plot4 = $.jqplot('chart4', [l6], {
   				axes: {
        			xaxis: {
          				renderer: $.jqplot.DateAxisRenderer,
          				labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
         				tickRenderer: $.jqplot.CanvasAxisTickRenderer,
          				tickOptions: {
              				// labelPosition: 'middle',
              				angle: 15
          				}
           
        			},
       				yaxis: {
          				labelRenderer: $.jqplot.CanvasAxisLabelRenderer
        			}
      			}
    		});
  			var plot5 = $.jqplot('chart5', [l7], {
   				title:'Default Date Axis',
    			axes:{
        			xaxis:{
            			renderer:$.jqplot.DateAxisRenderer
        			}
    			},
  			});
  			var plot6 = $.jqplot('chart6', [l8], {
   				title:'Default Date Axis',
    			axes:{
        			xaxis:{
            			renderer:$.jqplot.DateAxisRenderer
        			}
    			},
  			});
  			var plot7 = $.jqplot('chart7', [l9], {
   				title:'Default Date Axis',
    			axes:{
        			xaxis:{
            			renderer:$.jqplot.DateAxisRenderer
        			}
    			},
  			});
  			var plot8 = $.jqplot('chart8', [l10], {
   				title:'Default Date Axis',
    			axes:{
        			xaxis:{
            			renderer:$.jqplot.DateAxisRenderer
        			}
    			},
  			});
  			var plot9 = $.jqplot('chart9', [l11], {
   				title:'Default Date Axis',
    			axes:{
        			xaxis:{
            			renderer:$.jqplot.DateAxisRenderer
        			}
    			},
  			});
  			var plot10 = $.jqplot('chart10', [l12], {
   				title:'Default Date Axis',
    			axes:{
        			xaxis:{
            			renderer:$.jqplot.DateAxisRenderer
        			}
    			},
  			});
  			

  		var l21=<%=stat.myTypeCheese()%>;
  		
  			var plot11 = jQuery.jqplot ('chart11', [l21], { 
	      		height: 300,
	      		width: 500,
	      		seriesDefaults:{
           			renderer:$.jqplot.PieRenderer, 
            		trendline:{ show:false }, 
            		rendererOptions: { padding: 8, showDataLabels: true }
        		},
	      		legend:{show:true},
	      		title: {
       				text: '?',   // title for the plot,
        			show: true
    			}
	    	});
	    	
	    var l22=<%=stat.myActiTypeCheese()%>;
  		
  			var plot12 = jQuery.jqplot ('chart12', [l22], { 
	      		height: 300,
	      		width: 500,
	      		seriesDefaults:{
           			renderer:$.jqplot.PieRenderer, 
            		trendline:{ show:false }, 
            		rendererOptions: { padding: 8, showDataLabels: true }
        		},
	      		legend:{show:true},
	      		title: {
       				text: '?',   // title for the plot,
        			show: true
    			}
	    	});
	    
   		
   		
   		
   		
   		var l131=<%=stat.meVSModel().get(0)%>;  //arr:1wat al gedaan,2 goals, 3 modeltraject,4 vakken
   		var l132=<%=stat.meVSModel().get(1)%>; 
   		var l133=<%=stat.meVSModel().get(2)%>; //bar==>niet
   		var l134=<%=stat.meVSModel().get(3)%>; 
   		
   		var plot13 = $.jqplot('chart13', [l131,l132,l133], {
       		// The "seriesDefaults" option is an options object that will
        	// be applied to all series in the chart.
        		seriesDefaults:{
            		renderer:$.jqplot.BarRenderer,
           			pointlabels:{show:true},
        		},
        	// Custom labels for the series are specified with the "label"
        	// option on the series option.  Here a series option object
        	// is specified for each series.
       			series:[
            		{label:'My stats'},
            		{label:'goals'},
            		{label:'model'}
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
                		ticks: l134
            		}
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            		
        		},
        		title: {
       				text: 'comparison between you and the average for each course',   // title for the plot,
        			show: true
    			}
    		});
   		
   		
   	
   		//var l15=<%=stat.overallTime()%>;//int
   		//var l16=<%=stat.getMaximumStudie()%>;
   		
	var l171=<%=stat.myMeanRatingBar().get(0)%>;  //arr 1 de gemiddelde rating voor elk vak 2 de vakken
   		var l172=<%=stat.myMeanRatingBar().get(1)%>; //bar==>niet
   		
   		var plot14 = $.jqplot('chart14', [l171], {
       		// The "seriesDefaults" option is an options object that will
        	// be applied to all series in the chart.
        		seriesDefaults:{
            		renderer:$.jqplot.BarRenderer,
           			pointlabels:{show:true},
        		},
        	// Custom labels for the series are specified with the "label"
        	// option on the series option.  Here a series option object
        	// is specified for each series.
       			series:[
            		{label:'My ratings'}
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
                		ticks: l172
            		}
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            		
        		},
        		title: {
       				text: 'comparison between you and the average for each course',   // title for the plot,
        			show: true
    			}
    		});
    		
    		//var l181=<%=stat.myStuddyBuddys().get(0)%>;   //arr 1 hoeveel keer de buddy voorkomt 2 de namen van de buddys.
   		//var l182=<%=stat.myStuddyBuddys().get(1)%>;  //bar==>niet
   		
   		var l191=<%=stat.myMeanPlaceRatingBar().get(0)%>;  //arr 1 de gemiddelde rating voor elk vak 2 de vakken
   		var l192=<%=stat.myMeanPlaceRatingBar().get(1)%>;    //bar==> niet
   		
   		var plot15 = $.jqplot('chart15', [l191], {
       		// The "seriesDefaults" option is an options object that will
        	// be applied to all series in the chart.
        		seriesDefaults:{
            		renderer:$.jqplot.BarRenderer,
           			pointlabels:{show:true},
        		},
        	// Custom labels for the series are specified with the "label"
        	// option on the series option.  Here a series option object
        	// is specified for each series.
       			series:[
            		{label:'My ratings'}
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
                		ticks: l192
            		}
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            		
        		},
        		title: {
       				text: 'comparison between you and the average for each course',   // title for the plot,
        			show: true
    			}
    		});
    		
    		/**var l201=<%=stat.myMeanBuddyRatingBar().get(0)%>;//1 de gemiddelde rating voor elke buddy 2 de buddy's
   		var l202=<%=stat.myMeanBuddyRatingBar().get(1)%>;//bar==>niet
   		
   		var plot16 = $.jqplot('chart16', [l201], {
       		// The "seriesDefaults" option is an options object that will
        	// be applied to all series in the chart.
        		seriesDefaults:{
            		renderer:$.jqplot.BarRenderer,
           			pointlabels:{show:true},
        		},
        	// Custom labels for the series are specified with the "label"
        	// option on the series option.  Here a series option object
        	// is specified for each series.
       			series:[
            		{label:'My ratings'}
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
                		ticks: l202
            		}
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            		
        		},
        		title: {
       				text: 'comparison between you and the average for each course',   // title for the plot,
        			show: true
    			}
    		});
    		**/
    		
    		var l23=<%=stat.overallTypeCheese()%>;//werkt
   			var l24=<%=stat.overallActiTypeCheese()%>;//werkt
   			var l25=<%=stat.overallPlacesCheese()%>;//werkt
   		
   		
   		var plot17 = jQuery.jqplot ('chart17', [l23], { 
	      		height: 300,
	      		width: 500,
	      		seriesDefaults:{
           			renderer:$.jqplot.PieRenderer, 
            		trendline:{ show:false }, 
            		rendererOptions: { padding: 8, showDataLabels: true }
        		},
	      		legend:{show:true},
	      		title: {
       				text: 'Personnal relative time per course',   // title for the plot,
        			show: true
    			}
	    	});
	    	var plot18 = jQuery.jqplot ('chart18', [l24], { 
	      		height: 300,
	      		width: 500,
	      		seriesDefaults:{
           			renderer:$.jqplot.PieRenderer, 
            		trendline:{ show:false }, 
            		rendererOptions: { padding: 8, showDataLabels: true }
        		},
	      		legend:{show:true},
	      		title: {
       				text: 'Your study locations',   // title for the plot,
        			show: true
    			}
	    	});
	    	var plot19 = jQuery.jqplot ('chart19', [l25], { 
	      		height: 300,
	      		width: 500,
	      		seriesDefaults:{
           			renderer:$.jqplot.PieRenderer, 
            		trendline:{ show:false }, 
            		rendererOptions: { padding: 8, showDataLabels: true }
        		},
	      		legend:{show:true},
	      		title: {
       				text: 'Your study locations',   // title for the plot,
        			show: true
    			}
	    	});
    		
    		
    		
   		
   	
   		

    		
    		
    			
    			<%for(int a=0; a<coursesstudent.size();a++){%>
    					var c<%=a%> = <%=stat.myTypeCheese(coursesstudent.get(a))%>;
    					
    					
    					
    					var plotc<%=a%> = jQuery.jqplot ('chartc<%=a%>', [c<%=a%>], { 
	      					height: 300,
	      					width: 500,
	      					seriesDefaults:{
           						renderer:$.jqplot.PieRenderer, 
            					trendline:{ show:false }, 
            					rendererOptions: { padding: 8, showDataLabels: true }
        					},
	      					legend:{show:true},
	      					title: {
       							text: 'try',   // title for the plot,
        						show: true
    						}
	    				});
	    				
	    				var d1<%=a%> = <%=stat.typeBar(coursesstudent.get(a)).get(0)%>;
    					var d2<%=a%> = <%=stat.typeBar(coursesstudent.get(a)).get(1)%>;
    					
    					var plotd<%=a%> = $.jqplot('chartd<%=a%>', [d1<%=a%>], {
       					// The "seriesDefaults" option is an options object that will
        				// be applied to all series in the chart.
        					seriesDefaults:{
            					renderer:$.jqplot.BarRenderer,
           						pointlabels:{show:true},
        					},
        				// Custom labels for the series are specified with the "label"
        				// option on the series option.  Here a series option object
        				// is specified for each series.
       						series:[
            					{label:'My stats'}
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
                					ticks: d2<%=a%>
            					}
            				// Pad the y axis just a little so bars can get close to, but
            				// not touch, the grid boundaries.  1.2 is the default padding.
            		
        					},
        					title: {
       							text: 'comparison between you and the average for each course',   // title for the plot,
        						show: true
    						}
    					});
    					
    					
    					var e<%=a%> = <%=stat.meVSGoal2(coursesstudent.get(a))%>; //int
    					var f<%=a%> = <%=stat.meVSModel2(coursesstudent.get(a))%>;//int
    					
    					
    					
    					var g<%=a%> = <%=stat.myRatingBar(coursesstudent.get(a))%>;
    					
    					var plotg<%=a%> = $.jqplot('chartg<%=a%>', [g<%=a%>], {
       					// The "seriesDefaults" option is an options object that will
        				// be applied to all series in the chart.
        					seriesDefaults:{
            					renderer:$.jqplot.BarRenderer,
           						pointlabels:{show:true},
        					},
        				// Custom labels for the series are specified with the "label"
        				// option on the series option.  Here a series option object
        				// is specified for each series.
       						series:[
            					{label:'My ratings'}
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
                					ticks: [1,2,3,4,5]
            					}
            				// Pad the y axis just a little so bars can get close to, but
            				// not touch, the grid boundaries.  1.2 is the default padding.
            		
        					},
        					title: {
       							text: 'comparison between you and the average for each course',   // title for the plot,
        						show: true
    						}
    					});

    			
    			<%}%>
    			
    			
    		
    			
    			
    			
    			
    		
    
    			
    			
    		
    		
			
 
 
	    	
	    

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
			    var startdate = <%=request.getAttribute("startDate")%>;
			
				if(startdate!=null){

					var someDate = new Date(startdate);
					var timerTime = new Date(someDate.getTime());
					var timerStart = currentTime.getTime()-someDate.getTime();
					timerTime.setTime(timerStart);
					// Setting up a interval, executed every 1000 milliseconds

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

				var someDate = new Date(2011,12,9,0,0,0);
			}	
			
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

				
				}

			function animation(clock, current, total)
				{
					// Calculating the current angle:
					var angle = (360/total)*(current);

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
    	<h1><img src="stylesheets/Logo.png"/> </br></h1>
    	<h2>Welcome <%=(String)request.getAttribute("username")%> ! </h2>
    	
		<% String bezig=(String)request.getAttribute("bezig");
		if(bezig!=null){%>
			<p allign="right"> <%=request.getAttribute("curract").toString()%>
				<div id="fancyClock"></div>
			</p>
			<li><a href="#fragment-2"><span>Stop Tracking</span></a></li>
		<%}
		else{%>
			<li><a href="#fragment-1"><span>Start Tracking</span></a></li>
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
			<font size="6">
			<b>See you soon !</b></font>
			</br>
			</br>
        	<form action="/logout" method="post">
				<input type="submit" class="check" value="Logout" name="logout">
			</form>
    	</div>

    	
</div>
</body>
</html>







