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

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript"> 
	
	$(document).ready(function(){
		$(".flip12").click(function(){
   			 $(".flip2a").slideToggle("slow");
   			 $(".flip2b").slideToggle("slow");
 		});
 		$(".flip2a").click(function(){
   			 $(".panel2a").slideToggle("slow");
 		});
 		
	});
	</script>
 
	<style type="text/css"> 
	div.panel2b,p.flip1a, p.flip1b, p.flip2a, p.flip2b
	{
		margin:0px;
		padding:5px;
		text-align:center;
		background:#e5eecc;
		border:solid 1px #c3c3c3;
	}
	div.panel2b,p.flip2a,p.flip2b
	{
		
		display:none;
	}
	
	
	
	</style>
	
</head>
 
<body>



<form action="/courses" method="post">

<p class="flip1b">Burgerlijk Ingenieur fase 1</p>

<p class="flip1a">Burgerlijk Ingenieur fase 2</p>

	<p class="flip2a">Eerste semster</p>
	<p class="flip2b">Tweede semester</p>



<div class="panel2a">

	<%ArrayList courses = null; courses=(ArrayList)request.getAttribute("course");%>
	<%
 		for(int i=0 ; i<courses.size(); i++){
 		%>
    	<input type="checkbox" name="<%out.print(courses.get(i).toString());%>" value="<%out.print(courses.get(i).toString());%>" /> <%out.print(courses.get(i).toString());%><br/>	
    	<%
    	}
    	%>
	
</div>

<input type="submit" value="Submit" />
    </form>
	



	


 






 
</body>
</html>

 

	








</body>



</html>

