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


	<%! 
	int keuze1 = -1;
	int keuze2=-1;
	int keuze3=-1;
	%>

	<%!
		public void setKeuze(int i, int j)
		{
			if(i==1){
				keuze1=j;
			}
			if(i==2){
				keuze2=j;
			}
			if(i==3){
				keuze3=j;
			}
		}
	%>

	
	
	<% if(keuze1==-1){
	%>
	<form action="setKeuze(1,0)" methode="post">
	<input type="button" value="Scolair" name="Scolair" onclick="setKeuze(1,0)" >
	</form>
	<form action="setKeuze(1,1)" methode="post">
	<input type="button" value="Extrascolair" name="extrascolair" >
	</form>
	
	
	<%	
	}%>
	
	
	
	<% if(keuze1==0 && keuze2==-1){
	%>
	<form>
 		<%
 		ArrayList courses = null; courses=(ArrayList)request.getAttribute("courses");
 		for(int i=0 ; i<courses.size(); i++){
 		%>
    	<input type="radio" name="gekozenvak" value="<%out.print(courses.get(i).toString());%>" /> <%out.print(courses.get(i).toString());%><br/>	
    	<%
    	}
    	%>
    	<input type="submit" value="Submit" onclick="<%setKeuze(2,0);%>">
    	
    </form>
	<%
	}
	%>	
	
	
	<% if(keuze1==0 && keuze2==0 && keuze3==-1){%>
	
	<form >
	<input type="submit" value="Les" name="les" onclick="<%setKeuze(3,0);%>">
	</form>
	
	<form >
	<input type="submit" value="Zelfstudie" name="zelfstudie" onclick="<%setKeuze(3,1);%>">
	</form>
		
	<form >
	<input type="submit" value="Oefenzitting" name="oefenzitting" onclick="<%setKeuze(3,2);%>">
	</form>	
	<%
	}
	%>
	
	
	
	
	<% if(!(keuze1==-1 && keuze2==-1 && keuze3==-1)){%>
	<% String gekozenvak=(String)request.getParameter("gekozenvak");%>
	Please confirm this choice: </br>
	<% String voorstel="";
	if(keuze1==0){
		voorstel= "Scolair";}
	if(keuze1==1){
		voorstel="Extrascolair";}
	voorstel=voorstel + " --> " + gekozenvak + " --> ";
	if(keuze3==0){
		voorstel=voorstel + "Les";}
	if(keuze3==1){
		voorstel=voorstel + "Zelfstudie";}
	if(keuze3==2){
		voorstel=voorstel + "Oefenzitting";}
	System.out.println(voorstel);%>
	
		
	<form action="/home" method="post">
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