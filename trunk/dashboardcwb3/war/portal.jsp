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
  <script language="javascript" type="text/javascript" src="/plugin/jqplot.pieRenderer.min.js"></script>
<script language="javascript" type="text/javascript" src="/plugin/jqplot.barRenderer.min.js"></script>	 
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/stylesheets/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" type="text/css" href="/stylesheets/jquery.jqplot.min.css" />
	<script language="javascript" type="text/javascript" src="/js/jquery.jqplot.min.js"></script>
  <script>
  $(document).ready(function() {
    $("#accordion").accordion({
    	autoHeight: false,
		navigation: true
    });
    $( ".check" ).button();	
    $( "#dialog" ).dialog();
    $("#tabs").tabs();
  });
  </script>
</head>
<body style="font-size:62.5%;">
<div class="tabs" id="tabs">
	<ul>
    	<h1> <font size="10"> Learnkeeper </font> </br></h1>
    	<h2> This Application will allow you to track your study behavior at the KUL and model it in a very useful series of charts </br>
    		 Register and start tracking !</h2>
    	
 
       
    </ul>
</div>
<%String message1=(String) request.getAttribute("message1");
  String message2=(String) request.getAttribute("message2");
  if(message1==null){}
  else{%>
	<div id="dialog" title="<%=message1%>">
		<p><%=message2%></p>
	</div>
	<%}%> 
<div id="accordion">
	<h3><a href="#">Login</a></h3>
	<div>
		<form action="/login" method="post">
			Username: <input type="text" name="username"> <br />
			Password:  <input type="password" name="password" />
			<input type="submit" class="check" name="login" value="Login"/>
		</form>
	</div>
	<h3><a href="#">Register</a></h3>
	<div>
		<form action="/register" method="post">
			User Name: <input type="text" name="username" > <br />
			Password:  <input type="password" name="password" /> <br />
			Confirm password:  <input type="password" name="confirmedpassword" /><br />
			First Name:  <input type="text" name="firstname" /><br />
			Last Name: <input type="text" name="lastname"> <br />
			<input type="radio" name="gender" value="male" /> Male<br />
			<input type="radio" name="gender" value="female" /> Female<br />
			r-number: <input type="text" name="rnumber"> <br />
			<input type="submit" class="check" name="signup" value="Sign up"/>
		</form>
	</div>	
</div>
</body>
</html>