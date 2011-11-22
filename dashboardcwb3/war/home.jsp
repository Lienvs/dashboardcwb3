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




<table width="1500" border="0">
<tr>
<td colspan="2" style="background-color:#FFA500;text-align:center">
<h1>Hello</h1>
</td>
</tr>

<tr valign="top">
<td style="background-color:#C0C0C0;width:100px;text-align:top;">
<b>Menu</b><br />
<form action="/start" method="post">
	<input type="submit" value="Start" name="start">
	</form> <br />
<form action="home.jsp" method="post">
	<input type="submit" value="Stop" name="stop">
	</form><br />
<form action="statistics.jsp" method="post">
	<input type="submit" value="Statistics" name="statistics">
	</form><br />
	<form action="options.jsp" method="post">
	<input type="submit" value="Options" name="options">
	</form><br />
	<form action="/logout" method="post">
	<input type="submit" value="Logout" name="logout">		
	</form>
	
</td>
<td style="background-color:#EEEEEE;height:200px;width:1400px;text-align:top;">
Content goes here</td>
</tr>



</body>



</html>