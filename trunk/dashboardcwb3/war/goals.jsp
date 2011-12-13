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
<font size="6">
<b>Define your goals for the week</b></font>
</br>
</br>
<li>  a week is defined from Monday 0 am to Sunday 12 pm </li></br>
<li>  if you happen not to update your goals for the new week, we will set the identical goals as given for the previous week</li> </br>
<li>  it is always possible to overwrite your goals.</li>
</br>
</br>
<b>Choose the amount of hours you want to spend on each of your courses this week</b>
<form action=\goal  method="post">
	<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");
		ArrayList<Integer> goal=(ArrayList<Integer>) request.getAttribute("goal");
		if(goal.size()==0){
			for(int i=0; i<courses.size();i++){%>
			<DD> <%=courses.get(i).toString()%>:
					<select id="test"  name="<%=courses.get(i).toString()%>">
		        	<%for(int j=0; j<=30;j++){%>
						<option value="<%=j%>"><%=j%></option><%}%> 
		        	</select>
		</br>
	
			<%}

		}
		else{
			for(int k=0; k<courses.size();k++){%>
				<%=courses.get(k).toString()%> :
				 
					<select id="test"  name="<%=courses.get(k).toString()%>">
		        	<%for(int l=0; l<=30;l++){%>
						<option value="<%=l%>"> <%=l%></option><%}%>
		        	</select>
					 (current goal : <%=goal.get(k)%> hours )</br>
			<%}
		
		
		}}%>
		</br>
	
<input type="submit" id="submit" class="check" value="Submit">
</form>