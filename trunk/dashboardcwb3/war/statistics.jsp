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
  <link rel="stylesheet" type="text/css" href="/stylesheets/jquery.jqplot.min.css" />
 <script>
	 $(document).ready(function() {
		$("#accordion").accordion({
		autoHeight: false,
		navigation: true
    		});	
    	$("#accordioncourses").accordion({
		autoHeight: false,
		navigation: true
    		});			
			 $('#chart1').bind('accordionchange', function(event, ui) {
      			var index = $(this).find("h3").index ( ui.newHeader[0] );
      			if (index === 1) {
       				 plot1.replot();
     			 }
     			 else{ plot1.destroy();}
   			 });
   			 $('#chart2').bind('accordionchange', function(event, ui) {
      			var index = $(this).find("h3").index ( ui.newHeader[0] );
      			if (index === 2) {
       				 plot2.replot();
     			 }
     			 else{ plot2.destroy();}
   			 });
	});
	</script>
<div id="accordion">
		<h3><a href="#">Compairison of all your courses</a></h3>
		<div>
			<div id="chart1"></div>

		</div>
		<h3><a href="#">Compairison of all your places</a></h3>
		<div>
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
		<h3><a href="#">Section 4</a></h3>
		<div>
		sec4
		</div>
</div>