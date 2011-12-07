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
		<input type="button" id="onea" class="check" value="Scolair" name="wata" > </br>
		<input type="button" id="oneb" class="check" value="Extrascolair" name="watb" >
	</div>		
		<div style="display: none" id="two">
			<select name="welkvak" id="welkvak">
					<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");%>
 					<%for(int i=0 ; i<courses.size(); i++){%>
 						<option value="<%=courses.get(i).toString()%>"> <%=courses.get(i).toString()%></option>
    				<%}}%>
			</select>
    		<input type="submit" id="twoa" class="check" value="Submit" >				
		</div>		
		<div style="display: none" id="tree">
			<select name="watextra" id="watextra">
				<option value="Sleep"> Sleep</option>
 				<option value="Sport"> Sport</option>
 				<option value="Nightlife"> Nightlife</option>
			</select>
    		<input type="submit" id="treea" class="check" value="Submit" >	
		</div>		
		<div style="display: none" id="four">
			<input type="button" id="foura" class="check" value="Les" name="typea" ></br>
			<input type="button" id="fourb" class="check" value="Zelfstudie" name="typeb" ></br>
			<input type="button" id="fourc" class="check" value="Oefenzitting" name="typec" ></br>
		</div>
		<div style="display: none" id="five">
			Please confirm your choice </br>
				<form action="/start" method="post">
					<input  class="wat" name="wat" value="">
					<input  class="vak" name="vak" value="">
					<input  class="type" name="type" value="">
					<input  class="watextra" name="watextra" value="">
					<input type="submit" class="check" value="OK" name="ok">
				</form>	
				<form action="/home" method="post">
					<input type="submit" class="check" value="Don't confirm" name="home">
				</form>		
		</div>