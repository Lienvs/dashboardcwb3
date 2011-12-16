<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="course.Course"%>
<script>
	 $(document).ready(function() {
		$("#accordioncourse").accordion({
		autoHeight: false,
		navigation: true
    		});		
	});
</script>

<font size="6">
<b>A short description of each course</b></font>
</br>
</br>
<div id="accordioncourse">
<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");
	for(int j=0; j<courses.size(); j++){%>
		<h3><a href="#"><%=courses.get(j).toString()%></a></h3>
		<div>
			This course is taught by <%=courses.get(j).getProf()%> and has <%=courses.get(j).getStudyPoints()%> study credits.</br>
			This means the ideal student is expected by the University to spend <%=30*courses.get(j).getStudyPoints()%> hours to the study of this course. </br>
			There are <%=courses.get(j).getTotalLecture()%> class hours and <%=courses.get(j).getTotalPractice()%> hours of exercise sessions scheduled for this course.
		</div>
	<%}}%>
</div>