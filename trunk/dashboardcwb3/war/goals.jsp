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
<script>
function validate(evt) {
  var theEvent = evt || window.event;
  var key = theEvent.keyCode || theEvent.which;
  key = String.fromCharCode( key );
  var regex = /[0-9]|\./;
  if( !regex.test(key) ) {
    theEvent.returnValue = false;
    if(theEvent.preventDefault) theEvent.preventDefault();
  }
};
</script>
Please fill in your goals for this week.</br>
o  a week is defined from Monday 0 am to Sunday 12 pm </br>
o  if you happen not to update your goals for the new week, we will set the identical goals as given for the previous week </br>
o  it is always possible to overwrite your goals.</br>
<form action=\goal  method="post">
	<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");
		ArrayList<Integer> goal=(ArrayList<Integer>) request.getAttribute("goal");
		if(goal.size()==0){
			for(int i=0; i<courses.size();i++){%>
			<%=courses.get(i).toString()%>:
				<input type="int" class="numbersOnly" onkeypress='validate(event)' name="<%=courses.get(i).toString()%>" value="0">	hours of activity </br>
			<%}
		}
		else{
			for(int i=0; i<courses.size();i++){%>
				<%=courses.get(i).toString()%>:
				<input type="int" class="numbersOnly" onkeypress='validate(event)' name="<%=courses.get(i).toString()%>" value=<%=goal.get(i)%>>	</br>
			<%}
		
		
		}}%>
		
	
<input type="submit" id="submit" class="check" value="Submit">	
</form>