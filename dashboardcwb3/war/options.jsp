<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="course.Course"%>
 <script>
	$(function() {
		$( ".check" ).button();		
	});
	</script>
	<font size="6">
	<b>Update your curriculum </b></font>
	</br>
	</br>
<li>Courses you are currently subscribed to are listed in <t style="color:orange;">orange</t>.</li>
</br>
<li>Courses you can still subscribe to are listed in <t style="color:3399FF;">blue</t>.</li>
</br>
<li><b>Beware!</b> Submitting the deletion of a course, deletes all your information regarding this course! Resubmitting to this very cours will NOT restore your statistics concerning this course, but start from 0.</li>
</br>
<li>Any submitted change will affect your Goals : they will be resetted to 0.</li>
</br>

<%String message=(String) request.getAttribute("message");
  if(message==null){}
  else{%>
	<div id="dialog" title="Failed">
		<p><%=message%></p>
	</div>
	<%}%>
	<form action="/courses" method="post">
				<%
				ArrayList<Course> allcourses = null; allcourses=(ArrayList<Course>)request.getAttribute("allcourses");
				ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");	
				ArrayList<String> alleVakken = new ArrayList<String>();
				for (Course course: allcourses){
					alleVakken.add(course.toString());
				}
				ArrayList<String> vakken = new ArrayList<String>();
				for (Course course: courses){
					vakken.add(course.toString());
				}
				for(int i=0 ; i<allcourses.size(); i++){
					if(vakken.contains(alleVakken.get(i))){%>
						<div style="text-align:center"; >
						<input type="checkbox" checked="yes" class="check" id="<%out.print(allcourses.get(i).toString());%>" name="<%out.print(allcourses.get(i).toString());%>"/><label for="<%out.print(allcourses.get(i).toString());%>" style="width:400px"><%out.print(allcourses.get(i).toString());%></label></br>
						</div>
					<%}
					else{%>
						<div style="text-align:center"; >
						<input type="checkbox" class="check" id="<%out.print(allcourses.get(i).toString());%>" name="<%out.print(allcourses.get(i).toString());%>"/><label for="<%out.print(allcourses.get(i).toString());%>" style="width:400px"><%out.print(allcourses.get(i).toString());%></label></br>
						</div>
					<%}		
				}%></br>
				<div style="text-align:center"; >
				<input type="submit" class="check" value="Submit" name="submit"/>
				</div>
			</form>	
<script>
$("form").submit(function() {
      var n = $("input:checked").length;
      if(n==0){
      	alert("Please select at least one course");
      	return false;
      }
      else{
      return true;
      }
    });
</script>
