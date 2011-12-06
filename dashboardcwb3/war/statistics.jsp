<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="activity.*" %>

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



  
 

 <script>
	 $(document).ready(function() {
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
		var data = <%=request.getAttribute("comparisonCourses")%>;
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




<div id="accordion">
	<div>
		<h3><a href="#">Compairison of all your courses</a></h3>
		<div>
			<div id="chart1" style="height:300px; width:500px; " ></div>
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


