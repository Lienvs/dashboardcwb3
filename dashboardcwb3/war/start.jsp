
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
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
    <%! String voorstel=null;
    String scol=null;
    String extra=null;
    String vak=null;
    String type=null;
    %>
    
    
    
  </head>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
<body>

	<% if(request.getParameter("scolair")==null && request.getParameter("extrascolair")==null  && request.getParameter("gekozenvak")==null && request.getParameter("les")==null && request.getParameter("oefenzitting")==null && request.getParameter("zelfstudie")==null ){%>
	<form method="post">
	<input type="submit" value="Scolair" name="scolair" >
	</form>
	<form  method="post">
	<input type="submit" value="Extrascolair" name="extrascolair">
	</form>
	<%}%>
	
	
	
	<% if(request.getParameter("scolair")!=null && request.getParameter("gekozenvak")==null){%>
		
		<form method="post">
 		<%
 		ArrayList courses = null; courses=(ArrayList)request.getAttribute("courses");
 		for(int i=0 ; i<courses.size(); i++){
 		%>
    	<input type="radio" name="gekozenvak" value="<%out.print(courses.get(i).toString());%>" /> <%out.print(courses.get(i).toString());%><br/>	
    	<%
    	}
    	%>
    	<input type="submit" value="Submit" >
    	
  	  </form>
  	
  	  <%scol="scolair";%>
  	
	<%}%>
	
	<% if(request.getParameter("extrascolair")!=null){%>
		Dit is momenteel nog niet beschikbaar,gelieve terug naar de homepage te gaan.
		<form  	action="/home" method="post">
		<input type="submit" value="Home" name="home">
		</form>
	
		<%extra="extrascolair";%>
		
	<%}%>
	
	<% if(request.getParameter("gekozenvak")!=null && request.getParameter("les")==null && request.getParameter("zelfstudie")==null && request.getParameter("oefenzitting")==null){%>
		<form method="post">
		<input type="submit" value="Les" name="les" >
		</form>
		<form method="post">
		<input type="submit" value="Zelfstudie" name="zelfstudie" >
		</form>	
		<form method="post">
		<input type="submit" value="Oefenzitting" name="oefenzitting" >
		</form>	
		
		<% 
		vak=request.getParameter("gekozenvak");%>
		
	<%}%>
	
	<% if((request.getParameter("les")!=null || request.getParameter("zelfstudie")!=null || request.getParameter("oefenzitting")!=null)){%>
	
	Please confirm this choice: </br>
	
	<%
	if(request.getParameter("les")!=null){
		
		type="les";}
	if(request.getParameter("zelfstudie")!=null){
		
		type="zelfstudie";}
		
	if(request.getParameter("oefenzitting")!=null){
		
		type="oefenzitting";}
		
	%>
	
	<% if(extra==null && scol!=null && vak!=null && type!=null){
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
	<input type="submit" value="OK" name="ok">
	</form>	
	
	<form action="/home" method="post">
	<input type="submit" value="Don't confirm" name="home">
	</form>		
	
	
	
	<%}%>
	
	
	
	
	

</body>



</html>