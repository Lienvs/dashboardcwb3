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
 <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  <script language="javascript" type="text/javascript" src="/js/jquery.jqplot.min.js"></script>
  <script language="javascript" type="text/javascript" src="/plugin/jqplot.pieRenderer.min.js"></script>
  <script language="javascript" type="text/javascript" src="/plugin/jqplot.barRenderer.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/stylesheets/jquery.jqplot.min.css" />
 <script>
	 $(document).ready(function() {
		$("#accordion").accordion({
		autoHeight: false,
		navigation: true,
		collapsible: true
    		});	
    	$("#accordioncourses").accordion({
		autoHeight: false,
		navigation: true,
		collapsible: true
    		});		
    		$('#accordion').bind('accordionchange', function(event, ui) {
     			if (ui.index === 1 && plot1._drawCount === 0) {
       				plot1.replot();
       				plot3.replot();
      			}
      			else if (ui.index === 2 && plot2._drawCount === 0) {
        			plot2.replot();
     			}
    		});	
   			
	});
	</script>


	
	
<div id="accordion">
		<h3><a href="#">Compairison of all your courses</a></h3>
		<div>
			<%=request.getAttribute("myCoursesCheese")%>
			<div id="chart1"></div>
			<%=request.getAttribute("myCourseBar1")%>
			<%=request.getAttribute("myCourseBar2")%>
			<%=request.getAttribute("myCourseBar3")%>
			<div id="chart3"></div>
			<%=request.getAttribute("myFunInTime")%>
			<div id="chart4"></div>
			<%=request.getAttribute("myNightlifeInTime")%>
			<div id="chart5"></div>
			<%=request.getAttribute("mySleepInTime")%>
			<div id="chart6"></div>
			<%=request.getAttribute("mySportInTime")%>
			<div id="chart7"></div>
			<%=request.getAttribute("myTimeInTime")%>
			<div id="chart8"></div>
			<%=request.getAttribute("overallMeanTimeInTime")%>
			<div id="chart9"></div>

			
		</div>
		<h3><a href="#">Compairison of all your places</a></h3>
		<div>
			<%=request.getAttribute("myPlacesCheese")%>
				<div id="chart2"></div>	
		</div>
		<h3><a href="#">Your courses</a></h3>
		<div>
			<div id="accordioncourses">
				<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");
				for(int j=0; j<courses.size(); j++){%>
					<h3><a href="#"><%=courses.get(j).toString()%></a></h3>
					<div>
						
					</div>
				<%}}%>
			</div>
		</div>
		<h3><a href="#">All your activities</a></h3>
		<div>
		
			<%ArrayList<Activity> scol=(ArrayList<Activity>) request.getAttribute("scol");%>
<%ArrayList<Activity> extra=(ArrayList<Activity>) request.getAttribute("extra");%>
<%if(scol.size()!=0 || extra.size()!=0){%>
	<%if(scol.size()!=0){%>
		<h3> Scolair activities:</h3>
		<table border="1">
			<tr>
				<th >Course</th>
				<th >Type</th>
				<th >Duration</th>
				<th >Start</th>
				<th >Rating</th>
				<th >Comment</th>
			</tr>
			
			<%for(int i=0; i<scol.size();i++){%>
				<tr>
					<td><%=scol.get(i).getCourse().toString()%></td>
					<td><%=scol.get(i).getType()%></td>
					
					<%if(scol.get(i).equals(request.getAttribute("curract"))){%>
						<td> This is your current activity </td>
					<%}
					else{%>
						<td><%=scol.get(i).getDuration()%></td>
					<%}%>
					
					
					<td><%=scol.get(i).startDateToString()%></td>
					<td><%=scol.get(i).getRating()%></td>
					<td><%=scol.get(i).getComment()%></td>
				</tr>
			<%}%>
		</table>
	<%}%>
	
	

	<%if(extra.size()!=0){%>
		<h3> ExtraScolair activities:</h3>
		
		<table border="1">
			<tr>
				<th >Type</th>
				<th >Duration</th>
				<th >Start</th>
				<th >Rating</th>
				<th >Comment</th>
			</tr>
			
			<%for(int i=0; i<extra.size();i++){%>
				<tr>
					<td><%=extra.get(i).getType()%></td>
					
					<%if(extra.get(i).equals(request.getAttribute("curract"))){%>
						<td> This is your current activity </td>
					<%}
					else{%>
						<td><%=extra.get(i).getDuration()%></td>
					<%}%>
					
					
					<td><%=extra.get(i).startDateToString()	%></td>
					<td><%=extra.get(i).getRating()%></td>
					<td><%=extra.get(i).getComment()%></td>
				</tr>
			<%}%>
		</table>
	<%}%>




<%}
else{%>

<p> You have no activities. </p>

<%}%>




	
		
		</div>
</div>