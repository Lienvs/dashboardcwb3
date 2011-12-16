<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="course.Course"%>
<%@ page import="statistics.StatisticController"%>
<%@ page import="activity.Activity" %>

 <script>
	 $(document).ready(function() {
		$("#accordion").accordion({
		autoHeight: false,
		navigation: true,
		collapsible: true
    		});	
    	$("#accordioncourses").accordion({
		autoHeight: false,
		navigation: true,
		collapsible: true
    		});		
    		$('#accordion').bind('accordionchange', function(event, ui) {
     			if (ui.index === 1 && plot1._drawCount === 0) {
       				plot1.replot();
       				plot3.replot();
      			}
      			else if (ui.index === 2 && plot2._drawCount === 0) {
        			plot2.replot();
     			}
    		});	
   			
	});
	</script>

	<font size="6">
	<b>Your work trough stats</b></font>
	</br>
	</br>
	
	<% StatisticController stats=new StatisticController();%>
		Cummulated time of study: <b><%=stats.myTime()%></b> 
		</br>
		Longest study spree: <b><%=stats.getMaximumStudie()%></b>
		</br>
		
<div id="accordion">
		
		<h3><a href="#">Curricular</a></h3>
		<div>
		<%=stat.meVSModel().get(0)%>
		<%=stat.meVSModel().get(1)%>
		<%=stat.meVSModel().get(2)%>
		<%=stat.meVSModel().get(3)%>
			<div id="chart1"></div>
			<div id="chart20"></div>
			
			<div id="chart2"></div>	
			<div id="chart19"></div>
			
			<div id="chart3"></div> 
			
			<div id="chart8"></div>
			<div id="chart9"></div>
			
			<div id="chart11"></div>
			<div id="chart17"></div>
			
			<div id="chart12"></div>
			<div id="chart18"></div>
			
			<div id="chart13"></div>
			<div id="chart14"></div>
			<div id="chart15"></div>
			<!--<div id="chart16"></div>-->
			
			
			
			
			
				</div>
		<h3><a href="#">My courses</a></h3>
		<div>
			<div id="accordioncourses">
				<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");
				for(int j=0; j<courses.size(); j++){%>
					<h3><a href="#"><%=courses.get(j).toString()%></a></h3>
					<div>
						<div id="chartc<%=j%>"></div>
						<div id="chartd<%=j%>"></div>
						<div id="chartg<%=j%>"></div>
						<%=stat.meVSGoal2(coursesstudent.get(1))%>
						
						<p>My progress reaching my goal:</p>
						<div id="progressbare<%=j%>"></div>
						<p>My progress reaching the model:</p>
						<div id="progressbarf<%=j%>"></div>
						
						
					</div>
				<%}}%>
			</div>
		</div>
		<h3><a href="#">Extra-curricular</a></h3>
		<div>
			<div id="chart4"></div>	
			<div id="chart5"></div>
			<div id="chart6"></div>
			<div id="chart7"></div>
			<div id="chart10"></div>
		</div>
		<h3><a href="#">All your activities</a></h3>
		<div>
		
			<%ArrayList<Activity> scol=(ArrayList<Activity>) request.getAttribute("scol");%>
<%ArrayList<Activity> extra=(ArrayList<Activity>) request.getAttribute("extra");%>
<%if(scol.size()!=0 || extra.size()!=0){%>
	<%if(scol.size()!=0){%>
		<h3> Scolair activities:</h3>
		<table border="1">
			<tr>
				<th >Course</th>
				<th >Type</th>
				<th >Duration</th>
				<th >Start</th>
				<th >Rating</th>
				<th >Comment</th>
			</tr>
			
			<%for(int i=0; i<scol.size();i++){%>
				<tr>
					<td><%=scol.get(i).getCourse().toString()%></td>
					<td><%=scol.get(i).getTypeEnglish()%></td>
					
					<%if(scol.get(i).equals(request.getAttribute("curract"))){%>
						<td> This is your current activity </td>
					<%}
					else{%>
						<td><%=scol.get(i).getDurationToString()%></td>
					<%}%>
					
					
					<td><%=scol.get(i).startDateToString()%></td>
					<td><%=scol.get(i).getRating()%></td>
					<td><%=scol.get(i).getComment()%></td>
				</tr>
			<%}%>
		</table>
	<%}%>
	
	

	<%if(extra.size()!=0){%>
		<h3> ExtraScolair activities:</h3>
		
		<table border="1">
			<tr>
				<th >   Type     </th>
				<th >   Duration     </th>
				<th >   Start     </th>
				<th >   Rating      </th>
				<th >   Comment      </th>
			</tr>
			
			<%for(int i=0; i<extra.size();i++){%>
				<tr>
					<td><%=extra.get(i).getTypeEnglish()%></td>
					
					<%if(extra.get(i).equals(request.getAttribute("curract"))){%>
						<td> This is your current activity </td>
					<%}
					else{%>
						<td><%=extra.get(i).getDurationToString()%></td>
					<%}%>
					
					
					<td><%=extra.get(i).startDateToString()	%></td>
					<td><%=extra.get(i).getRating()%></td>
					<td><%=extra.get(i).getComment()%></td>
				</tr>
			<%}%>
		</table>
	<%}%>




<%}
else{%>

<p> You have no activities. </p>

<%}%>




	
		
		</div>
</div>