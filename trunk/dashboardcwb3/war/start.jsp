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
  
  <script language="javascript" type="text/javascript" src="/js/jquery.jqplot.min.js"></script>
  <script language="javascript" type="text/javascript" src="/plugin/jqplot.pieRenderer.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/stylesheets/jquery.jqplot.css" />
  
  <script>
  $(document).ready(function() {
    $( ".check" ).button();		
  });
  </script>
</head>
<body style="font-size:62.5%;">

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
					<%System.err.println("fout");%>
					<form method="post">
 						<% ArrayList courses = null; courses=(ArrayList)request.getAttribute("courses");
 						for(int i=0 ; i<courses.size(); i++){%>
    						<input type="radio" name="gekozenvak" value="<%out.print(courses.get(i).toString());%>" /> <%out.print(courses.get(i).toString());%><br/>	
    					<%}%>
    					<input type="submit" class="check" value="Submit" >
    				</form>
  	  				<%scol="scolair";%>
  	  				<%voorstel=null;%>
  	  				<%System.err.println("fout2");%>
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
	
	
	
	

</body>



</html>