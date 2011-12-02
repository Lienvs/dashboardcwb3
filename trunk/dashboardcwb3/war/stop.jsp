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
  
  </head>
<body style="font-size:62.5%;">

  <form action="/home" method="post">
  			<% Activity curract= (Activity) request.getAttribute("curract");%>
  			<% if(curract.getActivityType()=="fun"){
  			}%>
  			<% if(curract.getActivityType()=="scolair"){
  				if(curract.getType()=="les"){
  				
  				}
  				if(curract.getType()=="oefenzitting"){
  				
  				}
  				if(curract.getType()=="zelfstudie"){%>
  					Study location: 
					<select name="place" id="place">
						<% ArrayList places = null; places=(ArrayList)request.getAttribute("plaatsen");
 						for(int i=0 ; i<places.size(); i++){%>
 							<option value="<%out.print(places.get(i));%>"> <%out.print(places.get(i));%></option>
    					<%}%>
					</select><br />
					Study type: 
					<select name="stype" id="stype">
						<option value="Theory"> Theory</option>
 						<option value="Practice"> Practice</option>
					</select><br />
  				<%}%>
  			
  			
  			}%>
			rating: 
			<input type="radio" name="rating" value="1" /> 1
			<input type="radio" name="rating" value="2" /> 2
			<input type="radio" name="rating" value="3" /> 3
			<input type="radio" name="rating" value="4" /> 4
			<input type="radio" name="rating" value="5" /> 5</br>
			comment: <textarea name="comment" id="comment">
  				</textarea></br>
			<input type="submit" class="check" name="stop" value="Stop"/>
		</form>
  

  
  
  </body>
  </html>