<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
 <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css"/>
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
 <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script> 
 <script>
	$(function() {
		$( ".check" ).button();	
	    $("#tabs").tabs();
		$( "#dialog" ).dialog();	
	});
	</script>
</head>
<body>
<div class="tabs" id="tabs">
	<ul>
    	<h1> <img src="stylesheets/Logo.png"/></br></h1>
    	<h4> Select the courses you wish to use on this App. </br>
		We advise you take  all the courses you are subscribed to for your actual study : it will promote the accuracy of your tracking!  </br>  </h4>
    	
 
       
    </ul>
</div>

<%String message=(String) request.getAttribute("message");
  if(message==null){}
  else{%>
	<div id="dialog" title="Failed">
		<p><%=message%></p>
	</div>
	<%}%>
	
<form action="/courses" method="post">
	<%ArrayList courses = null; courses=(ArrayList)request.getAttribute("course");
	for(int i=0 ; i<courses.size(); i++){%>
		<input type="checkbox" class="check" id="<%out.print(courses.get(i).toString());%>" name="<%out.print(courses.get(i).toString());%>"/><label for="<%out.print(courses.get(i).toString());%>" style="width:800px"><%out.print(courses.get(i).toString());%></label></br>
	<%}%></br>
	<input type="submit" class="check" value="Submit" name="submitcourse"/>
</form>	
</body>
</html>