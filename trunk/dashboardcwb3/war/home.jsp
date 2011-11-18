<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
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
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
<body>

	<script type="text/javascript">
	network.getCurrentUser().getUserName();
	</script>
	
	
	<form action="" method="post">
	<input type="submit" value="Start" name="start">
	</form>
	
	<form action="home.jsp" method="post">
	<input type="submit" value="Stop" name="stop">
	</form>
	
	<form action="statistics.jsp" method="post">
	<input type="submit" value="Statistics" name="statistics">
	</form>
	
	<form action="options.jsp" method="post">
	<input type="submit" value="Options" name="options">
	</form>
	
	<form action="/logout" method="post">
	<input type="submit" value="Logout" name="logout">		
	</form>
	
	
	
	
	
	

</body>



</html>