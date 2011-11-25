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

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript"> 
	
	$(document).ready(function(){
		$(".flip1").click(function(){
   			 $(".panel1").slideToggle("slow");
 		});
 		$(".flip2").click(function(){
   			 $(".panel2").slideToggle("slow");
 		});
 		
	});
	</script>
 
	<style type="text/css"> 
	div.panel1,p.flip1,div.panel2,p.flip2
	{
		margin:0px;
		padding:5px;
		text-align:center;
		background:#e5eecc;
		border:solid 1px #c3c3c3;
	}
	
	div.panel1
	{
		height:50px;
		display:none;
	}
	div.panel2
	{
		height:220px;
		display:none;
	}
	
	</style>
	
</head>
 
<body>

<% String message=(String)request.getAttribute("message");
		if(message==null){}
		else{
		out.println(message);}%>
 
<div class="panel1">


	<form action="/login" method="post">
	User Name: <input type="text" name="username"> <br />
	Password:  <input type="password" name="password" />
	<input type="submit" value="Login" name="login"/>
	</form>


</div>
 
<p class="flip1">Login</p>


<div class="panel2">

	<form action="/register" method="post">
	User Name: <input type="text" name="username" > <br />
	Password:  <input type="password" name="password" /> <br />
	Confirm password:  <input type="password" name="confirmedpassword" /><br />
	First Name:  <input type="text" name="firstname" /><br />
	Last Name: <input type="text" name="lastname"> <br />
	<input type="radio" name="gender" value="male" /> Male<br />
	<input type="radio" name="gender" value="female" /> Female<br />
	r-number: <input type="text" name="rnumber"> <br />
	<input type="submit" value="Sign up" name="signup"/>
	</form>


</div>
 
<p class="flip2">Register</p>


 
</body>
</html>

 

	
