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
		$( "#check" ).button();
		
	});
	</script>

  
  
  
  <input type="checkbox" id="check" /><label for="check">vak</label>
 
  
  
 
  
		<form action="/courses" method="post">
			<%ArrayList courses = null; courses=(ArrayList)request.getAttribute("course");%>
			<%
 			for(int i=0 ; i<courses.size(); i++){
 			%>
 				
				<input type="checkbox"  id= "check" name="<%out.print(courses.get(i).toString());%>" value="<%out.print(courses.get(i).toString());%>" /><label ><%out.print(courses.get(i).toString());%></label>
	
    		<%
    		}
   	 	%>
	
		
		
		


<input type="submit" value="Submit" />
    </form>
    
   
	
  

</head>
</html>