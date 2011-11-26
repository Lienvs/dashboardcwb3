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
  
  
  <script>
	$(function() {
		$( ".check" ).button();		
	});
	</script>
	



</head>

<body style="font-size:62.5%;">

<form action="/courses" method="post">
	<%ArrayList courses = null; courses=(ArrayList)request.getAttribute("course");
	for(int i=0 ; i<courses.size(); i++){%>
		<input type="checkbox" class="check" id="<%out.print(courses.get(i).toString());%>" name="<%out.print(courses.get(i).toString());%>"/><label for="<%out.print(courses.get(i).toString());%>" style="width:400px"><%out.print(courses.get(i).toString());%></label></br>
	<%}%></br>
	<input type="submit" class="check" value="Submit"/>
</form>	







		
	
    		
   	 	
   	 	</body>
</html>