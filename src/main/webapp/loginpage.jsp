<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN PAGE</title>
<link rel="stylesheet" href="login.css" type="text/css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

	<script src="test.js"></script>  
</head>
<body>
<h1 style=text-align:center;>PK's BANK</h1>
<div class="container">

<div class="left">
</div>

<div class="middle">
	<form name="myform" class="data" action="home" method="post" onSubmit="return loginValidate()">
	
		<h3 class="title">LOGIN</h3>
	    <i class="fa fa-user-circle-o fa-2x"></i>
		<label for="user" style=margin-top:5px;><h4>USER ID</h4></label> 
		
		<input id="user" type="number" class="entry" name="userID"> 
		
		<label for="password"><h4>PASSWORD</h4></label>
		
		<input id="password" type="password" class="entry" name="password"> 
		
		<input type="submit" id="sub" class="click" value="SIGN IN">
		
		</br>
		</br>
		<a class="btn btn-primary" href="forgetpassword.jsp" role="button" >forgetPassword?</a>
		
		
	</form>
	<%  if(request.getAttribute("error")!=null)
	    {
	%>
	    <h3 style=text-align:center;color:red;><b><%=request.getAttribute("error")%></b></h3>
	    
	    <%
	    }%>
	    </div>
	    <div class="right">
	    <h1 class="tag">Fresh paint and bright smiles make</br> it home.We make it happen.</h1>
<img  class="rightimg" src="https://images.unsplash.com/photo-1579621970795-87facc2f976d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8YmFua2luZ3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"/>
	    </div>
</div>
</body>
</html>