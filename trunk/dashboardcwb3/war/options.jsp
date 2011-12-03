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

Courses you are currently subscribed to are in orange.</br>
Courses you can still subscribe to are in blue.</br>
Be aware, deleting a course once, deletes all your information regarding this course!</br> 

<%String message=(String) request.getAttribute("message");
  if(message==null){}
  else{%>
	<div id="dialog" title="Failed">
		<p><%=message%></p>
	</div>
	<%}%>

	<form action="/courses" method="post">
				<%ArrayList courses = null; courses=(ArrayList)request.getAttribute("courses");
				ArrayList allcourses = null; allcourses=(ArrayList)request.getAttribute("allcourses");
				for(int i=0 ; i<allcourses.size(); i++){
					if(courses.contains(allcourses.get(i))){%>
						<input type="checkbox" checked="yes" class="check" id="<%out.print(allcourses.get(i).toString());%>" name="<%out.print(allcourses.get(i).toString());%>"/><label for="<%out.print(allcourses.get(i).toString());%>" style="width:400px"><%out.print(allcourses.get(i).toString());%></label></br>
					<%}
					else{%>
						<input type="checkbox" class="check" id="<%out.print(allcourses.get(i).toString());%>" name="<%out.print(allcourses.get(i).toString());%>"/><label for="<%out.print(allcourses.get(i).toString());%>" style="width:400px"><%out.print(allcourses.get(i).toString());%></label></br>
					<%}
		
				}%></br>
				<input type="submit" class="check" value="Submit" name="submit"/>
			</form>	
<script>
$("form").submit(function() {
      var n = $("input:checked").length;
      if(n==0){
      	alert("Please select at least one course");
      	return false;
      }
      else{
      return true;
      }
    });
</script>


	
    		
   	 	
   	 	</body>
</html>