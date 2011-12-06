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
	 $(document).ready(function() {
		var stop = false;
		$( "#accordion h3" ).click(function( event ) {
			if ( stop ) {
				event.stopImmediatePropagation();
				event.preventDefault();
				stop = false;
			}
		});
		$( "#accordion" )
			.accordion({
				header: "> div > h3"
			})
			.sortable({
				axis: "y",
				handle: "h3",
				stop: function() {
					stop = true;
				}
			});
	});
	</script>

<div id="accordion">
<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");
	for(int j=0; j<courses.size(); j++){%>
	<div>
		<h3><a href="#"><%=courses.get(j).toString()%></a></h3>
		<div>
			This course is taught by <%=courses.get(j).getProf().getName()%> and has <%=courses.get(j).getStudyPoints()%> studypoints.</br>
		</div>
	</div>
	<%}}%>
</div>