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
  </head>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
<body>

	<% 
	int keuze1 = -1;
	int keuze2=-1;
	int keuze3=-1;
	%>
	
	<% if(keuze1==-1){
	%>
	<form action="#" method=setKeuze(1,0)>
	<input type="submit" value="Scolair" name="Scolair">
	</form>
	<form action="#" method=setKeuze11(1,1)>
	<input type="submit" value="Extrascolair" name="extrascolair">
	</form>
	<%	
	}%>
	
	<% private void setKeuze(int i, int j){
		if(i==1){
			keuze1=j;
		}
		if(i==2){
			keuze2=j;
		}
		if(i==3){
			keuze3=j;
		}
	}%>
	
	
	<% if(keuze1==0 && keuze2=-1){
	%>
	<form action="#" method=setKeuze(2,0)>
 		<%
 		ArrayList courses = null; courses=(ArrayList)request.getAttribute("courses");
 		for(int i=0 ; i<courses.size(); i++){
 		%>
    	<input type="radio" name="gekozenvak" value="<%out.print(courses.get(i).toString());%>" /> <%out.print(courses.get(i).toString());%><br/>	
    	<%
    	}
    	%>
    	<input type="submit" value="Submit" />
    </form>
	<%
	}
	%>	
	
	
	<% if(keuze1==0 && keuze2==0 && keuze3=-1){%>
	
	<form action="#" method=keuze(3,0)>
	<input type="submit" value="Les" name="les">
	</form>
	
	<form action="#" method=keuze(3,1)>
	<input type="submit" value="Zelfstudie" name="zelfstudie">
	</form>
		
	<form action="#" method=keuze(3,2)>
	<input type="submit" value="Oefenzitting" name="oefenzitting">
	</form>	
	<%
	}
	%>
	
	<% if(!(keuze1==-1 && keuze2==-1 && keuze3==-1)){%>
	Please confirm this choice: </br>
	<% String voorstel="";
	if(keuze1==0){
		voorstel= "Scolair";}
	if(keuze1==1){
		voorstel="Extrascolair";}
	voorstel=voorstel + " --> " + gekozenvak.getValue() + " --> ";
	if(keuze3==0){
		voorstel=voorstel + "Les";}
	if(keuze3==1){
		voorstel=voorstel + "Zelfstudie";}
	if(keuze3==2){
		voorstel=voorstel + "Oefenzitting";}
	System.out.println(voorstel);%>
	
		
	<form action="/starttrack" method="post">
	<input type="submit" value="OK" name="ok">
	</form>	
	
	<form action="/home" method="post">
	<input type="submit" value="Back to home" name="home">
	</form>		
	
	
	
	
	
	
	
	<%
	}
	%>
	
	






</body>



</html>