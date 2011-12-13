<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="activity.*" %>
<%@ page import="course.*"%>
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
	<div id="one">
		Is the activity you want to track studyrelated or not ? </br>
		<input type="button" id="onea" class="check" value="Yes" name="wata" > </br>
		<input type="button" id="oneb" class="check" value="No" name="watb" >
	</div>		
		<div style="display: none" id="two">
			Select a course </br>
			<select name="welkvak" id="welkvak">
					<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");%>
 					<%for(int i=0 ; i<courses.size(); i++){%>
 						<option value="<%=courses.get(i).toString()%>"> <%=courses.get(i).toString()%></option>
    				<%}}%>
			</select>
    		<input type="submit" id="twoa" class="check" value="Submit" ></br>
    		<input type="submit" id="backonea" class="check" value="Back">				
		</div>		
		<div style="display: none" id="tree">
			Select what you wish to track </br>
			<select name="watextra" id="watextra">
				<option value="Sleep"> Sleep</option>
 				<option value="Sport"> Sport</option>
 				<option value="Nightlife"> Nightlife</option>
			</select>
    		<input type="submit" id="treea" class="check" value="Submit" ></br>
    		<input type="submit" id="backoneb" class="check" value="Back">	
		</div>		
		<div style="display: none" id="four">
			Select the type of your activity </br>
			<input type="button" id="foura" class="check" value="A lecture" name="typea" ></br>
			<input type="button" id="fourb" class="check" value="Studying" name="typeb" ></br>
			<input type="button" id="fourc" class="check" value="Practice / lab / exercising" name="typec" ></br></br>
    		<input type="submit" id="backtwo" class="check" value="Back">
		</div>
		<div style="display: none" id="five">
							
			Confirm your choice or go back to Home </br>
				<form action="/start" method="post">
					<input class="wat" name="wat" value="">
							<input  class="vak" name="vak" value="">
							<input  class="type" name="type" value="">
							<input   class="watextra" name="watextra" value="">
					<input type="submit" class="check" value="OK" name="ok">
				</form>	
				<form action="/home" method="post">
					<input type="submit" class="check" value="Don't confirm" name="home">
				</form>		
		</div>