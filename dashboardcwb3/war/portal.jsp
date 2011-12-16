<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<html>
<head>
  <script src="/form/gen_validatorv4.js" type="text/javascript"></script> 
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/stylesheets/jquery-ui-1.8.16.custom.css" />

  <script>
  $(document).ready(function() {
    $("#accordion").accordion({
    	autoHeight: false,
		navigation: true
    });
    $( ".check" ).button();	
    $( "#dialog" ).dialog();
    $("#tabs").tabs();
  });
  </script>
</head>
<body>
<div class="tabs" id="tabs">
	<ul>
    	<h1> <img src="stylesheets/Logo.png"/></br></h1>
    	<h4>   The one App to track your studies !</h4>
    	
 
       
    </ul>
</div>
<%String message1=(String) request.getAttribute("message1");
  String message2=(String) request.getAttribute("message2");
  if(message1==null){}
  else{%>
	<div id="dialog" title="<%=message1%>">
		<p><%=message2%></p>
	</div>
	<%}%> 
<div id="accordion">
	<h3><a href="#">Login</a></h3>
	<div>
		<form action="/login" method="post">
			Username: <input type="text" name="username"> <br />
			Password:  <input type="password" name="password" />
			<input type="submit" class="check" name="login" value="Login"/>
		</form>
	</div>
	<h3><a href="#">New on LearnKeeper? Click to Sign up</a></h3>
	<div>
		<form action="/register" id="myform" method="post">
		<p>
			<label for='username'>User Name: </label>
			<input id="username" type="text" name="username" />
		</p>
		<p>
			<label for='password'>Choose a password: </label>
			<input id="password" type="password" name="password" />
		</p>
		<p>
			<label for='confirmedpassword'>Confirm password: </label>
			<input id="confirmedpassword" type="password" name="confirmedpassword" />
		</p>
		<p>
			<label for='firstname'>First Name: </label>
			<input id="firstname" type="text" name="firstname" />
		</p>
		<p>
			<label for='lastname'>Last Name: </label>
			<input id="lastname" type="text" name="lastname"/>
		</p>
		<p>
			<label for='gender'>Gender: </label>
    		<select id="gender"  name="gender">
        		<option value="000" selected="selected">[choose your gender]</option>
        		<option value="male">Male</option>
        		<option value="female">Female</option>
        	</select>
		</p>
		<p>
			<label for='rnumber'>r-number: </label>
			<input id="rnumber" type="text" name="rnumber"/>
		</p>
		<p>
			<input type="submit" id="submit" class="check" name="submit" value="Sign up"/>
		</p>
		</form>
		
	
	</div>	
</div>
</body>
</html>