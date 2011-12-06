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

If you would like to stop the tracking of your current activity, please do fill in this form and click 'stop'.</br>
  <form action="/stop" method="post">
  			<% Activity act=(Activity) request.getAttribute("curract");
  			if(act.getType().equals("Zelfstudie")){%>
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
  

  
  
 