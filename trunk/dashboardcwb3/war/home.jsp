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
  $(document).ready(function() {
    $("#tabs").tabs();
    $( ".check" ).button();		
  });
  </script>
</head>
<body style="font-size:62.5%;">
  
<div id="tabs">
	<ul>
    	<h1> Username!! </h1>
    	
    	<%String bezig=(String)request.getAttribute("bezig");
		if(bezig.equals("ja")){%>
			<li><a href="#fragment-2"><span>Stop</span></a></li>
		<%}
		else{%>
			<li><a href="#fragment-1"><span>Start</span></a></li>
		<%}%>
 
        <li><a href="#fragment-3"><span>Statistics</span></a></li>
        <li><a href="#fragment-4"><span>Goals</span></a></li>
        <li><a href="#fragment-5"><span>Course information</span></a></li>
        <li><a href="#fragment-6"><span>Options</span></a></li>
        <li><a href="#fragment-7"><span>Logout</span></a></li>
    </ul>
    
    	<%if(bezig.equals("ja")){%>
			<div id="fragment-2">
      			vragenlijst inladen </br>
				<form action="/home" method="post">
					<input type="submit" class="check" value="Stop activity" name="stop">
				</form>
  			</div>
		<%}
		if(!bezig.equals("ja")){%>
			<div id="fragment-1">
        		<%! String voorstel=null;
  					String scol=null;
   					String extra=null;
    				String vak=null;
    				String type=null;
    			%>
    			
    			<% if(request.getParameter("scolair")==null && request.getParameter("extrascolair")==null  && request.getParameter("gekozenvak")==null && request.getParameter("les")==null && request.getParameter("oefenzitting")==null && request.getParameter("zelfstudie")==null ){%>
					<form method="post">
						<input type="submit" class="check" value="Scolair" name="scolair" >
					</form>
					<form  method="post">
						<input type="submit" class="check" value="Extrascolair" name="extrascolair">
					</form>	
				<%voorstel=null;
				}
				
				if(request.getParameter("scolair")!=null && request.getParameter("gekozenvak")==null){%>
					<form method="post">
 						<% ArrayList courses = null; courses=(ArrayList)request.getAttribute("courses");
 						for(int i=0 ; i<courses.size(); i++){%>
    						<input type="radio" name="gekozenvak" value="<%out.print(courses.get(i).toString());%>" /> <%out.print(courses.get(i).toString());%><br/>	
    					<%}%>
    					<input type="submit" class="check" value="Submit" >
    				</form>
  	  				<%scol="scolair";%>
  	  				<%voorstel=null;%>
  				<%}
	
				if(request.getParameter("extrascolair")!=null){%>
					Dit is momenteel nog niet beschikbaar,gelieve terug naar de homepage te gaan.
					<form  	action="/home" method="post">
						<input type="submit" class="check" value="Home" name="home">
					</form>
					<%extra="extrascolair";%>
					<%voorstel=null;%>
				<%}
	
				if(request.getParameter("gekozenvak")!=null && request.getParameter("les")==null && request.getParameter("zelfstudie")==null && request.getParameter("oefenzitting")==null){%>
					<form method="post">
						<input type="submit" class="check" value="Les" name="les" >
					</form>
					<form method="post">
						<input type="submit" class="check" value="Zelfstudie" name="zelfstudie" >
					</form>	
					<form method="post">
						<input type="submit" class="check" value="Oefenzitting" name="oefenzitting" >
					</form>	
					<% vak=request.getParameter("gekozenvak");%>
					<%voorstel=null;%>
				<%}
	
				if((request.getParameter("les")!=null || request.getParameter("zelfstudie")!=null || request.getParameter("oefenzitting")!=null)){%>
					Please confirm this choice: </br>
					<%
					if(request.getParameter("les")!=null){
						type="les";
					}
					if(request.getParameter("zelfstudie")!=null){
						type="zelfstudie";
					}
					if(request.getParameter("oefenzitting")!=null){
						type="oefenzitting";
					}
					
					if(extra==null && scol!=null && vak!=null && type!=null){
						voorstel = scol + " --> " + vak +" --> "+ type;
					}
					if(scol==null && extra!=null){
						voorstel = extra;
					}
					%>
					<%=voorstel%>
					<%
					session.setAttribute("scol",scol);
					session.setAttribute("extra",extra);
					session.setAttribute("type",type);
					session.setAttribute("vak",vak);
					session.setAttribute("voorstel",voorstel);
					%>
		
					<form action="/home" method="post">
						<input type="submit" class="check" value="OK" name="ok">
					</form>	
	
					<form action="/home" method="post">
						<input type="submit" class="check" value="Don't confirm" name="home">
					</form>		
	
	
	
					<%}%>
				</div>
		<%}%>
  
    	<div id="fragment-3">
        	<% ArrayList activities = null; activities=(ArrayList)request.getAttribute("activities");
        	if(activities.size()==0){%>
        		<p>You have no activities</p>
        	<%}
        	for(int i=0; i<activities.size(); i++){
        		//tostring==>error
        	}%>
    	</div>
    	<div id="fragment-4">
        	goals
    	</div>
    	<div id="fragment-5">
        	course
    	</div>
    	<div id="fragment-6">
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
				<input type="submit" class="check" value="Submit" name="submitcourse"/>
			</form>	
    	</div>
    	<div id="fragment-7">
        	<form action="/logout" method="post">
				<input type="submit" class="check" value="Logout" name="logout">
			</form>
    	</div>
</div>
</body>
</html>







