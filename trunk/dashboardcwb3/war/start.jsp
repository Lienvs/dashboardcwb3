<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="course.Course"%>
	<div id="one">
	<font size="6">
	<b>Start tracking here</b></font>
	</br>
	</br>
	 <font size="3"><font color="black">Is the activity you want to track study-related or not ?</font></font> 
		</br> </br>
		<input type="button" id="onea" class="check" value="Yes" name="wata" >
		<input type="button" id="oneb" class="check" value="No" name="watb" >
	</div>		
		<div style="display: none" id="two">
			Select a course </br></br>
			<select name="welkvak" id="welkvak">
					<%{ArrayList<Course> courses=(ArrayList<Course>) request.getAttribute("courses");%>
 					<%for(int i=0 ; i<courses.size(); i++){%>
 						<option value="<%=courses.get(i).toString()%>"> <%=courses.get(i).toString()%></option>
    				<%}}%>
			</select> 
			</br> </br>
    		<input type="submit" id="twoa" class="check" value="Submit" >
    		<input type="submit" id="backonea" class="check" value="Back">				
		</div>		
		<div style="display: none" id="tree">
			Select what you wish to track </br></br>
			<select name="watextra" id="watextra">
				<option value="Sleep"> Sleep</option>
 				<option value="Sport"> Sport</option>
 				<option value="Nightlife"> Nightlife</option>
			</select></br></br>
    		<input type="submit" id="treea" class="check" value="Submit" >
    		<input type="submit" id="backoneb" class="check" value="Back">	
		</div>		
		<div style="display: none" id="four">
			Select the type of your activity </br> </br>
			<input type="button" id="foura" class="check" value="A lecture" name="typea" ></br>
			<input type="button" id="fourb" class="check" value="Studying" name="typeb" ></br>
			<input type="button" id="fourc" class="check" value="Practice / lab / exercising" name="typec" ></br></br>
    		<input type="submit" id="backtwo" class="check" value="Back">
		</div>
		<div style="display: none" id="five">
							
			Confirm your choice or go back to Home 
			</br></br>
				<form action="/start" method="post">
					<input type="hidden" class="wat" name="wat" value="">
					<input type="hidden" class="vak" name="vak" value="">
					<input type="hidden" class="type" name="type" value="">
					<input  type="hidden" class="watextra" name="watextra" value="">
					<input type="submit" class="check" value="Confirm" name="ok">
				</form>
				<form action="/home" method="post">
					<input type="submit" class="check" value="Home" name="home">
				</form>		
		</div>