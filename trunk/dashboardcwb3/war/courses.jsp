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
		$("#accordioncourse").accordion({
		autoHeight: false,
		navigation: true
    		});		
	});
</script>
<div id="accordioncourse">
<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");
	for(int j=0; j<courses.size(); j++){%>
		<h3><a href="#"><%=courses.get(j).toString()%></a></h3>
		<div>
			This course is taught by <%=courses.get(j).getProf().getName()%> and has <%=courses.get(j).getStudyPoints()%> study credits.</br>
			This means the ideal student is expected by the University to spend <%=30*courses.get(j).getStudyPoints()%> hours to the study of this course. </br>
			There are <%=courses.get(j).getTotalLecture()%> class hours and <%=courses.get(j).getTotalPractice()%> hours of exercise sessions scheduled for this course.
		</div>
	<%}}%>
</div>