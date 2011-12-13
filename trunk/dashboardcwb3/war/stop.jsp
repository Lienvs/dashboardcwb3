<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="activity.*" %>
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
<script>
	$(function() {
		var availableTags = <%=request.getAttribute("allusersstring")%>;
		$( "#tags" ).autocomplete({
			source: availableTags
		});
	});
	</script>
	
	<font size="6">
	<b>Fill in to stop tracking</b></font>
	</br></br>
    <form action="/stop" method="post">
  			<%{ Activity act=(Activity) request.getAttribute("curract");
  			if(act.getType().equals("Zelfstudie")){%>
  				Study location: 
  				<% ArrayList places = null; places=(ArrayList)request.getAttribute("plaatsen");%>
						<select name="place" id="place">
							<%
 							for(int i=0 ; i<places.size(); i++){
 								if(places.get(i).equals("Other")){%>
 									<option SELECTED value="<%out.print(places.get(i));%>" > <%out.print(places.get(i));%></option>
 								<%}
								else{%>
								<option value="<%out.print(places.get(i));%>" > <%out.print(places.get(i));%></option>
 								<%}	
    				  		}%>
							
						</select>
						</br></br>
						Was it rather theoretical or more focussed on practice ? 
						<select name="stype" id="stype">
							<option value="Theory"> Theory</option>
 							<option value="Practice"> Practice</option>
						</select><br /> </br>
				<div class="ui-widget">
					<b> Studied with a friend ? </b></br>
				    Select your studybuddy (only if he is also on this App)  
					
					<input name="studybuddy" id="tags">	
				</div>			</br>					
			<%}}%>
			</br>
			Give a rating of your productivity (1 = deceiving ; 5 = optimal) </br>
				<input type="radio" name="rating" value="1" /> 1
				<input type="radio" name="rating" value="2" /> 2
				<input type="radio" name="rating" value="3" /> 3
				<input type="radio" name="rating" value="4" /> 4
				<input type="radio" name="rating" value="5" /> 5 </br> </br>
			Perhaps a comment ?   
			</br>
			<textarea name="comment" id="comment"> </textarea>
			</br></br>
			<input type="submit" class="check" name="stop" value="Stop"/>
		</form>