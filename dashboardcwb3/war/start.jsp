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
    $('#onea').click(function() {
 		$('#one').hide();
 		$('#two').show();
 		$('.wat').val("Scolair");
 		
    });
    $('#oneb').click(function() {
 		$('#one').hide();
 		$('#tree').show();
 		$('.wat').val("Extrascolair");
    });
    $('#twoa').click(function() {
 		$('#two').hide();
 		$('#four').show();
 		var text=$("#welkvak").val();
 		$('.vak').val(text);
    });
    $('#foura').click(function() {
 		$('#four').hide();
 		$('#five').show();
 		$('.type').val("Les");

    });
    $('#fourb').click(function() {
 		$('#four').hide();
 		$('#five').show();
 		$('.type').val("Zelfstudie");
 		
    });
    $('#fourc').click(function() {
 		$('#four').hide();
 		$('#five').show();
 		$('.type').val("Oefenzitting");

    });
    
  });
  </script>
  

  <style type="text/css"> 

  </style>
  
</head>
<body style="font-size:62.5%;">

	<div id="one">
		<input type="button" id="onea" class="check" value="Scolair" name="wata" > </br>
		<input type="button" id="oneb" class="check" value="Extrascolair" name="watb" >
	</div>
		
		 
		
		
		<div style="display: none" id="two">
			<select name="welkvak" id="welkvak">
				<% ArrayList courses = null; courses=(ArrayList)request.getAttribute("courses");
 					for(int i=0 ; i<courses.size(); i++){%>
 						<option value="<%out.print(courses.get(i).toString());%>"> <%out.print(courses.get(i).toString());%></option>
    				<%}%>
			</select>
    		<input type="submit" id="twoa" class="check" value="Submit" >				
		</div>
		
		
		
		<div style="display: none" id="tree">
		Dit is momenteel nog niet beschikbaar,gelieve terug naar de homepage te gaan.
			<form  	action="/home" method="post">
				<input type="submit" class="check" value="Home" name="home">
			</form>
		</div>
		
		<div style="display: none" id="four">
			<input type="button" id="foura" class="check" value="Les" name="typea" ></br>
			<input type="button" id="fourb" class="check" value="Zelfstudie" name="typeb" ></br>
			<input type="button" id="fourc" class="check" value="Oefenzitting" name="typec" ></br>
		</div>
		
					
		
		

		<div style="display: none" id="five">

			Please confirm your choice </br>
				<form action="/home" method="post">
					<input type="hidden" class="wat" name="wat" value="">
					<input type="hidden" class="vak" name="vak" value="">
					<input type="hidden" class="type" name="type" value="">
					<input type="submit" class="check" value="OK" name="ok">
				</form>	
				<form action="/home" method="post">
					<input type="submit" class="check" value="Don't confirm" name="home">
				</form>		
		</div>

</body>
</html>