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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      




</html>

	<body>
	
	
	<% String message=(String)request.getAttribute("message");
		if(message==null){}
		else{
		 out.println(message);}%>

	<form action="/register" method="post">
	User Name: <input type="text" name="username" > <br />
	Password:  <input type="password" name="password" /> <br />
	Confirm password:  <input type="password" name="confirmedpassword" /><br />
	First Name:  <input type="text" name="firstname" /><br />
	Last Name: <input type="text" name="lastname"> <br />
	<input type="radio" name="gender" value="male" /> Male<br />
	<input type="radio" name="gender" value="female" /> Female<br />
	r-number: <input type="text" name="rnumber"> <br />
	<input type="submit" value="Sign up"/>
	</form>
	
	Already signed up? Login here:
	
	<form action="portal.jsp" method="post">
	<input type="submit" value="Login" name="login">
	</form>

</body>


</html>